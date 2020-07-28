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
<title>医疗机构服务范围信息</title>
</head>
<body>
	<div class="layui-fluid">
		<div class="layui-card">
			<div class="layui-form layui-card-header layuiadmin-card-header-auto">
				<div class="layui-form-item">
					<!-- <div class="layui-inline pt">
						<label class="layui-form-label ">城市名称</label>
						<div class="layui-input-block " style="width: 160px;">
							<select name="insureUnit.cityCode" id="city" lay-search="">
								<option value="" disabled selected style='display: none;'>请选择城市</option>
							</select>
						</div>
					</div> -->
					<div class="layui-inline pt">
						<label class="layui-form-label">单位名称</label>
						<div class="layui-input-inline">
							<input type="text"
								name="insureUnit.unitName" placeholder="请输入单位名称"
								autocomplete="off" class="layui-input">
						</div>
					</div>

					<div class="layui-inline">
						<button id="insureunit-search" class="layui-btn layuiadmin-btn-useradmin" lay-submit
							lay-filter="LAY-user-front-search">
							<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
						</button>
					</div>
					<div class="layui-inline">
						<button id='insureunit-add' data-type="add"
							class="layui-btn layui-icon-add layuiadmin-btn-useradmin" lay-submit
							lay-filter="LAY-user-front-add"><i class="layui-icon layui-icon-add-circle layuiadmin-button-btn" ></i>添加</button>
					</div>
				</div>
			</div>

			<div class="layui-card-body">

				<table id="medicalrangeinfo" class="layui-hide"
					lay-filter="medicalrangeinfo"></table>
				<script type="text/html" id="table-useradmin-webuser">
					{{#if (!existsButton('insureunit-look')) { }}
					<a class="layui-btn layui-btn-xs" lay-event="view"><i class="layui-icon layui-icon-search"></i>查看</a>
          			{{# } }}
					{{#if (!existsButton('insureunit-edit')) { }}
					<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="update"><i class="layui-icon layui-icon-edit"></i>编辑</a>
         		 	{{# } }}
					{{#if (!existsButton('insureunit-delete')) { }}
					<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete"><i class="layui-icon layui-icon-delete"></i>删除</a>
        			{{# } }}
				</script>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/app/js/insureunit/insureunit.js"></script>
</body>
</html>