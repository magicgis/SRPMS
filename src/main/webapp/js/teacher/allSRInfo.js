/**
 * Created by zheng on 2015/6/8.
 */
/**
 * 所有科研信息
 * */
var actorTemp = [];
$(function () {
    $('#allSRInfoTable').bootstrapTable({
        url: '/api/workflow/order/' + userName + '/all/all',
        sidePagination: "server",
        columns: [{
            radio: true
            //visible:false
        }, {
            field: 'id',
            title: 'id',
            sortable: true,
            visible: false
        }, {
            field: 'ActorList',
            title: '人员',
            formatter: "actorTran",
            sortable: true
        }, {
            field: 'name',
            title: '科研名称',
            sortable: true
        }, {
            field: 'WF_Type',
            title: '科研类型',
            sortable: true,
            formatter: "wfTypeTran"
        }, {
            field: 'Status',
            title: '状态',
            sortable: true,
            formatter: 'statusTran'
        }, {
            field: 'operator',
            align: 'center',
            title: '操作',
            width: 75,
            formatter: view
        }],
        responseHandler: tableTrans
    });
    /* $('#tableOrd').hide();*/
});
//$('#allSRInfoTable').on('click-row.bs.table', function (e, row) {
//    var wfType = row['WF_Type'];
//    var taskId = row['id'];
//    for (var key in selectDefferent) {
//        if (key == wfType) {
//            $("#confirmForm").empty();
//            var url = selectDefferent[key];
//            showForm();
//            $("#confirmForm").load(url, function () {
//                $('#WF_Task').val(taskId);
//                $('form input').val(null);
//                if (row['actors'] != null) {
//                    actorTemp = row['actors'];
//                } else {
//                    actorTemp =[];
//                }
//                /*显示成员表*/
//                scanActorTable();
//                showFiles(row["filesData"]); //显示文件信息
//                $('#' + wfType).autofill(row, {
//                    findbyname: true,
//                    restrict: false
//                });
//                uneditableForm();
//
//                magOrConfer();
//                $("#actorTable").bootstrapTable('load', actorTemp);
//                $('#addDiff').hide();
//                $('#reply').hide();
//                $('#getScore').hide();
//                if (wfType == 'paper') {
//                    paperType();
//                    $('#magId').val(row['mag.name']);
//                }
//                //showForm();
//            });
//        }
//    }
//});
//监听 所有科研
$('#allSRInform').click(function () {
    allSRInform();
});
//监听 主导科研
$('#orderSRInfo').click(function () {
    orderSRInfo();
});
//监听 参与科研
$('#partakeBtn').click(function () {
    partake();
});
//监听 要确认科研
$('#confirmBtn').click(function () {
    waitConfirm();
});
//监听 点击返回
$("#back").click(function () {
    showTable();
});
function allSRInform() {
    $('#confirm').hide();
    var orderUrl = '/api/workflow/order/' + userName + '/all/all';
    showTable(orderUrl);
}
function orderSRInfo() {
    $('#confirm').hide();
    var orderUrl = '/api/workflow/order/' + userName + '/all/1st';
    showTable(orderUrl);
}
function partake() {
    $('#confirm').hide();
    var orderUrl = '/api/workflow/order/' + userName + '/all/2nd';
    showTable(orderUrl);
}
function waitConfirm() {
    $('#confirm').show();
    var orderUrl = '/api/workflow/' + userName + '/confirmTask';
    showTable(orderUrl);
}

$('.allSubmit').click(function () {

    BootstrapDialog.confirm({
        title: '统一提交',
        message: '确认 ?',
        type: BootstrapDialog.TYPE_INFO,
        closable: true,
        draggable: true,
        btnCancelLabel: '取消',
        btnOKLabel: '确认',
        btnOKClass: 'btn-ok',
        callback: function (result) {
            /**
             * userName,taskId,status
             */
            if (result) {
                workflow.submitByTeacher({WF_User: userName}).success(function (data) {
                    console.log(data);
                    if (data == 'true') {
                        afterSuccess("提交成功！");
                    } else {
                        BootstrapDialog.alert("提交失败");
                    }
                    $('#allSRInfoTable').bootstrapTable('refresh');
                });
            }
        }
    });
});
$('#confirm').click(function () {
    $('#IsComplete').val(true);
    var jsonData = $("#paper").serializeJSON();
    $.each(jsonData, function (key, value) {
        if (value == "" || value == null) {
            delete jsonData[key];
        }
    });
    if (filesData != null) {
        jsonData['filesData'] = filesData;
    }
    jsonData["magName"] = $("#magName").val();
    jsonData["actors"] = getActorsData();
    jsonData["WF_User"] = userName;
    BootstrapDialog.confirm({
        title: '确认信息',
        message: '确认 ?',
        type: BootstrapDialog.TYPE_INFO,
        closable: true,
        draggable: true,
        btnCancelLabel: '取消',
        btnOKLabel: '确认',
        btnOKClass: 'btn-ok',
        callback: function (result) {
            /**
             * userName,taskId,status
             */
            if (result) {
                workflow.execute(userName, $('#WF_Task').val(), jsonData).success(function () {
                    afterSuccess("确认成功！");
                    showTable();
                });
            }
        }
    });
});