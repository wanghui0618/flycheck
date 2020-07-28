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
<title>主页面</title>
<style>
.layui-table td, .layui-table th {
    position: relative;
    padding: 9px 15px;
    min-height: 20px;
    line-height: 24px;
    font-size: 14px;
}
.layui-fluid {
    overflow: hidden;
}
.daiban li{
	height: 65px;
	width: 180px;
	border-radius: 3px;
	color:#fff;
	margin: 0px 6px 13px 6px;
}
.xinzeng{
	background-image: linear-gradient(-225deg, #27AEFF 0%, #2284FF 100%);
}
.youxiao{
	background-image: linear-gradient(-225deg, #F48A56 0%, #EA5C44 100%);
}
.wuxiao{
	background-image: linear-gradient(-225deg, #F5B00C 0%, #EF8D07 100%);
}
.zhuyuan{
	background-image: linear-gradient(-225deg, #00D888 0%, #00B365 100%);
}
.menzhen{
	background-image: linear-gradient(-225deg, #B28EF1 0%, #806BE4 100%);
}
.mente{
	background-image: linear-gradient(-225deg, #2DE0BB 0%, #03BCC7 100%);
}
.qualityStyle{
width:15%
}
</style>
</head>
<body>
<div class="layui-fluid" style="overflow:hidden">
    <div class="layui-row layui-col-space15" style="overflow:hidden">
    <div class="layui-col-md12">
    <span  id="contextPath" style="display: none;"><%=request.getContextPath()%></span>
      		<div class="layui-card">
  				<div class="layui-card-header" >
					<label id="dateweek" style="margin-left:10px;">&nbsp;</label><label style="font-size: 16px;">&nbsp;&nbsp;&nbsp;昨日上传数据</label>
				</div>
  				<div class="layui-card-body">
				  	<ul class="layui-row layui-col-space10 layui-this daiban">
                        <li id="userStatusA" style="padding-top: 0px;" class="layui-col-xs2 xinzeng" href="javascript:;" >
                        	<div style="display: block;float: left;padding:0px 15px;line-height:65px;">
                        		<img style="height:40px;" src="<%=request.getContextPath() %>/images/datatrans/xinzeng.png"  />
                        	</div>
                            <div style="line-height: 25px;margin-top: 7px;">
                             	<h3 style="font-size: 12px;">新增数据</h3>
                               	<p style="font-size: 20px;"><label id="userStatus"></label></p>
                            </div>
                        </li>
                        <li id="jhA" style="padding-top: 0px;" class="layui-col-xs2 youxiao" href="javascript:;" >
                        	<div style="display: block;float: left;padding:0px 15px;line-height:65px;">
                        		<img style="height:40px;" src="<%=request.getContextPath() %>/images/datatrans/youxiao.png"  />
                        	</div>
                            <div style="line-height: 25px;margin-top: 7px;">
                             	<h3 style="font-size: 12px;">有效数据</h3>
                               	<p style="font-size: 20px;"><label id="jh"></label></p>
                            </div>
                        </li>
                        <li id="gsA" style="padding-top: 0px;" class="layui-col-xs2 wuxiao" href="javascript:;" >
                        	<div style="display: block;float: left;padding:0px 15px;line-height:65px;">
                        		<img style="height:40px;" src="<%=request.getContextPath() %>/images/datatrans/wuxiao.png"  />
                        	</div>
                            <div style="line-height: 25px;margin-top: 7px;">
                             	<h3 style="font-size: 12px;">无效数据</h3>
                               	<p style="font-size: 20px;"><label id="gs"></label></p>
                            </div>
                        </li>
                        <li id="zynumberA" style="padding-top: 0px;" class="layui-col-xs2 zhuyuan" href="javascript:;">
                        	<div style="display: block;float: left;padding:0px 15px;line-height:65px;">
                        		<img style="height:40px;" src="<%=request.getContextPath() %>/images/datatrans/zhuyuan.png"  />
                        	</div>
                            <div style="line-height: 25px;margin-top: 7px;">
                             	<h3 style="font-size: 12px;">住院</h3>
                               	<p style="font-size: 20px;"><label id="zynumber"></label></p>
                            </div>
                        </li>
                        <li id="mznumberA" style="padding-top: 0px;" class="layui-col-xs2 menzhen" href="javascript:;" >
                        	<div style="display: block;float: left;padding:0px 15px;line-height:65px;">
                        		<img style="height:40px;" src="<%=request.getContextPath() %>/images/datatrans/menzhen.png"  />
                        	</div>
                            <div style="line-height: 25px;margin-top: 7px;">
                             	<h3 style="font-size: 12px;">门诊</h3>
                               	<p style="font-size: 20px;"><label id="mznumber"></label></p>
                            </div>
                        </li>
                        <li id="mtnumberA" style="padding-top: 0px;" class="layui-col-xs2 mente" href="javascript:;" >
                        	<div style="display: block;float: left;padding:0px 15px;line-height:65px;">
                        		<img style="height:40px;" src="<%=request.getContextPath() %>/images/datatrans/mente.png"  />
                        	</div>
                            <div style="line-height: 25px;margin-top: 7px;">
                             	<h3 style="font-size: 12px;">门特</h3>
                               	<p style="font-size: 20px;"><label id="mtnumber"></label></p>
                            </div>
                        </li>
                    </ul>
  				</div>
  			</div>
	</div>
      <div class="layui-col-md12 layui-col-xs12">
        <div class="layui-row layui-col-space15">
          <div class="layui-col-md4">
            <div class="layui-card">
                   <div class="layui-card-header" style="border-bottom: 1px solid #f6f6f6;">
  					<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
  					昨日上传数量统计
 					<span style="float: right;" href="javascript:;" lay-urlname="昨日上传数量统计" lay-href="<%=request.getContextPath()%>/view/todaynumber">查看全部<img style="width:14px;margin-top:-2px;padding-right: 2px;margin-left: 6px;" src="<%=request.getContextPath() %>/images/auditing/more.png"  /></span> 
  				</div>
  				 <div class="layui-card-body layui-text" >
  				 <div  style="height:235px;" >
                 <!--  <table id="todayTable" class="layui-hide" lay-filter="todayTable"></table> -->
                 <table  style="border: 1px;border:0px;width: 100%;line-height:34px;">
							<colgroup>
								<col width="230">
								<col>
							</colgroup>
							<tbody>
								<tr style="background: #FEF3E5">
									<td style="padding-left:30px;width:40%" class="qualityStyle">医院名称</td>
									<td style="" class="qualityStyle">上传总数量</td>
								</tr>
							</tbody>
							<tbody id="todaynumber" ></tbody>
						</table>
						</div>
                  </div>
            </div>
            <div class="layui-card">
            	<div class="layui-card-header" style="border-bottom: 1px solid #f6f6f6;">
  					<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
  					全部—医院上传数量统计
 					<span style="float: right;" lay-urlname="全部—医院上传数量统计" href="javascript:;" lay-href="<%=request.getContextPath()%>/view/updateCount">查看全部<img style="width:14px;margin-top:-2px;padding-right: 2px;margin-left: 6px;" src="<%=request.getContextPath() %>/images/auditing/more.png"  /></span>
 					<li class="layui-this" style="height:15px;line-height:15px;">Top10</li> 
  				</div>
				<div id="bar" style="height: 240px;padding-bottom: 20px"></div>
            </div>
          </div>
          
          <div class="layui-col-md4">
            <div class="layui-card">
                <div class="layui-card-header" style="border-bottom: 1px solid #f6f6f6;">
  					<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
  					昨日上传质量统计
  					<span style="float: right;" lay-urlname="昨日上传质量统计" href="javascript:;" lay-href="<%=request.getContextPath()%>/view/qualitynumber">查看全部<img style="width:14px;margin-top:-2px;padding-right: 2px;margin-left: 6px;" src="<%=request.getContextPath() %>/images/auditing/more.png"  /></span> 
  				</div>
  				  <div class="layui-card-body layui-text" >
  				 <div  style="height:235px;" >
                      <table  style="border: none;border:0px;width: 100%;line-height:34px;">
               <colgroup>
                <col width="230" >
                <col>
                <col>
              </colgroup>
                 <tbody >
               <tr style="background:#FEF3E5">
               <td style="padding-left:30px;width:40%" class="qualityStyle">医院名称</td>
                <td style="" class="qualityStyle">数据总量</td>
                <td style="" class="qualityStyle">有效数据</td>
                <td style="" class="qualityStyle">无效数据</td>
                <td style="" class="qualityStyle">有效率</td>
               </tr>
               </tbody>
              <tbody id="qualityToday" >
              </tbody>
              </table>
              </div>
                  </div>
            </div>
            <div class="layui-card">
            	<div class="layui-card-header" style="border-bottom: 1px solid #f6f6f6;">
  					<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
  					全部—医院上传质量统计
 					<span style="float: right;" lay-urlname="全部—医院上传质量统计" href="javascript:;" lay-href="<%=request.getContextPath()%>/view/qualityCount">查看全部<img style="width:14px;margin-top:-2px;padding-right: 2px;margin-left: 6px;" src="<%=request.getContextPath() %>/images/auditing/more.png"  /></span>
  				</div>
              <div class="layui-card-body">
              <div id="tableTwo" style="height:255px;" >
              <table  style="border: none;border:0px;width: 100%;line-height:34px;">
               <colgroup>
                <col width="230" >
                <col>
                <col>
              </colgroup>
                 <tbody >
               <tr style="background:#E5F7EF">
               <td style="padding-left:30px;width:40%" class="qualityStyle">医院名称</td>
                <td style="" class="qualityStyle">数据总量</td>
                <td style="" class="qualityStyle">有效数据</td>
                <td style="" class="qualityStyle">无效数据</td>
                <td style="" class="qualityStyle">有效率</td>
               </tr>
               </tbody>
              <tbody id="quality" >
              </tbody>
              </table>
              </div>
              </div>
            </div>
          </div>
          
         <div class="layui-col-md4">
            <div class="layui-card">
                 <div class="layui-card-header" style="border-bottom: 1px solid #f6f6f6;">
  					<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
  					昨日上传完整性统计
  					<span style="float: right;" lay-urlname="昨日上传完整性统计" href="javascript:;" lay-href="<%=request.getContextPath()%>/view/tegritynumber">查看全部<img style="width:14px;margin-top:-2px;padding-right: 2px;margin-left: 6px;" src="<%=request.getContextPath() %>/images/auditing/more.png"  /></span> 
  				 </div>
  				  				  <div class="layui-card-body layui-text" >
  				 <div  style="height:235px;" >
                      <table  style="border: none;border:0px;width: 100%;line-height:34px;">
               <colgroup>
                <col width="230" >
                <col>
                <col>
              </colgroup>
                 <tbody >
               <tr style="background:#FEF3E5">
               <td style="padding-left:30px;width:40%" class="qualityStyle">医院名称</td>
                <td style="" class="qualityStyle">数据总量</td>
                <td style="" class="qualityStyle">完整数据</td>
                <td  class="qualityStyle" style="width:18%">不完整数据</td>
                <td  class="qualityStyle" style="width:12%">完整率</td>
               </tr>
               </tbody>
              <tbody id="IntegrityToday" >
              </tbody>
              </table>
              </div>
                  </div>
            </div>
            <div class="layui-card">
                <div class="layui-card-header" style="border-bottom: 1px solid #f6f6f6;">
  					<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
  					全部—医院上传完整性统计
 					<span style="float: right;" lay-urlname="全部—医院上传完整性统计"  href="javascript:;" lay-href="<%=request.getContextPath()%>/view/tegrityCount">查看全部<img style="width:14px;margin-top:-2px;padding-right: 2px;margin-left: 6px;" src="<%=request.getContextPath() %>/images/auditing/more.png"  /></span>
  				</div>
              <div class="layui-card-body">
              <div id="tableThree" style="height:255px;" >
              <table style="border:0px;width: 100%;line-height:34px;">
              <colgroup>
                <col width="230">
                <col >
              </colgroup>
                <tbody >
		               <tr style="background:#E5F7EF">
		               <td style="padding-left:30px;width:40%">医院名称</td>
		                <td class="qualityStyle">数据总量</td>
		                <td class="qualityStyle">完整数据</td>
		                <td class="qualityStyle" style="width:18%">不完整数据</td>
		                <td class="qualityStyle" style="width:12%">完整率</td>
		               </tr>
		               </tbody>
               <tbody id="istegritytable" >
               </tbody>
              </table>
             </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
	<script src="<%=request.getContextPath() %>/js/echarts_jpp/echarts-2.2.7/build/dist/echarts.js"></script>
   <script type="text/javascript">
     // 路径配置
     require.config({
         paths: {
             echarts: $WEB_ROOT_PATH+'/js/echarts_jpp/echarts-2.2.7/build/dist'
         }
     });
	   // 使用
  require(
      [
          'echarts',
          'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
      ],
      function (ec) {
          // 基于准备好的dom，初始化echarts图表
          var myChart = ec.init(document.getElementById('bar')); 
          
          var option = {
              tooltip: {
                  show: true
              },
              legend: {
                  data:['']
              },
              xAxis : [
                  {
                      type : 'category',
                      axisLabel: {    
                          rotate:40 ,
                          fontSize : 7
                       } ,
                      data : []
                  }
              ],
              yAxis : [
                  {
                      type : 'value'
                  }
              ],
              series : [
                  {
                      "name":"上传数据",
                      "type":"bar",
                      barWidth:15,
                      "data":[],
                      itemStyle:{
                          normal:{
                              color:'#319CFE'
                          }
                      }
                  }
              ]
          };
  		
          // 为echarts对象加载数据 
            $.ajax({
         	     url:$WEB_ROOT_PATH+'/dhccApi/medical/medical/listQuality',
			         type : "post",		
			         async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
			         dataType : "json",	
			         success : function(result) {
			        	 var hvdata=result.data;
			        	 if (hvdata != null && hvdata.length > 0) {
			        		 var i;
			        		 if(hvdata.length>10){
			        			 k=9;
			        		 }else{
			        			 k=hvdata.length;
			        		 }
			        		 for(var i=0;i<k;i++){ 
				                   option.series[0].data.push(hvdata[i].qualityNumber);
				                   option.xAxis[0].data.push(hvdata[i].qualityName);
				             } 
			        		 myChart.setOption(option); 
			        	 }
			         }
            });
          
      }
  );
  </script>
  <script>
  layui.config({
	    base: '<%=request.getContextPath() %>/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'console'], function(){
		    var a = new Array("日", "一", "二", "三", "四", "五", "六");
		    var time=(new Date).getTime()-24*60*60*1000;
			var date = new Date(time);
			var today_nian = date.getFullYear();
			var today_yue = date.getMonth()+1;
			var today_day = date.getDate();
			var week = date.getDay();
			var str = today_nian+"年"+today_yue+"月"+today_day+"日&nbsp;星期"+ a[week];
			$("#dateweek").html(str);
			
		    $.getJSON($WEB_ROOT_PATH+'/dhccApi/medical/medical/listQuality',
					function(data){
		    	var tboy=$("#number");
		    	var mm;
				var  dataList= data.data;
				for(var i=0 ;i<dataList.length;i++){
					if(i<=4){
						var tr = document.createElement("tr");
						tr.innHTML="<td style='padding-left:30px;'>"+dataList[i].qualityName+"</td>"+"<td>"+dataList[i].qualityNumber+"</td>"+"<td>"+(i+1)+"</td>";
						if(i%2==0){
							mm+="<tr style='background:#fff'>"+tr.innHTML+"</tr>";
						}else{
							mm+="<tr style='background:#F6FAFF'>"+tr.innHTML+"</tr>";
						}
					}
			    }
				tboy.html(mm);
			});
		    
			$.getJSON($WEB_ROOT_PATH+'/dhccApi/medical/medical/listTegrity?tableName=T_PICCBID_MEDICAL',
					function(data){
		    	var tboy=$("#quality");
		    	var mm;
				var  dataList= data.data;
				var count=0;
				for(var i=0 ;i<dataList.length;i++){
					if(count<=4&&dataList[i].qualityName!=null){
						var tr = document.createElement("tr");
						var mix=dataList[i].effectiveRate;
						var qualityName=dataList[i].qualityName;
						if(qualityName.length>8){
							qualityName=qualityName.substr(0,8)+"...";
						}
						tr.innHTML="<td style='padding-left:30px'>"+qualityName+"</td>"+"<td>"+dataList[i].totalCount+"</td>"+"<td>"+dataList[i].effectiveNumber+"</td>"+"<td>"+dataList[i].qualityNumber+"</td>"+"<td>"+(mix==0?mix+"":mix+"%")+"</td>";
						if(count%2==0){
							mm+="<tr style='background:#fff'>"+tr.innHTML+"</tr>";
						}else{
							mm+="<tr style='background:#F5FCF9'>"+tr.innHTML+"</tr>";
						}
						count++;
					}
			    }
				if(dataList.length==0){
					var tr = document.createElement("tr");
					tr.innHTML="<td style='padding-left:30px'>未上传数据。。。</td>"
					mm+="<tr style='background:#fff'>"+tr.innHTML+"</tr>";
				}
				if(dataList.length==0){
					var tr = document.createElement("tr");
					tr.innHTML="<td colspan='5' style='text-align: center;'><img src="+$WEB_ROOT_PATH+"/images/empty.png></td>"
					mm+="<tr style='background:#fff'>"+tr.innHTML+"</tr>";
				}
				tboy.html(mm);
			});
		    $.getJSON($WEB_ROOT_PATH+'/dhccApi/medical/medical/Tegrity',
					function(data){
		    	var tboy=$("#istegritytable");
		    	var mm;
				var  dataList= data.data;
				var count=0;
                for(var i=0 ;i<dataList.length;i++){
					
					if(count<=4&&dataList[i].qualityName!=null){
						var tr = document.createElement("tr");
						var mix=dataList[i].effectiveRate;
						var qualityName=dataList[i].qualityName;
						if(qualityName.length>8){
							qualityName=qualityName.substr(0,8)+"...";
						}
						tr.innHTML="<td style='padding-left:30px'>"+qualityName+"</td>"+"<td>"+dataList[i].totalCount+"</td>"+"<td>"+dataList[i].effectiveNumber+"</td>"+"<td>"+dataList[i].qualityNumber+"</td>"+"<td>"+(mix==0?mix+"":mix+"%")+"</td>";
						if(count%2==0){
							mm+="<tr style='background:#fff'>"+tr.innHTML+"</tr>";
						}else{
							mm+="<tr style='background:#F5FCF9'>"+tr.innHTML+"</tr>";
						}
						count++;
					}
			    }
        		if(dataList.length==0){
					var tr = document.createElement("tr");
					tr.innHTML="<td style='padding-left:30px'>未上传数据。。。</td>"
					mm+="<tr style='background:#fff'>"+tr.innHTML+"</tr>";
				}
        		if(dataList.length==0){
					var tr = document.createElement("tr");
					tr.innHTML="<td colspan='5' style='text-align: center;'><img src="+$WEB_ROOT_PATH+"/images/empty.png></td>"
					mm+="<tr style='background:#fff'>"+tr.innHTML+"</tr>";
				}
				tboy.html(mm);
			});
		    

	  });
  </script>
  <script type="text/javascript">
  layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;
	    
	    var time=(new Date).getTime()-24*60*60*1000;
	    var yesterday=new Date(time);
		var today_nian = yesterday.getFullYear();
		var today_yue = yesterday.getMonth()+1;
		var today_day = yesterday.getDate();
		var str = today_nian+"-"+today_yue+"-"+today_day;
			    //今日上传数量
			    $.getJSON($WEB_ROOT_PATH+'/dhccApi/admin/admin/TodayNumber',
						function(data){
			    	var tboy=$("#todaynumber");
			    	var mm;
					var  dataList= data.data;
					var count=0;
					for(var i=0 ;i<dataList.length;i++){
						if(count<=4&& dataList[i].orgName!=null){
							var tr = document.createElement("tr");
							tr.innHTML="<td style='padding-left:30px'>"+dataList[i].orgName+"</td>"
							+"<td style='padding-left:30px'>"+dataList[i].totalNumber+"</td>";
							if(count%2==0){
								mm+="<tr style='background:#fff'>"+tr.innHTML+"</tr>";
							}else{
								mm+="<tr style='background:#FFFBF5'>"+tr.innHTML+"</tr>";
							}
							count++;
						}
				    }
				if(dataList.length==0){
					var tr = document.createElement("tr");
					tr.innHTML="<td style='padding-left:30px'>未上传数据。。。</td>"
					mm+="<tr style='background:#fff'>"+tr.innHTML+"</tr>";
				}
				if(dataList.length==0){
					var tr = document.createElement("tr");
					tr.innHTML="<td colspan='5' style='text-align: center;'><img src="+$WEB_ROOT_PATH+"/images/empty.png></td>"
					mm+="<tr style='background:#fff'>"+tr.innHTML+"</tr>";
				}	
			  tboy.html(mm);
				});
	          


	          
				//今日上传质量统计	          
			    $.getJSON($WEB_ROOT_PATH+'/dhccApi/admin/admin/QualityToday',
						function(data){
			    	var tboy=$("#qualityToday");
			    	var mm;
					var  dataList= data.data;
					var count=0;
	                for(var i=0 ;i<dataList.length;i++){
						
	        			if(count<=4&&dataList[i].orgName!=null ){
							var tr = document.createElement("tr");
							var mix=dataList[i].effective;
							var qualityName=dataList[i].orgName;
							if(qualityName.length>8){
								qualityName=qualityName.substr(0,8)+"...";
							}
							tr.innHTML="<td style='padding-left:30px'>"+qualityName+"</td>"+"<td>"+dataList[i].totalNumber+"</td>"+"<td>"+dataList[i].effectNumber+"</td>"+"<td>"+dataList[i].uneffectNumber+"</td>"+"<td>"+(mix==0?mix+"":mix+"%")+"</td>";
							if(count%2==0){
								mm+="<tr style='background:#fff'>"+tr.innHTML+"</tr>";
							}else{
								mm+="<tr style='background:#FFFBF5'>"+tr.innHTML+"</tr>";
							}
							count++;
						}
				    }
					if(dataList.length==0){
						var tr = document.createElement("tr");
						tr.innHTML="<td style='padding-left:30px'>未上传数据。。。</td>"
						mm+="<tr style='background:#fff'>"+tr.innHTML+"</tr>";
					}
					if(dataList.length==0){
						var tr = document.createElement("tr");
						tr.innHTML="<td colspan='5' style='text-align: center;'><img src="+$WEB_ROOT_PATH+"/images/empty.png></td>"
						mm+="<tr style='background:#fff'>"+tr.innHTML+"</tr>";
					}
					tboy.html(mm);
				});
				
				//今日上传完整性统计	          
			    $.getJSON($WEB_ROOT_PATH+'/dhccApi/admin/admin/IntegrityToday',
						function(data){
			    	var tboy=$("#IntegrityToday");
			    	var mm;
					var  dataList= data.data;
					var count=0;
	                for(var i=0 ;i<dataList.length;i++){
	        			if(count<=4&& dataList[i].orgName!=null){
							var tr = document.createElement("tr");
							var mix=dataList[i].effective;
							var qualityName=dataList[i].orgName;
							if(qualityName.length>8){
								qualityName=qualityName.substr(0,8)+"...";
							}
							tr.innHTML="<td style='padding-left:30px'>"+qualityName+"</td>"+"<td>"+dataList[i].totalNumber+"</td>"+"<td>"+dataList[i].effectNumber+"</td>"+"<td>"+dataList[i].uneffectNumber+"</td>"+"<td>"+(mix==0?mix+"":mix+"%")+"</td>";
							if(count%2==0){
								mm+="<tr style='background:#fff'>"+tr.innHTML+"</tr>";
							}else{
								mm+="<tr style='background:#FFFBF5'>"+tr.innHTML+"</tr>";
							}
							count++;
						}
				    }
	        		if(dataList.length==0){
						var tr = document.createElement("tr");
						tr.innHTML="<td style='padding-left:30px'>未上传数据。。。</td>"
						mm+="<tr style='background:#fff'>"+tr.innHTML+"</tr>";
					}
					if(dataList.length==0){
						var tr = document.createElement("tr");
						tr.innHTML="<td colspan='5' style='text-align: center;'><img src="+$WEB_ROOT_PATH+"/images/empty.png></td>"
						mm+="<tr style='background:#fff'>"+tr.innHTML+"</tr>";
					}
					tboy.html(mm);
				});
	    
	    //获取就诊信息
	    $.getJSON($WEB_ROOT_PATH+'/dhccApi/admin/admin/listNumber',
				function(data){
	    	var data =data.data;
	    	var zynumber=data[0].zynumber;
	    	var mznumber=data[0].mznumber;
	    	var mtnumber=data[0].mtnumber;
	    	var contextPath=$("#contextPath").text();
	    	$("#zynumber").html(zynumber==0?'-':zynumber)
	    	$("#mznumber").html(mznumber==0?'-':mznumber)
	    	$("#mtnumber").html(mtnumber==0?'-':mtnumber)
	    	if(zynumber!=0){
	    		$("#zynumberA").attr("lay-href",contextPath+'/medicalCount/yestodayZy');
	    		$("#zynumberA").attr("lay-urlname","昨日新增住院数据");
	    	}
	    	if(mznumber!=0){
	    		$("#mznumberA").attr("lay-href",contextPath+'/medicalCount/yestodayMz');
	    		$("#mznumberA").attr("lay-urlname","昨日新增门诊数据");
	    	}
	    	if(mtnumber!=0){
	    		$("#mtnumberA").attr("lay-href",contextPath+'/medicalCount/yestodayMt');
	    		$("#mtnumberA").attr("lay-urlname","昨日新增门特数据");
	    	}
		});
	    
	  //今日上传数据统计
	    $.getJSON($WEB_ROOT_PATH+'/dhccApi/medical/medical/todayUploadDataCount?tableName=T_PICCBID_MEDICAL&inFlag='+str,
				function(data){
	    	var total=data[0].untegrity+data[0].tegrity;
	    	var tegrity=data[0].tegrity;
	    	var untegrity=data[0].untegrity;
	    	var contextPath=$("#contextPath").text();
	    	$("#userStatus").html(total==0?'-':total);
	    	$("#jh").html(tegrity==0?'-':tegrity);
	    	$("#gs").html(untegrity==0?'-':untegrity);
	    	if(total!=0){
	    		$("#userStatusA").attr("lay-href",contextPath+'/medicalCount/yestodayUpdateData');
	    		$("#userStatusA").attr("lay-urlname","昨日新增数量");
	    	}
	    	if(tegrity!=0){
	    		$("#jhA").attr("lay-href",contextPath+'/medicalCount/yestodayQualityData');
	    		$("#jhA").attr("lay-urlname","昨日有效数据");
	    	}
	    	if(untegrity!=0){
	    		$("#gsA").attr("lay-href",contextPath+'/medicalCount/yestodayUnqualityData');
	    		$("#gsA").attr("lay-urlname","昨日无效数据");
	    	}
		});
	  });
  </script>
</body>
</html>