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
