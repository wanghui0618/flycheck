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
<title>药品基础信息管理页面</title>
</head>
<body>
	<div class="layui-fluid">
		<div class="layui-card">
			<fieldset class="layui-elem-field layui-field-title">
				<legend>药品基础信息</legend>
			</fieldset>

			<div class="layui-card-body" style="margin-top: -30px;">
				<div class="layui-form" lay-filter="layuiadmin-form-useradmin"
					id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">

					<input type="hidden" name="drugInfo.id" id="id"
						hide=true>

					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label ">城市名称</label>
							<div class="layui-input-inline ">
								<select name="drugInfo.cityCode" id="cityCode"
									style="width: 100px;" lay-search="">
									<option value="" disabled selected style='display: none;'>请选择城市</option>
								</select>
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label" style="width: 90px;">药&nbsp;品&nbsp;分&nbsp;类</label>
							<div class="layui-input-inline" id="drugType">
								<select name="drugInfo.drugType">
									<option value="0">0-甲类</option>
									<option value="1">1-乙类</option>
									<option value="2">2-丙类</option>
								</select>
							</div>
						</div>
					</div>



					<div class="layui-form-item">
							<div class="layui-inline">
							<label class="layui-form-label">药品编码</label>
							<div class="layui-input-inline">
								<input type="text" id="itemCode"
									name="drugInfo.itemCode" autocomplete="off"
									class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label" style="width: 90px;">药&nbsp;品&nbsp;名&nbsp;称</label>
							<div class="layui-input-inline">
								<input type="text" id="itemName"
									name="drugInfo.itemName" autocomplete="off"
									class="layui-input">
							</div>
						</div>
					</div>
						<div class="layui-form-item">
							<div class="layui-inline">
							<label class="layui-form-label">药剂名称</label>
							<div class="layui-input-inline">
								<input type="text" id="doseForm"
									name="drugInfo.doseForm" autocomplete="off"
									class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label" style="width: 90px;">药品通用名</label>
							<div class="layui-input-inline">
								<input type="text" id="generalName"
									name="drugInfo.generalName" autocomplete="off"
									class="layui-input">
							</div>
						</div>
					</div>

					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">药品规格</label>
							<div class="layui-input-inline">
								<input type="text" id="itemStandard"
									name="drugInfo.itemStandard" autocomplete="off"
									class="layui-input">
							</div>
						</div>
					</div>
						<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">单&nbsp;&nbsp;&nbsp;&nbsp;位&nbsp;&nbsp;&nbsp;</label>
							<div class="layui-input-inline">
								<input type="text" id="itemUnit"
									name="drugInfo.itemUnit" autocomplete="off"
									class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label" style="width: 90px;">生&nbsp;产&nbsp;厂&nbsp;家</label>
							<div class="layui-input-inline">
								<input type="text" id="producer"
									name="drugInfo.producer" autocomplete="off"
									class="layui-input">
							</div>
						</div>
						
					</div>
					
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">起始日期</label>
							<div class="layui-input-inline">
								<input type="text" id="beginTime" name="drugInfo.beginTime"
									lay-verify="required" placeholder="请选择有效起始日期" autocomplete="off"
									class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label" style="width: 90px;">终&nbsp;止&nbsp;日&nbsp;期</label>
							<div class="layui-input-inline">
								<input type="text" id="endTime" name="drugInfo.endTime"
									lay-verify="required" placeholder="请选择有效终止日期" autocomplete="off"
									class="layui-input">
							</div>
						</div>
						</div>

						<div class="layui-form-item">
								<div class="layui-inline">
								<label class="layui-form-label">医&nbsp;&nbsp;&nbsp;&nbsp;保&nbsp;&nbsp;&nbsp;</label>
								<div class="layui-input-inline" id="isMedicare">
									<input type="radio" name="drugInfo.isMedicare" value="1"
										title="是&nbsp;&nbsp;&nbsp;&nbsp;"> <input type="radio"
										name="drugInfo.isMedicare" value="0" title="否&nbsp;&nbsp;&nbsp;" >
								</div>
							</div>

							<div class="layui-inline">
								<label class="layui-form-label" style="width: 90px;">有&nbsp;效&nbsp;标&nbsp;志</label>
								<div class="layui-input-inline" id="validFlag">
									<input type="radio" name="drugInfo.validFlag" value="1"
										title="是&nbsp;&nbsp;&nbsp;&nbsp;" > <input type="radio"
										name="drugInfo.validFlag" value="0" title="否&nbsp;&nbsp;&nbsp;">
								</div>
							</div>
						</div>
							<div class="layui-form-item">
							<div class="layui-inline">
								<label class="layui-form-label">发票项目</label>
								<div class="layui-input-inline">
									<input type="text" id="invoiceProject"
										name="drugInfo.invoiceProject" autocomplete="off"
										class="layui-input">
								</div>
							</div>

							<div class="layui-inline">
								<label class="layui-form-label" style="width: 90px;">备&nbsp;&nbsp;&nbsp;&nbsp;注&nbsp;&nbsp;&nbsp;</label>
								<div class="layui-input-inline">
									<input type="text" id="comments"
										name="drugInfo.comments" autocomplete="off"
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
	


	<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/rulesManager/drugInfo.js"></script>
</body>
</html>