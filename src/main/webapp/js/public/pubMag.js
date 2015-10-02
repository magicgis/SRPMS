/**
 * Created by zheng on 2015/5/8.
 */
//因为数据特殊性需要重写
function tableTranMags(res) {
    var ans = res;
    $.each(res["rows"],function(index,value){
        /*取出variableMap*/
        var maps=value['variableMap'];
        /*把string放入*/
        for(var key in maps) {
            if (typeof(maps[key]) == 'string'||typeof(maps[key]) == 'object') {
                ans["rows"][index][key] = maps[key];
            }
        }
        /*拿出最新的args*/
        var temp=maps[getSubmission(maps)];
        /*瞎写都能生效……*/
        if(temp!=undefined||temp!=null){
            $.each(temp,function(key,value){
                ans["rows"][index][key]=temp[key];
            });
        }
    });
    //console.log(ans);
    return ans;
}

function typeTran(value, row) {
    if (value == 'conferPaper') {
        return '会议论文';
    } else if (value == 'magPaper') {
        return '期刊论文';
    } else if (value == 'newsPaper') {
        return '报刊论文';
    } else {
        return;
    }
}

// 表单不可编辑
function uneditableForm(){
    $('form input').attr("disabled", "disabled");
}
// 表单可编辑
function editableForm(){
    $('form input').removeAttr("disabled", "disabled");
}
//显示详情
function showForm(){
    $('#magTable-box').addClass('collapsed');
    $('#mag-box').removeClass('collapsed');
}
// 显示总览
function showTable(){
    $('#MagTable').bootstrapTable('refresh');
    $('#magTable-box').removeClass('collapsed');
    $('#mag-box').addClass('collapsed');
}
/**
 *查看/编辑样式
 *
 */
function view(index,row,value){
    {
        return [
            '<a class="edit ml10" href="javascript:void(0)" title="Edit">',
            '<i class="ace-icon fa fa-pencil bigger-130"></i>查看',
            '</a>'
        ].join('');
    }
}
/**
 * 显示成功提示
 * @param msg
 */
function afterSuccess(msg){
    $('form input').val(null);
    $('#paper-box').addClass('collapsed');
    $('#info_alert').empty();
    $('<div class="alert alert-block alert-success" id="success_info"></div>').appendTo('#info_alert');
    $('<button type="button" class="close" data-dismiss="alert"><i class="ace-icon fa fa-times">' +
    '</i></button><i class="ace-icon fa fa-check green" id="success_icon"></i>').appendTo('#success_info');
    $('#success_icon').append(msg);
    setTimeout(function(){
        $('#info_alert').empty();
    },1500);
}
/**
 * 文件上传
 */
function upToLoadFile(){
    $('#upload').Huploadify({
        auto:true,
        fileTypeExts:'*.txt;*.jpg;*.jpeg;*.JPG;*.JPEG;*.png;*.pdf;*.doc;*.docx;',
        multi:true,
        fileSizeLimit:99999,
        showUploadedPercent:true,//是否实时显示上传的百分比，如20%
        showUploadedSize:true,
        method:'post',
        uploader:'/api/file/upload',
        onUploadSuccess:function(file, data){
            var fileInfo={};
            fileInfo['size']=formatFileSize(file.size,false);
            fileInfo['fileKey']=data;
            filesData[file.name]=fileInfo;
            console.log(filesData);
            scanFiles(filesData);
        }
    });
}
/**
 * 显示上传的资料
 * @param filesData
 */
function scanFiles(filesData){
    for(var key in filesData){
        var fileId=filesData[key]['fileKey'];
        var fileSize=filesData[key]['size']
        $('#downFiles').prepend('<li id="li'+fileId+'" class="dd-item"> ' +
        '<div class="dd-handle">' +
        '<font size="1">'+key+'</font>&nbsp;&nbsp;&nbsp;&nbsp;'+
        '<span>（'+fileSize+'）</span>'+
        '<div class="pull-right action-buttons">' +
        '<a class="blue" style="cursor:pointer" onclick="downFile(\''+fileId+'\')" id="down_'+fileId+'">' +
        '<i class="ace-icon fa  fa-cloud-download  bigger-140"></i>' +
        '</a>&nbsp;&nbsp;&nbsp;&nbsp;' +
        '<a class="fd red" style="cursor:pointer" onclick="delFile(\''+fileId+'\')" id="del_'+fileId+'">' +
        '<i class="ace-icon fa fa-trash-o bigger-140"></i>' +
        '</a>' +
        '</div>'+
        '</div> ' +
        '</li>');
    }
}
/**
 *文件下载
 * @param fileId
 */
function downFile(fileId){
    var url='/api/file/'+fileId;
    //window.location.href=url;
    $.ajax({
        url: url,
        type: 'get',
        success: function() {
            window.location = url;
        }
    });

    /*    var xhr = new XMLHttpRequest();
     xhr.open( "GET", url);
     xhr.setRequestHeader('Authorization', $.cookie('srpms_token'));
     xhr.addEventListener( "load", function(){
     console.log(this.responseText);
     data = this.responseText;
     data = "data:text/csv;base64,"+btoa(data);
     document.location = data;
     }, false);
     xhr.send(null);*/
}
/**
 * 删除上传的文件
 * @param fileId
 */
function delFile(fileId){
    /*    $("#li" + fileId).remove();*/
    console.log(filesData)
    BootstrapDialog.confirm({
        title: '提示！',
        message: '你确定要删除该项吗?',
        type: BootstrapDialog.TYPE_WARNING,
        closable: true,
        draggable: true,
        btnCancelLabel: '取消',
        btnOKLabel: '确定',
        btnOKClass: 'btn-warning',
        callback: function(result) {
            if(result) {
                $.ajax({
                    type:'DELETE',
                    url:'/api/file/'+fileId,
                    success:function(){
                        for(var key in filesData){
                            if(filesData[key]['fileKey']==fileId){
                                delete filesData[key];
                            }
                        }
                        $("#li" + fileId).remove();
                    }
                });
            }
        }
    });
}
/**
 * 文件大小
 * @param size byKB
 */
function formatFileSize(size,byKB){
    if (size>1024*1024 && !byKB){
        size = (Math.round(size * 100 / (1024 * 1024)) / 100).toString() + 'MB';
    }
    else{
        size = (Math.round(size * 100 / 1024) / 100).toString() + 'KB';
    }
    return size;
}
/**
 * 显示文件信息
 * @param data
 */
function showFiles(data){
    if(data==null||data==undefined){
        $('#downFiles').empty();
    }else{
        $('#downFiles').empty();
        scanFiles(data);
    }
}

//监听 点击论文表
$('#MagTable').on('click-row.bs.table', function (e, row, $element) {
    var orderId = row["id"];
    window.location.href = '/magazine/' + orderId;
});