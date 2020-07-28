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
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui//layuiadmin/style/admin.css" media="all">
<title>收费类别分布</title>
</head>
<body style="overflow:hidden">
<div style="padding: 8px; background-color: #F2F2F2;">
<div class="layui-row layui-col-space15">
     <div class="layui-card">
        <div class="layui-card-body">
   			<div id="main" ></div>
  		</div>
      </div>
    </div>
    </div>
    <!-- ECharts单文件引入 -->
	<script src="<%=request.getContextPath() %>/js/echarts_jpp/echarts-2.2.7/build/dist/echarts.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/medical/medicalNum.js"></script>
		 <script type="text/javascript">
		 var sumAmount=0;
		 var applyPayAmount=0;
		 var selfPayAmount=0;
		 var fullOrdination=0;
		 var partialOrdination=0;
		 var partialPayment=0;
		 var fullPayment=0;
		 var itemCost=0;
		 var itemPrice=0;
		 function child(obj){
				 var inFlag=obj;
			    $.getJSON($WEB_ROOT_PATH+'/dhccApi/medicalId/medicalId/GetToll?inFlag='+inFlag,
						function(data){
			    	if(data==null){
			    		  sumAmount=0;
			    		  applyPayAmount=0;
			    		  selfPayAmount=0;
			    		  fullOrdination=0;
			    		  partialOrdination=0;
			    		  partialPayment=0;
			    		  fullPayment=0;
			    		  itemCost=0;
			    		  itemPrice=0;
			    	}else{
			    		itemCost=parseInt(data.itemCost);
				    	sumAmount=parseInt(data.sumAmount);
				    	itemPrice=parseInt(data.itemPrice);
				    	applyPayAmount=parseInt(data.applyPayAmount);
				    	selfPayAmount=parseInt(data.selfPayAmount);
				    	fullOrdination=parseInt(data.fullOrdination);
				    	partialOrdination=parseInt(data.partialOrdination);
				    	partialPayment=parseInt(data.partialPayment);
				    	fullPayment=parseInt(data.fullPayment);
			    	}
			    
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
			                'echarts/chart/radar' // 使用柱状图就加载bar模块，按需加载
			            ],
			            function (ec) {
			                // 基于准备好的dom，初始化echarts图表
			                var myChart = ec.init(document.getElementById('main')); 
			                
			                var option = option = {
			               		    title : {
			               		        text: ' 收费类别分布',
			               		       /*  subtext: '完全实况球员数据' */
			               		    },
			               		    tooltip : {
			               		        trigger: 'axis'
			               		    },
			               		    legend: {
			               		        x : 'center',
			               		        data:['收费类别分布']
			               		    },
			               		    toolbox: {
			               		        show : true,
			               		        feature : {
			               		           /*  mark : {show: true}, */
			               		          /*   dataView : {show: true, readOnly: false}, */
			               		            restore : {show: true},
			               		       /*      saveAsImage : {show: true} */
			               		        }
			               		    },
			               		    calculable : true,
			               		    polar : [
			               		        {
			               		            indicator : [
			               		                {text : '总金额'},
			               		                {text : '报销金额'},
			               		             	{text : '自付金额'},
			               		          		{text : '金额统筹'},
			               		       			{text : '部分统筹'},
			               		    			{text : '部分自付'},
			               		 				{text : '全额自付'},
			               						{text : '项目金额'},
			               		                {text : '项目单价'}
			               		            ],
			               		            radius : 130
			               		        }
			               		    ],
			               		    series : [
			               		        {
			               		            name: '收费类别分布',
			               		            type: 'radar',
			               		            itemStyle: {
			               		                normal: {
			               		                    areaStyle: {
			               		                        type: 'default'
			               		                    }
			               		                }
			               		            },
			               		            data : [
			               		                {
			               		                    value : [sumAmount,applyPayAmount,selfPayAmount,fullOrdination
			               		                    	,partialOrdination,partialPayment,fullPayment,itemCost,itemPrice],
			               		                    name : '收费类别分布'
			               		                }
			               		            
			               		            ]
			               		        }
			               		    ]
			               		};
			                myChart.setOption(option); 
			            }
			        );
					});
		 }
    </script>
</body>
</html>