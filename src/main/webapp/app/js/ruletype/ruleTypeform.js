var index;
//初始化	
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;
	    table.render({
	    	elem: '#dictTable'
	            ,url: $WEB_ROOT_PATH+'/dhccApi/dictmaintain/dictmaintain/listVo1?idd='+adc
	            ,cellMinWidth: 80
	             ,height: 415
	            ,where: {  ilegalChild: '1'  }
	            ,cols: [[
	            	   {type: 'numbers', title: '序号' }
	            	   ,{field:'left', title:'操作', toolbar: '#table-useradmin-webuser', width:150}
		              ,{field:'id', title: 'ID', sort: true, hide:true}
		              ,{field:'name', title: '字典名称'}
		              ,{field:'value', title: '字典值'}
		              ,{field:'type', title:'业务类型'}
		              ,{field:'ruleProperty',title:'规则适用类型'}
		              /*,{field:'type', title: '业务类型代码'}*/
		              ,{field:'cityName', title:'城市名称',sort: true}
		              ,{field:'orgCode', title:'机构编码'}
		              ,{field:'orgName',title:'机构名称'}
		            ]]
	            ,done:function(){
	      			$("[data-field='cityName']").children().each(function(){
	                    if($(this).text()=='001'){
	                        $(this).text("北京市")
	                    }else if($(this).text()=='002'){
	                        $(this).text("成都市")
	                    }else if($(this).text()=='003'){
	                    	$(this).text("广州市")
	                    }else if($(this).text()=='007'){
	                    	$(this).text("西安市")
	                    }else if($(this).text()=='DHCC'){
	                    	$(this).text("DHCC")
	                    }
	                });
	      		  }
		    		,page: true
		          });
	    
	    //监听搜索
		form.on('submit(LAY-user-front-search)', function(data){
	    	var field = data.field;
	    	
	        //执行重载
	        layui.table.reload('dictTable', {
	            where: field
	        });
	    });
	  
	   //添加事件
	    var active = {
	    		c: function(){
	    		    	//新增方法
	    		        layer.open({
	    		          type: 2
	    		          ,title: '添加规则'
	    		          ,content: $WEB_ROOT_PATH+'/dictmaintain/dictmaintainform'
	    		          ,maxmin: true
	    		          ,area: ['800px', '350px']
	    		          ,btn: ['确定', '取消']
	    		        ,success: function(layero, index){
    						var iframeWindow = window['layui-layer-iframe'+ index];
    						//加载select下拉option
    						iframeWindow.loadSelect();
    					}
	    		          ,yes: function(index, layero){
	    		            var iframeWindow = window['layui-layer-iframe'+ index]
	    		            ,submitID = 'LAY-user-front-submit'
	    		            ,submit = layero.find('iframe').contents().find('#'+ submitID);

	    		            //监听提交
	    		            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
	    		              var field = data.field; //获取提交的字段
	       
	    	  //提交 Ajax后台 
              var url=$WEB_ROOT_PATH+"/dhccApi/dictmaintain/dictmaintain/saveFromRule";
              $.post(url,field,function(result){
            	  var inFlag=result.inFlag;
            	  if(inFlag==0){
            		  layer.msg('添加成功!');
            		  //后台成功后，静态更新表格中的数据
                      table.reload('dictTable'); //数据刷新
                      layer.close(index); //关闭弹层
            	  }else if(inFlag==1){
            		  layer.msg('该规则编号已存在！')
            		  return false;
            	  }
			  });
	       });
	    submit.trigger('click');
	     }
	  }); 
	}
	   
};
	    
	    function  getSome(){
	      	var ss= $("input[name='ruleType.applyCity']");
	      	if(ss[0].checked==true){
	      		$("input[name='ruleType.cityName']").show();
	      		
	      	}else{
	      		$("input[name='ruleType.cityName']").hide();
	      	}
	      	   
	    }
	    
	  //监听行点击
	    table.on('tool(dictTable)', function(obj){
	      var data = obj.data;
	      console.log(data);
	      if(obj.event === 'del'){
	    	//删除
	        layer.confirm('确定删除该条记录吗？', function(index){
	        	//执行 Ajax 后重载
	            var url=$WEB_ROOT_PATH+"/dhccApi/dictmaintain/dictmaintain/delete";
	            $.post(url,{"dictmaintain.Id":data.id},function(result){
	        	    table.reload('dictTable');
	                layer.msg('已删除');
	    	    });
	        });
	      } else if(obj.event === 'edit'){
	    	//修改方法
		    layer.open({
		          type: 2
		          ,title: '修改规则'
		          ,content: $WEB_ROOT_PATH+'/dictmaintain/dictmaintainform'
		          ,maxmin: true
		          ,area: ['400px', '300px']
		          ,btn: ['确定', '取消']
		          ,success: function(layero, index){
		        	 
		        	  var iframeWindow = window['layui-layer-iframe'+ index];
		        	  //向此iframe层方法 传递参数
		        	  iframeWindow.child(JSON.stringify(data));
		        	//加载select下拉option
					iframeWindow.loadSelect();
						
		          }
		          ,yes: function(index, layero){
		            var iframeWindow = window['layui-layer-iframe'+ index]
		            ,submitID = 'LAY-user-front-submit'
		            ,submit = layero.find('iframe').contents().find('#'+ submitID);
	
		            //监听提交
		            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
		              var field = data.field; //获取提交的字段
		              alert(field);
		              //提交 Ajax后台 
		              var url=$WEB_ROOT_PATH+"/dhccApi/dictmaintain/dictmaintain/saveFromRule";
		              $.post(url,field,function(result){
		            	  var inFlag= result.inFlag; 
		            	  if(inFlag==0){
		            		  layer.msg('修改成功!');
			            	  //后台成功后，静态更新表格中的数据
			                  table.reload('dictTable'); //数据刷新
			                  layer.close(index); //关闭弹层
		            	  }else if(inFlag==1){
		            		  layer.msg('该规则编号数据已更新！编号无法改变！')
		            		   table.reload('dictTable'); //数据刷新
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
  