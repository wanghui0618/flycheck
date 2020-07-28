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
				,url: $WEB_ROOT_PATH+'/dhccApi/flymedical/flymedical/screenSameEntryAndExitDate'
				,cellMinWidth: 80
				,height: tableHeight
				,cols: [[
					{field:'id', title: 'ID', sort: true, hide:true}
					,{title: '序号',templet: '#xuhao', width:60}
					,{field: 'admissionDate', title: '入院时间', width:130}
					,{field: 'dischargeDate', title: '出院时间', width:130 }
					,{field: 'hospitalName', title: '医疗机构名称' , width:260}
					,{field: 'patientName', title: '姓名', width:100}  
					,{field: 'patientGender', title: '性别', width:60}
					,{field: 'admissionDeptName', title: '入院科室', width:130}
					,{field: 'dischargeDeptName', title: '出院科室', width:130}
					,{field: 'icd10NameBasy', title: '主要诊断', width:280} 
					,{field: 'patientAddress', title: '地址', width:280}
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