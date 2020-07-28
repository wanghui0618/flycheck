var medical_id='';//medical表id
var billing_no='';//就诊明细id
var is_ilegal='';//是否违规
var index_last=null;
var statu_last=null;
var jh='';//稽核全局状态
var gs='';//公示全局条件
var excelData = null;
var audit_methods_qj='';//审核方式 0---明细  1--主病历
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
		//加载医院下拉字典
        /*$.getJSON($WEB_ROOT_PATH+'/dhccApi/dataauthority/dataauthority/findOrg',
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
	             ,height: 240
	             ,limits: [5,10,20]
	             ,limit: 5
	             ,where:{"medical.orgCode":orgCode,"medical.orgName":orgName,"cityName":cityName,"type":type,"typeNoTz":typeNoTz,"typeNameTz":typeNameTz,"sHType":"zs"}
	            ,cols: [[
	            	   {type: 'numbers', title: '序号' }
		              ,{field:'id', title: 'ID', sort: true, hide:false}
		            /* ,{field:'left', title:'稽核', toolbar: '#table-useradmin-webuser2', width:120}*/
		             /* ,{field:'status', title:'数据校验标识' }*/
		              ,{field:'finaStatus', title:'终审结果' }
		              ,{field:'aduitStatus', title:'稽核结果' }
		              ,{field:'resultAppealStatu', title:'公示结果' }
		              ,{field:'userStatus', title:'初审结果' }
		              ,{field:'sysStatus', title:'机审结果' }
		              ,{field:'name', width:120, title:'姓名'}
		              ,{field:'sex', width:70,title: '性别' }
		              ,{field:'age',width:70, title:'年龄' }
		              ,{field:'idcard', width:170,title:'身份证号' }
		              ,{field:'sscno',  width:170,title: '社保卡号' }
		              ,{field:'orgName',width:170, title:'医疗机构名称' }
		              ,{field:'departName', title:'科室' }
		              ,{field:'admissionNo',width:170, title:'住院号' }
		              ,{field:'admissionType',  width:90,title: '住院类型' }
		              ,{field:'crowdType', title:'人群类别' }
		              ,{field:'orgCode', width:170,title:'医疗机构编码'}
		              ,{field:'cityCode', title:'城市编码' }
		              ,{field:'condition',  width:150,title: '病情' }
		              ,{field:'billingNo',width:90, title:'收费单据号'}
		              ,{field:'inhosDate', title:'入院日期' }
		              ,{field:'outhosDate', title:'出院日期' }
		              ,{field:'balanceDate',width:90, title:'结算日期'}
		              ,{field:'dischargeState', title:'出院状态' }
		              ,{field:'treatmentWay', title:'就诊方式' }
		              ,{field:'stayLength', title:'住院天数' }
		              ,{field:'totalCost', title:'总金额' }
		              ,{field:'fundCost', title:'基金支付金额' }
		              ,{field:'selfCost', title:'个人负担金额' }
		              ,{field:'largeCostM', title:'大额应支付' }
		              ,{field:'largeCostR', title:'大额实支付' }
		              ,{field:'paymentDate',width:150, title:'费用发生日期'}
		              ,{field:'reversalMark',width:115, title:'冲销标志'}
		              ,{field:'medicalType',  width:90,title: '险种类型' }
		              ,{field:'basicCostM', width:90, title:'基本统筹应支付' }
		              ,{field:'povertyAlleviationSubsidy',  width:90,title:'扶贫补助' }
		              ,{field:'financeSubsidy', title:'财政补助' }
		              ,{field:'officialSubsidy',  width:115,title:'公务员补助' }
		              ,{field:'treatmentType',  width:115,title:'待遇享受类别' }
		              ,{field:'medicalTreatmentState',  width:115,title:'医疗待遇状态' }
		              ,{field:'createTime', title:'数据上传时间' }
		              ,{field:'rangeCost', title:'本次纳入报销范围金额' }
		              ,{field:'basicCostR', title:'基本统筹实际支付' }
		              ,{field:'selfPayAmount', title:'个人自付金额' }
		              ,{field:'selfExpenditureAmount', title:'个人自费金额' }
		              ,{field:'sscAccountCost', title:'个人账户自付' }
		              ,{field:'cashCost', title:'现金支付金额' }
		              ,{field:'civilAffairSubsidy', title:'民政救助'}
		              ,{field:'fullOrdination', title:'全额统筹' }
		              ,{field:'partialOrdination', title:'部分统筹' }
		              ,{field:'partialPayment', title:'部分自付' }
		              ,{field:'fullPayment', title:'全额自付' }
		              ,{field:'reimbursementType', title:'报销类型' }
		              ,{field:'hospCount', title:'住院次数' }
		              ,{field:'insuranceMark', title:'险种标志' }
		              ,{field:'inDiagnosisNo', title:'入院诊断编码' }
		              ,{field:'inDiagnosisName', title:'入院诊断名称' }
		              ,{field:'outDiagnosisNo', title:'出院诊断编码' }
		              ,{field:'outDiagnosisName', title:'出院诊断名称' }
		              ,{field:'diagType', title:'就诊类型' }
		           
		              
		            ]]
		            ,done:function(res){
		            	$('tr').eq(1).css("background-color","#EEF6FF");
		            	
		            	
		            	var result=res.data;
		            	
		            	if(result.length>0){
		            		medical_id=result[0].id;
		            		jh=result[0].aduitStatus;
		            		gs=result[0].resultAppealStatu;
		            		var getValue = JSON.stringify(layui.formSelects.value('select1', 'val'));
			        		is_ilegal=getValue.substring(1,getValue.length-1);
			            	$('#dg').datagrid('load',{'medicalDetail.medicalId': medical_id,'medicalDetail.isIlegal':is_ilegal});
		            		renovateTabs();//tab选项卡动态显示
			            	sysVerifyFourLoad(result[0],form);
		            	}else{
		            		medical_id='';
		            		//ycwsj();//tab选项卡动态显示,全隐藏
			            	sysVerifyFourLoad('',form);
			            	var getValue = JSON.stringify(layui.formSelects.value('select1', 'val'));
			        		is_ilegal=getValue.substring(1,getValue.length-1);
			            	$('#dg').datagrid('load',{'medicalDetail.medicalId': medical_id,'medicalDetail.isIlegal':is_ilegal});
		            	}
		            	//renovateTabs();//tab选项卡动态显示
		            	//sysVerifyFourLoad(result[0],form);
		            	$("#medical_id_form").val(medical_id);
		            	//console.info(medical_id);
		            	
		            	hideButtonStatic();//静态按钮权限控制
		            	//如果终审是正常，隐藏审核按钮
		            	if(result[0].finaStatus=='待终审'){
		            		$("#zsTj").show();
		            	}else{
		            		$("#zsTj").hide();
		            	}
		            	if(czHiden(jh,gs)){
	        	    		$('#dg').datagrid('hideColumn', 'operate');
	        	    	}else{
	        	    		$('#dg').datagrid('showColumn', 'operate');
	        	    	}
		            	if(jh=='-'||audit_methods_qj=='1'){
		            		$('#dg').datagrid('hideColumn', 'aduitStatus');
		            	}else{
		            		$('#dg').datagrid('showColumn', 'aduitStatus');
		            	}
		            	if (allExportData.length < 1){
		                    $.get( $WEB_ROOT_PATH+'/dhccApi/medical/medical/listVo1', '', function (res) {                    
		                        	allExportData=res;	                         
		                     });
		                }
		            	
		      		 }
		      		,page: true
        		});

		 layui.use(['table'],function(){
	    	 $("#medical-jpp-zs-medical-qbdc").click(function(){
	    		 var balanceDate,inhosDate,billingNo,diagType,finaStatus,idcard,name,orgCode,outhosDate;
	    		 if(excelData != null){
	    			/* field["medical.name"]*/
	    			 balanceDate = excelData.balanceDate;
	    			 inhosDate=excelData.inhosDate;
	    			 billingNo=excelData["medical.billingNo"];
	    			 diagType=excelData["medical.diagType"];
	    			 finaStatus=excelData["medical.finaStatus"];
	    			 idcard=excelData["medical.idcard"];
	    			 name=excelData["medical.name"];
	    			 outhosDate=excelData["outhosDate"];
	    			 orgCode=excelData["medical.orgCode"];
	    		 }	
	    		 
	    		 
	 	       	/*table.exportFile(ins1.config.id,allExportData,'xls');*/
	    		 window.open($WEB_ROOT_PATH+'/dhccApi/medicalexcel/medicalexcel/exportExcelToSelf-zs?balanceDate='+balanceDate+'&inhosDate='+inhosDate+'&billingNo='+billingNo+'&diagType='+diagType+'&finaStatus='+finaStatus+'&idcard='+idcard+ '&name='+name+'&orgCode='+orgCode+'&outhosDate='+outhosDate);
	         })
	    });
	   
	    table.on('tool(userTable)', function(obj){
	    	var data = obj.data;
	    	console.info(obj.event);
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
	    
	    
	   //行监听
	    table.on('row(userTable)', function(obj){
	    	$("#zsTj").show();
	    	$("tr").css("background-color",""); 
	         $(this).css("background-color","#EEF6FF"); 
	    	//刷新就诊明细
	    	var result=obj.data;
	    	//findMedicalDate(result.id);
	    	if(!result){
	    		return;
	    	}
	    	//将medical_id赋值到全局变量
	    	medical_id=result.id;
	    	jh=result.aduitStatus;
    		gs=result.resultAppealStatu;
    		 $("#ruleType").val('');//下表违规类型选中状态置空
    		 form.render('select');
	    	renovateTabs();//tab选项卡动态显示
	    	sysVerifyFourLoad(result,form);//加载4个tab违规统计
	    	//如果终审是正常，隐藏审核按钮
	    
	    	if(result.finaStatus=='待终审'){
	    		$("#zsTj").show();
	    	}else{
	    		$("#zsTj").hide();
	    	}
	    	hideButtonStatic();//静态按钮权限控制
	    	$("#medical_id_form").val(medical_id);
	    	var getValue = JSON.stringify(layui.formSelects.value('select1', 'val'));
    		is_ilegal=getValue.substring(1,getValue.length-1);
	    	$('#dg').datagrid('load',{'medicalDetail.medicalId': medical_id,'medicalDetail.isIlegal':is_ilegal}); 
	    	if(czHiden(jh,gs)){
	    		$('#dg').datagrid('hideColumn', 'operate');
	    	}else{
	    		$('#dg').datagrid('showColumn', 'operate');
	    	}
	    	if(jh=='-'||audit_methods_qj=='1'){
        		$('#dg').datagrid('hideColumn', 'aduitStatus');
        	}else{
        		$('#dg').datagrid('showColumn', 'aduitStatus');
        	}
	    });
	    
	    //监听搜索上
		form.on('submit(LAY-user-front-search)', function(data){
	    	var field = data.field;
	    	excelData = field;
	    	console.log(field);
	        //执行重载
	        layui.table.reload('userTable', {
	            where: field
	        });
	    });
	  //监听搜索下
		form.on('submit(LAY-user-front-search2)', function(data){
			var field = data.field;
			$('#dg').datagrid('load',field);
		});
		//监听批量提交上
		form.on('submit(LAY-user-front-search-zs-allUpdate1)', function(data){
			layer.confirm('确定要批量审核？', function(index){
			var url=$WEB_ROOT_PATH+'/dhccApi/medical/medical/updateFinaStatusAll';
				$.post(url,function(result){
					var inFlag= result.inFlag; 
					if(inFlag==0){
						layer.msg('批量审核成功!,共 '+result.plsh+' 条数据被审核。');
						table.reload('userTable'); //数据刷新
					}
				});
			});
		});
		 //终审审核提交
		$('#medical-jpp-zs-medical-tj').click(function(){
			var finaStatus=$("#violationStatus option:selected").val();
			//var finaStatus=$('input[name="violationStatus"]:checked').val();
			if(!finaStatus){
				layer.msg('请选择审核结果后再提交!');
				return;
			}
			var url=$WEB_ROOT_PATH+'/dhccApi/medical/medical/updateFinalStatusByMedicalId';
			var filed={"medical.finaStatus":finaStatus,"medical.id":medical_id};
			$.post(url,filed,function(result){
				var inFlag= result.inFlag; 
				if(inFlag==0){
					layer.msg('提交成功!');
					hxtjzt();
					table.reload('userTable'); //数据刷新
				}
			});

		});
		//按主病历详情点击事件
		$("#xq-jh").click(function(){
			layer.open({
				type: 2
				,title: '稽核信息'
				,content: $WEB_ROOT_PATH+'/medical/medicalAuditForm-jpp-zs-zbl'
				,maxmin: true
				,area: ['800px', '470px']
				,success: function(layero, index){
					var iframeWindow = window['layui-layer-iframe'+ index];
					var url=$WEB_ROOT_PATH+"/dhccApi/medicalaudit/medicalAudit/findZhuBiao?medicalId1="+medical_id;
			        var filed={"medicalAudit.medicalId":medical_id};
			        $.post(url,filed,function(result){
			        	iframeWindow.child(JSON.stringify(result));/*调用弹出窗口，填充该行数据到修改表单*/
			        });
				}
				
				}); 
		});
		$("#xq-gs").click(function(){
			layer.open({
				type: 2
				,title: '公示信息'
				,content: $WEB_ROOT_PATH+'/medical/resultAppeal/resultAppeallnfo-jpp-zs-main'
				,maxmin: true
				,area: ['800px', '470px']
				,success: function(layero, index){
					var iframeWindow = window['layui-layer-iframe'+ index];
			        iframeWindow.child(medical_id);/*调用弹出窗口，填充该行数据到修改表单*/
				}
				
				}); 
		});
	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });
//获取4表联查的数据
function findMedicalDate(medical_id){
	 var url=$WEB_ROOT_PATH+"/dhccApi/medical/medical/findMedicalDate";
	 $.post(url,{"medicalVoJpp.id":medical_id},function(result){
		 //console.log(result);
		 var medicalVoJpp=result.medicalVoJpp;
		 for ( var index in medicalVoJpp) {
			$("#" + index).html(medicalVoJpp[index]);
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
//保存详细下拉框,并更新该条明细审核IS_ILEGAL状态
function saveItem(index){
	var row = $('#dg').datagrid('getRows')[index];
	var withholdingQuantity= $("#withholdingQuantity").val();
	var withholdingAmount=$("#withholdingAmount").val();
	var deductions=$("#deductions").val();
	var violationStatus=$("input[name='violationStatus']:checked").val();
	 var field={"medicalCostVerify.medicalId":medical_id,"medicalCostVerify.costId":row.id,"medicalCostVerify.violationStatus":violationStatus,"medicalCostVerify.deductions":deductions,"medicalCostVerify.withholdingAmount":withholdingAmount,"medicalCostVerify.withholdingQuantity":withholdingQuantity};
	  var url=$WEB_ROOT_PATH+"/dhccApi/medical/medical/medicalCostSave";
	  $.post(url,field,function(result){
 	  if(result.inFlag==0){
 		  layer.msg('保存成功!');
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
	if(audit_methods_qj=='0'&&(isilegal=='0'||isilegal=='1')){
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
function sysVerifyFourLoad(row,form){
	sysVerify("sysVerifyTableDiv-cs",row,form,'cs');
	sysVerify("sysVerifyTableDiv-jh",row,form,'jh');
	sysVerify("sysVerifyTableDiv-gs",row,form,'gs'); 
	sysVerify("sysVerifyTableDiv-zs",row,form,'zs');
	showMonelyStatus(row);
}
//回显右侧金额、违规状态、备注等信息
function showMonelyStatus(row){
	if(row==''){
		$("#rightStatus-cs").html("初审结果： ");
		$("#rightMoney-cs").html("扣款金额：");
		$("#rightRemarks-cs").html("备注：");
		$("#rightStatus-jh").html("稽核结果：");
		$("#rightMoney-jh").html("扣款金额：");
		$("#rightRemarks-jh").html("备注：");
		$("#rightStatus-gs").html("公示结果： ");
		$("#rightMoney-gs").html("扣款金额：");
		$("#rightRemarks-gs").html("备注：");
		$("#rightStatus-zs").html("终审结果： ");
		$("#rightMoney-zs").html("扣款金额：");
		$("#rightRemarks-zs").html("备注：");
		return;
	}
	
	
	
	var url=$WEB_ROOT_PATH+"/dhccApi/medical/medical/findById";
	var filed={"medical.id":row.id};
	$.post(url,filed,function(result){
		var medical=result.medical;
		//console.log(medical);
		//全局变量赋值--审核方式
		audit_methods_qj=medical.auditMethods;
		showJHGS();
		//详情-按钮控制
		if(audit_methods_qj=='1'){
			$("#xq-jh").show();
			$("#xq-gs").show();
		}else{
			$("#xq-jh").hide();
			$("#xq-gs").hide();
		}
		
		
		var ust= medical.userStatus;
		var jhjg=medical.aduitStatus;
		var gsjg=medical.resultAppealStatu;
		var zsjg=medical.finaStatus;
		if(ust=='0'){
			ust="违规";
		}else if(ust=='1'){
			ust="疑似违规";
		}else if(ust=='2'){
			ust="正常";
		}else{
			ust="待初审";
		}
		if(jhjg=='0'){
			jhjg="违规";
		}else if(jhjg=='2'){
			jhjg="正常";
			$("#xq-jh").hide();
		}
		if(gsjg=='0'){
			gsjg="违规";
		}else if(gsjg=='2'){
			gsjg="正常";
		}
		if(zsjg=='0'){
			zsjg="违规";
		}else if(zsjg=='2'){
			zsjg="正常";
		}else{
			zsjg="待终审";
		}
		var zsje='';
		if(jh=='-'&&gs=='-'){
			if(zsjg=='正常'){
			zsje='';
			}else{
				zsje=medical.userVerifyMoney;
			}
			
		}else{
			zsje=medical.totalAmount;
		}
		if(medical){
			$("#rightStatus-cs").html("初审结果： "+ust);
			$("#rightMoney-cs").html("扣款金额："+(medical.userVerifyMoney==null?'':medical.userVerifyMoney));
			$("#rightRemarks-cs").html("备注："+(medical.userVerifyRemarks==null?'':medical.userVerifyRemarks));
			$("#rightStatus-jh").html("稽核结果："+jhjg);
			$("#rightMoney-jh").html("扣款金额："+(medical.totalAmount==null?'':medical.totalAmount));
			$("#rightRemarks-jh").html("备注：");
			$("#rightStatus-gs").html("公示结果： "+gsjg);
			$("#rightMoney-gs").html("扣款金额："+(medical.totalAmount==null?'':medical.totalAmount));
			$("#rightRemarks-gs").html("备注：");
			$("#rightStatus-zs").html("终审结果： "+zsjg);
			$("#rightMoney-zs").html("扣款金额："+(zsje==null?'':zsje));
			$("#rightRemarks-zs").html("备注："+(medical.finaVerifyRemarks==null?'':medical.finaVerifyRemarks));
		}
	});
}

//右侧违规统计显示
function sysVerify(tableNameId,row,form,shzt){
	//console.log(row);
	//var table=document.getElementById(tableNameId);
	//removeAllChild(table);
	
	var div=document.getElementById(tableNameId);
	//removeAllChild(div);
	$("#"+tableNameId).empty();
	//加一个新的空table  id="sysVerify" border="0" cellpadding="0" cellspacing="0"
	var table=document.createElement("table");
	//table.setAttribute("id","sysVerify");
	table.setAttribute("border","0");
	table.setAttribute("cellpadding","0");
	table.setAttribute("cellspacing","0");
	div.appendChild(table)
	if(row==''){
		//1.无违规信息--无
		var tr=document.createElement('tr');
		tr.innerHTML="<div style='width:100%;height:100%;text-align:center;'><div><img style='width:70px;' src="+$WEB_ROOT_PATH+"/images/empty.png></div><div style='line-height: 20px;'>无数据</div></div>";
		//tr.setAttribute("text-align","center");
		table.appendChild(tr);
		return;
	}
	
	
	
	//判断机审状态（1.未审核--该数据未机审  2无违规信息--无）
	var statu=row.sysStatus;
	if(statu){
		if(statu=='未违规'){
			//1.无违规信息--无
			var tr=document.createElement('tr');
			//tr.innerHTML='无违规信息';
			tr.innerHTML="<div style='width:100%;height:100%;text-align:center;'><div><img style='width:70px;' src="+$WEB_ROOT_PATH+"/images/empty.png></div><div style='line-height: 20px;'>无违规信息</div></div>";
			table.appendChild(tr);
		}else{
			//2.违规
			//ajax根据medical_id获取数据
			var url=$WEB_ROOT_PATH+"/dhccApi/violationdetails/violationDetail/findByMedicalId";
			var filed={"violationDetail.medicalId":medical_id,"shzt":shzt};
			$.post(url,filed,function(result){
				//2.1拼接数据
				console.log(shzt);
				console.log(result);
				var sysVerifyVo=result.sysVerifyVo;
				if(!sysVerifyVo){
					var tr=document.createElement('tr');
					tr.innerHTML='暂时无违规信息！';
					table.appendChild(tr);
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
				$("#"+tableNameId).on('click','.isexistNum',function(){
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
    //var table = document.getElementById(table);
	
    while(table.hasChildNodes()) //当div下还存在子节点时 循环继续
    {
        table.removeChild(table.firstChild);
    }
}
function switchChang(i,td,typeNames){
	if(!typeNames){
		td.innerHTML=i+1+"."+'其他';
	}else{
		td.innerHTML=i+1+"."+typeNames;
	}
}
function audit(index){
	event.stopPropagation();
    $('#dg').datagrid('selectRow',index);// 关键在这里  
    var row = $('#dg').datagrid('getSelected');
    if (row&&audit_methods_qj=='0'){  //按明细审核的且有值
    	layer.open({
		type: 2
		,title: '稽核信息'
		,content: $WEB_ROOT_PATH+'/medical/medicalAuditForm-jpp-zs'
		,maxmin: true
		,area: ['800px', '450px']
		,success: function(layero, index){
			var iframeWindow = window['layui-layer-iframe'+ index];
			var url=$WEB_ROOT_PATH+'/dhccApi/medicalaudit/medicalAudit/findAuditByMIdMDId';
	        var filed={"medicalAudit.medicalId":medical_id,"medicalAudit.medicalDetailId":row.id};
	       
	        $.post(url,filed,function(result){
	        	console.log(result); 
	        	iframeWindow.child(JSON.stringify(result));/*调用弹出窗口，填充该行数据到修改表单*/
	        });
			//向此iframe层方法 传递参数
		}
		
		}); 
    	
    }else{
    	layer.msg("该病历按主病例审核的，请点击右侧详情查看明细");
    }
    
}  
function audit1(index){
	event.stopPropagation();
	$('#dg').datagrid('selectRow',index);// 关键在这里  
	var row = $('#dg').datagrid('getSelected');
	if (row&&audit_methods_qj=='0'){  
		layer.open({
			type: 2
			,title: '公示信息'
				,content: $WEB_ROOT_PATH+'/resultAppeal/resultAppealInfo-jpp-zs'
				,maxmin: true
				,area: ['1000px', '450px']
		,success: function(layero, index){
			var iframeWindow = window['layui-layer-iframe'+ index];
			iframeWindow.child(row);/*调用弹出窗口，填充该行数据到修改表单*/
		}
		}); 
		
	}else{
    	layer.msg("该病历按主病例审核的，请点击右侧详情查看明细");
    }
}  
//回显提交状态
function hxtjzt(){
	$('input[name="violationStatus"]').attr("checked",false);
}
function czHiden(jh,gs){
	if(audit_methods_qj=='0'){
		if(jh=='-'){
			if(gs=='-'){
				return true;
			}else{
				return false;
			}
		}else{
			return false
		}
	}else{
		return true;
	}
	
}
//无数据，全隐藏
function ycwsj(){
	$("#csTab").hide();
	$("#jsTab").hide();
	$("#jhTab").hide();
	$("#gsTab").hide();
	$("#zsTab").hide();
}
//上表行点击及初始化，刷新右侧选项卡
function renovateTabs(){
	if(jh=='-'&&gs=='-'){
		$("#jhTab").hide();
		//$("#jhdiv").hide();
		$("#gsTab").hide();
		//$("#gsdiv").hide();
		return 'nothing';
	}
	if(gs=='-'){
		$("#jhTab").show();
		//$("#jhdiv").show();
		$("#gsTab").hide();
		//$("#gsdiv").hide();
		return 'jh';
	}else{
		$("#jhTab").hide();
		//$("#jhdiv").hide();
		$("#gsTab").show();
		//("#gsdiv").show();
		return 'gs';
	}
}
//右侧违规统计点击事件

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
	
	

	

//下表稽核公示查看显示控制
function showJHGS(){
	if(czHiden(jh,gs)){
		$('#dg').datagrid('hideColumn', 'operate');
	}else{
		$('#dg').datagrid('showColumn', 'operate');
	}
	if(jh=='-'||audit_methods_qj=='1'){
		$('#dg').datagrid('hideColumn', 'aduitStatus');
	}else{
		$('#dg').datagrid('showColumn', 'aduitStatus');
	}
}
