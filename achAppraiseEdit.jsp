<%--
  Created by IntelliJ IDEA.
  User: yxm
  Date: 2015/10/3
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%--<jsp:useBean id="ObjectMapper" scope="application" class="com.fasterxml.jackson.databind.ObjectMapper"/>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>

    <meta name="description" content="Dynamic tables and grids using jqGrid plugin"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>

    <jsp:include page="public/jsHeader.jsp"/>
    <style>
        td.bs-checkbox {
            display: none;
        }

        th.bs-checkbox {
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
                        <a href="'allSRInfo.jsp'/>">Home</a>
                    </li>
                    <li class="active">成果鉴定</li>
                </ul>
                <!-- /.breadcrumb -->
            </div>

            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="widget-box transparent " style="opacity: 1;" id="newAchAppraiseTable-box">
                            <div class="widget-body" id="publicArea">

                                <form id="achAppraise" class="form-horizontal" role="form">
                                    <div hidden="hidden">
                                        <input type="text" name="WF_Order" id="WF_Order"/>
                                        <input type="text" name="WF_Task" id="WF_Task"/>
                                        <input type="text" name="IsComplete" id="IsComplete"/>
                                        <input type="text" name="pscore" id="score"/>
                                        <input type="text" name="dept.value" id="deptValue"/>
                                        <input type="text" name="achAppraise.standard.value" id="type"/>
                                    </div>
                                    <div id="achAppraiseInfo" class="col-xs-12 col-md-6 widget-container-col ui-sortable">
                                        <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
                                            <div class="widget-header">
                                                <h4 class="widget-title">鉴定信息</h4>
                                            </div>
                                            <div class="widget-body ">
                                                <div class="widget-main">
                                                    <div class="row">
                                                        <div class="form-group col-xs-12">
                                                            <label class="col-sm-2 control-label no-padding-left"
                                                                   for="name">成果名称</label>

                                                            <div class="col-sm-9">
                                                                <input id="name" name="name"
                                                                       type="text" class="form-control col-xs-12"
                                                                       placeholder="请选择"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group col-xs-12 col-sm-6">
                                                            <label class="col-sm-4 control-label no-padding-left"
                                                                   for="dept">所属部门</label>

                                                            <div class="col-sm-8">
                                                                <input id="dept" name="dept.id"
                                                                       type="text" class="form-control col-xs-12"
                                                                       placeholder="请选择"/>
                                                            </div>
                                                        </div>
                                                        <div class="form-group col-xs-12 col-sm-6">
                                                            <label class="col-sm-4 control-label no-padding-left"
                                                                   for="certificateNo">鉴定证书号</label>

                                                            <div class="col-sm-8">
                                                                <input id="certificateNo" name="certificateNo"
                                                                       type="text" class="col-xs-12" placeholder=""/>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="form-group col-xs-12 col-sm-6">
                                                            <label class="col-sm-4 control-label no-padding-left"
                                                                   for="date">鉴定日期</label>

                                                            <div class="col-sm-8">
                                                                <input id="date" name="date"
                                                                       type="text"
                                                                       class="form-control col-xs-12 date-picker"
                                                                       data-date-format="yyyy-mm-dd"/>
                                                            </div>
                                                        </div>
                                                        <div class="form-group col-xs-12 col-sm-6">
                                                            <label class="col-sm-4 control-label no-padding-left"
                                                                   for="result">鉴定结论</label>

                                                            <div class="col-sm-8">
                                                                <input id="result" name="achAppraise.standard.id"
                                                                       type="text" class="form-control col-xs-12"
                                                                       placeholder="请选择"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group col-xs-12 col-sm-6">
                                                            <label class="col-sm-4 control-label no-padding-left"
                                                                   for="certifyUnit">鉴定单位</label>

                                                            <div class="col-sm-8">
                                                                <input name="certifyUnit" id="certifyUnit"
                                                                       type="text" class="form-control col-xs-12"
                                                                       placeholder=""/>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="col-xs-12">
                                                            <div class="col-xs-12 widget-container-col ui-sortable"
                                                                 id="fileHead">
                                                                <div class="widget-box transparent ui-sortable-handle"
                                                                     style="opacity: 1;">
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
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <div class="col-xs-12 col-md-6">

                                    <div id="actorInfo" class="col-xs-12 widget-container-col ui-sortable">
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
                                                            <a data-toggle="modal" id="getScore"
                                                               class="btn btn-primary btn-sm">计算分数</a>

                                                        </div>
                                                        <table id="actorTable"
                                                               data-toolbar="#actorToolbar"
                                                               data-show-footer="true"
                                                               data-show-columns="true"
                                                               data-show-toggle="true"></table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-12" id="msg_alert"></div>
                                    </div>

                                    <div id="unitInfo" class="col-xs-12 widget-container-col ui-sortable">
                                        <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
                                            <div class="widget-header">
                                                <h4 class="widget-title">共有单位信息</h4>
                                            </div>
                                            <div class="widget-body">
                                                <div class="widget-main">
                                                    <div class="row">
                                                        <div id="unitToolbar">
                                                            <a id="addUnit" class="btn btn-primary btn-sm"><i
                                                                    class="glyphicon glyphicon-plus"></i> 添加单位</a>
                                                            <a id="editUnit" class="btn btn-primary btn-sm"><i
                                                                    class="glyphicon glyphicon-pencil"></i> 编辑单位</a>
                                                        </div>
                                                        <table id="unitTable"
                                                               data-toolbar="#unitToolbar"
                                                               data-show-toggle="false"
                                                               data-show-footer="true"></table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-xs-12">
                                    <div class="widget-body">
                                        <div class="row">
                                            <div id="formBtn" class="col-xs-12 clearfix">
                                                <div class="pull-left onEdit">
                                                    <button class="tabOrdBtn btn btn-danger btn-sm del" type="button">
                                                        <i class="ace-icon fa fa-trash  bigger-110"></i>
                                                        删除
                                                    </button>
                                                </div>
                                                <div class="pull-right">
                                                    <span class="onEdit">
                                                        <button class="tabOrdBtn btn btn-success btn-sm confirm"
                                                                type="button">
                                                            <i class="ace-icon fa fa-check bigger-110"></i>
                                                            确认
                                                        </button>
                                                        <button class="tabOrdBtn btn btn-primary btn-sm save"
                                                                type="button">
                                                            <i class="ace-icon fa fa-save bigger-110"></i>
                                                            保存
                                                        </button>
                                                    </span>
                                                    <span class="onApproval">
                                                        <button class="btn btn-success btn-sm approve" type="button">
                                                            <i class="ace-icon fa fa-check bigger-110"></i>
                                                            通过
                                                        </button>
                                                        <button class="btn btn-danger btn-sm refuse" type="button">
                                                            <i class="ace-icon fa fa-remove bigger-110"></i>
                                                            驳回
                                                        </button>
                                                    </span>
                                                </div>
                                            </div>
                                        </div>
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
<jsp:include page="public/footer.jsp"/>
<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
    <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
</a>


<!-- /.main-container -->
<script src='<c:url value="/js/public/pubAppraise.js"/>'></script>
<script src='<c:url value="/js/public/public.js"/>'></script>

<c:choose>
    <c:when test="${sessionScope.level == '1'}">
        <script src="<c:url value="/js/teacher/appraiseEdit.js"/>"></script>
    </c:when>
    <c:when test="${sessionScope.level == '2'}">
        <script src="<c:url value="/js/college/appraiseEdit.js"/>"></script>
    </c:when>
    <c:when test="${sessionScope.level == '3'}">
        <script src="<c:url value="/js/school/appraiseEdit.js"/>"></script>
    </c:when>
</c:choose>

</body>


<script>

    jQuery(function ($) {

        $('.date-picker').datepicker({
            autoclose: true,
            todayHighlight: true
        }).next().on(ace.click_event, function () {
            $(this).prev().focus();
        });
    });

    upToLoadFile();

    //监听 确认
    $(".confirm").click(function () {
        confirm();
    });
    //监听 返回
    $('.back').click(function () {
        history.go(-1);
    });
    //监听 删除
    $('.del').click(function () {
        delOrder();
    });
    //监听 通过
    $('.approve').click(function () {
        approve();
    });
    //监听 驳回
    $('.refuse').click(function () {
        refuse();
    });
    //监听 保存
    $('.save').click(function () {
        save();
    });
    //监听 添加单位
    $('#addUnit').click(function () {
        addUnit();
    });
    //监听 添加成员
    $('#addActor').click(function () {
        addActor();
    });
    //监听 编辑成员
    $('#editActor').click(function () {
        editActor();
    });
    //监听 编辑单位
    $('#editUnit').click(function () {
        editUnit();
    });
    //监听 分配分数
    $('#getScore').click(function () {
        getScore();
    });
</script>
</html>