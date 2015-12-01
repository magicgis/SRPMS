/**
 * Created by huyuanyuan555 on 2015/4/26.
 */
$(function(){
    if(!isNull(user['id'])){
        $('.editUser').show();
        uneditableForm();
    }else{
        $('.editUser').hide();
        $('.saveAdd').show();
        enableditForm()
    }
});
//*********************************************样式显示和具体功能函数********************************
//保存添加的数据
function saveAdd() {
    var staff = $('#staffInfo').serializeJSON();
    $.ajax({
        url: '/api/staff/new',
        type: 'POST',
        data: JSON.stringify(staff),
        contentType: 'application/json;charset=UTF-8',
        success: function (result) {
            console.log(result);
            if (!result) {
                successInfo('信息添加成功!');
                window.location.href = '/index/user/all';
            } else {
                failInfo('信息添加失败！');
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
    selectFill(user);
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

