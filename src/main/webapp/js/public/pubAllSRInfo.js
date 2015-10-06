/**
 * Created by zheng on 2015/6/8.
 */
/**
 * 不同项目名称所对应得表单
 * */
var selectDefferent = {
    "project": "../public/projectForm.jsp",
    "patent": "",
    "paper": "../public/paperForm.jsp",
    "newOther": "",
    "newMedicine": "",
    "instrument": "",
    "food": "",
    "change": "",
    "award": "",
    "appraise": ""
};
var wfTypeTans = {
    "project": "项目",
    "patent": "专利",
    "paper": "论文",
    "newOther": "其他新产品",
    "newMedicine": "新药",
    "instrument": "新器械",
    "food": "新食品",
    "change": "差异",
    "award": "成果获奖",
    "appraise": "成果鉴定"
};
/**
 *查看/编辑样式
 *
 * */
function view(index, row, value) {
    {
        return [
            '<a class="edit ml10" href="javascript:void(0)" title="Edit">',
            '<i class="ace-icon fa fa-pencil bigger-130"></i>查看',
            '</a>'
        ].join('');
    }
}
//成员表
function scanActorTable() {
    $('#actorTable').bootstrapTable({
        columns: [
            {
                field: 'staff.id',
                title: '工号',
                sortable: true,
                visible: false
            }, {
                field: 'rank',
                title: '排名',
                sortable: true,
                //editable: true,
                footerFormatter: "totalNameFormatter"
            }, {
                field: 'staff.name',
                title: '成员',
                sortable: true
            }, {
                field: 'role',
                title: '角色',
                sortable: true
            }, {
                field: 'score',
                title: '分数',
                sortable: true,
                //editable: true,
                footerFormatter: "totalMarksFormatter"
            }, {
                field: 'unit',
                title: '归属单位',
                //editable: true,
                sortable: true
            }, {
                field: 'operate',
                title: '操作',
                sortable: true,
                formatter: "operateFormatter",
                events: "operateEvents"
            }],
        data: actorTemp
    });
}
// 成员翻译
function actorTran(value,row){
    if(value!=undefined && value!=null && value!="")
        return value.substring(0,value.length-1);
    else
        return;
}
// 状态翻译
function statusTran(value, row) {
    if (value == 'Blank') {
        return '待填写';
    } else if (value == 'Uncomplete') {
        return '已保存';
    } else if (value == 'Complete') {
        return '等待确认';
    } else if (value == "ApprovedByCol") {
        return '待学院统一提交';
    } else if (value == 'WaitForSubmit') {
        return '待统一提交';
    } else if (value == 'WaitForCol') {
        return '学院审核中';
    } else if (value == 'WaitForDep') {
        return '管理部门审核中';
    } else if (value == 'RefuseByDep') {
        return '管理部门驳回，待学院审核'
    } else if (value == 'RefuseByCol') {
        return '学院驳回，待修改'
    }
}
//论文类型
function paperType() {
    if ($('#paperType').val() == "conferPaper") {
        $('#paperType').val("会议论文")
    } else if ($('#paperType').val() == "magPaper") {
        $('#paperType').val("期刊论文");
    } else if ($('#paperType').val() == "newsPaper") {
        $('#paperType').val("报刊论文")
    } else {

    }
}
/*
 * 显示表单时的类型翻译
 * @param收录类型||论文类型
 * */
function DisplayForm($type, ItemValue) {
    if (ItemValue == null || ItemValue == "") {
        $type[0].selectize.setValue("");
    } else {
        $type[0].selectize.setValue(ItemValue);
    }
}
//显示Mag、Confer还是newsPaper
function magOrConfer() {
    if ($('#paperType').val() == "conferPaper") {
        $('#conferInfo').show();
        $('#newsInfo').hide();
        $('#magInfo').hide();
        $('.paperWord').hide();
        $('.mag-input').val(null);
        $('.news-input').val(null);
    } else if ($('#paperType').val() == "magPaper") {
        $('#conferInfo').hide();
        $('#newsInfo').hide();
        $('#magInfo').show();
        $('.paperWord').hide();
        $('.confer-input').val(null);
        $('.news-input').val(null);
    } else if ($('#paperType').val() == "newsPaper") {
        $('#newsInfo').show();
        $('.paperWord').show();
        $('#conferInfo').hide();
        $('#magInfo').hide();
        $('.mag-input').val(null);
        $('.confer-input').val(null);
    } else {
        $('#newsInfo').hide();
        $('#conferInfo').hide();
        $('#magInfo').hide();
        $('.paperWord').hide();
        $('.mag-input').val(null);
        $('.confer-input').val(null);
        $('.news-input').val(null);
    }
}
function wfTypeTran(value, row) {
    for (var key in wfTypeTans) {
        if (key == value) {
            return wfTypeTans[key];
        }
    }
}
/*function scanForm(wfType){
 for(var key in selectDefferent) {
 if (key==wfType) {
 $("#confirmForm").empty();
 var url=selectDefferent[key];
 $("#confirmForm").load(url*//*,function(){}*//*);
 }
 }
 }*/
function showForm() {
    $('#confirmTable-box').addClass('collapsed');
    $('#confirm-box').removeClass('collapsed');
    $('#addActor').hide();
}
//显示总览
function showTable(newUrl) {
    $('#confirmTable-box').removeClass('collapsed');
    $('#confirm-box').addClass('collapsed');
    $('#allSRInfoTable').bootstrapTable('refresh', {url: newUrl});
}
function uneditableForm() {
    $('form input').attr("disabled", "disabled");
    $('form select').attr("disabled", "disabled");
    $('#actorTable').bootstrapTable('hideColumn', 'operate');
}
function totalNameFormatter(data) {
    return "共" + data.length + "人";
}

function totalMarksFormatter(data) {
    var total = 0;
    $.each(data, function (i, row) {
        if(row.score !== null && row.score !== undefined && row.score !=="") {
            total += +(row.score.toString().substring(0));
        }
    });
    return '共' + total.toFixed(0) + "分";
}
//显示上传的资料
function scanFiles(filesData) {
    for (var key in filesData) {
        var fileId = filesData[key]['fileKey'];
        var fileSize = filesData[key]['size'];
        $('#downFiles').prepend('<li id="li' + fileId + '" class="dd-item"> ' +
        '<div class="dd-handle">' +
        '<font size="1">' + key + '</font>&nbsp;&nbsp;&nbsp;&nbsp;' +
        '<span>（' + fileSize + '）</span>' +
        '<div class="pull-right action-buttons">' +
        '<a class="blue" style="cursor:pointer" onclick="downFile(\'' + fileId + '\')" id="down_' + fileId + '">' +
        '<i class="ace-icon fa  fa-cloud-download  bigger-140"></i>' +
        '</a>&nbsp;&nbsp;&nbsp;&nbsp;' +
        '<a class="fd red" style="cursor:pointer" onclick="delFile(\'' + fileId + '\')" id="del_' + fileId + '">' +
        '<i class="ace-icon fa fa-trash-o bigger-140"></i>' +
        '</a>' +
        '</div>' +
        '</div> ' +
        '</li>');
    }
}
/**
 *文件下载
 * @param fileId
 **/
function downFile(fileId) {
    var url = '/api/file/' + fileId;
    //window.location.href=url;
    $.ajax({
        url: url,
        type: 'get',
        success: function () {
            window.location = url;
        }
    });
}
/**
 * 删除上传的文件
 * @param fileId
 * */
function delFile(fileId) {
    $("#li" + fileId).remove();
    $.ajax({
        type: 'DELETE',
        url: '' + fileId,
        success: function () {
            $("#li" + fileId).remove();
        }
    });
}
/**
 * 文件大小
 * @param size byKB
 * */
function formatFileSize(size, byKB) {
    if (size > 1024 * 1024 && !byKB) {
        size = (Math.round(size * 100 / (1024 * 1024)) / 100).toString() + 'MB';
    }
    else {
        size = (Math.round(size * 100 / 1024) / 100).toString() + 'KB';
    }
    return size;
}
/**
 * 显示文件信息
 */
function showFiles(data) {
    if (data == null || data == undefined) {
        $('#downFiles').empty();
    } else {
        $('#downFiles').empty();
        scanFiles(data);
    }
}