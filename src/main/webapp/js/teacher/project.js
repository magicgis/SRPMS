/**
 * Created by Administrator on 2015/4/16.
 */
var which = 0;
$(function () {
    $('#ProjectTable').bootstrapTable({
        url: '/api/workflow/order/' + userName + '/project/all',
        sidePagination: "server",
        columns: [{
            radio: true
        }, {
            field: 'id',
            title: 'id',
            sortable: true,
            visible: false
        }, {
            field: 'name',
            title: '名称',
            sortable: true
        }, {
            field: 'type',
            title: '类别',
            sortable: true
        }, {
            field: 'rank',
            title: '等级',
            sortable: true
        }, {
            field: 'rateUnit',
            title: '评分来源',
            sortable: true
        }, {
            field: 'real_time',
            title: '实际结题时间',
            sortable: true
        }, {
            field: 'Status',
            title: '状态',
            sortable: true,
            formatter: 'statusTran'
        }, {
            field: 'operator',
            align: 'center',
            title: '操作',
            formatter: view
        }],
        responseHandler: tableTrans
    });
});
$('#waitConfirm').click(function () {
    
});
$('#leadProject').click(function () {

});
//监听 点击表
/**
 * */
/*统一提交*/
$("#submit").click(function () {
    submitProjectInfo();
});
/*
 * 提交所有
 * */
function submitProjectInfo() {
    var submitData = 'WF_User=' + userName;
    workflow.submitByTeacher(submitData).success(function () {
        afterSuccess("提交成功");
        window.location.href="/project";
    })
}
$('#ProjectTable').on('click-row.bs.table', function (e, row, $element) {
    var orderId = row["id"];
    window.location.href = '/order/' + orderId;
});