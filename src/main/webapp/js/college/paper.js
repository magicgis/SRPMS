
var pTable;
$(function () {
    pTable = $('#PaperTable').bootstrapTable({
        url: '/api/workflow/order/' + userName + '/paper/all',
        sidePagination: "server",
        columns: [{
            radio: true
        }, {
            field: 'id',
            title: 'id',
            sortable: true,
            visible: false
        }, {
            field: 'name',
            title: '论文名称',
            sortable: true
        }, {
            field: 'type',
            title: '论文类别',
            sortable: true,
            formatter: "typeTran"
        }, {
            field: 'S-ACTOR',
            title: '提交者',
            sortable: true
        }, {
            field: 'pubDate',
            title: '发表日期',
            sortable: true
        }, {
            field: 'WF_Actor',
            title: '参与人',
            sortable: true,
            formatter: 'actorTran'
        }, {
            field: 'Status',
            title: '状态',
            sortable: true,
            formatter: 'statusTran'
        }, {
            field: 'operator',
            align: 'center',
            title: '操作',
            formatter: view
        }],
        responseHandler: tableTrans
    });

});
var tableTypes = "PaperTable";
var orderUrl = '/api/workflow/order/' + userName + '/paper/all';

$('#PaperTable').on('click-row.bs.table', function (e, row, $element) {
    var orderId = row["id"];
    window.location.href = '/order/' + orderId;
});

//监听 统一提交
$('#submit').click(function () {
    submitCol();
});
/**
 * 统一提交
 * */
function submitCol() {
    var userInfo = {'WF_User': userName};
    workflow.submitByCol(userInfo).success(function () {
        afterSuccess("提交成功");
        showTable(orderUrl, tableTypes);
    });
}
//监听 点击table
//$('#PaperTable').on('click-row.bs.table', function (e, row, $element) {
//    $('form input').val(null);
//    actorTemp = [];
//    var orderId = row["id"];
//    var status = row["Status"];
//    uneditableForm();
//    if (status == 'WaitForCol') {
//        workflow.latestTask(orderId).success(function (currentTask) {
//            var taskId = currentTask[0]['id'];
//            $('#WF_Task').val(taskId);
//        });
//        $("#Approve").removeAttr("disabled", "disabled");
//        $("#Refuse").removeAttr("disabled", "disabled");
//    } else if (status.indexOf('Refuse') >= 0) {
//        workflow.latestTask(orderId).success(function (currentTask) {
//            var taskId = currentTask[0]['id'];
//            $('#WF_Task').val(taskId);
//            //console.log(taskId);
//            if (status.indexOf('Refuse') >= 0) {
//                $('#reply').show();
//                $('#reply-display').show();
//                var reply = $('#reply-display').children('p');
//                var who = $('#reply-display').children('small');
//                reply.empty();
//                who.empty();
//                if (status.indexOf("Dep") >= 0) {
//                    reply.append(currentTask[0]['variableMap']['replyByDep']);
//                    who.append("管理部门批复");
//                } else {
//                    $('#reply').hide();
//                    $("#Approve").attr("disabled", "disabled");
//                    $("#Refuse").attr("disabled", "disabled");
//                }
//            }
//        });
//    } else {
//        $("#Approve").attr("disabled", "disabled");
//        $("#Refuse").attr("disabled", "disabled");
//    }
//    if (row['actors'] != null) {
//        actorTemp = row['actors'];
//    }
//    showFiles(row["filesData"]);//显示文件信息
//    $('.fd').hide();
//    $('#paper').autofill(row, {
//        findbyname: true,
//        restrict: false
//    });
//    $("#magId").val(row["mag.name"]);
//    magOrConfer();
//    $("#actorTable").bootstrapTable('load', actorTemp);
//    $('#paperTable-box').addClass('collapsed');
//    $('#paper-box').removeClass('collapsed');
//});
//
///**
// * 下一条信息
// * */
//function nextItemInfo(table) {
//    var selectedRows = table.bootstrapTable('getSelections');
//    if (selectedRows.length == 0) {
//        BootstrapDialog.show({
//            title: '通知',
//            type: BootstrapDialog.TYPE_WARNING,
//            message: '请选择一条数据。'
//        });
//        return;
//    }
//    var thisRow = selectedRows[0];	//获取选中行数据
//    var rowIndex = table.bootstrapTable("getRowIndex", thisRow) + 1;//下一条论文记录的行数rowIndex
//    var options = table.bootstrapTable('getPager').data("pagination").options;
//    var totalRowNum = options.total;	//总行数
//    if (rowIndex <= totalRowNum - 1) {
//        table.bootstrapTable("selectRow", rowIndex);
//        var selRows = table.bootstrapTable('getSelections');
//        var row = selRows[0];	//选中下一条论文
//        var orderId = row.id;//下一条论文的id
//    } else {
//        BootstrapDialog.show({
//            title: '通知',
//            type: BootstrapDialog.TYPE_WARNING,
//            message: '已是最后一条。'
//        });
//    }
//}
