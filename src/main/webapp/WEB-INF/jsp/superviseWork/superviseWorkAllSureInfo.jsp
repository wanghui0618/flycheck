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

<title>违规类型明细统计</title>
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
  					医院公示反馈人次全部信息
  					
  			</div>
			<div class="layui-card-body">
				 <div class="layui-row layui-col-space15">
            	 <div class="layui-col-md12">
				   <table id="violationDetail" class="layui-hide" lay-filter="violationDetail"></table>
				 </div>
				<!--  <div class="layui-col-md5">
				   	<div id="main1" style="height: 485px;padding-left: 100px" align="right"></div>
				 </div> -->
				 </div>
			</div>
		</div>
	</div>
	<script  src="<%=request.getContextPath() %>/js/echarts_home/echarts.min.js"></script>
	
	<script type="text/javascript">
	//初始化	
	layui.config({
		base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	}).extend({
		index: 'lib/index' //主入口模块
	}).use(['index', 'table'], function(){
		var $ = layui.$
		,form = layui.form
		,table = layui.table;
		table.render({
			elem: '#violationDetail'
			,url: $WEB_ROOT_PATH+"/dhccApi/superviseWork/superviseWork/getAllRuleInfo"
			 ,cellMinWidth: 80
			 ,height: document.documentElement.clientHeight-65		    
			 ,cols: [[
			 {type: 'numbers', title: '序号' }
			,{field:'pname',title: '医院名',align:"center"}
			,{field:'pnumber',title: '公示反馈人次',align:"center",sort:true,templet:function(d){
     		   var codex = d.pnumber;
    		   if(codex == "0"){
    			   codex="-";
    		   }else if(codex == null){
    			   codex="-";
    		   }else{
    			   codex=codex;	
    		   }
    		   return '<span >'+ codex +'</span>';
    		   }}
			/* ,{field:'enumber',title: '排除违规人次',align:"center",templet:function(d){
	     		   var codex = d.enumber;
	    		   if(codex == "0"){
	    			   codex="/";
	    		   }else if(codex == null){
	    			   codex="/";
	    		   }else{
	    			   codex=codex;	
	    		   }
	    		   return '<span >'+ codex +'</span>';
	    		   }}
			,{field:'surenumber',title: '确认违规人次',align:"center",templet:function(d){
	     		   var codex = d.surenumber;
	    		   if(codex == "0"){
	    			   codex="/";
	    		   }else if(codex == null){
	    			   codex="/";
	    		   }else{
	    			   codex=codex;	
	    		   }
	    		   return '<span >'+ codex +'</span>';
	    		   }} */
			
			]]
		,page: true
		});

		//监听搜索
		form.on('submit(LAY-user-front-search)', function(data){
			var field = data.field;
			//执行重载
			layui.table.reload('violationDetail', {
				where: field
			});
		});
		
		//按钮事件绑定底层方法-勿动
		$('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});
	});
	
	</script>
</body>
</html>