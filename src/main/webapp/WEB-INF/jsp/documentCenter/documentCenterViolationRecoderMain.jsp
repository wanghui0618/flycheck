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
    <title>审核记录</title>
</head>
<style>
.layui-colla-title {
background-color: #DDDDDD;
height: 25px;
line-height: 23px;
font-size:12px;
}
.layui-table-cell {
	height: 15px;
	line-height: 15px;
	font-size: 12px;
	color:black;
}
.jsmenu{
vertical-align:top;
font-weight: bold;"d
}
.tablesh tr td{
word-wrap:break-word;
word-break:break-all;
max-width:300px;
}

</style>
<body>
<div class="layui-fluid" style="font-size:12px;overflow-y: hidden;">
	<div class="layui-row" ><!-- 行 -->
		<div class="layui-card" ><!-- 面板 -->
			<div class="layui-card-header" style="font-size:12px;">
				<lable id="itemCostTop">项目金额：</lable>
				<lable id="deductCost" style="margin-left:110px">扣除金额：</lable>
			</div>
			<div class="layui-card-body">
					<div class="layui-collapse" lay-accordion>
						
						<div class="layui-colla-item">
							<h2 class="layui-colla-title">机审</h2>
							<div class="layui-colla-content layui-show">
								<div style="height:120px;overflow:auto;">
									<div  class="jsmenu">机审结果：<lable id="jsResult" style="font-weight: normal;"></lable></div>
									<span class="jsmenu" >违规类别:</span>
									<div id="jsViolationType" class="layui-inline">
										<table style="margin-left:5px">
											
										</table>
									</div>
									<div class="jsmenu">扣除金额：<lable id="jsDeductCost" style="font-weight: normal;"></lable></div>
									<span class="jsmenu" >扣除原因:</span>
									<div id="jsViolationDes" class="layui-inline">
										<table class="tablesh" style="margin-left:5px">
											
										</table>
									</div>
								</div>
							</div>
						</div>
						<div class="layui-colla-item" id="csmb">
							<h2 class="layui-colla-title">初审</h2>
							<div class="layui-colla-content ">
								<div style="height:110px;overflow:auto;">
									<div class="jsmenu">初审结果：<lable id="csResult" style="font-weight: normal;"></lable></div>
									<span class="jsmenu" >违规类别:</span>
									<div id="csViolationType" class="layui-inline">
										<table style="margin-left:5px">
											
										</table>
									</div>
									<div class="jsmenu">扣除金额：<lable id="csDeductCost" style="font-weight: normal;"></lable></div>
									<span class="jsmenu" >扣除原因:</span>
									<div id="csViolationDes" class="layui-inline">
										<table class="tablesh" style="margin-left:5px">
											
										</table>
									</div>
								</div>
							</div>
						</div>
						<div class="layui-colla-item" id="jhmb">
							<h2 class="layui-colla-title">稽核</h2>
							<div class="layui-colla-content">
								<div style="height:110px;overflow:auto;">
									<div class="jsmenu">稽核结果：<lable id="jhResult" style="font-weight: normal;"></lable></div>
									<div class="jsmenu">扣除金额：<lable id="jhDeductCost" style="font-weight: normal;"></lable></div>
									<div class="jsmenu">扣除原因：<lable id="jhDeductDesc" style="font-weight: normal;"></lable></div>
									<div class="jsmenu">稽核意见：<lable id="jhAuditSuggest" style="font-weight: normal;"></lable></div>
									<span class="jsmenu" >稽核材料:</span>
									<div id="jsData" class="layui-inline">
										<table class="tablesh" style="margin-left:5px">
											
										</table>
									</div>
								</div>
							</div>
						</div>
						<div class="layui-colla-item" id="gsmb">
							<h2 class="layui-colla-title">公示</h2>
							<div class="layui-colla-content">
								<div style="height:110px;overflow:auto;">
									<div class="jsmenu">公示结果：<lable id="gsResult" style="font-weight: normal;"></lable></div>
									<div class="jsmenu">扣除金额：<lable id="gsDeductCost" style="font-weight: normal;"></lable></div>
									<div class="jsmenu">扣除原因：<lable id="gsDeductDesc" style="font-weight: normal;"></lable></div>
									<span class="jsmenu" >公示材料:</span>
									<div id="gsData" class="layui-inline">
										<table class="tablesh" style="margin-left:5px">
											
										</table>
									</div>
								</div>
							</div>
						</div>
						<div class="layui-colla-item" id="zsmb">
							<h2 class="layui-colla-title">终审</h2>
							<div class="layui-colla-content ">
								<div style="height:80px;overflow:auto;">
									<div class="jsmenu">终审结果：<lable id="zsResult" style="font-weight: normal;"></lable></div>
									<div class="jsmenu">扣除金额：<lable id="zsDeductCost" style="font-weight: normal;"></lable></div>
								</div>
							</div>
						</div>
					</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
//全局变量
var type_no='';
var medical_all=new Object();
layui.config({
	   base: '<%=request.getContextPath() %>/plugins/layui/layuiadmin/'
	}).extend({
	    index: 'lib/index' //主入口模块
	}).use(['index', 'form','laydate','element','table'], function(){
		var form=layui.form;
		var element = layui.element;
		var table=layui.table;
		
		//折叠面板选项控制
		violationProcessController();
		//机审数据加载
		jsLoad();
		//初审数据加载
		csLoad();
		//稽核数据加载
		jhLoad();
		//终审数据加载
		zsLoad();
	});		
function child(typeNo,medicalAll){
	type_no=typeNo;
	medical_all=JSON.parse(medicalAll);
	console.log(medical_all);
	//金额赋值
	$("#itemCostTop").html("项目金额："+medical_all.totalCost);
	
}
//折叠面板显示控制（根据审核进程）
function violationProcessController(){
	$("#jhmb").hide();
	$("#gsmb").hide();
	$("#zsmb").hide();
	if(medical_all){
		if(medical_all.auditStatus){
			$("#jhmb").show();
		}
		if(medical_all.resultAppealStatu){
			$("#gsmb").show();
		}
		if(medical_all.finaStatus){
			if(medical_all.finaStatus!='待终审'){
				$("#zsmb").show();
			}
		}
	}
}
//机审动态赋值
function jsLoad(){
	var url=$WEB_ROOT_PATH+"/dhccApi/documentCenter/documentCenter/violationRecoder";
	var filed={"comType":'js',"medicalId":medical_all.id,"isMain":'main'};
	$.post(url,filed,function(result){
		var violationDetails=result.violationDetails;
		if(violationDetails){
			var isIlegal=medical_all.sysStatus;
			//isIlegal=isIlegal=='0'?'违规':(isIlegal=='1'?'疑似违规':'正常')
			$("#jsResult").html(isIlegal);
			if(isIlegal=='违规'||isIlegal=='疑似违规'){
				$("#jsDeductCost").html(medical_all.totalCost);
				$("#zsDeductCost").html(medical_all.totalCost);
			}else{
				$("#jsDeductCost").html(0);
			}
			$("#jsViolationType").empty();
			$("#jsViolationDes").empty();
			$("#csViolationType").empty();

			var divT=document.getElementById("jsViolationType");
			var divD=document.getElementById("jsViolationDes");
			var divT1=document.getElementById("csViolationType");

			//加一个新的空table  class="tablesh" style="margin-left:5px"
			var tableT=document.createElement("table");
			tableT.setAttribute("margin-left","5");
			tableT.setAttribute("class","tablesh");
			divT.appendChild(tableT)
			var tableD=document.createElement("table");
			tableD.setAttribute("margin-left","5");
			divD.appendChild(tableD)
			var tableT1=document.createElement("table");
			tableT1.setAttribute("margin-left","5");
			divT1.appendChild(tableT1)
			var str1="";
			for(var i=0;i<violationDetails.length;i++){
				//判断重复
				if(!toRepeat(str1,violationDetails[i].typeName)){
					createTable(tableT,tableD,violationDetails,i);
					var tr=document.createElement('tr');
					var td=document.createElement('td');
					td.innerHTML=i+1+"."+violationDetails[i].typeName;
					tr.appendChild(td);
					tableT1.appendChild(tr);
				}
				str1=str1+violationDetails[i].typeName;
			}
		}
	});
}
//拼接table
function createTable(tableT,tableD,violationDetails,i){
	var tr=document.createElement('tr');
	var td=document.createElement('td');
	td.innerHTML=i+1+"."+violationDetails[i].typeName;
	tr.appendChild(td);
	tableT.appendChild(tr);
	var tr=document.createElement('tr');
	var td=document.createElement('td');
	td.innerHTML=i+1+"."+violationDetails[i].returnDesc;
	tr.appendChild(td);
	tableD.appendChild(tr);
}
//typeName去重复
function toRepeat(str1,str2){
	if(str1.search(str2) != -1){
		return true
	}else{
		return false;
	}
}
//初审动态赋值
function csLoad(){
	var url=$WEB_ROOT_PATH+"/dhccApi/documentCenter/documentCenter/violationRecoder";
	var filed={"comType":'cs',"medicalId":medical_all.id,"isMain":'main'};
	$.post(url,filed,function(result){
		var medicalCostVerify=result.medicalCostVerify;
		console.log(medicalCostVerify);
		if(medicalCostVerify){
			var violationStatus=medicalCostVerify.violationStatus;
			violationStatus=violationStatus=='0'?'违规':(violationStatus=='1'?'疑似违规':'正常');
			$("#csResult").html(violationStatus);
			$("#csDeductCost").html(medicalCostVerify.withholdingAmount);
			$("#csViolationDes").empty();
			var divD1=document.getElementById("csViolationDes");
			var tableD1=document.createElement("table");
			tableD1.setAttribute("margin-left","5");
			tableD1.setAttribute("class","tablesh");
			divD1.appendChild(tableD1)
			var divD1=document.getElementById("csViolationDes");
			var tr=document.createElement('tr');
			var td=document.createElement('td');
			td.innerHTML=medicalCostVerify.deductions;
			tr.appendChild(td);
			tableD1.appendChild(tr);
			//最终扣除金额
			var dca=medicalDetailAll.deductions;
			var deductCost=dca?dca:medicalCostVerify.withholdingAmount;
			$("#deductCost").html("扣除金额："+deductCost);
		}
	});
}
//稽核动态赋值
function jhLoad(){
	var url=$WEB_ROOT_PATH+"/dhccApi/documentCenter/documentCenter/violationRecoder";
	var filed={"comType":'jh',"medicalId":medical_all.id,"isMain":'main'};
	$.post(url,filed,function(result){
		var medicalAuditVo=result.medicalAuditVo;
		if(medicalAuditVo){
			var aduitStatus=medicalAuditVo.aduitStatus;
			aduitStatus=aduitStatus=='0'?'违规':(violationStatus=='2'?'正常':violationStatus);
			$("#jhResult").html(aduitStatus);
			$("#jhDeductCost").html(medicalDetailAll.withholdingAmount);
			$("#jhDeductDesc").html(medicalDetailAll.deductions);
			$("#jhAuditSuggest").html(medicalAuditVo.auditOpinion);
			var fileurl = medicalAuditVo.fileurl;
			var fileResurl = medicalAuditVo.filenameurl;
			fileShow(fileurl,fileResurl,'jsData');
		}
	});
}
//公示动态赋值
function gsLoad(){
	var url=$WEB_ROOT_PATH+"/dhccApi/documentCenter/documentCenter/violationRecoder";
	var filed={"comType":'gs',"medicalId":medical_all.id,"isMain":'main'};
	$.post(url,filed,function(result){
		var resultAppeal=result.resultAppeal;
		if(resultAppeal){
			var examineComments=resultAppeal.examineComments;
			examineComments=examineComments=='0'?'违规':(examineComments=='2'?'正常':examineComments);
			$("#gsResult").html(examineComments);
			$("#gsDeductCost").html(medicalDetailAll.withholdingAmount);
			$("#gsDeductDesc").html(medicalDetailAll.deductions);
			fileurl =resultAppeal.fileUrl;
		  	fileResurl =resultAppeal.originalFilename;
			fileShow(fileurl,fileResurl,'gsData');
		}
	});
}
//材料显示
function fileShow(fileurl,fileResurl,divId){
	 $("#"+divId).empty();
	 var divT=document.getElementById(divId);
	//加一个新的空table  class="tablesh" style="margin-left:5px"
	 var table=document.createElement("table");
	 table.setAttribute("margin-left","5");
	 table.setAttribute("class","tablesh");
	 divT.appendChild(table)
	 
	 if(fileurl!=null && fileurl!="" && fileResurl!=null && fileResurl!="" ){
		   var imgs= fileurl.split(",");
		   var imgsNameRes = fileResurl.split(",");
		   for(var i=0;i<imgs.length;i++){
			   var tr=document.createElement("tr");
				var td=document.createElement("td");
				var nameFile=imgsNameRes[i].split(".")[1];
				
				var a=document.createElement("a");
				a.setAttribute("href",$WEB_ROOT_PATH+"/storeFile/"+imgs[i]);
				a.innerHTML =imgsNameRes[i];
				td.appendChild(a);
				
				tr.appendChild(td);
				table.appendChild(tr);
		   }
	 }
}
//终审动态加载
function zsLoad(){
	$("#zsResult").html(medical_all.finaStatus);
}
</script>
</body>
</html>