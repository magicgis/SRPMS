/**
 * Created by zheng on 2015/10/3.
 */
$(function () {

    init(entity, all, replyByDep, 1);
});
var flag = true;
function save() {
    var send = new Object();
    send['IsComplete'] = 'false';
    send['actors'] = getActorsData();
    send['fund'] = getFundsData();
    send['Main-Actor'] = Main_Actor;
    send['Main-ActorName'] = Main_ActorName;
    if($('#attr').val() == '联合项目' || entity['attr'] == "子课题"){
        send['units'] = getUnitsData();
    }
    workflow.execute(userName, taskId, send).success(function () {
        afterSuccess("保存成功！");
        window.location.href = '/index/process/project/all';
    });
}
function confirm() {
    var status = all['Status'];
    var send = new Object();
    send['IsComplete'] = 'true';
    send['Main-Actor'] = Main_Actor;
    send['Main-ActorName'] = Main_ActorName;
    send['actors'] = getActorsData();
    send['fund'] = getFundsData();
    if($('#attr').val() == '联合项目' || entity['attr'] == "子课题"){
        send['units'] = getUnitsData();
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
                workflow.execute(userName, taskId, send).success(function (data) {
                    if ("valid" in data) {
                        if (data["valid"] == true) {
                            afterSuccess("确认成功！");
                            window.location.href = '/index/process/project/all';
                        } else {
                            errorMsg(data["msg"]);
                        }
                    } else {
                        afterSuccess("确认成功！");
                        window.location.href = '/index/process/project/all';
                    }
                });
            }
        }
    });
}
/**************************编辑成员||计算分数||**************************************/
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
            fillRoles(projectRoles);
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
                findbyname: false,
                restrict: false
            });
            disableSelectize($actor);
            disableSelectize($role);
            disableSelectize($units);
            $("#rank").attr("disabled", "disabled");
            $(".editableModal").show();
            //是否可编辑
            if (flag) {//可编辑
                $("#btn-ok").removeAttr("disabled").show();
                $("#score").removeAttr("disabled");
            } else {  //不可编辑
                $("#btn-ok").attr("disabled", "disabled").hide();
                $("#score").attr("disabled", "disabled");
            }
            if (row["staff.id"] == "9998" || row["staff.id"] == "9999") {
                $("#score").attr("disabled", "disabled");
            }
        }
    });
}
//function getScore() {
//    var jsonData = getFormData('project');
//    workflow.getScore(jsonData).success(function (data) {
//        if (data["valid"] == false) { // 检验不合格
//            errorMsg(data["msg"]);
//            flag = true;
//        } else if (data["hasSum"] == false) { // 后台分配分数
//            $("#actorTable").bootstrapTable('load', data["actors"]);
//            flag = false;
//            errorMsg(data["msg"]);
//        } else if (data["hasSum"] == true) {  // 给总分，负责人分配分数
//            $("#score").val(data["sum"]);
//            $("#showSum").html("总分：" + data["sum"] + "分");
//            errorMsg("总分为" + data["sum"] + "分，" + data["msg"]);
//            flag = true;
//        }
//    });
//}
