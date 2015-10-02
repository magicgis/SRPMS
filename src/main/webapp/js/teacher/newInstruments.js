/**
 * Created by zheng on 2015/4/17.
 */
var actorTemp =[];
var unitTemp =[];
$(function () {
    showForm();
    $('#NewInstrumentsTable').bootstrapTable({
        url: '/api/workflow/order/xgfan/newInstruments/all',
        sidePagination:"server",
        columns: [{
            radio:true
        },{
            field: 'id',
            title: 'id',
            sortable: true,
            visible:false
        },{
            field:'apparName',
            title:'医疗器械名称',
            sortable:true
        },{
            field:'apparStaRole',
            title:'角色',
            sortable:true,
            formatter:"typeTran"
        },{
            field:'apparStaScore',
            title:'得分',
            sortable:true
        },{
            field:'newInstrumentsDate',
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
            //footerFormatter:"totalNameFormatter"
        },{
            field:'marks',
            title:'分数',
            editable:true,
            sortable:true,
            footerFormatter:"totalMarksFormatter"
            //footerFormatter:"totalMarksFormatter"
        },{
            field:'operate',
            title:'操作',
            sortable:true,
            formatter:"operateIAFormatter",
            events:"operateIAEvents"
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
            formatter:"operateIUFormatter",
            events:"operateIUEvents"
        }],
        data:unitTemp
    });
    $('#instrUnitInfo').hide();
    $('#reply-box').hide();
    $('#reply').hide();
});
//监听 点击论文表
$('#NewInstrumentsTable').on('click-row.bs.table', function (e, row, $element) {
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
    $('#instruments').autofill(row, {
        findbyname: true,
        restrict: false
    });
    if(row['isFirInInstru']=="false"){
        if(row['units']!=null){
            unitTemp=row['units'];
        }
        $('#instrUnitInfo').show();
        $('#unitTable').bootstrapTable("load",unitTemp);
    }
    $("#actorTable").bootstrapTable('load',actorTemp);
    showForm();
});
//医疗器械类别
$('#apparType').change(function(){
    $('#explainInfo').show();
});
//是否为第一单位
$('#isFirInInstru').change(function(){
    firstOrOther();
});
//监听 点击保存
$("#save").click(function(){
});
//监听 点击确认
$("#confirm").click(function(){
});
//监听 点击返回
$("#back").click(function(){
});
//监听  删除
$("#del").click(function(){
});
//监听 点击撤回
$("#orderBack").click(function(){
});
/*统一提交*/
$("#submit").click(function(){
});
/*添加新药品*/
$("#addInstruments").click(function(){
});
