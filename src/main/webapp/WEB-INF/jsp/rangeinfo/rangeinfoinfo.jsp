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
	href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css"
	media="all">

<link rel="stylesheet"
	href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css"
	media="all">

<title>城市机构管理</title>
<style>
.layui-form-label {
	width: 140px;
}

.layui-form-item {
	margin-top: 25px;
}
</style>
</head>
<body>
<style>
    .layui-form-select dl { max-height:130px; }
</style>
	<div class="layui-form" lay-filter="layuiadmin-form-useradmin"
		id="layuiadmin-form-studentadmin" style="padding: 20px 0 0 0;">

		<input type="hidden" name="rangeInfo.id" id="id">
		<!-- rangeInfo -->
		<div class="layui-form-item">
			<label class="layui-form-label">城市编码</label>
			<div class="layui-input-inline">
				<select name="rangeInfo.cityCode" id="city" lay-search="">
					<option value="" disabled selected style='display: none;'>请选择城市</option>
				</select>
			</div>
			
			<label class="layui-form-label">机构类型</label>
			<div class="layui-input-inline">
				<select name="rangeInfo.orgType" id="orgType">
					<option value="" disabled selected style='display: none;'>请选择机构类型</option>
					<option value="1">医院</option>
					<option value="0">门诊</option>
					<option value="2">药店</option>
				</select>
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">机构编号</label>
			<div class="layui-input-inline">
				<input type="text" id="orgCode"
					name="rangeInfo.orgCode" lay-verify="required"
					placeholder="请输入机构编号 " autocomplete="off" class="layui-input">
			</div>

			<label class="layui-form-label">机构名称</label>
			<div class="layui-input-inline">
				<input type="text" id="orgName"
					name="rangeInfo.orgName" lay-verify="required"
					placeholder="请输入机构名称" autocomplete="off" class="layui-input">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">互联网结算开通</label>
			<div class="layui-input-inline">
				<select name="rangeInfo.netContact" id="netContact">
					<option value="" disabled selected style='display: none;'>请选择</option>
					<option value="1">开通</option>
					<option value="0">未开通</option>
					<option value="2">停止</option>
				</select>
			</div>
			
			<label class="layui-form-label">住院结算开通</label>
			<div class="layui-input-inline">
				<select name="rangeInfo.inhosBalance" id="inhosBalance">
					<option value="" disabled selected style='display: none;'>请选择</option>
					<option value="1">开通</option>
					<option value="0">未开通</option>
					<option value="2">停止</option>
				</select>
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">门诊大病结算开通</label>
			<div class="layui-input-inline">
				<select name="rangeInfo.outpatientBalance" id="outpatientBalance">
					<option value="" disabled selected style='display: none;'>请选择</option>
					<option value="1">开通</option>
					<option value="0">未开通</option>
					<option value="2">停止</option>
				</select>
			</div>
			
			<label class="layui-form-label">生育结算开通</label>
			<div class="layui-input-inline">
				<select name="rangeInfo.birthBalance" id="birthBalance">
					<option value="" disabled selected style='display: none;'>请选择</option>
					<option value="1">开通</option>
					<option value="0">未开通</option>
					<option value="2">停止</option>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">刷卡结算开通</label>
			<div class="layui-input-inline">
				<select name="rangeInfo.cardBanlance" id="cardBanlance">
					<option value="" disabled selected style='display: none;'>请选择</option>
					<option value="1">开通</option>
					<option value="0">未开通</option>
					<option value="2">停止</option>
				</select>
			</div>
			<label class="layui-form-label">经办机构编码</label>
			<div class="layui-input-inline">
				<input type="text" id="handdingInsCode"
					name="rangeInfo.handdingInsCode" lay-verify="required"
					placeholder="请输入经办机构编码" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">定点协议结算日期</label>
			<div class="layui-input-inline">
				<input type="text" id="insBalanceDate" name="rangeInfo.insBalanceDate"
					lay-verify="required" placeholder="请选择有效日期" autocomplete="off"
					class="layui-input">
			</div>

			<label class="layui-form-label">定点协议终止或暂停结算日期</label>
			<div class="layui-input-inline">
				<input type="text" id="insBalanceStopDate" name="rangeInfo.insBalanceStopDate"
					lay-verify="required" placeholder="请选择有效日期" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		<div class="layui-form-item layui-hide">
			<input type="button" lay-submit lay-filter="LAY-cityorg-front-submit"
				id="LAY-cityorg-front-submit" value="确认">
		</div>
	</div>

<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/rangeinfo/rangeinfoinfo.js"></script>
	<script>

  layui.config({
    base: '<%=request.getContextPath() %>/plugins/layui/layuiadmin/'
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'form','laydate'], function(){
	  var form=layui.form;
	
	//监听select change
	 form.on('select(city)', function(data){
			 //$("#cityName").attr("value",data.text);
			// alert($(data.elem).find("option:selected").text());
			$("#cityName").val($(data.elem).find("option:selected").text());
			form.render('select');
	 
	});
	
  });
	 function child(obj){
		 
		  var selfPayRatio = JSON.parse(obj);
		  $("#id").val(selfPayRatio["id"]);
		  for (var index in selfPayRatio){
		      $("#"+index).val(selfPayRatio[index]);
		  }
		  $("#insuranceType").find("option[value ='"+selfPayRatio.insuranceType+"']").prop("selected",true);
		  var code=selfPayRatio.cityCode;
		  loadSelect(code);
		  
	  }
	//加载城市下拉字典,根据参数为select赋值
  	function loadSelect(code){
  	
		 $.getJSON($WEB_ROOT_PATH+'/dhccApi/dict/dict/findDict?dictRequestVo.tableName=t_piccbid_dict_city', 
							function(data){
			 					var form=layui.form;
					     		var  dataList= data.dictList;
					     		for(var i=0 ;i<dataList.length;i++){
					     			var mm="<option value='"+dataList[i].value+"'>"+dataList[i].text+"</option>";
					     			$("#city").append(mm); 
					     		}
					     		if(code!=null){
						           $("#city").find("option[value ='"+code+"']").attr("selected","selected");
					     		}
					     		form.render('select');
			}); 
  	}
 
  </script>
</body>
</html>