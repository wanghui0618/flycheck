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
	    	elem: '#drugruleTable'
	            ,url: $WEB_ROOT_PATH+'/dhccApi/drugrule/drugRule/list'
	            /*,cellMinWidth: 80*/
	           // ,where: {  cityCode: 'DHCC'  }
	            ,height: tableHeight
	            ,cols: [[
	            	   {type: 'numbers',width:40, title: '序号',fixed: 'left' }
	            	   ,{field:'id', title: 'ID', sort: true, hide:true} 
		              ,{title:'基础信息', toolbar: '#table-useradmin-webuser',fixed: 'left', width:150,align:'center'/*,margin:auto*/}
		              ,{title:'规则', toolbar: '#table-useradmin-webuser1',fixed: 'left', width:151,align:'center'/*,margin:auto*/}
		              ,{field:'cityName', width:88,title: '所属城市',fixed: 'left',align:'center',templet: function(d){
	                	    var codex =d.cityName;
		                	if(codex==null||codex==""){
		                		codex="-";
		                	}
		                    return '<span >'+ codex +'</span>'
	                  }}
		              ,{field:'itemCode',width:190,align:'center', title: '药品编码',fixed: 'left'}
		              ,{field:'itemName',width:130,align:'center', title: '药品名称',fixed: 'left'}
		              ,{field:'generalName', width:150,align:'center',title: '通用名'}
		              ,{field:'drugType', width:90,align:'center',title: '药品分类',templet: function(d){
	                	    var codex =d.drugType;
		                	if(codex==null||codex==""){
		                		codex="-";
		                	}
		                	if(codex=="0"){
		                		codex="甲类";
		                	}
		                	if(codex=="1"){
		                		codex="乙类";
		                	}
		                	if(codex=="2"){
		                		codex="丙类";
		                	}
		                    return '<span >'+ codex +'</span>'
	                  }}
		              ,{field:'sexFlag',width:90,align:'center', title: '性别限制' ,templet: function(d){
	                	    var codex =d.sexFlag;
		                	if(codex==null||codex==""){
		                		codex="-";
		                	}
		                	if(codex=="0"){
		                		codex="无限制";
		                	}
		                	if(codex=="1"){
		                		codex="限男性";
		                	}
		                	if(codex=="2"){
		                		codex="限女性";
		                	}
		                    return '<span >'+ codex +'</span>'
	                  }
	                }
		              ,{field:'indicationFlag', width:100,align:'center',title: '适应症标志',templet: function(d){
	                	    var codex =d.indicationFlag;
		                	if(codex==null||codex==""){
		                		codex="-";
		                	}
		                	if(codex=="1"){
		                		codex="是";
		                	}
		                	if(codex=="0"){
		                		codex="否";
		                	}
		                    return '<span >'+ codex +'</span>'
	                  }}
		              ,{field:'indicationComments',width:260,align:'center',title: '适应症内容'/*,event: 'indicationRegex'*/}
		              ,{field:'rescueFlag',width:100,align:'center',title: '限抢救用药',templet: function(d){
	                	    var codex =d.rescueFlag;
		                	if(codex==null||codex==""){
		                		codex="-";
		                	}
		                	if(codex=="1"){
		                		codex="是";
		                	}
		                	if(codex=="0"){
		                		codex="否";
		                	}
		                    return '<span >'+ codex +'</span>'
	                  }}
		              ,{field:'contraindicationFlag',width:90,align:'center', title: '禁忌标志',templet: function(d){
	                	    var codex =d.contraindicationFlag;
		                	if(codex==null||codex==""){
		                		codex="-";
		                	}

		                	if(codex=="1"){
		                		codex="是";
		                	}
		                	if(codex=="0"){
		                		codex="否";
		                	}
		                    return '<span >'+ codex +'</span>'
	                  }}
		              ,{field:'contraindicationComments',width:260,align:'center', title: '禁忌内容'}
		              ,{field:'insuranceMark',width:90,align:'center', title: '险种标志',templet: function(d){
	                	    var codex =d.insuranceMark;
		                	if(codex==null||codex==""){
		                		codex="-";
		                	}
		                    return '<span >'+ codex +'</span>'
	                  }}
		              ,{field:'orgLevel', width:90,align:'center',title: '医院等级',templet: function(d){
	                	    var codex =d.orgLevel;
		                	if(codex==null||codex==""){
		                		codex="-";
		                	}
		                    return '<span >'+ codex +'</span>'
	                  }}
		              ,{field:'personType',width:100,align:'center', title: '适用：儿童',templet: function(d){
	                	    var codex =d.personType;
		                	if(codex==null||codex==""){
		                		codex="-";
		                	}
		                	if(codex=="1"){
		                		codex="是";
		                	}
		                	if(codex=="0"){
		                		codex="否";
		                	}
		                    return '<span >'+ codex +'</span>'
	                  }}
		              ,{field:'diagType',width:90,align:'center', title: '就医类型'
		            	  ,templet: function(d){
		                	    var codex =d.diagType;
			                	if(codex==null||codex==""){
			                		codex="-";
			                	}
			                	if(codex == "null"){
									  codex="-";
								  }
			                	if(codex=="1"){
			                		codex="门诊";
			                	}
			                	if(codex=="0"){
			                		codex="住院";
			                	}
			                    return '<span >'+ codex +'</span>'
		                  }}
		              //,{field:'orgTypeLevel', width:120,align:'center',title: '医疗机构类型'}             
		              ,{field:'invoiceProject',width:90,align:'center', title: '发票项目',templet: function(d){
	                	    var codex =d.invoiceProject;
		                	if(codex==null||codex==""){
		                		codex="-";
		                	}
		                    return '<span >'+ codex +'</span>'
	                  }}
		              //,{field:'drugType',width:120,align:'center', title: '药品分类'}
		              //,{field:'doseForm',width:90,align:'center', title: '药剂名称'}
		              ,{field:'itemStandard',width:180,align:'center', title: '药品规格'}
		              ,{field:'itemUnit',width:80,align:'center', title: '单位'}
		              ,{field:'producer',width:260,align:'center', title: '生产厂商'}
		              ,{field:'beginTime',width:135,align:'center', title: '起始日期'}
		              ,{field:'endTime', width:135,align:'center',title: '终止日期'}
		              ,{field:'isMedicare',width:90,align:'center', title: '医保用药'
		            	  ,templet: function(d){
		                	    var codex =d.isMedicare;
			                	if(codex==null||codex==""){
			                		codex="-";
			                	}
			                	//alert(codex);
			                	if(codex=="1"){
			                		codex="是";
			                	}
			                	if(codex=="0"){
			                		codex="否";
			                	}
			                    return '<span >'+ codex +'</span>'
		                  }}
		              ,{field:'validFlag',width:90,align:'center', title: '有效标志'
		            	  ,templet: function(d){
		                	    var codex =d.validFlag;
			                	if(codex==null||codex==""){
			                		codex="-";
			                	}
			                	//alert(codex);
			                	if(codex=="0"){
			                		codex="否";
			                	}
			                	if(codex=="1"){
			                		codex="是";
			                	}
			                    return '<span >'+ codex +'</span>'
		                  }}
		              ,{field:'comments',width:160,align:'center', title: '备注'}
	         
	            ]]
	            ,page: true
	          });
	    
	    
	    hideButtonStatic();//按钮权限
	    
	    //监听搜索
		form.on('submit(LAY-user-front-search)', function(data){
	    	var field = data.field;    	
	    	 console.info(field);
	        //执行重载
	        layui.table.reload('drugruleTable', {
	            where: field
	        });
	    });
		
		//添加事件
	    var active = {
	      add: function(){
	    	  //药品信息修改
	    	    layer.open({
			          type: 2
			          ,title: '新增'
			          ,content: $WEB_ROOT_PATH+'/drugrule/drugInfo'
			          ,maxmin: true
			          ,area: ['650px', '530px']
			          ,btn: ['确定', '取消']
	    	          ,success: function(layero, index){
		        	  var iframeWindow = window['layui-layer-iframe'+ index];
		        	  //向此iframe层方法 传递参数
		        	  iframeWindow.loadSelect();
		              }
	                  ,yes: function(index, layero){
			            var iframeWindow = window['layui-layer-iframe'+ index]
			            ,submitID = 'layuiadmin-btn-useradmin'
			            ,submit = layero.find('iframe').contents().find('#'+ submitID);

			            //监听提交
			            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
			              var field = data.field; //获取提交的字段
                        //提交 Ajax后台 
			              var url=$WEB_ROOT_PATH+"/dhccApi/drugrule/drugRule/saveInfo";
			              //console.info(field);
			              $.post(url,field,function(result){	
			            	  var inFlag= result.inFlag; 
			            	  if(inFlag==1){
				                  layer.msg('该编码已存在!');
			            		  return false;
			            	 }else {
			            		 layer.msg('新增成功!');
				            	  //后台成功后，静态更新表格中的数据
				                  table.reload('drugruleTable'); //数据刷新
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
		

	  
	    //监听行点击
	    table.on('tool(drugruleTable)', function(obj){
	      var data = obj.data;
	      if(obj.event === 'xiugai'){
	    	  //修改规则信息
	    	    layer.open({
			          type: 2
			          ,title: '规则维护'
			          ,content: $WEB_ROOT_PATH+'/drugrule/drugRuleInfo'
			          ,maxmin: true
			          ,area: ['650px', '485px']
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
			              
			             
			              console.info(field);
			              
			              //提交 Ajax后台 
			              var url=$WEB_ROOT_PATH+"/dhccApi/drugrule/drugRule/save";
			              $.post(url,field,function(result){	
			            	 // var inFlag= result.inFlag; 
			            	  //if(inFlag==0){
			            		  layer.msg('编辑成功!');
				            	  //后台成功后，静态更新表格中的数据
				                  table.reload('drugruleTable'); //数据刷新
				                  layer.close(index); //关闭弹层
				            
			            	// }else if(inFlag==1){
			            		 // layer.msg('该用户名已被注册!');
			            		 // return false;
			            	// }
			            	  //关闭弹层
						  });
			              
			             
			            });  
			            submit.trigger('click');
			          }
			        }); 
	   
	      } else if(obj.event === 'shanchu'){
	    	//删除规则信息
	    	  var itemCode=data.itemCode;
		        layer.confirm('是否确定删除', function(index){
		        	//执行 Ajax 后重载
		            var url=$WEB_ROOT_PATH+"/dhccApi/drugrule/drugRule/delete";
		            $.post(url,{"drugRule.itemCode":itemCode},function(result){
		        	    table.reload('drugruleTable');
		                layer.msg('已删除');
		    	    });
		        });
	      }else if(obj.event === 'shanchu2'){
	    	//药品信息删除
	    	  var itemCode=data.itemCode;
	    	  var id=data.id;
	    	  var cityCode =data.cityCode;
		        layer.confirm('是否确定删除', function(index){
		        	//执行 Ajax 后重载
		            var url=$WEB_ROOT_PATH+"/dhccApi/drugrule/drugRule/deleteInfo";
		            $.post(url,{"drugInfo.id":id,
		            	        "drugRule.itemCode":itemCode,
		            	        "drugInfo.itemCode":itemCode,
		            	        "drugInfo.cityCode":cityCode,
		            	},function(result){	
		            	  var inFlag2= result.inFlag2; 
		            	  if(inFlag2==1){
			                  layer.msg('该数据存在关联信息，请先解除关联');
		            		  return false;
		            	 }else {
		            		 layer.msg('删除成功!');
			            	  //后台成功后，静态更新表格中的数据
			                  table.reload('drugruleTable'); //数据刷新
			                  layer.close(index); //关闭弹层
		            	 }
		            	  //关闭弹层
					  });
		        });
	    	  
	      }else if(obj.event === 'xiugai2'){
	    	  //药品信息修改
	    	    layer.open({
			          type: 2
			          ,title: '修改'
			          ,content: $WEB_ROOT_PATH+'/drugrule/drugInfo'
			          ,maxmin: true
			          ,area: ['650px', '530px']
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
				            iframeWindow.layui.form.on('submit('+ submitID +')', function(data1){
				            	 console.info(data1);		
				              var field2 = data1.field; //获取提交的字段
			      	                          
			              //提交 Ajax后台 
			              var url=$WEB_ROOT_PATH+"/dhccApi/drugrule/drugRule/saveInfo";
			              $.post(url,field2,function(result){	
			            	
			            		  layer.msg('编辑成功!');
				            	  //后台成功后，静态更新表格中的数据
				                  table.reload('drugruleTable'); //数据刷新
				                  layer.close(index); //关闭弹层
				        
						  });
			              
			             
			            });  
			            submit.trigger('click');
			          }
			        }); 
	   
	      
	    	  
	      }else if(obj.event === 'indicationRegex'){
	    	  var itemCode=data.itemCode;
	    	  var url=$WEB_ROOT_PATH+"/dhccApi/drugrule/drugRule/indicationRegex";
	    	  $.post(url,{"drugRule.itemCode":itemCode},function(indicationRegex){	
	    		  layer.prompt({
	    			  formType: 2
	    			  ,title: '编辑适应症：'+ data.indicationComments +' 的正则表达式'
	    			  ,value: indicationRegex

	    		  }, function(value, index){
	    			  layer.close(index);

	    			  //这里一般是发送修改的Ajax请求

	    			  //同步更新表格和缓存对应的值


	    		  });
	      });  	  

	      }
	    });
	    
	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });