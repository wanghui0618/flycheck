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
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css"
	media="all">

<link rel="stylesheet"
	href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css"
	media="all">

<title>药品相互作用管理</title>
<style>
.layui-form-label{
	width:115px;
}

</style>
</head>
<body>
	<div class="layui-card" style="width:999px;" >
		<table id="interactionDrugNameTable2" class="layui-hide" lay-filter="interactionDrugNameTable2" ></table>
	</div>

	<script>
	var name2="";
  layui.config({
    base: '<%=request.getContextPath() %>/plugins/layui/layuiadmin/'
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'form','laydate','table'], function(){
	  var form=layui.form;
	  var table=layui.table;
	  table.render({
			 elem: '#interactionDrugNameTable2'
			,url: $WEB_ROOT_PATH+'/dhccApi/druginteraction/drugInteraction/listInteractionVo'
			,height: 330
			,where: {"drugInteraction.drugName": name2}
		 	,cols: [[
			  {type: 'numbers', title: '序号' }
			 ,{field:'id', width:80,hide:true,title: '编号'}
			
			 ,{field:'drugName', width:200,title: '药品名称'}
			 ,{field:'interactionDrugName', width:200,title: '相互作用药品名称'}
			 ,{field:'interactionEffect', width:200,title: '相互作用效果'}
			 ,{field:'clinicalRecommendations', width:180,title: '临床建议'}
			 ,{field:'clinicalEvidence', width:200,title: '临床证据'}
			 ,{field:'evidenceLevel', width:100,title: '证据级别'}
			 ,{field:'reference', width:200,title: '参考文献'}
			 ,{field:'createDate', width:110,title: '创建时间'}
			 ,{field:'createUserName', width:110,title: '创建人'}
			 ]]
		 ,page: true
		 });
  })
  
  function child(obj){
	  var cityOrg = JSON.parse(obj);
	  $("#id").val(cityOrg["id"]);
	  for (var index in cityOrg){
	      $("#"+index).val(cityOrg[index]);
	  }
  }
  function child1(obj){
	  var cityOrg = JSON.parse(obj);
	  name2=cityOrg["drugName"];
	  
  }

  </script>
</body>
</html>