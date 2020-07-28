//初始化	
var medical_id='';
var tableAll;
var indexAll;
var billing_no='';
var is_ilegal='';
var index_last=null;
var statu_last=null;
var toolbarType="";
var formSelects = layui.formSelects;
var medicalId;
var excelData = null;
var historyNumber='';
var historyStatus='';
var audit_methods='';
var appeal_status='';
var appeal_id="";
var item_id ='';
var button_event ='';
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table','laydate','form','element'], function(){
		  var $ = layui.$
		  ,form = layui.form
		  ,table = layui.table
		  , laydate = layui.laydate;
		  tableAll=table;
		  var element = layui.element;
		  element.on('tab(docDemoTabBrief)', function(data){
			  findMedicalDate(medical_id);
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
		  laydate.render({
			  elem: '#balanceDate'
				  ,trigger:'click'
					  ,format:'yyyy-MM-dd'
						  ,range: true
		  });
		  
		  var dict_rule=localStorage.getItem('dict_rule');//从浏览器数据库取出
		  var rule=$.parseJSON(dict_rule);//解析成对象
		  if(rule){
			  for(var i=0 ;i<rule.length;i++){
				  var mm="<option value='"+rule[i].value+"'>"+rule[i].text+"</option>";
				  $("#ruleType").append(mm); 
			  }
			  form.render('select');
		  }else{
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
			  var org_save=JSON.stringify(orgs);//解析为字符串
			  localStorage.setItem('org_save',org_save);//存入浏览器数据库
			  for(var i=0 ;i<orgs.length;i++){
				  var mm="<option value='"+orgs[i].value+"'>"+orgs[i].text+"</option>";
				  $("#zyOrgName").append(mm);
			  }
			  form.render('select');
		  });


		  table.render({
			  elem: '#resultAppeal'
				  ,url: $WEB_ROOT_PATH+'/dhccApi/resultAppeal/resultAppeal/listAndFind'
				  ,cellMinWidth: 100
				  ,height: 226
				  ,limit: 5
				  ,cols: [[
					  {type: 'numbers',width:40, title: '序号',fixed: 'left' }
					  ,{field:'id', title: 'ID', sort: true, hide:true} 
				      ,{field:'sysStatus', width:90,align:'center',fixed:'left',title:'机审结果',templet: function(d){
						  var a =d.sysStatus;
						  if(a=="1"){
							  a="疑似违规";
						  }else if(a=="0"){
							  a="违规";
						  }else if(a=="2"){
							  a="正常";
						  }else if(a==null || a=="null"){
							  a="未机审";
						  }
						  return '<span >'+ a +'</span>'
					  }
					  }               
					  ,{field:'name', width:70, title:'姓名',align:'center'}
					  ,{field:'sex', width:70, title: '性别' ,align:'center'}
					  ,{field:'age', width:70, title:'年龄' ,align:'center'}
					  ,{field:'idcard', width:140, title:'身份证号' ,align:'center'}
					  ,{field:'sscno',  width:115,title: '社保卡号' ,align:'center'}
					  ,{field:'admissionNo', title:'住院号' ,align:'center'}
					  ,{field:'billingNo',width:90, title:'收费单据号',align:'center'}
					  ,{field:'orgName', title:'医疗机构' ,width:150,align:'center'}
					  ,{field:'departName', title:'科室' ,align:'center'}
					  ,{field:'inhosDate', title:'入院日期' ,align:'center'}
					  ,{field:'outhosDate', title:'出院日期' ,align:'center'}
					  ,{field:'condition',  width:90,title: '病情' ,align:'center'}
					  ,{field:'paymentDate',width:90, title:'费用发生日期',align:'center'}
					  ,{field:'crowdType', title:'人群类别' ,align:'center'}
					  ,{field:'orgCode', width:90,title:'医疗机构编码',align:'center'}
					  ,{field:'cityCode', title:'城市编码' ,align:'center'}
					  ,{field:'reversalMark',width:115, title:'冲销标志',align:'center'}
					  ,{field:'admissionType',  width:90,title: '住院类型',align:'center' }
					  ,{field:'medicalType',  width:90,title: '险种类型' ,align:'center'}
					  ,{field:'totalCost', title:'总金额' ,align:'center'}
					  ,{field:'fundCost', title:'基金支付金额' ,align:'center'}
					  ,{field:'selfCost', title:'个人负担金额' ,align:'center'}
					  ,{field:'basicCostM', width:90, title:'基本统筹应支付' ,align:'center'}
					  ,{field:'povertyAlleviationSubsidy',  width:90,title:'扶贫补助' ,align:'center'}
					  ,{field:'financeSubsidy', title:'财政补助' ,align:'center'}
					  ,{field:'officialSubsidy',  width:115,title:'公务员补助' ,align:'center'}
					  ,{field:'treatmentType',  width:115,title:'待遇享受类别' ,align:'center'}
					  ,{field:'medicalTreatmentState',  width:115,title:'医疗待遇状态' ,align:'center'}
					  ,{field:'dischargeState', title:'出院状态' ,align:'center'}
					  ,{field:'treatmentWay', title:'就诊方式' ,align:'center'}
					  ,{field:'stayLength', title:'住院天数' ,align:'center'}
					  ,{field:'createTime', title:'数据上传时间' ,align:'center'}
					  ,{field:'rangeCost', title:'本次纳入报销范围金额' ,align:'center'}
					  ,{field:'basicCostR', title:'基本统筹实际支付' ,align:'center'}
					  ,{field:'selfPayAmount', title:'个人自付金额' ,align:'center'}
					  ,{field:'selfExpenditureAmount', title:'个人自费金额' ,align:'center'}
					  ,{field:'sscAccountCost', title:'个人账户自付' ,align:'center'}
					  ,{field:'cashCost', title:'现金支付金额' ,align:'center'}
					  ,{field:'largeCostM', title:'大额应支付' ,align:'center'}
					  ,{field:'largeCostR', title:'大额实支付' ,align:'center'}
					  ,{field:'civilAffairSubsidy', title:'民政救助',align:'center'}
					  ,{field:'fullOrdination', title:'全额统筹' ,align:'center'}
					  ,{field:'partialOrdination', title:'部分统筹' ,align:'center'}
					  ,{field:'partialPayment', title:'部分自付' ,align:'center'}
					  ,{field:'fullPayment', title:'全额自付' ,align:'center'}
					  ,{field:'reimbursementType', title:'报销类型' ,align:'center'}
					  ,{field:'hospCount', title:'住院次数' ,align:'center'}
					  ,{field:'insuranceMark', title:'险种标志' ,align:'center'}
					  ,{field:'inDiagnosisNo', title:'入院诊断编码' ,align:'center'}
					  ,{field:'inDiagnosisName', title:'入院诊断名称' ,align:'center'}
					  ,{field:'outDiagnosisNo', title:'出院诊断编码' ,align:'center'}
					  ,{field:'outDiagnosisName', title:'出院诊断名称' ,align:'center'}
					  ,{field:'diagType', title:'就诊类型' ,align:'center',templet: function(d){
						  var b =d.diagType;
						  if(b=="1"){
							  b="住院";
						  }else if(b=="2"){
							  b="普通门诊";
						  }else if(b=="3"){
							  b="门诊大病";
						  }else if(b=="9"){
							  b="其他";
						  }
						  return '<span >'+ b +'</span>'
					  }}	           
					  ,{field:'countTime', title: '审核次数',width:90,align:'center',templet: function(d){
						  var b =d.countTime;
						  return '<span >第'+ b +'次</span>'
					  }}	 
					  ,{field:'examinePerson', title: '审核人',width:70,align:'center'}	 
					  ,{field:'medicalId', title: '病历编号',width:200,align:'center', hide:true}	 
					  //,{field:'createDate', title: '病历编号',width:200,align:'center', hide:true}	 
					  //,{field:'orgName', title: '机构名称',width:150,align:'center'}
					  //,{field:'orgCode', title: '机构编码',width:150,align:'center', hide:true}
					  //,{field:'appealReason', title: '申诉原因',width:410,align:'center'}
					  //,{field:'appealDate', title: '申诉日期',width:110,align:'center'}   
					  //,{field:'appealPerson',title: '申诉人',width:100,align:'center'}
					  //,{field:'examineComments',title: '审核意见',width:410,align:'center'}
					  //,{field:'examinePerson',title: '审核人',width:90,align:'center'}
					  //,{field:'createDate', title: '创建时间',width:110,align:'center'}
					  //,{field:'updateDate',title: '更新时间',width:110,width:110,align:'center'}
					  //,{field:'fileUrl',title: '审核结果ID',width:110,width:110,align:'center', hide:true}
					  //,{field:'originalFilename',title: '审核结果ID',width:110,width:110,align:'center', hide:true}

					  ]]
		  ,done:function(data){	 
			  $(document).find('[data-index="0"]').trigger("click");		
			  $(document).find('[data-index="0"]').css("background-color","#C0C0C0");

		  }
		  ,page: true
		  });
		  hideButtonStatic();//按钮权限


		  layui.use(['table'],function(){
			  $("#resultAppeal-sexportExcel").click(function(){
				  /*table.exportFile(ins1.config.id,allExportData,'xls');*/

				  var balanceDate,inhosDate,outhosDate,admissionNo,billingNo,diagType,idcard,ilegalType,name,orgName,sscno,status,sysStatus;
				  if(excelData != null){
					  /* field["medical.name"]*/
					  balanceDate = excelData.balanceDate;
					  inhosDate=excelData.inhosDate;
					  outhosDate=excelData["outhosDate"];
					  admissionNo=excelData["resultAppealVo.admissionNo"];
					  billingNo=excelData["resultAppealVo.billingNo"];
					  diagType=excelData["resultAppealVo.diagType"];
					  idcard=excelData["resultAppealVo.idcard"];
					  ilegalType=excelData["resultAppealVo.ilegalType"];
					  name=excelData["resultAppealVo.name"];
					  orgName=excelData["resultAppealVo.orgName"];
					  sscno=excelData["resultAppealVo.sscno"];
					  status=excelData["resultAppealVo.status"];
					  sysStatus=excelData["medical.sysStatus"];
				  }	
				  window.open($WEB_ROOT_PATH+'/dhccApi/medical/medical/exportExcelToSelf-gs?balanceDate='+balanceDate+'&inhosDate='+inhosDate+'&outhosDate='+outhosDate+'&admissionNo='+admissionNo+'&billingNo='+billingNo+'&diagType='+diagType+'&idcard='+idcard+ '&ilegalType='+ilegalType+'&name='+name+'&orgName='+orgName+'&sscno='+sscno+'&status='+status+'&sysStatus='+sysStatus);
			  })
		  });
		  form.on('submit(LAY-user-front-search)', function(data){
			  var field = data.field; 
			  excelData = field;
			  historyStatus=field["resultAppealVo.status"];
			  layui.table.reload('resultAppeal', {
				  where: field
			  });
			  $("#dg").datagrid('reload');
		  });
		  form.on('submit(botton-search)', function(data){
			  var field = data.field;
			  $('#dg').datagrid('load',field);
		  });
		  table.on('row(resultAppeal)', function(obj){	
			  var result=obj.data;
			  medical_id=result.medicalId;
			  appeal_id=result.id;
			  $("#medical_id_form").val(medical_id);
			  var getValue = JSON.stringify(layui.formSelects.value('select1', 'val'));
			  is_ilegal=getValue.substring(1,getValue.length-1);
			  $('#dg').datagrid('reload',{'medicalDetail.medicalId': medical_id,'medicalDetail.isIlegal':is_ilegal,'historyNumber':historyNumber}); 
			  $("tr").css("background-color",""); 
			  obj.tr.css("background-color","#C0C0C0");
		  });
		     //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });

$(function(){
	formSelects.data('select1', 'local', {
	    arr: [
	        {"name": "违规", "value": 0,"selected":"selected"},
	        {"name": "疑似违规", "value": 1,"selected":"selected"},
	        {"name": "正常", "value": 2}
	    ]
	});
		var getValue = JSON.stringify(layui.formSelects.value('select1', 'val'));
		is_ilegal=getValue.substring(1,getValue.length-1);	
		/* easyui渲染 */
		$('#dg').datagrid({		
			pageSize: 10,
			fit: false,
			remoteSort:false,
			nowrap:false,
			fitColumns:false,
			checkOnSelect: false, 
			selectOnCheck: false,
			autoRowHeight:false,
			onClickRow:tableRowClick,
			url:$WEB_ROOT_PATH+'/dhccApi/resultAppeal/resultAppeal/getList',		
			columns:[[	
				 {field:'id',title:'ID',width:80,hidden:true}
				,{field:'violationStatus', title: '初审结果',fixed: 'left' ,width:100,align:'center',formatter:function(value,row){
	        	    var a =row.violationStatus;
	            	if(a=="1"){
	            		a="疑似违规";
	            	}else if(a=="0"){
	            		a="违规";
	            	}else if(a=="2"){
	            		a="正常";
	            	}else if(a==null || a=="null"){
	            		a="未初审";
	            	}
	                return '<span >'+ a +'</span>'
	          	},align:'center'}
				,{field:'isIlegal', title: '机审结果',fixed: 'left' ,width:100,formatter:function(value,row){
	        	    var a =row.isIlegal;
	            	if(a=="1"){
	            		a="疑似违规";
	            	}else if(a=="0"){
	            		a="违规";
	            	}else if(a=="2"){
	            		a="正常";
	            	}else if(a==null || a=="null"){
	            		a="未机审";
	            	}
	                return '<span >'+ a +'</span>'
	          	},align:'center'}
				,{field:'itemCode', title: '项目编号',width:180,align:'center'}
	            ,{field:'itemName', title: '项目名称',width:150,align:'center'}
	            ,{field:'drugType', title: '药品类别',width:80,align:'center'}
	            ,{field:'itemCost', title: '项目金额',align:'center'}
	            ,{field:'sumAmount', title: '总金额',align:'center'}
	            ,{field:'itemNum', title: '项目数量',align:'center'}
	            ,{field:'itemPrice', title: '项目单价',align:'center'}
	            ,{field:'itemStandard', title: '项目规格',width:80,align:'center'}
	            ,{field:'doseForm', title: '剂型',align:'center'}
	            ,{field:'limitPrice', title: '限价金额',align:'center'}
	            ,{field:'partialOrdination', title: '部分统筹',align:'center'}
	            ,{field:'partialPayment', title: '部分自付',align:'center'}
	            ,{field:'recipelId', title: '门诊处方编号',align:'center'}
	            ,{field:'selfPayAmount', title: '自付金额',align:'center'}
	            ,{field:'singleDose', title: '单次用量',align:'center'}
	            ,{field:'takeFrequence', title: '服用频次',align:'center'}
	            ,{field:'useDay', title: '用药天数',align:'center'}
	            ,{field:'applyPayAmount', title: '报销金额',align:'center'}
	            ,{field:'ilegalType', title: '违规类型',align:'center'}
	            ,{field:'applyPayLevel',align:'center', title: '报销级别',formatter: function(value){
	        	    var a =value;
	            	if(a=="1"){
	            		a="甲类";
	            	}else if(a=="2"){
	            		a="乙类";
	            	}else if(a=="3"){
	            		a="丙类";
	            	}else if(a==null || a=="null"){
	            		a="";
	            	}
	                return '<span >'+ a +'</span>'
	          }}
	            ,{field:'chargeType', title: '收费类别',align:'center'}
	            ,{field:'deliverWay',title: '给药途径',align:'center'}
	            ,{field:'doseUnit', title: '用量单位',align:'center'}
	            ,{field:'feeCreateDate', title: '费用发生时间',width:100,align:'center'}
	            ,{field:'fullOrdination', title: '全额统筹',align:'center'}
	            ,{field:'fullPayment', title: '全额自付',align:'center'}
	            ,{field:'isInsuranceProject', title: '是否医保项目',align:'center',formatter: function(value){
	        	    var a =value;
	            	if(a=="0"){
	            		a="否";
	            	}else if(a=="1"){
	            		a="是"
	            	}else if(a==null || a=="null"){
	            		a="";
	            	}
	                return '<span >'+ a +'</span>'
	          }}
			]],
			view: detailview,	
			onLoadSuccess: function(data){
				for(var i=0;i<data.rows.length;i++){
					$(this).datagrid('getExpander', i).html(i+1);
				}
			},
			detailFormatter:function(index,row){
				return '<div class="ddv"></div>';
			},
			onExpandRow: function(index,row){
				var ddv = $(this).datagrid('getRowDetail',index).find('div.ddv');	
	            $("#ExpandRow").val(index);
				ddv.panel({
					border:true,
					cache:true,
					href:$WEB_ROOT_PATH+'/resultAppeal/showForm?index='+index,
					onLoad:function(){
						$('#dg').datagrid('fixDetailRowHeight',index);
						$('#dg').datagrid('selectRow',index);
						var table=$('#dg').datagrid('getRowDetail',index).find('table.showtable');
						//动态拼接
						var url=$WEB_ROOT_PATH+"/dhccApi/violationdetails/violationDetail/findByMId";
						var filed={"violationDetail.medicalId":medical_id,"violationDetail.medicalDetailId":row.id};
						$.post(url,filed,function(result){
							var violationDetails=result.violationDetails;
							if(violationDetails){
							
							if(violationDetails.length>0){
								for(var i=0;i<violationDetails.length;i++){
									var tr=document.createElement("tr");
									var td=document.createElement("td");
									var typeNo=parseInt(violationDetails[i].typeNo);
								    td.innerHTML=i+1+"."+violationDetails[i].typeName;
									td.setAttribute("style","width: 280px;height:28px");
									var td2=document.createElement("td");
									td2.innerHTML='违规原因：'+violationDetails[i].returnDesc;
									td2.setAttribute("colspan","2");
									tr.appendChild(td);
									tr.appendChild(td2);
									table.append(tr);
								}
							}
							}
							//详情框赋值
							var url=$WEB_ROOT_PATH+"/dhccApi/resultAppeal/resultAppeal/getCostDetail";									
							$.post(url,{"medicalDetail.id":row.id},function(result){
								result=result.medicalDetail;
								//修正后待验证
								 $('#dg').datagrid('getRowDetail',index).find('form').form('load',result);													
							});
							
						});
				
					}					
				});				
				$('#dg').datagrid('fixDetailRowHeight',index);//固定详情页面高度
			}
		});
	   $("#dg").datagrid('resize'); 
	   //$('.datagrid-cell').css('font-size','10px');
	   $('.datagrid-header .datagrid-cell span ').css('font-size','10px');   
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
		 if(medicalVoJpp["diagType"]=='1'){
			 $("#diagType").html("住院");
		 }else if(medicalVoJpp["diagType"]=='2'){
			 $("#diagType").html("普通门诊");
		 }else if(medicalVoJpp["diagType"]=='3'){
			 $("#diagType").html("门诊大病");
		 }else{
			 $("#diagType").html("其他");
		 }closeLayer
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
	  var isIlegal=row.isIlegal;
      if(isIlegal==2){
    	  return;
      }
      item_id=row.id;
      var appealResult=row.appealResult;
      if(appealResult==2){
    	  return;
      }
	 if(audit_methods==0){
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
function removeAllChild(table){
  
    while(table.hasChildNodes()) //当div下还存在子节点时 循环继续
    {
        table.removeChild(table.firstChild);
    }
}
function switchChang(i,td,typeNo){
	var typeNo=parseInt(typeNo);
	switch(typeNo) {
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
	}
}
function child(obj) {
	var medicalExamine = JSON.parse(obj);
	medicalId = medicalExamine.id;
}