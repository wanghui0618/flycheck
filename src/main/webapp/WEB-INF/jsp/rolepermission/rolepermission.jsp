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
    <title>角色权限</title>
    <style>
        .pt {
            width: 300px;
        }    </style>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-col-xs12 layui-col-md12">
        <!-- 填充内容 -->
        <div class="layui-card">
            <div class="layui-card-body">
                <div class="layui-card">
                    <div class="layui-form layui-card-header layuiadmin-card-header-auto">
                        <div class="layui-form-item">
                            <div class="layui-inline pt">
                                <label class="layui-form-label" style="width:90px">角色名称</label>
                                <div class="layui-input-block">
                                    <input type="text" style="width: 160px;"
                                           name="rolePermission.roleName" placeholder="请输入角色名称"
                                           autocomplete="off" class="layui-input">
                                </div>
                            </div>

                            <div class="layui-inline pt">
                                <label class="layui-form-label" style="width:90px">权限代码</label>
                                <div class="layui-input-block">
                                    <input type="text" style="width: 160px;"
                                           name="rolePermission.permissionCode" placeholder="请输入权限代码"
                                           autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline pt">
                                <label class="layui-form-label" style="width:90px">权限名称</label>
                                <div class="layui-input-block">
                                    <input type="text" style="width: 160px;"
                                           name="rolePermission.permissionName" placeholder="请输入权限名称"
                                           autocomplete="off" class="layui-input">
                                </div>
                            </div>

                            <div class="layui-inline " style="margin-left: 20px">
                                <button class="layui-btn layuiadmin-btn-useradmin" lay-submit
                                        lay-filter="LAY-user-front-search">
                                    <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
                                </button>
                            </div>
                            <div class="layui-inline">
                                <button class="layui-btn layuiadmin-btn-useradmin" data-type="add">新增</button>
                            </div>
                        </div>
                    </div>

                    <div class="layui-card-body">

                        <table id="rolePermissionTable" class="layui-hide"
                               lay-filter="rolePermissionTable"></table>

                        <script type="text/html" id="table-useradmin-webuser">
                            <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="update"><i
                                    class="layui-icon layui-icon-edit"></i>编辑</a>
                            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete"><i
                                    class="layui-icon layui-icon-delete"></i>删除</a>
                        </script>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript"
        src="<%=request.getContextPath()%>/app/js/rolepermission/rolepermission.js"></script>
</body>
</html>