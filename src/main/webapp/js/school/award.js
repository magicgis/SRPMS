/**
 * Created by zheng on 2015/4/24.
 */
var which = 0;
$(function () {
    allInfoList();
});
$('#all').click(function () {
    which = 0;
    $('#AwardTable').bootstrapTable('destroy');
    allInfoList();
});
$('#audit').click(function () {
    which = 1;
    $('#AwardTable').bootstrapTable('destroy');
    auditInfoList()
});
//监听 点击新建
$("#add").click(function () {
    window.location.href = '/achAward/new';
});

$('#AwardTable').on('click-row.bs.table', function (e, row, $element) {
    var orderId = row["id"];
    if (which == 0) {
        window.location.href = '/achAward/' + orderId;
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
    $('#AwardTable').bootstrapTable({
        url: '/api/achAward/all',
        sidePagination:"server",
        columns: [{
            radio: true
        }, {
            field: 'id',
            title: 'id',
            sortable: true,
            visible: false
        }, {
            field: 'achName',
            title: '录入人',
            sortable: true
        }, {
            field: 'achType',
            title: '成果名称',
            sortable: true
            // formatter:"typeTran"
        }, {
            field: 'awardName',
            title: '类型',
            sortable: 'true'
        }, {
            field: 'awardRank',
            title: '获奖时间',
            sortable: true
        },{
            field:'Status',
            title:'状态',
            sortable:true,
            formatter:'statusTran'
        }, {
            field: 'process',
            title: '流程状态',
            sortable: true,
            formatter: 'processTran'
        }]
    });
}
function auditInfoList() {
    $('#AwardTable').bootstrapTable({
        url: '/api/workflow/order/' + userName + '/achAward/all',
        sidePagination:"server",
        columns: [{
            radio: true
            //visible:false
        }, {
            field: 'id',
            title: 'id',
            sortable: true,
            visible: false
        }, {
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
        }, {
            field: 'process',
            title: '流程状态',
            sortable: true,
            formatter: 'processTran'
        }],
        responseHandler: tableTrans
    });
}