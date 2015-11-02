<%--
  Created by IntelliJ IDEA.
  User: zheng
  Date: 2015/4/18
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="othersInfo" class="form-horizontal" role="form">
  <div hidden="hidden">
    <input type="text" name="WF_Task" id="WF_Task"/>
    <input type="text" name="IsComplete" id="IsComplete"/>
  </div>
  <div id="newInstrumentsInfo" class="col-xs-12 col-md-7 widget-container-col ui-sortable">
    <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
      <div class="widget-header">
        <h4 class="widget-title">其它新产品信息</h4>
      </div>
      <div class="widget-body ">
        <div class="widget-main">

          <div class="row">
            <div class="form-group col-xs-12 col-sm-6">
              <label class="col-sm-4 control-label no-padding-left"
                     for="otherName">产品名称</label>
              <div class="col-sm-8">
                <input type="text" id="otherName" name="otherName"
                       placeholder="" class="col-xs-12"/>
              </div>
            </div>
            <div class="form-group col-xs-12 col-sm-6">
              <label class="col-sm-4 control-label no-padding-left"
                     for="otherProdNo">生产许可编号</label>
              <div class="col-sm-8">
                <input type="text" id="otherProdNo" name="otherProdNo"
                       placeholder="" class="col-xs-12"/>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="form-group col-xs-12 col-sm-6">
              <label class="col-sm-4 control-label no-padding-left"
                     for="isFirInOther">第一研发单位</label>
              <div class="col-sm-8">
                <select class="form-control" id="isFirInOther"
                        name="isFirInOther">
                  <option value="unKnow" selected="selected">请选择
                  </option>
                  <option value="true">是</option>
                  <option value="false">否</option>
                </select>
              </div>
            </div>
          </div>

          <div class="row">
            <div class="form-group col-xs-12 col-sm-6">
              <label class="col-sm-4 control-label no-padding-left"
                     for="upFiles">上传附件</label>
            </div>
            <div class="form-group col-xs-12 col-sm-6">
            </div>
          </div>
          <div class="row">
            <div class="col-xs-10 col-xs-offset-1">
              <input multiple="" type="file" id="upFiles"/>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div id="otherStaInfo" class="col-xs-12 col-md-5 widget-container-col ui-sortable">
    <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
      <div class="widget-header">
        <h4 class="widget-title">参与人员</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <div class="row">
            <div id="actorOthersToolbar">
                <a id="addOtherActor" class="btn btn-primary">
                 <i class="glyphicon glyphicon-plus"></i> 添加成员
                </a>
              <font size="4" color="#inline">总分：</font><input type="text" name="patScore" id="patScore" size="12px" disabled/>
            </div>
            <table id="actorTable"
                   data-toolbar="#actorOthersToolbar"
                   data-show-toggle="true"
                   data-show-footer="true"></table>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div id="otherUnitInfo" class="col-xs-12 col-md-7 widget-container-col ui-sortable">
    <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
      <div class="widget-header">
        <h4 class="widget-title">共有单位信息</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <div class="row">
            <div id="otherUnitToolbar">
              <a id="addOtherUnit" class="btn btn-primary">
                <i class="glyphicon glyphicon-plus"></i> 添加共有单位
              </a>
            </div>
            <table id="unitTable"
                   data-toolbar="#otherUnitToolbar"
                   data-show-toggle="true"
                   data-show-footer="true"></table>
          </div>
        </div>
      </div>
    </div>
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
              <textarea class="form-control" id="reply-box" ></textarea>
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
