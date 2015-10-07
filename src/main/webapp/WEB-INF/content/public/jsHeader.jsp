<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: guofan
  Date: 2015/4/11
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- basic scripts -->

<!--[if !IE]> -->
<script src="<c:url value="/assets/js/jquery.2.1.1.min.js"/>"></script>
<%--<script src="../assets/js/fileinput_locale_zh.js"></script>--%>
<!-- <![endif]-->

<!--[if IE]>
<script src="/assets/js/jquery.1.11.1.min.js"></script>
<![endif]-->

<!--[if !IE]> -->
<script type="text/javascript">
    window.jQuery || document.write("<script src='/assets/js/jquery.min.js'>" + "<" + "/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
window.jQuery || document.write("<script src='assets/js/jquery1x.min.js'>" + "<" + "/script>");
</script>
<![endif]-->
<%--<script type="text/javascript">--%>
<%--if ('ontouchstart' in document.documentElement) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");--%>
<%--</script>--%>

<script src="<c:url value="/assets/js/bootstrap.min.js"/>"></script>

<!-- page specific plugin scripts -->
<%--<script src="../assets/js/bootstrap-datepicker.min.js"></script>--%>

<!-- ace scripts -->
<script src="<c:url value="/assets/js/ace-elements.min.js"/>"></script>
<script src="<c:url value="/assets/js/ace.min.js"/>"></script>

<!-- bootstrap & fontawesome -->
<link rel="stylesheet" href="<c:url value="/assets/css/bootstrap.min.css"/>"/>
<link rel="stylesheet" href="<c:url value="/assets/font-awesome/4.2.0/css/font-awesome.min.css"/>"/>

<!-- page specific plugin styles -->
<link rel="stylesheet" href="<c:url value="/assets/css/jquery-ui.min.css"/>"/>
<link rel="stylesheet" href="<c:url value="/assets/css/jquery-ui.custom.min.css"/>"/>

<link rel="stylesheet" href="<c:url value="/assets/css/datepicker.min.css"/>"/>
<link rel="stylesheet" href="<c:url value="/assets/css/bootstrap-timepicker.min.css"/>"/>
<link rel="stylesheet" href="<c:url value="/assets/css/daterangepicker.min.css"/>"/>
<link rel="stylesheet" href="<c:url value="/assets/css/bootstrap-datetimepicker.min.css"/>"/>
<%--<link rel="stylesheet" href="../assets/css/colorpicker.min.css"/>--%>
<link rel="stylesheet" href="<c:url value="/assets/css/bootstrap-dialog.min.css"/>">

<link rel="stylesheet" href="<c:url value="/assets/css/selectize.bootstrap3.css"/>">

<!-- ace styles -->
<link rel="stylesheet" href="<c:url value="/assets/css/ace.min.css"/>" class="ace-main-stylesheet" id="main-ace-style"/>

<!--[if lte IE 9]>
<link rel="stylesheet" href="/assets/css/ace-part2.min.css" class="ace-main-stylesheet"/>
<link rel="stylesheet" href="/assets/css/ace-rtl.min.css" />
<![endif]-->

<!--[if lte IE 9]>
<link rel="stylesheet" href="/assets/css/ace-ie.min.css"/>
<![endif]-->

<!-- inline styles related to this page -->

<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

<!--[if lte IE 8]>
<script src="/assets/js/excanvas.min.js"></script>
<script src="/assets/js/html5shiv.min.js"></script>
<script src="/assets/js/respond.min.js"></script>
<![endif]-->

<!-- 完全不知道哪些有用哪些没有了-->
<%--<script src="../assets/js/jquery-ui.custom.min.js"></script>--%>
<%--<script src="../assets/js/jquery.ui.touch-punch.min.js"></script>--%>
<script src="<c:url value="/assets/js/chosen.jquery.min.js"/>"></script>
<%--<script src="../assets/js/fuelux.spinner.min.js"></script>--%>
<script src="<c:url value="/assets/js/bootstrap-datepicker.min.js"/>"></script>
<script src="<c:url value="/assets/js/moment.min.js"/>"></script>
<script src="<c:url value="/assets/js/daterangepicker.min.js"/>"></script>

<script src="<c:url value="/plugin/jquery.serializejson.js"/>"></script>
<script src="<c:url value="/plugin/form/jquery.formautofill.min.js"/>"></script>
<%--<script src="../assets/js/jquery.ui.touch-punch.min.js"></script>--%>
<!-- -->


<!-- bootstrap-table start -->
<link rel="stylesheet" href="<c:url value="/plugin/bootstrap-table/src/bootstrap-table.css"/>">
<script src="<c:url value="/plugin/bootstrap-table/src/bootstrap-table.js"/>"></script>
<script src="<c:url value="/plugin/tableExport/jquery.base64.js"/>"></script>
<script src="<c:url value="/plugin/bootstrap-table/src/extensions/editable/bootstrap-table-editable.js"/>"></script>


<script src="<c:url value="/plugin/bootstrap-table/src/extensions/export/bootstrap-table-export.js"/>"></script>
<script src="<c:url value="/plugin/tableExport/tableExport.js"/>"></script>
<script src="<c:url value="/plugin/bootstrap3-dialog/js/bootstrap-dialog.min.js"/>"></script>

<script src="<c:url value="/assets/js/jquery.cookie.js"/>"></script>
<script src="<c:url value="/assets/js/jquery.form.js"/>"></script>

<script src="<c:url value="/assets/js/selectize.min.js"/>"></script>
<script src="<c:url value="/assets/js/bootstrap-table-flatJSON.min.js"/>"></script>

<!-- Huploadify -->
<script src="<c:url value="/plugin/Huploadify/jquery.Huploadify.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/plugin/Huploadify/Huploadify.css"/>">

<!-- bootstrap-table end -->
<style>
    .modal-content {
        border-radius: 5px;
        box-shadow: 0px 3px 9px rgba(0, 0, 0, 0.5);
    }

    .modal-footer {
        border-radius: 5px;
        background-color: #ffffff;
    }

    .selectize-input {
        border-radius: 0px;
        border: 1px solid #D5D5D5 !important;
    }

    textarea,
    input[type="text"] {
        color: #000;
    }

    .form-group input[disabled] {
        color: #333 !important;
    }

    .selectize-control .selectize-input.disabled {
        /*color: #848484;*/
        opacity: 1;
        border-color: #d5d5d5;
        background-color: #EEE;
    }

    .selectize-control.multi .selectize-input.disabled > div {
        color: #333;
        background: none repeat scroll 0 0 #EEE;
    }

    button.tabOrdBtn {
        margin: 0px 10px 0px 10px;
    }
    /*.user-info {*/
    /*width: 150px;*/
    /*}*/
</style>
