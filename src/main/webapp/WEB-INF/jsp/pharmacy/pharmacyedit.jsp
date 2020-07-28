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
<title>辅助与重点监控用药信息新增/修改页面</title>
</head>
<body style="overflow:hidden">
<div class="layui-form" lay-filter="layuiadmin-form-useradmin" id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">
<input type="hidden" id="id" name="pharmacy.id">
<fieldset style="width:90%;margin:0 auto">
    <legend>编辑辅助用药信息</legend>
    <div class="layui-form-item">
      <label class="layui-form-label">药品名称</label>
      <div class="layui-input-inline">
        <input type="text" id="drugName" name="pharmacy.drugName" lay-verify="required" placeholder="请填写名称" autocomplete="off" maxlength="30" class="layui-input">
      </div>
      <label class="layui-form-label">药品剂型</label>
      <div class="layui-input-inline">
        <input type="text" id="drugForm" name="pharmacy.drugForm" lay-verify="required"   placeholder="请填写药品剂型" autocomplete="off" maxlength="20" class="layui-input">
      </div>
    </div>
    <br>
      <div class="layui-form-item">
      <label class="layui-form-label">地区或机构</label>
      <div class="layui-input-inline">
        <input type="text" id="areaName" name="pharmacy.areaName" lay-verify="required"   placeholder="请填写地区或机构" autocomplete="off" maxlength="20" class="layui-input">
      </div>
      <label class="layui-form-label">发文时间</label>
        <div class="layui-input-inline">
         <input type="text" class="layui-input" id="dispatchTime"  name="pharmacy.dispatchTime"  placeholder="yyyy-MM-dd">
        </div>
    </div>
    <br>
    <div class="layui-form-item">
      <label class="layui-form-label">监管类别</label>
      <div class="layui-input-inline">
        <input type="text" id="supervice" name="pharmacy.supervice" lay-verify="required"   placeholder="请填写监管类别" autocomplete="off" maxlength="80" class="layui-input">
      </div>
      <label class="layui-form-label">政策文件</label>
        <div class="layui-input-inline">
          	<input type="text" id="policyDocument" name="pharmacy.policyDocument"   lay-verify="required"  placeholder="请填写政策文件" autocomplete="off" maxlength="80" class="layui-input">
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
  }).use(['index', 'form','laydate'], function(){
	  form = layui.form;
	  var layer = layui.layer;
	  var laydate = layui.laydate;
	  
	  laydate.render({
		    elem: '#dispatchTime'
		  });
	 
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