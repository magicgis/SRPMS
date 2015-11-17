<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>科研积分管理系统</title>

    <meta name="description" content="Dynamic tables and grids using jqGrid plugin"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>

    <jsp:include page="public/jsHeader.jsp"/>
    <script src="<c:url value="/plugin/stateman/stateman.js"/>"></script>

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
                        <a href="#">Home</a>
                    </li>
                    <li class="active" id="title">科研概况</li>
                </ul>
                <!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="widget-box transparent " style="opacity: 1;" id="confirmTable-box">
                            <div class="widget-body">
                                <div id="tableCon">
                                    <table id="mainTable"
                                           data-show-columns="true"
                                           data-show-refresh="true"
                                           data-page-list="[10,15,20]"
                                           data-pagination="true"
                                           data-single-select="true"
                                           data-click-to-select="true">
                                    </table>
                                    <div id="ProcessToolbar" style="display: none">
                                        <c:choose>
                                            <c:when test="${sessionScope.level == '1'}">
                                                <div class="btn-group">
                                                    <button class="btn btn-primary dropdown-toggle levelOption"
                                                            data-toggle="dropdown">
                                                        查看选项
                                                        <span class="ace-icon fa fa-caret-down icon-on-right"></span>
                                                    </button>
                                                    <ul class="dropdown-menu dropdown-menu-left">
                                                        <li class="level all">
                                                            <a>所有科研</a>
                                                        </li>
                                                        <li class="level 1st">
                                                            <a>主导科研</a>
                                                        </li>
                                                        <li class="level 2nd">
                                                            <a>参与科研</a>
                                                        </li>
                                                    </ul>
                                                </div>
                                                <div class="btn-group">
                                                    <button class="btn btn-primary dropdown-toggle"
                                                            data-toggle="dropdown">
                                                        填报信息
                                                        <span class="ace-icon fa fa-caret-down icon-on-right"></span>
                                                    </button>
                                                    <ul class="dropdown-menu dropdown-menu-left">
                                                        <li id="newPaper">
                                                            <a>论文</a>
                                                        </li>
                                                        <li id="newBook">
                                                            <a>著作</a>
                                                        </li>
                                                    </ul>
                                                </div>
                                                <button class="btn btn-success allSubmit">
                                                    <i class="ace fa fa-check"></i>统一提交
                                                </button>
                                            </c:when>
                                            <c:when test="${sessionScope.level == '2'}">
                                                <button class="btn btn-success allSubmit">
                                                    <i class="ace fa fa-check"></i>统一提交
                                                </button>
                                            </c:when>
                                            <c:when test="${sessionScope.level == '3'}">

                                            </c:when>
                                        </c:choose>
                                    </div>
                                    <div id="EntityToolbar" style="display: none">
                                        <c:choose>
                                            <c:when test="${sessionScope.level == '3'}">
                                                <div class="form-group col-xs-12">
                                                    <div class="row">
                                                        <div class="col-xs-6">
                                                            <button class="btn btn-primary dropdown-toggle"
                                                                    data-toggle="dropdown">
                                                                信息添加
                                                                <span class="ace-icon fa fa-caret-down icon-on-right"></span>
                                                            </button>
                                                            <ul class="dropdown-menu dropdown-menu-left">
                                                                <li id="newPatent">
                                                                    <a href="<c:url value="/patent/new"/> ">专利信息</a>
                                                                </li>
                                                                <li id="newProject">
                                                                    <a href="<c:url value="/project/new"/> ">项目信息</a>
                                                                </li>
                                                                <li id="newAppaise">
                                                                    <a href="<c:url value="/achAppraisal/new"/> ">鉴定信息</a>
                                                                </li>
                                                                <li id="newAchTran">
                                                                    <a href="<c:url value="/achTran/new"/> ">转化信息</a>
                                                                </li>
                                                                <li id="newAchAward">
                                                                    <a href="<c:url value="/achAward/new"/> ">获奖信息</a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:when>
                                            <c:when test="${sessionScope.level == '1'}">
                                                <div class="btn-group">
                                                    <button class="btn btn-primary dropdown-toggle levelOption"
                                                            data-toggle="dropdown">
                                                        查看选项
                                                        <span class="ace-icon fa fa-caret-down icon-on-right"></span>
                                                    </button>
                                                    <ul class="dropdown-menu dropdown-menu-left">
                                                        <li class="level all">
                                                            <a>所有科研</a>
                                                        </li>
                                                        <li class="level 1st">
                                                            <a>主导科研</a>
                                                        </li>
                                                        <li class="level 2nd">
                                                            <a>参与科研</a>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </c:when>
                                        </c:choose>
                                    </div>
                                    <div id="MagToolbar" style="display: none">
                                        <c:choose>
                                            <c:when test="${sessionScope.level == '1'}">
                                                <button class=" newMag btn btn-primary btn-sm">
                                                    <i class="ace-icon fa fa-plus bigger-160"></i>添加期刊
                                                </button>
                                            </c:when>
                                        </c:choose>
                                    </div>
                                    <div id="SearchToolbar" style="display: none">
                                        <c:choose>
                                            <c:when test="${sessionScope.level == '3'}">

                                                <div class="input-group">
                                                    <div class="input-group-btn">
                                                        <button type="button"
                                                                class="btn-sm btn dropdown-toggle viewOption"
                                                                data-toggle="dropdown" aria-haspopup="true"
                                                                aria-expanded="false">
                                                            类别 <span class="caret"></span></button>
                                                        <ul class="dropdown-menu">
                                                            <li class="viewType all"><a>所有</a></li>
                                                            <li class="viewType project"><a>科研项目</a></li>
                                                            <li class="viewType paper"><a>论文</a></li>
                                                            <li class="viewType book"><a>著作</a></li>
                                                            <li class="viewType patent"><a>专利</a></li>
                                                            <li class="viewType "><a>成果鉴定</a></li>
                                                            <li class="viewType "><a>成果转化</a></li>
                                                            <li class="viewType "><a>成果获奖</a></li>
                                                        </ul>
                                                    </div><!-- /btn-group -->
                                                    <select  class="form-control" id="viewUser"
                                                            aria-label="...">
                                                    </select>
                                                </div>
                                                <!-- /input-group -->

                                            </c:when>
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
    <jsp:include page="public/footer.jsp"/>
</div>


<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
    <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
</a>
<!-- /.main-container -->
<script src='<c:url value="/js/public/public.js"/>'></script>
<script src='<c:url value="/js/public/pubAllSRInfo.js"/>'></script>


<script>
    var viewTable = $('#mainTable');


</script>

<c:choose>
    <c:when test="${sessionScope.level == '1'}">
        <script src="<c:url value="/js/teacher/index.js"/>"></script>
    </c:when>
    <c:when test="${sessionScope.level == '2'}">
        <script src="<c:url value="/js/college/index.js"/>"></script>
    </c:when>
    <c:when test="${sessionScope.level == '3'}">
        <script src="<c:url value="/js/school/index.js"/>"></script>
    </c:when>
</c:choose>

<script>
    //类别更换
    $('.process').click(function (self) {
        var cls = $(this).attr('class').replace("process", "").trim();
        if (cls != 'open' && cls != '' && cls != 'process') {
            stateman.go('process.something.' + level, {param: {obj: cls}});
        }
    });

    //类别更换
    $('.entity').click(function (self) {
        var cls = $(this).attr('class').replace("entity", "").trim();
        if (cls != 'open' && cls != '' && cls != 'entity') {
            stateman.go('entity.something.' + level, {param: {obj: cls}});
        }
    });
    //点击链接
    $('.hyperlink').click(function (self) {
        var cls = $(this).attr('class').replace("hyperlink", "").trim();
        console.log(cls);
        stateman.go(cls);
    });
</script>


</body>
</html>