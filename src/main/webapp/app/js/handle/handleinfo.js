//
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;
	    
	    
	    //加载医疗机构下拉字典
      /*  $.getJSON($WEB_ROOT_PATH+'/dhccApi/handle/handle/findCityNameDict',
            function(data){
                for(var i=0 ;i<data.length;i++){
                    var mm="<option value='"+data[i].value+"'>"+data[i].text+"</option>";
                    $("#cityName").append(mm);
                }
                form.render('select');
           });*/
        
        //加载医疗机构下拉字典
       /* $.getJSON($WEB_ROOT_PATH+'/dhccApi/handle/handle/findOrgNameDict',
            function(data){
                for(var i=0 ;i<data.length;i++){
                    var mm="<option value='"+data[i].value+"'>"+data[i].text+"</option>";
                    $("#handdingInsName").append(mm);
                }
                form.render('select');
           });*/
	    
	    table.render({
	    	elem: '#handleTable'
	        ,url: $WEB_ROOT_PATH+'/dhccApi/handle/handle/listVo'
	        ,cellMinWidth: 80
	        ,height:tableHeight+10
	        ,cols: [[
			 {type: 'numbers', width:80, title: '编号'}
   			,{title:'操作', width: 160, align:'center', toolbar: '#table-orgadmin-webuser',hide:rowOperate(['handle-edit','handle-del'])}        
			,{field:'cityName', width: 140,width:120,align:'center', title: '城市名称' }
			,{field:'handdingInsCode', width: 120, align:'center',title: '统筹区编码' }
			,{field:'handdingInsName', align:'center', title: '统筹区名称' }
			,{field:'address', align:'center',title: '地址' }
			,{field:'phone', width:180,align:'center',title: '联系电话' }
			,{field:'regionCode', width: 200, align:'center',title: '行政区域' }
			/*,{field:'createDate', width: 200, align:'center',title: '上传时间' }*/
			/*,{field:'updateDate', align:'center',title: '更新时间' }*/
	            ]]
	            ,page: true
	          });
	    hideButtonStatic();//按钮权限
	    
	    //监听搜索
		form.on('submit(LAY-org-front-search)', function(data){
	    	var field = data.field;
	        //执行重载
	        layui.table.reload('handleTable', {
	            where: field
	        });
	    });
	  
	    //添加事件
	    var active = {
	      add: function(){
	    	//新增方法
	        layer.open({
	          type: 2
	          ,title: '添加经办机构信息'
	          ,content: $WEB_ROOT_PATH+'/handle/handleadd'
	          ,maxmin: true
	          ,area: ['800px', '400px']
	          ,btn: ['确定', '取消']
	          ,yes: function(index, layero){
	            var iframeWindow = window['layui-layer-iframe'+ index]
	            ,submitID = 'LAY-org-front-submit'
	            ,submit = layero.find('iframe').contents().find('#'+ submitID);

	            //监听提交
	            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
	              var field = data.field; //获取提交的字段
	              //提交 Ajax后台 
	              var url=$WEB_ROOT_PATH+"/dhccApi/handle/handle/save";
	              $.post(url,field,function(result){
	            		  layer.msg('添加成功!');
	            		//后台成功后，静态更新表格中的数据
		                  table.reload('handleTable'); //数据刷新
		                  layer.close(index); //关闭弹层
				  });
	            });  
	            submit.trigger('click');
	          }
	        }); 
	      }
	    };
	    //监听行点击
	    table.on('tool(handleTable)', function(obj){
	      var data = obj.data;
	      if(obj.event === 'del'){
	    	//删除
		  layer.confirm('确定删除该经办机构信息？', function(index){
		      //执行 Ajax 后重载
		        var url=$WEB_ROOT_PATH+"/dhccApi/handle/handle/delete";
		        $.post(url,{'handle.id':data.id},function(result){
		        	table.reload('handleTable');
		            layer.msg('已删除');
		    	    });
		        });
	      } else if(obj.event === 'edit'){
	    	//修改方法
		    layer.open({
		          type: 2
		          ,title: '编辑经办机构信息'
		          ,content: $WEB_ROOT_PATH+'/handle/handleedit'
		          ,maxmin: true
		          ,area: ['800px', '400px']
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
		              var url=$WEB_ROOT_PATH+"/dhccApi/handle/handle/edit";
		              $.post(url,field,function(result){
		            		  layer.msg('修改成功!');
			            	  //后台成功后，静态更新表格中的数据
			                  table.reload('handleTable'); //数据刷新
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