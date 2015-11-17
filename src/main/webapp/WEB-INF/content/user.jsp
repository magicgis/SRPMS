<%--
  Created by IntelliJ IDEA.
  User: zheng
  Date: 2015/11/14
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
  <meta charset="utf-8"/>
  <title>
    <c:choose>
      <c:when test="${sessionScope.level == '1'}">
        已入库信息 - 教师
      </c:when>
      <c:when test="${sessionScope.level == '2'}">
        学院已入库信息概况
      </c:when>
      <c:when test="${sessionScope.level == '3'}">
        已入库科研信息概况-学校
      </c:when>
    </c:choose></title>

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

    .bars {
      width: 40%;
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
          <li class="active" id="title">科研概况</li>
        </ul>
        <!-- /.breadcrumb -->
      </div>
      <div class="page-content">
        <div class="row">
          <c:choose>
            <c:when test="${sessionScope.level == '1'}">
              <div class="row">
                <button id="editInfo" class="btn btn-primary">
                  <i class="fa fa-pencil"></i> 账号信息管理
                </button>
              </div>
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
                               placeholder="" class="form-control"/>
                      </div>
                    </div>
                  </div>
                </div>
              </form>
            </c:when>
            <c:when test="${sessionScope.level == '2'|| sessionScope.level == '3'}">
              <div class="col-xs-12">
                <div class="widget-box transparent " style="opacity: 1;" id="UserToolbar-box">
                  <div class="widget-body">
                    <div id="tableCon">
                      <table id="UserTable"
                             data-toolbar="#UserToolbar"
                             data-search="true"
                             data-show-columns="true"
                             data-show-refresh="true"
                             data-show-toggle="true"
                             data-page-list="[10]"
                             data-pagination="true"
                             data-single-select="true"
                             data-click-to-select="true">
                      </table>
                      <div id="UserToolbar" class="bootstrap-table">
                        <c:choose>
                          <c:when test="${sessionScope.level == '3'}">
                            <button id="addUser" class="btn btn-primary">
                              <i class="fa fa-plus"></i> 添&nbsp;&nbsp;加
                            </button>
                            <button id="scanUser" class="btn btn-primary">
                              <i class="fa fa-lock"></i> 查&nbsp;&nbsp;看
                            </button>

                            <button id="resetPwd" class="btn btn-primary">
                              <i class="fa fa-lock"></i> 密码重置
                            </button>
                          </c:when>
                          <c:when test="${sessionScope.level == '2'}">
                            <button id="editUser" class="btn btn-primary">
                              <i class="fa fa-pencil"></i> 修改
                            </button>
                            <button id="resetPwd" class="btn btn-primary">
                              <i class="fa fa-lock"></i> 密码重置
                            </button>
                          </c:when>
                        </c:choose>

                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </c:when>
          </c:choose>
          <div class="col-xs-12" id="info_alert"></div>
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
<%--<script src='<c:url value="/js/public/public.js"/>'></script>--%>
<script src='<c:url value="/js/public/pubInfo.js"/>'></script>
<script>
  $('#UserTable').bootstrapTable({
    url: '/api/staff/all',
    sidePagination: "server",
    flat: "true",
    columns: [{
      checkbox: true,
      visible: true
    }, {
      field: 'id',
      title: 'id',
      sortable: true,
      visible: false
    }, {
      field: 'name',
      title: '姓名',
      sortable: true
    }, {
      field: 'idCard',
      title: '身份证号',
      sortable: true
    }, {
      field: 'rank.value',
      title: '职称',
      sortable: true
    }, {
      field: 'position',
      title: '岗位',
      sortable: true
    }, {
      field: 'edu',
      title: '学历',
      sortable: true
    }, {
      field: 'degree',
      title: '学位',
      sortable: true
    }, {
      field: 'operator',
      align: 'center',
      title: '操作',
      formatter: disableOrEnable,
      events: operateEvents
    }]
  });
</script>
<script>

</script>
<c:choose>
  <c:when test="${sessionScope.level == '1'}">
    <script src="<c:url value="/js/teacher/userInfo.js"/>"></script>
  </c:when>
  <c:when test="${sessionScope.level == '2'}">
    <script src="<c:url value="/js/college/userInfo.js"/>"></script>
  </c:when>
  <c:when test="${sessionScope.level == '3'}">
  </c:when>
</c:choose>

</body>
</html>
