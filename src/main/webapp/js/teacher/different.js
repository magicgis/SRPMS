/**
 * Created by zheng on 2015/4/29.
 */
var actorTemp = [];
var unitTemp = [];
var fundTemp = [];
/*
 * 所有差异数据
 * */
$(function () {
    showForm();//实施时要注释掉
    $('#differentTable').bootstrapTable({
        //url: '/controller/workflow/order/xgfan/paper/all',
        sidePagination: "server",
        columns: [{
            radio: true
            //visible:false
        }, {
            field: 'id',
            title: 'id',
            sortable: true,
            visible: false
        }, {
            field: 'creator',
            title: '申请人',
            sortable: true
        }, {
            field: 'searchName',
            title: '科研名称',
            sortable: true,
            formatter: "typeTran"
        }, {
            field: 'searchType',
            title: '科研类型',
            sortable: true
        }, {
            field: 'Status',
            title: '状态',
            sortable: true,
            formatter: 'statusTran'
        }],
        responseHandler: tableTrans
    });
});
