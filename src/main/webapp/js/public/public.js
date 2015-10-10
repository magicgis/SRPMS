var paperRoles = [{"role": "第一作者"}, {"role": "通讯作者"}, {"role": "第二作者"}
    , {"role": "第三作者"}, {"role": "第四作者"}, {"role": "其他"}];
var patentRoles = [{"role": "第一专利权人"}, {"role": "知识产权所有人"}];
var projectRoles = [{"role": "负责人"}, {"role": "参与人"}];

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
 * @param WF_User
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
 * @param WF_User
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

/**--------------------------处理数据公共方法------------------**/
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
    ;
    $.each(respon, function (index, value) {
        /*取出variableMap*/
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
 * 公共方法 对任务的完成状态进行翻译
 * @param value
 * @return String
 * */
function statusTran(value, row) {
    //定义一个条件数组函数  所有状态项
    var termArray = {
        'Blank': '待填写',
        'Uncomplete': '已保存',
        'Complete': '等待他人确认',
        'ApprovedByCol': '待学院统一提交',
        'WaitForSubmit': '待统一提交',
        'WaitForCol': '学院审核中',
        'WaitForCollegeSubmit': '待学院统一提交',
        'WaitForDep': '管理部门审核中',
        'RefuseByDep': '管理部门驳回，待学院审核',
        'RefuseByCol': '学院驳回，待修改'
    };
    for (var key in termArray) {
        if (value == key) {
            return termArray[key];
        }
    }
}

// 成员翻译
function actorTran(value, row) {
    if (value != undefined && value != null && value != "")
        return value.substring(0, value.length - 1);
    else
        return;
}

function hideActorOperate() {
    $('#actorTable').bootstrapTable('hideColumn', 'operate');
}
function hideUnitOperate() {
    $('#unitTable').bootstrapTable('hideColumn', 'operate');
}

// 简单消息弹框
function messageModal(message) {
    BootstrapDialog.show({
        title: '通知',
        type: BootstrapDialog.TYPE_INFO,
        message: message
    });
}
// 获取编辑页面的数据
function getFormData(type) {
    var jsonData = $("#" + type).serializeJSON();
    $.each(jsonData, function (key, value) {
        if (isNull(value)) {
            delete jsonData[key];
        }
    });
    if (filesData != null) {
        jsonData['filesData'] = filesData;
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

/**--------------------------附件公共方法------------------**/
/**
 * 文件上传
 */
function upToLoadFile() {
    $('#upload').Huploadify({
        header: {"Authorization": $.cookie('srpms_token')},
        auto: true,
        fileTypeExts: '*.txt;*.jpg;*.jpeg;*.JPG;*.JPEG;*.png;*.pdf;*.doc;*.docx;',
        multi: true,
        fileSizeLimit: 99999,
        showUploadedPercent: true,//是否实时显示上传的百分比，如20%
        showUploadedSize: true,
        method: 'post',
        uploader: '/api/file/upload',
        onUploadSuccess: function (file, data) {
            var fileInfo = {};
            fileInfo['size'] = formatFileSize(file.size, false);
            fileInfo['fileKey'] = data;
            filesData[file.name] = fileInfo;
            console.log(filesData);
            scanFiles(filesData);
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

    /*    var xhr = new XMLHttpRequest();
     xhr.open( "GET", url);
     xhr.setRequestHeader('Authorization', $.cookie('srpms_token'));
     xhr.addEventListener( "load", function(){
     console.log(this.responseText);
     data = this.responseText;
     data = "data:text/csv;base64,"+btoa(data);
     document.location = data;
     }, false);
     xhr.send(null);*/
}
/**
 * 删除上传的文件
 * @param fileId
 */
function delFile(fileId) {
    /*    $("#li" + fileId).remove();*/
    console.log(filesData)
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
    });
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
        console.log('in ' + ItemValue);
        $type[0].selectize.setValue(ItemValue);
        console.log('out');
    } else {
        $type[0].selectize.createItem(ItemValue);
    }
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
    return (str == '' || str == undefined || str == null || str == '{}' || str == {} || jQuery.isEmptyObject(str));
}

/**--------------------------提示信息公共方法------------------**/
/**
 * 显示成功提示
 * @param msg
 */
function afterSuccess(msg) {
    $('form input').val(null);
    $('#paper-box').addClass('collapsed');
    $('#info_alert').empty();
    $('<div class="alert alert-block alert-success" id="success_info"></div>').appendTo('#info_alert');
    $('<button type="button" class="close" data-dismiss="alert"><i class="ace-icon fa fa-times">' +
        '</i></button><i class="ace-icon fa fa-check green" id="success_icon"></i>').appendTo('#success_info');
    $('#success_icon').append(msg);
    setTimeout(function () {
        $('#info_alert').empty();
    }, 1500);
}
/**
 * 错误信息
 * @param msg
 */
var checktime;
var count = 0;
var time;
function errorMsg(msg) {
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
    if (statusVlaue == null) {
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
                    return "11110";//能修改，能删除，能撤回，无批复原因
                } else if (statusVlaue == "RefuseByCol") {
                    return "11111";//能修改，能删除，能撤回,有批复原因
                } else if (statusVlaue == "Complete") {
                    return "10110";//不能修改，能删除，能撤回，无批复原因
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
                return "201";//不能批复，有批复原因
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


/**--------------------------成员表公共方法------------------**/
//// 将对话框里的值加载进成员表
//function subActorInfo(index,flag) {
//    var id = $('#actor').val();
//    var actor = $('#actor').text();
//    var marks = $('#marks').val();
//    var units = $('#units').val();
//    var role = $('#role').val();
//    var rank = $('#rank').val();
//    var mark = (marks == "" ? '0.00' : (marks / units.length).toFixed(2));
//    $.each(units, function (i, value) {
//        actorTemp.push({"staff.id": id, "rank": rank, "staff.name": actor, "role": role, "score": mark, "unit": value});
//    });
//    if(flag) {  // 增加一行
//        $('#actorTable').bootstrapTable("load", actorTemp);
//    } else {    // 替换一行
//        actorTemp.remove(index);
//        $('#actorTable').bootstrapTable('load',actorTemp);
//    }
//}
//
//window.operateEvents = {
//    'click .removeActor': function (e, value, row, index) {
//        $('#actorTable').bootstrapTable('remove', {
//            field: 'staff.id',
//            values: [row["staff.id"]]
//        });
//    },
//    'click .editActor': function (e, value, row, index) {
//        editActor(row, index);
//    }
//};
//// 操作
//function operateFormatter(value, row, index) {
//    return [
//        '<a class="editActor" href="javascript:void(0)" title="edit" >',
//        '<i class="ace-icon fa fa-pencil bigger-110"></i>',
//        '</a>&nbsp;&nbsp;&nbsp;',
//        '<a class="removeActor" href="javascript:void(0)" title="Remove" >',
//        '<i class="glyphicon glyphicon-remove"></i>',
//        '</a>'
//    ].join('');
//}
//// 总人数
//function totalNameFormatter(data) {
//    return "共" + data.length + "人";
//}
//// 总分
//function totalMarksFormatter(data) {
//    var total = 0;
//    $.each(data, function (i, row) {
//        if(row.score !== null && row.score !== undefined && row.score !=="") {
//            total += +(row.score.toString().substring(0));
//        }
//    });
//    return '共' + total.toFixed(0) + "分";
//}


/**--------------------------单位表公共方法------------------**/
////将对话框里的值加载进单位表
//function subUnitInfo() {
//    var unit = $('#unit').val();
//    var rank = $('#rank').val();
//    unitTemp.push({"rank": rank, "unit": unit});
//    console.log(unitTemp);
//    $('#unitTable').bootstrapTable('load',unitTemp);
//}
//
//window.operateEventsUnit = {
//    'click .removeUnit': function (e, value, row, index) {
//        $('#unitTable').bootstrapTable('remove', {
//            field: 'unit',
//            values: [row["unit"]]
//        });
//    },
//    'click .editUnit': function (e, value, row, index) {
//        editUnit(row, index);
//    }
//};
//// 操作
//function operateFormatterUnit(value, row, index) {
//    return [
//        '<a class="editUnit" href="javascript:void(0)" title="edit" >',
//        '<i class="ace-icon fa fa-pencil bigger-110"></i>',
//        '</a>&nbsp;&nbsp;&nbsp;',
//        '<a class="removeUnit" href="javascript:void(0)" title="Remove" >',
//        '<i class="glyphicon glyphicon-remove"></i>',
//        '</a>'
//    ].join('');
//}
//// 总个数
//function totalUnitFormatter(data) {
//    return "共" + data.length + "个";
//}
