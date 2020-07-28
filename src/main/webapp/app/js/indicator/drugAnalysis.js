var selectedYear;
//初始化	 
layui.config({
    base: $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table','element','laydate'], function () {
    var $ = layui.$
        , form = layui.form
        , element = layui.element
        , table = layui.table
        ,laydate = layui.laydate;
    
   
		laydate.render({
			elem: '#test10'
			,value: nowYear()
			,max: nowYear()
			,type: 'year',
			done:function(value){
				selectedYear=value;
				test1(selectedYear);
				test2(selectedYear);
				layui.table.reload('addressTable', {
					where: {"costStatisticsVo.year":value}
					,page: { curr: 1}
				});
				layui.table.reload('doctorTable', {
					where: {"costStatisticsVo.year":value}
					,page: { curr: 1}
				});
			}
		});
		
	
    function nowYear(){
		var date=new Date();
		var nowYear=date.getFullYear();
		return nowYear.toString();
	}
    
    
    
    function test1(selectedYear){
    	console.log(selectedYear)
    	require.config({
    	    paths: {
    	        echarts: $WEB_ROOT_PATH+'/js/echarts_jpp/echarts-2.2.7/build/dist'
    	    }
    	});
    		// 使用
    		require(
    	 	[
    	     	'echarts',
    	     	'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
    	 	],
    	 	function (ec) {
    	        // 基于准备好的dom，初始化echarts实例
    	        var myChart = ec.init(document.getElementById('cost'));
    	        
    	        $.ajax({
    	     	     url: $WEB_ROOT_PATH+"/dhccApi/medicaldetail/medicalDetail/drugPriceList?selectedYear="+selectedYear,
    			         type : "post",		
    			         async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
    			         dataType : "json",	
    			         success : function(result) {
    			        	 if(result.data.length==0){
    			        		 myChart.setOption({ //加载数据图表
        							                     	title : {
        									x : 'center'
        								},
        								tooltip : {
        									trigger : 'item',
        									formatter : "{a} <br/>{b} : {c} ({d}%)"
        								},
        								legend : {
        									left : 'left',
        									data : []
        								},
        								color:['#F93AF9','#F6C4CE','#39E9E9'],
        								series : [ {
        									name : '金额',
        									type : 'pie',
        									radius : '60%',
        									center : [
        											'50%',
        											'60%' ],
        									data : []
        								} ]
        							            }); 
    			        	 }else{
    			        		 var names=[];//定义两个数组
        		                 var nums=[];
        			        	 
        		                 names.push("药品");
        						 var obj = new Object();
        						 obj.name = "药品";
        						 obj.value = result.data[0].medicalCost;
        						 nums.push(obj);
        						 
        						 names.push("诊疗项目");
        						 var obj = new Object();
        						 obj.name = "诊疗项目";
        						 obj.value = result.data[0].treatmentProjectCost;
        						 nums.push(obj);
        						 
        						 names.push("耗材");
        						 var obj = new Object();
        						 obj.name = "耗材";
        						 obj.value = result.data[0].consumableMaterialCost;
        						 nums.push(obj);
        						 
        						 myChart.setOption({ //加载数据图表
        							                     	title : {
        									x : 'center'
        								},
        								tooltip : {
        									trigger : 'item',
        									formatter : "{a} <br/>{b} : {c} ({d}%)"
        								},
        								legend : {
        									left : 'left',
        									data : names
        								},
        								color:['#F93AF9','#F6C4CE','#39E9E9'],
        								series : [ {
        									name : '金额',
        									type : 'pie',
        									radius : '60%',
        									center : [
        											'50%',
        											'60%' ],
        									data : nums
        								} ]
        							            }); 
    			        	 
    			        	 
    						 myChart.on("click", pieConsole1);
    					        function pieConsole1(param) {
    					            var name = param.name;
    					            console.log(name)
    					            if(name=='诊疗项目'){
    					            	var path =$WEB_ROOT_PATH+'/dhccApi/coststatistics/costStatistics/listDiagnosisTreatmentVo';
    					    			layui.table.reload('addressTable',{
    					    				 url: path
    					    				 ,cols: [[
    					  	            	   {type:'numbers',width: '5%',align: 'center',title: '序号' }
    					  		              ,{field:'handdingInsName',width: '35%',align: 'center',title: '统筹区'}
    					  		              ,{field:'diagnosisTreatmentCost',width: '20%',align: 'center',title: '诊疗总费用'}
    					  		              ,{field:'totalCost',width: '20%',align: 'center',title: '总费用'}
    					  		              ,{field:'itemRadio',width: '20%',align: 'center',title: '诊疗占比'}
    					  		            ]]
    					    			,page:false
    					    		     });
    									
    					    			var path =$WEB_ROOT_PATH+'/dhccApi/coststatistics/costStatistics/listHospitalDiagnosisTreatmentVo';
    					    			layui.table.reload('doctorTable',{
    					    				 url: path
    					    				 ,cols: [[
    					  	            	   {type:'numbers',width: '5%',align: 'center',title: '序号' }
    					  		              ,{field:'orgName',width: '35%',align: 'center',title: '医院名字'}
    					  		              ,{field:'diagnosisTreatmentCost',width: '20%',align: 'center',title: '诊疗总费用'}
    					  		              ,{field:'totalCost',width: '20%',align: 'center',title: '总费用'}
    					  		              ,{field:'itemRadio',width: '20%',align: 'center',title: '诊疗占比'}
    					  		            ]]
    					    			,page:false
    					    		     });
    					            }else if(name=='耗材'){
    					            	var path =$WEB_ROOT_PATH+'/dhccApi/coststatistics/costStatistics/listConsumablesVo';
    					    			layui.table.reload('addressTable',{
    					    				 url: path
    					    				 ,cols: [[
    					  	            	   {type:'numbers',width: '5%',align: 'center',title: '序号' }
    					  		              ,{field:'handdingInsName',width: '35%',align: 'center',title: '统筹区'}
    					  		              ,{field:'consumablesCost',width: '20%',align: 'center',title: '耗材总费用'}
    					  		              ,{field:'totalCost',width: '20%',align: 'center',title: '总费用'}
    					  		              ,{field:'itemRadio',width: '20%',align: 'center',title: '耗材占比'}
    					  		            ]]
    					    			,page:false
    					    		     });
    									
    					    			var path =$WEB_ROOT_PATH+'/dhccApi/coststatistics/costStatistics/listHospitalConsumablesVo';
    					    			layui.table.reload('doctorTable',{
    					    				 url: path
    					    				 ,cols: [[
    					  	            	   {type:'numbers',width: '5%',align: 'center',title: '序号' }
    					  		              ,{field:'orgName',width: '35%',align: 'center',title: '医院名字'}
    					  		              ,{field:'consumablesCost',width: '20%',align: 'center',title: '耗材总费用'}
    					  		              ,{field:'totalCost',width: '20%',align: 'center',title: '总费用'}
    					  		              ,{field:'itemRadio',width: '20%',align: 'center',title: '耗材占比'}
    					  		            ]]
    					    			,page:false
    					    		     });
    					            }else if(name=='药品'){
    					            	var path =$WEB_ROOT_PATH+'/dhccApi/coststatistics/costStatistics/listVo';
    					    			layui.table.reload('addressTable',{
    					    				 url: path
    					    				 ,cols: [[
    					  	            	   {type:'numbers',width: '5%',align: 'center',title: '序号' }
    					  		              ,{field:'handdingInsName',width: '35%',align: 'center',title: '统筹区'}
    					  		              ,{field:'medicalCost',width: '20%',align: 'center',title: '药品总费用'}
    					  		              ,{field:'totalCost',width: '20%',align: 'center',title: '总费用'}
    					  		              ,{field:'itemRadio',width: '20%',align: 'center',title: '药占比'}
    					  		            ]]
    					    			,page:false
    					    		     });
    									
    					    			var path =$WEB_ROOT_PATH+'/dhccApi/coststatistics/costStatistics/listHospitalVo';
    					    			layui.table.reload('doctorTable',{
    					    				 url: path
    					    				 ,cols: [[
    					  	            	   {type:'numbers',width: '5%',align: 'center',title: '序号' }
    					  		              ,{field:'orgName',width: '35%',align: 'center',title: '医院名字'}
    					  		              ,{field:'medicalCost',width: '20%',align: 'center',title: '药品总费用'}
    					  		              ,{field:'totalCost',width: '20%',align: 'center',title: '总费用'}
    					  		              ,{field:'itemRadio',width: '20%',align: 'center',title: '药占比'}
    					  		            ]]
    					    			,page:false
    					    		     });
    					            }
    					            
    					        }
    					        
    			        	 }
    			         }
    	       });
    	 	  }
    		);
    }
    test1(nowYear());
    
    function test2(selectedYear){
    	require.config({
    	    paths: {
    	        echarts: $WEB_ROOT_PATH+'/js/echarts_jpp/echarts-2.2.7/build/dist'
    	    }
    	});
    		// 使用
    		require(
    	 	[
    	     	'echarts',
    	     	'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
    	 	],
    	 	function (ec) {
    	        // 基于准备好的dom，初始化echarts实例
    	        var myChart = ec.init(document.getElementById('main'));
    	        
    	        $.ajax({
    	     	     url: $WEB_ROOT_PATH+"/dhccApi/medicaldetail/medicalDetail/drugAnalysisList?selectedYear="+selectedYear,
    			         type : "post",		
    			         async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
    			         dataType : "json",	
    			         success : function(result) {
    			        	 if(result.data.length==0){
    			        		 myChart.setOption({ //加载数据图表
        							                     	title : {
        									x : 'center'
        								},
        								tooltip : {
        									trigger : 'item',
        									formatter : "{a} <br/>{b} : {c} ({d}%)"
        								},
        								legend : {
        									left : 'left',
        									data : []
        								},
        								color:['#FF0000','#B0B060','#FAFA6B','#3A60F9','#F93AF9','#899895','#8F00EE','#39E9E9','#4D30BE','#F6C4CE','#172D2D','#52CC33'],
        								series : [ {
        									name : '金额',
        									type : 'pie',
        									radius : '60%',
        									center : [
        											'50%',
        											'60%' ],
        									data : []
        								} ]
        						});
    			        	 }else{
    			        	 var names=[];//定义两个数组
    		                 var nums=[];
    			        	 
    			        	 names.push("西药");
    						 var obj = new Object();
    						 obj.name = "西药";
    						 obj.value = result.data[0].western;
    						 nums.push(obj);
    						 
    						 names.push("中成药");
    						 var obj = new Object();
    						 obj.name = "中成药";
    						 obj.value = result.data[0].chinesePatentCost;
    						 nums.push(obj);
    						 
    						 names.push("中草药");
    						 var obj = new Object();
    						 obj.name = "中草药";
    						 obj.value = result.data[0].chineseMedicineCost;
    						 nums.push(obj);
    			        	 
    		                 names.push("治疗费");
    						 var obj = new Object();
    						 obj.name = "治疗费";
    						 obj.value = result.data[0].treatmentCost;
    						 nums.push(obj);
    						 
    						 names.push("检查费");
    						 var obj = new Object();
    						 obj.name = "检查费";
    						 obj.value = result.data[0].inspectionCost;
    						 nums.push(obj);
    						 
    						 names.push("检验费");
    						 var obj = new Object();
    						 obj.name = "检验费";
    						 obj.value = result.data[0].inspectionFee;
    						 nums.push(obj);
    						 
    						 names.push("化验费");
    						 var obj = new Object();
    						 obj.name = "化验费";
    						 obj.value = result.data[0].laboratoryFee;
    						 nums.push(obj);
    						 
    						 names.push("手术费");
    						 var obj = new Object();
    						 obj.name = "手术费";
    						 obj.value = result.data[0].operationCost;
    						 nums.push(obj);
    						 
    						 names.push("血费");
    						 var obj = new Object();
    						 obj.name = "血费";
    						 obj.value = result.data[0].bloodChargesCost;
    						 nums.push(obj);
    						 
    						 names.push("材料费");
    						 var obj = new Object();
    						 obj.name = "材料费";
    						 obj.value = result.data[0].material;
    						 nums.push(obj);
    						 
    						 names.push("医疗服务费");
    						 var obj = new Object();
    						 obj.name = "医疗服务费";
    						 obj.value = result.data[0].medicalServiceCost;
    						 nums.push(obj);
    						 
    						 names.push("其它");
    						 var obj = new Object();
    						 obj.name = "其它";
    						 obj.value = result.data[0].other;
    						 nums.push(obj);
    						 
    						 myChart.setOption({ //加载数据图表
    							                     	title : {
    									x : 'center'
    								},
    								tooltip : {
    									trigger : 'item',
    									formatter : "{a} <br/>{b} : {c} ({d}%)"
    								},
    								legend : {
    									left : 'left',
    									data : names
    								},
    								color:['#FF0000','#B0B060','#FAFA6B','#3A60F9','#F93AF9','#899895','#8F00EE','#39E9E9','#4D30BE','#F6C4CE','#172D2D','#52CC33'],
    								series : [ {
    									name : '金额',
    									type : 'pie',
    									radius : '60%',
    									center : [
    											'50%',
    											'60%' ],
    									data : nums
    								} ]
    						});
    						 
    						 myChart.on("click", pieConsole2);
    						 function pieConsole2(param) {
    							 console.log(param)
 					            var name = param.name;
    							 console.log(name)
    							 if(name=='检查费'){
    								 var path =$WEB_ROOT_PATH+'/dhccApi/coststatistics/costStatistics/listInspectionCostVo';
 					    			layui.table.reload('addressTable',{
 					    				 url: path
 					    				 ,cols: [[
 					  	            	   {type:'numbers',width: '5%',align: 'center',title: '序号' }
 					  		              ,{field:'handdingInsName',width: '35%',align: 'center',title: '统筹区'}
 					  		              ,{field:'inspectionCost',width: '20%',align: 'center',title: '检查费'}
 					  		              ,{field:'totalCost',width: '20%',align: 'center',title: '总费用'}
 					  		              ,{field:'itemRadio',width: '20%',align: 'center',title: '检查费占比'}
 					  		            ]]
 					    			,page:false
 					    		     });
 									
 					    			var path =$WEB_ROOT_PATH+'/dhccApi/coststatistics/costStatistics/listHospitalInspectionCostVo';
 					    			layui.table.reload('doctorTable',{
 					    				 url: path
 					    				 ,cols: [[
 					  	            	   {type:'numbers',width: '5%',align: 'center',title: '序号' }
 					  		              ,{field:'orgName',width: '35%',align: 'center',title: '医院名字'}
 					  		              ,{field:'inspectionCost',width: '20%',align: 'center',title: '检查费'}
 					  		              ,{field:'totalCost',width: '20%',align: 'center',title: '总费用'}
 					  		              ,{field:'itemRadio',width: '20%',align: 'center',title: '检查费占比'}
 					  		            ]]
 					    			,page:false
 					    		     });
    							 }
    							 
    						 }
    						 
    			         }	 
    			     }
    	       });
    	 	  }
    		);   
    }
    test2(nowYear());
    
    function test3() {
    	table.render({
    		elem: '#addressTable'
	        ,url: $WEB_ROOT_PATH+'/dhccApi/coststatistics/costStatistics/listVo'
	        ,cellMinWidth: 80
	        ,height: 280
	        ,cols: [[
	        	{type:'numbers',width: '5%',align: 'center',title: '序号' }
	        	,{field:'handdingInsName',width: '35%',align: 'center',title: '统筹区'}
		        ,{field:'medicalCost',width: '20%',align: 'center',title: '药品总费用'}
		        ,{field:'totalCost',width: '20%',align: 'center',title: '总费用'}
		        ,{field:'itemRadio',width: '20%',align: 'center',title: '药占比',templet:function(d){
		        	var codex = d.itemRadio;
		        	if(codex=="%"){
		        		codex="0%";
		        	}
		        	return codex;
		        }}
		        ]]
    	,page:false
    	});
    }
    test3();
	    
    function test4() {
    	table.render({
    		elem: '#doctorTable'
    		,url: $WEB_ROOT_PATH+'/dhccApi/coststatistics/costStatistics/listHospitalVo'
	        ,cellMinWidth: 80
	        ,height: 280
	        ,cols: [[
	        	{type:'numbers',width: '5%',align: 'center',title: '序号' }
	        	,{field:'orgName',width: '35%',align: 'center',title: '医院名字'}
	        	,{field:'medicalCost',width: '20%',align: 'center',title: '药品总费用'}
	        	,{field:'totalCost',width: '20%',align: 'center',title: '总费用'}
	        	,{field:'itemRadio',width: '20%',align: 'center',title: '药占比'}
	        	]]
    	,page:false
    	});
	}
	test4();
	    
	    
	    
	    //按钮事件绑定底层方法-勿动
	$('.layui-btn.layuiadmin-btn-useradmin').on('click', function () {
		var type = $(this).data('type');
		ctive[type] ? active[type].call(this) : '';
		});
});