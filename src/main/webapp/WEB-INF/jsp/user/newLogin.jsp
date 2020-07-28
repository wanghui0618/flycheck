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
					最近登录用户信息
					<div style="margin-top: 6px;display: inline-block;margin-left: 20px;">
  					<input placeholder="请输入查询信息" style="background-image: url(<%=request.getContextPath() %>/images/oauth/search.png);background-repeat: no-repeat;border: solid 1px #e6e6e6;background-position: right;width:180px;height:32px;padding-right: 20px;" type="text" name="userVo.keyDom" id="keyDom"  class="layui-input" >
  					</div>
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
		var url = window.location.href
		var num = url.split("?num=")[1];
		table.render({
			elem: '#violationDetail'
			,url: $WEB_ROOT_PATH+"/dhccApi/user/user/listNewLogin?num="+num
			,height: tableHeight+5
			,cellMinWidth: 80
		    ,cols: [[
			 {type: 'numbers', title: '序号' }
			,{field:'name',title: '姓名',width: 100,align:"center"}
			,{field:'loginName',width: 100,title: '用户名',align:"center"}
			,{field:'phone',width: 140,title: '手机号码',align:"center"}
			,{field:'email',width: 140,title: '邮箱',align:"center"}
			,{field:'unitName',width: 130,title: '组织名称',align:"center"}
			,{field:'roleName',width: 130,title: '角色名称',align:"center"}
			,{field:'loginTime',title: '最近一次登录时间',align:"center"}
			,{field:'logoutTime',title: '最近一次登录退出时间',align:"center"}
			,{field:'minutes',title: '在线时长',align:"center"}
			]]
		,page: true
		,limit:10
		});

		//监听搜索
		/* form.on('submit(LAY-user-front-search)', function(data){
			var field = data.field;
			//执行重载
			layui.table.reload('violationDetail', {
				where: field
			});
		}); */
		var flag = true;
	    $('#keyDom').on('compositionstart',function(){
	        flag = false;
	    })
	    $('#keyDom').on('compositionend',function(){
	        flag = true;
	    })
	    $('#keyDom').on('input',function(){
	        setTimeout(function(){
	            if(flag){
	            	var keyDom = document.getElementById("keyDom").value;
	            	layui.table.reload('violationDetail', {
	                    where: {keyDom:keyDom}
	                    ,page: { curr: 1}
	                });
	            }
	        },0)
	    })
		
		//按钮事件绑定底层方法-勿动
		$('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});
	});
	
	</script>
</body>
</html>