<%--
  Created by IntelliJ IDEA.
  User: zheng
  Date: 2015/11/14
  Time: 13:45
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
  <title>教师信息-
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
      /*display: inline;*/
    /*}*/

    /*th.bs-checkbox {*/
      /*display: inline;*/
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
            <a href="#">Home</a>
          </li>
          <li class="active">教师信息</li>
        </ul>
        <!-- /.breadcrumb -->
      </div>
      <div class="page-content">
        <c:if test="${sessionScope.level == '1'}">
          <div class="row">
            <button id="editInfo" class="btn btn-primary">
              <i class="fa fa-pencil"></i> 账号信息管理
            </button>
          </div>
        </c:if>
        <div class="col-xs-offset-2">
          <button class="btn btn-primary bigger-120 editUser">
            <i class="fa fa-pencil"></i> 信息修改
          </button>
        </div>

        <div class=" col-xs-offset-4">
          <div class="row">
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

                <%--<div class="row">--%>
                  <%--<div class="form-group col-xs-12 col-sm-6">--%>
                    <%--<label class="col-sm-4 control-label no-padding-left">姓名缩写：</label>--%>

                    <%--<div class="col-sm-8">--%>
                      <%--<input type="text" id="staffByStaId_staSnm" name="staff.staSnm"--%>
                             <%--placeholder="" class="col-xs-12"/>--%>
                    <%--</div>--%>
                  <%--</div>--%>
                <%--</div>--%>

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
                    <label class="col-sm-4 control-label no-padding-left" for="deptName">学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;院：</label>

                    <div class="col-sm-8">
                      <input id="deptName" name="staff.col.value"
                             placeholder="" class="form-control"
                             value="${user.staff.col.value}"/>
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="form-group col-xs-12 col-sm-6">
                    <label class="col-sm-4 control-label no-padding-left">职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</label>

                    <div class="col-sm-8">
                      <input id="rankName" name="staff.rank.value"
                             placeholder="" class="form-control"
                             value="${user.staff.rank.value}"/>
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="form-group col-xs-12 col-sm-6">
                    <label class="col-sm-4 control-label no-padding-left">岗&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位：</label>

                    <div class="col-sm-8">
                      <input id="position" name="staff.position"
                             placeholder="" class="form-control"
                             value="${user.staff.position}"/>
                    </div>
                  </div>
                </div>

                <%--<div id="syRole" class="row">--%>
                  <%--<div class="form-group col-xs-12 col-sm-6 col-xs-offset-2">--%>
                    <%--<label class="col-sm-4 control-label no-padding-left">角&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色：</label>--%>

                    <%--<div class="col-sm-8">--%>
                      <%--<input id="role" name="privilege"--%>
                             <%--placeholder="" class="form-control" value="${user.privilege}"/>--%>
                    <%--</div>--%>
                  <%--</div>--%>
                <%--</div>--%>

                <%--<div class="row">--%>
                  <%--<div class="form-group col-xs-12 col-sm-6">--%>
                    <%--<label class="col-sm-4 control-label no-padding-left">学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;历：</label>--%>

                    <%--<div class="col-sm-8">--%>
                      <%--<input id="edu" name="staff.edu"--%>
                             <%--placeholder="" class="form-control" value="${user.staff.edu}"/>--%>
                    <%--</div>--%>
                  <%--</div>--%>
                <%--</div>--%>

                <div class="row">
                  <div class="form-group col-xs-12 col-sm-6">
                    <label class="col-sm-4 control-label no-padding-left">学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位：</label>

                    <div class="col-sm-8">
                      <input id="degree" name="staff.degree"
                             value="${user.staff.degree}"
                             placeholder="" class="form-control"/>
                    </div>
                  </div>
                </div>
              </div>
            </form>

            <div class="row">
              <div class="form-group col-xs-12 col-sm-6 ">
                <div class="col-sm-8 col-sm-offset-10">
                  <c:if test="${sessionScope.level == '3'}">
                    <button class="btn btn-success btn-sm saveAdd" type="button"
                            style="display: none">
                      <i class="ace-icon fa fa-save bigger-110"></i>
                      保存
                    </button>
                  </c:if>
                  <button class="btn btn-success btn-sm saveEdit" type="button"
                          style="display: none">
                    <i class="ace-icon fa fa-save bigger-110"></i>
                    保存
                  </button>

                  <button class="btn btn-danger btn-sm canEdit" type="button">
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
  <jsp:include page="public/footer.jsp"/>
</div>
<!-- /.main-content -->


<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
  <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
</a>
<!-- /.main-container -->
</body>
<%--<script src='<c:url value="/js/public/public.js"/>'></script>--%>
<script src='<c:url value="/js/public/pubInfo.js"/>'></script>
<script src='<c:url value="/js/public/route.js"/>'></script>

<script type="text/javascript">
  var user=${ObjectMapper.writeValueAsString(user)};
  allSelects();
  $('.saveEdit').click(function(){
    saveEdit();
    //清空表单中的数据
    $('form input').val(null).removeAttr('selected');
  });
  $('.saveAdd').click(function(){
    saveAdd();
    //清空表单中的数据
    $('form input').val(null).removeAttr('selected');
  });
  //修改教师信息
  $('.editUser').click(function () {
    // $('#saveEdit').show();//只是测试 根据用户状态判断用户信息是否可编辑
    editUser();
  });

  //返回到教师信息主页面
  $('.canEdit').click(function () {
    //清空表单中的数据
    $('form input').val(null).removeAttr('selected');
    history.go(-1);
  });
</script>
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
</html>

