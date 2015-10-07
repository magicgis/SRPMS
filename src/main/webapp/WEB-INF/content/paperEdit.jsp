<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<jsp:useBean id="ObjectMapper" scope="application" class="com.fasterxml.jackson.databind.ObjectMapper"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>论文管理-
        <c:choose>
            <c:when test="${sessionScope.level == '1'}">
                教师
            </c:when>
            <c:when test="${sessionScope.level == '2'}">
                学院
            </c:when>
            <c:when test="${sessionScope.level == '3'}">
                学校
            </c:when>
        </c:choose>
    </title>

    <meta name="description" content="Dynamic tables and grids using jqGrid plugin"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>

    <jsp:include page="public/jsHeader.jsp"/>
    <style>
        .noradio td.bs-checkbox {
            display: none;
        }

        .noradio th.bs-checkbox {
            display: none;
        }
    </style>
</head>

<body class="no-skin">
<jsp:include page="public/topbar.jsp"/>

<div class="main-container" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
        } catch (e) {
        }
    </script>

    <jsp:include page="sidebar.jsp"/>

    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try {
                        ace.settings.check('breadcrumbs', 'fixed')
                    } catch (e) {
                    }
                </script>
                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="">Home</a>
                    </li>
                    <li class="active">论文</li>
                </ul>
                <!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;"
                             id="paper-box">
                            <div class="widget-body">
                                <form id="paper" class="form-horizontal" role="form">
                                    <div hidden="hidden">
                                        <input type="text" name="WF_Task" id="WF_Task"/>
                                        <input type="text" name="WF_Order" id="WF_Order"/>
                                        <input type="text" name="IsComplete" id="IsComplete"/>
                                        <input type="text" name="mag.name" id="magName"/>
                                        <input type="text" name="sum" id="score"/>
                                        <input type="text" name="mag.standard.id" id="magStandardId"/>
                                        <%--<input type="text" name="newspaper.standard.id" id="newsStandardId"/>--%>
                                        <%--<input type="text" name="confer.standard.id" id="conferStandardId"/>--%>
                                    </div>
                                    <div class="col-xs-12 col-md-6">
                                        <div id="paperInfo" class="col-xs-12 widget-container-col ui-sortable">
                                            <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
                                                <div class="widget-header">
                                                    <h4 class="widget-title">论文信息</h4>
                                                </div>
                                                <div class="widget-body ">
                                                    <div class="widget-main">
                                                        <div class="row">
                                                            <div class="form-group col-xs-12 col-sm-6">
                                                                <label class="col-sm-4 control-label no-padding-right"
                                                                       for="paperName">论文名称</label>

                                                                <div class="col-sm-8">
                                                                    <input type="text" id="paperName" name="name"
                                                                           placeholder="" class="col-xs-12"
                                                                           value=""/>
                                                                </div>
                                                            </div>

                                                            <div class="form-group col-xs-12 col-sm-6">
                                                                <label class="col-sm-4 control-label no-padding-right"
                                                                       for="pubDate">发表日期</label>

                                                                <div class="col-sm-8">
                                                                    <input class="form-control date-picker" id="pubDate"
                                                                           type="text" name="pubDate"
                                                                           data-date-format="yyyy-mm-dd"
                                                                           value=""/>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="form-group col-xs-12 col-sm-6 type">
                                                                <label class="col-sm-4 control-label no-padding-right"
                                                                       for="type">论文类型</label>

                                                                <div class="col-sm-8">
                                                                    <input class="form-control" id="type"
                                                                           type="text" name="type"
                                                                           placeholder="请选择"
                                                                           value=""/>
                                                                </div>
                                                            </div>

                                                            <div class="form-group col-xs-12 col-sm-6 paperWord">
                                                                <label class="col-sm-4 control-label no-padding-right"
                                                                       for="paperWord">论文字数</label>

                                                                <div class="col-sm-8">
                                                                    <input type="text" name="numWord" id="paperWord"
                                                                           placeholder="" class="col-xs-12"/>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div id="conferInfo" class="col-xs-12 widget-container-col ui-sortable">
                                            <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
                                                <div class="widget-header">
                                                    <h4 class="widget-title">会议信息</h4>
                                                </div>
                                                <div class="widget-body ">
                                                    <div class="widget-main">
                                                        <div class="row">
                                                            <div class="form-group col-xs-12 col-sm-6">
                                                                <label class="col-sm-4 control-label no-padding-right"
                                                                       for="conferName">会议名称</label>

                                                                <div class="col-sm-8">
                                                                    <input type="text" id="conferName"
                                                                           name="confer.name"
                                                                           placeholder=""
                                                                           class="col-xs-12 confer-input"/>
                                                                </div>
                                                            </div>
                                                            <div class="form-group col-xs-12 col-sm-6">
                                                                <label class="col-sm-4 control-label no-padding-right"
                                                                       for="conferType">收录类型</label>

                                                                <div class="col-sm-8">
                                                                    <input class="form-control" id="conferType"
                                                                           type="text" name="confer.standard.id"
                                                                           placeholder="请选择"/>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="form-group col-xs-12 col-sm-6">
                                                                <label class="col-sm-4 control-label no-padding-right"
                                                                       for="conferTime">会议时间</label>

                                                                <div class="col-sm-8">
                                                                    <input type="text" style="width: 200px"
                                                                           name="confer.time" id="conferTime"
                                                                           placeholder="示例：03/18/2013 - 03/23/2013"
                                                                           class="col-xs-12 confer-input"/>
                                                                </div>
                                                            </div>
                                                            <div class="form-group col-xs-12 col-sm-6">
                                                                <label class="col-sm-4 control-label no-padding-right"
                                                                       for="conferAddress">会议地点</label>

                                                                <div class="col-sm-8">
                                                                    <input type="text" name="confer.addr"
                                                                           id="conferAddress"
                                                                           class="col-xs-12 confer-input"
                                                                    <%--data-rel='tooltip' title='示例：武汉，中国' data-placement='bottom'--%>
                                                                           placeholder="示例：武汉，中国"/>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div id="newsInfo" class="col-xs-12 widget-container-col ui-sortable">
                                            <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
                                                <div class="widget-header">
                                                    <h4 class="widget-title">报刊信息</h4>
                                                </div>
                                                <div class="widget-body ">
                                                    <div class="widget-main">
                                                        <div class="row">
                                                            <div class="form-group col-xs-12 col-sm-6">
                                                                <label class="col-sm-4 control-label no-padding-right"
                                                                       for="newsName">报刊名称</label>

                                                                <div class="col-sm-8">
                                                                    <input class="form-control" id="newsName"
                                                                           type="text"
                                                                           name="newspaper.name" placeholder="请选择"/>
                                                                </div>
                                                            </div>
                                                            <div class="form-group col-xs-12 col-sm-6">
                                                                <label class="col-sm-4 control-label no-padding-right"
                                                                       for="newsPeriod">报刊等级</label>

                                                                <div class="col-sm-8">
                                                                    <input class="form-control" id="newsType"
                                                                           name="newspaper.standard.id"
                                                                           placeholder="请输入"/>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="form-group col-xs-12 col-sm-6">
                                                                <label class="col-sm-4 control-label no-padding-right"
                                                                       for="newsPeriod">发表期号</label>

                                                                <div class="col-sm-8">
                                                                    <input class="form-control" id="newsPeriod"
                                                                           type="text"
                                                                           name="newspaper.period" placeholder="请输入"/>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div id="magInfo" class="col-xs-12 widget-container-col ui-sortable">
                                            <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
                                                <div class="widget-header">
                                                    <h4 class="widget-title">期刊信息</h4>

                                                    <div class="widget-toolbar no-border">
                        <span class="btn btn-primary btn-xs" id="addDiff">
                            <i class="ace-icon fa fa-plus bigger-100"></i>
                            添加新期刊
                        </span>
                                                    </div>
                                                </div>
                                                <div class="widget-body ">
                                                    <div class="widget-main">
                                                        <div class="row">
                                                            <div class="form-group col-xs-12">
                                                                <div class="col-sm-2 control-label no-padding-right">
                                                                    <label for="magId">期刊名称</label>
                                                                </div>
                                                                <div class="col-sm-9">
                                                                    <input class="form-control mag-input" type="text"
                                                                           name="mag.id" id="magId"
                                                                           placeholder="请输入"/>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="form-group col-xs-12 col-sm-6">
                                                                <label class="col-sm-4 control-label no-padding-right"
                                                                       for="magLevel">刊物级别</label>

                                                                <div class="col-sm-8">
                                                                    <input type="text" id="magLevel"
                                                                           class="form-control mag-input uneditableInput"
                                                                           data-rel='tooltip' title='不可编辑'
                                                                           data-placement='right'
                                                                           name="mag.standard.infoMap.col_type"
                                                                           placeholder=""/>
                                                                </div>
                                                            </div>
                                                            <div class="form-group col-xs-12 col-sm-6">
                                                                <label class="col-sm-4 control-label no-padding-right"
                                                                       for="issn">ISSN</label>

                                                                <div class="col-sm-8">
                                                                    <input type="text" id="issn"
                                                                           class="form-control mag-input uneditableInput"
                                                                           data-rel='tooltip' title='不可编辑'
                                                                           data-placement='right'
                                                                           name="issn" placeholder=""/>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="form-group col-xs-12 col-sm-6">
                                                                <label class="col-sm-4 control-label no-padding-right"
                                                                       for="cn">CN</label>

                                                                <div class="col-sm-8">
                                                                    <input type="text" id="cn"
                                                                           class="form-control mag-input uneditableInput"
                                                                           data-rel='tooltip' title='不可编辑'
                                                                           data-placement='right'
                                                                           name="cn" placeholder=""/>
                                                                </div>
                                                            </div>
                                                            <div class="form-group col-xs-12 col-sm-6">
                                                                <label class="col-sm-4 control-label no-padding-right"
                                                                       for="vol">卷号</label>

                                                                <div class="col-sm-8">
                                                                    <input type="text" id="vol" name="vol"
                                                                           placeholder="" class="col-xs-12 mag-input"/>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="form-group col-xs-12 col-sm-6">
                                                                <label class="col-sm-4 control-label no-padding-right"
                                                                       for="issue">期号</label>

                                                                <div class="col-sm-8">
                                                                    <input type="text" id="issue" name="iss"
                                                                           placeholder="" class="col-xs-12 mag-input"/>
                                                                </div>
                                                            </div>
                                                            <div class="form-group col-xs-12 col-sm-6">
                                                                <label class="col-sm-4 control-label no-padding-right"
                                                                       for="bePage">起止页码</label>

                                                                <div class="col-sm-8">
                                                                    <input type="text" id="bePage" name="bgPage"
                                                                           placeholder="例：23-40"
                                                                           class="col-xs-12 mag-input"/>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>


                                    <div id="actorInfo" class="col-xs-12 col-md-6 widget-container-col">
                                        <div class="widget-box transparent ui-sortable-handle col-xs-12"
                                             style="opacity: 1;">
                                            <div class="widget-header" id="actorInfoHeader">
                                                <h4 class="widget-title">参与人员</h4>
                                                <span id="showSum" style="font-size: 15px"></span>
                                            </div>
                                            <div class="widget-body">
                                                <div class="widget-main">
                                                    <div class="row">
                                                        <div id="actorToolbar">
                                                            <a data-toggle="modal" id="addActor"
                                                               class="btn btn-primary btn-sm">添加成员</a>　
                                                            <%--<a data-toggle="modal" id="editActor" class="btn btn-primary btn-sm" style="display: none;">编辑成员</a>--%>
                                                            <a data-toggle="modal" id="getScore"
                                                               class="btn btn-primary btn-sm">计算分数</a>
                                                        </div>
                                                        <table id="actorTable"
                                                               data-toolbar="#actorToolbar"
                                                               data-show-footer="true"></table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-xs-12" id="msg_alert"></div>
                                    </div>


                                    <div id="reply" class="col-xs-12 col-md-6 widget-container-col">
                                        <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
                                            <div class="widget-header">
                                                <h4 class="widget-title">批复</h4>
                                            </div>
                                            <div class="widget-body">
                                                <div class="widget-main">
                                                    <div class="row">
                                                        <div class="col-xs-12">
                                                            <textarea class="form-control" id="reply-box"></textarea>
                                                            <blockquote class="pull-left" id="reply-display"
                                                                        hidden="hidden">
                                                                <p></p>
                                                                <small class="pull-right">
                                                                </small>
                                                            </blockquote>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-xs-12">
                                        <div class="col-xs-12 col-md-6 widget-container-col ui-sortable" id="fileHead">
                                            <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
                                                <div class="widget-header">
                                                    <h4 class="widget-title">附件信息</h4>

                                                    <div class="widget-toolbar no-border">
                                                        <div id="upload">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="widget-body">
                                                    <div class="widget-main">
                                                        <div class="dd" id="nestable">
                                                            <ol class="dd-list" id="downFiles"></ol>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <div class="row">
                                    <div id="formBtn" class="col-xs-12 clearfix">
                                        <c:choose>
                                            <c:when test="${sessionScope.level == '1'}">
                                                <div class="pull-left">
                                                    <button class="tabOrdBtn btn btn-danger btn-sm" type="button"
                                                            id="del">
                                                        <i class="ace-icon fa fa-trash  bigger-110"></i>
                                                        删除
                                                    </button>
                                                    <button class="tabOrdBtn btn btn-danger btn-sm orderBack"
                                                            type="button">
                                                        <i class="ace-icon fa  fa-repeat bigger-110"></i>
                                                        撤回
                                                    </button>
                                                </div>
                                                <div id="paperBtn" class="pull-right">
                                                    <button class="btn btn-info btn-sm back" type="button">
                                                        <i class="ace-icon fa fa-reply  bigger-110"></i>
                                                        返回
                                                    </button>
                                                    <button class="tabOrdBtn btn btn-success btn-sm" type="button"
                                                            id="confirm">
                                                        <i class="ace-icon fa fa-check bigger-110"></i>
                                                        确认
                                                    </button>
                                                    <button class="tabOrdBtn btn btn-primary btn-sm" type="button"
                                                            id="save">
                                                        <i class="ace-icon fa fa-save bigger-110"></i>
                                                        保存
                                                    </button>
                                                    <button class="btn btn-success btn-sm" type="button" id="confirmC">
                                                        <i class="ace-icon fa fa-check bigger-110"></i>
                                                        确认
                                                    </button>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="col-md-offset-4 col-md-8">

                                                    &nbsp; &nbsp; &nbsp;
                                                    <button class="btn btn-success" type="button" id="Approve">
                                                        <i class="ace-icon fa fa-check bigger-110"></i>
                                                        通过
                                                    </button>
                                                    &nbsp; &nbsp; &nbsp;
                                                    <button class="btn back" type="button">
                                                        <i class="ace-icon fa fa-reply  bigger-110"></i>
                                                        返回
                                                    </button>
                                                    &nbsp; &nbsp; &nbsp;
                                                    <button class="btn btn-danger" type="button" id="Refuse">
                                                        <i class="ace-icon fa fa-remove bigger-110"></i>
                                                        驳回
                                                    </button>
                                                    &nbsp; &nbsp; &nbsp;

                                                </div>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12" id="info_alert"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- /.main-content -->

<jsp:include page="public/footer.jsp"/>

<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
    <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
</a>
<!-- /.main-container -->

<script src='<c:url value="/js/public/public.js"/>'></script>
<script src='<c:url value="/js/public/pubPaper.js"/>'></script>


<script type="text/javascript">


    $(function ($) {
        $('#conferTime').daterangepicker();

        showTooltip();
    });

    getMagName();
    upToLoadFile();
    CollectionType();
    NewIssue();
    selectData();
//    $('#reply-box').hide();
//    $('#reply').hide();
    $('#confirmC').hide();
    paperType();

    var entity = ${ObjectMapper.writeValueAsString(order)};
    var args = entity['variableMap'];
    var latestInfo = args['WF_Latest'];
    if(latestInfo == undefined) {
        latestInfo = new Object();
    }
    var taskId = '${taskId}';
    var orderId = entity['id'];
    var status = args['Status'];
    // 批复
    var replyByCol, replyByDep;
    var approvalByCol = getApprovalByCol(args);
    if(approvalByCol !== ""){
        replyByCol = args[approvalByCol]['replyByCol'];
    }
    var approvalByDep = getApprovalByDep(args);
    if(approvalByDep !== "") {
        replyByDep = args[approvalByDep]['replyByDep'];
    }
    var paperType = latestInfo["type"];
    var filesData = latestInfo["filesData"];

    console.log(entity);

    $('#actorTable').bootstrapTable({
        columns: [
            {
                field: 'staff.id',
                title: '工号',
                sortable: true,
                visible: false
            }, {
                field: 'rank',
                title: '排名',
                sortable: true,
                footerFormatter: "totalNameFormatter"
            }, {
                field: 'staff.name',
                title: '成员',
                sortable: true
            }, {
                field: 'role',
                title: '角色',
                sortable: true
            }, {
                field: 'score',
                title: '分数',
                sortable: true,
                footerFormatter: "totalMarksFormatter"
            }, {
                field: 'unit',
                title: '归属单位',
                sortable: true
            }, {
                field: 'operate',
                title: '操作',
                sortable: true,
                formatter: "operateFormatter",
                events: "operateEvents"
            }],
        data: actorTemp
    });

    if (entity != null || !$.isEmptyObject(entity)) {
        //清空
        $('form input').val();

        actorTemp = [];
        //获取orderId与taskId

        $("#WF_Order").val(orderId);
        $('#WF_Task').val(taskId);


        //  期刊选择
        var $magId = $("#magId").selectize();
        //  论文类型
        var $paperType = $("#type").selectize();


        DisplayForm($paperType, paperType, 0);

        if (paperType == "magPaper") {
            // 期刊名称以及刊物级别
            // 期刊名称
            var magId = latestInfo["mag.id"];

            addOptionSelectize($magId, [{'id': magId, 'name': latestInfo['mag.name']}]);

            DisplayForm($magId, magId, 0);

            // 期刊级别
            if ((isNull(magId) || isInt(magId)) // 期刊没填 期刊在库中
                    && !(status == "Blank" || status == "Uncomplete" || status.indexOf('Refuse') >= 0) // 期刊不在填写中
            ) {
                recoveryMagLevel();
            } else {
                replaceMagLevel();
                console.log(latestInfo['mag.standard.infoMap.col_type']);
                DisplayForm($("#otherPaper").selectize(), latestInfo['mag.standard.infoMap.col_type'], 0);
            }
        }// 显示magId与判断magId的代码顺序不能改

        //可编辑状态
        if (status == "Blank" || status == "Uncomplete" || status.indexOf('RefuseByCol') >= 0) {
            $('#confirm').show();
            $('#save').show();
            $('#orderBack').hide();
            $('#del').show();
        }
        //不可编辑
        else {
            uneditableForm();
            disableSelectize($magId);
            disableSelectize($("#conferType").selectize());
            disableSelectize($("#newsType").selectize());
            if (document.querySelector("#otherPaper")) {
                disableSelectize($("#otherPaper").selectize());
            }
            $('#del').hide();
            if (status == 'Complete' || status == 'WaitForSubmit') {
                $('#orderBack').show();
            } else {
                $('#orderBack').hide();
            }
            $('#confirm').hide();
            $('#save').hide();
        }

        //显示mag或者confer
        magOrConfer();

        //显示总分
        var score = latestInfo['sum'];
        if (score == undefined || score == null || score == "") {
            $("#showSum").html("");
        } else {
            $("#showSum").html("　可分配总分：" + score + "分");
        }
        //成员信息
        if (latestInfo['actors'] != null) {
            actorTemp = latestInfo['actors'];
        }
        $("#actorTable").bootstrapTable('load', actorTemp);
        // 会议论文收录类型
        DisplayForm($("#conferType").selectize(), latestInfo["confer.standard.id"], 0);
        // 报刊等级
        DisplayForm($("#newsType").selectize(), latestInfo["newspaper.standard.id"], 0);
        // 填充表单
        $('#paper').autofill(latestInfo, {
            findbyname: true,
            restrict: false
        });
        // 文件信息
        if(filesData != undefined) {
            showFiles(filesData);
        }
    }

    //监听 更换论文类型
    $('#type').change(function () {
        magOrConfer();
        magOrConfer_add();
    });
    //监听 增加新期刊
    $("#addDiff").click(function () {
        window.location.href = "magazine";
    });

    //监听 添加成员
    $('#addActor').click(function () {
        addActor();
    });
    //监听 分配分数
    $('#getScore').click(function () {
        getScore();
    });
    //监听 点击保存
    $("#save").click(function () {
        save();
    });
    //监听 点击确认
    $("#confirm").click(function () {
        confirm();
    });
    //监听 点击返回
    $(".back").click(function () {
        history.go(-1);
    });
    //监听 删除
    $("#del").click(function () {
        delOrder();
    });
    //监听 点击撤回
    $(".orderBack").click(function () {
        getOrderBack();
    });
    //监听 点击通过
    $("#Approve").click(function () {
        approve();
    });
    //监听 点击驳回
    $("#Refuse").click(function () {
        refuse();
    });
    //监听 部分input不可输入
    $(".uneditableInput").focus(function () {
        this.blur();
    });


</script>


<c:choose>
    <c:when test="${sessionScope.level == '1'}">
        <script src="<c:url value="/js/teacher/paperEdit.js"/>"></script>
    </c:when>
    <c:when test="${sessionScope.level == '2'}">
        <script src="<c:url value="/js/college/paperEdit.js"/>"></script>
    </c:when>
    <c:when test="${sessionScope.level == '3'}">
        <script src="<c:url value="/js/school/paperEdit.js"/>"></script>
    </c:when>
</c:choose>

</body>
</html>