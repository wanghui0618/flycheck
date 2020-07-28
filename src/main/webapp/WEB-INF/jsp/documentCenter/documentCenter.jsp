<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <%@include file="/WEB-INF/jsp/common/scriptInc.jsp"%>
    <%@include file="/WEB-INF/jsp/common/easyui.jsp" %>
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/layui/css/layui.css"
          media="all">
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/style/admin.css"
          media="all">
    <title>单据中心</title>
<style>
.layui-table-page{
    height: 38px;
}
</style>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-card">
       <div class="layui-form layui-card-header layuiadmin-card-header-auto" style="font-size:12px;">
					
						<div class="layui-inline " >
							<label class="layui-form-label">就诊类型</label>
							<div class="layui-input-inline"  >
								<select name="medical.diagType">
									<option></option>
									<option value="1">住院</option>
									<option value="2">门诊</option>
									<option value="3">门特</option>
								</select>
							</div>
						</div>

						<div class="layui-inline ">
							<label class="layui-form-label">机审结果</label>
							<div class="layui-input-inline" >
								<select name="medical.sysStatus">
									<option></option>
									<option value="0">违规</option>
									<option value="2">正常</option>
									<option value="1">疑似违规</option>
								</select>
							</div>
						</div>

						<div class="layui-inline ">
							<label class="layui-form-label">医疗机构</label>
							<div class="layui-input-inline" >
								<!-- <select id="zyOrgName" name="medical.orgCode" lay-verify="" lay-search=" ">
                                    <option value="" disabled selected style='display:none;'>请选择</option>
                                </select> -->
                                <input id="getOrgName" name="orgCodeYl"/>
                                <input type="text" id="orgCode" name="medical.orgCode" style="display: none;" />
							</div>
							

						</div>
						<div class="layui-inline ">
							<label class="layui-form-label">结算日期</label>
							<div class="layui-input-inline" >
								<input type="text" id="finaTime" name="balanceDate"
									placeholder="请选择查询时间段" autocomplete="off" class="layui-input">
							</div>
						</div>

					<!-- <div class="layui-inline" id="xiala" style="margin-left: 20px;">
						<div onclick="showSearch()"
							style="color: #2284FF; cursor: pointer; z-index: 9999; display: inline-block;">
							<i class="layui-icon layui-icon-down"
								style="position: relative; margin-left: 10px;">更多</i>
						</div>
					</div> -->
					<div id="xiala" onclick="showSearch()" style="color:#2284FF;cursor:pointer;z-index:9999;display: inline-block;">更多<img style="margin: 0 auto; height: 6px;width: 8px;" src="<%=request.getContextPath()%>/images/main/xiala.png" /></div>
						
					<div class="layui-inline cxtjtop">
							<label class="layui-form-label">入院日期</label>
							<div class="layui-input-inline" >
								<input type="text" id="inhosDate" name="inhosDate"
									placeholder="请选择查询时间段" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-inline cxtjtop" >
							<label class="layui-form-label" >出院日期</label>
							<div class="layui-input-inline" >
								<input type="text" id="outhosDate" name="outhosDate"
									placeholder="请选择查询时间段" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-inline cxtjtop" >
							<label class="layui-form-label">违规类型</label>
							<div class="layui-input-inline" >
								<select id="ruleTypeOn" name="typeNoSerach" xm-select="select2" xm-select-direction="down" xm-select-radio lay-search>
									<option value="" disabled selected style='display:none;'>请选择类型</option>
								</select>
							</div>
						</div>
						<div class="layui-inline cxtjtop" >
							<label class="layui-form-label">诊断</label>
							<div class="layui-input-inline" >
								<input type="text" id="bqcx" name="medical.condition"
									placeholder="请输入诊断" autocomplete="off" class="layui-input">
							</div>
						</div>
						
						
						<div class="layui-inline cxtjtop" >
							<label class="layui-form-label">姓名</label>
							<div class="layui-input-inline" >
								<input type="text" id="name1" name="medical.name"
									placeholder="请输入姓名" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-inline cxtjtop">
							<label class="layui-form-label">身份证</label>
							<div class="layui-input-inline" >
								<input type="text" id="idcard1" name="medical.idcard"
									placeholder="请输入身份证" autocomplete="off" class="layui-input">
							</div>
						</div>
						
						<div class="layui-inline cxtjtop" >
							<label class="layui-form-label">病例号</label>
							<div class="layui-input-inline" >
								<input type="text" id="billingNo" name="medical.id"
									placeholder="请输入病例号" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-inline cxtjtop" >
							<label class="layui-form-label">项目编码</label>
							<div class="layui-input-inline" >
								<input type="text" id="itemCOdeOrName" name="itemCOdeOrName"
									placeholder="项目编码" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-inline cxtjtop" >
							<label class="layui-form-label">项目名称</label>
							<div class="layui-input-inline" >
								<input type="text" id="itemOnlyName" name="itemOnlyName"
									placeholder="项目名称" autocomplete="off" class="layui-input">
							</div>
						</div>

					<!-- <div class="layui-inline" id="shangla" style="margin-left: 20px;">
						<div onclick="hideSearch()"
							style="color: #2284FF; cursor: pointer; z-index: 9999; display: inline-block;">
							<i class="layui-icon layui-icon-up"
								style="position: relative; margin-left: 10px;">收起</i>
						</div>
					</div> -->
					<div id="shangla" onclick="hideSearch()" style="color:#2284FF;cursor:pointer;z-index:9999;display: inline-block;">收起<img style="margin: 0 auto; height: 6px;width: 8px;" src="<%=request.getContextPath()%>/images/main/shangla.png" /></div>
						
					<div class="layui-inline " style="margin-left:10px">
							<button id="medical-jpp-medical-search" stylename="search" class="layui-btn layuiadmin-btn-useradmin layui-btn-sm" lay-submit
								lay-filter="LAY-user-front-search">
								<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
							</button>
						</div>
					
				</div>

        <div class="layui-card-body">
            <table id="medicalTable" class="layui-hide" lay-filter="medicalTable"></table>
            <script type="text/html" id="table-useradmin-webuser">
                {{#if (!existsButton('cityorg-update')) { }}
                   <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="view"><i class="layui-icon layui-icon-list"></i>查看明细</a>
                {{# } }}
            </script>
        </div>
    </div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/documentCenter/documentCenter.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/common/orgDictSelect.js"></script>
<script type="text/javascript">
$(".cxtjtop").hide();
$("#shangla").hide();
function showSearch(){
	$("#shangla").show();
	$("#xiala").hide();
	$(".cxtjtop").show();
}
function hideSearch(){
	$(".cxtjtop").hide();
	$("#shangla").hide();
	$("#xiala").show();
}
</script>
</body>
</html>