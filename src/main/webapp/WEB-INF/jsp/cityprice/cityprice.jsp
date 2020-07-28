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

<title>项目价格</title>
<style>

</style>
</head>
<body>
	<div class="layui-fluid">
		<div class="layui-card" >
			<div class="layui-form layui-card-header layuiadmin-card-header-auto">
				<div class="layui-form-item">
					<!-- <div class="layui-inline pt">
						<label class="layui-form-label " >城市名称</label>
						<div class="layui-input-block " style="width: 130px;">
							<select name="dictCityPrice.cityCode" id="city" lay-search="">
								<option value="" disabled selected style='display:none;'>请选择城市</option>
							</select>
						</div>
					</div> -->
					<div class="layui-inline pt">
						<label class="layui-form-label">项目编码</label>
						<div class="layui-input-inline">
							<input type="text"
								name="dictCityPrice.itemCode" placeholder="请输入项目编码" autocomplete="off"
								class="layui-input">
						</div>
					</div>
					<div class="layui-inline pt">
						<label class="layui-form-label">项目名称</label>
						<div class="layui-input-inline">
							<input type="text"
								name="dictCityPrice.itemName" placeholder="请输入项目名称" autocomplete="off"
								class="layui-input">
						</div>
					</div>
					
					<div class="layui-inline">
						<button id="cityprice-search1" class="layui-btn layuiadmin-btn-useradmin" lay-submit
							lay-filter="LAY-user-front-search">
							<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
						</button>
					</div>
					<div class="layui-inline">
						<!-- 	<button id='cityprice-add1' data-type="add" class="layui-icon-add layui-btn" lay-submit
							lay-filter="LAY-user-front-add" > <i class="layui-icon layui-icon-add-circle layuiadmin-button-btn" ></i>添加
							</button> -->
							   <button id='cityprice-add1' class="layui-btn layuiadmin-btn-useradmin" stylename="add" data-type="add"> 
             <i class="layui-icon layui-icon-add-circle layuiadmin-button-btn" ></i>新增
             </button>
					</div>
				</div>
			</div>

			<div class="layui-card-body">

				<table id="cityPriceTable" class="layui-hide"
					lay-filter="cityPriceTable"></table>
				<script type="text/html" id="table-useradmin-webuser">
        			{{#if (!existsButton('cityprice-update1')) { }}
         					 <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="update"><i class="layui-icon layui-icon-edit"></i>编辑</a>
        			 {{# } }}
          			 {{#if (!existsButton('cityprice-delete1')) { }}
         					<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete"><i class="layui-icon layui-icon-delete"></i>删除</a> 
        			 {{# } }}	
				</script>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="<%=request.getContextPath() %>/app/js/cityprice/cityprice.js"></script>
</body>
</html>