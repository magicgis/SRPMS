/**
 * Created by zheng on 2015/5/11.
 */
$(function(){
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
        }],
        responseHandler: tableTrans
    });
});
$('#BookTable').on('click-row.bs.table', function (e, row, $element) {
    var orderId = row["id"];
    window.location.href = '/order/' + orderId;
});
