/**
 * Created by zheng on 2015/6/28.
 */
$(function () {
    $('#MagTable').bootstrapTable({
        url: '/api/workflow/allMagOrder',
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
            field: 'name',
            title: '刊物全名',
            sortable: true
        }, {
            field: 'issn',
            title: 'ISSN',
            sortable: true
            //formatter: typeTran
        }, {
            field: 'cn',
            title: 'CN',
            sortable: true
        }, {
            field: 'sub',
            title: '刊物类别',
            sortable: true
        }, {
            field: 'type',
            title: '收录类型',
            sortable: true
        }, {
            field: 'operator',
            align: 'center',
            title: '操作',
            formatter: view
        }],
        responseHandler: tableTranMags
    });
    upToLoadFile();
});




//监听 新增
$('#addMag').click(function () {
    start();
});

//新建
function start() {
    workflow.startOrder(userName, "newMag").success(function (data) {
        afterSuccess("新建成功！");
        $('#MagTable').bootstrapTable('refresh');
    });
}


