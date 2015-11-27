<%--
  Created by IntelliJ IDEA.
  User: zheng
  Date: 2015/10/12
  Time: 0:01
  To change this template use File | Settings | File Templates.
--%>
<jsp:useBean id="ObjectMapper" scope="application" class="com.fasterxml.jackson.databind.ObjectMapper"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>著作信息-
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
        /*td.bs-checkbox {*/
        /*display: none;*/
        /*}*/
        /*th.bs-checkbox {*/
        /*display: none;*/
        /*}*/
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
                    <li class="active">著作</li>
                </ul>
                <!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">
                    <form id="book" class="form-horizontal" role="form">
                        <div hidden="hidden">
                            <input type="text" name="id" id="bookId" value="${book.id}"/>
                            <input type="text" name="WF_Type" id="WF_Type" value="book"/>
                        </div>
                        <div id="newInstrumentsInfo" class="col-xs-12 col-md-6 widget-container-col ui-sortable">
                            <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
                                <div class="widget-header">
                                    <h4 class="widget-title">著作信息</h4>
                                </div>
                                <div class="widget-body ">
                                    <div class="widget-main">
                                        <div class="row">
                                            <div class="form-group col-xs-12 col-sm-6">
                                                <label class="col-sm-4 control-label no-padding-left"
                                                       for="bkName">著作名称</label>
                                                <div class="col-sm-8">
                                                    <input type="text" id="bkName" name="name"
                                                           value="${book.name}"
                                                           placeholder="" class="col-xs-12"/>
                                                </div>
                                            </div>
                                            <div class="form-group col-xs-12 col-sm-6">
                                                <label class="col-sm-4 control-label no-padding-left"
                                                       for="dept">所属部门</label>
                                                <div class="col-sm-8">
                                                    <div class="col-sm-13">
                                                        <input id="dept" name="dept.id"
                                                               type="text" class="form-control col-xs-12"
                                                               placeholder="请选择"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group col-xs-12 col-sm-6">
                                                <label class="col-sm-4 control-label no-padding-left"
                                                       for="pubType">出版类型</label>
                                                <div class="col-sm-8">
                                                    <input class="form-control" id="pubType"
                                                           type="text" name="pubType"
                                                           value="${book.pubType}"
                                                           placeholder="请选择"/>
                                                </div>
                                            </div>
                                            <div class="form-group col-xs-12 col-sm-6">
                                                <label class="col-sm-4 control-label no-padding-left"
                                                       for="pubDate">出版年月</label>
                                                <div class="col-sm-8">
                                                    <input type="text" id="pubDate" name="pubDate"
                                                           class="col-xs-12 date-picker"
                                                           value="${book.pubDate}"
                                                           placeholder="" data-date-format="yyyy-mm-dd"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group col-xs-12 col-sm-6">
                                                <label class="col-sm-4 control-label no-padding-left"
                                                       for="isbn">ISBN号</label>
                                                <div class="col-sm-8">
                                                    <input type="text" id="isbn" name="isbn"
                                                           value="${book.isbn}"
                                                           placeholder="" class="col-xs-12"/>
                                                </div>
                                            </div>
                                            <div class="form-group col-xs-12 col-sm-6">
                                                <label class="col-sm-4 control-label no-padding-left"
                                                       for="publisher">出版社</label>
                                                <div class="col-sm-8">
                                                    <input type="text" id="publisher" name="publisher"
                                                           value="${book.publisher}"
                                                           placeholder="" class="col-xs-12"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group col-xs-12 col-sm-6">
                                                <label class="col-sm-4 control-label no-padding-left"
                                                       for="isAward" placeholder="请选择"
                                                        >著作获奖</label>
                                                <div class="col-sm-8">
                                                    <input class="form-control" id="isAward"
                                                           type="text" name="isAward"
                                                           placeholder="请选择"/>
                                                </div>
                                            </div>

                                            <div class="form-group col-xs-12 col-sm-6">
                                                <label class="col-sm-4 control-label no-padding-left"
                                                       for="awarDtype">所获奖项</label>
                                                <div class="col-sm-8">
                                                    <input class="form-control" id="awarDtype"
                                                           type="text" name="awardtype"
                                                           placeholder="请选择"/>
                                                </div>
                                            </div>


                                        </div>
                                        <div class="row">
                                            <%--<div class="form-group col-xs-12 col-sm-6">--%>
                                                <%--<label class="col-sm-4 control-label no-padding-left"--%>
                                                       <%--for="year">计分年度</label>--%>
                                                <%--<div class="col-sm-8">--%>
                                                    <%--<input type="text" name="year" id="year"--%>
                                                           <%--value="${book.year}"--%>
                                                           <%--placeholder="" class="col-xs-12"/>--%>
                                                <%--</div>--%>
                                            <%--</div>--%>
                                            <div class="form-group col-xs-12 col-sm-6">
                                                <label class="col-sm-4 control-label no-padding-left"
                                                       for="bulDate">获奖时间</label>
                                                <div class="col-sm-8">
                                                    <input class="form-control date-picker" type="text" id="bulDate"
                                                           name="bulDate" value="${book.bulDate}"
                                                           data-date-format="yyyy-mm-dd" placeholder=""
                                                           class="col-xs-12"/>
                                                </div>
                                            </div>
                                        <%--</div>--%>
                                        <%--<div class="row">--%>
                                            <div class="form-group col-xs-12 col-sm-6">
                                                <label class="col-sm-4 control-label no-padding-left"
                                                       for="sumWord">著作总字数</label>
                                                <div class="col-sm-8">
                                                    <input type="text" name="sumWord" id="sumWord"
                                                           value="${book.sumWord}"
                                                           placeholder="单位：万字" class="col-xs-12"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-12">
                                                <div class="col-xs-12 widget-container-col ui-sortable" id="fileHead">
                                                    <div class="widget-box transparent ui-sortable-handle"
                                                         style="opacity: 1;">
                                                        <div class="widget-header">
                                                            <h4 class="widget-title">附件信息</h4>
                                                            <div class="widget-toolbar no-border">
                                                                <c:if test="${sessionScope.level == '3'}">
                                                                    <div id="upload">
                                                                    </div>
                                                                </c:if>
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
                        <div id="actorInfo" class="col-xs-12 col-md-6 widget-container-col ui-sortable">
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
                                                <c:choose>
                                                    <c:when test="${sessionScope.level == '3'}">
                                                        <a class="btn btn-primary btn-sm addActor">添加成员</a>
                                                    </c:when>
                                                </c:choose>

                                                <span class="giveSum">
                                                    <c:if test="${sessionScope.level == '3'}">
                                                        <a class="tabOrdBtn btn btn-primary btn-sm getScore">计算分数</a>
                                                    </c:if>
                                                    <label for="totalScore">总分：</label>
                                                    <input class="score" type="text"
                                                           name="score" id="totalScore" value="${book.score}">
                                                </span>
                                            </div>
                                            <table id="actorTable"
                                                   data-toolbar="#actorToolbar"
                                                   data-show-footer="true"
                                                   data-show-columns="false"
                                                   data-show-toggle="false">
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>

	                        <div class="col-xs-12" id="msg_alert"></div>
                        </div>
                        <div id="reply" class="col-xs-12 col-md-5 widget-container-col ui-sortable">
                            <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
                                <div class="widget-header">
                                    <h4 class="widget-title">批复</h4>
                                </div>
                                <div class="widget-body ">
                                    <div class="widget-main">
                                        <div class="row">
                                            <div class="col-xs-12">
                                                <textarea class="form-control" id="reply-box"></textarea>
                                                <blockquote class="pull-left" id="reply-display" hidden="hidden">
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
                    </form>
	                <div class="col-xs-12" id="info_alert"></div>
                    <div class="row">
                        <div id="formBtn" class="col-xs-12 clearfix">
                            <div class="pull-left onDel">
                                <c:if test="${sessionScope.level == '3'}">
                                    <button class="tabOrdBtn btn btn-danger btn-sm del"
                                            type="button">
                                        <i class="ace-icon fa fa-trash  bigger-110"></i>
                                        删除
                                    </button>
                                    <button class="tabOrdBtn btn btn-danger btn-sm orderBack"
                                            type="button">
                                        <i class="ace-icon fa  fa-repeat bigger-110"></i>
                                        撤回
                                    </button>
                                </c:if>
                            </div>

                            <div class="pull-right">
                                <button class="tabOrdBtn btn btn-danger btn-sm back"
                                        type="button">
                                    <i class="ace-icon fa fa-reply  bigger-110"></i>
                                    返回
                                </button>
                            <span class="onEdit">
                                <button class="tabOrdBtn btn btn-primary btn-sm save"
                                        type="button">
                                    <i class="ace-icon fa fa-save bigger-110"></i>
                                    保存
                                </button>
                                <button class="tabOrdBtn btn btn-success btn-sm confirm"
                                        type="button">
                                    <i class="ace-icon fa fa-check bigger-110"></i>
                                    确认
                                </button>
                            </span>
                            <span class="onApprove">
                                <button class="tabOrdBtn btn btn-success btn-sm Approve"
                                        type="button">
                                    <i class="ace-icon fa fa-check bigger-110"></i>
                                    通过
                                </button>
                                <button class="tabOrdBtn btn btn-warning btn-sm Refuse"
                                        type="button">
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
    <jsp:include page="public/footer.jsp"/>
</div>

<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
    <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
</a>
<!-- /.main-container -->
</body>
<script src='<c:url value="/js/public/public.js"/>'></script>
<script src='<c:url value="/js/public/pubBook.js"/>'></script>
<%--<script src='<c:url value="/js/public/pubEdit.js"/>'></script>--%>
<script src='<c:url value="/js/public/route.js"/>'></script>

<script type="text/javascript">
    $(function ($) {
        $('.date-picker').datepicker({
            autoclose: true,
            todayHighlight: true
        }).next().on(ace.click_event, function () {
            $(this).prev().focus();
        });
    });
    var entity = ${ObjectMapper.writeValueAsString(book)};
    console.log(entity);
    if(isNull(entity)) {
        entity = {};
    }
    var all = entity['argMap']; // 获得 成员，单位，附件，负责人等信息
    var isAward=all['isAward'];
    var dept = entity['dept'];
    var taskId = '${taskId}';  // 获得 task的id
    var taskName = '${taskName}';


    if (!isNull(all)) {
        fullUpInfoBook(all,entity);//
    } else {
        all = {};
    }
    getPubType();//选择框
    upToLoadFile();//文件上传
    getDept();

    //获得批复
    var approvalByCol = getApprovalByCol(all);
    if (approvalByCol != "") {
        replyByCol = all[approvalByCol]['replyByCol'];
    }
    var approvalByDep = getApprovalByDep(all);
    if (approvalByDep != "") {
        replyByDep = all[approvalByDep]['replyByDep'];
    }
    //IsAward();
    if (filesData == null) {
        filesData = {};
    }
    if (dept != null) {  // 显示 所属部门
        var $dept = $('#dept').selectize();
        addOptionSelectize($dept, [dept]);
        DisplayForm($dept, dept['id'], 0);
    }
    $('#actorTable').bootstrapTable({
        columns: [
            {
                field: 'staff.id',
                title: '工号',
                sortable: true,
                visible: true
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
            },  {
                field: 'textNumber',
                title: '承担字数',
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
    $('.addActor').click(function () {
        addActor();
    });
    $('#isAward').change(function () {
	    IsAward();
    });
    //监听 分配分数
    $('.getScore').click(function () {
        getScore();
    });
    //监听 点击保存
    $(".save").click(function () {
        save();
    });
    //监听 点击确认
    $(".confirm").click(function () {
        confirm();
    });
    //监听 点击返回
    $(".back").click(function () {
        history.go(-1);
    });
    //监听 删除
    $(".del").click(function () {
        delOrder();
    });
    //监听 点击撤回
    $(".orderBack").click(function () {
        getOrderBack();
    });
    //监听 点击通过
    $(".Approve").click(function () {
        Approve();
    });
    //监听 点击驳回
    $(".Refuse").click(function () {
        refuse();
    });
    $('.getScore').click(function () {
//        getFormData('project');
        saveStep1().success(function(data) {
            saveStep2(data).success(function (res) {
                getScore('book');
            });
        });
    });
</script>
<c:choose>
    <c:when test="${sessionScope.level == '1'}">
        <script src="<c:url value="/js/teacher/bookEdit.js"/>"></script>
    </c:when>
    <c:when test="${sessionScope.level == '2'}">
        <script src="<c:url value="/js/college/bookEdit.js"/>"></script>
    </c:when>
    <c:when test="${sessionScope.level == '3'}">
        <script src="<c:url value="/js/school/bookEdit.js"/>"></script>
    </c:when>
</c:choose>
</html>