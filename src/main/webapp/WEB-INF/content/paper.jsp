<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
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
                        <div class="widget-box transparent " style="opacity: 1;" id="paperTable-box">
                            <div class="widget-body">
                                <div id="tableOrder">
                                    <table id="PaperTable" class="noradio"
                                           data-toolbar="#tPaperToolbar"
                                           data-search="true"
                                           data-show-columns="true"
                                           data-show-refresh="true"
                                           data-show-toggle="true"
                                           data-page-list="[10]"
                                           data-pagination="true"
                                           data-single-select="true"
                                           data-click-to-select="true">
                                    </table>
                                    <div id="tPaperToolbar">
                                        <c:choose>
                                            <c:when test="${sessionScope.level == '1'}">
                                                <div class="btn-group">
                                                    <button class="btn btn-primary dropdown-toggle"
                                                            data-toggle="dropdown">
                                                        查看选项
                                                        <span class="ace-icon fa fa-caret-down icon-on-right"></span>
                                                    </button>
                                                    <ul class="dropdown-menu dropdown-menu-left">
                                                        <li>
                                                            <a href="#" id="allPaper">全部论文</a>
                                                        </li>
                                                        <li>
                                                            <a href="#" id="orderPaper">主导论文</a>
                                                        </li>
                                                        <li>
                                                            <a href="#" id="partakeBtn">参与论文</a>
                                                        </li>
                                                        <li>
                                                            <a href="#" id="confirmBtn">待确认论文</a>
                                                        </li>
                                                    </ul>
                                                </div>
                                                <button id="addPaper" class="paOperate btn btn-primary">
                                                    <i class="fa fa-plus"></i>添加论文
                                                </button>
                                            </c:when>
                                            <c:when test="${sessionScope.level == '1'||sessionScope.level == '2'}">
                                                <button class="paOperate btn btn-success btn-sm" type="button"
                                                        id="submit">
                                                    <i class="ace-icon fa fa-check bigger-160"></i>
                                                    统一提交
                                                </button>
                                            </c:when>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12" id="info_alert"></div>
                    <div class="col-xs-12">
                        <div class="widget-box transparent ui-sortable-handle collapsed" style="opacity: 1;"
                             id="paper-box">
                            <div class="widget-body">
                                <jsp:include page="public/paperForm.jsp"/>
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


<c:choose>
    <c:when test="${sessionScope.level == '1'}">
        <script src="<c:url value="/js/teacher/paper.js"/>"></script>
    </c:when>
    <c:when test="${sessionScope.level == '2'}">
        <script src="<c:url value="/js/college/paper.js"/>"></script>
    </c:when>
    <c:when test="${sessionScope.level == '3'}">
        <script src="<c:url value="/js/school/paper.js"/>"></script>
    </c:when>
</c:choose>


<script type="text/javascript">

    //监听 点击通过
    $("#Approve").click(function () {
        approve();
    });
    //监听 点击驳回
    $("#Refuse").click(function () {
        refuse();
    });

    $(function ($) {
        $('#conferTime').daterangepicker();
        $('.date-picker').datepicker({
            autoclose: true,
            todayHighlight: true
        }).next().on(ace.click_event, function () {
            $(this).prev().focus();
        });
        showTooltip();
    });

    function showTooltip() {
        $('[data-rel=tooltip]').tooltip({container: 'body'});
    }
    jQuery(function ($) {
        $('.date-picker').datepicker({
            autoclose: true,
            todayHighlight: true
        }).next().on(ace.click_event, function () {
            $(this).prev().focus();
        });
    });
</script>
</body>
</html>
