/**
 * Created by huyuanyuan555 on 2015/4/26.
 */
$(function () {
//员工基本信息不可编辑
    //员工信息填充
    staffInfoFill();
    $('#baseInfo input').attr("readOnly", 'true');
    $('#baseInfo select').attr("disabled", 'true');
});

//账号管理
$('#editInfo').click(function () {
    formShow();
    $('#staId').focus();
});
$('#save').click(function () {
    save();
});

$('#cancel').click(function () {
    cancel();
});
//表单填充信息
function staffInfoFill() {
    $.ajax({
        type: 'get',
        url: '/api/user/current',
        success: function (data) {
            console.log(dataTrans(data));
            var transData = dataTrans(data);
            $('#staffInfo').autofill(transData, {
                findbyname: true,
                restrict: false
            })
        }
    });
}
//数据平铺
function dataTrans(data) {
    var currUser = data;
    for (var key in data) {
        if (typeof(data[key]) == 'object') {
            var dataObject = data[key];
            for (var k in dataObject) {
                currUser[key + '.' + k] = data[key][k];
            }
        }
    }
    return currUser;
}
//保存修改的账号信息
function save() {
    var accountInfo = $('#account').serializeJSON();
    accountInfo['user'] = $('#id').val();
    console.log(accountInfo);
    $.ajax({
        Authorization: $.cookie('srpms_token'),
        url: '/api/user/password',
        type: 'put',
        data: JSON.stringify(accountInfo),
        contentType: 'application/json;charset=UTF-8',
        success: function (data) {
            console.log(data);
            if (data == 'true') {
                messageModal("修改成功。");
            } else if (data == 'false') {
                messageModal("修改失败。");
            }

        }
    });
}


//取消按钮功能
function cancel() {
    $('#account').hide();
}

function formShow() {
    $('#account').show();
}