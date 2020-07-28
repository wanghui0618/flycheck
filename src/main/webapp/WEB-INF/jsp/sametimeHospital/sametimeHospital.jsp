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
    <title>就诊次数异常</title>
</head>
<style>
	.textbox.easyui-fluid.combo {
		margin-top: 0px;}
</style>
<body>
<div class="layui-fluid">
	<div class="layui-card">
		<div class="layui-form layui-card-header layuiadmin-card-header-auto">
			<div class="layui-form-item">
												
					   <div class="layui-inline">
                            <label class="layui-form-label">医疗机构</label>
                            <div class="layui-input-inline">
                                <input id="getOrgName" name="hospitalName" type="text"/>
                                 <input type="text" id="orgCode" name="hospitalId" style="display: none"/>
                            </div>
                        </div>

            			       		
						<div class="layui-inline ">
							<label class="layui-form-label" style="width: 100px;">入院日期</label>
							<div class="layui-input-inline">
								<input class="layui-input" name="admissiondate" type="text" id="admissiondate"
									placeholder="20180808/20181107">
							</div>
						</div>
						
						<div class="layui-inline">
							<label class="layui-form-label" style="width: 100px;">出院日期</label>
							<div class="layui-input-inline">
								<input class="layui-input" name="dischargedate" type="text" id="dischargedate"
									placeholder="20180808/20181107">
							</div>
						</div>
						
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
		<div class="layui-card-body">
			<table id="dg" class="layui-hide" lay-filter="dg"></table>
		</div>
		
		 <!-- <div class="layui-form-item layui-row">
							<div class="layui-inline">
								<label class="layui-form-label" style="width: 100px;">汇总：</label>
								<button class="layui-btn layui-btn-radius layui-btn-danger">
									总病例数<span id="total_number_of_cases"></span>
								</button>
								<button class="layui-btn layui-btn-radius layui-btn-danger">
									涉及病例总金额<span id="total_amount"></span>
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
</div>

<!--医疗机构下拉js-->
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/common/orgDictSelect.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/sametimeHospital/sametimeHospital.js"></script>
<script type="text/html" id="barDetails">
	<a class="layui-btn layui-btn-xs" lay-event="detail">查看明细</a>
</script>
<script type="text/html" id="barDetailsA">
	<a class="layui-btn layui-btn-xs" lay-event="export">导出</a>
</script>
</body>
</html>
