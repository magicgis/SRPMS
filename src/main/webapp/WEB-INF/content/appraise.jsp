<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%--
  Created by IntelliJ IDEA.
  User: huyuanyuan555
  Date: 2015/4/19
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>成果鉴定管理 -
        <c:choose>
            <c:when test="${sessionScope.level == '1'}">
                老师
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
                        <a href="index.jsp">Home</a>
                    </li>
                    <li class="active">成果鉴定</li>
                </ul>
                <!-- /.breadcrumb -->
            </div>

            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="widget-box transparent " style="opacity: 1;" id="appraiseTable-box">
                            <div class="widget-body">
                                <table id="AppraiseTable"
                                       data-toolbar="#AppraiseToolbar"
                                       data-search="true"
                                       data-show-columns="true"
                                       data-show-refresh="true"
                                       data-show-toggle="true"
                                       data-page-list="[10]"
                                       data-pagination="true"
                                       data-single-select="true"
                                       data-click-to-select="true">
                                </table>
                                <div id="AppraiseToolbar">
                                    <c:if test="${sessionScope.level == '3'}">
                                        <div class="btn-group">
                                            <button class="btn btn-primary dropdown-toggle"
                                                    data-toggle="dropdown">
                                                查看选项
                                                <span class="ace-icon fa fa-caret-down icon-on-right"></span>
                                            </button>
                                            <ul class="dropdown-menu dropdown-menu-left">
                                                <li>
                                                    <a href="#" id="all">全部鉴定信息</a>
                                                </li>
                                                <li>
                                                    <a href="#" id="approve">待审批鉴定信息</a>
                                                </li>
                                            </ul>
                                        </div>
                                        <button id="add" class="btn btn-primary">
                                            <i class="fa fa-plus"></i> 添加成果鉴定
                                        </button>
                                    </c:if>
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
</div>

<!-- /.main-container -->
<%--<script src='<c:url value="/js/public/pubAppraise.js"/>'></script>--%>
<script src='<c:url value="/js/public/public.js"/>'></script>

<c:choose>
    <c:when test="${sessionScope.level == '1'}">
        <script src="<c:url value="/js/teacher/appraise.js"/>"></script>
    </c:when>
    <c:when test="${sessionScope.level == '2'}">
        <script src="<c:url value="/js/college/appraise.js"/>"></script>
    </c:when>
    <c:when test="${sessionScope.level == '3'}">
        <script src="<c:url value="/js/school/appraise.js"/>"></script>
    </c:when>
</c:choose>


</body>
</html>

