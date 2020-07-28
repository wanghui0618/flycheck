<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css" media="all">
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css" media="all">
<script  src="<%=request.getContextPath() %>/js/echarts_home/echarts.min.js"></script>
<script src="<%=request.getContextPath() %>/js/echarts_jpp/echarts-2.2.7/build/dist/echarts.js"></script>
<title>药占比分析</title>
<style type="text/css">
html{
background-color: #F2F2F2;
}
</style>
</head>
<body>
<div class="layui-fluid" style="overflow: hidden;">

	<div class="layui-row layui-col-space15">
		
		<div class="layui-col-md12">
			<div class="layui-col-md12" style="padding-left: 2px;padding-right: 5px;">
      			<div class="layui-card">
					<div class="layui-form layui-card-header layuiadmin-card-header-auto">
          				<div class="layui-form-item">
          				
			<div class="layui-inline">
				<label class="layui-form-label" style="width:80px;">年份选择</label>
				<div class="layui-input-inline">
					<input type="text" name="inFlag" style=""  class="layui-input" id="test10" placeholder="yyyy" readonly="true">
				</div>
        	</div>
        	
		</div>
		</div>
		</div>
		</div>
		</div>
	
		<div class="layui-col-md12">
		
			<div class="layui-col-md6" style="padding-left: 2px;padding-right: 5px;">
				<div class="layui-card">
		              	<div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;">
	  						<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
	  						项目金额统计
	  				  	</div>
		              	<div class="layui-card-body" style="color:#3D3D3D">
		       				<div id="cost"  style="height:240px"></div>
		      		  	</div>
		        	</div>
		        </div>
		        
		     <div class="layui-col-md6" style="padding-left: 2px;padding-right: 5px;">
				<div class="layui-card">
		              	<div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;">
	  						<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
	  						各收费项目金额统计
	  				  	</div>
		              	<div class="layui-card-body" style="color:#3D3D3D">
		       				<div id="main" style="height:240px"></div>
		      		  	</div>
		        	</div>
		        </div>
		        
		  </div>      

		<div class="layui-col-md12">
			<div class="layui-col-md6" style="padding-left: 5px;padding-right: 5px;">
				<div class="layui-card">
				        <div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;">
			  				<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
			  					统筹区占比TOP10
			  			</div>
			         <div class="layui-card-body">
          				<table id="addressTable" class="layui-hide" lay-filter="addressTable" ></table>
        			</div>
		        </div>
                 <!-- <table id="addressTable" table class="layui-table" ></table> -->
                 </div>
                 
                 <div class="layui-col-md6" style="padding-left: 5px;padding-right: 5px;">
				<div class="layui-card">
				        <div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;">
			  				<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
			  					医院占比TOP10
			  			</div>
			         <div class="layui-card-body">
          				<table id="doctorTable" class="layui-hide" lay-filter="doctorTable" ></table>
        			</div>
		        </div>
                 </div>
                 
                 </div>


	</div>
</div>
<script src="<%=request.getContextPath()%>/app/js/indicator/drugAnalysis.js"></script>
</body>
</html>