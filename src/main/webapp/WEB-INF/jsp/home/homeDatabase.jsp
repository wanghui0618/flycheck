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
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/knowledge/index.css">

<title>人保智能审核系统</title>
<style>
.layui-table td, .layui-table th {
    position: relative;
    padding: 9px 15px;
    min-height: 20px;
    line-height: 24px;
    font-size: 14px;
}
.rd_table{
	width:100%;
	margin:0 auto;
}
/* .rd_table td{
	border:1px solid #eee;
} */
.select_search{
	width: 120px;
	display: block;
	float: left;
	margin-left:0px;
}
.select_search input{
	border:1px solid #eee;
	height: 45px;
}
.layui-card-header {
	padding:3px 3px; 
}
.curr {
    color: #0171bb;
    font-style: italic;
    font-weight: bolder;
}
.skin_knw .part {
    background: url(images/knw_top_bg.jpg) repeat-x;
}

.part .hd {
    background-color: #F7F7F7;
    background-position: 0 -11px;
    background-repeat: repeat-x;
    border-color: #CCE5B8;
    border-style: solid solid none;
    border-width: 1px 1px medium;
    padding-left: 10px;
    border-radius: 4px;
    border-bottom-left-radius: 0;
    border-bottom-right-radius: 0;
}
.layui-card .layui-tab-brief .layui-tab-title {
    height: 45px;
    border-bottom-color: #f6f6f6;
}
.layui-card .layui-tab-brief .layui-tab-content {
    padding: 2px;
}
.layui-card .layui-tab-brief .layui-tab-title li.layui-this {
    color: #2284FF;
}
.layui-icon {
    color: #2284FF;
}
</style>
</head>
<body style="overflow-x:hidden;">
<form class="layui-form" action="">
<div class="layui-fluid" style="overflow-y:hidden;">
    <div class="layui-row layui-col-space15">
      <div class="layui-col-md12">
        <div class="layui-card">
          <div class="layui-card-body layui-text layadmin-text">
							<div id="parentDiv">
								<div class="layui-input-block select_search" style="font-size: 14px; margin-top: 18px; margin-left: 20px; width: 170px; height: 43px; padding-left: 5px">
									<select name="interest"  id="Type">
										<option value="" selected="">全部</option>
									</select>
								</div> 
								<input id="title" maxlength="40" class="validatebox" data-options="required:true" name="question.title"
									style="font-size: 14px; margin-left: 10px; margin-top: 18px; width: 590px; height: 43px; padding-left: 20px"
									placeholder="搜行业或医药知识（综合搜索）" ></input>
								<button id="title"
									style="background-color: #2284FF; border-color: #2284FF;  margin-left: 10px; margin-top: 18px; height: 45px; width: 80px; color: #fff"
									class="btn btn-info" type="button" onclick="check()">搜索
								</button>
								<span style="margin-left: 20px;">热门关键字：</span><span id="hotKeys"></span>
							</div>
							<div id="div_items" style="display: none ;">
								<div></div>
							</div>
							<div>
              	<table style="width: 100%; margin-top: 18px; margin-bottom: 18px;  margin-left: 20px; ">
              			<tr style="height: 32px">
              			<td width="19%" href="javascript:;" lay-href="<%=request.getContextPath()%>/icd/icdinfo">
              			疾病分类与代码（ICD）&nbsp;<span  id="T_PICCBID_DICT_ICD"></span></td>
              			<td width="19%" href="javascript:;" lay-href="<%=request.getContextPath()%>/druglnstmction/druglnstmctioninfo" >
              			药品说明书&nbsp;&nbsp;<span id="T_PICCBID_DRUG_LNSTMCTION"></span></td>
              			<td width="19%" href="javascript:;" lay-href="<%=request.getContextPath()%>/clinicalguideline/clinicalguideline" >
              			临床指南&nbsp;&nbsp;<span id="T_PICCBID_CLINICAL_GUIDELINE"></span></td>
              			<td width="19%" href="javascript:;" lay-href="<%=request.getContextPath()%>/clinicalPathway/clinicalPathway" >
              			临床路径&nbsp;&nbsp;<span id="T_PICCBID_CLINICALPATH"></span></td>
              			<td width="19%" href="javascript:;" lay-href="<%=request.getContextPath()%>/druguse/druguseinfo" >
              			合理用药&nbsp;&nbsp;<span id="T_PICCBID_DRUGUSE"></span></td>
              			
              		</tr>
              			<tr style="padding-top: 14px; height: 32px;">
              			<td style="padding-top: 10px;" href="javascript:;" lay-href="<%=request.getContextPath()%>/druginteraction/drugInteraction">
              			药物相互作用&nbsp;&nbsp;<span id="T_PICCBID_DRUG_INTERACTION"></span></td>
              			<td style="padding-top: 10px;" href="javascript:;" lay-href="<%=request.getContextPath()%>/excitant/excitantinfo">
              			兴奋剂禁用目录&nbsp;&nbsp;<span id="T_PICCBID_EXCITANT"></span></td>
              			<td style="padding-top: 10px;" href="javascript:;" lay-href="<%=request.getContextPath()%>/pharmacy/pharmacyinfo">
              			辅助与重点监控用药&nbsp;&nbsp;<span id="T_PICCBID_PHARMACY"></span></td>
              			<td style="padding-top: 10px;" href="javascript:;" lay-href="<%=request.getContextPath()%>/knowledge/knowledgeInfo">
              			医药知识库&nbsp;&nbsp;<span id="T_PICCBID_KNOWLEDGE"></span></td>
              		</tr>
              	</table>
              </div>
						</div>
        </div>
      </div>
      
      <div class="layui-col-md12">
          <div class="layui-card" style="min-height:247px">
            <div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;"><img style="margin-left: 15px;margin-top:-3px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
            	分类浏览
            	<span><button id="indexDatabaseHome-edit"
						class="layui-btn layui-btn-normal layui-btn-xs"
						href="javascript:;"
						lay-href="<%=request.getContextPath()%>/knowledge/knowledgeInfo"
						lay-tips="知识库编辑" style="background-color:#2284FF;float: right; margin-right: 5px; margin-top: 5px">
						知识库编辑
					</button>
				</span>
            </div>
            <div class="layui-card-body">
            	<div class="sortBrow">
					<div class="bd clearfix" id="hotType">
					</div>
				</div>
			</div>
          </div>
      </div>
      
      
      <div class="layui-col-md12">
        <div class="layui-row layui-col-space15">
          <div class="layui-col-md4">
            <div class="layui-card">
              <div class="layui-card-body">
              <div class="layui-tab layui-tab-brief">
					  <ul class="layui-tab-title">
					    <li class="layui-this">今日推送</li>
					    <li>本周热点</li>
					    <li>本月热点</li>
					  </ul>
					  <div class="layui-tab-content">
					    <div class="layui-tab-item layui-show">
												<table class="layui-table">
													<colgroup>
														<col width="10">
														<col>
													</colgroup>
													<tbody id="Day">

													</tbody>
												</table>									
											</div>
					    <div class="layui-tab-item">
					    	<table class="layui-table">
													<colgroup>
														<col width="10">
														<col>
													</colgroup>
													<tbody id="Week">

													</tbody>
												</table>	
												</div>
					    <div class="layui-tab-item">	
					    <table class="layui-table">
													<colgroup>
														<col width="10">
														<col>
													</colgroup>
													<tbody id="Month">

													</tbody>
												</table>	
												</div>
					  </div>
					</div>     	
              </div>
            </div>
          </div>
          <div class="layui-col-md4">
            <div class="layui-card">
              <div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;"><img style="margin-left: 15px;margin-top:-3px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
	            	最新知识
	          </div>
              <div class="layui-card-body">
                					<table class="layui-table">
													<colgroup>
														<col width="10">
														<col>
													</colgroup>
													<tbody id="newAdd">
										
													</tbody>
												</table>
              </div>
            </div>
          </div>
          <div class="layui-col-md4">
            <div class="layui-card">
              <div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;"><img style="margin-left: 15px;margin-top:-3px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
	            	推荐知识
	          </div>
              <div class="layui-card-body">
                					<table class="layui-table">
													<colgroup>
														<col width="10">
														<col>
													</colgroup>
													<tbody id="top10">
														
													</tbody>
												</table>
              </div>
            </div>
          </div>
      </div>
    </div>
  </div>
  </div> 
  </form>

  <script>
  layui.config({
    base: '<%=request.getContextPath() %>/plugins/layui/layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'console','form'], function(){
	   var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;
	   
		//文章title行点击事件
		$(".listArticle").on("click",function(e){
			//console.info(e);
			
		});
	//加载分类
		 $.getJSON($WEB_ROOT_PATH+'/dhccApi/dict/dict/findDict?dictRequestVo.tableName=T_PICCBID_KNOWLEDGE', 
					function(data){
			     		var  dataList= data.dictList;
			     		for(var i=0 ;i<dataList.length;i++){
			     			var mm="<option value='"+dataList[i].value+"'>"+dataList[i].value+"</option>";
			     			$("#Type").append(mm); 
			     		}
			    		form.render('select');
		 });
	  
		//文本框输入
	 	$('#title').bind('input propertychange', function() {
	 		$("#div_items").css('display', 'block');//只要输入就显示列表框

	 		if ($("#title").val().length <= 0) {
	 			$(".div_item").css('display', 'none');//如果什么都没填，全部隐藏
	 			return;
	 		}
	 		$(".div_item").css('display', 'none');//如果填了，先将所有的选项隐藏
	 		var keywords=$("#title").val();

	 		var typeSmall=$("#Type").val();	
	 		
	 		$.getJSON($WEB_ROOT_PATH+'/dhccApi/knowledge/knowledge/findHotKeys?knowledge.keywords='+keywords+'&knowledge.typeSmall='+typeSmall,function(data){
	 			//var str="<div style='height:15px;line-height:15px;padding-left: 8px;font-size:12px;align:center;' class='div_item'>文章标题：</div>";
	 
	 			//var num = data.length;
	 			//var str="<div style='height:35px;line-height:35px;padding-left: 8px;' class='div_item'>相关文章标题：</div>";
	 			
	 			var str=""
	 			for( var key in data ){
	 				var value=data[key];
	 				str+="<div style='height:35px;line-height:35px;padding-left: 8px;' typeName='"+value+"' class='div_item'>"+key+"</div>";
	 			}
	 			document.getElementById('div_items').innerHTML=str;
	 			// 解决样式不生效
	 			$("#div_items").css({"width":"610px","margin-left": "205px","background-color":"white","border":"1px solid #66afe9","border-top":"0px","position":"absolute","z-index":"999","margin-top":"-0.5px"});
	 			//移入移出效果
	 			$(".div_item").hover(function () {
	 				$(this).css('background-color', '#1C86EE').css('color', 'white');
	 			}, function () {
	 				$(this).css('background-color', 'white').css('color', 'black');
	 			});

	 			
	 	
	 			
	 			//行点击事件
  		    $(".div_item").on("click",function(e){
  		        //console.info($(this).attr("id"));
  		    	var type=$(this).attr("typeName");
  		    	var title=$(this).html();
  		   
  		    	if(type==2){
  		    		checkC(title);
  		    	}else{
  		    		checkB(type);
  		    	}
  		    	
  		    	$("#title").val(title);
  		    /*	//增加浏览次数+1
  		    	addViews($(this).attr("id"));
  		    	e.stopPropagation();*/
  		    });
  			
	 		});
	 		 //隐藏列表框
	 	    $("body").click(function () {
		        $("#div_items").css('display', 'none');
		    });

	 	});

	  
	//罗列所有大类
	  var url = $WEB_ROOT_PATH+'/dhccApi/knowledge/knowledge/listAllMainType';
	 	postReq(url,'', function(data){
	 		//console.log(data);
	 		var text="";
	 		var i=0;
	 		for(;i<data.length;i++){	
	 			var dataType = data[i];
	 			var str="checkB('"+dataType+"')";
	 			switch(i) {
		 		     case 0:text+='<dl style="height:80px;border:1px dashed #C2D9F5;">';break;
		 		     case 1:text+='<dl style="height:80px;border:1px dashed #F5CEC1;">';break;
		 		     case 2:text+='<dl style="height:80px;border:1px dashed #ABEACE;">';break;
		 		     case 3:text+='<dl style="height:80px;border:1px dashed #C9BFF6;">';break;
		 		     default:text+='<dl style="height:80px;border:1px dashed #C2D9F5;">';break;
		 		} 
	 			text+='<dt style="height:78px ;width:120px ;" >';
	 			switch(i) {
		 		     case 0:text+='<span style="width:100px;height:30px;background: #E8F2FF;display:block;text-align: center;line-height: 30px;"><a style="font-size:16px;color:#2284FF"';break;
		 		     case 1:text+='<span style="width:100px;height:30px;background: #FEF3E5;display:block;text-align: center;line-height: 30px;"><a style="font-size:16px;color:#EB6D45"';break;
		 		     case 2:text+='<span style="width:100px;height:30px;background: #E5F7EF;display:block;text-align: center;line-height: 30px;"><a style="font-size:16px;color:#00B466"';break;
		 		     case 3:text+='<span style="width:100px;height:30px;background: #E6E1FA;display:block;text-align: center;line-height: 30px;"><a style="font-size:16px;color:#826CE4"';break;
		 		     default:text+='<span style="width:100px;height:30px;background: #E6E1FA;display:block;text-align: center;line-height: 30px;"><a style="font-size:16px;color:#826CE4"';break;
	 			} 
	 			text+=' onclick='+str+'>'+dataType+'</a></span></dt><dd id="'+dataType+'">';
	 			//罗列所有小分类
	 			$.getJSON($WEB_ROOT_PATH+'/dhccApi/knowledge/knowledge/listAllSecondType?knowledge.typeSmall='+dataType,function(list){
	 				var textSecond="";
					 for(var i=0;i<list.length;i++){
							var knowledge = list[i];
							var id = knowledge['id'];
							var title = knowledge['title'];
					        var titleLength=title.length;
				         	var dataType=knowledge['typeSmall']
					        var	strId="checkC('"+id+"')";
				         	
				         	var reg =/^[a-zA-Z/-0-9/+]*$/;
				         	var regTitle=title.substring(0,14);
				         	regTitle=regTitle.replace(/\s+/g,"");	
				         	if(reg.test(regTitle)){
				         		if(titleLength>12){
					              	title=title.substring(0,12)+"...";
					             }					           
				         	}else{
				         		if(titleLength>6){
					              	title=title.substring(0,6)+"...";
					             }
				         	} 		
				         	console.info(title);
				           textSecond+='<dt style="width:33%;margin:0;margin-top:10px;"><a style="padding-left: 3px; width:100% ; font-size:14px;font-weight: normal;color:#353535;" onclick='+strId+'>'+title+'</a></dt>';
						
					 }
					 document.getElementById(dataType).innerHTML=textSecond;	 	
			});
				 			
	 			text+='</dd></dl>';
	 		} 		 		
	 		document.getElementById("hotType").innerHTML=text;	 		
	 	},function(){
	 	/*	$CommonUI.alert("失败!");*/
	 	}, {
	 		skipHidden : false
	 	});
	 	
 
		//查询各知识库总数据量
		$.getJSON($WEB_ROOT_PATH+'/dhccApi/knowledge/knowledge/countArticleNumber',function(articleNumber){
			//console.log(articleNumber);
			var text="";
			for(var key in articleNumber ){			
				var	articleNum = articleNumber[key];
				//text='<span>['+articleNum+']</span>';
				text='['+articleNum+']';
				document.getElementById(key).innerHTML=text;		
			} 		 		
		});	
		
		//List今日热点article
		ListArticleUrl=$WEB_ROOT_PATH+'/dhccApi/knowledge/knowledge/listArticle?inflag=1';
		$.getJSON(ListArticleUrl,function(article){
			var text="";
			 for(var i=0;i<(article.length<5?article.length:5);i++){
					var knowledge = article[i];
					var id = knowledge['id'];
					var title = knowledge['title'];
					var titleLength=title.length;
					var reg =/^[a-zA-Z/-0-9/+]*$/;
		         	var regTitle=title.substring(0,50);
		         	regTitle=regTitle.replace(/\s+/g,"");	
		           	if(reg.test(regTitle)){
		         		if(titleLength>45){
			              	title=title.substring(0,44)+"...";
			             }					           
		         	}else{
		         		if(titleLength>22){
			              	title=title.substring(0,21)+"...";
			             }
		         	} 
					var createDate = knowledge['createDate'];
					var	strId="checkC('"+id+"')";
					text+='<tr  style="margin-left: 20px; margin-top: 15px;" onclick='+strId+' ><td class="listArticle"><a class="layui-icon layui-icon-read"></a><span style="height: 30px; margin-left: 8px; font-size: 14px;">'+title+'</span><span style="font-size: 14px; height: 20px; float: right;">'+createDate+'</span></td></tr>';	
				} 			
			document.getElementById("Day").innerHTML=text;
		});	
		//List每周热点article
		ListArticleUrl=$WEB_ROOT_PATH+'/dhccApi/knowledge/knowledge/listArticle?inflag=2';
		$.getJSON(ListArticleUrl,function(article){
			var text="";
			 for(var i=0;i<(article.length<5?article.length:5);i++){
					var knowledge = article[i];
					var id = knowledge['id'];
					var title = knowledge['title'];
					var titleLength=title.length;
					var reg =/^[a-zA-Z/-0-9/+]*$/;
		         	var regTitle=title.substring(0,50);
		         	regTitle=regTitle.replace(/\s+/g,"");	
		         	if(reg.test(regTitle)){
		         		if(titleLength>45){
			              	title=title.substring(0,44)+"...";
			             }					           
		         	}else{
		         		if(titleLength>22){
			              	title=title.substring(0,21)+"...";
			             }
		         	} 
					var createDate = knowledge['createDate'];
					var	strId="checkC('"+id+"')";
					text+='<tr  style="margin-left: 20px; margin-top: 15px;" onclick='+strId+' ><td class="listArticle"><a class="layui-icon layui-icon-read"></a><span style="height: 30px; margin-left: 8px; font-size: 14px;">'+title+'</span><span style="font-size: 14px; height: 20px; float: right;">'+createDate+'</span></td></tr>';	
				} 			
			document.getElementById("Week").innerHTML=text;
		});	
		//List每月热点article
		ListArticleUrl=$WEB_ROOT_PATH+'/dhccApi/knowledge/knowledge/listArticle?inflag=3';
		$.getJSON(ListArticleUrl,function(article){
			var text="";
			 for(var i=0;i<(article.length<5?article.length:5);i++){
					var knowledge = article[i];
					var id = knowledge['id'];
					var title = knowledge['title'];
					var titleLength=title.length;
					var reg =/^[a-zA-Z/-0-9/+]*$/;
		         	var regTitle=title.substring(0,50);
		         	regTitle=regTitle.replace(/\s+/g,"");	
		           	if(reg.test(regTitle)){
		         		if(titleLength>45){
			              	title=title.substring(0,44)+"...";
			             }					           
		         	}else{
		         		if(titleLength>22){
			              	title=title.substring(0,21)+"...";
			             }
		         	} 
					var createDate = knowledge['createDate'];
					var	strId="checkC('"+id+"')";
					text+='<tr  style="margin-left: 20px; margin-top: 15px;" onclick='+strId+' ><td class="listArticle"><a class="layui-icon layui-icon-read"></a><span style="height: 30px; margin-left: 8px; font-size: 14px;">'+title+'</span><span style="font-size: 14px; height: 20px; float: right;">'+createDate+'</span></td></tr>';	
				} 		
			document.getElementById("Month").innerHTML=text;
		});	
		
		//List最新新增文章
		ListArticleUrl5=$WEB_ROOT_PATH+'/dhccApi/knowledge/knowledge/listArticle?inflag=5';
		$.getJSON(ListArticleUrl5,function(article){
			var text="";
			 for(var i=0;i<(article.length<5?article.length:5);i++){
				var knowledge = article[i];
				var id = knowledge['id'];
				var title = knowledge['title'];
				var titleLength=title.length;
			 	var reg =/^[a-zA-Z/-0-9/+]*$/;
	         	var regTitle=title.substring(0,50);
	         	regTitle=regTitle.replace(/\s+/g,"");	
	           	if(reg.test(regTitle)){
	         		if(titleLength>45){
		              	title=title.substring(0,44)+"...";
		             }					           
	         	}else{
	         		if(titleLength>22){
		              	title=title.substring(0,21)+"...";
		             }
	         	} 
				var createDate = knowledge['createDate'];
				var	strId="checkC('"+id+"')";
				text+='<tr  style="margin-left: 20px; margin-top: 15px;" onclick='+strId+' ><td class="listArticle"><a class="layui-icon layui-icon-read"></a><span style="height: 30px; margin-left: 8px; font-size: 14px;">'+title+'</span><span style="font-size: 14px; height: 20px; float: right;">'+createDate+'</span></td></tr>';	
			} 
				
			document.getElementById("newAdd").innerHTML=text;
		});	
		
		 var ListArticleUrl4=$WEB_ROOT_PATH+'/dhccApi/knowledge/knowledge/listArticle?inflag=4';
		$.getJSON(ListArticleUrl4,function(article){
			var text="";
			 for(var i=0;i<(article.length<5?article.length:5);i++){
					var knowledge = article[i];
					var id = knowledge['id'];
					var title = knowledge['title'];
					var titleLength=title.length;
					var reg =/^[a-zA-Z/-0-9/+]*$/;
		         	var regTitle=title.substring(0,50);
		         	regTitle=regTitle.replace(/\s+/g,"");	
		           	if(reg.test(regTitle)){
		         		if(titleLength>45){
			              	title=title.substring(0,44)+"...";
			             }					           
		         	}else{
		         		if(titleLength>22){
			              	title=title.substring(0,21)+"...";
			             }
		         	} 
					var createDate = knowledge['createDate'];
					var	strId="checkC('"+id+"')";
					text+='<tr  style="margin-left: 20px; margin-top: 15px;" onclick='+strId+' ><td class="listArticle"><a class="layui-icon layui-icon-read"></a><span style="height: 30px; margin-left: 8px; font-size: 14px;">'+title+'</span><span style="font-size: 14px; height: 20px; float: right;">'+createDate+'</span></td></tr>';	
				} 
					
			document.getElementById("top10").innerHTML=text;		 		
		});	
		
		//热门词条TOP5
		  var url = $WEB_ROOT_PATH+'/dhccApi/knowledge/knowledge/listHotKeys';
		  //7console.log(url);
		 	postReq(url, '', function(data){
		 		//console.log(data);
		 		var text="";
		 		for(var i=0;i<5;i++){ 			
		 			var keywords = data[i];
	 				var str="checkA('"+keywords+"')";
	 				text+='<span style="width:90px;height:35px;"><a style="font-size:15px; padding-left:10px; width:80px ;align:center;color:#353535;" onclick='+str+'>'+keywords+'</a></span>';

		 		}
		 		document.getElementById("hotKeys").innerHTML=text;
		 	}, function(){
		 		/*$CommonUI.alert("失败!");*/
		 	}, {
		 		skipHidden : false
		 	});
		
		
  });
  
//搜索按钮
	function check(){
		var title=$("#title").val();
		var smallType=$("#Type").val();	
	    layer.open({
	        type: 2
	        ,title: "相关关键字："+title
	        ,content: $WEB_ROOT_PATH+'/knowledge/knowledgeInfo'
	        ,maxmin: true
	        ,area: ['1200px', '550px']
	        ,success: function(layero, index){
	  	  var iframeWindow = window['layui-layer-iframe'+ index];
	  	  //向此iframe层方法 传递参数
	  	  iframeWindow.listAll(title,smallType);
	    }
	  });   
	}
	
	//top10查询
	function checkA(title){
	    layer.open({
	        type: 2
	        ,title: "关键字："+title
	        ,content: $WEB_ROOT_PATH+'/knowledge/knowledgeInfo'
	        ,maxmin: true
	        ,area: ['1200px', '550px']
	        ,success: function(layero, index){
	  	  var iframeWindow = window['layui-layer-iframe'+ index];
	  	  //向此iframe层方法 传递参数
	  	  iframeWindow.listKeyTitle(title);
	    }
	  }); 

	}
	

	//分类浏览   大类
	function checkB(title){
	    layer.open({
	        type: 2
	        ,title:  "分类为："+title
	        ,content: $WEB_ROOT_PATH+'/knowledge/knowledgeInfo'
	        ,maxmin: true
	        ,area: ['1200px', '550px']
	        ,success: function(layero, index){
	  	  var iframeWindow = window['layui-layer-iframe'+ index];
	  	  //向此iframe层方法 传递参数
	  	  iframeWindow.list(title);
	    }
	  });   
	 }
	//分类浏览   小类
	function checkC(id){
		$.getJSON($WEB_ROOT_PATH+'/dhccApi/knowledge/knowledge/list?knowledge.id='+id,function(data){
			var fieldNo=data.data;
			var field =fieldNo[0]; 
			window.parent.parent.showKnowledgeView(field);
		});
	}


  </script>
</body>
</html>