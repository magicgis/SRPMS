/**
 * Created by zheng on 2015/4/19.
 */
var actorTemp =[];
var unitTemp =[];
$(function () {
    showForm();
    $('#NewInstrumentsTable').bootstrapTable({
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
            field:'apparName',
            title:'医疗器械名称',
            sortable:true
        },{
            field:'S-ACTOR',
            title:'创造者',
            sortable:true,
            formatter:"typeTran"
        },{
            field:'deptName',
            title:'所属部门',
            sortable:true
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
        responseHandler: tableTrans
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
            field:'apparStaRole',
            title:'角色',
            editable:true,
            sortable:true
            //footerFormatter:"totalMarksFormatter"
        },{
            field:'apparStaScore',
            title:'分数',
            editable:true,
            sortable:true
            // footerFormatter:"totalMarksFormatter"
        }],
        data:actorTemp
    });
    $('#unitTable').bootstrapTable({
        columns: [{
            field:'apparUnit',
            title:'单位名称',
            editable:true,
            sortable:true
            // footerFormatter:"totalNameFormatter"
        },{
            field:'apparUnitRank',
            title:'单位排名',
            //editable:true,
            //sortable:true
            //    footerFormatter:"totalMarksFormatter"
        }],
        data:unitTemp
    });
    $('#instrUnitInfo').hide();
    //$('#reply-box').hide();
    $('#reply').hide();
    $('#actorInstrumentsToolbar').hide();
    $('#apparUnitToolbar').hide();
});

//监听 点击表
$('#NewInstrumentsTable').on('click-row.bs.table', function (e, row, $element) {

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