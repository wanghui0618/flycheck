<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>单病种收费价格管理</title>
		<%@include file="/WEB-INF/jsp/common/scriptInc.jsp"%>
	</head>
	<body>
		<div class="layui-fluid">
			<div class="layui-card">
				<div class="layui-form layui-card-header layuiadmin-card-header-auto">
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">疾病系统</label>
							<div class="layui-input-inline">
								<select name="singleDisease.system" id="system" lay-serch="">
									<option value="" disabled selected style='display: none;'>请选择系统</option>
								</select>
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">病种名称</label>
							<div class="layui-input-inline">
								<input type="text" name="singleDisease.mainDiagName" placeholder="请输入病种名称" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<button id="insureunit-search" class="layui-btn layuiadmin-btn-useradmin" lay-submit lay-filter="LAY-user-front-search">
								<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
							</button>
						</div>
						<div class="layui-inline">
							<button id='insureunit-add' data-type="add"	class="layui-btn layui-icon-add layuiadmin-btn-useradmin" lay-submit lay-filter="LAY-user-front-add"><i class="layui-icon layui-icon-add-circle layuiadmin-button-btn" ></i>添加</button>
						</div>
					</div>
				</div>
				<div class="layui-card-body">
					<table id="medicalrangeinfo" class="layui-hide"	lay-filter="medicalrangeinfo"></table>
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
		<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/singleDisease/singleDisease.js"></script>
	</body>
</html>