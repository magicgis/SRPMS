<%--
  Created by IntelliJ IDEA.
  User: huyuanyuan555
  Date: 2015/10/3
  Time: 10:13
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
    <title>成果鉴定信息-
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
                        <a href="#">Home</a>
                    </li>
                    <li class="active">成果鉴定</li>
                </ul>
                <!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">

                    <div class="col-xs-12">
                        <form id="appraise" class="form-horizontal" role="form">
                            <div hidden="hidden">
                                <input type="text" name="id" id="achAppraiseId" value="${achAppraisal.id}"/>
                                <input type="text" name="WF_Type" id="WF_Type" value="achAppraisal"/>
                            </div>

                            <div class="col-xs-12 col-md-6">

                                <div id="achInfo" class="col-xs-12 widget-container-col ui-sortable">
                                    <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
                                        <div class="widget-header">
                                            <h4 class="widget-title">成果信息</h4>
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
                                                                   placeholder="" value="${achAppraisal.name}"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="form-group col-xs-12 col-sm-6">
                                                        <label class="col-sm-4 control-label no-padding-left"
                                                               for="dept">所属部门</label>

                                                        <div class="col-sm-8"><!--选择框-->
                                                            <input class="form-control" id="dept"
                                                                   name="dept.id" placeholder="请选择"/>
                                                        </div>
                                                    </div>

                                                    <div class="form-group col-xs-12 col-sm-6">
                                                        <label class="col-sm-4 control-label no-padding-left"
                                                               for="regDate">登记日期</label>

                                                        <div class="col-sm-8">
                                                            <input type="text" id="regDate" name="regDate"
                                                                   class="form-control col-xs-12 date-picker"
                                                                   data-date-format="yyyy-mm-dd"
                                                                   placeholder="" value="${achAppraisal.regDate}"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </div>

                                <div id="appraiseInfo" class="col-xs-12 widget-container-col ui-sortable">
                                    <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
                                        <div class="widget-header">
                                            <h4 class="widget-title">鉴定信息</h4>
                                        </div>
                                        <div class="widget-body ">
                                            <div class="widget-main">
                                                <div class="row">
                                                    <div class="form-group col-xs-12 col-sm-6">
                                                        <label class="col-sm-4 control-label no-padding-left"
                                                               for="certifyUnit">鉴定单位
                                                        </label>

                                                        <div class="col-sm-8">
                                                            <input id="certifyUnit" name="certifyUnit"
                                                                   type="text" class="form-control col-xs-12"
                                                                   placeholder="" value="${achAppraisal.certifyUnit}"/>
                                                        </div>
                                                    </div>

                                                    <div class="form-group col-xs-12 col-sm-6">
                                                        <label class="col-sm-4 control-label no-padding-left"
                                                               for="certificateNo">证书号</label>

                                                        <div class="col-sm-8">
                                                            <input id="certificateNo" name="certificateNo"
                                                                   type="text" class="form-control col-xs-12"
                                                                   placeholder=""
                                                                   value="${achAppraisal.certificateNo}"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="form-group col-xs-12 col-sm-6">
                                                        <label class="col-sm-4 control-label no-padding-left"
                                                               for="date">鉴定日期</label>

                                                        <div class="col-sm-8">
                                                            <input class="form-control date-picker"
                                                                   name="date" id="date" type="text"
                                                                   data-date-format="yyyy-mm-dd"
                                                                   value="${achAppraisal.date}"/>
                                                        </div>
                                                    </div>
                                                    <div class="form-group col-xs-12 col-sm-6">
                                                        <label class="col-sm-4 control-label no-padding-left"
                                                               for="way">鉴定形式</label>

                                                        <div class="col-sm-8">
                                                            <input type="text" id="way" name="way"
                                                                   placeholder="" class="form-control col-xs-12"
                                                                   value="${achAppraisal.way}"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="form-group col-xs-12 col-sm-6">
                                                        <label class="col-sm-4 control-label no-padding-left"
                                                               for="appType">鉴定类别</label>

                                                        <div class="col-sm-8"><!--选择框-->
                                                            <input id="appType" name="appType"
                                                                   class="form-control" placeholder="请选择"/>
                                                        </div>
                                                    </div>
                                                    <div class="form-group col-xs-12 col-sm-6">
                                                        <label class="col-sm-4 control-label no-padding-left"
                                                               for="appRank">鉴定等级</label>

                                                        <div class="col-sm-8"><!--选择框-->
                                                            <input id="appRank" name="standard.id"
                                                                   class="form-control" placeholder="请选择"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-xs-12 widget-container-col ui-sortable"
                                     id="fileHead">
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
                                                        <c:choose>
                                                            <c:when test="${sessionScope.level == '3'}">
                                                                <a class="btn btn-primary btn-sm addActor">添加成员</a>
                                                            </c:when>
                                                        </c:choose>

                                                        <span class="giveSum">
                                                            <button class="tabOrdBtn btn btn-primary btn-sm getScore">计算分数</button>
                                                            <label for="totalScore">原则上可分配总分：</label>
                                                            <input class="score" type="text"
                                                                   name="score" id="totalScore" value="${achAppraisal.score}">
                                                        </span>
                                                    </div>
                                                    <table id="actorTable"
                                                           data-toolbar="#actorToolbar"
                                                           data-show-footer="true"
                                                           data-show-columns="false"
                                                           data-show-toggle="false"></table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12" id="msg_alert"></div>
                                <div id="unitInfo" class="col-xs-12 widget-container-col ui-sortable">
                                    <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
                                        <div class="widget-header">
                                            <h4 class="widget-title">共有单位信息</h4>
                                        </div>

                                        <div class="widget-body">
                                            <div class="widget-main">
                                                <div class="row">
                                                    <div id="unitToolbar">
                                                        <c:if test="${sessionScope.level == '3'}">
                                                            <a class="btn btn-primary btn-sm addUnit">
                                                                添加单位</a>
                                                        </c:if>
                                                    </div>
                                                    <table id="unitTable"
                                                           data-toolbar="#unitToolbar"
                                                           data-show-footer="true"
                                                           data-show-columns="false"
                                                           data-show-toggle="false"></table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>


                        </form>

                        <div id="reply" class="col-xs-12 col-xs-offset-6 col-md-6 widget-container-col ui-sortable">
                            <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
                                <div class="widget-header">
                                    <h4 class="widget-title">批复</h4>
                                </div>
                                <div class="widget-body ">
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

                    </div>

                    <div class="col-xs-12" id="info_alert"></div>

                </div>

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
                                <button class="tabOrdBtn btn btn-success btn-sm approve"
                                        type="button">
                                    <i class="ace-icon fa fa-check bigger-110"></i>
                                    通过
                                </button>
                                <button class="tabOrdBtn btn btn-warning btn-sm refuse"
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
    <!-- /.main-content -->
    <jsp:include page="public/footer.jsp"/>

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
    </a>
</div>
</body>

<script>

    var actorTemp = [];
    var unitTemp = [];
    var Main_Actor;
    var Main_ActorName;
    var filesData = {};
    var status;
    var replyByCol, replyByDep;

</script>

<script src='<c:url value="/js/public/public.js"/>'></script>
<script src='<c:url value="/js/public/pubAppraise.js"/>'></script>
<script src='<c:url value="/js/public/route.js"/>'></script>

<script>


    var StdList = [];
    var appTypeList = [];
    function getStdList() { // 拦截standard表的数据
        $.ajax({
            type: 'GET',
            async: false, // false
            url: '/api/standard/type/成果鉴定',
            dataType: 'json',
            contentType: 'application/json;charset=UTF-8',
            success: function (data) {
                StdList = data;
                appTypeList = getList(StdList, 'jdprop');
            }
        });
    }
    getStdList(); // 获取成果鉴定的standard待填充
    getDept();   // 初始化 学院
    upToLoadFile(); // 初始化 上传文件的
    var $appRank = $("#appRank").selectize({ // 初始化 鉴定等级
        valueField: 'id',
        labelField: 'value',
        maxItems: 1
    });
    var $appType = $("#appType").selectize({ // 初始化 鉴定类型
        valueFieled: 'value',
        labelField: 'value',
        options: appTypeList,
        maxItems: 1,
        onChange: function (result) { // onChange时间 绑定级联

            var appRankList = [];
            appRankList = getStandardList(StdList, 'jdprop', 'jdtype', result);
            $appRank[0].selectize.clearOptions();
            $appRank[0].selectize.addOption(appRankList);
        }
    });

    // 成员，单位，文件
    var entity =  ${ObjectMapper.writeValueAsString(achAppraisal)}; // 获得 order 或 实体
    var userLevel = ${sessionScope.level};
    var isMain = 0;

    var all = entity['argMap']; // 获得 成员，单位，附件，负责人等信息
    var dept = entity['dept'];
    var standard = entity['standard'];
    var taskId = '${taskId}';  // 获得 task的id
    var taskName = '${taskName}';

    if (!isNull(all)) {
        filesData = all['filesData'];
        unitTemp = all['units'];
        Main_Actor = all['Main-Actor'];
        if (Main_Actor == userName) {
            isMain = 1;
        }
        status = all['Status'];
        Main_ActorName = all['Main-ActorName'];
    }

    var approvalByCol = getApprovalByCol(all);
    if (approvalByCol !== "") {
        replyByCol = all[approvalByCol]['replyByCol'];
    }
    var approvalByDep = getApprovalByDep(all);
    if (approvalByDep !== "") {
        replyByDep = all[approvalByDep]['replyByDep'];
    }

    // 显示 附件
    if (filesData == null) {
        filesData = {};
    }
    showFiles(filesData);

    getActors(); // 这是取成员的

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
    $('#unitTable').bootstrapTable({
        columns: [{
            field: 'rank',
            title: '排名',
            editable: false,
            sortable: true,
            footerFormatter: "totalUnitFormatter"
        }, {
            field: 'unit',
            title: '单位名称',
            editable: false,
            sortable: true
        }, {
            field: 'operate',
            title: '操作',
            sortable: false,
            formatter: "operateFormatterUnit",
            events: "operateEventsUnit"
        }],
        data: unitTemp
    });
    if (!isNull(standard)) {
        DisplayForm($appType, standard['infoMap']['jdprop'], 0);
        DisplayForm($appRank, standard['id'], 0);
    }

    if (!isNull(dept)) {  // 显示 所属部门
        var $dept = $('#dept').selectize();
        addOptionSelectize($dept, [dept]);
        DisplayForm($dept, dept['id'], 0);
    }

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
    $('.orderBack').click(function () {
        getOrderBack();
    });
    //监听 添加单位
    $('.addUnit').click(function () {
        addUnit();
    });
    //监听 添加成员
    $('.addActor').click(function () {
        addActor();
    });
    //监听 分配分数
    $('.getScore').click(function () {
        getScore('appraise');
    });


</script>

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
</html>