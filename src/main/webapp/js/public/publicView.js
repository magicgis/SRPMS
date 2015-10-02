/**
 * Created by Administrator on 2015/8/2.
 */
var diff = {
    "paper" : ["paperType", "conferType", "newsType", "magId"],
    "book" : []
};

function fillForm(row, type){
    var  score = row['score'];
    if(score == undefined || score == null || score =="") {
        $("#showSum").html("");
    }else {
        $("#showSum").html("总分："+score+"分");
    }
    if (row['actors'] != null) {
        actorTemp = row['actors'];
    }
    $("#actorTable").bootstrapTable('load', actorTemp);
}