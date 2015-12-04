/**
 * Created by zheng on 2015/6/8.
 */
/**
 * 不同项目名称所对应得表单
 * */
var selectDefferent = {
    "project": "../public/projectForm.jsp",
    "patent": "",
    "paper": "../public/paperForm.jsp",
    "newOther": "",
    "newMedicine": "",
    "instrument": "",
    "food": "",
    "change": "",
    "award": "",
    "appraise": ""
};
// 论文的类型翻译
function typeTran(value, row) {
    if (value == 'conferPaper') {
        return '会议论文';
    } else if (value == 'magPaper') {
        return '期刊论文';
    } else if (value == 'newsPaper') {
        return '报刊论文';
    } else {
        return;
    }
}
function mainActorTran(value, row){
    console.log(row);
    var main_Actor=row['WF_Latest']['actors'][0]['staff.name'];
    if(!isNull(main_Actor)){
        return main_Actor;
    }else{
        return '--';
    }
}

/**
 *查看/编辑样式
 *
 * */
function view(index, row, value) {
    {
        return [
            '<a class="edit ml10" href="javascript:void(0)" title="Edit">',
            '<i class="ace-icon fa fa-pencil bigger-130"></i>查看',
            '</a>'
        ].join('');
    }
}

// 成员翻译
function actorTran(value,row){
    if(value!=undefined && value!=null && value!="")
        return value.substring(0,value.length-1);
    else
        return;
}

//论文类型
function paperType() {
    if ($('#paperType').val() == "conferPaper") {
        $('#paperType').val("会议论文")
    } else if ($('#paperType').val() == "magPaper") {
        $('#paperType').val("期刊论文");
    } else if ($('#paperType').val() == "newsPaper") {
        $('#paperType').val("报刊论文")
    } else {

    }
}
/*
 * 显示表单时的类型翻译
 * @param收录类型||论文类型
 * */
function DisplayForm($type, ItemValue) {
    if (ItemValue == null || ItemValue == "") {
        $type[0].selectize.setValue("");
    } else {
        $type[0].selectize.setValue(ItemValue);
    }
}

function tableTranMags(res) {
    var ans = res;
    $.each(res["rows"],function(index,value){
        /*取出variableMap*/
        var maps=value['variableMap'];
        /*把string放入*/
        for(var key in maps) {
            if (typeof(maps[key]) == 'string'||typeof(maps[key]) == 'object') {
                ans["rows"][index][key] = maps[key];
            }
        }
        /*拿出最新的args*/
        var temp=maps[getSubmission(maps)];
        /*瞎写都能生效……*/
        if(temp!=undefined||temp!=null){
            $.each(temp,function(key,value){
                ans["rows"][index][key]=temp[key];
            });
        }
    });
    //console.log(ans);
    return ans;
}
