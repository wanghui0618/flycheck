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
.layui-form-label {
    width: 90px;
    }
    .layui-form-item {
	margin-bottom: 14px;
}
</style>
<title>药品管理新增/修改页面</title>
</head>
<body>
	<div class="layui-fluid">
		<div class="layui-card" style="height: 360px;">
			<fieldset class="layui-elem-field layui-field-title">
				<legend>药品</legend>
			</fieldset>
			<div class="layui-card-body" style="margin-top:-20px; ">
				<div class="layui-form" lay-filter="layuiadmin-form-useradmin"
					id="layuiadmin-form-useradmin" style="padding: 0px 0 0 0;">

					<input type="hidden" name="drugRule.itemCode" id="itemCode"
						hide=true>

					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">性&nbsp;别&nbsp;限&nbsp;制&nbsp;</label>
							<div class="layui-input-inline" id="sexFlag">
								<select name="drugRule.sexFlag">
									<option value="0">0-无限制</option>
									<option value="1">1-限男性</option>
									<option value="2">2-限女性</option>
								</select>
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">限儿童用药</label>
							<div class="layui-input-inline" id="personType">
								<input type="radio" name="drugRule.personType" value="1"
									title="是&nbsp;&nbsp;&nbsp;&nbsp;"> <input type="radio"
									name="drugRule.personType" value="0"
									title="否&nbsp;&nbsp;&nbsp;">
							</div>
						</div>
					</div>



					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">适应症标志</label>
							<div class="layui-input-inline" id="indicationFlag">
								<input type="radio" name="drugRule.indicationFlag" value="1"
									title="是&nbsp;&nbsp;&nbsp;&nbsp;"> <input type="radio"
									name="drugRule.indicationFlag" value="0" title="否&nbsp;&nbsp;&nbsp;" >
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">适应症内容</label>
							<div class="layui-input-inline" >
								<input type="text" id="indicationComments"
									name="drugRule.indicationComments" autocomplete="off"
									class="layui-input" >
							</div>
						</div>
					</div>

					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">禁忌症标志</label>
							<div class="layui-input-inline" id="contraindicationFlag">
								<input type="radio" name="drugRule.contraindicationFlag"
									value="1" title="是&nbsp;&nbsp;&nbsp;&nbsp;"> <input type="radio"
									name="drugRule.contraindicationFlag" value="0" title="否&nbsp;&nbsp;&nbsp;"
									>
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">禁忌症内容</label>
							<div class="layui-input-inline" >
								<input type="text" id="contraindicationComments"
									name="drugRule.contraindicationComments" autocomplete="off"
									class="layui-input" >
							</div>
						</div>
					</div>
					
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">险&nbsp;种&nbsp;标&nbsp;志&nbsp;</label>
							<div class="layui-input-inline">
								<input type="text" id="insuranceMark"
									name="drugRule.insuranceMark" autocomplete="off"
									class="layui-input">
							</div>
						</div>

							<div class="layui-inline">
								<label class="layui-form-label">医院等级</label>
								<div class="layui-input-inline">
									<input type="text" id="orgLevel" name="drugRule.orgLevel"
										autocomplete="off" class="layui-input">
								</div>
							</div>
						</div>

						<div class="layui-form-item">
							<div class="layui-inline">
								<label class="layui-form-label">限抢救用药</label>
								<div class="layui-input-inline" id="rescueFlag">
									<input type="radio" name="drugRule.rescueFlag" value="1"
										title="是&nbsp;&nbsp;&nbsp;&nbsp;"> <input type="radio"
										name="drugRule.rescueFlag" value="0" title="否&nbsp;&nbsp;&nbsp;" >
								</div>
							</div>

							<div class="layui-inline">
								<label class="layui-form-label">就医类型</label>
								<div class="layui-input-inline" id="diagType">
									<input type="radio" name="drugRule.diagType" value="1"
										title="门诊" > <input type="radio"
										name="drugRule.diagType" value="0" title="住院">
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

	<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/rulesManager/drugRuleInfo.js"></script>
</body>
</html>