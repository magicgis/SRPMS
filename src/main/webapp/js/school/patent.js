/**
 * Created by zheng on 2015/4/22.
 */
//所有的专利
//var allUrl = '/api/workflow/order/' + userName + '/patent/all';
//当前url
//var url = allUrl;

$(function () {

    // todo  需要根据实际进行修改
    $('#PatentTable').bootstrapTable({
        url: '/api/patent/all',
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
            title: '专利名称',
            sortable: true
        }, {
            field: 'state',
            title: '专利状态',
            sortable: true
        }, {
            field: 'ActorList',
            title: '参与者',
            sortable: true
        }, {
            field: 'Status',
            title: '状态',
            sortable: true,
            formatter: 'statusTran'
        }]
    });

});

//监听 点击新建
$("#add").click(function () {
    window.location.href = '/patent/new';
});

$('#PatentTable').on('click-row.bs.table', function (e, row, $element) {
    var orderId = row["id"];
    window.location.href = '/patent/' + orderId;
});
