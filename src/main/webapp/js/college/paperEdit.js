/**
 * Created by Administrator on 2015/10/7.
 */
$(function () {
    init();

    $("#upload").hide();
});

function init() {

    if (status == 'WaitForCol' || status.indexOf('RefuseByDep') >= 0) {
        $('#reply').show();
        $('#reply-box').show();
        if (status.indexOf("Dep") >= 0) {
            $('#reply-display').show();
            var reply = $('#reply-display').children('p');
            var who = $('#reply-display').children('small');
            reply.append(replyByDep);
            who.append("管理部门批复");
        }
    } else {
        $('#reply').hide();
        $("#Approve").hide();
        $("#Refuse").hide();
    }
}

/**
 * 通过
 * */
function approve() {
    var req = Object();
    req["DecByCol"] = true;
    req["replyByCol"] = $('#reply-box').val();
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
                workflow.execute(userName, taskId, req).success(function () {
                    window.location.href = '/paper';
                });
            } else {
                BootstrapDialog.show({
                    title: '通知',
                    message: '你已取消。'
                });
            }
        }
    });
}
/**
 * 驳回
 * */
function refuse() {
    var req = Object();
    req["DecByCol"] = false;
    req["replyByCol"] = $('#reply-box').val();
    BootstrapDialog.confirm({
        title: '警告！',
        message: '你确定要驳回吗?',
        type: BootstrapDialog.TYPE_WARNING,
        closable: true,
        draggable: true,
        btnCancelLabel: '取消',
        btnOKLabel: '确定',
        btnOKClass: 'btn-warning',
        callback: function (result) {
            if (result) {
                workflow.execute(userName, taskId, req).success(function () {
                    window.location.href = '/paper';
                });
            } else {
                BootstrapDialog.show({
                    title: '通知',
                    message: '你已取消。'
                });
            }
        }
    });
}