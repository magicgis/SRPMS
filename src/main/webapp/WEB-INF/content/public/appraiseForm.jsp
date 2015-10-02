
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="paper" class="form-horizontal" role="form">
  <div hidden="hidden">
    <input type="text" name="WF_Task" id="WF_Task"/>
    <input type="text" name="IsComplete" id="IsComplete"/>
  </div>
  <div id="achInfo" class="col-xs-12 col-md-7 widget-container-col ui-sortable">
    <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
      <div class="widget-header">
        <h4 class="widget-title">成果信息</h4>
      </div>
      <div class="widget-body ">
        <div class="widget-main">
          <div class="row">
            <div class="form-group col-xs-12 col-sm-6">
              <label class="col-sm-4 control-label no-padding-left"
                     for="achName">成果名称</label>

              <div class="col-sm-8">
                <input type="text" id="achName" name="achName"
                       placeholder="" class="col-xs-12"/>
              </div>
            </div>

            <div class="form-group col-xs-12 col-sm-6">
              <label class="col-sm-4 control-label no-padding-left"
                     for="achType">成果类型</label>

              <div class="col-sm-8">
                <select class="form-control" id="achType"
                        name="achType">
                </select>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="form-group col-xs-12 col-sm-6">
              <label class="col-sm-4 control-label no-padding-left"
                     for="deptId">所属部门</label>

              <div class="col-sm-8">
                <select class="form-control" id="deptId"
                        name="deptId">
                </select>
              </div>
            </div>

            <div class="form-group col-xs-12 col-sm-6">
              <label class="col-sm-4 control-label no-padding-left"
                     for="regNo">成果登记号</label>

              <div class="col-sm-8">
                <input type="text" id="regNo" name="regNo"
                       placeholder="" class="col-xs-12"/>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="form-group col-xs-12 col-sm-6">
              <label class="col-sm-4 control-label no-padding-left"
                      for="regDate">登记日期</label>
              <div class="col-sm-8">
                <input type="text" id="regDate" name="regDate"
                      placeholder="" class="col-xs-12" data-date-format="yyyy-mm-dd"/>
              </div>
            </div>
            <div class="form-group col-xs-12 col-sm-6">
              <label class="col-sm-4 control-label no-padding-left"
                      for="isComAch">成果共有</label>
              <div class="col-sm-8">
                <select class="form-control" id="isComAch"
                      name="isComAch">
                  <option value="unKnow" selected="selected">请选择
                  </option>
                  <option value="yes">是</option>
                  <option value="no">否</option>
                </select>
              </div>
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
            <div id="actorToolbar">
              <a id="addActor" class="btn btn-primary">
                <i class="glyphicon glyphicon-plus"></i> 添加成员
              </a>
              <font size="4" color="#inline">总分：</font><input type="text" name="patScore" id="patScore" size="12px" disabled/>
            </div>
            <table id="actorTable"
                   data-toolbar="#actorToolbar"
                   data-show-toggle="true"
                   data-show-footer="true">
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div id="appraiseInfo"
       class="col-xs-12 col-md-7 widget-container-col ui-sortable">
    <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
      <div class="widget-header">
        <h4 class="widget-title">鉴定信息</h4>
      </div>
      <div class="widget-body ">
        <div class="widget-main">
          <div class="row">
            <div class="form-group col-xs-12 col-sm-6">
              <label class="col-sm-4 control-label no-padding-left"
                     for="checkName">鉴定单位
              </label>
              <div class="col-sm-8">
                <input type="text" id="checkName" name="checkName"
                       placeholder="" class="col-xs-12 confer-input"/>
              </div>
            </div>

            <div class="form-group col-xs-12 col-sm-6">
              <label class="col-sm-4 control-label no-padding-left"
                     for="appraiseId">证书号</label>
              <div class="col-sm-8">
                <input type="text" id="appraiseId" name="appraiseId"
                       placeholder="" class="col-xs-12 confer-input"/>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="form-group col-xs-12 col-sm-6">
              <label class="col-sm-4 control-label no-padding-left"
                     for="appraiseDate">鉴定日期</label>

              <div class="col-sm-8">
                <input class="form-control date-picker confer-input"
                       name="appraiseDate" id="appraiseDate" type="text"
                       data-date-format="yyyy-mm-dd"/>
              </div>
            </div>
            <div class="form-group col-xs-12 col-sm-6">
              <label class="col-sm-4 control-label no-padding-left"
                     for="appraiseWay">鉴定形式</label>

              <div class="col-sm-8">
                <input type="text" id="appraiseWay" name="appraiseWay"
                       placeholder="" class="col-xs-12 confer-input"/>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="form-group col-xs-12 col-sm-6">
              <label class="col-sm-4 control-label no-padding-left"
                     for="appraiseResult">鉴定结论</label>

              <div class="col-sm-8">
                <input type="text" id="appraiseResult" name="appraiseResult"
                      placeholder="" class="col-xs-12"/>
              </div>
            </div>

            <div class="form-group col-xs-12 col-sm-6">
              <label class="col-sm-4 control-label no-padding-left"
                     for="upFiles">上传附件</label>
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

  <div id="unitInfo" class="col-xs-12 col-md-5 widget-container-col ui-sortable">
    <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
      <div class="widget-header">
        <h4 class="widget-title">单位信息</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <div class="row">
            <div id="unitToolbar">
              <a id="addUnit" class="btn btn-primary">
                <i class="glyphicon glyphicon-plus"></i>添加单位
              </a>
            </div>
            <table id="unitTable"
                   data-toolbar="#unitToolbar"
                   data-show-toggle="true"
                   data-show-footer="true">
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</form>
