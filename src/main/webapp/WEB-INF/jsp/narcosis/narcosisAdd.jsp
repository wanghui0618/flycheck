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
<title>麻醉项目审核新增/修改页面</title>
</head>
<body style="overflow:hidden">
<div class="layui-form" lay-filter="layuiadmin-form-useradmin" id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">
<input type="hidden" id="id" name="narcosis.id">
       <br>
       <div class="layui-form">
       <div class="layui-form-item" style="padding-left:15px">
        <div class="layui-inline">
        <label class="layui-form-label">城市编码</label>
        <input type="text" id="cityCode" name="narcosis.cityCode" lay-verify="number" placeholder="请填写城市编码" autocomplete="off" maxlength="15" class="layui-input">
        </div>
       <div class="layui-inline">
       <label class="layui-form-label">规则编码</label>
        <input type="text" id="typeNo" name="narcosis.typeNo" lay-verify="number"   placeholder="请填写规则编码" autocomplete="off" maxlength="10" class="layui-input">
       </div>
         <div class="layui-inline">
      <label class="layui-form-label">规则名称</label>
      <div class="layui-input-inline">
        <input type="text" id="typeName" name="narcosis.typeName" lay-verify=""   placeholder="请填写规则名称" autocomplete="off" maxlength="40" class="layui-input">
      </div>
       </div>
      </div>
    <br>
      <div class="layui-form-item" style="padding-left:15px">
        <div class="layui-inline">
      <label class="layui-form-label">目录编码</label>
        <div class="layui-input-inline">
          	<input type="text" id="itemCode" name="narcosis.itemCode"   lay-verify="number"  placeholder="请填写目录编码" autocomplete="off" maxlength="30" class="layui-input">
        </div>
         </div>
       <div class="layui-inline">
      <label class="layui-form-label">目录名称</label>
      <div class="layui-input-inline">
        <input type="text" id="itemName" name="narcosis.itemName" lay-verify=""   placeholder="请填写目录名称" autocomplete="off" maxlength="20" class="layui-input">
      </div>
       </div>
       <div class="layui-inline">
      <label class="layui-form-label">判断逻辑类型</label>
        <div class="layui-input-inline">
          	<input type="text" id="logicType" name="narcosis.logicType"    placeholder="请填写" autocomplete="off" maxlength="60" class="layui-input">
        </div>
         </div>
    </div>
    <br>
      <div class="layui-form-item" style="padding-left:15px">
       <div class="layui-inline">
      <label class="layui-form-label">判断逻辑类型明细</label>
      <div class="layui-input-inline">
        <input type="text" id="logicTypeDetail" name="narcosis.logicTypeDetail" lay-verify=""   placeholder="请填写" autocomplete="off" maxlength="100" class="layui-input">
      </div>
       </div>
       <div class="layui-inline">
      <label class="layui-form-label">判断逻辑</label>
        <div class="layui-input-inline">
          	<input type="text" id="logic" name="narcosis.logic"    placeholder="请填写" autocomplete="off" maxlength="50" class="layui-input">
        </div>
         </div>
         </div>
         <div class="layui-form-item" style="padding-left:15px">
          <div class="layui-inline">
      <label class="layui-form-label">内涵项目编码</label>
      <div class="layui-input-inline">
        <input type="text" id="includeItemCode" name="narcosis.includeItemCode" lay-verify=""   placeholder="请填写" autocomplete="off" maxlength="50" class="layui-input">
      </div>
       </div>
        <div class="layui-inline">
      <label class="layui-form-label">内涵项目名称</label>
      <div class="layui-input-inline">
        <input type="text" id="includeItemName" name="narcosis.includeItemName" lay-verify=""   placeholder="请填写" autocomplete="off" maxlength="50" class="layui-input">
      </div>
       </div>
    </div>
    <br>
      <div class="layui-form-item" style="padding-left:15px">
        <div class="layui-inline">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-inline">
        <input type="text" id="comments" name="narcosis.comments"    placeholder="请填写备注" autocomplete="off" maxlength="100" class="layui-input">
        </div>
         </div>
       
    </div>
    </div>
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