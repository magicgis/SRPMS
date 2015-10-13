<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    try {
        ace.settings.check('main-container', 'fixed')
    } catch (e) {
    }
</script>

<div id="sidebar" class="sidebar responsive">
    <script type="text/javascript">
        try {
            ace.settings.check('sidebar', 'fixed')
        } catch (e) {
        }
    </script>
    <ul class="nav nav-list">
        <li class="">
            <a href="allSRInfo">
                <i class="menu-icon fa fa-home"></i>
                <span class="menu-text"> 总览 </span>
            </a>
            <b class="arrow"></b>
        </li>

        <c:choose>
            <c:when test="${sessionScope.level == '1'}">
            </c:when>
            <c:when test="${sessionScope.level == '2'|| sessionScope.level == '3'}">
                <li class="">
                    <a href="<c:url value="/userInfo"/>">
                        <i class="menu-icon glyphicon glyphicon-user"></i>
                        <span class="menu-text">教师信息 </span>
                    </a>
                    <b class="arrow"></b>
                </li>
            </c:when>
        </c:choose>


        <li class="">
            <a href="<c:url value="/project"/>">
                <i class="menu-icon fa fa-flask"></i>
                <span class="menu-text"> 科研项目 </span>
            </a>
            <b class="arrow"></b>
        </li>

        <li class="">
            <a href="#" class="dropdown-toggle">
                <i class="menu-icon fa fa-archive "></i>
                <span class="menu-text"> 科研成果 </span>

                <b class="arrow fa fa-angle-down"></b>
            </a>
            <b class="arrow"></b>
            <ul class="submenu">
                <li class="">
                    <a href="<c:url value="/appraise"/>">
                        <i class="menu-icon fa fa-caret-right"></i>
                        成果鉴定
                    </a>

                    <b class="arrow"></b>
                </li>

                <li class="">
                    <a href="<c:url value="/achAward"/>">
                        <i class="menu-icon fa fa-caret-right"></i>
                        成果获奖
                    </a>
                    <b class="arrow"></b>
                </li>
            </ul>
        </li>

        <li class="">
            <a href="<c:url value="/paper"/>">
                <i class="menu-icon fa fa- fa-file-word-o"></i>
                <span class="menu-text"> 论文 </span>
            </a>
            <b class="arrow"></b>
        </li>

        <li class="">
            <a href="<c:url value="/book"/>">
                <i class="menu-icon fa fa-book"></i>
                <span class="menu-text"> 著作 </span>
            </a>
            <b class="arrow"></b>
        </li>

        <li class="">
            <a href="<c:url value="/patent"/>">
                <i class="menu-icon fa fa-fax"></i>
                <span class="menu-text"> 专利 </span>
            </a>
            <b class="arrow"></b>
        </li>

        <li class="">
            <a href="<c:url value="/change"/>">
                <i class="menu-icon fa fa-bar-chart"></i>
                <span class="menu-text"> 成果转化 </span>
            </a>
            <b class="arrow"></b>
        </li>

        <li class="">
            <a href="#" class="dropdown-toggle">
                <i class="menu-icon fa fa-university "></i>
                <span class="menu-text"> 新产品开发 </span>

                <b class="arrow fa fa-angle-down"></b>
            </a>

            <b class="arrow"></b>

            <ul class="submenu">
                <li class="">
                    <a href="<c:url value="/newMedicine"/>">
                        <i class="menu-icon fa fa-caret-right"></i>
                        新药
                    </a>

                    <b class="arrow"></b>
                </li>

                <li class="">
                    <a href="<c:url value="/newFunctionFood"/>">
                        <i class="menu-icon fa fa-caret-right"></i>
                        新功能性食品
                    </a>

                    <b class="arrow"></b>
                </li>
                <li class="">
                    <a href="<c:url value="/newInstruments"/>">
                        <i class="menu-icon fa fa-caret-right"></i>
                        新医疗器具
                    </a>

                    <b class="arrow"></b>
                </li>

                <li class="">
                    <a href="<c:url value="/newOthers"/>">
                        <i class="menu-icon fa fa-caret-right"></i>
                        其他新产品
                    </a>

                    <b class="arrow"></b>
                </li>
            </ul>
        </li>
        <li class="">
            <a href="<c:url value="/different"/>">
                <i class="menu-icon fa fa-bar-chart"></i>
                <span class="menu-text"> 差异处理 </span>
            </a>
            <b class="arrow"></b>
        </li>
        <li>
            <a href="<c:url value="/magazine"/>">
                <i class="menu-icon glyphicon glyphicon-bookmark"></i>
                <span class="menu-text">期刊新增 </span>
            </a>
            <b class="arrow"></b>
        </li>
        <c:choose>
            <c:when test="${sessionScope.level == '1'}">
            </c:when>
            <c:when test="${sessionScope.level == '2'}">
            </c:when>
            <c:when test="${sessionScope.level == '3'}">
                <li>
                    <a href="<c:url value="/sysBaseInfo"/>">
                        <i class="menu-icon glyphicon glyphicon-bookmark"></i>
                        <span class="menu-text">基础表管理 </span>
                    </a>
                    <b class="arrow"></b>
                </li>
            </c:when>
        </c:choose>
    </ul>
    <!-- /.nav-list -->

    <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
        <i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left"
           data-icon2="ace-icon fa fa-angle-double-right"></i>
    </div>

    <script type="text/javascript">
        try {
            ace.settings.check('sidebar', 'collapsed')
        } catch (e) {
        }
    </script>
</div>