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

        <ul class="nav ace-nav pull-right">
            <li class="light-blue">
                <a href="#">
                    <span class="user-info">
                        <small> 当前用户:<br></small>
                        ${sessionScope.user.staff.name}
                    </span>
                </a>
            </li>
            <li class="light-blue">
                <a href="javascript:void(0)" onclick="amend()">
                    <i class="ace-icon fa fa-cog"></i>
                </a>
            </li>

            <li class="light-blue">
                <a href="<c:url value="/logout"/>">
                    <i class="ace-icon fa fa-power-off"></i>
                </a>
            </li>
        </ul>
    </div>
    <!-- /.navbar-container -->
</div>
<script>
    var userName = '${sessionScope.user.staff.id}';
</script>
<c:choose>
    <c:when test="${sessionScope.level == '1'}">
        <script>
            function amend(){
                BootstrapDialog.show({
                    type: BootstrapDialog.TYPE_PRIMARY,
                    message: function (dialog) {
                        var $message = $('<div></div>');
                        var pageToLoad = dialog.getData('pageToLoad');
                        $message.load(pageToLoad);
                        return $message;
                    },
                    title: "密码修改",
                    data: {
                        'pageToLoad': '../../../dialog/amend.html'
                    },
                    closeByBackdrop: false,
                    buttons: [{
                        id: 'btn-oknm',
                        icon: 'glyphicon glyphicon-check',
                        label: '确定',
                        cssClass: 'btn-info',
                        autospin: false,
                        action: function (dialogRef) {
                            if (!isFull()) {
                                messageModal('请将信息填写完整。');
                                return;
                            }
                            editPassWord();
                            dialogRef.close();
                        }
                    },{
                        id: 'btn-cancel',
                        icon: 'glyphicon glyphicon-remove',
                        label: '关闭',
                        cssClass: 'btn-danger',
                        autospin: false,
                        action: function (dialogRef) {
                            dialogRef.close();
                        }
                    }]
//                    onshown: function () {
//                        fillRoles(projectRoles);
//                    }
                });
            }
            function editPassWord(){
                var accountInfo = $('#pwdInfo').serializeJSON();
                accountInfo['user'] = userName;
                console.log(accountInfo);
//                $.ajax({
//                    url: '/api/user/password',
//                    type: 'put',
//                    data: JSON.stringify(accountInfo),
//                    contentType: 'application/json;charset=UTF-8',
//                    success: function (data) {
//                        if (data == 'true') {
//                            messageModal("修改成功。");
//                        } else if (data == 'false') {
//                            messageModal("修改失败。");
//                        }
//                    }
//                });
            }
        </script>
    </c:when>
</c:choose>
