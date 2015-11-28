/**
 * Created by zheng on 2015/4/24.
 */
var actorTemp = [];
var Main_Actor;
var Main_ActorName;
var replyByCol, replyByDep;
var filesData = {};
var $actorTable = $('#actorTable');
var awarDtype = {
    "公开出版著作": [{"id": "10201", "value": "国家图书奖"}, {"id": "10202", "value": "全国优秀科技图书奖（科技进步奖科技著作）一等奖"}, {
        "id": "10203",
        "value": "全国优秀科技图书奖（科技进步奖科技著作）二等奖"
    }, {"id": "10204", "value": "全国优秀科技图书奖（科技进步奖科技著作）三等奖"}],
    "教育部规划教材": [{"id": "10211", "value": "国家优秀教材一等奖"}, {"id": "10212", "value": "国家优秀教材二等奖"}, {
        "id": "10213",
        "value": "国家优秀教材三等奖"
    }],

    "行业规划教材": [{"id": "10221", "value": "新世纪全国高等中医药优秀教材奖一等奖"}, {
        "id": "10222",
        "value": "新世纪全国高等中医药优秀教材奖二等奖"
    }, {"id": "10223", "value": "新世纪全国高等中医药优秀教材奖三等奖"}],

    "协编教材": [{"id": "10231", "value": ""}],
    "其他教材": [{"id": "10241", "value": ""}]
};
// 将对话框里的值加载进成员表
function subActorInfo(index, flag) {
    var id = $('#actor').val();
    var actor = $('#actor').text();
    var marks = $('#score').val();
    var units = $('#units').val();
    var role = $('#role').val();
    var rank = $('#rank').val();
    var textNumber = $('#textNumber').val();
    var mark = (marks == "" ? '0' : (marks / units.length).toFixed(2));
    actorTemp = getActorsData();
    $.each(units, function (i, value) {
        actorTemp.push({
            "staff.id": id, "rank": rank, "staff.name": actor,
            "role": role, "score": marks, "unit": value, "textNumber": textNumber
        });
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
function awardTypeSelect(pubType,awarded){
    var awardtypes;
    if(!isNull(pubType)){
        awardtypes= awarDtype[pubType];
        $awardtype[0].selectize.clearOptions();
        $awardtype[0].selectize.addOption(awardtypes);
    }
    if(!isNull(awarded)){
        console.log("***************");
        console.log(awarded);
        DisplayForm($awardtype, awarded, 0);
    }
}
function getPubType() {
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
            console.log($('#pubType').val());
            awardTypeSelect($('#pubType').val(),awardType);
        }
    });
}
function getUnitsData() {
}
/**************************************************************/

function fullUpInfoBook(all, entity) {
    if(!isNull(entity)||!isNull(all)){
        getActors();
        filesData = all['filesData'];
        Main_Actor = all['Main-Actor'];
        Main_ActorName = all['Main-ActorName'];
        replyByCol = all['replyByCol'];
        replyByDep = all['replyByDep'];
        showFiles(filesData);
        $("#actorTable").bootstrapTable('load', actorTemp);
    }
}
