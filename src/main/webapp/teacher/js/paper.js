var actorTemp = [];
//var userCurrent=$.cookie('TokenUser');
$(function () {
    //$.ajaxSetup({
    //    headers:{'Authorization':$.cookie('TokenUser')},
    //    statusCode:{
    //        401:function(){
    //            window.location.href='../login.jsp';
    //        }
    //    }
    //});
    $('#PaperTable').bootstrapTable({
        url: '/api/workflow/order/xgfan/paper/all',
        sidePagination: "server",
        columns: [{
            radio: true
            //visible:false
        }, {
            field: 'id',
            title: 'id',
            sortable: true,
            visible: false
        }, {
            field: 'paperName',
            title: '论文名称',
            sortable: true
        }, {
            field: 'paperType',
            title: '论文类别',
            sortable: true,
            formatter: "typeTran"
        }, {
            field: 'pubDate',
            title: '发表日期',
            sortable: true
        }, {
            field: 'WF_Actor',
            title: '参与者',
            sortable: true,
            formatter: 'actorTran'
        }, {
            field: 'Status',
            title: '状态',
            sortable: true,
            formatter: 'statusTran'
        }, {
            field: 'operator',
            align: 'center',
            title: '操作',
            formatter: view
        }],
        responseHandler: tableTrans
    });

    $('#actorTable').bootstrapTable({
        columns: [{
            field: 'actor',
            title: '成员',
            sortable: true,
            footerFormatter: "totalNameFormatter"
        }, {
            field: 'role',
            title: '角色',
            sortable: true
        }, {
            field: 'marks',
            title: '分数',
            sortable: true,
            footerFormatter: "totalMarksFormatter"
        }, {
            field: 'units',
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

    $('#reply-box').hide();
    $('#reply').hide();
    $('#magName').selectize({
        valueField: 'id',
        labelField: 'name',
        searchField: 'name',
        sortField: {
            field: 'text',
            direction: 'asc'
        },
        options: [],
        create: false,
        load: function (query, callback) {
            if (!query.length) return callback();
            $.ajax({
                url: '../api/mag/json',
                type: 'GET',
                dataType: 'json',
                data: {
                    query: query
                },
                error: function () {
                    callback();
                },
                success: function (res) {
                    callback(res);
                }
            });
        }
    });
    $('#paperType').selectize();
});
//监听 点击论文表
$('#PaperTable').on('click-row.bs.table', function (e, row, $element) {
    $('form input').val(null);
    actorTemp = [];
    var orderId = row["id"];
    var status = row["Status"];
    if (status == "Blank" || status == "Uncomplete" || status.indexOf('Refuse') >= 0) {
        editableForm();
        $('#confirm').show();
        $('#save').show();
        $('#orderBack').hide();
        $('#del').show();
        workflow.latestTask(orderId).success(function (currentTask) {
            var taskId = currentTask[0]['id'];
            $('#WF_Task').val(taskId);
            if (status.indexOf('Refuse') >= 0) {
                $('#reply').show();
                $('#reply-display').show();
                var reply = $('#reply-display').children('p');
                var who = $('#reply-display').children('small');
                reply.empty();
                who.empty();
                if (status.indexOf("Col") >= 0) {
                    reply.append(currentTask[0]['variableMap']['replyByCol']);
                    who.append("学院批复");
                } else {
                    reply.append(currentTask[0]['variableMap']['replyByDep']);
                    who.append("管理部门批复");
                }
            } else {
                $('#reply').hide();
            }
        });
    } else {
        $('#del').hide();
        if (status == 'Complete' || status == 'WaitForSubmit') {
            $('#orderBack').show();
        } else {
            $('#orderBack').hide();
        }
        $('#confirm').hide();
        $('#save').hide();
        uneditableForm();
    }
    if (row['actors'] != null) {
        actorTemp = row['actors'];
    }
    $('#paper').autofill(row, {
        findbyname: true,
        restrict: false
    });
    magOrConfer();
    $("#actorTable").bootstrapTable('load', actorTemp);
    showForm();
});
$('#actorTable').on('editable-save.bs.table', function (e, row, $element) {
    $('#actorTable').bootstrapTable("load", actorTemp);
});
//监听 更换论文类型
$('#paperType').change(function () {
    magOrConfer();
});
//监听 申请差异
$("#DiffAdd").click(function () {
    window.location.href = "magazine.jsp";
});
$('#addActor').click(function () {

});
//监听 点击保存
$("#save").click(function () {
    save();
});
//监听 点击确认
$("#confirm").click(function () {
    confirm();
});
//监听 点击返回
$("#back").click(function () {
    showTable();
});
//监听 删除
$("#del").click(function () {
    delOrder();
});
//监听 点击撤回
$("#orderBack").click(function () {
    getOrderBack();
});
//监听 统一提交
$("#submit").click(function () {
    submit();
});
//监听 添加论文
$("#addPaper").click(function () {
    start();
});
/**
 * 撤回order
 */
function getOrderBack() {
    var row = $('#PaperTable').bootstrapTable('getSelections')[0];
    var order = row['id'];
    var jsonData = Object();
    jsonData['order'] = order;
    jsonData['user'] = userName;
    window.workflow.getBack(userName, order).success(function () {
        afterSuccess("已撤回");
        showTable();
    });
}
/**
 * 删除order
 */
function delOrder() {
    var row = $('#PaperTable').bootstrapTable('getSelections')[0];
    var order = row['id'];
    BootstrapDialog.confirm({
        title: '警告！',
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
                    showTable();
                });
            } else {
                BootstrapDialog.show({
                    title: '通知',
                    message: '你已取消。'
                });
            }
        }
    });
}
/**
 * 提交
 */
function submit() {
    $.ajax({
        type: 'post',
        url: '/api/workflow/submitAll',
        data: "WF_User=" + userName,
        success: function () {
            afterSuccess("提交成功");
            showTable();
        }
    });
}
/**
 * 新建
 */
function start() {
    $('form input').val(null);

    actorTemp = [{
        "actor": "",
        "marks": "0"
    }];
    $('#actorTable').bootstrapTable('load', actorTemp);
    workflow.startOrder(userName, "basicProcess_Beta", "paper").success(function (data) {
        afterSuccess("新建成功！");
        //showTable();
        showForm();
    });
}
/**
 * 确认
 */
function confirm() {
    $('#IsComplete').val(true);
    var jsonData = $("#paper").serializeJSON();
    $.each(jsonData, function (key, value) {
        if (value == "" || value == null) {
            delete jsonData[key];
        }
    });
    jsonData["actors"] = getActorsData();
    jsonData['filesData'] = filesData;
    jsonData["WF_User"] = userName;
    BootstrapDialog.confirm({
        title: '确认信息',
        message: '你确认保存吗?',
        type: BootstrapDialog.TYPE_INFO,
        closable: true,
        draggable: true,
        btnCancelLabel: '取消!',
        btnOKLabel: '确认!',
        btnOKClass: 'btn-ok',
        callback: function (result) {
            if (result) {
                workflow.execute(userName, '', jsonData).success(function () {
                    afterSuccess("确认成功！");
                    showTable();
                });
            } else {
                BootstrapDialog.show({
                    title: '通知',
                    message: '你已取消。'
                });
            }
        }
    });
}
/**
 * 保存
 */
function save() {
    $('#IsComplete').val(false);
    var jsonData = $("#paper").serializeJSON();
    $.each(jsonData, function (key, value) {
        if (value == "" || value == null) {
            delete jsonData[key];
        }
    });
    jsonData["actors"] = getActorsData();
    jsonData['filesData'] = filesData;
    jsonData["WF_User"] = userName;
    workflow.execute(userName, '', jsonData).success(function () {
        afterSuccess("保存成功！");
        showTable();
    });
}
/**
 * 上传论文资料
 * var myDropzone
 * */
var filesData = {};
Dropzone.autoDiscover = false;
$(".dropzone").dropzone({
    //headers:{'Authorization':o1['Authorization']},
    dictDefaultMessage: '请选择要上传的文件',
    clickable: true,
    url: "/api/file/upload",
    method: 'post',
    addRemoveLinks: true,
    dictRemoveFile: '删除',
    dictRemoveLinks: "x",
    dictCancelUpload: "x",
    autoDiscover: false,
    init: function () {
        this.on('success', function (file) {
            filesData[file.name] = file['xhr']['response'];
            console.log(file.name + "++" + file['xhr']['response']);
        });
        this.on('removedfile', function (file) {
            delUpload(file);
        });
    }
});
/**
 * 删除已上传的资料
 * */
function delUpload(file) {
    var keyName = file.name;
    for (var key in filesData) {
        if (key == keyName) {
            var fileId = filesData[keyName];
            delete key;
        }
    }
    ;
    $.ajax({
        type: '',//删除的方法
        url: '',//删除的请求路径
        data: '' + fileId,//提交的数据
        success: function () {

        }
    });
}
/**
 * 打开对话框
 */
function openDialog() {
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
            'pageToLoad': '../public/addActor.html'
        },
        buttons: [{
            id: 'btn-ok',
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
                subActorInfo();
                dialogRef.close();
            }
        }],
        onshown: function () {
            fillRoles('1');
        }
    });
}