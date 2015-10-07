var actorTemp = [];
var pTable;
$(function () {
    $('#PaperTable').bootstrapTable({
        url: '/api/workflow/order/' + userName + '/paper/all',
        sidePagination: "server",
        columns: [{
            radio: true,
            visible: false
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
            field: 'WF_Col',
            title: '学院',
            sortable: true
        }, {
            field: 'ActorList',
            title: '参与人',
            sortable: true,
            formatter:'actorTran'
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
//var tableTypes = "PaperTable";
//var orderUrl = '/api/workflow/order/' + userName + '/paper/all';


//监听 点击table
//$('#PaperTable').on('click-row.bs.table', function (e, row) {
//    $('form input').val(null);
//    actorTemp = [{
//        "actor": "",
//        "marks": "0"
//    }];
//    var orderId = row["id"];
//    var status = row["Status"];
//    uneditableForm();
//    if (status == 'WaitForDep') {
//        workflow.latestTask(orderId).success(function (currentTask) {
//            var taskId = currentTask[0]['id'];
//            $('#WF_Task').val(taskId);
//        });
//        $("#Approve").removeAttr("disabled", "disabled");
//        $("#Refuse").removeAttr("disabled", "disabled");
//    } else {
//        $("#Approve").attr("disabled", "disabled");
//        $("#Refuse").attr("disabled", "disabled");
//    }
//    if (row['actors'] != null) {
//        actorTemp = row['actors'];
//    }
//    $("#magId").val(row["mag.name"]);
//    showFiles(row["filesData"]); //显示文件信息
//    $('.fd').hide();
//    $('#paper').autofill(row, {
//        findbyname: true,
//        restrict: false
//    });
//    magOrConfer();
//    $("#actorTable").bootstrapTable('load', actorTemp);
//    showForm();
//});