/**
 * Created by huyuanyuan555 on 2015/4/26.
 */

$(function () {
    $('#UserTable').bootstrapTable({
        url: '/api/staff/all',
        sidePagination: "server",
        flat: "true",
        columns: [{
            checkbox: true,
            visible: true
        }, {
            field: 'id',
            title: 'id',
            sortable: true,
            visible: false
        }, {
            field: 'name',
            title: '姓名',
            sortable: true
        }, {
            field: 'idCard',
            title: '身份证号',
            sortable: true
        }, {
            field: 'rank.value',
            title: '职称',
            sortable: true
        }, {
            field: 'position',
            title: '岗位',
            sortable: true
        }, {
            field: 'edu',
            title: '学历',
            sortable: true
        }, {
            field: 'degree',
            title: '学位',
            sortable: true
        }, {
            field: 'operator',
            align: 'center',
            title: '操作',
            formatter: disableOrEnable,
            events: operateEvents
        }]
    });
    allSelects();
});
//添加教师信息
$('#addUser').click(function () {
    //清空表单中的数据
    $('form input').val(null).removeAttr('selected');
    $('#staffId').removeAttr("disabled", 'disabled');
    userFormShow();
    $('#saveEdit').hide();
    $('#saveAdd').show();
});

//保存添加的数据
$('#saveAdd').click(function () {
    saveAdd();
    //清空表单中的数据
    $('form input').val(null).removeAttr('selected');
});

//修改教师信息
$('#editUser').click(function () {
    // $('#saveEdit').show();//只是测试 根据用户状态判断用户信息是否可编辑
    var row = $('#UserTable').bootstrapTable('getSelections');
    editUser(row);
});

//保存修改后的数据
$('#saveEdit').click(function () {
    saveEdit();
    //清空表单中的数据
    $('form input').val(null).removeAttr('selected');
});
//重置用户密码
$('#resetPwd').click(function () {
    rePassword();
});

//返回到教师信息主页面
$('#canEdit').click(function () {
    showTable();
});
//*********************************************样式显示和具体功能函数********************************
//保存添加的数据
function saveAdd() {
    var staff = $('#staffInfo').serializeJSON();
    $.ajax({
        url: '../api/user/new',
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
                showTable();
            }
        },
        error: function (msg) {
            //失败提示
            failInfo('信息添加失败！');
        }
    });
}

////编辑教师信息表单样式
function editUser(row) {
    if (row.length != 1) {
        BootstrapDialog.show({
            title: '温馨提示：',
            message: '请选择一条要编辑的数据！'
        });
        return;
    }
    var staff = row[0];
    //console.log(row);
    //根据用户状态判断用户信息是否可编辑
    if (staff['user.status'] == 1) {
        $('#staffInfo input').removeAttr('disabled', 'disabled');
        $('#staffInfo select').removeAttr('disabled', 'disabled');
        $('#saveEdit').show();
    } else {
        $('#staffInfo input').attr('disabled', 'disabled');
        $('#staffInfo select').attr('disabled', 'disabled');
        $('#saveEdit').hide();
    }

    $('form input').val(null);
    //选择框数据加载
    //console.log(staff);
    selectFill(staff);
    //选中记录加载到表单中
    $('#staffInfo').autofill(staff, {
        findbyname: true,
        restrict: false
    });
    userFormShow();
}


//保存修改后的数据
function saveEdit() {
    var staff = $('#staffInfo').serializeJSON();
    var id = $('#staffId').val();
    $.each(staff, function (key, value) {
        if (key == 'user.privilege') {
            delete staff[key];
        }
    });
    console.log(staff);
    $.ajax({
        url: '/api/staff/' + id,
        type: 'put',
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(staff),
        success: function (result) {
            console.log(result);
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
    var staff = $('#UserTable').bootstrapTable('getSelections')[0];
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
                /*//成功提示
                 successInfo('信息修改成功!');*/
                showTable();
            }
        }
    });
}
