/**
 * Created by zheng on 2015/10/3.
 */
$(function(){
    $('#fundToolbar').hide();
    init(entity,all,replyByDep,2);
});
//监听 添加金额信息
function Approve(){
    var approveInfo = Object();
    approveInfo["DecByCol"]=true;
    approveInfo["replyByCol"]=$('#reply-box').val();
    workflow.execute('col',taskId,approveInfo).success(function(){
        afterSuccess('已审批通过！');
        window.location.href = '/index/process/project/all';
    });
}
function Refuse(){
    var refuseInfo = Object();
    refuseInfo["DecByCol"]=false;
    refuseInfo["replyByCol"]=$('#reply-box').val();
    workflow.execute('col',taskId,refuseInfo).success(function(){
        afterSuccess('已驳回给教师！');
        window.location.href = '/index/process/project/all';
    });
}