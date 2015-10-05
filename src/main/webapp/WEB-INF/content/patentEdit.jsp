<jsp:useBean id="ObjectMapper" scope="application" class="com.fasterxml.jackson.databind.ObjectMapper"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: guofan
  Date: 2015/10/2
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>

    <meta name="description" content="Dynamic tables and grids using jqGrid plugin"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>专利信息</title>
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
                    <li class="active">专利</li>
                </ul>
                <!-- /.breadcrumb -->
            </div>

            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="widget-box transparent " style="opacity: 1;" id="newPatentTable-box">
                            <div class="widget-body" id="publicArea">

                                <form id="patent" class="form-horizontal" role="form">
                                    <div hidden="hidden">
                                        <%--todo 根据需求来添加相应的隐藏输入框--%>
                                        <%--<input type="text" name="WF_Order" id="WF_Order"/>--%>
                                        <%--<input type="text" name="WF_Task" id="WF_Task"/>--%>
                                        <%--<input type="text" name="IsComplete" id="IsComplete"/>--%>
                                        <%--<input type="text" name="pscore" id="score"/>--%>
                                        <input type="text" name="dept.value" id="deptValue"
                                               value="${patent.dept.value}"/>
                                        <%--<input type="text" name="patent.standard.value" id="patTypeValue"/>--%>
                                        <input type="text" name="id" id="patentId" value="${patent.id}"/>
                                    </div>
                                    <div id="patentInfo" class="col-xs-12 col-md-6 widget-container-col ui-sortable">
                                        <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
                                            <div class="widget-header">
                                                <h4 class="widget-title">专利信息</h4>
                                            </div>
                                            <div class="widget-body ">
                                                <div class="widget-main">
                                                    <div class="row">
                                                        <div class="form-group col-xs-12">
                                                            <label class="col-sm-2 control-label no-padding-left"
                                                                   for="name">专利名称</label>

                                                            <div class="col-sm-9">
                                                                <input id="name" name="name"
                                                                       type="text" class="form-control col-xs-12"
                                                                       placeholder="请选择" value="${patent.name}"/>
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
                                                                   for="patentNo">专利号</label>

                                                            <div class="col-sm-8">
                                                                <input id="patentNo" name="patentNo"
                                                                       type="text" class="col-xs-12" placeholder=""
                                                                       value="${patent.patentNo}"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group col-xs-12 col-sm-6">
                                                            <label class="col-sm-4 control-label no-padding-left"
                                                                   for="patentPubNo">公开号</label>

                                                            <div class="col-sm-8">
                                                                <input id="patentPubNo" name="patentPubNo"
                                                                       type="text" class="col-xs-12" placeholder=""
                                                                       value="${patent.patentPubNo}"/>
                                                            </div>
                                                        </div>
                                                        <div class="form-group col-xs-12 col-sm-6">
                                                            <label class="col-sm-4 control-label no-padding-left"
                                                                   for="patType">专利类型</label>

                                                            <div class="col-sm-8">
                                                                <input id="patType" name="standard.id"
                                                                       type="text" class="form-control col-xs-12"
                                                                       placeholder="请选择"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group col-xs-12 col-sm-6">
                                                            <label class="col-sm-4 control-label no-padding-left"
                                                                   for="apprDate">获批时间</label>

                                                            <div class="col-sm-8">
                                                                <input id="apprDate" name="apprDate"
                                                                       type="text"
                                                                       class="form-control col-xs-12 date-picker"
                                                                       data-date-format="yyyy-mm-dd"
                                                                       value="${patent.apprDate}"/>
                                                            </div>
                                                        </div>
                                                        <div class="form-group col-xs-12 col-sm-6">
                                                            <label class="col-sm-4 control-label no-padding-left"
                                                                   for="pubDate">公告时间</label>

                                                            <div class="col-sm-8">
                                                                <input id="pubDate" name="pubDate"
                                                                       type="text"
                                                                       class="form-control col-xs-12 date-picker"
                                                                       data-date-format="yyyy-mm-dd"
                                                                       value="${patent.pubDate}"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

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
                                                    <button class="tabOrdBtn btn btn-danger btn-sm back" type="button">
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


</body>
<script src='<c:url value="/js/public/public.js"/>'></script>

<script>
    var actorTemp = [];
    var unitTemp = [];


    // 成员，单位，文件
    var all = '${ObjectMapper.writeValueAsString(patent.argMap)}';
    console.log(all);
    var filesData = {};
    if (!isNull(all)) {
        all = jQuery.parseJSON(all);
        filesData = all['filesData'];
        actorTemp = all['actors'];
        unitTemp = all['units'];
    }
    upToLoadFile();

    if (filesData == null) {
        filesData = {};
    }
    scanFiles(filesData);

    //选择框
    var dept = '${ObjectMapper.writeValueAsString(patent.dept)}';
    var standardId = '${ObjectMapper.writeValueAsString(patent.standard)}';
    dept = jQuery.parseJSON(dept);


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

    $('#patType').selectize({ //todo
        valueField: 'id',
        labelField: 'value',
        maxItems: 1,
        //todo 需要换成真实数据
        options: [
            {"id": "1023", "value": "国际发明专利"},
            {"id": "1024", "value": "中国发明专利"},
            {"id": "1025", "value": "外观专利"},
            {"id": "1026", "value": "实用专利"}],
        onChange: function (result) {
            $('#patTypeValue').val(this.getItem(result)["context"]["innerHTML"]);
        }
    });
    $('#dept').selectize({
        valueField: 'id',
        labelField: 'value',
        maxItems: 1,
        preload: true,
        load: function (query, callback) {
            $.ajax({
                url: '../api/baseinfo/院系',
                type: 'GET',
                dataType: 'json',
                error: function () {
                    callback();
                },
                success: function (res) {
                    callback(res);
                }
            });
        },
        onChange: function (result) {
            $('#deptValue').val(this.getItem(result)["context"]["innerHTML"]);
        }
    });


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
<!-- /.main-container -->
<script src='<c:url value="/js/public/pubPatent.js"/>'></script>
<c:choose>
    <c:when test="${sessionScope.level == '1'}">
        <script src="<c:url value="/js/teacher/patentEdit.js"/>"></script>
    </c:when>
    <c:when test="${sessionScope.level == '2'}">
        <script src="<c:url value="/js/college/patentEdit.js"/>"></script>
    </c:when>
    <c:when test="${sessionScope.level == '3'}">
        <script src="<c:url value="/js/school/patentEdit.js"/>"></script>
    </c:when>
</c:choose>
</html>