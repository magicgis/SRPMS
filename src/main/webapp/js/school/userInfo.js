/**
 * Created by huyuanyuan555 on 2015/4/26.
 */
$(function(){
    //selectFill(user);
    //$('#staffInfo').autofill(user, {
    //    findbyname: true,
    //    restrict: false
    //});
    uneditableForm();
});
//添加教师信息
function addUser(){
    //清空表单中的数据
    $('form input').val(null).removeAttr('selected');
    $('#staffId').removeAttr("disabled", 'disabled');
    enableditForm();
    $('.saveEdit').hide();
    $('.saveAdd').show();
}
//*********************************************样式显示和具体功能函数********************************
//保存添加的数据
function saveAdd() {
    var staff = $('#staffInfo').serializeJSON();
    $.ajax({
        url: '/api/user/new',
        type: 'post',
        data: JSON.stringify(staff),
        contentType: 'application/json;charset=UTF-8',
        success: function (result) {
            if (result.errmsg) {
                //失败提示
                failInfo('信息添加失败！');
                return;
            } else {
                /* //成功提示
                 successInfo('信息添加成功!');*/
            }
        },
        error: function (msg) {
            //失败提示
            failInfo('信息添加失败！');
        }
    });
}

////编辑教师信息表单样式
function editUser() {
    var staff = user;
    //根据用户状态判断用户信息是否可编辑
    if (staff['status'] == 1) {
        enableditForm();
        $('.saveEdit').show();
    } else {
        uneditableForm();
        $('.saveEdit').hide();
    }

    //$('form input').val(null);
    ////选择框数据加载
    ////console.log(staff);
    selectFill(user);
    ////选中记录加载到表单中
    //$('#staffInfo').autofill(user, {
    //    findbyname: true,
    //    restrict: false
    //});
}


//保存修改后的数据
function saveEdit() {
    var staff = $('#staffInfo').serializeJSON();
    var id = $('#id').val();
    $.each(staff, function (key, value) {
        if (key == 'user.privilege') {
            delete staff[key];
        }
    });
    $.ajax({
        url: '/api/staff/' + id,
        type: 'put',
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(staff),
        success: function (result) {
            if (result.errmsg) {
                //失败提示
                failInfo('信息修改失败！');
                return;
            } else {
                /*//成功提示
                 successInfo('信息修改成功!');*/
                showTable();
            }
        },
        error: function (msg) {
            //失败提示
            failInfo('信息修改失败！');
        }
    });
}

/*
 * 教师密码重置
 *
 * */
function rePassword() {
    var staff = user;
    if (staff == null) {
        BootstrapDialog.show({
            title: '温馨提示：',
            message: '请选择一条要重置密码的数据！'
        });
        return;
    }
    $.ajax({
        type: 'put',
        url: '../api/staff/reset/' + staff['staff.id'],
        contentType: 'application/json;charset=UTF-8',
        success: function (result) {
            if (result.errmsg) {
                //失败提示
                failInfo('密码重置失败！');
                return;
            } else {
                /*//成功提示
                 successInfo('信息修改成功!');*/
                showTable();
            }
        }
    });
}
