<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%--
  Created by IntelliJ IDEA.
  User: huyuanyuan555
  Date: 2015/5/8
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>湖北中医药大学</title>
    <meta name="description" content="User login page"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <jsp:include page="public/jsHeader.jsp"></jsp:include>
</head>
<body class="login-layout light-login">
<div class="main-container">
    <div class="main-content">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <div class="login-container">
                    <div class="space-14"></div>
                    <div class="center">
                        <h1>
                            <i class="ace-icon fa fa-leaf green"></i>
                            <span class="grey" id="id-text2">湖北中医药大学<br/>科研信息管理系统</span>
                        </h1>
                    </div>

                    <div class="space-10"></div>

                    <div class="position-relative">
                        <div id="login-box" class="login-box visible widget-box no-border">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h2 class="header blue lighter bigger ">
                                        <%--<i class="ace-icon fa fa-coffee green"></i>--%>
                                        请登录
                                    </h2>

                                    <div class="space-6"></div>

                                    <form id="loginForm">
                                        <fieldset>
                                            <div id="accountInfo">
                                                <label class="block clearfix">
                                                    <span class="block input-icon input-icon-left">
                                                        <input type="text" name="username" id="username"
                                                               class="form-control" placeholder="账号"/>
                                                        <i class="ace-icon fa fa-user"></i>
                                                    </span>
                                                </label>

                                                <label class="block clearfix">
                                                    <span class="block input-icon input-icon-left">
                                                        <input type="password" name="password" id="password"
                                                               class="form-control" placeholder="密码"/>
                                                        <i class="ace-icon fa fa-lock"></i>
                                                    </span>
                                                </label>

                                                <div class="space">
                                                </div>
                                            </div>

                                            <div class="space">
                                            </div>
                                            <div id="masg_alert">

                                            </div>
                                            <div class="clearfix">

                                                <i id="loginBtn"
                                                   class="width-35 pull-right btn btn-sm btn-primary">
                                                    <span class="bigger-110">登录</span>
                                                </i>
                                            </div>

                                            <div class="space-4"></div>
                                        </fieldset>
                                    </form>
                                </div>
                                <!-- /.widget-main -->

                                <div class="toolbar clearfix">
                                    <div>
                                        <a href="#" data-target="#forgot-box"
                                           class="forgot-password-link">
                                            <i class="ace-icon fa fa-arrow-left"></i>
                                            忘记密码
                                        </a>
                                    </div>

                                    <div>
                                        <a href="#" data-target="#signup-box"
                                           class="user-signup-link">
                                            注册账号
                                            <i class="ace-icon fa fa-arrow-right"></i>
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <!-- /.widget-body -->
                        </div>
                        <!-- /.login-box -->

                        <div id="forgot-box" class="forgot-box widget-box no-border">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h4 class="header red lighter bigger">
                                        <i class="ace-icon fa fa-key"></i>
                                        温馨提示：
                                    </h4>

                                    <div class="space-6"></div>
                                    <p>

                                    </p>

                                    <form>
                                        <fieldset>
                                            <label class="block clearfix">
                                                <span class="block input-icon input-icon-right">
                                                 <h3>请联系学院，申请密码重置</h3>
                                            </span>
                                            </label>

                                            <div class="clearfix">
                                            </div>
                                        </fieldset>
                                    </form>
                                </div>
                                <!-- /.widget-main -->

                                <div class="toolbar center">
                                    <a href="#" data-target="#login-box" class="back-to-login-link">
                                        返&nbsp;&nbsp;回
                                        <i class="ace-icon fa fa-arrow-right"></i>
                                    </a>
                                </div>
                            </div><!-- /.widget-body -->
                        </div><!-- /.forgot-box -->

                        <div id="signup-box" class="signup-box widget-box no-border">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h4 class="header green lighter bigger">
                                        <i class="ace-icon fa fa-users blue"></i>
                                        温馨提示：
                                    </h4>

                                    <div class="space-6"></div>
                                    <p> </p>

                                    <form>
                                        <fieldset>
                                            <label class="block clearfix">
                                             <span class="block input-icon input-icon-right">

                                             </span>
                                            </label>

                                            <label class="block clearfix">
                                                <span class="block input-icon input-icon-right">

                                                </span>
                                            </label>

                                            <label class="block">
                                                 <span class="lbl">
                                                     <h3>请联系学校，为您注册一个账号！</h3>
                                                 </span>
                                            </label>

                                            <div class="space-24"></div>

                                            <div class="clearfix">

                                            </div>
                                        </fieldset>
                                    </form>
                                </div>

                                <div class="toolbar center">
                                    <a href="#" data-target="#login-box" class="back-to-login-link">
                                        <i class="ace-icon fa fa-arrow-left"></i>
                                        返&nbsp;&nbsp;回
                                    </a>
                                </div>
                            </div><!-- /.widget-body -->
                        </div><!-- /.signup-box -->

                        <!-- /.signup-box -->
                    </div>
                    <!-- /.position-relative -->

                    <div class="navbar-fixed-top align-right">
                        <br/>
                        &nbsp;
                        <a id="btn-login-light" href="#">Light</a>
                        &nbsp;
                        <span class="blue">/</span>
                        &nbsp;
                        <a id="btn-login-blur" href="#">Blur</a>
                        &nbsp;
                        <span class="blue">/</span>
                        &nbsp;
                        <a id="btn-login-dark" href="#">Dark</a>
                        &nbsp; &nbsp; &nbsp;
                    </div>
                </div>
            </div>
            <!-- /.col -->
        </div>
        <!-- /.row -->
    </div>
    <!-- /.main-content -->
</div>
<!-- /.main-container -->
<script src="<c:url value="/js/public/login.js"/>"></script>

<script type="text/javascript">
    jQuery(function ($) {
        $(document).on('click', '.toolbar a[data-target]', function (e) {
            e.preventDefault();
            var target = $(this).data('target');
            $('.widget-box.visible').removeClass('visible');//hide others
            $(target).addClass('visible');//show target
        });
    });
    //changing background
    jQuery(function ($) {
        $('#btn-login-dark').on('click', function (e) {
            $('body').attr('class', 'login-layout');
            $('#id-text2').attr('class', 'white');

            e.preventDefault();
        });
        $('#btn-login-light').on('click', function (e) {
            $('body').attr('class', 'login-layout light-login');
            $('#id-text2').attr('class', 'grey');

            e.preventDefault();
        });
        $('#btn-login-blur').on('click', function (e) {
            $('body').attr('class', 'login-layout blur-login');
            $('#id-text2').attr('class', 'white');

            e.preventDefault();
        });

    });
</script>
</body>
</html>
