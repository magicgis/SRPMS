$('.allSubmit').click(function () {

    BootstrapDialog.confirm({
        title: '统一提交',
        message: '确认 ?',
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
                workflow.submitByCol({WF_User: userName}).success(function (data) {
                    console.log(data);
                    if (data == 'true') {
                        afterSuccess("提交成功！");
                    } else {
                        BootstrapDialog.alert("提交失败");
                    }
                    $('#allSRInfoTable').bootstrapTable('refresh');
                });
            }
        }
    });
});