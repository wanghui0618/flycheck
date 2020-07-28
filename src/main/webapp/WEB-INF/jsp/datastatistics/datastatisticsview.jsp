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

<title>明细单</title>
<style>
.lb {
	margin-left: 5px; 
	width: 90px;
	font-size: 13px;
	font-weight: normal;
	margin-top:5px;
	display: inline-block;
	text-align: right;
	/* border-style: solid; */
}
.lb1 {
	margin-left: 50px; 
	width: 125px;
	font-size: 13px;
	font-weight: normal;
	margin-top:5px;
	display: inline-block;
	text-align: right;
	/* border-style: solid; */
}
.ipt {
    overflow: hidden;
    text-overflow:ellipsis;
    white-space: nowrap;
    margin-left: 5px;
	width: 170px;
	font-size: 13px;
	font-weight: normal;
	margin-top:5px;
	display: inline-block;
	text-align: left;
	/* border-style: solid;  */
}

#div{
    margin-top: 3.5px;
    margin-bottom:3.5px;

}
</style>
</head>
<body>

<!-- <div class="layui-form" lay-filter="layuiadmin-form-useradmin" id="layuiadmin-form-useradmin" style="padding: 0 0 0 0;"> -->
  				<div class="layui-fluid" id="parent"><!-- 宽度100% -->
  							<div class="layui-card" id="child"><!-- 面板 -->
  								<!-- <h5 style="font-size:13px;font-weight: bold;">就诊登记信息</h5> -->
      							<hr style="margin-top:1px;margin-bottom:1px;">
      							<div id="div">
      								<lable class="lb1">项目编号:</lable>
      								<lable class="ipt" id="itemCode"></lable>
      								<lable class="lb">项目名称:</lable>
      								<lable class="ipt" id="itemName"></lable>
      								<lable class="lb">项目规格:</lable>
      								<lable class="ipt" id="itemStandard"></lable>
      								<lable class="lb">药品类别:</lable>
      								<lable class="ipt" id="drugType"></lable>
      							</div>
      							<div id="div">
      								<lable class="lb1">项目单价:</lable>
      								<lable class="ipt" id="itemPrice"></lable>
      								<lable class="lb">项目数量:</lable>
      								<lable class="ipt" id="itemNum"></lable>
      								<lable class="lb">项目金额:</lable>
      								<lable class="ipt" id="itemCost"></lable>
      								<lable class="lb">收费单据号:</lable>
      								<lable class="ipt" id="billingNo"></lable>
      							</div>
      							<div id="div">
      								<lable class="lb1">报销级别:</lable>
      								<lable class="ipt" id="applyPayLevel"></lable>
      								<lable class="lb">是否医保项目:</lable>
      								<lable class="ipt" id="isInsuranceProject"></lable>
      								<lable class="lb">剂型:</lable>
      								<lable class="ipt" id="doseForm"></lable>
      								<lable class="lb">费用发生时间:</lable>
      								<lable class="ipt" id="feeCreateDate"></lable>
      							</div>
      							<div id="div">
      								<lable class="lb1">收费类别:</lable>
      								<lable class="ipt" id="chargeType"></lable>
      								<lable class="lb">用药天数:</lable>
      								<lable class="ipt" id="useDay"></lable>
      								<lable class="lb">单次用量:</lable>
      								<lable class="ipt" id="singleDose"></lable>
      								<lable class="lb">用量单位:</lable>
      								<lable class="ipt" id="doseUnit"></lable>
      							</div>
      							<div id="div">
      								<lable class="lb1">给药途径:</lable>
      								<lable class="ipt" id="deliverWay"></lable>
      								<lable class="lb">服用频次:</lable>
      								<lable class="ipt" id="takeFrequence"></lable>
      								<lable class="lb">总金额:</lable>
      								<lable class="ipt" id="sumAmount"></lable>
      								<lable class="lb">报销金额:</lable>
      								<lable class="ipt" id="applyPayAmount"></lable>
      							</div>
      							<div id="div">
      								<lable class="lb1">自付金额:</lable>
      								<lable class="ipt" id="selfPayAmount"></lable>
      								<lable class="lb">全额统筹:</lable>
      								<lable class="ipt" id="fullOrdination"></lable>
      								<lable class="lb">部分统筹:</lable>
      								<lable class="ipt" id="partialOrdination"></lable>
      								<lable class="lb">部分自付:</lable>
      								<lable class="ipt" id="partialPayment"></lable>
      							</div>
      							<div id="div">
      								<lable class="lb1">全额自付:</lable>
      								<lable class="ipt" id="fullPayment"></lable>
      								<lable class="lb">限价金额:</lable>
      								<lable class="ipt" id="limitPrice"></lable>
      								<lable class="lb">是否违规:</lable>
      								<lable class="ipt" id="isIlegal"></lable>
      								<lable class="lb">违规类型:</lable>
      								<lable class="ipt" id="ilegalType"></lable>
      							</div>
      							<div id="div">
      								<lable class="lb1">门诊处方ID:</lable>
      								<lable class="ipt" id="recipelId"></lable>
      								<lable class="lb">住院医嘱ID:</lable>
      								<lable class="ipt" id="adviceId"></lable>
      								<lable class="lb">项目类别:</lable>
      								<lable class="ipt" id="projectType"></lable>
      								<lable class="lb">规格:</lable>
      								<lable class="ipt" id="specs"></lable>
      							</div>
      							<div id="div">
      								<lable class="lb1">取药总量:</lable>
      								<lable class="ipt" id="totalDrugIntake"></lable>
      								<lable class="lb">药量天数:</lable>
      								<lable class="ipt" id="dosageDays"></lable>
      								<lable class="lb" style="width:120px;">是否出院带药处方:</lable>
      								<lable class="ipt" id="prescriptionDischarge"></lable>

      							</div>
      							<div id="div">
      							    <lable class="lb1">医疗机构编码:</lable>
      								<lable class="ipt" id="medicalInsCode"></lable>
      								<lable class="lb">医疗机构名称:</lable>
      								<lable class="ipt" id="medicalInsName"></lable>
      								<lable class="lb">药品计量单位:</lable>
      								<lable class="ipt" id="drugMeaUnit"></lable>
      								<lable class="lb">收费计量单位:</lable>
      								<lable class="ipt" id="feeMeaUnit"></lable>
      							</div>
      							<div id="div">
      							    <lable class="lb1">科室编码:</lable>
      								<lable class="ipt" id="departCode"></lable>
      								<lable class="lb">科室名称:</lable>
      								<lable class="ipt" id="departName"></lable>
      								<lable class="lb">医师编码:</lable>
      								<lable class="ipt" id="docCode"></lable>
      								<lable class="lb">医师姓名:</lable>
      								<lable class="ipt" id="docName"></lable>
      							</div>
      							<div id="div">
      							    <lable class="lb1">医院的医疗服务名称:</lable>
      								<lable class="ipt" id="hosServiceName"></lable>
      								<lable class="lb">结算日期:</lable>
      								<lable class="ipt" id="balanceDate"></lable>
      								<lable class="lb">医保金额:</lable>
      								<lable class="ipt" id="insCost"></lable>
      								<lable class="lb">基金支付:</lable>
      								<lable class="ipt" id="fundCost"></lable>
      							</div>
      							<div id="div">
      							    <lable class="lb1">非医保金额:</lable>
      								<lable class="ipt" id="notInsCost"></lable>
      								<lable class="lb">药品用量:</lable>
      								<lable class="ipt" id="durgDosage"></lable>
      								<lable class="lb">项目单位:</lable>
      								<lable class="ipt" id="itemUnit"></lable>
      								<lable class="lb">医嘱组号:</lable>
      								<lable class="ipt" id="adviceGroup"></lable>
      							</div>
      							<div id="div">
      							    <lable class="lb1">医嘱顺序号:</lable>
      								<lable class="ipt" id="adviceOrder"></lable>
      								<lable class="lb">自付比例:</lable>
      								<lable class="ipt" id="selfPayRate"></lable>
      								<lable class="lb">创建日期:</lable>
      								<lable class="ipt" id="createDate"></lable>
      							</div>
      							<br>
  							</div>
<!--   </div> -->

 	   </div>
  <script>

  <%-- layui.config({
	    base: '<%=request.getContextPath() %>/plugins/layui/layuiadmin/'
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'form'], function(){})
   --%>

  layui.config({
	    base: '<%=request.getContextPath() %>/plugins/layui/layuiadmin/'
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'form',], function(){
				})



  function child(obj){
	  var medicalDetail = JSON.parse(obj);
	  $("#id").val(medicalDetail["id"]);

	  if(medicalDetail.prescriptionDischarge=='0'){
		  medicalDetail.prescriptionDischarge='非外配处方'
	  }else if(medicalDetail.prescriptionDischarge=='1'){
		  medicalDetail.prescriptionDischarge='外配处方'
	  }

	  if(medicalDetail.isIlegal=='0'){
		  medicalDetail.isIlegal='未违规'
	  }else if(medicalDetail.isIlegal=='1'){
		  medicalDetail.isIlegal='违规'
	  }else if(medicalDetail.isIlegal=='2'){
		  medicalDetail.isIlegal='疑似违规'
	  }

	  if(medicalDetail.isInsuranceProject=='0'){
		  medicalDetail.isInsuranceProject='否'
	  }else if(medicalDetail.isInsuranceProject=='1'){
		  medicalDetail.isInsuranceProject='是'
	  }

	  for (var index in medicalDetail){
          if(index=="itemName"){//显示有省略号的内容
              $("#"+index).attr("title",medicalDetail[index]);//修改title值
          }

          $("#"+index).html(medicalDetail[index]);
	  }
  }
  </script>
</body>
</html>