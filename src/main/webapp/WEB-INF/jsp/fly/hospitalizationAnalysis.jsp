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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/style/login.css"
	media="all">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/layui/tree/css/zTreeStyle.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/app/js/dictdiag/js/jquery.ztree.all.js"></script>
<title>分解住院情况筛查分析</title>
</head>
<body>
	<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
            <div class="layui-form-item">
					
						单次住院天数：<input id="stayLength" type="text" name="stayLength"
							placeholder="请输入单次住院天数" autocomplete="off"
							class="layui-input layui-inline"> 年度住院频次 ：<input id="num"
							type="text" name="num" placeholder="年度住院频次" autocomplete="off"
							class="layui-input layui-inline">
						<div class="layui-form layui-inline">
							<div class="layui-form-item layui-inline">
								<div class="layui-inline">
									<label class="layui-form-label">入院时间</label>
									<div class="layui-input-inline layui-inline">
										<input type="text" class="layui-input" id="test5"
											placeholder="yyyy-MM-dd HH:mm:ss" name="indate">
									</div>
								</div>

								<div class="layui-inline">
									<label class="layui-form-label">出院时间</label>
									<div class="layui-input-inline layui-inline">
										<input type="text" class="layui-input" id="test6"
											placeholder="yyyy-MM-dd HH:mm:ss" name="outdate">
									</div>
								</div>
							</div>
						</div>
						
						<div class="layui-inline layuiadmin-btn-useradmin">
							<button id="search" class="layui-btn" lay-submit=""
								lay-filter="search">
								查询住院信息
							</button>
						</div>
					
				</div>
        </div>
      <div class="layui-card-body">
            <table id="analysis" class="layui-hide" lay-filter="analysis"></table>    
        </div>
    </div>
</div>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/app/js/fly/hospitalizationAnalysis.js"></script>
</body>

</html>