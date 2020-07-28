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
	

	table.render({
		elem: '#medicalrangeinfo'
		,url: $WEB_ROOT_PATH+'/dhccApi/infoforbid/infoforbid/listVo'
		,height: tableHeight
		,cellMinWidth: 80
		,where: {ilegalChild: '1'}
	    ,cols: [[
		 {type: 'numbers', title: '序号' }
		,{field:'id', width:80,hide:true,title: '编号',align:"center"}
		,{title:'操作', align:'center',toolbar: '#table-useradmin-webuser', width:230	,align:"center",hide:rowOperate(['infoforbid-edit','infoforbid-delete','infoforbid-look'])}
		,{field:'orgCode', width:100,hide:true,title: '机构编号'}
		,{field:'orgName', width:240,title: '医疗机构',align:"center",templet: function(d){
    	    var codex =d.orgName;
        	if(codex==null||codex==""){
        		codex="-";
        	}else if(codex == ""){
 			   codex="-";
 		   }else if(codex == "null"){
 			   codex="-";
 		   }else if(codex == null){
 			   codex="-";
 		   }
            return '<span >'+ codex +'</span>'
		}}
		,{field:'cityName', width:120,title: '所属城市',hide:true,align:'center',templet: function(d){
    	    var codex =d.cityName;
        	if(codex==null||codex==""){
        		codex="-";
        	}
            return '<span >'+ codex +'</span>'
		}}
		,{field:'orgType',width:90, title: '机构类型',align:"center",templet: function(d){
    	    var codex =d.orgType;
        	if(codex==null||codex==""){
        		codex="-";
        	}else if(codex == '1'){
  			   codex='医院'
  		   }else if(codex == '2'){
  			   codex='药店'
  		   }else if(codex == '0'){
  			   codex='门诊'
  		   }else if(codex == ""){
 			   codex="-";
 		   }else if(codex == "null"){
 			   codex="-";
 		   }else if(codex == null){
 			   codex="-";
 		   }
            return '<span >'+ codex +'</span>'
		}}
		,{field:'stopReason',title: '停止原因',align:"center",templet: function(d){
    	    var codex =d.stopReason;
        	if(codex==null||codex==""){
        		codex="-";
        	}else if(codex == ""){
 			   codex="-";
 		   }else if(codex == "null"){
 			   codex="-";
 		   }else if(codex == null){
 			   codex="-";
 		   }
            return '<span >'+ codex +'</span>'
		}}
		,{field:'areaCode',width:90, title: '地区编码',hide:"true",align:"center"}
		,{field:'stopBeginDate',width:150, title: '资格停止时间',align:"center",templet: function(d){
    	    var codex =d.stopBeginDate;
        	if(codex==null||codex==""){
        		codex="-";
        	}else if(codex == ""){
 			   codex="-";
 		   }else if(codex == "null"){
 			   codex="-";
 		   }else if(codex == null){
 			   codex="-";
 		   }
            return '<span >'+ codex +'</span>'
		}}
		,{field:'stopOverDate',width:150, title: '停止资格终止时间',align:"center",templet: function(d){
    	    var codex =d.stopOverDate;
        	if(codex==null||codex==""){
        		codex="-";
        	}else if(codex == ""){
 			   codex="-";
 		   }else if(codex == "null"){
 			   codex="-";
 		   }else if(codex == null){
 			   codex="-";
 		   }
            return '<span >'+ codex +'</span>'
		}}
		/*,{field:'createDate', title: '创建时间'}*/
		/*,{field:'updateDate',width:120, title: '更新时间'}*/
		]]
	,page: true
	,done:function(res, curr, count){    //res 接口返回的信息
		    $("[data-field = 'insuranceType']").children().each(function(){
		        if($(this).text() == '1'){
		            $(this).text("医疗");
		        }else if($(this).text() == '2'){
		             $(this).text("工伤");
		        }else if($(this).text() == '3'){
			         $(this).text("生育");
			    }
		    })
		}
		
	});
	hideButtonStatic();//按钮权限
	//监听搜索
	form.on('submit(LAY-user-front-search)', function(data){
		var field = data.field;
		//执行重载
		layui.table.reload('medicalrangeinfo', {
			where: field
		});
	});

	//添加事件
	var active = {
			add: function(){

			//添加记录
			layer.open({
				type: 2
				,title: '保存医疗机构停止资格信息'
				,content: $WEB_ROOT_PATH+'/infoforbidinfo/infoforbidinfo'
				,maxmin: true
				,area: ['800px', '420px']
				,btn: ['确定', '取消']
				,success: function(layero, index){
					var iframeWindow = window['layui-layer-iframe'+ index];
					//加载select下拉option
					iframeWindow.loadSelect();
				}
				,yes: function(index, layero){
					var iframeWindow = window['layui-layer-iframe'+ index]
					,submitID = 'LAY-cityorg-front-submit'
					,submit = layero.find('iframe').contents().find('#'+ submitID);
					iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
					var field = data.field; //获取提交的字段
					//提交 Ajax后台 
					var url=$WEB_ROOT_PATH+"/dhccApi/infoforbid/infoforbid/saveMine";
					console.log(field);
					$.post(url,field,function(result){
						var inFlag= result.inFlag; 
						if(inFlag==0){
							layer.msg('保存成功!');
							//后台成功后，静态更新表格中的数据
							layer.close(index); //关闭弹层
							table.reload('medicalrangeinfo'); //数据刷新
						}else if(inFlag==1){
							layer.msg('已经存在该条threeDirectoryCode+cityCode!');
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
	table.on('tool(medicalrangeinfo)', function(obj){
		var data = obj.data;
		if(obj.event === 'delete'){
			layer.confirm('确定要删除该条数据？', function(index){
				//执行 Ajax 后重载
				var url=$WEB_ROOT_PATH+"/dhccApi/infoforbid/infoforbid/delete";
				$.post(url,{'infoForbid.id':data.id},function(result){
					table.reload('medicalrangeinfo');
					layer.msg('删除成功！');
				});
			});

		}else if(obj.event === 'update'){
			//修改
			layer.open({
				type: 2
				,title: '修改医疗机构停止资格信息'
					,content: $WEB_ROOT_PATH+'/infoforbidinfo/infoforbidinfo'
					,maxmin: true
					,area: ['800px', '420px']
			,btn: ['确定', '取消']
			,success: function(layero, index){
				var iframeWindow = window['layui-layer-iframe'+ index];
				//向此iframe层方法 传递参数
				iframeWindow.child(JSON.stringify(data));/*调用弹出窗口，填充该行数据到修改表单*/
			}
			,yes: function(index, layero){
				var iframeWindow = window['layui-layer-iframe'+ index]
				,submitID = 'LAY-cityorg-front-submit'
					,submit = layero.find('iframe').contents().find('#'+ submitID);

				//监听提交
				iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
					var field = data.field; //获取提交的字段
					//提交 Ajax后台 
					var url=$WEB_ROOT_PATH+"/dhccApi/infoforbid/infoforbid/saveMine";
					$.post(url,field,function(result){
						var inFlag= result.inFlag; 
						if(inFlag==0){
							layer.msg('修改成功!');
							//后台成功后，静态更新表格中的数据
							table.reload('medicalrangeinfo'); //数据刷新
							layer.close(index); //关闭弹层
						}else{
							layer.msg('已经存在该条threeDirectoryCode+cityCode!');
							return false;
						}
					});
				});  
				submit.trigger('click');
			}
			}); 
		}else if(obj.event === 'view'){
			 //修改
    	    layer.open({
		          type: 2
		          ,title: '查看医疗机构停止资格信息'
		          ,content: $WEB_ROOT_PATH+'/infoforbidinfo/infoforbidinfoview'
		          ,maxmin: true
		          ,area: ['800px', '400px']
		          ,btn: ['关闭']
    	          ,success: function(layero, index){
	        	  var iframeWindow = window['layui-layer-iframe'+ index];
		        	  //向此iframe层方法 传递参数
		        	  iframeWindow.child(JSON.stringify(data));
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