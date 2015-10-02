<jsp:useBean id="ObjectMapper" scope="application" class="com.fasterxml.jackson.databind.ObjectMapper"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>期刊信息</title>

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
                        <a href="<c:url value="/allSRInfo"/>">Home</a>
                    </li>
                    <li class="active">期刊</li>
                </ul>
                <!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">
                    <form id="magazine" class="form-horizontal" role="form">
                        <div hidden="hidden">
                            <input type="text" name="WF_Task" id="WF_Task" value="${task.id}"/>
                            <input type="text" name="WF_Order" id="WF_Order" value="${task.orderId}"/>
                        </div>
                        <div id="magInfo" class="col-xs-12 col-md-10 widget-container-col ui-sortable">
                            <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
                                <div class="widget-header">
                                    <h4 class="widget-title">新期刊信息</h4>
                                </div>
                                <div class="widget-body ">
                                    <div class="widget-main">
                                        <div class="row">
                                            <div class="form-group col-xs-12 col-sm-6">
                                                <label class="col-sm-4 control-label no-padding-right"
                                                       for="name">刊物全名</label>

                                                <div class="col-sm-8">
                                                    <input type="text" name="name" id="name"
                                                           value="${task.variableMap.get("name")}"
                                                           class="col-xs-12 mag-input"/>
                                                </div>
                                            </div>
                                            <div class="form-group col-xs-12 col-sm-6">
                                                <label class="col-sm-4 control-label no-padding-left"
                                                       for="issn">ISSN</label>

                                                <div class="col-sm-8">
                                                    <input type="text" id="issn" name="issn"
                                                           value="${task.variableMap.get("issn")}" placeholder=""
                                                           class="col-xs-12 mag-input"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group col-xs-12 col-sm-6">
                                                <label class="col-sm-4 control-label no-padding-left"
                                                       for="cn">CN</label>

                                                <div class="col-sm-8">
                                                    <input type="text" id="cn" name="cn" placeholder=""
                                                           value="${task.variableMap.get("cn")}"
                                                           class="col-xs-12 mag-input"/>
                                                </div>
                                            </div>
                                            <div class="form-group col-xs-12 col-sm-6">
                                                <label class="col-sm-4 control-label no-padding-left"
                                                       for="magLevel">刊物类别</label>

                                                <div class="col-sm-8">
                                                    <input type="text" id="magLevel" name="sub"
                                                           value="${task.variableMap.get("sub")}" placeholder=""
                                                           class="col-xs-12 mag-input"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group col-xs-12 col-sm-6">
                                                <label class="col-sm-4 control-label no-padding-left"
                                                       for="type">收录类型</label>

                                                <div class="col-sm-8">
                                                    <input type="text" id="type" name="type"
                                                           value="${task.variableMap.get("type")}" placeholder=""
                                                           class="col-xs-12 mag-input"/>
                                                </div>
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
                                                <%--<i class="ace-icon fa fa-plus bigger-110"></i>--%>
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
                            <div id="magPaperBtn" class="pull-left" hidden="hidden">
                            </div>
                            <div class="pull-right">
                                <button class="btn btn-info btn-sm back" type="button">
                                    <i class="ace-icon fa fa-reply  bigger-110"></i>
                                    返回
                                </button>
                                <c:choose>
                                    <c:when test="${sessionScope.level == '1'}">
                                        <button class="confirm btn btn-success btn-sm teacher" type="button">
                                            <i class="ace-icon fa fa-check bigger-110"></i>
                                            提交
                                        </button>
                                    </c:when>
                                    <c:when test="${sessionScope.level == '3'}">
                                        <button class="confirm btn btn-success btn-sm school" type="button">
                                            <i class="ace-icon fa fa-check bigger-110"></i>
                                            通过
                                        </button>

                                        <button class="btn btn-danger btn-sm refuse" type="button">
                                            <i class="ace-icon fa fa-plus bigger-110"></i>
                                            驳回
                                        </button>
                                    </c:when>
                                </c:choose>

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
<script src='<c:url value="/js/public/pubMag.js"/>'></script>

<c:choose>
    <c:when test="${sessionScope.level == '1'}">
        <script src="<c:url value="/js/teacher/magEdit.js"/>"></script>
    </c:when>
    <c:when test="${sessionScope.level == '3'}">
        <script src="<c:url value="/js/school/magEdit.js"/>"></script>
    </c:when>
</c:choose>

</body>
<script>

    var status = '${task.taskName}';

    if (status == 'ApprovalByDep') {
        $('input').attr('disabled', 'disabled');
        $('.teacher').hide();
        // todo 禁用上传按钮
    }

    upToLoadFile();
    //真不容易
    var filesData = ${ObjectMapper.writeValueAsString(task.variableMap.get("filesData"))};

    if (filesData == null) {
        filesData = {};
    }

    scanFiles(filesData);


    $(".confirm").click(function () {
        confirm();
    });

    $('.back').click(function () {
        history.go(-1);
    });

    $('.refuse').click(function () {
        delOrder();
    })


</script>
</html>



