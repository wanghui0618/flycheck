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
			     		$("#city").find("option[value='DHCC']").attr("selected","selected");
					    form.render('select');
		 });

	    table.render({
	    	elem: '#userTable'
	            ,url: '/piccbid/app/js/resultAppeal/overtreatment4.json'
	            ,cellMinWidth: 80
	           // ,where: {  }
	            ,height: tableHeight
	            ,cols: [[
	            	   {type: 'numbers',width:40, title: '序号',fixed: 'left' }
	            	   ,{field:'left',width:80, title:'操作', align:'center',toolbar: '#table-useradmin-webuser'}
	            	  ,{field:'id', title: 'ID', sort: true, hide:true} 
		              //,{title:'操作', toolbar: '#table-useradmin-webuser', width:150,align:'center'}
		              //,{field:'billingNo', title: '单据号',width:100,align:'center'}
		              ,{field:'hospitalCode', title: '患者编号',align:'center'}
		              ,{field:'hospitalName', title: '患者姓名',align:'center'}
		              ,{field:'hospitalName1', title: '医院名称',align:'center'}
		              ,{field:'hospitalName2', title: '可疑金额',align:'center'}
		              ,{field:'hospitalName3', title: '疾病',align:'center'}
		              ,{field:'hospitalName4', title: '异常项目',align:'center'}
		                         		             

	            ]]
	            ,page: true
	          });
	    
	    //监听搜索
		form.on('submit(LAY-user-front-search)', function(data){
	    	var field = data.field;    	
	    	 console.info(field);
	        //执行重载
	        layui.table.reload('blackList', {
	            where: field
	        });
	    });
		
		//添加事件
	/*    var active = {
	      add: function(){
	    	  //参保信息新增
	    	    layer.open({
			          type: 2
			          ,title: '黑名单记录新增'
			          ,content: $WEB_ROOT_PATH+'/blackList/blackListInfo'
			          ,maxmin: true
			          ,area: ['800px', '400px']
			          ,btn: ['确定', '取消']  
	                  ,yes: function(index, layero){
	                	 
			            var iframeWindow = window['layui-layer-iframe'+ index]
			    	
			            ,submitID = 'layuiadmin-btn-useradmin'
			            ,submit = layero.find('iframe').contents().find('#'+ submitID);

			            //监听提交
			            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
			              var field = data.field; //获取提交的字段
                        //提交 Ajax后台 
			              var url=$WEB_ROOT_PATH+"/dhccApi/blackList/blackList/save";
			              //console.info(field);
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
		
*/
		 table.on('row(userTable)', function(obj){
		      var data = obj.data;
		      layer.open({
		          type: 2
		          ,title: '血型冲突'
		          ,content: $WEB_ROOT_PATH+'/bigDataAntiFraud/overtreatmentInfo4'
		          ,maxmin: true
		          ,area: ['850px', '420px']
		      });
		 });
	    //监听行点击
	   /* table.on('tool(blackList)', function(obj){
	      var data = obj.data;
	      if(obj.event === 'xiugai'){
	    	  //修改
	    	    layer.open({
			          type: 2
			          ,title: '黑名单记录信息管理'
			          ,content: $WEB_ROOT_PATH+'/blackList/blackListInfo'
			          ,maxmin: true
			          ,area: ['800px', '400px']
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
	   
	      } else if(obj.event === 'shanchu'){
	    	//删除
	    	  var id=data.id;
	    	  var name=data.name;
		        layer.confirm('是否确定删除<span style="color: red;">'+name+"'</span>'的信息", function(index){
		        	//执行 Ajax 后重载
		            var url=$WEB_ROOT_PATH+"/dhccApi/blackList/blackList/delete";
		            $.post(url,{"blackList.id":id},function(result){
		        	    table.reload('blackList');
		                layer.msg('已删除');
		    	    });
		        });
	      }
	    });*/
	    
	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });