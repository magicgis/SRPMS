/**
 * Created by zheng on 2015/10/12.
 */
$(function(){
    disableSelectize($('#pubType').selectize());
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
    var status = all['Status'];
    var statusCode=processStatus(status,0,2);
    $('#reply').show();
    $('#reply-display').show();
    var reply = $('#reply-display').children('p');
    var who = $('#reply-display').children('small');

    switch (parseInt(statusCode)){
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
    //if (status.indexOf('Refuse') >= 0) {
    //    $('#reply').show();
    //    $('#reply-display').show();
    //    var reply = $('#reply-display').children('p');
    //    var who = $('#reply-display').children('small');
    //    reply.empty();
    //    who.empty();
    //    if (status.indexOf("Dep") >= 0) {
    //        reply.append(replyByDep);
    //        who.append("管理部门批复");
    //    } else if(status=='WaitForDep') {
    //        $('#reply').hide();
    //        $('.onApprove').hide();
    //    }
    //}
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