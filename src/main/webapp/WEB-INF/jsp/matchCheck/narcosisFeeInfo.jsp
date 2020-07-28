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
.layui-form-label {
    width: 110px;
}
</style>
<title>麻醉项目价格审核基础信息管理页面</title>
</head>
<body>
	<div class="layui-fluid">
		<div class="layui-card">
			<fieldset class="layui-elem-field layui-field-title">
				<legend>麻醉项目价格基础信息</legend>
			</fieldset>

			<div class="layui-card-body" style="margin-top: -30px;">
				<div class="layui-form" lay-filter="layuiadmin-form-useradmin"
					id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">

					<input type="hidden" name="narcosisFee.id" id="id" hide=true>
					<input type="hidden" name="narcosisFee.typeNo" id="typeNo" hide=true>
					<input type="hidden" name="narcosisFee.typeName" id="typeName"
						hide=true>

					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">城市名称</label>
							<div class="layui-input-inline">
								<select name="narcosisFee.cityCode" id="cityCode" lay-search="">
									<option value="" disabled selected style='display: none;'>请选择城市</option>
								</select>
							</div>
						</div>
					</div>

					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">目录编码</label>
							<div class="layui-input-inline">
								<input type="text" id="itemCode" name="narcosisFee.itemCode"
									autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">目录名称</label>
							<div class="layui-input-inline">
								<input type="text" id="itemName" name="narcosisFee.itemName"
									autocomplete="off" class="layui-input">
							</div>
						</div>
					</div>

					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">判断逻辑类型</label>
							<div class="layui-input-inline">
								<input type="text" id="logicType" name="narcosisFee.logicType"
									autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">逻辑类型明细</label>
							<div class="layui-input-inline">
								<input type="text" id="logicTypeDetail"
									name="narcosisFee.logicTypeDetail" autocomplete="off"
									class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">判断逻辑</label>
							<div class="layui-input-inline">
								<input type="text" id="logic" name="narcosisFee.logic"
									autocomplete="off" class="layui-input">
							</div>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">备注</label>
							<div class="layui-input-inline">
								<input type="text" id="comments" name="narcosisFee.commentse"
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
	


	<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/matchCheck/narcosisFeeInfo.js"></script>
</body>
</html>