document.getElementById("main").style.height=document.documentElement.clientHeight-60+"px";
var form;
var table;
var $;
var arrList;

layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table','echarts','laydate'], function(){
			 laydate=layui.laydate;
			    form = layui.form;
			    table = layui.table;
			    $ = layui.$;
			      //加载医院下拉字典
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
				 dateChange();
		 });

function dateChange(value){
	 arrList=new Array(12);
	 var key;
		for(var i=0;i<12;i++){
			arrList[i]="0";
		}
	var date =new Date();
	if(value==null|value==''){
		value=date.getFullYear();
		key =1;
	}else{
		key=0;
	}
	if(value==date.getFullYear()){
		key =1;
	}
	$("#yearHtml").html(value+"年上传数据量");

	 echarts = layui.echarts.init($('#main')[0]);
	    table.render({
	    	elem: '#dataTable'
	            ,url: $WEB_ROOT_PATH+'/dhccApi/medical/medical/count'
	            ,where: { inFlag:value  }
	    		,cellMinWidth: 80
	            , height: tableHeight-2
	            ,cols: [[
	            	   {type: 'numbers',width:40, title: '序号',fixed: 'left' },
	            	   {field:'monthTime', title: '月份',align:'center',templet: function(d){
	            		   var b =d.monthTime;
	            		   if(key==0){
								  return  b +'月';  
	            		   }else{
	            			   var thisMonth=date.getMonth()+2; 
	            			   console.log(thisMonth);
	            			   if(b<thisMonth){
	            				   return  b +'月';   
	            			   }else{
	            				   return  "-";    
	            			   }
	            		   }  					  
					   }}
		              ,{field:'dataNumber',title: '上传数据量', align:'center',templet: function(d){
		            	  var b =d.monthTime;
		            	  var dataNumber =d.dataNumber;
	            		   if(key==0){
								  return  dataNumber ;  
	            		   }else{
	            			   var thisMonth=date.getMonth()+2; 
	            			   if(b<thisMonth){
	            				   return dataNumber ;   
	            			   }else{
	            				   return  "-";    
	            			   }
	            		   }  					  
					   }}
	            ]]
	            //,page: true
	            ,done:function select(data){            	
	            	var count=data["data"];               
	            	Object.keys(count).forEach(function(key){
	            		var obj=count[key];  
	            		var objNumber=obj["dataNumber"];
	            		var objMonth=obj["monthTime"];
	            		var index=parseInt(objMonth);
	            		arrList[index-1]=objNumber;
	            	});
	   		option = {
	   		    title : {
	   		        text: ""//value+'年数据上传量',
	   		    },
	   		    tooltip : {
	   		        trigger: 'axis'
	   		    },
	   		    legend: {
	   		        data:['']
	   		    },
	   		    toolbox: {
	   		        show : true,
	   		        feature : {
	   		            magicType : {show: true, type: ['line', 'bar']},
	   		        }
	   		    },
	   		    calculable : true,
	   		    xAxis : [
	   		        {
	   		            type : 'category',
	   		            data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
	   		        }
	   		    ],
	   		    yAxis : [
	   		        {
	   		            type : 'value'
	   		        }
	   		    ],
	   		    series : [
	   		        {
	   		            name:'上传数据量',
	   		            type:'line',
	   		            barMaxWidth:20, 
	   		            data:[arrList[0],arrList[1],arrList[2],arrList[3]
	   		            	,arrList[4],arrList[5],arrList[6],arrList[7]
	   		            	,arrList[8],arrList[9],arrList[10],arrList[11]],
	   		        },
	   		    ]
	   		};
   	        echarts.setOption(option); 	
 		    }        
          });
}