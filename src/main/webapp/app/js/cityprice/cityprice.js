//初始化	
layui.config({
	base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
	index: 'lib/index' //主入口模块
}).use(['index', 'table'], function(){
	var $ = layui.$
	,form = layui.form
	,table = layui.table;
	//加载城市下拉字典
	 $.getJSON($WEB_ROOT_PATH+'/dhccApi/dict/dict/findDict?dictRequestVo.tableName=t_piccbid_dict_city', 
				function(data){
		     		var  dataList= data.dictList;
		     		for(var i=0 ;i<dataList.length;i++){
		     			var mm="<option value='"+dataList[i].value+"'>"+dataList[i].text+"</option>";
		     			$("#city").append(mm); 
		     		}
		     	form.render('select');
	});
	 //var ids=['cityprice-update','cityprice-delete'];
	table.render({
		elem: '#cityPriceTable'
			,url: $WEB_ROOT_PATH+'/dhccApi/cityprice/dictCityPrice/listVo'
			,height: tableHeight
			,where: {  ilegalChild: '1'  }
	,cols: [[
		{type: 'numbers', title: '序号' }
		,{field:'id', width:80,hide:true,title: '编号'}
		,{title:'操作', align:'center',toolbar: '#table-useradmin-webuser', width:180,hide:rowOperate(['cityprice-update1','cityprice-delete1'])}
		/*,{field:'cityName',width:110, title: '城市名称'}*/
		,{field:'itemCode', width:110,title: '项目编码'}
		,{field:'itemName',width:220, title: '项目名称'}
		,{field:'itemContent',width:120, title: '项目内涵'}
		,{field:'exclusionContent', width:200,title: '除外内容'}
		,{field:'chargeUnit',width:120, title: '计价单位'}
		,{field:'iiiaPrice',width:120, title: '三甲医院'}
		,{field:'iiiaLowPrice',width:80, title: '三甲以下医院'}
		,{field:'regularPrice',width:120, title: '原价'}
		,{field:'primaryRegularPrice',width:120, title: '基层原方案'}
		,{field:'primaryPrice',width:140, title: '基层'}
		,{field:'countyPrice',width:140, title: '县'}
		,{field:'cityPrice',width:140, title: '市'}
		,{field:'explanation',width:140, title: '说明'}
		/*,{field:'cityCode',width:110, title: '城市编码'}*/

		]]
	,page: true
	});
	hideButtonStatic();//按钮权限
	//监听搜索
	form.on('submit(LAY-user-front-search)', function(data){
		var field = data.field;
		//执行重载
		layui.table.reload('cityPriceTable', {
			where: field
		});
	});

	//添加事件
	var active = {
			add: function(){

			//添加记录
			layer.open({
				type: 2
				,title: '保存项目价格'
				,content: $WEB_ROOT_PATH+'/cityprice/citypriceinfo'
				,maxmin: true
				,area: ['750px', '500px']
				,btn: ['确定', '取消']
				,success: function(layero, index){
					var iframeWindow = window['layui-layer-iframe'+ index];
					//加载select下拉option
					iframeWindow.loadSelect();
				}
				,yes: function(index, layero){
					var iframeWindow = window['layui-layer-iframe'+ index]
					,submitID = 'LAY-cityprice-front-submit'
					,submit = layero.find('iframe').contents().find('#'+ submitID);
					iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
					var field = data.field; //获取提交的字段
					//提交 Ajax后台 
					var url=$WEB_ROOT_PATH+"/dhccApi/cityprice/dictCityPrice/saveMine";
					$.post(url,field,function(result){
						var inFlag= result.inFlag; 
						if(inFlag==0){
							layer.msg('保存成功!');
							//后台成功后，静态更新表格中的数据
							layer.close(index); //关闭弹层
							table.reload('cityPriceTable'); //数据刷新
						}else if(inFlag==1){
							layer.msg('已经存在该条itemCode+cityCode!');
							return false;
						}
					});
				});
				submit.trigger('click');
				}
				});
			}
	};

	//监听行点击
	table.on('tool(cityPriceTable)', function(obj){
		var data = obj.data;
		if(obj.event === 'delete'){
			layer.confirm('确定要删除该条数据？', function(index){
				//执行 Ajax 后重载
				var url=$WEB_ROOT_PATH+"/dhccApi/cityprice/dictCityPrice/delete";
				$.post(url,{'dictCityPrice.id':data.id},function(result){
					table.reload('cityPriceTable');
					layer.msg('删除成功！');
				});
			});

		}else if(obj.event === 'update'){
			//修改
			layer.open({
				type: 2
				,title: '修改项目价格'
					,content: $WEB_ROOT_PATH+'/cityprice/citypriceinfo'
					,maxmin: true
					,area: ['750px', '500px']
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
					var url=$WEB_ROOT_PATH+"/dhccApi/cityprice/dictCityPrice/saveMine";
					$.post(url,field,function(result){
						var inFlag= result.inFlag; 
						if(inFlag==0){
							layer.msg('修改成功!');
							//后台成功后，静态更新表格中的数据
							table.reload('cityPriceTable'); //数据刷新
							layer.close(index); //关闭弹层
						}else{
							layer.msg('已经存在该条orgCode+cityCode!');
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
});