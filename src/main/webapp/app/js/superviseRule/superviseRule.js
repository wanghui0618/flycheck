//初始化	
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table','laydate'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;
	    var laydate = layui.laydate;
	    laydate.render({
	    	elem: '#finaTime'
	    		,trigger:'click'
	    			,format:'yyyy-MM-dd'
	    				,range: true
	    });
	    table.render({
	    	elem: '#userTable'
	    		 ,url: $WEB_ROOT_PATH+"/dhccApi/superviseRes/superviseRes/getAllAreaData1"
	            ,cellMinWidth: 80
	             ,height: 415
	            ,cols: [[
	            	  {type: 'numbers', width:120, title: '排名'}
						,{field:'pname', align:'center',title: '统筹区' }
						,{field: 'pnumber',width:160,align:'center', title: '违规人次',templet: function(d){
							var codex = d.pnumber;
							if(codex=="0" || codex==null){
								codex="-";
							}
							return '<span>'+ codex +'</span>'
						 }}
						]]
		    		,page: true
		          });
	    
	    //监听搜索
		form.on('submit(LAY-user-front-search)', function(data){
	    	var field = data.field;
	        //执行重载
	        layui.table.reload('userTable', {
	            where: field
	        });
	    });
	  
		$.ajax({
			url : $WEB_ROOT_PATH+"/dhccApi/superviseRes/superviseRes/judgeUser",
			type : "get",
			success : function(data1) {
				var flag = data1;
				if(flag == "notAdmin"){
					$('#selProvince').hide();
				}else if(flag == 'admin'){
					$('#selProvince').show();
				}
			},
			error : function (data){
				alert("服务器错误");
			}
			
			
		});
		
	  //监听行点击
	    table.on('tool(userTable)', function(obj){
	      var data = obj.data;
	      if(obj.event === 'del'){
	    	//删除
	    	  layer.open({
		          type: 2
		          ,title: '饼图'
		          ,content: $WEB_ROOT_PATH+'/superviseRule/superviseRuleform1'
		          ,maxmin: true
		          ,area: ['900px', '600px']
		         /* ,btn: ['确定', '取消']
		          ,success: function(layero, index){
		        	  var iframeWindow = window['layui-layer-iframe'+ index];
		        	//加载select下拉option
						iframeWindow.loadSelect();
		        	  //向此iframe层方法 传递参数
		        	  iframeWindow.child(JSON.stringify(data));
		        	  console.log(JSON.stringify(data));
		        	
		          }
		          ,yes: function(index, layero){
		            var iframeWindow = window['layui-layer-iframe'+ index]
		            ,submitID = 'LAY-user-front-submit'
		            ,submit = layero.find('iframe').contents().find('#'+ submitID);
	
		            //监听提交
		            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
		              var field = data.field; //获取提交的字段
		              console.log(field);
		              //提交 Ajax后台 
		              var url=$WEB_ROOT_PATH+"/dhccApi/dictmaintain/dictmaintain/save1";
		              $.post(url,field,function(result){
		            	  var inFlag=result.inFlag;
		            	  if(inFlag==0){
		            		  layer.msg('修改成功!');
		            		  //后台成功后，静态更新表格中的数据
		                      table.reload('userTable'); //数据刷新
		                      layer.close(index); //关闭弹层
		            	  }else if(inFlag==1){
		            		  layer.msg('修改成功!')
		            		   table.reload('userTable'); //数据刷新
		                      layer.close(index); //关闭弹层
		            		  //return false;
		            	  }
					  });
			       });
		            submit.trigger('click');
		          }*/
		        }); 
	      } else if(obj.event === 'edit'){
	    	//修改方法
		    layer.open({
		          type: 2
		          ,title: '柱形图'
		          ,content: $WEB_ROOT_PATH+'/superviseRule/superviseRuleform'
		          ,maxmin: true
		          ,area: ['900px', '600px']
		       /*   ,btn: ['确定', '取消']
		          ,success: function(layero, index){
		        	  var iframeWindow = window['layui-layer-iframe'+ index];
		        	//加载select下拉option
						iframeWindow.loadSelect();
		        	  //向此iframe层方法 传递参数
		        	  iframeWindow.child(JSON.stringify(data));
		        	  console.log(JSON.stringify(data));
		        	
		          }
		          ,yes: function(index, layero){
		            var iframeWindow = window['layui-layer-iframe'+ index]
		            ,submitID = 'LAY-user-front-submit'
		            ,submit = layero.find('iframe').contents().find('#'+ submitID);
	
		            //监听提交
		            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
		              var field = data.field; //获取提交的字段
		              console.log(field);
		              //提交 Ajax后台 
		              var url=$WEB_ROOT_PATH+"/dhccApi/dictmaintain/dictmaintain/save1";
		              $.post(url,field,function(result){
		            	  var inFlag=result.inFlag;
		            	  if(inFlag==0){
		            		  layer.msg('修改成功!');
		            		  //后台成功后，静态更新表格中的数据
		                      table.reload('userTable'); //数据刷新
		                      layer.close(index); //关闭弹层
		            	  }else if(inFlag==1){
		            		  layer.msg('修改成功!')
		            		   table.reload('userTable'); //数据刷新
		                      layer.close(index); //关闭弹层
		            		  //return false;
		            	  }
					  });
			       });
		            submit.trigger('click');
		          }*/
		        }); 
	      }
	      else if(obj.event === 'edit1'){
		    	//修改方法
			    layer.open({
			          type: 2
			          ,title: '折线图'
			          ,content: $WEB_ROOT_PATH+'/superviseRule/superviseRuleform2'
			          ,maxmin: true
			          ,area: ['900px', '600px']
			       /*   ,btn: ['确定', '取消']
			          ,success: function(layero, index){
			        	  var iframeWindow = window['layui-layer-iframe'+ index];
			        	//加载select下拉option
							iframeWindow.loadSelect();
			        	  //向此iframe层方法 传递参数
			        	  iframeWindow.child(JSON.stringify(data));
			        	  console.log(JSON.stringify(data));
			        	
			          }
			          ,yes: function(index, layero){
			            var iframeWindow = window['layui-layer-iframe'+ index]
			            ,submitID = 'LAY-user-front-submit'
			            ,submit = layero.find('iframe').contents().find('#'+ submitID);
		
			            //监听提交
			            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
			              var field = data.field; //获取提交的字段
			              console.log(field);
			              //提交 Ajax后台 
			              var url=$WEB_ROOT_PATH+"/dhccApi/dictmaintain/dictmaintain/save1";
			              $.post(url,field,function(result){
			            	  var inFlag=result.inFlag;
			            	  if(inFlag==0){
			            		  layer.msg('修改成功!');
			            		  //后台成功后，静态更新表格中的数据
			                      table.reload('userTable'); //数据刷新
			                      layer.close(index); //关闭弹层
			            	  }else if(inFlag==1){
			            		  layer.msg('修改成功!')
			            		   table.reload('userTable'); //数据刷新
			                      layer.close(index); //关闭弹层
			            		  //return false;
			            	  }
						  });
				       });
			            submit.trigger('click');
			          }*/
			        }); 
		      }
	    });
	    

	    function deRepeat(arr){
	           var newArr=[];
	           for(var i=0;i<arr.length;i++){
	        	   var text=arr[i].text;
	               if($.inArray(arr[i],newArr)==-1){
	                   newArr.push(arr[i]);
	               }
	           }
	           return newArr;
	       }
	   
	    
	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });