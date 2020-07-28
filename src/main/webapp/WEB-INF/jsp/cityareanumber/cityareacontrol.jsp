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
	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/layui/css/layui.css"
	media="all">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/style/admin.css"
	media="all">

<title>违规类型明细统计</title>
<style>
.pt {
	width: 300px;
}
</style>
</head>
<body>
	<div class="layui-fluid">
		<div class="layui-card">
			<div class="layui-form layui-card-header layuiadmin-card-header-auto">
				<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
				接入地区数据对照情况	
			</div>

			<div class="layui-card-body">
				 <div class="layui-row layui-col-space15">
            	 <div class="layui-col-md12">
				   <table id="violationDetail" class="layui-hide" lay-filter="violationDetail"></table>
				 </div>
				<!--  <div class="layui-col-md7">
				   	<div id="main" style="height: 485px;"></div>
				 </div> -->
				 </div>
			</div>
		</div>
	</div>
	<script  src="<%=request.getContextPath() %>/js/echarts_home/echarts.min.js"></script>
	<script type="text/javascript">
	 // 基于准备好的dom，初始化echarts实例
    var dataAxis=new Array();
	var data=new Array();
     $.ajax({
	url : $WEB_ROOT_PATH+"/dhccApi/homerest/homerest/findCityNumber",
	type : "get",
	success : function(data1) {	
		for(var index in data1){
			dataAxis[index] = data1[index].name;
			data[index]=parseInt(data1[index].digit);
		}
		  var myChart = echarts.init(document.getElementById('main'));
	        // 指定图表的配置项和数据
	        var option = {
	            title: {
	                text: ''
	            },
	            tooltip: {},
	            legend: {
	            	x: '30px',
	                y: '5px',
	                data:['数量']
	            },
	            xAxis: {
	                data: dataAxis
	            },
	            yAxis: {},
	            series: [{
	                name: '数量',
	                type: 'bar',
	                barWidth:16,
	                data: data,
                    itemStyle:{
                        normal:{
                            color:'#319CFE'
                        }
                    }
	            }]
	        };
	        // 使用刚指定的配置项和数据显示图表。
	        myChart.setOption(option);
		
	},
	error : function(data) {
		alert("服务器错误");
	}
});
   
    
	</script>
	
	
	
	<script type="text/javascript">
	//初始化	
	layui.config({
		base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	}).extend({
		index: 'lib/index' //主入口模块
	}).use(['index', 'table'], function(){
		var $ = layui.$
		,form = layui.form
		,table = layui.table;
		
		table.render({
			elem: '#violationDetail'
			/* ,url: $WEB_ROOT_PATH+"/dhccApi/cityAreaNumber/cityAreaNumber/listVo" */
			,height: document.documentElement.clientHeight-65

			,cellMinWidth: 80
		    ,cols: [[
			 {type: 'numbers', title: '序号' }
			,{field:'area',width:150,title: '行政区域',align:"center"}
			,{field:'city', width:150,title: '接入城市',align:"center"}
			,{field:'time', width:150,title: '接入时间',align:"center"}
			,{field:'icd', width:110,title: 'icd对照数',align:"center"}
			,{field:'noticd', width:110,title: '未对照',align:"center"}
			,{field:'drug', width:110,title: '药品对照数',align:"center"}
			,{field:'notdrug', width:110,title: '未对照',align:"center"}
			,{field:'seedoc', width:110,title: '诊疗对照数',align:"center"}
			,{field:'notseedoc', width:110,title: '未对照',align:"center"}
			,{field:'treamet', width:110,title: '耗材对照数',align:"center"}
			,{field:'nottreamet', width:110,title: '未对照',align:"center"}
			]]
			,data:[{
				"area":"四川省",
				"city":"遂宁市",
				"time":"2016-10-21",
				"icd":"351",
				"noticd":"45",
				"drug":"441",
				"notdrug":"22",
				"seedoc":"211",
				"notseedoc":"661",
				"treamet":"33",
				"nottreamet":"616"
			},{
				"area":"四川省",
				"city":"成都市",
				"time":"2018-3-21",
				"icd":"3501",
				"noticd":"15",
				"drug":"4461",
				"notdrug":"2",
				"seedoc":"2131",
				"notseedoc":"6",
				"treamet":"3321",
				"nottreamet":"16"
			}	,{
				"area":"北京市",
				"city":"北京市",
				"time":"2014-6-07",
				"icd":"506",
				"noticd":"3",
				"drug":"850",
				"notdrug":"5",
				"seedoc":"640",
				"notseedoc":"14",
				"treamet":"563",
				"nottreamet":"7"
			}	,{
				"area":"陕西省",
				"city":"西安市",
				"time":"2019-1-21",
				"icd":"35",
				"noticd":"45",
				"drug":"44",
				"notdrug":"22",
				"seedoc":"21",
				"notseedoc":"66",
				"treamet":"33",
				"nottreamet":"616"
			}	,{
				"area":"山西省",
				"city":"太原市",
				"time":"2019-6-21",
				"icd":"351",
				"noticd":"45",
				"drug":"44",
				"notdrug":"221",
				"seedoc":"21",
				"notseedoc":"661",
				"treamet":"33",
				"nottreamet":"616"
			}	,{
				"area":"辽宁省",
				"city":"大连市",
				"time":"2015-08-19",
				"icd":"335",
				"noticd":"45",
				"drug":"444",
				"notdrug":"22",
				"seedoc":"521",
				"notseedoc":"66",
				"treamet":"633",
				"nottreamet":"616"
			}		]
		,page: true
		});

		//监听搜索
		form.on('submit(LAY-user-front-search)', function(data){
			var field = data.field;
			//执行重载
			layui.table.reload('violationDetail', {
				where: field
			});
		});
		
		//按钮事件绑定底层方法-勿动
		$('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});
	});
	
	</script>
</body>
</html>