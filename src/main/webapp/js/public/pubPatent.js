/**
 * Created by zheng on 2015/4/22.
 */
var $actorTable = $('#actorTable');
var actorTemp = [];
var unitTemp = [];
// 将对话框里的值加载进成员表
function subActorInfo(index,flag) {
    var id = $('#actor').val();
    var actor = $('#actor').text();
    var marks = $('#marks').val();
    var units = $('#units').val();
    var role = $('#role').val();
    var rank = $('#rank').val();
    var mark = (marks == "" ? '0' : (marks / units.length).toFixed(2));
    actorTemp = getActorsData();
    $.each(units, function (i, value) {
        actorTemp.push({"staff.id": id, "rank": rank, "staff.name": actor, "role": role, "score": mark, "unit": value});
    });
    if(flag) {  // 增加一行
        $('#actorTable').bootstrapTable("load", actorTemp);
    } else {    // 替换一行
        actorTemp.remove(index);
        $('#actorTable').bootstrapTable('load',actorTemp);
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
        if(row.score !== null && row.score !== undefined && row.score !=="") {
            total += +(row.score.toString().substring(0));
        }
    });
    return '共' + total.toFixed(0) + "分";
}

// 将对话框里的值加载进单位表
function subUnitInfo(index) {
    var unit = $('#unit').val();
    var rank = $('#rank').val();
    unitTemp.push({"rank": rank, "unit": unit});
    if(index == null) {  // 增加一行
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
// 成员翻译
function actorTran(value,row){
    if(value!=undefined && value!=null && value!="")
        return value.substring(0,value.length-1);
    else
        return;
}
//显示控制
function showForm(){
    $('#newPatentTable-box').addClass('collapsed');
    $('#patent-box').removeClass('collapsed');
}

function showTable(newUrl){
    $('#PatentTable').bootstrapTable('refresh', {url: newUrl});

    $('#newPatentTable-box').removeClass('collapsed');
    $('#patent-box').addClass('collapsed');
}
function showTableSchool(newUrl){
    DisplayForm($('#patType').selectize(), null, 0);
    $("#showSum").html("");
    showTable(newUrl);
}
//表单可编辑
function editableForm(){
    $('form input').removeAttr("disabled", "disabled");
    $('form select').removeAttr("disabled", "disabled");
    $('#addDiff').removeAttr("disabled", "disabled");
    $('#addActor').show();
    $('#getScore').show();
    $('.delFiles').show();
    $('#actorTable').bootstrapTable('showColumn', 'operate');
    $('#unitTable').bootstrapTable('showColumn', 'operate');
}
//表单不可编辑
function uneditableForm(){
    $('form input').attr("disabled", "disabled");
    $('form select').attr("disabled", "disabled");
    $('#addDiff').attr("disabled", "disabled");
    $('#addActor').hide();
    $('#addUnit').hide();
    $('#getScore').hide();
    $('.delFiles').hide();
    $('#actorTable').bootstrapTable('hideColumn', 'operate');
    $('#unitTable').bootstrapTable('hideColumn', 'operate');
}

/*
 * 获取人员信息
 *
 * */
function getActorsData(){
    var actorsData = $("#actorTable").bootstrapTable('getData');
    $.each(actorsData, function (index, value) {
        delete value[0];
    });
    return actorsData;
}
/*
 * 获取参加单位信息
 *
 * */
function getUnitsData(){
    return $("#unitTable").bootstrapTable('getData');
}
/**
 * 显示审批
 */
function showApproval() {
    $('.onEdit').hide();
    $('.onApproval').show();
}
/**
 * 显示编辑
 */
function showEdit() {
    $('.onEdit').show();
    $('.onApproval').hide();
}