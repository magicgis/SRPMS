<%--
  Created by IntelliJ IDEA.
  User: yxm
  Date: 2015/4/16
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="project" class="form-horizontal" role="form">
    <div hidden="hidden">
        <input type="text" name="WF_Task" id="WF_Task"/>
        <input type="text" name="WF_Order" id="WF_Order"/>
        <input type="text" name="IsComplete" id="IsComplete"/>
        <input type="text" name="score" id="score"/>
    </div>
    <div id="projInfo" class="col-xs-12 col-md-7 widget-container-col ui-sortable">
        <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
            <div class="widget-header">
                <h4 class="widget-title">项目信息</h4>

            </div>
            <div class="widget-body ">
                <div class="widget-main">
                    <div class="row">
                        <div class="form-group col-xs-12 col-sm-6">
                            <label class="col-sm-4 control-label no-padding-left"
                                   for="code">项目代码</label>

                            <div class="col-sm-8">
                                <input type="text" id="code" name="code"
                                       placeholder="" class="col-xs-12"/>
                            </div>
                        </div>

                        <div class="form-group col-xs-12 col-sm-6">
                            <label class="col-sm-4 control-label no-padding-left"
                                   for="name">项目名称</label>

                            <div class="col-sm-8">
                                <input type="text" id="name" name="name"
                                       placeholder="" class="col-xs-12"/>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-xs-12 col-sm-6">
                            <label class="col-sm-4 control-label no-padding-left"
                                   for="type">项目类别</label>

                            <div class="col-sm-8">
                                <div class="col-sm-13">
                                    <input class="form-control" id="type"
                                           type="text" name="type"
                                           placeholder="请选择"/>
                                </div>
                            </div>
                        </div>

                        <div class="form-group col-xs-12 col-sm-6">
                            <label class="col-sm-4 control-label no-padding-left"
                                   for="rank">项目等级</label>

                            <div class="col-sm-8">
                                <div class="col-sm-13">
                                    <input class="form-control" id="rank"
                                           type="text" name="rank"
                                           placeholder="请选择"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-xs-12 col-sm-6">
                            <label class="col-sm-4 control-label no-padding-left"
                                   for="dept">所属部门</label>

                            <div class="col-sm-8">
                                <div class="col-sm-13">
                                    <input class="form-control" id="dept"
                                           type="text" name="dept"
                                           placeholder="请选择"/>
                                </div>
                            </div>
                        </div>

                        <div class="form-group col-xs-12 col-sm-6">
                            <label class="col-sm-4 control-label no-padding-left"
                                   for="attr">项目属性</label>

                            <div class="col-sm-8">
                                <div class="col-sm-13">
                                    <input class="form-control" id="attr"
                                           type="text" name="attr"
                                           placeholder="请选择"/>
                                </div>

                            </div>
                        </div>
                    </div>


                    <div class="row">
                        <div class="form-group col-xs-12 col-sm-6">
                            <label class="col-sm-4 control-label no-padding-left"
                                   for="rateUnit">项目来源</label>

                            <div class="col-sm-8">
                                <div class="col-sm-13">
                                    <input class="form-control" id="rateUnit"
                                           type="text" name="rateUnit"
                                           placeholder="请选择"/>
                                </div>

                            </div>
                        </div>
                        <%--项目评分归属字段--%>
                        <div class="form-group col-xs-12 col-sm-6">
                            <label class="col-sm-4 control-label no-padding-left"
                                   for="rateSrc">项目归属</label>

                            <div class="col-sm-8">
                                <div class="col-sm-13">
                                    <input class="form-control" id="rateSrc"
                                           type="text" name="rateSrc"
                                           placeholder="请选择"/>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-xs-12 col-sm-6">
                            <label class="col-sm-4 control-label no-padding-left"
                                   for="planSolTime">计划结题时间</label>

                            <div class="col-sm-8">
                                <input class="form-control date-picker" id="planSolTime"
                                       type="text" name="plan_time"
                                       data-date-format="yyyy-mm-dd"/>
                            </div>
                        </div>
                        <div class="form-group col-xs-12 col-sm-6">
                            <label class="col-sm-4 control-label no-padding-left"
                                   for="is_appr">获得立项</label>

                            <div class="col-sm-8">
                                <div class="col-sm-13">
                                    <input class="form-control" id="is_appr"
                                           type="text" name="is_appr"
                                           placeholder="请选择"/>
                                </div>

                            </div>
                        </div>
                    </div>

                    <div class="row">

                        <div class="form-group col-xs-12 col-sm-6">
                            <label class="col-sm-4 control-label no-padding-left"
                                   for="appr_time">立项时间</label>

                            <div class="col-sm-8">
                                <input class="form-control date-picker" id="appr_time"
                                       type="text" name="appr_time"
                                       data-date-format="yyyy-mm-dd"/>
                            </div>
                        </div>
                        <div class="form-group col-xs-12 col-sm-6">
                            <label class="col-sm-4 control-label no-padding-left"
                                   for="real_time">实际结题时间</label>

                            <div class="col-sm-8">
                                <input class="form-control date-picker" id="real_time"
                                       type="text" name="real_time"
                                       data-date-format="yyyy-mm-dd"/>
                            </div>
                        </div>
                    </div>


                    <div class="row">

                        <!--获奖字段-->
                        <div class="form-group col-xs-12 col-sm-6">
                            <label class="col-sm-4 control-label no-padding-left"
                                   for="isAwdProj">是否获奖</label>

                            <div class="col-sm-8">
                                <div class="col-sm-13">
                                    <input class="form-control" id="isAwdProj"
                                           type="text" name="isAwdProj"
                                           placeholder="请选择"/>
                                </div>

                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-xs-12">
                            <div class="col-xs-12 widget-container-col ui-sortable" id="fileHead">
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
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div id="actorInfo" class="col-xs-12 col-md-5 widget-container-col ui-sortable">
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

    <div id="fundInfo" class="col-xs-12 col-md-5 widget-container-col ui-sortable">
        <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
            <div class="widget-header">
                <h4 class="widget-title">金额到账信息（单位：万元）</h4>
            </div>
            <div class="widget-body">
                <div class="widget-main">
                    <div class="row">
                        <div id="fundToolbar">
                            <a id="addFund" class="btn btn-primary btn-sm">
                                <i class="glyphicon glyphicon-plus"></i> 添加添加金额信息
                            </a>
                        </div>
                        <table id="fundTable"
                               data-toolbar="#fundToolbar"
                               data-show-toggle="true"
                               data-show-footer="true"></table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="unitInfo" class="col-xs-12 col-md-5 col-xs-offset-7 widget-container-col ui-sortable">
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
    <div id="reply" class="col-xs-12 col-md-5 col-xs-offset-7 widget-container-col ui-sortable">
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
