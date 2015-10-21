$(function () {
    // 各种初始化
    init();

});
var flag = true;
function init() {
    $('#reply-box').hide();
    if (status.indexOf('Refuse') >= 0) {
        $('#reply').show();
        $('#reply-display').show();
        var reply = $('#reply-display').children('p');
        var who = $('#reply-display').children('small');
        if (status.indexOf("Col") >= 0) {
            reply.append(replyByCol);
            who.append("学院批复");
        } else {
            $('#del').hide();
            $('#confirm').hide();
            $('#save').hide();
            reply.append(replyByDep);
            who.append("管理部门批复");
        }
    } else {
        $('#reply').hide();
    }
}


/**
 * 计算分数
 */
function getScore() {
    var jsonData = getFormData('paper');
    workflow.getScore(jsonData).success(function (data) {
        console.log(data);
        if (data["valid"] == false) {
            errorMsg(data["msg"]);
            flag = true;
        } else if (data["hasSum"] == false) {
            $("#actorTable").bootstrapTable('load', data["actors"]);
            flag = false;
            errorMsg(data["msg"]);
        } else if (data["hasSum"] == true) {
            $("#score").val(data["sum"]);
            console.log($("#score").val());
            console.log(data["sum"]);
            $("#showSum").html("　可分配总分：" + data["sum"] + "分");
            errorMsg("总分为" + data["sum"] + "分，" + data["msg"]);
            flag = true;
        }
    });
}

/**
 * 保存
 */
function save() {
    $('#IsComplete').val(false);
    var jsonData = getFormData('paper');
    workflow.execute(userName, taskId, jsonData).success(function () {
        afterSuccess("保存成功！");
        window.location.href = '/allSRInfo';
    });
    console.log("haha");
}
/**
 * 确认
 */
function confirm() {
    $('#IsComplete').val(true);
    var jsonData = getFormData('paper');
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
                    if ("valid" in data) {
                        if (data["valid"] == true) {
                            afterSuccess("确认成功！");
                            window.location.href = '/paper';
                        } else {
                            errorMsg(data["msg"]);
                            flag = true;
                        }
                    } else {
                        afterSuccess("确认成功！");
                        window.location.href = '/paper';
                    }
                });
            }
        }
    });
}

/**
 * 撤回order
 */
function getOrderBack() {
    //var row = $('#PaperTable').bootstrapTable('getSelections')[0];
    //var order = row['id'];
    //var send = new Object();
    //send['order'] = orderId;
    //send['user'] = userName;
    window.workflow.getBack(userName, orderId).success(function () {
        afterSuccess("已撤回");
        window.location.href = '/paper';
    });
}
/**
 * 删除order
 */
function delOrder() {
    var order = $("#WF_Order").val();
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
                    window.location.href = '/paper';
                });
            }
        }
    });
}

/**
 * 添加成员
 */
function addActor() {
    BootstrapDialog.show({
        type: BootstrapDialog.TYPE_PRIMARY,
        message: function (dialog) {
            var $message = $('<div></div>');
            var pageToLoad = dialog.getData('pageToLoad');
            $message.load(pageToLoad);
            return $message;
        },
        title: "成员信息",
        data: {
            'pageToLoad': '/dialog/addActor.html'
        },
        closeByBackdrop: false,
        buttons: [{
            id: 'btn-oknm',
            icon: 'glyphicon glyphicon-check',
            label: '添加',
            cssClass: 'btn-info',
            autospin: false,
            action: function (dialogRef) {
                if (!isFull()) {
                    messageModal('请将信息填写完整。');
                    return;
                }
                subActorInfo(null, 1);
                dialogRef.close();
            }
        }],
        onshown: function () {
            fillRoles(paperRoles);
        }
    });
}

/**
 * 编辑成员
 */
function editActor(row, index) {
    BootstrapDialog.show({
        type: BootstrapDialog.TYPE_PRIMARY,
        message: function (dialog) {
            var $message = $('<div></div>');
            var pageToLoad = dialog.getData('pageToLoad');
            $message.load(pageToLoad);
            return $message;
        },
        title: "成员信息",
        data: {
            'pageToLoad': '/dialog/addActor.html'
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
                subActorInfo(index, 0);
                dialogRef.close();
            }
        }, {
            id: 'btn-cancel',
            icon: 'glyphicon glyphicon-check',
            label: '关闭',
            cssClass: 'btn-info',
            autospin: false,
            action: function (dialogRef) {
                dialogRef.close();
            }
        }],
        onshown: function () {
            fillRoles(paperRoles);
            var $actor = $("#actor").selectize();
            var $role = $("#role").selectize();
            var $units = $("#units").selectize();
            addOptionSelectize($actor, [{'id': row["staff.id"], 'name': row["staff.name"], "col": {"value": ""}}]);
            $actor[0].selectize.setValue(row["staff.id"]);
            //填充角色
            DisplayForm($role, row["role"], 0);
            //填充单位
            DisplayForm($units, row["unit"], 1);
            //填充其他
            $('#actorsInfo').autofill(row, {
                findbyname: true,
                restrict: false
            });
            $(".editableModal").show();
            //是否可编辑
            if (flag) {//可编辑
                enableSelectize($actor);
                enableSelectize($role);
                enableSelectize($units);
                $("#rank").removeAttr("disabled");
                $("#marks").removeAttr("disabled");
                $("#btn-ok").show();
            } else {  //不可编辑
                disableSelectize($actor);
                disableSelectize($role);
                disableSelectize($units);
                $("#rank").attr("disabled", "disabled");
                $("#marks").attr("disabled", "disabled");
                $("#btn-ok").attr("disabled", "disabled").hide();
            }
            if (row["staff.id"] == "9998" || row["staff.id"] == "9999") {
                $("#marks").attr("disabled", "disabled");
            }
        }
    });
}

