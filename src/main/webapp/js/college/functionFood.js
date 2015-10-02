/**
 * Created by zheng on 2015/4/19.
 */
var actorTemps =[];
var foodUnitTemps =[];
$(function () {
    showForm();
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
            field:'S-ACTOR',
            title:'提交者',
            sortable:true
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
        }],
        data:actorTemps
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
        }],
        data:foodUnitTemps
    });
    $('#foodUnitInfo').hide();
    $('#reply-box').show();
    $('#reply').show();
    $('#foodUnitToolbar').hide();
    $('#actorNewFoodToolbar').hide();
    uneditableForm();
});

//监听 点击table
$('#NewFoodTable').on('click-row.bs.table', function (e, row, $element) {
    //$('form input').val(null);
    //actorTemps =[{"staName":"", "staId":"","foodStaRole":"","foodStaScore":"0"  }];
    //var orderId = row["id"];
    //var status = row["Status"];
    //uneditableForm();
    //if(status == 'WaitForCol') {
    //    $.ajax({
    //        type: 'get',
    //        url: '/controller/workflow/ord' + orderId + '/task',
    //        success: function (currentTask) {
    //            var taskId = currentTask[0]['id'];
    //            $('#WF_Task').val(taskId);
    //        }
    //    });
    //    $("#Approve").removeAttr("disabled","disabled");
    //    $("#Refuse").removeAttr("disabled","disabled");
    //}else{
    //    $("#Approve").attr("disabled","disabled");
    //    $("#Refuse").attr("disabled","disabled");
    //}
    //if(row['actors']!=null) {
    //    actorTemp = row['actors'];
    //}
    //$('#paper').autofill(row, {
    //    findbyname: true,
    //    restrict: false
    //});
    //magOrConfer();
    //$("#actorTable").bootstrapTable('load',actorTemp);
    //$('#paperTable-box').addClass('collapsed');
    //$('#paper-box').removeClass('collapsed');
});

//监听 点击返回
$("#back").click(function(){
});

$("#Approve").click(function(){
});

$("#Refuse").click(function(){
});

$("#next").click(function(){
   // console.log($('#tNewFoodTable').bootstrapTable('getSelections'));
});

$("#previous").click(function(){
});


function approve(){
    //var jsonData = Object();
    //jsonData["DecByCol"]=true;
    //jsonData["WF_User"]="col";
    //jsonData["WF_Task"]=$('#WF_Task').val();
    //jsonData["replyByCol"]=$('#reply-box').val();
    //pubapprove(jsonData);
}

function refuse(){
    //var jsonData = Object();
    //jsonData["DecByCol"]=false;
    //jsonData["WF_User"]="col";
    //jsonData["WF_Task"]=$('#WF_Task').val();
    //jsonData["replyByCol"]=$('#reply-box').val();
    //pubrefuse(jsonData);
}
