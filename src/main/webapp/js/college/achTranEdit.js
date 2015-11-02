/**
 * Created by Administrator on 2015/10/3.
 */
$(function(){

    init(entity,all,replyByDep,2);
});
function approve(){
    var approveInfo = Object();
    approveInfo["DecByCol"]=true;
    approveInfo["replyByCol"]=$('#reply-box').val();
    workflow.execute(userName, taskId, approveInfo).success(function(){
        afterSuccess('已审批通过！');
    });
}

function refuse(){
    var refuseAwardInfo = Object();
    refuseAwardInfo["DecByCol"]=false;
    refuseAwardInfo["replyByCol"]=$('#reply-box').val();
    workflow.execute(userName, taskId, refuseAwardInfo).success(function(){
        afterSuccess('已驳回给教师！');
    });
}