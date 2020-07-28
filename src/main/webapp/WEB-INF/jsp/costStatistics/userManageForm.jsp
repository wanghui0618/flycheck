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
<!-- <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts-all-3.js"></script> -->
<title>用户管理</title>
<style>
.layui-table td, .layui-table th {
    position: relative;
    padding: 9px 15px;
    min-height: 20px;
    line-height: 24px;
    font-size: 14px;
}
.lb {
	margin-left: 10px; 
	width: 85px;
	/* font-size: 13px; */
	font-weight: normal;
	margin-top:5px;
	display: inline-block;
	text-align: right;
	/* border-style: solid; */
}
.ipt {
	margin-left: 5px;
	width: 100px;
	/* font-size: 13px; */
	font-weight: normal;
	margin-top:5px;
	display: inline-block;
	text-align: left;
	/* border-style: solid;  */
}
.ipd {
	margin-left: 5px;
	width: 100px;
	font-size: 30px;
	font-weight: normal;
	margin-top:20px;
	display: inline-block;
	text-align: center;
	/* border-style: solid;  */
}

#div{
    margin-top: 20px;
    margin-bottom:20px;
	width: 80%  ;
	float: left 
}

</style>
</head>
<body>      	 							 
<div class="layui-fluid" style="padding-top: 1px">
 	<div class="layui-card" >
		<div class="layui-card" id="child" style="margin: 0px;height: 78px;width: 100%">
			<div style="width:5%;height: 78px;float: left;">
					<label class="ipd" id="name"></label>
			</div>
      		<div id="div">
      			<lable class="lb">性别:</lable>
      			<lable class="ipt" id="sex"></lable>      			
      			<lable class="lb">参保号:</lable>
      			<lable class="ipt" id="insuranceCode"></lable>
      			<lable class="lb">身份证号:</lable>
      			<lable class="ipt" id="idCard"></lable>
      			<lable class="lb">城市:</lable>
      			<lable class="ipt" id="cityName"></lable>	      								
      			<lable class="lb">人员类别:</lable>
      			<lable class="ipt" id="insurePersonType"></lable>
      		</div>
      		
      	</div> 
	</div>
	     	
    <div class="layui-card" >
		
	 <div class="layui-form layui-card-header layuiadmin-card-header-auto" style="width: 50%;padding-left: 68%">
        <div class="layui-form-item" style="position: absolute;top: 0px">        
          <div class="layui-inline">
				<label class="layui-form-label" >统计区间</label>
				<div class="layui-input-block">
					<input type="text" class="layui-input" name="balanceDate" id="balanceDate" placeholder="日期范围">
				</div>
			</div>
			<div class="layui-inline" >
	            <button class="layui-btn layuiadmin-btn-useradmin"  lay-submit lay-filter="LAY-user-front-search">查询
	              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
	            </button>
	        </div>
 
        </div>
      </div>
	
       <div class="layui-tab">
 		 <ul class="layui-tab-title" style="width: 50%">
    	 	<li class="layui-this">住院分析</li>
    		<li>门诊分析</li>
    		<li>违规分析</li>
  		 </ul>
  		 
 		 <div class="layui-tab-content">
    		<div class="layui-tab-item layui-show">
     			<div class="layui-card-body">
        			<table id="zhuyuanTable" class="layui-hide" lay-filter="menzhenTable"></table>
     			</div>     			
    		</div>   	
   	 		<div class="layui-tab-item">
   	 			<div class="layui-card-body">
        			<table id="menzhenTable" class="layui-hide" lay-filter="zhuyuanTable"></table>
     			</div>   			
    		</div>
    		<div class="layui-tab-item">
    		<div class="layui-card-body">
        			<table id="weiguiTable" class="layui-hide" lay-filter="weiguiTable"></table>
     			</div>
    		</div>
    	  </div>
  		</div>
	  
    </div>
    <div class="layui-card">
			<div class="layui-card-header" style="border-bottom: 1px solid #f6f6f6;">
				<img style="margin-top: -2px; padding-right: 8px;"src="<%=request.getContextPath()%>/images/auditing/mark.png" />
						费用趋势图
			</div>
			<div class="layui-card-body">
				<div id="main" style="height: 250px;width: 95%"></div>
			</div>
	</div>
	
	<div class="layui-card">
		<div class="layui-col-md3" >
			<div class="layui-card" >
			<div class="layui-card-header"
						style="border-bottom: 1px solid #f6f6f6;">
						<img style="margin-top: -2px; padding-right: 4px;"
							src="<%=request.getContextPath()%>/images/auditing/mark.png" />
						医院
			</div>
			<div class="layui-card-body layui-text" >		
					<table class="layui-table" style="border: 1px solid;">
							<colgroup>
								<col width="100">
								<col>
							</colgroup>
							<tbody>
								<!-- 行政区域  接入城市 接入时间 icd对照数 
              		未对照 药品对照数 未对照 诊疗对照数 未对照 耗材对照数 未对照 -->
								<tr style="background: #E5F7EF">
									<td style="width: 55%">医院名称</td>
									<td style="width: 19%">次数</td>
									<td style="width: 26%">费用</td>
								</tr>
							</tbody>
							<tbody id="hospital"></tbody>
						</table>			
			</div>
			</div>
		</div>
		<div class="layui-col-md3" >
			<div class="layui-card" >
			<div class="layui-card-header"
						style="border-bottom: 1px solid #f6f6f6;">
						<img style="margin-top: -2px; padding-right: 4px;"
							src="<%=request.getContextPath()%>/images/auditing/mark.png" />
						诊断
					</div>
					<div class="layui-card-body layui-text" >
						<table class="layui-table" style="border: 1px solid;">
							<colgroup>
								<col width="100">
								<col>
							</colgroup>
							<tbody>
								<!-- 行政区域  接入城市 接入时间 icd对照数 
              		未对照 药品对照数 未对照 诊疗对照数 未对照 耗材对照数 未对照 -->
								<tr style="background: #E5F7EF">
									<td style="width: 55%">诊断名称</td>
									<td style="width: 19%">次数</td>
									<td style="width: 26%">费用</td>
								</tr>
								
							</tbody>
								<tbody id="diagnose"></tbody>
						</table>
					</div>
			</div>
		</div>
		<div class="layui-col-md3" >
			<div class="layui-card">
				<div class="layui-card-header"
						style="border-bottom: 1px solid #f6f6f6;">
						<img style="margin-top: -2px; padding-right: 4px;"
							src="<%=request.getContextPath()%>/images/auditing/mark.png" />
						目录种类
					</div>
					<div class="layui-card-body layui-text" >
					
						<table class="layui-table" style="border: 1px solid;">
							<colgroup>
								<col width="100">
								<col>
							</colgroup>
							<tbody>
								<!-- 行政区域  接入城市 接入时间 icd对照数 
              		未对照 药品对照数 未对照 诊疗对照数 未对照 耗材对照数 未对照 -->
								<tr style="background: #E5F7EF">
									<td style="width: 55%">目录名称</td>
									<td style="width: 19%">次数</td>
									<td style="width: 26%">费用</td>
								</tr>								
							</tbody>
							<tbody id="detail1"></tbody>
						</table>
					</div>
			</div>
		</div>
		<div class="layui-col-md3" >
			<div class="layui-card" >
				<div class="layui-card-header"
						style="border-bottom: 1px solid #f6f6f6;">
						<img style="margin-top: -2px; padding-right: 4px;"
							src="<%=request.getContextPath()%>/images/auditing/mark.png" />
						收费类别
					</div>
					<div class="layui-card-body layui-text" >
						<table class="layui-table" style="border: 1px solid;">
							<colgroup>
								<col width="100">
								<col>
							</colgroup>
							<tbody>								
								<tr style="background: #E5F7EF">
									<td style="width: 55%">收费类别</td>
									<td style="width: 19%">次数</td>
									<td style="width: 26%">费用</td>
								</tr>
							</tbody>
							<tbody id="drug"></tbody>
						</table>
					</div>
			</div>
		</div>
	</div>
		
  </div>
  <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/costStatistics/userManageForm.js"></script> 
  <script src="<%=request.getContextPath() %>/js/echarts_jpp/echarts-2.2.7/build/dist/echarts.js"></script>
  <script type="text/javascript">
           
    </script>
<!--   <script>
	//注意：选项卡 依赖 element 模块，否则无法进行功能性操作
	layui.use('element', function(){
 	 var element = layui.element;

	});
  </script> -->
  <script>
 
  </script>
</body>
</html>