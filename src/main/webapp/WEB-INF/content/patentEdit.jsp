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
            <a href="/allSRInfo">Home</a>
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
                    <input type="text" name="WF_Order" id="WF_Order"/>
                    <input type="text" name="WF_Task" id="WF_Task"/>
                    <input type="text" name="IsComplete" id="IsComplete"/>
                    <input type="text" name="pscore" id="score"/>
                    <input type="text" name="dept.value" id="deptValue"/>
                    <input type="text" name="patent.standard.value" id="patTypeValue"/>
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
                                       class="form-control col-xs-12" type="text"
                                       placeholder="请选择"/>
                              </div>
                            </div>
                          </div>
                          <div class="row">
                            <div class="form-group col-xs-12 col-sm-6">
                              <label class="col-sm-4 control-label no-padding-left"
                                     for="dept">所属部门</label>
                              <div class="col-sm-8">
                                <input id="dept" name="dept.id"
                                       type="text" class="form-control col-xs-12"/>
                              </div>
                            </div>
                            <div class="form-group col-xs-12 col-sm-6">
                              <label class="col-sm-4 control-label no-padding-left"
                                     for="patentNo">专利号</label>
                              <div class="col-sm-8">
                                <input type="text" name="patentNo" id="patentNo"
                                       placeholder="" class="col-xs-12"/>
                              </div>
                            </div>
                          </div>
                          <div class="row">
                            <div class="form-group col-xs-12 col-sm-6">
                              <label class="col-sm-4 control-label no-padding-left"
                                     for="patentPubNo">公开号</label>
                              <div class="col-sm-8">
                                <input type="text" id="patentPubNo" name="patentPubNo"
                                       placeholder="" class="col-xs-12"/>
                              </div>
                            </div>
                            <div class="form-group col-xs-12 col-sm-6">
                              <label class="col-sm-4 control-label no-padding-left"
                                     for="patType">专利类型</label>
                              <div class="col-sm-8">
                                <input id="patType" name="patent.standard.id"
                                       class="form-control col-xs-12" type="text"
                                       placeholder="请选择"/>
                              </div>
                            </div>
                          </div>
                          <div class="row">
                            <div class="form-group col-xs-12 col-sm-6">
                              <label class="col-sm-4 control-label no-padding-left"
                                     for="state">专利状态</label>
                              <div class="col-sm-8">
                                <input type="text" name="state" id="state"
                                       placeholder="" class="form-control col-xs-12"/>
                              </div>
                            </div>
                            <div class="form-group col-xs-12 col-sm-6">
                              <label class="col-sm-4 control-label no-padding-left"
                                     for="endfillDate">终止填写时间</label>
                              <div class="col-sm-8">
                                <input class="form-control date-picker" type="text" id="endfillDate" name="endfillDate"
                                       data-date-format="yyyy-mm-dd"  class="col-xs-12"/>
                              </div>
                            </div>
                          </div>
                          <div class="row">
                            <div class="form-group col-xs-12 col-sm-6">
                              <label class="col-sm-4 control-label no-padding-left"
                                     for="apprDate">获批时间</label>
                              <div class="col-sm-8">
                                <input class="form-control date-picker" type="text" name="apprDate" id="apprDate"
                                       data-date-format="yyyy-mm-dd" class="col-xs-12"/>
                              </div>
                            </div>
                            <div class="form-group col-xs-12 col-sm-6">
                              <label class="col-sm-4 control-label no-padding-left"
                                     for="pubDate">公告时间</label>
                              <div class="col-sm-8">
                                <input class="form-control date-picker" type="text" id="pubDate" name="pubDate"
                                       data-date-format="yyyy-mm-dd"  placeholder="" class="col-xs-12"/>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
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
                                <a id="addUnit" class="btn btn-primary btn-sm"><i class="glyphicon glyphicon-plus"></i> 添加单位</a>
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
                              <a data-toggle="modal" id="addActor" class="btn btn-primary btn-sm">添加成员</a>
                              <%--<a data-toggle="modal" id="getScore" class="btn btn-primary btn-sm">计算分数</a>--%>
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

                </form>

                <div class="col-xs-12">
                    <div class="widget-body">
                      <div class="row">

                        <div id="formBtn" class="col-xs-12 clearfix">
                          <div class="pull-right">
                            <button class="btn btn-info btn-sm" type="button" id="back">
                              <i class="ace-icon fa fa-reply  bigger-110"></i>
                              返回
                            </button>
                            <button class="tabOrdBtn btn btn-success btn-sm" type="button" id="confirm">
                              <i class="ace-icon fa fa-check bigger-110"></i>
                              确认
                            </button>
                            <button class="tabOrdBtn btn btn-primary btn-sm" type="button" id="save">
                              <i class="ace-icon fa fa-save bigger-110"></i>
                              保存
                            </button>
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
<!-- /.main-container -->
<%--<script src='<c:url value="/js/public/pubPatent.js"/>'></script>--%>
<%--<script src='<c:url value="/js/public/public.js"/>'></script>--%>
</body>
<script src='<c:url value="/js/school/patentEdit.js"/>'></script>

</html>