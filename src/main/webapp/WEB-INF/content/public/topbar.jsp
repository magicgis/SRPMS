<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: guofan
  Date: 2015/4/9
  Time: 22:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="navbar" class="navbar navbar-default">
    <script type="text/javascript">
        try {
            ace.settings.check('navbar', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="navbar-container" id="navbar-container">
        <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
            <span class="sr-only"></span>

            <span class="icon-bar"></span>

            <span class="icon-bar"></span>

            <span class="icon-bar"></span>
        </button>

        <div class="navbar-header pull-left">
            <a href="#" class="navbar-brand">
                <small>
                    <i class="fa fa-leaf"></i>
                    科研积分管理系统
                </small>
            </a>
        </div>

        <div class="navbar-buttons  nav navbar-top-links navbar-right navbar-header pull-right" role="navigation">
                    <button>
                        <span class="user-info" style="font-size: 18px">${sessionScope.user.staff.name}</span>
                    </button>
        </div>
    </div>
    <!-- /.navbar-container -->
</div>
<script>
    var userName = '${sessionScope.user.staff.id}';
</script>
