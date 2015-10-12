/**
 * Created by zheng on 2015/10/12.
 */
/**与项目信息有关的 保存||确认||撤回||删除||提交所有**/
$(function () {
    //TODO
    $('#reply').hide();
    init();
});
function init() {
    var status = entity['Status'];
    var statusCode=processStatus(status,0,3);
    if (entity['process'] == '1' || entity['process'] == '9') {
        $('#reply').show();
        $('.onEdit').hide();
        $('.onDel').hide();
        $('#upload').hide();
        $('.addActor').hide();
        disableSelectize($('#pubType').selectize());
        uneditableForm();
        hideActorOperate();
        // 实体中不能审批，order中才可以。实体中没有status，所以这样判断
        switch (parseInt(statusCode)){
            case 1:
                $('.onApprove').hide();
            case 311:
                $('#reply').show();
                $('#reply-display').show();
                var reply = $('#reply-display').children('p');
                var who = $('#reply-display').children('small');
                reply.empty();
                who.empty();
                $('.onDel').show();
                break;
            case 301:
                $('#reply').show();
                $('#reply-display').show();
                var reply = $('#reply-display').children('p');
                var who = $('#reply-display').children('small');
                reply.empty();
                who.empty();
                $('.onDel').show();
                break;
        }

        //if ( !isNull(status) && status.indexOf('refuse')>=0) {
        //    $('#reply').show();
        //    $('#reply-display').show();
        //    var reply = $('#reply-display').children('p');
        //    var who = $('#reply-display').children('small');
        //    reply.empty();
        //    who.empty();
        //    if (status.indexOf("WaitForDep") >= 0) { // 学院通过了
        //        reply.append(replyByCol);
        //        who.append("学院批复");
        //        $('.onApproval').show();
        //    } else {
        //        $('.onApproval').hide();
        //        $('#reply').hide();
        //    }
        //}else{
        //    $('.onApproval').hide();
        //}
    } else if (entity['process'] == null || entity['process'] == '0') { // 刚刚新增或未启动
        $('.onApproval').hide();
        $('.onEdit').show();$('.onDel').show();
    }
}
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
                        workflow.startEntityOrder("book", $('#bookId').val()).success(function (data) {
                            //history.go(-1);
                        });
                    }
                }
            });
        })
    });

}
function orderBack() {
    var row = $('#BookTable').bootstrapTable('getSelections')[0];
    var order = row['id'];
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
                    //window.location.href = "/book";
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
                    //window.location.href = "/book";
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
                    // window.location.href = "/book";
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
            fillRoles(bookRoles);
            $('.bookTextNumber').show();
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
/********************************保存***************************/
function saveStep1() {
    return $.ajax({
        url: '/api/book/book',
        data: $('#book').serialize(),
        type: 'POST',
        dataType: 'text'
    })
}
function saveStep2(data) {
    var send = new Object();
    //避免新建的时候多次点击保存多次新建
    $('#bookId').val(data);
    send['actors'] = getActorsData();
    send['filesData'] = filesData;
    send['Main-Actor'] = Main_Actor;
    send['Main-ActorName'] = Main_ActorName;
    console.log(send);
    return $.ajax({
        type: 'put',
        url: '/api/book/' + data,
        data: JSON.stringify(send),
        dataType: 'json',
        contentType: 'application/json;charset=UTF-8'
    })
}