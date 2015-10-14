/**
 * Created by Administrator on 2015/10/3.
 */
$(function() {

    uneditableForm();
    hideUnitOperate();
    $('.onApproval').hide();
    $('.onDel').hide();
    $('#upload').hide();
    $('#reply-box').hide();
    $('#reply').hide();
    init();
});
function init() {
    var status = all['Status'];
    var isMain = isMainActor(Main_Actor,userName);
    var statusCode = processStatus(status,isMain,1);
    switch (parseInt(statusCode)){
        case 01:
            $('.confirm').show();
            hideActorOperate();
            $('#getScore').hide();
            $('.save').hide();
            break;
        case 00:
            $('.confirm').hide();
            hideActorOperate();
            $('#getScore').hide();
            $('.save').hide();
            break;
        case 11110:
            // hideActorOperate();
            $('#getScore').show();
            $('.save').show();
            $('.confirm').show();
            break;
        case 10110:
            $('.confirm').hide();
            hideActorOperate();
            $('#getScore').hide();
            $('.save').hide();
            break;
        case 10000:
            $('.confirm').hide();
            hideActorOperate();
            $('#getScore').hide();
            $('.save').hide();
            break;
        case 11111:
            $('#reply').show();
            $('#reply-display').show();
            var reply = $('#reply-display').children('p');
            var who = $('#reply-display').children('small');
            reply.empty();
            who.empty();
            //$('.confirm').hide();
            //$('.save').hide();
            reply.append(replyByCol);
            who.append("学院批复");
            break;
    }

}


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
        //window.location.href = "/appraise";
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
                            //window.location.href = "/appraise";
                        } else {
                            errorMsg(data["msg"]);
                        }
                    } else {
                        afterSuccess("确认成功！");
                        //window.location.href = "/appraise";
                    }
                });
            }
        }
    });
}

/**
 * 编辑成员
 */
var flag=true;

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
            fillRoles(appraiseRoles);
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
            disableSelectize($actor);
            disableSelectize($role);
            disableSelectize($units);
            $("#rank").attr("disabled", "disabled");
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
    var jsonData = getFormData('paper');
    workflow.getScore(jsonData).success(function (data) {
        if (data["valid"] == false) { // 检验不合格
            errorMsg(data["msg"]);
            flag = true;
        } else if (data["hasSum"] == false) { // 后台分配分数
            $("#actorTable").bootstrapTable('load', data["actors"]);
            flag = false;
            errorMsg(data["msg"]);
        } else if (data["hasSum"] == true) {  // 给总分，负责人分配分数
            $("#score").val(data["sum"]);
            $("#showSum").html("总分：" + data["sum"] + "分");
            errorMsg("总分为" + data["sum"] + "分，" + data["msg"]);
            flag = true;
        }
    });
}