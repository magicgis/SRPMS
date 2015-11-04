/**
 * Created by zheng on 2015/10/12.
 */
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
 * 保存
 */
function save() {
    $('#IsComplete').val(false);
    var jsonData = getFormData('book');
    if (awardedData != null) {
        jsonData['awardedData'] = awardedData;
    }
    workflow.execute(userName, taskId, jsonData).success(function () {
        afterSuccess("保存成功！");
        window.location.href = '/process-book-all';
    });
}
function confirm() {
    $('#IsComplete').val(true);
    var jsonData = getFormData('book');
    if (awardedData != null) {
        jsonData['awardedData'] = awardedData;
    }
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
                            window.location.href = '/process-book-all';
                        } else {
                            errorMsg(data["msg"]);
                            flag = true;
                        }
                    } else {
                        afterSuccess("确认成功！");
                        window.location.href = '/process-book-all';
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

    window.workflow.getBack(userName, orderId).success(function () {
        afterSuccess("已撤回");
        window.location.href = '/process-book-all';
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
                    window.location.href = '/process-book-all';
                });
            }
        }
    });
}
/**************************编辑成员||计算分数||选择已有著作**************************************/
function addAwarded(){
    BootstrapDialog.show({
        type: BootstrapDialog.TYPE_PRIMARY,
        message: function (dialog) {
            var $message = $('<div></div>');
            var pageToLoad = dialog.getData('pageToLoad');
            $message.load(pageToLoad);
            return $message;
        },
        title: "著作信息",
        data: {
            'pageToLoad': '/dialog/bookAward.html'
        },
        closeByBackdrop: false,
        buttons: [{
            id: 'btn-oknm',
            icon: 'glyphicon glyphicon-check',
            label: '确认',
            cssClass: 'btn-info',
            autospin: false,
            action: function (dialogRef) {
                //if (!isFull()) {
                //    messageModal('请将信息填写完整。');
                //    return;
                //}
                awardedInfo();
                dialogRef.close();
            }
        }],
        onshown: function () {

        }
    });
}
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
            $('.bookTextNumber').show();
            fillRoles(bookRoles);
        }
    });
}
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
                //console.log(getActorsData());
                dialogRef.close();
            }
        }],
        onshown: function () {
            fillRoles(bookRoles);
            var $actor = $("#actor").selectize();
            var $role = $("#role").selectize();
            var $units = $("#units").selectize();
            addOptionSelectize($actor, [{'id': row["staff.id"], 'name': row["staff.name"], "col": {"value": ""}}]);
            $actor[0].selectize.setValue(row["staff.id"]);
            //填充角色
            DisplayForm($role, row["role"], 0);
            $("#role").val(row["role"]);
            //填充单位
            DisplayForm($units, row["unit"], 1);
            //填充其他
            $('#actorsInfo').autofill(row, {
                findbyname: true,
                restrict: false
            });
            disableSelectize($actor);
            disableSelectize($role);
            disableSelectize($units);
            $("#rank").attr("disabled", "disabled");
            $("#textNumber").attr("disabled", "disabled");
            $('.bookTextNumber').show();
            $(".editableModal").show();
            //是否可编辑
            if (flag) {//可编辑
                $("#btn-ok").removeAttr("disabled").show();
                $("#marks").removeAttr("disabled");
            } else {  //不可编辑
                $("#btn-ok").attr("disabled", "disabled").hide();
                $("#marks").attr("disabled", "disabled");
            }
            if (row["staff.id"] == "9998" || row["staff.id"] == "9999") {
                $("#marks").attr("disabled", "disabled");
            }
        }
    });
}
/**
 * 计算分数
 */
function getScore() {
    var jsonData = getFormData('book');
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
