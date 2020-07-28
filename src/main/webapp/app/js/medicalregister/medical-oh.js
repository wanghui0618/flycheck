var medical_id='';//medical表id
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
				     		}
				     	form.render('select');
			});
		}
		//加载医院下拉字典
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
           });
	    
	   var allExportData = [];
	   var ins1= table.render({
	    	elem: '#userTable'
	            ,url: $WEB_ROOT_PATH+'/dhccApi/medicalregister/medicalRegister/listVo'
	            ,cellMinWidth: 100
	             ,height: 230
	             ,limit: 5
	             ,where:{"type":type,"comfrom":"cs"}
	            ,cols: [[
	            	   {type: 'numbers', title: '序号' }
		              ,{field:'id', title: 'ID', sort: true, hide:false}
		              ,{field:'sysStatus', title:'机审结果' }
		              ,{field:'name', width:120, title:'姓名'}
		              ,{field:'sex', title: '性别' }
		              ,{field:'age', title:'年龄' }
		              ,{field:'idCard', title:'身份证号' }
		              ,{field:'aduitStatus', title:'稽核状态' }
		              ,{field:'balanceArea', title:'结算地点' }
		              ,{field:'balanceAreaFlag', title:'异地结算标志'}
		              ,{field:'balanceDate', title:'结算日期'}
		              ,{field:'balanceId', title:'结算编号'}
		              ,{field:'cityCode', title:'城市编号'}
		              ,{field:'cityName', title:'城市名称'}
		              ,{field:'departCode', title:'科室编码'}
		              ,{field:'departName', title:'科室名称'}
		              ,{field:'docCode', title:'医师编码'}
		              ,{field:'docName', title:'医师名称'}
		              ,{field:'finaStatus', title:'终审状态'}
		              ,{field:'handdingInsCode', title:'经办机构编码'}
		              ,{field:'handdingInsName', title:'经办机构名称'}
		              ,{field:'hosCode', title:'医院编码'}
		              ,{field:'hosName', title:'医院名称'}
		              ,{field:'inhosDate', title:'入院日期'}
		              ,{field:'inhosSeeDoc', title:'入院诊断'}
		              ,{field:'inhosWay', title:'入院方式'}
		              ,{field:'insPersonType', title:'参保人员类别'}
		              ,{field:'insuranceType', title:'险种标志'}
		              ,{field:'outhosDate', title:'出院时间'}
		              ,{field:'outhosFlag', title:'出院出院标志'}
		              ,{field:'outhosReason', title:'出院原因'}
		              ,{field:'outhosSeeDoc', title:'出院诊断'}
		              ,{field:'personFlag', title:'人员状态'}
		              ,{field:'personType', title:'人员类别'}
		              ,{field:'resultAppealStatus', title:'公示状态'}
		              ,{field:'seeDocDetail', title:'就诊类别明细'}
		              ,{field:'seeDocId', title:'就诊登记编码'}
		              ,{field:'seeDocType', title:'就诊类别'}
		              ,{field:'selfMedical', title:'个人医疗年度'}
		              ,{field:'unitId', title:'单位编码'}
		              ,{field:'userStatus', title:'初审结果'}
		             
		            ]]
		            ,done:function(res){
		            	$('tr').eq(1).css("background-color","#C0C0C0");
		            	/*
		            	if (allExportData.length < 1){
		                    $.get( $WEB_ROOT_PATH+'/dhccApi/medical/medical/listVo1', '', function (res) {                    
		                        	allExportData=res;	                         
		                     });
		                }*/
		            	
		            	
		            	//console.info(res);
		            	var result=res.data;
		            	//console.info(result[0]);
		            	if(result.length>0){
			            	medical_id=result[0].id;
			            	//findMedicalDate(medical_id);
			            	sysVerify(result[0],form);
			            	amountTotal();//金额统计
			            	$("#medical_id_form").val(medical_id);
		            	}
		            	//console.info(medical_id);
		            	var getValue = JSON.stringify(layui.formSelects.value('select1', 'val'));
		        		is_ilegal=getValue.substring(1,getValue.length-1);
		            	$('#dg').datagrid('load',{'medicalDetail.medicalId': medical_id,'medicalDetail.isIlegal':is_ilegal,'comOrigin':'oh'});
		            
		      		 }
		      		,page: true
        		});
	    
		 layui.use(['table'],function(){

	    	 $("#medical-jpp-qbdc").click(function(){
	 	       	/*table.exportFile(ins1.config.id,allExportData,'xls');*/
	    		var balanceDate,inhosDate,billingNo,diagType,idcard,name,orgCode,sysStatus,outhosDate;
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
	    		 }
	    		 window.open($WEB_ROOT_PATH+'/dhccApi/medical/medical/exportExcelToSelf?balanceDate='+balanceDate+'&inhosDate='+inhosDate+'&billingNo='+billingNo+'&diagType='+diagType+'&idcard='+idcard+ '&name='+name+'&orgCode='+orgCode+'&sysStatus='+sysStatus+'&outhosDate='+outhosDate);
	         
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
	         $(this).css("background-color","#C0C0C0"); 
	    	//刷新就诊明细
	    	var result=obj.data;
	    	//findMedicalDate(result.id);
	    		
	    	//将medical_id赋值到全局变量
	    	medical_id=result.id;
	    	hxtjzt(0);//回显选中状态--中间提交
	    	sysVerify(result,form);
	    	amountTotal();//金额统计
	    	 $("#ruleType").val('');//下表违规类型选中状态置空
	    	 form.render('select');
	    	$("#medical_id_form").val(medical_id);
	    	var getValue = JSON.stringify(layui.formSelects.value('select1', 'val'));
    		is_ilegal=getValue.substring(1,getValue.length-1);
	    	$('#dg').datagrid('reload',{'medicalDetail.medicalId': medical_id,'medicalDetail.isIlegal':is_ilegal,'comOrigin':'oh'}); 
	    	
	    });
	    
	    //监听搜索上
		form.on('submit(LAY-user-front-search)', function(data){
			excelData = data.field;
	    	var field = data.field;
	    	console.log(field);
	        //执行重载
	        layui.table.reload('userTable', {
	            where: field
	        });
	    });
		//监听批量提交上
		form.on('submit(LAY-user-front-search-allUpdate)', function(data){
			layer.confirm('确定要批量审核？', function(index){
			var url=$WEB_ROOT_PATH+'/dhccApi/medicalregister/medicalRegister/updateUserStatusByMedicalId';
			var filed={"isAll":'pl'};
			$.post(url,filed,function(result){
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
			field['comOrigin']='oh';
			$('#dg').datagrid('reload',field);
		});
	  //人工审核提交
		$('#medical-jpp-tj').click(function(){
			var userStatus=$('input[name="violationStatus"]:checked').val();
			if(!userStatus){
				layer.msg('请选择审核结果后再提交!');
				return;
			}
			var url=$WEB_ROOT_PATH+'/dhccApi/medicalregister/medicalRegister/updateUserStatusByMedicalId';
			var filed={"medicalRegister.userStatus":userStatus,"medicalRegister.id":medical_id};
			$.post(url,filed,function(result){
				var inFlag= result.inFlag; 
				if(inFlag==0){
					layer.msg('提交成功!');
					table.reload('userTable'); //数据刷新
					hxtjzt();
				}
			});

		});
		 //人工审核提交
		$('#medical-jpp-zy1-tj').click(function(){
			 var userStatus=$("#violationStatus option:selected").val();
			//var userStatus=$('input[name="violationStatus"]:checked').val();//去向
			if(!userStatus){
				layer.msg('请选择去向后，再提交!');
				return;
			}else{
				var shfs=$("#packType option:selected").val();
				if(shfs=='1'){
					//按主病例审核
					var zblzt=$("#packTypeReact option:selected").val();//主病例审核状态
					//alert(zblzt);
					if(!zblzt){
						layer.msg('请选择主病例审核结果后再提交!');
						return;
					}else{
						var amountTotals=$("#amountTotal").val();//初审预扣金额
						var amountRemarks=$("#amountRemarks").val();//初审预扣金额备注
						var url=$WEB_ROOT_PATH+'/dhccApi/medicalregister/medicalRegister/updateUserStatusByMedicalId';
						var filed={"medicalRegister.userStatus":zblzt,"medicalRegister.id":medical_id,"medicalRegister.userVerifyDirection":userStatus,"medicalRegister.userVerifyMoney":amountTotals,"medicalRegister.userVerifyRemarks":amountRemarks,"medicalRegister.auditMethods":shfs};
						$.post(url,filed,function(result){
							var inFlag= result.inFlag; 
							if(inFlag==0){
								layer.msg('提交成功!');
								table.reload('userTable'); //数据刷新
								hxtjzt();
							}
						});
					}
				}else{
					//按明细审核
					//判断是否全部初审，如果全部初审--生成初审整体结果
					var url=$WEB_ROOT_PATH+'/dhccApi/medicalregister/medicalRegister/judgeUserStatus';
					var filed={"medicalRegister.id":medical_id};
					$.post(url,filed,function(result){
						//alert(result);
						if(result=='noFinish'||result=='noDetails'){//初审状态
							layer.msg('还有病例明细未审核或无明细!');
							return;
						}else{
							if(userStatus=='2'&&result!='2'){
								
									layer.msg('病例存在违规或疑似违规，不能提交至正常!');
									return;
								
							}else{
							
								var amountTotals=$("#amountTotal").val();//初审预扣金额
								var amountRemarks=$("#amountRemarks").val();//初审预扣金额备注
								var url=$WEB_ROOT_PATH+'/dhccApi/medicalregister/medicalRegister/updateUserStatusByMedicalId';
								var filed={"medicalRegister.userStatus":result,"medicalRegister.id":medical_id,"medicalRegister.userVerifyDirection":userStatus,"medicalRegister.userVerifyMoney":amountTotals,"medicalRegister.userVerifyRemarks":amountRemarks,"medicalRegister.auditMethods":shfs};
								$.post(url,filed,function(result){
									var inFlag= result.inFlag; 
									if(inFlag==0){
										layer.msg('提交成功!');
										table.reload('userTable'); //数据刷新
										hxtjzt();
									}
								});
							
							}
						}
					
					});
				}
			}
		});
		//批量明细审核（复选框选中的）
		$("#medical-jpp-detail-zy-pltj").click(function(){
			var rows = $('#dg').datagrid('getSelections'); 
			//console.log(rows);
			if(rows.length==0){
				layer.msg('未选择数据，不能批量提交!');
				return;
			}
			var userStatusPl=$('input[name="violationStatusPl"]:checked').val();
			if(!userStatusPl){
				layer.msg('未选择审核状态，不能批量提交!');
			}else{
				 var url=$WEB_ROOT_PATH+"/dhccApi/medicalregister/medicalRegister/userStatusPl";
				 var rows=JSON.stringify(rows);
				 $.post(url,{"MedicalDetailPl":rows,"userStatusPl":userStatusPl,'medicalIdPl':medical_id},function(result){
					 if(result.inFlag=='0'){
						 layer.msg("提交成功");
						 amountTotal();//右侧总金额刷新
						 refreshPlDetailsRadio();//提交状态置空
				 		 var getValue = JSON.stringify(layui.formSelects.value('select1', 'val'));
				 		 is_ilegal=getValue.substring(1,getValue.length-1);
					     $('#dg').datagrid('reload',{'medicalDetail.medicalId': medical_id,'medicalDetail.isIlegal':is_ilegal,'comOrigin':'oh'});
					 }else{
						 layer.msg("提交失败");
					 }
				 });
			}
		});
	    
	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });
//获取4表联查的数据
function findMedicalDate(medical_id){
	 var url=$WEB_ROOT_PATH+"/dhccApi/medicalregister/medicalRegister/findMedicalRegisterDate";
	 $.post(url,{"medicalRegister.id":medical_id},function(result){
		
		 var medicalVoJpp=result.medicalRegisterDetailVo;
		 console.log(medicalVoJpp);
		 for( var index in medicalVoJpp) {
				$("#"+index).html(medicalVoJpp[index]);
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
	var field={"costVerifyOH.medicalId":medical_id,"costVerifyOH.costId":row.id,"costVerifyOH.violationStatus":violationStatus,"costVerifyOH.deductions":deductions,"costVerifyOH.withholdingAmount":withholdingAmount,"costVerifyOH.withholdingQuantity":withholdingQuantity};
	  var url=$WEB_ROOT_PATH+"/dhccApi/medicalregister/medicalRegister/medicalCostSave";
	  $.post(url,field,function(result){
 	  if(result.inFlag==0){
 		  layer.msg('保存成功!');
 		 amountTotal();//金额统计
 		  //刷新
 		 var getValue = JSON.stringify(layui.formSelects.value('select1', 'val'));
 		 is_ilegal=getValue.substring(1,getValue.length-1);
	     $('#dg').datagrid('reload',{'medicalDetail.medicalId': medical_id,'medicalDetail.isIlegal':is_ilegal,'comOrigin':'oh'}); 
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
	var table=document.getElementById("sysVerify");
	removeAllChild(table);
	//判断机审状态（1.未审核--该数据未机审  2无违规信息--无）
	var statu=row.sysStatus;
	if(statu){
		if(statu=='未违规'){
			//1.无违规信息--无
			var tr=document.createElement('tr');
			tr.innerHTML='无违规信息';
			//tr.setAttribute("text-align","center");
			table.appendChild(tr);
		}else{
			//2.违规
			//ajax根据medical_id获取数据
			var url=$WEB_ROOT_PATH+"/dhccApi/medicalregister/medicalRegister/findByMedicalId";
			var filed={"medicalRegister.id":medical_id,"comfrom":'cs'};
			$.post(url,filed,function(result){
				//2.1拼接数据
				console.log(result.sysVerifyVos);
				var sysVerifyVo=result.sysVerifyVos;
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
					var td=document.createElement('td');
					var td2=document.createElement('td');
					var td3=document.createElement('td');
					switchChang(i,td,sysVerifyVo[i].typeNo,sysVerifyVo[i].typeNames);
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
//右侧违规明细点击事件
function wgDetailonclick(typeno,form){
	var t=$(typeno).attr("typevalue");
	//alert(t);
	 $("#ruleType").find("option[value ='"+t+"']").attr("selected","selected");
	 form.render('select');
	//刷新病例明细
	 var getValue = JSON.stringify(layui.formSelects.value('select1', 'val'));
	 is_ilegal=getValue.substring(1,getValue.length-1);
    $('#dg').datagrid('reload',{'medicalDetail.medicalId': medical_id,'medicalDetail.isIlegal':is_ilegal,'medicalDetail.ilegalType':t,'comOrigin':'oh'});
}
function removeAllChild(table){
    while(table.hasChildNodes()) //当div下还存在子节点时 循环继续
    {
        table.removeChild(table.firstChild);
    }
}
function switchChang(i,td,typeNo,typeNames){
	var typeNo=parseInt(typeNo);
	if(!typeNames){
		td.innerHTML=i+1+"."+'其他';
	}else{
		td.innerHTML=i+1+"."+typeNames;
	}
	/*switch(typeNo) {
    case 10:
   	 td.innerHTML=i+1+"."+'限性别用药或诊疗项目';
       break;
    case 20:
   	 td.innerHTML=i+1+"."+'限儿童用药或诊疗项目';
       break;
    case 30:
   	 td.innerHTML=i+1+"."+'重复用药审核';
       break;
    case 50:
   	 td.innerHTML=i+1+"."+'药物配伍禁忌审核';
       break;
    case 60:
   	 td.innerHTML=i+1+"."+'肝功能异常用药提醒';
       break;
    case 70:
   	 td.innerHTML=i+1+"."+'肾功能异常用药提醒';
       break;
    case 80:
   	 td.innerHTML=i+1+"."+'限工商保险审核';
       break;
    case 90:
   	 td.innerHTML=i+1+"."+'限生育保险药品审核';
       break;
    case 100:
   	 td.innerHTML=i+1+"."+'限适应症药品审核';
       break;
    case 110:
   	 td.innerHTML=i+1+"."+'限二线用药药品审核';
       break;
    case 120:
   	 td.innerHTML=i+1+"."+'限耐药菌引起的感染类审核';
       break;
    case 130:
   	 td.innerHTML=i+1+"."+'限特定疾病药品审核';
       break;
    case 140:
   	 td.innerHTML=i+1+"."+'限医院等级用药';
       break;
    case 150:
   	 td.innerHTML=i+1+"."+'限抢救用药';
       break;
    case 160:
   	 td.innerHTML=i+1+"."+'诊疗项目收费冲突审核';
       break;
    case 170:
   	 td.innerHTML=i+1+"."+'分解住院或住院天数异常';
       break;
    case 180:
   	 td.innerHTML=i+1+"."+'超标准收费审核';
       break;
    case 190:
   	 td.innerHTML=i+1+"."+'项目超限次审核';
       break;
    case 200:
   	 td.innerHTML=i+1+"."+'药品超每日最大用量审核';
       break;
    case 210:
   	 td.innerHTML=i+1+"."+'中药饮片单味审核';
       break;
    case 220:
   	 td.innerHTML=i+1+"."+'限禁忌症用药';
       break;
    case 230:
   	 td.innerHTML=i+1+"."+'限禁忌症用药(疾病相关)';
       break;
    default:
   	 td.innerHTML=i+1+"."+'其他';
	}*/
}
//回显提交状态
function hxtjzt(){
	//$('input[name="violationStatus"]').attr("checked",false);
	$("#violationStatus").val("");
	$("#packTypeReact").val("");
}
//违规金额统计(初审)
function  amountTotal(){
	var url=$WEB_ROOT_PATH+"/dhccApi/medicaldetail/medicalDetail/amountTotal";
	var filed={"medicalDetail.medicalId":medical_id,"comOrigin":'zy'};
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