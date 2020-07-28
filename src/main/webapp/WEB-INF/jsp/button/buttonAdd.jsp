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
    <title>按钮管理页面</title>
</head>
<body>
<div class="layui-fluid">
    <fieldset style="width:90%;margin:0 auto">
        <legend>按钮信息</legend>


        <div class="layui-card-body">
            <div class="layui-form" lay-filter="layuiadmin-form-useradmin"
                 id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">

                <input type="hidden" name="button.id" id="id" hide=true>
                <input type="hidden" name="button.buttonPageName" id="name" hide=true>

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">按钮名称</label>
                        <div class="layui-input-inline">
                            <input type="text" id="menuName"
                                   name="button.buttonName" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">按钮代码</label>
                        <div class="layui-input-inline">
                            <input type="text" id="menuCode"
                                   name="button.buttonCode" autocomplete="off"
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