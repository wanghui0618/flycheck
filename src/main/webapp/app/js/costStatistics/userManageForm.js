//初始化	
 var medical_idCard;
  var medical_insuranceCode;
  var flag = true;
  function child(obj){
	  var person = JSON.parse(obj);
	  medical_idCard=person.idCard;
	  medical_insuranceCode=person.insuranceCode;
	  for (var index in person){
		  if(person.sex=='1'){
			  person.sex='男'
		  }else if(person.sex=='0'){
			  person.sex='女'
		  }else if(person.sex=='2'){
			  person.sex=''
		  }else{
			  person.sex=person.sex
		  };
		  if(person.cityName==''||person.cityName==null){
			  person.cityName='北京市'
		  };
		  if(person.idCardType==''||person.idCardType==1){
			  person.idCardType='身份证号'
		  }
		  	     $("#" + index).html(person[index]);
		  }
	  $.ajax({
			url:$WEB_ROOT_PATH+'/dhccApi/medical/medical/userManage?medical.insurePersonCode='+ medical_insuranceCode,
			type:"post",
			async: true,
			datatype:"json",
			success: function(result){
				var tboy=$("#hospital");
		    	var mm;
				var  dataList= result;
				var count=0;
				if(dataList.length ==0){
					var tr = document.createElement("tr");
					tr.innHTML="<td style='width: 55%;' colspan='3' align='center'>"+"无数据"+"</td>";
					mm+="<tr style='background:#fff;height:212px'>"+tr.innHTML+"</tr>";
				}else{
					if(dataList.length < 5){
						for(var i=0 ;i<dataList.length;i++){
							var tr = document.createElement("tr");
							if(dataList[i].orgName!=null){							
								var orgName = dataList[i].orgName;
								if(orgName.length > 9){
									orgName = orgName.substr(0,7) + "...";
								}
								
								tr.innHTML="<td style='width: 55%'>"+orgName+"</td>"+"<td style='width: 19%'>"+dataList[i].orgNum+"</td>"+"<td style='width: 26%'>"+ dataList[i].totalMoney+"</td>";
								if(count%2==0){
									mm+="<tr style='background:#fff'>"+tr.innHTML+"</tr>";
								}else{
									mm+="<tr style='background:#F5FCF9'>"+tr.innHTML+"</tr>";
								}							
							}
							count++;
					    }
						for(var i=0 ; i<(5-dataList.length);i++){
							var tr = document.createElement("tr");
								tr.innHTML="<td style='width: 55%;'>"+"</td>"+"<td style='width: 19%'>"+" "+"</td>"+"<td style='width: 26%'>"+"</td>";
								if(count%2==0){
									mm+="<tr style='background:#fff;height:42.4px'>"+tr.innHTML+"</tr>";
								}else{
									mm+="<tr style='background:#F5FCF9;height:42.4px'>"+tr.innHTML+"</tr>";
								}							
							
							count++;
						}
					}else{
						for(var i=0 ;i<5;i++){
							if(dataList[i].orgName!=null){
								var tr = document.createElement("tr");
								var orgName = dataList[i].orgName;
								if(orgName.length > 9){
									orgName = orgName.substr(0,7) + "...";
								}
								
								tr.innHTML="<td style='width: 55%'>"+orgName+"</td>"+"<td style='width: 19%'>"+dataList[i].orgNum+"</td>"+"<td style='width: 26%'>"+ dataList[i].totalMoney+"</td>";
								if(count%2==0){
									mm+="<tr style='background:#fff'>"+tr.innHTML+"</tr>";
								}else{
									mm+="<tr style='background:#F5FCF9'>"+tr.innHTML+"</tr>";
								}
								count++;
							}
					    }
					}
				}

				
				tboy.html(mm);
				
			}
					
		});
	  $.ajax({
			url:$WEB_ROOT_PATH+'/dhccApi/medical/medical/userDiagnose?medical.insurePersonCode='+ medical_insuranceCode,
			type:"post",
			async: true,
			datatype:"json",
			success: function(result){
				var tboy=$("#diagnose");
		    	var mm;
				var  dataList= result;
				var count=0;
				if(dataList.length == 0){
					var tr = document.createElement("tr");
					tr.innHTML="<td style='width: 55%;' colspan='3' align='center'>"+"无数据"+"</td>";
					mm+="<tr style='background:#fff;height:212px'>"+tr.innHTML+"</tr>";
				}else{
					if(dataList.length < 5){
						for(var i=0 ;i<dataList.length;i++){
							var tr = document.createElement("tr");
							if(dataList[i].condition!=null){						
								var condition = dataList[i].condition;
								if(condition.length > 9){
									condition = condition.substr(0,7) + "...";
								}						
								tr.innHTML="<td style='width: 55%'>"+condition+"</td>"+"<td style='width: 19%'>"+dataList[i].conditionNum+"</td>"+"<td style='width: 26%'>"+ dataList[i].conditionCost+"</td>";
								if(count%2==0){
									mm+="<tr style='background:#fff'>"+tr.innHTML+"</tr>";
								}else{
									mm+="<tr style='background:#F5FCF9'>"+tr.innHTML+"</tr>";
								}
								count++;
							}	
								
							}
						for(var i=0 ; i<(5-dataList.length);i++){
							var tr = document.createElement("tr");
								tr.innHTML="<td style='width: 55%;'>"+"</td>"+"<td style='width: 19%'>"+" "+"</td>"+"<td style='width: 26%'>"+"</td>";
								if(count%2==0){
									mm+="<tr style='background:#fff;height:42.4px'>"+tr.innHTML+"</tr>";
								}else{
									mm+="<tr style='background:#F5FCF9;height:42.4px'>"+tr.innHTML+"</tr>";
								}							
							
							count++;
						}
					    }else{
							for(var i=0 ;i<5;i++){
								var tr = document.createElement("tr");
								if(dataList[i].condition!=null){						
									var condition = dataList[i].condition;
									if(condition.length > 9){
										condition = condition.substr(0,7) + "...";
									}						
									tr.innHTML="<td style='width: 55%'>"+condition+"</td>"+"<td style='width: 19%'>"+dataList[i].conditionNum+"</td>"+"<td style='width: 26%'>"+ dataList[i].conditionCost+"</td>";
									if(count%2==0){
										mm+="<tr style='background:#fff'>"+tr.innHTML+"</tr>";
									}else{
										mm+="<tr style='background:#F5FCF9'>"+tr.innHTML+"</tr>";
									}
									count++;
								}	
									
								}
					    }
				}

				

				tboy.html(mm);
				
			}
					
		});	
	  if(layui.table != undefined && flag){
			getload();  
		  };
  }
function getdetail(id){
	var medicalId=id;
	if(id==0){
		var tboy=$("#detail1");
		var tboy1=$("#drug");
		var mm;
		
		var count=0;
		var tr = document.createElement("tr");
		tr.innHTML="<td style='width: 55%;' colspan='3' align='center'>"+"无数据"+"</td>";
		mm+="<tr style='background:#fff;height:212px'>"+tr.innHTML+"</tr>";
		tboy.html(mm);
		tboy1.html(mm);
	}else{ 
		$.ajax({
		url:$WEB_ROOT_PATH+'/dhccApi/medicaldetail/medicalDetail/userManageDetail?medicalDetail.medicalId='+ medicalId,
		type:"post",
		async: true,
		datatype:"json",
		success: function(result){
			var tboy=$("#detail1");
			tboy.empty();
	    	var mm;
			var  dataList= result;
			var count=0;
			if(dataList.length ==0){
				var tr = document.createElement("tr");
				tr.innHTML="<td style='width: 55%;' colspan='3' align='center'>"+"无数据"+"</td>";
				mm+="<tr style='background:#fff;height:212px'>"+tr.innHTML+"</tr>";
			}else{
				if(dataList.length < 5){
					for(var i=0 ;i<dataList.length;i++){
						var tr = document.createElement("tr");
						if(dataList[i].itemName!=null){						
							var itemName = dataList[i].itemName;
							if(itemName.length > 9){
								itemName = itemName.substr(0,7) + "...";
							}						
							tr.innHTML="<td style='width: 55%'>"+itemName+"</td>"+"<td style='width: 19%'>"+dataList[i].itemNumber+"</td>"+"<td style='width: 26%'>"+ dataList[i].totalMoney+"</td>";
							if(count%2==0){
								mm+="<tr style='background:#fff'>"+tr.innHTML+"</tr>";
							}else{
								mm+="<tr style='background:#F5FCF9'>"+tr.innHTML+"</tr>";
							}
							count++;
						}	
							
						}
					for(var i=0 ; i<(5-dataList.length);i++){
						var tr = document.createElement("tr");
							tr.innHTML="<td style='width: 55%;'>"+"</td>"+"<td style='width: 19%'>"+" "+"</td>"+"<td style='width: 26%'>"+"</td>";
							if(count%2==0){
								mm+="<tr style='background:#fff;height:42.4px'>"+tr.innHTML+"</tr>";
							}else{
								mm+="<tr style='background:#F5FCF9;height:42.4px'>"+tr.innHTML+"</tr>";
							}							
						
						count++;
					}
				    }else{
						for(var i=0 ;i<5;i++){
							var tr = document.createElement("tr");
							if(dataList[i].itemName!=null){						
								var itemName = dataList[i].itemName;
								if(itemName.length > 9){
									itemName = itemName.substr(0,7) + "...";
								}						
								tr.innHTML="<td style='width: 55%'>"+itemName+"</td>"+"<td style='width: 19%'>"+dataList[i].itemNumber+"</td>"+"<td style='width: 26%'>"+ dataList[i].totalMoney+"</td>";
								if(count%2==0){
									mm+="<tr style='background:#fff'>"+tr.innHTML+"</tr>";
								}else{
									mm+="<tr style='background:#F5FCF9'>"+tr.innHTML+"</tr>";
								}
								count++;
							}	
								
							}
				    }
			}

			

			tboy.html(mm);
			
		}
				
	});
	$.ajax({
		url:$WEB_ROOT_PATH+'/dhccApi/medicaldetail/medicalDetail/userManageDrug?medicalDetail.medicalId='+ medicalId,
		type:"post",
		async: true,
		datatype:"json",
		success: function(result){
			var tboy=$("#drug");
			tboy.empty();
	    	var mm;
			var  dataList= result;
			var count=0;
			/*var number=dataList.length;*/
			if(dataList.length == 0){
				var tr = document.createElement("tr");
				tr.innHTML="<td style='width: 55%;' colspan='3' align='center'>"+"无数据"+"</td>";
				mm+="<tr style='background:#fff;height:212px'>"+tr.innHTML+"</tr>";
			}else{
				if(dataList.length < 5){
					for(var i=0 ;i<dataList.length;i++){
						var tr = document.createElement("tr");
						if(dataList[i].chargeType!=null){						
							var chargeType = dataList[i].chargeType;
							if(chargeType.length > 9){
								chargeType = chargeType.substr(0,7) + "...";
							}						
							tr.innHTML="<td style='width: 55%'>"+chargeType+"</td>"+"<td style='width: 19%'>"+dataList[i].chargeNum+"</td>"+"<td style='width: 26%'>"+ dataList[i].totalMoney+"</td>";
							if(count%2==0){
								mm+="<tr style='background:#fff'>"+tr.innHTML+"</tr>";
							}else{
								mm+="<tr style='background:#F5FCF9'>"+tr.innHTML+"</tr>";
							}
							count++;
						}else{
							tr.innHTML="<td style='width: 55%;'>"+"</td>"+"<td style='width: 19%'>"+" "+"</td>"+"<td style='width: 26%'>"+"</td>";
							if(count%2==0){
								mm+="<tr style='background:#fff;height:42.4px'>"+tr.innHTML+"</tr>";
							}else{
								mm+="<tr style='background:#F5FCF9;height:42.4px'>"+tr.innHTML+"</tr>";
							}							
						
						count++;}	
							
						}
					for(var i=0 ; i<(5-dataList.length);i++){
						var tr = document.createElement("tr");
							tr.innHTML="<td style='width: 55%;'>"+"</td>"+"<td style='width: 19%'>"+" "+"</td>"+"<td style='width: 26%'>"+"</td>";
							if(count%2==0){
								mm+="<tr style='background:#fff;height:42.4px'>"+tr.innHTML+"</tr>";
							}else{
								mm+="<tr style='background:#F5FCF9;height:42.4px'>"+tr.innHTML+"</tr>";
							}							
						
						count++;
					}
				    }else{
						for(var i=0 ;i<5;i++){
							var tr = document.createElement("tr");
							if(dataList[i].chargeType!=null){						
								var chargeType = dataList[i].chargeType;
								if(chargeType.length > 9){
									chargeType = chargeType.substr(0,7) + "...";
								}						
								tr.innHTML="<td style='width: 55%'>"+chargeType+"</td>"+"<td style='width: 19%'>"+dataList[i].chargeNum+"</td>"+"<td style='width: 26%'>"+ dataList[i].totalMoney+"</td>";
								if(count%2==0){
									mm+="<tr style='background:#fff'>"+tr.innHTML+"</tr>";
								}else{
									mm+="<tr style='background:#F5FCF9'>"+tr.innHTML+"</tr>";
								}
								count++;
							}	
								
							}
				    }
			}

			

			tboy.html(mm);
			
		}
				
	});
	
	}
	
	 
	  
}
function getload(){
    var $ = layui.$
    ,form = layui.form
    ,table = layui.table;
    
    var laydate = layui.laydate;
    var BalanceDate=[];
	var cost=[];
	       
    laydate.render({
    	elem: '#balanceDate'
    		,trigger:'click'
    			,format:'yyyy-MM-dd'
    				,range: true
    });
    table.render({
    	elem: '#menzhenTable'
            ,url: $WEB_ROOT_PATH+'/dhccApi/medical/medical/listDiag2'
            ,cellMinWidth: 80
            ,height: 420
            ,where: { ilegalChild: '1' ,'medical.insurePersonCode':medical_insuranceCode}
            ,cols: [[
            	{type: 'numbers', width:80, title: '编号'} 	
            	,{field:'id', title: 'ID', sort: true, hide:true}
	              ,{field:'orgName',  width: 220, align:'center', title: '就诊机构'}
	              ,{field:'inhosDate',  width: 150, align:'center', title: '就诊日期'}
	              ,{field:'balanceDate',  width: 150, align:'center', title: '结算日期'}
	              ,{field:'totalCost', width: 120, align:'center', title: '总金额'}
	              ,{field:'fundCost',width: 120,align:'center', title: '报销金额'}
	              ,{field:'selfCost', width: 120, align:'center', title: '个人负担金额'}
	              ,{field:'basicCostM', width: 120, align:'center', title: '统筹应付'}
	              ,{field:'seeDocType', width: 140, align:'center', title: '医疗类别'}
	              ,{field:'condition', width: 180, align:'center', title: '诊断名称'}
	              ,{field:'finaStatus', width: 120,align:'center', title: '终审状态'}		         
	              ,{field:'applyPayLevel', width: 110, align:'center', title: '医生'}
	              ,{field:'departName', width: 170, align:'center', title: '科室'}
	              
            ]]
            ,done:function(data){
            	if(data.count==0){
            		var id=0;
            		 getdetail(id);
            	}
            	var id=data.data['0']['id'];
   	            medicalId=id;
   	         	var index=-1;
   	            //分类显示中文名称
   	            $("[data-field='id']").children().each(function () {
   	            index++;
   	         
   	            if ($(this).text() == data.data['0']['id']) {
   	              $('tr').eq(index).css("background-color","#F2F2F2");
   	              
   	              getdetail(id);
   	                }
   	            
   	             });}
            ,page: true
          });
    table.on('row(menzhenTable)', function (obj){
    	 var data = obj.data;
            var id = data.id;
           // alert('已选中患者为'+data.patientName);
            medicalId=data.id;	            
            $("tr").css("background-color",""); 
            $(this).css("background-color","#F2F2F2");
            getdetail(id);
            });
  
    table.render({
    	elem: '#zhuyuanTable'
            ,url: $WEB_ROOT_PATH+'/dhccApi/medical/medical/listDiag1'
            ,cellMinWidth: 80
            ,height: 420
            ,where: { ilegalChild: '1' ,'medical.insurePersonCode':medical_insuranceCode}
            ,cols: [[
            	{type: 'numbers', width:80, title: '编号'} 	
            	,{field:'id', title: 'ID', sort: true, hide:true}
	              ,{field:'orgName',  width: 220, align:'center', title: '就诊机构'}
	              ,{field:'inhosDate',  width: 150, align:'center', title: '入院日期'}
	              ,{field:'outhosDate',  width: 150, align:'center', title: '出院日期'}
	              ,{field:'balanceDate',  width: 150, align:'center', title: '结算日期'}
	              ,{field:'totalCost', width: 120, align:'center', title: '总金额'}
	              ,{field:'fundCost',width: 120,align:'center', title: '报销金额'}
	              ,{field:'selfCost', width: 120, align:'center', title: '个人负担金额'}
	              ,{field:'basicCostM', width: 120, align:'center', title: '统筹应付'}
	              ,{field:'seeDocType', width: 140, align:'center', title: '医疗类别'}
	              ,{field:'condition', width: 180, align:'center', title: '诊断名称'}
	              ,{field:'finaStatus', width: 120,align:'center', title: '终审状态'}		         
	              ,{field:'applyPayLevel', width: 110, align:'center', title: '医生'}
	              ,{field:'departName', width: 170, align:'center', title: '科室'}
            ]]
            ,done:function(data){
            	if(data.count==0){
            		var id=0;
            		 getdetail(id);
            	}
            	var id=data.data['0']['id'];
   	            medicalId=id;
   	         	var index=-1;
   	            //分类显示中文名称
   	            $("[data-field='id']").children().each(function () {
   	            index++;
   	            if ($(this).text() == data.data['0']['id']) {
   	              $('tr').eq(index).css("background-color","#F2F2F2");
   	              getdetail(id);
   	                }	   	            
   	             });}
            ,page: true
          });
    table.on('row(zhuyuanTable)', function (obj){
    	 var data = obj.data;
            var id = data.id;
           // alert('已选中患者为'+data.patientName);
            medicalId=data.id;	            
            $("tr").css("background-color",""); 
            $(this).css("background-color","#F2F2F2");
            getdetail(id);
            });
    table.render({
    	elem: '#weiguiTable'
    		 ,url: $WEB_ROOT_PATH+'/dhccApi/medical/medical/listDiag3'
	            ,cellMinWidth: 80
	            ,height: 420
	            ,where: { ilegalChild: '1' ,'medical.insurePersonCode':medical_insuranceCode}
            ,cols: [[
            	{type: 'numbers', width:80, title: '编号'} 	
            	,{field:'id', title: 'ID', sort: true, hide:true}
	              ,{field:'orgName',  width: 220, align:'center', title: '就诊机构'}
	              ,{field:'inhosDate',  width: 150, align:'center', title: '就诊日期'}
	              ,{field:'balanceDate',  width: 150, align:'center', title: '结算日期'}
	              ,{field:'totalCost', width: 120, align:'center', title: '总金额'}
	              ,{field:'fundCost',width: 120,align:'center', title: '报销金额'}
	              ,{field:'selfCost', width: 120, align:'center', title: '个人负担金额'}
	              ,{field:'basicCostM', width: 120, align:'center', title: '统筹应付'}
	              ,{field:'seeDocType', width: 140, align:'center', title: '医疗类别'}
	              ,{field:'condition', width: 180, align:'center', title: '诊断名称'}
	              ,{field:'finaStatus', width: 120,align:'center', title: '终审状态'}		         
	              ,{field:'applyPayLevel', width: 110, align:'center', title: '医生'}
	              ,{field:'departName', width: 170, align:'center', title: '科室'}
            ]]
            ,done:function(data){
            	if(data.count==0){
            		var id=0;
            		 getdetail(id);
            	}
            	var id=data.data['0']['id'];
   	            medicalId=id;
   	         	var index=-1;
   	            //分类显示中文名称
   	            $("[data-field='id']").children().each(function () {
   	            index++;
   	            if ($(this).text() == data.data['0']['id']) {
   	              $('tr').eq(index).css("background-color","#F2F2F2");
   	              getdetail(id);
   	                }
   	             });}
            ,page: true
          });
    
    table.on('row(weiguiTable)', function (obj){
    	 var data = obj.data;
            var id = data.id;
           // alert('已选中患者为'+data.patientName);
            medicalId=data.id;	            
            $("tr").css("background-color",""); 
            $(this).css("background-color","#F2F2F2");
            getdetail(id);
            });

    
    require.config({
	    paths: {
	        echarts: $WEB_ROOT_PATH+'/js/echarts_jpp/echarts-2.2.7/build/dist'
	    }
	});
	////折线图
	require(
		 	[
		     	'echarts',
		     	'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
		 	],
		 	function (ec) {
	            // 基于准备好的dom，初始化echarts实例
		 		var myChart = echarts.init(document.getElementById('main'));
			    $.ajax({
					url:$WEB_ROOT_PATH+'/dhccApi/medical/medical/listDiag1?page=0&limit=20&ilegalChild=1&medical.insurePersonCode='+ medical_insuranceCode,
					type:"post",
					async: false,
					datatype:"json",
					success: function(result){
						var json=result.data;
						json.sort(function(a,b){
							return Date.parse(a.balanceDate) - Date.parse(b.balanceDate);
						});				
						for(var i=0;i<json.length;i++){
							BalanceDate.push(json[i].balanceDate);
							cost.push(json[i].totalCost)
						}	   						
					}   			    
			   });
			    
	    myChart.setOption({

            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: BalanceDate
            },
            yAxis: {
                type: 'value'
            },
            grid: {
                x:  50,
                x2: 15,
                y: 30,
                y2: 30
            },
            series: [{
                data: cost,
                
                type: 'line',
                /*areaStyle: {
                  normal: {
               	    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                          { offset: 0, color: "LightSalmon" },
                          { offset: 0.5, color: "pink" },
                          { offset: 1, color: "Seashell" }
                        ])
                         color:'#E77D65' 
                  }
                },*/
                itemStyle : { 
               	 normal: {
               		 label : {
               			 show: true,
               			 textStyle: {
               			        color: '#E77D65',
               			        fontSize:'9',
               			    }
               			 }
                    }
                },
                
            }]
	    });
	}
);
    
    //监听搜索
	form.on('submit(LAY-user-front-search)', function(data){
    	var field = data.field;
        //执行重载
        layui.table.reload('menzhenTable', {
            where: field
        });
        layui.table.reload('zhuyuanTable', {
            where: field
        });
        layui.table.reload('weiguiTable', {
            where: field
        });
    });
}
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table','laydate','form','element'], function(){
		  if(medical_insuranceCode != undefined){
			  flag=false;
			  getload();
		  }


	 
		
		    
		    
		    //按钮事件绑定底层方法-勿动
		    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
		      var type = $(this).data('type');
		      active[type] ? active[type].call(this) : '';
		    });
		  });