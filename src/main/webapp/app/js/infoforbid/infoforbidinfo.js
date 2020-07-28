//初始化	
layui.config({
	base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
}).use(['laydate'], function(){
	var $ = layui.$
	,form = layui.form
	,table = layui.table;
	var laydate=layui.laydate;
	laydate.render({
		elem:'#stopBeginDate'
			,trigger:'click'
			  ,type:'date'
				//,format:'yyyy-MM-dd' 
	});
	laydate.render({
		elem:'#stopOverDate'
			,trigger:'click'
			  ,type:'date'
				//,format:'yyyy-MM-dd' 
	});
	
});

