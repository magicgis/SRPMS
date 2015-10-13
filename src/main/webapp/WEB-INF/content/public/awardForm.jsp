<%--
  Created by IntelliJ IDEA.
  User: zheng
  Date: 2015/4/24
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="award" class="form-horizontal" role="form">
    <div hidden="hidden">
        <input type="text" name="standard.id" id="standardId" value="${award.standard.id}"/>
        <input type="text" name="id" id="awardId" value="${award.id}"/>
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
        <div class="widget-box transparent ui-sortable-handle col-xs-12"
             style="opacity: 1;">
            <div class="widget-header" id="actorInfoHeader">
                <h4 class="widget-title">参与人员</h4>
                <span id="showSum" style="font-size: 15px"></span>
            </div>
            <div class="widget-body">
                <div class="widget-main">
                    <div class="row">
                        <div id="actorToolbar">
                            <c:choose>
                                <c:when test="${sessionScope.level == '3'}">
                                    <a data-toggle="modal" id="addActor"
                                       class="btn btn-primary btn-sm">添加成员</a>
                                </c:when>
                                <c:when test="${sessionScope.level == '1'}">
                                    <a data-toggle="modal" id="getScore"
                                       class="btn btn-primary btn-sm">计算分数</a>

                                </c:when>
                            </c:choose>
                        </div>
                        <table id="actorTable"
                               data-toolbar="#actorToolbar"
                               data-show-footer="true"
                               data-show-columns="false"
                               data-show-toggle="false">
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="awardInfo"
         class="col-xs-12 col-md-7 widget-container-col ui-sortable">
        <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
            <div class="widget-header">
                <h4 class="widget-title">获奖信息</h4>
            </div>
            <div class="widget-body ">
                <div class="widget-main">
                    <div class="row">
                        <div class="form-group col-xs-12 col-sm-6">
                            <label class="col-sm-4 control-label no-padding-left"
                                   for="nature">奖励性质</label>

                            <div class="col-sm-8">
                                <select class="form-control" id="nature"
                                        name="nature">
                                    <option value="unKnow" selected="selected">请选择</option>
                                    <option value="educationAward">自然类科学类奖</option>
                                    <option value="educationAward">教育教学成果奖</option>
                                    <option value="logicalAward">哲学社会科学奖</option>
                                    <option value="departmentAward">政府调研奖</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group col-xs-12 col-sm-6">
                            <label class="col-sm-4 control-label no-padding-left"
                                   for="items">奖项类别</label>

                            <div class="col-sm-8">
                                <select class="form-control" id="items"
                                        name="items">
                                    <option value="unKnow" selected="selected">请选择</option>
                                    <option value="">国家最高科学技术奖</option>
                                    <option value="">国家自然科学奖</option>
                                    <option value="">国家技术发明奖</option>
                                    <option value="">国家科技进步奖</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-xs-12 col-sm-6">
                            <label class="col-sm-4 control-label no-padding-left"
                                   for="grade">奖项等级</label>

                            <div class="col-sm-8">
                                <select class="form-control" id="grade"
                                        name="grade">
                                    <option value="unKnow" selected="selected">请选择
                                    </option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group col-xs-12 col-sm-6">
                            <label class="col-sm-4 control-label no-padding-left"
                                   for="date">获奖时间
                            </label>

                            <div class="col-sm-8">
                                <input type="text" id="date" name="date"
                                       placeholder="" class="col-xs-12"
                                       data-date-format="yyyy-mm-dd"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="col-xs-12 widget-container-col ui-sortable" id="fileHead">
                                <div class="widget-box transparent ui-sortable-handle"
                                     style="opacity: 1;">
                                    <div class="widget-header">
                                        <h4 class="widget-title">附件信息</h4>

                                        <div class="widget-toolbar no-border">
                                            <c:if test="${sessionScope.level == '3'}">
                                                <div id="upload">
                                                </div>
                                            </c:if>
                                        </div>
                                    </div>
                                    <div class="widget-body">
                                        <div class="widget-main">
                                            <div class="dd" id="nestable">
                                                <ol class="dd-list" id="downFiles"></ol>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="unitInfo" class="col-xs-12 col-md-5 widget-container-col ui-sortable">
        <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
            <div class="widget-header">
                <h4 class="widget-title">共有单位信息</h4>
            </div>
            <div class="widget-body">
                <div class="widget-main">
                    <div class="row">
                        <div id="unitToolbar">
                            <a class="btn btn-primary btn-sm addUnit"><i
                                    class="glyphicon glyphicon-plus"></i> 添加单位</a>
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
    <div id="reply" class="col-xs-12 col-md-5 widget-container-col ui-sortable">
        <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
            <div class="widget-header">
                <h4 class="widget-title">批复</h4>
            </div>
            <div class="widget-body ">
                <div class="widget-main">
                    <div class="row">
                        <div class="col-xs-12">
                            <textarea class="form-control" id="reply-box"></textarea>
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

