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
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui//layuiadmin/style/admin.css" media="all">
<title>区域内定点药店</title>
</head>
<body style="overflow:hidden">
<div class="layui-fluid">
    <div class="layui-card">
      <div class="layui-form layui-card-header layuiadmin-card-header-auto">
            <div class="layui-form-item">
     <!--        <div class="layui-inline">
            <label class="layui-form-label">城市名称</label>
           		<div class="layui-input-block" style="width:130px;">
    	        <select id="cityCode" name="drugStore.cityCode" lay-filter="cityAdminarea" lay-search="">
    	           <option  value=""  disabled selected style='display:none;'>请选择</option>
               	</select>
         		</div>
             </div> -->
          <div class="layui-inline">
            <label class="layui-form-label">药店编码</label>
            <div class="layui-input-block">
              <input type="text" name="drugStore.storeNo" placeholder="请输入药店编码" autocomplete="off" maxlength="15" class="layui-input">
            </div>
          </div>
            <div class="layui-inline">
            <label class="layui-form-label">药店名称</label>
            <div class="layui-input-block">
              <input type="text" name="drugStore.storeName" placeholder="请输入药店名称" autocomplete="off" maxlength="10" class="layui-input">
            </div>
            </div>
            <div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-useradmin" lay-submit lay-filter="LAY-drugstore-front-search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn" stylename="search" ></i>查询
            </button>
            </div>
        </div>
       </div>
       <table id="drugstoreTable" class="layui-hide" lay-filter="drugstoreTable"></table>
      </div>
      </div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/drugstore/drugStoreinfo.js"></script>
</body>
</html>