/**
 * Created by huyuanyuan555 on 2015/4/19.
 */
$(function () {
    $('#AppraiseTable').bootstrapTable({
        url: '/api/workflow/order/' + userName + '/achAppraisal/all',
        sidePagination: "server",
        columns: [{
            radio:true
            //visible:false
        },{
            field: 'id',
            title: 'id',
            sortable: true,
            visible:false
        },{
            field:'name',
            title:'成果名称',
            sortable:true
        },{
            field:'achType',
            title:'成果类型',
            sortable:true,
            formatter:"typeTran"
        }, {
            field:'certifyUnit',
            title:'鉴定单位',
            sortable:'true'
        },{
            field:'date',
            title:'鉴定日期',
            sortable:true
        },{
            field:'ActorList',
            title:'参与者',
            sortable:true,
            formatter: "actorTran"
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
