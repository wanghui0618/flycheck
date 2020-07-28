 //初始化	
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;
/*		 $.getJSON($WEB_ROOT_PATH+'/dhccApi/dict/dict/findDict?dictRequestVo.tableName=t_piccbid_dict_city', 
					function(data){
			     		var  dataList= data.dictList;
			     		for(var i=0 ;i<dataList.length;i++){
			     			var mm="<option value='"+dataList[i].value+"'>"+dataList[i].text+"</option>";
			     			$("#city").append(mm); 
			     		}
			     		listPersonMedical
			     		$("#city").find("option[value='DHCC']").attr("selected","selected");
					    form.render('select');
		 });*/
	    table.render({
	    	elem: '#userTable'
	            ,url: $WEB_ROOT_PATH+'/dhccApi/insuredPerson/insuredPerson/listPersonMedical'
	            ,cellMinWidth: 80
	             ,height: document.documentElement.clientHeight-80
	            
	            ,cols: [[
	            	{type: 'numbers', title: '序号' }
	            	,{field:'left', title:'操作', align:'center', toolbar: '#table-useradmin-webuser', width:80}
		              ,{field:'id', title: 'ID', sort: true, hide:true}
		              ,{field:'name', width:100,align:'center',title: '参保人'}
		              ,{field:'sex', title: '性别',width:80,align:'center',templet: function(d){
	                	    var codex =d.sex;
		                	if(codex=="1"){
		                		codex="男";
		                	}else if(codex=="0"){
		                		codex="女";
		                	}else if(codex=="2"){
		                		codex="";
		                	}else{
		                		codex=codex;
		                	}
		                    return '<span >'+ codex +'</span>'
	                  }}
		              ,{field:'birthday',width:130, align:'center', title: '出生日期'}
		              ,{field:'nation', width:150,align:'center',title: '民族'}
		              ,{field:'insuranceCode',width:200, align:'center',title: '参保号'}  
		              ,{field:'phone', width:120,align:'center',title: '联系电话'}
		              ,{field:'idCard', align:'center',width:270,title: '身份证号'}		           		    
		              ,{field:'insurePersonType',align:'center', title: '人员类别'}
		            
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
	  
	   //添加事件
	    var active = {
	    		add: function(){
	    			
	    			 
	    		    	//新增方法
	    		        layer.open({
	    		          type: 2
	    		          ,title: '添加字典'
	    		          ,content: $WEB_ROOT_PATH+'/costStatistics/userManageForm'
	    		          ,maxmin: true
	    		          ,area: ['500px', '450px']
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
	    		             // console.log(field);
	       
	    	  //提交 Ajax后台 
              var url=$WEB_ROOT_PATH+"/dhccApi/dictmaintain/dictmaintain/save";
              $.post(url,field,function(result){
            	  var inFlag=result.inFlag;
            	  if(inFlag==0){
            		  layer.msg('添加成功!');
            		  //后台成功后，静态更新表格中的数据
                      table.reload('userTable'); //数据刷新
                      layer.close(index); //关闭弹层
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
	  //监听行点击
	    table.on('tool(userTable)', function(obj){
		      var data = obj.data;
	            if(obj.event === 'edit'){
		    	//修改方法
			    layer.open({
			          type: 2
			          ,title: '就诊信息详情'
			          ,content: $WEB_ROOT_PATH+'/costStatistics/userManageform'
			          ,maxmin: true
			          ,area: ['1100px', '450px']
			          ,btn: ['确定', '取消']
			          ,success: function(layero, index){
			        	  var iframeWindow = window['layui-layer-iframe'+ index];
			        	  //向此iframe层方法 传递参数
			        	  iframeWindow.child(JSON.stringify(data));
			          }
			          ,yes: function(index, layero){
			            var iframeWindow = window['layui-layer-iframe'+ index]
			            ,submitID = 'LAY-user-front-submit'
			            ,submit = layero.find('iframe').contents().find('#'+ submitID);
		
			            //监听提交
			            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
			              var field = data.field; //获取提交的字段
			              //提交 Ajax后台 
			              var url=$WEB_ROOT_PATH+"/dhccApi/medical/medical/save";
			              $.post(url,field,function(result){
			            	  var inFlag= result.inFlag; 
			            	  if(inFlag==0){
			            		  layer.msg('修改成功!');
				            	  //后台成功后，静态更新表格中的数据
				                  table.reload('userTable'); //数据刷新
				                  layer.close(index); //关闭弹层
			            	  }
						  });
			            });  
			            submit.trigger('click');
			          }
			        }); 
		      }else if(obj.event === 'viewInfo'){
		    	    //明细
		    	  layer.open({
			          type: 2
			          ,title: '当前参保人详情'
			          ,content: $WEB_ROOT_PATH+'/costStatistics/userManageForm'
			          ,maxmin: true
			          ,area: ['91%', '550px']
		    	      ,success: function(layero, index){
		        	  var iframeWindow = window['layui-layer-iframe'+ index];
		        	  //向此iframe层方法 传递参数
		        	  iframeWindow.child(JSON.stringify(data));
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
	   
	    
	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });