/**
 * Created by guofan on 2015/9/24.
 */

function confirm() {
    var jsonData = $("#magazine").serializeJSON();

    $.each(jsonData, function (key, value) {
        if (value == "" || value == null || key == "WF_Order") {
            delete jsonData[key];
        }
    });

    if (filesData != null) {
        jsonData['filesData'] = filesData;
    }
    //jsonData['filesData'] = filesData;
    jsonData["WF_User"] = userName;
    BootstrapDialog.confirm({
        title: '提交信息',
        message: '确认提交?',
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
                workflow.execute(userName, $('#WF_Task').val(), jsonData).success(function () {
                    BootstrapDialog.alert("提交成功！");
                    window.location.href="/magazine";
                });
            }
        }
    });
}