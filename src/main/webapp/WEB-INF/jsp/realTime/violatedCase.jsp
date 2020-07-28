<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" %>
<%@page import="com.dhcc.piccbid.entity.user.User" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css" media="all">
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css" media="all">
<!-- easyui -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/js/easyui-jpp/plugins/jquery-easyui-1.7.1/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/js/easyui-jpp/plugins/jquery-easyui-1.7.1/themes/icon.css"/>
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/plugins/layui/dist-jpp/dist/formSelects-v4.css"
	type="text/css" />
<script
	src="<%=request.getContextPath() %>/plugins/layui/dist-jpp/dist/formSelects-v4.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/js/easyui-jpp/plugins/jquery-easyui-1.7.1/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/js/easyui-jpp/plugins/jquery-easyui-1.7.1/jquery.easyui.min.js"></script>
<script type="text/javascript" 
    src="<%=request.getContextPath() %>/js/easyui-jpp/plugins/jquery-easyui-datagridview/datagrid-detailview.js"></script>
<script type="text/javascript" 
    src="<%=request.getContextPath() %>/js/easyui-jpp/plugins/jquery-easyui-datagridview/datagrid-defaultview.js"></script>
<style>
.layui-form-select dl {
 	max-height:130px; 
}
.layui-table-cell {
	height: 22px;
	line-height: 18px;
	font-size: 13px;
}
/* .layui-form input[type=checkbox], .layui-form input[type=radio], .layui-form select {

    box-shadow: 0 0 black;
} */
.layui-card-header{
line-height: 21px;
}
.layui-form input[type=checkbox]{
    display:block;
    margin-left:6px;
    padding: 0;
    width: 15px;
    height: 18px;
}
}
.datagrid-row {
  height: 25px;
  text-align:center;
}
.lb {
	margin-left: 10px; 
	width: 85px;
	font-size: 13px;
	font-weight: normal;
	margin-top:5px;
	display: inline-block;
	text-align: right;
	/* border-style: solid; */
}
.ipt {
	margin-left: 5px;
	width: 100px;
	font-size: 13px;
	font-weight: normal;
	margin-top:5px;
	display: inline-block;
	text-align: left;
	/* border-style: solid;  */
}
#parent {
	position: relative;
	height: 220px;
	margin-left: -10px;
	margin-top: 10px;
	overflow:hidden;
	/* 高度根据需求自行设定 */
}

#child {
	margin: 0px 12px;
	padding:5px 8px;
	position: absolute;
	width: 100%;
	left: 0;
	top: 0;
	right: 0;
	bottom: 0; /* left ,top,right,bottom都为0，充满真个页面 overflow-y : auto; */
	overflow:hidden;

	/* 设置Y轴出现滚动条，X轴隐藏 */
}
.layui-tab-title .layui-this::after {
	height: 22px;
}
.layui-form-label {
	padding: 9px 3px;
}
.layui-input-block {
	margin-left: 90px;
	width:160px;
}
</style>
<%
	User user = (User)session.getAttribute("user");
	String LoginName = user.getName();
	String roleCode = user.getRoleCode();
%>
<title>审核结果申诉</title>
</head>
<body>
	<div class="layui-fluid">
		<!-- 宽度100% -->
		<div class="layui-row">
			<!-- 行 -->
			<div class="layui-card">
				<!-- 面板 -->
				<div
					class="layui-form layui-card-header layuiadmin-card-header-auto"
					style="font-size: 12px">
					<div class="layui-form-item" style="height: 32px">

						<div class="layui-inline">
							<label class="layui-form-label">就诊类型</label>
							<div class="layui-input-inline">
								<select name="resultAppealVo.diagType" lay-search="">
									<option value="" disabled selected style='display: none;'>请选择就诊类型</option>
									<option value="1">住院</option>
									<option value="2">普通门诊</option>
									<option value="3">门诊大病</option>
									<option value="9">其他</option>
								</select>
							</div>
						</div>

						<div class="layui-inline">
							<label class="layui-form-label">机审结果</label>
							<div class="layui-input-inline">
								<select name="resultAppealVo.sysStatus">
									<option value="" disabled selected style='display: none;'>请选择机审结果</option>
									<option value="0">违规</option>
									<option value="1">疑似违规</option>
									<option value="2">正常</option>
								</select>
							</div>
						</div>

						<div class="layui-inline">
							<label class="layui-form-label">机构名称</label>
							<div class="layui-input-inline">
								<select id="OrgName" name="resultAppealVo.orgName" lay-search="">
									<option value="" class="layui-select-tips layui-unselect">请选择医疗机构</option>
								</select>
							</div>
						</div>

						<div class="layui-inline">
							<label class="layui-form-label">入院时间</label>
							<div class="layui-input-inline">
								<input type="text" name="inhosDate" id="inhosDate"
									placeholder="请输入院时间" autocomplete="off" class="layui-input">
							</div>
						</div>
						<button id='resultAppeal-search-top'
								class="layui-btn layuiadmin-btn-useradmin layui-btn-sm"
								lay-submit lay-filter="LAY-user-front-search">
								<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
							</button>
					</div>

					<!-- <div class="layui-form-item" style="height: 32px">

						<div class="layui-inline">
							<label class="layui-form-label">住院号</label>
							<div class="layui-input-inline">
								<input type="text" name="resultAppealVo.admissionNo"
									placeholder="请输入住院号" autocomplete="off" class="layui-input">
							</div>
						</div>


						<div class="layui-inline">
							<label class="layui-form-label">姓名</label>
							<div class="layui-input-inline">
								<input type="text" name="resultAppealVo.name"
									placeholder="请输入患者姓名" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">社保卡号</label>
							<div class="layui-input-inline">
								<input type="text" name="resultAppealVo.sscno"
									placeholder="请输入社保卡号" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">身份证号</label>
							<div class="layui-input-inline">
								<input type="text" name="resultAppealVo.idcard"
									placeholder="请输入身份证号" autocomplete="off" class="layui-input">
							</div>
						</div>
					</div> -->

					<!-- <div class="layui-form-item" style="height: 32px">
						<div class="layui-inline">
							<label class="layui-form-label">收费单据号</label>
							<div class="layui-input-inline" style="width: 163.6px">
								<input type="text" name="resultAppealVo.billingNo"
									placeholder="请输入收费单据号" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">违规类型</label>
							<div class="layui-input-inline">
								<select id="ruleType" name="resultAppealVo.ilegalType"
									lay-search="">
									<option value="" disabled selected style='display: none;'>请选择类型</option>
								</select>
							</div>
						</div>
						<div class="layui-inline" style="margin-left: 22px">
							<button id='resultAppeal-search-top'
								class="layui-btn layuiadmin-btn-useradmin layui-btn-sm"
								lay-submit lay-filter="LAY-user-front-search">
								<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
							</button>
						</div>
						style="float:right; margin-right:5px"
						<div class="layui-inline">
							<button class="layui-btn layuiadmin-btn-useradmin layui-btn-sm"
								id="resultAppeal-sexportExcel">
								<i class="layui-icon  layuiadmin-button-btn"></i>全部导出
							</button>
						</div>
					</div> -->

				</div>

				<div class="layui-card-body" style="margin-top: 14px;">
					<table id="resultAppeal" class="layui-hide"
						lay-filter="resultAppeal"></table>
				</div>

			</div>
		</div>

		<div class="layui-row">
			<!-- 行 -->
			<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief"
				style="margin-bottom: 0px;">
				<!-- 简洁选项卡 -->
				<ul class="layui-tab-title"
					style="margin-top: 0px; height: 18px; border-bottom-width: 0px;">
					<li style="font-size: 13px; height: 15px; line-height: 15px">就诊记录</li>
					<li class="layui-this"
						style="font-size: 13px; height: 15px; line-height: 15px">就诊明细记录</li>
				</ul>
				<div class="layui-tab-content"
					style="margin-top: -15px; padding-left: 0px; padding-right: 0px;">
					<div class="layui-tab-item ">
						<div class="layui-fluid" id="parent">
							<!-- 宽度100% -->
							<div class="layui-card" id="child">
								<!-- 面板 -->
								<h5 style="font-size: 13px; font-weight: bold;">就诊信息</h5>
								<hr style="margin-top: 1px; margin-bottom: 1px;">
								<div>
									<lable class="lb">姓名:</lable>
									<lable class="ipt" id="name"></lable>
									<lable class="lb">身份证号:</lable>
									<lable class="ipt" id="idcard"></lable>
									<lable class="lb">年龄:</lable>
									<lable class="ipt" id="age"></lable>
									<lable class="lb">性别:</lable>
									<lable class="ipt" id="sex"></lable>
									<lable class="lb">城市:</lable>
									<lable class="ipt" id="cityName"></lable>
									<lable class="lb">机构名称:</lable>
									<lable class="ipt" id="orgName"></lable>
								</div>

								<div>
									<lable class="lb">就诊类型:</lable>
									<lable class="ipt" id="diagType"></lable>

									<lable class="lb">住院天数:</lable>
									<lable class="ipt" id="stayLength"></lable>
									<lable class="lb">入院日期:</lable>
									<lable class="ipt" id="inhosDate"></lable>
									<lable class="lb">出院日期:</lable>
									<lable class="ipt" id="outhosDate"></lable>
									<lable class="lb">入院类型:</lable>
									<lable class="ipt" id="dischargeState"></lable>
									<lable class="lb">出院方式:</lable>
									<lable class="ipt" id="dischargeState"></lable>

								</div>
								<div>
									<lable class="lb">总金额:</lable>
									<lable class="ipt" id="sumAmount"></lable>
									<lable class="lb">基本医疗金额:</lable>
									<lable class="ipt" id="basicCostR"></lable>
									<lable class="lb">医保金额:</lable>
									<lable class="ipt" id="basicCostR"></lable>
									<lable class="lb">非医保金额:</lable>
									<lable class="ipt" id="selfExpenditureAmount"></lable>
									<lable class="lb">报销金额:</lable>
									<lable class="ipt" id="fundCost"></lable>
									<lable class="lb">个人自付金额:</lable>
									<lable class="ipt" id="selfPayAmount"></lable>
								</div>

								<div style="height: 8px;">&nbsp;</div>
								<h5 style="font-size: 13px; font-weight: bold;">诊断信息</h5>
								<hr style="margin-top: 1px; margin-bottom: 1px;">
								<div>
									<lable class="lb">主要诊断:</lable>
									<lable class="ipt" id="condition"></lable>
									<lable class="lb">次要诊断:</lable>
									<lable class="ipt" id="outDiagnosisName"></lable>
								</div>

								<div style="height: 8px;">&nbsp;</div>
								<h5 style="font-size: 13px; font-weight: bold;">手术信息</h5>
								<hr style="margin-top: 1px; margin-bottom: 1px;">
								<div>
									<lable class="lb">手术名称:</lable>
									<lable class="ipt" id="operationName"></lable>
									<lable class="lb">手术级别:</lable>
									<lable class="ipt" id="operationLevel"></lable>
									<lable class="lb">切口类型:</lable>
									<lable class="ipt" id="incisionType"></lable>
									<lable class="lb">手术开始时间:</lable>
									<lable class="ipt" id="operBeginTime"></lable>
									<lable class="lb">手术结束时间:</lable>
									<lable class="ipt" id="operEndTime"></lable>
									<lable class="lb">麻醉方式:</lable>
									<lable class="ipt" id="narcosisWay"></lable>
								</div>
								<div>
									<lable class="lb">愈合状态:</lable>
									<lable class="ipt" id="healingLevel"></lable>
									<lable class="lb">手术人员:</lable>
									<lable class="ipt" id="operationDoc"></lable>
									<lable class="lb">麻醉者:</lable>
									<lable class="ipt" id="narcosisDoc"></lable>
								</div>
								<br>
							</div>
						</div>
					</div>
					<div class="layui-tab-item layui-show">
						<div class="layui-fluid" style="padding: 10px 0px 0px 0px;">
							<!-- 宽度100% -->
							<div class="layui-row">
								<!-- 行 -->
								<div class="layui-card">
									<!-- 面板 -->

									<div
										class="layui-form layui-card-header layuiadmin-card-header-auto"
										style="font-size: 12px">

										<input type="hidden" name="medicalDetail.medicalId"
											id="medical_id_form"></input>
										<!-- 隐藏标签 -->
										<div class="layui-form-item" style="height: 30px">
											<div class="layui-inline">
												<label class="layui-form-label">违规类型</label>
												<div class="layui-input-block" style="width: 120px">
													<select id="ruleType" name="medicalDetail.ilegalType">
														<option value="" disabled selected style='display: none;'>请选择类型</option>

													</select>
												</div>
											</div>
											<div class="layui-inline">
												<label class="layui-form-label">机审结果</label>
												<div class="layui-input-block" style="width: 260px">
													<select name="medicalDetail.isIlegal" id="Myselect"
														xm-select="select1" xm-select-direction="down">
														<option></option>
														<option value="0">违规</option>
														<option value="2">正常</option>
														<option value="1">疑似违规</option>
													</select>
												</div>
											</div>

											<div class="layui-inline">
												<label class="layui-form-label" style="width: 90px">项目编码/名称</label>
												<div class="layui-input-block">
													<input type="text" id="finaTime"
														name="medicalDetail.itemName" placeholder=""
														autocomplete="off" class="layui-input">
												</div>
											</div>
										</div>
									</div>
									<div class="layui-card-body" style="margin-top: 13px">
										<table id="dg" fitColumns="true" singleSelect="true"
											pagination="true"></table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="<%=request.getContextPath() %>/app/js/realTime/violatedCase.js"></script>
</body>
</html>