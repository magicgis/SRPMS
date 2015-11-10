<%--
  Created by IntelliJ IDEA.
  User: guofan
  Date: 2015/10/15
  Time: 2:28
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>待确认项目</title>

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
                        <a href="">Home</a>
                    </li>
                    <li class="active">待确认项目</li>
                </ul>
                <!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="widget-box transparent " style="opacity: 1;" id="confirmTable-box">
                            <div class="widget-body">
                                <div id="tableCon">
                                    <table id="taskTable"
                                           data-show-columns="true"
                                           data-show-refresh="true"
                                           data-page-list="[10,15,20]"
                                           data-pagination="true"
                                           data-single-select="true"
                                           data-click-to-select="true">
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
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
<script src='<c:url value="/js/public/pubAllSRInfo.js"/>'></script>
<script type="text/javascript">
    $("#taskTable").bootstrapTable({
        url: '/api/workflow/' + userName + '/confirmTask',
        sidePagination: "server",
        flat: 'true',
        columns: [{
            radio: true
        }, {
            field: 'id',
            title: 'id',
            visible: false
        }, {
            field: 'variableMap.ActorList',
            title: '人员',
            formatter: "actorTran"
        }, {
            field: 'variableMap.WF_Latest.name',
            title: '科研名称'
        }, {
            field: 'variableMap.WF_Type',
            title: '科研类型',
            formatter: "wfTypeTran"
        }, {
            field: 'variableMap.Status',
            title: '状态',
            formatter: 'statusTran'
        }]
    });

    $('#taskTable').on('click-row.bs.table', function (e, row) {
        var taskId = row['id'];
        window.location.href = '/task/' + taskId;
    });

    //类别更换
    $('.processType').click(function (self) {
        window.location.href = "/process-" + this.id.substr(0, this.id.length - 4) + "-all";
    });

    $('.entityType').click(function (self) {
        window.location.href = "/entity-" + this.id.substr(0, this.id.length - 6) + "-all";
    });

</script>
</body>
</html>