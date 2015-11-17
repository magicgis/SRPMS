/*用户管理相关*/

//启用或禁用
function disableOrEnable(index, row, value) {
    //测试区
    if (row['user.status'] == 1) {
        return [
            '<label><input class="UserEnableChange ace ace-switch ace-switch-7" type="checkbox" ' +
            'checked name="switch-field-1"><span class="lbl"></span></label>'
        ].join('');
    } else {
        return [
            '<label><input class="UserDisableChange ace ace-switch ace-switch-7" type="checkbox" ' +
            'name="switch-field-1"><span class="lbl"></span></label>'
        ].join('');
    }
}

//启用或禁用 操作
window.operateEvents = {
    //测试
    'change .UserEnableChange': function (e, value, row, index) {
        var ableType = 'disable';
        var TipInfo = '已禁用';
        disableOrEnableUser(row['id'], ableType, TipInfo);
    },
    'change .UserDisableChange': function (e, value, row, index) {
        $('#UserTable').bootstrapTable('resetView', {'clickToSelect': false});
        var ableType = 'enable';
        var TipInfo = '已起用';
        disableOrEnableUser(row['id'], ableType, TipInfo);
    }
};

function disableOrEnableUser(id, ableType, TipInfo) {
    $.ajax({
        type: 'POST',
        url: '../api/staff/' + ableType + '/' + id,
        contentType: 'application/json;charset=UTF-8',
        success: function (result) {
            if (result.errmsg) {
                //失败提示
                failInfo('操作失败！');
                return;
            } else {
            }
        }
    });
}

$('#scanUser').click(function () {
    //console.log($('#UserTable').bootstrapTable('getSelections')[0]["id"]);
    window.location.href = '/user/' + viewTable.bootstrapTable('getSelections')[0]["id"];
});

$('#resetPwd').click(function () {
    var staff = viewTable.bootstrapTable('getSelections')[0];
    if (staff == null) {
        BootstrapDialog.show({
            title: '温馨提示：',
            message: '请选择一条要重置密码的数据！'
        });
        return;
    }
    $.ajax({
        type: 'put',
        url: '../api/staff/reset/' + staff['id'],
        contentType: 'application/json;charset=UTF-8',
        success: function (result) {
            if (result.errmsg) {
                //失败提示
                failInfo('密码重置失败！');
                return;
            } else {
                //成功提示
                successInfo('信息修改成功!');
            }
        }
    });
});

/*用户管理结束*/
