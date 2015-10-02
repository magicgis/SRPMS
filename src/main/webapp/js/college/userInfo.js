/**
 * Created by huyuanyuan555 on 2015/4/26.
 */

$(function () {
    $('#UserTable').bootstrapTable({
        url: '/api/staff/all',
        flat: true,
        sidePagination: "server",
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
            // formatter:"typeTran"
        }, {
            field: 'srank',
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
            field: 'user.privilege',
            title: '角色',
            sortable: true
        }]
    });
    allSelects();
});

//修改教师信息
$('#editUser').click(function () {
    var row = $('#UserTable').bootstrapTable('getSelections');
    if (row.length != 1) {
        BootstrapDialog.show({
            title: '温馨提示：',
            message: '请选择一条要编辑的数据！'
        });
        return;
    }
    var staff = row[0];
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
    selectFill(staff);
    //选中记录加载到表单中
    $('#staffInfo').autofill(staff, {
        findbyname: true,
        restrict: false
    });
    userFormShow();
});

//重置用户密码
$('#resetPwd').click(function () {
    rePassword();
});

//返回到教师信息主页面
$('#canEdit').click(function () {
    showTable();
});
//*********************************************样式显示********************************
//保存修改后的数据
$('#save').click(function () {
    var staff = $('#staffInfo').serializeJSON();
    var id = $('#staffId').val();
    $.ajax({
        url: '/api/staff/' + id,
        type: 'put',
        data: JSON.stringify(staff),
        success: function (result) {
            if (result.errmsg) {
                //失败提示
                failInfo('信息修改失败！');
                return;
            } else {
                /*//成功提示
                 successInfo('信息添加成功!');*/
                showTable();
            }
        },
        error: function (msg) {
            failInfo('信息修改失败！');
        }
    });
});

//重置密码
function rePassword() {
    var staff = $('#UserTable').bootstrapTable('getSelections')[0];
    if (staff == null) {
        BootstrapDialog.show({
            title: '温馨提示：',
            message: '请选择一条数据！'
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
                showTable();
            }
        }
    });
    showTable();
}

//学院 角色不可编辑
$(function () {
    $('#role').attr("readOnly", 'readonly');
    $('#staId').attr("readOnly", 'readonly');
});
