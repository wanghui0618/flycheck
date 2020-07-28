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
<title>机审疑点明细</title>
<style>
.lableValue{
width:245px;
font-size:13px;
text-align: left;
display: inline-block;
}
.lableKey{
font-size:13px;
}
.lineDiv{
height:25px;
}
.layui-table-cell {
	height: 20px;
	line-height: 18px;
	font-size: 12px;
	color:black;
}

</style>
</head>
<body>
<div class="layui-fluid" style="font-size:12px;overflow-y: hidden;">
	<div class="layui-row" ><!-- 行 -->
		<div class="layui-card"><!-- 面板 -->
			<div class="layui-card-header" style="height:85px">
				<div class="lineDiv">
					<lable class="lableKey">规则名称：</lable><lable id="typeNameTop" class='lableValue'></lable>
					<lable class="lableKey">规则编码：</lable><lable id="typeNoTop" class='lableValue'></lable>
					<lable class="lableKey">疑点内容：</lable><lable id="itemNameTop" class='lableValue'></lable>
				</div>
				<div class="lineDiv">
					<lable class="lableKey">检出时间：</lable><lable id="sysViolationDateTop" class='lableValue'></lable>
					<lable class="lableKey">预扣金额：</lable><lable id="ykCost" class='lableValue'></lable>
					<lable class="lableKey">实际金额：</lable><lable id="sjCost" class='lableValue'></lable>
				</div>
				<div class="lineDiv" >
					<lable class="lableKey">扣除原因：</lable><lable id="returnDesTop" class='lableValue'  style="width:860px;"></lable>
				</div>
				<!-- <div class="lineDiv">
					<lable class="lableKey">检出时间：</lable><lable id="sysViolationDateTop" class='lableValue'></lable>
					<lable class="lableKey">机审任务：</lable><lable id="jsrwTop" class='lableValue'></lable>
				</div> -->
				<div id="totalAmount" style="color:blue;float:right;text-align:right;font-size:11px;margin-top:-10px;height:15px ">涉及条数：16 条</div>
			</div>
			<div class="layui-card-body">
				<div class="layui-fluid" style="margin-top:0px">
					<table id="medicalDetailsTypeNo" class="layui-hide layui-table" lay-filter="medicalDetails" lay-size="sm" style="width:100%"></table>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
//全局变量
var medical_id='';
var item_code='';
var medical_detail_id='';
var type_no='';
var item_name='';//显示不完的项目名称
var return_desc='';//显示不完全违规描述
layui.config({
   base: '<%=request.getContextPath() %>/plugins/layui/layuiadmin/'
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'form','laydate','element','table'], function(){
	var form=layui.form;
	var element = layui.element;
	var table=layui.table;
	
	//渲染medicalDetail表
	table.render({
    	elem: '#medicalDetailsTypeNo'
        ,url: $WEB_ROOT_PATH+'/dhccApi/documentCenter/documentCenter/list'
        ,height: 275
        ,limits: [5,10,20]
        ,limit: 5
        ,where:{'documentBasicVo.medicalId':medical_id,"documentBasicVo.itemCode":item_code}
        ,cols: [[
            	{type: 'numbers', title: '序号' }
            	,{field:'id',title:'ID',hide:true,width:80}
				,{field:'userStatus', title: '初审结果',width:150}
	            ,{field:'isIlegal', title: '机审结果',width:100,templet:function(d){
	            	if(d.isIlegal=='0'){
	            		return '违规';
	            	}else if(d.isIlegal=='1'){
	            		return '疑似违规';
	            	}else if(d.isIlegal=='2'){
	            		return '正常';
	            	}else{
	            		return d.isIlegal;
	            	}
	            }}
	            ,{field:'feeCreateDate', title: '费用发生时间',width:130}
				,{field:'itemCode', title: '项目编号',width:150}
	            ,{field:'itemName', title: '项目名称',width:250}
	            ,{field:'itemStandard', title: '项目规格',width:80}
	            ,{field:'itemPrice', title: '项目单价'}
	            ,{field:'itemCost', title: '项目金额'}
	            ,{field:'itemNum', title: '项目数量'}
	            ,{field:'chargeType', title: '收费类别',width:120}
	            ,{field:'drugType', title: '药品类别',width:120}
	            ,{field:'sumAmount', title: '总金额'}
	            ,{field:'doseForm', width:100,title: '剂型'}
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
	            ,{field:'applyPayLevel', title: '报销级别'}
	            ,{field:'deliverWay',title: '给药途径'}
	            ,{field:'doseUnit', title: '用量单位'}
	            ,{field:'fullOrdination', title: '全额统筹'}
	            ,{field:'fullPayment', title: '全额自付'}
	            ,{field:'isInsuranceProject', title: '是否医保项目'}
	           ]]
	    ,done:function(res){
	          // $('tr').eq(1).css("background-color","#EEF6FF");
	          $("#totalAmount").html('涉及条数： '+res.count +" 条" );
	      }
	    ,page: true
    });
	//TOP赋值
	ruleTable(medical_id,medical_detail_id,type_no);
	//显示不完全的鼠标悬停事件
	topHover('itemNameTop',item_name);
	
});
function child(obj,typeNo){
	//将medical_id传过来，做全局变量
	var medicalDetail = JSON.parse(obj);
	medical_id=medicalDetail["medicalId"];
	item_code=medicalDetail["itemCode"];
	medical_detail_id=medicalDetail["id"];
	item_name=medicalDetail["itemName"];
	type_no=typeNo;
	//赋值
	$("#itemNameTop").html(cantShowAll(medicalDetail["itemName"],20));
	$("#ykCost").html(medicalDetail["itemCost"]);
	$("#sjCost").html(medicalDetail["itemCost"]);
}
//TOP赋值
function ruleTable(medicalId,medicalDetailId,typeNo){
	var url=$WEB_ROOT_PATH+'/dhccApi/medicalId/medicalId/FindMedicalId';
	var filed={'detailId.medicalId':medicalId,"detailId.medicalDetailId":medicalDetailId,"detailId.typeNo":typeNo};
	$.post(url,filed,function(result){
		if(result){
			console.log(result);
			$("#typeNameTop").html(result.typeName);
			$("#typeNoTop").html(result.typeNo);
			return_desc=result.returnDesc;
			$("#returnDesTop").html(cantShowAll(result.returnDesc,100));
			topHover('returnDesTop',return_desc);
			$("#sysViolationDateTop").html(result.sysAuditingDate);
		}
	});
}
//显示不完全，截取字符串，拼接....
function cantShowAll(str,length){
	if(str&&str.length>length){	
	 	return str=str.substring(0,length)+'...';
	}else{
		return str;
	}
}
//鼠标悬停事件
function topHover(idname,value){
	$("#"+idname).hover(function() {
		subtips = layer.tips(value, '#'+idname,{tips:[1,'#0000FF'],time: 5000});
	}, function() {
		layer.close(subtips);
	});
}

</script>
</body>
</html>