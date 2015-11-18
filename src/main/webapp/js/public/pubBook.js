/**
 * Created by zheng on 2015/4/24.
 */
var actorTemp = [];
var Main_Actor;
var Main_ActorName;
var replyByCol, replyByDep;
var $actorTable = $('#actorTable');
var awarDtype= {
     "公开出版著作":
            [{"id":"10201","value":"国家图书奖"},{"id":"10202","value":"全国优秀科技图书奖（科技进步奖科技著作）一等奖"},{"id":"10203","value":"全国优秀科技图书奖（科技进步奖科技著作）二等奖"},{"id":"10204","value":"全国优秀科技图书奖（科技进步奖科技著作）三等奖"}],
    "教育部规划教材":
            [{"id":"10211","value":"国家优秀教材一等奖"},{"id":"10212","value":"国家优秀教材二等奖"},{"id":"10213","value":"国家优秀教材三等奖"}],

    "行业规划教材":
            [{"id":"10221","value":"新世纪全国高等中医药优秀教材奖一等奖"},{"id":"10222","value":"新世纪全国高等中医药优秀教材奖二等奖"},{"id":"10223","value":"新世纪全国高等中医药优秀教材奖三等奖"}],

    "协编教材":[{"id":"10231","value":""}],
    "其他教材":[{"id":"10241","value":""}]};
// 将对话框里的值加载进成员表
function subActorInfo(index, flag) {
    var id = $('#actor').val();
    var actor = $('#actor').text();
    var marks = $('#score').val();
    var units = $('#units').val();
    var role = $('#role').val();
    var rank = $('#rank').val();
    var textNumber = $('#textNumber').val();
    //var mark = (marks == "" ? '0' : (marks / units.length).toFixed(2));
    //测试专用
    //scorelatestInfoocation(marks,index);
    actorTemp = getActorsData();
    $.each(units, function (i, value) {
        actorTemp.push({"staff.id": id, "rank": rank, "staff.name": actor,
            "role": role, "score": marks, "unit": value,"textNumber":textNumber});
    });
    if (rank == '1' || rank == 1) {
        Main_Actor = id;
        Main_ActorName = actor;
    }
    if (flag) {  // 增加一行
        $('#actorTable').bootstrapTable("load", actorTemp);
    } else {    // 替换一行
        actorTemp.remove(index);
        $('#actorTable').bootstrapTable('load', actorTemp);
    }
}
// 移除和编辑成员
window.operateEvents = {
    'click .removeActor': function (e, value, row, index) {
        $('#actorTable').bootstrapTable('remove', {
            field: 'staff.id',
            values: [row["staff.id"]]
        });
    },
    'click .editActor': function (e, value, row, index) {
        editActor(row, index);
    }
};
// 操作
function operateFormatter(value, row, index) {
    return [
        '<a class="editActor" href="javascript:void(0)" title="edit" >',
        '<i class="ace-icon fa fa-pencil bigger-110"></i>',
        '</a>&nbsp;&nbsp;&nbsp;',
        '<a class="removeActor" href="javascript:void(0)" title="Remove" >',
        '<i class="glyphicon glyphicon-remove"></i>',
        '</a>'
    ].join('');
}
// 总人数
function totalNameFormatter(data) {
    return "共" + data.length + "人";
}
// 总分
function totalMarksFormatter(data) {
    var total = 0;
    $.each(data, function (i, row) {
        if (row.score !== null && row.score !== undefined && row.score !== "") {
            total += +(row.score.toString().substring(0));
        }
    });
    return '共' + total.toFixed(0) + "分";
}
/**************************获取表格数据************************/
function getActorsData() {
    var actorTemp = $("#actorTable").bootstrapTable('getData');
    $.each(actorTemp, function (index, value) {
        delete value[0];
    });
    return actorTemp;
}
function getPubType() {//awardtype

    var $awardtype = $('#awarDtype').selectize({ // 初始化 鉴定等级
        valueField: 'id',
        labelField: 'value',
        maxItems: 1,
        create: true,
        onFocus: function() {
            if($('#pubType').val()==""){
                messageModal("请先选择出版类型");
            }
        }
    });

    var $isAward=$('#isAward').selectize({ // 初始化 鉴定等级
        valueField: 'id',
        labelField: 'value',
        options: [
            {"id": "false", "value": "否"},
            {"id": "true", "value": "是"}],
        maxItems: 1,
        create: false
    });
    $('#pubType').selectize({
        valueField: 'value',
        labelField: 'value',
        create: true,
        options: [
            {"id": "1020", "value": "公开出版著作"},
            {"id": "1021", "value": "教育部规划教材"},
            {"id": "1022", "value": "行业规划教材"},
            {"id": "1023", "value": "协编教材"},
            {"id": "1024", "value": "其他教材"}],
        maxItems: 1,
        onChange: function () {
            var awardtypes = awarDtype[$('#pubType').val()];
            $awardtype[0].selectize.clearOptions();
            $awardtype[0].selectize.addOption(awardtypes);
        }
    });

}

function IsAward() {
    if($('#isAward').val() == 'true') {
        enableSelectize($('#awarDtype').selectize());
    } else {
        $('#awarDtype').selectize()[0].selectize.setValue("");
        disableSelectize($('#awarDtype').selectize());
    }
}

// 表单不可编辑
function unEditTableBook() {
    $('form input').attr("disabled", "disabled");
    $('form select').attr("disabled", "disabled");
    var elementlist = document.querySelectorAll('.selectized');
    $.each(elementlist, function(index, value) {
        disableSelectize($(value).selectize());
    });
    $('.addActor').hide();
    $('.getScore').hide();
    $('.delFiles').hide();
    $('#actorTable').bootstrapTable('hideColumn', 'operate');
    $('#upload').hide();
    //$('.delAwardeds').hide();
}

function getUnitsData(){}
/**
 * 获得最新的批复
 * @param data
 */
function getApprovalBy(data) {
    var max = 0;
    var keyStr = "";
    for (var key in pData) {
        var partn = new RegExp('WF_\\d+_Submission');
        if (partn.test(key)) {
            var keyValue = parseInt(key.substring(3, key.length - 11));
            if (max < keyValue) {
                max = keyValue;
            }
        }
        keyStr = "WF_" + max + "_Submission";
    }
    return keyStr;
}
//增加已获奖著作
//var awardedData;
//if (awardedData == null) {
//    awardedData = {};
//}
//function awardedInfo(){
//    $('#BookAward').on('check.bs.table',function(e, row){
//        awardedData[row["id"]]=row["name"];
//    });
//    $('#BookAward').on('uncheck-all.bs.table',function(e, row){
//        for (var key in awardedData) {
//            if (awardedData[key] == row["id"]) {
//                delete awardedData[key];
//            }
//        }
//    });
//    scanAwardInfo(awardedData);
//}
//function scanAwardInfo(awardedData){
//    for (var key in awardedData) {
//        var bookName = awardedData[key]['name'];
//        $('#downFiles').prepend('<li id="li' + fileId + '" class="dd-item"> ' +
//        '<div class="dd-handle">' +
//        '<font size="1">《' + bookName + '》</font>&nbsp;&nbsp;&nbsp;&nbsp;' +
//        '<div class="pull-right action-buttons">' +
//        '<a class="fd red delAwardeds" style="cursor:pointer" onclick="delAwarded(\'' + key + '\')" >' +
//        '<i class="ace-icon fa fa-trash-o bigger-140"></i>' +
//        '</a>' +
//        '</div>' +
//        '</div> ' +
//        '</li>');
//    }
//}
//function delAwarded(fileId) {
//    /*    $("#li" + fileId).remove();*/
//    for (var key in awardedData) {
//        if (awardedData[key] == fileId) {
//            delete awardedData[key];
//        }
//    }
//    $("#li" + fileId).remove();
//}