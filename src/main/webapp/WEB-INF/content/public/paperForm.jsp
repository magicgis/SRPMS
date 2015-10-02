<%--
  Created by IntelliJ IDEA.
  User: guofan
  Date: 2015/4/11
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="paper" class="form-horizontal" role="form">
    <div hidden="hidden">
        <input type="text" name="WF_Task" id="WF_Task"/>
        <input type="text" name="WF_Order" id="WF_Order"/>
        <input type="text" name="IsComplete" id="IsComplete"/>
        <input type="text" name="mag.name" id="magName"/>
        <input type="text" name="score" id="score"/>
        <input type="text" name="mag.standard.id" id="magStandardId"/>
        <%--<input type="text" name="newspaper.standard.id" id="newsStandardId"/>--%>
        <%--<input type="text" name="confer.standard.id" id="conferStandardId"/>--%>
    </div>
    <div class="col-xs-12 col-md-6">
        <div id="paperInfo" class="col-xs-12 widget-container-col ui-sortable">
            <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
                <div class="widget-header">
                    <h4 class="widget-title">论文信息</h4>
                </div>
                <div class="widget-body ">
                    <div class="widget-main">
                        <div class="row">
                            <div class="form-group col-xs-12 col-sm-6">
                                <label class="col-sm-4 control-label no-padding-right"
                                       for="paperName">论文名称</label>

                                <div class="col-sm-8">
                                    <input type="text" id="paperName" name="name"
                                           placeholder="" class="col-xs-12"/>
                                </div>
                            </div>

                            <div class="form-group col-xs-12 col-sm-6">
                                <label class="col-sm-4 control-label no-padding-right"
                                       for="pubDate">发表日期</label>

                                <div class="col-sm-8">
                                    <input class="form-control date-picker" id="pubDate"
                                           type="text" name="pubDate"
                                           data-date-format="yyyy-mm-dd"/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-12 col-sm-6 type">
                                <label class="col-sm-4 control-label no-padding-right"
                                       for="type">论文类型</label>

                                <div class="col-sm-8">
                                    <input class="form-control" id="type"
                                           type="text" name="type"
                                           placeholder="请选择"/>
                                </div>
                            </div>

                            <div class="form-group col-xs-12 col-sm-6 paperWord">
                                <label class="col-sm-4 control-label no-padding-right"
                                       for="paperWord">论文字数</label>

                                <div class="col-sm-8">
                                    <input type="text" name="numWord" id="paperWord"
                                           placeholder="" class="col-xs-12"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="conferInfo" class="col-xs-12 widget-container-col ui-sortable">
            <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
                <div class="widget-header">
                    <h4 class="widget-title">会议信息</h4>
                </div>
                <div class="widget-body ">
                    <div class="widget-main">
                        <div class="row">
                            <div class="form-group col-xs-12 col-sm-6">
                                <label class="col-sm-4 control-label no-padding-right"
                                       for="conferName">会议名称</label>

                                <div class="col-sm-8">
                                    <input type="text" id="conferName" name="confer.name"
                                           placeholder=""
                                           class="col-xs-12 confer-input"/>
                                </div>
                            </div>
                            <div class="form-group col-xs-12 col-sm-6">
                                <label class="col-sm-4 control-label no-padding-right"
                                       for="conferType">收录类型</label>

                                <div class="col-sm-8">
                                    <input class="form-control" id="conferType"
                                           type="text" name="confer.standard.id"
                                           placeholder="请选择"/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-12 col-sm-6">
                                <label class="col-sm-4 control-label no-padding-right"
                                       for="conferTime">会议时间</label>

                                <div class="col-sm-8">
                                    <input type="text" style="width: 200px"
                                           name="confer.time" id="conferTime"
                                           placeholder="示例：03/18/2013 - 03/23/2013"
                                           class="col-xs-12 confer-input"/>
                                </div>
                            </div>
                            <div class="form-group col-xs-12 col-sm-6">
                                <label class="col-sm-4 control-label no-padding-right"
                                       for="conferAddress">会议地点</label>

                                <div class="col-sm-8">
                                    <input type="text" name="confer.addr"
                                           id="conferAddress" class="col-xs-12 confer-input"
                                    <%--data-rel='tooltip' title='示例：武汉，中国' data-placement='bottom'--%>
                                           placeholder="示例：武汉，中国"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="newsInfo" class="col-xs-12 widget-container-col ui-sortable">
            <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
                <div class="widget-header">
                    <h4 class="widget-title">报刊信息</h4>
                </div>
                <div class="widget-body ">
                    <div class="widget-main">
                        <div class="row">
                            <div class="form-group col-xs-12 col-sm-6">
                                <label class="col-sm-4 control-label no-padding-right"
                                       for="newsName">报刊名称</label>

                                <div class="col-sm-8">
                                    <input class="form-control" id="newsName" type="text"
                                           name="newspaper.name" placeholder="请选择"/>
                                </div>
                            </div>
                            <div class="form-group col-xs-12 col-sm-6">
                                <label class="col-sm-4 control-label no-padding-right"
                                       for="newsPeriod">报刊等级</label>

                                <div class="col-sm-8">
                                    <input class="form-control" id="newsType"
                                           name="newspaper.standard.id" placeholder="请输入"/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-12 col-sm-6">
                                <label class="col-sm-4 control-label no-padding-right"
                                       for="newsPeriod">发表期号</label>

                                <div class="col-sm-8">
                                    <input class="form-control" id="newsPeriod"  type="text"
                                           name="newspaper.period" placeholder="请输入"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="magInfo" class="col-xs-12 widget-container-col ui-sortable">
            <div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
                <div class="widget-header">
                    <h4 class="widget-title">期刊信息</h4>

                    <div class="widget-toolbar no-border">
                        <span class="btn btn-primary btn-xs" id="addDiff">
                            <i class="ace-icon fa fa-plus bigger-100"></i>
                            添加新期刊
                        </span>
                    </div>
                </div>
                <div class="widget-body ">
                    <div class="widget-main">
                        <div class="row">
                            <div class="form-group col-xs-12">
                                <div class="col-sm-2 control-label no-padding-right">
                                    <label for="magId">期刊名称</label>
                                </div>
                                <div class="col-sm-9">
                                    <input class="form-control mag-input" type="text"
                                           name="mag.id" id="magId"
                                           placeholder="请输入"/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-12 col-sm-6">
                                <label class="col-sm-4 control-label no-padding-right"
                                       for="magLevel">刊物级别</label>

                                <div class="col-sm-8">
                                    <input type="text" id="magLevel" class="form-control mag-input uneditableInput"
                                           data-rel='tooltip' title='不可编辑' data-placement='right'
                                           name="mag.standard.infoMap.col_type" placeholder=""/>
                                </div>
                            </div>
                            <div class="form-group col-xs-12 col-sm-6">
                                <label class="col-sm-4 control-label no-padding-right"
                                       for="issn">ISSN</label>

                                <div class="col-sm-8">
                                    <input type="text" id="issn" class="form-control mag-input uneditableInput"
                                           data-rel='tooltip' title='不可编辑' data-placement='right'
                                           name="issn" placeholder=""/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-12 col-sm-6">
                                <label class="col-sm-4 control-label no-padding-right"
                                       for="cn">CN</label>

                                <div class="col-sm-8">
                                    <input type="text" id="cn" class="form-control mag-input uneditableInput"
                                           data-rel='tooltip' title='不可编辑' data-placement='right'
                                           name="cn" placeholder=""/>
                                </div>
                            </div>
                            <div class="form-group col-xs-12 col-sm-6">
                                <label class="col-sm-4 control-label no-padding-right"
                                       for="vol">卷号</label>

                                <div class="col-sm-8">
                                    <input type="text" id="vol" name="vol"
                                           placeholder="" class="col-xs-12 mag-input"/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-12 col-sm-6">
                                <label class="col-sm-4 control-label no-padding-right"
                                       for="issue">期号</label>

                                <div class="col-sm-8">
                                    <input type="text" id="issue" name="iss"
                                           placeholder="" class="col-xs-12 mag-input"/>
                                </div>
                            </div>
                            <div class="form-group col-xs-12 col-sm-6">
                                <label class="col-sm-4 control-label no-padding-right"
                                       for="bePage">起止页码</label>

                                <div class="col-sm-8">
                                    <input type="text" id="bePage" name="bgPage"
                                           placeholder="例：23-40"
                                           class="col-xs-12 mag-input"/>
                                </div>
                            </div>
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
                            <%--<a data-toggle="modal" id="editActor" class="btn btn-primary btn-sm" style="display: none;">编辑成员</a>--%>
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
            <div class="widget-body">
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
