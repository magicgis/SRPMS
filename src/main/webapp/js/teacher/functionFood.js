/**
 * Created by zheng on 2015/4/16.
 */
var actorTemp =[];
var unitTemp =[];
$(function () {
    $('#NewFoodTable').bootstrapTable({
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
            field:'foodName',
            title:'食品名称',
            sortable:true
        },{
            field:'foodStaRole',
            title:'角色',
            sortable:true,
            formatter:"typeTran"
        },{
            field:'foodStaScore',
            title:'得分',
            sortable:true
        },{
            field:'newFoodDate',
            title:'日期',
            sortable:true
        },{
            field:'Status',
            title:'状态',
            sortable:true,
            formatter:'statusTran'
        }],
        responseHandler: function (res) {
            //$('.bs-checkbox').hide();
            var re = res;
            for (var i = 0; i < re.length; i++) {
                /*取出variableMap*/
                var p=re[i]['variableMap'];
                /*把string放入*/
                for(var key in p) {
                    if (typeof(p[key]) == 'string'||typeof(p[key]) == 'array') {
                        re[i][key] = p[key];
                    }
                }
                /*拿出最新的args*/
                if(p['Status']!='Blank'){
                    var temp=p[getSubmission(p)];
                    /*瞎写都能生效……*/
                    $.each(temp,function(key,content){
                        re[i][key]=temp[key];
                    });
                }
                   //console.log(res[i]);
                //console.log('ok');
            }
            var totalOrder = re;
            return totalOrder;
        }
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
            field:'foodStaRole',
            title:'角色',
            editable:true,
            sortable:true
            //footerFormatter:"totalMarksFormatter"
        },{
            field:'foodStaScore',
            title:'分数',
            editable:true,
            sortable:true
            // footerFormatter:"totalMarksFormatter"
        },{
            field:'operate',
            title:'操作',
            sortable:true,
            formatter:"operateFFormatter",
            events:"operateFEvents"
        }],
        data:actorTemp
    });
    $('#unitTable').bootstrapTable({
        columns: [{
            field:'foodUnit',
            title:'单位名称',
            editable:true,
            sortable:true
            // footerFormatter:"totalNameFormatter"
        },{
            field:'foodUnitRank',
            title:'单位排名',
            editable:true,
            sortable:true
            //    footerFormatter:"totalMarksFormatter"
        },{
            field:'operate',
            title:'操作',
            sortable:true,
            formatter:"operateUFFormatter",
            events:"operateUFEvents"
        }],
        data:unitTemp
    });
    showTable();
    $('#foodUnitInfo').hide();
    //$('#reply-box').hide();
    $('#reply').hide();
});
//监听 点击论文表
$('#NewFoodTable').on('click-row.bs.table', function (e, row, $element) {
    $('form input').val(null);
    actorTemp =[{"actor":"",
        "marks":"0"  }];
    var orderId = row["id"];
    var status = row["Status"];
    if (status == "Blank" || status == "Uncomplete" || status.indexOf('Refuse') >= 0) {
        editableForm();
        $('#confirm').show();
        $('#save').show();
        $('#orderBack').hide();
        $('del').show();
        $.ajax({
            type: 'get',
            url: '/api/workflow/ord' + orderId + '/task',
            success: function (currentTask) {
                var taskId = currentTask[0]['id'];
                $('#WF_Task').val(taskId);
                if(status.indexOf('Refuse')>=0){
                    $('#reply').show();
                    $('#reply-display').show();
                    var reply = $('#reply-display').children('p');
                    var who = $('#reply-display').children('small');
                    reply.empty();
                    who.empty();
                    if(status.indexOf("Col")>=0){
                        reply.append(currentTask[0]['variableMap']['replyByCol']);
                        who.append("学院批复");
                    }else{
                        reply.append(currentTask[0]['variableMap']['replyByDep']);
                        who.append("管理部门批复");
                    }
                }else{
                    $('#reply').hide();
                }
            }
        });
    } else {
        $('del').hide();
        if(status == 'Complete'||status == 'WaitForSubmit'){
            $('#orderBack').show();
        }else{
            $('#orderBack').hide();
        }
        $('#confirm').hide();
        $('#save').hide();
        uneditableForm();
    }
    if(row['actors']!=null) {
        actorTemp = row['actors'];
    }
    $('#paper').autofill(row, {
        findbyname: true,
        restrict: false
    });
    $("#actorTable").bootstrapTable('load',actorTemp);
    showForm();
});
//是否为第一单位
$('#isFirInMed').change(function(){
    firstOrOther();
});
//监听 点击保存
$("#save").click(function(){
});
//监听 点击确认
$("#confirm").click(function(){
});
//监听 点击返回
$("#back").click(function(){
});
//监听  删除
$("#del").click(function(){
});
//监听 点击撤回
$("#orderBack").click(function(){
});
/*统一提交*/
$("#submit").click(function(){
});
/*添加新药品*/
$("#add").click(function(){
});
//成功提示
function afterSuccess(msg){
    $('form input').val(null);
    $('#paper-box').addClass('collapsed');
    $('#info_alert').empty();
    $('<div class="alert alert-block alert-success" id="success_info"></div>').appendTo('#info_alert');
    $('<button type="button" class="close" data-dismiss="alert"><i class="ace-icon fa fa-times">' +
    '</i></button><i class="ace-icon fa fa-check green" id="success_icon"></i>').appendTo('#success_info');
    $('#success_icon').append(msg);
}