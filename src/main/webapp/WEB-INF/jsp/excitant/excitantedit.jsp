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
<title>兴奋剂信息新增/修改页面</title>
</head>
<body style="overflow:hidden">
<div class="layui-form" lay-filter="layuiadmin-form-useradmin" id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">
<input type="hidden" id="id" name="excitant.id">
<fieldset style="width:85%;margin:0 auto">
    <legend>编辑兴奋剂信息</legend>
    <div class="layui-form-item">
      <label class="layui-form-label">来源</label>
      <div class="layui-input-inline">
        <input type="text" id="source" name="excitant.source" lay-verify="required" placeholder="请填写来源" autocomplete="off" maxlength="30" class="layui-input">
      </div>
      <label class="layui-form-label">分类</label>
      <div class="layui-input-inline">
        <input type="text" id="classify" name="excitant.classify" lay-verify="required"   placeholder="请填写分类" autocomplete="off" maxlength="20" class="layui-input">
      </div>
    </div>
    <br>
      <div class="layui-form-item">
      <label class="layui-form-label">药品通用名</label>
      <div class="layui-input-inline">
        <input type="text" id="commonName" name="excitant.commonName" lay-verify="required"   placeholder="请填写药品通用名" autocomplete="off" maxlength="20" class="layui-input">
      </div>
      <label class="layui-form-label">核心成分</label>
        <div class="layui-input-inline">
          	<input type="text" id="core" name="excitant.core"   lay-verify="required"   placeholder="请填写核心成分" autocomplete="off" maxlength="20" class="layui-input">
        </div>
    </div>
    <br>
    <div class="layui-form-item">
      <label class="layui-form-label">英文名称</label>
      <div class="layui-input-inline">
        <input type="text" id="englishName" name="excitant.englishName" lay-verify="required"   placeholder="请填写英文名称" autocomplete="off" maxlength="80" class="layui-input">
      </div>
      <label class="layui-form-label">海关编码</label>
        <div class="layui-input-inline">
          	<input type="text" id="customsCode" name="excitant.customsCode"   lay-verify="required"  placeholder="请填写海关编码" autocomplete="off" maxlength="30" class="layui-input">
        </div>
    </div>
    <br>
    </fieldset>
    <br>
    <br>
    <br>
    <div class="layui-form-item layui-hide" style="align:center">
      <input type="button" lay-submit lay-filter="LAY-org-front-submit" id="LAY-org-front-submit" value="确认">
    </div>
  </div>


  <script>
  var form;
  layui.config({
    base: '<%=request.getContextPath() %>/plugins/layui/layuiadmin/'
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'form'], function(){
	  form = layui.form;
	  var layer = layui.layer;
	 
  })
  function child(obj){
	  var org = JSON.parse(obj);
	  for (var index in org){
	      $("#"+index).val(org[index]);
	  }
  }
  </script>
</body>
</html>