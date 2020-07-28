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
	    	elem: '#blackList'
	            ,url: $WEB_ROOT_PATH+'/dhccApi/blackList/blackList/list'
	            ,cellMinWidth: 80
	            ,where: { cityCode: 'DHCC'  }
	            ,height:tableHeight
	            ,cols: [[
	            	   {type: 'numbers',width:40, title: '序号',fixed: 'left' }
	            	  ,{field:'id', title: 'ID', sort: true, hide:true} 
		              ,{title:'操作', toolbar: '#table-useradmin-webuser', width:220,align:'center'}
		              ,{field:'cityName', width:88,title: '所属城市',align:'center',templet: function(d){
	                	    var codex =d.cityName;
		                	if(codex==null||codex==""){
		                		codex="-";
		                	}		                	
		                    return '<span >'+ codex +'</span>'
	                  }}
		              ,{field:'type', title: '类型',width:97,align:'center',templet: function(d){
	                	    var codex =d.type;
		                	if(codex=="1"){
		                		codex="参保人";
		                	}else if(codex=="2"){
		                		codex="医院";
		                	}else if(codex=="3"){
		                		codex="药店";
		                	}else{
		                		codex=codex;
		                	}
		                    return '<span >'+ codex +'</span>'
	                  }}
		              ,{field:'name', title: '姓名/名称',width:303,align:'center'}
		              ,{field:'code',title: '标识号', width:202,align:'center'}     	                          
		              /*,{field:'updateDate',title: '更新时间',width:110,align:'center'}*/
		              ,{field:'scale',title: '严重级别', width:100,align:'center' ,templet: function(d){
	                	    var codex =d.scale;
		                	if(codex=="1"){
		                		codex="非常严重";
		                	    return '<span style="color:#FF3300;font-weight: 600;"; >'+ codex +'</span>'
		                	}else if(codex=="2"){
		                		codex="严重";
		                    	return '<span style="color:#F49200;font-weight: 600;";>'+ codex +'</span>'
		                	}else if(codex=="3"){
		                		codex="轻度";
		                	 	return '<span style="color:#D8C609DE;font-weight: 600;"; >'+ codex +'</span>'
		                	}else{
		                		codex=codex;
		                	}
		                    return '<span >'+ codex +'</span>'
	                  }}
		              ,{field:'result',title: '上黑名单原因',width:216,align:'center'}  
		              ,{field:'createUser',title: '添加人',width:114,align:'center'}
		              ,{field:'createDate', title: '添加时间',width:124,align:'center'}
	            ]]
	            ,page: true
	          });
	    
	    //console.log("开调用按钮权限");
	    hideButtonStatic();//按钮权限
	    
	    //监听搜索
		form.on('submit(LAY-user-front-search)', function(data){
	    	var field = data.field;    	
	        //执行重载
	        layui.table.reload('blackList', {
	            where: field
	        });
	    });
		
		//添加事件
	    var active = {
	      add: function(){
	    	  //参保信息新增
	    	    layer.open({
			          type: 2
			          ,title: '黑名单信息-新增'
			          ,content: $WEB_ROOT_PATH+'/blackList/blackListInfo'
			          ,maxmin: true
			          ,area: ['700px', '410px']
			          ,btn: ['确定', '取消']  
	    	          ,success: function(layero, index){
			        	  var iframeWindow = window['layui-layer-iframe'+ index];
			        	  //向此iframe层方法 传递参数
			        	  iframeWindow.loadSelect();
			              }
	                  ,yes: function(index, layero){
			            var iframeWindow = window['layui-layer-iframe'+ index]
			            ,submitID = 'layuiadmin-btn-useradmin'
			            ,submit = layero.find('iframe').contents().find('#'+ submitID);

			            //监听提交
			            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
			              var field = data.field; //获取提交的字段
                        //提交 Ajax后台 
			              var url=$WEB_ROOT_PATH+"/dhccApi/blackList/blackList/save";
			              $.post(url,field,function(result){	
			            	  var inFlag= result.inFlag; 
			            	  if(inFlag==2){
				                  layer.msg('该标识号已存在!');
			            		  return false;
			            	 }else if(inFlag==1){
			            		 layer.msg('新增成功!');
				            	  //后台成功后，静态更新表格中的数据
				                  table.reload('blackList'); //数据刷新
				                  layer.close(index); //关闭弹层
			            	 }else if(inFlag==3){
			            		 layer.msg('该参保号已存在!');
			            		 return false;
			            	 }else{
			            		 layer.msg('网络异常');
				            	  //后台成功后，静态更新表格中的数据
				                  table.reload('blackList'); //数据刷新
				                  layer.close(index); //关闭弹层
			            	 }
			            	  //关闭弹层
						  });
			              
			             
			            });  
			            submit.trigger('click');
			          }
			        }); 
  
	      }
	    };
		

	  
	    //监听行点击
	    table.on('tool(blackList)', function(obj){
	      var data = obj.data;
	      if(obj.event === 'xiugai'){
	    	  //修改
	    	    layer.open({
			          type: 2
			          ,title: '黑名单信息-修改'
			          ,content: $WEB_ROOT_PATH+'/blackList/blackListInfo'
			          ,maxmin: true
			          ,area: ['700px', '410px']
			          ,btn: ['确定', '取消']
	    	          ,success: function(layero, index){
		        	  var iframeWindow = window['layui-layer-iframe'+ index];
		        	  //向此iframe层方法 传递参数
		        	  iframeWindow.child(JSON.stringify(data));
		              }
			          ,yes: function(index, layero){
			            var iframeWindow = window['layui-layer-iframe'+ index]
			            ,submitID = 'layuiadmin-btn-useradmin'
			            ,submit = layero.find('iframe').contents().find('#'+ submitID);

			            //监听提交
			            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
			              var field = data.field; //获取提交的字段
			              
			             
			             
			              
			              //提交 Ajax后台 
			              var url=$WEB_ROOT_PATH+"/dhccApi/blackList/blackList/save";
			              $.post(url,field,function(result){
			            	  var inFlag= result.operateSuccess; 
			            	  if(inFlag==true){
			            		  layer.msg('修改成功!');
			            		  //后台成功后，静态更新表格中的数据
			            		  table.reload('blackList'); //数据刷新
			            		  layer.close(index); //关闭弹层

			            	  }else{
			            		  layer.msg('修改失败!');
			            		  //后台成功后，静态更新表格中的数据
			            		  table.reload('blackList'); //数据刷新
			            		  layer.close(index); //关闭弹层
			            	  }
			              }
			              );

			            });  
			            submit.trigger('click');
			          }
			        }); 
	   
	      }else if(obj.event === 'view'){
	    	  //修改
	    	    layer.open({
			          type: 2
			          ,title: '黑名单信息-查看'
			          ,content: $WEB_ROOT_PATH+'/blackList/blackListInfo'
			          ,maxmin: true
			          ,area: ['700px', '410px']
			          ,btn: ['关闭']
	    	          ,success: function(layero, index){
		        	  var iframeWindow = window['layui-layer-iframe'+ index];
			        	  //向此iframe层方法 传递参数
			        	  iframeWindow.child(JSON.stringify(data));
		              }
			        }); 
	   
	      } else if(obj.event === 'shanchu'){
	    	//删除
	    	  var id=data.id;
	    	  var name=data.name;
		        layer.confirm('是否确定删除<span style="color: red;">'+name+'</span>的信息', function(index){
		        	//执行 Ajax 后重载
		            var url=$WEB_ROOT_PATH+"/dhccApi/blackList/blackList/delete";
		            $.post(url,{"blackList.id":id},function(result){
		        	    table.reload('blackList');
		                layer.msg('已删除');
		    	    });
		        });
	      }
	    });
	    
	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });