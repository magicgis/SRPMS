/**
 * Created by zheng on 2015/4/29.
 */
/**
 * 不同项目名称所对应得表单
 * */
var selectDefferent={"project":"../public/differentProjectForm.jsp",
    "patent":"",
    "newOther":"",
    "newMedicine":"",
    "instrument":"",
    "food":"",
    "change":"",
    "award":"",
    "appraise":""};
/**
 * 不同差异申请传来的参数
 * */
$(function(){
    var tmpArr,QueryString;
    var URL = document.location.toString(); //获取带参URL
    if (URL.lastIndexOf("?") != -1) {
        QueryString = URL.substring(URL.lastIndexOf("?") + 1, URL.length);
        tmpArr = QueryString.split("&");//分离参数
        for (i = 0; i <= tmpArr.length; i++) {
            try {
                eval(tmpArr[i]);
            }
            catch (e) {
                var re = new RegExp("(.*)=(.*)", "ig");
                re.exec(tmpArr[i]);
                try { eval(RegExp.$1 + "=" + "\"" + RegExp.$2 + "\""); }
                catch (e) {}
            }
        }
    }
    else {
        QueryString = "";
    }
    if (proType) {
        var proType = unescape(proType);
        //var WF_Task,id;
        //WF_Task=unescape(WF_Task);
        //id=unescape(id);
        //方法不能删除
        var temp=proType;
        for(var key in selectDefferent) {
            if (key==temp) {
                $("#differentChange").empty();
                var url=selectDefferent[key];
                $("#differentChange").load(url);
            }
        }
    }else{
        $("#differentChange").empty();
    }
});
//function differentData(WF_Task,id){
//    $.ajax({
//        type:'',
//        url:'',
//        success:function(){
//
//        }
//    });
//}
/*
* 显示详情
* */
function showForm(){
   // $('#differentTable-box').addClass('collapsed');
    $('#different-box').removeClass('collapsed');
}
 /**
* 测试用的，使用时要删掉
* */
//$('#differentType').change(function(){
//     //方法不能删除
//     $.each(selectDefferent,function(index){
//         //var differentInfo=value;
//         var temp=$('#differentType').val();
//         for(var key in selectDefferent) {
//             if (key==temp) {
//                 var url=selectDefferent[key];
//                 $("#differentChange").load(url);
//             }
//         }
//     });
//});
//function viewTable(key1){
//    $('#'+key1).bootstrapTable({
//        columns: [{
//            field:'actor',
//            title:'成员',
//            editable:true,
//            sortable:true,
//            footerFormatter:"totalNameFormatter"
//        },{
//            field:'staId',
//            title:'工号',
//            editable:true,
//            sortable:true
//        },{
//            field:'role',
//            title:'角色',
//            editable:true,
//            sortable:true
//            //footerFormatter:"totalNameFormatter"
//        },{
//            field:'marks',
//            title:'分数',
//            editable:true,
//            sortable:true,
//            footerFormatter:"totalMarksFormatter"
//            //footerFormatter:"totalMarksFormatter"
//        },{
//            field:'operate',
//            title:'操作',
//            sortable:true,
//            formatter:"operateAFormatter",
//            events:"operateAEvents"
//        }],
//        data:actorTemp
//    });
//}
//$('#info_alert').empty();
//$('<div class="alert alert-block alert-success" id="success_info"></div>').appendTo('#info_alert');
//显示总览
//function showTable(){
//    $('#ProjectTable').bootstrapTable('refresh',{silent: true});
//    $('#projTable-box').removeClass('collapsed');
//    $('#proj-box').addClass('collapsed');
//}