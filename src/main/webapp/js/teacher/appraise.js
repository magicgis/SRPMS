/**
 * Created by huyuanyuan555 on 2015/4/19.
 */
var actorTemp =[{"actor":"",
    "marks":"0"  }];
var unitTemp =[{"unitName":"",
    "rank":""  }];

$(function () {
    $('#AppraiseTable').bootstrapTable({
        url: '/api/workflow/xgfan/order/all',
        columns: [{
            radio:true
            //visible:false
        },{
            field: 'id',
            title: 'id',
            sortable: true,
            visible:false
        },{
            field:'achName',
            title:'成果名称',
            sortable:true
        },{
            field:'achType',
            title:'成果类型',
            sortable:true
           // formatter:"typeTran"
        }, {
            field:'checkName',
            title:'鉴定单位',
            sortable:'true'
        },{
            field:'appraiseDate',
            title:'鉴定日期',
            sortable:true
        },{
            field:'WF_Actor',
            title:'参与者',
            sortable:true
        },{
            field:'Status',
            title:'状态',
            sortable:true,
            formatter:'statusTran'
        }],
        responseHandler: function (res) {
            //$('.bs-checkbox').hide();
            var re = res;
            for (var i = 0; i < re.length; i++) {
                /*取出variableMap*/
                var p=re[i]['variableMap'];
                /*把string放入*/
                for(var key in p) {
                    if (typeof(p[key]) == 'string'||typeof(p[key]) == 'array') {
                        re[i][key] = p[key];
                    }
                }
                /*拿出最新的args*/
                if(p['Status']!='Blank'){
                    var temp=p[getSubmission(p)];
                    /*瞎写都能生效……*/
                    $.each(temp,function(key,content){
                        re[i][key]=temp[key];
                    });
                }
                //console.log(res[i]);
                //console.log('ok');
            }
            var totalOrder = re;
            return totalOrder;
        }
    });

    $('#actorTable').bootstrapTable({
        columns: [{
            field:'actor',
            title:'姓名',
            editable:true,
            sortable:true,
            footerFormatter:"totalNameFormatter"
        },{
            field:'staId',
            title:'工号',
            editable:true,
            sortable:true
            //footerFormatter:"totalNameFormatter"
        },{
            field:'marks',
            title:'分数',
            editable:true,
            sortable:true,
            footerFormatter:"totalMarksFormatter"
        },{
            field:'role',
            title:'角色',
            editable:true,
            sortable:true
          //  footerFormatter:"totalNameFormatter"
        },{
            field:'operate',
            title:'操作',
            sortable:true,
            formatter:"operateFormatter",
            events:"operateEvents"
        }],
        data:actorTemp
    });

    $('#unitTable').bootstrapTable({
        columns: [{
            field:'unitName',
            title:'单位名称',
            editable:true,
            sortable:true,
            footerFormatter:"totalNameFormattera"
        },{
            field:'unitRank',
            title:'单位排名',
            editable:true,
            sortable:true
           // footerFormatter:"totalNameFormatter"
        },{
            field:'operate',
            title:'操作',
            sortable:true,
            formatter:"operateFormattera",
            events:"operateEventsa"
        }],
        data:unitTemp
    });


    $('#reply-box').hide();

    $('#reply').hide();


    unitTableShow();
});
//成员表的操作
window.operateEvents = {
    'click .removeActor': function (e, value, row, index) {
        $('#actorTable').bootstrapTable('remove', {
            field: 'actor',
            values: [row.actor]
        });
    }
};


function operateFormatter(value, row, index) {
    return [
        '<a class="removeActor" href="javascript:void(0)" title="Remove" >',
        '<i class="glyphicon glyphicon-remove"></i>',
        '</a>'
    ].join('');
}

//-------------------------共有单位表的操作-----------
window.operateEventsa = {
    'click .removeUnit': function (e, value, row, index) {
        $('#unitTable').bootstrapTable('remove', {
            field: 'unitName',
            values: [row.unitName]
        });
    }
};

function operateFormattera(value, row, index) {
    return [
        '<a class="removeUnit" href="javascript:void(0)" title="Remove" >',
        '<i class="glyphicon glyphicon-remove"></i>',
        '</a>'
    ].join('');
}
//监听 点击成果鉴定表
$('#AppraiseTable').on('click-row.bs.table', function (e, row, $element) {
    $('form input').val(null);
    actorTemp =[{"actor":"",
        "marks":"0"  }];
    var orderId = row["id"];
    var status = row["Status"];
    if (status == "Blank" || status == "Uncomplete" || status.indexOf('Refuse') >= 0) {
        editableForm();
        $('#confirm').show();
        $('#save').show();
        $('#orderBack').hide();
        $('del').show();
        $.ajax({
            type: 'get',
            url: '/api/workflow/ord' + orderId + '/task',
            success: function (currentTask) {
                var taskId = currentTask[0]['id'];
                $('#WF_Task').val(taskId);
                if(status.indexOf('Refuse')>=0){
                    $('#reply').show();
                    $('#reply-display').show();
                    var reply = $('#reply-display').children('p');
                    var who = $('#reply-display').children('small');
                    reply.empty();
                    who.empty();
                    if(status.indexOf("Col")>=0){
                        reply.append(currentTask[0]['variableMap']['replyByCol']);
                        who.append("学院批复");
                    }else{
                        reply.append(currentTask[0]['variableMap']['replyByDep']);
                        who.append("管理部门批复");
                    }
                }else{
                    $('#reply').hide();
                }
            }
        });
    } else {
        $('del').hide();
        if(status == 'Complete'||status == 'WaitForSubmit'){
            $('#orderBack').show();
        }else{
            $('#orderBack').hide();
        }
        $('#confirm').hide();
        $('#save').hide();
        uneditableForm();
    }
    if(row['actors']!=null) {
        actorTemp = row['actors'];
    }
    $('#appraise').autofill(row, {
        findbyname: true,
        restrict: false
    });
    //magOrConfer();
    //$("#actorTable").bootstrapTable('load',actorTemp);
    showForm();
});

//监听 修改成员表
$('#actorTable').on('editable-save.bs.table', function (e, row, $element){
    $('#actorTable').bootstrapTable("load",actorTemp);
});

//监听 添加成员
$("#addActor").click(function(){
    actorTemp.push({
        "actor":"",
        "marks":"0"}) ;
    $('#actorTable').bootstrapTable("load",actorTemp);
});
//监听 项目共有
$('#isComAch').change(function(){
    unitTableShow();
});

//监听 修改单位表
    $('#unitTable').on('editable-save.bs.table', function (e, row, $element){
        $('#unitTable').bootstrapTable("load",unitTemp);
    });

//监听  添加单位
    $("#addUnit").click(function(){
        unitTemp.push({
            "unitName":"",
            "rank":""}) ;
        $('#unitTable').bootstrapTable("load",unitTemp);
    });

////监听 点击保存
//$("#save").click(function(){
//    save();
//    showTable();
//});
////监听 点击确认
//$("#confirm").click(function(){
//    confirm();
//    showTable();
//});
//监听 点击返回
$("#back").click(function(){
    showTable();
});

//$("#del").click(function(){
//    delOrder();
//    showTable();
//});
//
////监听 点击撤回
//$("#orderBack").click(function(){
//    getOrderBack();
//    showTable();
//});

///*统一提交*/
//$("#submit").click(function(){
//    submit();
//});
/*编辑成果鉴定*/
$("#editAppraise").click(function(){
    //start();
    showForm();
});
/*//撤回order
function getOrderBack(){
    var row = $('#AppraiseTable').bootstrapTable('getSelections')[0];
    var order = row['id'];
    var jsonData = Object();
    jsonData['order'] = order;
    jsonData['user'] = 'xgfan';
    $.ajax({
        type:'post',
        url:'/controller/workflow/getBack',
        data:JSON.stringify(jsonData),
        dataType : 'json',
        contentType:'application/json;charset=UTF-8',
        success:function(){
            afterSuccess("已撤回");
        }
    })
}

//删除order
function delOrder(){
    var row = $('#AppraiseTable').bootstrapTable('getSelections')[0];
    var order = row['id'];
    $.ajax({
        type:'DELETE',
        url:'/controller/workflow/ord'+order,
        success:function(){
            afterSuccess("已删除");
        }
    })
}

//提交
function submit(){
    $.ajax({
        type:'post',
        url:'/controller/workflow/submitAll',
        data:"WF_User=xgfan",
        success:function(){
            afterSuccess("提交成功");
            showTable();
        }
    });
}
//新建
function start(){
    $('form input').val(null);
    actorTemp = [{"actor":"",
        "marks":"0"  }];
    $('#actorTable').bootstrapTable('load',actorTemp);
    $.ajax({
        type:'post',
        url:'/controller/workflow/start',
        data:{"WF_Process":"basicProcess_Beta",
            "WF_Type":"paper", "WF_User":"xgfan"},
        success: function(data){
            afterSuccess("新建成功");
            showTable();
        }
    });
}
//确认
function confirm(){
    $('#IsComplete').val(true);
    var jsonData = $("#paper").serializeJSON();
    jsonData["actors"]=getActorsData();
    jsonData["WF_User"]="xgfan";
    $.ajax({
        type:'post',
        url:'/controller/workflow/execute',
        data:JSON.stringify(jsonData),
        dataType : 'json',
        contentType:'application/json;charset=UTF-8',
        success:function(){
            afterSuccess("已确认");
            showTable();
        }
    });
}
//保存所有的数据
function save(){
    $('#IsComplete').val(false);
    var jsonData = $("#appraise").serializeJSON();                                            //{----???------}
    jsonData["actors"]=getActorsData();
    jsonData["WF_User"]="xgfan";
    $.ajax({
        type:'post',
        url:'/controller/workflow/execute',
        data:JSON.stringify(jsonData),
        dataType : 'json',
        contentType:'application/json;charset=UTF-8',
        success:function(){
            afterSuccess("保存成功");
            showTable();
        }
    });
}
//获取成员数据
//function getActorsData(){
//    return $("#actorTable").bootstrapTable('getData');
//}
//成功提示
function afterSuccess(msg){
    $('form input').val(null);
    $('#paper-box').addClass('collapsed');
    $('#info_alert').empty();
    $('<div class="alert alert-block alert-success" id="success_info"></div>').appendTo('#info_alert');
    $('<button type="button" class="close" data-dismiss="alert"><i class="ace-icon fa fa-times">' +
    '</i></button><i class="ace-icon fa fa-check green" id="success_icon"></i>').appendTo('#success_info');
    $('#success_icon').append(msg);
}*/

