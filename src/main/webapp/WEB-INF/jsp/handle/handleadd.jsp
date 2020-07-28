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
<title>经办机构信息新增/修改页面</title>
</head>
<body style="overflow:hidden">
<div class="layui-form" lay-filter="layuiadmin-form-useradmin" id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">
<input type="hidden" id="id" name="handle.id">
       <br>
       <div class="layui-form-item">
       <label class="layui-form-label">城市名称</label>
       <div class="layui-input-inline">
        <input type="text" id="cityName" name="handle.cityName" lay-verify="required"   placeholder="请填写城市名称" autocomplete="off" maxlength="50" class="layui-input">
       </div>
<!--        <label class="layui-form-label">经办机构ID</label>
       <div class="layui-input-inline">
        <input type="text" id="orgCode" name="handle.orgCode" lay-verify="number"   placeholder="请填写经办机构ID" autocomplete="off" maxlength="50" class="layui-input">
       </div> -->
          <label class="layui-form-label">行政区划代码</label>
       <div class="layui-input-inline">
        <input type="text" id="regionCode" name="handle.regionCode" lay-verify="number"   placeholder="请填写行政区划代码" autocomplete="off" maxlength="50" class="layui-input">
       </div>
      </div>
       <br>
       <div class="layui-form-item">
       <label class="layui-form-label">经办机构名称</label>
       <div class="layui-input-inline">
        <input type="text" id="handdingInsName" name="handle.handdingInsName" lay-verify="required"   placeholder="请填写经办机构名称" autocomplete="off" maxlength="50" class="layui-input">
       </div>
       <label class="layui-form-label">地址</label>
       <div class="layui-input-inline">
        <input type="text" id="address" name="handle.address" lay-verify="required"   placeholder="请填写地址" autocomplete="off" maxlength="50" class="layui-input">
       </div>
      </div>
       <br>
       <div class="layui-form-item">
       <label class="layui-form-label">联系电话</label>
       <div class="layui-input-inline">
        <input type="text" id="phone" name="handle.phone" lay-verify="number"   placeholder="请填写联系电话" autocomplete="off" maxlength="50" class="layui-input">
       </div>
      </div>
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