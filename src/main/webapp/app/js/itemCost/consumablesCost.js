var diagType='';
var applyPayLevel='';
var handdingCode='';
var orgCode='';
layui.config({
    base: $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table','element'], function () {
    var $ = layui.$
        , form = layui.form
        , element = layui.element
        , table = layui.table;
    
    function consumablesCost(){
    	table.render({
            elem: '#consumablesTable'
            , url: $WEB_ROOT_PATH + '/dhccApi/coststatistics/costStatistics/consumablesCost'
            , cellMinWidth: 80
            , height: tableHeight
            , cols: [[
                  {type: 'numbers', title: '序号'}
                , {field: 'itemCode', title: '编码', hide: true}
                , {field: 'medicalName', title: '耗材名称',align: 'center'}
                , {field: 'totalNum', title: '耗材数量',align: 'center'}
                , {field: 'totalCost', title: '总金额(元)',align: 'center'}
            ]]
            , page: true
            ,done: function(res, curr, count){
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
            	if(count != 0){
                //获取第一行的itemCode
                var firstItemCode = res.data[0].itemCode;
                var firstItemName = res.data[0].medicalName;
                //执行reloadTable函数实现echarts图表的展示
                reloadTable(firstItemCode,firstItemName);
            	}else{
                	reloadTable2();
            	}	
            }
        })
    };
    consumablesCost();
    
    //监听搜索
    form.on('submit(LAY-user-front-search)', function (data) {
    	diagType=$("#diagType").val();
	 	applyPayLevel=$("#applyPayLevel").val();
	 	handdingCode=$("#handdingCode").val();
	 	orgCode=$("#orgCode").val();
        var field = data.field;
      //执行重载
        layui.table.reload('consumablesTable', {
            where: field
            ,page: { curr: 1}
        	 ,done: function(res, curr, count){
            //如果是异步请求数据方式，res即为你接口返回的信息。
            //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
            if(count != 0){//说明有数据
           	//获取第一行的itemCode
                var firstItemCode = res.data[0].itemCode;
                var firstItemName = res.data[0].medicalName;
                reloadTable1(firstItemCode,firstItemName,diagType,applyPayLevel,handdingCode,orgCode);
            }else{
           	 reloadTable2();
            }
          }
        });
    });
        
  //监听行单击事件
 	table.on('row(consumablesTable)', function(obj){
 		//点击获取行的itemCode
 		var itemCode = obj.data.itemCode;
 		var medicalName=obj.data.medicalName;
 		
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
             // 基于准备好的dom，初始化echarts图表
             var myChart = ec.init(document.getElementById('mainTwo')); 
            
          		// 为echarts对象加载数据
               $.ajax({
            	     url: $WEB_ROOT_PATH+"/dhccApi/coststatistics/costStatistics/getConsumablesCostByItemCode?itemCode="+itemCode+"&diagType="+diagType+"&applyPayLevel="+applyPayLevel+"&handdingCode="+handdingCode+"&orgCode="+orgCode,
    		         type : "post",		
    		         async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
    		         dataType : "json",	
    		         success : function(result) {
    		        	 var hvdata=result.data;
    		        	 var names=[];//定义两个数组
    	                 var nums=[];
    		        	 if (hvdata != null && hvdata.length > 0) {
    		        		 var k;
    		        		 if(hvdata.length>10){
    		        			 k=10;
    		        		 }else{
    		        			 k=hvdata.length;
    		        		 }
    		        		 for(var i=0;i<k;i++){ 	
    			                 names.push(hvdata[i].orgName);
    			                 var obj = new Object();
    			                 obj.name = hvdata[i].orgName;
    			                 obj.value =hvdata[i].totalCost;
    			                 nums.push(obj);
    		                }
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
								color:['#F93AF9','#F6C4CE','#39E9E9','#172D2D','#52CC33','#FF0000','#B0B060','#FAFA6B','#3A60F9','#899895'],
								series : [ {
									name : medicalName,
									type : 'pie',
									radius : '60%',
									center : [
											'50%',
											'60%' ],
									data : nums
								} ]
							            }); 
    		         }
    		       }
               }); 
         }
     );
 	});	
 		
    function reloadTable(firstItemCode,firstItemName){
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
             // 基于准备好的dom，初始化echarts图表
             var myChart = ec.init(document.getElementById('mainTwo')); 
            
          		// 为echarts对象加载数据
               $.ajax({
            	     url: $WEB_ROOT_PATH+"/dhccApi/coststatistics/costStatistics/getConsumablesCostByItemCode?itemCode="+firstItemCode,
    		         type : "post",		
    		         async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
    		         dataType : "json",	
    		         success : function(result) {
    		        
    		        	 var hvdata=result.data;
    		        	 var names=[];//定义两个数组
    	                 var nums=[];
    		        	 if (hvdata != null && hvdata.length > 0) {
    		        		 var k;
    		        		 if(hvdata.length>10){
    		        			 k=10;
    		        		 }else{
    		        			 k=hvdata.length;
    		        		 }
    		        		 for(var i=0;i<k;i++){ 	
    			                 names.push(hvdata[i].orgName);
    			                 var obj = new Object();
    			                 obj.name = hvdata[i].orgName;
    			                 obj.value =hvdata[i].totalCost;
    			                 nums.push(obj);
    		                }
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
								color:['#F93AF9','#F6C4CE','#39E9E9','#172D2D','#52CC33','#FF0000','#B0B060','#FAFA6B','#3A60F9','#899895'],
								series : [ {
									name : firstItemName,
									type : 'pie',
									radius : '60%',
									center : [
											'50%',
											'60%' ],
									data : nums
								} ]
							            }); 
    		         }
    		       }
               }); 
         }
     );
    }
    
    function reloadTable1(firstItemCode,firstItemName,diagType,applyPayLevel,handdingCode,orgCode){
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
             // 基于准备好的dom，初始化echarts图表
             var myChart = ec.init(document.getElementById('mainTwo')); 
            
          		// 为echarts对象加载数据
               $.ajax({
            	     url: $WEB_ROOT_PATH+"/dhccApi/coststatistics/costStatistics/getConsumablesCostByItemCode?itemCode="+firstItemCode+"&diagType="+diagType+"&applyPayLevel="+applyPayLevel+"&handdingCode="+handdingCode+"&orgCode="+orgCode,
    		         type : "post",		
    		         async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
    		         dataType : "json",	
    		         success : function(result) {
    		        
    		        	 var hvdata=result.data;
    		        	 var names=[];//定义两个数组
    	                 var nums=[];
    		        	 if (hvdata != null && hvdata.length > 0) {
    		        		 var k;
    		        		 if(hvdata.length>10){
    		        			 k=10;
    		        		 }else{
    		        			 k=hvdata.length;
    		        		 }
    		        		 for(var i=0;i<k;i++){ 	
    			                 names.push(hvdata[i].orgName);
    			                 var obj = new Object();
    			                 obj.name = hvdata[i].orgName;
    			                 obj.value =hvdata[i].totalCost;
    			                 nums.push(obj);
    		                }
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
								color:['#F93AF9','#F6C4CE','#39E9E9','#172D2D','#52CC33','#FF0000','#B0B060','#FAFA6B','#3A60F9','#899895'],
								series : [ {
									name : firstItemName,
									type : 'pie',
									radius : '60%',
									center : [
											'50%',
											'60%' ],
									data : nums
								} ]
							            }); 
    		         }
    		       }
               }); 
         }
     );
    }
    
    function reloadTable2(){
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
             // 基于准备好的dom，初始化echarts图表
             var myChart = ec.init(document.getElementById('mainTwo')); 
             
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
					color:['#F93AF9','#F6C4CE','#39E9E9','#172D2D','#52CC33','#FF0000','#B0B060','#FAFA6B','#3A60F9','#899895'],
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
         }
     );
    }
    
  //按钮事件绑定底层方法-勿动
    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
});