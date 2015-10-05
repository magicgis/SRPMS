<jsp:useBean id="ObjectMapper" scope="application" class="com.fasterxml.jackson.databind.ObjectMapper"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>项目信息</title>

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

        input.ace.ace-switch.ace-switch-7[type="checkbox"] + .lbl::before {
            content: "启动 \a0\a0\a0\a0\a0\a0\a0\a0\a0\a0\a0\a0停止";
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
                    <form id="project" class="form-horizontal" role="form">
                        <div hidden="hidden">
                            <input type="text" name="dept.value" id="deptValue" value="${patent.dept.value}"/>
                            <input type="text" name="WF_Task" id="WF_Task" value="${task.id}"/>
                            <%--<input type="text" name="WF_Order" id="WF_Order" value="${task.orderId}"/>--%>
                            <%--<input type="text" name="IsComplete" id="IsComplete"/>--%>
                            <%--<input type="text" name="score" id="score"/>--%>
                            <input type="text" name="standard.id" id="standardId" value="${patent.stand.id}"/>
                            <input type="text" name="id" id="projectId" value="${patent.id}"/>
                        </div>
                        <div id="projInfo" class="col-xs-12 col-md-7 widget-container-col ui-sortable">
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
                                                           value=""
                                                           placeholder="" class="col-xs-12"/>
                                                </div>
                                            </div>

                                            <div class="form-group col-xs-12 col-sm-6">
                                                <label class="col-sm-4 control-label no-padding-left"
                                                       for="name">项目名称</label>

                                                <div class="col-sm-8">
                                                    <input type="text" id="name" name="name"
                                                           value=""
                                                           placeholder="" class="col-xs-12"/>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="form-group col-xs-12 col-sm-6">
                                                <label class="col-sm-4 control-label no-padding-left"
                                                       for="type">项目类别</label>

                                                <div class="col-sm-8">
                                                    <div class="col-sm-13">
                                                        <input class="form-control" id="type"
                                                               type="text" name="type"
                                                               value=""
                                                               placeholder="请选择"/>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-group col-xs-12 col-sm-6">
                                                <label class="col-sm-4 control-label no-padding-left"
                                                       for="rank">项目等级</label>

                                                <div class="col-sm-8">
                                                    <div class="col-sm-13">
                                                        <input class="form-control" id="rank"
                                                               type="text" name="rank"
                                                               value=""
                                                               placeholder="请选择"/>
                                                    </div>
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
                                                       for="rateUnit">项目来源</label>

                                                <div class="col-sm-8">
                                                    <div class="col-sm-13">
                                                        <input class="form-control" id="rateUnit"
                                                               type="text" name="rateUnit"
                                                               value=""
                                                               placeholder="请选择"/>
                                                    </div>

                                                </div>
                                            </div>
                                            <%--项目评分归属字段--%>
                                            <div class="form-group col-xs-12 col-sm-6">
                                                <label class="col-sm-4 control-label no-padding-left"
                                                       for="rateSrc">项目归属</label>

                                                <div class="col-sm-8">
                                                    <div class="col-sm-13">
                                                        <input class="form-control" id="rateSrc"
                                                               type="text" name="rateSrc"
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
                                                           value=""
                                                           data-date-format="yyyy-mm-dd"/>
                                                </div>
                                            </div>
                                            <div class="form-group col-xs-12 col-sm-6">
                                                <label class="col-sm-4 control-label no-padding-left"
                                                       for="isAppr">获得立项</label>

                                                <div class="col-sm-8">
                                                    <div class="col-sm-13">
                                                        <input class="form-control" id="isAppr"
                                                               type="text" name="isAppr"
                                                               value=""
                                                               placeholder="请选择"/>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">

                                            <div class="form-group col-xs-12 col-sm-6">
                                                <label class="col-sm-4 control-label no-padding-left"
                                                       for="appr_time">立项时间</label>

                                                <div class="col-sm-8">
                                                    <input class="form-control date-picker" id="appr_time"
                                                           type="text" name="apprDate"
                                                           value=""
                                                           data-date-format="yyyy-mm-dd"/>
                                                </div>
                                            </div>
                                            <div class="form-group col-xs-12 col-sm-6">
                                                <label class="col-sm-4 control-label no-padding-left"
                                                       for="real_time">实际结题时间</label>

                                                <div class="col-sm-8">
                                                    <input class="form-control date-picker" id="real_time"
                                                           type="text" name="realDate"
                                                           value=""
                                                           data-date-format="yyyy-mm-dd"/>
                                                </div>
                                            </div>
                                        </div>


                                        <div class="row">

                                            <!--获奖字段-->
                                            <div class="form-group col-xs-12 col-sm-6">
                                                <label class="col-sm-4 control-label no-padding-left"
                                                       for="isAwdProj">是否获奖</label>

                                                <div class="col-sm-8">
                                                    <div class="col-sm-13">
                                                        <input class="form-control" id="isAwdProj"
                                                               type="text" name="isAwdProj"
                                                               value=""
                                                               placeholder="请选择"/>
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
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="actorInfo" class="col-xs-12 col-md-5 widget-container-col ui-sortable">
                            <div class="widget-box transparent ui-sortable-handle col-xs-12" style="opacity: 1;">
                                <div class="widget-header" id="actorInfoHeader">
                                    <h4 class="widget-title">参与人员</h4>
                                    <span id="showSum" style="font-size: 15px"></span>
                                </div>
                                <div class="widget-body">
                                    <div class="widget-main">
                                        <div class="row">
                                            <div id="actorToolbar">
                                                <a data-toggle="modal"
                                                   class="btn btn-primary btn-sm addActor">添加成员</a>
                                                <a data-toggle="modal"
                                                   class="btn btn-primary btn-sm getScore">计算分数</a>
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

                        <div id="fundInfo" class="col-xs-12 col-md-5 widget-container-col ui-sortable">
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
                        <div id="unitInfo" class="col-xs-12 col-md-5 col-xs-offset-7 widget-container-col ui-sortable">
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
                            <div class="pull-left onEdit">
                                <button class="btn btn-danger del" type="button">
                                    <i class="ace-icon fa fa-trash  bigger-100"></i>
                                    删除
                                </button>
                                &nbsp;
                                <button class="btn btn-danger orderBack" type="button">
                                    <i class="ace-icon fa  fa-repeat bigger-100"></i>
                                    撤回
                                </button>
                            </div>
                            <div class="pull-right">
                                <span class="onEdit">
                                    <button class="confirm btn btn-success" type="button">
                                        <i class="ace-icon fa fa-check bigger-110"></i>
                                        确认
                                    </button>
                                    &nbsp;
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
                                    &nbsp;
                                    <button class="btn btn-danger Refuse" type="button">
                                        <i class="ace-icon fa fa-remove bigger-110"></i>
                                        驳回
                                    </button>
                                </span>
                                <%--<c:choose>--%>
                                <%--<c:when test="${sessionScope.level == '1'}">--%>
                                <%--<button class="confirm btn btn-success teacher" type="button">--%>
                                <%--<i class="ace-icon fa fa-check bigger-110"></i>--%>
                                <%--确认--%>
                                <%--</button>--%>
                                <%--</c:when>--%>
                                <%--<c:when test="${sessionScope.level == '2'}">--%>
                                <%--<button class="btn btn-success Approve" type="button">--%>
                                <%--<i class="ace-icon fa fa-check bigger-110"></i>--%>
                                <%--通过--%>
                                <%--</button>--%>
                                <%--&nbsp;--%>
                                <%--<button class="btn btn-danger Refuse" type="button">--%>
                                <%--<i class="ace-icon fa fa-remove bigger-110"></i>--%>
                                <%--驳回--%>
                                <%--</button>--%>
                                <%--&nbsp;--%>
                                <%--</c:when>--%>
                                <%--<c:when test="${sessionScope.level == '3'}">--%>
                                <%--<button class="btn btn-danger del" type="button">--%>
                                <%--<i class="ace-icon fa fa-trash  bigger-100"></i>--%>
                                <%--删除--%>
                                <%--</button>--%>
                                <%--&nbsp;--%>
                                <%--<button class="btn btn-danger orderBack" type="button">--%>
                                <%--<i class="ace-icon fa  fa-repeat bigger-100"></i>--%>
                                <%--撤回--%>
                                <%--</button>--%>
                                <%--&nbsp;--%>
                                <%--<button class="btn btn-info save" type="button">--%>
                                <%--<i class="ace-icon fa fa-save bigger-110"></i>--%>
                                <%--保存--%>
                                <%--</button>--%>
                                <%--<button class="btn btn-success Approve school hidden" type="button">--%>
                                <%--<i class="ace-icon fa fa-check bigger-110"></i>--%>
                                <%--通过--%>
                                <%--</button>--%>
                                <%--&nbsp;--%>
                                <%--<button class="btn btn-danger Refuse school hidden" type="button">--%>
                                <%--<i class="ace-icon fa fa-remove bigger-110"></i>--%>
                                <%--驳回--%>
                                <%--</button>--%>
                                <%--<button class="confirm btn btn-success" type="button">--%>
                                <%--<i class="ace-icon fa fa-check bigger-110"></i>--%>
                                <%--确认--%>
                                <%--</button>--%>
                                <%--</c:when>--%>
                                <%--</c:choose>--%>
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
</div>
<!-- /.main-content -->

<jsp:include page="public/footer.jsp"/>

<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
    <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
</a>


<!-- /.main-container -->
<script src='<c:url value="/js/public/public.js"/>'></script>
<script src='<c:url value="/js/public/pubProject.js"/>'></script>

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

<script type="text/javascript">
    $(function ($) {
        $('.date-picker').datepicker({
            autoclose: true,
            todayHighlight: true
        }).next().on(ace.click_event, function () {
            $(this).prev().focus();
        });
    });

    <%--var status = '${task.taskName}';--%>

    <%--if (status == 'ApprovalByDep') {--%>
    <%--$('input').attr('disabled', 'disabled');--%>
    <%--$('.teacher').hide();--%>
    <%--//  禁用上传按钮--%>
    <%--$('#upload').hide();--%>
    <%--}--%>

    <%--if (status == "Blank" || status == "Uncomplete") {//根据项目信息完成状态显示不同功能按钮--%>
    <%--$('.school').show();--%>
    <%--$('.save').hide();--%>
    <%--$('.orderBack').hide();--%>
    <%--$('.del').hide();--%>
    <%--} else {--%>
    <%--$('.save').show();--%>
    <%--$('.orderBack').show();--%>
    <%--$('.del').show();--%>
    <%--$('.school').hide();--%>
    <%--}--%>
    // todo 取出实体内的额外信息，附件信息也应该在其中。
    var all = '${ObjectMapper.writeValueAsString(project.argMap)}';
    //todo 取出部门信息，可同理取出专利类型
    var dept = '${ObjectMapper.writeValueAsString(project.dept)}';
//    if (all!='') {
//        all = jQuery.parseJSON(all);
//        filesData = all['filesData'];
//        actorTemp = all['actors'];
//        fundTemp = all['fund'];
//        unitTemp = all['units'];
//    }
    dept = jQuery.parseJSON(dept);
    allSections();//选择框
    upToLoadFile();//文件上传
    firstOrOther();//是否是联合单位
    //真不容易
    var filesData;
    if (filesData == null) {
        filesData = {};
    }

    scanFiles(filesData);
    //是否为第一单位
    $('#attr').change(function () {
        firstOrOther();
    });
    $('.back').click(function () {
        history.go(-1);
    });
    //监听 添加成员
    $('.addActor').click(function () {
        addActor();
    });
    //监听 分配分数
    $('.getScore').click(function () {
        getScore();
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
    })
    $(".del").click(function () {
        delOrder();
    });
    $(".Approve").click(function () {
        Approve();
    })
    $(".Refuse").click(function () {
        Refuse();
    })
</script>
</body>
</html>
