/**
 * Created by Administrator on 2015/10/3.
 */
$(function(){

    $('#reply').show();

    uneditableForm();

    init();

    $('#upload').hide();
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

function approve(){
    var approveInfo = Object();
    approveInfo["DecByCol"]=true;
    approveInfo["replyByCol"]=$('#reply-box').val();
    workflow.execute('col',$('#WF_Task').val(),approveInfo).success(function(){
        //window.location.href="/patent";
    });
}

function refuse(){
    var refuseAwardInfo = Object();
    refuseAwardInfo["DecByCol"]=false;
    refuseAwardInfo["replyByCol"]=$('#reply-box').val();
    workflow.execute('col',$('#WF_Task').val(),refuseAwardInfo).success(function(){
        //window.location.href="/patent";
    });
}