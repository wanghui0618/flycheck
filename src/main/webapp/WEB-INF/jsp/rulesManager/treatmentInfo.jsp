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
</style>
<title>诊疗基础信息管理页面</title>
</head>
<body>
	<div class="layui-fluid">
		<div class="layui-card" >
			<fieldset class="layui-elem-field layui-field-title">
				<legend>诊疗基础信息</legend>
			</fieldset>
			<div class="layui-card-body" style="margin-top: -30px;">
				<div class="layui-form" lay-filter="layuiadmin-form-useradmin"
					id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">

					<input type="hidden" name="treatmentInfo.id" id="id"
						hide=true>

					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">城市名称</label>
							<div class="layui-input-inline">
								<select name="treatmentInfo.cityCode" id="cityCode" lay-search="">
									<option value="" disabled selected style='display: none;'>请选择城市</option>
								</select>
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">诊疗分类</label>
							<div class="layui-input-inline" id="projectType">
								<select name="treatmentInfo.projectType">
									<option value="0">0-甲类</option>
									<option value="1">1-乙类</option>
									<option value="2">2-丙类</option>
								</select>
							</div>
						</div>
					</div>



					<div class="layui-form-item">
							<div class="layui-inline">
							<label class="layui-form-label">诊疗编码</label>
							<div class="layui-input-inline">
								<input type="text" id="itemCode"
									name="treatmentInfo.itemCode" autocomplete="off"
									class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">诊疗名称</label>
							<div class="layui-input-inline">
								<input type="text" id="itemName"
									name="treatmentInfo.itemName" autocomplete="off"
									class="layui-input">
							</div>
						</div>
					</div>

					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">诊疗规格</label>
							<div class="layui-input-inline">
								<input type="text" id="itemStandard"
									name="treatmentInfo.itemStandard" autocomplete="off"
									class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">单&nbsp;&nbsp;&nbsp;&nbsp;位&nbsp;&nbsp;&nbsp;</label>
							<div class="layui-input-inline">
								<input type="text" id="itemUnit"
									name="treatmentInfo.itemUnit" autocomplete="off"
									class="layui-input">
							</div>
						</div>
				
					</div>
					
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">起始日期</label>
							<div class="layui-input-inline">
								<input type="text" id="beginTime" name="treatmentInfo.beginTime"
									lay-verify="required" placeholder="请选择有效起始日期" autocomplete="off"
									class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">终止日期</label>
							<div class="layui-input-inline">
								<input type="text" id="endTime" name="treatmentInfo.endTime"
									lay-verify="required" placeholder="请选择有效终止日期" autocomplete="off"
									class="layui-input">
							</div>
						</div>
						</div>

						<div class="layui-form-item">
								<div class="layui-inline">
								<label class="layui-form-label">医&nbsp;&nbsp;&nbsp;&nbsp;保&nbsp;&nbsp;&nbsp;</label>
								<div class="layui-input-inline" id="isMedicare">
									<input type="radio" name="treatmentInfo.isMedicare" value="1"
										title="是&nbsp;&nbsp;&nbsp;&nbsp;"> <input type="radio"
										name="treatmentInfo.isMedicare" value="0" title="否&nbsp;&nbsp;&nbsp;" >
								</div>
							</div>

							<div class="layui-inline">
								<label class="layui-form-label">有效标志</label>
								<div class="layui-input-inline" id="validFlag">
									<input type="radio" name="treatmentInfo.validFlag" value="1"
										title="是&nbsp;&nbsp;&nbsp;&nbsp;" > <input type="radio"
										name="treatmentInfo.validFlag" value="0" title="否&nbsp;&nbsp;&nbsp;">
								</div>
							</div>
						</div>
							<div class="layui-form-item">
							<div class="layui-inline">
								<label class="layui-form-label">发票项目</label>
								<div class="layui-input-inline">
									<input type="text" id="invoiceProject"
										name="treatmentInfo.invoiceProject" autocomplete="off"
										class="layui-input">
								</div>
							</div>

							<div class="layui-inline">
								<label class="layui-form-label">备&nbsp;&nbsp;&nbsp;&nbsp;注&nbsp;&nbsp;&nbsp;</label>
								<div class="layui-input-inline">
									<input type="text" id="comments"
										name="treatmentInfo.comments" autocomplete="off"
										class="layui-input">
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
	


	<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/rulesManager/treatmentInfo.js"></script>
</body>
</html>