<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>科研概况 -
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
        </c:choose></title>

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
                        <a href="allSRInfo">Home</a>
                    </li>
                    <li class="active">科研概况</li>
                </ul>
                <!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="widget-box transparent " style="opacity: 1;" id="confirmTable-box">
                            <div class="widget-body">
                                <div id="tableCon">
                                    <table id="allSRInfoTable"
                                           data-toolbar="#SRToolbar"
                                           data-search="true"
                                           data-show-columns="true"
                                           data-show-refresh="true"
                                           data-show-toggle="true"
                                           data-page-list="[10]"
                                           data-pagination="true"
                                           data-single-select="true"
                                           data-click-to-select="true">
                                    </table>
                                    <div id="SRToolbar">
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
                                                            <a href="#" id="allSRInform">所有科研</a>
                                                        </li>
                                                        <li>
                                                            <a href="#" id="orderSRInfo">主导科研</a>
                                                        </li>
                                                        <li>
                                                            <a href="#" id="partakeBtn">参与科研</a>
                                                        </li>
                                                        <li>
                                                            <a href="#" id="confirmBtn">待确认</a>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </c:when>
                                            <c:when test="${sessionScope.level == '2'}">

                                            </c:when>
                                            <c:when test="${sessionScope.level == '3'}">

                                            </c:when>
                                        </c:choose>

                                        <button class="btn btn-success allSubmit">
                                            <i class="ace fa fa-check"></i>统一提交
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12" id="info_alert"></div>
                    <div class="col-xs-12">
                        <div class="widget-box transparent ui-sortable-handle collapsed" style="opacity: 1;"
                             id="confirm-box">
                            <div class="widget-body">
                                <div id="confirmForm">
                                    <%--加载各种科研类型的表单--%>
                                </div>
                                <div class="row">
                                    <div id="formBtn" class="col-xs-12 clearfix">
                                        <div id="paperBtn" class="pull-right">
                                            <button class="btn btn-info btn-sm" type="button" id="back">
                                                <i class="ace-icon fa fa-reply  bigger-110"></i>
                                                返回
                                            </button>
                                            <button class="btn btn-success btn-sm" type="button" id="confirm">
                                                <i class="ace-icon fa fa-check bigger-110"></i>
                                                确认
                                            </button>
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
<!-- /.main-content -->

<jsp:include page="public/footer.jsp"/>

<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
    <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
</a>
<!-- /.main-container -->
<script src='<c:url value="/js/public/public.js"/>'></script>
<script src='<c:url value="/js/public/pubAllSRInfo.js"/>'></script>
<c:choose>
    <c:when test="${sessionScope.level == '1'}">
        <script src="<c:url value="/js/teacher/allSRInfo.js"/>"></script>
    </c:when>
    <c:when test="${sessionScope.level == '2'}">
        <script src="<c:url value="/js/college/allSRInfo.js"/>"></script>
    </c:when>
    <c:when test="${sessionScope.level == '3'}">
        <script src="<c:url value="/js/school/allSRInfo.js"/>"></script>
    </c:when>
</c:choose>
<script type="text/javascript">
    $(function ($) {
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