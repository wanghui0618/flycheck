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
				,url: $WEB_ROOT_PATH+'/dhccApi/medicaldetail/medicalDetail/statisticsDrugs'
				,cellMinWidth: 80
				,height: tableHeight
				,cols: [[
					{title: '序号',templet: '#xuhao', width:'10%'}
					,{field: 'itemName', title: '项目名称', width:'30%'}
					,{field: 'balanceDate', title: '结算日期', width:'20%' }
					,{field: 'itemNum', title: '总数' , width:'20%'}
					,{field: 'itemCost', title: '总费用', width:'20%'} 
				]]
				,page: true
			}
		);
	}
);