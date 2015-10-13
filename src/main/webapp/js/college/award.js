/**
 * Created by zheng on 2015/4/24.
 */
$(function () {
    $('#AwardTable').bootstrapTable({
        url: '/api/workflow/order/' + userName + '/achAward/all',
        sidePagination: "server",
        columns: [{
            radio: true
            //visible:false
        }, {
            field: 'id',
            title: 'id',
            sortable: true,
            visible: false
        },  {
            field: 'achName',
            title: '录入人',
            sortable: true
        }, {
            field: 'name',
            title: '成果名称',
            sortable: true
        }, {
            field: 'awardType',
            title: '类型',
            sortable: 'true'
        }, {
            field: 'date',
            title: '获奖时间',
            sortable: true
        }, {
            field: 'Status',
            title: '状态',
            sortable: true,
            formatter: 'statusTran'
        }],
        responseHandler:tableTrans
    });
});
//监听 点击table
$('#AwardTable').on('click-row.bs.table', function (e, row, $element) {
    var orderId = row["id"];
    window.location.href = '/order/' + orderId;
});