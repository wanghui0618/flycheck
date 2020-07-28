//初始化	
var indexAll;
var medicalId;
function GetRequest() {
	var url = location.search; //获取url中"?"符后的字串 
	var theRequest = new Object();
	if (url.indexOf("?") != -1) {
	var str = url.substr(1);
	strs = str.split("&");
	for (var i = 0; i < strs.length; i++) {
	theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
	}
	}
	return theRequest;
}
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table','laydate','form'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;
	    var laydate = layui.laydate;
	    
	    var Request = GetRequest()
	    var orgCode = Request["orgCode"];
	    var type = Request["type"];
	   
	    
	  //日期范围
	    laydate.render({
	      elem: '#finaTime'
	    	  ,trigger:'click'
						,format:'yyyy-MM-dd'
							,range: true
	    });
	    
	    

	    table.render({
	    	elem: '#userTable'
	            ,url: $WEB_ROOT_PATH+'/dhccApi/medical/medical/listVo'
	            ,cellMinWidth: 100
	             ,height: 215
	             ,where:{"medical.orgCode":orgCode,"type":type}
	            ,cols: [[
	            	   {type: 'numbers', title: '序号' }
	            	   ,{field:'left', title:'操作', toolbar: '#table-useradmin-webuser', width:90}
	            	   ,{field:'left', title:'稽核', toolbar: '#table-useradmin-webuser2', width:90}
		              ,{field:'id', title: 'ID', sort: true, hide:true}
		              ,{field:'status', title:'数据校验标识' }
		              ,{field:'sysStatus', title:'机审结果状态' }
		              ,{field:'userStatus', title:'人工审核结果状态' }
		              ,{field:'finaStatus', title:'终审结果状态' }
		              ,{field:'orgCode', width:90,title:'医疗机构编码'}
		              ,{field:'billingNo',width:90, title:'收费单据号'}
		              ,{field:'cityCode', title:'城市编码' }
		              ,{field:'orgName', title:'城市名' }
		              ,{field:'name', width:120, title:'姓名'}
		              ,{field:'sex', title: '性别' }
		              ,{field:'idcard', title:'身份证号' }
		              ,{field:'sscno',  width:115,title: '社保卡号' }
		              ,{field:'paymentDate',width:90, title:'费用发生日期'}
		              ,{field:'reversalMark',width:115, title:'冲销标志'}
		              ,{field:'admissionNo', title:'住院号' }
		              ,{field:'admissionType',  width:90,title: '住院类型' }
		              ,{field:'medicalType',  width:90,title: '险种类型' }
		              ,{field:'condition',  width:90,title: '病情' }
		              ,{field:'departName', title:'科室' }
		              ,{field:'inhosDate', title:'入院日期' }
		              ,{field:'outhosDate', title:'出院日期' }
		              ,{field:'totalCost', title:'总金额' }
		              ,{field:'fundCost', title:'基金支付金额' }
		              ,{field:'selfCost', title:'个人负担金额' }
		              ,{field:'basicCostM', width:90, title:'基本统筹应支付' }
		              ,{field:'povertyAlleviationSubsidy',  width:90,title:'扶贫补助' }
		              ,{field:'financeSubsidy', title:'财政补助' }
		              ,{field:'officialSubsidy',  width:115,title:'公务员补助' }
		              ,{field:'treatmentType',  width:115,title:'待遇享受类别' }
		              ,{field:'medicalTreatmentState',  width:115,title:'医疗待遇状态' }
		              ,{field:'dischargeState', title:'出院状态' }
		              ,{field:'treatmentWay', title:'就诊方式' }
		              ,{field:'age', title:'年龄' }
		              ,{field:'stayLength', title:'住院天数' }
		              ,{field:'createTime', title:'数据上传时间' }
		              ,{field:'rangeCost', title:'本次纳入报销范围金额' }
		              ,{field:'basicCostR', title:'基本统筹实际支付' }
		              ,{field:'selfPayAmount', title:'个人自付金额' }
		              ,{field:'selfExpenditureAmount', title:'个人自费金额' }
		              ,{field:'sscAccountCost', title:'个人账户自付' }
		              ,{field:'cashCost', title:'现金支付金额' }
		              ,{field:'largeCostM', title:'大额应支付' }
		              ,{field:'largeCostR', title:'大额实支付' }
		              ,{field:'civilAffairSubsidy', title:'民政救助' }
		              ,{field:'fullOrdination', title:'全额统筹' }
		              ,{field:'partialOrdination', title:'部分统筹' }
		              ,{field:'partialPayment', title:'部分自付' }
		              ,{field:'fullPayment', title:'全额自付' }
		              ,{field:'crowdType', title:'人群类别' }
		              ,{field:'reimbursementType', title:'报销类型' }
		              ,{field:'hospCount', title:'住院次数' }
		              ,{field:'insuranceMark', title:'险种标志' }
		              ,{field:'inDiagnosisNo', title:'入院诊断编码' }
		              ,{field:'inDiagnosisName', title:'入院诊断名称' }
		              ,{field:'outDiagnosisNo', title:'出院诊断编码' }
		              ,{field:'outDiagnosisName', title:'出院诊断名称' }
		              ,{field:'diagType', title:'就诊类型' }
		           
		              
		            ]]
		            ,done:function(data){
		            	var id=data.data['0']['id'];
		   	            medicalId=id;
		   	         	var index=-1;
		   	            //分类显示中文名称
		   	            $("[data-field='id']").children().each(function () {
		   	            index++;
		   	            if ($(this).text() == data.data['0']['id']) {
		   	              $('tr').eq(index).css("background-color","#C0C0C0");
		   	                }
		   	             });
		   	            
		   	            var url=$WEB_ROOT_PATH+"/dhccApi/medicalexamine/medicalExamine/findData?medicalId1="+medicalId;
		   	            $.post(url,{"medicalExamine.medicalId":data.data['0']['id']},function(result){
		   	        	  if(result.medicalExamine['status']!=null || result.medicalExamine['costMoney']!=null || result.medicalExamine['comments']!=null){
		   	        		  $("#ssd").hide();
		   	        		  $("#ssd1").hide();
		   	        		  $("#ssd2").show();
		   	        		  document.getElementById("cost").readOnly=true;
		   	        		  document.getElementById("comm").readOnly=true;
		   	        		  document.getElementById("commm").readOnly=true;
		   	        		//radio标签动态选中
		   	        		if(result.medicalExamine['status']==null){
			   	        		$("input[name='medicalExamine.status']").get(result.medicalExamine['status']).checked=false;
			   	        		$('#status').attr("disabled",false);
			   	        		$('#status2').attr("disabled",false);
			   	        	  }else if(result.medicalExamine['status']!=null){
			   	        		$("input[name='medicalExamine.status']").get(result.medicalExamine['status']).checked=true;
			   	        		$('#status').attr("disabled",true);
			   	        		$('#status2').attr("disabled",true);
			   	        	  }
		   	        	  }else if(result.medicalExamine['status']==null && result.medicalExamine['costMoney']==null && result.medicalExamine['comments']==null){
		   	        		  $("#ssd").show();
		   	        		  $("#ssd1").show();
		   	        		  $("#ssd2").hide();
		   	        		  document.getElementById("cost").readOnly=false;
		   	        		  document.getElementById("comm").readOnly=false;
		   	        		  document.getElementById("commm").readOnly=false;
		   	        		$('#status').attr("disabled",false);
		   	        		$('#status2').attr("disabled",false);
		   	        		$("input[name='medicalExamine.status']").get(result.medicalExamine['status']).checked=false;
		   	        	  }
		   	        	  
		   	        	  
		   	        	  
		   	        	  //type=text标签动态赋值
		   	        	  document.getElementsByName("medicalExamine.costMoney")[0].value = result.medicalExamine['costMoney'];
		   	        	  //审核意见动态赋值
		   	        	  form.val("layuiadmin-form-useradmin", {
		   		    		  // "name": "value"
		   		    		  "medicalExamine.comments":result.medicalExamine['comments']
		   		    		  ,"check[read]": true
		   		    		  ,"open": false
		   		    		  ,"desc": "我爱layui"
		   		    		})
		   	    	    });
		   	            
		   	            var url=$WEB_ROOT_PATH+"/dhccApi/medicalexamine/medicalExamine/findData1?medicalId1="+medicalId;
		   	            $.post(url,{"medicalExamine.medicalId":data.data['0']['id']},function(result1){
		   	            	 if(result1.medicalExamine['status']!=null || result1.medicalExamine['comments']!=null){
		   		        		  $("#hhd").hide();
		   		        		  $("#hhd1").hide();
		   		        		  $("#hhd2").show();
		   		        		document.getElementById("commm").readOnly=true;
		   		        	//radio标签动态选中
			   	            	 if(result1.medicalExamine['status']==null){
			   	            	  $("input[lay-filter='status2']").get(result1.medicalExamine['status']).checked=false;
			   	            	$('#status1').attr("disabled",false);
			   	        		$('#status3').attr("disabled",false);
			   	            	 }else if(result1.medicalExamine['status']!=null){
			   	            	  $("input[lay-filter='status2']").get(result1.medicalExamine['status']).checked=true;
			   	            	$('#status1').attr("disabled",true);
			   	        		$('#status3').attr("disabled",true);
			   	            	 }
		   		        	  }else if(result1.medicalExamine['status']==null && result1.medicalExamine['comments']==null){
		   		        		  $("#hhd").show();
		   		        		  $("#hhd1").show();
		   		        		  $("#hhd2").hide();
		   		        		document.getElementById("commm").readOnly=false;
		   		        		$('#status1').attr("disabled",false);
			   	        		$('#status3').attr("disabled",false);
		   		        	  $("input[lay-filter='status2']").get(result1.medicalExamine['status']).checked=false;
		   		        	  }
		   	            	
		   		        	 
		   		        	  
		   		        	  //审核意见动态赋值
		   		        	  form.val("layuiadmin-form-useradmin1", {
		   			    		  // "name": "value"
		   			    		  "medicalExamine.comments":result1.medicalExamine['comments']
		   			    		  ,"check[write]": true
		   			    		  ,"open": false
		   			    		  ,"desc": "我爱layui"
		   			    		})
		   			    		
		   	            });
		   	            
		   	         $("[data-field='status']").children().each(function(){
		                    if($(this).text()=='0'){
		                        $(this).text("未审核")
		                    }else if($(this).text()=='1'){
		                        $(this).text("机审完成")
		                    }else if($(this).text()=='2'){
		                        $(this).text("人工审完成")
		                    }else if($(this).text()=='3'){
		                        $(this).text("终审完成")
		                    }
		                });
		      			$("[data-field='userStatus']").children().each(function(){
		                    if($(this).text()=='0'){
		                        $(this).text("未违规")
		                    }else if($(this).text()=='1'){
		                        $(this).text("违规")
		                    }
		                });
		      			$("[data-field='finaStatus']").children().each(function(){
		                    if($(this).text()=='0'){
		                        $(this).text("未违规")
		                    }else if($(this).text()=='1'){
		                        $(this).text("违规")
		                    }
		                });
		      			$("[data-field='sysStatus']").children().each(function(){
		                    if($(this).text()=='0'){
		                        $(this).text("未违规")
		                    }else if($(this).text()=='1'){
		                        $(this).text("违规")
		                    }else if($(this).text()=='2'){
		                        $(this).text("疑似违规")
		                    }
		                });
		      			$("[data-field='medicalType']").children().each(function(){
		                    if($(this).text()=='301'){
		                        $(this).text("职工医疗")
		                    }else if($(this).text()=='305'){
		                        $(this).text("居民医疗")
		                    }else if($(this).text()=='309'){
		                        $(this).text("新农合")
		                    }else if($(this).text()=='401'){
		                        $(this).text("生育险种")
		                    }else if($(this).text()=='501'){
		                        $(this).text("工伤险种")
		                    }
		                });
		      			
		      			$("[data-field='crowdType']").children().each(function(){
		                    if($(this).text()=='A'){
		                        $(this).text("职工")
		                    }else if($(this).text()=='B'){
		                        $(this).text("居民")
		                    }else if($(this).text()=='C'){
		                        $(this).text("农合")
		                    }
		                });
		      		  }
		      		,page: true
        		});
	    
	    var thisRowData;
	    table.on('row(userTable)', function (obj){
	    	 var data = obj.data;
	            var id = data.id;
	           // alert('已选中患者为'+data.patientName);
	            medicalId=data.id;
	            
	            
	            $("tr").css("background-color",""); 
	            $(this).css("background-color","#C0C0C0"); 
	            
	            
	            var url=$WEB_ROOT_PATH+"/dhccApi/medicalexamine/medicalExamine/findData?medicalId1="+medicalId;
	            $.post(url,{"medicalExamine.medicalId":data.id},function(result){
	   	        	  if(result.medicalExamine['status']!=null || result.medicalExamine['costMoney']!=null || result.medicalExamine['comments']!=null){
	   	        		  $("#ssd").hide();
	   	        		  $("#ssd1").hide();
	   	        		  $("#ssd2").show();
	   	        		  document.getElementById("cost").readOnly=true;
	   	        		  document.getElementById("comm").readOnly=true;
	   	        		  document.getElementById("commm").readOnly=true;
	   	        		//radio标签动态选中
	   	        		if(result.medicalExamine['status']==null){
		   	        		$("input[name='medicalExamine.status']").get(result.medicalExamine['status']).checked=false;
		   	        		$('#status').attr("disabled",false);
		   	        		$('#status2').attr("disabled",false);
		   	        	  }else if(result.medicalExamine['status']!=null){
		   	        		$("input[name='medicalExamine.status']").get(result.medicalExamine['status']).checked=true;
		   	        		$('#status').attr("disabled",true);
		   	        		$('#status2').attr("disabled",true);
		   	        	  }
	   	        	  }else if(result.medicalExamine['status']==null && result.medicalExamine['costMoney']==null && result.medicalExamine['comments']==null){
	   	        		  $("#ssd").show();
	   	        		  $("#ssd1").show();
	   	        		  $("#ssd2").hide();
	   	        		  document.getElementById("cost").readOnly=false;
	   	        		  document.getElementById("comm").readOnly=false;
	   	        		  document.getElementById("commm").readOnly=false;
	   	        		$('#status').attr("disabled",false);
	   	        		$('#status2').attr("disabled",false);
	   	        		$("input[name='medicalExamine.status']").get(result.medicalExamine['status']).checked=false;
	   	        	  }
	   	        	  
	   	        	  
	   	        	  
	   	        	  //type=text标签动态赋值
	   	        	  document.getElementsByName("medicalExamine.costMoney")[0].value = result.medicalExamine['costMoney'];
	   	        	  //审核意见动态赋值
	   	        	  form.val("layuiadmin-form-useradmin", {
	   		    		  // "name": "value"
	   		    		  "medicalExamine.comments":result.medicalExamine['comments']
	   		    		  ,"check[read]": true
	   		    		  ,"open": false
	   		    		  ,"desc": "我爱layui"
	   		    		})
	   	    	    });
	            
	            var url=$WEB_ROOT_PATH+"/dhccApi/medicalexamine/medicalExamine/findData1?medicalId1="+medicalId;
	            $.post(url,{"medicalExamine.medicalId":data.id},function(result1){
  	            	 if(result1.medicalExamine['status']!=null || result1.medicalExamine['comments']!=null){
  		        		  $("#hhd").hide();
  		        		  $("#hhd1").hide();
  		        		  $("#hhd2").show();
  		        		document.getElementById("commm").readOnly=true;
  		        	//radio标签动态选中
	   	            	 if(result1.medicalExamine['status']==null){
	   	            	  $("input[lay-filter='status2']").get(result1.medicalExamine['status']).checked=false;
	   	            	$('#status1').attr("disabled",false);
	   	        		$('#status3').attr("disabled",false);
	   	            	 }else if(result1.medicalExamine['status']!=null){
	   	            	  $("input[lay-filter='status2']").get(result1.medicalExamine['status']).checked=true;
	   	            	$('#status1').attr("disabled",true);
	   	        		$('#status3').attr("disabled",true);
	   	            	 }
  		        	  }else if(result1.medicalExamine['status']==null && result1.medicalExamine['comments']==null){
  		        		  $("#hhd").show();
  		        		  $("#hhd1").show();
  		        		  $("#hhd2").hide();
  		        		document.getElementById("commm").readOnly=false;
  		        		$('#status1').attr("disabled",false);
	   	        		$('#status3').attr("disabled",false);
  		        	  $("input[lay-filter='status2']").get(result1.medicalExamine['status']).checked=false;
  		        	  }
  	            	
  		        	 
  		        	  
  		        	  //审核意见动态赋值
  		        	  form.val("layuiadmin-form-useradmin1", {
  			    		  // "name": "value"
  			    		  "medicalExamine.comments":result1.medicalExamine['comments']
  			    		  ,"check[write]": true
  			    		  ,"open": false
  			    		  ,"desc": "我爱layui"
  			    		})
  			    		
  	            });
	            
	          $("#totalCost").html(data.totalCost);
	    });
		    		
	    
	    
	    //监听搜索
		form.on('submit(LAY-user-front-search)', function(data){
	    	var field = data.field;
	        //执行重载
	        layui.table.reload('userTable', {
	            where: field
	        });
	    });
	  
	   //添加事件
	    var active = {
	    		add: function(){
	    		    	//新增方法
	    		        layer.open({
	    		          type: 2
	    		          ,title: '添加信息'
	    		          ,content: $WEB_ROOT_PATH+'/medical/medicalform'
	    		          ,maxmin: true
	    		          ,area: ['1100px', '450px']
	    		          ,btn: ['确定', '取消']
	    		          ,yes: function(index, layero){
	    		            var iframeWindow = window['layui-layer-iframe'+ index]
	    		            ,submitID = 'LAY-user-front-submit'
	    		            ,submit = layero.find('iframe').contents().find('#'+ submitID);

	    		            //监听提交
	    		            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
	    		              var field = data.field; //获取提交的字段
	       
	    	  //提交 Ajax后台 
              var url=$WEB_ROOT_PATH+"/dhccApi/medical/medical/save";
              $.post(url,field,function(result){
            	  var inFlag=result.inFlag;
            	  if(inFlag==0){
            		  layer.msg('添加成功!');
            		  //后台成功后，静态更新表格中的数据
                      table.reload('userTable'); //数据刷新
                      layer.close(index); //关闭弹层
            	  }
			  });
	       });
	    submit.trigger('click');
	     }
	  }); 
	}
};
	  //监听行点击
	    table.on('tool(userTable)', function(obj){
	    	console.log(arguments)
	      var data = obj.data;
	      if(obj.event === 'del'){
	    	//删除
	        layer.confirm('确定删除该条记录吗？', function(index){
	        	//执行 Ajax 后重载
	            var url=$WEB_ROOT_PATH+"/dhccApi/medical/medical/delete";
	            $.post(url,{"medical.Id":data.id},function(result){
	        	    table.reload('userTable');
	                layer.msg('已删除');
	    	    });
	        });
	      } else if(obj.event === 'edit'){
	    	//修改方法
		    layer.open({
		          type: 2
		          ,title: '修改就诊信息'
		          ,content: $WEB_ROOT_PATH+'/medical/medicalform'
		          ,maxmin: true
		          ,area: ['1100px', '450px']
		          ,btn: ['确定', '取消']
		          ,success: function(layero, index){
		        	  var iframeWindow = window['layui-layer-iframe'+ index];
		        	  //向此iframe层方法 传递参数
		        	  iframeWindow.child(JSON.stringify(data));
		          }
		          ,yes: function(index, layero){
		            var iframeWindow = window['layui-layer-iframe'+ index]
		            ,submitID = 'LAY-user-front-submit'
		            ,submit = layero.find('iframe').contents().find('#'+ submitID);
	
		            //监听提交
		            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
		              var field = data.field; //获取提交的字段
		              //提交 Ajax后台 
		              var url=$WEB_ROOT_PATH+"/dhccApi/medical/medical/save";
		              $.post(url,field,function(result){
		            	  var inFlag= result.inFlag; 
		            	  if(inFlag==0){
		            		  layer.msg('修改成功!');
			            	  //后台成功后，静态更新表格中的数据
			                  table.reload('userTable'); //数据刷新
			                  layer.close(index); //关闭弹层
		            	  }
					  });
		            });  
		            submit.trigger('click');
		          }
		        }); 
	      }else if(obj.event === 'viewInfo'){
	    	    //明细
	    	  layer.open({
		          type: 2
		          ,title: "病例明细"
		          ,content: $WEB_ROOT_PATH+'/medical/medicaltab-jpp'
		          ,maxmin: true
		          ,area: ['1200px', '480px']
	    	  	  ,success: function(layero, index){
	        	  var iframeWindow = window['layui-layer-iframe'+ index];
	        	  //向此iframe层方法 传递参数\
					  console.log(iframeWindow)
	        	  iframeWindow.child(JSON.stringify(data));
	          }
}); 
	      }
	      else if(obj.event=='audit'){
			  layer.open({
				  type: 2
				  ,title: '修改稽核信息'
				  ,content: $WEB_ROOT_PATH+'/medical/medicalAuditForm'
				  ,maxmin: true
				  ,area: ['1100px', '450px']
				  ,btn: ['确定', '取消']
				  ,success: function(layero, index){

					  var url=$WEB_ROOT_PATH+"/dhccApi/medicalaudit/medicalAudit/findData?medicalId1="+medicalId;
					  $.ajaxSetup({
						  async : false
					  });
					  $.post(url,{"medicalAudit.medicalId":data.id},function(result){
						  console.log(result);
						  resultt=result.medicalAudit;
					  })
					  var iframeWindow = window['layui-layer-iframe'+ index];
					  //向此iframe层方法 传递参数
					  iframeWindow.child(JSON.stringify(resultt));
				  }
				  ,yes: function(index, layero){
					  var iframeWindow = window['layui-layer-iframe'+ index]
						  ,submitID = 'LAY-user-front-submit'
						  ,submit = layero.find('iframe').contents().find('#'+ submitID);
					  indexAll=index;
					  //监听提交
					  /*iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
						  var field = data.field; //获取提交的字段
						  //提交 Ajax后台
						  layer.close(index); //关闭弹层
					  });*/
					  submit.trigger('click');
				  }
			  });
		  }
	    });
	    var medicalExamine;
	    form.on('submit(LAY-user-front-save)', function(data){
	    	var field = data.field;
	    	
	        //执行重载
	    	 var url=$WEB_ROOT_PATH+"/dhccApi/medicalexamine/medicalExamine/save?medicalId1="+medicalId;
             $.post(url,field,function(result){
            	 $("#ssd").hide();
       		  $("#ssd1").hide();
       		  $("#ssd2").show();
       		 document.getElementById("cost").readOnly=true;
       		 document.getElementById("comm").readOnly=true;
           	  var inFlag=result.inFlag;
           	  if(inFlag==0){
           		  layer.msg('添加成功!');
           		  //后台成功后，静态更新表格中的数据
                     table.reload('userTable'); //数据刷新
                     form.render();//刷新表单
                     
           	  }
			  });
	    	
	        layui.table.reload('userTable', {
	            where: field
	        });
	    });
	    
	    form.on('submit(LAY-user-front-save1)', function(data){
	    	var field = data.field;
	    	
	        //执行重载
	    	
	    	 var url=$WEB_ROOT_PATH+"/dhccApi/medicalexamine/medicalExamine/save1?medicalId2="+medicalId;
             $.post(url,field,function(result){
            	 $("#hhd").hide();
       		  $("#hhd1").hide();
       		  $("#hhd2").show();
       		document.getElementById("commm").readOnly=true;
           	  var inFlag=result.inFlag;
           	  if(inFlag==0){
           		  layer.msg('添加成功!');
           		  //后台成功后，静态更新表格中的数据
                     table.reload('userTable'); //数据刷新
           	  }
			  });
	    	
	        layui.table.reload('userTable', {
	            where: field
	        });
	    });
	    
	    
	    form.on('submit(LAY-user-front-white)', function(data){
	    	  $("input[id='status']").get('0').checked=true;
	    	//type=text标签动态赋值
      	  document.getElementsByName("medicalExamine.costMoney")[0].value ='';
      	$('#status').attr("disabled",false);
   		$('#status2').attr("disabled",false);
      	  //审核意见动态赋值
      	  form.val("layuiadmin-form-useradmin", {
	    		  // "name": "value"
	    		  "medicalExamine.comments":''
	    		  ,"check[write]": true
	    		  ,"open": false
	    		  ,"desc": "我爱layui"
	    		})
	    });
	    
	    form.on('submit(LAY-user-front-white1)', function(data){
	    	//radio标签动态选中
      	  $("input[lay-filter='status2']").get('0').checked=true;
      	$('#status1').attr("disabled",false);
   		$('#status3').attr("disabled",false);
      	  //审核意见动态赋值
      	  form.val("layuiadmin-form-useradmin1", {
	    		  // "name": "value"
	    		  "medicalExamine.comments":''
	    		  ,"check[write]": true
	    		  ,"open": false
	    		  ,"desc": "我爱layui"
	    		})
	    });
	    
	    form.on('submit(LAY-user-front-backout)',function(data){
	    	$('#status').attr("disabled",false);
       		$('#status2').attr("disabled",false);
       		if(medicalId==null){
    			layer.msg("请选中要审核的数据！");
    			return;
    		}
	    	 var url=$WEB_ROOT_PATH+"/dhccApi/medicalexamine/medicalExamine/white?medicalId1="+medicalId;
	    	  $.post(url,{"medicalExamine.medicalId":medicalId},function(result){
	    		  var inFlag=result.inFlag;
	    		  if(inFlag==0){
	    			  $("#ssd").show();
	        		  $("#ssd1").show();
	        		  $("#ssd2").hide();
	        		  document.getElementById("cost").readOnly=false;
	        		  document.getElementById("comm").readOnly=false;
	    		//radio标签动态选中
	        		  if(result.medicalExamine['status']==null){
	        			  
	        			  $("input[name='medicalExamine.status']").get(result.medicalExamine['status']).checked=false;
	        		  }else if(result.medicalExamine['status']!=null){
	        			  $("input[name='medicalExamine.status']").get(result.medicalExamine['status']).checked=true;
	        		  }
	        	 
	        	  //type=text标签动态赋值
	        	  document.getElementsByName("medicalExamine.costMoney")[0].value = result.medicalExamine['costMoney'];
	        	  //审核意见动态赋值
	        	  form.val("layuiadmin-form-useradmin", {
		    		  // "name": "value"
		    		  "medicalExamine.comments":result.medicalExamine['comments']
		    		  ,"check[write]": true
		    		  ,"open": false
		    		  ,"desc": "我爱layui"
		    		})
	    		  }else if(inFlag==1){
	    			  $("#ssd").show();
	        		  $("#ssd1").show();
	        		  $("#ssd2").hide();
	        		  document.getElementById("cost").readOnly=false;
	        		  document.getElementById("comm").readOnly=false;
	    			  $("input[id='status']").get('0').checked=false;
	    		    	//type=text标签动态赋值
	    	      	  document.getElementsByName("medicalExamine.costMoney")[0].value ='';
	    	      	  //审核意见动态赋值
	    	      	  form.val("layuiadmin-form-useradmin", {
	    		    		  // "name": "value"
	    		    		  "medicalExamine.comments":''
	    		    		  ,"check[write]": true
	    		    		  ,"open": false
	    		    		  ,"desc": "我爱layui"
	    		    		})
	    		  }
	    	  })
	    });
	    
	    form.on('submit(LAY-user-front-backout1)',function(data){
	    	 $('#status1').attr("disabled",false);
        		$('#status3').attr("disabled",false);
        		if(medicalId==null){
        			layer.msg("请选中要审核的数据！");
        			return;
        		}
	    	var url=$WEB_ROOT_PATH+"/dhccApi/medicalexamine/medicalExamine/white1?medicalId1="+medicalId;
	    	  $.post(url,{"medicalExamine.medicalId":medicalId},function(result){
	    		  var inFlag=result.inFlag;
	    		  if(inFlag==0){
	    			  $("#hhd").show();
	        		  $("#hhd1").show();
	        		  $("#hhd2").hide();
	        		  document.getElementById("commm").readOnly=false;
	    		//radio标签动态选中
	        		  if(result.medicalExamine['status']==null){
	        			  $("input[lay-filter='status2']").get(result.medicalExamine['status']).checked=false;
	        			 
	        			  }else if(result.medicalExamine['status']!=null){
	        				  $("input[lay-filter='status2']").get(result.medicalExamine['status']).checked=true;
	        			  }
	        	 
	        	  form.val("layuiadmin-form-useradmin1", {
		    		  // "name": "value"
		    		  "medicalExamine.comments":result.medicalExamine['comments']
		    		  ,"check[write]": true
		    		  ,"open": false
		    		  ,"desc": "我爱layui"
		    		})
	    		  }else if(inFlag==1){
	    			  $("#hhd").show();
	        		  $("#hhd1").show();
	        		  $("#hhd2").hide();
	    			  $("input[lay-filter='status2']").get('0').checked=false;
	    			  document.getElementById("commm").readOnly=false;
	    	      	  //审核意见动态赋值
	    	      	  form.val("layuiadmin-form-useradmin1", {
	    		    		  // "name": "value"
	    		    		  "medicalExamine.comments":''
	    		    		  ,"check[write]": true
	    		    		  ,"open": false
	    		    		  ,"desc": "我爱layui"
	    		    		})
	    		  }
	    	  })
	    });
	    
	    
	
	    
	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });
	
function closeLayer(){
	layer.close(indexAll); //关闭弹层
	layer.msg('保存成功!');
}