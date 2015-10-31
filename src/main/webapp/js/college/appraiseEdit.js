/**
 * Created by Administrator on 2015/10/3.
 */
$(function(){
    uneditableForm();
    hideActorOperate();
    hideUnitOperate();
    $('.onEdit').hide();
    $('.onDel').hide();
    $('#reply').show();
    $('#upload').hide();
    $('#getScore').hide();
    init(entity,all,replyByDep,2);
});

function approve(){
    var approveInfo = Object();
    approveInfo["DecByCol"]=true;
    approveInfo["replyByCol"]=$('#reply-box').val();
    workflow.execute(userName, taskId, approveInfo).success(function(){
        //window.location.href="/appraise";
    });
}

function refuse(){
    var refuseAwardInfo = Object();
    refuseAwardInfo["DecByCol"]=false;
    refuseAwardInfo["replyByCol"]=$('#reply-box').val();
    workflow.execute(userName, taskId, refuseAwardInfo).success(function(){
        //window.location.href="/appraise";
    });
}