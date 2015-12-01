/**
 * Created by zheng on 2015/6/12.
 */
function allSelects() {
    //部门
    $('#deptName').selectize({
        valueField: 'id',
        labelField: 'value',
        searchField: 'value',
        options: [],
        maxItems: 1,
        create: false,
        preload: true,
        load: function (query, callback) {
            $.ajax({
                url: '/api/baseinfo/院系',
                type: 'get',
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
        }
    });
    $('#rankName').selectize({
        valueField: 'id',
        labelField: 'value',
        maxItems: 1,
        options: [
            {"id": "13001", "value": "讲师"},
            {"id": "13002", "value": "副教授"},
            {"id": "13003", "value": "教授"}]
    });

    //岗位
    $('#position').selectize({
        valueField: 'value',
        labelField: 'value',
        maxItems: 1,
        options: [
            {"id": "行政人员", "value": "行政人员"},
            {"id": "教学人员", "value": "教学人员"},
            {"id": "教辅人员", "value": "教辅人员"},
            {"id": "科研人员", "value": "科研人员"},
            {"id": "后勤人员", "value": "后勤人员"},
            {"id": "退休人员", "value": "退休人员"}]
    });

    // 角色
    $('#role').selectize({
        valueField: 'value',
        labelField: 'value',
        maxItems: 1,
        options: [
            {"id": "普通用户", "value": "普通用户"},
            {"id": "科研处", "value": "科研处"},
            {"id": "管理员", "value": "管理员"}]
    });

    //学历
    $('#edu').selectize({
        valueField: 'value',
        labelField: 'value',
        maxItems: 1,
        options: [
            {"id": "专科", "value": "专科"},
            {"id": "本科", "value": "本科"}]
    });

    //学位
    $('#degree').selectize({
        valueField: 'value',
        labelField: 'value',
        maxItems: 1,
        options: [
            {"id": "学士", "value": "学士"},
            {"id": "硕士", "value": "硕士"},
            {"id": "博士", "value": "博士"}
        ]
    });
}
//实时请求的数据
function realTimeQuery(query, queryItem, callback) {

};
/**
 * select的数据填充
 *
 * */
function selectFill(staff) {
    //定义一个条件数组函数
    var termArray = {
        'deptName': staff['staff.dept.id'],
        'rankName': staff['staff.rank'],
        'degree': staff['staff.degree'],
        'position': staff['staff.position']
    };
    for (var key in termArray) {
        var $type = $('#' + key).selectize();
        DisplayForm($type, termArray[key], 1);
    }
}


//显示教师信息
function enableditForm() {
    $('form input').removeAttr("disabled", "disabled");
    var elementlist = document.querySelectorAll('.selectized');
    $.each(elementlist, function(index, value) {
        enableSelectize($(value).selectize());
    });
}
function uneditableForm() {
    $('form input').attr("disabled", "disabled");
    $('form select').attr("disabled", "disabled");
    var elementlist = document.querySelectorAll('.selectized');
    $.each(elementlist, function(index, value) {
        disableSelectize($(value).selectize());
    });//select
}
function failInfo(info) {
    BootstrapDialog.show({
        title: '温馨提示：',
        type: BootstrapDialog.TYPE_WARNING,
        message: info
    });
}
//保存成功的信息
function successInfo(info) {
    BootstrapDialog.show({
        title: '温馨提示：',
        type: BootstrapDialog.TYPE_SUCCESS,
        message: info
    });
}
function disableSelectize($type) {
    $type[0].selectize.disable();
}
function enableSelectize($type) {
    $type[0].selectize.enable();
}
function DisplayForm($type, ItemValue, flag) {
    if (!isNull(ItemValue)) {
        $type[0].selectize.setValue("");
    } else if (flag == '0') {
        $type[0].selectize.setValue(ItemValue);
    } else {
        $type[0].selectize.createItem(ItemValue);
    }
}
function isNull(str) {
    return (str == '' || str == undefined || str == null || str == {} || jQuery.isEmptyObject(str));
}