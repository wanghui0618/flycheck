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
	    	elem: '#personTable'
	            ,url: $WEB_ROOT_PATH+'/dhccApi/insuredPerson/insuredPerson/list'
	            /*,cellMinWidth: 80*/
	            //,where: { cityCode: 'DHCC'  }
	            ,height:tableHeight
	            ,cols: [[
	            	   {type: 'numbers',width:40, title: '序号',fixed: 'left' }
	            	  ,{field:'id', title: 'ID', sort: true, hide:true} 
		              ,{title:'操作', toolbar: '#table-useradmin-webuser',fixed: 'left', width:150,align:'center'}
		      /*        ,{field:'cityName', width:88,title: '所属城市',fixed: 'left',align:'center',templet: function(d){
	                	    var codex =d.cityName;
		                	if(codex==null||codex==""){
		                		codex="北京市人民医院";
		                	}
		                    return '<span >'+ codex +'</span>'
	                  }}*/
		              ,{field:'insuranceCode', title: '参保号',fixed: 'left',width:160,align:'center'}  

		              //个人基础信息
		              ,{field:'name', title: '姓名',fixed: 'left',width:80,align:'center'}
		              //,{field:'idCardType',title: '证件类型',fixed: 'left', width:88,align:'center'}
		              ,{field:'idCard', title: '身份证号',fixed: 'left',width:175,align:'center'}	                          
		              ,{field:'sex', title: '性别',fixed: 'left' ,templet: function(d){
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
	                  },width:50,align:'center'}
		              ,{field:'birthday',title: '出生日期',width:110,align:'center'}   
		              
		              //个人详情
		              ,{field:'phone',width:130,align:'center', title: '联系电话'}
		              ,{field:'address', width:170,align:'center',title: '住址'}
		              ,{field:'email',width:170,align:'center', title: '电子邮箱'}  
		              ,{field:'nation',title: '民族', width:88,align:'center'}      
		              ,{field:'maritalStatus',title: '婚姻状态', width:90,align:'center' ,templet: function(d){
	                	    var codex =d.maritalStatus;
		                	if(codex=="1"){
		                		codex="已婚";
		                	}else if(codex=="0"){
		                		codex="未婚";
		                	}else if(codex==null){
		                		codex="未说明";
		                	}else{
		                		codex=codex;
		                	}
		                    return '<span >'+ codex +'</span>'
	                  }}
		              ,{field:'relocationSigns',width:90,align:'center', title: '异地安置' ,templet: function(d){
	                	    var codex =d.relocationSigns;
		                	if(codex=="1"){
		                		codex="异地";
		                	}else if(codex=="0"){
		                		codex="本地";
		                	}else{
		                		codex="";
		                	}
		                    return '<span >'+ codex +'</span>'
	                  }}      
		              ,{field:'householdRegist', title: '户口性质',width:90,align:'center'}        
		              ,{field:'householdAddress', title: '户口所在地',width:100,align:'center'}
		              ,{field:'postalCode', title: '邮政编码',width:90,align:'center'}
		             
		              //社会信息
		              ,{field:'personStatus', title: '个人状态',width:100,align:'center',templet: function(d){
	                	    var codex =d.personStatus;
		                	if(codex=="1"){
		                		codex="生存";
		                	}else{
		                		codex="";
		                	}
		                    return '<span >'+ codex +'</span>'
	                  }} 
		              ,{field:'health',width:90,align:'center', title: '健康状态'}
		              ,{field:'identity', title: '个人身份',width:90,align:'center'} 
		              ,{field:'education', title: '学历',width:90,align:'center'}  
		              ,{field:'survivalStatus', title: '生存状态',width:90,align:'center'}  
		              ,{field:'employmentStatus',width:100,align:'center', title: '就业状态'}
		              ,{field:'retirementSigns',width:90,align:'center', title: '离退休'}
		              ,{field:'workBeginTime',title: '参加工作时间',width:120,align:'center'}
		              ,{field:'technicalGrade',title: '专业技术职务等级', width:150,align:'center'}
		              ,{field:'nationCareerGrade',width:150,align:'center', title: '国家职业资格等级'}
		              ,{field:'administrativePost',width:100,align:'center', title: '行政职务'}  
		              ,{field:'farmerSigns',width:90,align:'center', title: '农民工',templet: function(d){
	                	    var codex =d.farmerSigns;
		                	if(codex=="0"){
		                		codex="否";
		                	}else if(codex=="1"){
		                		codex="是";
		                	}else{
		                		codex="";
		                	}
		                    return '<span >'+ codex +'</span>'
	                  }}
		              ,{field:'poorSigns',width:100,align:'center', title: '精准扶贫',templet: function(d){
	                	    var codex =d.poorSigns;
		                	if(codex=="0"){
		                		codex="否";
		                	}else if(codex=="1"){
		                		codex="是";
		                	}else{
		                		codex="";
		                	}
		                    return '<span >'+ codex +'</span>'
	                  }}
		              
		              //社保信息
		              ,{field:'insuranceBeginTime',width:110,align:'center', title: '参保日期'}
		              ,{field:'insuranceAttr', width:100,align:'center',title: '参保属性'}
		              ,{field:'insuranceType', width:100,align:'center',title: '险种类型'}
		              ,{field:'cityPostalCode', title: '统筹区邮政编码',width:130,align:'center'}		     
		              ,{field:'insuranceYear',width:130,align:'center', title: '连续参保年数'}
		              ,{field:'insuranceCardStatus',width:100,align:'center', title: '医保卡状态'}
		              ,{field:'insuranceCardChangeReason',width:130,align:'center', title: '状态变更原因'}
		              ,{field:'socialForm',width:130,align:'center', title: '社会化管理形式'}      
		              ,{field:'insuranceUnitsCode',width:120,align:'center', title: '参保单位编码'}
		              ,{field:'insuranceUnitsName',width:120,align:'center', title: '参保单位名称'}
		              ,{field:'insuranceUnitsInChareName', width:150,align:'center',title: '参保单位联系人'}
		              ,{field:'insuranceUnitsInCharePhone',width:160,align:'center', title: '参保单位联系人电话'}
		              ,{field:'comments',width:100,align:'center', title: '备注'}
	         
	            ]]
	            ,page: true
	          });
	    
	    //监听搜索
		form.on('submit(LAY-user-front-search)', function(data){
	    	var field = data.field;    	
	    	 console.info(field);
	        //执行重载
	        layui.table.reload('personTable', {
	            where: field
	        });
	    });
		
		//添加事件
	    var active = {
	      add: function(){
	    	  //参保信息新增
	    	    layer.open({
			          type: 2
			          ,title: '参保人信息新增'
			          ,content: $WEB_ROOT_PATH+'/insuredPerson/insuredPersonInfo'
			          ,maxmin: true
			          ,area: ['800px', '500px']
			          ,btn: ['确定', '取消']
	    	          ,success: function(layero, index){
	    	        	  var iframeWindow = window['layui-layer-iframe'+ index];
	    	        	  //加载select下拉option
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
			              var url=$WEB_ROOT_PATH+"/dhccApi/insuredPerson/insuredPerson/save";
			              //console.info(field);
			              $.post(url,field,function(result){	
			            	  var inFlag= result.inFlag; 
			            	  if(inFlag==2){
				                  layer.msg('该身份号已存在!');
			            		  return false;
			            	 }else if(inFlag==1){
			            		 layer.msg('新增成功!');
				            	  //后台成功后，静态更新表格中的数据
				                  table.reload('personTable'); //数据刷新
				                  layer.close(index); //关闭弹层
			            	 }else if(inFlag==3){
			            		 layer.msg('该参保号已存在!');
			            		 return false;
			            	 }else{
			            		 layer.msg('网络异常');
				            	  //后台成功后，静态更新表格中的数据
				                  table.reload('personTable'); //数据刷新
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
	    table.on('tool(personTable)', function(obj){
	      var data = obj.data;
	      if(obj.event === 'xiugai'){
	    	  //修改
	    	    layer.open({
			          type: 2
			          ,title: '参保人信息管理'
			          ,content: $WEB_ROOT_PATH+'/insuredPerson/insuredPersonInfo'
			          ,maxmin: true
			          ,area: ['800px', '500px']
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
			              var url=$WEB_ROOT_PATH+"/dhccApi/insuredPerson/insuredPerson/save";
			              $.post(url,field,function(result){
			            	  var inFlag= result.operateSuccess; 
			            	  if(inFlag==true){
			            		  layer.msg('修改成功!');
			            		  //后台成功后，静态更新表格中的数据
			            		  table.reload('personTable'); //数据刷新
			            		  layer.close(index); //关闭弹层

			            	  }else{
			            		  layer.msg('修改失败!');
			            		  //后台成功后，静态更新表格中的数据
			            		  table.reload('personTable'); //数据刷新
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
		            var url=$WEB_ROOT_PATH+"/dhccApi/insuredPerson/insuredPerson/delete";
		            $.post(url,{"insuredPerson.id":id},function(result){
		        	    table.reload('personTable');
		                layer.msg('已删除');
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