//初始化
var firstData="";
layui.config({
	base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
	index: 'lib/index' //主入口模块
}).use(['index', 'table'], function(){
	var $ = layui.$
	,form = layui.form
	,table = layui.table;
	
	 table.render({
		 elem: '#drugTable'
			 ,url: $WEB_ROOT_PATH+'/dhccApi/druginteraction/drugInteraction/listVo'
			 ,height: document.documentElement.clientHeight-65
			 ,cellMinWidth: 150
			/* ,toolbar: '#table-useradmin-webuser2'*/
	 ,cols: [[
		 {type: 'numbers',align: 'center', title: '序号' }
		 ,{field:'id', width:80,hide:true,title: '编号'}
		 ,{title:'操作', align:'center',toolbar: '#table-useradmin-webuser1', width:250,hide:rowOperate(['drugInteraction-drug-show','drugInteraction-interaction-add','drugInteraction-drug-update','drugInteraction-drug-delete'])}
		 ,{field:'drugName',align: 'center', title: '药品通用名称'}
		 ,{field:'tradeName',align: 'center', title: '商品名称'}
		 ,{field:'tradeEnglishName',align: 'center', title: '商品英文名称'}
		 ,{field:'alias',align: 'center', title: '别名'}
		 ,{field:'englisName',align: 'center', title: '英文名称'}
		 ,{field:'chinesePinyin',align: 'center', title: '汉语拼音'}
		 ,{field:'mainComponents',align: 'center', title: '主要成分'}
		 ]]
	 ,page: true
	 ,done(res){
		 //console.log(res.data[0].drugName);
		 firstData=res.data[0].drugName;
		 
		 reflash2($,form,table);//渲染子表
	 }
	 });
	 
	
	 
	 //监听头部工具条
	 table.on('toolbar(drugTable)', function(obj){
		 if(obj.event==='add'){
			//添加记录
				layer.open({
					type: 2
					,title: '添加药物'
					,content: $WEB_ROOT_PATH+'/druginteraction/drugNameInfo'
					,maxmin: true
					,area: ['500px', '200px']
					,btn: ['确定', '取消']
					,success: function(layero, index){
						var iframeWindow = window['layui-layer-iframe'+ index];
					}
					,yes: function(index, layero){
						var iframeWindow = window['layui-layer-iframe'+ index]
						,submitID = 'LAY-cityprice-front-submit'
						,submit = layero.find('iframe').contents().find('#'+ submitID);
						iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
						var field = data.field; //获取提交的字段
						//提交 Ajax后台 
						var url=$WEB_ROOT_PATH+"/dhccApi/druginteraction/drugInteraction/saveDrugName";
						$.post(url,field,function(result){
							var inFlag= result.inFlag; 
							if(inFlag==0){
								layer.msg('保存成功!');
								//后台成功后，静态更新表格中的数据
								layer.close(index); //关闭弹层
								layui.table.reload('drugTable', {
									where: {"drugInteraction.drugName": ''  }
								});
								layui.table.reload('interactionDrugNameTable', {
									where: {"drugInteraction.drugName": field['drugInteraction.drugName']  }
								});
							}else if(inFlag==1){
								layer.msg('已经存在该条记录!');
								return false;
							}
						});
					});
					submit.trigger('click');
					}
					}); 
		 }
	 });
	 
	 hideButtonStatic();//静态按钮授权
	//监听搜索
	form.on('submit(LAY-user-front-search)', function(data){
		var field = data.field;
		//执行重载
		layui.table.reload('drugTable', {
			where: field
		});
		layui.table.reload('interactionDrugNameTable', {
			where: field
		});
	});
	//添加
	form.on('submit(LAY-user-front-add)', function(data){
		var field = data.field;
		//执行重载
		layer.open({
			type: 2
			,title: '添加药物相互作用'
			,content: $WEB_ROOT_PATH+'/druginteraction/drugNameInfo-update'
			,maxmin: true
			,area: ['1400px', '500px']
			,btn: ['确定', '取消']
			,success: function(layero, index){
				var iframeWindow = window['layui-layer-iframe'+ index];
			}
			,yes: function(index, layero){
				var iframeWindow = window['layui-layer-iframe'+ index]
				,submitID = 'LAY-cityprice-front-submit'
				,submit = layero.find('iframe').contents().find('#'+ submitID);
				iframeWindow.updateName();
				iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
				var field = data.field; //获取提交的字段
				//提交 Ajax后台 
				var url=$WEB_ROOT_PATH+"/dhccApi/druginteraction/drugInteraction/saveOrUpdate";
				$.post(url,field,function(result){
					var inFlag= result.inFlag; 
					if(inFlag==0){
						layer.msg('保存成功!');
						//后台成功后，静态更新表格中的数据
						layer.close(index); //关闭弹层
						layui.table.reload('drugTable', {
							where: {"drugInteraction.drugName": ''  }
						});
						/*layui.table.reload('interactionDrugNameTable', {
							where: {"drugInteraction.drugName": field['drugInteraction.drugName']  }
						});*/
					}else if(inFlag==1){
						layer.msg('已经存在该条记录!');
						return false;
					}
				});
			});
			submit.trigger('click');
			}
			}); 
	});
	//主表行工具监听
	table.on('tool(drugTable)', function(obj){
		var data = obj.data;
		if(obj.event === 'add'){
			//添加记录
			layer.open({
				type: 2
				,title: '添加药物相互作用'
				,content: $WEB_ROOT_PATH+'/druginteraction/drugNameInfo-update'
				,maxmin: true
				,area: ['1400px', '500px']
				,success: function(layero, index){
					var iframeWindow = window['layui-layer-iframe'+ index];
					//向此iframe层方法 传递参数
					//iframeWindow.child1(JSON.stringify(data));/*调用弹出窗口，填充该行数据到修改表单*/
				}
				,yes: function(index, layero){
					var iframeWindow = window['layui-layer-iframe'+ index]
					,submitID = 'LAY-cityprice-front-submit'
					,submit = layero.find('iframe').contents().find('#'+ submitID);
					iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
					var field = data.field; //获取提交的字段
					//提交 Ajax后台 
					var url=$WEB_ROOT_PATH+"/dhccApi/druginteraction/drugInteraction/saveOrUpdate";
					$.post(url,field,function(result){
						var inFlag= result.inFlag; 
						if(inFlag==0){
							layer.msg('保存成功!');
							//后台成功后，静态更新表格中的数据
							layer.close(index); //关闭弹层
							/*layui.table.reload('drugTable', {
								where: {"drugInteraction.drugName": field['drugInteraction.drugName']  }
							});*/
							layui.table.reload('interactionDrugNameTable', {
								where: {"drugInteraction.drugName": field['drugInteraction.drugName']  }
							});
						}else if(inFlag==1){
							layer.msg('已经存在该条记录!');
							return false;
						}
					});
				});
				submit.trigger('click');
				}
				});
		}
		if(obj.event === 'delete'){
			layer.confirm('确定要删除该条数据？', function(index){
				//执行 Ajax 后重载
				var url=$WEB_ROOT_PATH+"/dhccApi/druginteraction/drugInteraction/delete";
				$.post(url,{'drugInteraction.id':data.id,'drugInteraction.drugName':data.drugName},function(result){
					table.reload('drugTable');
					table.reload('interactionDrugNameTable');
					layer.msg('删除成功！');
				});
			});

		}else if(obj.event === 'update'){
			//修改
			layer.open({
				type: 2
				,title: '编辑药物相互作用'
					,content: $WEB_ROOT_PATH+'/druginteraction/drugNameInfo-update'
					,maxmin: true
					,area: ['1400px', '500px']
			,btn: ['确定', '取消']
			,success: function(layero, index){
				var iframeWindow = window['layui-layer-iframe'+ index];
				//向此iframe层方法 传递参数
				iframeWindow.child(JSON.stringify(data));/*调用弹出窗口，填充该行数据到修改表单*/
			}
			,yes: function(index, layero){
				var iframeWindow = window['layui-layer-iframe'+ index]
				,submitID = 'LAY-cityprice-front-submit'
				,submit = layero.find('iframe').contents().find('#'+ submitID);
				iframeWindow.updateName();
				//submit.trigger('click');
				//layer.close(index); //关闭弹层
				//监听提交
				iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
					var field = data.field; //获取提交的字段
					console.log(field);
					//提交 Ajax后台 
					var url=$WEB_ROOT_PATH+"/dhccApi/druginteraction/drugInteraction/saveOrUpdate";
					$.post(url,field,function(result){
						var inFlag= result.inFlag; 
						if(inFlag==0){

							layer.msg('修改成功!');
							//后台成功后，静态更新表格中的数据
							layui.table.reload('drugTable', {
								where: {"drugInteraction.drugName": '' }
							});
							/*layui.table.reload('interactionDrugNameTable', {
								where: {"drugInteraction.drugName": field['drugInteraction.drugName']  }
							});*/
							layer.close(index); //关闭弹层
						}else{
							layer.msg('已经存在该条记录'+inFlag);
							return false;
						}
					});
				});  
				submit.trigger('click');
			}
			}); 
		}else if(obj.event === 'show'){
			//console.log(data);
			//var d=JSON.stringify(data)
			window.parent.parent.drugInteraction(data);
		}
	});	
	
	//添加事件
	var active = {
			add1: function(){

			//添加记录
			layer.open({
				type: 2
				,title: '添加药品通用名'
				,content: $WEB_ROOT_PATH+'/druginteraction/drugNameInfo'
				,maxmin: true
				,area: ['500px', '200px']
				,btn: ['确定', '取消']
				,success: function(layero, index){
					var iframeWindow = window['layui-layer-iframe'+ index];
				}
				,yes: function(index, layero){
					var iframeWindow = window['layui-layer-iframe'+ index]
					,submitID = 'LAY-cityprice-front-submit'
					,submit = layero.find('iframe').contents().find('#'+ submitID);
					iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
					var field = data.field; //获取提交的字段
					//提交 Ajax后台 
					var url=$WEB_ROOT_PATH+"/dhccApi/druginteraction/drugInteraction/saveDrugName";
					$.post(url,field,function(result){
						var inFlag= result.inFlag; 
						if(inFlag==0){
							layer.msg('保存成功!');
							//后台成功后，静态更新表格中的数据
							layer.close(index); //关闭弹层
							layui.table.reload('drugTable', {
								where: {"drugInteraction.drugName": field['drugInteraction.drugName']  }
							});
							layui.table.reload('interactionDrugNameTable', {
								where: {"drugInteraction.drugName": field['drugInteraction.drugName']  }
							});
						}else if(inFlag==1){
							layer.msg('已经存在该条记录!');
							return false;
						}
					});
				});
				submit.trigger('click');
				}
				});
			}
	};
	
	table.on('row(drugTable)', function(obj){
		  layui.table.reload('interactionDrugNameTable', {
				where: {"drugInteraction.drugName": obj.data.drugName  }
		  });
	});
		 
	
	
	//监听行点击
	table.on('tool(interactionDrugNameTable)', function(obj){
		var data = obj.data;
		if(obj.event === 'delete'){
			layer.confirm('确定要删除该条数据？', function(index){
				//执行 Ajax 后重载
				var url=$WEB_ROOT_PATH+"/dhccApi/druginteraction/drugInteraction/delete";
				$.post(url,{'drugInteraction.id':data.id},function(result){
					table.reload('drugTable');
					table.reload('interactionDrugNameTable');
					layer.msg('删除成功！');
				});
			});

		}else if(obj.event === 'update'){
			//修改
			layer.open({
				type: 2
				,title: '修改药品相互作用'
					,content: $WEB_ROOT_PATH+'/druginteraction/drugInteractionInfo'
					,maxmin: true
					,area: ['750px', '320px']
			,btn: ['确定', '取消']
			,success: function(layero, index){
				var iframeWindow = window['layui-layer-iframe'+ index];
				//向此iframe层方法 传递参数
				iframeWindow.child(JSON.stringify(data));/*调用弹出窗口，填充该行数据到修改表单*/
			}
			,yes: function(index, layero){
				var iframeWindow = window['layui-layer-iframe'+ index]
				,submitID = 'LAY-cityprice-front-submit'
					,submit = layero.find('iframe').contents().find('#'+ submitID);

				//监听提交
				iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
					var field = data.field; //获取提交的字段
					//提交 Ajax后台 
					var url=$WEB_ROOT_PATH+"/dhccApi/druginteraction/drugInteraction/saveMine";
					$.post(url,field,function(result){
						var inFlag= result.inFlag; 
						if(inFlag==0){
							layer.msg('修改成功!');
							//后台成功后，静态更新表格中的数据
							layui.table.reload('drugTable', {
								where: {"drugInteraction.drugName": field['drugInteraction.drugName']  }
							});
							layui.table.reload('interactionDrugNameTable', {
								where: {"drugInteraction.drugName": field['drugInteraction.drugName']  }
							});
							layer.close(index); //关闭弹层
						}else{
							layer.msg('已经存在该条记录!');
							return false;
						}
					});
				});  
				submit.trigger('click');
			}
			}); 
		}
	});

	//按钮事件绑定底层方法-勿动
	$('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
		var type = $(this).data('type');
		active[type] ? active[type].call(this) : '';
	});
	//按钮事件绑定底层方法-勿动
	/*$('#add').on('click', function(){
		alert(1110);
		var type = $(this).data('type');
		active[type] ? active[type].call(this) : '';
	});*/
});
function reflash2($,form,table){
	 table.render({
		 elem: '#interactionDrugNameTable'
		,url: $WEB_ROOT_PATH+'/dhccApi/druginteraction/drugInteraction/listInteractionVo'
		
		,height: 408
	 	,cols: [[
		  {type: 'numbers', title: '序号' }
		 ,{field:'id', width:80,hide:true,title: '编号'}
		 ,{title:'操作', align:'center',toolbar: '#table-useradmin-webuser', width:180,hide:rowOperate(['drugInteraction-interaction-update','drugInteraction-interaction-delete'])}
		 ,{field:'interactionDrugName', width:200,title: '相互作用药品名称'}
		 ,{field:'interactionEffect', width:200,title: '相互作用效果'}
		 ,{field:'clinicalRecommendations', width:180,title: '临床建议'}
		 ,{field:'clinicalEvidence', width:200,title: '临床证据'}
		 ,{field:'evidenceLevel', width:100,title: '证据级别'}
		 ,{field:'reference', width:200,title: '参考文献'}
		 ,{field:'createDate', width:110,title: '创建时间'}
		 ,{field:'createUserName', width:110,title: '创建人'}
		 ]]
	 ,page: true
	 });
	// alert(firstData);
	 layui.table.reload('interactionDrugNameTable', {
		 where:{"drugInteraction.drugName":firstData}
	 });
}