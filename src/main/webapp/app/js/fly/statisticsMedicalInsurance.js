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
		
	['index', 'table', 'element', 'laydate'],
	
	function(){
		
		var $ = layui.$;
		
		$('.layui-btn.layuiadmin-btn-useradmin').on(
			'click',
			function(){
				var type = $(this).data('type');
				active[type] ? active[type].call(this) : '';
			}
		);
		
		var laydate = layui.laydate;
		//年范围
		laydate.render(
			{
				elem: '#yearPlug'
				,value: nowYear()
				,max:nowYear()
				,type: 'year'
			}
		);
		
		var table = layui.table;
		
		table.render(
			{
				elem: '#firstTable'
				,url: $WEB_ROOT_PATH+'/dhccApi/medicaldetail/medicalDetail/findByYear'
				,cellMinWidth: 80
				,height: tableHeight
				,cols: [[
					{title: '序号',templet: '.serialNumber', width:'10%'}
					,{field: 'useDay', title: '年份', width:'15%' }
					,{field: 'sumAmount', title: '总金额', width:'25%'}
					,{field: 'insCost', title: '医保金额' , width:'25%'}
					,{field: 'selfPayAmount', title: '自付金额' , width:'25%'}
				]]
				,page: true
			}
		);
		
		table.render(
			{
				elem : '#secondTable'
				,url : $WEB_ROOT_PATH+'/dhccApi/medicaldetail/medicalDetail/findByMonth'
				,cellMinWidth : 80
				,height : tableHeight
				,cols : [[
					{title: '序号',templet: '.serialNumber', width:'10%'}
					,{field: 'useDay', title: '月份', width:'15%' }
					,{field: 'sumAmount', title: '总金额', width:'25%'}
					,{field: 'insCost', title: '医保金额' , width:'25%'}
					,{field: 'selfPayAmount', title: '自付金额' , width:'25%'}
				]]
				,page : true
				,where : {
					year : nowYear()
				}
			}
		);
		
		var form = layui.form;
		//监听搜索
		form.on(
			'submit(LAY-org-front-search)',
			function(data){
				var field = data.field;
				//执行重载
				layui.table.reload(
					'secondTable',
					{
						where: field
						,page: {
							curr: 1 //重新从第 1 页开始
						}
					}
				);
			}
		);
		
	}
	
);

function nowYear(){
	var date=new Date();
	var nowYear=date.getFullYear();
	return nowYear.toString();
}