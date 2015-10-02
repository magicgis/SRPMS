/**
 * Created by zheng on 2015/5/11.
 */
var actorTemp =[];
$(function(){
    showForm();
    $('#BookTable').bootstrapTable({
        //url: '/api/workflow/order/xgfan/project/all',
        sidePagination:"server",
        columns: [{
            radio:true
        },{
            field: 'id',
            title: 'id',
            sortable: true,
            visible:false
        },{
            field:'projName',
            title:'著作名称',
            sortable:true
        },{
            field:'projType',
            title:'著作类型',
            sortable:true,
            formatter:"typeTran"
        },{
            field:'bkWdNum',
            title:'著作总字数',
            sortable:true,
            formatter:"rateUnitTran"
        },{
            field:'actSolTime',
            title:'著作获奖情况',
            sortable:true
        },{
            field:'projRank',
            title:'出版社',
            sortable:true,
            formatter:"rankTran"
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
            field:'units',
            title:'归属单位',
            editable:true,
            sortable:true
        },{
            field:'operate',
            title:'操作',
            sortable:true,
            formatter:"operateFormatter",
            events:"operateEvents"
        }],
        data:actorTemp
    });
    $('#reply-box').hide();
    $('#reply').hide();
});

//监听 点击表
$('#BookTable').on('click-row.bs.table', function (e, row, $element) {
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
    $('#book').autofill(row, {
        findbyname: true,
        restrict: false
    });
    $("#actorTable").bootstrapTable('load',actorTemp);
    showForm();
});
//监听 申请差异
$("#DiffAdd").click(function(){
    different();
});
$('#isFirInOther').change(function(){
    firstOrOther();
});
/*添加新著作*/
$("#addBook").click(function(){
    startNewInfo();
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