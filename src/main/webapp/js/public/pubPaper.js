var $actorTable = $('#actorTable');

// 类型翻译
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
// 显示Mag、Confer还是newsPaper
function magOrConfer() {
    if ($('#type').val() == "conferPaper") {
        $('#conferInfo').show();
        $('#newsInfo').hide();
        $('#magInfo').hide();
        $('.paperWord').hide();
        $('.mag-input').val(null);
        $('.news-input').val(null);
    } else if ($('#type').val() == "magPaper") {
        $('#conferInfo').hide();
        $('#newsInfo').hide();
        $('#magInfo').show();
        $('.paperWord').hide();
        $('.confer-input').val(null);
        $('.news-input').val(null);
    } else if ($('#type').val() == "newsPaper") {
        $('#newsInfo').show();
        $('.paperWord').show();
        $('#conferInfo').hide();
        $('#magInfo').hide();
        $('.mag-input').val(null);
        $('.confer-input').val(null);
    } else {
        $('#newsInfo').hide();
        $('#conferInfo').hide();
        $('#magInfo').hide();
        $('.paperWord').hide();
        $('.mag-input').val(null);
        $('.confer-input').val(null);
        $('.news-input').val(null);
    }
}
function magOrConfer_add(){
    clearOptionsSelectize($("#magId").selectize());
    DisplayForm($("#newsType").selectize(), '', 0);
    DisplayForm($("#conferType").selectize(), '', 0);
}
// 表单不可编辑
function uneditableForm() {
    $('form input').attr("disabled", "disabled");
    $('form select').attr("disabled", "disabled");
    $('#addDiff').hide();
    $('#addActor').hide();
    $('#getScore').hide();
    $('.delFiles').hide();
    $('#actorTable').bootstrapTable('hideColumn', 'operate');
    $('.type').hide();
    $('#upload').hide();
}
// 表单可编辑
function editableForm() {
    $('form input').removeAttr("disabled", "disabled");
    $('form select').removeAttr("disabled", "disabled");
    $('#addDiff').show();
    $('#addActor').show();
    $('#getScore').show();
    $('.delFiles').show();
    $('#actorTable').bootstrapTable('showColumn', 'operate');
    $('.type').show();
}
// 显示总览
function showTable(newUrl, tableTypes) {
    $('#' + tableTypes).bootstrapTable('refresh', {url: newUrl});
    $('#paperTable-box').removeClass('collapsed');
    $('#paper-box').addClass('collapsed');
}
// 返回论文列表 教师
function showTableTeacher(url, tableTypes){
    showTable(url, tableTypes); // 回到table
    // 清空选择框
    DisplayForm($('#type').selectize(), '', 0);
    $("#showSum").html("");
    magOrConfer_add();
    // 清除计时
    clearClick();
}
// 显示详情
function showForm() {
    $('#paperTable-box').addClass('collapsed');
    $('#paper-box').removeClass('collapsed');
}
// 将对话框里的值加载进成员表
function subActorInfo(index,flag) {
    var id = $('#actor').val();
    var actor = $('#actor').text();
    var marks = $('#marks').val();
    var units = $('#units').val();
    var role = $('#role').val();
    var rank = $('#rank').val();
    var mark = (marks == "" ? '0.00' : (marks / units.length).toFixed(2));
    actorTemp = getActorsData();
    $.each(units, function (i, value) {
        actorTemp.push({"staff.id": id, "rank": rank, "staff.name": actor, "role": role, "score": mark, "unit": value});
    });
    if(flag) {  // 增加一行
        $('#actorTable').bootstrapTable("load", actorTemp);
    } else {    // 替换一行
        actorTemp.remove(index);
        $actorTable.bootstrapTable('load',actorTemp);
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
        if(row.score !== null && row.score !== undefined && row.score !=="") {
            total += +(row.score.toString().substring(0));
        }
    });
    return '共' + total.toFixed(0) + "分";
}

// 论文类型
function getPaperType() {
    $('#type').selectize({
        valueField: 'id',
        labelField: 'value',
        options: [
            {"id": "magPaper", "value": "期刊论文"},
            {"id": "conferPaper", "value": "会议论文"},
            {"id": "newsPaper", "value": "报刊论文"}],
        maxItems: 1
    });
}
// 收录类型
function CollectionType() {
    $('#conferType').selectize({
        valueField: 'id',
        labelField: 'value',
        options: [  //todo
            {"id":"1014","value": "SCI IV区"},
            {"id":"1015","value": "SSCI"},
            {"id":"1016","value": "EI"},
            {"id":"1017","value": "A&HCI"}],
        maxItems: 1
    });
}
// 报刊级别
function NewIssue() {
    $('#newsType').selectize({
        valueField: 'id',
        labelField: 'value',
        create: true,
        options: [
            {"id":"1020","value": "一类报刊(50分)"},
            {"id":"1021","value": "一类报刊(40分)"},
            {"id":"1022","value": "二类刊物二类报刊"}],
        maxItems: 1
    });
}
//日期选择区间
function selectData() {
    $('#reservation').daterangepicker(null,
        function (start, end, label) {
            console.log(start.toISOString(), end.toISOString(), label);
        }
    );
}
/**
 * 获取期刊
 */
function getMagName() {
    $('#magId').selectize({
        valueField: 'id',
        labelField: 'name',
        searchField: 'name',
        sortField: {
            field: 'text',
            direction: 'asc'
        },
        maxItems: 1,
        create: true,
        load: function (query, callback) {
            if (!query.length) return callback();
            $.ajax({
                url: '/api/mag/json',
                type: 'GET',
                dataType: 'json',
                data: {
                    query: query
                },
                error: function () {
                    callback();
                },
                success: function (res) {
                    callback(res);
                }
            });
        },
        onChange: function (result) {
            var magId = this;
            if ($("#magId").val() != "") {
                var getMag = $.ajax({
                    url: '/api/mag/' + result,
                    type: 'GET',
                    dataType: 'json'
                });
                // 根据magId获取期刊信息 并填充
                getMag.complete(function (info) {
                    var statusCode = getMag.status;
                    if (statusCode == 200) {
                        recoveryMagLevel();
                        $('#magName').val(magId.getItem(result)["context"]["innerHTML"]);
                        getMag.success(function (data) {
                            $("#magLevel").val(data["standard"]["infoMap"]["col_type"]);
                            $("#issn").val(data["issn"]);
                            $("#cn").val(data["cn"]);
                            $("#magStandardId").val(data["standard"]["id"]);
                        });
                    } else if (statusCode == 204) {
                        replaceMagLevel();
                        $('#magName').val(result);
                    }
                });
            }

        }
    });
}
//刊物级别的替换
function recoveryMagLevel() {
    if (document.querySelector("#otherPaper")) {
        $("#otherPaper").parent().empty().append("<input data-rel='tooltip' type='text' "
            + "class='form-control mag-input uneditableInput' id='magLevel' "
            + "name='mag.standard.infoMap.col_type' title='不可编辑' data-placement='right' "
            + "onfocus='this.blur()' onmouseover='showTooltip()'/>");
    }
}

//刊物级别的替换
function replaceMagLevel() {
    if (document.querySelector("#magLevel")) {
        $("#magLevel").replaceWith(
            "<select id='otherPaper' class='form-control mag-input' name='mag.standard.infoMap.col_type'>" +
            "</select>");
        $("#otherPaper").selectize({
            valueField: 'id',
            labelField: 'value',
            create: false,
            options: [
                {"id": "其它外文论文", "value": "其它外文论文"},
                {"id": "其它中文论文", "value": "其它中文论文"}
            ],
            onChange: function (result) {
                if (result == "其它外文论文") {
                    $("#magStandardId").val("1018");    //todo
                } else {
                    $("#magStandardId").val("1019");    //todo
                }
            }
        });
    }
}
/**
 * 获得最新的批复
 * @param data
 */
function getApprovalBy(data) {
    var max = 0;
    var keyStr = "";
    for (var key in pData) {
        var partn = new RegExp('WF_\\d+_Submission');
        if (partn.test(key)) {
            var keyValue = parseInt(key.substring(3, key.length - 11));
            if (max < keyValue) {
                max = keyValue;
            }
        }
        keyStr = "WF_" + max + "_Submission";
    }
    return keyStr;
}
/**
 * 通过成员table获取成员信息
 * @returns {JSON ARRAY}
 */
function getActorsData() {
    var actorsData = $("#actorTable").bootstrapTable('getData');
    $.each(actorsData, function (index, value) {
        delete value[0];
    });
    return actorsData;
}
function getUnitsData(){}
/**
 *查看/编辑样式
 *
 */
function view(index, row, value) {
    {
        return [
            //'<a class="edit ml10" href="javascript:void(0)" title="Edit">',
            '<i class="ace-icon fa fa-pencil bigger-130 editPaper"></i>'
            //'查看',
            //'</a>'
        ].join('');
    }
}
