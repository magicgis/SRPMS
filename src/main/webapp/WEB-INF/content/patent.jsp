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
    <title>专利 -
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
                        <a href="/allSRInfo">Home</a>
                    </li>
                    <li class="active">专利</li>
                </ul>
                <!-- /.breadcrumb -->
            </div>

            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="widget-box transparent " style="opacity: 1;" id="newPatentTable-box">
                            <div class="widget-body" id="publicArea">
                                <table id="PatentTable"
                                       data-toolbar="#tPatentToolbar"
                                       data-search="true"
                                       data-show-columns="true"
                                       data-show-refresh="true"
                                       data-show-toggle="true"
                                       data-page-list="[10]"
                                       data-pagination="true"
                                       data-single-select="true"
                                       data-click-to-select="true">
                                </table>
                                <div id="tPatentToolbar">

                                    <c:choose>
                                        <c:when test="${sessionScope.level == '1'}">
                                            <button id="submit" class="btn btn-success">
                                                <i class="fa fa-check"></i> 统一提交
                                            </button>
                                        </c:when>
                                        <c:when test="${sessionScope.level == '3'}">
                                            <button id="add" class="btn btn-primary">
                                                <i class="fa fa-plus"></i> 添加专利
                                            </button>
                                        </c:when>
                                    </c:choose>
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
<script src='<c:url value="/js/public/pubPatent.js"/>'></script>
<script src='<c:url value="/js/public/public.js"/>'></script>

<c:choose>
    <c:when test="${sessionScope.level == '1'}">
        <script src="<c:url value="/js/teacher/patent.js"/>"></script>
    </c:when>
    <c:when test="${sessionScope.level == '2'}">
        <script src="<c:url value="/js/college/patent.js"/>"></script>
    </c:when>
    <c:when test="${sessionScope.level == '3'}">
        <script src="<c:url value="/js/school/patent.js"/>"></script>
    </c:when>
</c:choose>

</body>
</html>








