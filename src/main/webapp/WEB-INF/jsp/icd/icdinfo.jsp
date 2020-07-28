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
<title>ICD库</title>
</head>
<body style="overflow:hidden">
<div class="layui-fluid">
    <div class="layui-card">
      <div class="layui-form layui-card-header layuiadmin-card-header-auto">
        <div class="layui-form-item">
      <button class="layui-btn layui-btn-sm"  lay-submit lay-filter="LAY-btn-front-search" id="btn" >全部数据</button>
      <button class="layui-btn layui-btn-sm"  lay-submit lay-filter="LAY-icdg-front-search" id="btn1" >广东版2017</button>
      <button class="layui-btn layui-btn-sm"  lay-submit lay-filter="LAY-icdgj1-front-search" id="btn2">国标2011版</button>
      <button class="layui-btn layui-btn-sm"  lay-submit lay-filter="LAY-icdgj2-front-search" id="btn3">国标2015版</button>
      <button class="layui-btn layui-btn-sm"  lay-submit lay-filter="LAY-icdgjlc1-front-search" id="btn4">国家临床V1.1</button>
      <button class="layui-btn layui-btn-sm"  lay-submit lay-filter="LAY-icdgjlc2-front-search" id="btn5">国家临床V2.0</button>
      <button class="layui-btn layui-btn-sm"  lay-submit lay-filter="LAY-icdbjlc-front-search"id="btn6" >北京临床V6.01</button>
             <input  type="checkbox"  id="aaa" name="like3[write]" lay-skin="primary" title="ICD-9"  value="DT_ICD9" checked=""  lay-filter="icd1">
             <input  type="checkbox"  id="bbb"name="like1[read]" lay-skin="primary" title="ICD-10"  value="DT_ICD10" checked=""  lay-filter="icd2">
             <input type="text" name="icd.code" placeholder="请输入ICD编码或名称"  class="layui-input"
     style="display: inline-block;">
             <!-- <input type="text" name="icd.name" placeholder="请输入ICD名称"  
     style="margin:2px 10px 20px 5px;width:100px;height:35px;padding:1px 1px 1px 1px ;"> -->
            <button class="layui-btn layuiadmin-btn-useradmin" lay-submit lay-filter="LAY-org-front-search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
            </button>
       </div>
       


      </div>
      <div class="layui-card-body">
    	<table id="icdTable" class="layui-hide" lay-filter="icdTable"></table>
		</div>
      </div>
    </div>
  </div>
  
	<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/icd/icdinfo.js"></script>
</body>
</html>