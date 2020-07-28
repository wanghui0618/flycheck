<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh"><!-- 声明页面使用中文 -->
	<head>
		<meta http-equiv="content-type" content="text/html"/><!-- 页面以html形式输出 -->
		<meta http-equiv="charset" content="UTF-8"/><!-- 设置字符集为UTF-8 -->
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/><!-- 定义页面伸缩属性 -->
		
		<!-- 定义浏览器的渲染方式 -->
		<meta name="renderer" content="webkit"/><!-- 强制Chromium内核，作用于360浏览器、QQ浏览器等国产双核浏览器 -->
		<meta name="force-rendering" content="webkit"/><!-- 强制Chromium内核，作用于其他双核浏览器 -->
		<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1"/><!-- 如果有安装Google Chrome Frame插件则强制为Chromium内核，否则强制本机支持的最高版本IE内核，作用于IE浏览器 -->
		
		<%@include file="/WEB-INF/jsp/common/scriptInc.jsp"%>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/layui/css/layui.css" media="all"><!-- media属性规定被链接文档将显示在什么设备上 -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/style/admin.css" media="all">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/style/UserHistoryin.css" media="all">
		<title>主诊断异常筛查</title>
	</head>
	<body>
		<div class="layui-fluid"><!-- 不固定容器宽度 -->
			<div class="layui-card"><!-- 卡片面板 -->
				<div class="layui-form layui-card-header layuiadmin-card-header-auto">
					<div class="layui-form-item">
						<div class="layui-inline layui-col-md4">
							<div class="layui-inline">
								<label class="layui-form-label">诊断名称</label>
								<div class="layui-input-block">
									<input type="text" name="inhosDiag" placeholder="请输入诊断名称" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-inline">
								<button class="layui-btn" lay-submit lay-filter="search">
									<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
								</button>
								<button class="layui-btn  layui-icon-add layuiadmin-btn-useradmin" data-type="add">
									<i class="layui-icon layui-icon-add-circle-fine layuiadmin-button-btn"></i>新增
								</button>
							</div>
						</div>
					</div>
				</div>
				<div class="layui-card-body layui-inline layui-col-md4">
					<table id="diagnosis" class="layui-hide" lay-filter="diagnosis">
					</table>
					<script type="text/html" id="table-useradmin-webuser">
						{{#if (!existsButton('edit')) { }}
							<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
						{{# } }}
						{{#if (!existsButton('delete')) { }}
							<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete"><i class="layui-icon layui-icon-delete"></i>删除</a>
						{{# } }}
					</script>
				</div>
				<div class="layui-card-body layui-inline layui-col-md8">
					<table id="diseaseAndDiagnosticStatistics" class="layui-hide" lay-filter="diseaseAndDiagnosticStatistics"></table>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/fly/flyScreenMainDiagnosis.js"></script>
	</body>
</html>