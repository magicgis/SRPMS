/**
 * Created by zheng on 2015/10/12.
 */
$(function(){

    init(entity, all, replyByDep, 2);

});
//监听 添加金额信息
function Approve(){
    var approveInfo = Object();
    approveInfo["DecByCol"]=true;
    approveInfo["replyByCol"]=$('#reply-box').val();
    workflow.execute(userName, taskId, approveInfo).success(function(){
        afterSuccess('已通过！');
        window.location.href = '/index/process/food/all';
    });
}
function Refuse(){
    var refuseInfo = Object();
    refuseInfo["DecByCol"]=false;
    refuseInfo["replyByCol"]=$('#reply-box').val();
    workflow.execute(userName, taskId, refuseInfo).success(function(){
        afterSuccess('已驳回！');
        window.location.href = '/index/process/food/all';
    });
}