<%--
  Created by IntelliJ IDEA.
  User: huyuanyuan555
  Date: 2015/4/25
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class=" col-xs-offset-4">
    <form id="staffInfo" class="form-horizontal" role="form">
        <div id="baseInfo">

            <div class="row">
                <div class="form-group col-xs-12 col-sm-6">
                    <label class="col-sm-4 control-label no-padding-left">工&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</label>

                    <div class="col-sm-8">
                        <input type="text" id="staffId" name="id"
                               placeholder="" class="col-xs-12"/>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-xs-12 col-sm-6">
                    <label class="col-sm-4 control-label no-padding-left">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</label>

                    <div class="col-sm-8">
                        <input type="text" id="staName" name="name"
                               placeholder="" class="col-xs-12"/>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-xs-12 col-sm-6">
                    <label class="col-sm-4 control-label no-padding-left">姓名缩写：</label>

                    <div class="col-sm-8">
                        <input type="text" id="staffByStaId_staSnm" name="staSnm"
                               placeholder="" class="col-xs-12"/>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-xs-12 col-sm-6">
                    <label class="col-sm-4 control-label no-padding-left">身份证号：</label>

                    <div class="col-sm-8">
                        <input type="text" id="IDcard" name="idCard"
                               placeholder="" class="col-xs-12"/>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-xs-12 col-sm-6">
                    <label class="col-sm-4 control-label no-padding-left" for="deptName">学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;院：</label>

                    <div class="col-sm-8">
                        <input type="text" id="colName" name="col.id"
                               placeholder="" class="cl form-control"/>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-xs-12 col-sm-6">
                    <label class="col-sm-4 control-label no-padding-left" for="deptName">部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门：</label>

                    <div class="col-sm-8">
                        <input type="text" id="deptName" name="dept.id"
                               placeholder="" class="cl form-control"/>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-xs-12 col-sm-6">
                    <label class="col-sm-4 control-label no-padding-left">职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</label>

                    <div class="col-sm-8">
                        <input type="text" id="rankName" name="rank.id"
                               placeholder="" class="cl form-control"/>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-xs-12 col-sm-6">
                    <label class="col-sm-4 control-label no-padding-left">岗&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位：</label>

                    <div class="col-sm-8">
                        <input type="text" id="position" name="position"
                               placeholder="" class="cl form-control"/>
                    </div>
                </div>
            </div>

            <div id="syRole" class="row">
                <div class="form-group col-xs-12 col-sm-6 col-xs-offset-2">
                    <label class="col-sm-4 control-label no-padding-left">角&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色：</label>

                    <div class="col-sm-8">
                        <input type="text" id="role" name="user.privilege"
                               placeholder="" class="cl form-control"/>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-xs-12 col-sm-6">
                    <label class="col-sm-4 control-label no-padding-left">学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;历：</label>

                    <div class="col-sm-8">
                        <input type="text" id="edu" name="edu"
                               placeholder="" class="cl form-control"/>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-xs-12 col-sm-6">
                    <label class="col-sm-4 control-label no-padding-left">学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位：</label>

                    <div class="col-sm-8">
                        <input type="text" id="degree" name="degree"
                               placeholder="" class="cl form-control"/>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>