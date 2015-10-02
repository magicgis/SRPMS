<%--
  Created by IntelliJ IDEA.
  User: zheng
  Date: 2015/4/16
  Time: 8:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="medicine" class="form-horizontal" role="form">
  <div hidden="hidden">
    <input type="text" name="WF_Task" id="WF_Task"/>
    <input type="text" name="IsComplete" id="IsComplete"/>
  </div>
  <div id="medicineInfo" class="col-xs-12 col-md-7 widget-container-col ui-sortable">
    <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
      <div class="widget-header">
        <h4 class="widget-title">药品信息</h4>

      </div>
      <div class="widget-body ">
        <div class="widget-main">
          <div class="row">
            <div class="form-group col-xs-12 col-sm-6">
              <label class="col-sm-4 control-label no-padding-left"
                     for="medName">药品名称</label>

              <div class="col-sm-8">
                <input type="text" id="medName" name="medName"
                       placeholder="" class="col-xs-12"/>
              </div>
            </div>

            <div class="form-group col-xs-12 col-sm-6">
              <label class="col-sm-4 control-label no-padding-left"
                     for="medAttribute">新药属性</label>

              <div class="col-sm-8">
                <input type="text" name="medAttribute" id="medAttribute"
                       placeholder="" class="col-xs-12"/>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="form-group col-xs-12 col-sm-6">
              <label class="col-sm-4 control-label no-padding-left"
                     for="medType">新药类别</label>

              <div class="col-sm-8">
                <select class="form-control" id="medType"
                        name="medType">
                  <option value="unKnow" selected="selected">请选择
                  </option>
                  <option value="ChineseMedicine">中药</option>
                  <option value="chemicalAgent">化学药</option>
                  <option value="biologicalDrug">生物药</option>
                </select>
              </div>
            </div>
            <div class="form-group col-xs-12 col-sm-6">
              <label class="col-sm-4 control-label no-padding-left"
                     for="devpStage">研发阶段</label>
              <div class="col-sm-8">
                <select class="form-control" id="devpStage"
                        name="devpStage">
                  <option value="unKnow" selected="selected">请选择
                  </option>
                  <option value="PreclincalStudies">临床前研究</option>
                  <option value="ClinicalPhases">临床研究</option>
                </select>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="form-group col-xs-12 col-sm-6">
              <label class="col-sm-4 control-label no-padding-left"
                     for="clinCode">临床批号</label>
              <div class="col-sm-8">
                <input type="text" id="clinCode" name="clinCode"
                       placeholder="" class="col-xs-12"/>
              </div>
            </div>
            <div class="form-group col-xs-12 col-sm-6">
              <label class="col-sm-4 control-label no-padding-left"
                     for="prodCode">生产批号</label>
              <div class="col-sm-8">
                <input type="text" id="prodCode" name="prodCode"
                       placeholder="" class="col-xs-12"/>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="form-group col-xs-12 col-sm-6">
              <label class="col-sm-4 control-label no-padding-left"
                     for="medCertId">证书号</label>
              <div class="col-sm-8">
                <input type="text" id="medCertId" name="medCertId"
                       placeholder="" class="col-xs-12"/>
              </div>
            </div>
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
  <div id="actorInfo" class="col-xs-12 col-md-5 widget-container-col ui-sortable">
    <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
      <div class="widget-header">
        <h4 class="widget-title">参与人员</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <div class="row">
            <div id="actorNewMedicineToolbar">
              <a id="addNewMedicineActor" class="btn btn-primary">
                <i class="glyphicon glyphicon-plus"></i> 添加成员
              </a>
              <font size="4" color="#inline">总分：</font><input type="text" name="patScore" id="patScore" size="12px" disabled/>
            </div>
            <table id="actorTable"
                   data-toolbar="#actorNewMedicineToolbar"
                   data-show-toggle="true"
                   data-show-footer="true"></table>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div id="medunitInfo"
       class="col-xs-12 col-md-7 widget-container-col ui-sortable">
    <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
      <div class="widget-header">
        <h4 class="widget-title">共有单位信息</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <div class="row">
            <div id="medunitToolbar">
              <a id="addMedunit" class="btn btn-primary">
                <i class="glyphicon glyphicon-plus"></i> 添加成员
              </a>
            </div>
            <table id="unitTable"
                   data-toolbar="#medunitToolbar"
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
