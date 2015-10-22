/**
 * Created by zheng on 2015/10/12.
 */
$(function(){
    disableSelectize($('#pubType').selectize());
    disableSelectize($('#dept').selectize());
    uneditableForm();
    hideActorOperate();
    $('.onEdit').hide();
    $('#reply-box').show();
    $('#reply').show();
    $('#upload').hide();
    $('.addActor').hide();
    $('.getScore').hide();
    init();
});
function init() {
    $('#reply').show();
    $('#reply-display').show();
    var reply = $('#reply-display').children('p');
    var who = $('#reply-display').children('small');
    var status = entity['Status'];
    var statusCode=parseInt(processStatus(status,0,3));
    switch (statusCode){
        case 211:
            reply.append(replyByDep);
            who.append("管理部门批复学院批复");
            break;
        case 210:
            reply.remove();
            who.remove();
            break;
        case 200:
            reply.append(replyByDep);
            who.append("管理部门批复");
            $('#reply').attr("disable","disable");
            $('.onApprove').hide();
            break;
    }
}
//监听 添加金额信息
function Approve(){
    var approveInfo = Object();
    approveInfo["DecByCol"]=true;
    approveInfo["replyByCol"]=$('#reply-box').val();
    workflow.execute('col',taskId,approveInfo).success(function(){
        afterSuccess('审核成功！');
    });
}
function Refuse(){
    var refuseInfo = Object();
    refuseInfo["DecByCol"]=false;
    refuseInfo["replyByCol"]=$('#reply-box').val();
    workflow.execute('col',taskId,refuseInfo).success(function(){
        afterSuccess('驳回成功！');
    });
}