/**
 * Created by zheng on 2015/10/3.
 */
$(function(){
    $('#actorTable').bootstrapTable({
        columns: [
            {
                field: 'staff.id',
                title: '工号',
                sortable: true,
                visible: false
            }, {
                field: 'rank',
                title: '排名',
                sortable: true,
                //editable: true,
                footerFormatter: "totalNameFormatter"
            }, {
                field: 'staff.name',
                title: '成员',
                sortable: true
            }, {
                field: 'role',
                title: '角色',
                sortable: true
            }, {
                field: 'score',
                title: '分数',
                sortable: true,
                //editable: true,
                footerFormatter: "totalMarksFormatter"
            }, {
                field: 'unit',
                title: '归属单位',
                //editable: true,
                sortable: true
            }, {
                field: 'operate',
                title: '操作',
                sortable: true,
                formatter: "operateAFormatter",
                events: "operateAEvents"
            }],
        data: actorTemp
    });
    //一点小问题，应该马上就能解决
    $('#fundTable').bootstrapTable({
        columns: [{
            field: 'to_acct_time',
            title: '到账时间',
            //editable: true,
            sortable: true
        }, {
            field: 'to_acct_mny',
            title: '到账金额',
            //editable: true,
            sortable: true,
            footerFormatter: "totalFundsFormatter"
        }, {
            field: 'out_mny',
            title: '外拨金额',
            // editable: true,
            sortable: true,
            footerFormatter: "totalEFundFormatter"
        }, {
            field: 'operate',
            title: '操作',
            sortable: true,
            formatter: "operateFFormatter",
            events: "operateFEvents"
        }],
        data: fundTemp
    });
    $('#unitTable').bootstrapTable({
        columns: [{
            field: 'rank',
            title: '排名',
            sortable: true,
            footerFormatter: "totalUnitFormatter"
        }, {
            field: 'unit',
            title: '单位名称',
            sortable: true
        }, {
            field: 'operate',
            title: '操作',
            sortable: false,
            formatter: "operateFormatterUnit",
            events: "operateEventsUnit"
        }],
        data: unitTemp
    });
    $('#reply-box').show();
    $('#reply').show();
    $('#upload').hide();
    $('.addActor').hide();
    $('.getScore').hide();
    $('.addUnit').hide();
});

//监听 添加金额信息
$('.addFund').hide();
function Approve(){
    var approveInfo = Object();
    approveInfo["DecByCol"]=true;
    approveInfo["replyByCol"]=$('#reply-box').val();
    workflow.execute('col',$('#WF_Task').val(),approveInfo).success(function(){
        showTable();
    });
}
function Refuse(){
    var refuseAwardInfo = Object();
    refuseAwardInfo["DecByCol"]=false;
    refuseAwardInfo["replyByCol"]=$('#reply-box').val();
    workflow.execute('col',$('#WF_Task').val(),refuseAwardInfo).success(function(){
        showTable();
    });
}