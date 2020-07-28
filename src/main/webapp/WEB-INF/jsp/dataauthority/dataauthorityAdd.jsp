<%@ page language="java" import="java.util.*"
         contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
    <title>数据权限管理页面</title>
    <style>
        #orgCode+.layui-form-selected dl{
            width: 250px;
            height:275px;
            overflow-x: auto;
        }
    </style>
</head>
<body>
<div class="layui-fluid" style="height:435px;max-height:435px;">
    <legend>信息</legend>


    <div class="layui-card-body">
        <div class="layui-form" lay-filter="layuiadmin-form-useradmin"
             id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">

            <input type="hidden" name="dataAuthority.id" id="id"
                   hide=true>
            <div class="layui-form-item">
                    <label class="layui-form-label">城市</label>
                    <div class="layui-input-block" style="width: 250px" >
                        <select id="cityCode" name="dataAuthority.cityCode">
                            <option value="" disabled selected style='display:none;width: 250px'>请选择</option>
                        </select>
                </div>

            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">医院</label>
                <div class="layui-input-block" style="width: 250px">
                    <select id="orgCode" name="dataAuthority.orgCode" style="width: 250px;">
                        <option value="" disabled selected style='display:none; width: 250px'>请选择</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item layui-hide">
                <input type="button" lay-submit
                       lay-filter="layuiadmin-btn-useradmin"
                       id="layuiadmin-btn-useradmin" value="确认">
            </div>
        </div>
    </div>
</div>


<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/dataauthority/dataauthorityAdd.js"></script>
</body>
</html>