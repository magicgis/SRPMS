<%--
  Created by IntelliJ IDEA.
  User: zheng
  Date: 2015/4/21
  Time: 23:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                            <a data-toggle="modal" id="getScore" class="btn btn-primary btn-sm">计算分数</a>
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

    <div id="reply" class="col-xs-12 col-md-6 widget-container-col ui-sortable">
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

    <div class="col-xs-12">
        <div class="col-xs-12 col-md-6 widget-container-col ui-sortable" id="fileHead">
            <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
                <div class="widget-header">
                    <h4 class="widget-title">附件信息</h4>

                    <div class="widget-toolbar no-border">
                        <div id="upload">
                        </div>
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
</form>
