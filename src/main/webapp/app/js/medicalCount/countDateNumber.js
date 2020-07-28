document.getElementById("main").style.height=document.documentElement.clientHeight-122+"px";
document.getElementById("mainzu").style.height=document.documentElement.clientHeight-110+"px";
var monthData=['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'];
  		var totalData=new Array();
  		var basicData=new Array();
        // 基于准备好的dom，初始化echarts实例
        /* var dataAxis=new Array();
    	var data=new Array(); */
        $.ajax({
    	url : $WEB_ROOT_PATH+"/dhccApi/medicalCount/medicalCount/countDateNumber",
		type : "post",
		success : function(data) {	
			var data=data.data;
			for (var index in data) {
				totalData[index]=(data[index].totalCost);
				basicData[index]=(data[index].basicCostM);
			}
			var myChart = echarts.init(document.getElementById('main'));
			// 指定图表的配置项和数据
			option = {
						toolbox: {
									top:-5,
									right:150,
									show: true,
									feature: {
										mark:{show:false		},
										magicType: {
											type: ['line', 'bar'],
											show: true
										},
										dataZoom: {
											show: false
										},
										dataView: {
											show: false,
											readOnly:false
										},
										restore: {
											show: false	
										},
										saveAsImage: {
											show: false
										}
								}
							}
						,
					    title: {
					        text: ''
					    },
					    tooltip: {
					        trigger: 'axis',
					        axisPointer: {
					            type: 'shadow'
					        },
					       
					       
					    },
					    legend: {
					        data: ['总金额','统筹应支付']
					    },
					    grid: {
					        left: '3%',
					        right: '20%',
					        bottom: '10%',
					        top:'10%',
					        containLabel: true
					    },
					    xAxis: {
					    	type: 'category',
					        data: monthData,axisLabel: {  
					        	   interval:0,  
					        	   rotate:0  
					        	}  
					    },
					    yAxis: {
					    	type: 'value',
					    	 axisLabel: {  
		                            show: true,  
		                            interval: 'auto',  
		                            formatter: '{value} '  
		                            },  
		                        show: true  
					    },
					    series: [
					        {
					            name: '总金额',
					            type: 'bar',
					            data: totalData,
					             
					            	label: {
					            		normal: {
					                        show: false,
					                        position: 'top',
					                        formatter: '{c}'
					                    }

					            		    }
					            
					        },
					        {
					            name: '统筹应支付',
					            type: 'bar',
					            data: basicData,
					        	label: {
				            		normal: {
				                        show: false,
				                        position: 'top',
				                        formatter: '{c}'
				                    }

				            		    }
					        }
					    ]
					};
				myChart.setOption(option);
				
		},
		error : function(data) {
			alert("服务器错误");
		} 
    });
	
	
	//初始化	
	layui.config({
		base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	}).extend({
		index: 'lib/index' //主入口模块
	}).use(['index', 'table','laydate'], function(){
		var $ = layui.$
		,form = layui.form
		,table = layui.table
		,laydate=layui.laydate;
		laydate.render({
			  elem: '#createTime'
				  ,type:"year"
					  ,trigger:'click'
						 ,value: new Date() 
						   ,isInitValue:true
						      ,done:function(value){  
						    	  dateChange(value);
						      }
		  }); 
		function dateChange(value){
			 $.ajax({
			    	url : $WEB_ROOT_PATH+"/dhccApi/medicalCount/medicalCount/countDateNumber?inFlag="+value,
					type : "post",
					success : function(data) {	
						var data=data.data;
						for (var index in data) {
							totalData[index]=(data[index].totalCost);
							basicData[index]=(data[index].basicCostM);
						}
						var myChart = echarts.init(document.getElementById('main'));
						// 指定图表的配置项和数据
						option = {
									toolbox: {
												top:-5,
												right:150,
												show: true,
												feature: {
													mark:{show:false		},
													magicType: {
														type: ['line', 'bar'],
														show: true
													},
													dataZoom: {
														show: false
													},
													dataView: {
														show: false,
														readOnly:false
													},
													restore: {
														show: false	
													},
													saveAsImage: {
														show: false
													}
											}
										}
									,
								    title: {
								        text: '金额统计'
								    },
								    tooltip: {
								        trigger: 'axis',
								        axisPointer: {
								            type: 'shadow'
								        },
								       
								       
								    },
								    legend: {
								        data: ['总金额','统筹应支付']
								    },
								    grid: {
								        left: '3%',
								        right: '20%',
								        bottom: '10%',
								        top:'10%',
								        containLabel: true
								    },
								    xAxis: {
								    	type: 'category',
								        data: monthData,axisLabel: {  
								        	   interval:0,  
								        	   rotate:0  
								        	}  
								    },
								    yAxis: {
								    	type: 'value',
								    	 axisLabel: {  
					                            show: true,  
					                            interval: 'auto',  
					                            formatter: '{value} '  
					                            },  
					                        show: true  
								    },
								    series: [
								        {
								            name: '总金额',
								            type: 'bar',
								            data: totalData,
								             
								            	label: {
								            		normal: {
								                        show: false,
								                        position: 'top',
								                        formatter: '{c}'
								                    }

								            		    }
								            
								        },
								        {
								            name: '统筹应支付',
								            type: 'bar',
								            data: basicData,
								        	label: {
							            		normal: {
							                        show: false,
							                        position: 'top',
							                        formatter: '{c}'
							                    }

							            		    }
								        }
								    ]
								};
							myChart.setOption(option);
							
					},
					error : function(data) {
						alert("服务器错误");
					} 
			    });
				
		}

		
		//按钮事件绑定底层方法-勿动
		$('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});
	});
