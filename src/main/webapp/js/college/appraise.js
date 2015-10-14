
$(function () {
    $('#AppraiseTable').bootstrapTable({
        url: '/api/workflow/order/'+userName+'/achAppraisal/all',
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
            field:'achName',
            title:'成果名称',
            sortable:true
        },{
            field:'achType',
            title:'成果类别',
            sortable:true,
            formatter:"typeTran"
        },{
            field:'checkName',
            title:'鉴定单位',
            sortable:true
        },{
            field:'WF_Actor',
            title:'参与人',
            sortable:true
        },{
            field:'appraiseDate',
            title:'鉴定日期',
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

//监听 点击成果鉴定表
$('#AppraiseTable').on('click-row.bs.table', function (e, row, $element) {
    var orderId = row["id"];
    window.location.href = '/order/' + orderId;
});