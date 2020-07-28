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
				,url: $WEB_ROOT_PATH+'/dhccApi/medical/medical/screenSameEntryAndExitDate'
				,cellMinWidth: 80
				,height: tableHeight
				,cols: [[
					{field:'id', title: 'ID', sort: true, hide:true}
					,{title: '序号',templet: '#xuhao', width:'5%'}
					,{field: 'inhosDate', title: '入院时间', width:'7%'}
					,{field: 'outhosDate', title: '出院时间', width:'7%' }
					,{field: 'orgName', title: '医疗机构名称' , width:'11%'}
					,{field: 'name', title: '姓名', width:'5%'}  
					,{field: 'sex', title: '性别', width:'5%'}
					,{field: 'inDiagnosisName', title: '入院诊断', width:'15%'}
					,{field: 'outDiagnosisName', title: '出院诊断', width:'15%'}
					,{field: 'insureCost', title: '医保金额', width:'10%'} 
					,{field: 'notInsureCost', title: '非医保金额', width:'10%'}
					,{field: 'claimCost', title: '报销金额', width:'10%'}
				]]
				,page: true
			}
		);
		//监听搜索
		form.on(
			'submit(LAY-org-front-search)',
			function(data){
				var field = data.field;
				//执行重载
				layui.table.reload(
					'userTable',
					{
						where: field
						,page: {
							curr: 1 //重新从第 1 页开始
						}
					}
				);
			}
		);
		layui.use(
			'laydate',
			function() {
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
			}
		);
		function nowYear(){
			var date=new Date();
			var nowYear=date.getFullYear();
			return nowYear.toString();
		}
		$('.layui-btn.layuiadmin-btn-useradmin').on(
			'click',
			function(){
				var type = $(this).data('type');
				active[type] ? active[type].call(this) : '';
			}
		);
	}
);