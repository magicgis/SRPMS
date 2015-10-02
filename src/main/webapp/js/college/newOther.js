/**
 * Created by zheng on 2015/4/19.
 */
var actorTemps =[];
var otherUnitTemps =[];
$(function () {
    showForm();
    $('#NewOtherTable').bootstrapTable({
        url:'',
        columns: [{
            radio:true
            //visible:false
        },{
            field: 'id',
            title: 'id',
            sortable: true,
            visible:false
        },{
            field:'otherName',
            title:'产品名称',
            sortable:true
        },{
            field:'S-ACTOR',
            title:'提交者',
            sortable:true
        },{
            field:'otherStaScore',
            title:'得分',
            sortable:true
        },{
            field:'newOtherDate',
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
            field:'staName',
            title:'姓名',
            editable:true,
            sortable:true
            // footerFormatter:"totalNameFormatter"
        },{
            field:'staId',
            title:'工号',
            editable:true,
            sortable:true
            // footerFormatter:"totalMarksFormatter"
        },{
            field:'otherStaRole',
            title:'角色',
            editable:true,
            sortable:true
            //footerFormatter:"totalMarksFormatter"
        },{
            field:'otherStaScore',
            title:'分数',
            editable:true,
            sortable:true
            // footerFormatter:"totalMarksFormatter"
        }],
        data:actorTemps
    });
    $('#unitTable').bootstrapTable({
        columns: [{
            field:'otherUnit',
            title:'单位名称',
            editable:true,
            sortable:true
            // footerFormatter:"totalNameFormatter"
        },{
            field:'otherUnitRank',
            title:'单位排名',
            editable:true,
            sortable:true
            //    footerFormatter:"totalMarksFormatter"
        }],
        data:otherUnitTemps
    });
    $('#otherUnitInfo').hide();
    //$('#reply-box').hide();
    //$('#reply').hide();
    $('#actorOthersToolbar').hide();
    $('#otherUnitToolbar').hide();
    $('#reply-box').show();
    $('#reply').show();
    uneditableForm();
});
//点几行监听
$('#NewOtherTable').on('click-row.bs.table', function (e, row, $element) {

})
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


function approve(){
    //var jsonData = Object();
    //jsonData["DecByCol"]=true;
    //jsonData["WF_User"]="col";
    //jsonData["WF_Task"]=$('#WF_Task').val();
    //jsonData["replyByCol"]=$('#reply-box').val();
    //pubapprove(jsonData);
}

function refuse(){
    //var jsonData = Object();
    //jsonData["DecByCol"]=false;
    //jsonData["WF_User"]="col";
    //jsonData["WF_Task"]=$('#WF_Task').val();
    //jsonData["replyByCol"]=$('#reply-box').val();
    //pubrefuse(jsonData);
}