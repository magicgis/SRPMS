/**
 * Created by zheng on 2015/4/24.
 */
var actorTemp = [];
var Main_Actor;
var Main_ActorName;
var replyByCol, replyByDep;
var $actorTable = $('#actorTable');
var awarDtype= {
     "1020":
            [{"10201":"国家图书奖"},{"10202":"全国优秀科技图书奖（科技进步奖科技著作）一等奖"},{"10203":"全国优秀科技图书奖（科技进步奖科技著作）二等奖"},{"10204":"全国优秀科技图书奖（科技进步奖科技著作）三等奖"}],
    "1021":
            [{"10211":"国家优秀教材一等奖"},{"10212":"国家优秀教材二等奖"},{"10213":"国家优秀教材三等奖"}],

    "1022":
            [{"10221":"“新世纪全国高等中医药优秀教材”奖一等奖"},{"10222":"“新世纪全国高等中医药优秀教材”奖二等奖"},{"10223":"“新世纪全国高等中医药优秀教材”奖三等奖"}],

    "1023":[{"1023":""}]};
// 将对话框里的值加载进成员表
function subActorInfo(index, flag) {
    var id = $('#actor').val();
    var actor = $('#actor').text();
    var marks = $('#marks').val();
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
function getPubType(){//awardtype
    var $awardtype=$('#awarDtype').selectize({ // 初始化 鉴定等级
        valueField: 'id',
        labelField: 'value',
        maxItems: 1,
        create:true
    });
    $('#pubType').selectize({
        valueField: 'id',
        labelField: 'value',
        create: true,
        options: [
            {"id":"1020","value": "公开出版著作"},
            {"id":"1021","value": "教育部规划教材"},
            {"id":"1022","value": "协编教材"},
            {"id":"1023","value":"其他教材"}],
        maxItems: 1
    }).change(function(){
        var awardtypes=awarDtype[$('#pubType').val()];
        $awardtype[0].selectize.clearOptions();
        $awardtype[0].selectize.addOption(awardtypes);
    });
}
// 表单不可编辑
function unEditTableBook() {
    $('form input').attr("disabled", "disabled");
    $('form select').attr("disabled", "disabled");
    var elementlist = document.querySelectorAll('.selectized');
    $.each(elementlist, function(index, value) {
        disableSelectize($(value).selectize());
    });//select
    $('#addActor').hide();
    $('#getScore').hide();
    $('.delFiles').hide();
    $('#actorTable').bootstrapTable('hideColumn', 'operate');
    $('#upload').hide();
    $('.delAwardeds').hide();
}
// 表单可编辑
function editTableBook() {
    //enableSelectize($type)
    $('form input').removeAttr("disabled", "disabled");
    var elementlist = document.querySelectorAll('.selectized');
    $.each(elementlist, function(index, value) {
        enableSelectize($(value).selectize());
    });
    $('form select').attr("disabled", "disabled");
    $('form select').removeAttr("disabled", "disabled");
    $('#addActor').show();
    $('#getScore').show();
    $('.delFiles').show();
    $('#upload').show();
    $('#actorTable').bootstrapTable('showColumn', 'operate');
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
var awardedData;
if (awardedData == null) {
    awardedData = {};
}
function awardedInfo(){
    $('#BookAward').on('check.bs.table',function(e, row){
        awardedData[row["id"]]=row["name"];
    });
    $('#BookAward').on('uncheck-all.bs.table',function(e, row){
        for (var key in awardedData) {
            if (awardedData[key] == row["id"]) {
                delete awardedData[key];
            }
        }
    });
    scanAwardInfo(awardedData);
}
function scanAwardInfo(awardedData){
    for (var key in awardedData) {
        var bookName = awardedData[key]['name'];
        $('#downFiles').prepend('<li id="li' + fileId + '" class="dd-item"> ' +
        '<div class="dd-handle">' +
        '<font size="1">《' + bookName + '》</font>&nbsp;&nbsp;&nbsp;&nbsp;' +
        '<div class="pull-right action-buttons">' +
        '<a class="fd red delAwardeds" style="cursor:pointer" onclick="delAwarded(\'' + key + '\')" >' +
        '<i class="ace-icon fa fa-trash-o bigger-140"></i>' +
        '</a>' +
        '</div>' +
        '</div> ' +
        '</li>');
    }
}
function delAwarded(fileId) {
    /*    $("#li" + fileId).remove();*/
    for (var key in awardedData) {
        if (awardedData[key] == fileId) {
            delete awardedData[key];
        }
    }
    $("#li" + fileId).remove();
}