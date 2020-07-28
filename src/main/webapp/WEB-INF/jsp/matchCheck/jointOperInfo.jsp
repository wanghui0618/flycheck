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
.layui-form-item{
margin-bottom: 14px;
}
.layui-form-label {
    width: 110px;
}
</style>
<title>联合手术项目审核基础信息管理页面</title>
</head>
<body>
	<div class="layui-fluid">
		<div class="layui-card" >
			<fieldset class="layui-elem-field layui-field-title">
				<legend>联合手术项目基础信息</legend>
			</fieldset>
			<div class="layui-card-body" style="margin-top: -30px;">
				<div class="layui-form" lay-filter="layuiadmin-form-useradmin"
					id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">

					<input type="hidden" name="jointOper.id" id="id" hide=true>
					<input type="hidden" name="jointOper.typeNo" id="typeNo" hide=true>
					<input type="hidden" name="jointOper.typeName" id="typeName"
						hide=true>

					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">城市名称</label>
							<div class="layui-input-inline">
								<select name="jointOper.cityCode" id="cityCode" lay-search="">
									<option value="" disabled selected style='display: none;'>请选择城市</option>
								</select>
							</div>
						</div>
					</div>



					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">目录编码</label>
							<div class="layui-input-inline">
								<input type="text" id="itemCode" name="jointOper.itemCode"
									autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">目录名称</label>
							<div class="layui-input-inline">
								<input type="text" id="itemName" name="jointOper.itemName"
									autocomplete="off" class="layui-input">
							</div>
						</div>
					</div>

					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">判断逻辑类型</label>
							<div class="layui-input-inline">
								<input type="text" id="logicType" name="jointOper.logicType"
									autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">逻辑类型明细</label>
							<div class="layui-input-inline">
								<input type="text" id="logicTypeDetail"
									name="jointOper.logicTypeDetail" autocomplete="off"
									class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">判断逻辑</label>
							<div class="layui-input-inline">
								<input type="text" id="logic" name="jointOper.logic"
									autocomplete="off" class="layui-input">
							</div>
						</div>
					</div>

					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">内涵项目编码</label>
							<div class="layui-input-inline">
								<input type="text" id="includeItemCode"
									name="jointOper.includeItemCode" autocomplete="off"
									class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">内涵项目名称</label>
							<div class="layui-input-inline">
								<input type="text" id="includeItemName"
									name="jointOper.includeItemName" autocomplete="off"
									class="layui-input">
							</div>
						</div>
					</div>

					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">公立一类费用</label>
							<div class="layui-input-inline">
								<input type="text" id="publicFirstFee" name="jointOper.publicFirstFee"
									autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">公立二类费用</label>
							<div class="layui-input-inline">
								<input type="text" id="publicSecondFee" name="jointOper.publicSecondFee"
									autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">公立三类费用</label>
							<div class="layui-input-inline">
								<input type="text" id="publicThirdFee" name="jointOper.publicThirdFee"
									autocomplete="off" class="layui-input">
							</div>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">非公立一类费用</label>
							<div class="layui-input-inline">
								<input type="text" id="firstFee" name="jointOper.firstFee"
									autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">非公立二类费用</label>
							<div class="layui-input-inline">
								<input type="text" id="secondFee" name="jointOper.secondFee"
									autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">非公立三类费用</label>
							<div class="layui-input-inline">
								<input type="text" id="thirdFee" name="jointOper.thirdFee"
									autocomplete="off" class="layui-input">
							</div>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">备注</label>
							<div class="layui-input-inline">
								<input type="text" id="comments" name="jointOper.commentse"
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
	


	<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/matchCheck/jointOperInfo.js"></script>
</body>
</html>