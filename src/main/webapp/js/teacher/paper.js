var actorTemp = [];
var filesData = {};
var pTable;
var flag = true;
/**
 * 论文信息表
 *全部||主导||确认||参与
 * */
$(function () {
    pTable = $('#PaperTable').bootstrapTable({
        sidePagination: "server",
        columns: [{
            radio: true
        }, {
            field: 'id',
            title: 'id',
            sortable: true,
            visible: false
        }, {
            field: 'name',
            title: '论文名称',
            sortable: true
        }, {
            field: 'type',
            title: '论文类别',
            sortable: true,
            formatter: "typeTran"
        }, {
            field: 'pubDate',
            title: '发表日期',
            sortable: true
        }, {
            field: 'ActorList',
            title: '参与者',
            sortable: true,
            formatter: "actorTran"
        }, {
            field: 'Status',
            title: '状态',
            sortable: true,
            formatter: 'statusTran'
        }, {
            field: 'operator',
            align: 'center',
            title: '操作',
            formatter: view
        }],
        responseHandler: tableTrans
    });
    allInfo();

});

//论文表
var tableTypes = "PaperTable";
//所有的论文
var allUrl = '/api/workflow/order/' + userName + '/paper/all';
//主导的论文
var orderUrl = '/api/workflow/order/' + userName + '/paper/1st';
//参与的论文
var partakeUrl = '/api/workflow/order/' + userName + '/paper/2nd';
//等待确认的论文
var confirmUrl = '/api/workflow/' + userName + '/confirmTask';
//当前url
var url = allUrl;


pTable.on('click-row.bs.table', function (e, row, $element) {
    var orderId = row["id"];
    window.location.href = '/order/' + orderId;
});

function allInfo() {
    pTable.bootstrapTable('refresh', {url: allUrl});
}
//所有的论文
$('#allPaper').click(function () {
    $('.paOperate').show();
    url = allUrl;
    showTableTeacher(url, tableTypes);
    $('.tabOrdBtn').show();
    $('#confirmC').hide();
});
//主导论文
$('#orderPaper').click(function () {
    $('.paOperate').show();
    url = orderUrl;
    showTableTeacher(url, tableTypes);
    $('.tabOrdBtn').show();
    $('#confirmC').hide();
});
//参与论文
$('#partakeBtn').click(function () {
    $('.paOperate').hide();
    url = partakeUrl;
    showTableTeacher(url, tableTypes);
    $('#confirmC').hide();
});
/*待确认论文*/
$('#confirmBtn').click(function () {
    $('.paOperate').hide();
    $('#confirmC').show();
    url = confirmUrl;
    showTableTeacher(url, tableTypes);
});

//监听 统一提交
$("#submit").click(function () {
    submit();
});

/**
 * 提交
 */
function submit() {
    var userInfo = "WF_User=" + userName
    workflow.submitByTeacher(userInfo).success(function () {
        afterSuccess("提交成功");
        showTableTeacher(url, tableTypes);
    });
}
//监听 点击论文表
//$('#PaperTable').on('click-row.bs.table', function (e, row, $element) {
//    $('form input').val(null);
//    actorTemp = [];
//    var orderId = row["id"];
//    $("#WF_Order").val(orderId);
//    var status = row["Status"];
//    if (row['parentTaskId'] = !null) {
//        var taskId = row['id'];
//        $('#WF_Task').val(taskId);
//    }
//    var $magId = $("#magId").selectize();
//    // 论文类型
//    var $paperType = $("#type").selectize();
//    var paperType = row["type"];
//    DisplayForm($paperType, paperType, 0);
//
//    if(paperType == "magPaper"){ // 期刊名称以及刊物级别
//        // 期刊名称
//        var magId = row["mag.id"];
//        addOptionSelectize($magId, [{'id' : magId, 'name' : row['mag.name']}]);
//        DisplayForm($magId, magId, 0);
//        // 期刊级别
//        if ((isNull(magId) || isInt(magId)) // 期刊没填 期刊在库中
//            && !(status == "Blank" || status == "Uncomplete" || status.indexOf('Refuse') >= 0) // 期刊不在填写中
//        ) {
//            recoveryMagLevel();
//        } else {
//            replaceMagLevel();
//            DisplayForm($("#otherPaper").selectize(), row["mag.standard.infoMap.col_type"], 0);
//        }
//    }// 显示magId与判断magId的代码顺序不能改
//
//    if (status == "Blank" || status == "Uncomplete" || status.indexOf('Refuse') >= 0) {
//        editableForm();
//        enableSelectize($magId);
//        if (document.querySelector("#otherPaper")) {
//            enableSelectize($("#otherPaper").selectize());
//        }
//        $('#confirm').show();
//        $('#save').show();
//        $('#orderBack').hide();
//        $('#del').show();
//        workflow.latestTask(orderId).success(function (currentTask) {
//            var taskId = currentTask[0]['id'];
//            $('#WF_Task').val(taskId);
//            if (status.indexOf('Refuse') >= 0) {
//                $('#reply').show();
//                $('#reply-display').show();
//                var reply = $('#reply-display').children('p');
//                var who = $('#reply-display').children('small');
//                reply.empty();
//                who.empty();
//                if (status.indexOf("Col") >= 0) {
//                    reply.append(currentTask[0]['variableMap']['replyByCol']);
//                    who.append("学院批复");
//                } else {
//                    $('#del').hide();
//                    $('#confirm').hide();
//                    $('#save').hide();
//                    reply.append(currentTask[0]['variableMap']['replyByDep']);
//                    who.append("管理部门批复");
//                }
//            } else {
//                $('#reply').hide();
//            }
//        });
//    } else {
//        uneditableForm();
//        disableSelectize($magId);
//        if (document.querySelector("#otherPaper")) {
//            disableSelectize($("#otherPaper").selectize());
//        }
//
//        $('#del').hide();
//        if (status == 'Complete' || status == 'WaitForSubmit') {
//            $('#orderBack').show();
//        } else {
//            $('#orderBack').hide();
//        }
//        $('#confirm').hide();
//        $('#save').hide();
//    }
//    magOrConfer();
//    //总分
//    var  score = row['score'];
//    if(score == undefined || score == null || score =="") {
//        $("#showSum").html("");
//    }else {
//        $("#showSum").html("可分配总分："+score+"分");
//    }
//    //成员信息
//    if (row['actors'] != null) {
//        actorTemp = row['actors'];
//    }
//    $("#actorTable").bootstrapTable('load', actorTemp);
//    // 会议论文收录类型
//    DisplayForm($("#conferType").selectize(), row["confer.standard.id"], 0);
//    // 报刊等级
//    DisplayForm($("#newsType").selectize(), row["newspaper.standard.id"], 0);
//    // 填充表单
//    $('#paper').autofill(row, {
//        findbyname: true,
//        restrict: false
//    });
//    // 文件信息
//    showFiles(row["filesData"]);
//    // 显示表单
//    showForm();
//});