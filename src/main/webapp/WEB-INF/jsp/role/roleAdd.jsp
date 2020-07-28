<%@ page language="java" import="java.util.*"
         contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <%@include file="/WEB-INF/jsp/common/scriptInc.jsp"%>

    <style>
    </style>
    <title>角色管理页面</title>
</head>
<body>
<div class="layui-fluid">
    <fieldset style="width:90%;margin:0 auto">
        <legend>角色信息</legend>


        <div class="layui-card-body">
            <div class="layui-form" lay-filter="layuiadmin-form-useradmin"
                 id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">

                <input type="hidden" name="role.id" id="id"
                       hide=true>

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">角色名称</label>
                        <div class="layui-input-inline">
                            <input type="text" id="roleName" lay-verify="required"
                                   name="role.roleName" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">角色代码</label>
                        <div class="layui-input-inline">
                            <input type="text" id="roleCode"
                                   name="role.roleCode" autocomplete="off" lay-verify="required"
                                   class="layui-input">
                        </div>
                    </div>
                </div>




                <div class="layui-form-item layui-hide">
                    <input type="button" lay-submit
                           lay-filter="layuiadmin-btn-useradmin"
                           id="layuiadmin-btn-useradmin" value="确认">
                </div>
            </div>
        </div>
    </fieldset>
</div>



<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/dictdiag/dictdiagAdd.js"></script>
</body>
</html>