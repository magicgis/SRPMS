var actorTemp =[{"actor":"",
    "marks":"0"  }];

var unitTemp =[{"unitName":"",
    "rank":""  }];

$(function () {
    $('#tAppraiseTable').bootstrapTable({
        url: '/api/workf low/order/col/appraise/all',
        sidePagination:"server",
        columns: [{
            radio:true
        },{
            field: 'id',
            title: 'id',
            sortable: true,
            visible:false
        },{
            field:'S-ACTOR',
            title:'提交者',
            sortable:true
        },{
            field:'achName',
            title:'成果名称',
            sortable:true
        },{
            field:'achType',
            title:'成果类别',
            sortable:true,
            formatter:"typeTran"
        },{
            field:'checkName',
            title:'鉴定单位',
            sortable:true
        },{
            field:'WF_Actor',
            title:'参与人',
            sortable:true
        },{
            field:'appraiseDate',
            title:'鉴定日期',
            sortable:true
        },{
            field:'Status',
            title:'状态',
            sortable:true,
            formatter:'statusTran'
        }],
        responseHandler: tableTrans
    });

    $('#actorTable').bootstrapTable({
        columns: [{
            field:'actor',
            title:'成员',
            editable:true,
            sortable:true
           // footerFormatter:"totalNameFormatter"
        },{
            field:'marks',
            title:'分数',
            editable:true,
            sortable:true
          //  footerFormatter:"totalMarksFormatter"
        }],
        data:actorTemp
    });

    $('#unitTable').bootstrapTable({
        columns: [{
            field:'unitName',
            title:'单位名称',
            editable:true,
            sortable:true
          //  footerFormatter:"totalNameFormattera"
        },{
            field:'unitRank',
            title:'单位排名',
            editable:true,
            sortable:true
            // footerFormatter:"totalNameFormatter"
        }],
        data:unitTemp
    });
    $('#reply-box').show();
    $('#reply').show();
    uneditableForm();
});

//监听 点击table
$('#tAppraiseTable').on('click-row.bs.table', function (e, row, $element) {
    $('form input').val(null);
    actorTemp =[{"actor":"",
        "marks":"0"  }];
    var orderId = row["id"];
    var status = row["Status"];
    uneditableForm();
    if(status == 'WaitForCol') {
        workflow.latestTask(orderId).success(function (currentTask) {
            var taskId = currentTask[0]['id'];
            $('#WF_Task').val(taskId);
        });
        $("#Approve").removeAttr("disabled","disabled");
        $("#Refuse").removeAttr("disabled","disabled");
    }else{
        $("#Approve").attr("disabled","disabled");
        $("#Refuse").attr("disabled","disabled");
    }
    if(row['actors']!=null) {
        actorTemp = row['actors'];
    }
    $('#appraise').autofill(row, {
        findbyname: true,
        restrict: false
    });
    unitTableShow();
    $("#actorTable").bootstrapTable('load',actorTemp);
    $("#unitTable").bootstrapTable('load',unitTemp);
    $('#appraiseTable-box').addClass('collapsed');
    $('#appraise-box').removeClass('collapsed');
});

//监听 点击返回
$("#back").click(function(){
    showTable();
});

$("#Approve").click(function(){
    approve();
});
//
$("#Refuse").click(function(){
    refuse();
});

$("#next").click(function(){
    console.log($('#tPaperTable').bootstrapTable('getSelections'));
});

$("#previous").click(function(){

})


/function approve() {
//    var req = Object();
//    req["DecByCol"]=true;
//    req["replyByCol"]=$('#reply-box').val();
//    workflow.execute('col',$('#WF_Task').val(),req).success(function(){
//        showTable();
    //   })
}
//
function refuse(){
//    var req = Object();
//    req["DecByCol"]=false;
//    req["replyByCol"]=$('#reply-box').val();
//    workflow.execute('col',$('#WF_Task').val(),req).success(function(){
//        showTable();
//    });
}