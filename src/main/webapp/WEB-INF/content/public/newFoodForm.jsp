<%--
  Created by IntelliJ IDEA.
  User: zheng
  Date: 2015/4/16
  Time: 8:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="food" class="form-horizontal" role="form">
  <div hidden="hidden">
    <input type="text" name="WF_Task" id="WF_Task"/>
    <input type="text" name="IsComplete" id="IsComplete"/>
  </div>
  <div id="newFoodInfo" class="col-xs-12 col-md-7 widget-container-col ui-sortable">
    <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
      <div class="widget-header">
        <h4 class="widget-title">新功能食品信息</h4>

      </div>
      <div class="widget-body ">
        <div class="widget-main">
          <div class="row">
            <div class="form-group col-xs-12 col-sm-6">
              <label class="col-sm-4 control-label no-padding-left"
                     for="foodName">食品名称</label>
              <div class="col-sm-8">
                <input type="text" id="foodName" name="foodName"
                       placeholder="" class="col-xs-12"/>
              </div>
            </div>
            <div class="form-group col-xs-12 col-sm-6">
              <label class="col-sm-4 control-label no-padding-left"
                     for="foodProdNo">生产许可编号</label>
              <div class="col-sm-8">
                <input type="text" id="foodProdNo" name="foodProdNo"
                       placeholder="" class="col-xs-12"/>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="form-group col-xs-12 col-sm-6">
              <label class="col-sm-4 control-label no-padding-left">备注</label>
              <div class="col-sm-4">
                <textarea class="form-control" id=="foodNote" name="foodNote" rows="3" style="width: 472px; height: 79px;"></textarea>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="form-group col-xs-12 col-sm-6">
              <label class="col-sm-4 control-label no-padding-left"
                     for="isFirInMed">第一研发单位</label>
              <div class="col-sm-8">
                <select class="form-control" id="isFirInMed"
                        name="isFirInMed">
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
  <div id="foodStaInfo" class="col-xs-12 col-md-5 widget-container-col ui-sortable">
    <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
      <div class="widget-header">
        <h4 class="widget-title">参与人员</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <div class="row">
            <div id="actorNewFoodToolbar">
              <font size="4">总分：</font><input type="text" name="allScores" id="allScores" size="12px" disabled/>
              <a id="addNewFoodActor" class="btn btn-primary">
                <i class="glyphicon glyphicon-plus"></i> 添加成员
              </a>
            </div>
            <table id="actorTable"
                   data-toolbar="#actorNewFoodToolbar"
                   data-show-toggle="true"
                   data-show-footer="true"></table>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div id="foodUnitInfo"
       class="col-xs-12 col-md-7 widget-container-col ui-sortable">
    <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
      <div class="widget-header">
        <h4 class="widget-title">共有单位信息</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <div class="row">
            <div id="foodUnitToolbar">
              <a id="addFoodUnit" class="btn btn-primary">
                <i class="glyphicon glyphicon-plus"></i> 添加成员
              </a>
            </div>
            <table id="unitTable"
                   data-toolbar="#foodUnitToolbar"
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

