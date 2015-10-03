/**
 * Created by zheng on 2015/4/22.
 */
var actorTemp =[];
var unitTemp =[];
var filesData = {};
//所有的专利
//var allUrl = '/api/workflow/order/' + userName + '/patent/all';
//当前url
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

//监听 点击新建
$("#add").click(function(){
    start();
});

/*
 * 新建
 *
 * */
function start(){
    $('form input').val(null);
    $('#downFiles').empty();
    actorTemp = [];
    unitTemp = [];
    filesData = {};
    workflow.startOrder(userName,"basicProcess_Beta","patent").success(function(data){
        afterSuccess("新建成功！");
        var orderId = data["id"];
        $('#WF_Order').val(orderId);
        //workflow.latestTask(orderId).success(function (currentTask) {
            // todo 跳转编辑页面
        //});
    });
}
