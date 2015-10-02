/**
 * Created by zheng on 2015/4/16.
 */
//是否是第一单位
function firstOrOther(){
    if($('#isFirInMed').val()=='false'){
         $('#medunitInfo').show();
         //$('#unitTable').bootstrapTable("load",unitTemp);
    }else if($('#isFirInMed').val()=='true'){
        $('#medunitInfo').hide();
        $('#unitTable').bootstrapTable("load",unitTemp);
    }else{
        $('#medunitInfo').hide();
        $('#unitTable').bootstrapTable("load",unitTemp);
    }
}
//添加 删除 行动作
window.operateAEvents = {
    'click .removeNewMedicineActor': function (e, value, row, index) {
        $('#actorTable').bootstrapTable('remove', {
            field: 'staId',
            values: [row.staId]
        });
    }
};
function operateAFormatter(value, row, index) {
    return [
        '<a class="removeNewMedicineActor" href="javascript:void(0)" title="Remove" >',
        '<i class="glyphicon glyphicon-remove"></i>',
        '</a>'
    ].join('');
}
window.operateMEvents = {
    'click .removeMedunit': function (e, value, row, index) {
        $('#unitTable').bootstrapTable('remove', {
            field: 'medunit',
            values: [row.medunit]
        });
    }
};
function operateMFormatter(value, row, index) {
    return [
        '<a class="removeMedunit" href="javascript:void(0)" title="Remove" >',
        '<i class="glyphicon glyphicon-remove"></i>',
        '</a>'
    ].join('');
}
//监听 修改成员表
$('#actorTable').on('editable-save.bs.table', function (e, row, $element){
    $('#actorTable').bootstrapTable("load",actorTemp);
});

//监听 添加成员
$("#addNewMedicineActor").click(function(){
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

//监听 添单位员
$("#addMedunit").click(function(){
    unitTemp.push({"unit_id":"",
        "unit_rank":"",
        "unit_name":""}) ;
    $('#unitTable').bootstrapTable("load",unitTemp);
});
//显示总览
function showTable(){
    $('#NewMedicineTable').bootstrapTable('refresh',{silent: true});
    $('#medicineTable-box').removeClass('collapsed');
    $('#medicine-box').addClass('collapsed');
}
function showForm(){
    $('#medicineTable-box').addClass('collapsed');
    $('#medicine-box').removeClass('collapsed');
}
//不可修改
function editableForm(){
    $('form input').removeAttr("disabled", "disabled");
    $('form select').removeAttr("disabled", "disabled");
    //$('#fundTable').bootstrapTable('showColumn','operate');
    $('#actorTable').bootstrapTable('showColumn','operate');
    $('#unitTable').bootstrapTable('showColumn','operate');
}
function uneditableForm(){
    $('form input').attr("disabled", "disabled");
    $('form select').attr("disabled", "disabled");
    $('#addNewMedicineActor').attr("disabled", "disabled");
    $('#addMedunit').attr("disabled", "disabled");
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