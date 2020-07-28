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
<title>药品限价信息新增/修改页面</title>
</head>
<body style="overflow:hidden">
<div class="layui-form" lay-filter="layuiadmin-form-useradmin" id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">
<input type="hidden" id="id" name="drugPrice.id">
<fieldset style="width:80%;margin:0 auto">
    <legend>添加药品限价信息</legend>
        <br>
         <div class="layui-form-item">
           <label class="layui-form-label">城市编码</label>
           <div class="layui-input-inline" >
    	      <select id="cityCode" name="drugPrice.cityCode" lay-filter="cityCode" >
    	        <option  value=""  disabled selected style='display:none;'>请选择</option>
              </select>
          </div>
         <label class="layui-form-label">药品编码</label>
         <div class="layui-input-inline">
        <input type="text" id="drugId" name="drugPrice.drugId" lay-verify="required" placeholder="请填写药品编码" autocomplete="off" maxlength="25" class="layui-input">
        </div>
       </div>
       <br>
       <div class="layui-form-item">
          <label class="layui-form-label">药品名称</label>
          <div class="layui-input-inline">
            <input type="text" id="drugName" name="drugPrice.drugName" lay-verify="required"   placeholder="请填写药品名称" autocomplete="off" maxlength="20" class="layui-input">
          </div>
          <label class="layui-form-label">医院等级</label>
          <div class="layui-input-inline">
            <input type="text" id="hospLevel" name="drugPrice.hospLevel" lay-verify="required"   placeholder="请填写医院等级" autocomplete="off" maxlength="10" class="layui-input">
          </div>
       </div>
       <br>
       <div class="layui-form-item">
       <label class="layui-form-label">剂型</label>
       <div class="layui-input-inline">
        <input type="text" id="dosageForm" name="drugPrice.dosageForm" lay-verify="required"   placeholder="请填写剂型名称" autocomplete="off" maxlength="10" class="layui-input">
       </div>
       <label class="layui-form-label">定价上限金额</label>
        <div class="layui-input-inline">
          	<input type="text" id="maxMoney" name="drugPrice.maxMoney"   lay-verify="number"  placeholder="请填写上限金额" autocomplete="off" maxlength="10" class="layui-input">
        </div>
      </div>
       <br>
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
	  
	    $.getJSON($WEB_ROOT_PATH+'/dhccApi/dict/dict/findDict?dictRequestVo.tableName=t_piccbid_dict_city_xingzheng',
				function(data){
			var  dataList= data.dictList;
			for(var i=0 ;i<dataList.length;i++){
			var mm="<option value='"+dataList[i].value+"'>"+dataList[i].text+"</option>";
			$("#cityCode").append(mm); 
		     		}
			form.render('select');
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