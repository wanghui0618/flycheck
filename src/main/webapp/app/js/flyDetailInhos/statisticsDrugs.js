//初始化	
layui.config(
	{
		base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	}
).extend(
	{
		index: 'lib/index' //主入口模块
	}
).use(
	['index', 'table','element'],
	function(){
		var $ = layui.$
		,form = layui.form
		,table = layui.table;
		table.render(
			{
				elem: '#userTable'
				,url: $WEB_ROOT_PATH+'/dhccApi/flyDetailInhos/flyDetailInhos/statisticsDrugs'
				,cellMinWidth: 80
				,height: tableHeight
				,cols: [[
					{title: '序号',templet: '#xuhao', width:'10%'}
					,{field: 'itemName', title: '项目名称', width:'30%'}
					,{field: 'year', title: '年份', width:'20%' }
					,{field: 'num', title: '总数' , width:'20%'}
					,{field: 'cost', title: '总费用', width:'20%'} 
				]]
				,page: true
			}
		);
	}
);