var paperRoles = [{"role": "第一作者"}, {"role": "通讯作者"}, {"role": "第二作者"}
    , {"role": "第三作者"}, {"role": "第四作者"}, {"role": "其他"}];
var patentRoles = [{"role": "第一专利权人"}, {"role": "知识产权所有人"}];
var projectRoles = [{"role": "负责人"}, {"role": "参与人"}];
var bookRoles = [{"role": "主编或第一主编"}, {"role": "第二及以下编"}, {"role": "副主编"}, {"role": "参编"}];
var awardRoles = [{"role": "负责人"}, {"role": "参与人"}];
var appraiseRoles = [{"role": "负责人"}, {"role": "参与人"}];
var achTranRoles = appraiseRoles;
var foodRoles=appraiseRoles;
var instruRoles=appraiseRoles;
var medicineRoles=appraiseRoles;
var otherRoles=appraiseRoles;
/**--------------------------工作流公共方法------------------**/
var workflow = window.workflow || {};
/**
 * 删除order
 * @param order orderId
 * @returns {*}
 */
workflow.delOrder = function (order) {
    return $.ajax({
        type: 'DELETE',
        url: '/api/workflow/ord' + order
    })
};


/**
 * 开启新流程
 * @param user 用户
 * @param processName 流程名
 * @param type 流程类型
 * @returns {*}
 */
workflow.startOrder = function (user, processName, type) {
    return $.ajax({
        type: 'post',
        url: '/api/workflow/start',
        data: {
            WF_User: user,
            WF_Process: processName,
            WF_Type: type
        },
        dataType: 'json'
    });
};

workflow.startEntityOrder = function (type, entityId) {
    return $.ajax({
        type: 'post',
        url: '/api/workflow/start/' + type + "/" + entityId,
        dataType: 'json'
    });
};

/**
 * 撤回order
 * @param user 用户
 * @param order orderId
 * @returns {*}
 */
workflow.getBack = function (user, order) {
    var sendData = Object();
    sendData['order'] = order;
    sendData['user'] = user;
    return $.ajax({
        type: 'post',
        url: '/api/workflow/getBack',
        data: JSON.stringify(sendData),
        dataType: 'json',
        contentType: 'application/json;charset=UTF-8'
    })
};
workflow.getEntityBack = function (type, entityId) {
    return $.ajax({
        type: 'POST',
        url: '/api/workflow/reset/'+ type +'/'+entityId,
        contentType: 'application/json;charset=UTF-8'
    })
};
/**
 * 执行任务
 * @param user 用户
 * @param task taskId
 * @param args 参数（js object）
 * @returns {*}
 */
workflow.execute = function (user, task, args) {
    var data = Object();
    data['WF_User'] = user;
    data['WF_Task'] = task;
    jQuery.extend(data, args);
    return $.ajax({
        type: 'POST',
        url: '/api/workflow/execute',
        data: JSON.stringify(data),
        dataType: 'json',
        contentType: 'application/json;charset=UTF-8'
    });
};
/**
 * 获取Order最新任务
 * @param order orderId
 * @returns {*}
 */
workflow.latestTask = function (order) {
    return $.ajax({
        type: 'get',
        url: '/api/workflow/ord' + order + '/task',
        dataType: "json"
    });
};
/**
 * 统一提交
 * @param submitData
 * @returns boolean
 */
workflow.submitByTeacher = function (submitData) {
    return $.ajax({
        type: 'post',
        async: true,
        url: '/api/workflow/submitByTeacher',
        data: submitData,
        contentType: 'application/x-www-form-urlencoded;charset=UTF-8'
    });
};

/**
 * 统一提交
 * @param submitData
 * @returns boolean
 */
workflow.submitByCol = function (submitData) {
    return $.ajax({
        type: 'post',
        url: '/api/workflow/submitByCol',
        data: submitData,
        contentType: 'application/x-www-form-urlencoded;charset=UTF-8'
    });
};
/**
 * 获取总分或者分数分配
 * @param args
 * @returns {*}
 */
workflow.getScore = function (args) {
    return $.ajax({
        type: 'POST',
        url: '/api/workflow/getScore',
        data: JSON.stringify(args),
        contentType: 'application/json;charset=UTF-8'
    });
};

/**--------------------------实体类公共方法------------------**/
//var entity = window.entity || {};

workflow.delEntity = function(type, id) {
    return $.ajax({
        type: 'DELETE',
        url: '/api/'+ type +'/'+ id,
        contentType: 'application/json;charset=UTF-8'
    });
};


/**--------------------------处理数据公共方法 table相关的------------------**/
/**
 * 对获取到的数据进行处理
 * 方便表格显示
 * @param res
 * @returns {*}
 */
function tableTrans(res) {
    //res = res["rows"];
    /*var ans = res;*/
    if (res["rows"] == undefined || res["rows"] == null) {
        var respon = res;
    } else {
        respon = res["rows"]
    }
    $.each(respon, function (index, value) {
        /*取出variableMap*/
        respon[index]['orderId']=value['id'];
        var maps = value['variableMap'];
        /*把string放入*/
        for (var key in maps) {
            if (typeof(maps[key]) == 'string' || typeof(maps[key]) == 'object') {
                respon[index][key] = maps[key];
            }
        }
        /*拿出最新的args*/
        if (maps['Status'] != null || maps['Status'] != undefined) {
            if (maps['Status'] != 'Blank') {
                var temp = maps[getSubmission(maps)];
                /*瞎写都能生效……*/
                $.each(temp, function (key, value) {
                    respon[index][key] = temp[key];
                });
                if (temp['filesData'] != null || temp['filesData'] != undefined) {
                    respon[index]['filesData'] = temp['filesData'];
                }
            }
        }
    });
    if (res["rows"] == undefined || res["rows"] == null) {
        //console.log(respon);
        return respon;
    } else {
        res["rows"] = respon;
        return res;
    }
}

function getSubmission(pData) {
    var max;
    var keyStr = "";
    for (var key in pData) {
        var partn = new RegExp('WF_\\d+_Submission');
        if (partn.test(key)) {
            var keyValue = key.substring(3, key.length - 11);
            if (parseInt(max) < parseInt(keyValue) || max == undefined) {
                max = keyValue;
            }
        }
        if (max != undefined) {
            keyStr = "WF_" + max + "_Submission";
        }
    }
    return keyStr;
}
function getApprovalByCol(pData) {
    var max;
    var keyStr = "";
    for (var key in pData) {
        var partn = new RegExp('WF_\\d+_ApprovalByCol');
        if (partn.test(key)) {
            var keyValue = key.substring(3, key.length - 14);
            if (parseInt(max) < parseInt(keyValue) || max == undefined) {
                max = keyValue;
            }
        }
        if (max != undefined) {
            keyStr = "WF_" + max + "_ApprovalByCol";
        }
    }
    return keyStr;
}
function getApprovalByDep(pData) {
    var max;
    var keyStr = "";
    for (var key in pData) {
        var partn = new RegExp('WF_\\d+_ApprovalByDep');
        if (partn.test(key)) {
            var keyValue = key.substring(3, key.length - 14);
            if (parseInt(max) < parseInt(keyValue) || max == undefined) {
                max = keyValue;
            }
        }
        if (max != undefined) {
            keyStr = "WF_" + max + "_ApprovalByDep";
        }
    }
    return keyStr;
}
/**
 * 对英文双引号限制
 * */
$('#name').keyup(function(){
    $('#name').val($('#name').val().replace(/\"/g,"“"));
});
 /**
 * 公共方法 对任务的完成状态进行翻译
 * @param value
 * @return String
 * */
function statusTran(value, row) {
    //定义一个条件数组函数  所有状态项
    //审核中蓝颜色
    //已通过绿色
    //未审核红色
    var termArray = {
        'Blank': '<span class="label label-lg label-danger arrowed-in">待填写</span>',
        'Uncomplete': '<span class="label label-lg label-danger arrowed-in">已保存</span>',
        'Complete': '<span class="label label-lg label-danger arrowed-in">等待他人确认</span>',
        'ApprovedByCol': '<span class="label label-lg label-danger arrowed-in">待学院统一提交</span>',
        'WaitForSubmit': '<span class="label label-lg label-danger arrowed-in">待统一提交</span>',
        'WaitForCol': '<span class="label label-lg label-info arrowed-in">学院审核中</span>',
        'WaitForCollegeSubmit': '<span class="label label-lg label-danger arrowed-in">待学院统一提交</span>',
        'WaitForDep': '<span class="label label-lg label-info arrowed-in">管理部门审核中</span>',
        'RefuseByDep': '<span class="label label-lg label-danger arrowed-in">管理部门驳回，待学院审核</span>',
        'RefuseByCol': '<span class="label label-lg label-danger arrowed-in">学院驳回，待修改</span>'
    };
    if(!isNull(value)){
        var status=termArray[value];
        return status;
    }else{
        return '--';
    }
}

/**
 * 进度
 * @param arg
 * @returns {*}
 */
function processTran(arg) {
    var t = {
        "0": '<span class="label label-lg label-danger arrowed-in">未启动</span>',
        "1": '<span class="label label-lg label-info arrowed-in">流程中</span>',
        "9": '<span class="label label-lg label-success label-white middle">已结束</span>'
    };
    return t[arg];
}

//function typeTran(value) {
//    var termArray = {
//        "paper": "论文",
//        "book": "著作",
//        "project": "项目",
//        "patent":"专利",
//        "medicine":"新药",
//        "food":"功能性食品",
//        "instrument":"医疗器械",
//        "other":"其他"
//    };
//    if(isNull(value)){
//        return '';
//    }
//    return termArray[value];
//}

function paperTypeTran(value) {
    if (value == 'conferPaper') {
        return '会议论文';
    } else if (value == 'magPaper') {
        return '期刊论文';
    } else if (value == 'newsPaper') {
        return '报刊论文';
    } else {
        return '';
    }
}

function sumWordEntityTran(value, row) {
    if(value !== null){
        return value + '万字';
    }
    return;
}

function sumWordProcessTran(value, row) {
    if(value !== null) {
        return value + '万字';
    }
    //else if(row['sumWord'] !== null) {
    //    return row['sumWord']+ '万字';
    //}
    else {
        return;
    }

}

// 成员翻译
function actorTran(value, row) {
    if (value != undefined && value != null && value != "")
        return value.substring(0, value.length - 1);
    else
        return;
}

function bkAwardEntityTran(value, row) {
    if(row['argMap.isAward'] == 'false') {
        return '未获奖';
    } else {
        return row['argMap.awardtype']
    }
}

// 著作获奖是否翻译
function bkAwardTran(value, row){
    if(value == 'false') {
        return '未获奖';
    } else {
        return row['variableMap.awardtype'];
    }
}

// 编辑页面获得成员信息
function getActors() {
    var keyStr = getSubmission(all);
    if (isNull(all['actors'])) { // 新建的
        actorTemp = [];
    } else if (keyStr == "") {
        actorTemp = all['actors'];
    }
    else {
        actorTemp = all[keyStr]['actors'];
    }
}

function hideActorOperate() {
    $('#actorTable').bootstrapTable('hideColumn', 'operate');
}
function hideUnitOperate() {
    $('#unitTable').bootstrapTable('hideColumn', 'operate');
}
function hideFundOperate() {
    $('#fundTable').bootstrapTable('hideColumn', 'operate');
}
function hideColumnScore() {
    $('#actorTable').bootstrapTable('hideColumn', 'score');
}
/**--------------------------简单消息弹框------------------**/
function messageModal(message) {
    BootstrapDialog.show({
        title: '通知',
        type: BootstrapDialog.TYPE_INFO,
        message: message
    });
}
/**--------------------------获取编辑页面的数据------------------**/
function getFormJSON(type) {
    var jsonData = $("#" + type).serializeJSON();
    jsonData['score'] = (Math.round($('#totalScore').val())).toString();

    return jsonData;
}

function getForm_notSerialize(){
    var jsonData = new Object();

    var elementlist = document.querySelectorAll('input');
    $.each(elementlist, function(index, value) {
        var attrName = $(value).attr('name');
        var attrVal = $(value).attr('value');
        if( !isNull(attrName)) {
            jsonData[attrName] = attrVal;
        }
    });// 表单的序列化 不能用时 调用这个方法
    jsonData['score'] = (Math.round($('#totalScore').val())).toString();

    if (filesData != null) {
        jsonData['filesData'] = filesData;
    }
    if ($("form").attr('name') == 'project') {
        jsonData['fund'] = getFundsData();
    }
    jsonData["actors"] = getActorsData();
    jsonData["units"] = getUnitsData();
    jsonData["WF_User"] = userName;
    return jsonData;
}

function getFormData(type) {
    var jsonData = $("#" + type).serializeJSON();
    jsonData['score'] = (Math.round($('#totalScore').val())).toString();

    $.each(jsonData, function (key, value) {
        if (isNull(value)) {
            delete jsonData[key];
        }
    });

    if (filesData != null) {
        jsonData['filesData'] = filesData;
    }
    if (type == 'project') {
        jsonData['fund'] = getFundsData();
    }
    jsonData["actors"] = getActorsData();
    jsonData["units"] = getUnitsData();
    jsonData["WF_User"] = userName;
    return jsonData;
}


/**--------------------------拓展公共方法------------------**/
Array.prototype.remove = function (dx) {
    if (isNaN(dx) || dx > this.length) {
        return false;
    }
    for (var i = 0, n = 0; i < this.length; i++) {
        if (this[i] != this[dx]) {
            this[n++] = this[i]
        }
    }
    this.length -= 1
};

Array.prototype.unique = function () {
    var n = {}, r = []; //n为hash表，r为临时数组
    for (var i = 0; i < this.length; i++) //遍历当前数组
    {
        if (!n[this[i]]) //如果hash表中没有当前项
        {
            n[this[i]] = true; //存入hash表
            r.push(this[i]); //把当前数组的当前项push到临时数组里面
        }
    }
    return r;
};
/**
 * 获取级联选择框的第一个的数据
 * @param data standard表中的数据
 * @param str  需要的字段的key 作为字符串参数传进来
 * @returns {Array}
 */
function getList(data, str) {
    var tempArray = [];
    var tempList = [];
    var i = 0; // 是否可以被添加进去
    $.each(data, function (index, obj) {
        tempArray.push(obj['infoMap'][str]);
    });
    tempArray = tempArray.unique(); // array去重

    $.each(tempArray, function (index, value) { // 转换成JSON的List
        var type = {};
        type['value'] = value;
        tempList.push(type);
    });
    return tempList;
}

function getListWithId(data ,str){
    var tempList = [];
    $.each(data, function (index, obj) {
        var temp = {};
        temp['id'] = obj['id'];
        temp['value'] = obj['infoMap'][str];
        tempList.push(temp);
    });
    return tempList;
}

/**
 *
 * @param data standard表中的数据
 * @param str1 上一个选择框的筛选条件的key
 * @param str2 本选择框要显示的内容的key
 * @param result 对应于str1的筛选条件
 * @return tempList2
 */
function getStandardList(data, str1, str2, result) {

    var tempList2 = [];
    var tempList = $.grep(data, function (obj, index) {
        return obj['infoMap'][str1] == result;
    });
    $.each(tempList, function (index, obj) {
        var temp = {};
        temp['id'] = obj['id'];
        temp['value'] = obj['infoMap'][str2];
        tempList2.push(temp);
    });
    return tempList2;
}


function getStandardList1(data, str1, value1, str2, value2, str3) {

    var tempList2 = [];
    var tempList = $.grep(data, function (obj, index) {
        return obj['infoMap'][str1] == value1;
    });
    var tempList1 = $.grep(data, function (obj, index) {
        return obj['infoMap'][str2] == value2;
    });
    $.each(tempList1, function (index, obj) {
        var temp = {};
        temp['id'] = obj['id'];
        temp['value'] = obj['infoMap'][str3];
        tempList2.push(temp);
    });
    return tempList2;
}

function getStandardList2(data, str1, value1, str2, value2, str3, value3, str4) {

    var tempList2 = [];
    var tempList = $.grep(data, function (obj, index) {
        return obj['infoMap'][str1] == value1;
    });
    tempList = $.grep(data, function (obj, index) {
        return obj['infoMap'][str2] == value2;
    });
    tempList = $.grep(data, function (obj, index) {
        return obj['infoMap'][str3] == value3;
    });
    $.each(tempList, function (index, obj) {
        var temp = {};
        temp['id'] = obj['id'];
        temp['value'] = obj['infoMap'][str4];
        tempList2.push(temp);
    });
    return tempList2;
}

//}

/**--------------------------附件公共方法------------------**/
/**
 * 文件上传
 */
function upToLoadFile() {
    $('#upload').Huploadify({
        header: {"Authorization": $.cookie('srpms_token')},
        auto: true,//xls,xlsx
        fileTypeExts: '*.txt;*.jpg;*.jpeg;*.JPG;*.JPEG;*.png;*.pdf;*.doc;*.docx;*.zip;*.ZIP;*.xsl;*.xlsx',
        multi: true,
        fileSizeLimit: 99999,
        showUploadedPercent: true,//是否实时显示上传的百分比，如20%
        showUploadedSize: true,
        method: 'post',
        uploader: '/api/file/upload',
        onUploadSuccess: function (file, data) {
            var fileInfo = {};
            var tempFileData = {};
            if(!isNull(data)){
                fileInfo['size'] = formatFileSize(file.size, false);
                fileInfo['fileKey'] = data;
                tempFileData[file.name] = fileInfo;
                filesData[file.name] = fileInfo;
                scanFiles(tempFileData);
            }
        },
        onUploadError:function (file, data){
            messageModal("上传失败！")
        }
    });
}
/**
 * 显示上传的资料
 * @param filesData
 */
function scanFiles(filesData) {
    for (var key in filesData) {
        var fileId = filesData[key]['fileKey'];
        var fileSize = filesData[key]['size'];
        $('#downFiles').prepend('<li id="li' + fileId + '" class="dd-item"> ' +
            '<div class="dd-handle">' +
            '<font size="1">' + key + '</font>&nbsp;&nbsp;&nbsp;&nbsp;' +
            '<span>（' + fileSize + '）</span>' +
            '<div class="pull-right action-buttons">' +
            '<a class="blue" style="cursor:pointer" onclick="downFile(\'' + fileId + '\')" id="down_' + fileId + '">' +
            '<i class="ace-icon fa  fa-cloud-download  bigger-140"></i>' +
            '</a>&nbsp;&nbsp;&nbsp;&nbsp;' +
            '<a class="fd red delFiles" style="cursor:pointer" onclick="delFile(\'' + fileId + '\')" id="del_' + fileId + '">' +
            '<i class="ace-icon fa fa-trash-o bigger-140"></i>' +
            '</a>' +
            '</div>' +
            '</div> ' +
            '</li>');
    }
}
/**
 *文件下载
 * @param fileId
 */
function downFile(fileId) {
    var url = '/api/file/' + fileId;
    //window.location.href=url;
    $.ajax({
        url: url,
        type: 'get',
        success: function () {
            window.location = url;
        }
    });
}
/**
 * 删除上传的文件
 * @param fileId
 */
function delFile(fileId) {
    /*    $("#li" + fileId).remove();*/
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
                if(!(fileInData(filesData,fileId))){
                    $("#li" + fileId).remove();
                }else{
                    $.ajax({
                        type: 'DELETE',
                        url: '/api/file/' + fileId,
                        success: function () {
                            for (var key in filesData) {
                                if (filesData[key]['fileKey'] == fileId) {
                                    delete filesData[key];
                                }
                            }
                            $("#li" + fileId).remove();
                        }
                    });
                }
            }
        }
    });
}
function fileInData(filesData,fileId){
    for (var key in filesData) {
        if (filesData[key]['fileKey'] == fileId) {
            return true;
        }else{
            return false;
        }
    }
}
/**
 * 文件大小
 * @param size byKB
 */
function formatFileSize(size, byKB) {
    if (size > 1024 * 1024 && !byKB) {
        size = (Math.round(size * 100 / (1024 * 1024)) / 100).toString() + 'MB';
    }
    else {
        size = (Math.round(size * 100 / 1024) / 100).toString() + 'KB';
    }
    return size;
}
/**
 * 显示文件信息
 * @param data
 */
function showFiles(data) {
    if (data == null || data == undefined) {
        $('#downFiles').empty();
    } else {
        $('#downFiles').empty();
        scanFiles(data);
    }
}
/**--------------------------选择框公共方法------------------**/
/**
 * 禁用selectize选择框
 * @param $type
 */
function disableSelectize($type) {
    $type[0].selectize.disable();
}
/**
 * 解除selectize的禁用
 * @param $type
 */
function enableSelectize($type) {
    $type[0].selectize.enable();
}
/**
 * selectize 新增选项
 * @param $type
 * @param data
 */
function addOptionSelectize($type, data) {
    $type[0].selectize.addOption(data);
}
/**
 * 清楚selectize选项
 * @param $type
 */
function clearOptionsSelectize($type) {
    $type[0].selectize.clearOptions();
}
/**
 * 选择框赋值
 * @param $type 选择框对象
 * @param ItemValue 值
 * @param flag 记号
 */
function DisplayForm($type, ItemValue, flag) {
    if (isNull(ItemValue)) {
        $type[0].selectize.setValue("");
    } else if (flag == '0') {
        $type[0].selectize.setValue(ItemValue);
    } else {
        $type[0].selectize.createItem(ItemValue);
    }
}
/**
 * 所属部门选择框初始化
 */
function getDept() {
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
        }
    });
}

/**--------------------------字符串属性公共方法------------------**/
/**
 * 判断是否是数值字符串
 * @param str
 * @returns {boolean true 数值型 | false 非数值型}
 */
function isInt(str) {
    if (str == '' || str == undefined || str == null) {// 空的直接返回false
        return false;
    } else { // 否则根据具体情况判断
        return (!isNaN(parseInt(str, 10)) || parseInt(str, 10).length == str.length);
    }
}
function isNull(str) {
    return (str == '' || str == undefined || str == null || str == {} || jQuery.isEmptyObject(str));
}

/**--------------------------提示信息公共方法------------------**/
/**
 * 显示成功提示
 * @param msg
 */
function afterSuccess(msg) {
    $('form input').val(null);
    $('#info_alert').empty();
    $('<div class="alert alert-block alert-success" id="success_info"></div>').appendTo('#info_alert');
    $('<button type="button" class="close" data-dismiss="alert"><i class="ace-icon fa fa-times">' +
        '</i></button><i class="ace-icon fa fa-check green" id="success_icon"></i>').appendTo('#success_info');
    $('#success_icon').append(msg);
    setTimeout(function () {
        $('#info_alert').empty();
    }, 2000);
}
/**
 * 错误信息
 * @param msg
 */
var checktime;
var count = 0;
var time;
function errorMsg(msg) {
    $(".getScore").attr("disabled", "disabled");
    $("#getScore").attr("disabled", "disabled");
    $('#msg_alert').empty();
    $('<div class="alert alert-block alert-danger" id="msg_info"></div>').appendTo('#msg_alert');
    $('<button type="button" class="close" data-dismiss="alert"><i class="ace-icon fa fa-times">' +
        '</i></button><i class="glyphicon glyphicon-info-sign" id="msg_icon"></i>').appendTo('#msg_info');
    var tip = " 提示：" + msg;
    $('#msg_icon').append(tip);
    checktime = setTimeout(function () {
        if (count > 10) {
            clearClick();
        }
    }, 12000);
    time = setInterval(function () {
        $("#msg_icon").text(tip + "(" + (10 - count) + ")");
        count++;
    }, 1000);
}
function clearClick() {
    $('#msg_alert').empty();
    count = 0;
    window.clearInterval(time);  //结束setInterval循环
    window.clearInterval(checktime);
    $(".getScore").removeAttr("disabled");
    $("#getScore").removeAttr("disabled");
}

jQuery(function ($) {

    $('.date-picker').datepicker({
        autoclose: true,
        todayHighlight: true
    }).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });
});


function showTooltip() {
    $('[data-rel=tooltip]').tooltip({container: 'body'});
}

/**
 *
 * @param statusVlaue 当前任务状态，不存在则传null
 * @param isMain 是否为负责人，1或者0，不存在此关系则传0；
 * @param userLevel 用户等级
 */
function processStatus(statusVlaue, isMain, userLevel) {
    //应该是实体
    if (statusVlaue == null || statusVlaue == ''||statusVlaue == undefined) {
        switch (userLevel) {
            case 1:
                return 0;//能看
            case 2:
                return 0;//能看
            case 3:
                return 1;//什么都能
        }
    }
    switch (userLevel) {
        case 1:
            //当前用户为负责人
            if (isMain == 1) {
                if (statusVlaue == "Blank" || statusVlaue == "Uncomplete") {
                    return "11110";//能修改，不能删除，不能撤回，无批复原因
                } else if (statusVlaue == "RefuseByCol") {
                    return "11111";//能修改，能删除，能撤回,有批复原因 todo 能撤回吗？
                } else if (statusVlaue == "Complete") {
                    return "10110";//不能修改，能删除，能撤回，无批复原因
                } else if (statusVlaue == "WaitForSubmit") {
                    return "10011";//不能修改，不能删除，能撤回，无批复原因
                } else {
                    return "10000";//只能查看
                }
            } else {
                if (statusVlaue == "Complete") {
                    return "01";//能确认
                } else {
                    return "00";//不能确认
                }
            }
        case 2:
            if (statusVlaue == "WaitForCol") {
                return "210";//能批复，无批复原因
            } else if (statusVlaue == "RefuseByDep") {
                return "211";//能批复，有批复原因
            } else {
                return "200";//只能看
            }
        case 3:
            if (statusVlaue == "WaitForDep") {
                return "311";//能批复，能删除
            } else {
                return "301";//能批复，能删除
            }
    }
}


function isMainActor(MainActor, userName) {
    if (MainActor == userName) {
        return 1;
    } else {
        return 0;
    }
}

/*拓展jquery 获取url的值*/
(function ($) {
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r !== null) return unescape(r[2]);
        return null;
    }
})(jQuery);
/**
 * 全部的初始化脚本
 * 教师1||学院2||学校3
 * */
function init(entity,all,replyByDep,level) {
    var process = entity['process'];
    switch (level){
        case 1:

            uneditableForm();
            hideUnitOperate();
            $('#totalScore').attr('disabled', 'disabled');
            $('.removeActor').hide();
            $('#upload').hide();
            $('.delFiles').hide();
            $('.onApprove').hide();

            var status = all['Status'];
            var isMain = isMainActor(all['Main-Actor'],userName);
            var statusCode = processStatus(status,isMain,level);
            switch (statusCode){
                case '01':
                    console.log('01');
                    $('.save').hide();
                    $('#reply').hide();
                    $('.onDel').hide();
                    $('.getScore').hide();
                    hideActorOperate();
                    break;
                case '00':
                    console.log('00');
                    $('.confirm').hide();
                    $('.save').hide();
                    $('#reply').hide();
                    $('.onDel').hide();
                    $('.getScore').hide();
                    hideActorOperate();
                    break;
                case '11110':
                    console.log('11110');
                    $('#reply').hide();
                    $('.onDel').hide();
                    break;
                case '10110':
                    console.log('10110');
                    hideActorOperate();
                    $('.getScore').hide();
                    $('.onEdit').hide();
                    $('.del').hide();
                    $('#reply').hide();
                    break;
                case '10011':
                    console.log('10000');
                    hideActorOperate();
                    $('#reply').hide();
                    $('.onEdit').hide();
                    $('.del').hide();
                    $('.getScore').hide();
                    break;
                case '10000':
                    console.log('10000');
                    hideActorOperate();
                    $('#reply').hide();
                    $('.onEdit').hide();
                    $('.onDel').hide();
                    $('.getScore').hide();
                    break;
                case '11111':
                    console.log('11111');
                    $('#reply').show();
                    $('#reply-box').hide();
                    $('#reply-display').show();
                    var reply = $('#reply-display').children('p');
                    var who = $('#reply-display').children('small');
                    reply.append(replyByCol);
                    who.append("学院批复");

                    $('.onDel').hide();
                    break;
            }
            break;
        case 2:

            uneditableForm();
            hideActorOperate();
            hideUnitOperate();
            hideFundOperate();
            $('#upload').hide();
            $('.getScore').hide();
            $('#totalScore').attr('disabled', 'disabled');
            $('.delFiles').hide();
            $('.onEdit').hide();
            $('.onDel').hide();

            $('#reply').show();
            $('#reply-display').show();
            var reply = $('#reply-display').children('p');
            var who = $('#reply-display').children('small');

            var status = all['Status'];
            var statusCode=parseInt(processStatus(status,0,level));

            switch (statusCode){
                case 211:
                    console.log('211');
                    reply.append(replyByDep);
                    who.append("管理部门批复");
                    break;
                case 210:
                    console.log('210');
                    $('#reply-display').hide();
                    break;
                case 200:
                    console.log('200');
                    reply.append(replyByCol);
                    who.append("学院批复");
                    $('#reply-box').hide();
                    $('.onApprove').hide();
                    break;
            }
            break;
        case 3:
            var status = all['Status'];
            var statusCode = parseInt(processStatus(status, 0, level));
            switch (statusCode) {
                case 1:
                   // console.log('1');
                    $('.onApprove').hide();
                    $('#reply').hide();
                    //$('.onDel').hide();
                    break;
                case 311:
                    console.log('311');
                    $('.getScore').hide();
                    $('#totalScore').attr('disabled', 'disabled');

                    $('#reply').show();
                    $('#reply-display').show();
                    var reply = $('#reply-display').children('p');
                    var who = $('#reply-display').children('small');
                    reply.append(replyByCol);
                    who.append("学院批复");
                    break;
                case 301:
                    console.log('301');
                    $('.getScore').hide();
                    $('#totalScore').attr('disabled', 'disabled');

                    $('#reply').show();
                    $('#reply-box').hide();
                    $('#reply-display').show();
                    var reply = $('#reply-display').children('p');
                    var who = $('#reply-display').children('small');
                    reply.append(replyByDep);
                    who.append("管理部门批复");

                    $('.onApprove').hide();
                    break;
            }
            if (process == '1') {
                $('.onEdit').hide();
                //$('.onDel').hide();

                uneditableForm();
                $('#upload').hide();
                $('.delFiles').hide();

                hideActorOperate();
                hideUnitOperate();
                hideFundOperate();
                //hideColumnScore(); // 还在流程中 分数不给看
                $('.addActor').hide();
                $('.addUnit').hide();
                $('.addFund').hide();
                $('.getScore').hide();
                $('#totalScore').attr('disabled', 'disabled');

            } else if(process == '9') {
                hideActorOperate();
                hideUnitOperate();
                hideFundOperate();
                $('.confirm').hide();
                $('.onApprove').hide();
                $('.orderBack').hide();

            } else if (process == null || process == '0') { // 刚刚新增或未启动
                $('#upload').show();
                $('.onEdit').show();
                $('.onApprove').hide();
                $('.orderBack').hide();
                $('#reply').hide();
               // hideColumnScore();
                if (process == '0') {
                    $('.del').show();
                } else {
                    $('.del').hide();
                }
            }
            break;
    }
    console.log('process:', process, 'status:', status, 'level:', level, 'isMain:', isMain, 'statusCode:', statusCode);
}
function uneditableForm() {
    $('form input').attr("disabled", "disabled");
    $('form select').attr("disabled", "disabled");
    var elementlist = document.querySelectorAll('.selectized');
    $.each(elementlist, function(index, value) {
        disableSelectize($(value).selectize());
    });//select
}

var wfTypeTans = {
    "book":"著作",
    "project": "项目",
    "patent": "专利",
    "paper": "论文",
    "newOther": "其他新产品",
    "newMedicine": "新药",
    "instrument": "新器械",
    "food": "新食品",
    "change": "差异",
    "award": "成果获奖",
    "achAppraisal": "成果鉴定"
};

function wfTypeTran(value,row ) {
    if(!isNull(value)){
        return wfTypeTans[value];
    }else{
        return '--' ;
    }
}
function projtypeTran(value, row){
    if(!isNull(row['standard'])){
        return row['standard']['infoMap']['projtype'];
    }else{
        return '--'
    }
}
function rankTran(value, row){
    if(!isNull(row['standard'])){
        return row['standard']['infoMap']['projrank'];
    }else{
        return '--'
    }
}
function projbelong(value, row){
    if(!isNull(row['standard'])){
        return row['standard']['infoMap']['projbelong'];
    }else{
        return '--'
    }
}

/**
 * 计算分数
 * @param type form的id
 */
function getScore(type) {
    var jsonData = getFormData(type);
    caculator(jsonData);
    //console.log(jsonData);
    //workflow.getScore(jsonData).success(function (data) {
    //    if (data["valid"] == false) { // 检验不合格
    //        errorMsg(data["msg"]);
    //        flag = true;
    //    } else if (data["hasSum"] == false) { // 后台分配分数
    //        $("#actorTable").bootstrapTable('load', data["actors"]);
    //        flag = false;
    //        errorMsg(data["msg"]);
    //    } else if (data["hasSum"] == true) {  // 给总分，负责人分配分数
    //        $("#totalScore").val(Math.floor(data["sum"]));
    //        errorMsg("总分为" + data["sum"] + "分，" + data["msg"]);
    //        flag = true;
    //    }
    //});
}
function caculator(jsonData){
    workflow.getScore(jsonData).success(function (data) {
        if (data["valid"] == false) { // 检验不合格
            errorMsg(data["msg"]);
            flag = true;
        } else if (data["hasSum"] == false) { // 后台分配分数
            $("#actorTable").bootstrapTable('load', data["actors"]);
            flag = false;
            errorMsg(data["msg"]);
        } else if (data["hasSum"] == true) {  // 给总分，负责人分配分数
            $("#totalScore").val(Math.floor(data["sum"]));
            errorMsg("总分为" + data["sum"] + "分，" + data["msg"]);
            flag = true;
        }
    });
}
//修改密码
function amend(){
    //todo 修改密码的dialog
    BootstrapDialog.show({
        type: BootstrapDialog.TYPE_PRIMARY,
        message: function (dialog) {
            var $message = $('<div></div>');
            var pageToLoad = dialog.getData('pageToLoad');
            $message.load(pageToLoad);
            return $message;
        },
        title: "密码修改",
        data: {
            'pageToLoad': '/dialog/amend.html'
        },
        closeByBackdrop: false,
        buttons: [{
            id: 'btn-oknm',
            icon: 'glyphicon glyphicon-check',
            label: '确定',
            cssClass: 'btn-info',
            autospin: false,
            action: function (dialogRef) {
                if (!isFull()) {
                    messageModal('请将信息填写完整。');
                    return;
                }
                editPassWord();
                dialogRef.close();
            }
        },{
            id: 'btn-cancel',
            icon: 'glyphicon glyphicon-remove',
            label: '关闭',
            cssClass: 'btn-danger',
            autospin: false,
            action: function (dialogRef) {
                dialogRef.close();
            }
        }]
//                    onshown: function () {
//                        fillRoles(projectRoles);
//                    }
    });
}

function editPassWord(){
    //todo 修改密码
    var accountInfo = $('#pwdInfo').serializeJSON();
    accountInfo['user'] = userName;
    $.ajax({
        url: '/api/user/password',
        type: 'put',
        data: JSON.stringify(accountInfo),
        contentType: 'application/json;charset=UTF-8',
        success: function (data) {
            if (data == 'true') {
                messageModal("修改成功。");
            } else if (data == 'false') {
                messageModal("修改失败。");
            }
        }
    });
}

function getMainActor() {
    var Main_ActorRank = 9999;
    var actorInfo = getActorsData();
    var Info = {};
    Info['Main-Actor'] = '';
    Info['Main-ActorName'] = '';

    if(actorInfo.length == 0 ) { //如果表空了 或者主负责人被删了
        return Info;
    }

    $.each(actorInfo, function(index, value) {
        if(value['staff.id'] !== '9999' && value['staff.id'] !== '9998'
            && parseInt(value['rank']) < parseInt(Main_ActorRank)
            && (value['unit'] == '湖北中医药大学' || value['unit'] == '中医院')) { //TODO

            Info['Main-Actor'] = value['staff.id'];
            Info['Main-ActorName'] = value['staff.name'];
            Main_ActorRank = value['rank'];
        }
    });
    return Info;
}

