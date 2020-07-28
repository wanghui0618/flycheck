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
<title>医院上传数量统计</title>
</head>
<body style="">
<div class="layui-fluid">
  <div class="layui-card">
          <div class="layui-card-header" style="border-bottom: 1px solid #f6f6f6;">
  					<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
  					统筹区排名
  				</div>
		   <table id="tcNumber" class="layui-hide" lay-filter="tcNumber"></table>
     </div>
    </div>
		<script type="text/javascript" ">
		//
		layui.config({
			    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
			  }).extend({
			    index: 'lib/index' //主入口模块
			  }).use(['index', 'table'], function(){
			    var $ = layui.$
			    ,form = layui.form
			    ,table = layui.table;
			
			    table.render({
			    	elem: '#tcNumber'
			        ,url: $WEB_ROOT_PATH+"/dhccApi/admin/admin/TcNumber"
			        ,cellMinWidth: 80
			        ,height: document.documentElement.clientHeight-65
			        ,cols: [[
			         {type: 'numbers', width:120, title: '排名'}
					,{field:'pname', align:'center',title: '统筹区' }
					,{field: 'pnumber',width:160,align:'center', title: '违规人次',templet: function(d){
						var codex = d.pnumber;
						if(codex=="0" || codex==null){
							codex="-";
						}
						return '<span>'+ codex +'</span>'
				}}
					
			            ]]
			            ,page: true
			          });
		
			  });
	</script>
</body>
</html>