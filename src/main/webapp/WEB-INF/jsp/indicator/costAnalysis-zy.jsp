<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/layui/css/layui.css"
	media="all">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/style/admin.css"
	media="all">
<script  src="<%=request.getContextPath() %>/js/echarts_home/echarts.min.js"></script>
<script
	src="<%=request.getContextPath()%>/js/echarts_jpp/echarts-2.2.7/build/dist/echarts.js"></script>
<title>次均费用分析</title>
<style type="text/css">
.layui-table td, .layui-table th {
	position: relative;
	padding: 9px 15px;
	min-height: 20px;
	line-height: 24px;
	font-size: 14px;
}

.layui-fluid {
	padding: 10px 6px 0px 6px;
	overflow-x: hidden;
}

.daiban li {
	height: 65px;
	width: 180px;
	border-radius: 3px;
	color: #fff;
	margin: 0px 6px 13px 6px;
}

.layui-table-body {
	overflow: hidden;
}

.layui-table-page {
	border-top: 0px;
}
/* .zr-element{
	left: -40px;
	width: 520px!important;
	height: 319px!important;
} */
.layui-input-block {
	margin-left: 60px;
}

.layui-form-label {
	padding: 9px 0px;
	width: 60px;
}

table th td {
	border: none;
}

html {
	background-color: #D3D3D3;
}
</style>
</head>
<body>
	
	<div class="layui-fluid" style="overflow: hidden;">

		<div class="layui-row layui-col-space15">

			<div class="layui-col-md12"
				style="padding-left: 5px; padding-right: 5px;">
				<div class="layui-card">
					<div class="layui-card-header"
						style="font-size: 14px; border-bottom: 1px solid #f6f6f6;">
						<img style="margin-top: -2px; padding-right: 8px;"
							src="<%=request.getContextPath()%>/images/auditing/mark.png" />
						图例分析
					</div>
					<div class="layui-card-body">
						<div id="main" style="width: 28%; height: 250px; float: left;"></div>
						<div id="main1" style="width: 44%; height: 250px; float: left;"></div>
						<div id="main2" style="width: 28%; height: 250px; float: left;"></div>
					</div>
				</div>
			</div>
			<div class="layui-col-md12">
				<div class="layui-col-md6"
					style="padding-left: 4px; padding-right: 5px;">
					<div class="layui-card">
						<div class="layui-card-header"
							style="border-bottom: 1px solid #f6f6f6;">
							<img style="margin-top: -2px; padding-right: 4px;"
								src="<%=request.getContextPath()%>/images/auditing/mark.png" />
							统筹区住院次均费用top10
						</div>
						<div class="layui-card-body layui-text" style="padding-top: 6px">
							<table class="layui-table" style="border: 1px solid;">
								<colgroup>
									<col width="100">
									<col>
								</colgroup>
								<tbody id="mzAllInfo">
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="layui-col-md6"
					style="padding-left: 4px; padding-right: 5px;">
					<div class="layui-card">
						<div class="layui-card-header"
							style="border-bottom: 1px solid #f6f6f6;">
							<img style="margin-top: -2px; padding-right: 4px;"
								src="<%=request.getContextPath()%>/images/auditing/mark.png" />
							医院住院次均费用top10
						</div>
						<div class="layui-card-body layui-text">
							<!-- <table class="layui-table" style="border: 1px solid;">
								<colgroup>
									<col width="120">
									<col>
								</colgroup> -->
								<table class="layui-table" style="border: 1px solid;">
									<colgroup>
										<col width="100">
										<col>
									</colgroup>
									<tbody id="YYZRAllInfo">
									</tbody>
								</table>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
	<script
		src="<%=request.getContextPath()%>/app/js/indicator/costAnalysis-zy.js"></script>
	<script type="text/javascript">
var total = "";
var dataArr = new Array();
function data1(value,name){
	var o = new Object();
	o.value=value;
	o.name=name;
	return o;
}
Number.prototype.formatMoney = function (places, symbol, thousand, decimal) {
    places = !isNaN(places = Math.abs(places)) ? places : 2;
    symbol = symbol !== undefined ? symbol : "$";
    thousand = thousand || ",";
    decimal = decimal || ".";
    var number = this,
        negative = number < 0 ? "-" : "",
        i = parseInt(number = Math.abs(+number || 0).toFixed(places), 10) + "",
        j = (j = i.length) > 3 ? j % 3 : 0;
    return symbol + negative + (j ? i.substr(0, j) + thousand : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "1" + thousand) + (places ? decimal + Math.abs(number - i).toFixed(places).slice(2) : "");
};
function changeNumber(num){

	   if(num && num!=null){  
	        num = String(num);  
	        var left=num.split('.')[0],right=num.split('.')[1];  
	        right = right ? (right.length>=2 ? '.'+right.substr(0,2) : '.'+right+'0') : '.00';  
	        var temp = left.split('').reverse().join('').match(/(\d{1,3})/g);  
	        return (Number(num)<0?"-":"") + temp.join(',').split('').reverse().join('')+right;  
	    }else if(num===0){   //注意===在这里的使用，如果传入的num为0,if中会将其判定为boolean类型，故而要另外做===判断  
	        return '0.00';  
	    }else{  
	        return "";  
	    }  
}
var totalMoney=0;
var dataYear1=new Array();
        // 基于准备好的dom，初始化echarts实例
         $.ajax({
        	url:$WEB_ROOT_PATH+"/dhccApi/hosanalysis/hosanalysis/getPPersonCostByYearZY",
        	type:"post",
        	success:function(data){
        		//console.log(data);
        		for(var index  in data){
        			totalMoney += parseFloat(data[index].pnumber);
        			dataYear1[index] = data[index].year;
        			//alert(total.formatMoney());
        			var dataPie = data1(parseFloat(data[index].pnumber),data[index].year);
        			//console.log(data1(1,2));
        			//console.log(parseInt(data[index].pnumber));
        			dataArr[index] = dataPie;}
        		//dataYear=dataYear.sort();
        var myChart = echarts.init(document.getElementById('main'));

        /* app.title = '环形图'; */

        option = {
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}年: 共{c}元({d}%)"
    },
    legend: {
        x: 'center',
        data:dataYear1
    },

	title: {
        "text": '住院总次均费用',
        subtext:changeNumber(Math.round(totalMoney))+'元',
        //itemGap:90,
        "x": '60px',
        "y": '90px',
         textAlign: "center",
        "subtextStyle": {
            "fontWeight": 'normal',
            "fontSize": 14,
            color:'black',
            
        }
	}, 
    series: [
        {
            name:'住院总次均费用',
            type:'pie',
            radius: ['50%', '70%'],
            avoidLabelOverlap: false,
            label: {
                normal: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    show: true,
                    textStyle: {
                        fontSize: '30',
                        fontWeight: 'bold'
                    }
                }
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data:dataArr
        }
    ]
};
        
     // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);},error:function(){
        	}
        });
    </script>
	<script type="text/javascript">
    var total = "";
    var yearArr= new Array();
    var valueArr = new Array();
    var yearFlag = "2019";
    $.ajax({
    	url:$WEB_ROOT_PATH+"/dhccApi/hosanalysis/hosanalysis/getPCostByMonthZY",
    	type:"post",
    	success:function(data){
    		console.log(data);
    		for(var index in data){
    			var year = data[index].year;
    		
    			yearArr[index]=data[index].year;
    		
    			//yearFlag = data[index].year.substring(0,4);
    			//if(yearFlag == )
    			//console.log(yearFlag);
    			
    			valueArr[index]=parseFloat(data[index].pnumber);
    			
    		}
    		
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main1'));

     // 指定图表的配置项和数据
        var option = {
            title: {
                text: '2019年住院月度费用趋势'
            },
            tooltip: {},
            legend: {
                data:['住院月度次均费用']
            },
            xAxis: {
                data: yearArr,
                axisLabel:{
                    //X轴刻度配置
                    interval:0 //0：表示全部显示不间隔；auto:表示自动根据刻度个数和宽度自动设置间隔个数
               }
            },
            yAxis: {},
            series: [{
                name: '住院月度次均费用',
                type: 'line',
                data: valueArr
            }]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        	},error:function(data){
        		}
        	});
    </script>
	<script type="text/javascript">

    var dataArr = new Array();
    var dataYear2 = new Array();
    function data1(value,name){
    	var o = new Object();
    	o.value=value;
    	o.name=name;
    	return o;
    }
    function changeNumber(num){

 	   if(num && num!=null){  
 	        num = String(num);  
 	        var left=num.split('.')[0],right=num.split('.')[1];  
 	        right = right ? (right.length>=2 ? '.'+right.substr(0,2) : '.'+right+'0') : '.00';  
 	        var temp = left.split('').reverse().join('').match(/(\d{1,3})/g);  
 	        return (Number(num)<0?"-":"") + temp.join(',').split('').reverse().join('')+right;  
 	    }else if(num===0){   //注意===在这里的使用，如果传入的num为0,if中会将其判定为boolean类型，故而要另外做===判断  
 	        return '0.00';  
 	    }else{  
 	        return "";  
 	    }  
    }
    var totalMoney = 0;
        // 基于准备好的dom，初始化echarts实例
         $.ajax({
        	url:$WEB_ROOT_PATH+"/dhccApi/hosanalysis/hosanalysis/getPCostByYear",
        	type:"post",
        	success:function(data){
        		//console.log(data);
        		for(var index  in data){
        			totalMoney += parseFloat(data[index].pnumber);
        			console.log(totalMoney);
        			dataYear2[index] = data[index].year;
        			var dataPie = data1(parseFloat(data[index].pnumber),data[index].year);
        			//console.log(data1(1,2));
        			//console.log(parseInt(data[index].pnumber));
        			dataArr[index] = dataPie;}
        		//dataYear=dataYear.sort();
        		
        var myChart = echarts.init(document.getElementById('main2'));

        /* app.title = '环形图'; */

        option = {
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}年: 共{c}元({d}%)"
    },
    legend: {
        x: 'center',
        data:dataYear2
    },

	title: {
        "text": '住院总收入',
        subtext:changeNumber(Math.round(totalMoney))+'元',
        //itemGap:90,
        "x": '360px',
        "y": '90px',
         textAlign: "center",
        "subtextStyle": {
            "fontWeight": 'normal',
            "fontSize": 14,
            color:'black',
            
        }
	}, 
    series: [
        {
            name:'年份总收入',
            type:'pie',
            radius: ['50%', '70%'],
            avoidLabelOverlap: false,
            label: {
                normal: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    show: true,
                    textStyle: {
                        fontSize: '30',
                        fontWeight: 'bold'
                    }
                }
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data:dataArr
        }
    ]
};
     // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);},error:function(data){
        	}
        });
    </script>
	<!-- 统筹区门诊排名 -->
	<script>
	$.ajax({
		url:$WEB_ROOT_PATH+"/dhccApi/hosanalysis/hosanalysis/getAllAreaDataZR",
		type:"get",
		success:function(data){
			var table = $('#mzAllInfo');
			var flag=1;
    		var html = " <tr style='background:#E5F7EF'>"
					+"<td>排名</td>"
					+"<td>统筹区</td>"
					+"<td>住院次数</td>"
					+"<td>住院总费用</td>"
					+"<td>住院均次费用</td>"
					+"<td>占比</td>"
					+"</tr>"
					var i = 1;
                for(var index in data){
                	
                	if(index==10 || index > 10){
                		break;
                	}else{
                		
                	var area=data[index].area!=null?data[index].area:"";
                	if(area == ""){
                		continue;
                	}
                	flag++;
                	var number=data[index].pnumber!=null?data[index].pnumber:"-";
                	var pnumber=data[index].pnumber!=null?data[index].pnumber:"-";
                	var pcost = data[index].pcost!=null?data[index].pcost:"-";
                	var pavgcost = data[index].pavgcost!=null?data[index].pavgcost:"-";
                	var prate = data[index].prate!=null?data[index].prate:"-";
                	if(index%2==0){
                		html+="<tr style='background:#fff'>";
                	}else{
                		html+="<tr style='background:#F5FCF9'>";
                	}
               		html+="<td>"+(i++)+"</td>"
               		+"<td>"+area+"</td>"
               		+"<td>"+pnumber+"</td>"
               		+"<td>"+changeNumber(parseFloat(pcost))+"</td>"
               		+"<td>"+changeNumber(parseFloat(pavgcost))+"</td>"
               		+"<td>"+prate+"</td>"
               		+"</tr>";
                }
                
                
                }
                for(var i = flag;i<=10;i++){
                	html+="<td>"+i+"</td>"
               		+"<td>"+"</td>"
               		+"<td>"+"</td>"
               		+"<td>"+"</td>"
               		+"<td>"+"</td>"
               		+"<td>"+"</td>"
               		+"</tr>";
                }
                table.html(html);
    			
			
		},
		error:function(data){
			
		}
	});
	
	
	</script>
	<!-- 医院住院排名 -->
	<script>
	$.ajax({
		url:$WEB_ROOT_PATH+"/dhccApi/hosanalysis/hosanalysis/getAllYYDataZR",
		type:"get",
		success:function(data){
			var flag =1;
			var table = $('#YYZRAllInfo');
    		var html = " <tr style='background:#E5F7EF'>"
					+"<td>排名</td>"
					+"<td>医院</td>"
					+"<td>住院次数</td>"
					+"<td>住院总费用</td>"
					+"<td>住院均次费用</td>"
					+"<td>占比</td>"
					+"</tr>"
					var i = 1;
                for(var index in data){
                	
                	if(index==10){
                		break;
                	}else{
                		
                	var area=data[index].area!=null?data[index].area:"";
                	if(area == ""){
                		continue;
                	}
                	flag++;
                	var pnumber=data[index].pnumber!=null?data[index].pnumber:"-";
                	var pcost = data[index].pcost!=null?data[index].pcost:"-";
                	var pavgcost = data[index].pavgcost!=null?data[index].pavgcost:"-";
                	var prate = data[index].prate!=null?data[index].prate:"-";
                	if(index%2==0){
                		html+="<tr style='background:#fff'>";
                	}else{
                		html+="<tr style='background:#F5FCF9'>";
                	}
               		html+="<td>"+(i++)+"</td>"
               		+"<td>"+area+"</td>"
               		+"<td>"+pnumber+"</td>"
               		+"<td>"+changeNumber(parseFloat(pcost))+"</td>"
               		+"<td>"+changeNumber(parseFloat(pavgcost))+"</td>"
               		+"<td>"+prate+"</td>"
               		+"</tr>";
                }
                
                
                }
                for(var i = flag;i<=10;i++){
                	html+="<td>"+i+"</td>"
               		+"<td>"+"</td>"
               		+"<td>"+"</td>"
               		+"<td>"+"</td>"
               		+"<td>"+"</td>"
               		+"<td>"+"</td>"
               		+"</tr>";
                }
                table.html(html);
		}, 
		error:function(data){
			
		}
	});
	
	
	</script>

	<script>
  layui.config({
    base: '<%=request.getContextPath()%>/plugins/layui/layuiadmin/' //静态资源所在路径
									}).extend({
								index : 'lib/index' //主入口模块
							}).use([ 'index', 'console' ]);
				</script>
</body>
</html>