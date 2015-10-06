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
    init();
});

function init() {
    var status = all['Status'];
    if (status.indexOf('Refuse') >= 0) {
        $('#reply').show();
        $('#reply-display').show();
        var reply = $('#reply-display').children('p');
        var who = $('#reply-display').children('small');
        reply.empty();
        who.empty();
        if (status.indexOf("Dep") >= 0) {
            reply.append(replyByDep);
            who.append("管理部门批复");
        } else {
            $('.onApproval').hide();
            $('#reply').hide();
        }
    }else if(status.indexOf('WaitForCollegeSubmit') >= 0){
        $('.onApproval').hide();
        $('#reply').hide();
    }
    console.log(status.indexOf('WaitForCollegeSubmit'));
}

function approve(){
    var approveInfo = Object();
    approveInfo["DecByCol"]=true;
    approveInfo["replyByCol"]=$('#reply-box').val();
    workflow.execute(userName,taskId,approveInfo).success(function(){
        //window.location.href="/patent";
    });
}

function refuse(){
    var refuseAwardInfo = Object();
    refuseAwardInfo["DecByCol"]=false;
    refuseAwardInfo["replyByCol"]=$('#reply-box').val();
    workflow.execute(userName,taskId,refuseAwardInfo).success(function(){
        //window.location.href="/patent";
    });
}