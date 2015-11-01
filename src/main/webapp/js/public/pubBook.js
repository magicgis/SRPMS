/**
 * Created by zheng on 2015/4/24.
 */
var actorTemp = [];
var Main_Actor;
var Main_ActorName;
var replyByCol, replyByDep;
var $actorTable = $('#actorTable');

// 将对话框里的值加载进成员表
function subActorInfo(index, flag) {
    var id = $('#actor').val();
    var actor = $('#actor').text();
    var marks = $('#marks').val();
    var units = $('#units').val();
    var role = $('#role').val();
    var rank = $('#rank').val();
    var textNumber = $('#textNumber').val();
    //var mark = (marks == "" ? '0' : (marks / units.length).toFixed(2));
    //测试专用
    //scoreAllocation(marks,index);
    actorTemp = getActorsData();
    $.each(units, function (i, value) {
        actorTemp.push({"staff.id": id, "rank": rank, "staff.name": actor,
            "role": role, "score": marks, "unit": value,"textNumber":textNumber});
    });
    if (rank == '1' || rank == 1) {
        Main_Actor = id;
        Main_ActorName = actor;
    }
    if (flag) {  // 增加一行
        $('#actorTable').bootstrapTable("load", actorTemp);
    } else {    // 替换一行
        actorTemp.remove(index);
        $('#actorTable').bootstrapTable('load', actorTemp);
    }
}
// 移除和编辑成员
window.operateEvents = {
    'click .removeActor': function (e, value, row, index) {
        $('#actorTable').bootstrapTable('remove', {
            field: 'staff.id',
            values: [row["staff.id"]]
        });
    },
    'click .editActor': function (e, value, row, index) {
        editActor(row, index);
    }
};
// 操作
function operateFormatter(value, row, index) {
    return [
        '<a class="editActor" href="javascript:void(0)" title="edit" >',
        '<i class="ace-icon fa fa-pencil bigger-110"></i>',
        '</a>&nbsp;&nbsp;&nbsp;',
        '<a class="removeActor" href="javascript:void(0)" title="Remove" >',
        '<i class="glyphicon glyphicon-remove"></i>',
        '</a>'
    ].join('');
}
// 总人数
function totalNameFormatter(data) {
    return "共" + data.length + "人";
}
// 总分
function totalMarksFormatter(data) {
    var total = 0;
    $.each(data, function (i, row) {
        if (row.score !== null && row.score !== undefined && row.score !== "") {
            total += +(row.score.toString().substring(0));
        }
    });
    //测试专用
    //var tempScore=$('#tempScore').val();
    var Score=50-total.toFixed(0);
    if(Score<=0){
        messageModal("分数分配错误！");
        return;
    }
    $('.testScore').empty();
    $('#tempScore').val(Score);
    $('.testScore').append('剩余'+Score+'分');
    return '共' + total.toFixed(0) + "分";
}
/**************************获取表格数据************************/
function getActorsData() {
    var actorTemp = $("#actorTable").bootstrapTable('getData');
    $.each(actorTemp, function (index, value) {
        delete value[0];
    });
    return actorTemp;
}
function getPubType(){
    $('#pubType').selectize({
        valueField: 'id',
        labelField: 'value',
        create: true,
        options: [
            {"id":"1020","value": "公开出版著作"},
            {"id":"1021","value": "教育部规划教材"},
            {"id":"1022","value": "协编教材"},
            {"id":"1023","value":"其他教材"}],
        maxItems: 1
    });
}
function fullUpInfo(all,entity){
    if (!isNull(all)) {
        getActors();
        filesData = all['filesData'];
        Main_Actor = all['Main-Actor'];
        Main_ActorName = all['Main-ActorName'];
        replyByCol = all['replyByCol'];
        replyByDep = all['replyByDep'];
        $("#actorTable").bootstrapTable('load', actorTemp);
        showFiles(filesData);
    }
}