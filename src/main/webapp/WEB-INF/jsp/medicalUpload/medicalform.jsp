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
<title>患者就诊记录</title>
<style>
.layui-form-item{
	margin-left:20px;
}
</style>
</head>
<body>
<div class="layui-form" lay-filter="layuiadmin-form-useradmin" id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">
    <input type="hidden" id="id" name="medical.id" >
   		<div class="layui-form-item">
      	<label class="layui-form-label">姓名</label>
      	<div class="layui-input-inline">
        	<input type="text" id="name" name="medical.name" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
      
      	<label class="layui-form-label">性别</label>
      	<div class="layui-input-inline">
      		 <select   id="sex" name="medical.sex" lay-filter="ifType">
      		            <option value="">请选择</option>
      					<option value="男">男</option>
      					<option value="女">女</option>
        </select>
        	<!-- <input type="text" id="sex" name="medical.sex"  placeholder="请输入" autocomplete="off" class="layui-input"> -->
     	 </div>
     	 <label class="layui-form-label">年龄</label>
      	<div class="layui-input-inline">
        	<input type="text" id="age" name="medical.age"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
       </div>
      
      <div class="layui-form-item">
      	<label class="layui-form-label">身份证号</label>
      	<div class="layui-input-inline">
        	<input type="text" id="idcard" name="medical.idcard"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
      	<label class="layui-form-label">社保卡号</label>
      	<div class="layui-input-inline">
        	<input type="text" id="sscno" name="medical.sscno"  placeholder="请输入" autocomplete="off" class="layui-input">
     	</div>
     	<label class="layui-form-label">人群类别</label>
      	<div class="layui-input-inline">
      	<input type="text" id="crowdType" name="medical.crowdType"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
      </div>
      
       <div class="layui-form-item">
      	<label class="layui-form-label">统筹区编码</label>
      	<div class="layui-input-inline">
      	<input type="text" id="handdingInsCode" name="medical.handdingInsCode"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	<label class="layui-form-label">就诊类型</label>
      	<div class="layui-input-inline">
     	     <select   id="diagType" name="medical.diagType" lay-filter="ifType">
      		        <option value="">请选择</option>
      				<option value="住院">住院</option>
      				<option value="门诊">门诊</option>
      				<option value="门特">门特</option>
      				<option value="其他">其他</option>
        	</select>
     	 </div>
      	<label class="layui-form-label">医疗机构</label>
      	<div class="layui-input-inline">
        	<input type="text" id="orgName" name="medical.orgName"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
        </div>
         		
      <div class="layui-form-item">
      	<label class="layui-form-label">费用发生日期</label>
      	<div class="layui-input-inline">
        	<input type="text" id="paymentDate" name="medical.paymentDate"   placeholder="右键点击选择日期" autocomplete="off" class="layui-input">
     	 </div>
      	
      	<label class="layui-form-label">入院日期</label>
      	<div class="layui-input-inline">
        	<input type="text" id="inhosDate" name="medical.inhosDate"   placeholder="右键点击选择日期" autocomplete="off" class="layui-input">
     	 </div>
         		
      	<label class="layui-form-label">出院日期</label>
      	<div class="layui-input-inline">
        	<input type="text" id="outhosDate" name="medical.outhosDate"   placeholder="右键点击选择日期" autocomplete="off" class="layui-input">
     	 </div>
      	
      </div>
      
      <div class="layui-form-item">
      
      <label class="layui-form-label">住院号</label>
      	<div class="layui-input-inline">
        	<input type="text" id="admissionNo" name="medical.admissionNo"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
      
      	<label class="layui-form-label">住院类型</label>
      	<div class="layui-input-inline">
        	<input type="text" id="admissionType" name="medical.admissionType"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	 
     	 <label class="layui-form-label">科室</label>
      	<div class="layui-input-inline">
        	<input type="text" id="departName" name="medical.departName"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
      </div>
         		
      <div class="layui-form-item">
      	<label class="layui-form-label">险种类型</label>
      	<div class="layui-input-inline">
        	<input type="text" id="medicalType" name="medical.medicalType"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
         		
      	<label class="layui-form-label">病情</label>
      	<div class="layui-input-inline">
        	<input type="text" id="condition" name="medical.condition"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
        <label class="layui-form-label">收费单据号</label>
      	<div class="layui-input-inline">
        	<input type="text" id="billingNo" name="medical.billingNo"  lay-verify="number"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
      </div>
         		
         		
      <div class="layui-form-item">
      	<label class="layui-form-label">总金额</label>
      	<div class="layui-input-inline">
        	<input type="text" id="totalCost" name="medical.totalCost"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	<label class="layui-form-label">基本统筹应支付</label>
      	<div class="layui-input-inline">
        	<input type="text" id="basicCostM" name="medical.basicCostM"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	 
     	 <label class="layui-form-label">冲销标志</label>
      	<div class="layui-input-inline">
        	<input type="text" id="reversalMark" name="medical.reversalMark"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
      </div>
         		
      <div class="layui-form-item">
      	<label class="layui-form-label">基金支付金额</label>
      	<div class="layui-input-inline">
        	<input type="text" id="fundCost" name="medical.fundCost"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
         		
      	<label class="layui-form-label">账户自付金额</label>
      	<div class="layui-input-inline">
        	<input type="text" id="sscAccountCost" name="medical.sscAccountCost"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
         		
      	<label class="layui-form-label">现金支付金额</label>
      	<div class="layui-input-inline">
        	<input type="text" id="cashCost" name="medical.cashCost"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
      </div>
         		
      <div class="layui-form-item">
      	<label class="layui-form-label">民政救助</label>
      	<div class="layui-input-inline">
        	<input type="text" id="civilAffairSubsidy" name="medical.civilAffairSubsidy"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
         		
      	<label class="layui-form-label">扶贫补助</label>
      	<div class="layui-input-inline">
        	<input type="text" id="povertyAlleviationSubsidy" name="medical.povertyAlleviationSubsidy"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
         		
      	<label class="layui-form-label">财政补助</label>
      	<div class="layui-input-inline">
        	<input type="text" id="financeSubsidy" name="medical.financeSubsidy"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
      </div>
         		
      <div class="layui-form-item">
      	<label class="layui-form-label">公务员补助</label>
      	<div class="layui-input-inline">
        	<input type="text" id="officialSubsidy" name="medical.officialSubsidy"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
      </div>
         		
     <!--  <div class="layui-form-item">
      	<label class="layui-form-label">商保基金支付</label>
      	<div class="layui-input-inline">
        	<input type="text" id="biFundCost" name="medical.biFundCost"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
         		
      	<label class="layui-form-label">商保账户支付</label>
      	<div class="layui-input-inline">
        	<input type="text" id="biAccountCost" name="medical.biAccountCost"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
         		
      	<label class="layui-form-label">商保现金支付</label>
      	<div class="layui-input-inline">
        	<input type="text" id="biCashCost" name="medical.biCashCost"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
      </div> -->
         		
      <div class="layui-form-item">
      	<label class="layui-form-label">个人负担金额</label>
      	<div class="layui-input-inline">
        	<input type="text" id="selfCost" name="medical.selfCost"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
         		
      	<label class="layui-form-label">待遇享受类别</label>
      	<div class="layui-input-inline">
        	<input type="text" id="treatmentType" name="medical.treatmentType"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
         		
      	<label class="layui-form-label">医疗待遇状态</label>
      	<div class="layui-input-inline">
        	<input type="text" id="medicalTreatmentState" name="medical.medicalTreatmentState"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
      </div>
         		
      <div class="layui-form-item">
      	<label class="layui-form-label">出院状态</label>
      	<div class="layui-input-inline">
        	<input type="text" id="dischargeState" name="medical.dischargeState"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
         		
      	<label class="layui-form-label">就诊方式</label>
      	<div class="layui-input-inline">
        	<input type="text" id="treatmentWay" name="medical.treatmentWay"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
         		
      	
         		
      	<label class="layui-form-label">住院天数</label>
      	<div class="layui-input-inline">
        	<input type="text" id="stayLength" name="medical.stayLength"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
      </div>
          
          
      
      		<div class="layui-form-item layui-hide">
      <input type="button" lay-submit lay-filter="LAY-user-front-submit" id="LAY-user-front-submit" value="确认">
    </div>
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
	  }).use(['index', 'form','laydate'], function(){
		  var laydate=layui.laydate;
			laydate.render({
				elem:'#paymentDate',
				trigger:'click',
				type:'datetime',
				format:'yyyy-MM-dd'
				})  
				
				var laydate2=layui.laydate;
			laydate2.render({
				elem:'#inhosDate',
				trigger:'click',
				type:'datetime',
				format:'yyyy-MM-dd'
				})  
				
				var laydate3=layui.laydate;
			laydate3.render({
				elem:'#outhosDate',
				trigger:'click',
				type:'datetime',
				format:'yyyy-MM-dd'
				})  
				
				
		  
	  })
  
  function child(obj){
	  var medical = JSON.parse(obj);
	  $("#id").val(medical["id"]);
	  for (var index in medical){
	      $("#"+index).val(medical[index]);
	  }
  }
  </script>
</body>
</html>