/**
 * Created by Administrator on 2015/6/10.
 */

$(function () {
    $("#baseInfoTable").bootstrapTable({
        url: '/api/baseinfo/all',
        sidePagination: "server",
        columns: [{
            radio: true,
            visible: true
        }, {
            field: 'id',
            title: 'id',
            visible: false
        }, {
            field: 'tableName',
            title: '类型',
            align: 'center',
            sortable: true
        }, {
            field: 'value',
            title: '值',
            sortable: true
        }]
    });
    //添加
    $("#add").click(function () {
        addItem();
    });
    $("#edit").click(function () {
        editItem();
    });
    $("#delete").click(function () {
        delItem();
    });
});
function addItem() {
    BootstrapDialog.show({
        type: BootstrapDialog.TYPE_PRIMARY,
        message: function (dialog) {
            var $message = $('<div></div>');
            var pageToLoad = dialog.getData('pageToLoad');
            $message.load(pageToLoad);

            return $message;
        },
        title: "成员信息",
        data: {
            'pageToLoad': '/dialog/baseInfo.html'
        },
        buttons: [{
            id: 'btn-add',
            icon: 'glyphicon glyphicon-check',
            label: '保存(S)',
            cssClass: 'btn-info',
            hotkey: 83,
            autospin: false,
            action: function (dialogRef) {
                if (!isFull()) {
                    BootstrapDialog.show({
                        title: '通知',
                        type: BootstrapDialog.TYPE_INFO,
                        message: '请将信息填写完整。'
                    });
                    return;
                }
                var JsonData = $("#baseInfo").serializeJSON();
                saveOrUpdate('/api/baseinfo/new', JSON.stringify(JsonData), 'post').success(function () {
                    $("#baseInfoTable").bootstrapTable('refresh');
                    dialogRef.close();
                });
            }
        }]
    });
}

function editItem() {
    var row = $("#baseInfoTable").bootstrapTable('getSelections');
    if (row.length != 1) {
        BootstrapDialog.show({
            title: '温馨提示：',
            message: '请选择一条要编辑的数据！'
        });
        return;
    }
    BootstrapDialog.show({
        type: BootstrapDialog.TYPE_PRIMARY,
        message: function (dialog) {
            var $message = $('<div></div>');
            var pageToLoad = dialog.getData('pageToLoad');
            $message.load(pageToLoad);

            return $message;
        },
        title: "成员信息",
        data: {
            'pageToLoad': '/dialog/baseInfo.html'
        },
        buttons: [{
            id: 'btn-edit',
            icon: 'glyphicon glyphicon-check',
            label: '保存(S)',
            cssClass: 'btn-info',
            hotkey: 83,
            autospin: false,
            action: function (dialogRef) {
                if (!isFull()) {
                    BootstrapDialog.show({
                        title: '通知',
                        type: BootstrapDialog.TYPE_INFO,
                        message: '请将信息填写完整。'
                    });
                    return;
                }
                var JsonData = $("#baseInfo").serializeJSON();
                var url = '/api/baseinfo/' + row.id;
                saveOrUpdate(url, JSON.stringify(JsonData), 'put').success(function () {
                    $("#baseInfoTable").bootstrapTable('refresh');
                    dialogRef.close();
                });
            }
        }],
        onshown: function () {
            $("#baseInfo").autofill(row[0], {
                findbyname: true
            });
        }
    });
}

function delItem() {
    var row = $("#baseInfoTable").bootstrapTable('getSelections');
    if (row.length != 1) {
        BootstrapDialog.show({
            title: '温馨提示：',
            message: '请选择一条要编辑的数据！'
        });
        return;
    }
    $.ajax({
        url: '/api/baseinfo/' + row[0].id,
        type: 'delete',
        success: function () {
            $("#baseInfoTable").bootstrapTable('refresh');
        },
        error: function () {
            BootstrapDialog.show({
                title: '通知',
                type: BootstrapDialog.TYPE_INFO,
                message: '该条数据正被使用，删除失败。'
            });
        }
    });
}

function saveOrUpdate(url, args, method) {
    return $.ajax({
        url: url,
        type: method,
        data: args,
        contentType: 'application/json;charset=UTF-8'
    });
}