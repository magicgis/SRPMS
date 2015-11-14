<jsp:useBean id="ObjectMapper" scope="application" class="com.fasterxml.jackson.databind.ObjectMapper"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>项目信息-
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
                        <a href="<c:url value="/allSRInfo"/>">Home</a>
                    </li>
                    <li class="active">项目</li>
                </ul>
                <!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">
                    <form id="project" class="form-horizontal" role="form">
                        <div hidden="hidden">
                            <input type="text" name="id" id="projectId" value="${project.id}"/>
                            <input type="text" name="WF_Type" id="WF_Type" value="project"/>
                        </div>

                        <div id="projInfo" class="col-xs-12 col-md-6 widget-container-col ui-sortable">

                            <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
                                <div class="widget-header">
                                    <h4 class="widget-title">项目信息</h4>
                                </div>
                                <div class="widget-body ">
                                    <div class="widget-main">
                                        <div class="row">
                                            <div class="form-group col-xs-12 col-sm-6">
                                                <label class="col-sm-4 control-label no-padding-left"
                                                       for="code">项目代码</label>

                                                <div class="col-sm-8">
                                                    <input type="text" id="code" name="code"
                                                           placeholder="" class="col-xs-12" value="${project.code}"/>
                                                </div>
                                            </div>

                                            <div class="form-group col-xs-12 col-sm-6">
                                                <label class="col-sm-4 control-label no-padding-left"
                                                       for="name">项目名称</label>

                                                <div class="col-sm-8">
                                                    <input type="text" id="name" name="name"
                                                           placeholder="" class="col-xs-12" value="${project.name}"/>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
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

                                            <div class="form-group col-xs-12 col-sm-6">
                                                <label class="col-sm-4 control-label no-padding-left"
                                                       for="attr">项目属性</label>

                                                <div class="col-sm-8">
                                                    <div class="col-sm-13">
                                                        <input class="form-control" id="attr"
                                                               type="text" name="attr"
                                                               value=""
                                                               placeholder="请选择"/>
                                                    </div>

                                                </div>
                                            </div>

                                        </div>

                                        <div class="row">
                                            <div class="form-group col-xs-12 col-sm-6">
                                                <label class="col-sm-4 control-label no-padding-left"
                                                       for="isAppr">获得立项</label>

                                                <div class="col-sm-8">
                                                    <div class="col-sm-13">
                                                        <select class="form-control" id="isAppr" value="${project.isAppr}">
                                                            <option value=""></option>
                                                            <option value="1">是</option>
                                                            <option value="0">否</option>
                                                        </select>
                                                    </div>

                                                </div>
                                            </div>

                                            <div class="form-group col-xs-12 col-sm-6">
                                                <label class="col-sm-4 control-label no-padding-left"
                                                       for="appr_time">立项时间</label>

                                                <div class="col-sm-8">
                                                    <input class="form-control date-picker" id="appr_time"
                                                           type="text" name="apprDate"
                                                           data-date-format="yyyy-mm-dd" value="${project.apprDate}" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row standard1">
                                            <div class="form-group col-xs-12 col-sm-6">
                                                <label class="col-sm-4 control-label no-padding-left"
                                                       for="projtype">项目类别</label>

                                                <div class="col-sm-8">
                                                    <div class="col-sm-13">
                                                        <input class="form-control projStand" id="projtype"
                                                               type="text" name="projtype"
                                                               placeholder="请选择"/>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-group col-xs-12 col-sm-6">
                                                <label class="col-sm-4 control-label no-padding-left"
                                                        for="projrank">项目等级</label>

                                                <div class="col-sm-8">
                                                    <div class="col-sm-13">
                                                        <input class="form-control projStand"
                                                               type="text" id="projrank" name="projrank"
                                                               placeholder="请选择"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row standard1">
                                            <div class="form-group col-xs-12 col-sm-6">
                                                <label class="col-sm-4 control-label no-padding-left"
                                                       for="projorig">项目来源</label>

                                                <div class="col-sm-8">
                                                    <div class="col-sm-13">
                                                        <input class="form-control projStand" id="projorig"
                                                               type="text" name="projorig"
                                                               value=""
                                                               placeholder="请选择"/>
                                                    </div>

                                                </div>
                                            </div>
                                            <%--项目评分归属字段--%>
                                            <div class="form-group col-xs-12 col-sm-6">
                                                <label class="col-sm-4 control-label no-padding-left"
                                                       for="projbelong">项目归属</label>

                                                <div class="col-sm-8">
                                                    <div class="col-sm-13">
                                                        <input class="form-control projStand" id="projbelong"
                                                               type="text" name="standard.id"
                                                               placeholder="请选择"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row standard0">
                                            <div class="form-group col-xs-12 col-sm-6">
                                                <label class="col-sm-4 control-label no-padding-left"
                                                       for="projtype0">项目类别</label>

                                                <div class="col-sm-8">
                                                    <div class="col-sm-13">
                                                        <input class="form-control projStand" id="projtype0"
                                                               type="text" name="standard.id"
                                                               placeholder="请选择"/>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-group col-xs-12 col-sm-6">
                                                <label class="col-sm-4 control-label no-padding-left"
                                                       for="projorig0">项目来源</label>

                                                <div class="col-sm-8">
                                                    <div class="col-sm-13">
                                                        <input class="form-control projStand" id="projorig0"
                                                               type="text"
                                                               value=""
                                                               placeholder="请选择"/>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="form-group col-xs-12 col-sm-6">
                                                <label class="col-sm-4 control-label no-padding-left"
                                                       for="planSolTime">计划结题时间</label>

                                                <div class="col-sm-8">
                                                    <input class="form-control date-picker" id="planSolTime"
                                                           type="text" name="planDate"
                                                           data-date-format="yyyy-mm-dd" value="${project.planDate}"/>
                                                </div>
                                            </div>

                                            <div class="form-group col-xs-12 col-sm-6">
                                                <label class="col-sm-4 control-label no-padding-left"
                                                       for="realDate">实际结题时间</label>

                                                <div class="col-sm-8">
                                                    <input class="form-control date-picker" id="realDate"
                                                           type="text" name="realDate"
                                                           data-date-format="yyyy-mm-dd"  value="${project.realDate}" />
                                                </div>
                                            </div>
                                        </div>

                                        <%--<div class="row">--%>

                                            <%--<!--获奖字段-->--%>
                                            <%--<div class="form-group col-xs-12 col-sm-6">--%>
                                                <%--<label class="col-sm-4 control-label no-padding-left"--%>
                                                       <%--for="isAwdProj">是否获批</label>--%>

                                                <%--<div class="col-sm-8">--%>
                                                    <%--<div class="col-sm-13">--%>
                                                        <%--<select class="form-control" id="isAwdProj"--%>
                                                                <%--placeholder="请选择">--%>
                                                            <%--<option value=""></option>--%>
                                                            <%--<option value="1">是</option>--%>
                                                            <%--<option value="0">否</option>--%>
                                                        <%--</select>--%>
                                                    <%--</div>--%>

                                                <%--</div>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>

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
                            <div class="widget-box transparent ui-sortable-handle col-xs-12" style="opacity: 1;">
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
	                                                       name="score" id="totalScore" value="${project.score}">
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

                            <div class="col-xs-12" id="msg_alert"></div>

                        </div>

                        <div id="fundInfo" class="col-xs-12 col-md-6 widget-container-col ui-sortable">
                            <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
                                <div class="widget-header">
                                    <h4 class="widget-title">金额到账信息（单位：万元）</h4>
                                </div>
                                <div class="widget-body">
                                    <div class="widget-main">
                                        <div class="row">
                                            <div id="fundToolbar">
                                                <a class="btn btn-primary btn-sm addFund">
                                                    <i class="glyphicon glyphicon-plus"></i> 添加添加金额信息
                                                </a>
                                            </div>
                                            <table id="fundTable"
                                                   data-toolbar="#fundToolbar"
                                                   data-show-toggle="true"
                                                   data-show-footer="true"></table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div id="unitInfo" class="col-xs-12 col-md-6 col-xs-offset-6 widget-container-col ui-sortable">
                            <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
                                <div class="widget-header">
                                    <h4 class="widget-title">共有单位信息</h4>
                                </div>
                                <div class="widget-body">
                                    <div class="widget-main">
                                        <div class="row">
                                            <div id="unitToolbar">
                                                <a class="btn btn-primary btn-sm addUnit"><i
                                                        class="glyphicon glyphicon-plus"></i> 添加单位</a>
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

                        <div id="reply" class="col-xs-12 col-md-6 col-xs-offset-6 widget-container-col ui-sortable">
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

                    <div class="row">

                        <div id="formBtn" class="col-xs-12 clearfix">
                            <div class="pull-left onDel">
                                <c:if test="${sessionScope.level == '3'}">
                                 <button class="btn btn-danger tabOrdBtn del" type="button">
                                    <i class="ace-icon fa fa-trash  bigger-100"></i>
                                    删除
                                 </button>

                                 <button class="btn btn-danger tabOrdBtn orderBack" type="button">
                                    <i class="ace-icon fa  fa-repeat bigger-100"></i>
                                    撤回
                                 </button>
                                </c:if>
                            </div>
                            <div class="pull-right">
                                <span class="onEdit">
                                    <button class="confirm tabOrdBtn btn btn-success" type="button">
                                        <i class="ace-icon fa fa-check bigger-110"></i>
                                        确认
                                    </button>

                                    <button class="btn btn-info tabOrdBtn save" type="button">
                                        <i class="ace-icon fa fa-save bigger-110"></i>
                                        保存
                                    </button>
                                </span>
                                <span class="onApprove">
                                    <button class="btn btn-success tabOrdBtn Approve" type="button">
                                        <i class="ace-icon fa fa-check bigger-110"></i>
                                        通过
                                    </button>

                                    <button class="btn btn-danger tabOrdBtn Refuse" type="button">
                                        <i class="ace-icon fa fa-remove bigger-110"></i>
                                        驳回
                                    </button>
                                </span>
                                <button class="btn btn-success tabOrdBtn back" type="button">
                                    <i class="ace-icon fa fa-reply  bigger-110"></i>
                                    返回
                                </button>
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

<script>

    var actorTemp = [];
    var unitTemp = [];
    var fundTemp = [];
    var Main_Actor;
    var Main_ActorName;
    var filesData = {};
    var replyByCol, replyByDep;
    var StdList = [];
    var projtypeList = [];
</script>

<script src='<c:url value="/js/public/public.js"/>'></script>
<script src='<c:url value="/js/public/pubProject.js"/>'></script>

<script type="text/javascript">
    $(function ($) {
        $('.date-picker').datepicker({
            autoclose: true,
            todayHighlight: true
        }).next().on(ace.click_event, function () {
            $(this).prev().focus();
        });
    });
    $('.standard0').hide();
    // 成员，单位，文件
    var entity =  ${ObjectMapper.writeValueAsString(project)}; // 获得 entity 或 实体
    console.log(entity);
    if(isNull(entity)) {
        entity = {};
    }
    var all = entity['argMap']; // 获得 成员，单位，附件，负责人等信息
    var attr=entity['attr'];
    var dept = entity['dept'];
    var isAppr=entity['isAppr'];
    console.log(isAppr);
    var standard = entity['standard'];
    var taskId = '${taskId}';  // 获得 task的id
    var taskName = '${taskName}';

    if (!isNull(all)) {
        fullUpInfo(all,entity);//
    } else {
        all = {};
    }

    var approvalByCol = getApprovalByCol(all);
    if (approvalByCol !== "") {
        replyByCol = all[approvalByCol]['replyByCol'];
    }
    var approvalByDep = getApprovalByDep(all);
    if (approvalByDep !== "") {
        replyByDep = all[approvalByDep]['replyByDep'];
    }
    function getStdList(projectSet) { // 拦截standard表的数据
        if(!isNull(StdList)||!isNull(projtypeList)){
            StdList = [];projtypeList = [];
        }
        $.ajax({
            type: 'GET',
            async: false, // false
            url: '/api/standard/type/'+projectSet,
            dataType: 'json',
            contentType: 'application/json;charset=UTF-8',
            success: function (data) {
                StdList = data;
	            projtypeList = getList(StdList, 'projtype');
            }
        });
    }
    //getStdList();
    var $isAppr=$('#isAppr').selectize({ // 初始化 鉴定等级
        valueField: 'id',
        labelField: 'value',
        options: [
            {"id": "0", "value": "否"},
            {"id": "1", "value": "是"}],
        maxItems: 1,
        create: true,
        onChange:function(){
            var setProject=$('#isAppr').val();
            if(setProject=='1'){
                $('.standard0').hide();
                $('.standard1').show();
                var projectSet="项目立项";
                getStdList(projectSet);
                standardSelects1(StdList,projtypeList);
            }else if(setProject=='0'){
                $('.standard0').show();
                $('.standard1').hide();
                var projectSet="项目未获立项";
                getStdList(projectSet);
                standardSelects0(StdList);
            }
        }
    });
    allSections();
    getDept();//选择框
    upToLoadFile();//文件上传
    firstOrOther();//是否是联合单位

    if (filesData == null) {
        filesData = {};
    }
    if (dept != null||!isNull(attr)||!isNull(isAppr)) {  // 显示 所属部门
        var $dept = $('#dept').selectize();
        var $attr = $('#attr').selectize();
        addOptionSelectize($dept, [dept]);
        DisplayForm($dept, dept['id'], 0);
        DisplayForm($attr, attr,0);
    }
    if(!isNull(standard)){

        if(standard['type']=='项目立项'){
            DisplayForm($isAppr, "1",0);
            $('.standard0').hide();
            $('.standard1').show();
            var projectSet="项目立项";
            getStdList(projectSet);
            standardSelects1(StdList,projtypeList,standard);
        }else{
            DisplayForm($isAppr, "0",0);
//            $('#isAppr').val('0');
            $('.standard0').show();
            $('.standard1').hide();
            var projectSet="项目未获立项";
            getStdList(projectSet);
            standardSelects0(StdList,standard);
        }
    }

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
                formatter: "operateAFormatter",
                events: "operateAEvents"
            }],
        data: actorTemp
    });
    $('#fundTable').bootstrapTable({
        columns: [{
            field: 'time',
            title: '到账时间',
            sortable: true
        }, {
            field: 'mny',
            title: '到账金额',
            sortable: true,
            footerFormatter: "totalFundsFormatter"
        }, {
            field: 'outMny',
            title: '外拨金额',
            sortable: true,
            footerFormatter: "totalEFundFormatter"
        }, {
            field: 'operate',
            title: '操作',
            sortable: true,
            formatter: "operateFFormatter",
            events: "operateFEvents"
        }],
        data: fundTemp
    });
    $('#unitTable').bootstrapTable({
        columns: [{
            field: 'rank',
            title: '排名',
            sortable: true,
            footerFormatter: "totalUnitFormatter"
        }, {
            field: 'unit',
            title: '单位名称',
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
    //是否为第一单位
    $('#attr').change(function () {
        firstOrOther();
    });

    $('.back').click(function () {
        //$('#ProjectTable').bootstrapTable('refresh', {url: '/api/project/all'});
        history.go(-1);
    });
    //监听 添加成员
    $('.addActor').click(function () {
        addActor();
    });
    //监听 分配分数
    $('.getScore').click(function () {
        getScore('project');
    });
    //监听 添加单位
    $('.addUnit').click(function () {
        addUnit();
    });
    //监听 添加金额信息
    $('.addFund').click(function () {
        addFund();
    });
    //监听 点击保存||确认||撤回||删除
    $(".save").click(function () {
        save();
    });
    $(".confirm").click(function () {
        confirm();
    });
    $(".orderBack").click(function () {
        orderBack();
    });
    $(".del").click(function () {
        delOrder();
    });
    $(".Approve").click(function () {
        Approve();
    });
    $(".Refuse").click(function () {
        Refuse();
    });
    //选择
</script>
<c:choose>
    <c:when test="${sessionScope.level == '1'}">
        <script src="<c:url value="/js/teacher/projectEdit.js"/>"></script>
    </c:when>
    <c:when test="${sessionScope.level == '2'}">
        <script src="<c:url value="/js/college/projectEdit.js"/>"></script>
    </c:when>
    <c:when test="${sessionScope.level == '3'}">
        <script src="<c:url value="/js/school/projectEdit.js"/>"></script>
    </c:when>
</c:choose>
</html>

