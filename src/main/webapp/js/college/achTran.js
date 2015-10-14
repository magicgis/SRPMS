
$(function () {
    $('#AchTranTable').bootstrapTable({
        url: '/api/workflow/order/'+userName+'/achTran/all',
        sidePagination:"server",
        columns: [{
            radio:true
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
            title:'成果名称',
            sortable:true
        },{
            field:'achType',
            title:'成果类别',
            sortable:true,
            formatter:"typeTran"
        },{
            field:'tranUnit',
            title:'转让单位',
            sortable:'true'
        },{
            field:'date',
            title:'转让日期',
            sortable:true
        },{
            field:'WF_Actor',
            title:'参与人',
            sortable:true
        },{
            field:'Status',
            title:'状态',
            sortable:true,
            formatter:'statusTran'
        }],
        responseHandler: tableTrans
    });
});

//监听 点击成果转化表
$('#AchTranTable').on('click-row.bs.table', function (e, row, $element) {
    var orderId = row["id"];
    window.location.href = '/order/' + orderId;
});