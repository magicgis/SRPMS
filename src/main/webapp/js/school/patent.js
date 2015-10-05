/**
 * Created by zheng on 2015/4/22.
 */
//所有的专利
//var allUrl = '/api/workflow/order/' + userName + '/patent/all';
//当前url
//var url = allUrl;

$(function () {

    // todo  需要根据实际进行修改 important
    $('#PatentTable').bootstrapTable({
        url: '/api/patent/all',
        sidePagination: "server",
        flat: true,
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
            field: 'argMap.Main-ActorName',
            title: '负责人'
        }, {
            field: 'process',
            title: '流程状态',
            sortable: true,
            formatter: 'processTran'
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


function processTran(arg) {
    var t = {
        "0": "未启动",
        "1": "流程中",
        "9": "已结束"
    };
    return t[arg];
}
