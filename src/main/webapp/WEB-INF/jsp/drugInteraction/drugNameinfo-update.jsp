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
  	<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css" media="all">
  	<script src="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
  	<script src="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/lay/modules/layer.js" type="text/javascript" charset="utf-8"></script>
  	
  	 <link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/modules/layer/default/layer.css" media="all">
		
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css"
	media="all">
	
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css"
	media="all">

<title>药品相互作用管理</title>
<style>
.layui-form-label {
	width: 115px;
}
.layui-input-inline{
width:140px;
}
table.table {
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
	color: #333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
	margin:-5px 43px;
	width:1300px;
}

table.table th {
	border-width: 1px;
	padding: 4px;
	border-style: solid;
	font-size: 14px;
	border-color: #666666;
	background-color: #dedede;
	line-height: 20px;
}

table.table td {
	border-width: 1px;
	padding: 4px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
	line-height: 25px;
}
table.table td input{
	line-height: 23px;
	width:185px;
}
.layui-form input[type=checkbox], .layui-form input[type=radio], .layui-form select {
    display: inline;
}
.layui-form-checkbox {
    display: none;
}
</style>
</head>
<body>
	<div class="layui-form" lay-filter="layuiadmin-form-useradmin"
		id="layuiadmin-form-studentadmin" style="padding: 10px 0 0 0;">

		<input type="hidden" name="drugInteraction.id" id="id">

		<div class="layui-form-item">
			<label class="layui-form-label">药品通用名称</label>
			<div class="layui-input-inline">
				<input type="text" id="drugName" name="drugInteraction.drugName"
					lay-verify="required" placeholder="请输入药品通用名称 "autocomplete="off"
					class="layui-input">
			</div>
			<label class="layui-form-label">商品名称</label>
			<div class="layui-input-inline">
				<input type="text" id="tradeName" name="drugInteraction.tradeName"
					 placeholder="请输入商品名称 "autocomplete="off"
					class="layui-input">
			</div>
		
		
			<label class="layui-form-label">商品英文名称</label>
			<div class="layui-input-inline">
				<input type="text" id="tradeEnglishName" name="drugInteraction.tradeEnglishName"
					 placeholder="请输入商品英文名称 "autocomplete="off"
					class="layui-input">
			</div>
			<label class="layui-form-label" style="width:50px">别名</label>
			<div class="layui-input-inline">
				<input type="text" id="alias" name="drugInteraction.alias"
					 placeholder="请输入别名 "autocomplete="off"
					class="layui-input">
			</div>
		
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">英文名称</label>
			<div class="layui-input-inline">
				<input type="text" id="englisName" name="drugInteraction.englisName"
					 placeholder="请输入英文名称 "autocomplete="off"
					class="layui-input">
			</div>
			<label class="layui-form-label">汉语拼音</label>
			<div class="layui-input-inline">
				<input type="text" id="chinesePinyin" name="drugInteraction.chinesePinyin"
					 placeholder="请输入汉语拼音 "autocomplete="off"
					class="layui-input">
			</div>
		
		
			<label class="layui-form-label">主要成分</label>
			<div class="layui-input-inline">
				<input type="text" id="mainComponents" name="drugInteraction.mainComponents"  placeholder="请输入主要成分" autocomplete="off"class="layui-input">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">相互作用药品：</label>
		</div>
		<div class="layui-form-item">
			<table class="table" id="drugInteractionTable">
		        <thead>
		            <tr>
		                <th>选择</th>
		                <th>相互作用药品名称</th>
		                <th>相互作用效果</th>
		                <th>临床建议</th>
		                <th>临床证据</th>
		                <th>证据级别</th>
		                <th>参考文献</th>
		            </tr>
		        </thead>
		        <tbody id="trlist">
		            <tr id="tr">
		                <td><input type="checkbox" name="checkbox" style="width:30px"/></td>
		                <td><input type="text" id="interactionDrugName" name="drugInteractions[0].interactionDrugName" /></td>
		                <td><input type="text" id="interactionEffect" name="drugInteractions[0].interactionEffect"/></td>
		                <td><input type="text" id="clinicalRecommendations" name="drugInteractions[0].clinicalRecommendations"/></td>
		                <td><input type="text" id="clinicalEvidence" name="drugInteractions[0].clinicalEvidence"/></td>
		                <td><input type="text" id="evidenceLevel" name="drugInteractions[0].evidenceLevel"/></td>
		                <td><input type="text" id="reference" name="drugInteractions[0].reference"/></td>
		            </tr>
		        </tbody>
		    </table>
		    <div style="margin:10px 43px;">
		        <input type="button" id="addrow" value="新增" />
		        <input type="button" id="removerow" value="删除" />
		    </div>
		</div>
		
		
		
		
		
		

		<div class="layui-form-item layui-hide">
			<input type="button"  lay-submit lay-filter="LAY-cityprice-front-submit"
				id="LAY-cityprice-front-submit" value="确认">
		</div>
	</div>
	
  <script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.2.1.1.js"></script>

	<script>
	
	$(function(){
	      $("#addrow").click(addrow);//绑定添加事件
	      $("#removerow").click(removerow);//绑定删除事件。
	  });
	layui.config({
	    base: '<%=request.getContextPath() %>/plugins/layui/layuiadmin/'
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'form','laydate'], function(){
		  var form=layui.form;
		
	  })
  
  
  
  
  var trlisthtml = $("#trlist").html();//获取默认的一行tr，用作复制
  function addrow(){//增加
      $(".table>tbody:last").append(trlisthtml);//向tbody最后添加一行tr.
  }
  
  function removerow(){//移除
      $('input[name="checkbox"]:checked').each(function(){
		    $(this).parent().parent().remove();//移除当前行 checkbox的父级是td，td的父级是tr，然后删除tr。就ok了。用each，选择多行遍历删除
      });
  }
  //按钮点击事件
  /* $("#LAY-cityprice-front-submit").click(function(){
	  var httpurl =getContextPath();
	 var url=httpurl+"/dhccApi/druginteraction/drugInteraction/saveOrUpdate"; 
	 
	 var list= getFileds();
	 var filed={"drugInteractions":list};
	 $.post(url,filed,function(result){
		 
	 }); 
  
  })*/
  //新增tr完成后，修改input标签name
  function updateName(){
	  var i=0;
	  $("#drugInteractionTable tbody tr").each(function(){
          var a = $(this).children();//获取每一行
          console.log(a.eq(1).find("input"));
          a.eq(1).find("input").attr("name","drugInteractions["+i+"].interactionDrugName");
          a.eq(2).find("input").attr("name","drugInteractions["+i+"].interactionEffect");
          a.eq(3).find("input").attr("name","drugInteractions["+i+"].clinicalRecommendations");
          a.eq(4).find("input").attr("name","drugInteractions["+i+"].clinicalEvidence");
          a.eq(5).find("input").attr("name","drugInteractions["+i+"].evidenceLevel");
          a.eq(6).find("input").attr("name","drugInteractions["+i+"].reference");
          i++; 
	  });   
  }
  function child(obj){
	  var cityOrg = JSON.parse(obj);
	  $("#id").val(cityOrg["id"]);
	  for (var index in cityOrg){
	      $("#"+index).val(cityOrg[index]);
	  }
	  //table动态回显
	  var httpurl =getContextPath();
	  var url=httpurl+"/dhccApi/druginteraction/drugInteraction/findDate"; 
	  var filed={"drugInteraction.id":cityOrg["id"],"drugInteraction.drugName":cityOrg["drugName"]};
	  $.post(url,filed,function(result){
		console.log(result);
		//拼接
		if(!result){
			return;
		}
		 
		 var table=document.getElementById("drugInteractionTable");
		 for(var i=1;i<result.length;i++){
			 addrow();
		 };
		 //debugger;
		updateName();
		 var i=0;
		 $("#drugInteractionTable tbody tr").each(function(){
	          var a = $(this).children();//获取每一行
	          a.eq(1).find("input").val(result[i].interactionDrugName);
	          a.eq(2).find("input").val(result[i].interactionEffect);
	          a.eq(3).find("input").val(result[i].clinicalRecommendations);
	          a.eq(4).find("input").val(result[i].clinicalEvidence);
	          a.eq(5).find("input").val(result[i].evidenceLevel);
	          a.eq(6).find("input").val(result[i].reference);
	          i++;
	          console.log(a);
		});
	  });
	  
  }

  //封装参数
  function getFileds(){
	  var list=[];
	  //遍历table
	  $("#drugInteractionTable tbody tr").each(function(){
            var a = $(this).children();//获取每一行
           
            var drugInteraction ={
            drugName:$("#drugName").val(),
            tradeName:$("#tradeName").val(),
            tradeEnglishName:$("#tradeEnglishName").val(),
            alias:$("#alias").val(),
            englisName:$("#englisName").val(),
            chinesePinyin:$("#chinesePinyin").val(),
            mainComponents:$("#mainComponents").val(),
            
            interactionDrugName:a.eq(1).find("input").val(),
            interactionEffect:a.eq(2).find("input").val(),
            clinicalRecommendations:a.eq(3).find("input").val(),
            clinicalEvidence:a.eq(4).find("input").val(),
            evidenceLevel:a.eq(5).find("input").val(),
            reference:a.eq(6).find("input").val()
            };
           
          /*   var str="";
          str='{ drugInteraction.drugName :'+drugName+', drugInteraction.tradeName :'+tradeName+', drugInteraction.tradeEnglishName :'+tradeEnglishName;
          str=str+', drugInteraction.alias :'+alias+' , drugInteraction.englisName :'+englisName+' , drugInteraction.chinesePinyin :'+chinesePinyin;
          str=str+', drugInteraction.mainComponents :'+mainComponents+', drugInteraction.interactionDrugName :'+interactionDrugName+', drugInteraction.interactionEffect :'+interactionEffect;
          str=str+', drugInteraction.clinicalRecommendations :'+clinicalRecommendations+', drugInteraction.clinicalEvidence :'+clinicalEvidence+', drugInteraction.evidenceLevel :'+evidenceLevel+', drugInteraction.reference :'+reference+' }';
　　　　　　	list[i++]=str; */
          list.push(drugInteraction);
       });
	  console.log(list);
	  console.log(JSON.stringify(list).toString());
	  return list;
  }
  function getContextPath(){
		var strFullPath=window.document.location.href;
		var strPath=window.document.location.pathname;
		var pos=strFullPath.indexOf(strPath);
		var prePath=strFullPath.substring(0,pos);
		var postPath=strPath.substring(0,strPath.substr(1).indexOf('/')+1);
		var basePath = prePath;
		//if(canBeAccess(prePath + postPath)){
			basePath = prePath + postPath;
		//}
		return basePath;
	}
  </script>
</body>
</html>