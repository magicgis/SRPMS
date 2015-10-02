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

//是否显示共有单位表
function unitTableShow(){
    if($('#isComAch').val()=="yes"){
        $('#unitInfo').show();
    }else if($('#isComAch').val()=="no"){
        $('#unitInfo').hide();
    }else{
        $('#unitInfo').hide();
    }
}

//表单不可编辑
function uneditableForm(){
    $('form input').attr("disabled", "disabled");
    $('form select').attr("disabled", "disabled");
    $('#addActor').attr("disabled", "disabled");
    $('#actorTable').bootstrapTable('hideColumn','operate');
}

//表单可编辑
function editableForm(){
    $('form input').removeAttr("disabled", "disabled");
    $('form select').removeAttr("disabled", "disabled");
    $('#addActor').removeAttr("disabled", "disabled");
    $('#actorTable').bootstrapTable('showColumn','operate');
}

//显示总览
function showTable(){
    $('#AppraiseTable').bootstrapTable('refresh',{silent: true});
    $('#appraiseTable-box').removeClass('collapsed');
    $('#appraise-box').addClass('collapsed');
}

//显示详情
function showForm(){
    $('#appraiseTable-box').addClass('collapsed');
    $('#appraise-box').removeClass('collapsed');
}

//获取最新提交内容
function getSubmission(pData){
    var max = 0;
    var keyStr="";
    for(var key in pData){
        var partn = new RegExp('WF_\\d+_Submission',["i"]);
        if(partn.exec(key)){
            var keyValue=key.substring(3,key.length-11);
            if(max < keyValue){
                max = keyValue;
            }
        }
        keyStr="WF_"+max+"_Submission";
    }
    return keyStr;
}
//--------------------------------actorTable总计----------------------------------------
function totalNameFormatter(data) {
    return "共"+data.length+"人";
}

function totalMarksFormatter(data) {
    var total = 0;
    $.each(data, function (i, row) {
        total += +(row.marks.toString().substring(0));
    });
    return '总分:' + total+"分";
}
//--------------------------------unitTable总计------------------------------------
function totalNameFormattera(data){
    return "共"+data.length+"个单位"
}

//审核未通过
function pubrefuse(data){
    return $.ajax({
        type:'post',
        url:'/api/workflow/execute',
        data:JSON.stringify(data),
        dataType : 'json',
        contentType:'application/json;charset=UTF-8',
        success:function(){
            return showTable;
        }
    });
}
//审核通过
function pubapprove(data){
    return $.ajax({
        type:'post',
        url:'/api/workflow/execute',
        data:JSON.stringify(data),
        dataType : 'json',
        contentType:'application/json;charset=UTF-8',
        success:function(){
            return showTable;
        }
    });
}

