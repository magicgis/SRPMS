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
                <li class="hyperlink task">
                    <a>
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

                <li class="process all">
                    <a>
                        <i class="menu-icon fa fa-home"></i>
                        <span class="menu-text"> 所有 </span>
                    </a>
                    <b class="arrow"></b>
                </li>

                <li class="process project">
                    <a>
                        <i class="menu-icon fa fa-flask"></i>
                        <span class="menu-text"> 科研项目 </span>
                    </a>
                    <b class="arrow"></b>
                </li>

                <li class="process paper">
                    <a>
                        <i class="menu-icon fa fa- fa-file-word-o"></i>
                        <span class="menu-text"> 论文 </span>
                    </a>
                    <b class="arrow"></b>
                </li>

                <li class="process book">
                    <a>
                        <i class="menu-icon fa fa-book"></i>
                        <span class="menu-text"> 著作 </span>
                    </a>
                    <b class="arrow"></b>
                </li>

                <li class="process patent">
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

                        <li class="process achAppraisal">
                            <a>
                                <i class="menu-icon fa fa-legal"></i>
                                <span class="menu-text">成果鉴定</span>
                            </a>

                            <b class="arrow"></b>
                        </li>

                        <li class="process achAward">
                            <a>
                                <i class="menu-icon fa fa-trophy"></i>
                                <span class="menu-text"> 成果获奖</span>
                            </a>
                            <b class="arrow"></b>
                        </li>

                        <li class="process achTran">
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


                        <li class="processType" id="newMedicineView">
                            <a>
                                <i class="menu-icon fa fa-medkit"></i>

                                <span class="menu-text"> 新药 </span>

                            </a>

                            <b class="arrow"></b>
                        </li>

                        <li class="processType" id="newFoodView">
                            <a>
                                <i class="menu-icon fa fa-birthday-cake"></i>
                                <span class="menu-text"> 新功能性食品</span>
                            </a>

                            <b class="arrow"></b>
                        </li>
                        <li class="processType" id="newInstruView">
                            <a>

                                <i class="menu-icon fa fa-stethoscope"></i>
                                <span class="menu-text"> 新医疗器具 </span>

                            </a>

                            <b class="arrow"></b>
                        </li>

                        <li class="processType" id="newOtherView">
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

                <li class="entity project">
                    <a>
                        <i class="menu-icon fa fa-flask"></i>
                        <span class="menu-text"> 科研项目 </span>
                    </a>
                    <b class="arrow"></b>
                </li>

                <li class="entity paper">
                    <a>
                        <i class="menu-icon fa fa- fa-file-word-o"></i>
                        <span class="menu-text"> 论文 </span>
                    </a>
                    <b class="arrow"></b>
                </li>

                <li class="entity book">
                    <a>
                        <i class="menu-icon fa fa-book"></i>
                        <span class="menu-text"> 著作 </span>
                    </a>
                    <b class="arrow"></b>
                </li>

                <li class="entity patent">
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

                        <li class="entity achAppraisal">
                            <a>
                                <i class="menu-icon fa fa-legal"></i>
                                <span class="menu-text">成果鉴定</span>
                            </a>

                            <b class="arrow"></b>
                        </li>

                        <li class="entity achAward">
                            <a>
                                <i class="menu-icon fa fa-trophy"></i>
                                <span class="menu-text"> 成果获奖</span>
                            </a>
                            <b class="arrow"></b>
                        </li>

                        <li class="entity achTran">
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


                        <li class="entityType" id="newMedicineEntity">
                            <a>
                                <i class="menu-icon fa fa-medkit"></i>

                                <span class="menu-text"> 新药 </span>

                            </a>

                            <b class="arrow"></b>
                        </li>

                        <li class="entityType" id="newFoodEntity">
                            <a>
                                <i class="menu-icon fa fa-birthday-cake"></i>
                                <span class="menu-text"> 新功能性食品</span>
                            </a>

                            <b class="arrow"></b>
                        </li>
                        <li class="entityType" id="newInstruEntity">
                            <a>

                                <i class="menu-icon fa fa-stethoscope"></i>
                                <span class="menu-text"> 新医疗器具 </span>

                            </a>

                            <b class="arrow"></b>
                        </li>

                        <li class="entityType" id="newOtherEntity">
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


        <c:choose>
            <c:when test="${sessionScope.level == '3'}">
                <li class="hyperlink search">
                    <a>
                        <i class="menu-icon fa fa-search "></i>
                        <span class="menu-text">查找 </span>
                    </a>
                    <b class="arrow"></b>
                </li>
            </c:when>
        </c:choose>

        <li class="hyperlink mag">
            <a>
                <i class="menu-icon glyphicon glyphicon-bookmark"></i>
                <span class="menu-text">期刊新增 </span>
            </a>
            <b class="arrow"></b>
        </li>


        <c:choose>
            <c:when test="${sessionScope.level == '1'}">
                <li class="">
                    <a>
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