<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%--
  Created by IntelliJ IDEA.
  User: zheng
  Date: 2015/5/11
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>著作管理 -
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
                        <a href="index.jsp">Home</a>
                    </li>
                    <li class="active">著作</li>
                </ul>
                <!-- /.breadcrumb -->
            </div>

            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="widget-box transparent " style="opacity: 1;" id="bookTable-box">
                            <div class="widget-body">
                                <table id="BookTable"
                                       data-toolbar="#tBookToolbar"
                                       data-search="true"
                                       data-show-columns="true"
                                       data-show-refresh="true"
                                       data-show-toggle="true"
                                       data-page-list="[10]"
                                       data-pagination="true"
                                       data-single-select="true"
                                       data-click-to-select="true">
                                </table>
                                <div id="tBookToolbar">
                                    <button id="addBook" class="btn btn-primary">
                                        <i class="fa fa-plus"></i>添加项目
                                    </button>
                                    <button id="submit" class="btn btn-success">
                                        <i class="fa fa-check"></i> 统一提交
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12" id="info_alert"></div>
                    <div class="col-xs-12">
                        <div class="widget-box transparent ui-sortable-handle collapsed" style="opacity: 1;"
                             id="book-box">
                            <div class="widget-body">
                                <div class="row">
                                    <jsp:include page="public/bookForm.jsp"/>

                                    <c:choose>
                                        <c:when test="${sessionScope.level == '1'}">
                                            <div id="formBtn" class="col-xs-12 clearfix">
                                                <div class="pull-left">
                                                    <button class="btn btn-primary" type="button" id="DiffAdd">
                                                        <i class="ace-icon fa fa-trash  bigger-100"></i>
                                                        差异申请
                                                    </button>
                                                    <button class="btn btn-danger   " type="button" id="del">
                                                        <i class="ace-icon fa fa-trash  bigger-100"></i>
                                                        删除
                                                    </button>
                                                    &nbsp; &nbsp; &nbsp;
                                                    <button class="btn btn-danger  " type="button" id="orderBack">
                                                        <i class="ace-icon fa  fa-repeat bigger-100"></i>
                                                        撤回
                                                    </button>
                                                </div>
                                                <div class="pull-right">
                                                    <button class="btn btn-success" type="button" id="confirm">
                                                        <i class="ace-icon fa fa-check bigger-110"></i>
                                                        确认
                                                    </button>

                                                    <button class="btn btn-info" type="button" id="save">
                                                        <i class="ace-icon fa fa-save bigger-110"></i>
                                                        保存
                                                    </button>
                                                        <%--&nbsp; &nbsp; &nbsp;--%>
                                                    <button class="btn btn-primary" type="button" id="back">
                                                        <i class="ace-icon fa fa-reply  bigger-110"></i>
                                                        返回
                                                    </button>
                                                </div>
                                            </div>
                                        </c:when>
                                        <c:when test="${sessionScope.level == '2'}">
                                            <div id="formBtn" class="col-xs-12">
                                                <div class="col-md-offset-1 col-md-8">
                                                    <button class="btn btn-success" type="button" id="Approve">
                                                        <i class="ace-icon fa fa-check bigger-110"></i>
                                                        通过
                                                    </button>
                                                    &nbsp; &nbsp; &nbsp;
                                                    <button class="btn" type="button" id="back">
                                                        <i class="ace-icon fa fa-reply  bigger-110"></i>
                                                        返回
                                                    </button>
                                                    &nbsp; &nbsp; &nbsp;
                                                    <button class="btn btn-danger" type="button" id="Refuse">
                                                        <i class="ace-icon fa fa-remove bigger-110"></i>
                                                        驳回
                                                    </button>
                                                    &nbsp; &nbsp; &nbsp;
                                                    <button class="btn btn-primary" type="button" id="next">
                                                        下一条
                                                        <i class="ace-icon fa fa-arrow-right bigger-110"></i>
                                                    </button>
                                                </div>
                                            </div>
                                        </c:when>
                                        <c:when test="${sessionScope.level == '3'}">
                                            <div id="formBtn" class="col-xs-12">
                                                <div class="col-md-offset-1 col-md-8">
                                                    <button class="btn btn-success" type="button" id="Approve">
                                                        <i class="ace-icon fa fa-check bigger-110"></i>
                                                        通过
                                                    </button>
                                                    &nbsp; &nbsp; &nbsp;
                                                    <button class="btn" type="button" id="back">
                                                        <i class="ace-icon fa fa-reply  bigger-110"></i>
                                                        返回
                                                    </button>
                                                    &nbsp; &nbsp; &nbsp;
                                                    <button class="btn btn-danger" type="button" id="Refuse">
                                                        <i class="ace-icon fa fa-remove bigger-110"></i>
                                                        驳回
                                                    </button>
                                                    &nbsp; &nbsp; &nbsp;
                                                    <button class="btn btn-primary" type="button" id="next">
                                                        下一条
                                                        <i class="ace-icon fa fa-arrow-right bigger-110"></i>
                                                    </button>
                                                </div>
                                            </div>
                                        </c:when>
                                    </c:choose>

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
<!-- /.main-content -->

<jsp:include page="public/footer.jsp"/>

<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
    <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
</a>
</div>


<!-- /.main-container -->
<script src='<c:url value="/js/public/public.js"/>'></script>
<script src='<c:url value="/js/public/pubBook.js"/>'></script>

<c:choose>
    <c:when test="${sessionScope.level == '1'}">
        <script src="<c:url value="/js/teacher/book.js"/>"></script>
    </c:when>
    <c:when test="${sessionScope.level == '2'}">
        <script src="<c:url value="/js/college/book.js"/>"></script>
    </c:when>
    <c:when test="${sessionScope.level == '3'}">
        <script src="<c:url value="/js/school/book.js"/>"></script>
    </c:when>
</c:choose>
<%--<script src="test.js"></script>--%>
<script type="text/javascript">
    jQuery(function ($) {
        $('#upFiles').ace_file_input({
            style: 'well',
            btn_choose: 'Drop files here or click to choose',
            btn_change: null,
            no_icon: 'ace-icon fa fa-cloud-upload',
            droppable: true,
            thumbnail: 'small'//large | fit
            //,icon_remove:null//set null, to hide remove/reset button
            /**,before_change:function(files, dropped) {
						//Check an example below
						//or examples/file-upload.html
						return true;
					}*/
            /**,before_remove : function() {
						return true;
					}*/
            ,
            preview_error: function (filename, error_code) {
                //name of the file that failed
                //error_code values
                //1 = 'FILE_LOAD_FAILED',
                //2 = 'IMAGE_LOAD_FAILED',
                //3 = 'THUMBNAIL_FAILED'
                //alert(error_code);
            }

        }).on('change', function () {
            //console.log($(this).data('ace_input_files'));
            //console.log($(this).data('ace_input_method'));
        });


        //datepicker plugin
        //link
        $('.date-picker').datepicker({
            autoclose: true,
            todayHighlight: true
        })
            //show datepicker when clicking on the icon
                .next().on(ace.click_event, function () {
                    $(this).prev().focus();
                });


    });

</script>
</body>
</html>
