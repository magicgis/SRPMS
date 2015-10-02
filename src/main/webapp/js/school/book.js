/**
 * Created by zheng on 2015/5/17.
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
            field:'S-ACTOR',
            title:'提交者',
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
        }],
        data:actorTemp
    });
    uneditableForm();
    $('#reply-box').show();
    $('#reply').show();
});
//监听 点击table
$('#BookTable').on('click-row.bs.table', function (e, row, $element) {
    $('form input').val(null);
    actorTemp =[];
    var orderId = row["id"];
    var status = row["Status"];
    if(status == 'WaitForCol') {
        workflow.latestTask(orderId).success(function (currentTask) {
            var taskId = currentTask[0]['id'];
            $('#WF_Task').val(taskId);
        });
        $("#Approve").removeAttr("disabled","disabled");
        $("#Refuse").removeAttr("disabled","disabled");
    }else{
        $("#Approve").attr("disabled","disabled");
        $("#Refuse").attr("disabled","disabled");
    }
    if(row['actors']!=null) {
        actorTemp = row['actors'];
    }
    $('#book').autofill(row, {
        findbyname: true,
        restrict: false
    });
    $("#actorTable").bootstrapTable('load',actorTemp);
    showForm();
});
//监听 点击返回
$("#back").click(function(){
    showTable();
});
//通过
$("#Approve").click(function(){
    approveInfo();
});
//驳回
$("#Refuse").click(function(){
    refuseAwardInfo();
});
//下一条
$("#next").click(function(){
    // console.log($('#tNewFoodTable').bootstrapTable('getSelections'));
});
//上一条
$("#previous").click(function(){
});
