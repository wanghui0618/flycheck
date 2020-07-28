//
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;
	    
	    /*$.getJSON($WEB_ROOT_PATH+'/dhccApi/dict/dict/findDict?dictRequestVo.tableName=t_piccbid_dict_city_xingzheng',
				function(data){
			var  dataList= data.dictList;
			for(var i=0 ;i<dataList.length;i++){
			var mm="<option value='"+dataList[i].value+"'>"+dataList[i].text+"</option>";
			$("#cityAdminarea").append(mm); 
		     		}
			form.render('select');
			});*/
	    
	    
	    table.render({
	    	elem: '#codeTable'
	        ,url: $WEB_ROOT_PATH+'/dhccApi/code/code/listVo'
	        ,cellMinWidth: 80
	        ,height: document.documentElement.clientHeight-65
	        ,cols: [[
			 {type: 'numbers', width:40, title: '序号'}
   			,{title:'操作', width: 160,hide:rowOperate(['code-update','code-delete']), align:'center', toolbar: '#table-orgadmin-webuser'}        
			,{field:'businessId',width:250,align:'center', title: '业务编码' }
			,{field:'businessTypeCode',align:'center', title: '业务类别代码' }
			,{field:'businessType', align:'center',title: '业务类别' }
	            ]]
	            ,page: true
	          });
	    hideButtonStatic();//按钮权限
	    //监听搜索
		form.on('submit(LAY-org-front-search)', function(data){
	    	var field = data.field;
	        //执行重载
	    	console.log(field);
	        layui.table.reload('codeTable', {
	            where: field
	        });
	    });
	  
	    //添加事件
	    var active = {
	      add: function(){
	    	//新增方法
	        layer.open({
	          type: 2
	          ,title: '添加业务信息'
	          ,content: $WEB_ROOT_PATH+'/code/codeAdd'
	          ,maxmin: true
	          ,area: ['500px', '350px']
	          ,btn: ['确定', '取消']
	          ,yes: function(index, layero){
	            var iframeWindow = window['layui-layer-iframe'+ index]
	            ,submitID = 'LAY-org-front-submit'
	            ,submit = layero.find('iframe').contents().find('#'+ submitID);

	            //监听提交
	            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
	              var field = data.field; //获取提交的字段
	              //提交 Ajax后台 
	              var url=$WEB_ROOT_PATH+"/dhccApi/code/code/save";
	              $.post(url,field,function(result){
	            	  /*if(result.id=="1"){
	            		  layer.msg('该城市编码已经存在，请勿重复添加！');
	            	  }else if(result.id=="2"){
	            		  layer.msg('该城市名称已经存在，请勿重复添加!');
	            	  }else{*/
	            		  layer.msg('添加成功!');
	            		//后台成功后，静态更新表格中的数据
		                  table.reload('codeTable'); //数据刷新
		                  layer.close(index); //关闭弹层
	            	  /*}*/
				  });
	            });  
	            submit.trigger('click');
	          }
	        }); 
	      }
	    };
	    //监听行点击
	    table.on('tool(codeTable)', function(obj){
	      var data = obj.data;
	      if(obj.event === 'del'){
	    	//删除
		  layer.confirm('确定删除该信息？', function(index){
		      //执行 Ajax 后重载
		        var url=$WEB_ROOT_PATH+"/dhccApi/code/code/delete";
		        $.post(url,{'code.id':data.id},function(result){
		        	table.reload('codeTable');
		            layer.msg('已删除');
		    	    });
		        });
	      } else if(obj.event === 'edit'){
	    	//修改方法
		    layer.open({
		          type: 2
		          ,title: '修改业务信息'
		          ,content: $WEB_ROOT_PATH+'/code/codeUpdate'
		          ,maxmin: true
		          ,area: ['500px', '350px']
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
		              var url=$WEB_ROOT_PATH+"/dhccApi/code/code/edit";
		              $.post(url,field,function(result){
		            	  if(result.id=="1"){
		            		  layer.msg('该城市编码已经存在，请重新填写城市编码！');
		            	  }else if(result.id=="2"){
		            		  layer.msg('该城市名称已经存在，请重新填写城市名称!');
		            	  }else{
		            		  layer.msg('修改成功!');
			            	  //后台成功后，静态更新表格中的数据
			                  table.reload('codeTable'); //数据刷新
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
function rowOperate(ids) {
	var count;
	if(ids){
		for(var i=0;i<ids.length;i++){
			if(!existsButton(ids[i])){
				return false;
			}
		}
		return true;
	}else{
	    return true;
	}
}