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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/layui/css/layui.css"
	media="all">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/style/admin.css"
	media="all">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/style/UserHistoryin.css"
	media="all">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/layui/css/layui.css"
	media="all">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/style/admin.css"
	media="all">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/app/js/physicalExaminationAdmission/getDict.js"></script>

<%@include file="/WEB-INF/jsp/common/scriptInc.jsp"%>
<%@include file="/WEB-INF/jsp/common/easyui.jsp"%>
<title>体检式入院</title>
</head>
<!--但label中的文字超过4个字时使用  -->
<style>
.layui-form-label {
	width: 80px;
}
</style>

<body>
	<div class="layui-fluid">
		<div class="layui-card">
			<div class="layui-form layui-card-header layuiadmin-card-header-auto">
				<div class="layui-form-item">

					<!-- 第1个 -->
					<div class="layui-inline">
						<label class="layui-form-label">诊断</label>
						<div class="layui-input-inline">
							<input id="getDiagName" name="admissionDiseaseName" /> <input
								type="text" id="diagCode" name="admissionDiseaseId"
								style="display: none;" />
						</div>
					</div>

					<!-- 第2个 -->
					<div class="layui-inline">
						<label class="layui-form-label">医疗机构</label>
						<div class="layui-input-inline">
							<input id="getOrgName" name="hospitalName" /> <input type="text"
								id="orgCode" name="hospitalId" style="display: none;" />
						</div>
					</div>


					<!-- 第3个 -->
					<div class="layui-inline">
						<label class="layui-form-label">结算日期</label>
						<div class="layui-input-inline">
							<input class="layui-input" id="billDate" name="billDate"
								type="text" readonly placeholder="请选择结算日期">
						</div>
					</div>

					<!-- 下拉按钮 -->
					<div id="xiala" onclick="showSearch()"
						style="color: #2284FF; cursor: pointer; z-index: 9999; margin-left: 10px; display: inline-block;">
						更多 <img style="margin: 0 auto; height: 6px; width: 8px;"
							src="<%=request.getContextPath()%>/images/main/xiala.png" />
					</div>

					<!-- 第二行 -->
					<!-- 隐藏/显示 -->

					<div class="layui-inline cxtjtop">
						<label class="layui-form-label">入院日期</label>
						<div class="layui-input-inline">
							<input class="layui-input" id="admissionDate"
								name="admissionDate" type="text" readonly placeholder="请选择入院日期">
						</div>
					</div>

					<div class="layui-inline cxtjtop">
						<label class="layui-form-label">出院日期</label>
						<div class="layui-input-inline">
							<input class="layui-input" id="dischargeDate"
								name="dischargeDate" type="text" readonly placeholder="请选择出院日期">
						</div>
					</div>

					<!--组合判断条件-->
					<div class="layui-inline cxtjtop">
						<label class="layui-form-label">药品费用</label>
						<div class="layui-input-inline" style="width: 80px;">
							<select name="code" id="code" autocomplete="off"
								style="width: 100px;">
								<option value=">">大于</option>
								<option value="=">等于</option>
								<option selected value="<">小于</option>
							</select>
						</div>
						<div class="layui-input-inline" style="width: 77px;">
							<input type="text" name="Sumdrugs" id="Sumdrugs"
								style="width: 76px;" value="200" autocomplete="off"
								class="layui-input">
						</div>
					</div>

					<div class="layui-inline cxtjtop">
						<label class="layui-form-label" style="width: 80px;">检查费占比</label>
						<div class="layui-input-inline" style="width: 80px;">
							<select name="code1" id="code1" autocomplete="off"
								style="width: 90px;">
								<option value=">" selected="selected">大于</option>
								<option value="=">等于</option>
								<option value="<">小于</option>
							</select>
						</div>


						<div class="layui-input-inline" style="width: 38px;">
							<input type="text" name="jianchafei" id="jianchafei"
								style="width: 76px;" value="80" autocomplete="off"
								class="layui-input">
						</div>
						<div class="layui-input-inline" style="width: 40px;">
							<input type="text" name="baifenhao" id="baifenhao"
								style="width: 36px;" autocomplete="off" class="layui-input"
								value="%" readonly="readonly"">
						</div>
					</div>

					<!-- 上收按钮 -->
					<div id="shangla" onclick="hideSearch()"
						style="color: #2284FF; cursor: pointer; margin-left: 30px; z-index: 9999; display: inline-block;">
						收起 <img style="margin: 0 auto; height: 6px; width: 8px;"
							src="<%=request.getContextPath()%>/images/main/shangla.png" />
					</div>


					<!--组合按钮框-->
					<div class="layui-inline">
						<button class="layui-btn layuiadmin-btn-useradmin" lay-submit
							lay-filter="physicalExamination" stylename="search">
							<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
						</button>
						<button class="layui-btn layui-btn-primary" type="button"
							id="resets" stylename="allUpdate">
							<i class="layui-icon layui-icon-refresh layuiadmin-button-btn"></i>重置
						</button>
						<button id="violation-dc" stylename="export"
							class="layui-btn layuiadmin-btn-useradmin layui-icon-down-main"
							lay-submit lay-filter="LAY-user-front-export">
							<i class="layui-icon layui-icon-file  layuiadmin-button-btn"></i>导出
						</button>
					</div>



				</div>
			</div>

			<!--下方table-->
			<div class="layui-card-body">
				<script type="text/html" id="mingxi">
                <a class="layui-btn layui-btn-xs" lay-event="mingxi">结算明细</a>
            </script>
				<table id="physicalExaminationAdmission" class="layui-hide"
					lay-filter="physicalExaminationAdmission">
				</table>
				 <div class="layui-form-item">
					<div class="layui-inline" >
						<label class="layui-form-label" >汇总：</label>
						<div class="layui-input-inline" style="margin-top: 6px">
							总病例数：<input class="layui-btn layui-btn-sm layui-btn-danger"
								type="button" name="bingli" id="bingli" value="">
						</div>
						<div class="layui-input-inline" style="margin-top: 6px">
							总金额：<input class="layui-btn layui-btn-sm layui-btn-danger"
								type="button" name="jine" id="jine" value="">
						</div>
						<div class="layui-input-inline" style="margin-left: 30px;margin-top: 6px">
							涉及明细数：<input class="layui-btn layui-btn-sm layui-btn-danger"
								type="button" name="bingliMX" id="bingliMX" value="">
						</div>
						<div class="layui-input-inline" style="width:250px;margin-left: 20px;margin-top: 6px">
							涉及明细金额：<input class="layui-btn layui-btn-sm layui-btn-danger"
								type="button" name="jineMX" id="jineMX" value="">
						</div>
					</div>
				</div>
				
				<!-- <div class="layui-tab layui-tab-brief">
                            <ul class="layui-tab-title">
                                <li class="layui-this">汇总：</li>
                                <li>总病例数：<span class="layui-badge"  id="bingli" name="bingli"  value=""></span></li>
                                <li>涉及病例总金额：<span class="layui-badge"  id="jine" name="jine"  value=""></span></li>
                                <li>总病例明细数：<span class="layui-badge" id="bingliMX"  name="bingliMX" value=""></span></li>
                                <li>涉及病例明细总金额：<span class="layui-badge" id="jineMX" name="jineMX"  value=""></span></li>
                            </ul>
                            <div class="layui-tab-content"></div>
                        </div> -->
				
			</div>
		</div>
	</div>

	<script type="text/javascript"
		src="<%=request.getContextPath()%>/app/js/physicalExaminationAdmission/physicalExaminationAdmission.js"></script>
	<!--医疗机构下拉js-->
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/app/js/common/orgDictSelect.js"></script>
	<!--诊断下拉js-->
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/app/js/common/diagDictSelect.js"></script>
</body>
</html>