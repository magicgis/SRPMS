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
            field: 'WF_Actor',
            title: '参与人',
            sortable: true
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
    $('#actorTable').bootstrapTable({
        columns: [{
            field: 'id',
            title: '工号',
            sortable: true,
            visible: false
        }, {
            field: 'actor',
            title: '成员',
            sortable: true,
            footerFormatter: "totalNameFormatter"
        }, {
            field: 'role',
            title: '角色',
            sortable: true
        }, {
            field: 'marks',
            title: '分数',
            sortable: true,
            footerFormatter: "totalMarksFormatter"
        }, {
            field: 'units',
            title: '归属单位',
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
    $("#upload").hide();
    // c
});
var tableTypes = "PaperTable";
var orderUrl = '/api/workflow/order/' + userName + '/paper/all';
//监听 点击table
$('#PaperTable').on('click-row.bs.table', function (e, row) {
    $('form input').val(null);
    actorTemp = [{
        "actor": "",
        "marks": "0"
    }];
    var orderId = row["id"];
    var status = row["Status"];
    uneditableForm();
    if (status == 'WaitForDep') {
        workflow.latestTask(orderId).success(function (currentTask) {
            var taskId = currentTask[0]['id'];
            $('#WF_Task').val(taskId);
        });
        $("#Approve").removeAttr("disabled", "disabled");
        $("#Refuse").removeAttr("disabled", "disabled");
    } else {
        $("#Approve").attr("disabled", "disabled");
        $("#Refuse").attr("disabled", "disabled");
    }
    if (row['actors'] != null) {
        actorTemp = row['actors'];
    }
    $("#magId").val(row["mag.name"]);
    showFiles(row["filesData"]); //显示文件信息
    $('.fd').hide();
    $('#paper').autofill(row, {
        findbyname: true,
        restrict: false
    });
    magOrConfer();
    $("#actorTable").bootstrapTable('load', actorTemp);
    showForm();
});

//监听 点击返回
$(".back").click(function () {
    showTable(orderUrl, tableTypes);
});


/**
 * 通过
 * */
function approve() {
    var req = Object();
    req["DecByDep"] = true;
    req["replyByDep"] = $('#reply-box').val();
    BootstrapDialog.confirm({
        title: '确认信息',
        message: '你确认通过吗?',
        type: BootstrapDialog.TYPE_SUCCESS,
        closable: true,
        draggable: true,
        btnCancelLabel: '取消!',
        btnOKLabel: '确认!',
        btnOKClass: 'btn-ok',
        callback: function (result) {
            if (result) {
                workflow.execute(userName, $('#WF_Task').val(), req).success(function () {
                    showTable();
                });
            }
        }
    });
}
/**
 * 驳回
 * */
function refuse() {
    var req = Object();
    req["DecByDep"] = false;
    req["replyByDep"] = $('#reply-box').val();
    BootstrapDialog.confirm({
        title: '警告！',
        message: '你确定驳回吗?',
        type: BootstrapDialog.TYPE_WARNING,
        closable: true,
        draggable: true,
        btnCancelLabel: '取消',
        btnOKLabel: '确定',
        btnOKClass: 'btn-warning',
        callback: function (result) {
            if (result) {
                workflow.execute(userName, $('#WF_Task').val(), req).success(function () {
                    showTable();
                });
            }
        }
    });
}