<%@ page language="java" import="java.util.*"
         contentType="text/html; charset=UTF-8" %>
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
    <link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/tree/css/zTreeStyle.css">
    <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/dictdiag/js/jquery.ztree.all.js"></script>
    <title>角色</title>
</head>
<body>
<div class="layui-fluid" style="overflow-y: hidden">
    <div class="layui-row layui-col-space15">
    <div class="layui-col-md2"style="width: 20%">
        <div class="layui-card" id="lefttab" style="overflow: auto">
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
    <div class="layui-col-md10 " style="width: 80%">
        <!-- 填充内容 -->
        <div class="layui-card">
                    <div class="layui-form layui-card-header layuiadmin-card-header-auto">
                        <div class="layui-form-item">
                            <div class="layui-inline pt">
                                <label class="layui-form-label">角色名称</label>
                                <div class="layui-input-block">
                                    <input type="text"
                                           name="role.roleName" placeholder="请输入角色名称"
                                           autocomplete="off" class="layui-input">
                                </div>
                            </div>

                            <div class="layui-inline pt">
                                <label class="layui-form-label">角色代码</label>
                                <div class="layui-input-block">
                                    <input type="text"
                                           name="role.roleCode" placeholder="请输入角色代码"
                                           autocomplete="off" class="layui-input">
                                </div>
                            </div>

                            <div class="layui-inline">
                                <button class="layui-btn layuiadmin-btn-useradmin" lay-submit
                                        lay-filter="LAY-user-front-search">
                                    <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
                                </button>
                            </div>
                          <%--  <div class="layui-inline">
                                <button class="layui-btn layuiadmin-btn-useradmin" data-type="add">新增</button>
                            </div>--%>
                        </div>
                    </div>

                    <div class="layui-card-body">

                        <table id="roleTable" class="layui-hide" lay-filter="roleTable"></table>
                        <script type="text/html" id="table-useradmin-webuser">
                            <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="autho"><i class="layui-icon layui-icon-auz"></i>授权</a>
                        </script>
                    </div>
        </div>
    </div>
    </div>
</div>


<script type="text/javascript"
        src="<%=request.getContextPath()%>/app/js/role/role.js"></script>
</body>
</html>