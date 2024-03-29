/**
 * Created by zheng on 2015/10/12.
 */
$(function () {

    init(entity, all, replyByDep, 1);
    //todo 算分信息
});
function save() {
    var send = new Object();
    send['IsComplete'] = 'false';
    send['actors'] = getActorsData();
    workflow.execute(userName, taskId, send).success(function () {
        afterSuccess("保存成功！");
        window.location.href = '/index/process/others/all';
    });
}
function confirm() {
    var send = new Object();
    if( isMainActor(Main_Actor, userName) ) {
        send =  getForm_notSerialize();
        send['actors'] = getActorsData();
        send['units']=getUnitsData();
        send['IsComplete'] = 'true';
    }
    send['WF_User'] = userName;
    send['WF_Task'] = taskId;
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
                workflow.execute(userName, taskId, send).success(function (data, textStatus, xhr) {
                    var statusCode =xhr.status;
                    if (statusCode == 200) {
                        if ("valid" in data) {
                            if (data["valid"] == true) {
                                afterSuccess("确认成功！");
                                window.location.href = '/index/process/others/all';
                            } else {
                                errorMsg(data["msg"]);
                            }
                        } else {
                            afterSuccess("确认成功！");
                            window.location.href = '/index/process/others/all';
                        }
                    } else if(statusCode == 204) {
                        window.location.href = '/index/process/others/all';
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
            fillRoles(otherRoles);
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
                findbyname: true,
                restrict: false
            });

            $("#textNumber").attr("disabled", "disabled");
            $(".editableModal").show();

            if (row["staff.id"] == "9998" || row["staff.id"] == "9999") {
                $("#score").attr("disabled", "disabled");
            }
        }
    });
}
