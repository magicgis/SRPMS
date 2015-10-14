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
    init();
});
function init() {
    var status = all['Status'];
    var statusCode=processStatus(status,0,2);
    $('#reply').show();
    $('#reply-display').show();
    var reply = $('#reply-display').children('p');
    var who = $('#reply-display').children('small');

    switch (parseInt(statusCode)){
        case 211:
            $('#reply-box').show();
            reply.append(replyByDep);
            who.append("管理部门批复");
            break;
        case 210:
            $('#reply-box').show();
            reply.remove();
            who.remove();
            break;
        case 200:
            reply.append(replyByCol);
            who.append("学院批复");
            $('#reply-box').hide();
            $('.onApproval').hide();
            break;
    }
}

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