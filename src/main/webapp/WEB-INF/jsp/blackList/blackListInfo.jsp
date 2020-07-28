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
<style>
</style>
<title>黑名单记录新增/修改页面</title>
<style>
.layui-form-label {
	width: 115px;
}
</style>
</head>
<body>
	<div class="layui-fluid" style="padding-top: 5px;">
		<div class="layui-card" style="height: 300px;">
			<div class="layui-card-body">
				<div class="layui-form" lay-filter="layuiadmin-form-useradmin"
					id="layuiadmin-form-useradmin" style="padding: 0 0 0 0;">

					<input type="hidden" name="blackList.id" id="id" hide=true>
					<input type="hidden" name="blackList.createUser" id="createUser">
					<input type="hidden" name="blackList.createDate" id="createDate">
					<input type="hidden" name="blackList.updateDate" id="updateDate">
					<!-- 第0行 -->
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label ">城市名称</label>
							<div class="layui-input-inline ">
								<select name="blackList.cityCode" id="cityCode"
									style="width: 100px;" lay-search="">
									<option value="" disabled selected style='display: none;'>请选择城市</option>
								</select>
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">类型</label>
							<div class="layui-input-inline">
								<select name="blackList.type" autocomplete="off" id="type"
									lay-verify="required" lay-filter="type">
									<option value="">请选择类型</option>
									<option value="1">参保人</option>
									<option value="2">医院</option>
									<option value="3">药店</option>
								</select>
							</div>
						</div>
					</div>
					<!-- 第一行 -->	
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">姓名/名称</label>
							<div class="layui-input-inline">
								<input type="text" id="name" name="blackList.name"
									lay-verify="required" placeholder="请输入姓名/名称" autocomplete="off"
									class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">身份证号/机构代码/药店编号</label>
							<div class="layui-input-inline" style="margin-top: 8px;">
								<input type="text" id="code" name="blackList.code"
									lay-verify="required|number" placeholder="请输入标识号"
									autocomplete="off" class="layui-input">
							</div>
						</div>
					</div>
					<!-- 第二行 -->
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">严重级别</label>
							<div class="layui-input-inline">
								<select name="blackList.scale" autocomplete="off" id="scale"
									lay-verify="required" lay-filter="scale">
									<option value="">请选择严重级别</option>
									<option value="1">非常严重</option>
									<option value="2">严重</option>
									<option value="3">轻度</option>
								</select>
							</div>
						</div>
					</div>
					<div class="layui-form-item" style="margin-top: 13px;">
						<div class="layui-inline">
							<label class="layui-form-label">原因说明</label>
							<div class="layui-input-inline">
								<input type="text" id="result" name="blackList.result"
									style="width: 495px;" lay-verify="required"
									placeholder="请说明列入黑名单原因" autocomplete="off" class="layui-input">
							</div>
						</div>
					</div>

					<div class="layui-form-item layui-hide">
						<input type="button" lay-submit
							lay-filter="layuiadmin-btn-useradmin"
							id="layuiadmin-btn-useradmin" value="确认">
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/blackList/blackListInfo.js"></script>
</body>
</html>