
layui.config({
    base: $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table','element'], function () {
    var $ = layui.$
        , form = layui.form
        , element = layui.element
        , table = layui.table;
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
    	table.render({
   	     elem: '#condidtionTable'
	            //,url: $WEB_ROOT_PATH+'/dhccApi/coststatistics/costStatistics/listVo'
	            ,url:$WEB_ROOT_PATH+'/dhccApi/hosanalysis/hosanalysis/getConditionByZYConditionList3'
	            ,cellMinWidth: 80
	            ,limit:10
	            ,height: document.documentElement.clientHeight-80,
	            page:true
	            ,cols: [[
	            	   {type: 'numbers', title: '序号' }
	            	   ,{field:'area',title: '病情'}
			              /*,{field:'pnumber', title: '住院次数',templet:function(a){
			            	  var codex = a.pnumber;
			            	  if(codex == "" || codex == null || codex == 0 || codex == "%"){
			            		  codex="-";
			            	  }
			            	  return codex;
			              }}*/
			              /*,{field:'pcost',title: '住院费用',templet:function(a){
			            	  var codex = a.pcost;
			            	  if(codex == "" || codex == null || codex == 0 || codex == "%"){
			            		  codex="-";
			            	  }
			            	  return changeNumber(parseFloat(codex));
			              }}*/
			              ,{field:'pnumber',title: '住院次均费用',templet:function(a){
			            	  var codex = a.pnumber;
			            	  if(codex == "" || codex == null || codex == 0 || codex == "%"){
			            		  codex="-";
			            	  }
			            	  return changeNumber(parseFloat(codex));
			              }}
			              ,{field:'cvnum',title: 'CV' }
			              /*,{field:'prate',title: '费用占比',templet:function(a){
			            	  var codex = a.prate;
			            	  if(codex == "" || codex == null || codex == 0 || codex == "%"){
			            		  codex="-";
			            	  }
			            	  return codex;
			              }}*/
		            ]]
		          });
    	form.on('submit(LAY-user-front-search2)', function(data){
    		//console.log(data.field);
 			var field = data.field;
 			//console.log(field+"123456478");
 			test(field);
 			//执行重载
 			layui.table.reload('condidtionTable', {
 				page:{
 	                curr:1  //从第一页开始
 	            },
 				where: field
 			});
 		}); 
    	function test(obj){
    		document.getElementById('mainTwo').style.height = document.documentElement.clientHeight-80 + "px";
    		var illName = obj.illName;
    		var class1 = obj.beiShu;
    		//console.log(obj.data);
    		//console.log(class1+"999")
    		$.ajax({
   			 url:$WEB_ROOT_PATH+'/dhccApi/medicalAnalysis/medicalAnalysis/abnormalCost2',
   			 //url:$WEB_ROOT_PATH+'/dhccApi/medicalAnalysis/medicalAnalysis/monthlyTrendsData',
         	  	 type : "post",		
     	         async : false,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
     	         dataType : "json",
     	        data:{illName:illName,class1:class1},
     	         success : function(result) {
		        	 var hvdata=result.data;
		        	 //console.log(hvdata);
		        	 var mon = parseFloat(hvdata[0].tCsot);
		        	 require.config({
					        paths: {
					            echarts: $WEB_ROOT_PATH+'/js/echarts_jpp/echarts-2.2.7/build/dist'
					        }
					    });
		        	 require(
		    			     [
		    			         'echarts',
		    			         'echarts/chart/scatter' ,
		    			         'echarts/chart/bar'
		    			     ],
		    			     function (ec) {
		    			         var myChart = ec.init(document.getElementById('mainTwo')); 
		    			         //,height: document.documentElement.clientHeight-80;
		    			         var option = {
		    			 			    title : {
		    						        text: '住院费用',
		    						       
		    						    },
		    						    tooltip : {
		    						        trigger: 'axis',
		    						        showDelay : 0,
		    						        formatter : function (params) {
		    						            if (params.value.length > 1) {
		    						            	 return params.seriesName + ' :<br/>'
			 						                   + params.value[1] + '元' +'<br/>'
			 						                   + params.value[3] +'<br/>'
			 						                   + params.value[2];
		    						                   //+ params.value[1] + '元';
		    						            }
		    						            else {
		    						                return params.seriesName + ' :<br/>'
		    						                   + params.name + ' : '
		    						                   + params.value + ' ';
		    						            }
		    						        },  
		    						        axisPointer:{
		    						            show: true,
		    						            type : 'cross',
		    						            lineStyle: {
		    						                type : 'dashed',
		    						                width : 1
		    						            }
		    						        }
		    						    },
		    						    legend: {
		    						        data:['费用']
		    						    },
		    						    /*toolbox: {
		    						        show : true,
		    						        feature : {
		    						            mark : {show: true},
		    						            dataZoom : {show: true},
		    						            dataView : {show: true, readOnly: false},
		    						            restore : {show: true},
		    						            saveAsImage : {show: true}
		    						        }
		    						    },*/
		    						    xAxis : [
		    						        {
		    						            type : 'value',
		    						            data: [1,2,3,4,5,6,7,8,9,10,11,12],
		    						            axisLabel : {
		    						                formatter: '{value} 月'
		    						            }
		    						        }
		    						    ],
		    						    yAxis : [
		    						        {
		    						            type : 'value',
		    						            name:'费用',
		    						            data: [],
		    						        axisLabel : {
		    					                formatter: '{value} 元'
		    					            }
		    						        }
		    						    ],
		    						    series : [
		    						        
		    						        {
		    						            name:'费用',
		    						            type:'scatter',
		    						            symbolSize: 10,
		    						            data: [
		    						            ],
		    						            /*markPoint : {
		    						               /* data : [
		    						                    {type : 'max', name: '最大值'},
		    						                    {type : 'min', name: '最小值'}
		    						                ]
		    						            },*/
		    						            /*symbolSize: function (dataItem) {
		    						                return dataItem[1] * 0.00002;
		    						            },*/
		    						            markLine : {
		    						            	itemStyle:{
		    						            		normal:{
		    						            			label:{
		    						            				formatter :'住院次均费用',
		    						            			}
		    						            		}
		    						            	},
		    						                data : [
		    						                	//{type : 'average', name: '平均值'},
		    						                	//{name:'ggg',yAxis:25000}
		    						                	//{type : 'average*3', name: '平均值'},
		    						                	//{type : 'average/3', name: '平均值'}
		    						                	[ 
		    						                		{ name: mon+'元', xAxis: 0, yAxis: mon},
		    						                        { xAxis: 12, yAxis: mon },
		    						                    ],
		    						                ]
		    						            },
		    						        }
		    						    ]
		    						};
		    			         //console.log(option);
		    			         var i = 0;
		    			         var data2 = [0,mon,'平均费用',''];
		    			         option.series[0].data.push(data2);
		    			         var bei = document.getElementById("gggg").value;
		    			         var name = document.getElementById("hahaha").value;
		    			         //var name = document.getElementById("zyOrgName2").value;
		    			         //console.log(name);
		    			         //console.log(bei+"----");
		    			         for(i = 0; i < hvdata.length;i++){
		    			        	 var data1 = [];
		    			        	 if(parseFloat(hvdata[i].totalCost)*bei <= mon || parseFloat(hvdata[i].totalCost)/bei >= mon){
		    			        		 data1.push(parseInt(hvdata[i].monthTime));
			    			        	 data1.push(parseFloat(hvdata[i].totalCost));
			    			        	 data1.push(hvdata[i].orgName);
			    			        	 data1.push(hvdata[i].yearData);
			    			        	 	//console.log(data1);
			    			        	 option.series[0].data.push(data1);
			    			        	 //console.log(parseFloat(hvdata[i].totalCost));
		    			        	 }
		    			        	 
		    			        	//console.log(option.series[0].data);
		    			         }
		    			         //myChart = layui.echarts.init($('#mainTwo')[0]);
		    			         myChart.setOption(option); 
		    			     	}
		    			     );
		         }
   		});
    	}
    	table.on('row(test)', function(obj){
    		var illName = obj.data.area;
    		var mon = Math.floor(parseFloat(obj.data.pnumber)*100)/100;   //处理后端数据调整为两位小数
    		document.getElementById('mainTwo').style.height = document.documentElement.clientHeight-80 + "px";
    		//alert(illName);
    		//console.log(mon);
    		//var data1 = [];
    		$.ajax({
    			 url:$WEB_ROOT_PATH+'/dhccApi/medicalAnalysis/medicalAnalysis/abnormalCost2',
    			 //url:$WEB_ROOT_PATH+'/dhccApi/medicalAnalysis/medicalAnalysis/monthlyTrendsData',
          	  	 type : "post",		
      	         async : false,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
      	         dataType : "json",
      	         data:{illName:illName},
      	         success : function(result) {
		        	 var hvdata=result.data;
		        	 //console.log(hvdata);
		        	 require.config({
					        paths: {
					            echarts: $WEB_ROOT_PATH+'/js/echarts_jpp/echarts-2.2.7/build/dist'
					        }
					    });
		        	 require(
		    			     [
		    			         'echarts',
		    			         'echarts/chart/scatter' ,
		    			         'echarts/chart/bar'
		    			     ],
		    			     function (ec) {
		    			         var myChart = ec.init(document.getElementById('mainTwo')); 
		    			         //,height: document.documentElement.clientHeight-80;
		    			         var option = {
		    			 			    title : {
		    						        text: '住院费用',
		    						       
		    						    },
		    						    tooltip : {
		    						        trigger: 'axis',
		    						        showDelay : 0,
		    						        formatter : function (params) {
		    						            if (params.value.length > 1) {
		    						                return params.seriesName + ' :<br/>'
		 						                   + params.value[1] + '元' +'<br/>'
		 						                  + params.value[3]  +'<br/>'
		 						                   + params.value[2];
		    						                   //+ params.value[1] + '元';
		    						            }
		    						            else {
		    						                return params.seriesName + ' :<br/>'
		    						                   + params.name + ' : '
		    						                   + params.value + ' ';
		    						            }
		    						        },  
		    						        /*axisPointer:{
		    						            show: true,
		    						            type : 'cross',
		    						            lineStyle: {
		    						                type : 'dashed',
		    						                width : 1
		    						            }
		    						        }*/
		    						    },
		    						    legend: {
		    						        data:['费用']
		    						    },
		    						    /*toolbox: {
		    						        show : true,
		    						        feature : {
		    						            mark : {show: true},
		    						            dataZoom : {show: true},
		    						            dataView : {show: true, readOnly: false},
		    						            restore : {show: true},
		    						            saveAsImage : {show: true}
		    						        }
		    						    },*/
		    						    xAxis : [
		    						        {
		    						            type : 'value',
		    						            data: [1,2,3,4,5,6,7,8,9,10,11,12],
		    						            axisLabel : {
		    						                formatter: '{value} 月'
		    						            }
		    						        }
		    						    ],
		    						    yAxis : [
		    						        {
		    						            type : 'value',
		    						            name:'费用',
		    						            data: [],
		    						        axisLabel : {
		    					                formatter: '{value} 元'
		    					            }
		    						        }
		    						    ],
		    						    series : [
		    						        
		    						        {
		    						            name:'费用',
		    						            type:'scatter',
		    						            symbolSize: 10,
		    						            data: [
		    						            ],
		    						            /*markPoint : {
		    						               /* data : [
		    						                    {type : 'max', name: '最大值'},
		    						                    {type : 'min', name: '最小值'}
		    						                ]
		    						            },*/
		    						            /*symbolSize: function (dataItem) {
		    						                return dataItem[1] * 0.00002;
		    						            },*/
		    						            markLine : {
		    						            	itemStyle:{
		    						            		normal:{
		    						            			label:{
		    						            				formatter :'住院次均费用',
		    						            			}
		    						            		}
		    						            	},
		    						                data : [
		    						                	//{type : 'average', name: '平均值'},
		    						                	//{name:'ggg',yAxis:25000}
		    						                	//{type : 'average*3', name: '平均值'},
		    						                	//{type : 'average/3', name: '平均值'}
		    						                	[ 
		    						                		{ name: mon+'元', xAxis: 0, yAxis: mon},
		    						                        { xAxis: 12, yAxis: mon },
		    						                    ],
		    						                ]
		    						            },
		    						        }
		    						    ]
		    						};
		    			         //console.log(option);
		    			         var i = 0;
		    			         var data2 = [0,mon,'平均费用',''];
		    			         option.series[0].data.push(data2);
		    			         var bei = document.getElementById("gggg").value;
		    			         var name = document.getElementById("hahaha").value;
		    			         //console.log(name);
		    			         //console.log(bei+"----");
		    			         for(i = 0; i < hvdata.length;i++){
		    			        	 var data1 = [];
		    			        	 if(parseFloat(hvdata[i].totalCost)*bei <= mon || parseFloat(hvdata[i].totalCost)/bei >= mon){
		    			        		 data1.push(parseInt(hvdata[i].monthTime));
			    			        	 data1.push(parseFloat(hvdata[i].totalCost));
			    			        	 data1.push(hvdata[i].orgName);
			    			        	 data1.push(hvdata[i].yearData);
			    			        	 	//console.log(data1);
			    			        	 option.series[0].data.push(data1);
			    			        	 //console.log(parseFloat(hvdata[i].totalCost));
		    			        	 }
		    			        	 
		    			        	//console.log(option.series[0].data);
		    			         }
		    			         //myChart = layui.echarts.init($('#mainTwo')[0]);
		    			         myChart.setOption(option); 
		    			     	}
		    			     );
		         }
    		});
    	});
  //按钮事件绑定底层方法-勿动
//    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function () {
//        var type = $(this).data('type');
//        //active[type] ? active[type].call(this) : '';
//    });
});