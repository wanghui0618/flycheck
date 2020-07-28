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
	['index', 'table'],
	function(){
		var $ = layui.$,
			form = layui.form,
			table = layui.table;
		//加载系统下拉字典
		$.getJSON(
			$WEB_ROOT_PATH+'/dhccApi/singleDisease/singleDisease/findSystem', 
			function(data){
				var dataList = data.singleDiseases;
			    for(var i=0 ;i<dataList.length;i++){
			    	var mm="<option value='"+dataList[i].system+"'>"+dataList[i].system+"</option>";
			    	$("#system").append(mm);
			    }
			    form.render('select');
			}
		);
		table.render(
			{
				elem: '#medicalrangeinfo',
				url: $WEB_ROOT_PATH+'/dhccApi/singleDisease/singleDisease/list',
				height: tableHeight,
				cellMinWidth: 80,
				where: {ilegalChild: '1'},
			    cols: [[
					{type: 'numbers', title: '序号' },
					{field:'id', width:80,hide:true,title: '编号',align:"center"},
					{title:'操作', align:'center',toolbar: '#table-useradmin-webuser', width:230	,align:"center",hide:rowOperate(['insureunit-edit','insureunit-delete','insureunit-look'])},
					{field:'system', width:150,title: '疾病系统',align:'center'},
					{field:'code', width:100,hide:true,title: '系统序号'},
					{field:'cityCode',width:100, title: '城市编码',hide:"true",align:"center"},
					{field:'mainDiagCode', title: '疾病编码',align:"center"},
					{field:'mainDiagName', width:200,title: '病种名称',align:"center"},
					{field:'exceptionsCode',width:100, title: '除外内容医保编码',hide:"true",align:"center"},
					{field:'treatmentType',width:150, title: '治疗类别',align:"center"},
					{field:'anaesthesiaWay', title: '麻醉方式',align:"center"},
					{field:'type1', title: '一类',align:"center"},
					{field:'type2', title: '二类',align:"center"},
					{field:'exceptions',width:150, title: '除外内容',align:"center"}
					/*{field:'remark',title: '备注',align:"center"}*/
				]],
				page: true
			}
		);
		hideButtonStatic();//按钮权限
		//监听搜索
		form.on(
			'submit(LAY-user-front-search)',
			function(data){
				var field = data.field;
				//执行重载
				layui.table.reload(
					'medicalrangeinfo',
					{where: field}
				);
			}
		);
		//添加事件
		var active = {
			add: function(){
				//添加记录
				layer.open(
					{
						type: 2,
						title: '保存单病种收费信息',
						content: $WEB_ROOT_PATH+'/singleDisease/singleDiseaseInfo',
						maxmin: true,
						area: ['800px', '420px'],
						btn: ['确定', '取消'],
						success: function(layero, index){
							var iframeWindow = window['layui-layer-iframe'+ index];
							//加载select下拉option
							iframeWindow.loadSelect();
						},
						yes: function(index, layero){
							var iframeWindow = window['layui-layer-iframe'+ index],
								submitID = 'LAY-cityorg-front-submit',
								submit = layero.find('iframe').contents().find('#'+ submitID);
							iframeWindow.layui.form.on(
								'submit('+ submitID +')',
								function(data){
									var field = data.field; //获取提交的字段
									var url=$WEB_ROOT_PATH+"/dhccApi/singleDisease/singleDisease/save";
									$.post(
										url,
										field,
										function(result){
											var inFlag= result.operateSuccess; 
											if(inFlag){
												layer.msg('保存成功!');
												//后台成功后，静态更新表格中的数据
												table.reload('medicalrangeinfo');
												//关闭弹层
												layer.close(index);
											}else{
												layer.msg('未知错误，保存失败!');
												return false;
											}
										}
									);
								}
							);
							submit.trigger('click');
						}
					}
				);
			}
		};
		//监听行点击
		table.on(
			'tool(medicalrangeinfo)',
			function(obj){
				var data = obj.data;
				if(obj.event === 'delete'){
					layer.confirm(
						'确定要删除该条数据？',
						function(index){
							//执行 Ajax 后重载
							var url=$WEB_ROOT_PATH+"/dhccApi/singleDisease/singleDisease/delete";
							$.post(
								url,
								{'singleDisease.id':data.id},
								function(result){
									table.reload('medicalrangeinfo');
									layer.msg('删除成功！');
								}
							);
						}
					);
				}else if(obj.event === 'update'){
					//修改
					layer.open(
						{
							type: 2,
							title: '修改单病种收费信息',
							content: $WEB_ROOT_PATH+'/singleDisease/singleDiseaseInfo',
							maxmin: true,
							area: ['800px', '420px'],
							btn: ['确定', '取消'],
							success: function(layero, index){
								var iframeWindow = window['layui-layer-iframe'+ index];
								//向此iframe层方法 传递参数
								iframeWindow.child(JSON.stringify(data));/*调用弹出窗口，填充该行数据到修改表单*/
							},
							yes: function(index, layero){
								var iframeWindow = window['layui-layer-iframe'+ index],
								submitID = 'LAY-cityorg-front-submit',
								submit = layero.find('iframe').contents().find('#'+ submitID);
								//监听提交
								iframeWindow.layui.form.on(
									'submit('+ submitID +')',
									function(data){
										//获取提交的字段
										var field = data.field; 
										console.log(field);
										//提交 Ajax后台 
										var url=$WEB_ROOT_PATH+"/dhccApi/singleDisease/singleDisease/save";
										$.post(
											url,
											field,
											function(result){
												var inFlag= result.operateSuccess; 
												if(inFlag){
													layer.msg('修改成功!');
													//后台成功后，静态更新表格中的数据
													table.reload('medicalrangeinfo');
													//关闭弹层
													layer.close(index);
												}else{
													layer.msg('未知错误，修改失败!');
													return false;
												}
											}
										);
									}
								);
								submit.trigger('click');
							}
						}
					);
				}else if(obj.event === 'view'){
					//修改
					layer.open(
						{
							type: 2,
							title: '查看单病种收费信息',
					        content: $WEB_ROOT_PATH+'/singleDisease/singleDiseaseInfo-view',
					        maxmin: true,
					        area: ['800px', '400px'],
					        btn: ['关闭'],
			    	        success: function(layero, index){
			    	        	var iframeWindow = window['layui-layer-iframe'+ index];
					        	//向此iframe层方法 传递参数
					        	iframeWindow.child(JSON.stringify(data));
			    	        }
						}
					);
				}
			}
		);
		//按钮事件绑定底层方法-勿动
		$('.layui-btn.layuiadmin-btn-useradmin').on(
			'click',
			function(){
				var type = $(this).data('type');
				active[type] ? active[type].call(this) : '';
			}
		);
	}
);