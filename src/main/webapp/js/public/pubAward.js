/**
 * Created by zheng on 2015/4/24.
 */
//是否联合项目
function firstOrOther(){
    if($('#isComAch').val()=='true'){
        $('#unitInfo').show();
        //$('#medunitTable').bootstrapTable("load",medunitTemps);
    }else if($('#isComAch').val()=='false'){
        $('#unitInfo').hide();
        $('#unitTable').bootstrapTable("load",unitTemp);
    }else{
        $('#unitInfo').hide();
        $('#unitTable').bootstrapTable("load",unitTemp);
    }
}
//显示详情
function showForm(){
    //$('#awardTable-box').addClass('collapsed');用时不能注释
    $('#award-box').removeClass('collapsed');
}
//显示总览
function showTable(){
    $('#AwardTable').bootstrapTable('refresh',{silent: true});
    $('#awardTable-box').removeClass('collapsed');
    $('#award-box').addClass('collapsed');
}
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
};
//添加 删除 行动作
window.operateAAEvents = {
    'click .removeAwardActor': function (e, value, row, index) {
        $('#actorTable').bootstrapTable('remove', {
            field: 'staId',
            values: [row.staId]
        });
    }
};
function operateAAFormatter(value, row, index) {
    return [
        '<a class="removeAwardActor" href="javascript:void(0)" title="Remove" >',
        '<i class="glyphicon glyphicon-remove"></i>',
        '</a>'
    ].join('');
};
window.operateAUEvents = {
    'click .removeUnit': function (e, value, row, index) {
        $('#unitTable').bootstrapTable('remove', {
            field: 'unitName',
            values: [row.unitName]
        });
    }
};
function operateAUFormatter(value, row, index) {
    return [
        '<a class="removeUnit" href="javascript:void(0)" title="Remove" >',
        '<i class="glyphicon glyphicon-remove"></i>',
        '</a>'
    ].join('');
}
//监听 修改成员表
$('#actorTable').on('editable-save.bs.table', function (e, row, $element){
    $('#actorTable').bootstrapTable("load",actorTemp);
});

//监听 添加成员
$("#addActor").click(function(){
    actorTemp.push({"staName":"", "staId":"","role":"","marks":"0"  }) ;
    $('#actorTable').bootstrapTable("load",actorTemp);
});
//监听 修改单位表
$('#unitTable').on('editable-save.bs.table', function (e, row, $element){
    $('#unitTable').bootstrapTable("load",unitTemp);
});

//监听 添单位员
$("#addUnit").click(function(){
    unitTemp.push({"unitName":"", "unitRank":"" }) ;
    $('#unitTable').bootstrapTable("load",unitTemp);
});
//单位数目及人数
function totalNameFormatter(data) {
    return "共"+data.length+"人";
}
function totalMarksFormatter(data){
    return "共"+data.length+"人";
}
function totalUnitFormatter(data){
    return "共"+data.length+"单位";
}
//表单不可编辑
function uneditableForm(){
    $('form input').attr("disabled", "disabled");
    $('form select').attr("disabled", "disabled");
    $('#addActor').attr("disabled", "disabled");
    $('#addUnit').attr("disabled", "disabled");
    //$('#actorTable').bootstrapTable('hideColumn','operate');addUnit
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