/**
 * Created by zheng on 2015/10/12.
 */
$(function () {
    //TODO
    $('#reply').hide();
    init(entity,all,replyByDep,3);
});
/**与项目信息有关的 保存||确认||撤回||删除||提交所有**/
function save() {
    saveStep1().success(function(data) {

        saveStep2(data).success(function (res) {

        })
    });
}
function confirm() {
    //这儿需要先调用save()将信息保存一次
    saveStep1().success(function(data) {
        saveStep2(data).success(function (res) {
            BootstrapDialog.confirm({
                title: '是否启动流程',
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
                        workflow.startEntityOrder("newOther", $('#otherId').val()).success(function (data) {
                            //history.go(-1);
                        });
                    }
                }
            });
        })
    });

}
function orderBack() {
    var order = entity['id'];
    var jsonData = Object();
    jsonData['order'] = order;
    jsonData['user'] = userName;
    window.workflow.getBack(userName, order).success(function () {
        afterSuccess("已撤回");
    });
}
function delOrder() {
    var order = entity['id'];
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
                });
            }
        }
    });
}
function Approve() {
    var approveInfo = Object();
    approveInfo["DecByDep"] = true;
    approveInfo["replyByDep"] = $('#reply-box').val();
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
                workflow.execute('dep',taskId, approveInfo).success(function () {
                    afterSuccess('审批通过！');
                    //window.location.href = "/award";
                });
            }
        }
    });
}
/**
 * 驳回
 */
function Refuse() {
    var refuseInfo = Object();
    refuseInfo["DecByDep"] = false;
    refuseInfo["replyByDep"] = $('#reply-box').val();
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
                workflow.execute('dep', taskId, refuseInfo).success(function () {
                    afterSuccess('审批驳回！');
                    // window.location.href = "/award";
                });
            }
        }
    });
}
/*******************************有关成员的操作***************************/
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
            fillRoles(otherRoles);
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
            'pageToLoad': '../dialog/addActor.html'
        },
        closeByBackdrop: false,
        buttons: [{
            id: 'btn-ok',
            icon: 'glyphicon glyphicon-check',
            label: '保存',
            cssClass: 'btn-info',
            //hotkey: 83,
            autospin: false,
            action: function (dialogRef) {
                if (!isFull()) {
                    BootstrapDialog.show({
                        title: '通知',
                        type: BootstrapDialog.TYPE_INFO,
                        message: '请将信息填写完整。'
                    });
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
            //hotkey: 68,
            autospin: false,
            action: function (dialogRef) {
                dialogRef.close();
            }
        }],
        onshown: function () {
            fillRoles(otherRoles);
            //填充名字
            var $actor = $("#actor").selectize();
            $actor[0].selectize.addOption([{'id': row["staff.id"], 'name': row["staff.name"], "col": {"value": ""}}]);
            $actor[0].selectize.setValue(row["staff.id"]);
            //填充角色
            var $role = $("#role").selectize();
            DisplayForm($role, row["role"], 0);
            //填充单位
            var $units = $("#units").selectize();
            DisplayForm($units, row["unit"], 1);
            //填充其他
            $('#actorsInfo').autofill(row, {
                findbyname: true,
                restrict: false
            });
            //是否可编辑
            var flag = true;    //todo
            if (flag) {//可编辑
                enableSelectize($actor);
                enableSelectize($role);
                enableSelectize($units);
                $("#rank").removeAttr("disabled");
                $("#marks").removeAttr("disabled");
                //$("#btn-cancel").hide();
                $("#btn-ok").show();
                $(".editableModal").show();
            } else {  //不可编辑
                disableSelectize($actor);
                disableSelectize($role);
                disableSelectize($units);
                $("#aRank").attr("disabled", "disabled");
                $("#marks").attr("disabled", "disabled");
                $("#btn-ok").attr("disabled", "disabled").hide();
                //$("#btn-cancel").show();
                $(".editableModal").show();
            }
        }
    });
}
/*计算分数*/
function getScore() {
    var jsonData = getFormData("other");
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
/*****************************有关单位的操作***********************/
/**
 * 添加单位
 */
function addUnit() {
    BootstrapDialog.show({
        type: BootstrapDialog.TYPE_PRIMARY,
        message: function (dialog) {
            var $message = $('<div></div>');
            var pageToLoad = dialog.getData('pageToLoad');
            $message.load(pageToLoad);
            return $message;
        },
        title: "参与单位信息",
        data: {
            'pageToLoad': '../dialog/addUnit.html'
        },
        closeByBackdrop: false,
        buttons: [{
            id: 'btn-ok',
            icon: 'glyphicon glyphicon-check',
            label: '添加',
            cssClass: 'btn-info',
            autospin: false,
            action: function (dialogRef) {
                //console.log($("#rank").val());
                if (!isFull()) {
                    messageModal('请将信息填写完整。');
                    return;
                }
                subUnitInfo(null);
                dialogRef.close();
            }
        }]
    });
}
//编辑单位
function editUnit(row, index) {
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
            'pageToLoad': '../dialog/addUnit.html'
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
                    messageModal('请将单位信息填写完整。');
                    return;
                }
                subUnitInfo(index);
                dialogRef.close();
            }
        }],
        onshown: function () {
            var $unit = $("#unit").selectize();
            //填充单位
            DisplayForm($unit, row["unit"], 1);
            //填充其他
            $('#unitInfo').autofill(row, {
                findbyname: true,
                restrict: false
            });
        }
    });
}
/********************************保存***************************/
function saveStep1() {
    return $.ajax({
        url: '/api/newOther/newOther',
        data: $('#other').serialize(),
        type: 'POST',
        dataType: 'text'
    })
}
function saveStep2(data) {
    var send = new Object();
    //避免新建的时候多次点击保存多次新建
    $('#otherId').val(data);
    send['actors'] = getActorsData();
    send['filesData'] = filesData;
    send['Main-Actor'] = Main_Actor;
    send['Main-ActorName'] = Main_ActorName;
    send['units'] = getUnitsData();
    send['achName']= Main_ActorName;
    send['name']= $('#name').val();
    send['date']=$('#date').val();
    console.log(send);
    return $.ajax({
        type: 'put',
        url: '/api/newOther/' + data,
        data: JSON.stringify(send),
        dataType: 'json',
        contentType: 'application/json;charset=UTF-8'
    })
}
