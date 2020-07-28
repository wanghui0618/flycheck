//初始化	 
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;
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
	    
	    $(function(){
	    	test1();
	    	test2();
	    	test3();
		});
	    function test1() {
	    	 table.render({
	    	     elem: '#addressTable'
	            //,url: $WEB_ROOT_PATH+'/dhccApi/coststatistics/costStatistics/getAllYYDataMZList'
	            ,url:$WEB_ROOT_PATH+'/dhccApi/hosanalysis/hosanalysis/getAllAreaDataMZList'
	            ,limit:5
	            ,height: 280
	           ,page:true
	            ,cols: [[
	            	// 统筹区  门诊次数   门诊总费用   门诊均次费用  占比
	            	   {type: 'numbers', title: '序号' }
		              ,{field:'area',title: '统筹区'}
		              ,{field:'pnumber', title: '门诊次数',templet:function(a){
		            	  var codex = a.pnumber;
		            	  if(codex == "" || codex == null || codex == 0 || codex == "%"){
		            		  codex="-";
		            	  }
		            	  return codex;
		              }}
		              ,{field:'pcost',title: '门诊费用',templet:function(a){
		            	  var codex = a.pcost;
		            	  if(codex == "" || codex == null || codex == 0 || codex == "%"){
		            		  codex="-";
		            	  }else{
		            		  codex = changeNumber(parseFloat(codex));
		            	  }
		            	  return codex;
		              }}
		              ,{field:'pavgcost',title: '门诊次均费用',templet:function(a){
		            	  var codex = a.pavgcost;
		            	  if(codex == "" || codex == null || codex == 0 || codex == "%"){
		            		  codex="-";
		            	  }else{
		            		  codex=changeNumber(parseFloat(codex))
		            	  }
		            	  return codex;
		              }}
		              ,{field:'prate',title: '费用占比',templet:function(a){
		            	  var codex = a.prate;
		            	  if(codex == "" || codex == null || codex == 0 || codex == "%"|| codex == "0%"){
		            		  codex="-";
		            	  }
		            	  return codex;
		              }}
		             
		            ]]
	    	
		          });
		}
	    function test2() {
	    	 table.render({
	    	     elem: '#doctorTable'
	            //,url: $WEB_ROOT_PATH+'/dhccApi/coststatistics/costStatistics/listVo'
	            ,url:$WEB_ROOT_PATH+'/dhccApi/hosanalysis/hosanalysis/getAllYYDataMZList'
	            ,cellMinWidth: 80
	            ,limit:5
	            ,height: 280
	           ,page:true
	            ,cols: [[
	            	   {type: 'numbers', title: '序号' }
	            	   ,{field:'area',title: '医院'}
			              ,{field:'pnumber', title: '门诊次数',templet:function(a){
			            	  var codex = a.pnumber;
			            	  if(codex == "" || codex == null || codex == 0 || codex == "%"){
			            		  codex="-";
			            	  }
			            	  return codex;
			              }}
			              ,{field:'pcost',title: '门诊费用',templet:function(a){
			            	  var codex = a.pcost;
			            	  if(codex == "" || codex == null || codex == 0 || codex == "%"){
			            		  codex="-";
			            	  }else{
			            		  codex = changeNumber(parseFloat(codex));
			            	  }
			            	  return codex;
			              }}
			              ,{field:'pavgcost',title: '门诊次均费用',templet:function(a){
			            	  var codex = a.pavgcost;
			            	  if(codex == "" || codex == null || codex == 0 || codex == "%"){
			            		  codex="-";
			            	  }else{
			            		  codex = changeNumber(parseFloat(codex));
			            	  }
			            	  return codex;
			              }}
			              ,{field:'prate',title: '费用占比',templet:function(a){
			            	  var codex = a.prate;
			            	  if(codex == "" || codex == null || codex == 0 || codex == "%" || codex == "0%"){
			            		  codex="-";
			            	  }
			            	  return codex;
			              }}
		            ]]
		          });
		}
	    //condidtionTable
	    function test3() {
	    	 table.render({
	    	     elem: '#condidtionTable'
	            //,url: $WEB_ROOT_PATH+'/dhccApi/coststatistics/costStatistics/listVo'
	            ,url:$WEB_ROOT_PATH+'/dhccApi/hosanalysis/hosanalysis/getConditionByMZConditionList'
	            ,cellMinWidth: 80
	            ,limit:5
	            ,height: 280
	           ,page:true
	            ,cols: [[
	            	   {type: 'numbers', title: '序号' }
	            	   ,{field:'area',title: '病情'}
			              /*,{field:'pnumber', title: '门诊次数',templet:function(a){
			            	  var codex = a.pnumber;
			            	  if(codex == "" || codex == null || codex == 0 || codex == "%"){
			            		  codex="-";
			            	  }
			            	  return codex;
			              }}*/
			              /*,{field:'pcost',title: '门诊费用',templet:function(a){
			            	  var codex = a.pcost;
			            	  if(codex == "" || codex == null || codex == 0 || codex == "%"){
			            		  codex="-";
			            	  }
			            	  return changeNumber(parseFloat(codex));
			              }}*/
			              ,{field:'pnumber',title: '门诊次均费用',templet:function(a){
			            	  var codex = a.pnumber;
			            	  if(codex == "" || codex == null || codex == 0 || codex == "%"){
			            		  codex="-";
			            	  }
			            	  return changeNumber(parseFloat(codex));
			              }}
			              /*,{field:'prate',title: '费用占比',templet:function(a){
			            	  var codex = a.prate;
			            	  if(codex == "" || codex == null || codex == 0 || codex == "%"){
			            		  codex="-";
			            	  }
			            	  return codex;
			              }}*/
		            ]]
		          });
		}
	    
	    //getMZConditionByName
	    
	    table.on('row(doctorTest)', function(obj){
  	    	var name = obj.data.area;
  	    
  	    	
  	    	var totalMoney3 = 0;
  	    	var dataYear3 = new Array();
  	    	var dataArr3 =new Array()
  	    	function data3(value,name){
  	    		var o = new Object();
  	    		o.value=value;
  	    		o.name=name;
  	    		return o;
  	    	}


  	    	$.ajax({
  	    		url:$WEB_ROOT_PATH+"/dhccApi/hosanalysis/hosanalysis/getMZConditionByName?name="+name,
  	    		type:"post",
  	    		success:function(data1){
  	    			var data = data1.data;
  	    			for(var index  in data){
  	    				totalMoney3 += parseFloat(data[index].pnumber);
  	    				dataYear3[index]=data[index].area;
  	    				var dataPie = data3(parseFloat(data[index].pnumber).toFixed(2),data[index].area);
  	    				dataArr3[index] = dataPie;
  	    			}
  	    			// 路径配置
  	    			/* require.config({
  	    				paths : {
  	    					echarts : $WEB_ROOT_PATH
  	    							+ '/js/echarts_jpp/echarts-2.2.7/build/dist'
  	    				}
  	    			});
  	    			// 使用
  	    			require([ 'echarts', 'echarts/chart/pie', // 使用柱状图就加载bar模块，按需加载,
  	    			'echarts/chart/line' ], function(ec) { */
  	    				var myChart = echarts.init(document.getElementById('line3'));
  	    			     var   option = {
  	    			    tooltip: {
  	    			        trigger: 'item',
  	    			        formatter: "{a} <br/>{b}:<br> 次均费用{c}元({d}%)"
  	    			    },
  	    			   /*  legend: {
  	    			        x: 'center',
  	    			        data:dataYear1
  	    			    }, */

  	    				/*title: {
  	    			        "text": '病情门诊次均费用',
  	    			        subtext:changeNumber(Math.round(totalMoney3))+'元',
  	    			        //itemGap:90,
  	    			        "x": '300px',
  	    			        "y": '90px',
  	    			         textAlign: "center",
  	    			        "subtextStyle": {
  	    			            "fontWeight": 'normal',
  	    			            "fontSize": 14,
  	    			            color:'black',
  	    			            
  	    			        }
  	    				}, */
  	    			    series: [
  	    			        {
  	    			            name:'病情门诊次均费用',
  	    			            type:'pie',
  	    			            radius: ['50%', '70%'],
  	    			            avoidLabelOverlap: false,
  	    			            label: {
  	    			                normal: {
  	    			                    show: false,
  	    			                    position: 'center'
  	    			                },
  	    			                emphasis: {
  	    			                    show: true,
  	    			                    textStyle: {
  	    			                        fontSize: '30',
  	    			                        fontWeight: 'bold'
  	    			                    }
  	    			                }
  	    			            },
  	    			            labelLine: {
  	    			                normal: {
  	    			                    show: false
  	    			                }
  	    			            },
  	    			            data:dataArr3
  	    			        }
  	    			    ]
  	    			};
  	    		
  	    			myChart.setOption(option);
  	    			/* var ecConfig = require('echarts/config');  
  	    			myChart.on(ecConfig.EVENT.CLICK, eConsoleSpread); */
  	    			myChart.on('click', function (param) {
  	    				console.log(param);
  	    				alert("饼图点击事件");
  	    			});
  	    			
  	    		//});
  	    		
  	    		
  	    		},error:function(){
  	    		
  	    		
  	    		}
  	    	 
  	    	/* app.title = '环形图'; */
  	    	// 使用刚指定的配置项和数据显示图表。
  	    	});
  	    	var path =$WEB_ROOT_PATH+"/dhccApi/hosanalysis/hosanalysis/getMZConditionByNameList?name="+name;
			
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
  
	    
	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });