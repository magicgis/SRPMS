<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: huyuanyuan555
  Date: 2015/10/12
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<jsp:useBean id="ObjectMapper" scope="application" class="com.fasterxml.jackson.databind.ObjectMapper"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta charset="utf-8"/>
	<title>成果转化-
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
						<a href="#">Home</a>
					</li>
					<li class="active">成果转化</li>
				</ul>
				<!-- /.breadcrumb -->
			</div>

			<div class="page-content">
				<div class="row">
					<form id="achTran" class="form-horizontal" role="form">
						<div hidden="hidden">
							<input type="text" name="id" id="achTranId" value="${achTran.id}"/>
							<input type="text" name="WF_Type" id="WF_Type" value="achTran"/>
						</div>

						<div class="col-xs-12 col-md-6">
							<div id="achInfo" class="col-xs-12 widget-container-col ui-sortable">
								<div class="widget-box transparent ui-sortable-handle" style="opacity: 1;">
									<div class="widget-header">
										<h4 class="widget-title">成果转化信息</h4>
									</div>
									<div class="widget-body ">
										<div class="widget-main">
											<div class="row">
												<div class="form-group col-xs-12">
													<label class="col-sm-2 control-label no-padding-left"
													       for="name">成果名称</label>

													<div class="col-sm-9">
														<input id="name" name="name"
														       type="text" class="form-control col-xs-12"
														       placeholder="" value="${achTran.name}"/>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="form-group col-xs-12 col-sm-6">
													<label class="col-sm-4 control-label no-padding-left"
													       for="dept">所属部门</label>

													<div class="col-sm-8"><!--选择框-->
														<input class="form-control" id="dept"
														       name="dept.id" placeholder="请选择"/>
													</div>
												</div>

												<div class="form-group col-xs-12 col-sm-6">
													<label class="col-sm-4 control-label no-padding-left"
													       for="changeUnits">转让单位
													</label>

													<div class="col-sm-8">
														<input type="text" id="changeUnits" name="tranUnit"
														       placeholder="" class="form-control col-xs-12"
														       value="${achTran.tranUnit}"/>
													</div>
												</div>
											</div>

											<div class="row">
												<div class="form-group col-xs-12 col-sm-6">
													<label class="col-sm-4 control-label no-padding-left"
													       for="changeDate">转让时间</label>

													<div class="col-sm-8">
														<input class="form-control date-picker confer-input"
														       name="date" id="changeDate" type="text"
														       data-date-format="yyyy-mm-dd"
														       value="${achTran.date}"/>
													</div>
												</div>
												<div class="form-group col-xs-12 col-sm-6">
													<label class="col-sm-4 control-label no-padding-left"
													       for="money">转让金额</label>

													<div class="col-sm-8">
														<input type="text" id="money" name="money"
														       placeholder="单位：万元" class="form-control col-xs-12"
														       value="${achTran.money}"/>
													</div>
												</div>
											</div>

											<div class="row">
												<div class="form-group col-xs-12 col-sm-6">
													<label class="col-sm-4 control-label no-padding-left"
													       for="actualMoney">到账金额</label>

													<div class="col-sm-8">
														<input type="text" id="actualMoney" name="actualMoney"
														       placeholder="单位：万元" class="col-xs-12"
														       value="${achTran.actualMoney}"/>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>

							<div class="col-xs-12 widget-container-col ui-sortable"
							     id="fileHead">
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

						<div class="col-xs-12 col-md-6">

							<div id="actorInfo" class="col-xs-12 widget-container-col ui-sortable">
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
															<a class="btn btn-primary btn-sm addActor">添加成员</a>
														</c:when>
													</c:choose>

	                                                <span class="giveSum">
	                                                    <button class="tabOrdBtn btn btn-primary btn-sm getScore">计算分数</button>
	                                                    <label for="totalScore">原则上可分配总分：</label>
		                                                <input class="score" type="text"
		                                                       name="score" id="totalScore" value="${achTran.score}">
	                                                </span>
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
								<div class="col-xs-12" id="msg_alert"></div>
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
													<c:if test="${sessionScope.level == '3'}">
														<a class="btn btn-primary btn-sm addUnit">
															添加单位</a>
													</c:if>
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

					</form>

					<div id="reply" class="col-xs-12 col-sm-offset-6 widget-container-col ui-sortable">
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

					<div class="col-xs-12" id="info_alert"></div>

				</div>

				<div class="row">

					<div id="formBtn" class="col-xs-12 clearfix">
						<div class="pull-left onDel">
							<button class="tabOrdBtn btn btn-danger btn-sm del"
							        type="button">
								<i class="ace-icon fa fa-trash  bigger-110"></i>
								删除
							</button>
							<button class="tabOrdBtn btn btn-danger btn-sm orderBack"
							        type="button">
								<i class="ace-icon fa  fa-repeat bigger-110"></i>
								撤回
							</button>
						</div>
						<div class="pull-right">
							<button class="tabOrdBtn btn btn-danger btn-sm back"
							        type="button">
								<i class="ace-icon fa fa-reply  bigger-110"></i>
								返回
							</button>
                            <span class="onEdit">
                                <button class="tabOrdBtn btn btn-primary btn-sm save"
                                        type="button">
	                                <i class="ace-icon fa fa-save bigger-110"></i>
	                                保存
                                </button>
                                <button class="tabOrdBtn btn btn-success btn-sm confirm"
                                        type="button">
	                                <i class="ace-icon fa fa-check bigger-110"></i>
	                                确认
                                </button>
                            </span>
                            <span class="onApprove">
                                <button class="tabOrdBtn btn btn-success btn-sm approve"
                                        type="button">
	                                <i class="ace-icon fa fa-check bigger-110"></i>
	                                通过
                                </button>
                                <button class="tabOrdBtn btn btn-warning btn-sm refuse"
                                        type="button">
	                                <i class="ace-icon fa fa-remove bigger-110"></i>
	                                驳回
                                </button>
                            </span>
						</div>
					</div>

				</div>

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
</body>

<script>

	var actorTemp = [];
	var unitTemp = [];
	var Main_Actor;
	var Main_ActorName;
	var filesData = {};
	var status;
	var replyByCol, replyByDep;

</script>

<script src='<c:url value="/js/public/public.js"/>'></script>
<script src='<c:url value="/js/public/pubAchTran.js"/>'></script>

<script>

	getDept();   // 初始化 学院
	upToLoadFile(); // 初始化 上传文件的

	// 成员，单位，文件
	var entity =  ${ObjectMapper.writeValueAsString(achTran)}; // 获得 entity 或 实体

	console.log(entity);
	var userLevel = ${sessionScope.level};
	var isMain = 0;
	var all = entity['argMap']; // 获得 成员，单位，附件，负责人等信息
	var dept = entity['dept'];

	var taskId = '${taskId}';  // 获得 task的id
	var taskName = '${taskName}';

	if (!isNull(all)) {
		filesData = all['filesData'];
		unitTemp = all['units'];
		Main_Actor = all['Main-Actor'];
		if (Main_Actor == userName) {
			isMain = 1;
		}
		status = all['Status'];
		Main_ActorName = all['Main-ActorName'];
	} else {
		all = {};
	}

	var approvalByCol = getApprovalByCol(all);
	if (approvalByCol !== "") {
		replyByCol = all[approvalByCol]['replyByCol'];
	}
	var approvalByDep = getApprovalByDep(all);
	if (approvalByDep !== "") {
		replyByDep = all[approvalByDep]['replyByDep'];
	}

	// 显示 附件
	if (filesData == null) {
		filesData = {};
	}
	showFiles(filesData);


	if (!isNull(dept)) {  // 显示 所属部门
		var $dept = $('#dept').selectize();
		addOptionSelectize($dept, [dept]);
		DisplayForm($dept, dept['id'], 0);
	}

	getActors(); // 这是取成员的
	$('#actorTable').bootstrapTable({
		columns: [
			{
				field: 'staff.id',
				title: '工号',
				sortable: true,
				visible: false
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
	$('#unitTable').bootstrapTable({
		columns: [{
			field: 'rank',
			title: '排名',
			editable: false,
			sortable: true,
			footerFormatter: "totalUnitFormatter"
		}, {
			field: 'unit',
			title: '单位名称',
			editable: false,
			sortable: true
		}, {
			field: 'operate',
			title: '操作',
			sortable: false,
			formatter: "operateFormatterUnit",
			events: "operateEventsUnit"
		}],
		data: unitTemp
	});
	if (!isNull(dept)) {  // 显示 所属部门
		var $dept = $('#dept').selectize();
		addOptionSelectize($dept, [dept]);
		DisplayForm($dept, dept['id'], 0);
	}

	//监听 确认
	$(".confirm").click(function () {
		confirm();
	});
	//监听 返回
	$('.back').click(function () {
		history.go(-1);
	});
	//监听 删除
	$('.del').click(function () {
		delOrder();
	});
	//监听 通过
	$('.approve').click(function () {
		approve();
	});
	//监听 驳回
	$('.refuse').click(function () {
		refuse();
	});
	//监听 保存
	$('.save').click(function () {
		save();
	});
	$('.orderBack').click(function () {
		getOrderBack();
	});
	//监听 添加单位
	$('.addUnit').click(function () {
		addUnit();
	});
	//监听 添加成员
	$('.addActor').click(function () {
		addActor();
	});
	//监听 分配分数
	$('.getScore').click(function () {
		getScore('achTran');
	});

</script>

<c:choose>
	<c:when test="${sessionScope.level == '1'}">
		<script src="<c:url value="/js/teacher/achTranEdit.js"/>"></script>
	</c:when>
	<c:when test="${sessionScope.level == '2'}">
		<script src="<c:url value="/js/college/achTranEdit.js"/>"></script>
	</c:when>
	<c:when test="${sessionScope.level == '3'}">
		<script src="<c:url value="/js/school/achTranEdit.js"/>"></script>
	</c:when>
</c:choose>

</html>

