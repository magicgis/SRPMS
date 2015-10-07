var actorTemp = [];
var filesData = {};
var pTable;
var flag = true;
/**
 * 论文信息表
 *全部||主导||确认||参与
 * */
$(function () {
    pTable = $('#PaperTable').bootstrapTable({
        sidePagination: "server",
        columns: [{
            radio: true
        }, {
            field: 'id',
            title: 'id',
            sortable: true,
            visible: false
        }, {
            field: 'name',
            title: '论文名称',
            sortable: true
        }, {
            field: 'type',
            title: '论文类别',
            sortable: true,
            formatter: "typeTran"
        }, {
            field: 'pubDate',
            title: '发表日期',
            sortable: true
        }, {
            field: 'ActorList',
            title: '参与者',
            sortable: true,
            formatter: "actorTran"
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
    allInfo();
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
    // 各种初始化
    getMagName();
    upToLoadFile();
    paperType();
    CollectionType();
    NewIssue();
    selectData();
    $('#reply-box').hide();
    $('#reply').hide();
    $('#confirmC').hide();
});
//论文表
var tableTypes = "PaperTable";
//所有的论文
var allUrl = '/api/workflow/order/' + userName + '/paper/all';
//主导的论文
var orderUrl = '/api/workflow/order/' + userName + '/paper/1st';
//参与的论文
var partakeUrl = '/api/workflow/order/' + userName + '/paper/2nd';
//等待确认的论文
var confirmUrl = '/api/workflow/' + userName + '/confirmTask';
//当前url
var url = allUrl;

function allInfo() {
    pTable.bootstrapTable('refresh', {url: allUrl});
}
//所有的论文
$('#allPaper').click(function () {
    $('.paOperate').show();
    url = allUrl;
    showTableTeacher(url, tableTypes);
    $('.tabOrdBtn').show();
    $('#confirmC').hide();
});
//主导论文
$('#orderPaper').click(function () {
    $('.paOperate').show();
    url = orderUrl;
    showTableTeacher(url, tableTypes);
    $('.tabOrdBtn').show();
    $('#confirmC').hide();
});
//参与论文
$('#partakeBtn').click(function () {
    $('.paOperate').hide();
    url = partakeUrl;
    showTableTeacher(url, tableTypes);
    $('#confirmC').hide();
});
/*待确认论文*/
$('#confirmBtn').click(function () {
    $('.paOperate').hide();
    $('#confirmC').show();
    url = confirmUrl;
    showTableTeacher(url, tableTypes);
});
//监听 点击论文表
$('#PaperTable').on('click-row.bs.table', function (e, row, $element) {
    $('form input').val(null);
    actorTemp = [];
    var orderId = row["id"];
    $("#WF_Order").val(orderId);
    var status = row["Status"];
    if (row['parentTaskId'] = !null) {
        var taskId = row['id'];
        $('#WF_Task').val(taskId);
    }
    var $magId = $("#magId").selectize();
    // 论文类型
    var $paperType = $("#type").selectize();
    var paperType = row["type"];
    DisplayForm($paperType, paperType, 0);

    if(paperType == "magPaper"){ // 期刊名称以及刊物级别
        // 期刊名称
        var magId = row["mag.id"];
        addOptionSelectize($magId, [{'id' : magId, 'name' : row['mag.name']}]);
        DisplayForm($magId, magId, 0);
        // 期刊级别
        if ((isNull(magId) || isInt(magId)) // 期刊没填 期刊在库中
            && !(status == "Blank" || status == "Uncomplete" || status.indexOf('Refuse') >= 0) // 期刊不在填写中
        ) {
            recoveryMagLevel();
        } else {
            replaceMagLevel();
            DisplayForm($("#otherPaper").selectize(), row["mag.standard.infoMap.col_type"], 0);
        }
    }// 显示magId与判断magId的代码顺序不能改

    if (status == "Blank" || status == "Uncomplete" || status.indexOf('Refuse') >= 0) {
        editableForm();
        enableSelectize($magId);
        if (document.querySelector("#otherPaper")) {
            enableSelectize($("#otherPaper").selectize());
        }
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
                    $('#del').hide();
                    $('#confirm').hide();
                    $('#save').hide();
                    reply.append(currentTask[0]['variableMap']['replyByDep']);
                    who.append("管理部门批复");
                }
            } else {
                $('#reply').hide();
            }
        });
    } else {
        uneditableForm();
        disableSelectize($magId);
        if (document.querySelector("#otherPaper")) {
            disableSelectize($("#otherPaper").selectize());
        }

        $('#del').hide();
        if (status == 'Complete' || status == 'WaitForSubmit') {
            $('#orderBack').show();
        } else {
            $('#orderBack').hide();
        }
        $('#confirm').hide();
        $('#save').hide();
    }
    magOrConfer();
    //总分
    var  score = row['score'];
    if(score == undefined || score == null || score =="") {
        $("#showSum").html("");
    }else {
        $("#showSum").html("可分配总分："+score+"分");
    }
    //成员信息
    if (row['actors'] != null) {
        actorTemp = row['actors'];
    }
    $("#actorTable").bootstrapTable('load', actorTemp);
    // 会议论文收录类型
    DisplayForm($("#conferType").selectize(), row["confer.standard.id"], 0);
    // 报刊等级
    DisplayForm($("#newsType").selectize(), row["newspaper.standard.id"], 0);
    // 填充表单
    $('#paper').autofill(row, {
        findbyname: true,
        restrict: false
    });
    // 文件信息
    showFiles(row["filesData"]);
    // 显示表单
    showForm();
});
//监听 更换论文类型
$('#type').change(function () {
    magOrConfer();
    magOrConfer_add();
});
//监听 增加新期刊
$("#addDiff").click(function () {
    window.location.href = "magazine";
});
//监听 添加成员
$('#addActor').click(function () {
    addActor();
});
//监听 分配分数
$('#getScore').click(function () {
    getScore();
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
$(".back").click(function () {
    showTableTeacher(url,tableTypes);
});
//监听 删除
$("#del").click(function () {
    delOrder();
});
//监听 点击撤回
$(".orderBack").click(function () {
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
//监听 部分input不可输入
$(".uneditableInput").focus(function () {
    this.blur();
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
        showTableTeacher(url, tableTypes);
    });
}
/**
 * 删除order
 */
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
                    showTableTeacher(url, tableTypes);
                });
            }
        }
    });
}
/**
 * 提交
 */
function submit() {
    var userInfo = "WF_User=" + userName
    workflow.submitByTeacher(userInfo).success(function () {
        afterSuccess("提交成功");
        showTableTeacher(url, tableTypes);
    });
}
/**
 * 新建
 */
function start() {
    workflow.startOrder(userName, "basicProcess_Beta", "paper").success(function (data) {
        afterSuccess("新建成功！");
        var orderId = data["id"];
        window.location.href='/order/'+orderId;
    });
}
/**
 * 确认
 */
function confirm() {
    $('#IsComplete').val(true);
    var jsonData = getFormData('paper');
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
                    if("valid" in data){
                        if(data["valid"] == true){
                            afterSuccess("确认成功！");
                            showTableTeacher(url, tableTypes);
                        }else {
                            errorMsg(data["msg"]);
                        }
                    }else{
                        afterSuccess("确认成功！");
                        showTableTeacher(url, tableTypes);
                    }
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
    //var jsonData = $("#paper").serialize();
    var jsonData = getFormData('paper');
    //console.log(jsonData);
    workflow.execute(userName, '', jsonData).success(function () {
        afterSuccess("保存成功！");
        showTableTeacher(url, tableTypes);
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
            'pageToLoad': 'dialog/addActor.html'
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
            fillRoles(paperRoles);
        }
    });
}
/**
 * 编辑成员
 */
function editActor(row, index){
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
            'pageToLoad': 'dialog/addActor.html'
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
            fillRoles(paperRoles);
            var $actor = $("#actor").selectize();
            var $role = $("#role").selectize();
            var $units = $("#units").selectize();
            addOptionSelectize($actor, [{'id' : row["staff.id"], 'name' : row["staff.name"],"col":{"value":""}}]);
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
            //是否可编辑
            if(flag) {//可编辑
                enableSelectize($actor);
                enableSelectize($role);
                enableSelectize($units);
                $("#rank").removeAttr("disabled");
                $("#marks").removeAttr("disabled");
                $("#btn-ok").show();
                $(".editableModal").show();
            } else {  //不可编辑
                disableSelectize($actor);
                disableSelectize($role);
                disableSelectize($units);
                $("#rank").attr("disabled", "disabled");
                $("#marks").attr("disabled", "disabled");
                $("#btn-ok").attr("disabled", "disabled").hide();
                $(".editableModal").show();
            }
            if(row["staff.id"] == "9998" || row["staff.id"] == "9999"){
                $("#marks").attr("disabled", "disabled");
            }
        }
    });
}
/**
 * 计算分数
 */
function getScore() {
    var jsonData = getFormData('paper');
    workflow.getScore(jsonData).success(function(data) {
        if(data["valid"] == false) {
            errorMsg(data["msg"]);
            flag = true;
        }else if(data["hasSum"] == false) {
            $("#actorTable").bootstrapTable('load', data["actors"]);
            flag = false;
            errorMsg(data["msg"]);
        }else if(data["hasSum"] == true) {
            $("#score").val(data["sum"]);
            $("#showSum").html("可分配总分：" + data["sum"] + "分");
            errorMsg("总分为" + data["sum"] + "分，" + data["msg"]);
            flag = true;
        }
    });
}

//刊物级别的替换
function replaceMagLevel() {
    if (document.querySelector("#magLevel")) {
        $("#magLevel").replaceWith(
            "<select id='otherPaper' class='form-control mag-input' name='mag.standard.infoMap.col_type'>" +
            "</select>");
        $("#otherPaper").selectize({
            valueField: 'id',
            labelField: 'value',
            create: false,
            options: [
                {"id": "其它外文论文", "value": "其它外文论文"},
                {"id": "其它中文论文", "value": "其它中文论文"}
            ],
            onChange: function(result){
                if(result == "其它外文论文"){
                    $("#magStandardId").val("1018");    //todo
                }else{
                    $("#magStandardId").val("1019");    //todo
                }
            }
        });
    }
}
//刊物级别的替换
function recoveryMagLevel() {
    if (document.querySelector("#otherPaper")) {
        $("#otherPaper").parent().empty().append("<input data-rel='tooltip' type='text' "
            + "class='form-control mag-input uneditableInput' id='magLevel' "
            + "name='mag.standard.infoMap.col_type' title='不可编辑' data-placement='right' "
            + "onfocus='this.blur()' onmouseover='showTooltip()'/>");
    }
}
// 获取表单的JSON数据
//function getJSONData() {
//    var jsonData = $("#paper").serializeJSON();
//    $.each(jsonData, function (key, value) {
//        if (isNull(value)) {
//            delete jsonData[key];
//        }
//    });
//    if (filesData != null) {
//        jsonData['filesData'] = filesData;
//    }
//    jsonData["actors"] = getActorsData();
//    jsonData["WF_User"] = userName;
//    return jsonData;
//}
// 期刊名称 以及根据名称自动填充信息
function getMagName() {
    $('#magId').selectize({
        valueField: 'id',
        labelField: 'name',
        searchField: 'name',
        sortField: {
            field: 'text',
            direction: 'asc'
        },
        maxItems: 1,
        create: true,
        load: function (query, callback) {
            if (!query.length) return callback();
            $.ajax({
                url: 'api/mag/json',
                url: 'api/mag/json',
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
        },
        onChange: function (result) {
            var magId = this;
            if ($("#magId").val() != "") {
                var getMag = $.ajax({
                    url: 'api/mag/' + result,
                    type: 'GET',
                    dataType: 'json'
                });
                // 根据magId获取期刊信息 并填充
                getMag.complete(function (info) {
                    var statusCode = getMag.status;
                    if (statusCode == 200) {
                        recoveryMagLevel();
                        $('#magName').val(magId.getItem(result)["context"]["innerHTML"]);
                        getMag.success(function (data) {
                            $("#magLevel").val(data["standard"]["infoMap"]["col_type"]);
                            $("#issn").val(data["issn"]);
                            $("#cn").val(data["cn"]);
                            $("#magStandardId").val(data["standard"]["id"]);
                        });
                    } else if (statusCode == 204) {
                        replaceMagLevel();
                        $('#magName').val(result);
                    }
                });
            }

        }
    });
}