/**
 * Created by zheng on 2015/10/12.
 */
$(function(){
    uneditableForm();
    hideActorOperate();
    $('.onEdit').hide();
    $('#reply-box').show();
    $('#reply').show();
    $('#upload').hide();
    $('.addActor').hide();
    $('.getScore').hide();
    init(entity,all,replyByDep,2);
});
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