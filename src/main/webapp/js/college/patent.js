/**
 * Created by zheng on 2015/4/22.
 */
var actorTemp =[];
var unitTemp =[];
var allUrl = '/api/workflow/order/'+userName+'/patent/all';
var url = allUrl;
$(function () {
    $('#PatentTable').bootstrapTable({
        url:'/api/workflow/order/'+userName+'/patent/all', //todo
        sidePagination:"server",
        columns: [{
            radio:true
        },{
            field: 'id',
            title: 'id',
            sortable: true,
            visible:false
        },{
            field:'name',
            title:'专利名称',
            sortable:true
        },{
            field:'state',
            title:'专利状态',
            sortable:true
        },{
            field:'ActorList',
            title:'参与者',
            sortable:true
        },{
            field:'Status',
            title:'状态',
            sortable:true,
            formatter:'statusTran'
        }],
        responseHandler: tableTrans
    });

    $('#actorTable').bootstrapTable({
        columns: [
            {
                field: 'staff.id',
                title: '工号',
                sortable: true,
                visible: false
            }, {
                field: 'rank',
                title: '排名',
                sortable: true,
                footerFormatter: "totalNameFormatter"
            }, {
                field: 'staff.name',
                title: '成员',
                sortable: true
            }, {
                field: 'role',
                title: '角色',
                sortable: true
            }, {
                field: 'score',
                title: '分数',
                sortable: true,
                footerFormatter: "totalMarksFormatter"
            }, {
                field: 'unit',
                title: '归属单位',
                sortable: true
            }],
        data: actorTemp
    });
    $('#unitTable').bootstrapTable({
        columns: [{
            field:'rank',
            title:'排名',
            editable:false,
            sortable:true,
            footerFormatter:"totalUnitFormatter"
        },{
            field:'unit',
            title:'单位名称',
            editable:false,
            sortable:true
        }],
        data:unitTemp
    });
    $('#reply').show();
    uneditableForm();
    $('#upload').hide();
});

function approve(){
    var approveInfo = Object();
    approveInfo["DecByCol"]=true;
    approveInfo["replyByCol"]=$('#reply-box').val();
    workflow.execute('col',$('#WF_Task').val(),approveInfo).success(function(){
        window.location.href="/patent";
    });
}

function refuse(){
    var refuseAwardInfo = Object();
    refuseAwardInfo["DecByCol"]=false;
    refuseAwardInfo["replyByCol"]=$('#reply-box').val();
    workflow.execute('col',$('#WF_Task').val(),refuseAwardInfo).success(function(){
        window.location.href="/patent";
    });
}

//监听 点击table
//$('#PatentTable').on('click-row.bs.table', function (e, row, $element) {
//    $('form input').val(null);
//    actorTemp =[];
//    var orderId = row["id"];
//    var status = row["Status"];
//    //if (row['parentTaskId'] = !null) {
//    //    var taskId = row['id'];
//    //    $('#WF_Task').val(taskId);
//    //}
//    uneditableForm();
//    if(status == 'WaitForCol') {
//        workflow.latestTask(orderId).success(function (currentTask) {
//            var taskId = currentTask[0]['id'];
//            $('#WF_Task').val(taskId);
//        });
//        $("#Approve").removeAttr("disabled","disabled");
//        $("#Refuse").removeAttr("disabled","disabled");
//    }else if (status.indexOf('Refuse') >= 0) {
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
//                if (status.indexOf("Dep") >= 0) {
//                    reply.append(currentTask[0]['variableMap']['replyByDep']);
//                    who.append("管理部门批复");
//                } else {
//                    $('#reply').hide();
//                    $("#Approve").attr("disabled", "disabled");
//                    $("#Refuse").attr("disabled", "disabled");
//                }
//            }
//        });
//    } else {
//        $("#Approve").attr("disabled", "disabled");
//        $("#Refuse").attr("disabled", "disabled");
//    }
//
//    //成员信息
//    if (row['actors'] != null) {
//        actorTemp = row['actors'];
//    }
//    $("#actorTable").bootstrapTable('load', actorTemp);
//    //单位信息
//    if(row['units'] !== null) {
//        unitTemp = row['units'];
//    }
//    $('#unitTable').bootstrapTable("load", unitTemp);
//    $('#patent').autofill(row, {
//        findbyname: true,
//        restrict: false
//    });
//    $('#dept').val( row['dept.value'] );
//    $('#patType').val( row['patent.standard.value'] );
//    // 文件信息
//    showFiles(row["filesData"]);
//    showForm();
//});
//监听 点击返回
//$("#back").click(function(){
//    showTable();
//});
//通过
//$("#Approve").click(function(){
//    approve();
//});
//驳回
//$("#Refuse").click(function(){
//    refuse();
//});
//下一条
//$("#next").click(function(){
//});
//上一条
//$("#previous").click(function(){
//});
