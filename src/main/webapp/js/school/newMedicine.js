/**
 * Created by zheng on 2015/4/19.
 */
var actorTemp =[];
var unitTemp =[];
$(function () {
    showForm();
    $('#NewMedicineTable').bootstrapTable({
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
            field:'medName',
            title:'新药品名称',
            sortable:true
        },{
            field:'S-ACTOR',
            title:'提交者',
            sortable:true
        },{
            field:'medStaScore',
            title:'得分',
            sortable:true
        },{
            field:'newMedicineDate',
            title:'日期',
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
    $('#medunitInfo').hide();
    //$('#reply-box').hide();
    $('#reply').hide();
    $('#actorNewMedicineToolbar').hide();
    $('#medunitToolbar').hide();
    uneditableForm();
});
//点几行监听
$('#NewMedicineTable').on('click-row.bs.table', function (e, row, $element) {

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