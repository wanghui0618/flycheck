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

<style type="text/css">
.layui-form-select dl{
max-height: 250px;
}
    .layui-form-item {
	margin-bottom: 14px;
}
.layui-form-label {
    width: 110px;
}
</style>
<title>项目与项目匹配信息管理页面</title>
</head>
<body>
	<div class="layui-fluid" style="padding-top: 0px;">
		<div class="layui-card">
			<fieldset class="layui-elem-field layui-field-title">
				<legend>项目与项目匹配基础信息</legend>
			</fieldset>

			<div class="layui-card-body" style="margin-top: -30px;">
				<div class="layui-form" lay-filter="layuiadmin-form-useradmin"
					id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">

					<input type="hidden" name="outMatch.id" id="id" hide=true>
					<input type="hidden" name="outMatch.typeNo" id="typeNo" hide=true>
					<input type="hidden" name="outMatch.typeName" id="typeName"
						hide=true>

					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">城市名称</label>
							<div class="layui-input-inline">
								<select name="outMatch.cityCode" id="cityCode" lay-search="">
									<option value="" disabled selected style='display: none;'>请选择城市</option>
								</select>
							</div>
						</div>
					</div>



					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">目录编码</label>
							<div class="layui-input-inline">
								<input type="text" id="itemCode" name="outMatch.itemCode"
									autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">目录名称</label>
							<div class="layui-input-inline">
								<input type="text" id="itemName" name="outMatch.itemName"
									autocomplete="off" class="layui-input">
							</div>
						</div>
					</div>

					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">判断逻辑类型</label>
							<div class="layui-input-inline">
								<input type="text" id="logicType" name="outMatch.logicType"
									autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">逻辑类型明细</label>
							<div class="layui-input-inline">
								<input type="text" id="logicTypeDetail"
									name="outMatch.logicTypeDetail" autocomplete="off"
									class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">判断逻辑</label>
							<div class="layui-input-inline">
								<input type="text" id="logic" name="outMatch.logic"
									autocomplete="off" class="layui-input">
							</div>
						</div>
					</div>

					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">内涵项目编码</label>
							<div class="layui-input-inline">
								<input type="text" id="includeItemCode"
									name="outMatch.includeItemCode" autocomplete="off"
									class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">内涵项目名称</label>
							<div class="layui-input-inline">
								<input type="text" id="includeItemName"
									name="outMatch.includeItemName" autocomplete="off"
									class="layui-input">
							</div>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">备注</label>
							<div class="layui-input-inline">
								<input type="text" id="comments" name="outMatch.commentse"
									autocomplete="off" class="layui-input">
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
	


	<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/matchCheck/outMatchInfo.js"></script>
</body>
</html>