/**
 * Created by zheng on 2015/4/18.
 */
var actorTemp =[];
var unitTemp =[];
$(function () {
    $('#NewOtherTable').bootstrapTable({
        url: '/api/workflow/order/xgfan/newOther/all',
        sidePagination:"server",
        columns: [{
            radio:true
            //visible:false
        },{
            field: 'id',
            title: 'id',
            sortable: true,
            visible:false
        },{
            field:'otherName',
            title:'产品名称',
            sortable:true
        },{
            field:'otherStaRole',
            title:'角色',
            sortable:true,
            formatter:"typeTran"
        },{
            field:'otherStaScore',
            title:'得分',
            sortable:true
        },{
            field:'newOtherDate',
            title:'日期',
            sortable:true
        },{
            field:'Status',
            title:'状态',
            sortable:true,
            formatter:'statusTran'
        }],
        responseHandler: tableTrans
    });
    $('#actorTable').bootstrapTable({
        columns: [{
            field:'actor',
            title:'成员',
            editable:true,
            sortable:true,
            footerFormatter:"totalNameFormatter"
        },{
            field:'staId',
            title:'工号',
            editable:true,
            sortable:true
        },{
            field:'role',
            title:'角色',
            editable:true,
            sortable:true
        },{
            field:'marks',
            title:'分数',
            editable:true,
            sortable:true,
            footerFormatter:"totalMarksFormatter"
        },{
            field:'operate',
            title:'操作',
            sortable:true,
            formatter:"operateOAFormatter",
            events:"operateOAEvents"
        }],
        data:actorTemp
    });
    $('#unitTable').bootstrapTable({
        columns: [{
            field: 'unit_id',
            title: 'id',
            sortable: true,
            visible:false,
            footerFormatter:"totalUnitFormatter"
        },{
            field:'unit_rank',
            title:'单位排名',
            editable:true,
            sortable:true
        },{
            field:'unit_name',
            title:'单位名称',
            editable:true,
            sortable:true
        },{
            field:'operate',
            title:'操作',
            sortable:true,
            formatter:"operateOUFormatter",
            events:"operateOUEvents"
        }],
        data:unitTemp
    });
    $('#otherUnitInfo').hide();
    $('#reply-box').hide();
    $('#reply').hide();
});
//监听 点击论文表
$('#NewOtherTable').on('click-row.bs.table', function (e, row, $element) {
    $('form input').val(null);
    actorTemp =[];
    var orderId = row["id"];
    var status = row["Status"];
    if (status == "Blank" || status == "Uncomplete" || status.indexOf('Refuse') >= 0) {
        editableForm();
        $('#confirm').show();
        $('#save').show();
        $('#orderBack').hide();
        $('#del').show();
        workflow.latestTask(orderId).success(function(currentTask){
            var taskId = currentTask[0]['id'];
            $('#WF_Task').val(taskId);
            if (status.indexOf('Refuse') >= 0) {
                $('#reply').show();
                $('#reply-display').show();
                var reply = $('#reply-display').children('p');
                var who = $('#reply-display').children('small');
                reply.empty();
                who.empty();
                if (status.indexOf("Col") >= 0) {
                    reply.append(currentTask[0]['variableMap']['replyByCol']);
                    who.append("学院批复");
                } else {
                    reply.append(currentTask[0]['variableMap']['replyByDep']);
                    who.append("管理部门批复");
                }
            } else {
                $('#reply').hide();
            }
        });
    } else {
        $('#del').hide();
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
    $('#othersInfo').autofill(row, {
        findbyname: true,
        restrict: false
    });
    if(row['isFirInOther']=="true"){
        if(row['units']!=null){
            unitTemp=row['units'];
        }
        $('#unitInfo').show();
        $('#unitTable').bootstrapTable("load",unitTemp);
    }
    $("#actorTable").bootstrapTable('load',actorTemp);
    showForm();
});
//是否为第一单位
$('#isFirInOther').change(function(){
    firstOrOther();
});
//监听 点击保存
$("#save").click(function(){
    saveInfo();
});
//监听 点击确认
$("#confirm").click(function(){
    confirmInfo();
});
//监听 点击返回
$("#back").click(function(){
    showTable();
});
//监听  删除
$("#del").click(function(){
    deleteInfo();
});
//监听 点击撤回
$("#orderBack").click(function(){
    orderBackInfo();
});
/*统一提交*/
$("#submit").click(function(){
    submitAllPInfo();
});
/*添加新药品*/
$("#addNewOther").click(function(){
    startNewInfo();
});
/*
* 添加新药品
* */
function startNewInfo(){
    editableForm();
    $('form input').val(null);
    actorTemp =[{ "actor":"","staId":"","role":"","marks":"0"}];
    $('#actorTable').bootstrapTable('load',actorTemp);
    workflow.startOrder("xgfan","basicProcess_Beta","newOther").success(function(data){
        afterSuccess("新建成功！");
        showTable();
    });
}
/*
* 保存
* */
function saveInfo(){
    $('#IsComplete').val(false);
    var jsonData = $("#othersInfo").serializeJSON();
    $.each(jsonData,function(key,value){
        if(value==""||value==null){
            delete jsonData[key];
        }
    });
    jsonData["actors"]=getActorsData();
    if($('#isFirInOther').val()=='false'){
        jsonData['units']=getUnitsData();
    }
    jsonData["WF_User"]="xgfan";
    workflow.execute('xgfan','',jsonData).success(function(){
        afterSuccess("保存成功！");
        showTable();
    });
}
/*
* 确认
* */
function confirmInfo(){
    $('#IsComplete').val(true);
    var jsonData = $("#othersInfo").serializeJSON();
    $.each(jsonData,function(key,value){
        if(value==""||value==null){
            delete jsonData[key];
        }
    });
    jsonData["actors"]=getActorsData();
    if($('#isFirInOther').val()=='false'){
        jsonData['units']=getUnitsData();
    }
    jsonData["WF_User"]="xgfan";
    jsonData["WF_Actor"]="xgfan";
    workflow.execute('xgfan','',jsonData).success(function(){
        afterSuccess("保存成功！");
        showTable();
    });
}
/*
* 删除
* */
function deleteInfo(){
    var row = $('#NewOtherTable').bootstrapTable('getSelections')[0];
    var order = row['id'];
    workflow.delOrder(order).success(function(){
        afterSuccess("删除成功！");
        showTable();
    });
}
/*
* 提交
* */
function submitAllPInfo(){
    var submitData='WF_User=xgfan';
    workflow.submitAll(submitData).success(function(){
        afterSuccess("提交成功");
        showTable();
    })
}
/*
* 撤回
* */
function orderBackInfo(){
    editableForm();
    var row = $('#NewOtherTable').bootstrapTable('getSelections')[0];
    var order = row['id'];
    var jsonData = Object();
    jsonData['order'] = order;
    jsonData['user'] = 'xgfan';
    window.workflow.getBack('xgfan',order).success(function(){
        afterSuccess("已撤回");
        showTable();
        $('#del').show();
    });
}