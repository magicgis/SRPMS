/**
 * Created by zheng on 2015/10/3.
 */
$(function(){
    $.each(optionsMenu, function (key, value) {
        disableSelectize($('#' + value).selectize());
    });
    disableSelectize($('#dept').selectize());
    uneditableForm();
    $('.onEdit').hide();
    $('#reply-box').show();
    $('#reply').show();
    $('#upload').hide();
    $('.addActor').hide();
    $('.getScore').hide();
    $('.addUnit').hide();
    $('.addFund').hide();
    init();
});
function init() {
    var status = all['Status'];
    //console.log(all['Status']);
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
        } else if(status=='WaitForDep') {
            $('#reply').hide();
            $("#Approve").attr("disabled", "disabled");
            $("#Refuse").attr("disabled", "disabled");
        }
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