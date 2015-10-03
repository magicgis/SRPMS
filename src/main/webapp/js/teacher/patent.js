/**
 * Created by zheng on 2015/4/22.
 */
var unitTemp = [];
var actorTemp = [];
var filesData = {};
//所有的专利
//var allUrl = '/api/workflow/order/' + userName + '/patent/all';
//当前url
//var url = allUrl;

$(function () {
    $('#PatentTable').bootstrapTable({
        url: '/api/workflow/order/' + userName + '/patent/all',
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
            sortable:true,
            formatter: "actorTran"
        },{
            field:'Status',
            title:'状态',
            sortable:true,
            formatter:'statusTran'
        }],
        responseHandler:tableTrans
    });
});

/*统一提交*/
$("#submit").click(function(){
    submitAll();
});

/*
 * 提交所有
 *
 * */
function submitAll(){
    var submitData='WF_User='+userName;
    workflow.submitByTeacher(submitData).success(function(){
        afterSuccess("提交成功");
        window.location.href="/patent";
    });
}