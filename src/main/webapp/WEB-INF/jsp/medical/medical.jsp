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
<style>
.layui-input-block {
    margin-left: 90px;
    min-height: 36px;
}
.layui-input-block1 {
    margin-left: 90px;
    
}
.layui-form-label {
    width: 60px;
}
.layui-tab-title li{
	min-width: 62px;
}
.layui-form-radio {
    margin: 0px 0px 0 0;
}
</style>
<title>患者就诊记录</title>
</head>
<body>



   <div class="layui-fluid" id="LAY-component-grid-mobile-pc">
    <div class="layui-row layui-col-space10">
      <div class="layui-col-xs6 layui-col-md9">
        <!-- 填充内容 -->
        <div class="layui-card">
          <div class="layui-form layui-card-header layuiadmin-card-header-auto">
			        <div class="layui-form-item" >
			        	  <div class="layui-inline">
				            <label class="layui-form-label">就诊类型</label>
				            <div class="layui-input-block1" style="width:185px;">
				              <select name="medical.treatmentWay">
				              		<option ></option>
									<option value="门诊">门诊</option>
									<option value="门特">门特</option>
									<option value="住院">住院</option>
								</select>
				            </div>
				          </div>
			              <!-- <div class="layui-inline">
				            <label class="layui-form-label">姓名</label>
				            <div class="layui-input-block">
				              <input type="text" name="medical.patientName" placeholder="请输入" autocomplete="off" class="layui-input">
				            </div>
				          </div>
				          
				          <div class="layui-inline">
				            <label class="layui-form-label">身份证号</label>
				            <div class="layui-input-block">
				              <input type="text" name="medical.idNo" placeholder="请输入" autocomplete="off" class="layui-input">
				            </div>
				          </div> -->
				          
				          <div class="layui-inline">
				            <label class="layui-form-label">审核状态</label>
				            <div class="layui-input-block1" style="width:185px">
				              <select name="medical.status">
				              		<option ></option>
									<option value="机审完成">机审完成</option>
									<option value="人工审核完成">人工审核完</option>
									<option value="终审完成">终审完成</option>
								</select>
				            </div>
				          </div>
				          
				          <div class="layui-inline">
				            <label class="layui-form-label">结算日期</label>
				            <div class="layui-input-block" style="width:185px">
				            	<input type="text" id="finaTime" name="finaTime"
								 placeholder="请选择查询时间段" autocomplete="off"
								class="layui-input">
				            </div>
				          </div>
				          
				          <div class="layui-inline">
				            <label class="layui-form-label">医疗机构</label>
				            <div class="layui-input-block" style="width:185px" lay-search="">
				              <input type="text" name="orgName" placeholder="请输入" autocomplete="off" class="layui-input">
				            </div>
				          </div>
				          
				          <div class="layui-inline">
				            <label class="layui-form-label">收据单号</label>
				            <div class="layui-input-block" style="width:185px">
				              <input type="text" name="medical.billingNo" placeholder="请输入" autocomplete="off" class="layui-input">
				            </div>
				          </div>
				          
				           <div class="layui-inline">
				            <label class="layui-form-label">姓名</label>
				            <div class="layui-input-block" style="width:185px">
				              <input type="text" name="medical.name"  placeholder="请输入" autocomplete="off" class="layui-input">
				            </div>
				          </div>
				          
				            <div class="layui-inline">
				            <button class="layui-btn layuiadmin-btn-useradmin"  lay-submit lay-filter="LAY-user-front-search">
				              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
				            </button>
				          </div>
			      </div>
			   <div class="layui-card-body" style="padding-top:0px">
			     <table id="userTable" class="layui-hide" lay-filter="userTable"></table>
		        <script type="text/html" id="table-useradmin-webuser">
		  			<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="viewInfo">病例明细</a>
        		</script>
				   <script type="text/html" id="table-useradmin-webuser2">
					   <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="audit">稽核</a>
				   </script>

			   </div>
          </div>
        </div>
      </div>
      <div class="layui-col-xs6 layui-col-md3">
        <div class="layui-card">
          <div class="layui-card-body" style="padding:0px">
          		<div class="layui-tab layui-tab-card">
	            <ul class="layui-tab-title">
	              <li class="layui-this">系统审核</li>
	              <li class="">人工审核</li>
	            </ul>
	            <div class="layui-tab-content">
	              <div class="layui-tab-item layui-show">
	              		<h5>机审结果：<button class='layui-btn layui-btn-xs layui-btn-danger' name="medical.sysStatus" id="sysStatus" value="0" title="未违规">违规</button></h5>
	              		<h5>1、重复收费用药（2）</h5>
	              		<h5>2、住院天数超长，最大200，实际320</h5>
	              		<h5>3、药品性别违规（5）</h5>
	              </div>
	              <div class="layui-tab-item">
	              	<div class="layui-form" lay-filter="layuiadmin-form-useradmin" id="layuiadmin-form-useradmin" style="">
	              		<fieldset style="width:90%;margin:0 auto">
	    					<legend style="font-size:12px">病例费用：</legend>
		              		<h5>病例总金额：<span style="color: green;" name="medical.totalCost" id="totalCost">500</span></h5>
		              		<h5>统筹支付费用：<span style="color: green;">300.00【15%】</span></h5>
		              		<h5>药品：<span style="color: green;">150.80【7.54%】</span></h5>
		              		<h5>医疗服务：<span style="color: green;">1621.01【81.05%】</span></h5>
	              		</fieldset>
	              		<h5>审核结果：
	              			<input type="radio" id="status" name="medicalExamine.status" value="0" title="未违规" lay-filter="status1" class="layui-form-item" checked="false" disable>
     	 					<input type="radio" id="status2" name="medicalExamine.status" value="1" title="违规"  lay-filter="status1" class="layui-form-item" checked="false" disable>
     	 					<!-- <input type="radio" id="status" name="medicalExamine.status" value="2" title="疑似违规"  lay-filter="status1" class="layui-form-item"> -->
	              		</h5>
	              		<h5>实际扣款金额：<input type="text" id="cost" style="width:70px;display: inline-block;height:30px;" name="medicalExamine.costMoney"  class="layui-form-item" placeholder="" autocomplete="off" class="layui-input" readOnly="readOnly">（元）</h5>
	              		<h5>审核意见：</h5>
	              		<textarea placeholder="请输入内容" id="comm" class="layui-textarea" name="medicalExamine.comments" class="layui-form-item" readOnly="readOnly"></textarea>
	              		<div style="margin-top:8px"><button id="ssd" class='layui-btn layui-btn-sm layui-btn-normal' lay-submit lay-filter="LAY-user-front-save">保存</button><button class='layui-btn layui-btn-sm layui-btn-primary' id="ssd1" lay-submit lay-filter="LAY-user-front-white" lay-submit lay-filter="LAY-user-front-white">清空</button><button class='layui-btn layui-btn-sm layui-btn-danger' id="ssd2" lay-submit lay-filter="LAY-user-front-backout">撤销</button></div>
	              	</div>
	              </div>
	            </div>
	          </div>
          </div>
        </div>
        <div class="layui-card">
          <div class="layui-card-body" style="padding:0px">
          	<div class="layui-form" lay-filter="layuiadmin-form-useradmin1" id="layuiadmin-form-useradmin1" style="">
          		<div class="layui-card-header">终审结果</div>
          		<div class="layui-card-body">
          		
          			<h5>审核结果：<input type="radio" id="status1" name="medicalExamine.status" value="0" title="未违规" lay-filter="status2" class="layui-form-item" checked="false" disable>
     	 					<input type="radio" id="status3" name="medicalExamine.status" value="1" title="违规"  lay-filter="status2" class="layui-form-item" checked="false" disable></h5>
          			<h5>终审意见：</h5>
          			<textarea placeholder="请输入内容" id="commm" class="layui-textarea"  name="medicalExamine.comments" class="layui-form-item"></textarea>
          			<div style="margin-top:8px"><button class='layui-btn layui-btn-sm layui-btn-normal' id="hhd" lay-submit lay-filter="LAY-user-front-save1">保存</button><button class='layui-btn layui-btn-sm layui-btn-primary' id="hhd1" lay-submit lay-filter="LAY-user-front-white1">清空</button><button class='layui-btn layui-btn-sm layui-btn-danger' id="hhd2" lay-submit lay-filter="LAY-user-front-backout1">撤销</button></div>
          		</div>
          </div>
        </div>
        </div>
      </div>
    </div>
  </div>
 
  <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/medical/medical.js"></script>
  
  <script>
     var medicalId;
  function child(obj){
	  var medicalExamine = JSON.parse(obj);
	  medicalId=medicalExamine.id;
	  
  }
  </script>
  
</body>
</html>