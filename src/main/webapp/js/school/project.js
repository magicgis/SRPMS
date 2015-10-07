/**
 * Created by Administrator on 2015/4/16.
 */
var which = 0;
$(function () {
    allInfoList();
});
$('#all').click(function () {
    which = 0;
    $('#ProjectTable').bootstrapTable('destroy');
    allInfoList();
});
$('#audit').click(function () {
    which = 1;
    $('#ProjectTable').bootstrapTable('destroy');
    auditInfoList()
});
//监听 点击新建
$("#add").click(function () {
    window.location.href = '/project/new';
});

$('#ProjectTable').on('click-row.bs.table', function (e, row, $element) {
    var orderId = row["id"];
    if (which == 0) {
        window.location.href = '/project/' + orderId;
    } else {
        window.location.href = '/order/' + orderId;
    }
});
function processTran(arg) {
    var t = {
        "0": "未启动",
        "1": "流程中",
        "9": "已结束"
    };
    return t[arg];
}
function allInfoList() {
    $('#ProjectTable').bootstrapTable({
        url: '/api/project/all',
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
            title: '名称',
            sortable: true
        }, {
            field: 'type',
            title: '类别',
            sortable: true
            //formatter: "typeTran"
        }, {
            field: 'rank',
            title: '等级',
            sortable: true
            //formatter: "rankTran"
        }, {
            field: 'rateUnit',
            title: '评分来源',
            sortable: true
            //formatter: "rateUnitTran"
        }, {
            field: 'real_time',
            title: '实际结题时间',
            sortable: true
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
        }, {
            field: 'process',
            title: '流程状态',
            sortable: true,
            formatter: 'processTran'
        }]
    });
}
function auditInfoList() {
    $('#ProjectTable').bootstrapTable({
        url: '/api/workflow/order/' + userName + '/project/all',
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
            title: '名称',
            sortable: true
        }, {
            field: 'type',
            title: '类别',
            sortable: true
            //formatter: "typeTran"
        }, {
            field: 'rank',
            title: '等级',
            sortable: true
            //formatter: "rankTran"
        }, {
            field: 'rateUnit',
            title: '评分来源',
            sortable: true
            //formatter: "rateUnitTran"
        }, {
            field: 'real_time',
            title: '实际结题时间',
            sortable: true
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
        }, {
            field: 'process',
            title: '流程状态',
            sortable: true,
            formatter: 'processTran'
        }],
        responseHandler: tableTrans
    });
}