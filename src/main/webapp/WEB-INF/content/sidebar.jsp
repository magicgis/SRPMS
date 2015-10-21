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
            <a href="#">
                <i class="menu-icon fa fa-home"></i>
                <span class="menu-text"> 总览 </span>
            </a>
            <b class="arrow"></b>
        </li>

        <c:choose>
            <c:when test="${sessionScope.level == '1'}">
                <li class="">
                    <a href="<c:url value="/task"/>">
                        <i class="menu-icon fa fa-check-square-o "></i>
                        <span class="menu-text">待确认 </span>
                    </a>
                    <b class="arrow"></b>
                </li>
            </c:when>
        </c:choose>

        <li class="">

            <a class="dropdown-toggle">
                <i class="menu-icon fa fa-university "></i>
                <span class="menu-text"> 申报信息 </span>
                <b class="arrow fa fa-angle-down"></b>
            </a>
            <b class="arrow"></b>

            <ul class="submenu">

                <li class="">
                    <a href="<c:url value="/process"/>">
                        <i class="menu-icon fa fa-home"></i>
                        <span class="menu-text"> 所有 </span>
                    </a>
                    <b class="arrow"></b>
                </li>

                <li class="processType" id="projectView">
                    <a>
                        <i class="menu-icon fa fa-flask"></i>
                        <span class="menu-text"> 科研项目 </span>
                    </a>
                    <b class="arrow"></b>
                </li>

                <li class="processType" id="paperView">
                    <a>
                        <i class="menu-icon fa fa- fa-file-word-o"></i>
                        <span class="menu-text"> 论文 </span>
                    </a>
                    <b class="arrow"></b>
                </li>

                <li class="processType" id="bookView">
                    <a>
                        <i class="menu-icon fa fa-book"></i>
                        <span class="menu-text"> 著作 </span>
                    </a>
                    <b class="arrow"></b>
                </li>

                <li class="processType" id="patentView">
                    <a>
                        <i class="menu-icon fa fa-file-archive-o"></i>
                        <span class="menu-text"> 专利 </span>
                    </a>
                    <b class="arrow"></b>
                </li>

                <li class="">
                    <a href="#" class="dropdown-toggle">
                        <i class="menu-icon fa fa-caret-right"></i>
                        成果
                        <b class="arrow fa fa-angle-down"></b>
                    </a>

                    <b class="arrow"></b>

                    <ul class="submenu">

                        <li class="processType" id="achAppraisalView">
                            <a>
                                <i class="menu-icon fa fa-legal"></i>
                                <span class="menu-text">成果鉴定</span>
                            </a>

                            <b class="arrow"></b>
                        </li>

                        <li class="processType" id="achAwardView">
                            <a>
                                <i class="menu-icon fa fa-trophy"></i>
                                <span class="menu-text"> 成果获奖</span>
                            </a>
                            <b class="arrow"></b>
                        </li>

                        <li class="processType" id="achTranView">
                            <a>
                                <i class="menu-icon fa fa-recycle"></i>
                                <span class="menu-text"> 成果转化 </span>
                            </a>
                            <b class="arrow"></b>
                        </li>


                    </ul>
                </li>


                <li class="">
                    <a href="#" class="dropdown-toggle">
                        <i class="menu-icon fa fa-caret-right"></i>
                        新产品
                        <b class="arrow fa fa-angle-down"></b>
                    </a>

                    <b class="arrow"></b>

                    <ul class="submenu">


                        <li>
                            <a>
                                <i class="menu-icon fa fa-medkit"></i>

                                <span class="menu-text"> 新药 </span>

                            </a>

                            <b class="arrow"></b>
                        </li>

                        <li class="">
                            <a>
                                <i class="menu-icon fa fa-birthday-cake"></i>
                                <span class="menu-text"> 新功能性食品</span>
                            </a>

                            <b class="arrow"></b>
                        </li>
                        <li class="">
                            <a>

                                <i class="menu-icon fa fa-stethoscope"></i>
                                <span class="menu-text"> 新医疗器具 </span>

                            </a>

                            <b class="arrow"></b>
                        </li>

                        <li class="">
                            <a>
                                <i class="menu-icon fa fa-cubes"></i>
                                <span class="menu-text"> 其他新产品 </span>
                            </a>

                            <b class="arrow"></b>
                        </li>
                    </ul>
                </li>

            </ul>
        </li>

        <li class="">
            <a href="#" class="dropdown-toggle">
                <i class="menu-icon fa  fa-folder  "></i>
                <span class="menu-text"> 科研信息 </span>

                <b class="arrow fa fa-angle-down"></b>
            </a>

            <b class="arrow"></b>

            <ul class="submenu">

                <li class="entityType" id="projectEntity">
                    <a>
                        <i class="menu-icon fa fa-flask"></i>
                        <span class="menu-text"> 科研项目 </span>
                    </a>
                    <b class="arrow"></b>
                </li>

                <li class="entityType" id="paperEntity">
                    <a>
                        <i class="menu-icon fa fa- fa-file-word-o"></i>
                        <span class="menu-text"> 论文 </span>
                    </a>
                    <b class="arrow"></b>
                </li>

                <li class="entityType" id="bookEntity">
                    <a>
                        <i class="menu-icon fa fa-book"></i>
                        <span class="menu-text"> 著作 </span>
                    </a>
                    <b class="arrow"></b>
                </li>

                <li class="entityType" id="patentEntity">
                    <a>
                        <i class="menu-icon fa fa-fax"></i>
                        <span class="menu-text"> 专利 </span>
                    </a>
                    <b class="arrow"></b>
                </li>

                <li class="">
                    <a href="#" class="dropdown-toggle">
                        <i class="menu-icon fa fa-caret-right"></i>
                        成果
                        <b class="arrow fa fa-angle-down"></b>
                    </a>

                    <b class="arrow"></b>

                    <ul class="submenu">

                        <li class="entityType" id="achAppraisalEntity">
                            <a>
                                <i class="menu-icon fa fa-legal"></i>
                                <span class="menu-text">成果鉴定</span>
                            </a>

                            <b class="arrow"></b>
                        </li>

                        <li class="entityType" id="achAwardEntity">
                            <a>
                                <i class="menu-icon fa fa-trophy"></i>
                                <span class="menu-text"> 成果获奖</span>
                            </a>
                            <b class="arrow"></b>
                        </li>

                        <li class="entityType" id="achTranEntity">
                            <a>
                                <i class="menu-icon fa fa-recycle"></i>
                                <span class="menu-text"> 成果转化 </span>
                            </a>
                            <b class="arrow"></b>
                        </li>


                    </ul>
                </li>

                <li class="">
                    <a href="#" class="dropdown-toggle">
                        <i class="menu-icon fa fa-caret-right"></i>
                        新产品
                        <b class="arrow fa fa-angle-down"></b>
                    </a>

                    <b class="arrow"></b>

                    <ul class="submenu">


                        <li>
                            <a>
                                <i class="menu-icon fa fa-medkit"></i>

                                <span class="menu-text"> 新药 </span>

                            </a>

                            <b class="arrow"></b>
                        </li>

                        <li class="">
                            <a>
                                <i class="menu-icon fa fa-birthday-cake"></i>
                                <span class="menu-text"> 新功能性食品</span>
                            </a>

                            <b class="arrow"></b>
                        </li>
                        <li class="">
                            <a>

                                <i class="menu-icon fa fa-stethoscope"></i>
                                <span class="menu-text"> 新医疗器具 </span>

                            </a>

                            <b class="arrow"></b>
                        </li>

                        <li class="">
                            <a>
                                <i class="menu-icon fa fa-cubes"></i>
                                <span class="menu-text"> 其他新产品 </span>
                            </a>

                            <b class="arrow"></b>
                        </li>
                    </ul>
                </li>


            </ul>
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
                <li class="">
                    <a href="<c:url value="/userInfo"/>">
                        <i class="menu-icon fa fa-user"></i>
                        <span class="menu-text">个人信息 </span>
                    </a>
                    <b class="arrow"></b>
                </li>
            </c:when>
            <c:when test="${sessionScope.level == '2'|| sessionScope.level == '3'}">
                <li class="">
                    <a>
                        <i class="menu-icon glyphicon glyphicon-user"></i>
                        <span class="menu-text">教师信息 </span>
                    </a>
                    <b class="arrow"></b>
                </li>
            </c:when>
        </c:choose>
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

    <div class="sidebar-toggle responsive sidebar-collapse" id="sidebar-collapse">
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