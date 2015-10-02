<%--
  Created by IntelliJ IDEA.
  User: zheng
  Date: 2015/4/24
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
  <meta charset="utf-8"/>
  <title>成果转化管理 - 教师</title>

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
          <li class="active">成果转化</li>
        </ul>
        <!-- /.breadcrumb -->
      </div>

      <div class="page-content">
        <div class="row">
          <div class="col-xs-12">
            <div class="widget-box transparent " style="opacity: 1;" id="changeTable-box">
              <div class="widget-body">
                <table id="ChangeTable"
                       data-toolbar="#tChangeToolbar"
                       data-search="true"
                       data-show-columns="true"
                       data-show-refresh="true"
                       data-show-toggle="true"
                       data-page-list="[10]"
                       data-pagination="true"
                       data-single-select="true"
                       data-click-to-select="true">
                </table>
                <div id="tChangeToolbar">
                  <button id="addNewChange" class="btn btn-primary">
                    <i class="fa fa-pencil"></i>添加
                  </button>
                  <%--<button id="editAward" class="btn btn-primary">--%>
                  <%--<i class="fa fa-pencil"></i> 编辑--%>
                  <%--</button>--%>
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
                 id="change-box">
              <div class="widget-body">
                <div class="row">
                  <jsp:include page="public/awardForm.jsp"/>
                  <div id="formBtn" class="col-xs-12 clearfix">
                    <div class="pull-left">
                      <button class="btn btn-danger   " type="button" id="del">
                        <i class="ace-icon fa fa-trash  bigger-100"></i>
                        删除
                      </button>
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
<script src='../public/js/public.js'></script>
<script src='../public/js/pubChange.js'></script>
<script src="js/change.js"></script>
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
