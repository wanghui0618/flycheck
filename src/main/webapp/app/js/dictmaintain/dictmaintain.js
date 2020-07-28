//初始化	
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' // 静态资源所在路径
	  }).extend({
	    index: 'lib/index' // 主入口模块
	  }).use(['index', 'table'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;
	    
	    // 加载城市下拉字典
		 $.getJSON($WEB_ROOT_PATH+'/dhccApi/dict/dict/findDict?dictRequestVo.tableName=t_piccbid_dict', 
					function(data){
			     		var  dataList= data.dictList;
			     		
			     		let log = console.log.bind(console);
			     		let obj = {};
			     		
			     		var cur=[];
			     		person = dataList.reduce((cur,next) => {
			     		    obj[next.text] ? "" : obj[next.text] = true && cur.push(next);
			     		    return cur;
			     		},[]) // 设置cur默认类型为数组，并且初始值为空的数组
			     		log(person);
			     		dataList=person;
			     		for(var i=0 ;i<dataList.length;i++){
			     			
			     			var nn="<option value='"+dataList[i].value+"'>"+dataList[i].text+"</option>";
			     			$("#dictm").append(nn); 
			     		}
			     	form.render('select');
		});
		 
		 $.getJSON($WEB_ROOT_PATH+'/dhccApi/dict/dict/findDict?dictRequestVo.tableName=t_piccbid_dictd', 
					function(data){
			     		var  dataList= data.dictList;
			     		
			     		let log = console.log.bind(console);
			     		let obj = {};
			     		
			     		var cur=[];
			     		person = dataList.reduce((cur,next) => {
			     		    obj[next.text] ? "" : obj[next.text] = true && cur.push(next);
			     		    return cur;
			     		},[]) // 设置cur默认类型为数组，并且初始值为空的数组
			     		log(person);
			     		dataList=person;
			     		for(var i=0 ;i<dataList.length;i++){
			     			
			     			var nn="<option value='"+dataList[i].value+"'>"+dataList[i].text+"</option>";
			     			$("#typeDesc").append(nn); 
			     		}
			     	form.render('select');
		});
	    
		 
		 $.getJSON($WEB_ROOT_PATH+'/dhccApi/dict/dict/findDict?dictRequestVo.tableName=t_piccbid_dictCity', 
					function(data){
			     		var  dataList= data.dictList;
			     		
			     		let log = console.log.bind(console);
			     		let obj = {};
			     		
			     		var cur=[];
			     		person = dataList.reduce((cur,next) => {
			     		    obj[next.text] ? "" : obj[next.text] = true && cur.push(next);
			     		    return cur;
			     		},[]) // 设置cur默认类型为数组，并且初始值为空的数组
			     		log(person);
			     		dataList=person;
			     		for(var i=0 ;i<dataList.length;i++){
			     			
			     			var nn="<option value='"+dataList[i].value+"'>"+dataList[i].text+"</option>";
			     			$("#cityName").append(nn); 
			     		}
			     	form.render('select');
		});
		 
	    table.render({
	    	elem: '#userTable'
	            ,url: $WEB_ROOT_PATH+'/dhccApi/dictmaintain/dictmaintain/listVo'
	            ,cellMinWidth: 80
	            ,height: document.documentElement.clientHeight-80
	            ,where: {  ilegalChild: '1'  }
	            ,cols: [[
	            	   {type: 'numbers', title: '序号' }
	            	   ,{field:'left', title:'操作', toolbar: '#table-useradmin-webuser', width:150}
		              ,{field:'id', title: 'ID', sort: true, hide:true}
		              ,{field:'name', title: '字典名称'}
		              ,{field:'value', title: '字典值'}
		              ,{field:'type', title: '业务类型'}
		              ,{field:'handdingInsName', title:'统筹区',sort: true}
		              ,{field:'orgCode',hide:true, title:'机构编码'}
		              ,{field:'orgName',title:'机构名称'}
		            ]]
	            ,done:function(){
	      			$("[data-field='cityName']").children().each(function(){
	                    if($(this).text()=='210000'){
	                        $(this).text("沈阳市")
	                    }else if($(this).text()=='210200'){
	                        $(this).text("大连市")
	                    }else if($(this).text()=='140000'){
	                    	$(this).text("太原市")
	                    }else if($(this).text()=='140500'){
	                    	$(this).text("晋城市")
	                    }else if($(this).text()=='110000'){
	                    	$(this).text("北京市")
	                    }else if($(this).text()=='150100'){
	                    	$(this).text("贵阳市")
	                    }else if($(this).text()=='160100'){
	                    	$(this).text("泉州市")
	                    }else if($(this).text()=='170100'){
	                    	$(this).text("成都市")
	                    }else if($(this).text()=='170110'){
	                    	$(this).text("广元市")
	                    }else if($(this).text()=='180100'){
	                    	$(this).text("桂林市")
	                    }else if($(this).text()=='130100'){
	                    	$(this).text("西安市")
	                    }else if($(this).text()=='160200'){
	                    	$(this).text("福州市")
	                    }else if($(this).text()=='未知'){
	                    	$(this).text("-")
	                    }
	                });
	      		  }
		    		,page: true
		          });
	    hideButtonStatic();// 按钮权限
	    
	    // 监听搜索
		form.on('submit(LAY-user-front-search)', function(data){
	    	var field = data.field;
	        // 执行重载
	        layui.table.reload('userTable', {
	            where: field
	        });
	    });
	  
	   // 添加事件
	    var active = {
	    		add: function(){
	    			
	    			 
	    		    	// 新增方法
	    		        layer.open({
	    		          type: 2
	    		          ,title: '添加字典'
	    		          ,content: $WEB_ROOT_PATH+'/dictmaintain/dictmaintainform'
	    		          ,maxmin: true
	    		          ,area: ['800px', '350px']
	    		          ,btn: ['确定', '取消']
	    		          ,success: function(layero, index){
	    						var iframeWindow = window['layui-layer-iframe'+ index];
	    						// 加载select下拉option
	    						iframeWindow.loadSelect();
	    					}
	    		          ,yes: function(index, layero){
	    		            var iframeWindow = window['layui-layer-iframe'+ index]
	    		            ,submitID = 'LAY-user-front-submit'
	    		            ,submit = layero.find('iframe').contents().find('#'+ submitID);

	    		            // 监听提交
	    		            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
	    		              var field = data.field; // 获取提交的字段
	    		             // console.log(field);
	       
	    	  // 提交 Ajax后台
              var url=$WEB_ROOT_PATH+"/dhccApi/dictmaintain/dictmaintain/save";
              $.post(url,field,function(result){
            	  var inFlag=result.inFlag;
            	  if(inFlag==0){
            		  layer.msg('添加成功!');
            		  // 后台成功后，静态更新表格中的数据
                      table.reload('userTable'); // 数据刷新
                      layer.close(index); // 关闭弹层
            	  }else if(inFlag==1){
            		  layer.msg('该字典名称已存在！')
            		  return false;
            	  }
			  });
	       });
	    submit.trigger('click');
	     }
	  }); 
	}
};
	  // 监听行点击
	    table.on('tool(userTable)', function(obj){
	      var data = obj.data;
	     console.log(data);
	      if(obj.event === 'del'){
	    	// 删除
	        layer.confirm('确定要删除该条数据？', function(index){
	        	// 执行 Ajax 后重载
	            var url=$WEB_ROOT_PATH+"/dhccApi/dictmaintain/dictmaintain/delete";
	            $.post(url,{"dictmaintain.Id":data.id},function(result){
	        	    table.reload('userTable');
	                layer.msg('已删除');
	    	    });
	        });
	      } else if(obj.event === 'edit'){
	    	// 修改方法
		    layer.open({
		          type: 2
		          ,title: '修改字典'
		          ,content: $WEB_ROOT_PATH+'/dictmaintain/dictmaintainform'
		          ,maxmin: true
		          ,area: ['800px', '350px']
		          ,btn: ['确定', '取消']
		          ,success: function(layero, index){
		        	  var iframeWindow = window['layui-layer-iframe'+ index];
		        	// 加载select下拉option
						iframeWindow.loadSelect();
		        	  // 向此iframe层方法 传递参数
		        	  iframeWindow.child(JSON.stringify(data));
		        	  console.log(JSON.stringify(data));
		        	
		          }
		          ,yes: function(index, layero){
		            var iframeWindow = window['layui-layer-iframe'+ index]
		            ,submitID = 'LAY-user-front-submit'
		            ,submit = layero.find('iframe').contents().find('#'+ submitID);
	
		            // 监听提交
		            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
		              var field = data.field; // 获取提交的字段
		              console.log(field);
		              // 提交 Ajax后台
		              var url=$WEB_ROOT_PATH+"/dhccApi/dictmaintain/dictmaintain/save1";
		              $.post(url,field,function(result){
		            	  var inFlag=result.inFlag;
		            	  if(inFlag==0){
		            		  layer.msg('修改成功!');
		            		  // 后台成功后，静态更新表格中的数据
		                      table.reload('userTable'); // 数据刷新
		                      layer.close(index); // 关闭弹层
		            	  }else if(inFlag==1){
		            		  layer.msg('修改成功!')
		            		   table.reload('userTable'); // 数据刷新
		                      layer.close(index); // 关闭弹层
		            		  // return false;
		            	  }
					  });
			       });
		            submit.trigger('click');
		          }
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
	   
	    
	    // 按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });