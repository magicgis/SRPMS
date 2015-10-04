/**
 * Created by huyuanyuan555 on 2015/10/2.
 */

var actorTemp = [];
var unitTemp = [];
var Main_Actor;
var Main_ActorName;

$(function () {
    $('#actorTable').bootstrapTable({
        columns: [
            {
                field: 'staff.id',
                title: '工号',
                sortable: true,
                visible: false
            }, {
                field: 'rank',
                title: '排名',
                sortable: true,
                footerFormatter: "totalNameFormatter"
            }, {
                field: 'staff.name',
                title: '成员',
                sortable: true
            }, {
                field: 'role',
                title: '角色',
                sortable: true
            }, {
                field: 'score',
                title: '分数',
                sortable: true,
                footerFormatter: "totalMarksFormatter"
            }, {
                field: 'unit',
                title: '归属单位',
                sortable: true
            }, {
                field: 'operate',
                title: '操作',
                sortable: true,
                formatter: "operateFormatter",
                events: "operateEvents"
            }],
        data: actorTemp
    });
    $('#unitTable').bootstrapTable({
        columns: [{
            field: 'rank',
            title: '排名',
            editable: false,
            sortable: true,
            footerFormatter: "totalUnitFormatter"
        }, {
            field: 'unit',
            title: '单位名称',
            editable: false,
            sortable: true
        }, {
            field: 'operate',
            title: '操作',
            sortable: false,
            formatter: "operateFormatterUnit",
            events: "operateEventsUnit"
        }],
        data: unitTemp
    });

    $('#patType').selectize({ //todo
        valueField: 'id',
        labelField: 'value',
        maxItems: 1,
        options: [
            {"id": "1023", "value": "国际发明专利"},
            {"id": "1024", "value": "中国发明专利"},
            {"id": "1025", "value": "外观专利"},
            {"id": "1026", "value": "实用专利"}],
        onChange: function (result) {
            $('#patTypeValue').val(this.getItem(result)["context"]["innerHTML"]);
        }
    });
    $('#dept').selectize({
        valueField: 'id',
        labelField: 'value',
        maxItems: 1,
        preload: true,
        load: function (query, callback) {
            $.ajax({
                url: '../api/baseinfo/院系',
                type: 'GET',
                dataType: 'json',
                error: function () {
                    callback();
                },
                success: function (res) {
                    callback(res);
                }
            });
        },
        onChange: function (result) {
            $('#deptValue').val(this.getItem(result)["context"]["innerHTML"]);
        }
    });

    //console.log([dept]);

    //addOptionSelectize($('#dept').selectize(), [dept]);
    //DisplayForm($('#dept').selectize(), dept['id'], 0);
    //DisplayForm($('#patType').selectize(), standardId, 0);

});


/*
 * 保存
 *
 * */
function save() {
    var id = $('#patentId').val();
    var s = $('#patent').serialize();
    $.ajax({
        url: '/api/patent/patent',
        data: $('#patent').serialize(),
        type: 'POST',
        dataType: 'text',
        success: function (data) {
            var send = new Object();
            send['actors'] = getActorsData();
            send['units'] = getUnitsData();
            send['filesData'] = filesData;
            send['Main-Actor'] = Main_Actor;
            send['Main-ActorName'] = Main_ActorName;
            //todo 这儿还应该放上附件信息，主用户信息Main-Actor
            $.ajax({
                type: 'put',
                url: '/api/patent/' + data,
                data: JSON.stringify(send),
                dataType: 'json',
                contentType: 'application/json;charset=UTF-8',
                success: function (res) {
                    history.go(-1);
                }
            })
        }
    })
}
/*
 * 确认
 *
 * */
function confirm() {
    $('#IsComplete').val(true);
    var jsonData = getFormData('patent');
    BootstrapDialog.confirm({
        title: '确认信息',
        message: '确认?',
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
                workflow.execute(userName, $('#WF_Task').val(), jsonData).success(function (data) {
                    if ("valid" in data) {
                        if (data["valid"] == true) {
                            afterSuccess("确认成功！");
                            window.location.href = "/patent";
                        } else {
                            errorMsg(data["msg"]);
                        }
                    } else {
                        afterSuccess("确认成功！");
                        window.location.href = "/patent";
                    }
                });
            }
        }
    });
}
/*
 * 删除
 *
 * */
function delOrder() {
    var order = $("#WF_Order").val();
    BootstrapDialog.confirm({
        title: '提示！',
        message: '你确定要删除该项吗?',
        type: BootstrapDialog.TYPE_WARNING,
        closable: true,
        draggable: true,
        btnCancelLabel: '取消',
        btnOKLabel: '确定',
        btnOKClass: 'btn-warning',
        callback: function (result) {
            if (result) {
                workflow.delOrder(order).success(function () {
                    afterSuccess("删除成功！");
                    window.location.href = "/patent";
                });
            }
        }
    });
}

/**
 * 添加成员
 */
function addActor() {
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
            'pageToLoad': '/dialog/addActor.html'
        },
        closeByBackdrop: false,
        buttons: [{
            id: 'btn-oknm',
            icon: 'glyphicon glyphicon-check',
            label: '添加',
            cssClass: 'btn-info',
            autospin: false,
            action: function (dialogRef) {
                if (!isFull()) {
                    messageModal('请将信息填写完整。');
                    return;
                }
                subActorInfo(null, 1);
                dialogRef.close();
            }
        }],
        onshown: function () {
            fillRoles(patentRoles);
        }
    });
}
/**
 * 编辑成员
 */
function editActor(row, index) {
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
            'pageToLoad': '/dialog/addActor.html'
        },
        closeByBackdrop: false,
        buttons: [{
            id: 'btn-ok',
            icon: 'glyphicon glyphicon-check',
            label: '保存',
            cssClass: 'btn-info',
            autospin: false,
            action: function (dialogRef) {
                if (!isFull()) {
                    messageModal('请将信息填写完整。');
                    return;
                }
                subActorInfo(index, 0);
                dialogRef.close();
            }
        }, {
            id: 'btn-cancel',
            icon: 'glyphicon glyphicon-check',
            label: '关闭',
            cssClass: 'btn-info',
            autospin: false,
            action: function (dialogRef) {
                dialogRef.close();
            }
        }],
        onshown: function () {
            fillRoles(patentRoles);
            var $actor = $("#actor").selectize();
            var $role = $("#role").selectize();
            var $units = $("#units").selectize();
            addOptionSelectize($actor, [{'id': row["staff.id"], 'name': row["staff.name"], "col": {"value": ""}}]);
            $actor[0].selectize.setValue(row["staff.id"]);
            //填充角色
            DisplayForm($role, row["role"], 0);
            //填充单位
            DisplayForm($units, row["unit"], 1);
            //填充其他
            $('#actorsInfo').autofill(row, {
                findbyname: true,
                restrict: false
            });
        }
    });
}
/**
 * 添加单位
 */
function addUnit() {
    BootstrapDialog.show({
        type: BootstrapDialog.TYPE_PRIMARY,
        message: function (dialog) {
            var $message = $('<div></div>');
            var pageToLoad = dialog.getData('pageToLoad');
            $message.load(pageToLoad);
            return $message;
        },
        title: "参与单位信息",
        data: {
            'pageToLoad': '/dialog/addUnit.html'
        },
        closeByBackdrop: false,
        buttons: [{
            id: 'btn-ok',
            icon: 'glyphicon glyphicon-check',
            label: '添加',
            cssClass: 'btn-info',
            autospin: false,
            action: function (dialogRef) {
                if (!isFull()) {
                    messageModal('请将信息填写完整。');
                    return;
                }
                subUnitInfo(null);
                dialogRef.close();
            }
        }]
    });
}
/**
 * 编辑单位
 */
function editUnit(row, index) {
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
            'pageToLoad': '/dialog/addUnit.html'
        },
        closeByBackdrop: false,
        buttons: [{
            id: 'btn-ok',
            icon: 'glyphicon glyphicon-check',
            label: '保存',
            cssClass: 'btn-info',
            autospin: false,
            action: function (dialogRef) {
                if (!isFull()) {
                    messageModal('请将单位信息填写完整。');
                    return;
                }
                subUnitInfo(index);
                dialogRef.close();
            }
        }],
        onshown: function () {
            var $unit = $("#unit").selectize();
            //填充单位
            DisplayForm($unit, row["unit"], 1);
            //填充其他
            $('#unitInfo').autofill(row, {
                findbyname: true,
                restrict: false
            });
        }
    });
}
/**
 * 通过
 */
function approve() {
    var approveInfo = Object();
    approveInfo["DecByDep"] = true;
    approveInfo["replyByDep"] = $('#reply-box').val();
    BootstrapDialog.confirm({
        title: '确认信息',
        message: '你确认通过吗?',
        type: BootstrapDialog.TYPE_SUCCESS,
        closable: true,
        draggable: true,
        btnCancelLabel: '取消!',
        btnOKLabel: '确认!',
        btnOKClass: 'btn-ok',
        callback: function (result) {
            if (result) {
                workflow.execute('dep', $('#WF_Task').val(), approveInfo).success(function () {
                    window.location.href = "/patent";
                });
            }
        }
    });
}
/**
 * 驳回
 */
function refuse() {
    var refuseAwardInfo = Object();
    refuseAwardInfo["DecByDep"] = false;
    refuseAwardInfo["replyByDep"] = $('#reply-box').val();
    BootstrapDialog.confirm({
        title: '警告！',
        message: '你确定驳回吗?',
        type: BootstrapDialog.TYPE_WARNING,
        closable: true,
        draggable: true,
        btnCancelLabel: '取消',
        btnOKLabel: '确定',
        btnOKClass: 'btn-warning',
        callback: function (result) {
            if (result) {
                workflow.execute('dep', $('#WF_Task').val(), refuseAwardInfo).success(function () {
                    window.location.href = "/patent";
                });
            }
        }
    });
}

