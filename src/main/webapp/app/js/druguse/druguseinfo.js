//
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;
	    
	    
	    table.render({
	    	elem: '#druguseTable'
	        ,url: $WEB_ROOT_PATH+'/dhccApi/druguse/druguse/list'
	        ,cellMinWidth: 80
	        ,height: tableHeight
	        ,cols: [[
			 {type: 'numbers', width:40, title: '编号'}
   			,{title:'操作',  align:'center', toolbar: '#table-orgadmin-webuser',hide:rowOperate(['druguse-view','druguse-edit','druguse-del'])}        
			,{field:'drugbig',align:'center', title: '药品大类' }
			,{field:'drugsmall', align:'center',title: '药品小类' }
			,{field:'drugName',align:'center', title: '药品名称' }
			,{field:'englishname',align:'center', title: '英文名称' }
			/*,{field:'adaptation', width:500,align:'center',title: '适应症' }
			,{field:'usage',width:500, align:'center',title: '用法用量' }
			,{field:'pharmacology', width:800,align:'center',title: '药理学' }
			,{field:'pharmacokinetics', width:800,align:'center',title: '药动学' }
			,{field:'untowardEffect', width:500,align:'center',title: '不良反应' }
			,{field:'createUser', width:150,align:'center',title: '创始人' }
			,{field:'createDate', width:180,align:'center',title: '创建时间' }*/
	            ]]
	            ,page: true
	          });
	    hideButtonStatic();//按钮权限
	    
	    //监听搜索
		form.on('submit(LAY-druguse-front-search)', function(data){
	    	var field = data.field;
	        //执行重载
	        layui.table.reload('druguseTable', {
	            where: field
	        });
	    });
	  
	    //添加事件
	    var active = {
	      add: function(){
	    	//新增方法
	        layer.open({
	          type: 2
	          ,title: '添加合理用药'
	          ,content: $WEB_ROOT_PATH+'/druguse/druguseadd'
	          ,maxmin: true
	          ,area: ['750px', '500px']
	          ,btn: ['确定', '取消']
	          ,yes: function(index, layero){
	            var iframeWindow = window['layui-layer-iframe'+ index]
	            ,submitID = 'LAY-org-front-submit'
	            ,submit = layero.find('iframe').contents().find('#'+ submitID);

	            //监听提交
	            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
	              var field = data.field; //获取提交的字段
	              //提交 Ajax后台 
	              var url=$WEB_ROOT_PATH+"/dhccApi/druguse/druguse/save";
	              $.post(url,field,function(result){
	            		  layer.msg('添加成功!');
	            		//后台成功后，静态更新表格中的数据
		                  table.reload('druguseTable'); //数据刷新
		                  layer.close(index); //关闭弹层
				  });
	            });  
	            submit.trigger('click');
	          }
	        }); 
	      }
	    };
	    //监听行点击
	    table.on('tool(druguseTable)', function(obj){
	      var data = obj.data;
	      if(obj.event === 'del'){
	    	//删除
		  layer.confirm('确定删除该项？', function(index){
		      //执行 Ajax 后重载
		        var url=$WEB_ROOT_PATH+"/dhccApi/druguse/druguse/delete";
		        $.post(url,{'druguse.id':data.id},function(result){
		        	table.reload('druguseTable');
		            layer.msg('已删除');
		    	    });
		        });
	      } else if(obj.event === 'edit'){
	    	//修改方法
		    layer.open({
		          type: 2
		          ,title: '编辑合理用药'
		          ,content: $WEB_ROOT_PATH+'/druguse/druguseedit'
		          ,maxmin: true
		          ,area: ['750px', '500px']
		          ,btn: ['确定', '取消']
		          ,success: function(layero, index){
		        	  var iframeWindow = window['layui-layer-iframe'+ index]
		        	  //向此iframe层方法 传递参数
		        	  iframeWindow.child(JSON.stringify(data));
		        	  
		          }
		          ,yes: function(index, layero){
		            var iframeWindow = window['layui-layer-iframe'+ index]
		            ,submitID = 'LAY-org-front-submit'
		            ,submit = layero.find('iframe').contents().find('#'+ submitID);
	
		            //监听提交
		            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
		              var field = data.field; //获取提交的字段
		              //提交 Ajax后台 
		              var url=$WEB_ROOT_PATH+"/dhccApi/druguse/druguse/edit";
		              $.post(url,field,function(result){
		            		  layer.msg('修改成功!');
			            	  //后台成功后，静态更新表格中的数据
			                  table.reload('druguseTable'); //数据刷新
			                  layer.close(index); //关闭弹层
					  });
		            });  
		            submit.trigger('click');
		          }
		        }); 
	      }else if (obj.event === 'view') {
	    	  window.parent.parent.druguseView(data);
		    	//修改方法
			    /*layer.open({
			          type: 2
			          ,title: '合理用药信息-详情'
			          ,content: $WEB_ROOT_PATH+'/druguse/druguseview'
			          ,maxmin: true
			          ,area: ['750px', '500px']
			          ,btn: ['确定', '取消']
			          ,success: function(layero, index){
			        	  var iframeWindow = window['layui-layer-iframe'+ index]
			        	  //向此iframe层方法 传递参数
			        	  iframeWindow.child(JSON.stringify(data));
			        	  
			          }
			          ,yes: function(index, layero){
			            var iframeWindow = window['layui-layer-iframe'+ index]
			            ,submitID = 'LAY-org-front-submit'
			            ,submit = layero.find('iframe').contents().find('#'+ submitID);
		
			            //监听提交
			            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
			              var field = data.field; //获取提交的字段
			              //提交 Ajax后台 
			              var url=$WEB_ROOT_PATH+"/dhccApi/druguse/druguse/edit";
			              $.post(url,field,function(result){
			            		  layer.msg('修改成功!');
				            	  //后台成功后，静态更新表格中的数据
				                  table.reload('druguseTable'); //数据刷新
				                  layer.close(index); //关闭弹层
						  });
			            });  
			            submit.trigger('click');
			          }
			        }); */
	        }
	    });
	    
	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });