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
			$("#cityCode").append(mm); 
		     		}
			form.render();
			});
	    
	    table.render({
	    	elem: '#drugPriceTable'
	        ,url: $WEB_ROOT_PATH+'/dhccApi/drugprice/drugPrice/list'
	        ,cellMinWidth: 80
	        ,height: document.documentElement.clientHeight-65
	        ,cols: [[
			 {type: 'numbers', width:80, title: '编号'}
   			,{title:'操作', width: 160, align:'center', toolbar: '#table-orgadmin-webuser'}   
   			,{field:'cityCode',width:140,align:'center', title: '城市编号' }
			,{field:'drugId',width:200,align:'center', title: '药品编码' }
			,{field:'drugName',width:200,align:'center', title: '药品名称' }
			,{field:'hospLevel', align:'center',title: '医院等级' }
			,{field:'dosageForm',align:'center', title: '剂型' }
			,{field:'maxMoney', align:'center',title: '定价上限金额' }
		
	            ]]
	            ,page: true
	          });
	    
	    //监听搜索
		form.on('submit(LAY-drugprice-front-search)', function(data){
	    	var field = data.field;
	        //执行重载
	        layui.table.reload('drugPriceTable', {
	            where: field
	        });
	    });
	  
	    //添加事件
	    var active = {
	      add: function(){
	    	//新增方法
	        layer.open({
	          type: 2
	          ,title: '添加药品限价信息'
	          ,content: $WEB_ROOT_PATH+'/drugprice/drugPriceadd'
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
	              if(field["drugPrice.cityCode"]=="" || field["drugPrice.cityCode"]==null){
	            	  layer.msg('请选择城市编码！!');
	            	  return false;
	              }
	              //提交 Ajax后台 
	              var url=$WEB_ROOT_PATH+"/dhccApi/drugprice/drugPrice/save";
	              $.post(url,field,function(result){
	            	  if(result.id=="1"){
	            		  layer.msg('该药品编码不存在，请输入正确的药品编码！');
	            	  }else if(result.id=="2"){
	            		  layer.msg('该药品名称不存在，请输入正确的药品名称！!');
	            	  }else{
	            		  layer.msg('添加成功!');
	            		//后台成功后，静态更新表格中的数据
		                  table.reload('drugPriceTable'); //数据刷新
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
	    table.on('tool(drugPriceTable)', function(obj){
	      var data = obj.data;
	      if(obj.event === 'del'){
	    	//删除
		  layer.confirm('确定删除该药品限价吗？', function(index){
		      //执行 Ajax 后重载
		        var url=$WEB_ROOT_PATH+"/dhccApi/drugprice/drugPrice/delete";
		        $.post(url,{'drugPrice.id':data.id},function(result){
		        	table.reload('drugPriceTable');
		            layer.msg('已删除');
		    	    });
		        });
	      } else if(obj.event === 'edit'){
	    	//修改方法
		    layer.open({
		          type: 2
		          ,title: '编辑药品限价信息'
		          ,content: $WEB_ROOT_PATH+'/drugprice/drugPriceedit'
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
		              if(field["drugPrice.cityCode"]=="" || field["drugPrice.cityCode"]==null){
		            	  layer.msg('请选择城市编码！!');
		            	  return false;
		              }
		              //提交 Ajax后台 
		              var url=$WEB_ROOT_PATH+"/dhccApi/drugprice/drugPrice/save";
		              $.post(url,field,function(result){
		            	  if(result.id=="1"){
		            		  layer.msg('该药品编码不存在，请输入正确的药品编码！');
		            	  }else if(result.id=="2"){
		            		  layer.msg('该药品名称不存在，请输入正确的药品名称！!');
		            	  }else{
		            		  layer.msg('编辑成功!');
		            		//后台成功后，静态更新表格中的数据
			                  table.reload('drugPriceTable'); //数据刷新
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