/**
 * Created by zheng on 2015/10/12.
 */
$(function(){
    var elementlist = document.querySelectorAll('.selectized');
    $.each(elementlist, function(index, value) {
        disableSelectize($(value).selectize());
    });
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
            who.append("管理部门批复");
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