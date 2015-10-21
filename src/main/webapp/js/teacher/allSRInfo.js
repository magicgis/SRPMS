//参与类型切换
$('.level').click(function () {
    $('#levelOption').html(this.children[0].text + '<span class="ace-icon fa fa-caret-down icon-on-right"></span>');
    level = this.id;
    allTable.bootstrapTable("refresh", {url: apiUrl(srtype)});
    changeUrl();
});

$('#newPaper').click(function () {
    workflow.startOrder(userName, "basicProcess_Beta", "paper").success(function (data) {
        afterSuccess("新建成功！,请切换到论文查看");
        allTable.bootstrapTable("refresh");
    });
});

$('#newMag').click(function () {
    window.open("/magazine");
});

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
                workflow.submitByTeacher({WF_User: userName}).success(function (data) {
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