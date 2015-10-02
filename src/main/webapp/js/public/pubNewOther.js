/**
 * Created by zheng on 2015/4/18.
 */
//状态翻译
function statusTran(value,row){
    if(value == 'Blank'){
        return '待填写';
    }else if(value == 'Uncomplete'){
        return '已保存';
    }else if(value == 'Complete'){
        return '等待他人确认';
    }else if(value == 'WaitForSubmit'){
        return '待统一提交';
    }else if(value == 'WaitForCol'){
        return '学院审核中';
    }else if(value == 'WaitForDep'){
        return '管理部门审核中';
    }else if(value == 'RefuseByDep'){
        return '管理部门驳回，待修改'
    }else if(value == 'RefuseByCol'){
        return '学院驳回，待修改'
    }
}
//是否是第一单位
function firstOrOther(){
    if($('#isFirInOther').val()=='false'){
        $('#otherUnitInfo').show();
        $('#unitTable').bootstrapTable("load",unitTemp);
    }else if($('#isFirInOther').val()=='true'){
        $('#otherUnitInfo').hide();
        $('#unitTable').bootstrapTable("load",unitTemp);
    }else{
        $('#otherUnitInfo').hide();
        $('#unitTable').bootstrapTable("load",unitTemp);
    }
}
function showForm(){
    $('#newOtherTable-box').addClass('collapsed');
    $('#newOther-box').removeClass('collapsed');
}
function showTable(){
    $('#NewOtherTable').bootstrapTable('refresh',{silent: true});
    $('#newOtherTable-box').removeClass('collapsed');
    $('#newOther-box').addClass('collapsed');
}
//添加 删除 行动作
window.operateOAEvents = {
    'click .removeOtherActor': function (e, value, row, index) {
        $('#actorTable').bootstrapTable('remove', {
            field: 'staId',
            values: [row.staId]
        });
    }
};
function operateOAFormatter(value, row, index) {
    return [
        '<a class="removeOtherActor" href="javascript:void(0)" title="Remove" >',
        '<i class="glyphicon glyphicon-remove"></i>',
        '</a>'
    ].join('');
}
window.operateOUEvents = {
    'click .removeOtherUnit': function (e, value, row, index) {
        $('#unitTable').bootstrapTable('remove', {
            field: 'unit_id',
            values: [row.unit_id]
        });
    }
};
function operateOUFormatter(value, row, index) {
    return [
        '<a class="removeOtherUnit" href="javascript:void(0)" title="Remove" >',
        '<i class="glyphicon glyphicon-remove"></i>',
        '</a>'
    ].join('');
}
//监听 修改成员表
$('#actorTable').on('editable-save.bs.table', function (e, row, $element){
    $('#actorTable').bootstrapTable("load",actorTemp);
});

//监听 添加成员
$("#addOtherActor").click(function(){
    actorTemp.push({
        "actor":"",
        "staId":"",
        "role":"",
        "marks":"0"}) ;
    $('#actorTable').bootstrapTable("load",actorTemp);
});
//监听 修改单位表
$('#unitTable').on('editable-save.bs.table', function (e, row, $element){
    $('#unitTable').bootstrapTable("load",unitTemp);
});
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
//监听 添单位员
$("#addOtherUnit").click(function(){
    unitTemp.push({"unit_id":"",
        "unit_rank":"1",
        "unit_name":""}) ;
    $('#unitTable').bootstrapTable("load",unitTemp);
});
//不可修改
function editableForm(){
    $('form input').removeAttr("disabled", "disabled");
    $('form select').removeAttr("disabled", "disabled");
    //$('#actorTable').bootstrapTable('showColumn','operate');
    $('#actorTable').bootstrapTable('showColumn','operate');
    $('#unitTable').bootstrapTable('showColumn','operate');
}
function uneditableForm(){
    $('form input').attr("disabled", "disabled");
    $('form select').attr("disabled", "disabled");
    $('#addOtherActor').attr("disabled", "disabled");
    $('#addOtherUnit').attr("disabled", "disabled");
   // $('#addFund').attr("disabled", "disabled");
    $('#actorTable').bootstrapTable('hideColumn','operate');
    $('#unitTable').bootstrapTable('hideColumn','operate');
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
//获取人员信息
function getActorsData(){
    return $("#actorTable").bootstrapTable('getData');
}
//获取参加单位信息
function getUnitsData(){
    return $("#unitTable").bootstrapTable('getData');
}