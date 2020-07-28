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
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css" media="all">
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css" media="all">
<!-- easyui -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/js/easyui-jpp/plugins/jquery-easyui-1.7.1/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/js/easyui-jpp/plugins/jquery-easyui-1.7.1/themes/icon.css"/>
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/plugins/layui/dist-jpp/dist/formSelects-v4.css"
	type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/common/orgDictSelect.js"></script>
<script
	src="<%=request.getContextPath() %>/plugins/layui/dist-jpp/dist/formSelects-v4.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/js/easyui-jpp/plugins/jquery-easyui-1.7.1/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/js/easyui-jpp/plugins/jquery-easyui-1.7.1/jquery.easyui.min.js"></script>
<script type="text/javascript" 
    src="<%=request.getContextPath() %>/js/easyui-jpp/plugins/jquery-easyui-datagridview/datagrid-detailview.js"></script>
<script type="text/javascript" 
    src="<%=request.getContextPath() %>/js/easyui-jpp/plugins/jquery-easyui-datagridview/datagrid-defaultview.js"></script>
<title>上传数据统计</title>
<style>
.echart {
	width: 700px;
	height: 400px;
}

.pt {
	margin: 3px 3px;
	height: 90px;
}
.layui-card{
    height:540px;

}
/* 
.layui-card-body{
  height:495px;
}
.layui-form layui-border-box layui-table-view{
    height: 495px;
} */
</style>
</head>
<body >
	<div style="padding: 8px; background-color: #F2F2F2;">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md12">
				<div class="layui-card" style="height:60px;">
					<div class="layui-form layui-card-header layuiadmin-card-header-auto"
						style="padding: 3px">
						<div class="layui-form-item pt">

							<div class="layui-inline ">
								<label class="layui-form-label " style="width:45px">类型</label>
								<div class="layui-input-inline ">
									<select name="blackListVo.type" id="type"
										lay-filter="choose">
										<option value="1">医院</option>
										<option value="2">参保人</option>
										<option value="3">药店</option>
										<option value="4">医生</option>
									</select>
								</div>
							</div>
							
							<div class="layui-inline">
								<label class="layui-form-label "  style="width:55px">统筹区</label>
								<div class="layui-input-inline ">
									<select name="blackListVo.cityCode" id="cityCode"
										style="width: 180px;" lay-search>
										<option value="" disabled selected style='display: none;'>请选统筹区</option>
									</select>
								</div>
							</div>
							
							<div class="layui-inline " id="thirdInputBlock"> 
								<label class="layui-form-label ">医疗机构</label>
								<div class="layui-input-inline">
									<input id="getOrgName" />
									 <input type="text" id="orgCode" name="blackListVo.orgName" style="display: none;" />
								</div>
							</div>
						
							<div class="layui-inline " id="fourthInputBlock">
								<label class="layui-form-label " style="width:45px">年度</label>
									<div class="layui-input-inline ">
									<input id="violationDate" name="blackListVo.violationDate"type="text" class="layui-input"
									placeholder="yyyy">
								</div>
							</div>
						
							<div class="layui-inline">
								<button class="layui-btn layuiadmin-btn-useradmin" lay-submit stylename="search" 
									lay-filter="LAY-user-front-search">
									<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="layui-col-md6" style="width: 45%;">
				<div class="layui-card" >
		
					<div class="layui-card-body">
						<table id="dataTable" class="layui-hide" lay-filter="dataTable"></table>
					</div>
				</div>
			</div>
			<div class="layui-col-md6" style="width: 55%;">
				<div class="layui-card" id="noMsg">
				<!-- 	<div class="layui-card-header" style="height: 40px"
						id="layui-card-header-s" value=""></div> -->
									<div class="layui-inline ">		
								<span id="tableTitle" style="height: 35px;font-size: 19px;color:black;line-height: 40px;" ></span>
							</div>
					<div class="layui-card-body">
						<div id="main" class="echart"></div>
						<div id="msg" ></div> 
					</div>
				</div>
			</div>
		</div>

	</div>
	</div>
 <script type="text/javascript" src="<%=request.getContextPath()%>/app/js/countUpMedical/countUpViolation.js"></script> 
</body>
</html>