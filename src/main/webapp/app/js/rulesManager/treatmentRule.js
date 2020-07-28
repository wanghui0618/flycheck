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
	            ,url: $WEB_ROOT_PATH+'/dhccApi/treatmentrule/treatmentRule/list'
	            /*,cellMinWidth: 80*/
	          //  ,where: {  cityCode: 'DHCC'  }
	            ,height: tableHeight
	            ,cols: [[
	            	   {type: 'numbers',width:40, title: '序号',fixed: 'left' }
	            	   ,{field:'id', title: 'ID', sort: true, hide:true} 
		              ,{title:'基础信息', toolbar: '#table-useradmin-treatment',fixed: 'left', width:150,align:'center'/*,margin:auto*/}
		              ,{title:'规则', toolbar: '#table-useradmin-treatment1',fixed: 'left', width:151,align:'center'/*,margin:auto*/}
		              ,{field:'cityName', width:88,title: '所属城市',fixed: 'left',align:'center',templet: function(d){
	                	    var codex =d.cityName;
		                	if(codex==null||codex==""){
		                		codex="-";
		                	}
		                    return '<span >'+ codex +'</span>'
	                  }}
		              ,{field:'itemCode',width:140,align:'center', title: '诊疗编码',fixed: 'left'}
		              ,{field:'itemName',width:367,align:'center', title: '诊疗名称',fixed: 'left'}
		              ,{field:'projectType', width:90,align:'center',title: '诊疗分类',templet: function(d){
	                	    var codex =d.projectType;
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
		              ,{field:'indicationComments',width:260,align:'center',title: '适应症内容'}
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
		              ,{field:'personType',width:91,align:'center', title: '适用:儿童',templet: function(d){
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
		              //,{field:'orgTypeLevel',width:90,align:'center', title: '医疗机构类型'}
		              ,{field:'itemStandard',width:182,align:'center', title: '诊疗规格'}
		              ,{field:'itemUnit',width:60,align:'center', title: '单位'}
		              ,{field:'invoiceProject',width:90,align:'center', title: '发票项目',templet: function(d){
	                	    var codex =d.invoiceProject;
		                	if(codex==null||codex==""){
		                		codex="-";
		                	}
		                    return '<span >'+ codex +'</span>'
	                  }}     
		              ,{field:'beginTime',width:120,align:'center', title: '起始日期'}
		              ,{field:'endTime', width:120,align:'center',title: '终止日期'}
		              ,{field:'isMedicare',width:90,align:'center', title: '医保项目'
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
		              ,{field:'validFlag',width:90,align:'center', title: '是否有效'
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
	    	// console.info(field);
	        //执行重载
	        layui.table.reload('drugruleTable', {
	            where: field
	        });
	    });
		//添加事件
	    var active = {
	      add: function(){
	    	  //诊疗信息修改
	    	    layer.open({
			          type: 2
			          ,title: '新增'
			          ,content: $WEB_ROOT_PATH+'/treatmentrule/treatmentInfo'
			          ,maxmin: true
			          ,area: ['650px', '525px']
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
			              var url=$WEB_ROOT_PATH+"/dhccApi/treatmentrule/treatmentRule/saveInfo";
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
	    	  //规则新增/修改
	    	    layer.open({
			          type: 2
			          ,title: '规则维护'
			          ,content: $WEB_ROOT_PATH+'/treatmentrule/treatmentRuleInfo'
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
			              //提交 Ajax后台 
			              var url=$WEB_ROOT_PATH+"/dhccApi/treatmentrule/treatmentRule/save";
			              $.post(url,field,function(result){	
	
			            		  layer.msg('编辑成功!');
				            	  //后台成功后，静态更新表格中的数据
				                  table.reload('drugruleTable'); //数据刷新
				                  layer.close(index); //关闭弹层
		
						  });
	             
			            });  
			            submit.trigger('click');
			          }
			        }); 
	   
	      } else if(obj.event === 'shanchu'){
	    	//规则删除
	    	  var itemCode=data.itemCode;
		        layer.confirm('是否确定删除', function(index){
		        	//执行 Ajax 后重载
		            var url=$WEB_ROOT_PATH+"/dhccApi/treatmentrule/treatmentRule/delete";
		            $.post(url,{"treatmentRule.itemCode":itemCode},function(result){
		        	    table.reload('drugruleTable');
		                layer.msg('已删除');
		    	    });
		        });
	      } else if(obj.event === 'shanchu2'){
	    	//诊疗信息删除
	    	  var itemCode=data.itemCode;
	    	  var id=data.id;
	    	  var cityCode =data.cityCode;
		        layer.confirm('是否确定删除', function(index){
		        	//执行 Ajax 后重载
		            var url=$WEB_ROOT_PATH+"/dhccApi/treatmentrule/treatmentRule/deleteInfo";
		            $.post(url,{"treatmentInfo.id":id,
		            	        "treatmentRule.itemCode":itemCode,
		            	        "treatmentInfo.itemCode":itemCode,
		            	        "treatmentInfo.cityCode":cityCode,
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
	    	  //诊疗信息修改
	    	    layer.open({
			          type: 2
			          ,title: '修改'
			          ,content: $WEB_ROOT_PATH+'/treatmentrule/treatmentInfo'
			          ,maxmin: true
			          ,area: ['650px', '525px']
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
			              var url=$WEB_ROOT_PATH+"/dhccApi/treatmentrule/treatmentRule/saveInfo";
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
	   
	      
	    	  
	      }
	    });
	    
	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });