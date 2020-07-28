<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css"
          media="all">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css" media="all">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/login.css" media="all">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/tree/css/zTreeStyle.css">
    <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/dictdiag/js/jquery.ztree.all.js"></script>
    <title>组织管理</title>
</head>
<body>
<div class="layui-fluid" style="overflow:hidden;">
	<div class="layui-row layui-col-space15">
        <div class="layui-col-md3" style="width: 20%">
            <div class="layui-card" id="lefttab"  style="overflow: auto">
                <div class="layui-card-body" style="padding-left: 0px;padding-right: 0px;">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label style="width:100%; text-align:left;" id="diagNameText"
                                   class="layui-form-label"></label>
                        </div>
                    </div>
                    <div>
                        <ul id="tree-sorts" class="ztree"></ul>
                    </div>
                </div>
            </div>
        </div>

        <div class="layui-col-md9" style="width: 80%">
            <div class="layui-card">
                <div class="layui-form layui-card-header layuiadmin-card-header-auto">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label" style="width:70px;">组织名称</label>
                            <div class="layui-input-block" style="margin-left:80px;">
                                <input  id= "unitName" type="text" name="unit.unitName" placeholder="请输入组织名称" autocomplete="off"
                                       class="layui-input">
                            </div>
                        </div>

                        <div class="layui-inline">
                            <label class="layui-form-label" style="width:55px;">联系人</label>
                            <div class="layui-input-block" style="margin-left:65px;">
                                <input  id="concat" type="text" name="unit.concat" placeholder="请输入联系人" autocomplete="off"
                                       class="layui-input">
                            </div>
                        </div>

                        <div class="layui-inline">
                            <label class="layui-form-label" style="width:70px;">联系方式</label>
                            <div class="layui-input-block" style="margin-left:80px;">
                                <input  id="phone" type="text" name="unit.phone" placeholder="请输入联系方式" autocomplete="off"
                                       class="layui-input">
                            </div>
                        </div>

                        <div class="layui-inline">
                            <button class="layui-btn layuiadmin-btn-useradmin" lay-submit
                                    lay-filter="LAY-user-front-search">
                                <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
                            </button>
                        </div>
                    </div>
                </div>
                <div class="layui-card-body" style="overflow: hidden">
                    <table id="unitTable" class="layui-hide" lay-filter="unitTable"></table>
                </div>
            </div>
        </div>
        </div>
</div>
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/unit/unit.js"></script>
</body>
</html>