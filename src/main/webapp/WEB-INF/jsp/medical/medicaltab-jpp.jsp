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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/layui/css/layui.css"
	media="all">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/style/admin.css"
	media="all">

<title>明细单</title>
<style>
.itm {
	margin-top: 5px;
	height: 20px;
}

.lb {
	margin-left: 30px; 
	width: 100px;
	font-weight: bold;
	color:#636363;
	margin-top:10px;
	display: inline-block;
	text-align: right;
	/* border-style: solid; */
}
.ipt {
	margin-left: 5px;
	width: 180px;
	color:#666666;
	font-weight: normal;
	margin-top:10px;
	display: inline-block;
	text-align: left;
	/* border-style: solid;  */
}

.whool {
	margin: 0px 0px;
	position: absolute;
	width: 100%;
	height: 100%
}

#parent {
	position: relative;
	height: 375px;
	margin-left:-10px;
	margin-top:10px;
	/* 高度根据需求自行设定 */
}

#child {
	position: absolute;
	width: 100%;
	left: 0;
	top: 0;
	right: 0;
	bottom: 0; /* left ,top,right,bottom都为0，充满真个页面 overflow-y : auto; */
	overflow-y : auto;
	overflow-x: auto;

	/* 设置Y轴出现滚动条，X轴隐藏 */
}
</style>
</head>
<body >
<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief" style="margin-top:0px " >
  <ul class="layui-tab-title">
    <li class="layui-this">就诊信息</li>
    <li>费用明细</li>
  </ul>
  <div class="layui-tab-content" style="background:#FFF;padding: 0px 0px;">

			<div class="layui-tab-item layui-show" style="padding: 0px 0px;height:100%;">
				<div class="layui-container" >
					<div class="layui-row" >
    					<div class="layui-col-md8" id="parent" >
      						<div class="grid-demo grid-demo-bg1" id="child" style="font-weight: bold;color:black;">
      							就诊信息<br>
      							<hr style="margin-top:5px">
      							<div>
      								<lable class="lb">唯一就诊号:</lable>
      								<lable class="ipt" id="id"></lable>
      								<lable class="lb">医&#12288;保&#12288;号:</lable>
      								<lable class="ipt" id="sscno"></lable>
      								
      							</div>
      							<div>
      								<lable class="lb">参保人姓名:</lable>
      								<lable class="ipt" id="name"></lable>
      								<lable class="lb">人&nbsp;员&nbsp;类&nbsp;别:</lable>
      								<lable class="ipt" id="crowdType"></lable>
      								
      							</div>
      							<div>
      								<lable class="lb">就&nbsp;医&nbsp;方&nbsp;式:</lable>
      								<lable class="ipt" id="diagType"></lable>
      								<lable class="lb">参&nbsp;保&nbsp;类&nbsp;型:</lable>
      								<lable class="ipt" id="medicalType"></lable>
      							</div>
      							<div>
      								<lable class="lb">年&#12288;&#12288;&#12288;龄:</lable>
      								<lable class="ipt" id="age"></lable>
      								<lable class="lb" >性&#12288;&#12288;&#12288;别:</lable>
      								<lable class="ipt" id="sex"></lable>
      								
      							</div>
      							<div>
      								<lable class="lb">住&nbsp;院&nbsp;天&nbsp;数:</lable>
      								<lable class="ipt" id="stayLength"></lable>
      								<lable class="lb">入&nbsp;院&nbsp;日&nbsp;期:</lable>
      								<lable class="ipt" id="inhosDate"></lable>
      					        </div>
      					        <div>	
      								<lable class="lb">出&nbsp;院&nbsp;日&nbsp;期:</lable>
      								<lable class="ipt" id="outhosDate"></lable>
      								<!-- <lable class="lb">结算日起:</lable>
      								<lable class="ipt">2016-08-19</lable> -->
      							</div>
      							<div>
      								<lable class="lb">医&nbsp;院&nbsp;名&nbsp;称:</lable>
      								<lable class="ipt" id="orgName"></lable>
      								<lable class="lb">科&nbsp;室&nbsp;名&nbsp;称:</lable>
      								<lable class="ipt" id="departName"></lable>
      						    </div>
      							<div>
      								<lable class="lb">主&nbsp;治&nbsp;医&nbsp;师:</lable>
      								<lable class="ipt" id="doctorName"></lable>
      								<lable class="lb">出&nbsp;院&nbsp;方&nbsp;式:</lable>
      								<lable class="ipt" id="dischargeState"></lable>
      							</div>
      							<div>
      								
      								<lable class="lb">诊&#12288;&#12288;&#12288;断:</lable>
      								<lable class="ipt" id="outDiagnosisName"></lable>
      								<lable class="lb">手&#12288;&#12288;&#12288;术:</lable>
      								<lable class="ipt" id="operationName"></lable>
      							</div>
      							<div>
      								<lable class="lb">总&#12288;费&#12288;用:</lable>
      								<lable class="ipt" id="totalCost"></lable>
      								<lable class="lb">医保报销金额:</lable>
      								<lable class="ipt" id="basicCostR"></lable>
      							</div>
      							<div>
      								<lable class="lb">大病报销金额:</lable>
      								<lable class="ipt" id="largeCostR"></lable>
      								<lable class="lb">机&nbsp;审&nbsp;状&nbsp;态:</lable>
      								<lable class="ipt" id="sysStatus"></lable>
      							</div>
      							<div>
      								
      								<lable class="lb">人工审核状态:</lable>
      								<lable class="ipt" id="userStatus"></lable>
      								<br>
      								<lable class="lb">终&nbsp;审&nbsp;结&nbsp;果:</lable>
      								<lable class="ipt" id="finaStatus" style="width:500px;word-break:break-all;"></lable>
      							</div>
      							<br>
      							诊断信息<br>
      							<hr style="margin-top:5px">
      							<div>
      								<lable class="lb" >主&nbsp;要&nbsp;诊&nbsp;断:</lable>
      								<lable class="ipt" id="outDiagnosisName"></lable>
      								
      							</div>
      							<div>
      								<lable class="lb">次&nbsp;要&nbsp;诊&nbsp;断:</lable>
      								<lable class="ipt" id="outDiagnosisName"></lable>
      								
      							</div>
      							<br>
      							<br>
      							<br>
      							手术信息<br>
      							<hr style="margin-top:5px">
      							<div>
      								<lable class="lb">手&nbsp;术&nbsp;级&nbsp;别:</lable>
      								<lable class="ipt" id="operationLevel"></lable>
      								<lable class="lb" >切&nbsp;口&nbsp;类&nbsp;型:</lable>
      								<lable class="ipt" id="incisionType"></lable>
      								
      							</div>
      							<div>
      								<lable class="lb">手术开始时间:</lable>
      								<lable class="ipt" id="operBeginTime"></lable>
      								<lable class="lb">手术结束时间:</lable>
      								<lable class="ipt" id="operEndTime"></lable>
      								
      							</div>
      							<div>
      								<lable class="lb">麻&nbsp;醉&nbsp;方&nbsp;式:</lable>
      								<lable class="ipt" id="narcosisWay"></lable>
      								<lable class="lb">愈&nbsp;合&nbsp;状&nbsp;态:</lable>
      								<lable class="ipt" id="healingLevel"></lable>
      								
      							</div>
      							<div>
      								<lable class="lb">手&nbsp;术&nbsp;名&nbsp;称:</lable>
      								<lable class="ipt" id="operationName"></lable>
      								
      								
      							</div>
      							<div>
      								<lable class="lb">手&nbsp;术&nbsp;人&nbsp;员:</lable>
      								<lable class="ipt" id="operationDoc"></lable>
      								<lable class="lb">麻&#12288;醉&#12288;者:</lable>
      								<lable class="ipt" id="narcosisDoc"></lable>
      								
      							</div>
      							<br>
      						</div>
    					</div>
    					<div class="layui-col-md4" >
     					 	<div class="grid-demo" >
     					 		
     					 		
     					 		
     					 		
     					 		<form class="layui-form" action="" id="layuiadmin-form-useradmin2" lay-filter="layuiadmin-form-useradmin2">
     					 			 <div class="layui-form-item"  >
   				 						<label class="layui-form-label">审核结果:</label>
    									<div class="layui-input-block">
      										<input type="radio" id="status3" name="medicalExamine.status" value="0" title="未违规" checked class="layui-form-item" >
      										<input type="radio" id="status3"  name="medicalExamine.status" value="1" title="违规"  class="layui-form-item" >
   										 </div>
  									 </div>
  									 
  									 <!--  <div class="layui-form-item" style="margin-top:15px">
    								  	<label class="layui-form-label">责任单位:</label>
    									<div class="layui-input-block">
      										<input type="checkbox" name="like[write]" title="参保人" lay-skin="primary">
      										<input type="checkbox" name="like[read]" title="医生" checked lay-skin="primary">
     										<input type="checkbox" name="like[dai]" title="医院" lay-skin="primary">
    									</div>
  									  </div> -->
  									  
  									  <div class="layui-form-item layui-form-text" style="margin-top:15px">
    								  <label class="layui-form-label">终审意见:</label>
   			 							<div class="layui-input-block">
      										<textarea placeholder="请输入内容" class="layui-textarea" name="medicalExamine.comments" class="layui-form-item"></textarea>
      										
    									</div>
  									  </div>
  									  
  									   <div class="layui-form-item" style="margin-top:30px">
    									<div class="layui-input-block">
      										<button class='layui-btn layui-btn-sm layui-btn-normal' lay-submit lay-filter="LAY-user-front-save2">保存</button>
      										<!-- <button type="reset" class="layui-btn layui-btn-primary">重置</button> -->
    									</div>
  									   </div>
  									   
     					 		</form>
     					 	</div>
    					</div>
  					</div>
  				</div>
				
				
			</div>
			<div class="layui-tab-item">
				<div class="layui-container" >
					<div class="layui-row" >
    					<div class="layui-col-md8" id="parent" style="margin-left:-25px;margin-top:-12px">
      						<div class="grid-demo grid-demo-bg1" id="child" >
      							<div class="layui-fluid">
    								<div class="layui-card" style="padding:0px 0px;margin:0px 0px">
    									<div class="layui-card-body" style="padding:0px 0px;margin:0px 0px">
        									<table id="medicalTable" class="layui-hide jpptable" lay-filter="medicalTable"></table>
        								</div>
    								</div>
    							</div>
    							<!-- <iframe src="/piccbid/medical/medicalDetailInfo" class="whool" scrolling="auto";frameborder="0" ;style="width:100%"></iframe> -->
    				        </div>
    				     </div>   
    					<div class="layui-col-md4" >
    						<div class="grid-demo" >
    						 <div class="layui-tab-item layui-show" style="margin-left:35px">
	              					<h5>机审结果：<button class='layui-btn layui-btn-xs layui-btn-danger' name="medical.sysStatus" id="sysStatus" value="0" title="未违规">违规</button></h5>
	              					<h5 style="margin-top:5px">1、药品性别违规，当前：男性，限制：女性</h5>
	              			  <hr/>
	              			  </div>
    						 	<form class="layui-form" id="MedicalCost" lay-filter="MedicalCost" action="#" >
    						 		<input type="hidden" name="medicalCostVerify.medicalId" id="medicalId">
									<input type="hidden" name="medicalCostVerify.costId" id="costId">
     					 			 <div class="layui-form-item"  >
   				 						<label class="layui-form-label">是否违规:</label>
    									<!-- <div class="layui-input-block">
      										<input type="radio" id="violationStatus" name="medicalCostVerify.violationStatus"  value="1" title="违规">
      										<input type="radio" id="violationStatus" name="medicalCostVerify.violationStatus"  value="0" title="未违规" checked>
   										 </div> -->
   										 <div class="layui-input-block">
      										<input type="radio" id="violationStatus" name="medicalCostVerify.violationStatus" value="0" title="未违规" checked class="layui-form-item" >
      										<input type="radio" id="violationStatus"  name="medicalCostVerify.violationStatus" value="1" title="违规"  class="layui-form-item" >
   										 </div>
  									 </div>
     					 			 <div class="layui-form-item"  >
   				 						<label class="layui-form-label">预扣金额:</label>
    									<div class="layui-input-block">
      										<input type="text" class="layui-input" id="withholdingAmount" name="medicalCostVerify.withholdingAmount" >
   										 </div>
  									 </div>
     					 			 <div class="layui-form-item"  >
   				 						<label class="layui-form-label">预扣数量:</label>
    									<div class="layui-input-block">
      										<input type="text" class="layui-input" id="withholdingQuantity" name="medicalCostVerify.withholdingQuantity" >
   										 </div>
  									 </div>
  									 
  									 
  									  
  									  <div class="layui-form-item layui-form-text" style="margin-top:15px">
    								  <label class="layui-form-label">扣除原因:</label>
   			 							<div class="layui-input-block">
      										<textarea id="deductions" name="medicalCostVerify.deductions" placeholder="请输入内容" class="layui-textarea"></textarea>
    									</div>
  									  </div>
  									  
  									  <!--  <div class="layui-form-item" style="margin-top:20px">
    									<div class="layui-input-block">
      										
      										
    									</div>
  									   </div> -->
  									   
     					 		</form>
     					 		<div style="text-align:center">
     					 			<button class='layui-btn' onclick="showdate1()" lay-filter="medicalCostSave">保存</button>
     					 			<button type="reset" onclick="restform()" class="layui-btn layui-btn-primary">重置</button>
     					 		</div>
    						</div>
    					</div>
    				</div>
    			</div>
			</div>
			</div>
</div>

<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/medical/medicaltab-jpp.js"></script>
<script>
 function child(obj) {
	var medicalDetail = JSON.parse(obj);
	billing_no=medicalDetail.billingNo;
	medical_id=medicalDetail.id;
	findMedicalDate(medical_id)
	$("#medicalId").val(medical_id);
	
}
 //获取4表联查的数据
 function findMedicalDate(medical_id){
	 var url=$WEB_ROOT_PATH+"/dhccApi/medical/medical/findMedicalDate";
	 $.post(url,{"medicalVoJpp.id":medical_id},function(result){
		 //console.log(result);
		 var medicalVoJpp=result.medicalVoJpp;
		 for ( var index in medicalVoJpp) {
				$("#" + index).html(medicalVoJpp[index]);
		 }
		 //特殊处理
		 if(medicalVoJpp.sysStatus=='0'){
			 $("#sysStatus").html("未违规");
		 }else if(medicalVoJpp.sysStatus=='1'){
			 $("#sysStatus").html("违规");
		 }else if(medicalVoJpp.sysStatus=='2'){
			 $("#sysStatus").html("疑似违规");
		 }
		 if(medicalVoJpp.userStatus=='0'){
			 $("#userStatus").html("未违规");
		 }else if(medicalVoJpp.userStatus=='1'){
			 $("#userStatus").html("违规");
		 }else if(medicalVoJpp.userStatus=='2'){
			 $("#userStatus").html("疑似违规");
		 }
		 if(medicalVoJpp.finaStatus=='0'){
			 $("#finaStatus").html("未违规");
		 }else if(medicalVoJpp.finaStatus=='1'){
			 $("#finaStatus").html("违规");
		 }else if(medicalVoJpp.finaStatus=='2'){
			 $("#finaStatus").html("疑似违规");
		 }
		 
	 });
 }
</script>
</body>
</html>