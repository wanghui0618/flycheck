<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
	<%@include file="/WEB-INF/jsp/common/easyui.jsp" %>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css" media="all">
    <title>分解住院</title>
	<style>
		.textbox.easyui-fluid.combo {
			margin-top: 0px;}
	</style>
</head>
<body>
<div class="layui-fluid">
	<div class="layui-card">
		<div class="layui-form layui-card-header layuiadmin-card-header-auto">
			<div class="layui-form-item">
						<!-- <div class="layui-inline">
							<label class="layui-form-label" style="width: 100px;">人员类别</label>
							<div class="layui-input-inline">
								<input class="layui-input" name="route" type="text"
									placeholder="市属职工">
							</div>
						</div> -->
						<div class="layui-inline ">
                    <label class="layui-form-label ">人员类型</label>
                    <div class="layui-input-inline">
                        <select name="route" >
                            <option value="" selected>请选择</option>
                            <option value="市属职工">市属职工</option>
                            <option value="市属居民">市属居民</option>
                            <option value="省直（职工）">省直（职工）</option>
                            <option value="省内异地">省内异地</option>
                            <option value="异地省外">异地省外</option>
                            <option value="自费">自费</option>
                        </select>
                    </div>
                </div>
						<div class="layui-inline">
							<label class="layui-form-label" style="width: 100px;">结算时间</label>
							<div class="layui-input-inline">
								<input class="layui-input" name="settlementTime" type="text" id="settlementTime"
									   placeholder="20180808/20181107">
							</div>
						</div>
						<%--<div class="layui-inline">--%>
							<%--<label class="layui-form-label" style="width: 100px;">险种类型</label>--%>
							<%--<div class="layui-input-inline">--%>
								<%--<input class="layui-input" name="insurance" type="text"--%>
									   <%--placeholder="请输入">--%>
							<%--</div>--%>
						<%--</div>--%>
						<%--<div class="layui-inline">--%>
							<%--<label class="layui-form-label" style="width: 100px;">医院编码</label>--%>
							<%--<div class="layui-input-inline">--%>
								<%--<input class="layui-input" name="hospitalCode" type="text"--%>
									<%--placeholder="请输入">--%>
							<%--</div>--%>
						<%--</div>--%>
						<%--<div class="layui-inline">--%>
							<%--<label class="layui-form-label" style="width: 100px;">医院名称</label>--%>
							<%--<div class="layui-input-inline">--%>
								<%--<input class="layui-input" name="hospitalName" type="text"--%>
									<%--placeholder="请输入">--%>
							<%--</div>--%>
						<%--</div>--%>
						<div class="layui-inline">
							<label class="layui-form-label">医疗机构</label>
							<div class="layui-input-inline">
								<input id="getOrgName" name="hospitalName"/>
								<input type="text" id="orgCode" name="hospitalCode" style="display: none;"/>
							</div>
						</div>
						<div id="xiala" onclick="showSearch()" style="color:#2284FF;cursor:pointer;z-index:9999; margin-left: 10px;display: inline-block;">更多<img style="margin: 0 auto;height: 6px;width: 8px;" src="<%=request.getContextPath()%>/images/main/xiala.png" /></div>
						<div class="layui-inline cxtjtop">
							<label class="layui-form-label" style="width: 100px;">科室名称</label>
							<div class="layui-input-inline">
								<input class="layui-input" name="departmentName" type="text"
									placeholder="骨科一">
							</div>
						</div>
						<div class="layui-inline cxtjtop">
							<label class="layui-form-label" style="width: 100px;">分解住院天数</label>
							<div class="layui-input-inline" style="width: 89.5px">
								<select id="day">
									<option name="day" value="dayu">大于</option>
									<option name="day" value="xiaoyu" selected>小于</option>
									<option name="day" value="dengyu">等于</option>
								</select>
							</div>
							<div class="layui-input-inline">
								<input class="layui-input" name="decomposedHospitalStay"
									type="text" placeholder="15" style="width: 66.5px;" autocomplete="off">
							</div>
						</div>
						
						<div id="shangla" onclick="hideSearch()" style="color:#2284FF;cursor:pointer; margin-left: 68px;z-index:9999;display: inline-block;">收起<img style="margin: 0 auto;height: 6px;width: 8px;" src="<%=request.getContextPath()%>/images/main/shangla.png" /></div>
						<div class="layui-inline">
							<button class="layui-btn layuiadmin-btn-useradmin" lay-submit stylename="search"
								lay-filter="LAY-user-front-search" onclick="search()">
								<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
								</button>
							<button class="layui-btn layuiadmin-btn-useradmin" lay-submit stylename="allUpdate"
								lay-filter="LAY-user-front-search" onclick="reset()">
								<i class="layui-icon layui-icon-refresh layuiadmin-button-btn"></i>重置
								</button>
							<button class="layui-btn layuiadmin-btn-useradmin" lay-submit stylename="export"
								lay-filter="LAY-user-front-search" id="export">
								<i class="layui-icon layui-icon-file  layuiadmin-button-btn"></i>导出
								</button>
						</div>
						
			</div>
		</div>

		<div class="layui-card-body layui-inline layui-col-md4">
			<table id="dig" class="layui-hide" lay-filter="dig"></table>
			<!-- <div class="layui-form-item layui-row">
							<div class="layui-inline">
								<label class="layui-form-label" style="width: 100px;">汇总：</label>
								<button class="layui-btn layui-btn-radius layui-btn-danger">
									总病例数：<span id="total_number_of_cases"></span>
								</button>
								<button class="layui-btn layui-btn-radius layui-btn-danger">
									总金额：<span id="total_amount"></span>
								</button>
							</div>
						</div> -->
						<div class="layui-tab layui-tab-brief">
                            <ul class="layui-tab-title">
                                <li class="layui-this">汇总：</li>
                                <li>总病例数：<span class="layui-badge" id="total_number_of_cases"></span></li>
                                <li>涉及病例总金额：<span class="layui-badge" id="total_amount"></span></li>
                            </ul>
                            <div class="layui-tab-content"></div>
                        </div> 
		</div>

		<div class="layui-card-body layui-inline layui-col-md8">
			<table id="dg" class="layui-hide" lay-filter="dg"></table>
		</div>
		
		
						
	</div>
</div>

<!--医疗机构下拉js-->
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/common/orgDictSelect.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/decompositionHospital/decompositionHospital.js"></script>
<script type="text/html" id="barDetails">
	<a class="layui-btn layui-btn-xs" lay-event="detail">查看明细</a>
</script>
<script type="text/html" id="barDetailsA">
	<a class="layui-btn layui-btn-xs" lay-event="export">导出</a>
</script>
</body>
</html>
