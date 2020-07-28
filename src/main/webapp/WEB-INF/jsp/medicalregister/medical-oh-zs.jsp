<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" %>
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
#xldown >.layui-form-select dl {
 	max-height:100px; 
}
.layui-table-cell {
	height: 20px;
	line-height: 18px;
	font-size: 12px;
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
	height: 22px!important;
}
.layui-form-label {
	padding: 9px 3px;
}
.layui-input-block {
	margin-left: 90px;
}
.xm-select-parent .xm-select-title div.xm-select-label > span {
    position: relative;
    padding: 2px 3px;
    background-color: #009688;
    border-radius: 2px;
    color: #FFF;
    display: inline-block;
    line-height: 18px;
    height: 18px;
    margin: 2px 4px 2px 0;
    cursor: initial;
    user-select: none;
    font-size: 14px;
    -webkit-user-select: none;
}
</style>
<title>患者就诊记录</title>
</head>
<body>
	
	 <div class="layui-fluid"><!-- 宽度100% -->
		<div class="layui-row"><!-- 行 -->
			<div class="layui-card"><!-- 面板 -->
				<div class="layui-form layui-card-header layuiadmin-card-header-auto" style="font-size:12px">
					<div class="layui-form-item">
					
						<div class="layui-inline"">
							<label class="layui-form-label">姓名</label>
							<div class="layui-input-inline" >
								<input type="text" id="name1" name="medicalRegister.name"
									placeholder="请输入姓名" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">身份证</label>
							<div class="layui-input-inline" >
								<input type="text" id="idcard1" name="medicalRegister.idCard"
									placeholder="请输入身份证" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">医疗机构</label>
							<div class="layui-input-inline" >
								<select id="zyOrgName" name="medicalRegister.hosCode" lay-verify="" lay-search=" ">
                                    <option value="" disabled selected style='display:none;'>请选择</option>
                                </select>
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">入院日期</label>
							<div class="layui-input-inline" >
								<input type="text" id="inhosDate" name="inhosDate"
									placeholder="请选择时间段" autocomplete="off" class="layui-input">
							</div>
						</div>
						<button id="medical-jpp-medical-search" class="layui-btn layuiadmin-btn-useradmin layui-btn-sm" lay-submit
								lay-filter="LAY-user-front-search">
								<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
							</button>
					</div>
					<!-- <div class="layui-form-item" style="height:25px">
					
						<div class="layui-inline" style="margin-left:40px">
							<label class="layui-form-label">入院登记号</label>
							<div class="layui-input-block" >
								<input type="text" id="seeDocId" name="medicalRegister.seeDocId"
									placeholder="请输入入院登记号" autocomplete="off" class="layui-input">
							</div>
						</div>

						<div class="layui-inline">
							<label class="layui-form-label">终审结果</label>
							<div class="layui-input-block" >
								<select name="medicalRegister.finaStatus">
									<option></option>
									<option value="0">违规</option>
									<option value="2">未违规</option>
									<option value="dzs">待终审</option>
								</select>
							</div>
						</div>
						
						<div class="layui-inline" style="margin-left:60px">
							<button id="medical-jpp-medical-search" class="layui-btn layuiadmin-btn-useradmin layui-btn-sm" lay-submit
								lay-filter="LAY-user-front-search">
								<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
							</button>
						</div>
						<div class="layui-inline">
							<button id="medical-jpp-medical-zs-plsh" class="layui-btn layuiadmin-btn-useradmin layui-btn-sm" lay-submit
								lay-filter="LAY-user-front-search-allUpdate">
								<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>批量审核
							</button>
						</div>
						
						<div class="layui-inline">
							<button id="medical-jpp-qbdc" class="layui-btn layuiadmin-btn-useradmin layui-btn-sm" >
								<i class="layui-icon  layuiadmin-button-btn"></i>全部导出
							</button>
						</div>
					</div> -->
					<!-- <div class="layui-form-item" style="height:25px">
						
						<div class="layui-inline">
							<label class="layui-form-label">单据号</label>
							<div class="layui-input-block" >
								<input type="text" id="billingNo" name="medical.billingNo"
									placeholder="请输入单据号" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-inline" style="margin-left:60px">
							<button id="medical-jpp-medical-search" class="layui-btn layuiadmin-btn-useradmin layui-btn-sm" lay-submit
								lay-filter="LAY-user-front-search">
								<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
							</button>
						</div>
						<div class="layui-inline">
							<button id="medical-jpp-medical-zs-plsh" class="layui-btn layuiadmin-btn-useradmin layui-btn-sm" lay-submit
								lay-filter="LAY-user-front-search-allUpdate">
								<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>批量审核
							</button>
						</div>
						
						<div class="layui-inline">
							<button id="medical-jpp-qbdc" class="layui-btn layuiadmin-btn-useradmin layui-btn-sm" >
								<i class="layui-icon  layuiadmin-button-btn"></i>全部导出
							</button>
						</div>
						
					</div> -->
				</div>
 				<div class="layui-card-body">
 					<div class="layui-row layui-col-space10">
	 					<div class="layui-col-md8" >
	 					<div class="layui-card" style="min-height:230px;">
	 						<table id="userTable" class="layui-hide layui-table" lay-filter="userTable" lay-size="sm"  ></table>
	        				<script type="text/html" id="table-useradmin-webuser2">
					   		<a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="audit">稽核</a>
					   		<a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="publicShow">公示</a>
				   			</script>
				   		</div>
	 					</div>
	 					<div class="layui-col-md4" >
		 					<div class="layui-card" style="min-height:230px;border-width: 1px;border-style: solid;border-color: #e6e6e6;">
		 						<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief11">
								  <ul class="layui-tab-title" style="margin-top:0px;height:18px;border-bottom-width: 0px;background-color:#f2f2f2 ">
								    <li class="layui-this" style="font-size:13px;height:15px;line-height:15px">初审</li>
								    <li id='jhTab' style="font-size:13px;height:15px;line-height:15px">稽核</li>
								    <li style="font-size:13px;height:15px;line-height:15px">终审</li>
								  </ul>
								  <div class="layui-tab-content" style="margin-top:0px;padding-left: 0px;padding-right: 0px;">
								  	<div class="layui-tab-item layui-show">
								  		<div style="margin:5px 20px">
									  		<table id="sysVerify-cs" border="0" cellpadding="0" cellspacing="0"></table>
									  		<hr/>
									  		<lable id="rightStatus-cs">初审结果： </lable>
	    									<br/>
	    									<lable id="rightMoney-cs">扣除金额：</lable>
	    									<br/>
	    									<lable id="rightRemarks-cs">备注：</lable>
								  		</div>
								  	</div>
    								<div id="jhdiv" class="layui-tab-item">
    									<div style="margin:5px 20px">
    										<table id="sysVerify-jh" border="0" cellpadding="0" cellspacing="0"></table>
	    									<hr/>
	    									<lable id="rightStatus-jh">稽核结果：</lable>
	    									<lable id="xq-jh" style="color:red;margin-left:200px">详情</lable>
	    									<br/>
	    									<lable id="rightMoney-jh">扣除金额： </lable>
	    									<br/>
	    									<lable id="rightRemarks-jh">备注：</lable>
    									</div>
    								</div>
    								<div class="layui-tab-item">
    									<div style="margin:5px 20px">
    										<table id="sysVerify-zs" border="0" cellpadding="0" cellspacing="0"></table>
    										<hr/>
    										<lable id="rightStatus-zs">终审结果： </lable>
	    									<br/>
	    									<lable id="rightMoney-zs">扣除金额： </lable>
	    									<br/>
	    									<lable id="rightRemarks-zs">备注：</lable>
    									</div>
    								</div>
								  </div>
								</div> 
		 						
		 						
		 						
		 						<!-- <div class="layui-form layui-card-header " style="height:27px;font-size:13px;line-height:25px;background-color: #f2f2f2;">
		 							系统审核
		 						</div>
		 						<div class="layui-card-body">
		 							<table id="sysVerify" border="0" cellpadding="0" cellspacing="0">
		 								<tr>
		 									<td>1.限性别违规： 10</td>
		 								</tr>
		 								<tr>
		 									<td>2.限年龄违规： 5</td>
		 								</tr>
		 							</table>
		 						</div> -->
		 					</div>
	 					</div>
 					</div> 
  			 </div>
			</div>
		</div>
		
		<div class="layui-row"><!-- 行 -->
			<div  id="zsTj"  style="float: right;position: absolute;margin: -3px 0px;right: 20px;z-index:999">
				<span>病例审核：</span>
				<input type="radio"  name="violationStatus" value="2" >正常</input>
				<input type="radio"  style="margin-left:20px" name="violationStatus" value="0" >违规</input>
				<!-- <input type="radio"  style="margin-left:20px" name="violationStatus" value="2" >疑似违规</input> -->
				<!-- <button
					class="layui-btn layuiadmin-btn-useradmin layui-btn-sm"
					lay-submit lay-filter="LAY-user-front-search2">
					<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
				</button> -->
				<a id="medical-jpp-zs-medical-tj" class="layui-btn layui-btn-xs" lay-event="yes" ><i class="layui-icon layui-icon-ok"></i>提交</a>
			</div>
			<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief" style="margin-bottom: 0px;"><!-- 简洁选项卡 -->
  				<ul class="layui-tab-title" style="margin-top:0px;height:18px;border-bottom-width: 0px;" >
    				<li style="font-size:13px;height:15px;line-height:15px">就诊记录</li>
    				<li class="layui-this" style="font-size:13px;height:15px;line-height:15px">就诊明细记录</li>
  				</ul>
  				<div class="layui-tab-content" style="margin-top:-15px;padding-left: 0px;padding-right: 0px;">
  					<div class="layui-tab-item ">
  						<div class="layui-fluid" id="parent"><!-- 宽度100% -->
  							<div class="layui-card" id="child"><!-- 面板 -->
  								<h5 style="font-size:13px;font-weight: bold;">就诊信息</h5>
      							<hr style="margin-top:1px;margin-bottom:1px;">
      							<div>
      								<lable class="lb">姓名:</lable>
      								<lable class="ipt" id="name"></lable>
      								<lable class="lb">身份证号:</lable>
      								<lable class="ipt" id="idcard"></lable>	
      								<lable class="lb">年龄:</lable>
      								<lable class="ipt" id="age"></lable>
      								<lable class="lb" >性别:</lable>
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
      							
      							<div style="height:8px;">&nbsp;</div>
      							<h5 style="font-size:13px;font-weight: bold;">诊断信息</h5>
      							<hr style="margin-top:1px;margin-bottom:1px;">
      							<div>
      								<lable class="lb" >主要诊断:</lable>
      								<lable class="ipt" id="condition"></lable>
      								<lable class="lb" >次要诊断:</lable>
      								<lable class="ipt" id="outDiagnosisName"></lable>
      							</div>
      							
      							<div style="height:8px;">&nbsp;</div>
      							<h5 style="font-size:13px;font-weight: bold;">手术信息</h5>
      							<hr style="margin-top:1px;margin-bottom:1px;">
      							<div>
      								<lable class="lb">手术名称:</lable>
      								<lable class="ipt" id="operationName"></lable>
      								<lable class="lb">手术级别:</lable>
      								<lable class="ipt" id="operationLevel"></lable>
      								<lable class="lb" >切口类型:</lable>
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
						<div class="layui-fluid" style="padding: 10px 0px 0px 0px;"><!-- 宽度100% -->
							<div class="layui-row"><!-- 行 -->
							<div class="layui-card" ><!-- 面板 -->
									<div class="layui-form layui-card-header layuiadmin-card-header-auto" style="font-size:12px">
									<input type="hidden" name="medicalDetail.medicalId" id="medical_id_form" ></input><!-- 隐藏标签 -->
									<div class="layui-form-item" style="height: 25px">
										<div class="layui-inline">
											<label class="layui-form-label">违规类型</label>
											<div id="xldown" class="layui-input-block">
												<select id="ruleType" name="medicalDetail.ilegalType">
													<option value="" disabled selected style='display:none;'>请选择类型</option>
													
												</select>
											</div>
										</div>
										<div class="layui-inline">
											<label class="layui-form-label">是否违规</label>
											<div class="layui-input-block" style="width: 180px">
												<select name="medicalDetail.isIlegal" style="width:180px;" id="Myselect" xm-select="select1" xm-select-direction="down">
													<option></option>
													<option value="0">违规</option>
													<option value="2">正常</option>
													<option value="1">疑似违规</option>
												</select>
											</div>
										</div>

										<div class="layui-inline">
											<label class="layui-form-label" style="width:110px">项目编码/名称</label>
											<div class="layui-input-block" style="width: 210px;margin-left: 80px;">
												<input type="text" id="finaTime1111" name="itemNameOrCode"
													placeholder="请输入项目编码或名称" autocomplete="off"
													class="layui-input">
											</div>
										</div>

										<!-- <div class="layui-inline">
											<label class="layui-form-label">项目编码</label>
											<div class="layui-input-block">
												<input type="text" id="finaTimew" name="medicalDetail.itemCode"
													placeholder="请输入编码" autocomplete="off"
													class="layui-input">
											</div>
										</div> -->

										<div class="layui-inline">
											<button id="medical-jpp-zs-detail-search"
												class="layui-btn layuiadmin-btn-useradmin layui-btn-sm"
												lay-submit lay-filter="LAY-user-front-search2">
												<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
											</button>
										</div>
										<!-- <div style="margin-right: 5px;float: right;display: block;position: absolute;right: 0px;top: 13px;">查询结果：违规：1条;疑似违规：3条;总金额：196元;</div> -->
									</div>
								</div>
								<div class="layui-card-body"  style="margin-top:13px">
										<table id="dg" fitColumns="true" singleSelect="true"  pagination="true" ></table>
								</div>
							</div>
						</div>
						
						
						
						</div>
					</div>
  				</div>
			</div>    
		</div>
	 </div>
	<!--  easyui对话框 -->
	<div id="dlg" class="easyui-dialog" style="width:430px;height:360px;padding:10px 20px"
		closed="true" >
	
	<form id="fm" method="post">
	<table class="showtAudit" border="0" cellpadding="0" cellspacing="0" >
		<tr style="height:30px">
			<td><label style="width:200px">巡查审核结果:</label></td>
			<td><input name="medicalAudit.reviewResult" class="easyui-validatebox" ></td>
		</tr>
		<tr style="height:30px">
			<td><label style="width:200px">巡查时间:</label></td>
			<td><input name="medicalAudit.patrolTime" class="easyui-datetimebox" ></td>
		</tr>
		<tr style="height:30px">
			<td><label style="width:200px">巡查记录状态:</label></td>
			<td>
				<select id="cc" class="easyui-combobox" name="medicalAudit.patrolRecordState" >
				    <option value=""></option>
				    <option value="0">未提交</option>
				    <option value="1">提交</option>
				</select>
			</td>
		</tr>
		<tr style="height:30px">
			<td><label style="width:200px">审核意见:</label></td>
			<td><input name="medicalAudit.auditOpinion" class="easyui-validatebox"></td>
		</tr>
		<tr style="height:30px">
			<td><label style="width:200px">总体问题:</label></td>
			<td><input name="medicalAudit.overallProblem" class="easyui-validatebox" ></td>
		</tr>
		<tr style="height:30px">
			<td><label>巡查结论:</label></td>
			<td><input name="medicalAudit.patrolConclusion" class="easyui-validatebox" ></td>
		</tr>
		<tr style="height:30px">
			<td><label>数据创建时间:</label></td>
			<td><input name="medicalAudit.createDate" class="easyui-datetimebox"  ></td>
		</tr>
		<tr style="height:30px">
			<td><label>文件地址:</label></td>
			<td><input name="medicalAudit.fileurl" class="easyui-validatebox" ></td>
		</tr>
		<tr style="height:30px">
			<td><label>文件原始名称:</label></td>
			<td><input name="medicalAudit.filenameurl" class="easyui-validatebox" ></td>
		</tr>
		<tr style="height:30px">
			<td><label>稽核状态:</label></td>
			<td>
				<select id="cc" class="easyui-combobox" name="medicalAudit.aduitStatus" >
				    <option value=""></option>
				    <option value="0">违规</option>
				    <option value="2">未违规</option>
				</select>
			</td>
		</tr>
		
	</table>
		
	</form>
</div>


	<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/medicalregister/medical-oh-zs.js"></script>
	<script type="text/javascript">
	var formSelects = layui.formSelects;
	$(function(){ 
		
		formSelects.data('select1', 'local', {
		    arr: [
		        {"name": "违规", "value": 0,"selected":"selected"},
		        {"name": "疑似违规", "value": 1,"selected":"selected"},
		        {"name": "正常", "value": 2}
		    ]
		});
		var getValue = JSON.stringify(layui.formSelects.value('select1', 'val'));
		is_ilegal=getValue.substring(1,getValue.length-1);
		
		hideButtonStatic();//按钮授权
		/* easyui渲染 */
		$('#dg').datagrid({
			pageSize: 5,
			pageList: [5, 10, 15, 20],
			height:300,
			remoteSort:false,
			singleSelect:true,
			nowrap:false,
			fitColumns:false,
			autoRowHeight:false,
			onClickRow:tableRowClick,
			url:$WEB_ROOT_PATH+'/dhccApi/medicaldetail/medicalDetail/getList',
			queryParams:{'medicalDetail.medicalId': medical_id,'medicalDetail.isIlegal':is_ilegal,'comOrigin':'oh'},
			columns:[[
				/* {field:'ck',checkbox:true,width:40}, */
				{field:'id',title:'ID',hidden:true,width:80}
				,{field:'operate',title:'查看',align:'center',hidden:czHiden(jh,gs),width:$(this).width()*0.1,  
    				formatter:function(value, row, index){  
    					var str ='';
    					if(jh!=('-')){
    						str =str+ '<button href="#" onclick="audit('+index+')" style="color:red;">稽核信息</button>';
    					}
    					/* if(gs!=('-')){
    						str=str+'&#12288;<button href="#" onclick="audit1('+index+')" style="color:red;">公示信息</button>';
    					} */
        			//var str = '<button href="#" onclick="audit('+index+')" style="color:red;">稽核</button>&#12288;<button href="#" onclick="audit1('+index+')" style="color:red;">公示</button>';  
        			return str;  
				}}
	            ,{field:'aduitStatus', title: '稽核结果',width:150}
	            ,{field:'userStatus', title: '初审结果',width:150}
	            ,{field:'sysStatus', title: '机审结果',width:100,formatter:function(value){
	        	    var a =value;
	            	if(a=="1"){
	            		a="疑似违规";
	            	}else if(a=="0"){
	            		a="违规";
	            	}else if(a=="2"){
	            		a="正常";
	            	}else if(a==null || a=="null"){
	            		a="";
	            	}
	                return '<span >'+ a +'</span>'
	          	}}
				,{field:'itemCode', title: '项目编号',width:150}
	            ,{field:'itemName', title: '项目名称',width:150}
	            ,{field:'drugType', title: '药品类别',width:80}
	            ,{field:'itemCost', title: '项目金额'}
	            ,{field:'sumAmount', title: '总金额'}
	            ,{field:'itemNum', title: '项目数量'}
	            ,{field:'itemPrice', title: '项目单价'}
	            ,{field:'itemStandard', title: '项目规格',width:80}
	            ,{field:'doseForm', title: '剂型'}
	            ,{field:'limitPrice', title: '限价金额'}
	            ,{field:'partialOrdination', title: '部分统筹'}
	            ,{field:'partialPayment', title: '部分自付'}
	            ,{field:'recipelId', title: '门诊处方编号'}
	            ,{field:'selfPayAmount', title: '自付金额'}
	            ,{field:'singleDose', title: '单次用量'}
	            ,{field:'takeFrequence', title: '服用频次'}
	            ,{field:'useDay', title: '用药天数'}
	            ,{field:'applyPayAmount', title: '报销金额'}
	            ,{field:'ilegalType', title: '违规类型'}
	            ,{field:'applyPayLevel', title: '报销级别',formatter: function(value){
	        	    var a =value;
	            	if(a=="1"){
	            		a="甲类";
	            	}else if(a=="2"){
	            		a="乙类";
	            	}else if(a=="3"){
	            		a="丙类";
	            	}else if(a==null || a=="null"){
	            		a="";
	            	}
	                return '<span >'+ a +'</span>'
	          }}
	            /*,{field:'billingNo', title: '收费单据号'}*/
	            ,{field:'chargeType', title: '收费类别'}
	            ,{field:'deliverWay',title: '给药途径'}
	            ,{field:'doseUnit', title: '用量单位'}
	            ,{field:'feeCreateDate', title: '费用发生时间',width:100}
	            ,{field:'fullOrdination', title: '全额统筹'}
	            ,{field:'fullPayment', title: '全额自付'}
	            ,{field:'isInsuranceProject', title: '是否医保项目',formatter: function(value){
	        	    var a =value;
	            	if(a=="0"){
	            		a="否";
	            	}else if(a=="1"){
	            		a="是"
	            	}else if(a==null || a=="null"){
	            		a="";
	            	}
	                return '<span >'+ a +'</span>'
	          }}
			]],
			view: detailview,
			onLoadSuccess: function(data){
				for(var i=0;i<data.rows.length;i++){
					$(this).datagrid('getExpander', i).html(i+1);
				}
				
			},
			detailFormatter:function(index,row){
				return '<div class="ddv"></div>';
			},
			onExpandRow: function(index,row){
				var ddv = $(this).datagrid('getRowDetail',index).find('div.ddv');
				
	            $("#ExpandRow").val(index);
				ddv.panel({
					border:true,
					cache:true,
					href:$WEB_ROOT_PATH+'/medicalregister/showFormZs?index='+index,
					onLoad:function(){
						$('#dg').datagrid('fixDetailRowHeight',index);
						$('#dg').datagrid('selectRow',index);
						
						var table=$('#dg').datagrid('getRowDetail',index).find('table.showtable');
						
						//动态拼接
						var url=$WEB_ROOT_PATH+"/dhccApi/medicalregister/medicalRegister/findByMId";
						var filed={"violationDetailOH.medicalId":medical_id,"violationDetailOH.medicalDetailId":row.id};
						$.post(url,filed,function(result){
							//console.log(result);
							//开始拼接table
							var violationDetails=result.violationDetailOHs;
							if(violationDetails){
							
							if(violationDetails.length>0){
								for(var i=0;i<violationDetails.length;i++){
									var tr=document.createElement("tr");
									var td=document.createElement("td");
									var typeNo=parseInt(violationDetails[i].typeNo);
									if(violationDetails[i].typeName){
										 td.innerHTML=i+1+"."+violationDetails[i].typeName;
									}else{
										td.innerHTML=i+1+"."+'其他';
									}
									/* switch(typeNo) {
								     case 10:
								    	 td.innerHTML=i+1+"."+'限性别用药或诊疗项目';
								        break;
								     case 20:
								    	 td.innerHTML=i+1+"."+'限儿童用药或诊疗项目';
								        break;
								     case 30:
								    	 td.innerHTML=i+1+"."+'重复用药审核';
								        break;
								     case 50:
								    	 td.innerHTML=i+1+"."+'药物配伍禁忌审核';
								        break;
								     case 60:
								    	 td.innerHTML=i+1+"."+'肝功能异常用药提醒';
								        break;
								     case 70:
								    	 td.innerHTML=i+1+"."+'肾功能异常用药提醒';
								        break;
								     case 80:
								    	 td.innerHTML=i+1+"."+'限工商保险审核';
								        break;
								     case 90:
								    	 td.innerHTML=i+1+"."+'限生育保险药品审核';
								        break;
								     case 100:
								    	 td.innerHTML=i+1+"."+'限适应症药品审核';
								        break;
								     case 110:
								    	 td.innerHTML=i+1+"."+'限二线用药药品审核';
								        break;
								     case 120:
								    	 td.innerHTML=i+1+"."+'限耐药菌引起的感染类审核';
								        break;
								     case 130:
								    	 td.innerHTML=i+1+"."+'限特定疾病药品审核';
								        break;
								     case 140:
								    	 td.innerHTML=i+1+"."+'限医院等级用药';
								        break;
								     case 150:
								    	 td.innerHTML=i+1+"."+'限抢救用药';
								        break;
								     case 160:
								    	 td.innerHTML=i+1+"."+'诊疗项目收费冲突审核';
								        break;
								     case 170:
								    	 td.innerHTML=i+1+"."+'分解住院或住院天数异常';
								        break;
								     case 180:
								    	 td.innerHTML=i+1+"."+'超标准收费审核';
								        break;
								     case 190:
								    	 td.innerHTML=i+1+"."+'项目超限次审核';
								        break;
								     case 200:
								    	 td.innerHTML=i+1+"."+'药品超每日最大用量审核';
								        break;
								     case 210:
								    	 td.innerHTML=i+1+"."+'中药饮片单味审核';
								        break;
								     case 220:
								    	 td.innerHTML=i+1+"."+'限禁忌症用药';
								        break;
								     case 230:
								    	 td.innerHTML=i+1+"."+'限禁忌症用药(疾病相关)';
								        break;
								     default:
								    	 td.innerHTML=i+1+"."+'其他';
									} */
									//td.innerHTML=i+1+"."+violationDetails[i].typeName;
									td.setAttribute("style","width: 280px;height:28px");
									var td2=document.createElement("td");
									td2.innerHTML='违规原因：'+violationDetails[i].returnDesc;
									td2.setAttribute("colspan","2");
									tr.appendChild(td);
									tr.appendChild(td2);
									table.append(tr);
								}
									$('#dg').datagrid('fixDetailRowHeight',index);//固定详情页面高度
							}
							}
							//详情框赋值
							var url=$WEB_ROOT_PATH+"/dhccApi/medicalregister/medicalRegister/findCostVerify";
							
							$.post(url,{"costVerifyOH.medicalId":medical_id,"costVerifyOH.costId":row.id},function(result){
								//赋值
								$('#dg').datagrid('getRowDetail',index).find('form').form('load',result);
							});
						});
					
					}
					
				});
				
				$('#dg').datagrid('fixDetailRowHeight',index);//固定详情页面高度
			}
		});
	   $("#dg").datagrid('resize'); 

	   $('.datagrid-header .datagrid-cell span ').css('font-size','10px');
	   
	});
		layui.use(['element','form','table'], function(){
		   var element = layui.element;
		   var form=layui.form;
		   var table=layui.table;
		   element.on('tab(docDemoTabBrief)', function(data){
			  // alert(data.index);
			   findMedicalDate(medical_id);
		   });
		   
		});
	</script>
	<script>
		var medicalId;
		function child(obj) {
			var medicalExamine = JSON.parse(obj);
			medicalId = medicalExamine.id;
		}
		
		
	</script>

</body>
</html>