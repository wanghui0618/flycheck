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
</head>
<body>
	<div class="layui-fluid">
		<div class="layui-card">
			<div class="layui-form layui-card-header layuiadmin-card-header-auto">
				<!-- <div class="layui-inline" >
					<label class="layui-form-label">医院编码</label>
					<div class="layui-input-inline" id="xldown">
						 <input type="text"
                               name="hospitalTopStatistics.orgCode" placeholder="请输入医院编码" autocomplete="off"
                               class="layui-input">
					</div>
				</div>
				<div class="layui-inline" >
					<label class="layui-form-label">医院名称</label>
					<div class="layui-input-inline" id="xldown">
						<select id="ruleType" name="violationDetail.typeNo" xm-select="select2" xm-select-direction="down" lay-search>
							<option value="" disabled selected style='display:none;'>请选择类型</option>
						</select>
					</div>
				</div> -->
				<div class="layui-inline" >
					<label class="layui-form-label">医院级别</label>
					<div class="layui-input-inline" id="xldown">
						<select id="ruleType" name="hospitalTopStatistics.orgLevel" xm-select="select2" xm-select-direction="down" lay-search>
							<option value="" disabled selected style='display:none;'>请选择级别</option>
							<option value="一级" >一级</option>
							<option value="二级" >二级</option>
							<option value="三级" >三级</option>
						</select>
					</div>
				</div>
				<div class="layui-inline">
					<button id="medical-jpp-detail-search-cx"
						class="layui-btn layuiadmin-btn-useradmin"
						lay-submit lay-filter="LAY-user-front-search2">
						<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
					</button>
				</div>
				
			</div>

			<div class="layui-card-body">
				 <div class="layui-row layui-col-space15">
            	 <div class="layui-col-md6">
				   <table id="hospitalTopStatisticsTable" class="layui-hide" lay-filter="hospitalTopStatisticsTable"></table>
				 </div>
				 <div class="layui-col-md6">
  					<div class="layui-card-body">
   						<div id="main" style="height: 435px;"></div>
  					</div>	
				 </div>
				 </div>
			</div>
		</div>
	</div>
	<script src="<%=request.getContextPath() %>/js/echarts_jpp/echarts-2.2.7/build/dist/echarts.js"></script>
	<script type="text/javascript">
	require.config({
        paths: {
            echarts: $WEB_ROOT_PATH+'/js/echarts_jpp/echarts-2.2.7/build/dist'
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
			elem: '#hospitalTopStatisticsTable'
			,url: $WEB_ROOT_PATH+"/dhccApi/hospitalTopStatistics/hospitalTopStatistics/listVo"
			,where: {}
			,height:tableHeight
			,limit:10
			,cellMinWidth: 80
		    ,cols: [[
			 {type: 'numbers', title: '序号' }
			,{field:'id', hide:true,title: '编号'}
			,{field:'orgCode', width:115,title: '医院编码'}
			,{field:'orgName',width:220, title: '医院名称'}
			,{field:'orgLevel', title: '医院级别'}
			/* ,{field:'inhosDate', title: '入院日期'}
			,{field:'diagnum', title: '住院数'} */
			
			]]
		,page: true
		 ,done:function(res){
			 $('tr').eq(1).css("background-color","#C0C0C0");
         	var result=res.data;
         	if(result.length>0){
         		var orgCode=result[0].orgCode;
         		reloadTable(orgCode);
         	}
		 }
		});
	
		
		//监听搜索
		form.on('submit(LAY-user-front-search2)', function(data){
			var field = data.field;
			var tablenew=layui.table;
			//执行重载
			tablenew.reload('hospitalTopStatisticsTable', {
				where: field
			});
		});
		//行点击
		table.on('row(hospitalTopStatisticsTable)', function(obj){
			 $("tr").css("background-color",""); 
	         $(this).css("background-color","#C0C0C0"); 
			 var result=obj.data;
			 reloadTable(result.orgCode);
		 });
		//按钮事件绑定底层方法-勿动
		$('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});
	});
	
	
	function reloadTable(orgCode) {
	 require(['echarts','echarts/chart/line' // 使用柱状图就加载bar模块，按需加载
		 	  ],
	          function (echarts) {
					echarts.init(document.getElementById('main')).dispose();//销毁前一个实例
					memoBar = echarts.init(document.getElementById('main'));//构建下一个实例
							
		 // 为echarts对象加载数据 
         $.ajax({
		         url:$WEB_ROOT_PATH+"/dhccApi/hospitalTopStatistics/hospitalTopStatistics/searchByorgCode",
		         type : "post",		
		         async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
		         data:{"hospitalTopStatistics.orgCode":orgCode},
		         success : function(result) {
		        	 var hvdata=result;
		        	 if (hvdata != null && hvdata.length > 0) {
		        		 var k;
		        		 //if(hvdata.length>10){
		        			// k=20;
		        		// }else{
		        			 k=hvdata.length;
		        		 //}
		        		 var XData=[];
						 var YData=[];
		        		 for(var i=0;i<k;i++){ 
			                   /* option.series[0].data.push(dir);
			                   option.xAxis[0].data.push(hvdata[i].coName); */
			                 //拼接XData YData
		        			XData.push(hvdata[i].inhosDate);
			                YData.push(parseInt(hvdata[i].diagnum));
			             } 
		        		 console.log(YData);
		        		  var option = {
									        /* 线条颜色，可设置多个颜色 */
									        color: ['#ffa82f'],
									        /* 图像四周边距设置 */
									        grid:{
									            left:30,
									            top:30,
									            right:20,
									            bottom:30
										 },
										 /* 图例说明 */
										 legend: {
										     // 图例排项 vertical-"竖向"; horizontal-"横向"
									             orient: 'horizontal', 
									             // 图例组件离容器左侧的距离
									            right : 60,
										    top: 0,
										    //图例文字的样式
										    textStyle:{
										    	color:'#6ab2ec',
										    },
									            // 与series中每个name一一对应
									            data:['住院数']
									        },
										 /* 鼠标悬浮时显示数据 */
										 tooltip : {
									             trigger: 'axis',
									             axisPointer : {            // 坐标轴指示器，坐标轴触发有效
									                 type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
									             }
									         },
									        xAxis: {
									            type: 'category',
									            data: XData,
									            //设置轴线的属性
									            axisLine:{
									                lineStyle:{
									                    color:'#6ab2ec',
									                }
									             },
									             //调整x轴的lable
									             axisLabel:{ 
									        		rotate:"30",
									                textStyle:{
									                fontSize:10 // 让字体变小
									                }
									            } 
									        },
									        yAxis: {
									            type: 'value',
									            // 控制网格线是否显示
									            splitLine: {
									                 show: true, 
									                 //  改变轴线颜色
									                 lineStyle: {
									                     // 使用深浅的间隔色
									                     color: ['#132a6e']
									                 }                            
									             },
									            //设置轴线的属性
									            axisLine:{
									                 lineStyle:{
									                     color:'#6ab2ec',
									                 }
									             } 
									        },
									        /* 数据配置，若有多条折线则在数组中追加{name: , data: } */
									        series: [{
									            name:'住院数',
									            data: YData,
									            type: 'line',
									            symbol: 'circle',
									            // 设置折点大小
									            symbolSize: 10,
									            // 设置为光滑曲线
									            smooth: false
									        },]
									    };
		        		 memoBar.setOption(option); 
		        	 }
		        	 
		         }
         });
        
	 }); 
	}
	
	</script>
</body>
</html>