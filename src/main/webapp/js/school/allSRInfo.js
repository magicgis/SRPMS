/**
 * Created by zheng on 2015/6/12.
 */
/**
 * 所有科研信息
 * */
var actorTemp = [];
$(function () {
    $('#allSRInfoTable').bootstrapTable({
        url: '/api/workflow/order/' + userName + '/all/all',
        sidePagination: "server",
        columns: [{
            radio: true
            //visible:false
        }, {
            field: 'id',
            title: 'id',
            sortable: true,
            visible: false
        }, {
            field: 'ActorList',
            title: '人员',
            sortable: true,
            formatter: 'actorTran'
        }/*,{
         field:'searchName',
         title:'科研名称',
         sortable:true,

         }*/, {
            field: 'WF_Type',
            title: '科研类型',
            sortable: true,
            formatter: "wfTypeTran"
        }, {
            field: 'Status',
            title: '状态',
            sortable: true,
            formatter: 'statusTran'
        }, {
            field: 'operator',
            align: 'center',
            title: '操作',
            width: 75,
            formatter: view
        }],
        responseHandler: tableTrans
    });
    /* $('#tableOrd').hide();*/
});
//$('#allSRInfoTable').on('click-row.bs.table', function (e, row) {
//    var wfType = row['WF_Type'];
//    var taskId = row['id'];
//    for (var key in selectDefferent) {
//        if (key == wfType) {
//            $("#confirmForm").empty();
//            var url = selectDefferent[key];
//            showForm();
//            $("#confirmForm").load(url, function () {
//                $('#WF_Task').val(taskId);
//                $('form input').val(null);
//                uneditableForm();
//                if (row['actors'] != null) {
//                    actorTemp = row['actors'];
//                } else {
//                    actorTemp =[];
//                }
//                /*显示成员表*/
//                scanActorTable();
//                showFiles(row["filesData"]); //显示文件信息
//                $('#' + wfType).autofill(row, {
//                    findbyname: true,
//                    restrict: false
//                });
//
//                magOrConfer();
//                $("#actorTable").bootstrapTable('load', actorTemp);
//                $('#addDiff').hide();
//                $('#reply').hide();
//                $('#getScore').hide();
//                if (wfType == 'paper') {
//                    paperType();
//                    $('#magId').val(row['mag.name']);
//                }
//                //showForm();
//            });
//        }
//    }
//});
//监听 点击返回
$("#back").click(function () {
    showTable();
});