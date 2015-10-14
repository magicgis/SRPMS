/**
 * Created by zheng on 2015/4/22.
 */
var actorTemp =[];
var unitTemp =[];
//var allUrl = '/api/workflow/order/'+userName+'/patent/all';
//var url = allUrl;
$(function () {
    $('#PatentTable').bootstrapTable({
        url:'/api/workflow/order/'+userName+'/patent/all',
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
            title:'专利名称',
            sortable:true
        },{
            field:'state',
            title:'专利状态',
            sortable:true
        },{
            field:'ActorList',
            title:'参与者',
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
$('#PatentTable').on('click-row.bs.table', function (e, row, $element) {
    var orderId = row["id"];
    window.location.href = '/order/' + orderId;
});