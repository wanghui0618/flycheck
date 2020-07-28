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
	    	elem: '#pharmacyTable'
	        ,url: $WEB_ROOT_PATH+'/dhccApi/pharmacy/pharmacy/list'
	        ,cellMinWidth: 110
	        ,height: document.documentElement.clientHeight-65
	        ,cols: [[
			 {type: 'numbers', width:40, title: '编号'}
   			,{title:'操作', width: 150, align:'center', toolbar: '#table-orgadmin-webuser',hide:rowOperate(['pharmacy-edit','pharmacy-del'])}        
			,{field:'drugName',width:180,align:'center', title: '药品名称' }
			,{field:'drugForm', width:110,align:'center',title: '药品剂型' }
			,{field:'areaName',width:125, align:'center',title: '地区或机构' }
			,{field:'dispatchTime',width:120, align:'center',title: '发文时间' }
			,{field:'supervice', align:'center',title: '监管类别' }
			,{field:'policyDocument',width:600, align:'center',title: '政策文件' }
			/*,{field:'createUser', width:120,align:'center',title: '创始人' }
			,{field:'createDate',width:150, align:'center',title: '创建时间' }*/
	        ]]
	        ,page: true
	        });
	    hideButtonStatic();//按钮权限
	    
	    //监听搜索
		form.on('submit(LAY-pharmacy-front-search)', function(data){
	    	var field = data.field;
	        //执行重载
	        layui.table.reload('pharmacyTable', {
	            where: field
	        });
	    });
	  
	    //添加事件
	    var active = {
	      add: function(){
	    	//新增方法
	        layer.open({
	          type: 2
	          ,title: '添加用药信息'
	          ,content: $WEB_ROOT_PATH+'/pharmacy/pharmacyadd'
	          ,maxmin: true
	          ,area: ['850px', '500px']
	          ,btn: ['确定', '取消']
	          ,yes: function(index, layero){
	            var iframeWindow = window['layui-layer-iframe'+ index]
	            ,submitID = 'LAY-org-front-submit'
	            ,submit = layero.find('iframe').contents().find('#'+ submitID);

	            //监听提交
	            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
	              var field = data.field; //获取提交的字段
	              //提交 Ajax后台 
	              var url=$WEB_ROOT_PATH+"/dhccApi/pharmacy/pharmacy/save";
	              $.post(url,field,function(result){
	            		  layer.msg('添加成功!');
	            		//后台成功后，静态更新表格中的数据
		                  table.reload('pharmacyTable'); //数据刷新
		                  layer.close(index); //关闭弹层
				  });
	            });  
	            submit.trigger('click');
	          }
	        }); 
	      }
	    };
	    //监听行点击
	    table.on('tool(pharmacyTable)', function(obj){
	      var data = obj.data;
	      if(obj.event === 'del'){
	    	//删除
		  layer.confirm('确定删除该用药信息吗？', function(index){
		      //执行 Ajax 后重载
		        var url=$WEB_ROOT_PATH+"/dhccApi/pharmacy/pharmacy/delete";
		        $.post(url,{'pharmacy.id':data.id},function(result){
		        	table.reload('pharmacyTable');
		            layer.msg('已删除');
		    	    });
		        });
	      } else if(obj.event === 'edit'){
	    	//修改方法
		    layer.open({
		          type: 2
		          ,title: '编辑用药信息'
		          ,content: $WEB_ROOT_PATH+'/pharmacy/pharmacyedit'
		          ,maxmin: true
		          ,area: ['850px', '500px']
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
		              var url=$WEB_ROOT_PATH+"/dhccApi/pharmacy/pharmacy/edit";
		              $.post(url,field,function(result){
		            		  layer.msg('修改成功!');
			            	  //后台成功后，静态更新表格中的数据
			                  table.reload('pharmacyTable'); //数据刷新
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