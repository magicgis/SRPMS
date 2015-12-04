/**
 * Created by Administrator on 2015/10/3.
 */

//var flag = true;

$(function () {
    // 在教师界面的argMap，基本等同于order中的arg，
    // 存在Status,WF_Col,WF_Col_Id等等，基本是前台上一次放什么进去，第二次就能以同样的方式拿到
    // 还需要判断order的状态来 判断是否能算分与分配分数
    init(entity,all,replyByDep,1);
});
/*
 * 保存
 *   教师端的保存处理机制和论文大体一样
 *   区别在于：1，无需实体信息，即表格部分不需要，只需要table里的人
 *   保存就是isComplete为false，确认就是isComplete为true
 * */
function save() {
    var send = new Object();
    send['IsComplete'] = 'false';
    send['actors'] = getActorsData();
    workflow.execute(userName, taskId, send).success(function () {
        afterSuccess("保存成功！");
        window.location.href = '/index/process/patent/all';
    });
}
/*
 * 确认
 *
 * */
function confirm() {
    var status = all['Status'];
    var send = new Object();
    if(status == 'Uncomplete' || status == 'RefuseByCol'){
        send['IsComplete'] = 'true';
        send['actors'] = getActorsData();
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
                            window.location.href = '/index/process/patent/all';
                        } else {
                            errorMsg(data["msg"]);
                        }
                    } else {
                        afterSuccess("确认成功！");
                        window.location.href = '/index/process/patent/all';
                    }
                });
            }
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
            fillRoles(patentRoles);
            var $actor = $("#actor").selectize();
            var $role = $("#role").selectize();
            var $units = $("#units").selectize();
            addOptionSelectize($actor, [{'id': row["staff.id"], 'name': row["staff.name"], "col": {"value": ""}}]);
            $actor[0].selectize.setValue(row["staff.id"]);
            //填充角色
            DisplayForm($role, row["role"], 0);
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
            $(".editableModal").show();
            //是否可编辑
            //if (flag) {//可编辑
            //    $("#btn-ok").removeAttr("disabled").show();
            //    $("#score").removeAttr("disabled");
            //} else {  //不可编辑
            //    $("#btn-ok").attr("disabled", "disabled").hide();
            //    $("#score").attr("disabled", "disabled");
            //}
            if (row["staff.id"] == "9998" || row["staff.id"] == "9999") {
                $("#score").attr("disabled", "disabled");
            }
        }
    });
}
