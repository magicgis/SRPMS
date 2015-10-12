/**
 * Created by zheng on 2015/5/17.
 */
var which = 0;
$(function () {
    allInfoList();
});
$('#all').click(function () {
    which = 0;
    $('#BookTable').bootstrapTable('destroy');
    allInfoList();
});
$('#audit').click(function () {
    which = 1;
    $('#BookTable').bootstrapTable('destroy');
    auditInfoList()
});
//监听 点击新建
$("#add").click(function () {
    window.location.href = '/book/new';
});

$('#BookTable').on('click-row.bs.table', function (e, row, $element) {
    var orderId = row["id"];
    if (which == 0) {
        window.location.href = '/book/' + orderId;
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
    $('#BookTable').bootstrapTable({
        url: '/api/book/all',
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
            title:'著作名称',
            sortable:true
        },{
            field:'pubType',
            title:'出版类型',
            sortable:true
        },{
            field:'sumWord',
            title:'著作总字数',
            sortable:true
        },{
            field:'bkReward',
            title:'著作获奖情况',
            sortable:true
        },{
            field:'S-ACTOR',
            title:'提交者',
            sortable:true
        },{
            field:'publisher',
            title:'出版社',
            sortable:true,
            formatter:"rankTran"
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
    $('#BookTable').bootstrapTable({
        url: '/api/workflow/order/' + userName + '/book/all',
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
            title:'著作名称',
            sortable:true
        },{
            field:'pubType',
            title:'出版类型',
            sortable:true
        },{
            field:'sumWord',
            title:'著作总字数',
            sortable:true
        },{
            field:'bkReward',
            title:'著作获奖情况',
            sortable:true
        },{
            field:'S-ACTOR',
            title:'提交者',
            sortable:true
        },{
            field:'publisher',
            title:'出版社',
            sortable:true,
            formatter:"rankTran"
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
        }],
        responseHandler: tableTrans
    });
}

