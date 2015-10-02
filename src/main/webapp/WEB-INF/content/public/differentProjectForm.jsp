<%--
  Created by IntelliJ IDEA.
  User: zheng
  Date: 2015/5/5
  Time: 22:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
  $(function(){
    $("#infos").clone(true).appendTo($("#infosD"));
    var key1="actorTable";
    var key2="actorTableD";
    viewTable(key1);
    viewTable(key2);
  });
</script>
<div id="projectInfo" class="col-xs-12 col-md-6 widget-container-col ui-sortable">
  <form id="project " class="form-horizontal" role="form">
    <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
      <div class="widget-header">
        <h4 class="widget-title">原项目信息</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <div id="infos">
            <div class="row">
              <div class="form-group col-xs-12 col-sm-6 col-md-10">
                <label class="col-sm-4 control-label no-padding-left">项目代码</label>
                <div class="col-sm-8">
                  <input type="text" name="projId"
                         placeholder="" class="col-xs-12"/>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="form-group col-xs-12 col-sm-6 col-md-10">
                <label class="col-sm-4 control-label no-padding-left">项目名称</label>

                <div class="col-sm-8">
                  <input type="text" name="projName"
                         placeholder="" class="col-xs-12"/>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="form-group col-xs-12 col-sm-6 col-md-10">
                <label class="col-sm-4 control-label no-padding-left">项目类别</label>

                <div class="col-sm-8">
                  <select class="form-control" name="projType">
                    <option value="unKnow" selected="selected">请选择
                    </option>
                    <option value="philosophy">自然科学</option>
                    <option value="science">哲学与社会科学</option>
                    <option value="education">教育教学改革</option>
                  </select>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="form-group col-xs-12 col-sm-6 col-md-10">
                <label class="col-sm-4 control-label no-padding-left">项目等级</label>

                <div class="col-sm-8">
                  <select class="form-control"
                          name="projRank">
                    <option value="unKnow" selected="selected">请选择
                    </option>
                    <option value="nation">国家级</option>
                    <option value="province">省部级</option>
                  </select>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="form-group col-xs-12 col-sm-6 col-md-10">
                <label class="col-sm-4 control-label no-padding-left">所属部门</label>

                <div class="col-sm-8">
                  <select class="form-control" name="deptName">
                    <option value="unKnow" selected="selected">请选择</option>
                    <option value="01">信息工程学院</option>
                    <option value="02">药学院</option>
                    <option value="03">护理学院</option>
                    <option value="04">基础医学院</option>
                    <option value="05">人文学院</option>
                    <option value="06">管理学院</option>
                  </select>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="form-group col-xs-12 col-sm-6 col-md-10">
                <label class="col-sm-4 control-label no-padding-left">子课题</label>
                <div class="col-sm-8">
                  <select class="form-control" name="isSubProj">
                    <option value="unKnow" selected="selected">请选择</option>
                    <option value="true">是</option>
                    <option value="false">否</option>
                  </select>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="form-group col-xs-12 col-sm-6 col-md-10">
                <label class="col-sm-4 control-label no-padding-left">项目评分来源</label>

                <div class="col-sm-8">
                  <select class="form-control"
                          name="rateUnit">
                    <option value="unKnow" selected="selected">请选择</option>
                    <option value="01">国家科技部</option>
                    <option value="02">国家自然科学基金委员会</option>
                    <option value="03">国家中医药管理局</option>
                    <option value="04">教育部</option>
                    <option value="05">其他部委</option>
                  </select>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="form-group col-xs-12 col-sm-6 col-md-10">
                <label class="col-sm-4 control-label no-padding-left">项目评分归属</label>

                <div class="col-sm-8">
                  <select class="form-control"
                          name="rateSrc">
                    <option value="unKnow" selected="selected">请选择</option>
                    <option value="a973">“973”计划A类资助</option>
                    <option value="b973">“973”计划B类资助</option>
                    <option value="c973">“973”计划C类资助</option>
                    <option value="863">“863”计划</option>
                    <option value="nbs">国家重大科技专项</option>
                  </select>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="form-group col-xs-12 col-sm-6 col-md-10">
                <label class="col-sm-4 control-label no-padding-left">计划结题时间</label>

                <div class="col-sm-8">
                  <input class="form-control date-picker"
                         type="text" name="planSolTime"
                         data-date-format="yyyy-mm-dd"/>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="form-group col-xs-12 col-sm-6 col-md-10">
                <label class="col-sm-4 control-label no-padding-left">获得立项</label>

                <div class="col-sm-8">
                  <select class="form-control"
                          name="isProjAppr">
                    <option value="unKnow" selected="selected">请选择
                    </option>
                    <option value="true">是</option>
                    <option value="false">否</option>
                  </select>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="form-group col-xs-12 col-sm-6 col-md-10">
                <label class="col-sm-4 control-label no-padding-left">立项时间</label>

                <div class="col-sm-8">
                  <input class="form-control date-picker"
                         type="text" name="projApprTime"
                         data-date-format="yyyy-mm-dd" />
                </div>
              </div>
            </div>
            <div class="row">
              <div class="form-group col-xs-12 col-sm-6 col-md-10">
                <label class="col-sm-4 control-label no-padding-left">获奖</label>
                <div class="col-sm-8">
                  <select class="form-control" name="isAwdProj">
                    <option value="unKnow" selected="selected">请选择</option>
                    <option value="true">是</option>
                    <option value="false">否</option>
                  </select>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="widget-header">
            <h4 class="widget-title">参与人员</h4>
          </div>
          <table id="actorTable"
                 data-show-footer="true"></table>
        </div>
      </div>
    </div>
  </form>
</div>
<div id="projectInfoD" style="border-left:solid 2px #98ccff;" class="col-xs-12 col-md-6 widget-container-col ui-sortable">
  <form id="paperD" class="form-horizontal" role="form">
    <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
      <div class="widget-header">
        <h4 class="widget-title">差异信息填写</h4>
      </div>
      <div class="widget-body ">
        <div class="widget-main">
          <div id="infosD">
          </div>
        </div>
        <div class="row">
          <div class="widget-header">
            <h4 class="widget-title">参与人员</h4>
          </div>
          <div id="actorToolbar">
            <a id="addActor" class="btn btn-primary">
              <i class="glyphicon glyphicon-plus"></i> 添加成员
            </a>
            <%--<font size="4" color="#inline">总分：</font><input type="text" name="allScores" id="allScores" disabled/>--%>
          </div>
          <table id="actorTableD"
                 data-toolbar="#actorToolbar"
                 data-show-toggle="true"
                 data-show-footer="true">
          </table>
        </div>
      </div>
    </div>
  </form>
</div>
