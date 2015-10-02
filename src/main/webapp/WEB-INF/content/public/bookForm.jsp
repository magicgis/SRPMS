<%--
  Created by IntelliJ IDEA.
  User: zheng
  Date: 2015/5/11
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="book" class="form-horizontal" role="form">
  <div hidden="hidden">
    <input type="text" name="WF_Task" id="WF_Task"/>
    <input type="text" name="IsComplete" id="IsComplete"/>
  </div>
  <div id="newInstrumentsInfo" class="col-xs-12 col-md-7 widget-container-col ui-sortable">
    <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
      <div class="widget-header">
        <h4 class="widget-title">著作信息</h4>
      </div>
      <div class="widget-body ">
        <div class="widget-main">
          <div class="row">
            <div class="form-group col-xs-12 col-sm-6">
              <label class="col-sm-4 control-label no-padding-left"
                     for="bkId">著作编码</label>
              <div class="col-sm-8">
                <input type="text" name="bkId" id="bkId"
                       placeholder="" class="col-xs-12" />
              </div>
            </div>
            <div class="form-group col-xs-12 col-sm-6">
              <label class="col-sm-4 control-label no-padding-left"
                     for="bkName">著作名称</label>
              <div class="col-sm-8">
                <input type="text" id="bkName" name="bkName"
                       placeholder="" class="col-xs-12"/>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="form-group col-xs-12 col-sm-6">
              <label class="col-sm-4 control-label no-padding-left"
                     for="bkType">著作类型</label>
              <div class="col-sm-8">
                <select class="form-control" id="bkType" readonly
                        name="bkType">
                  <option value="unKnow" selected="selected">请选择
                  </option>
                  <option value="publishBook">公开出版著作</option>
                  <option value="eduBook">教育部规划教材</option>
                  <option value="helpBook">协编教材</option>
                  <option value="otherBook">其他教材</option>
                </select>
              </div>
            </div>
            <div class="form-group col-xs-12 col-sm-6">
              <label class="col-sm-4 control-label no-padding-left"
                     for="pubDate">出版年月</label>
              <div class="col-sm-8">
                <input type="text" id="pubDate" name="pubDate"
                       placeholder="" class="col-xs-12"/>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="form-group col-xs-12 col-sm-6">
              <label class="col-sm-4 control-label no-padding-left"
                     for="ISBN">ISBN号</label>
              <div class="col-sm-8">
                <input type="text" id="ISBN" name="ISBN"
                       placeholder="" class="col-xs-12"/>
              </div>
            </div>
            <div class="form-group col-xs-12 col-sm-6">
              <label class="col-sm-4 control-label no-padding-left"
                     for="publisher">出版社</label>
              <div class="col-sm-8">
                <input type="text" id="publisher" name="publisher"
                       placeholder="" class="col-xs-12"/>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="form-group col-xs-12 col-sm-6">
              <label class="col-sm-4 control-label no-padding-left"
                     for="bkReward">著作获奖情况</label>
              <div class="col-sm-8">
                <input type="text" name="bkReward" id="bkReward"
                       placeholder="" class="col-xs-12"/>
              </div>
            </div>
            <div class="form-group col-xs-12 col-sm-6">
              <label class="col-sm-4 control-label no-padding-left"
                     for="bkWdNum">著作总字数</label>
              <div class="col-sm-8">
                <input type="text" name="bkWdNum" id="bkWdNum"
                       placeholder="" class="col-xs-12"/>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="form-group col-xs-12 col-sm-6">
              <label class="col-sm-4 control-label no-padding-left"
                     for="scYear">计分年度</label>
              <div class="col-sm-8">
                <input type="text" name="scYear" id="scYear"
                       placeholder="" class="col-xs-12"/>
              </div>
            </div>
            <div class="form-group col-xs-12 col-sm-6">
              <label class="col-sm-4 control-label no-padding-left"
                     for="pat_ggDate">公告时间</label>
              <div class="col-sm-8">
                <input class="form-control date-picker" type="text" id="pat_ggDate" name="pat_ggDate"
                       data-date-format="yyyy-mm-dd"  placeholder="" class="col-xs-12"/>
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
            <div id="actorToolbar">
              <a data-toggle="modal" id="addActor" href="#myModal" class="btn btn-primary btn-large">添加成员</a>
              <%--<font size="4" color="#inline">总分：</font><input type="text" name="patScore" id="patScore" size="12px"/>--%>
            </div>
            <table id="actorTable"
                   data-toolbar="#actorToolbar"
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

