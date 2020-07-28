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
<title>按病种统计分析</title>
<style>
.layui-table-page{
    height: 50px;
}
</style>
</head>
<body style="overflow:hidden">
<div class="layui-fluid">
    <div class="layui-card">
      <div class="layui-form layui-card-header layuiadmin-card-header-auto">
        <div class="layui-form-item">
            
           <div class="layui-inline">
            <label class="layui-form-label">诊断名称</label>
            <div class="layui-input-inline">
              <input type="text" name="diseaseStaticsVo.inhosdiag" placeholder="请输入" autocomplete="off" maxlength="32" class="layui-input">
            </div>
          </div>               		  
      
          <div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-useradmin" lay-submit lay-filter="LAY-org-front-search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
            </button>
          </div>
        </div>
      </div>
      
      <table id="diseaseStatistics" class="layui-hide" lay-filter="diseaseStatistics"></table>
      
      </div>
    </div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/fly/diseaseStatistics/diseaseStatistics.js"></script>
	
	
</body>
</html>