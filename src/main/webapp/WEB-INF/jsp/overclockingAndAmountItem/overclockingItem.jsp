<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
	<%@include file="/WEB-INF/jsp/common/easyui.jsp" %>
<title>超频次和超住院天数</title>
</head>
<style>

</style>
<body>
	<div class="layui-fluid">
		<div class="layui-card">
			<form class="layui-form layui-card-header layuiadmin-card-header-auto">
				<div class="layui-form-item">
						<div class="layui-inline" id="testbelong">
							<label class="layui-form-label ">就诊途径</label>
							<div class="layui-input-inline">
								<select id="limitBelong"  lay-filter="test2" name="overclockingItemVo.limitBelong">
									<option value="住院" selected>住院</option>
									<option value="门诊">门诊</option>
								</select>
							</div>
						</div>

					<div class="layui-inline">
						<label class="layui-form-label">医疗机构</label>
						<div class="layui-input-inline">
							<input id="getOrgName" name="OverclockingItemVo.hospitalName"/>
							<input type="text" id="orgCode" name="OverclockingItemVo.hospitalId"
								   style="display: none;"/>
						</div>
					</div>

					<div class="layui-inline" >
						<label class="layui-form-label ">查询方案</label>
						<div class="layui-input-inline" id="limitQueryPlanDiv">
							<select id="limitQueryPlan" lay-filter="test" name="overclockingItemVo.limitQueryPlan">
								<option value="" >请选择</option>
								<option value="超出住院天数收费" id="mzzyts">超出住院天数收费</option>
								<option value="超频次收费" >超频次收费</option>
							</select>
						</div>
					</div>

					<div class="layui-inline" >
						<label class="layui-form-label ">收费类型</label>
						<div class="layui-input-inline" >
							<select id="limitChargeType" name="overclockingItemVo.limitChargeType">
								<option value="" id="limitChargeTypeDefault" >请选择</option>
								<option value="按时收费" >按时收费</option>
								<option value="按日收费">按日收费</option>
							</select>
						</div>
					</div>

					<div class="layui-inline" >
						<label class="layui-form-label ">住院天数</label>
						<div class="layui-input-inline" >
							<select id="limitDayType" name="overclockingItemVo.limitDayType">
								<option value="" id="limitDayTypedDefault" >请选择</option>
								<option value="算入不算出" >算入不算出</option>
								<option value="算入算出">算入算出</option>
							</select>
						</div>
					</div>

					<div class="layui-inline" >
						<label class="layui-form-label ">结算范围</label>
						<div class="layui-input-inline">
							<input type="text" name="overclockingItemVo.limitBillDate" class="layui-input" id="limitBillDate" placeholder="请选择结算时间 ">

						</div>
					</div>

					<div class="layui-inline" style="width:233px" >
						<label class="layui-form-label ">使用频次</label>
						<div class="layui-input-inline" style="width: 87px">
							<select name="overclockingItemVo.limitSign">
								<option value="等于">等于</option>
								<option value="大于" selected>大于</option>
								<option value="小于" >小于</option>
							</select>

						</div>
						<div class="layui-input-inline " style="width:60px">
								<input type="text" id="limitNumber" name="overclockingItemVo.limitNumber" placeholder="请输入"  autocomplete="off" class="layui-input" style="width:72px">
						</div>


						<div  hidden>
							<input type="text" id="selectItems" name="overclockingItemVo.limitItems" placeholder="请输入" autocomplete="off" class="layui-input" style="width:72px">
						</div>
					</div>


					<div class="layui-inline" >
						<label class="layui-form-label ">计算方式</label>
						<div class="layui-input-inline">
							<select id="limitCountType" name="overclockingItemVo.limitCountType">
								<option value="">请选择</option>
								<option value="次数累计" >次数累计</option>
								<option value="分别计算" >分别计算</option>
							</select>
						</div>
					</div>



					<div class="layui-inline" >
						<label class="layui-form-label ">出现条件</label>
						<div class="layui-input-inline">
							<select  id="limitAppearType" name="overclockingItemVo.limitAppearType" required>
								<option value="" >请选择</option>
								<option value="同次" >同次</option>
								<option value="同天" >同天</option>
							</select>
						</div>
					</div>
					
				<div class="layui-inline">
					<button id="consumableRule-search" class="layui-btn layuiadmin-btn-useradmin" lay-submit lay-filter="LAY-OverclockingItem-search" stylename="search">
						<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
					</button>
					 <button class="layui-btn layui-btn-primary" lay-submit
                        type="reset" id="resets" stylename="allUpdate" lay-filter="LAY-user-front-reset" >
                    	<i class="layui-icon layui-icon-refresh layuiadmin-button-btn"></i>重置
                	</button>
					<button id="exportInfo" stylename="export"
							class="layui-btn layuiadmin-btn-useradmin layui-icon-down-main"
							lay-submit lay-filter="LAY-user-front-export">
						<i class="layui-icon layui-icon-file  layuiadmin-button-btn"></i>导出
					</button>
				</div>
				
				</div>
			</form>

			<div class="layui-card-body">
				<div class="layui-container11">
					<div class="layui-row">
						<div class=" layui-col-md4">
							<textarea id="searchItems" rows="5" required lay-verify="required" placeholder="请输入一个或多个医保项目id，例如：123456,789521x(项目之间以英文逗号、空格、或换行分隔) 门诊无超出住院天数查询方案，同时超频查询方案根据医保id来
筛选项目使用次数大于或小于、等于输入的数量,反馈信息后的使用次数为每个项目在条件内的使用次数（累记项目显示各自使用次数）" class="layui-textarea" style="width:96%;min-height:480px"></textarea>
						</div>
						<div class="layui-col-md8">
						 	<table id="overclockingItemTable" class="layui-hide"  ></table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/overclockingAndAmountItem/overclockingItem.js"></script>
	<%--医疗机构下拉数据字典选择js--%>
	<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/common/orgDictSelect.js"></script>
</body>
</html>