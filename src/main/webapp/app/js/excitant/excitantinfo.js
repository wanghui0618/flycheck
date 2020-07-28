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
	    	elem: '#excitantTable'
	        ,url: $WEB_ROOT_PATH+'/dhccApi/excitant/excitant/list'
	        ,cellMinWidth: 120
	        ,height: document.documentElement.clientHeight-65
	        ,cols: [[
			 {type: 'numbers', width:60, title: '编号'}
   			,{title:'操作', width: 180, align:'center', toolbar: '#table-orgadmin-webuser',hide:rowOperate(['excitant-edit','excitant-del'])}        
			,{field:'source',width:220,align:'center', title: '来源' }
			,{field:'classify',width:120, align:'center',title: '分类' }
			,{field:'commonName',align:'center', title: '药品通用名' }
			,{field:'core', width:120,align:'center',title: '核心成分' }
			,{field:'englishName', width:210,align:'center',title: '英文名称' }
			,{field:'customsCode', width:210,align:'center',title: '海关编码' }
			/*,{field:'createUser',width:100, align:'center',title: '创始人' }
			,{field:'createDate', width:180,align:'center',title: '创建时间' }*/
	         ]]
	            ,page: true
	          });
	    hideButtonStatic();//按钮权限
	    
	    //监听搜索
		form.on('submit(LAY-excitant-front-search)', function(data){
	    	var field = data.field;
	        //执行重载
	        layui.table.reload('excitantTable', {
	            where: field
	        });
	    });
	  
	    //添加事件
	    var active = {
	      add: function(){
	    	//新增方法
	        layer.open({
	          type: 2
	          ,title: '添加兴奋剂信息'
	          ,content: $WEB_ROOT_PATH+'/excitant/excitantadd'
	          ,maxmin: true
	          ,area: ['800px', '500px']
	          ,btn: ['确定', '取消']
	          ,yes: function(index, layero){
	            var iframeWindow = window['layui-layer-iframe'+ index]
	            ,submitID = 'LAY-org-front-submit'
	            ,submit = layero.find('iframe').contents().find('#'+ submitID);

	            //监听提交
	            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
	              var field = data.field; //获取提交的字段
	              //提交 Ajax后台 
	              var url=$WEB_ROOT_PATH+"/dhccApi/excitant/excitant/save";
	              $.post(url,field,function(result){
	            		  layer.msg('添加成功!');
	            		//后台成功后，静态更新表格中的数据
		                  table.reload('excitantTable'); //数据刷新
		                  layer.close(index); //关闭弹层
				  });
	            });  
	            submit.trigger('click');
	          }
	        }); 
	      }
	    };
	    //监听行点击
	    table.on('tool(excitantTable)', function(obj){
	      var data = obj.data;
	      if(obj.event === 'del'){
	    	//删除
		  layer.confirm('确定删除该兴奋剂信息？', function(index){
		      //执行 Ajax 后重载
		        var url=$WEB_ROOT_PATH+"/dhccApi/excitant/excitant/delete";
		        $.post(url,{'excitant.id':data.id},function(result){
		        	table.reload('excitantTable');
		            layer.msg('已删除');
		    	    });
		        });
	      } else if(obj.event === 'edit'){
	    	//修改方法
		    layer.open({
		          type: 2
		          ,title: '编辑兴奋剂信息'
		          ,content: $WEB_ROOT_PATH+'/excitant/excitantedit'
		          ,maxmin: true
		          ,area: ['800px', '500px']
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
		              var url=$WEB_ROOT_PATH+"/dhccApi/excitant/excitant/edit";
		              $.post(url,field,function(result){
		            		  layer.msg('修改成功!');
			            	  //后台成功后，静态更新表格中的数据
			                  table.reload('excitantTable'); //数据刷新
			                  layer.close(index); //关闭弹层
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