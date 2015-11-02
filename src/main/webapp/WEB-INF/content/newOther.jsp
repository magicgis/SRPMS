<%--
  Created by IntelliJ IDEA.
  User: zheng
  Date: 2015/11/2
  Time: 15:28
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
    <title>其他新产品</title>

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
                    <li class="active">其他新产品</li>
                </ul>
                <!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">
                    <form id="other" class="form-horizontal" role="form">
                        <div hidden="hidden">
                            <input type="text" name="id" id="otherId" value="${other.id}"/>
                        </div>
                        <div id="achInfo" class="col-xs-12 col-md-7 widget-container-col ui-sortable">
                            <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
                                <div class="widget-header">
                                    <h4 class="widget-title">其他新产品信息</h4>
                                </div>
                                <div class="widget-body ">
                                    <div class="widget-main">
                                        <div class="row">
                                            <div class="form-group col-xs-12 col-sm-6">
                                                <label class="col-sm-4 control-label no-padding-left"
                                                       for="name">产品名称</label>

                                                <div class="col-sm-8">
                                                    <input type="text" id="name" name="name"
                                                           placeholder="" class="col-xs-12" value=""/>
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
                                                       for="otherProdNo">生产许可编号</label>

                                                <div class="col-sm-8">
                                                    <input type="text" id="otherProdNo" name="otherProdNo"
                                                           placeholder="" class="col-xs-12"/>
                                                </div>
                                            </div>

                                            <div class="form-group col-xs-12 col-sm-6">
                                                <label class="col-sm-4 control-label no-padding-left"
                                                       for="date">获批时间</label>

                                                <div class="col-sm-8">
                                                    <input class="form-control date-picker" id="date"
                                                           type="text" name="date"
                                                           data-date-format="yyyy-mm-dd" value="" />
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="form-group col-xs-12 col-sm-6">
                                                <label class="col-sm-4 control-label no-padding-left"
                                                       for="firstUnit">第一研发单位</label>

                                                <div class="col-sm-8">
                                                    <div class="col-sm-13">
                                                        <select class="form-control" id="firstUnit" name="firstUnit"
                                                                placeholder="请选择">
                                                            <option value=""></option>
                                                            <option value="1">是</option>
                                                            <option value="0">否</option>
                                                        </select>
                                                    </div>

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
                        <div id="actorInfo" class="col-xs-12 col-md-5 widget-container-col ui-sortable">
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
                                                        <a data-toggle="modal" id="addActor"
                                                           class="btn btn-primary btn-sm
                                                           glyphicon glyphicon-plus">添加成员</a>
                                                    </c:when>
                                                    <c:when test="${sessionScope.level == '1'}">
                                                        <a data-toggle="modal" id="getScore"
                                                           class="btn btn-primary btn-sm">计算分数</a>

                                                    </c:when>
                                                </c:choose>
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
                        </div>
                        <div id="unitInfo" class="col-xs-12 col-md-5 widget-container-col ui-sortable">
                            <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
                                <div class="widget-header">
                                    <h4 class="widget-title">共有单位信息</h4>
                                </div>
                                <div class="widget-body">
                                    <div class="widget-main">
                                        <div class="row">
                                            <div id="unitToolbar">
                                                <a class="btn btn-primary btn-sm" id="addUnit"><i
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
                        <div id="reply" class="col-xs-12 col-md-5 col-xs-offset-7 widget-container-col ui-sortable">
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
                                    <button class="btn btn-danger del" type="button">
                                        <i class="ace-icon fa fa-trash  bigger-100"></i>
                                        删除
                                    </button>
                                    &nbsp;
                                    <button class="btn btn-danger orderBack" type="button">
                                        <i class="ace-icon fa  fa-repeat bigger-100"></i>
                                        撤回
                                    </button>
                                </c:if>
                            </div>
                        <div class="pull-right">
                        <span class="onEdit">
                              <button class="confirm btn btn-success" type="button">
                                  <i class="ace-icon fa fa-check bigger-110"></i>
                                  确认
                              </button>

                              <button class="btn btn-info save" type="button">
                                  <i class="ace-icon fa fa-save bigger-110"></i>
                                  保存
                              </button>
                        </span>
                        <span class="onApprove">
                            <button class="btn btn-success Approve" type="button">
                                <i class="ace-icon fa fa-check bigger-110"></i>
                                通过
                            </button>

                           <button class="btn btn-danger Refuse" type="button">
                               <i class="ace-icon fa fa-remove bigger-110"></i>
                               驳回
                           </button>
                        </span>
                           <button class="btn btn-success back" type="button">
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
<!-- /.main-content -->


<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
    <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
</a>
<!-- /.main-container -->
</body>
<script src='<c:url value="/js/public/public.js"/>'></script>
<script src='<c:url value="/js/public/pubMedic.js"/>'></script>
<script type="text/javascript">
    $(function ($) {
        $('.date-picker').datepicker({
            autoclose: true,
            todayHighlight: true
        }).next().on(ace.click_event, function () {
            $(this).prev().focus();
        });
    });
    $('#instruType').focus(function () {
        $('#explainInfo').show();
    });
    $('#instruType').blur(function () {
        $('#explainInfo').hide();
    });
    // 成员，单位，文件
    // todo 取出实体内的额外信息，附件信息也应该在其中。
    var entity = ${ObjectMapper.writeValueAsString(newOther)};
    var all = ${ObjectMapper.writeValueAsString(newOther.argMap)};
    var dept = entity['dept'];
    var taskId = '${taskId}';
    var taskName = '${taskName}';
    upToLoadFile();//文件上传
    getDept();
    fullUpInfo(all, entity);//填充
    var filesData;
    if (filesData == null) {
        filesData = {};
    }
    if (dept !== null) {  // 显示 所属部门
        var $dept = $('#dept').selectize();
        addOptionSelectize($dept, [dept]);
        DisplayForm($dept, dept['id'], 0);
    }
    //是否为第一单位
    $('#firstUnit').change(function () {
        if ($('#firstUnit').val() == '1') {
            $('#unitInfo').show();
        } else {
            $('#unitInfo').hide();
        }
    });
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
    $('.back').click(function () {
        history.go(-1);
    });
    //监听 添加成员
    $('#addActor').click(function () {
        addActor();
    });
    //监听 分配分数
    $('#getScore').click(function () {
        getScore();
    });
    //监听 添加单位
    $('#addUnit').click(function () {
        addUnit();
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
</script>
<c:choose>
    <c:when test="${sessionScope.level == '1'}">
        <script src="<c:url value="/js/teacher/newOtherEdit.js"/>"></script>
    </c:when>
    <c:when test="${sessionScope.level == '2'}">
        <script src="<c:url value="/js/college/newOtherEdit.js"/>"></script>
    </c:when>
    <c:when test="${sessionScope.level == '3'}">
        <script src="<c:url value="/js/school/newOtherEdit.js"/>"></script>
    </c:when>
</c:choose>
</html>






