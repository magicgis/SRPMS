/**
 * Created by Administrator on 2015/4/16.
 */
var actorTemp = [];
var fundTemp = [];
var unitTemp = [];
var filesData = {};
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

    $('#actorTable').bootstrapTable({
        columns: [
            {
                field: 'staff.id',
                title: '工号',
                sortable: true,
                visible: false
            }, {
                field: 'rank',
                title: '排名',
                sortable: true,
                editable: false,
                footerFormatter: "totalNameFormatter"
            }, {
                field: 'staff.name',
                title: '成员',
                sortable: true
            }, {
                field: 'role',
                title: '角色',
                sortable: true
            }, {
                field: 'score',
                title: '分数',
                sortable: true,
                editable: false,
                footerFormatter: "totalMarksFormatter"
            }, {
                field: 'unit',
                title: '归属单位',
                editable: false,
                sortable: true
            }],
        data: actorTemp
    });

    $('#fundTable').bootstrapTable({
        columns: [{
            field: 'to_acct_time',
            title: '到账时间',
            editable: true,
            sortable: true
        }, {
            field: 'to_acct_mny',
            title: '到账金额',
            editable: true,
            sortable: true,
            footerFormatter: "totalFundsFormatter"
        }, {
            field: 'out_mny',
            title: '外拨金额',
            editable: true,
            sortable: true,
            footerFormatter: "totalEFundFormatter"
        }, {
            field: 'operate',
            title: '操作',
            sortable: true,
            formatter: "operateFFormatter",
            events: "operateFEvents"
        }],
        data: fundTemp
    });

    $('#unitTable').bootstrapTable({
        columns: [{
            field: 'rank',
            title: '排名',
            sortable: true,
            editable: false,
            footerFormatter: "totalUnitFormatter"
        }, {
            field: 'unit',
            title: '单位名称',
            editable: false,
            sortable: true
        }],
        data: unitTemp
    });
    allSections();
    upToLoadFile();
    $('#reply-box').hide();
    $('#reply').hide();
    $('#unitInfo').hide();
    $("#DiffAdd").hide();
    $('#unitToolbar').hide();
    $('#actorToolbar').hide();
});

//监听 点击表
/**
 * 待处理问题
 * 1.选择框清除
 * 2.选择框设置值
 * */

/* $('#ProjectTable').on('click-row.bs.table', function (e, row, $element) {
 uneditableForm();
 //复选框不能用
 selectEnable(0);
 $('form input').val(null);
 actorTemp = [];
 fundTemp = [];
 unitTemp = [];
 var orderId = row["id"];
 $("#WF_Order").val(orderId);
 if (row['parentTaskId'] != null) {
 var taskId = row['id'];
 $('#WF_Task').val(taskId);
 }
 var status = row["Status"];
 if (status == "Blank" || status == "Uncomplete" || status.indexOf('Refuse') >= 0) {
 // editableForm();
 //复选框能用
 //  selectEnable(1);
 $('#confirm').show();
 $('#save').show();
 $('#orderBack').hide();
 $('#del').show();
 workflow.latestTask(orderId).success(function (currentTask) {
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
 uneditableForm();
 //复选框不能用
 selectEnable(0);
 $('#del').hide();
 if (status == 'Complete' || status == 'WaitForSubmit') {
 $('#orderBack').show();
 } else {
 $('#orderBack').hide();
 }
 $('#confirm').hide();
 $('#save').hide();
 }
 //总分
 var score = row['score'];
 if (score == undefined || score == null || score == "") {
 $("#showSum").html("");
 } else {
 $("#showSum").html("总分：" + score + "分");
 }
 if (row['actors'] != null) {
 actorTemp = row['actors'];
 }
 $("#actorTable").bootstrapTable('load', actorTemp);
 if (row['funds'] != null) {
 fundTemp = row['funds'];
 }
 $("#fundTable").bootstrapTable('load', fundTemp);
 //if (row['units'] != null) {unitTemp = row['units'];}
 $('#project').autofill(row, {
 findbyname: true,
 restrict: false
 });
 $.each(optionsMenu, function (key, value) {
 DisplayForm($('#' + value).selectize(), row[value], 0);
 });
 //console.log(row["dept"]);
 // addOptionSelectize($magId, [{'id' : magId, 'name' : row['mag.name']}]);
 DisplayForm($('#dept').selectize(), row["dept"], 0);
 if (row['attr'] == "联合项目") {
 if (row['units'] != null) {
 unitTemp = row['units'];
 }
 $('#unitTable').bootstrapTable("load", unitTemp);
 $('#unitInfo').show();
 } else {
 $('#unitInfo').hide();
 }
 showFiles(row["filesData"]);
 showForm();
 $('#fundTable').bootstrapTable('showColumn', 'operate');
 });*/
//是否为第一单位
$('#attr').change(function () {
    firstOrOther();
});
//监听 申请差异
$("#DiffAdd").click(function () {
    different();
});
//监听 添加成员
$('#addActor').click(function () {
    addActor();
});
//监听 添加单位
$('#addUnit').click(function () {
    addUnit();
});
//监听 添加金额信息
$('#addFund').click(function () {
    addFund();
});
//监听 分配分数
$('#getScore').click(function () {
    getScore();
});
/*添加新项目*/
$("#addProject").click(function () {
    startNewProject();
});
//监听 点击保存
$("#save").click(function () {
    //console.log("save");
    save();
});
//监听 点击确认
$("#confirm").click(function () {
    confirmProjectInfo();
});
//监听 点击返回
$("#back").click(function () {
    showTable();
});
//监听  删除
$("#del").click(function () {
    delOrder();
});
//监听 点击撤回
$("#orderBack").click(function () {
    orderBackProjectInfo();
});
/*统一提交*/
$("#submit").click(function () {
    submitProjectInfo();
});
/**
 * 差异申请
 * */
function different() {
    var selectRow = $('#ProjectTable').bootstrapTable('getSelections')[0]['id'];
    var proType = "project";
    window.location.href = "defferent.jsp?" +
    "proType=" + escape("project")
    + "&WF_Task" + escape($('#WF_Task').val())
    + "&id=" + escape(selectRow);
}
//添加新项目的行为函数
function startNewProject() {
    $('form input').val(null);
    $('#downFiles').empty();
    actorTemp = [];
    filesData = {};
    $('#actorTable').bootstrapTable('load', actorTemp);
    $('#fundTable').bootstrapTable('load', fundTemp);
    $('#unitTable').bootstrapTable('load', unitTemp);
    editableForm();
    workflow.startOrder(userName, "basicProcess_Beta", "project").success(function (data) {
        afterSuccess("新建成功！");
        showForm();
        var orderId = data["id"];
        $('#WF_Order').val(orderId);
        workflow.latestTask(orderId).success(function (currentTask) {
            var taskId = currentTask[0]['id'];
            $('#WF_Task').val(taskId);
        });
    });

}
function save() {
    $('#IsComplete').val(false);
    var jsonData = getFormData("project");
    console.log(jsonData);
    workflow.execute(userName, '', jsonData).success(function () {
        afterSuccess("保存成功！");
        showTable();
    });
}

function delOrder() {
    //var row = $('#ProjectTable').bootstrapTable('getSelections')[0];
    var order = $("#WF_Order").val();
    //var order = row['id'];
    BootstrapDialog.confirm({
        title: '提示！',
        message: '你确定要删除该项吗?',
        type: BootstrapDialog.TYPE_WARNING,
        closable: true,
        draggable: true,
        btnCancelLabel: '取消',
        btnOKLabel: '确定',
        btnOKClass: 'btn-warning',
        callback: function (result) {
            if (result) {
                workflow.delOrder(order).success(function () {
                    afterSuccess("删除成功！");
                    showTable();
                });
            }
        }
    });
}
/*
 * 确认
 *
 * */

function confirmProjectInfo() {
    $('#IsComplete').val(true);
    var jsonData = getFormData("project");
    BootstrapDialog.confirm({
        title: '确认信息',
        message: '确认?',
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
                workflow.execute(userName, $('#WF_Task').val(), jsonData).success(function (data) {
                    //console.log(data);
                    if (data["valid"] == true) {
                        afterSuccess("确认成功！");
                        showTable();
                    } else {
                        errorMsg(data["msg"]);
                    }
                });
            }
        }
    });
}
function orderBackProjectInfo() {
    var row = $('#ProjectTable').bootstrapTable('getSelections')[0];
    var order = row['id'];
    var jsonData = Object();
    jsonData['order'] = order;
    jsonData['user'] = userName;
    window.workflow.getBack(userName, order).success(function () {
        afterSuccess("已撤回");
        showTable();
        $('#del').show();
    });
}
/*
 * 提交所有
 *
 * */
function submitProjectInfo() {
    var submitData = 'WF_User=' + userName;
    workflow.submitByTeacher(submitData).success(function () {
        afterSuccess("提交成功");
        showTable();
    })
}

/**
 * 添加资金信息
 */
function addFund() {
    BootstrapDialog.show({
        type: BootstrapDialog.TYPE_PRIMARY,
        message: function (dialog) {
            var $message = $('<div></div>');
            var pageToLoad = dialog.getData('pageToLoad');
            $message.load(pageToLoad);
            return $message;
        },
        title: "项目金额信息",
        data: {
            'pageToLoad': '../public/addFund.html'
        },
        closeByBackdrop: false,
        buttons: [{
            id: 'btn-ok',
            icon: 'glyphicon glyphicon-check',
            label: '添加',
            cssClass: 'btn-info',
            autospin: false,
            action: function (dialogRef) {
                // console.log($("#rank").val());
                if (!isFull()) {
                    messageModal('请将信息填写完整。');
                    return;
                }
                subFundInfo(null);
                dialogRef.close();
            }
        }]
    });
}
//编辑资金信息
function editFund(row, index) {
    BootstrapDialog.show({
        type: BootstrapDialog.TYPE_PRIMARY,
        message: function (dialog) {
            var $message = $('<div></div>');
            var pageToLoad = dialog.getData('pageToLoad');
            $message.load(pageToLoad);
            return $message;
        },
        title: "项目金额信息",
        data: {
            'pageToLoad': '../public/addFund.html'
        },
        closeByBackdrop: false,
        buttons: [{
            id: 'btn-ok',
            icon: 'glyphicon glyphicon-check',
            label: '保存',
            cssClass: 'btn-info',
            autospin: false,
            action: function (dialogRef) {
                if (!isFull()) {
                    messageModal('请将信息填写完整。');
                    return;
                }
                subUnitInfo(index);
                dialogRef.close();
            }
        }],
        onshown: function () {
            //var $unit = $("#unit").selectize();
            //填充单位
            //DisplayForm($unit, row["unit"], 1);
            //填充其他
            $('#fundInfo').autofill(row, {
                findbyname: true,
                restrict: false
            });
        }
    });
}
/**
 * 计算分数
 */
function getScore() {
    var jsonData = getFormData("project");
    //console.log(jsonData);
    workflow.getScore(jsonData).success(function (data) {
        if (data["valid"] == false) {
            errorMsg(data["msg"]);
        } else if (data["hasSum"] == false) {
            $("#actorTable").bootstrapTable('load', data["actors"]);
            errorMsg(data["msg"]);
        } else if (data["hasSum"] == true) {
            $("#score").val(data["sum"]);
            $("#showSum").html("总分：" + data["sum"] + "分");
            errorMsg(data["msg"]);
        }
    });
}