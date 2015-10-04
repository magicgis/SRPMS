/**
 * Created by zheng on 2015/4/21.
 */
//var actorTemp = [];
//var fundTemp = [];
//var unitTemp = [];
//var filesData = {};
$(function () {
    $('#ProjectTable').bootstrapTable({
        url: '/api/workflow/order/' + userName + '/project/all',
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
            title: '名称',
            sortable: true
        }, {
            field: 'type',
            title: '类别',
            sortable: true
        }, {
            field: 'rank',
            title: '等级',
            sortable: true
        }, {
            field: 'rateUnit',
            title: '评分来源',
            sortable: true
        }, {
            field: 'real_time',
            title: '实际结题时间',
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
});
//var actorTemp = [];
//var fundTemp = [];
//var unitTemp = [];
//$(function () {
//    $('#ProjectTable').bootstrapTable({
//        url: '/api/workflow/order/col/project/all',
//        sidePagination: "server",
//        columns: [{
//            radio: true
//        }, {
//            field: 'id',
//            title: 'id',
//            sortable: true,
//            visible: false
//        }, {
//            field: 'projName',
//            title: '项目名称',
//            sortable: true
//        }, {
//            field: 'projType',
//            title: '项目类别',
//            sortable: true
//        }, {
//            field: 'projRank',
//            title: '项目等级',
//            sortable: true
//        }, {
//            field: 'S-ACTOR',
//            title: '提交者',
//            sortable: true
//        }, {
//            field: 'grScore',
//            title: '得分',
//            sortable: true
//        }, {
//            field: 'actSolTime',
//            title: '结题时间',
//            sortable: true
//        }, {
//            field: 'Status',
//            title: '状态',
//            sortable: true,
//            formatter: 'statusTran'
//        }, {
//            field: 'operator',
//            align: 'center',
//            title: '操作',
//            formatter: view
//        }],
//        responseHandler: tableTrans
//    });
//    $('#actorTable').bootstrapTable({
//        columns: [{
//            field: 'actor',
//            title: '成员',
//            editable: true,
//            sortable: true,
//            footerFormatter: "totalNameFormatter"
//        }, {
//            field: 'staId',
//            title: '工号',
//            editable: true,
//            sortable: true
//        }, {
//            field: 'role',
//            title: '角色',
//            editable: true,
//            sortable: true
//        }, {
//            field: 'marks',
//            title: '分数',
//            editable: true,
//            sortable: true,
//            footerFormatter: "totalMarksFormatter"
//        }],
//        data: actorTemp
//    });
//
//    $('#fundTable').bootstrapTable({
//        columns: [{
//            field: 'to_acct_time',
//            title: '到账时间',
//            editable: true,
//            sortable: true,
//            footerFormatter: "totalFundsFormatter"
//        }, {
//            field: 'to_acct_mny',
//            title: '到账金额',
//            editable: true,
//            sortable: true
//            //footerFormatter:"totalFundFormatter"
//        }, {
//            field: 'out_mny',
//            title: '外拨金额',
//            editable: true,
//            sortable: true,
//            footerFormatter: "totalEFundFormatter"
//        }],
//        data: fundTemp
//    });
//    $('#unitTable').bootstrapTable({
//        columns: [{
//            field: 'unit_id',
//            title: 'id',
//            sortable: true,
//            visible: false,
//            footerFormatter: "totalUnitFormatter"
//        }, {
//            field: 'unit_rank',
//            title: '单位排名',
//            editable: true,
//            sortable: true
//        }, {
//            field: 'unit_name',
//            title: '单位名称',
//            editable: true,
//            sortable: true
//        }],
//        data: unitTemp
//    });
//    uneditableForm();
//    $('#unitInfo').hide();
//    $('#reply-box').show();
//    $('#reply').show();
//    $('#actorToolbar').hide();
//    $('#fundToolbar').hide();
//    $('#unitToolbar').hide();
//});
////监听 点击table
//$('#ProjectTable').on('click-row.bs.table', function (e, row, $element) {
//    $('form input').val(null);
//    actorTemp = [{
//        "actor": "",
//        "marks": "0"
//    }];
//    var orderId = row["id"];
//    var status = row["Status"];
//    if (status == 'WaitForCol') {
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
//    if ((row['actors'] != null) || (row['funds'] != null)) {
//        actorTemp = row['actors'];
//        fundTemp = row['funds'];
//    }
//    $('#project').autofill(row, {
//        findbyname: true,
//        restrict: false
//    });
//    if (row['isUnionProj'] == "true") {
//        if (row['units'] != null) {
//            unitTemp = row['units'];
//        }
//        $('#unitInfo').show();
//        $('#unitTable').bootstrapTable("load", unitTemp);
//    }
//    $("#actorTable").bootstrapTable('load', actorTemp);
//    $("#fundTable").bootstrapTable('load', fundTemp);
//    showForm();
//});
////监听 点击返回
//$("#back").click(function () {
//    showTable();
//});
////通过
//$("#Approve").click(function () {
//    approveInfo();
//});
////驳回
//$("#Refuse").click(function () {
//    refuseAwardInfo();
//});
////下一条
//$("#next").click(function () {
//    // console.log($('#tNewFoodTable').bootstrapTable('getSelections'));
//});
////上一条
//$("#previous").click(function () {
//});
//
//
//function approveInfo() {
//    var approveInfo = Object();
//    approveInfo["DecByCol"] = true;
//    approveInfo["replyByCol"] = $('#reply-box').val();
//    workflow.execute('col', $('#WF_Task').val(), approveInfo).success(function () {
//        showTable();
//    });
//}
//
//function refuseAwardInfo() {
//    var refuseAwardInfo = Object();
//    refuseAwardInfo["DecByCol"] = false;
//    refuseAwardInfo["replyByCol"] = $('#reply-box').val();
//    workflow.execute('col', $('#WF_Task').val(), refuseAwardInfo).success(function () {
//        showTable();
//    });
//}

