<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>单病种收费信息</title>
		<%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
		<style>
			.layui-form-label {
				width: 140px;
			}
			
			.layui-form-item {
				margin-top: 25px;
			}
		</style>
	</head>
	<body>
		<div class="layui-form" lay-filter="layuiadmin-form-useradmin" id="layuiadmin-form-studentadmin" style="padding: 20px 0 0 0;">
			<input type="hidden" name="singleDisease.id" id="id"/>
			<div class="layui-form-item">
				<label class="layui-form-label">疾病系统</label>
				<div class="layui-input-inline">
					<input id="system" class="layui-input" type="text" name="singleDisease.system"
							 placeholder="请输入疾病系统"/>
				</div>
				<label class="layui-form-label">系统序号</label>
				<div class="layui-input-inline">
					<input id="code" class="layui-input" type="text" name="singleDisease.code"
							 placeholder="请输入系统序号 ">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">城市编码</label>
				<div class="layui-input-inline">
					<select id="city" name="singleDisease.cityCode" lay-search="">
						<option value="" disabled selected style='display: none;'>请选择城市</option>
					</select>
				</div>
				<label class="layui-form-label">除外内容医保编码</label>
				<div class="layui-input-inline">
					<input id="exceptionsCode" class="layui-input" type="text" name="singleDisease.exceptionsCode"
							placeholder="请输入除外内容医保编码 ">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">诊断编码</label>
				<div class="layui-input-inline">
					<input id="mainDiagCode" class="layui-input" type="text" name="singleDisease.mainDiagCode"
							lay-verify="required" placeholder="请输入主要诊断编码 ">
				</div>
				<label class="layui-form-label">病种名称</label>
				<div class="layui-input-inline">
					<input id="mainDiagName" class="layui-input" type="text" name="singleDisease.mainDiagName"
							lay-verify="required" placeholder="请输入主要诊断 ">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">治疗类别</label>
				<div class="layui-input-inline">
					<input id="treatmentType" class="layui-input" type="text" name="singleDisease.treatmentType"
							lay-verify="required" placeholder="请输入治疗类别 ">
				</div>
				<label class="layui-form-label">麻醉方式</label>
				<div class="layui-input-inline">
					<input id="anaesthesiaWay" class="layui-input" type="text" name="singleDisease.anaesthesiaWay"
							placeholder="请输入麻醉方式 ">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">一类</label>
				<div class="layui-input-inline">
					<input id="type1" class="layui-input" type="text" name="singleDisease.type1"
							lay-verify="number" placeholder="请输入一类 ">
				</div>
				<label class="layui-form-label">二类</label>
				<div class="layui-input-inline">
					<input id="type2" class="layui-input" type="text" name="singleDisease.type2"
							lay-verify="number" placeholder="请输入二类 ">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">除外内容</label>
				<div class="layui-input-inline">
					<input id="exceptions" class="layui-input" type="text" name="singleDisease.exceptions"
							placeholder="请输入除外内容 ">
				</div>
				<label class="layui-form-label">备注</label>
				<div class="layui-input-inline">
					<input id="remark" class="layui-input" type="text" name="singleDisease.remark"
							placeholder="请输入备注 ">
				</div>
			</div>
			<div class="layui-form-item layui-hide">
				<input type="button" lay-submit lay-filter="LAY-cityorg-front-submit"
					id="LAY-cityorg-front-submit" value="确认">
			</div>
		</div>
		<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/infoforbid/infoforbidinfo.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/singleDisease/singleDiseaseInfo.js"></script>
	</body>
</html>