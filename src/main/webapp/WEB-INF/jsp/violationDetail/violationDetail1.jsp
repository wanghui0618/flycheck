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
	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/layui/css/layui.css"
	media="all">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/style/admin.css"
	media="all">

<title>当前在线</title>
<style>
.pt {
	width: 300px;
}
</style>
</head>
<body>
<div class="layui-fluid">
<div class="layui-card">
		   <div class="layui-card-header" style="border-bottom: 1px solid #f6f6f6;">
			  <img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
  					当前在线
  					<div style="float: right;margin-top: 6px;">
  					<%-- <input style="background-image: url(<%=request.getContextPath() %>/images/oauth/search.png);background-repeat: no-repeat;border: solid 1px #e6e6e6;background-position: right;width:180px;height:32px;padding-right: 20px;" type="text" name="userVo.keyDom" id="keyDom"  class="layui-input" > --%>
  					</div>
  				</div>

	             <div class="layui-card-body">
				      <div class="layui-row layui-col-space15">
            	      <div class="layui-col-md12">
			          <table id="violationDetail1" class="layui-hide" lay-filter="violationDetail1"></table>
                   </div>
                </div>
			</div>
		</div>
	</div>

		
		
		<script type="text/javascript" ">
		
		layui.config({
			    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
			  }).extend({
			    index: 'lib/index' //主入口模块
			  }).use(['index', 'table'], function(){
			    var $ = layui.$
			    ,form = layui.form
			    ,table = layui.table;
			    table.render({
			    	elem: '#violationDetail1'
			        ,url: $WEB_ROOT_PATH+'/dhccApi/user/user/userOnline'
			        ,height:  tableHeight+5
			        ,cellMinWidth: 80	           
			        ,cols: [[
			         {type: 'numbers', title: '序号' }
			    	,{field:'name',align:'center', title: '用户' }
					,{field:'phone', align:'center',title: '手机号' }
					,{field: 'loginTime',align:'center', title: '登陆时间'}
					,{field:'minutes',align:'center', title: '在线时间'}
			    ]]
			    ,page: true
			    });
			    
			  });
	</script>
</body>
</html>