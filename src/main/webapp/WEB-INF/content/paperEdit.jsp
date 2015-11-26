<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<jsp:useBean id="ObjectMapper" scope="application" class="com.fasterxml.jackson.databind.ObjectMapper"/>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta charset="utf-8"/>
	<title>论文管理-
		<c:choose>
			<c:when test="${sessionScope.level == '1'}">
				教师
			</c:when>
			<c:when test="${sessionScope.level == '2'}">
				学院
			</c:when>
			<c:when test="${sessionScope.level == '3'}">
				学校
			</c:when>
		</c:choose>
	</title>

	<meta name="description" content="Dynamic tables and grids using jqGrid plugin"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>

	<jsp:include page="public/jsHeader.jsp"/>
	<style>
		.noradio td.bs-checkbox {
			display: none;
		}

		.noradio th.bs-checkbox {
			display: none;
		}

	</style>
</head>

<body class="no-skin">
<jsp:include page="public/topbar.jsp"/>

<div class="main-container" id="main-container">
	<script type="text/javascript">
		try {
			ace.settings.check('main-container', 'fixed')
		} catch (e) {
		}
	</script>

	<jsp:include page="sidebar.jsp"/>

	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript">
					try {
						ace.settings.check('breadcrumbs', 'fixed')
					} catch (e) {
					}
				</script>
				<ul class="breadcrumb">
					<li>
						<i class="ace-icon fa fa-home home-icon"></i>
						<a href="">Home</a>
					</li>
					<li class="active">论文</li>
				</ul>
				<!-- /.breadcrumb -->
			</div>
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<div class="widget-box transparent ui-sortable-handle" style="opacity: 1;"
						     id="paper-box">
							<div class="widget-body">
								<form id="paper" class="form-horizontal" role="form">
									<div hidden="hidden">
										<input type="text" name="WF_Task" id="WF_Task"/>
										<input type="text" name="WF_Order" id="WF_Order"/>
										<input type="text" name="WF_Type" id="WF_Type" value="paper"/>
										<input type="text" name="IsComplete" id="IsComplete"/>
										<input type="text" name="mag.name" id="magName"/>
										<input type="text" name="mag.standard.id" id="magStandardId"/>
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
															<div class="form-group col-xs-12">
																<label class="col-sm-2 control-label no-padding-left"
																       for="paperName">论文名称</label>

																<div class="col-sm-9">
																	<input id="paperName" name="name"
																	       type="text" class="form-control col-xs-12"
																	       placeholder="请输入"/>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="form-group col-xs-12 col-sm-6">
																<label class="col-sm-4 control-label no-padding-left"
																       for="dept">所属部门</label>

																<div class="col-sm-8">
																	<input id="dept" name="dept.id"
																	       type="text" class="form-control col-xs-12"
																	       placeholder="请选择"/>
																</div>
															</div>
															<div class="form-group col-xs-12 col-sm-6">
																<label class="col-sm-4 control-label no-padding-right"
																       for="pubDate">发表日期</label>

																<div class="col-sm-8">
																	<input class="form-control date-picker" id="pubDate"
																	       type="text" name="pubDate"
																	       data-date-format="yyyy-mm-dd"
																	       value=""/>
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
																	       placeholder="请选择"
																	       value=""/>
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
																	<input type="text" id="conferName"
																	       name="confer.name"
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
																	       id="conferAddress"
																	       class="col-xs-12 confer-input"
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
																	<input class="form-control" id="newsName"
																	       type="text"
																	       name="newspaper.name" placeholder="请选择"/>
																</div>
															</div>
															<div class="form-group col-xs-12 col-sm-6">
																<label class="col-sm-4 control-label no-padding-right"
																       for="newsPeriod">报刊等级</label>

																<div class="col-sm-8">
																	<input class="form-control" id="newsType"
																	       name="newspaper.standard.id"
																	       placeholder="请输入"/>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="form-group col-xs-12 col-sm-6">
																<label class="col-sm-4 control-label no-padding-right"
																       for="newsPeriod">发表期号</label>

																<div class="col-sm-8">
																	<input class="form-control" id="newsPeriod"
																	       type="text"
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
																	<input type="text" id="magLevel"
																	       class="form-control mag-input uneditableInput"
																	       data-rel='tooltip' title='不可编辑'
																	       data-placement='right'
																	       name="mag.standard.infoMap.col_type"
																	       placeholder=""/>
																</div>
															</div>
															<div class="form-group col-xs-12 col-sm-6">
																<label class="col-sm-4 control-label no-padding-right"
																       for="issn">ISSN</label>

																<div class="col-sm-8">
																	<input type="text" id="issn"
																	       class="form-control mag-input uneditableInput"
																	       data-rel='tooltip' title='不可编辑'
																	       data-placement='right'
																	       name="issn" placeholder=""/>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="form-group col-xs-12 col-sm-6">
																<label class="col-sm-4 control-label no-padding-right"
																       for="cn">CN</label>

																<div class="col-sm-8">
																	<input type="text" id="cn"
																	       class="form-control mag-input uneditableInput"
																	       data-rel='tooltip' title='不可编辑'
																	       data-placement='right'
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


									<div id="actorInfo" class="col-xs-12 col-md-6 widget-container-col">
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
															<a data-toggle="modal" id="addActor"
															   class="btn btn-primary btn-sm">添加成员</a>　

															<a data-toggle="modal" id="getScore"
															   class="btn btn-primary btn-sm">计算分数</a>
															<label for="totalScore">总分：</label>
															<input class="score" type="text"
															       name="score" id="totalScore" value="${paper.score}">
														</div>
														<table id="actorTable"
														       data-toolbar="#actorToolbar"
														       data-show-footer="true"></table>
													</div>
												</div>
											</div>
										</div>

										<div class="col-xs-12" id="msg_alert"></div>
									</div>


									<div id="reply" class="col-xs-12 col-md-6 widget-container-col">
										<div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
											<div class="widget-header">
												<h4 class="widget-title">批复</h4>
											</div>
											<div class="widget-body">
												<div class="widget-main">
													<div class="row">
														<div class="col-xs-12">
															<textarea class="form-control" id="reply-box"></textarea>
															<blockquote class="pull-left" id="reply-display"
															            hidden="hidden">
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
								<div class="row">
									<div id="formBtn" class="col-xs-12 clearfix">
										<c:choose>
											<c:when test="${sessionScope.level == '1'}">
												<div class="pull-left delAndBack">
													<button class="tabOrdBtn btn btn-danger btn-sm" type="button"
													        id="del">
														<i class="ace-icon fa fa-trash  bigger-110"></i>
														删除
													</button>
													<button class="tabOrdBtn btn btn-danger btn-sm orderBack"
													        type="button">
														<i class="ace-icon fa  fa-repeat bigger-110"></i>
														撤回
													</button>
												</div>
												<div id="paperBtn" class="pull-right">
													<button class="tabOrdBtn btn btn-info btn-sm back" type="button">
														<i class="ace-icon fa fa-reply  bigger-110"></i>
														返回
													</button>
													<button class="tabOrdBtn btn btn-success btn-sm" type="button"
													        id="confirm">
														<i class="ace-icon fa fa-check bigger-110"></i>
														确认
													</button>
													<button class="tabOrdBtn btn btn-primary btn-sm" type="button"
													        id="save">
														<i class="ace-icon fa fa-save bigger-110"></i>
														保存
													</button>
													<button class="tabOrdBtn btn btn-success btn-sm" type="button"
													        id="confirmC">
														<i class="ace-icon fa fa-check bigger-110"></i>
														确认
													</button>
												</div>
											</c:when>
											<c:otherwise>
												<div class="col-md-offset-4 col-md-8">
													<button class="tabOrdBtn btn btn-success" type="button"
													        id="Approve">
														<i class="ace-icon fa fa-check bigger-110"></i>
														通过
													</button>

													<button class="tabOrdBtn btn back" type="button">
														<i class="ace-icon fa fa-reply  bigger-110"></i>
														返回
													</button>

													<button class="tabOrdBtn btn btn-danger" type="button" id="Refuse">
														<i class="ace-icon fa fa-remove bigger-110"></i>
														驳回
													</button>
												</div>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xs-12" id="info_alert"></div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="public/footer.jsp"/>
</div>


<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
	<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
</a>
<!-- /.main-container -->
<script>

	var actorTemp = [];

</script>

<script src='<c:url value="/js/public/public.js"/>'></script>
<script src='<c:url value="/js/public/pubPaper.js"/>'></script>
<script src='<c:url value="/js/public/route.js"/>'></script>


<script type="text/javascript">


	$(function ($) {

		$('#conferTime').daterangepicker();
		showTooltip();
	}); // 显示用的

	var StdList = [];
	var conferType = [];
	var magTypeList = [];
	var newsList = [];
	function getStdList() { // 拦截standard表的数据
		$.ajax({
			type: 'GET',
			async: false, // false
			url: '/api/standard/type/论文',
			dataType: 'json',
			contentType: 'application/json;charset=UTF-8',
			success: function (data) {
				StdList = data;
				newsList = getStandardList(StdList, 'papertype', 'col_type', '报刊');
				magTypeList = getStandardList(StdList, 'type', 'col_type', '其他论文');
				conferType = getStandardList(StdList, 'papertype', 'col_type', '会议');

			}
		});
	}
	getStdList(); // 获取成果鉴定的standard待填充

	getMagName(); // 初始化期刊名字
	getDept(); // 初始化所属部门
	CollectionType(); // 初始化收录类型
	NewIssue(); // 初始化报刊类型
	selectData(); // 会议时间插件
	getPaperType(); // 初始化论文类型插件

	var flag = true;// 当前可不可以分配分数

	$('#confirmC').hide();

	var entity = ${ObjectMapper.writeValueAsString(order)}; // 获得order
	var args = entity['variableMap']; // 成员，附件等信息都在里面
	var latestInfo = args['WF_Latest'];
	if (latestInfo == undefined) {
		latestInfo = new Object();
	}

	var taskId = '${taskId}';  // 获得task的id
	var orderId = entity['id']; // 获得order的id
	var status = args['Status']; // 获得状态
	var deptId = latestInfo['dept.id'];
	var dept = {};
	// 获得批复
	var replyByCol, replyByDep;
	var approvalByCol = getApprovalByCol(args);
	if (approvalByCol !== "") {
		replyByCol = args[approvalByCol]['replyByCol'];
	}
	var approvalByDep = getApprovalByDep(args);
	if (approvalByDep !== "") {
		replyByDep = args[approvalByDep]['replyByDep'];
	}

	var paperType = latestInfo["type"]; // 获得论文类型
	var filesData = latestInfo["filesData"]; // 获得附件
	if (isNull(filesData)) {
		filesData = {};
	}
	upToLoadFile(); // 初始化上传插件

	// 显示文件信息
	if (filesData != undefined && filesData != null) {
		showFiles(filesData);
	}

	console.log(entity);
	$('#actorTable').bootstrapTable({
		columns: [
			{
				field: 'staff.id',
				title: '工号',
				sortable: true,
				visible: true
			}, {
				field: 'rank',
				title: '排名',
				sortable: true,
				footerFormatter: "totalNameFormatter"
			}, {
				field: 'staff.name',
				title: '成员',
				sortable: true
			}, {
				field: 'role',
				title: '角色',
				sortable: true
			}, {
				field: 'score',
				title: '分数',
				sortable: true,
				footerFormatter: "totalMarksFormatter"
			}, {
				field: 'unit',
				title: '归属单位',
				sortable: true
			}, {
				field: 'operate',
				title: '操作',
				sortable: true,
				formatter: "operateFormatter",
				events: "operateEvents"
			}],
		data: actorTemp
	});

	if (entity != null || !$.isEmptyObject(entity)) {
		//清空
		$('form input').val();

		actorTemp = [];
		//  赋值 orderId与taskId
		$("#WF_Order").val(orderId);
		$('#WF_Task').val(taskId);

		//  赋值 论文类型
		var $paperType = $("#type").selectize();
		DisplayForm($paperType, paperType, 0);


		if (!isNull(deptId)) {
			$.ajax({
				url: '/api/baseinfo/id/' + deptId,
				type: 'GET',
				dataType: 'json',
				contentType: 'application/json;charset=UTF-8',
				success: function (data) {
					dept = data;
					addOptionSelectize($('#dept').selectize(), [dept]);
					DisplayForm($('#dept').selectize(), dept['id'], 0);
				}
			});
		}

		//  期刊选择框
		var $magId = $("#magId").selectize();

		if (paperType == "magPaper") { // 如果是期刊论文
			// 期刊名称以及刊物级别

			var magId = latestInfo["mag.id"];
			// 赋值 期刊名称
			addOptionSelectize($magId, [{'id': magId, 'name': latestInfo['mag.name']}]);
			DisplayForm($magId, magId, 0);

			// 赋值 期刊级别
			if ((isNull(magId) || isInt(magId)) // 期刊没填 或 期刊在库中
					&& !(status == "Blank" || status == "Uncomplete" || status.indexOf('Refuse') >= 0) // 期刊不在填写中
			) {
				recoveryMagLevel();
			} else {
				replaceMagLevel();
				DisplayForm($("#otherPaper").selectize(), latestInfo['mag.standard.infoMap.col_type'], 0);
			}
		}// end if 期刊论文 显示magId与判断magId的代码顺序不能改

		// 可编辑状态
		if (status == "Blank" || status == "Uncomplete" || status.indexOf('RefuseByCol') >= 0) {
			$('#confirm').show();
			$('#save').show();
			$('.orderBack').hide();
			$('#del').show();
		}
		// 不可编辑
		else {
			uneditableForm();
			console.log('fyh');

			$('#del').hide();
			$('#save').hide();
			if (status == 'Complete' || status == 'WaitForSubmit') { // 确认后和待统一提交还可以撤回
				$('.orderBack').show();
			} else {
				$('.orderBack').hide();
			}
			if (status == 'Complete' && window.location.href.indexOf('task')>=0) { // 主负责人确认后，参与人可以确认
				$('#confirm').show();
				$('.orderBack').hide();
			} else {
				$('#confirm').hide();
			}
		} // end if

		// 显示mag或者confer
		magOrConfer();

		// 显示总分

		// 显示成员信息
		if (latestInfo['actors'] != null) {
			actorTemp = latestInfo['actors'];
		}
		$("#actorTable").bootstrapTable('load', actorTemp);
		// 显示会议论文收录类型
		DisplayForm($("#conferType").selectize(), latestInfo["confer.standard.id"], 0);

		// 显示报刊等级
		DisplayForm($("#newsType").selectize(), latestInfo["newspaper.standard.id"], 0);

		// 填充表单input
		$('#paper').autofill(latestInfo, {
			findbyname: true,
			restrict: false
		});

	} // end if order非空
	//参与者不能删除上传的附件
	if(entity['creator']!=userName){//todo 判断是否是参与者
		$('.delFiles').hide();
	}else if(entity['creator']==userName){
		$('.delFiles').show();
	}
	//监听 更换论文类型
	$('#type').change(function () {
		magOrConfer();
		magOrConfer_add();
	});
	//监听 增加新期刊
	$("#addDiff").click(function () {
		window.location.href = "magazine";
	});

	//监听 添加成员
	$('#addActor').click(function () {
		addActor();
	});
	//监听 分配分数
	$('#getScore').click(function () {
		getScore();
	});
	//监听 点击保存
	$("#save").click(function () {
		save();
	});
	//监听 点击确认
	$("#confirm").click(function () {
		confirm();
	});
	//监听 点击返回
	$(".back").click(function () {
		history.go(-1);
	});
	//监听 删除
	$("#del").click(function () {
		delOrder();
	});
	//监听 点击撤回
	$(".orderBack").click(function () {
		getOrderBack();
	});
	//监听 点击通过
	$("#Approve").click(function () {
		approve();
	});
	//监听 点击驳回
	$("#Refuse").click(function () {
		refuse();
	});
	//监听 部分input不可输入
	$(".uneditableInput").focus(function () {
		this.blur();
	});
</script>


<c:choose>
	<c:when test="${sessionScope.level == '1'}">
		<script src="<c:url value="/js/teacher/paperEdit.js"/>"></script>
	</c:when>
	<c:when test="${sessionScope.level == '2'}">
		<script src="<c:url value="/js/college/paperEdit.js"/>"></script>
	</c:when>
	<c:when test="${sessionScope.level == '3'}">
		<script src="<c:url value="/js/school/paperEdit.js"/>"></script>
	</c:when>
</c:choose>

</body>
</html>
