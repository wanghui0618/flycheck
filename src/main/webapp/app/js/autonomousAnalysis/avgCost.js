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
	 	   option = {
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
		        		 option.series[0].data.push(hvdata[i].avgCost);
		                 option.xAxis[0].data.push(hvdata[i].orgName);
		             } 
		        		myChart.setOption(option,true); 
		        
		         }
     });
  }
  function yearChange3()
  {
	  var str = $("#findYear5 option:selected").val();
	  changeTable3(str);
  }