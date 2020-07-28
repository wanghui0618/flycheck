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
	['index', 'table', 'element'],
	function(){
		var $ = layui.$
		,form = layui.form
		,table = layui.table
		table.render(
			{
				elem: '#diagnosis'
				,cellMinWidth: 80
				,page: true
				,height: tableHeight
				,url: $WEB_ROOT_PATH+'/dhccApi/mainDiagnosis/mainDiagnosis/list'
				,cols: [[
					{field:'id', title: 'ID', sort: true, hide:true}
					,{type: 'numbers', width: 40, title: '编号',width:'10%'}
					,{field:'', title:'操作', align:'center', toolbar: '#table-useradmin-webuser',hide:rowOperate(['edit','delete']), width:'25%'}
					,{field: 'mainDiagnosis', title: '诊断名称' ,width:'25%'}
					,{field: 'projectId', title: '项目编码' ,width:'15%'}
					,{field: 'projectName', title: '项目名称' ,width:'25%'}
				]]
				,done: function(res, curr, count){
					initRightTable(res);
				}
			}
		);
		table.on(
			// 监听行双击事件
			'rowDouble(diagnosis)',
			function(obj){
				var data = obj.data;
				initRightTable(data);
			}
		);
		function initRightTable(data){
			table.render(
				{
					elem : '#diseaseAndDiagnosticStatistics'
					,url: $WEB_ROOT_PATH + '/dhccApi/medical/medical/relateDiagnosis'
					,cellMinWidth : 80
					,height : tableHeight
					,page : true
					,cols : [[
						{field:'id', title: 'ID', sort: true, hide:true}
						,{field: 'outDiagnosisName', title: '主要诊断', width:'25%'}
						,{field: 'name', title: '患者姓名', width:'25%'}
						,{field: 'orgName', title: '医疗机构', width:'25%'}
						,{field: 'totalCost', title: '总费用', width:'25%'}
					]]
					,where : {
						diagnosis : data.mainDiagnosis,
					}
				}
			);
		}
		// 监听搜索
		form.on(
			'submit(search)',
			function(data){
				var field = data.field;
				var inhosDiag=field.inhosDiag
				//执行重载
				initleftTable(inhosDiag);
			}
		);
		function initleftTable(data){
			table.render(
				{
					elem : '#diagnosis'
					,url : $WEB_ROOT_PATH+'/dhccApi/mainDiagnosis/mainDiagnosis/list'
					,cellMinWidth : 80
					,height : tableHeight
					,page : true
					,cols : [[
						{field:'id', title: 'ID', sort: true, hide:true}
						,{type: 'numbers', width: 40, title: '编号',width:'10%'}
						,{field:'', title:'操作', align:'center', toolbar: '#table-useradmin-webuser',hide:rowOperate(['edit','delete']), width:'25%'}
						,{field: 'mainDiagnosis', title: '诊断名称' ,width:'25%'}
						,{field: 'projectId', title: '项目编码' ,width:'15%'}
						,{field: 'projectName', title: '项目名称' ,width:'25%'}
					]]
					,where : {
						diagnosis : data,
					}
				}
			);
		}
		table.on(
			// 工具栏监听
			'tool(diagnosis)',
			function(obj){
				var data = obj.data;
				if(obj.event == 'edit'){
					layer.open(
						{
							type : 2,
							title : '修改信息',
							content : $WEB_ROOT_PATH + '/fly/flyScreenMainDiagnosisAdd',
							maxmin : true,
							area : ['600px', '450px'],
							btn : ['确定', '取消'],
							success : function(layero, index){
								var iframeWindow = window['layui-layer-iframe' + index];
								// 向子页面传递参数
								iframeWindow.child(JSON.stringify(data));
							},
							yes : function(index, layero){
								// 子页面
								var iframeWindow = window['layui-layer-iframe' + index];
								// 子页面提交按钮，值与lay-filter对应
								var submitId = 'LAY-user-front-submit';
								// 监听提交
								iframeWindow.layui.form.on(
									'submit(' + submitId + ')',
									function(data){
										// 获取提交的字段
										var field = data.field;
										var url = $WEB_ROOT_PATH + '/dhccApi/mainDiagnosis/mainDiagnosis/save';
										$.post(
											url,
											field,
											function(result){
												layer.msg('修改成功！');
												// 数据刷新
												table.reload('diagnosis');
												// 关闭弹层
												layer.close(index);
											}
										);
									}
								);
								var submit = layero.find('iframe').contents().find('#' + submitId);
								submit.trigger('click');
							}
						}
					);
				} else if(obj.event == 'delete'){
					layer.confirm(
						'确定删除该条记录吗？',
						function(index){
							var url = $WEB_ROOT_PATH + '/dhccApi/mainDiagnosis/mainDiagnosis/delete';
							$.post(
								url,
								{
									'flyScreenMainDiagnosis.id' : data.id
								},
								function(result){
									table.reload('diagnosis');
									layer.msg('已删除');
								}
							);
						}
					);
				}
			}
		);
		var active = {
			// 新增按钮
			add : function(){
				layer.open(
					{
						type : 2,
						title : '添加信息',
						content : $WEB_ROOT_PATH + '/fly/flyScreenMainDiagnosisAdd',
						maxmin : true,
						area : ['600px', '450px'],
						btn : ['确定', '取消'],
						yes : function(index, layero){
							// 子页面
							var iframeWindow = window['layui-layer-iframe' + index];
							// 子页面提交按钮，值与lay-filter对应
							var submitId = 'LAY-user-front-submit';
							// 监听提交
							iframeWindow.layui.form.on(
								'submit(' + submitId + ')',
								function(data){
									// 获取提交的字段
									var field = data.field;
									var url = $WEB_ROOT_PATH + '/dhccApi/mainDiagnosis/mainDiagnosis/save';
									$.post(
										url,
										field,
										function(result){
											layer.msg('添加成功！');
											// 数据刷新
											table.reload('diagnosis');
											// 关闭弹层
											layer.close(index);
										}
									);
								}
							);
							var submit = layero.find('iframe').contents().find('#' + submitId);
							submit.trigger('click');
						}
					}
				);
			}
		}
		// 按钮点击绑定
		$('.layui-btn.layuiadmin-btn-useradmin').on(
			'click',
			function(){
				var type = $(this).data('type');
				active[type] ? active[type].call(this) : '';
			}
		);
	}
);