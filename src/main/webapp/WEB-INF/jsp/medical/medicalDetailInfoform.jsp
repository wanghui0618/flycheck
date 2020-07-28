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

</head>
<body>
<div class="layui-form" lay-filter="layuiadmin-form-useradmin" id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">
    <input type="hidden" id="id" name="medicalDetail.id" >
    <input type="hidden" id="medicalId" name="medicalDetail.medicalId" >
        <div class="layui-form-item">
      	<label class="layui-form-label">项目编号</label>
      	<div class="layui-input-inline">
        	<input type="text" id="itemCode" name="medicalDetail.itemCode"   placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	 <label class="layui-form-label">项目名称</label>
      	<div class="layui-input-inline">
        	<input type="text" id="itemName" name="medicalDetail.itemName"   placeholder="请输入" autocomplete="off" class="layui-input">
      	 </div>
      	 <label class="layui-form-label">项目规格</label>
      	<div class="layui-input-inline">
        	<input type="text" id="itemStandard" name="medicalDetail.itemStandard"   placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
      	<label class="layui-form-label">药品类别</label>
      	<div class="layui-input-inline">
        	<input type="text" id="drugType" name="medicalDetail.drugType"   placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
        </div>
      
        <div class="layui-form-item">
      	<label class="layui-form-label">项目单价</label>
      	<div class="layui-input-inline">
        	<input type="text" id="itemPrice" name="medicalDetail.itemPrice"  lay-verify="required|number"    placeholder="请输入" autocomplete="off" class="layui-input">
     	</div>
      	<label class="layui-form-label">项目数量</label>
      	<div class="layui-input-inline">
        	<input type="text" id="itemNum" name="medicalDetail.itemNum" lay-verify="required|number"   placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	 <label class="layui-form-label">项目金额</label>
      	<div class="layui-input-inline">
        	<input type="text" id="itemCost" name="medicalDetail.itemCost" lay-verify="required|number"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	 <input type="hidden" id="billingNo" name="medicalDetail.billingNo" >
     	  	<!-- <label class="layui-form-label">收费单据号</label>
      	<div class="layui-input-inline">
        	<input type="text" id="billingNo" name="medicalDetail.billingNo"   lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div> -->
        </div>
   		
   		<div class="layui-form-item">
      	
      	<label class="layui-form-label">报销级别</label>
			<div class="layui-input-block " style="width: 190px;float: left;margin-left: 0px;">
				<select id="applyPayLevel" name="medicalDetail.applyPayLevel" >
					<option value="" disabled selected style='display:none;'>请选择类别</option>
					<option>甲类</option>
    	           	<option>乙类</option>
    	           	<option>丙类</option>
				</select>
			</div>
			
     	  	<label class="layui-form-label" style="margin-left: 10px;">是否医保项目</label>
            	<div class="layui-input-inline" id="isInsuranceProject"  style="width: 190px;float: left;margin-left: 0px;">
                     <input type="radio" name="medicalDetail.isInsuranceProject" value="1" title="是">
                     <input type="radio" name="medicalDetail.isInsuranceProject" value="0" title="否">
                </div>
     	 <label class="layui-form-label" >剂型</label>
      	<div class="layui-input-inline">
        	<input type="text" id="doseForm" name="medicalDetail.doseForm"   placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	  	<label class="layui-form-label">费用发生时间</label>
      	<div class="layui-input-inline">
        	<input type="text" id="feeCreateDate" name="medicalDetail.feeCreateDate"   lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
      </div>
      
         
        <div class="layui-form-item">
      	<label class="layui-form-label">收费类别</label>
      	<div class="layui-input-inline">
        	<input type="text" id="chargeType" name="medicalDetail.chargeType"   placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	  	<label class="layui-form-label">用药天数</label>
      	<div class="layui-input-inline">
        	<input type="text" id="useDay" name="medicalDetail.useDay"   lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	 <label class="layui-form-label">单次用量</label>
      	<div class="layui-input-inline">
        	<input type="text" id="singleDose" name="medicalDetail.singleDose"   placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	  	<label class="layui-form-label">用量单位</label>
      	<div class="layui-input-inline">
        	<input type="text" id="doseUnit" name="medicalDetail.doseUnit"   lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	 </div>
     	 
     	 
     	 <div class="layui-form-item">
      	<label class="layui-form-label">给药途径</label>
      	<div class="layui-input-inline">
        	<input type="text" id="deliverWay" name="medicalDetail.deliverWay"   placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	  	<label class="layui-form-label">服用频次</label>
      	<div class="layui-input-inline">
        	<input type="text" id="takeFrequence" name="medicalDetail.takeFrequence"   lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	 <label class="layui-form-label">总金额</label>
      	<div class="layui-input-inline">
        	<input type="text" id="sumAmount" name="medicalDetail.sumAmount"  lay-verify="required|number" placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	  	<label class="layui-form-label">报销金额</label>
      	<div class="layui-input-inline">
        	<input type="text" id="applyPayAmount" name="medicalDetail.applyPayAmount"   lay-verify="required|number" placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	 </div>
     	 
     	 
     	 <div class="layui-form-item">
      	<label class="layui-form-label">自付金额</label>
      	<div class="layui-input-inline">
        	<input type="text" id="selfPayAmount" name="medicalDetail.selfPayAmount" lay-verify="required|number"   placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	  	<label class="layui-form-label">全额统筹</label>
      	<div class="layui-input-inline">
        	<input type="text" id="fullOrdination" name="medicalDetail.fullOrdination"   lay-verify="required|number" placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	 <label class="layui-form-label">部分统筹</label>
      	<div class="layui-input-inline">
        	<input type="text" id="partialOrdination" name="medicalDetail.partialOrdination" lay-verify="required|number"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	  	<label class="layui-form-label">部分自付</label>
      	<div class="layui-input-inline">
        	<input type="text" id="partialPayment" name="medicalDetail.partialPayment"  lay-verify="required|number" placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	 </div>
     	 
     	 
     	 <div class="layui-form-item">
      	<label class="layui-form-label">全额自付</label>
      	<div class="layui-input-inline">
        	<input type="text" id="fullPayment" name="medicalDetail.fullPayment" lay-verify="required|number"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	  	<label class="layui-form-label">限价金额</label>
      	<div class="layui-input-inline">
        	<input type="text" id="limitPrice" name="medicalDetail.limitPrice"   lay-verify="required|number" placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	 
     	 <label class="layui-form-label ">是否违规</label>
			<div class="layui-input-block " style="width: 190px;float: left;margin-left: 0px;">
				<select id="isIlegal" name="medicalDetail.isIlegal" >
					<option value="" disabled selected style='display:none;'>请选择类别</option>
					<option value="0">未违规</option>
    	           	<option value="1">违规</option>
    	           	<option value="2">疑似违规</option>
				</select>
			</div>
     	  	<label class="layui-form-label" style="margin-left: 10px;">违规类型</label>
      	<div class="layui-input-inline">
        	<input type="text" id="ilegalType" name="medicalDetail.ilegalType"   lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	 </div>
     	 
     	 
     	 <div class="layui-form-item">
      	<label class="layui-form-label">门诊处方ID</label>
      	<div class="layui-input-inline">
        	<input type="text" id="recipelId" name="medicalDetail.recipelId"   placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	  	<label class="layui-form-label">住院医嘱ID</label>
      	<div class="layui-input-inline">
        	<input type="text" id="adviceId" name="medicalDetail.adviceId"   lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	 
			<label class="layui-form-label ">项目类别</label>
			<div class="layui-input-block " style="width: 190px;float: left;margin-left: 0px;">
				<select id="projectType" name="medicalDetail.projectType" >
					<option value="" disabled selected style='display:none;'>请选择类别</option>
					<option>药品</option>
    	           	<option>诊疗项目</option>
    	           	<option>耗材</option>
				</select>
			</div>
			
     	 <label class="layui-form-label" style="margin-left: 10px;">规格</label>
      	<div class="layui-input-inline" >
        	<input type="text" id="specs" name="medicalDetail.specs"   lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	 </div>
     	 
     	 
     	 <div class="layui-form-item">
      	<label class="layui-form-label">取药总量</label>
      	<div class="layui-input-inline">
        	<input type="text" id="totalDrugIntake" name="medicalDetail.totalDrugIntake" lay-verify="required|number"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	  	<label class="layui-form-label">药量天数</label>
      	<div class="layui-input-inline">
        	<input type="text" id="dosageDays" name="medicalDetail.dosageDays"   lay-verify="required|number" placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	
     	 <label class="layui-form-label" >是否出院带药处方</label>
            	<div class="layui-input-block" id="prescriptionDischarge" style="width: 250px;float: left;margin-left: 0px;">
                     <input type="radio"  name="medicalDetail.prescriptionDischarge" value="1" title="外配处方">
                     <input type="radio"  name="medicalDetail.prescriptionDischarge" value="0" title="非外配处方">
                </div>
     	 </div>
     	 
     	 <div class="layui-form-item">
      	<label class="layui-form-label">医疗机构编码</label>
      	<div class="layui-input-inline">
        	<input type="text" id="medicalInsCode" name="medicalDetail.medicalInsCode"   placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	  	<label class="layui-form-label">医疗机构名称</label>
      	<div class="layui-input-inline">
        	<input type="text" id="medicalInsName" name="medicalDetail.medicalInsName"   lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	  <label class="layui-form-label">药品计量单位</label>
      	<div class="layui-input-inline">
        	<input type="text" id="drugMeaUnit" name="medicalDetail.drugMeaUnit"   placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	  	<label class="layui-form-label">收费计量单位</label>
      	<div class="layui-input-inline">
        	<input type="text" id="feeMeaUnit" name="medicalDetail.feeMeaUnit"   lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	 
     	 </div>
     	 
     	 <div class="layui-form-item">
     	 <label class="layui-form-label">科室编码</label>
      	<div class="layui-input-inline">
        	<input type="text" id="departCode" name="medicalDetail.departCode"   placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	  	<label class="layui-form-label">科室名称</label>
      	<div class="layui-input-inline">
        	<input type="text" id="departName" name="medicalDetail.departName"   lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
      	<label class="layui-form-label">医师编码</label>
      	<div class="layui-input-inline">
        	<input type="text" id="docCode" name="medicalDetail.docCode"   placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	  	<label class="layui-form-label">医师姓名</label>
      	<div class="layui-input-inline">
        	<input type="text" id="docName" name="medicalDetail.docName"   lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	
     	 </div>
     	 
     	 <div class="layui-form-item">
      	<label class="layui-form-label">医院的医疗服务名称</label>
      	<div class="layui-input-inline">
        	<input type="text" id="hosServiceName" name="medicalDetail.hosServiceName"   placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	  	<label class="layui-form-label">结算日期</label>
      	<div class="layui-input-inline">
        	<input type="text" id="balanceDate" name="medicalDetail.balanceDate"   lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	 <label class="layui-form-label">医保金额</label>
      	<div class="layui-input-inline">
        	<input type="text" id="insCost" name="medicalDetail.insCost" lay-verify="required|number"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	  	<label class="layui-form-label">基金支付</label>
      	<div class="layui-input-inline">
        	<input type="text" id="fundCost" name="medicalDetail.fundCost" lay-verify="required|number"   lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div> 
     	 </div>
     	 
     	 <div class="layui-form-item">
      	<label class="layui-form-label">非医保金额</label>
      	<div class="layui-input-inline">
        	<input type="text" id="notInsCost" name="medicalDetail.notInsCost" lay-verify="required|number"   placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	  	<label class="layui-form-label">药品用量</label>
      	<div class="layui-input-inline">
        	<input type="text" id="durgDosage" name="medicalDetail.durgDosage"   lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div><label class="layui-form-label">项目单位</label>
      	<div class="layui-input-inline">
        	<input type="text" id="itemUnit" name="medicalDetail.itemUnit"   placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	  	<label class="layui-form-label">医嘱组号</label>
      	<div class="layui-input-inline">
        	<input type="text" id="adviceGroup" name="medicalDetail.adviceGroup"    placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	 </div> 
     	 
     	 <div class="layui-form-item">
      	<label class="layui-form-label">医嘱顺序号</label>
      	<div class="layui-input-inline">
        	<input type="text" id="adviceOrder" name="medicalDetail.adviceOrder"  placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	  	<label class="layui-form-label">自付比例</label>
      	<div class="layui-input-inline">
        	<input type="text" id="selfPayRate" name="medicalDetail.selfPayRate"   lay-verify="selfPayRate1" placeholder="请输入" autocomplete="off" class="layui-input">
     	 </div>
     	 <label class="layui-form-label">创建日期</label>
      	<div class="layui-input-inline">
        	<input type="text" id="createDate" name="medicalDetail.createDate"   placeholder="请输入" autocomplete="off" class="layui-input">
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
	  }).use(['index', 'form','table','laydate'], function(){
		  var $ = layui.$
			,form = layui.form
			,table = layui.table;

		  var laydate=layui.laydate;
			laydate.render({
				elem:'#feeCreateDate'
					,trigger:'click'
						,format:'yyyy-MM-dd' 
			});
			var laydate1=layui.laydate;
			laydate1.render({
				elem:'#createDate'
					,trigger:'click'
						,format:'yyyy-MM-dd' 
			}); 
			var laydate2=layui.laydate;
			laydate2.render({
				elem:'#balanceDate'
					,trigger:'click'
						,format:'yyyy-MM-dd' 
			}); 
			//自定义验证规则
			  form.verify({
				  selfPayRate1: function(value){
			      if(value.length > 10){
			        return '自付比例长度最多为10';
			      }
			    }
			  });
				
				
	  });
  function child(obj){
	  var medicalDetail = JSON.parse(obj);
	  $("#id").val(medicalDetail["id"]);
	  for (var index in medicalDetail){
	      $("#"+index).val(medicalDetail[index]);
	  }
	  $("#isInsuranceProject").find("input[value ='"+medicalDetail.isInsuranceProject+"']").attr("checked","true");
	  $("#prescriptionDischarge").find("input[value ='"+medicalDetail.prescriptionDischarge+"']").attr("checked","true");
  }
  </script>
</body>
</html>