//
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;
	    
	    $.getJSON($WEB_ROOT_PATH+'/dhccApi/dict/dict/findDict?dictRequestVo.tableName=t_piccbid_dict_city_xingzheng',
				function(data){
			var  dataList= data.dictList;
			for(var i=0 ;i<dataList.length;i++){
			var mm="<option value='"+dataList[i].value+"'>"+dataList[i].text+"</option>";
			$("#cityAdminarea").append(mm); 
		     		}
			form.render('select');
			});
	    
	    
	    table.render({
	    	elem: '#cityTable'
	        ,url: $WEB_ROOT_PATH+'/dhccApi/city/city/listVo'
	        ,cellMinWidth: 80
	        ,height: tableHeight
	        ,cols: [[
			 {type: 'numbers', width:40, title: '编号'}
   			,{title:'操作', width: 160, align:'center', toolbar: '#table-orgadmin-webuser',hide:rowOperate(['city-update','city-delete'])}        
			,{field:'cityCode',width:250,align:'center', title: '城市编码' }
			,{field:'cityName', align:'center',title: '城市名称' }
			,{field:'cityAdminarea',align:'center', title: '行政区域' }
			,{field:'remark', align:'center',title: '备注' }
		
	            ]]
	            ,page: true
	          });
	    hideButtonStatic();//按钮权限
	    
	    //监听搜索
		form.on('submit(LAY-org-front-search)', function(data){
	    	var field = data.field;
	        //执行重载
	        layui.table.reload('cityTable', {
	            where: field
	        });
	    });
	  
	    //添加事件
	    var active = {
	      add: function(){
	    	//新增方法
	        layer.open({
	          type: 2
	          ,title: '添加城市信息'
	          ,content: $WEB_ROOT_PATH+'/city/cityadd'
	          ,maxmin: true
	          ,area: ['800px', '300px']
	          ,btn: ['确定', '取消']
	          ,yes: function(index, layero){
	            var iframeWindow = window['layui-layer-iframe'+ index]
	            ,submitID = 'LAY-org-front-submit'
	            ,submit = layero.find('iframe').contents().find('#'+ submitID);

	            //监听提交
	            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
	              var field = data.field; //获取提交的字段
	              //提交 Ajax后台 
	              var url=$WEB_ROOT_PATH+"/dhccApi/city/city/save";
	              $.post(url,field,function(result){
	            	  if(result.id=="1"){
	            		  layer.msg('该城市编码已经存在，请勿重复添加！');
	            	  }else if(result.id=="2"){
	            		  layer.msg('该城市名称已经存在，请勿重复添加!');
	            	  }else{
	            		  layer.msg('添加成功!');
	            		//后台成功后，静态更新表格中的数据
		                  table.reload('cityTable'); //数据刷新
		                  layer.close(index); //关闭弹层
	            	  }
				  });
	            });  
	            submit.trigger('click');
	          }
	        }); 
	      }
	    };
	    //监听行点击
	    table.on('tool(cityTable)', function(obj){
	      var data = obj.data;
	      if(obj.event === 'del'){
	    	//删除
		  layer.confirm('确定删除该城市？', function(index){
		      //执行 Ajax 后重载
		        var url=$WEB_ROOT_PATH+"/dhccApi/city/city/delete";
		        $.post(url,{'city.id':data.id},function(result){
		        	table.reload('cityTable');
		            layer.msg('已删除');
		    	    });
		        });
	      } else if(obj.event === 'edit'){
	    	//修改方法
		    layer.open({
		          type: 2
		          ,title: '编辑城市信息'
		          ,content: $WEB_ROOT_PATH+'/city/cityedit'
		          ,maxmin: true
		          ,area: ['800px', '300px']
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
		              var url=$WEB_ROOT_PATH+"/dhccApi/city/city/edit";
		              $.post(url,field,function(result){
		            	  if(result.id=="1"){
		            		  layer.msg('该城市编码已经存在，请重新填写城市编码！');
		            	  }else if(result.id=="2"){
		            		  layer.msg('该城市名称已经存在，请重新填写城市名称!');
		            	  }else{
		            		  layer.msg('修改成功!');
			            	  //后台成功后，静态更新表格中的数据
			                  table.reload('cityTable'); //数据刷新
			                  layer.close(index); //关闭弹层
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