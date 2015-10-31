/**
 * Created by Administrator on 2015/10/3.
 */
$(function(){

    $('#reply').show();

    uneditableForm();

    disableSelectize($('#dept').selectize());
    disableSelectize($('#patType').selectize());
    hideActorOperate();
    hideUnitOperate();
    $('.onEdit').hide();
    $('#upload').hide();
    init(entity,all,replyByDep,2);
});

function approve(){
    var approveInfo = Object();
    approveInfo["DecByCol"]=true;
    approveInfo["replyByCol"]=$('#reply-box').val();
    workflow.execute(userName,taskId,approveInfo).success(function(){
        window.location.href="/patent";
    });
}

function refuse(){
    var refuseAwardInfo = Object();
    refuseAwardInfo["DecByCol"]=false;
    refuseAwardInfo["replyByCol"]=$('#reply-box').val();
    workflow.execute(userName,taskId,refuseAwardInfo).success(function(){
        window.location.href="/patent";
    });
}