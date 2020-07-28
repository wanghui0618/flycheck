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
		$.getJSON($WEB_ROOT_PATH+'/dhccApi/dict/dict/findDict?dictRequestVo.tableName=t_piccbid_dictCity', 
				function(data){
			var  dataList= data.dictList;
			var  form = layui.form
			for(var i=0 ;i<dataList.length;i++){
				var mm="<option value='"+dataList[i].value+"'>"+dataList[i].text+"</option>";
				$("#cityCode").append(mm); 
			}
			form.render('select');
		});
	      //加载医院下拉字典
		 laydate.render({
			  elem: '#violationDate'
				  ,type:"year"
					  ,trigger:'click'
						 ,value: new Date() 
						        ,isInitValue:true
		  }); 
	      //加载医院下拉字典
        $.getJSON($WEB_ROOT_PATH+'/dhccApi/dataauthority/dataauthority/findOrg',function(data){
                var orgs=data.pageModel.rows;
                for(var i=0 ;i<orgs.length;i++){
                    var mm="<option value='"+orgs[i].value+"'>"+orgs[i].text+"</option>";
                    $("#orgName").append(mm);
                }
                form.render('select');
           });
 
		  myUrl=$WEB_ROOT_PATH+'/dhccApi/blackList/blackList/listViolationTimes';
	    var firstLoadYear=new Date().getFullYear();
	   
	    table.render({
 	    	elem: '#dataTable'
 	    		,url: myUrl
 	    		,height: document.documentElement.clientHeight-115
 	            ,where: { "blackListVo.type": '1',"blackListVo.violationDate":firstLoadYear }
 	            ,cols: [[
 	           	   {type: 'numbers',width:40, title: '序号',fixed: 'left'}
 	              ,{field:'cityName', title: '城市',width:140,align:'center'}
	              ,{field:'orgName', title: '医疗机构名',width:255,align:'center'}
	              ,{field:'violationTimes',title: '违规次数', width:100,align:'center'}
	              //,{field:'violationDate',title: '年度', width:110,align:'center'}
	              ,{field:'totalAmount',title: '违规总金额', width:130,align:'center'}
		             
 	            ]]
 	           ,page: true
 	     	  ,done:function my(left){	 
 				  $(document).find('[data-index="0"]').trigger("click");		
 				  $(document).find('[data-index="0"]').css("background-color","#C0C0C0");
 				 var data=left["data"]; 
 				 if(data.length==0){
		 			  document.getElementById('tableTitle').innerHTML="<span style='padding-left: 220px;height: 35px;font-size: 19px;color:black;line-height: 40px;'>"+firstLoadYear+"年  医疗机构暂无违规记录</span>";	 	

 		 		/*	 $(document).find('#noMsg').css("background-image","url("+$WEB_ROOT_PATH+'/images/noMsg.png)');
 		 			 $(document).find('#noMsg').css("background-repeat","no-repeat");*/
 				 }else{
 					 var first=data["0"];
 				     var year=first["violationDate"];
	 			  document.getElementById('tableTitle').innerHTML="<span style='padding-left: 100px;'>"+year+"年  医疗机构违规记录统计   </span>               <span style='font-size:11px;color:red;' >*以违规次数作为排名标准</span> ";	 	
	 		 	  document.getElementById('msg').innerHTML='<span style="font-size:11px;color:red;float: right; margin-right: 100px; margin-top: 5px">*违规日期以就诊费用发生日期为准</span>'

 				 }
 	 		    }
 	          });
	    
	    //监听搜索
		form.on('submit(LAY-user-front-search)', function(data){
	    	var field = data.field;    
	        //执行重载
	    	 echarts = layui.echarts.init($('#main')[0]);
			 document.getElementById('msg').innerHTML='';
	        layui.table.reload('dataTable', {
	            where: field
	            
	        });
	        
	    });
	    
 
		  table.on('row(dataTable)', function(obj){		
			  var result=obj.data;
		  }); 
		  form.on('select(choose)', function(data){
			  echarts = layui.echarts.init($('#main')[0]);
			  document.getElementById('msg').innerHTML='';
			  value=data.value;
			  if(value==1){
			document.getElementById('thirdInputBlock').innerHTML="";  
			form.render(null,'thirdInputBlock');
			document.getElementById('fourthInputBlock').innerHTML='<label class="layui-form-label ">年度</label><div class="layui-input-inline" style="magrin-left: 20px" ><input id="violationDate" name="blackListVo.violationDate" type="text" class="layui-input" placeholder="yyyy"></div>';		
			form.render(null,'fourthInputBlock');
			document.getElementById('thirdInputBlock').innerHTML='<label class="layui-form-label ">医疗机构</label><div class="layui-input-inline"><input id="getOrgName" /><input type="text" id="orgCode" name="blackListVo.orgName" style="display: none;" /></div>';
		     //加载医院下拉字典
	        $.getJSON($WEB_ROOT_PATH+'/dhccApi/dataauthority/dataauthority/findOrg',function(data){
	                var orgs=data.pageModel.rows;
	                for(var i=0 ;i<orgs.length;i++){
	                    var mm="<option value='"+orgs[i].value+"'>"+orgs[i].text+"</option>";
	                    $("#orgName").append(mm);
	                }  
	           });
		      //加载医院下拉字典
			 laydate.render({
				  elem: '#violationDate'
					  ,type:"year"
						  ,trigger:'click'
							 ,value: new Date() 
							        ,isInitValue:true
			  }); 
	        form.render('select'); 
	        //医院
			  myUrl=$WEB_ROOT_PATH+'/dhccApi/blackList/blackList/listViolationTimes';
				  table.render({
			 	    	elem: '#dataTable'
			 	    		,url: myUrl
			 	    	   ,where: { "blackListVo.type": '1',"blackListVo.violationDate":firstLoadYear }
			 	    		,height: document.documentElement.clientHeight-115
			 	            ,cols: [[
			 	         	   {type: 'numbers',width:40, title: '序号',fixed: 'left'}
					              ,{field:'cityName', title: '城市',width:140,align:'center'}
					              ,{field:'orgName', title: '医疗机构名',width:255,align:'center'}
					              ,{field:'violationTimes',title: '违规次数', width:100,align:'center'}
					              //,{field:'violationDate',title: '年度', width:110,align:'center'}
					              ,{field:'totalAmount',title: '违规总金额', width:130,align:'center'}
			 		             
			 	            ]]
			 	           ,page: true
			 	     	  ,done:function my(left){	 
			 				  $(document).find('[data-index="0"]').trigger("click");		
			 				  $(document).find('[data-index="0"]').css("background-color","#C0C0C0");
			 				 var data=left["data"]; 
			 				 if(data.length==0){
			 		 			  document.getElementById('tableTitle').innerHTML="<span style='padding-left: 220px;height: 35px;font-size: 19px;color:black;line-height: 40px;'>"+firstLoadYear+"年  医疗机构暂无违规记录</span>";	 	
			 				
			 				 }else{
			 					 var first=data["0"];
			 				     var year=first["violationDate"];
				 			  document.getElementById('tableTitle').innerHTML="<span style='padding-left: 100px;'>"+year+"年  医疗机构违规记录统计   </span>               <span style='font-size:11px;color:red;' >*以违规次数作为排名标准</span> ";	 	
				 		 	  document.getElementById('msg').innerHTML='<span style="font-size:11px;color:red;float: right; margin-right: 5px; margin-top: 5px">*违规日期以就诊费用发生日期为准</span>'

			 				 }
			 	 		    }
			 	          });
			  }else if(value==2){
				  //参保人
					document.getElementById('thirdInputBlock').innerHTML='<label class="layui-form-label ">姓名</label><div class="layui-input-block " style="magrin-left: 20px" ><input type="text" style="width: 180px;" name="blackListVo.name" placeholder="请输入姓名" autocomplete="off" class="layui-input"></div>'	;
					document.getElementById('fourthInputBlock').innerHTML='<label class="layui-form-label ">身份证号</label><div class="layui-input-block " ><input type="text" style="width: 180px;" name="blackListVo.idCard" placeholder="请输入身份证号" autocomplete="off" lay-filter="number" class="layui-input"></div>';	
					form.render(null,'thirdInputBlock');
					form.render(null,'fourthInputBlock');
				  
				  myUrl=$WEB_ROOT_PATH+'/dhccApi/blackList/blackList/listViolationTimes';
				  table.render({
			 	    	elem: '#dataTable'
			 	    		,url: myUrl
			 	    		,where: { "blackListVo.type": '2'}
			 	    		,height: document.documentElement.clientHeight-115  
			 	            ,cols: [[
			 	            	   {type: 'numbers',width:40, title: '序号',fixed: 'left'}
			 	            	   ,{field:'cityName', title: '城市',width:140,align:'center'}
			 		              ,{field:'name', title: '名称',width:100,align:'center'}
			 		              ,{field:'idCard', title: '身份证号',width:170,align:'center'}
			 		              ,{field:'violationTimes',title: '违规次数', width:90,align:'center'}
			 		              ,{field:'totalAmount',title: '违规总金额', width:125,align:'center'}
			 		             
			 	            ]]
			 	            ,page: true
			 	     	  ,done:function my(left){	 
			 				  $(document).find('[data-index="0"]').trigger("click");		
			 				  $(document).find('[data-index="0"]').css("background-color","#C0C0C0");
			 				 var data=left["data"]; 
			 				 if(data.length==0){
			 		 			  document.getElementById('tableTitle').innerHTML="<span style='padding-left: 250px;height: 35px;font-size: 19px;color:black;line-height: 40px;'>参保人暂无违规记录</span>";	 	
			 				 }else{
			 				  document.getElementById('tableTitle').innerHTML=" <span style='padding-left: 250px;'>参保人违规记录  </span><span style='font-size:11px;color:red;' >*以违规次数作为排名标准</span> ";	 	
				 		 	  document.getElementById('msg').innerHTML='<span style="font-size:11px;color:red;float: right; margin-right: 100px;margin-top: 5px">*违规日期以就诊费用发生日期为准</span>'

			 				 }
			 	     	  }
			 	          });
			  }else if(value==3){
					//药店
				  myUrl=$WEB_ROOT_PATH+'/dhccApi/blackList/blackList/listViolationTimes';
				  table.render({
			 	    	elem: '#dataTable'
			 	    		,url: myUrl
			 	    		,where: { "blackListVo.type": '3'}
			 	    		,height: document.documentElement.clientHeight-115
			 	            ,cols: [[
			 	            	   {type: 'numbers',width:40, title: '序号',fixed: 'left'}
			 		              ,{field:'name', title: '名称',width:288,align:'center'}
			 		              ,{field:'violationTimes',title: '违规次数', width:110,align:'center'}
			 		             
			 	            ]]
			 	            ,page: true
			 	     	  ,done:function my(left){	 
			 				  $(document).find('[data-index="0"]').trigger("click");		
			 				  $(document).find('[data-index="0"]').css("background-color","#C0C0C0");
			 				 var data=left["data"]; 
			 				 if(data.length==0){
			 		 			  document.getElementById('tableTitle').innerHTML="<span style='padding-left: 250px;height: 35px;font-size: 19px;color:black;line-height: 40px;'>药店暂无违规记录</span>";	 	
			 				 }else{
			 				  document.getElementById('tableTitle').innerHTML=" <span style='padding-left: 250px;'>药店违规记录  </span><span style='font-size:11px;color:red;' >*以违规次数作为排名标准</span> ";	 	
				 		 	  document.getElementById('msg').innerHTML='<span style="font-size:11px;color:red;float: right; margin-right: 100px; margin-top: 5px">*违规日期以就诊费用发生日期为准</span>'

			 				 }
			 	     	  }
			 	        
			 	          });
			  }else if(value==4){
					//医生
				  myUrl=$WEB_ROOT_PATH+'/dhccApi/blackList/blackList/listViolationTimes';
				  table.render({
			 	    	elem: '#dataTable'
			 	    		,url: myUrl
			 	    		,height: document.documentElement.clientHeight-115
			 	    		,where: { "blackListVo.type": '4'}
			 	            ,cols: [[
			 	            	   {type: 'numbers',width:40, title: '序号',fixed: 'left'}
			 		              ,{field:'name', title: '名称',width:288,align:'center'}
			 		              ,{field:'violationTimes',title: '违规次数', width:110,align:'center'}
			 		             
			 	            ]]
			 	           ,page: true
			 	     	  ,done:function my(left){	 
			 				  $(document).find('[data-index="0"]').trigger("click");		
			 				  $(document).find('[data-index="0"]').css("background-color","#C0C0C0");
			 				 var data=left["data"]; 
			 				 if(data.length==0){
			 		 			  document.getElementById('tableTitle').innerHTML="<span style='padding-left: 250px;height: 35px;font-size: 19px;color:black;line-height: 40px;'>医生暂无违规记录</span>";	 	
			 				 }else{
			 				  document.getElementById('tableTitle').innerHTML=" <span style='padding-left: 250px;'>医院违规记录  </span><span style='font-size:11px;color:red;' >*以违规次数作为排名标准</span> ";	 	
				 		 	  document.getElementById('msg').innerHTML='<span style="font-size:11px;color:red;float: right; margin-right: 100px; margin-top: 5px">*违规日期以就诊费用发生日期为准</span>'

			 				 }
			 	     	  }
			 	        
			 	          });
			  }
			  table.reload('dataTable');
		  });   
		  //行监听
		  table.on('row(dataTable)', function(obj){	
			  $("tr").css("background-color",""); 
			  obj.tr.css("background-color","#C0C0C0");
              var myUrl; 
              var date=obj.data;
              console.log(date);
              var name=date.name;
              var idCard=date.idCard;
              var orgCode=date.orgCode;
              var violationDate=date.violationDate;
              var orgName=date.orgName;
			  var type=$("#type").val();
			  if(type==1){
				  myUrl=$WEB_ROOT_PATH+'/dhccApi/blackList/blackList/listAllDetail?blackListVo.type='+type+'&blackListVo.orgCode='+orgCode+'&blackListVo.violationDate='+violationDate;
				    $.getJSON(myUrl,function(violationData){
				    	console.log(violationData);
				    	var option = {
			 	   		    title : {
			 	   		        text: violationDate+' 年  '+orgName+"违规记录",
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
			 	   		        {  name:'违规次数',
			 	   		            type : 'value'
			 	   		            ,axisLine:false	
			 	   		        }
			 	   		    ],
			 	   		    series : [
			 	   		        {
			 	   		            
			 	   		            type:'line',
			 	   		            data:[violationData[0],
			 	   		            violationData[1],
			 	   		            violationData[2],
			 	   		            violationData[3],
			 	   		            violationData[4],
			 	   		            violationData[5],
			 	   		            violationData[6],
			 	   		            violationData[7],
			 	   		            violationData[8],
			 	   		            violationData[9],
			 	   		            violationData[10],
			 	   		            violationData[11],
			 	   		            	],
			 	   		            markPoint : {
			 	   		            symbolSize: 4,
			 	   	         		itemStyle:{
			 	   	         			normal:{
			 	   	         				label:{ 
			 	   	         					show: true,  
			 	                                   color: '#000000',//气泡中字体颜色
			 	   	         				}
			 	   	         			}
			 	   	         		},
			 	   		                data : [
			 	   		                    {   name : '违规总金额', value: violationData[12],xAxis:'1月',yAxis:violationData[0]},
			 	   		                    {	name : '违规总金额', value: violationData[13],xAxis:'2月',yAxis:violationData[1]},
			 	   		                    {	name : '违规总金额', value: violationData[14],xAxis:'3月',yAxis:violationData[2]},
			 	   		                    {	name : '违规总金额', value: violationData[15],xAxis:'4月',yAxis:violationData[3]},
			 	   		                    {	name : '违规总金额', value: violationData[16],xAxis:'5月',yAxis:violationData[4]},
			 	   		                    {	name : '违规总金额', value: violationData[17],xAxis:'6月',yAxis:violationData[5]},
			 	   		                    {	name : '违规总金额', value: violationData[18],xAxis:'7月',yAxis:violationData[6]},
			 	   		                    {	name : '违规总金额', value: violationData[19],xAxis:'8月',yAxis:violationData[7]},
			 	   		                    { 	name : '违规总金额', value: violationData[20],xAxis:'9月',yAxis:violationData[8]},
			 	   		                    { 	name : '违规总金额', value: violationData[21],xAxis:'10月',yAxis:violationData[9]},
			 	   		                    {	name : '违规总金额', value: violationData[22],xAxis:'11月',yAxis:violationData[10]},
			 	   		                    {	name : '违规总金额', value: violationData[23],xAxis:'12月',yAxis:violationData[11]}			 	   		              			 	   		                   
			 	   		                ]
			 	   		            }
			 	   		        },
			 	   		    ]
			 	   		};
			 
			 	   	        echarts.setOption(option); 
			 	              
				  });
			  }else if(type==2){
				  //待解决   目前只显示一个数据
				  myUrl=$WEB_ROOT_PATH+'/dhccApi/blackList/blackList/listAllDetail?blackListVo.type='+type+'&blackListVo.name='+name+'&blackListVo.idCard='+idCard;
				    $.getJSON(myUrl,function(violationData){
				    	var size=violationData.length;
				    	
				    	var option2 = {
			 	   		    title : {
			 	   		        text: name+"   违规记录",
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
			 	   		            data : [violationData[0]]
			 	   		            ,axisLine:false	
			 	   		            
			 	   		        }
			 	   		    ],
			 	   		    yAxis : [
			 	   		        {   name:'违规金额',
			 	   		            type : 'value'
			 	   		        }
			 	   		    ],
			 	   		    series : [
			 	   		        {
			 	   		            
			 	   		            type:'bar',
			 	   		            data:[violationData[1]],
			 	   		            barMaxWidth:20 ,
			 	   		            markPoint : {
			 	   		                data : [
			 	   		                  
			 	   		                ]
			 	   		            }
			 	   		        },
			 	   		    ]
			 	   		};
			 
			 	   	        echarts.setOption(option2); 
				  });
			  }
		   });
 });

