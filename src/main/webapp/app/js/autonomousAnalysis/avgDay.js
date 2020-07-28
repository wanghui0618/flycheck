  function yearChange2()
  {
	  var str = $("#findYear option:selected").val();
	  changeTable(str);
	  changeTable2(str);
	  changeTable3(str);
  }
  function changeTable(obj){
	  require.config({
	         paths: {
	             echarts: $WEB_ROOT_PATH+'/js/echarts_jpp/echarts-2.2.7/build/dist'
	         }
	     });
		   // 使用
	  require(
	      [
	          'echarts',
	          'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
	      ],
	      function (ec) {
	          // 基于准备好的dom，初始化echarts图表
	    	  var myChart = echarts.init(document.getElementById('main1'));
	 	     // 指定图表的配置项和数据
	 	    option = {
	 	    tooltip: {
	 	        trigger: 'axis',
	 	        axisPointer: {
	 	            type: 'cross',
	 	            crossStyle: {
	 	                color: '#999'
	 	            }
	 	        }
	 	    },
	 	    legend: {	
	 	        data:['住院费用','门诊费用','住院费用比率','门诊费用比率']
	 	    },
	 	    xAxis: [
	 	        {
	 	            type: 'category',
	 	            data: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
	 	            axisPointer: {
	 	                type: 'shadow'
	 	            }
	 	        }
	 	    ],
	 	    yAxis: [
	 	        {
	 	            /*type: 'value',
	 	            name: '费用',
	 	            min: 0,*/
	 	           
	 	            axisLabel: {
	 	                formatter: '{value} 万元'
	 	            }
	 	        },
	 	        {
	 	            type: 'value',
	 	            name: '比率',
	 	            min: 0,
	 	            max: 100,
	 	            interval: 10,
	 	            axisLabel: {
	 	                formatter: '{value} %'
	 	            }
	 	        }
	 	    ],
	 	    series: [
	 	        {
	 	            name:'住院费用',
	 	            type:'bar',
	 	            data:[]
	 	        },
	 	        {
	 	            name:'门诊费用',
	 	            type:'bar',
	 	            data:[]
	 	        },
	 	       
	 	        {
	 	            name:'住院费用比率',
	 	            type:'line',
	 	            yAxisIndex: 1,
	 	            data:[]
	 	        },
	 	        
	 	        {
	 	            name:'门诊费用比率',
	 	            type:'line',
	 	            yAxisIndex: 1,
	 	            data:[]
	 	        }
	 	    ]
	 	};

	      }
	  );
	 // alert(obj);4
	  $.ajax({
  	     url:$WEB_ROOT_PATH+'/dhccApi/medicalAnalysis/medicalAnalysis/monthlyTrendsData',
  	     
		         type : "post",		
		         async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
		         dataType : "json",
		         data:{inFlag:obj},
		         success : function(result) {
		        	 var hvdata=result.data;
		        	 var myChart = echarts.init(document.getElementById('main1'));
		        	 //myChart.clear();
		        	 //document.getElementById('main1').removeAttribute('_echarts_instance_');
		        	 //alert(hvdata);
		        	 //console.log(hvdata);
		        	 var index,i=0;
		        	 	for(index=1;index<=12;index++){
		        	 		if(i<hvdata.length && index==parseInt(hvdata[i].monthTime)){
		        	 			option.series[0].data.push(hvdata[i].inHospital);
		        	 			option.series[1].data.push(hvdata[i].outCost);
		        	 			option.series[2].data.push(hvdata[i].inCost);
		        	 			option.series[3].data.push(hvdata[i].costRatio);
		        	 			i++;
		        	 		}
		        	 		else{
		        	 			option.series[0].data.push(0+0);
		        	 			option.series[1].data.push(0+0);
		        	 			option.series[2].data.push(0+0);
		        	 			option.series[3].data.push(0+0);
		        	 		}
		        	 		//option.series[0].data.push("1");
		        	 		//option.series[1].data.push("2");
		        	 	}
		        		myChart.setOption(option,true); 
		        
		         }
     });
  }
  function changeTable2(obj){
	  require.config({
	         paths: {
	             echarts: $WEB_ROOT_PATH+'/js/echarts_jpp/echarts-2.2.7/build/dist'
	         }
	     });
		   // 使用
	  require(
	      [
	          'echarts',
	          'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
	      ],
	      function (ec) {
	          // 基于准备好的dom，初始化echarts图表
	    	  var myChart = echarts.init(document.getElementById('main2'));
	 	     // 指定图表的配置项和数据
	 	   option1 = {
	 			  tooltip : {
      		        trigger: 'axis',
      		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
      		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
      		        }
      		    },
      		    grid:{
      		    	x:70,
      		    	y:20,
      		    	x2:50,
      		    	y2:60
                  },
            xAxis : [
                {
                    type : 'category',
                    axisLabel: {    
                  	  interval:0,
                        rotate:15,
                        textStyle: {
                      	  fontSize : 8,
                        }
                        
                     } ,
                    data : [],
                     //data: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
                    axisTick: {
                        alignWithLabel: true
                    }
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:"平均住院天数",
                    type:"bar",
                    barWidth:16,
                    data:[],
                    itemStyle:{
                        normal:{
                            color:'#319CFE'
                        }
                    }
                }
            ]
	 	};

	      }
	  );
	 // alert(obj);4
	  $.ajax({
  	     url:$WEB_ROOT_PATH+'/dhccApi/medicalAnalysis/medicalAnalysis/findHosNumber',
  	     
		         type : "post",		
		         async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
		         dataType : "json",
		         data:{inFlag:obj},
		         success : function(result) {
		        	 var hvdata=result.data;
		        	 var myChart = echarts.init(document.getElementById('main2'));
		        	 //myChart.clear();
		        	 //document.getElementById('main1').removeAttribute('_echarts_instance_');
		        	 //alert(hvdata);
		        	 //console.log(hvdata);
		        	 var index = hvdata.length>10 ? 10 : hvdata.length
		        	 for(var i=0;i<index;i++){ 
	        			 //alert(hvdata[i].name);
		        		 option1.series[0].data.push(hvdata[i].avgDay);
		                 option1.xAxis[0].data.push(hvdata[i].orgName);
		             } 
		        		myChart.setOption(option1,true); 
		        
		         }
     });
  }
  function changeTable3(obj){
	  require.config({
	         paths: {
	             echarts: $WEB_ROOT_PATH+'/js/echarts_jpp/echarts-2.2.7/build/dist'
	         }
	     });
		   // 使用
	  require(
	      [
	          'echarts',
	          'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
	      ],
	      function (ec) {
	          // 基于准备好的dom，初始化echarts图表
	    	  var myChart = echarts.init(document.getElementById('main41'));
	 	     // 指定图表的配置项和数据
	 	   option2 = {
	 			  tooltip : {
      		        trigger: 'axis',
      		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
      		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
      		        }
      		    },
      		    grid:{
      		    	x:70,
      		    	y:20,
      		    	x2:50,
      		    	y2:60
                  },
            xAxis : [
                {
                    type : 'category',
                    axisLabel: {    
                  	  interval:0,
                        rotate:15,
                        textStyle: {
                      	  fontSize : 8,
                        }
                        
                     } ,
                    data : [],
                     //data: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
                    axisTick: {
                        alignWithLabel: true
                    }
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:"平均住院费用",
                    type:"bar",
                    barWidth:16,
                    data:[],
                    itemStyle:{
                        normal:{
                            color:'#319CFE'
                        }
                    }
                }
            ]
	 	};

	      }
	  );
	 // alert(obj);4
	  $.ajax({
  	     url:$WEB_ROOT_PATH+'/dhccApi/medicalAnalysis/medicalAnalysis/findMonNumber',
  	     
		         type : "post",		
		         async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
		         dataType : "json",
		         data:{inFlag:obj},
		         success : function(result) {
		        	 var hvdata=result.data;
		        	 var myChart = echarts.init(document.getElementById('main41'));
		        	 //myChart.clear();
		        	 //document.getElementById('main1').removeAttribute('_echarts_instance_');
		        	 //alert(hvdata);
		        	 //console.log(hvdata);
		        	 var index = hvdata.length>10 ? 10 : hvdata.length
		        	 for(var i=0;i<index;i++){ 
	        			 //alert(hvdata[i].name);
		        		 option2.series[0].data.push(hvdata[i].avgCost);
		                 option2.xAxis[0].data.push(hvdata[i].orgName);
		             } 
		        		myChart.setOption(option2,true); 
		        
		         }
     });
  }