//初始化	 
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;
	    
	    

		 //加载统筹区下拉字典
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
	     			var nn="<option value='"+dataList[i].text+"'>"+dataList[i].text+"</option>";
	     			//$("#cityName").append(nn);
			     			$("#zyOrgName").append(nn); 
			     		}
			     	form.render('select');
		}); 
	    
		 //加载病情下拉列表
		 $.getJSON($WEB_ROOT_PATH+'/dhccApi/hosanalysis/hosanalysis/getConditionList', 
					function(data){
			 var  dataList= data;
	     		for(var i=0 ;i<dataList.length;i++){
	     			var nn="<option value='"+dataList[i].area+"'>"+dataList[i].area+"</option>";
	     			//$("#cityName").append(nn);
			     			$("#zyOrgName2").append(nn); 
			     		}
			     	form.render('select');
		}); 
	    
	    
	    
	    
	    function changeNumber(num){

	 	   if(num && num!=null){  
	 	        num = String(num);  
	 	        var left=num.split('.')[0],right=num.split('.')[1];  
	 	        right = right ? (right.length>=2 ? '.'+right.substr(0,2) : '.'+right+'0') : '.00';  
	 	        var temp = left.split('').reverse().join('').match(/(\d{1,3})/g);  
	 	        return (Number(num)<0?"-":"") + temp.join(',').split('').reverse().join('')+right;  
	 	    }else if(num===0){   //注意===在这里的使用，如果传入的num为0,if中会将其判定为boolean类型，故而要另外做===判断  
	 	        return '0.00';  
	 	    }else{  
	 	        return "";  
	 	    }  
	    };
	    
	    	 table.render({
	    	     elem: '#addressTable'
	            //,url: $WEB_ROOT_PATH+'/dhccApi/coststatistics/costStatistics/listVo'
	            ,url:$WEB_ROOT_PATH+'/dhccApi/hosanalysis/hosanalysis/getInhosDayByAreaList'
	            ,height: 295
				,limit:5
			
				,page:true
	            /*,even: true*/ //开启隔行背景
	            ,cols: [[
	            	   {type: 'numbers', title: '序号' }
		              ,{field:'area',title: '统筹区'}
		              ,{field:'pnumber', title: '平均住院天数',templet:function(a){
		            	  var codex = parseFloat(a.pnumber);
		            	  if(codex=="" || codex == null || codex == 0){
		            		  codex="-"
		            		 return  codex;
		            	  }
		            	  return changeNumber(codex);
		              }}
		            ]]
		          });
		
	    
	    	 table.render({
	    	     elem: '#doctorTable'
	            //,url: $WEB_ROOT_PATH+'/dhccApi/coststatistics/costStatistics/listVo'
	            ,url:$WEB_ROOT_PATH+'/dhccApi/hosanalysis/hosanalysis/getInhosDayByHosList'
	            ,height: 250
	            ,limit:5
	            ,page:true
	            ,cols: [[
	            	   {type: 'numbers', title: '序号' }
		              ,{field:'area', title: '医院名字'}
		              ,{field:'pnumber', title: '平均住院天数',templet:function(a){
		            	  var codex = parseFloat(a.pnumber);
		            	  if(codex=="" || codex == null || codex == 0||codex=='%'){
		            		  codex="-"
		            		 return  codex;
		            	  }
		            	  return changeNumber(codex);
		              }}
		              /*,{field:'prate', title: '占总比',templet:function(a){
		            	  var codex = a.prate;
		            	  if(codex=="" || codex == null || codex == 0||codex=='%'){
		            		  codex="-"
		            		 return  codex;
		            	  }
		            	  return codex;
		              }}*/
		            ]]
		          });
		
	    
	    
	   
	    	 table.render({
	    	     elem: '#condidtionTable'
	            //,url: $WEB_ROOT_PATH+'/dhccApi/coststatistics/costStatistics/listVo'
	            ,url:$WEB_ROOT_PATH+'/dhccApi/hosanalysis/hosanalysis/getConditionByConditionList'
	            ,height: 250
	            ,limit:5
	            ,page:true
	            ,cols: [[
	            	   {type: 'numbers', title: '序号' }
		              ,{field:'area', title: '诊断名'}
		              ,{field:'pnumber', title: '平均住院天数',templet:function(a){
		            	  var codex = parseFloat(a.pnumber);
		            	  if(codex=="" || codex == null || codex == 0||codex=='%'){
		            		  codex="-"
		            		 return  codex;
		            	  }
		            	  return changeNumber(codex);
		              }}
		              /*,{field:'prate', title: '占总比',templet:function(a){
		            	  var codex = a.prate;
		            	  if(codex=="" || codex == null || codex == 0||codex=='%'){
		            		  codex="-"
		            		 return  codex;
		            	  }
		            	  return codex;
		              }}*/
		            ]]
		        });
	    	 
		
	  //监听行单击事件
	    	 table.on('row(doctorTest)', function(obj){
		  	    	var name = obj.data.area;
		  	    
		  	    	var dataAxis3 =new Array();
					var strNumber3=new Array();
					$.ajax({
						url: $WEB_ROOT_PATH+"/dhccApi/hosanalysis/hosanalysis/getConditionByName?name="+name,
						type:"get",
						success:function(data1){
							var data = data1.data
							for(var index in data){
								if(index == 11 || index > 10) break;
								var money = data[index].pnumber;
								money = money == null ? 0 :money;
								money = money == ""  ? 0 : money; 
								dataAxis3[index] = data[index].area;
								strNumber3[index] = parseInt(parseFloat(money));
							}
							
							// 路径配置
							require.config({
								paths : {
									echarts : $WEB_ROOT_PATH
											+ '/js/echarts_jpp/echarts-2.2.7/build/dist'
								}
							});
							// 使用
							require([ 'echarts', 'echarts/chart/bar', // 使用柱状图就加载bar模块，按需加载,
							'echarts/chart/line' ], function(ec) {
								// 基于准备好的dom，初始化echarts图表
								var myChart = ec.init(document.getElementById('line3'));
						var	option = {
								   /*  color: ['#003366', '#006699', '#4cabce', '#e5323e'], */
								    tooltip: {
								        trigger: 'axis',
								        axisPointer: {
								            type: 'shadow'
								        }
								    },
								    legend: {
								        data: ['平均住院天数']
								    },
								    toolbox: {
								        show: true,
								        top: -12,
								        left:20,
								        feature: {
								            magicType: {show: true, type: ['line', 'bar']},
								            restore: {show: true}
								        }
								    },
								    calculable: true,
								    xAxis: [
								        {
								            type: 'category',
								            axisTick: {show: false},
								            data:dataAxis3
								            ,  axisLabel: {
					                            interval:0,
					                            rotate:16
					                         }
								        }
								    ],
								    yAxis: [
								        {
								            type: 'value'
								        }
								    ],
								    series: [
								        {
								            name: '平均住院天数',
								            type: 'bar',
								            data: strNumber3
								        }
								    ]
								};
							
								myChart.setOption(option);
							});
						
							}
						});
					var path =$WEB_ROOT_PATH+"/dhccApi/hosanalysis/hosanalysis/getConditionByNameList?name="+name;
					
					layui.table.reload('condidtionTable',{
						 url: path,
						 limit:5,
						 page:true
				     });
					
		  	    });
	    	 
	    	 
	    	//监听搜索
	 		form.on('submit(LAY-user-front-search)', function(data){
	 			var field = data.field;
	 			
	 			//执行重载
	 			layui.table.reload('addressTable', {
	 				where: field
	 			});
	 		});
	 		//监听搜索
	 		form.on('submit(LAY-user-front-search1)', function(data){
	 			var field = data.field;
	 			
	 			//执行重载
	 			layui.table.reload('doctorTable', {
	 				where: field
	 			});
	 		});
	 		//监听搜索
	 		form.on('submit(LAY-user-front-search2)', function(data){
	 			var field = data.field;
	 			//执行重载
	 			layui.table.reload('condidtionTable', {
	 				where: field
	 			});
	 		});
	  
	   /* 
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
	    		          ,content: $WEB_ROOT_PATH+'/dictmaintain/dictmaintainform'
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
	      if(obj.event === 'del'){
	    	//删除
	        layer.confirm('确定要删除该条数据？', function(index){
	        	//执行 Ajax 后重载
	            var url=$WEB_ROOT_PATH+"/dhccApi/dictmaintain/dictmaintain/delete";
	            $.post(url,{"dictmaintain.Id":data.id},function(result){
	        	    table.reload('userTable');
	                layer.msg('已删除');
	    	    });
	        });
	      } else if(obj.event === 'edit'){
	    	//修改方法
		    layer.open({
		          type: 2
		          ,title: '修改字典'
		          ,content: $WEB_ROOT_PATH+'/dictmaintain/dictmaintainform'
		          ,maxmin: true
		          ,area: ['500px', '450px']
		          ,btn: ['确定', '取消']
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
	       }*/
	   
	    
	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });