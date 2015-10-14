
var which = 0; //默认显示全部转化
var AchTranTable = $('#AchTranTable');

$(function () {
    allList();
});

$('#approve').click(function() {
    which = 1; // 显示待审核专利
    AchTranTable.bootstrapTable('destroy');
    approveList();
});

$('#all').click(function() {
    which = 0; // 显示全部专利
    AchTranTable.bootstrapTable('destroy');
    allList();
});

//监听 点击新建
$("#add").click(function () {
    window.location.href = '/achTran/new';
});

//监听 点击table
AchTranTable.on('click-row.bs.table', function (e, row, $element) {
    var orderId = row["id"];
    if (which == 0) {
        window.location.href = '/achTran/' + orderId;
    } else {
        window.location.href = '/order/' + orderId;
    }
});

function allList() {
    AchTranTable.bootstrapTable({
        url: '/api/achTran/all',
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
            title: '成果名称',
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
}

function approveList(){
    AchTranTable.bootstrapTable({
        url: '/api/workflow/order/'+userName+'/achTran/all',
        sidePagination:"server",
        columns: [{
            radio:true,
            visible:false
        },{
            field: 'id',
            title: 'id',
            sortable: true,
            visible:false
        },{
            field:'S-ACTOR',
            title:'提交者',
            sortable:true
        },{
            field:'name',
            title:'成果转化名称',
            sortable:true
        },{
            field:'achType',
            title:'成果类别',
            sortable:true,
            formatter:"typeTran"
        },{
            field:'tranUnit',
            title:'转让单位',
            sortable:true
        },{
            field:'WF_Col',
            title:'学院',
            sortable:true
        },{
            field:'ActorList',
            title:'参与者',
            sortable:true,
            formatter: 'actorTran'
        },{
            field:'Status',
            title:'状态',
            sortable:true,
            formatter:'statusTran'
        }],
        responseHandler:tableTrans
    });

}


