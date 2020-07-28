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
	    	elem: '#indicationTreatTable'
	        ,url: $WEB_ROOT_PATH+'/dhccApi/indicationTreat/indicationTreat/listVo'
	        ,cellMinWidth: 80
	        ,height: tableHeight
	        ,cols: [[
			 {type: 'numbers', width:40, title: '编号'}
   			,{title:'操作', width: 160, align:'center', toolbar: '#table-orgadmin-webuser',hide:rowOperate(['indicationTreat-update','indicationTreat-delete'])}        
			,{field:'cityCode',width:140,align:'center', title: '城市编码' }
			,{field:'typeNo', width:150,align:'center',title: '规则编码' }
			,{field:'typeName',width:150,align:'center', title: '规则名称' }
			,{field:'itemCode',width:150, align:'center',title: '目录编码' }
			,{field:'itemName', width:180,align:'center',title: '目录名称' }
			,{field:'logicType',width:250, align:'center',title: '判断逻辑类型' }
			,{field:'logicTypeDetail', width:250,align:'center',title: '判断逻辑类型明细' }
			,{field:'logic',width:250, align:'center',title: '判断逻辑' }
			,{field:'logicSql1',width:250, align:'center',title: '逻辑sql1' }
			,{field:'logicSql2',width:250, align:'center',title: '逻辑sql2' }
			,{field:'comments', width:250,align:'center',title: '备注' }
			//,{field:'relevanceItem', width:250,align:'center',title: '判断逻辑的关联项目编码'}
	            ]]
	            ,page: true
	          });
	    hideButtonStatic();//按钮权限
	    
	    //监听搜索
		form.on('submit(LAY-org-front-search)', function(data){
	    	var field = data.field;
	        //执行重载
	        layui.table.reload('indicationTreatTable', {
	            where: field
	        });
	    });
	  
	    //添加事件
	    var active = {
	      add: function(){
	    	//新增方法
	        layer.open({
	          type: 2
	          ,title: '添加超限额收费信息'
	          ,content: $WEB_ROOT_PATH+'/indicationTreat/indicationTreatAdd'
	          ,maxmin: true
	          ,area: ['880px', '500px']
	          ,btn: ['确定', '取消']
	          ,yes: function(index, layero){
	            var iframeWindow = window['layui-layer-iframe'+ index]
	            ,submitID = 'LAY-org-front-submit'
	            ,submit = layero.find('iframe').contents().find('#'+ submitID);

	            //监听提交
	            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
	              var field = data.field; //获取提交的字段
	              //提交 Ajax后台 
	              console.log(field);
	              var url=$WEB_ROOT_PATH+"/dhccApi/indicationTreat/indicationTreat/save";
	              $.post(url,field,function(result){
	            		  layer.msg('添加成功!');
	            		//后台成功后，静态更新表格中的数据
		                  table.reload('indicationTreatTable'); //数据刷新
		                  layer.close(index); //关闭弹层
				  });
	            });  
	            submit.trigger('click');
	          }
	        }); 
	      }
	    };
	    //监听行点击
	    table.on('tool(indicationTreatTable)', function(obj){
	      var data = obj.data;
	      if(obj.event === 'del'){
	    	//删除
		  layer.confirm('确定删除该信息吗？', function(index){
		      //执行 Ajax 后重载
		        var url=$WEB_ROOT_PATH+"/dhccApi/indicationTreat/indicationTreat/delete";
		        $.post(url,{'indicationTreat.id':data.id},function(result){
		        	table.reload('indicationTreatTable');
		            layer.msg('删除成功');
		    	    });
		        });
	      } else if(obj.event === 'edit'){
	    	//修改方法
		    layer.open({
		          type: 2
		          ,title: '编辑超限额收费信息'
		          ,content: $WEB_ROOT_PATH+'/indicationTreat/indicationTreatAdd'
		          ,maxmin: true
		          ,area: ['880px', '500px']
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
		              var url=$WEB_ROOT_PATH+"/dhccApi/indicationTreat/indicationTreat/save";
		              $.post(url,field,function(result){
		            		  layer.msg('修改成功!');
			            	  //后台成功后，静态更新表格中的数据
			                  table.reload('indicationTreatTable'); //数据刷新
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