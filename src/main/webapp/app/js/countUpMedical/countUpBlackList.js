document.getElementById("main").style.height=document.documentElement.clientHeight-120+"px";
var url='';
//初始化	

layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table','echarts','laydate'], function(){
		  var $ = layui.$
		  ,form = layui.form
		  ,table = layui.table
		  ,type=''
		  ,laydate=layui.laydate;
	      //加载医院下拉字典
			 laydate.render({
				  elem: '#violationDate'
					  ,type:"year"
						  ,trigger:'click'
							 ,value: new Date() 
							        ,isInitValue:true
			  }); 
			  myUrl=$WEB_ROOT_PATH+'/dhccApi/blackList/blackList/listBlackDetail';
			    var firstLoadYear=new Date().getFullYear();
			   
			    echarts = layui.echarts.init($('#main')[0]);
			    
			    table.render({
		 	    	elem: '#dataTable'
		 	    		,url: myUrl
		 	    		,height: tableHeight-12
		 	    		,cellMinWidth: 80
		 	            ,where: { "blackListVo.type": '2' }
		 	            ,cols: [[
		 	           	   {type: 'numbers',width:40, title: '序号',fixed: 'left'}
		 	             // ,{field:'cityName', title: '城市',width:140,align:'center'}
			              ,{field:'orgName', title: '医疗机构名',width:255,align:'center'}
			              ,{field:'violationTimes',title: '违规次数', align:'center'}
			              ,{field:'totalAmount',title: '违规总金额', align:'center'}
		 	            ]]
		 	           ,page: true
		 	     	  ,done:function my(left){	 
		 				 var data=left["data"]; 
		 				 if(data.length==0){

		 				 }else{
		 					var arrName= new Array();
		 			    	var arrMoney= new Array();
		 			    	
		 					for(var i=0;i<data.length;i++){			
								var	dataDetail = data[i];
								arrName.push(dataDetail["orgName"]);		
								arrMoney.push(dataDetail["totalAmount"]);		
							} 
		 			    	
		 			    	var option = {
		 			     		    title : {
		 				   		        text:"违规金额统计"
		 				   		        	,x:'center' ,
		 				   		        	y:'top',
		 				   		        	textStyle :{fontSize: 25}
		 				   		    },
		 			 	   		    tooltip : {
		 			 	   		        trigger: 'axis'
		 			 	   		    },
		 			 	   		    legend: {
		 			 	   		        data:['']
		 			 	   		    },
		 			 	   		    toolbox: {
		 			 	   		    },
		 			 	   		    calculable : true,
		 			 	   		    xAxis : [
		 			 	   		        {   name:'医疗机构名称',
		 			 	   		            type : 'category',
		 			 	   		      data : arrName
		 			 	           	,axisLabel: {rotate :20}
		 			 	   		   ,axisLine:{width:2}
		 			 	   		        }
		 			 	   		    ],
		 			 	   		    yAxis : [
		 			 	   		        {  name:'违规金额',
		 			 	   		            type : 'value'
		 			 	   		        }
		 			 	   		    ],
		 			 	   		    series : [
		 			 	   		        {
		 			 	   		            type:'bar',
		 			 	   		            data:arrMoney,
		 			 	   			    barMaxWidth:15
		 			 	   		        },
		 			 	   		    ],
		 			 	   		};
		 			       echarts.setOption(option);	 
		 				 }   	    
		 	 		    }
		 	          });
	
			    form.on('select(choose)', function(data){
			    	echarts = layui.echarts.init($('#main')[0]);
			    	document.getElementById('msg').innerHTML='';
			    	value=data.value;
			    	if(value==2){

			    		//医院
			    		myUrl=$WEB_ROOT_PATH+'/dhccApi/blackList/blackList/listBlackDetail';
			    		table.render({
			    			elem: '#dataTable'
			    				,url: myUrl
			    				,where: { "blackListVo.type": '2' }
			    		,height: tableHeight-12
			    		,cols: [[
			    			{type: 'numbers',width:40, title: '序号',fixed: 'left'}
			    			//,{field:'cityName', title: '城市',width:140,align:'center'}
			    			,{field:'orgName', title: '医疗机构名',align:'center'}
			    			,{field:'violationTimes',title: '违规次数', width:100,align:'center'}
			    			//,{field:'violationDate',title: '年度', width:110,align:'center'}
			    			,{field:'totalAmount',title: '违规总金额', align:'center'}

			    			]]
			    		,page: true
			    		,done:function my(left){
			    			 var data=left["data"]; 
			 				 if(data.length==0){
			 					echarts.clear();
			 				 }else{

				 					var arrName= new Array();
				 			    	var arrMoney= new Array();
				 			    	
				 					for(var i=0;i<data.length;i++){			
										var	dataDetail = data[i];
										arrName.push(dataDetail["orgName"]);		
										arrMoney.push(dataDetail["totalAmount"]);		
									} 
				 			    	
				 			    	var option = {
				 			    		    title : {
				 				   		        text:"违规金额统计"
				 				   		        	,x:'center' ,
				 				   		        	y:'top',
				 				   		        	textStyle :{fontSize: 25}
				 				   		    },
				 			 	   		    tooltip : {
				 			 	   		        trigger: 'axis'
				 			 	   		    },
				 			 	   		    legend: {
				 			 	   		        data:['']
				 			 	   		    },
				 			 	   		    toolbox: {
				 			 	   /*		        show : true,
				 			 	   		        feature : {
				 			 	   		            magicType : {show: true, type: ['line', 'bar']},
				 			 	   		        }*/
				 			 	   		    },
				 			 	   		    calculable : true,
				 			 	   		    xAxis : [
				 			 	   		        {    name:'违规机构名',
				 			 	   		            type : 'category',
				 			 	   		      data : arrName
				 			 	   	,axisLabel: {rotate :20}
				 			 	   		   ,axisLine:{width:2}
				 			 	   		        }
				 			 	   		    ],
				 			 	   		    yAxis : [
				 			 	   		        {  name:'违规金额',
				 			 	   		            type : 'value'
				 			 	   /*		            ,axisLine:false	*/
				 			 	   		        }
				 			 	   		    ],
				 			 	   		    series : [
				 			 	   		        {
				 			 	   		            
				 			 	   		            type:'bar',
				 			 	   		            data:arrMoney,
						 			 	   		    barMaxWidth:15
				 			 	   		        },
				 			 	   		    ],
				 			 	   		};
				 			       echarts.setOption(option);	  
			 				 }
			    		}
			    		});
			    		
			    	}else if(value==1){
			    		//参保人
			    		myUrl=$WEB_ROOT_PATH+'/dhccApi/blackList/blackList/listBlackDetail';
			    		table.render({
			    			elem: '#dataTable'
			    				,url: myUrl
			    				,where: { "blackListVo.type": '1'}
			    		,height:tableHeight-12
			    		,cols: [[
			    			{type: 'numbers',width:40, title: '序号',fixed: 'left'}
			    			//,{field:'cityName', title: '城市',width:140,align:'center'}
			    			,{field:'name', title: '名称',width:100,align:'center'}
			    			,{field:'idCard', title: '身份证号',align:'center'}
			    			,{field:'violationTimes',title: '违规次数', width:92,align:'center'}
			    			,{field:'totalAmount',title: '违规总金额',align:'center'}

			    			]]
			    		,page: true
			    		,done:function my(left){

			    			 var data=left["data"]; 
			 				 if(data.length==0){
			 					echarts.clear();
			 				 }else{

				 					var arrName= new Array();
				 			    	var arrMoney= new Array();
				 			    	
				 					for(var i=0;i<data.length;i++){			
										var	dataDetail = data[i];
										arrName.push(dataDetail["name"]);		
										arrMoney.push(dataDetail["totalAmount"]);		
									} 
				 			    	
				 			    	var option = {
				 			 	   		    title : {
				 			 	   		        text:'违规金额统计'
				 			 	   		     	,x:'center' ,
			 				   		        	y:'top',
			 				   		        	textStyle :{fontSize: 25}
			 				   		    },
				 			 	   		    tooltip : {
				 			 	   		        trigger: 'axis'
				 			 	   		    },
				 			 	   		    legend: {
				 			 	   		        data:['']
				 			 	   		    },
				 			 	   		    toolbox: {
				 			 	   		    },
				 			 	   		    calculable : true,
				 			 	   		    xAxis : [
				 			 	   		    	{    name:'违规人',
				 			 	   		    		type : 'category',
				 			 	   		    		data : arrName
				 			 	   		    		,axisLabel: {rotate :20}
				 			 	   		    	,axisLine:{width:2}
				 			 	   		    	}
				 			 	   		    	],
				 			 	   		    yAxis : [
				 			 	   		        {  name:'违规金额',
				 			 	   		            type : 'value'
				 			 	   		        }
				 			 	   		    ],
				 			 	   		    series : [
				 			 	   		        { 
				 			 	   		            type:'bar',
				 			 	   		            data:arrMoney,
				 			 	   		       barMaxWidth:20
				 			 	   		        },
				 			 	   		    ],
				 			 	   		};
				 			       echarts.setOption(option);	  
			 				 }
			    		
			    		}
			    		});
			    	}else if(value==3){
			    		echarts.clear();
			    		//药店
			    		myUrl=$WEB_ROOT_PATH+'/dhccApi/blackList/blackList/listBlackDetail';
			    		table.render({
			    			elem: '#dataTable'
			    				,url: myUrl
			    				,where: { "blackListVo.type": '3'}
			    		,height: tableHeight-12
			    		,cols: [[
			    			{type: 'numbers',width:40, title: '序号',fixed: 'left'}
			    			,{field:'name', title: '名称',align:'center'}
			    			,{field:'violationTimes',title: '违规次数',align:'center'}
			    			]]
			    		,page: true
			    		,done:function my(left){
			    			$(".echart").html("<div style='width:100%;height:100%;text-align:center;'><div><img src="+$WEB_ROOT_PATH+"/images/empty.png></div><div style='line-height: 50px;'>无数据</div></div>");
			    		}
			    		});
			    	}else if(value==4){
			    		echarts.clear();
			    		//医生
			    		myUrl=$WEB_ROOT_PATH+'/dhccApi/blackList/blackList/listBlackDetail';
			    		table.render({
			    			elem: '#dataTable'
			    				,url: myUrl
			    				,height: tableHeight-12
			    				,where: { "blackListVo.type": '4'}
			    		,cols: [[
			    			{type: 'numbers',width:40, title: '序号',fixed: 'left'}
			    			,{field:'name', title: '名称',align:'center'}
			    			,{field:'violationTimes',title: '违规次数',align:'center'}
			    			]]
			    		,page: true
			    		,done:function my(left){
			    			$(".echart").html("<div style='width:100%;height:100%;text-align:center;'><div><img src="+$WEB_ROOT_PATH+"/images/empty.png></div><div style='line-height: 50px;'>无数据</div></div>");
			    		}
			    		});
			    	}
			    	table.reload('dataTable');
			    }); 
	  
	  });

