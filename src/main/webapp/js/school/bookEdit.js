/**
 * Created by zheng on 2015/10/12.
 */
/**与项目信息有关的 保存||确认||撤回||删除||提交所有**/
/**
 * Created by zheng on 2015/10/12.
 */
$(function () {
    //init();
    //unEditTableBook();
    init(entity,all,replyByDep,3);
});
/**与著作信息有关的 保存||确认||撤回||删除||提交所有**/
function save() {
    var Info = getMainActor();
    Main_Actor = Info['Main-Actor'];
    Main_ActorName = Info['Main-ActorName'];
    saveStep1().success(function(data) {
        saveStep2(data).success(function (res) {
            afterSuccess("保存成功！");
            window.location.href = '/index/entity/book/all';
        })
    });
}
function confirm() {
    var Info = getMainActor();
    if(!isNull(Info['Main-Actor'])) {
        Main_Actor = Info['Main-Actor'];
        Main_ActorName = Info['Main-ActorName'];
    } else {
        messageModal('请填写我校参与职工');
        return;
    }
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
                        workflow.startEntityOrder("book", $('#bookId').val()).success(function (data) {
                            afterSuccess("任务已启动！");
                            window.location.href = '/index/entity/book/all';
                        });
                    }
                }
            });
        })
    });

}
function orderBack() {
    window.workflow.getEntityBack('book', $('#bookId').val()).success(function (data) {
        if (data == 'true') {
            afterSuccess("已撤回");
            window.location.href = '/index/entity/book/all';
        } else {
            afterSuccess('撤回失败');
        }

    });
}
function delOrder() {
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
                workflow.delEntity('book', $('#bookId').val()).success(function (data) {
                    afterSuccess("删除成功！");
                    window.location.href = '/index/entity/book/all';
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
                workflow.execute('dep', taskId, approveInfo).success(function () {
                    afterSuccess('审批通过！');
                    window.location.href = '/index/process/book/all';
                });
            }
        }
    });
}
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
                    window.location.href = '/index/process/book/all';
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
            $('.bookTextNumber').show();
            fillRoles(bookRoles);
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
            $('.bookTextNumber').show();
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
/********************************保存***************************/
function saveStep1() {
    var data = getFormJSON('book');
    //data['standard.id'] = $('#projbelong').val();
    console.log(data);
    return $.ajax({
        url: '/api/book/book',
        data: data,
        type: 'POST',
        dataType: 'text'
    });
}
function saveStep2(data) {
    var send = new Object();
    //避免新建的时候多次点击保存多次新建
    $('#bookId').val(data);
    send['actors'] = getActorsData();
    //send['fund'] = getFundsData();
    send['filesData'] = filesData;
    send['Main-Actor'] = Main_Actor;
    send['Main-ActorName'] = Main_ActorName;
    send['isAward']=$('#isAward').val();
    send['awarDtype']=$('#awarDtype').val();
    send['isTrans']=$('#isTrans').val();
    //if($('#attr').val() == '联合项目' || entity['attr'] == "子课题"){
    //    send['units'] = getUnitsData();
    //}
    return $.ajax({
        type: 'put',
        url: '/api/book/' + data,
        data: JSON.stringify(send),
        dataType: 'json',
        contentType: 'application/json;charset=UTF-8'
    })
}