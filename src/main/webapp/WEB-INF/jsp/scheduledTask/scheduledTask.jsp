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

<title>定时任务管理</title>
</head>
<body>
	<div class="layui-fluid">
		<div class="layui-card">
			<div class="layui-form layui-card-header layuiadmin-card-header-auto">
				<div class="layui-form-item">
					
					<div class="layui-inline pt">
						<label class="layui-form-label">任务名称</label>
						<div class="layui-input-block">
							<input type="text"
								name="scheduledTask.taskKey" placeholder="请输入任务名称" autocomplete="off"
								class="layui-input">
						</div>
					</div>
					<div class="layui-inline pt">
						<label class="layui-form-label" style="width: 90px;">选择时间段</label>
						<div class="layui-input-block" style="margin-left: 100px;">
						<input type="text" id="paymentDate" name="balanceDate"
							placeholder="请选择时间段" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<button class="layui-btn layuiadmin-btn-useradmin" lay-submit
							lay-filter="LAY-user-front-search">
							<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
						</button>
					</div>
					<div class="layui-inline">
						<button id='add' data-type="add"
							class="layui-btn layui-icon-add layuiadmin-btn-useradmin" lay-submit
							lay-filter="LAY-user-front-add"><i class="layui-icon layui-icon-add-circle layuiadmin-button-btn"></i>添加</button>
					</div>
				</div>
			</div>

			<div class="layui-card-body">

				<table id="cityOrgTable" class="layui-hide"
					lay-filter="cityOrgTable"></table>
				<script type="text/html" id="table-useradmin-webuser">
                    <a class="layui-btn  layui-btn-xs" lay-event="Synchronize"><i class="layui-icon layui-icon-play"></i>同步</a>
                    <a class="layui-btn  layui-btn-xs" lay-event="addHospUsers"><i class="layui-icon layui-icon-play"></i>批量添加医院用户</a>
          			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="start"><i class="layui-icon layui-icon-play"></i>启动</a>
					<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="playOnce"><i class="layui-icon layui-icon-play"></i>立即执行一次</a>	
					<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="playOnceException"><i class="layui-icon layui-icon-play"></i>异常数据重审</a>
        		</script>
        		<!-- 
        		<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="update"><i class="layui-icon layui-icon-edit"></i>编辑</a>
				<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="delete"><i class="layui-icon layui-icon-delete"></i>删除</a>
        		 -->
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/app/js/scheduledTask/scheduledTask.js"></script>
</body>
</html>