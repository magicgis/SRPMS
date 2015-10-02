/**
 * Created by zheng on 2015/4/18.
 */
//是否是第一单位
function firstOrOther(){
    if($('#isFirInInstru').val()=='false'){
        $('#instrUnitInfo').show();
        //$('#medunitTable').bootstrapTable("load",medunitTemps);
    }else if($('#isFirInInstru').val()=='true'){
        $('#instrUnitInfo').hide();
        $('#apparUnitTable').bootstrapTable("load",unitTemp);
    }else{
        $('#instrUnitInfo').hide();
        $('#unitTable').bootstrapTable("load",unitTemp);
    }
}
//添加 删除行
function operateIAFormatter(value, row, index) {
    return [
        '<a class="removeInstrumentsActor" href="javascript:void(0)" title="Remove" >',
        '<i class="glyphicon glyphicon-remove"></i>',
        '</a>'
    ].join('');
}
window.operateIUEvents = {
    'click .removeApparUnit': function (e, value, row, index) {
        $('#unitTable').bootstrapTable('remove', {
            field: 'unit_id',
            values: [row.unit_id]
        });
    }
};
window.operateIAEvents = {
    'click .removeInstrumentsActor': function (e, value, row, index) {
        $('#actorTable').bootstrapTable('remove', {
            field: 'staId',
            values: [row.staId]
        });
    }
};
function operateIUFormatter(value, row, index) {
    return [
        '<a class="removeApparUnit" href="javascript:void(0)" title="Remove" >',
        '<i class="glyphicon glyphicon-remove"></i>',
        '</a>'
    ].join('');
}
//成员表
function totalNameFormatter(data) {
    return "共"+data.length+"人";
}

function totalMarksFormatter(data) {
    var total = 0;
    $.each(data, function (i, row) {
        total += +(row.marks.substring(0));
    });
    return '总分:' + total+"分";
}
function totalUnitFormatter(data){
    return "共:" + data.length + "个单位";
}
function editableForm(){
    $('form input').removeAttr("disabled", "disabled");
    $('form select').removeAttr("disabled", "disabled");
    $('#actorTable').bootstrapTable('showColumn','operate');
    $('#unitTable').bootstrapTable('showColumn','operate');
    $('#addInstrumentsActor').show();
    $('#addInstrumentsUnit').show();
}
function uneditableForm(){
    $('form input').attr("disabled", "disabled");
    $('form select').attr("disabled", "disabled");
    $('#addInstrumentsActor').hide();
    $('#addInstrumentsUnit').hide();
    $('#addFund').attr("disabled", "disabled");
    $('#actorTable').bootstrapTable('hideColumn','operate');
    $('#unitTable').bootstrapTable('hideColumn','operate');
}
//监听 修改成员表
$('#actorTable').on('editable-save.bs.table', function (e, row, $element){
    $('#actorTable').bootstrapTable("load",actorTemp);
});

//监听 添加成员
$("#addInstrumentsActor").click(function(){
    actorTemp.push({ "actor":"","staId":"","role":"","marks":"0"}) ;
    $('#actorTable').bootstrapTable("load",actorTemp);
});
//监听 修改单位表
$('#unitTable').on('editable-save.bs.table', function (e, row, $element){
    $('#unitTable').bootstrapTable("load",unitTemp);
});

//监听 添单位员
$("#addInstrumentsUnit").click(function(){
    unitTemp.push({"unit_id":"",
        "unit_rank":"",
        "unit_name":""}) ;
    $('#unitTable').bootstrapTable("load",unitTemp);
});
function showForm(){
   // $('#instrumentsTable-box').addClass('collapsed');
    $('#instruments-box').removeClass('collapsed');
}
//显示总览
function showTable(){
    $('#NewInstrumentsTable').bootstrapTable('refresh',{silent: true});
    $('#instrumentsTable-box').removeClass('collapsed');
    $('#instruments-box').addClass('collapsed');
}
//获取人员信息
function getActorsData(){
    return $("#actorTable").bootstrapTable('getData');
}
//获取参加单位信息
function getUnitsData(){
    return $("#unitTable").bootstrapTable('getData');
}
//成功提示
function afterSuccess(msg){
    $('form input').val(null);
    $('#paper-box').addClass('collapsed');
    $('#info_alert').empty();
    $('<div class="alert alert-block alert-success" id="success_info"></div>').appendTo('#info_alert');
    $('<button type="button" class="close" data-dismiss="alert"><i class="ace-icon fa fa-times">' +
    '</i></button><i class="ace-icon fa fa-check green" id="success_icon"></i>').appendTo('#success_info');
    $('#success_icon').append(msg);
}