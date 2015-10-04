/**
 * Created by Administrator on 2015/10/3.
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
                footerFormatter: "totalMarksFormatter"
            }, {
                field: 'unit',
                title: '归属单位',
                sortable: true
            }],
        data: actorTemp
    });
    $('#unitTable').bootstrapTable({
        columns: [{
            field:'rank',
            title:'排名',
            editable:false,
            sortable:true,
            footerFormatter:"totalUnitFormatter"
        },{
            field:'unit',
            title:'单位名称',
            editable:false,
            sortable:true
        }],
        data:unitTemp
    });
    $('#reply').show();

    uneditableForm();

    $('#upload').hide();
});

function approve(){
    var approveInfo = Object();
    approveInfo["DecByCol"]=true;
    approveInfo["replyByCol"]=$('#reply-box').val();
    workflow.execute('col',$('#WF_Task').val(),approveInfo).success(function(){
        window.location.href="/patent";
    });
}

function refuse(){
    var refuseAwardInfo = Object();
    refuseAwardInfo["DecByCol"]=false;
    refuseAwardInfo["replyByCol"]=$('#reply-box').val();
    workflow.execute('col',$('#WF_Task').val(),refuseAwardInfo).success(function(){
        window.location.href="/patent";
    });
}