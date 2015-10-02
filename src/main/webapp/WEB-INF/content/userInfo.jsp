<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <c:choose>
        <c:when test="${sessionScope.level == '1'}">
            <title>个人信息</title>
        </c:when>
        <c:when test="${sessionScope.level == '2'}">
            <title>教师信息</title>
        </c:when>
        <c:when test="${sessionScope.level == '3'}">
            <title>教师信息</title>
        </c:when>
    </c:choose>


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
                        <a href="">Home</a>
                    </li>
                    <li class="active">个人信息</li>
                </ul>
            </div>
            <c:choose>
                <c:when test="${sessionScope.level == '1'}">
                    <div class="page-content">
                        <div class="row">
                            <button id="editInfo" class="btn btn-primary">
                                <i class="fa fa-pencil"></i> 账号信息管理
                            </button>
                        </div>
                        <div class=" col-xs-offset-4">
                            <form id="staffInfo" class="form-horizontal" role="form">
                                <div id="baseInfo">

                                    <div class="row">
                                        <div class="form-group col-xs-12 col-sm-6">
                                            <label class="col-sm-4 control-label no-padding-left">工&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</label>

                                            <div class="col-sm-8">
                                                <input type="text" id="id" name="staff.id"
                                                       placeholder="" class="col-xs-12" value="${user.staff.id}"/>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="form-group col-xs-12 col-sm-6">
                                            <label class="col-sm-4 control-label no-padding-left">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</label>

                                            <div class="col-sm-8">
                                                <input type="text" id="staName" name="staff.name"
                                                       placeholder="" class="col-xs-12" value="${user.staff.name}"/>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="form-group col-xs-12 col-sm-6">
                                            <label class="col-sm-4 control-label no-padding-left">姓名缩写：</label>

                                            <div class="col-sm-8">
                                                <input type="text" id="staffByStaId_staSnm" name="staff.staSnm"
                                                       placeholder="" class="col-xs-12"/>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="form-group col-xs-12 col-sm-6">
                                            <label class="col-sm-4 control-label no-padding-left">身份证号：</label>

                                            <div class="col-sm-8">
                                                <input type="text" id="IDcard" name="staff.idCard"
                                                       placeholder="" class="col-xs-12" value="${user.staff.idCard}"/>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="form-group col-xs-12 col-sm-6">
                                            <label class="col-sm-4 control-label no-padding-left" for="deptName">部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门：</label>

                                            <div class="col-sm-8">
                                                <input id="deptName" name="staff.col.value"
                                                       placeholder="" class="form-control" value="${user.staff.col.value}"/>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="form-group col-xs-12 col-sm-6">
                                            <label class="col-sm-4 control-label no-padding-left">职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</label>

                                            <div class="col-sm-8">
                                                <input id="rankName" name="staff.rank.value"
                                                       placeholder="" class="form-control" value="${user.staff.rank.value}"/>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="form-group col-xs-12 col-sm-6">
                                            <label class="col-sm-4 control-label no-padding-left">岗&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位：</label>

                                            <div class="col-sm-8">
                                                <input id="position" name="staff.position"
                                                       placeholder="" class="form-control" value="${user.staff.position}"/>
                                            </div>
                                        </div>
                                    </div>

                                    <div id="syRole" class="row">
                                        <div class="form-group col-xs-12 col-sm-6 col-xs-offset-2">
                                            <label class="col-sm-4 control-label no-padding-left">角&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色：</label>

                                            <div class="col-sm-8">
                                                <input id="role" name="privilege"
                                                       placeholder="" class="form-control" value="${user.privilege}"/>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="form-group col-xs-12 col-sm-6">
                                            <label class="col-sm-4 control-label no-padding-left">学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;历：</label>

                                            <div class="col-sm-8">
                                                <input id="edu" name="staff.edu"
                                                       placeholder="" class="form-control" value="${user.staff.edu}"/>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="form-group col-xs-12 col-sm-6">
                                            <label class="col-sm-4 control-label no-padding-left">学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位：</label>

                                            <div class="col-sm-8">
                                                <input id="degree" name="staff.degree"
                                                       placeholder="" class="form-control" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>

                        <form id="account" class="col-md-offset-4 " style="display: none">
                            <hr/>
                            <h4>密码修改</h4>
                            <br/>

                            <div class="row">
                                <div class="form-group col-xs-12 col-sm-6">
                                    <label class="col-sm-4 control-label no-padding-left"
                                           for="pwd">原&nbsp;&nbsp;密&nbsp;&nbsp;码：</label>

                                    <div class="col-sm-8">
                                        <input type="password" id="pwd" name="oldPwd"
                                               placeholder="" class="col-xs-12" value="************"/>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="form-group col-xs-12 col-sm-6">
                                    <label class="col-sm-4 control-label no-padding-left"
                                           for="newPwd">新&nbsp;&nbsp;密&nbsp;&nbsp;码：</label>

                                    <div class="col-sm-8">
                                        <input type="password" id="newPwd" name="newPwd"
                                               placeholder="" class="col-xs-12"/>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="form-group col-xs-12 col-sm-6 ">
                                    <div class="col-sm-8 col-sm-offset-3">
                                        <button class="btn btn-success btn-sm" type="button" id="save">
                                            <i class="ace-icon fa fa-save bigger-110"></i>
                                            保存
                                        </button>
                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                        <button class="btn btn-danger btn-sm" type="button" id="cancel">
                                            <i class="ace-icon fa fa-remove  bigger-110"></i>
                                            取消
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </c:when>
                <c:when test="${sessionScope.level == '2'}">
                    <div class="page-content">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="widget-box transparent " style="opacity: 1;" id="userTable-box">
                                    <div class="widget-body">
                                        <table id="UserTable"
                                               data-toolbar="#cUserToolbar"
                                               data-search="true"
                                               data-show-columns="true"
                                               data-show-refresh="true"
                                               data-show-toggle="true"
                                            <%--data-show-pagination-switch="true"--%>
                                               data-page-list="[10]"
                                               data-pagination="true"
                                               data-single-select="true"
                                               data-click-to-select="true">
                                        </table>
                                        <div id="cUserToolbar">
                                            <button id="editUser" class="btn btn-primary">
                                                <i class="fa fa-pencil"></i> 修改
                                            </button>
                                            <button id="resetPwd" class="btn btn-primary">
                                                <i class="fa fa-lock"></i> 密码重置
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12" id="info_alert"></div>
                            <div class="col-xs-12">
                                <div class="widget-box transparent ui-sortable-handle collapsed" style="opacity: 1;"
                                     id="user-box">
                                    <div class="widget-body">


                                        <div class="row">
                                            <jsp:include page="../public/userInfoForm.jsp"/>
                                            <br/>

                                            <div class="row">
                                                <div class="form-group col-xs-12 col-sm-6 ">
                                                    <div class="col-sm-8 col-sm-offset-10">
                                                        <button class="btn btn-success btn-sm" type="button" id="save">
                                                            <i class="ace-icon fa fa-save bigger-110"></i>
                                                            保存
                                                        </button>
                                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                                        <button class="btn btn-danger btn-sm" type="button"
                                                                id="canEdit">
                                                            <i class="ace-icon fa fa-remove  bigger-110"></i>
                                                            取消
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:when>
                <c:when test="${sessionScope.level == '3'}">
                    <div class="page-content">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="widget-box transparent " style="opacity: 1;" id="userTable-box">
                                    <div class="widget-body">
                                        <table id="UserTable"
                                               data-toolbar="#cUserToolbar"
                                               data-search="true"
                                               data-show-columns="true"
                                               data-show-refresh="true"
                                               data-show-toggle="true"
                                               data-page-list="[10]"
                                               data-pagination="true"
                                               data-single-select="true"
                                               data-click-to-select="true">
                                        </table>
                                        <div id="cUserToolbar">
                                            <button id="addUser" class="btn btn-primary">
                                                <i class="fa fa-plus"></i> 添&nbsp;&nbsp;加
                                            </button>
                                            <button id="editUser" class="btn btn-primary">
                                                <i class="fa fa-pencil"></i> 修&nbsp;&nbsp;改
                                            </button>
                                            <button id="resetPwd" class="btn btn-primary">
                                                <i class="fa fa-lock"></i> 密码重置
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12" id="info_alert"></div>
                            <div class="col-xs-12">
                                <div class="widget-box transparent ui-sortable-handle collapsed" style="opacity: 1;"
                                     id="user-box">
                                    <div class="widget-body">
                                        <div class="row">
                                            <jsp:include page="public/userInfoForm.jsp"/>
                                            <br/>

                                            <div class="row">
                                                <div class="form-group col-xs-12 col-sm-6 ">
                                                    <div class="col-sm-8 col-sm-offset-10">
                                                        <button class="btn btn-success btn-sm" type="button"
                                                                id="saveAdd"
                                                                style="display: none">
                                                            <i class="ace-icon fa fa-save bigger-110"></i>
                                                            保存
                                                        </button>
                                                        <button class="btn btn-success btn-sm" type="button"
                                                                id="saveEdit"
                                                                style="display: none">
                                                            <i class="ace-icon fa fa-save bigger-110"></i>
                                                            保存
                                                        </button>
                                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                                        <button class="btn btn-danger btn-sm" type="button"
                                                                id="canEdit">
                                                            <i class="ace-icon fa fa-remove  bigger-110"></i>
                                                            取消
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:when>
            </c:choose>

        </div>
    </div>


</div>
<jsp:include page="public/footer.jsp"/>
<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
    <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
</a>

<script src="<c:url value="/js/public/pubInfo.js"/>"></script>

<c:choose>
    <c:when test="${sessionScope.level == '1'}">
        <script src="<c:url value="/js/teacher/userInfo.js"/>"></script>
    </c:when>
    <c:when test="${sessionScope.level == '2'}">
        <script src="<c:url value="/js/college/userInfo.js"/>"></script>
    </c:when>
    <c:when test="${sessionScope.level == '3'}">
        <script src="<c:url value="/js/school/userInfo.js"/>"></script>
    </c:when>
</c:choose>
</body>
</html>
