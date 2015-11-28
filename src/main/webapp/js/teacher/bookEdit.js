/**
 * Created by zheng on 2015/10/12.
 */
$(function () {
    // 各种初始化
    //init();
    init(entity, all, replyByDep, 1);
});
//var flag = true;
//function init() {
//    $('#reply-box').hide();
//    $('#totalScore').attr('disabled', 'disabled');
//
//    if (status.indexOf('Refuse') >= 0) {
//        $('#reply').show();
//        $('#reply-display').show();
//        var reply = $('#reply-display').children('p');
//        var who = $('#reply-display').children('small');
//        if (status.indexOf("Col") >= 0) {
//            reply.append(replyByCol);
//            who.append("学院批复");
//        } else {
//            $('#del').hide();
//            $('#confirm').hide();
//            $('#save').hide();
//            reply.append(replyByDep);
//            who.append("管理部门批复");
//        }
//    } else {
//        $('#reply').hide();
//    }
//}
///**
// * 保存
// */
//function save() {
//    $('#IsComplete').val(false);
//    var jsonData = getFormData('book');
//    //if (awardedData != null) {
//    //    jsonData['awardedData'] = awardedData;
//    //}
//    workflow.execute(userName, taskId, jsonData).success(function () {
//        afterSuccess("保存成功！");
//        window.location.href = '/index/process/book/all';
//    });
//}
//function confirm() {
//    $('#IsComplete').val(true);
//    var jsonData = getFormData('book');
//    //if (awardedData != null) {
//    //    jsonData['awardedData'] = awardedData;
//    //}
//    BootstrapDialog.confirm({
//        title: '确认信息',
//        message: '确认?',
//        type: BootstrapDialog.TYPE_INFO,
//        closable: true,
//        draggable: true,
//        btnCancelLabel: '取消',
//        btnOKLabel: '确认',
//        btnOKClass: 'btn-ok',
//        callback: function (result) {
//            /**
//             * userName,taskId,status
//             */
//            if (result) {
//                workflow.execute(userName, taskId, jsonData).success(function (data) {
//                    if ("valid" in data) {
//                        if (data["valid"] == true) {
//                            afterSuccess("确认成功！");
//                            window.location.href = '/index/process/book/all';
//                        } else {
//                            errorMsg(data["msg"]);
//                            flag = true;
//                        }
//                    } else {
//                        afterSuccess("确认成功！");
//                        window.location.href = '/index/process/book/all';
//                    }
//                });
//            }
//        }
//    });
//}
///**
// * 撤回order
// */
//function getOrderBack() {
//    window.workflow.getBack(userName, orderId).success(function () {
//        afterSuccess("已撤回");
//        window.location.href = '/index/process/book/all';
//    });
//}
///**
// * 删除order
// */
//function delOrder() {
//    var order = $("#WF_Order").val();
//    BootstrapDialog.confirm({
//        title: '提示！',
//        message: '你确定要删除该项吗?',
//        type: BootstrapDialog.TYPE_WARNING,
//        closable: true,
//        draggable: true,
//        btnCancelLabel: '取消',
//        btnOKLabel: '确定',
//        btnOKClass: 'btn-warning',
//        callback: function (result) {
//            if (result) {
//                workflow.delOrder(order).success(function () {
//                    afterSuccess("删除成功！");
//                    window.location.href = '/index/process/book/all';
//                });
//            }
//        }
//    });
//}
var flag = true;
function save() {
    var send = new Object();
    send['IsComplete'] = 'false';
    send['actors'] = getActorsData();
    //send['fund'] = getFundsData();
    send['Main-Actor'] = Main_Actor;
    send['Main-ActorName'] = Main_ActorName;
    //if($('#attr').val() == '联合项目' || entity['attr'] == "子课题"){
    //    send['units'] = getUnitsData();
    //}
    workflow.execute(userName, taskId, send).success(function () {
        afterSuccess("保存成功！");
        window.location.href = '/index/process/book/all';
    });
}
function confirm() {
    var status = all['Status'];
    var send = new Object();
    send['IsComplete'] = 'true';
    send['Main-Actor'] = Main_Actor;
    send['Main-ActorName'] = Main_ActorName;
    send['actors'] = getActorsData();
    //send['fund'] = getFundsData();
    //if($('#attr').val() == '联合项目' || entity['attr'] == "子课题"){
    //    send['units'] = getUnitsData();
    //}
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
                workflow.execute(userName, taskId, send).success(function (data) {
                    if ("valid" in data) {
                        if (data["valid"] == true) {
                            afterSuccess("确认成功！");
                            window.location.href = '/index/process/book/all';
                        } else {
                            errorMsg(data["msg"]);
                        }
                    } else {
                        afterSuccess("确认成功！");
                        window.location.href = '/index/process/book/all';
                    }
                });
            }
        }
    });
}
/**************************编辑成员||计算分数||选择已有获奖著作**************************************/
//function addActor() {
//    BootstrapDialog.show({
//        type: BootstrapDialog.TYPE_PRIMARY,
//        message: function (dialog) {
//            var $message = $('<div></div>');
//            var pageToLoad = dialog.getData('pageToLoad');
//            $message.load(pageToLoad);
//            return $message;
//        },
//        title: "成员信息",
//        data: {
//            'pageToLoad': '/dialog/addActor.html'
//        },
//        closeByBackdrop: false,
//        buttons: [{
//            id: 'btn-oknm',
//            icon: 'glyphicon glyphicon-check',
//            label: '添加',
//            cssClass: 'btn-info',
//            autospin: false,
//            action: function (dialogRef) {
//                if (!isFull()) {
//                    messageModal('请将信息填写完整。');
//                    return;
//                }
//                subActorInfo(null, 1);
//                dialogRef.close();
//            }
//        }],
//        onshown: function () {
//            $('.bookTextNumber').show();
//            fillRoles(bookRoles);
//        }
//    });
//}
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
                console.log("***********");
                subActorInfo(index, 0);
                $('.removeActor').hide();
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
            DisplayForm($units, row["unit"], 0);
            //填充其他
            $('#actorsInfo').autofill(row, {
                findbyname: false,
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
                $("#score").removeAttr("disabled");
            } else {  //不可编辑
                //$("#btn-ok").attr("disabled", "disabled").hide();
                $("#score").attr("disabled", "disabled");
            }
            if (row["staff.id"] == "9998" || row["staff.id"] == "9999") {
                $("#score").attr("disabled", "disabled");
            }
        }
    });
}