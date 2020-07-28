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

<title>耗材数据对应</title>
<style>
.layui-form-select{
	width:120px;
}
.layui-form-select .layui-input {
    padding-right: 30px;
    width:120px;
    cursor: pointer;
    border: 1px solid #C7C7C7;
}
.layui-input-inline {
    float: left;
    width: 120px!important;
    margin-right: 10px;
}
.layui-form-item .layui-inline {
    margin-right: 0px;
}
</style>
</head>
<body >
	<div style="padding: 8px; background-color: #F2F2F2;">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md6" >
				<div class="layui-card">
					<div class="layui-form layui-card-header layuiadmin-card-header-auto" style="padding:3px">
						<div class="layui-form-item pt">
							
							<div class="layui-inline ">
								<label class="layui-form-label ">版本信息</label>
								<div class="layui-input-block " style="width: 115px;">
									<select name="treatmentInfo.cityCode" id="city" lay-filter="city" lay-search=" ">
										<option value="" disabled selected style='display: none;'>请选择版本</option>
									</select>
								</div>
							</div>
							
							<div class="layui-inline">
								<label class="layui-form-label">诊疗编码</label>
								<div class="layui-input-block">
									<input  type="text" style="width: 115px;"
										name="treatmentInfo.itemCode" placeholder="诊疗编码"
										autocomplete="off" class="layui-input">
								</div>
							</div>
							
							<div class="layui-inline">
								<label class="layui-form-label">诊疗名称</label>
								<div class="layui-input-block">
									<input type="text" style="width: 115px;"
										name="treatmentInfo.itemName" placeholder="诊疗名称"
										autocomplete="off" class="layui-input">
								</div>
							</div>
							
							
							<div class="layui-inline">
								<button id="treatment-relation-search" class="layui-btn layuiadmin-btn-useradmin" lay-submit
									lay-filter="LAY-user-front-search">
								<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
								</button>
							</div>
							
						</div>
					</div>
					
					<div class="layui-card-body">
						<table id="drugruleTable" class="layui-hide" lay-filter="drugruleTable"></table>
						<script type="text/html" id="table-useradmin-webuser">
          					
							 {{#if (!existsButton('treatment-relation-cxgl')) { }}
         						<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="findrelation"><i class="layui-icon layui-icon-search"></i>查询关联</a>
        			 		 {{# } }}
       					 </script>
					</div>
				</div>
			</div>
			
			<div class="layui-col-md6">
				<div class="layui-card">
					<div class="layui-form layui-card-header layuiadmin-card-header-auto" style="padding:3px">
						<div class="layui-form-item pt">
							
							<div class="layui-inline ">
								<label class="layui-form-label ">版本信息</label>
								<div class="layui-input-block " style="width: 115px;">
									<select name="treatmentInfo.cityCode" id="city2" lay-filter="city2" lay-search=" ">
										<option value="" disabled selected style='display: none;'>请选择版本</option>
									</select>
								</div>
							</div>
							
							<div class="layui-inline">
								<label class="layui-form-label">诊疗编码</label>
								<div class="layui-input-block">
									<input  type="text" style="width: 115px;"
										name="treatmentInfo.itemCode" placeholder="诊疗编码"
										autocomplete="off" class="layui-input">
								</div>
							</div>
							
							<div class="layui-inline">
								<label class="layui-form-label">诊疗名称</label>
								<div class="layui-input-block">
									<input type="text" style="width: 115px;"
										name="treatmentInfo.itemName" placeholder="诊疗名称"
										autocomplete="off" class="layui-input">
								</div>
							</div>
							
							<div class="layui-inline">
								<button  id="treatment-relation-search2" class="layui-btn layuiadmin-btn-useradmin" lay-submit
									lay-filter="LAY-user-front-search-city">
								<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
								</button>
							</div>
						</div>
						
					</div>
					
					<div class="layui-card-body">
						<table id="drugruleTablecity" class="layui-hide" lay-filter="drugruleTablecity"></table>
					</div>
				</div>
			</div>
			

			<div class="layui-col-md12" style="position:absolute;bottom:0px;">
				<div class="layui-card" >
					<div class="layui-card-body" style="height:31px;text-align:center;">
						<button id='treatment-relation-gl' class="layui-btn layuiadmin-btn-useradmin layui-btn-xs" data-type="relation"  
							lay-filter="LAY-user-front-search-relation">
							关&nbsp;&nbsp;&nbsp;联<i class="layui-icon layui-icon-lianjie layuiadmin-button-btn"></i>
						</button>
						<button id="treatment-relation-jcgl" class="layui-btn layuiadmin-btn-useradmin layui-btn-xs" data-type="breakrelation"
							lay-filter="LAY-user-front-search-breakrelation">
							解除关联<i class="layui-icon layui-icon-lianjie layuiadmin-button-btn"></i>
						</button>
						<button id="treatment-relation-xsqbgl" class="layui-btn layuiadmin-btn-useradmin layui-btn-xs" data-type="showrelation"
							lay-filter="LAY-user-front-search-breakrelation">
							显示全部已关联<i class="layui-icon layui-icon-lianjie layuiadmin-button-btn"></i>
						</button>
						
					</div>
				</div>
			</div>

		</div>
	</div>
	 <script type="text/javascript"
		src="<%=request.getContextPath()%>/app/js/treatment_relation/treatmentRelation.js"></script> 
	<script>
		layui.use([ 'element', 'layer', 'form' ], function() {
			var element = layui.element;
			var layer = layui.layer;
			var form = layui.form;
		});
	</script>
</body>
</html>