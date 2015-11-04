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
                科研概况 - 教师
            </c:when>
            <c:when test="${sessionScope.level == '2'}">
                学院科研概况
            </c:when>
            <c:when test="${sessionScope.level == '3'}">
                学校科研概况
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
                                    <table id="allSRInfoTable"
                                           data-toolbar="#SRToolbar"
                                           data-show-columns="true"
                                           data-show-refresh="true"
                                           data-page-list="[10,15,20]"
                                           data-pagination="true"
                                           data-single-select="true"
                                           data-click-to-select="true">
                                    </table>
                                    <div id="SRToolbar">
                                        <c:choose>
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
                                                <div class="btn-group">
                                                    <button class="btn btn-primary dropdown-toggle"
                                                            data-toggle="dropdown">
                                                        信息申报
                                                        <span class="ace-icon fa fa-caret-down icon-on-right"></span>
                                                    </button>
                                                    <ul class="dropdown-menu dropdown-menu-left">
                                                        <li id="newPaper">
                                                            <a>论文</a>
                                                        </li>
                                                        <li id="newBook">
                                                            <a>著作</a>
                                                        </li>
                                                        <li id="newMag">
                                                            <a>期刊</a>
                                                        </li>
                                                    </ul>
                                                </div>
                                                <button class="btn btn-success allSubmit">
                                                    <i class="ace fa fa-check"></i>统一提交
                                                </button>
                                            </c:when>
                                            <c:when test="${sessionScope.level == '2'}">
                                                <button class="btn btn-success allSubmit">
                                                    <i class="ace fa fa-check"></i>统一提交
                                                </button>
                                            </c:when>
                                            <c:when test="${sessionScope.level == '3'}">

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


<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
    <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
</a>
<!-- /.main-container -->
<script src='<c:url value="/js/public/public.js"/>'></script>
<script src='<c:url value="/js/public/pubAllSRInfo.js"/>'></script>
<script>

    var allTable = $('#allSRInfoTable');
    //根据url载入不同的API
    var srtype = '${type}';//项目类型
    var level = '${level}';//参与类型
    console.log(srtype);
    console.log(level);
    if (srtype === null) {
        srtype = 'all';
    }
    if (level === null) {
        level = 'all';
    }


    $(function () {
        switchView(srtype + "View");
    });

    //链接跳转
    allTable.on('click-row.bs.table', function (e, row) {
        window.location.href = '/order/' + row['id'];
    });

    //类别更换
    $('.processType').click(function (self) {
        switchView(this.id);
    });

    function apiUrl(type) {
        srtype = type;
        return "/api/workflow/order/" + userName + "/" + type + "/" + level;
    }

    function switchView(processType) {
        var title = "Wow Title";
        switch (processType) {
            case 'projectView':
                allTable.bootstrapTable('destroy').bootstrapTable({
                    url: apiUrl("project"),
                    sidePagination: "server",
                    columns: [{
                        radio: true
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field: 'name',
                        title: '名称',
                        sortable: true
                    }, {
                        field: 'type',
                        title: '类别',
                        sortable: true
                    }, {
                        field: 'rank',
                        title: '等级',
                        sortable: true
                    }, {
                        field: 'rateUnit',
                        title: '评分来源',
                        sortable: true
                    }, {
                        field: 'realDate',
                        title: '实际结题时间',
                        sortable: true
                    }, {
                        field: 'Status',
                        title: '状态',
                        sortable: true,
                        formatter: 'statusTran'
                    }, {
                        field: 'operator',
                        align: 'center',
                        title: '操作',
                        formatter: view
                    }],
                    responseHandler: tableTrans
                });
                break;
            case 'patentView':
                allTable.bootstrapTable('destroy').bootstrapTable({
                    url: apiUrl("patent"),
                    sidePagination: "server",
                    columns: [{
                        radio: true
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field: 'name',
                        title: '专利名称',
                        sortable: true
                    }, {
                        field: 'standard.value',
                        title: '专利类型',
                        sortable: true
                    }, {
                        field: 'patentNo',
                        title: '专利号',
                        sortable: true
                    }, {
                        field: 'apprDate',
                        title: '获批时间',
                        sortable: true
                    }, {
                        field: 'ActorList',
                        title: '参与者',
                        sortable: true,
                        formatter: "actorTran"
                    }, {
                        field: 'Status',
                        title: '状态',
                        sortable: true,
                        formatter: 'statusTran'
                    }],
                    responseHandler: tableTrans
                });
                break;
            case 'paperView':
                allTable.bootstrapTable('destroy').bootstrapTable({
                    url: apiUrl("paper"),
                    sidePagination: "server",
                    columns: [{
                        radio: true
                    }, {
                        field: 'id',
                        title: 'id',
                        visible: false
                    }, {
                        field: 'name',
                        title: '论文名称'
                    }, {
                        field: 'type',
                        title: '论文类别',
                        formatter: "typeTran"
                    }, {
                        field: 'pubDate',
                        title: '发表日期'
                    }, {
                        field: 'ActorList',
                        title: '参与者',
                        formatter: "actorTran"
                    }, {
                        field: 'Status',
                        title: '状态',
                        formatter: 'statusTran'
                    }, {
                        field: 'operator',
                        align: 'center',
                        title: '操作',
                        formatter: view
                    }],
                    responseHandler: tableTrans
                });
                break;
            case 'bookView':
                allTable.bootstrapTable('destroy').bootstrapTable({
                    url: apiUrl("book"),
                    sidePagination: "server",
                    flat:true,
                    columns: [{
                        radio: true
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field: 'name',
                        title: '著作名称',
                        sortable: true
                    }, {
                        field: 'pubType',
                        title: '出版类型',
                        sortable: true,
                        formatter:'pubTypeTrans'
                    }, {
                        field: 'sumWord',
                        title: '著作总字数',
                        sortable: true
                    }, {
                        field: 'bkReward',
                        title: '著作获奖情况',
                        sortable: true
                    }, {
                        field:'argMap.Main-ActorName',
                        title:'负责人',
                        sortable:true
                    }, {
                        field: 'publisher',
                        title: '出版社',
                        sortable: true
                    }, {
                        field: 'Status',
                        title: '状态',
                        sortable: true,
                        formatter: 'statusTran'
                    }],
                    responseHandler: tableTrans
                });
                break;
            case 'achAppraisalView':
                allTable.bootstrapTable('destroy').bootstrapTable({
                    url: apiUrl("achAppraisal"),
                    sidePagination: "server",
                    columns: [{
                        radio: true
                        //visible:false
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field: 'name',
                        title: '成果名称',
                        sortable: true
                    }, {
                        field: 'achType',
                        title: '成果类型',
                        sortable: true,
                        formatter: "typeTran"
                    }, {
                        field: 'certifyUnit',
                        title: '鉴定单位',
                        sortable: 'true'
                    }, {
                        field: 'date',
                        title: '鉴定日期',
                        sortable: true
                    }, {
                        field: 'ActorList',
                        title: '参与者',
                        sortable: true,
                        formatter: "actorTran"
                    }, {
                        field: 'Status',
                        title: '状态',
                        sortable: true,
                        formatter: 'statusTran'
                    }],
                    responseHandler: tableTrans

                });
                break;
            case 'achAwardView':
                allTable.bootstrapTable('destroy').bootstrapTable({
                    url: apiUrl('achAward'),
                    sidePagination: "server",
                    flat:true,
                    columns: [{
                        radio: true
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field: 'name',
                        title: '成果名称',
                        sortable: true
                    }, {
                        field: 'achType',
                        title: '成果类型',
                        sortable: true,
                        formatter: 'typeTran'
                    },{
                        field: 'argMap.Main-ActorName',
                        title: '负责人'
                    },{
                        field: 'date',
                        title: '获奖时间',
                        sortable: true
                    }, {
                        field: 'Status',
                        title: '状态',
                        sortable: true,
                        formatter: 'statusTran'
                    }],
                    responseHandler: tableTrans
                });
                break;
            case 'achTranView':
                allTable.bootstrapTable('destroy').bootstrapTable({
                    url: apiUrl('achTran'),
                    sidePagination: "server",
                    columns: [{
                        radio: true
                        //visible:false
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field: 'name',
                        title: '成果名称',
                        sortable: true
                    }, {
                        field: 'achType',
                        title: '成果类型',
                        sortable: true,
                        formatter: "typeTran"
                    }, {
                        field: 'tranUnit',
                        title: '转让单位',
                        sortable: 'true'
                    }, {
                        field: 'date',
                        title: '转让日期',
                        sortable: true
                    }, {
                        field: 'ActorList',
                        title: '参与者',
                        sortable: true,
                        formatter: "actorTran"
                    }, {
                        field: 'Status',
                        title: '状态',
                        sortable: true,
                        formatter: 'statusTran'
                    }],
                    responseHandler: tableTrans

                });
                break;
            case 'newFoodView':
                allTable.bootstrapTable('destroy').bootstrapTable({
                    url: entityApiUrl('newFood'),
                    sidePagination: "server",
                    flat: true,
                    columns: [{
                        radio: true
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field:'name',
                        title:'食品名称',
                        sortable: true
                    }, {
                        field: 'argMap.Main-ActorName',
                        title: '负责人'
                    },{
                        field:'newFoodDate',
                        title:'获批时间',
                        sortable: true
                    },{
                        field: 'Status',
                        title: '状态',
                        sortable: true,
                        formatter: 'statusTran'
                    }],
                    responseHandler: tableTrans
                });
                break;
            case 'newInstruView':
                allTable.bootstrapTable('destroy').bootstrapTable({
                    url: entityApiUrl('newInstru'),
                    sidePagination: "server",
                    flat: true,
                    columns: [{
                        radio: true
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field:'name',
                        title:'食品名称',
                        sortable: true
                    },{
                        field:'newInstruDate',
                        title:'获批时间',
                        sortable: true
                    } ,{
                        field: 'argMap.Main-ActorName',
                        title: '负责人'
                    },{
                        field: 'Status',
                        title: '状态',
                        sortable: true,
                        formatter: 'statusTran'
                    }],
                    responseHandler: tableTrans
                });
                break;
            case 'newMedicineView':
            allTable.bootstrapTable('destroy').bootstrapTable({
                url: entityApiUrl('newMedicine'),
                sidePagination: "server",
                flat: true,
                columns: [{
                    radio: true
                }, {
                    field: 'id',
                    title: 'id',
                    sortable: true,
                    visible: false
                }, {
                    field:'name',
                    title:'药品名称',
                    sortable: true
                },{
                    field:'date',
                    title:'获批时间',
                    sortable: true
                } ,{
                    field: 'argMap.Main-ActorName',
                    title: '负责人'
                },{
                    field: 'Status',
                    title: '状态',
                    sortable: true,
                    formatter: 'statusTran'
                }],
                responseHandler: tableTrans
            });
            break;
            case 'newOtherView':
                allTable.bootstrapTable('destroy').bootstrapTable({
                    url: entityApiUrl('newOther'),
                    sidePagination: "server",
                    flat: true,
                    columns: [{
                        radio: true
                    }, {
                        field: 'id',
                        title: 'id',
                        sortable: true,
                        visible: false
                    }, {
                        field:'name',
                        title:'产品名称',
                        sortable: true
                    },{
                        field:'date',
                        title:'获批时间',
                        sortable: true
                    } ,{
                        field: 'argMap.Main-ActorName',
                        title: '负责人'
                    },{
                        field: 'Status',
                        title: '状态',
                        sortable: true,
                        formatter: 'statusTran'
                    }],
                    responseHandler: tableTrans
                });
                break;
            case 'allView':
                allTable.bootstrapTable('destroy').bootstrapTable({
                    url: apiUrl('all'),
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
                        field: 'Main-ActorName',
                        title: '负责人'
                    }, {
                        field: 'Status',
                        title: '状态',
                        formatter: 'statusTran'
                    }, {
                        field: 'operator',
                        align: 'center',
                        title: '操作',
                        width: 75,
                        formatter: view
                    }],
                    responseHandler: tableTrans
                });
                break;
        }
        changeUrl()
    }

    function changeUrl() {
        var stateObject = {};
        var newUrl = "/process-" + srtype + "-" + level;
        history.pushState(stateObject, null, newUrl);
    }

    $('.entityType').click(function (self) {
        window.location.href = "/entity-" + this.id.substr(0, this.id.length - 6) + "-all";
    });

</script>
<c:choose>
    <c:when test="${sessionScope.level == '1'}">
        <script src="<c:url value="/js/teacher/allSRInfo.js"/>"></script>
    </c:when>
    <c:when test="${sessionScope.level == '2'}">
        <script src="<c:url value="/js/college/allSRInfo.js"/>"></script>
    </c:when>
</c:choose>
</body>
</html>