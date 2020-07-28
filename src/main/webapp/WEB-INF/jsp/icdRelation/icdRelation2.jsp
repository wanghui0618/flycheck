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

<title>ICD数据对应</title>
<style>
.pt{
	margin:3px 3px;
	height:40px;
}
.layui-form-item .layui-inline {
    margin-bottom: 0px;
    margin-right: 0px;
}
.layui-form-label {
	width: 70px;
}
/* .layui-table-box{
    height:440px;

}
.layui-table-body layui-table-main{
    height:410px;

} */
</style>
</head>
<body >
	<div style="padding: 8px;padding-top: 13px; background-color: #F2F2F2;">
		<div class="layui-row layui-col-space15" style="height:500px">	
			<div class="layui-col-md6" style="height:480px">
				<div class="layui-card">
				<div class="layui-card-header" style="height: 25px;line-height: 30px;font-weight: 600; " id="layui-card-header"></div> 
					<div class="layui-form layui-card-header layuiadmin-card-header-auto" style="padding:3px">
						<div class="layui-form-item pt">					
							<div class="layui-inline "  style="margin-left:0px">
								<label class="layui-form-label ">ICD版本名</label>
								<div class="layui-input-block " style="width: 90px;" >
									<select name="icd.typeName" id="typeName" lay-filter="typeName">
										<option value="" disabled selected style='display: none;'>请选择版本</option>
									</select>
								</div>
							</div>							
							<div class="layui-inline" style="margin-left:0px">
								<label class="layui-form-label"style="width: 60px;margin-left:0px">ICD编码</label>
									<input type="text" style="width: 90px;"
										name="icd.code" placeholder="请输入ICD编码"
										autocomplete="off" class="layui-input">				
							</div>
							<div class="layui-inline" style="margin-left:0px">
								<label class="layui-form-label"style="width: 60px;margin-left:0px">ICD名称</label>
									<input type="text" style="width: 90px;"
										name="icd.name" placeholder="请输入ICD名称"
										autocomplete="off" class="layui-input">
							</div>							
							<div class="layui-inline" style="margin-left:0px">
								<button  id="city-search" class="layui-btn layuiadmin-btn-useradmin layui-btn-sm" lay-submit
									lay-filter="city-search">
								<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
								</button>
							</div>		
						</div>
					</div>
					
					<div class="layui-card-body" style="height:420px">
						<table id="icdTable" class="layui-hide" lay-filter="icdTable" style=" height:390px;"></table>
					</div>
				</div>
			</div>
			
			<div class="layui-col-md6" style="height:480px">
				<div class="layui-card">
 					<div class="layui-card-header" style="height: height: 25px;line-height: 30px;font-weight: 600; " id="layui-card-header-s" value="国家临床V2.0 ICD-10">国家临床V2.0 ICD-1</div>
 					<div class="layui-form layui-card-header layuiadmin-card-header-auto" style="padding:3px">
						<div class="layui-form-item pt">
							<div class="layui-inline " style="margin-left:0px">
								<label class="layui-form-label ">ICD版本名</label>
								<div class="layui-input-block " style="width: 90px;">
									<select name="icd.typeName" id="typeName2" lay-filter="typeName2">
										<option value="" disabled selected style='display: none;'>请选择版本名</option>
									</select>
								</div>
							</div>
							
							<div class="layui-inline" style="margin-left:0px">
								<label class="layui-form-label" style="width: 60px;margin-left:0px">ICD编码</label>
									<input  type="text" style="width: 90px;"
										name="icd.code" placeholder="请输入ICD编码"
										autocomplete="off" class="layui-input">
							</div>
							
						
							
							<div class="layui-inline" style="margin-left:0px">
								<label class="layui-form-label" style="width: 60px;margin-left:0px">ICD名称</label>
									<input type="text" style="width: 90px;"
										name="icd.name" placeholder="请输入ICD名称"
										autocomplete="off" class="layui-input">
							</div>							
							<div class="layui-inline" style="margin-left:0px">
								<button id="LAY-user-front-search-to" class="layui-btn layuiadmin-btn-useradmin layui-btn-sm" lay-submit
									lay-filter="LAY-user-front-search-to">
								<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
								</button>
							</div>
					</div>
					</div>
					<div class="layui-card-body" style="height:420px">
						<table id="icdTableTo" class="layui-hide" lay-filter="icdTableTo" style=" height:390px;"></table>
						<script type="text/html" id="table-useradmin-webuser">
          					<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="findrelation"><i class="layui-icon layui-icon-search"></i>查询关联</a>
       					 </script>
					</div>
				</div>
			</div>
			<div class="layui-col-md12" >
				<div class="layui-card" >
					<div class="layui-card-body" style="height:31px;text-align:center;">
				<button id='relation' class="layui-btn layuiadmin-btn-useradmin layui-btn-xs" data-type="allrelation"  
							lay-filter="LAY-user-front-search-relation">
							一键关联<i class="layui-icon layui-icon-lianjie layuiadmin-button-btn"></i>
						</button>
						<button id='relation' class="layui-btn layuiadmin-btn-useradmin layui-btn-xs" data-type="relation"  
							lay-filter="LAY-user-front-search-relation">
							关&nbsp;&nbsp;&nbsp;联<i class="layui-icon layui-icon-lianjie layuiadmin-button-btn"></i>
						</button>
						<button class="layui-btn layuiadmin-btn-useradmin layui-btn-xs" data-type="breakrelation"
							lay-filter="LAY-user-front-search-breakrelation">
							解除关联<i class="layui-icon layui-icon-lianjie layuiadmin-button-btn"></i>
						</button>
						<button class="layui-btn layuiadmin-btn-useradmin layui-btn-xs" href="javascript:;" lay-href="<%=request.getContextPath()%>/icdRelation/icdRelationShow" lay-tips="显示全部关联数据">
							显示全部关联数据<i class="layui-icon layui-icon-lianjie layuiadmin-button-btn"></i>
						</button>
							</div>
				</div>
			</div>

	<%-- 		<div class="layui-col-md12" >
				<div class="layui-card" >
					<div class="layui-card-body" style="height:31px;text-align:center;">
						<button id='relation' class="layui-btn layuiadmin-btn-useradmin layui-btn-radius" data-type="allrelation"  
							lay-filter="LAY-user-front-search-relation">
							一键关联<i class="layui-icon layui-icon-lianjie layuiadmin-button-btn"></i>
						</button>
						<button id='relation' class="layui-btn layuiadmin-btn-useradmin layui-btn-radius" data-type="relation"  
							lay-filter="LAY-user-front-search-relation">
							关&nbsp;&nbsp;&nbsp;联<i class="layui-icon layui-icon-lianjie layuiadmin-button-btn"></i>
						</button>
						<button class="layui-btn layuiadmin-btn-useradmin layui-btn-radius" data-type="breakrelation"
							lay-filter="LAY-user-front-search-breakrelation">
							解除关联<i class="layui-icon layui-icon-lianjie layuiadmin-button-btn"></i>
						</button>
						<button class="layui-btn layuiadmin-btn-useradmin layui-btn-radius" href="javascript:;" lay-href="<%=request.getContextPath()%>/icdRelation/icdRelationShow" lay-tips="显示全部关联数据">
							显示全部关联数据<i class="layui-icon layui-icon-lianjie layuiadmin-button-btn"></i>
						</button>
					</div>
				</div>
			</div> --%>

		</div>
	</div>
	 <script type="text/javascript"
		src="<%=request.getContextPath()%>/app/js/icdRelation/icdRelation2.js"></script> 
	<script>
		layui.use([ 'element', 'layer', 'form' ], function() {
			var element = layui.element;
			var layer = layui.layer;
			var form = layui.form;
		});
	</script>
</body>
</html>