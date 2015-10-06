/**
 * Created by zheng on 2015/10/3.
 */
$(function(){
    init();
    $('#reply-box').show();
    $('#reply').show();
    $('#upload').hide();
    $('.addActor').hide();
    $('.getScore').hide();
    $('.addUnit').hide();
});
function init() {
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
            $('#reply').hide();
            $("#Approve").attr("disabled", "disabled");
            $("#Refuse").attr("disabled", "disabled");
        }
    }
}
//监听 添加金额信息
$('.addFund').hide();
function Approve(){
    var approveInfo = Object();
    approveInfo["DecByCol"]=true;
    approveInfo["replyByCol"]=$('#reply-box').val();
    workflow.execute('col',taskId,approveInfo).success(function(){
    });
}
function Refuse(){
    var refuseAwardInfo = Object();
    refuseAwardInfo["DecByCol"]=false;
    refuseAwardInfo["replyByCol"]=$('#reply-box').val();
    workflow.execute('col',taskId,refuseAwardInfo).success(function(){
    });
}