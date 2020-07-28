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
<%@include file="/WEB-INF/jsp/common/easyui.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/layui/css/layui.css"
	media="all">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/style/admin.css"
	media="all">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/style/login.css"
	media="all">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/layui/tree/css/zTreeStyle.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/app/js/dictdiag/js/jquery.ztree.all.js"></script>
<title>住院天数异常</title>
</head>
<style>
.layui-form-select .layui-edge {
	left: 64.767px;
}
</style>
<body>
	<div class="layui-fluid">
		<div class="layui-card">
			<div class="layui-form layui-card-header layuiadmin-card-header-auto"
				lay-filter="addGoodsForm" id="addGoodsForm">
				<%--医疗机构和诊断做成下拉框--%>
				<div class="layui-inline">
					<label class="layui-form-label">医疗机构</label>
					<div class="layui-input-inline">
						<input id="getOrgName" name="hospitalName" /> <input type="text"
							id="orgCode" name="hospitalId" style="display: none;" />
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">诊断</label>
					<div class="layui-input-inline">
						<input id="getDiagName" name="admissionDiseaseName" /> <input
							type="text" id="diagCode" name="admissionDiseaseId"
							style="display: none;" />
					</div>
				</div>

				<div class="layui-inline">
					<label class="layui-form-label">住院天数</label>
					<div class="layui-input-inline" style="width: 89.5px;">
						<select id="code" name="code" autocomplete="off"
							style="width: 89.5px;">
							<option value="one">大于</option>
							<option value="two">等于</option>
							<option value="three">小于</option>
						</select>
					</div>
					<div class="layui-input-inline" style="width: 66.5px;">
						<input type="text" id="zyts" name="zyts"
							lay-verify="validateMoney" value="8" style="width: 66.5px;"
							autocomplete="off" class="layui-input">
					</div>
				</div>

				<!-- 下拉按钮 -->
				<div id="xiala" onclick="showSearch()"
					style="color: #2284FF; cursor: pointer; z-index: 9999; margin-left: 10px; display: inline-block;">
					更多 <img style="margin: 0 auto; height: 6px; width: 8px;"
						src="<%=request.getContextPath()%>/images/main/xiala.png" />
				</div>

				<div class="layui-inline cxtjtop">
					<label class="layui-form-label">结算日期</label>
					<div class="layui-input-inline">
						<input type="text" class="layui-input" id="test6"
							 name="paydate" placeholder="请选择结算日期区间">
					</div>
				</div>

				<div class="layui-inline cxtjtop">
					<label class="layui-form-label ">入院日期</label>
					<div class="layui-input-inline">
						<input type="text" class="layui-input" id="test7"
							 name="indate" placeholder="请选择入院日期区间">
					</div>
				</div>
				<div class="layui-inline cxtjtop">
					<label class="layui-form-label ">出院日期</label>
					<div class="layui-input-inline">
						<input type="text" class="layui-input" id="test8"
							 name="outdate" placeholder="请选择出院日期区间">
					</div>
				</div>

				<!-- 上收按钮 -->
				<div id="shangla" onclick="hideSearch()"
					style="color: #2284FF; cursor: pointer; margin-left: 10px; z-index: 9999; display: inline-block;">
					收起 <img style="margin: 0 auto; height: 6px; width: 8px;"
						src="<%=request.getContextPath()%>/images/main/shangla.png" />
				</div>

				<div class="layui-inline">
					<button id="search" class="layui-btn layuiadmin-btn-useradmin"
						lay-submit lay-filter="search" stylename="search">
						<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
					</button>

					<button id="reset" class="layui-btn layuiadmin-btn-useradmin"
						lay-submit lay-filter="resets" stylename="allUpdate">
						<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>重置
					</button>

					<!-- <button class="layui-btn" type="button">导出Excel</button> -->
					<button id="violation-dc" stylename="export"
						class="layui-btn layuiadmin-btn-useradmin layui-icon-down-main"
						lay-submit lay-filter="LAY-user-front-export">
						<i class="layui-icon layui-icon-file  layuiadmin-button-btn"></i>导出
					</button>
				</div>
			</div>
		</div>

		<div class="layui-card-body">
			<script type="text/html" id="barDemo">
                <a class="layui-btn layui-btn-xs" lay-event="searcDetail">结算明细</a>
            </script>
			<table id="abnormalHospitalStay" class="layui-hide"
				lay-filter="abnormalHospitalStay"></table>
			<div class="layui-tab layui-tab-brief">
				<ul class="layui-tab-title">
					<li class="layui-this">汇总：</li>
					<li>总病例数：<span class="layui-badge" id="rowsum"></span></li>
					<li>涉及病例总金额：<span class="layui-badge" id="sumAmount"></span></li>
				</ul>
				<div class="layui-tab-content"></div>
			</div>
		</div>
	</div>

	<script type="text/javascript"
		src="<%=request.getContextPath()%>/app/js/abnormalHospitalStay/abnormalHospitalStay.js"></script>
	<!--医疗机构下拉js-->
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/app/js/abnormalHospitalStay/orgDictSelect.js"></script>
	<!--诊断下拉js-->
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/app/js/abnormalHospitalStay/diagDictSelect.js"></script>
	<script type="text/javascript">
		$(".cxtjtop").hide();
		$("#shangla").hide();
		//
		// function showSearch() {
		//     $("#shangla").show();
		//     $("#xiala").hide();
		//     $(".cxtjtop").show();
		// }
		//
		// function hideSearch() {
		//     $(".cxtjtop").hide();
		//     $("#shangla").hide();
		//     $("#xiala").show();
		// }
	</script>

</body>

</html>