//初始化	
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table','echarts'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;
	    
	    
	 
	    
	    table.render({
	    	elem: '#userTable'
	            ,url: $WEB_ROOT_PATH+'/dhccApi/bigdata/bigdata/AverageNumber'
	            ,cellMinWidth: 80
	             ,height: 485
	            ,cols: [[
	            	  {type: 'numbers',width:100, title: '序号' }
		              ,{field:'id', title: 'ID', sort: true, hide:true}
		              ,{field:'pname', title: '疾病名称',width:280,align:'center'}
		              ,{field:'pnumber', title: '平均住院天数',width:160,align:'center'}
		              ,{field:'cvNumber', title: 'CV',width:100,align:'center'}
		            ]]
		    		,page: true
		          });
		form.on('submit(LAY-org-front-search)', function(data){
	    	var field = data.field;
	    	var pname =field.inFlag;
	    	if(pname==undefined){
	    		pname="";
	    	}
	    	var number =field.number;
	    	if(number=='' || number==null){
	    		number=3;
	    	}
	   	 var reg = /^[1-9]$/;
    	 if(!reg.test(number) || number==0){
    		 layer.msg('请输入1到9的数字!');
    		 return;
    	 }
    	 //重新加载table的图像
 	    $.getJSON($WEB_ROOT_PATH+'/dhccApi/bigdata/bigdata/AverageNumber?inFlag='+pname+'&number='+number,
				function(data){
 	    	var name;
 	    	var pnumber;
 	   	if(data.data.length==0){
    		 name ='wqrwetw431234t5';
    	}else{
  	    name =data.data[0].pname;
   	    pnumber =data.data[0].pnumber;}
	        option = {
				    title : {
				        text: '住院信息',
				    },
				    tooltip : {
				        trigger: 'axis',
				        showDelay : 0,
				        formatter : function (params) {
				        	var DataNumber =[];
				    		var aa=",";
				            if (params.value.length > 1) {
				            	if(params.value[2]=='平均' && params.value[3]=='结算日期'){
				            		  return params.value[2] +'住院'
					                   + params.value[1] + '天'+' <br/>';
					                
				            	}
				            	  return params.seriesName + ' :<br/>'
				                    + params.value[2] +'住院'
				                   + params.value[1] + '天'+' <br/>'
				                   +'结算日期：'+ params.value[3] ;
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
				        data:['住院信息']
				    },
				    toolbox: {
				        show : true,
				        feature : {
				            mark : {show: false},
				            dataZoom : {show: false},
				            dataView : {show: false, readOnly: false},
				            restore : {show: true},
				            saveAsImage : {show: false}
				        }
				    },
				    xAxis : [
				        {
				            type : 'value',
				            data: [],
				            axisLabel : {
				                formatter: '{value}月'
				            }
				        }
				    ],
				    yAxis : [
				        {
				            type : 'value',
				            name:'住院信息',
				            data: [],
				        axisLabel : {
			                formatter: '{value}天 '
			            }
				        }
				    ],
				    series : [
				        
				        {
				            name:'住院信息',
				            type:'scatter',
				            symbolSize: 10,
				            data: [],
				            markPoint : {
				                data : [
				                /*    {type : 'max', name: '最大值'},
				                    {type : 'min', name: '最小值'}*/
				                ]
				            },
				            markLine : {
				                data : [
				                	[
				                	{name: '住院平均天数', xAxis: 0, yAxis: pnumber},
				                	{name: '', xAxis: 12, yAxis: pnumber}
				                	]
				                ]
				            }
				            
				        }
				    ]
				};
 	    	if( name =='wqrwetw431234t5'){
					myChart = layui.echarts.init($('#main')[0]);
			        myChart.setOption(option);
 	    	}else{
			$.ajax({
				url: $WEB_ROOT_PATH+'/dhccApi/bigdata/bigdata/OutDate?inFlag='+name,
				type:"get",
				success:function(data){
					for(i = 0; i < data.length;i++){
						if(data[i].pnumber>= pnumber*number || data[i].pnumber<= Math.ceil(pnumber/number)){
							var value=new Array();
							value.push(data[i].personNumber);
							value.push(data[i].pnumber);
							value.push(data[i].pname);
							value.push(data[i].pdate);
							option.series[0].data.push(value)
						}
					}
					var aaa=new Array();
					aaa.push("");
					aaa.push(pnumber);
					aaa.push("平均");
					aaa.push("结算日期");
					option.series[0].data.push(aaa);
					myChart = layui.echarts.init($('#main')[0]);
			        myChart.setOption(option);
				   }
			
				});
 	    	}
			});
	        layui.table.reload('userTable', {
	            where: field
	        });
	    });
		//默认初始值
	    $.getJSON($WEB_ROOT_PATH+'/dhccApi/bigdata/bigdata/AverageNumber',
				function(data){
	    	var name =data.data[0].pname;
	    	var pnumber =data.data[0].pnumber;
	        option = {
				    title : {
				        text: '住院信息',
				    },
				    tooltip : {
				        trigger: 'axis',
				        showDelay : 0,
				        formatter : function (params) {
				        	var DataNumber =[];
				    		var aa=",";
				            if (params.value.length > 1) {
				             	if(params.value[2]=='平均' && params.value[3]=='结算日期'){
				            		  return params.value[2] +'住院'
					                   + params.value[1] + '天'+' <br/>';
					                
				            	}
				            	  return params.seriesName + ' :<br/>'
				                    + params.value[2] +'住院'
				                   + params.value[1] + '天'+' <br/>'
				                   +'结算日期：'+ params.value[3] ;
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
				        data:['住院信息']
				    },
				    toolbox: {
				        show : true,
				        feature : {
				            mark : {show: false},
				            dataZoom : {show: false},
				            dataView : {show: false, readOnly: false},
				            restore : {show: true},
				            saveAsImage : {show: false}
				        }
				    },
				    xAxis : [
				        {
				            type : 'value',
				            data: [],
				            axisLabel : {
				                formatter: '{value}月'
				            }
				        }
				    ],
				    yAxis : [
				        {
				            type : 'value',
				            name:'住院信息',
				            data: [],
				        axisLabel : {
			                formatter: '{value}天 '
			            }
				        }
				    ],
				    series : [
				        
				        {
				            name:'住院信息',
				            type:'scatter',
				            symbolSize: 10,
				            data: [],
				            markPoint : {
				                data : [
				                /*    {type : 'max', name: '最大值'},
				                    {type : 'min', name: '最小值'}*/
				                ]
				            },
				            markLine : {
				                data : [
				                	[
				                	{name: '', xAxis: 0, yAxis: pnumber},
				                	{name: '', xAxis: 12, yAxis: pnumber}
				                	]
				                ]
				            }
				            
				        }
				    ]
				};
			$.ajax({
				url: $WEB_ROOT_PATH+'/dhccApi/bigdata/bigdata/OutDate?inFlag='+name,
				type:"get",
				success:function(data){
					for(i = 0; i < data.length;i++){
						if(data[i].pnumber>= pnumber*3 || data[i].pnumber<= Math.ceil(pnumber/3)){
							var value=new Array();
							value.push(data[i].personNumber);
							value.push(data[i].pnumber);
							value.push(data[i].pname);
							value.push(data[i].pdate);
							option.series[0].data.push(value)
						}
					}
					var aaa=new Array();
					aaa.push(0);
					aaa.push(pnumber);
					aaa.push("平均");
					aaa.push("结算日期");
					option.series[0].data.push(aaa);
					myChart = layui.echarts.init($('#main')[0]);
			        myChart.setOption(option);
				   }
			
				});
			});
	    
	    table.on('row(userTable)', function(obj){
	    	var name = obj.data.pname;
	    	var pnumber =obj.data.pnumber;
	    	var aa =document.getElementById("value").value;
	    	if(aa=='' || aa==null){
	    		aa=3;
	    	}
	   	    var reg = /^[1-9]$/;
	    	 if(!reg.test(aa) || aa==0 ){
	    		 layer.msg('请输入1到9的数字!');
	    		 return;
	    	 }
		    option = {
				    title : {
				        text: '住院分析',
				    },
				    tooltip : {
				        trigger: 'axis',
				        showDelay : 0,
				        formatter : function (params) {
				        	var DataNumber =[];
				    		var aa=",";
				            if (params.value.length > 1) {
				             	if(params.value[2]=='平均' && params.value[3]=='结算日期'){
				            		  return params.value[2] +'住院'
					                   + params.value[1] + '天'+' <br/>';
					                
				            	}
				            	  return params.seriesName + ' :<br/>'
				                    + params.value[2] +'住院'
				                   + params.value[1] + '天'+' <br/>'
				                   +'结算日期：'+ params.value[3] ;
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
				        data:['住院信息']
				    },
				    toolbox: {
				        show : true,
				        feature : {
				            mark : {show: false},
				            dataZoom : {show: false},
				            dataView : {show: false, readOnly: false},
				            restore : {show: true},
				            saveAsImage : {show: false}
				        }
				    },
				    xAxis : [
				        {
				            type : 'value',
				            data: [],
				            axisLabel : {
				                formatter: '{value}月'
				            }
				        }
				    ],
				    yAxis : [
				        {
				            type : 'value',
				            name:'住院信息',
				            data: [],
				        axisLabel : {
			                formatter: '{value}天'
			            }
				        }
				    ],
				    series : [
				        
				        {
				            name:'住院信息',
				            type:'scatter',
				            symbolSize: 10,
				            data: [],
				            markPoint : {
				                data : [
				                /*    {type : 'max', name: '最大值'},
				                    {type : 'min', name: '最小值'}*/
				                ]
				            } ,
				            markLine : {
				                data : [
				                	[
					                	{name: '', xAxis: 0, yAxis: pnumber},
					                	{name: '', xAxis: 12, yAxis: pnumber}
					                	]
				                ]
				            } 
				            
				        }
				    ]
				};
			$.ajax({
				url: $WEB_ROOT_PATH+'/dhccApi/bigdata/bigdata/OutDate?inFlag='+name,
				type:"get",
				success:function(data){
					for(i = 0; i < data.length;i++){
						if(data[i].pnumber>= pnumber*aa || data[i].pnumber<= Math.ceil(pnumber/aa)){
							var value=new Array();
							value.push(data[i].personNumber);
							value.push(data[i].pnumber);
							value.push(data[i].pname);
							value.push(data[i].pdate);
							option.series[0].data.push(value)
						}
						
					}
					var aaa=new Array();
					aaa.push("");
					aaa.push(pnumber);
					aaa.push("平均");
					aaa.push("结算日期");
					option.series[0].data.push(aaa);
					myChart = layui.echarts.init($('#main')[0]);
			        myChart.setOption(option);
				   }
			
				});
	    });


	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });