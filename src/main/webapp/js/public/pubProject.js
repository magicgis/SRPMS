/**
 * Created by yxm on 2015/4/16.
 */
/**
 * 1表示项目类别，2表示项目等级，3表示项目属性
 * 4表示项目评分来源，5表示项目评分归属，6表示获得立项
 * 6表示是否获奖
 * @type {Array}
 */
var Main_Actor;
var Main_ActorName;
var allSelections =
    [
        {"1": [{"type": "自然科学"}, {"type": "哲学与社会科学"}, {"type": "教育教学改革"}, {"type": ""}, {"type": ""}]},
        {"2": [{"rank": "国家级"}, {"rank": "省部级"}, {"rank": ""}, {"rank": ""}, {"rank": ""}]},
        {"3": [{"attr": "子课题"}, {"attr": "联合项目"}, {"attr": "独立项目"}, {"attr": ""}, {"attr": ""}]},
        {"4": [{"rateUnit": "国家科技部"}, {"rateUnit": "国家自然科学基金委员会"}, {"rateUnit": "国家中医药管理局"}, {"rateUnit": "教育部"}, {"rateUnit": "其他部委"}]},
        {"5": [{"rateSrc": "“973”计划A类资助"}, {"rateSrc": "“973”计划B类资助"}, {"rateSrc": "“973”计划C类资助"}, {"rateSrc": "“863”计划"}, {"rateSrc": "国家重大科技专项"}]},
        {"6": [{"isAppr": "是"}, {"isAppr": "是"}]},
        {"7": [{"isAwdProj": "是"}, {"isAwdProj": "否"}]}
    ];
//项目表单中的复选框
var optionsMenu = {
    '1': 'type',
    '2': 'rank',
    '3': 'attr',
    '4': 'rateUnit',
    '5': 'rateSrc',
    '6': 'is_appr',
    '7': 'isAwdProj'
};
var $actorTable = $('#actorTable');
var actorTemp = [];
var unitTemp = [];
var fundTemp = [];
// 将对话框里的值加载进成员表
function subActorInfo(index, flag) {
    var id = $('#actor').val();
    var actor = $('#actor').text();
    var marks = $('#marks').val();
    var units = $('#units').val();
    var role = $('#role').val();
    var rank = $('#rank').val();
    var mark = (marks == "" ? '0' : (marks / units.length).toFixed(2));
    actorTemp = getActorsData();
    $.each(units, function (i, value) {
        actorTemp.push({"staff.id": id, "rank": rank, "staff.name": actor, "role": role, "score": mark, "unit": value});
    });
    if(rank == '1' || rank == 1){
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
    return '共' + total.toFixed(0) + "分";
}

// 将对话框里的值加载进单位表
function subUnitInfo(index) {
    var unit = $('#unit').val();
    var rank = $('#rank').val();
    //console.log(rank);
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
    var to_acct_time = $('#time').val();
    var to_acct_mny = $('#mny').val();
    var out_mny = $('#outMny').val();
    fundTemp.push({"to_acct_time": to_acct_time, "to_acct_mny": to_acct_mny, "out_mny": out_mny});
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
        total += +(row['to_acct_mny'].substring(0));
    });
    return '到账共:' + total + "万元";
}
//外拨金额
function totalEFundFormatter(data) {
    var total = 0;
    $.each(data, function (i, row) {
        total += +(row['out_mny'].substring(0));
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
////显示详情
//function showForm() {
//    $('#projTable-box').addClass('collapsed');
//    $('#proj-box').removeClass('collapsed');
//}
////显示总览
//function showTable() {
//    clearSelect();
//    clearOptionsSelectize($("#dept").selectize());
//    $('#ProjectTable').bootstrapTable('refresh', {silent: true});
//    $('#projTable-box').removeClass('collapsed');
//    $('#proj-box').addClass('collapsed');
//}
////可编辑
//function editableForm() {
//    $('form input').removeAttr("disabled", "disabled");
//    $('form select').removeAttr("disabled", "disabled");
//    $('#fundTable').bootstrapTable('showColumn', 'operate');
//    $('#actorTable').bootstrapTable('showColumn', 'operate');
//    $('#unitTable').bootstrapTable('showColumn', 'operate');
//}
////不可编辑
//function uneditableForm() {
//    $('form input').attr("disabled", "disabled");
//    $('form select').attr("disabled", "disabled");
//    $('#addActor').attr("disabled", "disabled");
//    $('#addUnit').attr("disabled", "disabled");
//    $('#addFund').attr("disabled", "disabled");
//    $('#fundTable').bootstrapTable('hideColumn', 'operate');
//    $('#actorTable').bootstrapTable('hideColumn', 'operate');
//    $('#unitTable').bootstrapTable('hideColumn', 'operate');
//}
function view(index, row, value) {
    {
        return [
            '<i class="ace-icon fa fa-pencil bigger-130 editActor"></i>'
        ].join('');
    }
}
/**************************表单中的复选框**********************/
//复选框 数值与选择
function allSections() {
    $.each(optionsMenu, function (key, value) {
        selectUniversal(value, key);
    });
    //selectUniversal("rank",2);
    $('#dept').selectize({
        valueField: 'id',
        labelField: 'value',
        delimiter: ',',
        persist: false,
        options: [],
        create: false,
        preload: true,
        // maxItems: 1,
        load: function (query, callback) {
            $.ajax({
                url: '../api/baseinfo/部门',
                type: 'GET',
                dataType: 'json',
                error: function () {
                    callback();
                },
                success: function (res) {
                    callback(res);
                }
            });
        },
        onChange: function (result) {
            $('#deptValue').val(this.getItem(result)["context"]["innerHTML"]);
        }
    });
}
function selectUniversal(Ids, type) {
    var num = parseInt(type);
    $('#' + Ids).selectize({
        valueField: Ids,
        labelField: Ids,
        options: allSelections[num - 1][type],
        create: false,
        maxItems: 1
    });
}
//复选框能用
function selectEnable(flag) {
    $.each(optionsMenu, function (key, value) {
        if (flag == 1) {
            $('#' + optionsMenu[key]).selectize()[0].selectize.enable();
        } else if (flag == 0) {
            $('#' + optionsMenu[key]).selectize()[0].selectize.disable();
        }
    });
}
//清除复选框的内容
function clearSelect() {
    $.each(optionsMenu, function (key, value) {
        DisplayForm($('#' + value).selectize(), '', 0);
    });
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
    if ($('#attr').val() == '联合项目') {
        $('#unitInfo').show();
    } else {
        $('#unitInfo').hide();
    }
}
/**********************************************************************************************************************/
/******************************************************修改分界线*******************************************************/
/******************************************************修改分界线*******************************************************/
/**********************************************************************************************************************/
////启用或禁用
//function disableOrEnable(index, row, value) {
//    //测试区
//    if (row['user.status'] == 1) {
//        return [
//            '<label><input class="UserEnableChange ace ace-switch ace-switch-7" type="checkbox" ' +
//            'checked name="switch-field-1"><span class="lbl"></span></label>'
//        ].join('');
//    } else {
//        return [
//            '<label><input class="UserDisableChange ace ace-switch ace-switch-7" type="checkbox" ' +
//            'name="switch-field-1"><span class="lbl"></span></label>'
//        ].join('');
//    }
//}
////启用或禁用 操作
//window.operateEvents = {
//    //测试
//    'change .UserEnableChange': function (e, value, row, index) {
//        var ableType = 'disable';
//        var TipInfo = '已禁用';
//        disableOrEnableUser(row['id'], ableType, TipInfo);
//    },
//    'change .UserDisableChange': function (e, value, row, index) {
//        $('#UserTable').bootstrapTable('resetView', {'clickToSelect': false});
//        var ableType = 'enable';
//        var TipInfo = '已起用';
//        disableOrEnableUser(row['id'], ableType, TipInfo);
//    }
//};
//function disableOrEnableUser(id, ableType, TipInfo) {
//    //alert("请选择项目负责人");
//    $.ajax({
//        type: 'POST',
//        url: '../api/staff/' + ableType + '/' + id,
//        contentType: 'application/json;charset=UTF-8',
//        success: function (result) {
//            if (result.errmsg) {
//                //失败提示
//                failInfo('操作失败！');
//                return;
//            } else {
//                showTable();
//            }
//        }
//    });
//}
//function typeTran(value,row){
//    var type={
//        'philosophy':'自然科学',
//        'science':'哲学与社会科学',
//        'education':'教育教学改革'
//    };
//    return type[value];
//}
//
//function rankTran(value,row){
//    var rank = {
//        'nation':'国家级',
//        'province':'省部级'
//    };
//    return rank[value];
//
//}
//




