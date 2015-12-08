/**
 * Created by zheng on 2015/10/12.
 */
$(function () {
    //init();
    init(entity, all, replyByDep, 1);
    hideActorOperate();
    $('.giveSum').hide();
    $('.save').hide();
});

var flag = true;
function save() {
    var send = new Object();
    send['IsComplete'] = 'false';
    send['actors'] = getActorsData();
    //send['Main-Actor'] = Main_Actor;
    //send['Main-ActorName'] = Main_ActorName;
    workflow.execute(userName, taskId, send).success(function () {
        afterSuccess("保存成功！");
        window.location.href = '/index/process/book/all';
    });
}
function confirm() {
    var send = new Object();
    if( isMainActor(Main_Actor, userName) ) {
        send =  getForm_notSerialize();
        send['actors'] = getActorsData();
        send['IsComplete'] = 'true';
    }
    send['WF_User'] = userName;
    send['WF_Task'] = taskId;
    BootstrapDialog.confirm({
        title: '确认信息',
        message: '确认?',
        type: BootstrapDialog.TYPE_INFO,
        closable: true,
        draggable: true,
        btnCancelLabel: '取消',
        btnOKLabel: '确认',
        btnOKClass: 'btn-ok',
        callback: function (result) {
            /**
             * userName,taskId,status
             */
            if (result) {
                workflow.execute(userName, taskId, send).success(function (data, textStatus, xhr) {
                    var statusCode =xhr.status;
                    if (statusCode == 200) {
                        if ("valid" in data) {
                            if (data["valid"] == true) {
                                afterSuccess("确认成功！");
                                window.location.href = '/index/process/book/all';
                            } else {
                                errorMsg(data["msg"]);
                            }
                        } else {
                            afterSuccess("确认成功！");
                            window.location.href = '/index/process/book/all';
                        }
                    } else if(statusCode == 204) {
                        window.location.href = '/index/process/book/all';
                    }
                });

            }
        }
    });
}
