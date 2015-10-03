/**
 * Created by Administrator on 2015/4/16.
 */
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
            //formatter: "typeTran"
        }, {
            field: 'rank',
            title: '等级',
            sortable: true
            //formatter: "rankTran"
        }, {
            field: 'rateUnit',
            title: '评分来源',
            sortable: true
            //formatter: "rateUnitTran"
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

/*添加新项目
* */
$("#addProject").click(function () {
    startNewProject();
});
/*统一提交*/
$(".submit").click(function () {
    submitProjectInfo();
});
//添加新项目的行为函数
function startNewProject() {
    workflow.startOrder(userName, "basicProcess_Beta", "project").success(function (data) {
        afterSuccess("新建成功！");
        $('#ProjectTable').bootstrapTable('refresh');
    });
}
function submitProjectInfo() {
    var submitData = 'WF_User=' + userName;
    workflow.submitByTeacher(submitData).success(function () {
        afterSuccess("提交成功");
        showTable();
    })
}