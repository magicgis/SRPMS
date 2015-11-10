<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>
        <c:choose>
            <c:when test="${sessionScope.level == '1'}">
                已入库信息 - 教师
            </c:when>
            <c:when test="${sessionScope.level == '2'}">
                学院已入库信息概况
            </c:when>
            <c:when test="${sessionScope.level == '3'}">
                已入库科研信息概况-学校
            </c:when>
        </c:choose></title>

    <meta name="description" content="Dynamic tables and grids using jqGrid plugin"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>

    <jsp:include page="public/jsHeader.jsp"/>
    <style>
        td.bs-checkbox {
            display: none;
        }

        th.bs-checkbox {
            display: none;
        }

	    .bars {
		    width: 40%;
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
                    <li class="active" id="title">科研概况</li>
                </ul>
                <!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="widget-box transparent " style="opacity: 1;" id="confirmTable-box">
                            <div class="widget-body">
                                <div id="tableCon">
                                    <table id="allEntityTable"
                                           data-toolbar="#EntityToolbar"
                                           data-show-columns="true"
                                           data-show-refresh="true"
                                           data-page-list="[10,15,20]"
                                           data-pagination="true"
                                           data-single-select="true"
                                           data-click-to-select="true">
                                    </table>
                                    <div id="EntityToolbar">
                                        <c:choose>
                                            <c:when test="${sessionScope.level == '3'}">
                                                <form class="form-horizontal" role="form">
                                                    <div class="form-group col-xs-12">
                                                        <div class="row">
                                                            <div class="btn-group col-xs-3">
                                                                <button class="btn btn-primary dropdown-toggle"
                                                                        data-toggle="dropdown">
                                                                    信息添加
                                                                    <span class="ace-icon fa fa-caret-down icon-on-right"></span>
                                                                </button>
                                                                <ul class="dropdown-menu dropdown-menu-left">
                                                                    <li id="newPatent">
                                                                        <a href="<c:url value="/patent/new"/> ">专利信息</a>
                                                                    </li>
                                                                    <li id="newProject">
                                                                        <a href="<c:url value="/project/new"/> ">项目信息</a>
                                                                    </li>
                                                                    <li id="newBook">
                                                                        <a href="<c:url value="/book/new"/> ">著作信息</a>
                                                                    </li>
                                                                    <li id="newAppaise">
                                                                        <a href="<c:url value="/achAppraisal/new"/> ">鉴定信息</a>
                                                                    </li>
                                                                    <li id="newAchTran">
                                                                        <a href="<c:url value="/achTran/new"/> ">转化信息</a>
                                                                    </li>
                                                                    <li id="newAchAward">
                                                                        <a href="<c:url value="/achAward/new"/> ">获奖信息</a>
                                                                    </li>
                                                                </ul>
                                                            </div>
                                                            <div class="col-xs-9">
                                                                <label class="col-xs-3 control-label no-padding-right"
                                                                       for="keyName">
                                                                    输入姓名:
                                                                </label>
                                                                <div class="col-xs-7">
                                                                    <input id="keyName" name="keyName"
                                                                           class="col-xs-12 no-padding-right" type="text">
                                                                </div>
                                                                <div class="col-xs-2">
                                                                    <button class="btn btn-success btn-sm inquiryBtn" type="button">查询</button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </form>
                                            </c:when>
                                            <c:when test="${sessionScope.level == '1'}">
                                                <div class="btn-group">
                                                    <button class="btn btn-primary dropdown-toggle"
                                                            data-toggle="dropdown" id="levelOption">
                                                        查看选项
                                                        <span class="ace-icon fa fa-caret-down icon-on-right"></span>
                                                    </button>
                                                    <ul class="dropdown-menu dropdown-menu-left">
                                                        <li class="level" id="all">
                                                            <a>所有科研</a>
                                                        </li>
                                                        <li class="level" id="1st">
                                                            <a>主导科研</a>
                                                        </li>
                                                        <li class="level" id="2nd">
                                                            <a>参与科研</a>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </c:when>
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

<!-- /.main-content -->


<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
    <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
</a>
<!-- /.main-container -->
<script src='<c:url value="/js/public/public.js"/>'></script>
<script>
    var student = [{"id":"9998","name":"在校学生","col":{"value":""}},
        {"id":"9999","name":"外校人员","col":{"value":""}}];
    var allTable = $('#allEntityTable');
    //根据url载入不同的API
    var entityType = '${type}';//项目类型
    var level = '${level}';//参与类型
    console.log(entityType);
    console.log(level);
    if (entityType === null) {
        srtype = 'all';
    }
    if (level === null) {
        level = 'all';
    }

    $(function () {
        switchEntityView(entityType + "Entity");
    });

    //链接跳转
    allTable.on('click-row.bs.table', function (e, row) {
        window.location.href = '/' + entityType + '/' + row['id'];
    });

    //类别更换
    $('.entityType').click(function (self) {
        switchEntityView(this.id);
    });

    function changeUrl() {
        var stateObject = {};
        var newUrl = "/entity-" + entityType + "-" + level;
        history.pushState(stateObject, null, newUrl);
    }

    $('.processType').click(function (self) {
        window.location.href = "/process-" + this.id.substr(0, this.id.length - 4) + "-all";
    });

    function view(index, row, value) {
        {
            return [
                '<a class="edit ml10" href="javascript:void(0)" title="Edit">',
                '<i class="ace-icon fa fa-pencil bigger-130"></i>查看',
                '</a>'
            ].join('');
        }
    }

    $('.inquiryBtn').click(function(){
        var keyName=$('#keyName').val();
        console.log(keyName);
        if(keyName==''){
            messageModal("请输入姓名！");
            return;
        }
        var keyNumber=keyName;
        allTable.bootstrapTable('destroy').bootstrapTable({
            url: "api/entity/"+keyNumber+"/all/all",
            sidePagination: "server",
            columns: [{
                radio: true
            }, {
                field: 'id',
                title: 'id',
                visible: false
            }, {
                field: 'name',
                title: '科研名称'
            }, {
                field: 'WF_Type',
                title: '科研类型',
                formatter: "wfTypeTran"
            }, {
                field: 'ActorList',
                title: '人员',
                formatter: "actorTran"
            }, {
                field: 'Status',
                title: '状态',
                formatter: 'statusTran'
            }, {
                field: 'operator',
                align: 'center',
                title: '操作',
                width: 75
            }],
            responseHandler: tableTrans
        });
    });

    $('#keyName').selectize({
        valueField: 'id',
        labelField: 'name',
        searchField: 'name',
        create: false,
        preload:true,
        maxItems: 1,
        render: {
            option: function(item, escape) {
                return '<div>' +
		                '<span class="id">' + escape(item.id) + '</span>' +
		                '&nbsp;' + '&nbsp;' +
                        '<span class="name">' + escape(item.name) + '</span>' +
                        '&nbsp;' + '&nbsp;' +
                        '<span class="dept">' + escape(item["col"].value || '') + '</span>' +
                        '</div>';
            }
        },
        load: function(query, callback) {
            if(!query.length) return callback(student);
            $.ajax({
                url: '../api/staff/json',
                type: 'get',
                dataType: 'json',
                data: {
                    query: query
                },
                error: function() {
                    callback();
                },
                success: function(res) {
                    if(res==undefined||res==null){
                        return;
                    }
                    callback(res);
                }
            });
        }
    });
</script>

<c:choose>
    <c:when test="${sessionScope.level == '1'}">
        <script src="<c:url value="/js/teacher/entity.js"/>"></script>
    </c:when>
    <c:when test="${sessionScope.level == '2'}">
        <script src="<c:url value="/js/college/entity.js"/>"></script>
    </c:when>
    <c:when test="${sessionScope.level == '3'}">
        <script src="<c:url value="/js/school/entity.js"/>"></script>
    </c:when>
</c:choose>

</body>
</html>