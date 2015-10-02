/**
 * Created by zheng on 2015/4/19.
 */
var actorTemp =[];//actorTemp
var unitTemp =[];//unitTemp
$(function () {
    showForm();
    $('#NewInstrumentsTable').bootstrapTable({
        url: '/api/workflow/order/col/newInstruments/all',
        sidePagination:"server",
        columns: [{
            radio:true,
            visible:false
        },{
            field: 'id',
            title: 'id',
            sortable: true,
            visible:false
        },{
            field:'apparName',
            title:'医疗器械名称',
            sortable:true
        },{
            field:'S-ACTOR',
            title:'创造者',
            sortable:true,
            formatter:"typeTran"
        },{
            field:'apparStaScore',
            title:'得分',
            sortable:true
        },{
            field:'newInstrumentsDate',
            title:'日期',
            sortable:true
        },{
            field:'Status',
            title:'状态',
            sortable:true,
            formatter:'statusTran'
        }],
        responseHandler:tableTrans
    });
    $('#actorTable').bootstrapTable({
        columns: [{
            field:'actor',
            title:'成员',
            editable:true,
            sortable:true,
            footerFormatter:"totalNameFormatter"
        },{
            field:'staId',
            title:'工号',
            editable:true,
            sortable:true
        },{
            field:'role',
            title:'角色',
            editable:true,
            sortable:true
            //footerFormatter:"totalNameFormatter"
        },{
            field:'marks',
            title:'分数',
            editable:true,
            sortable:true,
            footerFormatter:"totalMarksFormatter"
            //footerFormatter:"totalMarksFormatter"
        }],
        data:actorTemp
    });
    $('#unitTable').bootstrapTable({
        columns: [{
            field: 'unit_id',
            title: 'id',
            sortable: true,
            visible:false,
            footerFormatter:"totalUnitFormatter"
        },{
            field:'unit_rank',
            title:'单位排名',
            editable:true,
            sortable:true
        },{
            field:'unit_name',
            title:'单位名称',
            editable:true,
            sortable:true
        }],
        data:unitTemp
    });
    $('#instrUnitInfo').show();
    $('#reply-box').show();
    $('#reply').show();
    //$('#actorInstrumentsToolbar').hide();
    //$('#apparUnitToolbar').hide();
    uneditableForm();
});

//监听 点击论文表
$('#NewInstrumentsTable').on('click-row.bs.table', function (e, row, $element) {
    $('form input').val(null);
    actorTemp =[{ "actor":"","staId":"","role":"","marks":"0"}];
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
    $('#instruments').autofill(row, {
        findbyname: true,
        restrict: false
    });
    if(row['isFirInInstru']=="false"){
        if(row['units']!=null){
            unitTemp=row['units'];
        }
        $('#instrUnitInfo').show();
        $('#unitTable').bootstrapTable("load",unitTemp);
    }
    $("#actorTable").bootstrapTable('load',actorTemp);
    showForm();
});
//监听 点击返回
$("#back").click(function(){
});

$("#Approve").click(function(){
});

$("#Refuse").click(function(){
});

$("#next").click(function(){
    // console.log($('#tNewFoodTable').bootstrapTable('getSelections'));
});

$("#previous").click(function(){
});