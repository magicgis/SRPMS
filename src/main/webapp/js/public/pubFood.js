/**
 * Created by zheng on 2015/4/16.
 */
var actorTemp =[{"staName":"", "staId":"","foodStaRole":"","foodStaScore":"0"  }];
var unitTemp =[{"foodUnit":"", "foodUnitRank":"" }];
//是否是第一单位
function firstOrOther(){
    if($('#isFirInMed').val()=='false'){
        $('#foodUnitInfo').show();
        //$('#unitTable').bootstrapTable("load",unitTemp);
    }else if($('#isFirInMed').val()=='true'){
        $('#foodUnitInfo').hide();
        $('#unitTable').bootstrapTable("load",unitTemp);
    }else{
        $('#foodUnitInfo').hide();
        $('#unitTable').bootstrapTable("load",unitTemp);
    }
}
//新增 删除 航操作
window.operateFEvents = {
    'click .removeNewFoodActor': function (e, value, row, index) {
        $('#actorTable').bootstrapTable('remove', {
            field: 'staId',
            values: [row.staId]
        });
    }
};
function operateFFormatter(value, row, index) {
    return [
        '<a class="removeNewFoodActor" href="javascript:void(0)" title="Remove" >',
        '<i class="glyphicon glyphicon-remove"></i>',
        '</a>'
    ].join('');
}
window.operateUFEvents = {
    'click .removeFoodunit': function (e, value, row, index) {
        $('#unitTable').bootstrapTable('remove', {
            field: 'foodUnit',
            values: [row.foodUnit]
        });
    }
};
function operateUFFormatter(value, row, index) {
    return [
        '<a class="removeFoodunit" href="javascript:void(0)" title="Remove" >',
        '<i class="glyphicon glyphicon-remove"></i>',
        '</a>'
    ].join('');
}
function uneditableForm(){
    $('form input').attr("disabled", "disabled");
    $('form select').attr("disabled", "disabled");
    $('#addActor').attr("disabled", "disabled");
    $('#actorTable').bootstrapTable('hideColumn','operate');
}
//监听 修改成员表
$('#actorTable').on('editable-save.bs.table', function (e, row, $element){
    $('#actorTable').bootstrapTable("load",actorTemp);
});

//监听 添加成员
$("#addNewFoodActor").click(function(){
    actorTemp.push({
        "staName":"",
        "staId":"",
        "foodStaRole":"",
        "foodStaScore":"0"}) ;
    $('#actorTable').bootstrapTable("load",actorTemp);
});
//监听 修改单位表
$('#unitTable').on('editable-save.bs.table', function (e, row, $element){
    $('#unitTable').bootstrapTable("load",unitTemp);
});

//监听 添单位员
$("#addFoodUnit").click(function(){
    unitTemp.push({
        "foodUnit":"",
        "foodUnitRank":"" }) ;
    $('#unitTable').bootstrapTable("load",unitTemp);
});
function showForm(){
   // $('#foodTable-box').addClass('collapsed');
    $('#food-box').removeClass('collapsed');
}
