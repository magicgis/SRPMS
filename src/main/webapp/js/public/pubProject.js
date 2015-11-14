/**
 * Created by yxm on 2015/4/16.
 */
/**
 * 1表示项目类别，2表示项目等级，3表示项目属性
 * 4表示项目评分来源，5表示项目评分归属，6表示获得立项
 * 6表示是否获奖
 * @type {Array}
 */
// 将对话框里的值加载进成员表
function subActorInfo(index, flag) {
    var id = $('#actor').val();
    var actor = $('#actor').text();
    var marks = $('#marks').val();
    var units = $('#units').val();
    var role = $('#role').val();
    var rank = $('#rank').val();
    //var mark = (marks == "" ? '0' : (marks / units.length).toFixed(2));
    actorTemp = getActorsData();
    $.each(units, function (i, value) {
        actorTemp.push({
            "staff.id": id,
            "rank": rank,
            "staff.name": actor,
            "role": role,
            "score": marks,
            "unit": value
        });
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
window.operateAEvents = {
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
function operateAFormatter(value, row, index) {
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
    return '共' + total.toFixed(0) + "分";
}

// 将对话框里的值加载进单位表
function subUnitInfo(index) {
    var unit = $('#unit').val();
    var rank = $('#rank').val();
    //console.log(aRank);
    unitTemp.push({"rank": rank, "unit": unit});
    if (index == null) {  // 增加一行
        $('#unitTable').bootstrapTable("load", unitTemp);
    } else {    // 替换一行
        unitTemp.remove(index);
        $('#unitTable').bootstrapTable('load', unitTemp);
    }
    $('#unitTable').bootstrapTable('load', unitTemp);
}

window.operateEventsUnit = {
    'click .removeUnit': function (e, value, row, index) {
        $('#unitTable').bootstrapTable('remove', {
            field: 'unit',
            values: [row["unit"]]
        });
    },
    'click .editUnit': function (e, value, row, index) {
        editUnit(row, index);
    }
};
// 操作
function operateFormatterUnit(value, row, index) {
    return [
        '<a class="editUnit" href="javascript:void(0)" title="edit" >',
        '<i class="ace-icon fa fa-pencil bigger-110"></i>',
        '</a>&nbsp;&nbsp;&nbsp;',
        '<a class="removeUnit" href="javascript:void(0)" title="Remove" >',
        '<i class="glyphicon glyphicon-remove"></i>',
        '</a>'
    ].join('');
}
// 总个数
function totalUnitFormatter(data) {
    return "共" + data.length + "个";
}
function subFundInfo(index) {
    var Time = $('#time').val();
    var Mny = $('#mny').val();
    var OutMny = $('#outMny').val();
    fundTemp.push({"time": Time, "mny": Mny, "outMny": OutMny});
    if (index == null) {  // 增加一行
        $('#fundTable').bootstrapTable("load", fundTemp);
    } else {    // 替换一行
        fundTemp.remove(index);
        $('#fundTable').bootstrapTable('load', fundTemp);
    }
    $('#fundTable').bootstrapTable('load', fundTemp);
}
window.operateFEvents = {
    'click .removeFund': function (e, value, row, index) {
        $('#fundTable').bootstrapTable('remove', {
            field: 'to_acct_time',
            values: [row["to_acct_time"]]
        });
    },
    'click .editFund': function (e, value, row, index) {
        editFund(row, index);
    }
};

function operateFFormatter(value, row, index) {
    return [
        '<a class="editFund" href="javascript:void(0)" title="edit" >',
        '<i class="ace-icon fa fa-pencil bigger-110"></i>',
        '</a>&nbsp;&nbsp;&nbsp;',
        '<a class="removeFund" href="javascript:void(0)" title="Remove" >',
        '<i class="glyphicon glyphicon-remove"></i>',
        '</a>'
    ].join('');
}


//到账表
function totalFundsFormatter(data) {
    var total = 0;
    $.each(data, function (i, row) {
        total += +(row['mny'].substring(0));
    });
    return '到账共:' + total + "万元";
}
//外拨金额
function totalEFundFormatter(data) {
    var total = 0;
    $.each(data, function (i, row) {
        total += +(row['outMny'].substring(0));
    });
    return '外拨共:' + total + "万元";
}
// 成员翻译
function actorTran(value, row) {
    if (value != undefined && value != null && value != "")
        return value.substring(0, value.length - 1);
    else
        return;
}
/*********************表单||表格的动作和行为****************/
//表单不可编辑
//function uneditableForm(){
//    $('form input').attr("disabled", "disabled");
//    $('form select').attr("disabled", "disabled");
//    $('.delFiles').hide();
//}
function view(index, row, value) {
    {
        return [
            '<i class="ace-icon fa fa-pencil bigger-130 editActor"></i>'
        ].join('');
    }
}
/**************************表单中的复选框**********************/

function allSections(){
    $('#attr').selectize({
        valueField: 'value',
        labelField: 'value',
        options: [{"id":"1","value": "子课题"},
            {"id":"2","value": "联合项目"},
            {"id":"3","value":"独立项目"}],
        create: false,
        maxItems: 1
    });
    $('#isAppr').change(function(){
        var setProject=$('#isAppr').val();
        if(setProject=='1'){
            var projectSet="项目立项";
            //getStdList(projectSet);
        }else if(setProject=='0'){
            var projectSet="项目未获立项";
            //getStdList(projectSet);
        }
    });
    $(".projStand").focus(function(){
        if($('#isAppr').val()==""){
            messageModal("请选择项目是否获得立项！")
        }
    });
}
function clearSelect() {
    clearOptionsSelectize($("#dept").selectize());
}

/**************************获取表格数据************************/
function getActorsData() {
    var actorTemp = $("#actorTable").bootstrapTable('getData');
    $.each(actorTemp, function (index, value) {
        delete value[0];
    });
    return actorTemp;
}
//获取参加单位信息
function getUnitsData() {
    var unitTemp = $("#unitTable").bootstrapTable('getData');
    return unitTemp;
}
//获取资金到账信息
function getFundsData() {
    var fundTemp = $("#fundTable").bootstrapTable('getData');
    return fundTemp;
}
/*************************项目属性*********************************/
function firstOrOther() {
    if ($('#attr').val() == '独立项目') {
        $('#unitInfo').hide();
    } else {
        $('#unitInfo').show();
    }
}
/**************************************************************/
function fullUpInfo(all, entity) {
        getActors();
        filesData = all['filesData'];
        unitTemp = all['units'];
        fundTemp = all['fund'];
        Main_Actor = all['Main-Actor'];
        Main_ActorName = all['Main-ActorName'];
        replyByCol = all['replyByCol'];
        replyByDep = all['replyByDep'];
        showFiles(filesData);
        $("#fundTable").bootstrapTable('load', fundTemp);
        $("#actorTable").bootstrapTable('load', actorTemp);
        if (entity['attr'] == "联合项目" || entity['attr'] == "子课题") {
            if (all['units'] != null) {
                unitTemp = all['units'];
            }
            $('#unitTable').bootstrapTable("load", unitTemp);
            $('#unitInfo').show();
        } else {
            $('#unitInfo').hide();
        }
}

function standardSelects1(StdList,projtypeList,standard){
    //initSelect();
    $('#projbelong').attr("enable", "enable");
    $('#projrank').attr("enable", "enable");
    var $projbelong = $("#projbelong").selectize({ // 初始化 鉴定等级
        valueField: 'id',
        labelField: 'value',
        maxItems: 1
    });

    var $projorig = $("#projorig").selectize({ // 初始化 鉴定等级
        valueField: 'value',
        labelField: 'value',
        maxItems: 1,
        onChange: function (result) {
            var projbelongList = [];
            projbelongList = getStandardList2(StdList, 'projtype', $('#projtype').val(),
                'projrank',$('#projrank').val(), "projorig", $('#projorig').val(), "projbelong")
            $projbelong[0].selectize.clearOptions();
            $projbelong[0].selectize.addOption(projbelongList);

        }
    });

    var $projrank = $("#projrank").selectize({ // 初始化 鉴定等级
        valueField: 'value',
        labelField: 'value',
        maxItems: 1,
        onChange: function (result) {
            var projorigList = [];
            projorigList = getStandardList1(StdList, 'projtype', $('#projtype').val(), 'projrank',$('#projrank').val(), "projorig");
            // console.log(projorigList);
            $projorig[0].selectize.clearOptions();
            $projorig[0].selectize.addOption(projorigList);
        }
    });

    var $projtype = $("#projtype").selectize({ // 初始化 projtype
        valueFieled: 'value',
        labelField: 'value',
        options: projtypeList,
        maxItems: 1,
        onChange: function (result) { // onChange时间 绑定级联
            // console.log(result);
            var proRankList = [];
            proRankList = getStandardList(StdList, 'projtype', 'projrank', result);
            //console.log(proRankList);
            $projrank[0].selectize.clearOptions();
            $projrank[0].selectize.addOption(proRankList);
        }
    });
   if(!isNull(standard)){
       DisplayForm($projtype, standard['infoMap']['projtype'], 0);
       DisplayForm($projrank, standard['infoMap']['projrank'], 0);
       DisplayForm($projorig, standard['infoMap']['projorig'], 0);
       DisplayForm($projbelong, standard['id'], 0);
   }
}

function standardSelects0(StdList,standard){
    var projtypes=[];
    $.each(StdList, function (index, obj) {
        var temp = {};
        temp['id'] = obj['id'];
        temp['value'] = obj['infoMap']['projtype'];
        projtypes.push(temp);
    });
    //console.log(projtypes);
    $("#projbelong").val('');
    $("#projorig0").val('');
    var $projtype = $("#projtype0").selectize({ // 初始化 projtype
        valueField: 'id',
        labelField: 'value',
        options: projtypes,
        maxItems: 1,
        onChange: function (result) { // onChange时间 绑定级联
            $.each(StdList, function (index, obj) {
                if(obj['id']==$("#projtype0").val()){
                    var projorigTemp = obj['infoMap']['projorig'];
                    $("#projorig0").val(projorigTemp);
                }
            });
        }
    });
    $('#projrank0').attr("disabled", "disabled");
    if(!isNull(standard)){
        DisplayForm($projtype,standard['id'], 0);
        $("#projorig0").val(standard['infoMap']['projorig']);
    }
}