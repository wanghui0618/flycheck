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
    <%@include file="/WEB-INF/jsp/common/easyui.jsp" %>
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/layui/css/layui.css"
          media="all">
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/style/admin.css"
          media="all">
    <title>城市机构</title>
<style>
.layui-table-page{
    height: 38px;
}
</style>
</head>
<body style="overflow:hidden;">
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
            <div class="layui-form-item">
                <!-- <div class="layui-inline pt">
                    <label class="layui-form-label ">城市名称</label>
                    <div class="layui-input-block " style="width: 120px;">
                        <select name="dictCityOrg.cityCode" id="city" lay-search=" ">
                            <option value="" disabled selected style='display:none;'>请选择城市</option>
                        </select>
                    </div>
                </div> -->
                <div class="layui-inline">
                    <label class="layui-form-label">机构编码</label>
                    <div class="layui-input-block">
                        <input type="text"
                               name="dictCityOrg.orgCode" placeholder="请输入机构编码" autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-inline" style="width: 270px;">
                    <label class="layui-form-label">机构名称</label>
                    <div class="layui-input-block">
                    	<input id="getOrgName"/>
                        <input type="text" id="orgName" name="dictCityOrg.orgName" style="display: none;" />
                    </div>
                </div>

                <div class="layui-inline">
                    <button  id="cityorg-search"  class="layui-btn layuiadmin-btn-useradmin" lay-submit
                             lay-filter="LAY-user-front-search" stylename="search" >
                        <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
                    </button>
                </div>
                
                <div class="layui-inline">
                    <button id="cityorg-add" id='add' data-type="add" stylename="add" 
                            class="layui-btn layuiadmin-btn-useradmin" lay-submit
                            lay-filter="LAY-user-front-add"> <i class="layui-icon layui-icon-add-circle layuiadmin-button-btn" ></i>新增</button>
                </div>
                <!-- <div class="layui-inline" >
                    <button  id="city-syn"  class="layui-btn layui-icon-trans-main layuiadmin-btn-useradmin" lay-submit
                             lay-filter="LAY-user-front-syn">
                        <i class="layui-icon layui-icon-refresh layuiadmin-button-btn"></i>同步
                    </button>
                </div> -->
            </div>
        </div>

        <div class="layui-card-body">

            <table id="cityOrgTable" class="layui-hide"
                   lay-filter="cityOrgTable"></table>
            <script type="text/html" id="table-useradmin-webuser">
                {{#if (!existsButton('cityorg-update')) { }}
                         					<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="update"><i class="layui-icon layui-icon-edit"></i>编辑</a>
                        			 {{# } }}
                {{#if (!existsButton('cityorg-delete')) { }}
                         					<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete"><i class="layui-icon layui-icon-delete"></i>删除</a>
                        			 {{# } }}
            </script>
        </div>
    </div>
</div>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/app/js/cityorg/cityorg.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/common/orgDictSelect.js"></script>
</body>
</html>