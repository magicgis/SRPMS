/**
 * Created by zheng on 2015/4/24.
 */
var actorTemp = [];
var unitTemp = [];
var fundTemp = [];
var Main_Actor;
var Main_ActorName;
var replyByCol, replyByDep;
var $actorTable = $('#actorTable');
// 将对话框里的值加载进成员表
function subActorInfo(index, flag) {
    var id = $('#actor').val();
    var actor = $('#actor').text();
    var marks = $('#marks').val();
    var units = $('#units').val();
    var role = $('#role').val();
    var rank = $('#rank').val();
    //var mark = (marks == "" ? '0' : (marks / units.length).toFixed(2));
    actorTemp = getActorsData();
    $.each(units, function (i, value) {
        actorTemp.push({"staff.id": id, "rank": rank, "staff.name": actor, "role": role, "score": marks, "unit": value});
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
    //测试专用
    //var tempScore=$('#tempScore').val();
    var Score=50-total.toFixed(0);
    if(Score<=0){
        messageModal("分数分配错误！");
        return;
    }
    $('.testScore').empty();
    $('#tempScore').val(Score);
    $('.testScore').append('剩余'+Score+'分');
    return '共' + total.toFixed(0) + "分";
}

// 将对话框里的值加载进单位表
function subUnitInfo(index) {
    var unit = $('#unit').val();
    var rank = $('#rank').val();
    //console.log(aRank);
    unitTemp.push({"rank": rank, "unit": unit});
    if (index == null) {  // 增加一行
        $('#unitTable').bootstrapTable("load", unitTemp);
    } else {    // 替换一行
        unitTemp.remove(index);
        $('#unitTable').bootstrapTable('load', unitTemp);
    }
    $('#unitTable').bootstrapTable('load', unitTemp);
}

window.operateEventsUnit = {
    'click .removeUnit': function (e, value, row, index) {
        $('#unitTable').bootstrapTable('remove', {
            field: 'unit',
            values: [row["unit"]]
        });
    },
    'click .editUnit': function (e, value, row, index) {
        editUnit(row, index);
    }
};
// 操作
function operateFormatterUnit(value, row, index) {
    return [
        '<a class="editUnit" href="javascript:void(0)" title="edit" >',
        '<i class="ace-icon fa fa-pencil bigger-110"></i>',
        '</a>&nbsp;&nbsp;&nbsp;',
        '<a class="removeUnit" href="javascript:void(0)" title="Remove" >',
        '<i class="glyphicon glyphicon-remove"></i>',
        '</a>'
    ].join('');
}
// 总个数
function totalUnitFormatter(data) {
    return "共" + data.length + "个";
}
//表单不可编辑
function uneditableForm(){
    $('form input').attr("disabled", "disabled");
    $('form select').attr("disabled", "disabled");
    $('.delFiles').hide();
}
function fullUpInfo(all,entity){
    if (!isNull(all)) {
        getActors();
        filesData = all['filesData'];
        unitTemp = all['units'];
        Main_Actor = all['Main-Actor'];
        Main_ActorName = all['Main-ActorName'];
        replyByCol = all['replyByCol'];
        replyByDep = all['replyByDep'];
        $("#actorTable").bootstrapTable('load', actorTemp);
        $('#unitTable').bootstrapTable("load", unitTemp);
        showFiles(filesData);
    }
}
function allSelection(){
    $('#achType').selectize({
        valueField: 'id',
        labelField: 'value',
        options:[
            {"id": "paper", "value": "论文"},
            {"id": "book", "value": "著作"},
            {"id": "project", "value": "项目"},
            {"id": "patent","value":"专利"},
            {"id": "medicine","value":"新药"},
            {"id": "food","value":"功能性食品"},
            {"id": "instrument","value":"医疗器械"},
            {"id": "other","value":"其他"}],
        maxItems: 1
    });
}
/**************************获取表格数据************************/
function getActorsData() {
    var actorTemp = $("#actorTable").bootstrapTable('getData');
    $.each(actorTemp, function (index, value) {
        delete value[0];
    });
    return actorTemp;
}
//获取参加单位信息
function getUnitsData() {
    var unitTemp = $("#unitTable").bootstrapTable('getData');
    return unitTemp;
}