/**
 * Created by zheng on 2015/4/24.
 */
var actorTemp =[];
var unitTemp = [];
$(function () {
    showForm()//需要删掉
    $('#AwardTable').bootstrapTable({
        url: '/api/workflow/order/xgfan/award/all',
        columns: [{
            radio: true
            //visible:false
        }, {
            field: 'id',
            title: 'id',
            sortable: true,
            visible: false
        }, {
            field: 'achName',
            title: '成果名称',
            sortable: true
        }, {
            field: 'achType',
            title: '成果类型',
            sortable: true
            // formatter:"typeTran"
        }, {
            field: 'awardName',
            title: '奖项名称',
            sortable: 'true'
        }, {
            field: 'awardRank',
            title: '奖项等级',
            sortable: true
        }, {
            field: 'role',
            title: '角色',
            sortable: true
        }, {
            field: 'marks',
            title: '得分',
            sortable: true
        }, {
            field: 'awardTime',
            title: '获奖时间',
            sortable: true
        }, {
            field: 'Status',
            title: '状态',
            sortable: true,
            formatter: 'statusTran'
        }],
        responseHandler:tableTrans
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
            formatter:"operateAAFormatter",
            events:"operateAAEvents"
        }],
        data:actorTemp
    });

    $('#unitTable').bootstrapTable({
        columns: [{
            field:'unitName',
            title:'单位名称',
            editable:true,
            sortable:true,
            footerFormatter:"totalUnitFormatter"
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
            formatter:"operateAUFormatter",
            events:"operateAUEvents"
        }],
        data:unitTemp
    });
    $('#reply-box').hide();
    $('#reply').hide();
});
//监听 点击获奖成果表
$('#AwardTable').on('click-row.bs.table', function (e, row, $element) {
    $('form input').val(null);
    actorTemp =[];
    var orderId = row["id"];
    var status = row["Status"];
    if (status == "Blank" || status == "Uncomplete" || status.indexOf('Refuse') >= 0) {
        editableForm();
        $('#confirm').show();
        $('#save').show();
        $('#orderBack').hide();
        $('del').show();
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
    $('#project').autofill(row, {
        findbyname: true,
        restrict: false
    });
    $("#actorTable").bootstrapTable('load',actorTemp);
    showForm();
});
//是否为第一单位
$('#isComAch').change(function(){
    firstOrOther();
});
/*添加新项目*/
$("#addNewAward").click(function(){
    startNewAward();
});
//监听 点击保存
$("#save").click(function(){
    saveAwardInfo();
});
//监听 点击确认
$("#confirm").click(function(){
    confirmAwardInfo();
});
//监听 点击返回
$("#back").click(function(){
    showTable();
});
//监听  删除
$("#del").click(function(){
    deleteCurrentOrder();
});
//监听 点击撤回
$("#orderBack").click(function(){
    orderBackAwardInfo();
});
/*统一提交*/
$("#submit").click(function(){
    submitAwardInfo();
});
function startNewAward(){

}
function saveAwardInfo(){

}
function confirmAwardInfo(){

}
function deleteCurrentOrder(){

}
function orderBackAwardInfo(){

}
function submitAwardInfo(){

}