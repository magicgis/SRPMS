/**
 * Created by zheng on 2015/4/22.
 */
var unitTemp = [];
var actorTemp = [];
var filesData = {};
//所有的专利
//var allUrl = '/api/workflow/order/' + userName + '/patent/all';
//当前url
//var url = allUrl;

$(function () {
    $('#PatentTable').bootstrapTable({
        url: '/api/workflow/order/' + userName + '/patent/all',
        sidePagination:"server",
        columns: [{
            radio:true
        },{
            field: 'id',
            title: 'id',
            sortable: true,
            visible:false
        },{
            field:'name',
            title:'专利名称',
            sortable:true
        },{
            field:'state',
            title:'专利状态',
            sortable:true
        },{
            field:'ActorList',
            title:'参与者',
            sortable:true,
            formatter: "actorTran"
        },{
            field:'Status',
            title:'状态',
            sortable:true,
            formatter:'statusTran'
        }],
        responseHandler:tableTrans
    });
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
            field:'rank',
            title:'排名',
            sortable:true,
            footerFormatter:"totalUnitFormatter"
        },{
            field:'unit',
            title:'单位名称',
            sortable:true
        },{
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
            {"id": "1026", "value": "实用专利"}]
        //onChange: function(result) {
        //    $('#patTypeValue').val( this.getItem(result)["context"]["innerHTML"] );
        //}
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
        onChange: function(result) {
            $('#deptValue').val( this.getItem(result)["context"]["innerHTML"] );
        }
    });
    $('#reply').hide();
});

/*统一提交*/
$("#submit").click(function(){
    submitAll();
});

/*
 * 保存
 *
 * */
function save(){
    $('#IsComplete').val(false);
    var jsonData = getFormData('patent');
    workflow.execute(userName,'',jsonData).success(function(){
        afterSuccess("保存成功！");
        window.location.href="/patent";
    });
}
/*
 * 确认
 *
 * */
function confirm(){
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
                    if("valid" in data){
                        if(data["valid"] == true){
                            afterSuccess("确认成功！");
                            window.location.href="/patent";
                        }else {
                            errorMsg(data["msg"]);
                        }
                    }else{
                        afterSuccess("确认成功！");
                        window.location.href="/patent";
                    }
                });
            }
        }
    });
}
/*
 * 提交所有
 *
 * */
function submitAll(){
    var submitData='WF_User='+userName;
    workflow.submitByTeacher(submitData).success(function(){
        afterSuccess("提交成功");
        window.location.href="/patent";
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
            disableSelectize($actor);
            disableSelectize($role);
            disableSelectize($units);
            $("#rank").attr("disabled", "disabled");
            $(".editableModal").show();
            //是否可编辑
            if(flag) {//可编辑
                $("#btn-ok").removeAttr("disabled").show();
                $("#marks").removeAttr("disabled");
            } else {  //不可编辑
                $("#btn-ok").attr("disabled", "disabled").hide();
                $("#marks").attr("disabled", "disabled");
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
        if(data["valid"] == false) { // 检验不合格
            errorMsg(data["msg"]);
            flag = true;
        }else if(data["hasSum"] == false) { // 后台分配分数
            $("#actorTable").bootstrapTable('load', data["actors"]);
            flag = false;
            errorMsg(data["msg"]);
        }else if(data["hasSum"] == true) {  // 给总分，负责人分配分数
            $("#score").val(data["sum"]);
            $("#showSum").html("总分：" + data["sum"] + "分");
            errorMsg("总分为" + data["sum"] + "分，" + data["msg"]);
            flag = true;
        }
    });
}

//编辑单位
//function editUnit(row, index) {
//    BootstrapDialog.show({
//        type: BootstrapDialog.TYPE_PRIMARY,
//        message: function (dialog) {
//            var $message = $('<div></div>');
//            var pageToLoad = dialog.getData('pageToLoad');
//            $message.load(pageToLoad);
//            return $message;
//        },
//        title: "成员信息",
//        data: {
//            'pageToLoad': '../public/addUnit.html'
//        },
//        closeByBackdrop: false,
//        buttons: [{
//            id: 'btn-ok',
//            icon: 'glyphicon glyphicon-check',
//            label: '保存',
//            cssClass: 'btn-info',
//            autospin: false,
//            action: function (dialogRef) {
//                if (!isFull()) {
//                    messageModal('请将单位信息填写完整。');
//                    return;
//                }
//                subUnitInfo(index);
//                dialogRef.close();
//            }
//        }],
//        onshown: function () {
//            var $unit = $("#unit").selectize();
//            //填充单位
//            DisplayForm($unit, row["unit"], 1);
//            //填充其他
//            $('#unitInfo').autofill(row, {
//                findbyname: true,
//                restrict: false
//            });
//        }
//    });
//}
//监听 点击专利表
//$('#PatentTable').on('click-row.bs.table', function (e, row, $element) {
//    $('form input').val(null);
//    actorTemp = [];
//    unitTemp = [];
//    var orderId = row["id"];
//    var status = row["Status"];
//    if (row['parentTaskId'] = !null) {
//        var taskId = row['id'];
//        $('#WF_Task').val(taskId);
//    }
//    uneditableForm();
//    disableSelectize($("#patType").selectize());
//    if (status == "" || status.indexOf('Refuse') >= 0) { // todo
//        showEdit();
//        workflow.latestTask(orderId).success(function(currentTask){
//            var taskId = currentTask[0]['id'];
//            $('#WF_Task').val(taskId);
//            if (status.indexOf('Refuse') >= 0) {
//                $('#reply').show();
//                $('#reply-display').show();
//                var reply = $('#reply-display').children('p');
//                var who = $('#reply-display').children('small');
//                reply.empty();
//                who.empty();
//                if (status.indexOf("Col") >= 0) {
//                    reply.append(currentTask[0]['variableMap']['replyByCol']);
//                    who.append("学院批复");
//                } else {
//                    reply.append(currentTask[0]['variableMap']['replyByDep']);
//                    who.append("管理部门批复");
//                }
//            } else {
//                $('#reply').hide();
//            }
//        });
//    } else {
//        $('#confirm').hide();
//        $('#save').hide();
//    }
//    //总分
//    var  score = row['score'];
//    if(score == undefined || score == null || score =="") {
//        $("#showSum").html("");
//    }else {
//        $("#showSum").html("总分："+score+"分");
//    }
//    //成员信息
//    if (row['actors'] != null) {
//        actorTemp = row['actors'];
//    }
//    $("#actorTable").bootstrapTable('load', actorTemp);
//    //单位信息
//    if(row['units'] !== null) {
//        unitTemp = row['units'];
//    }
//    $('#unitTable').bootstrapTable("load", unitTemp);
//    //输入框信息
//    $('#patent').autofill(row, {
//        findbyname: true,
//        restrict: false
//    });
//    $('#dept').val( row['dept.value'] );
//    $('#patType').val( row['patent.standard.value'] );
//    // 文件信息
//    showFiles(row["filesData"]);
//    showForm();
//});
//监听 点击保存
//$("#save").click(function(){
//    save();
//});
//监听 点击确认
//$("#confirm").click(function(){
//    confirm();
//});
//监听 点击返回
//$("#back").click(function(){
//    //showTable();
//    showTableSchool(url);
//});

////监听 添加单位
//$('#addUnit').click(function () {
//    addUnit();
//});
////监听 添加成员
//$('#addActor').click(function () {
//    addActor();
//});
////监听 分配分数
//$('#getScore').click(function () {
//    getScore();
//});
//监听  删除
//$("#del").click(function(){
//    delOrder();
//});

/**
 * 添加单位
 */
//function addUnit() {
//    BootstrapDialog.show({
//        type: BootstrapDialog.TYPE_PRIMARY,
//        message: function (dialog) {
//            var $message = $('<div></div>');
//            var pageToLoad = dialog.getData('pageToLoad');
//            $message.load(pageToLoad);
//            return $message;
//        },
//        title: "参与单位信息",
//        data: {
//            'pageToLoad': '/dialog/addUnit.html'
//        },
//        closeByBackdrop: false,
//        buttons: [{
//            id: 'btn-ok',
//            icon: 'glyphicon glyphicon-check',
//            label: '添加',
//            cssClass: 'btn-info',
//            autospin: false,
//            action: function (dialogRef) {
//                if (!isFull()) {
//                    messageModal('请将信息填写完整。');
//                    return;
//                }
//                subUnitInfo(null);
//                dialogRef.close();
//            }
//        }]
//    });
//}
/**
 * 添加成员
 */
//function addActor() {
//    BootstrapDialog.show({
//        type: BootstrapDialog.TYPE_PRIMARY,
//        message: function (dialog) {
//            var $message = $('<div></div>');
//            var pageToLoad = dialog.getData('pageToLoad');
//            $message.load(pageToLoad);
//            return $message;
//        },
//        title: "成员信息",
//        data: {
//            'pageToLoad': '../public/addActor.html'
//        },
//        closeByBackdrop: false,
//        buttons: [{
//            id: 'btn-oknm',
//            icon: 'glyphicon glyphicon-check',
//            label: '添加',
//            cssClass: 'btn-info',
//            autospin: false,
//            action: function (dialogRef) {
//                if (!isFull()) {
//                    messageModal('请将信息填写完整。');
//                    return;
//                }
//                subActorInfo(null, 1);
//                dialogRef.close();
//            }
//        }],
//        onshown: function () {
//            fillRoles(patentRoles);
//        }
//    });
//}