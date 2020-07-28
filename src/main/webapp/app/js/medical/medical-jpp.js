var medical_id='';//medical表id
var rowMedical=null;//全局medical行数据
var sys_status_jsp='';
var billing_no='';//就诊明细id
var is_ilegal='';//是否违规
var index_last=null;
var statu_last=null;
var excelData=null;
//初始化
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
	    var orgName= Request["orgName"];
	    var cityName = Request["cityName"];
	    var typeNoTz= Request["typeNo"];
	    var typeNameTz= Request["typeNameTz"];
	    var type = Request["type"];
	    var handdingInsName = Request["handdingInsName"];
	    
	  //日期范围
	    laydate.render({
	    	elem: '#finaTime'
	    		,trigger:'click'
	    			,format:'yyyy-MM-dd'
	    				,range: true
	    });
	    laydate.render({
	    	elem: '#inhosDate'
	    		,trigger:'click'
	    			,format:'yyyy-MM-dd'
	    				,range: true
	    });
	    laydate.render({
	    	elem: '#outhosDate'
	    		,trigger:'click'
	    			,format:'yyyy-MM-dd'
	    				,range: true
	    });
	//加载规则下拉字典
	    //localStorage.clear();
		var dict_rule=localStorage.getItem('dict_rule');//从浏览器数据库取出
		var rule=$.parseJSON(dict_rule);//解析成对象
		if(rule){
			for(var i=0 ;i<rule.length;i++){
     			var mm="<option value='"+rule[i].value+"'>"+rule[i].text+"</option>";
     			$("#ruleType").append(mm); 
     			$("#ruleTypeOn").append(mm); 
     		}
			form.render('select');
		}else{
			//加载违规类型下拉字典
			$.getJSON($WEB_ROOT_PATH+'/dhccApi/dict/dict/findDict?dictRequestVo.tableName=t_piccbid_dict_rule', 
						function(data){
				     		var  dataList= data.dictList;
				     		var dict_rule=JSON.stringify(dataList);//解析为字符串
				     		//localStorage.clear();
				     		localStorage.setItem('dict_rule',dict_rule);//存入浏览器数据库
				     		
				     		for(var i=0 ;i<dataList.length;i++){
				     			var mm="<option value='"+dataList[i].value+"'>"+dataList[i].text+"</option>";
				     			$("#ruleType").append(mm); 
				     			$("#ruleTypeOn").append(mm); 
				     		}
				     	form.render('select');
			});
		}
		/*//加载医院下拉字典
        $.getJSON($WEB_ROOT_PATH+'/dhccApi/dataauthority/dataauthority/findOrg',
            function(data){
                var orgs=data.pageModel.rows
                //var  dataList= data.dictList;
                var org_save=JSON.stringify(orgs);//解析为字符串
                //localStorage.clear();
               
                localStorage.setItem('org_save',org_save);//存入浏览器数据库
                for(var i=0 ;i<orgs.length;i++){
                    var mm="<option value='"+orgs[i].value+"'>"+orgs[i].text+"</option>";
                    $("#zyOrgName").append(mm);
                }
                form.render('select');
           });*/
	   var allExportData = [];
	   var ins1= table.render({
	    	elem: '#userTable'
	            ,url: $WEB_ROOT_PATH+'/dhccApi/medical/medical/listVo'
	            ,cellMinWidth: 100
	             ,height: 230
	             ,limits: [5,10,20]
	             ,limit: 5
	             ,where:{"medical.orgCode":orgCode,"medical.orgName":orgName,"cityName":cityName,"type":type,"typeNoTz":typeNoTz,"typeNameTz":typeNameTz,"sHType":"cs","handdingInsName":handdingInsName}
	            ,cols: [[
	            	   {type: 'numbers', title: '序号' }
		              ,{field:'id', title: 'ID', sort: true, hide:true}
		             /* ,{field:'left', title:'稽核', toolbar: '#table-useradmin-webuser2', width:90}*/
		             /* ,{field:'status', title:'数据校验标识' }*/
		              ,{field:'sysStatus',width:80, title:'机审结果' }
		             /* ,{field:'userStatus', title:'人工审核结果状态' }*/
		              /*,{field:'finaStatus', title:'终审结果状态' }*/
		              ,{field:'diagType', title:'就诊类型' }
		              ,{field:'idcard', width:150,title:'身份证号' }
		              ,{field:'name', width:80, title:'姓名'}
		              ,{field:'sex',width:60, title: '性别' }
		              ,{field:'age', width:60,title:'年龄' }
		              ,{field:'orgName', width:180,title:'医疗机构' }
		              ,{field:'departName', title:'科室' }
		              ,{field:'condition',  width:150,title: '病情' }
		              ,{field:'paymentDate',width:100, title:'结算日期'}
		              ,{field:'inhosDate',width:100,  title:'入院日期' }
		              ,{field:'outhosDate',width:100,  title:'出院日期' }
		              ,{field:'stayLength', title:'住院天数' }
		              ,{field:'medicalType',  width:90,title: '险种类型' }
		              ,{field:'admissionNo', width:175,title:'住院号' }
		              ,{field:'sscno', width:150,title: '社保卡号' }
		              ,{field:'admissionType',  width:90,title: '住院类型' }
		              ,{field:'dischargeState', title:'出院状态' }
		              ,{field:'totalCost', title:'总金额' }
		              ,{field:'fundCost', title:'基金支付金额' }
		              ,{field:'selfCost', title:'个人负担金额' }
		              ,{field:'basicCostM', width:90, title:'基本统筹应支付' }
		              ,{field:'povertyAlleviationSubsidy',  width:90,title:'扶贫补助' }
		              ,{field:'financeSubsidy', title:'财政补助' }
		              ,{field:'officialSubsidy',  width:115,title:'公务员补助' }
		              ,{field:'treatmentType',  width:120,title:'待遇享受类别' }
		              ,{field:'medicalTreatmentState',  width:115,title:'医疗待遇状态' }
		              ,{field:'billingNo',width:175, title:'收费单据号'}
		              ,{field:'crowdType', title:'人群类别' }
		              ,{field:'reversalMark',width:115, title:'冲销标志'}
		              ,{field:'treatmentWay', title:'就诊方式' }
		              ,{field:'createTime', title:'数据上传时间' }
		              ,{field:'rangeCost', title:'本次纳入报销范围金额' }
		              ,{field:'basicCostR', title:'基本统筹实际支付' }
		              ,{field:'selfPayAmount', title:'个人自付金额' }
		              ,{field:'selfExpenditureAmount', title:'个人自费金额' }
		              ,{field:'sscAccountCost', title:'个人账户自付' }
		              ,{field:'cashCost', title:'现金支付金额' }
		              ,{field:'largeCostM', title:'大额应支付' }
		              ,{field:'largeCostR', title:'大额实支付' }
		              ,{field:'civilAffairSubsidy', title:'民政救助'}
		              ,{field:'fullOrdination', title:'全额统筹' }
		              ,{field:'partialOrdination', title:'部分统筹' }
		              ,{field:'partialPayment', title:'部分自付' }
		              ,{field:'fullPayment', title:'全额自付' }
		              ,{field:'reimbursementType', title:'报销类型' }
		              ,{field:'hospCount', title:'住院次数' }
		              ,{field:'insuranceMark', title:'险种标志' }
		              ,{field:'outDiagnosisNo', title:'出院诊断编码' }
		              ,{field:'outDiagnosisName', title:'出院诊断名称' }
		              ,{field:'inDiagnosisNo', title:'入院诊断编码' }
		              ,{field:'inDiagnosisName', title:'入院诊断名称' }
		              
		              /*,{field:'orgCode', width:140,title:'医疗机构编码'}*/
		              /*,{field:'cityCode', title:'城市编码' }*/
		            ]]
		            ,done:function(res){
		            	$('tr').eq(1).css("background-color","#EEF6FF");
		            	var result=res.data;
		            	//console.info(result[0]);
		            	if(result.length>0){
			            	medical_id=result[0].id;
			            	sys_status_jsp=result[0].sysStatus;
			            	//findMedicalDate(medical_id);
			            	sysVerify(result[0],form);
			            	rowMedical=result[0];//全局行数据赋值
			            	$("#medical_id_form").val(medical_id);
			            	amountTotal();//右侧总金额赋值
			            	hxtjzt();
		            	}else{
		            		medical_id='';
		            		sysVerify('',form);
		            		amountTotal();//右侧总金额赋值
		            	}
		            	//console.info(medical_id);
		            	var getValue = JSON.stringify(layui.formSelects.value('select1', 'val'));
		        		is_ilegal=getValue.substring(1,getValue.length-1);
		            	$('#dg').datagrid('load',{'medicalDetail.medicalId': medical_id,'medicalDetail.isIlegal':is_ilegal});
		            	
		      		 }
		      		,page: true
        		});
	    
		 layui.use(['table'],function(){

	    	 $("#medical-jpp-qbdc").click(function(){
	 	       	/*table.exportFile(ins1.config.id,allExportData,'xls');*/
	    		var balanceDate,inhosDate,billingNo,diagType,idcard,name,orgCode,sysStatus,outhosDate,typeNoSerach,condition,id;
	    		 if(excelData != null){
	    			/* field["medical.name"]*/
	    			 balanceDate = excelData.balanceDate;
	    			 inhosDate=excelData.inhosDate;
	    			 billingNo=excelData["medical.billingNo"];
	    			 diagType=excelData["medical.diagType"];
	    			 idcard=excelData["medical.idcard"];
	    			 name=excelData["medical.name"];
	    			 orgCode=excelData["medical.orgCode"];
	    			 sysStatus=excelData["medical.sysStatus"];
	    			 outhosDate=excelData["outhosDate"];
	    			 typeNoSerach=excelData["typeNoSerach"];
	    			 condition=excelData["medical.condition"];
	    			 id=excelData["medical.id"];
	    		 }
	    		// window.open($WEB_ROOT_PATH+'/dhccApi/medicalexcel/medicalexcel/exportExcelToSelf?balanceDate='+balanceDate+'&inhosDate='+inhosDate+'&billingNo='+billingNo+'&diagType='+diagType+'&idcard='+idcard+ '&name='+name+'&orgCode='+orgCode+'&sysStatus='+sysStatus+'&outhosDate='+outhosDate);
	    		  layer.open({
					  type: 2
					  ,title: '导出信息选择'
					  ,content: $WEB_ROOT_PATH+'/medicalexcel?balanceDate='+balanceDate+'&inhosDate='+inhosDate+'&billingNo='+billingNo+'&diagType='+diagType+'&idcard='+idcard+ '&name='+name+'&orgCode='+orgCode+'&sysStatus='+sysStatus+'&outhosDate='+outhosDate+'&typeNoSerach='+typeNoSerach+'&condition='+condition+'&id='+id
					  ,maxmin: true
					  ,area: ['900px', '500px']
					  ,btn: ['确定', '取消']
					  ,success: function(layero, index){
						  var iframeWindow = window['layui-layer-iframe'+ index];
						  //向此iframe层方法 传递参数
						  iframeWindow.child(JSON.stringify(resultt));
					  }
					  ,yes: function(index, layero){
						 
					  }
				  });
    		 
	    	 })
	    	/* '&outhosDate='+outhosDate+*/
	    });
	   
	    table.on('tool(userTable)', function(obj){
	    	var data = obj.data;	
	    	//console.info(obj.event);
	    	if(obj.event=='audit'){
				  layer.open({
					  type: 2
					  ,title: '编辑稽核信息'
					  ,content: $WEB_ROOT_PATH+'/medical/medicalAuditForm'
					  ,maxmin: true
					  ,area: ['800px', '450px']
					  ,btn: ['确定', '取消']
					  ,success: function(layero, index){
						  var url=$WEB_ROOT_PATH+"/dhccApi/medicalaudit/medicalAudit/findData?medicalId1="+medicalId;
						  $.ajaxSetup({
							  async : false
						  });
						  $.post(url,{"medicalAudit.medicalId":data.id},function(result){
							 // console.log(result);
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
	    
	    
	   //行监听
	    table.on('row(userTable)', function(obj){
	    	$("tr").css("background-color",""); 
	         $(this).css("background-color","#EEF6FF"); 
	    	//刷新就诊明细
	    	var result=obj.data;
	    	//findMedicalDate(result.id);
	    		
	    	//将medical_id赋值到全局变量
	    	medical_id=result.id;
	    	sys_status_jsp=result.sysStatus;
	    	hxtjzt(0);//回显选中状态--中间提交
	    	sysVerify(result,form);
	    	rowMedical=result;//全局行数据赋值
	    	 $("#ruleType").val('');//下表违规类型选中状态置空
	    	 form.render('select');
	    	 amountTotal();//右侧总金额赋值
	    	 hxtjzt();
	    	$("#medical_id_form").val(medical_id);
	    	var getValue = JSON.stringify(layui.formSelects.value('select1', 'val'));
    		is_ilegal=getValue.substring(1,getValue.length-1);
	    	$('#dg').datagrid('load',{'medicalDetail.medicalId': medical_id,'medicalDetail.isIlegal':is_ilegal}); 
	    	
	    });
	    
	    //监听搜索上
		form.on('submit(LAY-user-front-search)', function(data){
			excelData = data.field;
	    	var field = data.field;
	    	
	        //执行重载
	        layui.table.reload('userTable', {
	            where: field
	        });
	    });
		//监听批量提交上
		form.on('submit(LAY-user-front-search-allUpdate1)', function(data){
			layer.confirm('确定要批量审核？', function(index){
			var url=$WEB_ROOT_PATH+'/dhccApi/medical/medical/updateUserStatusAll';
				$.post(url,function(result){
					var inFlag= result.inFlag; 
					if(inFlag==0){
						layer.msg('批量审核成功!,共 '+result.plsh+' 条数据被审核。');
						table.reload('userTable'); //数据刷新
					}
				});
			});
		});
		
	  //监听搜索下
		form.on('submit(LAY-user-front-search2)', function(data){
			var field = data.field;
			$('#dg').datagrid('load',field);
		});
	  //人工审核提交
		$('#medical-jpp-tj').click(function(){
			var userStatus=$("#violationStatus option:selected").val();//去向
			if(!userStatus){
				layer.msg('请选择去向后，再提交!');
				return;
			}else{
				var shfs=$("#packType option:selected").val();
				if(shfs=='1'){
					//按主病历审核
					var zblzt=$("#packTypeReact option:selected").val();//主病历审核状态
					if(!zblzt){
						layer.msg('请选择主病历审核结果后再提交!');
						return;
					}else{
						var amountTotals=$("#amountTotal").val();//初审预扣金额
						var amountRemarks=$("#amountRemarks").val();//初审预扣金额备注
						if(zblzt=='2'){
							amountTotals='';
						}
						//开始按主病例提交
						var url=$WEB_ROOT_PATH+'/dhccApi/medical/medical/updateUserStatusByMedicalId';
						var filed={"medical.userStatus":zblzt,"medical.id":medical_id,"medical.userVerifyDirection":userStatus,"medical.userVerifyMoney":amountTotals,"medical.userVerifyRemarks":amountRemarks,"medical.auditMethods":shfs};
						$.post(url,filed,function(result){
							var inFlag= result.inFlag; 
							if(inFlag==0){
								layer.msg('提交成功!');
								table.reload('userTable'); //数据刷新
								hxtjzt();
								amountTotal();//右侧总金额刷新
								var getValue = JSON.stringify(layui.formSelects.value('select1', 'val'));
								is_ilegal=getValue.substring(1,getValue.length-1);
								$('#dg').datagrid('reload',{'medicalDetail.medicalId': medical_id,'medicalDetail.isIlegal':is_ilegal});
							}
						});
					}
				}else{
					//按明细审核
					//判断是否全部初审，如果全部初审--生成初审整体结果
					var amountTotals=$("#amountTotal").val();//初审预扣金额
					var amountRemarks=$("#amountRemarks").val();//初审预扣金额备注
					var url=$WEB_ROOT_PATH+'/dhccApi/medical/medical/judgeUserStatus';
					var filed={"medical.id":medical_id};
					$.post(url,filed,function(result){
						if(result=='noFinish'){//初审状态
							layer.msg('还有病历明细未审核或无明细!');
							return;
						}else{
							//初审带有违规、疑似违规的不能提交至正常已终审
							if(userStatus=='4'&&(result=='0'||result=='1')){
								layer.msg('初审带有违规、疑似违规的不能提交至正常!');
								return;
							}
							//审核状态为正常的或无违规明细的，金额为空,空的初审状态置为‘2’
							if(result=='2'||result=='noDetails'){
								result='2';
								amountTotals='';
							}
							if(userStatus=='4'){
								//切换为3，到终审，4仅用来判断终审已正常
								userStatus=='3';
							}
							//初审正常的不能提交至稽核、公示
							/*if(result=='2'&&(userStatus=='0'||userStatus=='1')){
								layer.msg('初审正常状态不能提交到稽核或公示!');
								return;
							}*/
							//按明细算正常的，并带有主病例违规的，仅能按主病例提交
							
								var url=$WEB_ROOT_PATH+'/dhccApi/medical/medical/IsMainMedicalVerify';
								var filed={"medical.id":medical_id};
								$.ajax({
								    url: url,
								    data:filed,
								    success: function(resultmain) {
								    	if(resultmain){
								    		//是主病例，且无违规明细
								    		if(result=='2'){
								    			layer.msg('该病历还含有主病例违规，需通过主病例审核提交!');
								    			return;
								    		}else{
								    			//存在违规明细，可以提交
								    			var url=$WEB_ROOT_PATH+'/dhccApi/medical/medical/updateUserStatusByMedicalId';
												var filed={"medical.userStatus":result,"medical.id":medical_id,"medical.userVerifyDirection":userStatus,"medical.userVerifyMoney":amountTotals,"medical.userVerifyRemarks":amountRemarks,"medical.auditMethods":shfs};
												$.post(url,filed,function(result){
													var inFlag= result.inFlag; 
													if(inFlag==0){
														layer.msg('提交成功!');
														table.reload('userTable'); //数据刷新
														hxtjzt();
														amountTotal();//右侧总金额刷新
														var getValue = JSON.stringify(layui.formSelects.value('select1', 'val'));
														is_ilegal=getValue.substring(1,getValue.length-1);
														$('#dg').datagrid('reload',{'medicalDetail.medicalId': medical_id,'medicalDetail.isIlegal':is_ilegal});
													}
												});
								    		}
										}else{
											//非主病例，开始提交
											var url=$WEB_ROOT_PATH+'/dhccApi/medical/medical/updateUserStatusByMedicalId';
											var filed={"medical.userStatus":result,"medical.id":medical_id,"medical.userVerifyDirection":userStatus,"medical.userVerifyMoney":amountTotals,"medical.userVerifyRemarks":amountRemarks,"medical.auditMethods":shfs};
											$.post(url,filed,function(result){
												var inFlag= result.inFlag; 
												if(inFlag==0){
													layer.msg('提交成功!');
													table.reload('userTable'); //数据刷新
													hxtjzt();
													amountTotal();//右侧总金额刷新
													var getValue = JSON.stringify(layui.formSelects.value('select1', 'val'));
													is_ilegal=getValue.substring(1,getValue.length-1);
													$('#dg').datagrid('reload',{'medicalDetail.medicalId': medical_id,'medicalDetail.isIlegal':is_ilegal});
												}
											});
										}
								    }
								});
								
							
							
						}
					});
					
				}	
			}	
			});		
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					/*var url=$WEB_ROOT_PATH+'/dhccApi/medical/medical/judgeUserStatus';
					var filed={"medical.id":medical_id};
					$.post(url,filed,function(result){
						if(result=='noFinish'){//初审状态
							layer.msg('还有病历明细未审核或无明细!');
							return;
						}else{
							if(userStatus=='3'&&(result!='noDetails'&&result!='2')){
								layer.msg('病历存在违规或疑似违规，不能提交至正常!');
								return;
							}else{
								var amountTotals=$("#amountTotal").val();//初审预扣金额
								var amountRemarks=$("#amountRemarks").val();//初审预扣金额备注
								if(result=='noDetails'){
									result='2';
								}
								if(result=='2'){
									//判断是否还有主病例违规
									var url=$WEB_ROOT_PATH+'/dhccApi/medical/medical/IsMainMedicalVerify';
									var filed={"medical.id":medical_id};
									$.post(url,filed,function(result){
										console.log(result);
										if(result){
											layer.msg('该病历还含有主病例违规，需通过主病例审核提交!');
											return;
										}else{
											//正常--金额置空
											amountTotals='';
											if(userStatus=='0'||userStatus=='1'){
												layer.msg('初审正常状态不能提交到稽核或公示!');
												return;
											}
											//提交
											var url=$WEB_ROOT_PATH+'/dhccApi/medical/medical/updateUserStatusByMedicalId';
											var filed={"medical.userStatus":result,"medical.id":medical_id,"medical.userVerifyDirection":userStatus,"medical.userVerifyMoney":amountTotals,"medical.userVerifyRemarks":amountRemarks,"medical.auditMethods":shfs};
											$.post(url,filed,function(result){
												var inFlag= result.inFlag; 
												if(inFlag==0){
													layer.msg('提交成功!');
													table.reload('userTable'); //数据刷新
													hxtjzt();
													amountTotal();//右侧总金额刷新
													var getValue = JSON.stringify(layui.formSelects.value('select1', 'val'));
													is_ilegal=getValue.substring(1,getValue.length-1);
													$('#dg').datagrid('reload',{'medicalDetail.medicalId': medical_id,'medicalDetail.isIlegal':is_ilegal});
												}
											});
										}
									});


								}else{
									//非正常，提交
									var url=$WEB_ROOT_PATH+'/dhccApi/medical/medical/updateUserStatusByMedicalId';
									var filed={"medical.userStatus":result,"medical.id":medical_id,"medical.userVerifyDirection":userStatus,"medical.userVerifyMoney":amountTotals,"medical.userVerifyRemarks":amountRemarks,"medical.auditMethods":shfs};
									$.post(url,filed,function(result){
										var inFlag= result.inFlag; 
										if(inFlag==0){
											layer.msg('提交成功!');
											table.reload('userTable'); //数据刷新
											hxtjzt();
											amountTotal();//右侧总金额刷新
											var getValue = JSON.stringify(layui.formSelects.value('select1', 'val'));
											is_ilegal=getValue.substring(1,getValue.length-1);
											$('#dg').datagrid('reload',{'medicalDetail.medicalId': medical_id,'medicalDetail.isIlegal':is_ilegal});
										}
									});
								}



							}

						}

					});
				}
			}*/
		
	//批量明细审核（复选框选中的）
		$("#medical-jpp-detail-pltj").click(function(){
			var rows = $('#dg').datagrid('getSelections'); 
			//console.log(rows);
			if(rows.length==0){
				layer.msg('未选择数据，不能批量提交!');
				return;
			}
			//var userStatusPl=$("#violationStatusPl option:selected").val();
			var userStatusPl=null;
			layer.confirm('所选明细审核为？', {
				  btn: ['正常','违规','疑似违规'] //按钮
				, 
				btn1:function(){
					userStatusPl='2';
					plDetailSh(rows,userStatusPl)
				}, 
				btn2:function(){
					userStatusPl='0';
					plDetailSh(rows,userStatusPl)
				},
				btn3:function(){
					userStatusPl='1';
					plDetailSh(rows,userStatusPl)
				}
		});
			//var userStatusPl=$('input[name="violationStatusPl"]:checked').val();
			
		});
	    
	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin.layui-btn-sm').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });
//批量审核
function plDetailSh(rows,userStatusPl){
	if(!userStatusPl){
		layer.msg('未选择审核状态，不能批量提交!');
	}else{
		var url=$WEB_ROOT_PATH+"/dhccApi/medical/medical/userStatusPl";
		var rows=JSON.stringify(rows);
		refreshPlDetailsRadio();//提交状态置空
		$.post(url,{"MedicalDetailPl":rows,"userStatusPl":userStatusPl,'medicalIdPl':medical_id},function(result){
			if(result.inFlag=='0'){
				layer.msg("提交成功");
				amountTotal();//右侧总金额刷新
				var getValue = JSON.stringify(layui.formSelects.value('select1', 'val'));
				is_ilegal=getValue.substring(1,getValue.length-1);
				$('#dg').datagrid('reload',{'medicalDetail.medicalId': medical_id,'medicalDetail.isIlegal':is_ilegal});
			}else{
				layer.msg("提交失败");
			}
		});
	}
}
//获取4表联查的数据
function findMedicalDate(medical_id){
	 var url=$WEB_ROOT_PATH+"/dhccApi/medical/medical/findMedicalDate";
	 $.post(url,{"medicalVoJpp.id":medical_id},function(result){
		
		 var medicalVoJpp=result.medicalVoJpp;
		 for( var index in medicalVoJpp) {
				$("#"+index).html(medicalVoJpp[index]);
		 }
		 $("#inhosDate_down").html(medicalVoJpp["inhosDate"]);
		 $("#outhosDate_down").html(medicalVoJpp["outhosDate"]);
		 if(medicalVoJpp["diagType"]=='1'){
			 $("#diagType").html("住院");
		 }else if(medicalVoJpp["diagType"]=='2'){
			 $("#diagType").html("普通门诊");
		 }else if(medicalVoJpp["diagType"]=='3'){
			 $("#diagType").html("门诊大病");
		 }else{
			 $("#diagType").html("其他");
		 }
	 });
}
function getvalue(valueObject,index){
	saveItem(valueObject,index);
}
//保存详细下拉框,并更新该条明细审核IS_ILEGAL状态
function saveItem(valueObject,index){
	var row = $('#dg').datagrid('getRows')[index];
	var withholdingQuantity= valueObject.withholdingQuantity;//$("#withholdingQuantity").val();
	var withholdingAmount=valueObject.withholdingAmount;//$("#withholdingAmount").val();
	var deductions=valueObject.deductions;//$("#deductions").val();
	var violationStatus=valueObject.violationStatus;//$("input[name='violationStatus']:checked").val();
	if(!violationStatus){
		 layer.msg('请先选择审核状态后再提交!');
		 return;
	}
	var field={"medicalCostVerify.medicalId":medical_id,"medicalCostVerify.costId":row.id,"medicalCostVerify.violationStatus":violationStatus,"medicalCostVerify.deductions":deductions,"medicalCostVerify.withholdingAmount":withholdingAmount,"medicalCostVerify.withholdingQuantity":withholdingQuantity};
	  var url=$WEB_ROOT_PATH+"/dhccApi/medical/medical/medicalCostSave";
	  $.post(url,field,function(result){
 	  if(result.inFlag==0){
 		  layer.msg('保存成功!');
 		  //刷新
 		 amountTotal();//右侧总金额刷新
 		 var form=layui.form; 
 		 sysVerify(rowMedical,form);//刷新右侧违规统计
 		 var getValue = JSON.stringify(layui.formSelects.value('select1', 'val'));
 		 is_ilegal=getValue.substring(1,getValue.length-1);
	     $('#dg').datagrid('reload',{'medicalDetail.medicalId': medical_id,'medicalDetail.isIlegal':is_ilegal}); 
 	  }
   });
}
//取消
function cancelItem(index){
    var row = $('#dg').datagrid('getRows')[index];
    if (row.isNewRecord){
        $('#dg').datagrid('deleteRow',index);
    } else {
        $('#dg').datagrid('collapseRow',index);
    }
}
//easyui行点击
function tableRowClick(index, row, value){
	 var isilegal=row.isIlegal;
	 var packType=$("#packType option:selected").val();
	 $("input[type='checkbox']").each(function(index, el){
         //如果当前的复选框不可选，则不让其选中
         if (el.disabled == true) {
             $('#dg').datagrid('unselectRow', index - 1);
         }
     })
	 
	 
	 if(packType=='0'&&(isilegal=='0'||isilegal=='1')){
		event.stopPropagation();
		
		//第一次打开
		if(index_last==null){
			$('#dg').datagrid('expandRow',index);
			index_last=index;
			statu_last=0;//打开状态
			return;
		}
		//判断是否点击的同一个
		if(index_last==index){
			$('#dg').datagrid('collapseRow',index);
			index_last=null;
			statu_last=null;
			return;
		}
		//打开新的
		if(statu_last==0){
			$('#dg').datagrid('collapseRow',index_last);
			index_last=null;
			statu_last=null;
			$('#dg').datagrid('expandRow',index);
			index_last=index;
			statu_last=0;//打开状态
			return;
		}
	}
}
//右侧系统审核动态拼接
function sysVerify(row,form){
	//console.log(row);
	var div=document.getElementById("sysVerifyTableDiv");
	//removeAllChild(div);
	$("#sysVerifyTableDiv").empty();
	//加一个新的空table  id="sysVerify" border="0" cellpadding="0" cellspacing="0"
	var table=document.createElement("table");
	table.setAttribute("id","sysVerify");
	table.setAttribute("border","0");
	table.setAttribute("style","width:100%");
	table.setAttribute("cellpadding","0");
	table.setAttribute("cellspacing","0");
	div.appendChild(table)
	//判断机审状态（1.未审核--该数据未机审  2无违规信息--无）
	if(row==''){
		//1.无违规信息--无
		var tr=document.createElement('tr');
		table.innerHTML="<div style='width:100%;height:100%;text-align:center;'><div><img style='width:70px;' src="+$WEB_ROOT_PATH+"/images/empty.png></div><div style='line-height: 20px;'>无数据</div></div>";
		//tr.setAttribute("text-align","center");
		table.appendChild(tr);
		return;
	}
	var statu=row.sysStatus;
	if(statu){
		if(statu=='未违规'){
			//1.无违规信息--无
			var tr=document.createElement('tr');
			table.innerHTML="<div style='width:100%;height:100%;text-align:center;'><div><img style='width:70px;' src="+$WEB_ROOT_PATH+"/images/empty.png></div><div style='line-height: 20px;'>无违规信息</div></div>";
			//tr.setAttribute("text-align","center");
			table.appendChild(tr);
		}else{
			//2.违规
			//ajax根据medical_id获取数据
			var url=$WEB_ROOT_PATH+"/dhccApi/violationdetails/violationDetail/findByMedicalId";
			var filed={"violationDetail.medicalId":medical_id,"shzt":'cs'};
			$.post(url,filed,function(result){
				//2.1拼接数据
				var sysVerifyVo=result.sysVerifyVo;
				if(!sysVerifyVo){
					table.innerHTML="<div style='width:100%;height:100%;text-align:center;'><div><img style='width:70px;' src="+$WEB_ROOT_PATH+"/images/empty.png></div><div style='line-height: 20px;'>无违规信息</div></div>";
					return;
				}
				for(var i=0;i<sysVerifyVo.length;i++){
					var tr=document.createElement('tr');
					tr.setAttribute("typevalue",sysVerifyVo[i].typeNo);
					tr.setAttribute("class",'isexistNum');
					//tr.setAttribute("οnclick",wgDetailonclick);
					var td=document.createElement('td');
					var td2=document.createElement('td');
					var td3=document.createElement('td');
					switchChang(i,td,sysVerifyVo[i].typeNames);
					td2.innerHTML="<span>&#12288;&#12288;</span>"+sysVerifyVo[i].countNum+"条";
					if(sysVerifyVo[i].haveCountNum){
						td3.innerHTML="<span>&#12288;&#12288;</span>"+"已审核 "+sysVerifyVo[i].haveCountNum+"条";
					}else{
						td3.innerHTML="<span>&#12288;&#12288;</span>"+"未审核";
					}
					tr.appendChild(td);
					tr.appendChild(td2);
					tr.appendChild(td3);
					table.appendChild(tr);
				}
				//为tr(存在违规条数的)添加点击事件
				$("#sysVerify").on('click','.isexistNum',function(){
					wgDetailonclick(this,form);
				});
			});
		}
		
	}else{
		//3.未审核--该数据未机审
		var tr=document.createElement('tr');
		tr.innerHTML='该条数据还未机审';
		table.appendChild(tr);
	}
	
}
function removeAllChild(table){
	table.empty();
	
	
   /* while(table.hasChildNodes()) //当div下还存在子节点时 循环继续
    {
        table.removeChild(table.firstChild);
    }*/
}
function switchChang(i,td,typeNames){
	if(!typeNames){
		td.innerHTML=i+1+"."+'其他';
	}else{
		td.innerHTML=i+1+"."+typeNames;
	}
}
//回显提交状态
function hxtjzt(){
	//$('input[name="violationStatus"]').attr("checked",false);
	$("#violationStatus").val("");
	$("#packTypeReact").val("");
	$("#packTypeReact").hide();
	$("#packTypeReact-lable").hide();
	$("#packType").val("0");
}
//右侧违规明细点击事件
function wgDetailonclick(typeno,form){
	var t=$(typeno).attr("typevalue");
	//从数据库获取，判断是否为主病例
	var url=$WEB_ROOT_PATH+"/dhccApi/violationdetails/violationDetail/IsMain";
	var filed={"violationDetail.typeNo":t,"violationDetail.medicalId":medical_id};
	$.post(url,filed,function(result){
		if(result){
			if(result.ruleProperty=='0'&&result.returnDescs.length>0){
				//按主病例，弹框显示returnDescs
				var desc='';
				for(var j=0;j<result.returnDescs.length;j++){
					desc=desc+"<font color='red'>【违规"+(j+1)+'】:</font>'+result.returnDescs[j].returnDesc+'</br>';
				}
				
				layer.alert(desc);
			}else{
				 $("#ruleType").val('');
				 $("#ruleType").find("option[value ='"+t+"']").attr("selected","selected");
				 form.render('select');
				//刷新病历明细
				 var getValue = JSON.stringify(layui.formSelects.value('select1', 'val'));
				 is_ilegal=getValue.substring(1,getValue.length-1);
			    $('#dg').datagrid('load',{'medicalDetail.medicalId': medical_id,'medicalDetail.isIlegal':is_ilegal,'medicalDetail.ilegalType':t});
			}
		}
	});
	
}
//违规金额统计(初审)
function  amountTotal(){
	var url=$WEB_ROOT_PATH+"/dhccApi/medicaldetail/medicalDetail/amountTotal";
	var filed={"medicalDetail.medicalId":medical_id};
	$.post(url,filed,function(amountTotal){
		if(amountTotal){
			$("#amountTotal").val(amountTotal);
		}else{
			$("#amountTotal").val('');
		}
	});
}
//批量提交后违规类型单选按钮刷新
function refreshPlDetailsRadio(){
	$('input[name="violationStatusPl"]').attr("checked",false);
}