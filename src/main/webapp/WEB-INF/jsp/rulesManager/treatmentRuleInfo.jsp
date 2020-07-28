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
	width: 90px;
}

.layui-form-item {
	margin-bottom: 14px;
}
</style>
<title>诊疗规则管理页面</title>
</head>
<body>
	<div class="layui-fluid">
		<div class="layui-card" style="height:360px ;">
			<fieldset class="layui-elem-field layui-field-title">
				<legend>诊疗</legend>
			</fieldset>

			<div class="layui-card-body" style="margin-top:-20px;">
				<div class="layui-form" lay-filter="layuiadmin-form-useradmin"
					id="layuiadmin-form-useradmin" style="padding: 0 0 0 0;">

					<input type="hidden" name="treatmentRule.itemCode" id="itemCode"
						hide=true>

					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">性&nbsp;别&nbsp;限&nbsp;制&nbsp;</label>
							<div class="layui-input-inline" id="sexFlag">
								<select name="treatmentRule.sexFlag">
									<option value="0">0-无限制</option>
									<option value="1">1-限男性</option>
									<option value="2">2-限女性</option>
								</select>
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">限儿童用</label>
							<div class="layui-input-inline" id="personType">
								<input type="radio" name="treatmentRule.personType" value="1"
									title="是&nbsp;&nbsp;&nbsp;&nbsp;"> <input type="radio"
									name="treatmentRule.personType" value="0" title="否&nbsp;&nbsp;&nbsp;" >
							</div>
						</div>
					</div>



					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">适应症标志</label>
							<div class="layui-input-inline" id="indicationFlag">
								<input type="radio" name="treatmentRule.indicationFlag" value="1"
									title="是&nbsp;&nbsp;&nbsp;&nbsp;"> <input type="radio"
									name="treatmentRule.indicationFlag" value="0" title="否&nbsp;&nbsp;&nbsp;" >
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">适应症内容</label>
							<div class="layui-input-inline" >
								<input type="text" id="indicationComments"
									name="treatmentRule.indicationComments" autocomplete="off"
									class="layui-input" >
							</div>
						</div>
					</div>

					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">禁忌症标志</label>
							<div class="layui-input-inline" id="contraindicationFlag">
								<input type="radio" name="treatmentRule.contraindicationFlag"
									value="1" title="是&nbsp;&nbsp;&nbsp;&nbsp;"> <input type="radio"
									name="treatmentRule.contraindicationFlag" value="0" title="否&nbsp;&nbsp;&nbsp;"
									>
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">禁忌症内容</label>
							<div class="layui-input-inline" >
								<input type="text" id="contraindicationComments"
									name="treatmentRule.contraindicationComments" autocomplete="off"
									class="layui-input" >
							</div>
						</div>
					</div>
					
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">险&nbsp;种&nbsp;标&nbsp;志&nbsp;</label>
							<div class="layui-input-inline">
								<input type="text" id="insuranceMark"
									name="treatmentRule.insuranceMark" autocomplete="off"
									class="layui-input">
							</div>
						</div>


							<div class="layui-inline">
								<label class="layui-form-label">医院等级</label>
								<div class="layui-input-inline">
									<input type="text" id="orgLevel" name="treatmentRule.orgLevel"
										autocomplete="off" class="layui-input">
								</div>
							</div>
						</div>

						<div class="layui-form-item">
							<div class="layui-inline">
								<label class="layui-form-label">限抢救用药</label>
								<div class="layui-input-inline" id="rescueFlag">
									<input type="radio" name="treatmentRule.rescueFlag" value="1"
										title="是&nbsp;&nbsp;&nbsp;&nbsp;"> <input type="radio"
										name="treatmentRule.rescueFlag" value="0" title="否&nbsp;&nbsp;&nbsp;" >
								</div>
							</div>

							<div class="layui-inline">
								<label class="layui-form-label">就医类型</label>
								<div class="layui-input-inline" id="diagType">
									<input type="radio" name="treatmentRule.diagType" value="1"
										title="门诊" > <input type="radio"
										name="treatmentRule.diagType" value="0" title="住院">
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
	</div>
	</div>

	<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/rulesManager/treatmentRuleInfo.js"></script>
</body>
</html>