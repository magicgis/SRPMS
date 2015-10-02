/**
 * Created by guofan on 2015/9/24.
 */


/**
 * 通过
 * */
function confirm() {
    var req = Object();
    req["DecByDep"] = true;
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
                workflow.execute(userName, $('#WF_Task').val(), req).success(function () {
                    afterSuccess("已通过！");
                    window.location.href="/magazine";
                });
            }
        }
    });
}

/**
 * 删除order
 */
function delOrder() {
    var order = $('#WF_Order').val();
    console.log(order);
    BootstrapDialog.confirm({
        title: '提示！',
        message: '您确认该期刊不通过吗?',
        type: BootstrapDialog.TYPE_WARNING,
        closable: true,
        draggable: true,
        btnCancelLabel: '取消',
        btnOKLabel: '确定',
        btnOKClass: 'btn-warning',
        callback: function (result) {
            if (result) {
                workflow.delOrder(order).success(function () {
                    BootstrapDialog.alert("删除成功！");
                    window.location.href="/magazine";
                });
            }
        }
    });
}