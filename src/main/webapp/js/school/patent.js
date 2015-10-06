/**
 * Created by zheng on 2015/4/22.
 */
//所有的专利
//var allUrl = '/api/workflow/order/' + userName + '/patent/all';
//当前url
//var url = allUrl;
var which = 0; //默认显示全部专利

$(function () {

    // todo  需要根据实际进行修改 important
    allPatentList();

});

$('#approve').click(function() {
    which = 1; // 显示待审核专利
    $('#PatentTable').bootstrapTable('destroy');
    approvePatentList();
});

$('#all').click(function() {
    which = 0; // 显示全部专利
    $('#PatentTable').bootstrapTable('destroy');
    allPatentList();
});

function allPatentList() {
    $('#PatentTable').bootstrapTable({
        url: '/api/patent/all',
        sidePagination: "server",
        flat: true,
        columns: [{
            radio: true
        }, {
            field: 'id',
            title: 'id',
            sortable: true,
            visible: false
        }, {
            field: 'name',
            title: '专利名称',
            sortable: true
        },
            //    {
            //    field: 'state',
            //    title: '专利类型',
            //    sortable: true
            //},
            {
                field: 'argMap.Main-ActorName',
                title: '负责人'
            }, {
                field: 'process',
                title: '流程状态',
                sortable: true,
                formatter: 'processTran'
            }]
    });
}

function approvePatentList(){
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
        },
            //    {
            //    field:'state',
            //    title:'专利状态',
            //    sortable:true
            //},
        {
            field:'ActorList',
            title:'参与者',
            sortable:true,
            formatter: 'actorTran'
        },{
            field:'Status',
            title:'状态',
            sortable:true,
            formatter:'statusTran'
        }],
        responseHandler: tableTrans
    });
}

//监听 点击新建
$("#add").click(function () {
    window.location.href = '/patent/new';
});



$('#PatentTable').on('click-row.bs.table', function (e, row, $element) {
    var orderId = row["id"];
    if (which == 0) {
        window.location.href = '/patent/' + orderId;
    } else {
        window.location.href = '/order/' + orderId;
    }
});


function processTran(arg) {
    var t = {
        "0": "未启动",
        "1": "流程中",
        "9": "已结束"
    };
    return t[arg];
}
