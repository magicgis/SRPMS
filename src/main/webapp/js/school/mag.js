/**
 * Created by zheng on 2015/6/28.
 */
var filesData = {};
$(function () {
    $('#MagTable').bootstrapTable({
        url: '/api/workflow/allMagOrder',
        sidePagination: "server",
        columns: [{
            checkbox: true,
            visible: true
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
            //formatter: "typeTran"
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
});




