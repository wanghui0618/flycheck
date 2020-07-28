<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!-- el表达式 -->

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

<title>药品相互作用管理</title>
<style>
.layui-form-label{
	width:115px;
}
.td_3{
	text-align: right;
	color: #1D9A04;
}
td{
	word-break: break-all;/* 自动换行 */
	line-height: 30px;
	text-indent:0px;
	font-size: 15px;
	padding-left: 10px;
}
html{
	background-color:#fff;
	font-size:15px;
}
</style>
</head>
<body>
 <table id="list" border="0" width="94%" style="border: none;margin: 0 auto;margin-top: 8px;" cellpadding="0" cellspacing="0"> 
 <tr>
 	<td style="font-weight: bold;">药品名称：</td>
 	<td style="font-weight: bold;" id="drugMain">？？</td>
 </tr> 
 </table>
	
	<script>
	var name2="";
  layui.config({
    base: '<%=request.getContextPath() %>/plugins/layui/layuiadmin/'
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index','table'], function(){
	  refrechTable(name2);
  });
  
  function child(obj){
	  var cityOrg = JSON.parse(obj);
	  $("#id").val(cityOrg["id"]);
	  for (var index in cityOrg){
	      $("#"+index).val(cityOrg[index]);
	  }
  }
  function child1(obj){
	  var cityOrg = JSON.parse(obj);
	  name2=cityOrg["drugName"];
	  
  }
//动态填充table行列
	function addTableDetail(result){
		var tBody=document.getElementById("list");
		//var listMsg =$("#list").data("videodetial");
		console.log(result);
		var trmain=document.createElement("tr");
		var tdmain=document.createElement("td");
		tdmain.innerHTML = "药品名称：";
		var td1main=document.createElement("td");
		trmain.appendChild(tdmain);
		trmain.appendChild(td1main);
		
		var trmain1=document.createElement("tr");
		var tdmain1=document.createElement("td");
		tdmain1.innerHTML = "商品名称：";
		var td1main1=document.createElement("td");
		trmain1.appendChild(tdmain1);
		trmain1.appendChild(td1main1);
		
		var trmain2=document.createElement("tr");
		var tdmain2=document.createElement("td");
		tdmain2.innerHTML = "商品英文名称：";
		var td1main2=document.createElement("td");
		trmain2.appendChild(tdmain2);
		trmain2.appendChild(td1main2);
		
		var trmain3=document.createElement("tr");
		var tdmain3=document.createElement("td");
		tdmain3.innerHTML = "别名：";
		var td1main3=document.createElement("td");
		trmain3.appendChild(tdmain3);
		trmain3.appendChild(td1main3);
		
		var trmain4=document.createElement("tr");
		var tdmain4=document.createElement("td");
		tdmain4.innerHTML = "英文名称：";
		var td1main4=document.createElement("td");
		trmain4.appendChild(tdmain4);
		trmain4.appendChild(td1main4);
		
		var trmain5=document.createElement("tr");
		var tdmain5=document.createElement("td");
		tdmain5.innerHTML = "汉语拼音：";
		var td1main5=document.createElement("td");
		trmain5.appendChild(tdmain5);
		trmain5.appendChild(td1main5);
		
		var trmain6=document.createElement("tr");
		var tdmain6=document.createElement("td");
		tdmain6.innerHTML = "主要成分：";
		var td1main6=document.createElement("td");
		trmain6.appendChild(tdmain6);
		trmain6.appendChild(td1main6);
		
		
		tBody.appendChild(trmain);
		tBody.appendChild(trmain1);
		tBody.appendChild(trmain2);
		tBody.appendChild(trmain3);
		tBody.appendChild(trmain4);
		tBody.appendChild(trmain5);
		tBody.appendChild(trmain6);
		for(var i=0;i<result.length;i++){
			
			td1main.innerHTML = result[i].drugName;
			td1main1.innerHTML = result[i].tradeName;
			td1main2.innerHTML = result[i].tradeEnglishName;
			td1main3.innerHTML = result[i].alias;
			td1main4.innerHTML = result[i].englisName;
			td1main5.innerHTML = result[i].chinesePinyin;
			td1main6.innerHTML = result[i].mainComponents;
			
			var tr3=document.createElement("tr");
			var td3=document.createElement("td");
			td3.innerHTML = '<center><hr width="100%" style= "height:1px;border:none;border-top:1px dashed #eee;background: center;" /></center>';
			td3.lineHeight="0px";
			var td13=document.createElement("td");
			td13.innerHTML = "";
			tr3.appendChild(td3);
			//tr3.appendChild(td13);
			td3.colSpan="2";
			tBody.appendChild(tr3);
			
			var tr=document.createElement("tr");
			var td=document.createElement("td");
			td.innerHTML = i+1+".相互作用药品名称：";
			td.width="150px";
			td.setAttribute("class","td_3");
			var td1=document.createElement("td");
			td1.innerHTML = result[i].interactionDrugName;
			tr.appendChild(td);
			tr.appendChild(td1);
			tBody.appendChild(tr);
			
			
			tr=document.createElement("tr");
			td=document.createElement("td");
			td.width="150px";
			td.setAttribute("class","td_3");
			td.innerHTML = "相互作用效果：";
			td1=document.createElement("td");
			td1.innerHTML = result[i].interactionEffect;
			tr.appendChild(td);
			tr.appendChild(td1);
			tBody.appendChild(tr);
			
			tr=document.createElement("tr");
			td=document.createElement("td");
			td.width="150px";
			td.setAttribute("class","td_3");
			td.innerHTML = "临床建议：";
			td1=document.createElement("td");
			td1.innerHTML = result[i].clinicalRecommendations;
			tr.appendChild(td);
			tr.appendChild(td1);
			tBody.appendChild(tr);
			
			tr=document.createElement("tr");
			td=document.createElement("td");
			td.width="150px";
			td.setAttribute("class","td_3");
			td.innerHTML = "临床证据：";
			td1=document.createElement("td");
			td1.innerHTML = result[i].clinicalEvidence;
			tr.appendChild(td);
			tr.appendChild(td1);
			tBody.appendChild(tr);
			
			tr=document.createElement("tr");
			td=document.createElement("td");
			td.width="150px";
			td.setAttribute("class","td_3");
			td.innerHTML = "证据级别：";
			td1=document.createElement("td");
			td1.innerHTML = result[i].evidenceLevel;
			tr.appendChild(td);
			tr.appendChild(td1);
			tBody.appendChild(tr);
			
			tr=document.createElement("tr");
			td=document.createElement("td");
			td.width="150px";
			td.setAttribute("class","td_3");
			td.innerHTML = "参考文献：";
			td1=document.createElement("td");
			td1.innerHTML = result[i].reference;
			tr.appendChild(td);
			tr.appendChild(td1);
			tBody.appendChild(tr);
			
			
		}
	}

function refrechTable(drugName){
	removeTable();
	var url = $WEB_ROOT_PATH+'/dhccApi/druginteraction/drugInteraction/findDate?drugInteraction.drugName='+drugName;
	$.post(url,function(result) {
			//console.log(result);
			addTableDetail(result);
		
	});
}
function removeTable(){
	var tb = document.getElementById('list');
    var rowNum=tb.rows.length;
    for (i=0;i<rowNum;i++)
    {
        tb.deleteRow(i);
        rowNum=rowNum-1;
        i=i-1;
    }
}
  </script>
</body>
</html>