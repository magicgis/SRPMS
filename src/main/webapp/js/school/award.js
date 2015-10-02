/**
 * Created by zheng on 2015/4/24.
 */
var actorTemp =[];
var unitTemp = [];
$(function () {
    $('#AwardTable').bootstrapTable({
        url: '/api/workflow/order/col/award/all',
        columns: [{
            radio: true
            //visible:false
        }, {
            field: 'id',
            title: 'id',
            sortable: true,
            visible: false
        }, {
            field: 'achName',
            title: '录入人',
            sortable: true
        }, {
            field: 'achType',
            title: '成果名称',
            sortable: true
            // formatter:"typeTran"
        }, {
            field: 'awardName',
            title: '类型',
            sortable: 'true'
        }, {
            field: 'awardRank',
            title: '获奖时间',
            sortable: true
        }, {
            field: 'Status',
            title: '状态',
            sortable: true,
            formatter: 'statusTran'
        }],
        responseHandler:tableTrans
    });

    $('#actorTable').bootstrapTable({
        columns: [{
            field:'actor',
            title:'姓名',
            editable:true,
            sortable:true,
            footerFormatter:"totalNameFormatter"
        },{
            field:'staId',
            title:'工号',
            editable:true,
            sortable:true
            //footerFormatter:"totalNameFormatter"
        },{
            field:'marks',
            title:'分数',
            editable:true,
            sortable:true,
            footerFormatter:"totalMarksFormatter"
        },{
            field:'role',
            title:'角色',
            editable:true,
            sortable:true
            //  footerFormatter:"totalNameFormatter"
        }],
        data:actorTemp
    });

    $('#unitTable').bootstrapTable({
        columns: [{
            field:'unitName',
            title:'单位名称',
            editable:true,
            sortable:true,
            footerFormatter:"totalUnitFormatter"
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
    $('#actorToolbar').hide();
    //$('#fundToolbar').hide();
    $('#unitToolbar').hide();
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