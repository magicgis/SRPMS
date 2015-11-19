/**
 * Created by zheng on 2015/10/12.
 */
/**与项目信息有关的 保存||确认||撤回||删除||提交所有**/
/**
 * Created by zheng on 2015/10/12.
 */
$(function () {
    init();
    unEditTableBook();
});
function init() {
    if (status == 'WaitForDep') {
        $('#reply').show();
        $('#reply-box').show();
        $('#reply-display').show();
        var reply = $('#reply-display').children('p');
        var who = $('#reply-display').children('small');
        reply.append(replyByCol);
        who.append("学院批复");
    } else {
        $('#reply').hide();
        $("#Approve").hide();
        $("#Refuse").hide();
    }
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