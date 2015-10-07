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
$('#allSRInfoTable').on('click-row.bs.table', function (e, row) {
    var taskId = row['id'];
    window.location.href='/order/'+taskId;
});
$("#back").click(function () {
    showTable();
});