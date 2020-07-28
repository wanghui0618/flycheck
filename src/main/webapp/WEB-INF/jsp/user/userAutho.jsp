<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/tree/css/zTreeStyle.css">
    <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/dictdiag/js/jquery.ztree.all.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css" media="all">

    <title>用户授权</title>
</head>
<body>
<div class="layui-tab layui-tab-brief" lay-filter="demo" >
    <ul class="layui-tab-title">
        <li class="layui-this">菜单权限</li>
        <li>数据权限</li>
        <li>按钮权限</li>
    </ul>
    <div  style="display: none;"></div>
    <div  class="layui-tab-content">
        <div id="nodes" class="layui-tab-item layui-show">
            <div class="layui-card" style="min-height:493px">
                <div class="layui-card-body" style="padding-left: 0px;padding-right: 0px;">
                    <div class="layui-form-item" >
                        <div class="layui-inline">
                            <label style="width:100%; text-align:left;" id="diagNameText" class="layui-form-label"></label>
                        </div>
                    </div>
                    <div>
                        <ul id="tree-sorts" class="ztree"></ul>
                    </div>
                </div>
            </div>
        </div>

        <div id="datas" class="layui-tab-item" style="overflow: hidden;">
            <div class="layui-card" style="min-height:493px">
                <div class="layui-card-body" style="padding-left: 0px;padding-right: 0px;">
                    <div class="layui-form-item" >
                        <div class="layui-inline">
                            <label style="width:100%; text-align:left;" id="dataAuthorityNameText" class="layui-form-label"></label>
                        </div>
                    </div>
                    <div>
                        <ul id="tree-sorts-data" class="ztree"></ul>
                    </div>
                </div>
            </div>
        </div>

        <div id="button" class="layui-tab-item" style="overflow: hidden;">
            <div class="layui-card" style="min-height:493px">
                <div class="layui-card-body" style="padding-left: 0px;padding-right: 0px;">
                    <div class="layui-form-item" >
                        <div class="layui-inline">
                            <label style="width:100%; text-align:left;" id="buttonAuthorityNameText" class="layui-form-label"></label>
                        </div>
                    </div>
                    <div>
                        <ul id="tree-sorts-button" class="ztree"></ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/user/userAutho.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/user/userAutho-data.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/user/userAutho-button.js"></script>
</body>
</html>