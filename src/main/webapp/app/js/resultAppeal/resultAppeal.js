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
			$.getJSON($WEB_ROOT_PATH+'/dhccApi/resultAppeal/resultAppeal/findUnitType',function(unitType){				
				toolbarType = unitType[0];
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
			//加载规则下拉字典
			var dict_rule=localStorage.getItem('dict_rule');//从浏览器数据库取出
			var rule=$.parseJSON(dict_rule);//解析成对象
			if(rule){
				for(var i=0 ;i<rule.length;i++){
	     			var mm="<option value='"+rule[i].value+"'>"+rule[i].text+"</option>";
	     			$("#ruleType1").append(mm); 
	     		}
				form.render('select');
			}else{
				//加载违规类型下拉字典
				$.getJSON($WEB_ROOT_PATH+'/dhccApi/dict/dict/findDict?dictRequestVo.tableName=t_piccbid_dict_rule', 
							function(data){
					     		var  dataList= data.dictList;
					     		var dict_rule=JSON.stringify(dataList);//解析为字符串
					     		for(var i=0 ;i<dataList.length;i++){
					     			var mm="<option value='"+dataList[i].value+"'>"+dataList[i].text+"</option>";
					     			$("#ruleType1").append(mm); 
					     		}
					     	form.render('select');
				});
			}
	      //加载医院下拉字典
	        $.getJSON($WEB_ROOT_PATH+'/dhccApi/dataauthority/dataauthority/findOrg',function(data){
	                var orgs=data.pageModel.rows;
	                for(var i=0 ;i<orgs.length;i++){
	                    var mm="<option value='"+orgs[i].value+"'>"+orgs[i].text+"</option>";
	                    $("#orgName").append(mm);
	                }
	                form.render('select');
	           });

		  table.render({
			  elem: '#resultAppeal'
				  ,url: $WEB_ROOT_PATH+'/dhccApi/resultAppeal/resultAppeal/listAndFind'
				  ,cellMinWidth: 100
				 // ,height: 226
				  ,limit: 5
				  ,limits:[5,10,20]
				  ,cols: [[
					  {type: 'numbers',width:40, title: '序号',fixed: 'left' }
					  ,{field:'id', title: 'ID', sort: true, hide:true} 
					  ,{field:'unitType',title: '操作',fixed: 'left' , width:260,align:'center',templet: function(d){
						  var auditMethods=d.auditMethods;
						  var money=d.totalAmount;
						  var reason=d.userVerifyRemarks;
						  var type=d.status;
						  var appealId=d.id;
						  var medicalId=d.medicalId;
						  var appealReason=d.appealReason;
						  var examineComments=d.examineComments;
						  var clickID="addInfoMain('"+appealId+"','"+money+"','"+reason+"')";
						  if(toolbarType==1 && ((type==6||type==2)&&auditMethods==0)){
							  {{if (!existsButton('resultAppeal-agree-reject')) { }}		          
							  return '<a class="layui-btn layui-btn-xs" lay-event="agree"><i class="layui-icon layui-icon-ok"></i>接受</a><a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="reject"><i class="layui-icon layui-icon-no">&#x1006;</i>不接受</a>';
							  {{} }}
						  }else if(toolbarType==1 && (type==2 && (auditMethods==1 && (examineComments==null||examineComments=='')))){					
							  return '<a  class="layui-btn layui-btn-normal layui-btn-xs" onclick="'+clickID+'"style="width:110px"><i class="layui-icon layui-icon-edit">材料（未审核）</i></a>'	
						  }else if(toolbarType==1 && (type==2 && auditMethods==1)){							  
							  return '<a class="layui-btn layui-btn-xs" lay-event="agree"><i class="layui-icon layui-icon-ok"></i>接受</a><a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="reject"><i class="layui-icon layui-icon-no">&#x1006;</i>不接受</a><a  class="layui-btn layui-btn-normal layui-btn-xs" onclick="'+clickID+'"style="width:90px">材料（已审核）</a>';
						  }else if(toolbarType==2 && (type==2 && auditMethods==0)){
							  return '<span style="color:black "; >'+ "等待对方处理 "+'</span>';	
						  }else if(toolbarType==2 && (type==2 && (auditMethods==1 && (examineComments==null||examineComments=='')))){							
							  return '<span style="color:black "; >'+ "等待对方处理 "+'</span>  <a  class="layui-btn layui-btn-normal layui-btn-xs" onclick="'+clickID+'"style="width:90px">材料（未审核）</a>';	
						  }else if(toolbarType==2 && (type==2 && auditMethods==1)){
							  return '<span style="color:black "; >'+ "等待对方处理 "+'</span>  <a  class="layui-btn layui-btn-normal layui-btn-xs" onclick="'+clickID+'"style="width:90px">材料（已审核）</a>';	
						  }else if(toolbarType==2 && (type==4||type==0)){
							  {{if (!existsButton("resultAppeal-yes-no")) { }}
							  return '<a class="layui-btn layui-btn-xs" lay-event="yes"><i class="layui-icon layui-icon-ok"></i>接受</a><a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="no"><i class="layui-icon layui-icon-no">&#x1006;</i>不接受</a>'	
							  {{} }}
						  }else if(toolbarType==2 && ((type==5||type==2)&&auditMethods==0)){
							  {{if (!existsButton("resultAppeal-send")) { }}
							  return '<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="send"><i class="layui-icon">发送</i></a>'	
							  {{} }}			               
						  }else if((toolbarType==2 && (type==5 && auditMethods==1) && (appealReason==null||appealReason==""))){		      
							  {{if (!existsButton("resultAppeal-send")) { }}
							 
							  return '<a  class="layui-btn layui-btn-normal layui-btn-xs" onclick="'+clickID+'"style="width:110px"><i class="layui-icon layui-icon-edit">提交材料</i></a>'	
							  {{} }}		               
						  }else if(toolbarType==2 && (type==5 && auditMethods==1)){		      
							  {{if (!existsButton("resultAppeal-send")) { }}
							
							  return '<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="send"><i class="layui-icon">发送</i></a><a class="layui-btn layui-btn-normal layui-btn-xs" onclick="'+clickID+'" style="width:110px"><i class="layui-icon layui-icon-edit">材料（已提交）</i></a>'	
							  {{} }}		               
						  }else if((type==1||type==3)&& auditMethods==0){
							  return '<span style="color:#00CC33"; >'+ "审核结果双方已确认 "+'</span>';
						  }else if((type==1||type==3)&& auditMethods==1){

							  return '<span style="color:#00CC33 "; >'+ "审核结果双方已确认 "+'</span>  <a  class="layui-btn layui-btn-normal layui-btn-xs" onclick="'+clickID+'"style="width:80px">历史材料</a>';
						  }else if(type==8 && auditMethods==0){
							  return '<span style="color:black "; >'+ "已重新申请审核 "+'</span>';
						  }else if(type==8 && auditMethods==1){
							
							  return '<span style="color:black "; >'+ "已重新申请审核 "+'</span>  <a  class="layui-btn layui-btn-normal layui-btn-xs" onclick="'+clickID+'"style="width:80px">历史材料</a>';
						  }else{
							  return'<span style="color:black "; >'+ "等待对方处理 "+'</span>'	;
						  }

					  }}    
					  ,{field:'status',title: '处理进度', width:255,fixed:'left',align:'center',templet: function(d){
						  var codex =d.status;
						  var examineComments=d.examineComments;
						  var auditMethods=d.auditMethods;
						  if(((examineComments!=null && examineComments!='') && auditMethods==1 ) && codex==2){
							  codex=6;
						  }
						  if(codex==0){
							  codex="医保局提交，医院未处理 ";
							  return '<span style="color:black"; >'+ codex +'</span>'	 
						  }else if(codex==1){
							  codex="医院接受违规";
							  return '<span style="color:#00CC33 "; >'+ codex +'</span>'	
						  }else if(codex==2){
							  codex="医院拒绝违规，申诉材料（已提交）";
							  return '<span style="color:black "; >'+ codex +'</span>'	
						  }else if(codex==5){
							  codex="医院拒绝违规，申诉材料（未提交）";
							  return '<span style="color:black "; >'+ codex +'</span>'	
						  }else if(codex==3){
							  codex="医保局接受申诉";
							  return '<span style="color:#00CC33 "; >'+ codex +'</span>'	
						  }else if(codex==4){
							  codex="医保局不接受申诉（已打回）";
							  return '<span style="color:red"; >'+ codex +'</span>'	
						  }else if(codex==6){
							  codex="医保局已提交审核意见";
							  return '<span style="color:black"; >'+ codex +'</span>'	
						  }else if(codex==8){
							  codex="医保局打回历史";
							  return '<span style="color:black"; >'+ codex +'</span>'	
						  }
					  }}

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
					  ,{field:'userStatus', width:90,align:'center',title:'初审结果',fixed:'left',templet: function(d){   
						  var a =d.userStatus;
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
					  }
					  }		              
					  ,{field:'diagType', title:'就诊类型' ,align:'center',templet: function(d){
						  var b =d.diagType;
						  if(b=="1"){
							  b="住院";
						  }else if(b=="2"){
							  b="门诊";
						  }else if(b=="3"){
							  b="门特";
						  }else if(b=="9"){
							  b="-";
						  }
						  return '<span >'+ b +'</span>'
					  }}	         
					  ,{field:'idcard', width:160, title:'身份证号' ,align:'center'}
					  ,{field:'name', width:100, title:'姓名',align:'center'}
					  ,{field:'sex', width:70, title: '性别' ,align:'center'}
					  ,{field:'age', width:70, title:'年龄' ,align:'center'}
					  ,{field:'orgName', title:'医疗机构' ,width:160,align:'center'}
					  ,{field:'departName', title:'科室' ,align:'center'}
					  ,{field:'condition',  width:180,title: '病情' ,align:'center'}
					  ,{field:'paymentDate',width:120, title:'结算日期',align:'center'}
					  ,{field:'inhosDate', title:'入院日期' ,width:105,align:'center'}
					  ,{field:'outhosDate', title:'出院日期' ,width:105,align:'center'}
					  ,{field:'stayLength', title:'住院天数' ,align:'center'}
					  ,{field:'medicalType',  width:90,title: '险种类型' ,align:'center'}
					  ,{field:'admissionNo',  width:193, title:'住院号' ,align:'center'}
					  ,{field:'sscno',  width:155,title: '社保卡号' ,align:'center'}
					  ,{field:'admissionType',  width:90,title: '住院类型',align:'center' }
					  ,{field:'dischargeState', title:'出院状态' ,align:'center'}
					  ,{field:'totalCost', title:'总金额' ,align:'center'}	
					  ,{field:'fundCost', title:'基金支付' ,align:'center'}
					  ,{field:'selfCost', title:'个人负担' ,align:'center'}	
					  ,{field:'basicCostM', width:130, title:'基本统筹应支付' ,align:'center'}
					  ,{field:'povertyAlleviationSubsidy',  width:90,title:'扶贫补助' ,align:'center'}
					  ,{field:'financeSubsidy', title:'财政补助' ,align:'center'}
					  ,{field:'officialSubsidy',  width:115,title:'公务员补助' ,align:'center'}
					  ,{field:'treatmentType',  width:150,title:'待遇享受类别' ,align:'center'}
					  ,{field:'medicalTreatmentState',  width:115,title:'医疗待遇状态' ,align:'center'}
					  ,{field:'billingNo',width:193, title:'收费单据号',align:'center'}
					  ,{field:'crowdType', title:'人群类别' ,align:'center'}
					  ,{field:'reversalMark',width:115, title:'冲销标志',align:'center'}
					  ,{field:'treatmentWay', title:'就诊方式' ,align:'center'}
					  /*,{field:'orgCode',width:150,title:'医疗机构编码',align:'center'}
					  ,{field:'cityCode',width:150, title:'城市编码' ,align:'center'}*/
					  ,{field:'createTime',  width:115, title:'数据上传时间' ,align:'center'}
					  ,{field:'rangeCost',  width:165, title:'本次纳入报销范围金额' ,align:'center'}
					  ,{field:'basicCostR',  width:140, title:'基本统筹实际支付' ,align:'center'}
					  ,{field:'selfPayAmount',  width:115, title:'个人自付金额' ,align:'center'}
					  ,{field:'selfExpenditureAmount',  width:115, title:'个人自费金额' ,align:'center'}
					  ,{field:'sscAccountCost',  width:115, title:'个人账户自付' ,align:'center'}
					  ,{field:'cashCost',  width:115, title:'现金支付金额' ,align:'center'}
					  ,{field:'largeCostM',  width:115, title:'大额应支付' ,align:'center'}
					  ,{field:'largeCostR',  width:115, title:'大额实支付' ,align:'center'}
					  ,{field:'civilAffairSubsidy', title:'民政救助',align:'center'}
					  ,{field:'fullOrdination', title:'全额统筹' ,align:'center'}
					  ,{field:'partialOrdination', title:'部分统筹' ,align:'center'}
					  ,{field:'partialPayment', title:'部分自付' ,align:'center'}
					  ,{field:'fullPayment', title:'全额自付' ,align:'center'}
					  ,{field:'reimbursementType', title:'报销类型' ,align:'center'}
					  ,{field:'hospCount', title:'住院次数' ,align:'center'}
					  ,{field:'insuranceMark', title:'险种标志' ,align:'center'}
					  /*,{field:'inDiagnosisNo',  width:120, title:'入院诊断编码' ,align:'center'}
					  ,{field:'inDiagnosisName',  width:180, title:'入院诊断名称' ,align:'center'}*/
					  ,{field:'outDiagnosisNo',  width:120, title:'出院诊断编码' ,align:'center'}
					  ,{field:'outDiagnosisName',  width:180, title:'出院诊断名称' ,align:'center'}
					  ,{field:'countTime', title: '审核次数',width:90,align:'center',templet: function(d){
						  var b =d.countTime;
						  if(b==null||b==''||b=='null'){
							  return '<span >-</span>'  
						  }
						  return '<span >第'+ b +'次</span>'
					  }}	 
					  ,{field:'examinePerson', title: '审核人',width:90,align:'center'}	 
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
			  $(document).find('[data-index="0"]').css("background-color","#EEF6FF");

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
				  window.open($WEB_ROOT_PATH+'/dhccApi/medicalexcel/medicalexcel/exportExcelToSelf-gs?balanceDate='+balanceDate+'&inhosDate='+inhosDate+'&outhosDate='+outhosDate+'&admissionNo='+admissionNo+'&billingNo='+billingNo+'&diagType='+diagType+'&idcard='+idcard+ '&ilegalType='+ilegalType+'&name='+name+'&orgName='+orgName+'&sscno='+sscno+'&status='+status+'&sysStatus='+sysStatus);
			  })
		  });
		  form.on('submit(LAY-user-front-search)', function(data){
			  var field = data.field; 
			  excelData = field;
			  //console.log(field);
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
		  form.on('submit(LAY-user-front-search-allUpdate)', function(data){
			  var array=new Array();
			  var sign=$("#dg").datagrid('getChecked').length;
			  if(sign==0){
				  layer.msg("请先勾选项目明细！");
				  return;
			  }
			  var medId=$("#dg").datagrid('getChecked')[0].medicalId;
			  array.push("0");	
			  for(var i=0;i<$("#dg").datagrid('getChecked').length;i++){
					  array.push($("#dg").datagrid('getChecked')[i].id);
					 var isIlegal = $("#dg").datagrid('getChecked')[i].isIlegal;
					 if(isIlegal==2){
						 layer.msg("不能修改机审为正常的明细！");
						  return;
					 }
				  };
			 if(toolbarType==1){
				  layer.open({
					  content: '所选明细审核为'
						  ,btn: ['正常', '违规']
				  ,btnAlign: 'c'
					  ,btn1: function(a,b){
						  var appealResult="2";
						  array[0]=appealResult;
						  var url = $WEB_ROOT_PATH + "/dhccApi/resultAppeal/resultAppeal/saveAppealResult?arrayList="+array;
						  $.post(url,{
						  }, function (result) {
							  var inFlag= result.inFlag; 
							  var countNum =result.arrayList.length;
							  countNum=countNum-1;
							  if(inFlag==0){
								  layer.msg('成功保存'+countNum+'条数据');
								  $("#dg").datagrid('reload');
								   getAllViolationCost(medId);
							  }else{
								  layer.msg('保存失败!');
								  $("#dg").datagrid('reload');
							  }
						  });
					  }
				  ,btn2: function(a){
					  var  appealResult="0";
					  array[0]=appealResult;
					  var url = $WEB_ROOT_PATH + "/dhccApi/resultAppeal/resultAppeal/saveAppealResult?arrayList="+array;
					  $.post(url,{}, function (result) {
						  var inFlag= result.inFlag;
						  var countNum =result.arrayList.length;
						  countNum=countNum-1;
						  if(inFlag==0){
							  layer.msg('成功保存'+countNum+'条数据');
							  $("#dg").datagrid('reload');
							   getAllViolationCost(medId);
						  }else{
							  layer.msg('保存失败!');
							  $("#dg").datagrid('reload');
						  }
					  });
				  }
				  });
			 }else if(toolbarType==2){
				  layer.open({
					  content: '批量提交所选明细为'
						  ,btn: ['接受', '不接受']
				  ,btnAlign: 'c'
					  ,btn1: function(a,b){
						 // console.log(a);
						  var appealResult="3";
						  array[0]=appealResult;		
						  var url = $WEB_ROOT_PATH + "/dhccApi/resultAppeal/resultAppeal/saveAppealResult?arrayList="+array;
						  $.post(url,{
						  }, function (result) {
							  var inFlag= result.inFlag; 
							  var countNum =result.arrayList.length;
							  countNum=countNum-1;
							  if(inFlag==0){
								  layer.msg('成功保存'+countNum+'条数据');
								  $("#dg").datagrid('reload');
								   getAllViolationCost(medId);
							  }else{
								  layer.msg('保存失败!');
								  $("#dg").datagrid('reload');
							  }
							  
						  });
					  }
				  ,btn2: function(a){
					  var  appealResult="null";
					  array[0]=appealResult;					
					  //执行 Ajax 后重载
					  var url = $WEB_ROOT_PATH + "/dhccApi/resultAppeal/resultAppeal/saveAppealResult?arrayList="+array;
					  $.post(url,{}, function (result) {
						  var inFlag= result.inFlag;
						  var countNum =result.arrayList.length;
						  countNum=countNum-1;
						  if(inFlag==0){
							  layer.msg('成功保存'+countNum+'条数据');
							  $("#dg").datagrid('reload');
							   getAllViolationCost(medId);
						  }else{
							  layer.msg('保存失败!');
							  $("#dg").datagrid('reload');
						  }
					  });
				  }
				  });
				  
			 };  
		  });
		  table.on('tool(resultAppeal)', function(obj){
			  var data = obj.data;
			  var name=data.name;
			  var auditMethods=data.auditMethods;
			  if(obj.event === 'chuli'){
			  }else if(obj.event === 'submit'){
				  var status="2";
			  }else if(obj.event === 'send'){
				  var status="2";
				  var id=data.id;
				  //判断是否没确认的违规明细都有申诉材料
				  if(auditMethods==0){
					  var url = $WEB_ROOT_PATH + "/dhccApi/resultAppeal/resultAppeal/sendCheck";
					  $.post(url,{"resultAppeal.medicalId":data.medicalId}, function (int) {
						  //console.log(int);
						  if(int==0){
							  layer.confirm('发送<span style="color: red;">'+name+'</span>的申诉材料,确认发送后将不能再修改！', function(index){
								  //执行 Ajax 后重载
								  var url = $WEB_ROOT_PATH + "/dhccApi/resultAppeal/resultAppeal/updateStatusById";
								  $.post(url,{"resultAppeal.status":status,
									  "resultAppeal.id":id
								  }, function (result) {
									  var inFlag= result.operateSuccess; 
									  if(inFlag==true){
										  layer.msg('发送成功!');
										  table.reload('resultAppeal'); //数据刷新
										  $("#dg").datagrid('reload');
									  }else{
										  layer.msg('发送失败!');
										  table.reload('resultAppeal'); //数据刷新
									  }
								  });
							  });
						  }else{			  
							  layer.msg('还有 '+int+' 条明细未处理！');
							  button_event=true;
							  return;
						  }	
					  });
				  }else{
					  layer.confirm('发送<span style="color: red;">'+name+'</span>的申诉材料,确认发送后将不能再修改！', function(index){
						  //执行 Ajax 后重载
						  var url = $WEB_ROOT_PATH + "/dhccApi/resultAppeal/resultAppeal/updateStatusById";
						  $.post(url,{"resultAppeal.status":status,
							  "resultAppeal.id":id
						  }, function (result) {
							  var inFlag= result.operateSuccess; 
							  if(inFlag==true){
								  layer.msg('发送成功!');
								  table.reload('resultAppeal'); //数据刷新
								  $("#dg").datagrid('reload');
							  }else{
								  layer.msg('发送失败!');
								  table.reload('resultAppeal'); //数据刷新
							  }
						  });
					  });	  
				  }			 
				  button_event=true;
				  return;
			  }else if(obj.event === 'agree'){
				  var status="3";
				  layer.confirm('接受<span style="color: red;">'+name+'</span>的审核，接受后此病历将判为--<span style="color: red;">正常</span>', function(index){
					  //执行 Ajax 后重载
					  var url = $WEB_ROOT_PATH + "/dhccApi/resultAppeal/resultAppeal/updateStatusById";
					  $.post(url,{"resultAppeal.status":status,
						  "resultAppeal.id":data.id,
						  "resultAppeal.medicalId":data.medicalId,
						  "resultAppeal.appealPerson":data.appealPerson, 
						  //"resultAppeal.examinePerson":LoginName, //待添加审核人名字
						  "auditMethods":data.auditMethods 		  
					  }, function (result) {
						  var inFlag= result.operateSuccess; 
						  if(inFlag==true){
							  layer.msg('接受成功!');
							  table.reload('resultAppeal'); //数据刷新
							  $("#dg").datagrid('reload');
						  }else{
							  layer.msg('接受失败!');
							  table.reload('resultAppeal'); //数据刷新
						  }
					  });
				  });
				  button_event=true;
				  return;
			  }else if(obj.event === 'reject'){				  
				  //判断是否有未审核的明细  
				  var status="4";			  
				  if(auditMethods==0){
					  var url = $WEB_ROOT_PATH + "/dhccApi/resultAppeal/resultAppeal/rejectCheck";
					  $.post(url,{"resultAppeal.medicalId":data.medicalId}, function (int) {
						  if(int==0){				
							  layer.confirm('拒绝接受<span style="color: red;">'+name+'</span>的审核。拒绝后将打回医院，进入新一轮申诉。', function(index){
								  //执行 Ajax 后重载
								  var url = $WEB_ROOT_PATH + "/dhccApi/resultAppeal/resultAppeal/save";
								  $.post(url,{"resultAppeal.status":status,
									  "resultAppeal.id":data.id,
									  "resultAppeal.medicalId":data.medicalId,
									  "resultAppeal.appealPerson":data.appealPerson, 
									  "resultAppeal.countTime":data.countTime,
									  "auditMethods":data.auditMethods,	  
								  }, function (result) {
									  var inFlag= result.operateSuccess; 
									  if(inFlag==true){
										  layer.msg('打回成功!');
										  table.reload('resultAppeal'); //数据刷新
										  $("#dg").datagrid('reload');
									  }else{
										  layer.msg('打回失败!');
										  table.reload('resultAppeal'); //数据刷新
									  }
								  });
							  }); 
						  }else{			  
							  layer.msg('还有 '+int+' 条明细未审核！');  
							  button_event=true;
							  return;
						  }	
					  });
				  }else{				
					  layer.confirm('拒绝接受<span style="color: red;">'+name+'</span>的审核。拒绝后将打回医院，进入新一轮申诉。', function(index){
						  //执行 Ajax 后重载
						  var url = $WEB_ROOT_PATH + "/dhccApi/resultAppeal/resultAppeal/save";
						  $.post(url,{"resultAppeal.status":status,
							  "resultAppeal.id":data.id,
							  "resultAppeal.medicalId":data.medicalId,
							  "resultAppeal.appealPerson":data.appealPerson, 
							  "resultAppeal.countTime":data.countTime,
							  "auditMethods":data.auditMethods,	  
						  }, function (result) {
							  var inFlag= result.operateSuccess; 
							  if(inFlag==true){
								  layer.msg('打回成功!');
								  table.reload('resultAppeal'); //数据刷新
								  $("#dg").datagrid('reload');
							  }else{
								  layer.msg('打回失败!');
								  table.reload('resultAppeal'); //数据刷新
							  }
						  });
					  }); 
				  }
				  button_event=true;
				  return;
			  }else if(obj.event === 'yes'){	  				
				  var status="1";	
				  var totalAmount=data.totalAmount;
				  var auditMethods=data.auditMethods;
				  if(auditMethods==1){
					  layer.msg('<span style="color: red;">扣款总金额：'+totalAmount+'元。</span>',{
			   			  icon: 1,
			   			  time: 3000 
			   			}, function(){			   				
							  layer.confirm('接受<span style="color: red;">'+name+'</span>的病历被判定为--<span style="color: red;">违规</span>', function(index){
								  //执行 Ajax 后重载
								  var url = $WEB_ROOT_PATH + "/dhccApi/resultAppeal/resultAppeal/save";
								  $.post(url,{"resultAppeal.status":status,
									  "resultAppeal.id":data.id,
									  "resultAppeal.medicalId":data.medicalId,
									  "resultAppeal.orgName":data.orgName,
									  "resultAppeal.orgCode":data.orgCode,
									  "resultAppeal.appealPerson":data.appealPerson, 
									  "auditMethods":data.auditMethods,		  
									  "totalAmount":data.totalAmount
								  }, function (result) {
									  var inFlag= result.operateSuccess; 
									  if(inFlag==true){
										  layer.msg('接受成功!');
										  table.reload('resultAppeal'); //数据刷新
										  $("#dg").datagrid('reload');
									  }else{
										  layer.msg('接受失败!');
										  table.reload('resultAppeal'); //数据刷新
									  }

								  });
							  });
			   			}); 
				  }else if(auditMethods==0){
					  var id=data.id;
					  var violationAmount;
					  var medicalId=data.medicalId;
					  $.getJSON($WEB_ROOT_PATH+'/dhccApi/resultAppeal/resultAppeal/getAllViolationCost?medicalDetail.medicalId='+medicalId,function(cost){				
						 if(cost.length==0){
							 violationAmount=0;
						 }else{
							  violationAmount=cost[4];
						 }				
						  layer.msg('<span style="color: red;">扣款总金额：'+violationAmount+'元。</span>',{
				   			  icon: 1,
				   			  time: 3000 
				   			}, function(){			   				
								  layer.confirm('接受<span style="color: red;">'+name+'</span>的病历被判定为--<span style="color: red;">违规</span>', function(index){
									  //执行 Ajax 后重载
									  var url = $WEB_ROOT_PATH + "/dhccApi/resultAppeal/resultAppeal/save";
									  //console.log(violationAmount);
									  $.post(url,{"resultAppeal.status":status,
										  "resultAppeal.id":data.id,
										  "resultAppeal.medicalId":data.medicalId,
										  "resultAppeal.orgName":data.orgName,
										  "resultAppeal.orgCode":data.orgCode,
										  "resultAppeal.appealPerson":data.appealPerson, 
										  "auditMethods":data.auditMethods,		  
										  "totalAmount":violationAmount,
									  }, function (result) {
										  var inFlag= result.operateSuccess; 
										  if(inFlag==true){
											  layer.msg('接受成功!');
											  table.reload('resultAppeal'); //数据刷新
											  $("#dg").datagrid('reload');
										  }else{
											  layer.msg('接受失败!');
											  table.reload('resultAppeal'); //数据刷新
										  }

									  });
								  });
				   			}); 
					  });				  
				  }
				  button_event=true;
				  return;
			  }else if(obj.event === 'no'){				  
				  var status="5";
							  
				  layer.confirm('拒绝接受<span style="color: red;">'+name+'</span>的审核，拒绝后即可针对每一条违规项目提交对应的申诉材料，申诉材料全部提交完成之后需点击--<span style="color: red;">发送</span>', function(index){
					  //执行 Ajax 后重载
					  var url = $WEB_ROOT_PATH + "/dhccApi/resultAppeal/resultAppeal/save";
					  $.post(url,{"resultAppeal.status":status,
						  "resultAppeal.id":data.id,
						  "resultAppeal.medicalId":data.medicalId,
						  "resultAppeal.orgName":data.orgName,
						  "resultAppeal.orgCode":data.orgCode,
						  "resultAppeal.countTime":data.countTime,
					  }, function (result) {
						  var inFlag= result.operateSuccess; 
						  if(inFlag==true){
							  layer.msg('拒绝成功!');
							  table.reload('resultAppeal'); //数据刷新
							  $("#dg").datagrid('reload');
						  }else{
							  layer.msg('拒绝失败!');
							  table.reload('resultAppeal'); //数据刷新
						  }

					  });
				  });    	  
			  };
			  medical_id='';
			  button_event=true;
			  return;  
		  });
		  //上行监听
		  table.on('row(resultAppeal)', function(obj){	
			  if(button_event==true){
				  button_event=false;
				  return;
			  }
			  
			  
			  var result=obj.data;
			  medical_id=result.medicalId;
			  audit_methods=result.auditMethods;
			  appeal_id=result.id;
			  appeal_status=result.status;
			  if(historyStatus=='8'){
				  historyNumber=result.countTime;
			  }else{
				  historyNumber="0"
			  }
			  $("#medical_id_form").val(medical_id);
			  var getValue = JSON.stringify(layui.formSelects.value('select1', 'val'));
			  is_ilegal=getValue.substring(1,getValue.length-1);
			  $("tr").css("background-color",""); 
			  obj.tr.css("background-color","#EEF6FF");
			
			  
				 if(audit_methods==0){
					 getAllViolationCost(medical_id);
					  $('#dg').datagrid('reload',{'medicalDetail.medicalId': medical_id,'medicalDetail.isIlegal':is_ilegal,'historyNumber':historyNumber}); 
					   if(toolbarType==1 && (appeal_status=='2'||appeal_status=='6')){
						   $('#resultAppeal-examineAll').css({ "display": "block" });
					   }
					   if(toolbarType==2 && appeal_status=='5'){
						   $('#resultAppeal-examineAll').css({ "display": "block" });
					   } 
				 }else{
					var totalAmount= result.totalAmount;
					if(totalAmount==null||totalAmount==''){
						totalAmount="- ";
					}
					var text="<span style='font-size:14px; color:red'>主病例违规，违规金额："+totalAmount+"元。</span>";
					 document.getElementById("showAllCostInfo").innerHTML=text;	
					  $('#dg').datagrid('reload',{'medicalDetail.medicalId': '0','medicalDetail.isIlegal':'0','historyNumber':historyNumber}); 

				 }

		  });

	/*	     //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });*/
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
			//fitColumns:false,
			showFooter:true,
			scrollbarSize: 10,
			fitColumns:true,
			checkOnSelect: false, 
			selectOnCheck: false,
			autoRowHeight:true,
			onClickRow:tableRowClick,
			url:$WEB_ROOT_PATH+'/dhccApi/resultAppeal/resultAppeal/getList',		
			//queryParams:{'medicalDetail.medicalId': "",'medicalDetail.isIlegal':""},4
			columns:[[	
				 {field:'id',title:'ID',width:80,hidden:true},
				 {field: 'ck', checkbox: true,width:40,align:'center'}
				,{field:'operate',title:'申诉材料',align:'center',fixed: 'left' ,width:120,formatter:function(value,row,index){
						   var itemIdisExist= row.itemIdIsExist;
						   var status=row.status;
						   var medicalId=row.medicalId;
						   var examineComments=row.examineComments;
						   var numberHistory=row.countTime;	
						   var appealResult=row.appealResult;
						   if(audit_methods==1){						
								var str ='<a>主病历处提交</a>';
								return str; 	
							}else if((numberHistory!=null && numberHistory!="")&& numberHistory!=0){
								var	clickID="addInfo('"+row.itemId+"','"+status+"','"+medicalId+"','"+row.countTime+"')";
								{{if (!existsButton("resultAppeal-detailsReadOnly")) { }}
								var str = '<a  class="layui-btn layui-btn-normal layui-btn-xs" onclick="'+clickID+'"style="width:110px"><i class="layui-icon layui-icon-edit"></i>历史查看</a>';
								return str; 
								{{} }}		
							}else if(appealResult==2){						
								var str ='<a style="color:black">—</a>';
								return str;						
							}else if(appealResult==3){						
								var str ='<a style="color:#00CC33">医院接受违规</a>';
								return str;						
							}else if(toolbarType==1 && (itemIdisExist==""||itemIdisExist==null)){						
								var str ='<a>未提交</a>';
								return str;						
							}else if(toolbarType==2 && ((status==''||status==null)||(status=='4'))){
								var str ='<a>待确认结果</a>';
								return str;
							}else if(toolbarType==1 && ((status=='2'||status=='6')&& (examineComments==null||examineComments==''))){
								var	clickID="addInfo('"+row.itemId+"','"+status+"','"+medicalId+"')";							
								{{if (!existsButton("resultAppeal-detailsView")) { }}
								var str = '<a  class="layui-btn layui-btn-normal layui-btn-xs" onclick="'+clickID+'"style="width:110px"><i class="layui-icon layui-icon-edit"></i>查看</a>';
								return str; 
								{{ } }}	
							}else if(toolbarType==1 && ((itemIdisExist!=""||itemIdisExist!=null)&&status=='8')){
								var	clickID="addInfo('"+row.itemId+"','"+status+"')";
								{{if (!existsButton("resultAppeal-company-detailsReadOnly")) { }}
								var str = '<a  class="layui-btn layui-btn-normal layui-btn-xs" onclick="'+clickID+'"style="width:110px"><i class="layui-icon layui-icon-edit"></i>查看</a>';
								return str; 
								{{ } }}
							}else if(toolbarType==2 && ((itemIdisExist!=""&&itemIdisExist!=null)&&(status=='2'||status=='8'))){
								var	clickID="addInfo('"+row.itemId+"','"+status+"')";
								{{if (!existsButton("resultAppeal-detailsReadOnly")) { }}
								var str = '<a  class="layui-btn layui-btn-normal layui-btn-xs" onclick="'+clickID+'"style="width:110px"><i class="layui-icon layui-icon-edit"></i>查看</a>';
								return str;
								{{ } }}
							}else if(toolbarType==2 && ((itemIdisExist!="" && itemIdisExist!=null) && status=='5')){
								var	clickID="addInfo('"+row.itemId+"','"+status+"','"+medicalId+"')";
								{{if (!existsButton("resultAppeal-detailsSubmitDone")) { }}
								var str = '<a  class="layui-btn  layui-btn-xs" onclick="'+clickID+'"style="width:110px"><i class="layui-icon layui-icon-edit"></i>查看(已提交)</a>';
								return str; 
								{{} }}
							}else if(toolbarType==2 && ((itemIdisExist==""||itemIdisExist==null)&& status=='5')){
								var	clickID="addInfo('"+row.itemId+"','"+status+"','"+medicalId+"')";
								{{if (!existsButton("resultAppeal-detailsSubmit")) { }}
								var str = '<a  class="layui-btn layui-btn-normal layui-btn-xs" onclick="'+clickID+'"style="width:110px"><i class="layui-icon layui-icon-edit"></i>未提交</a>';
								return str; 
								{{} }}
							}else{
								var str ='<a>未提交</a>';
								return str; 		
							};
					}}		
				,{field:'appealResult',title:'公示结果',width:80,align:'center',formatter:function(value,row){
					var a=row.appealResult;
		
		            	if(a=="2"){
		             		a="<span style='color:#00CC33'>正常</sapn>";
		            	}else if(a=="0"||a=="3"){
		            		a="<span style='color:red'>违规</sapn>";
		            	}else if(a==null || a=="null"){
		            		a="未审核";
		            	}
		                return '<span >'+ a +'</span>'				
				}}
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
	            ,{field:'itemName', title: '项目名称',width:430,align:'center'}
	            ,{field:'drugType', title: '药品类别',width:80,align:'center',formatter:function(value,row){
	        	    var a =row.drugType;
	            	if(a=="11"){
	            		a="甲类";
	            	}else if(a=="12"){
	            		a="乙类";
	            	}else if(a=="91"){
	            		a="丙类";
	            	}
	                return '<span >'+ a +'</span>'
	          	}}
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
			       if (data.rows.length > 0) {
			             for (var i = 0; i < data.rows.length; i++) {
			                 if (data.rows[i].isIlegal == 2 || audit_methods==1) {
			                     $("input[type='checkbox']")[i + 1].disabled = true;
			                 }
			             }
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
						//var table=document.getElementById("detailTable");
						/* var tr=document.createElement("tr");
						tr.innerHTML ='222';
						table.append(tr); */
						//console.log(medical_id+"-----"+row.id);
						//动态拼接
						var url=$WEB_ROOT_PATH+"/dhccApi/violationdetails/violationDetail/findByMId";
						var filed={"violationDetail.medicalId":medical_id,"violationDetail.medicalDetailId":row.id};
						$.post(url,filed,function(result){
							var violationDetails=result.violationDetails;
							var autoHeight;
							if(violationDetails==null||violationDetails==''){
								autoHeight=36;
								$(document).find('[colspan="1"]').parent().css("height",autoHeight );
								$(document).find('[colspan="1"]').css("height",autoHeight );
								$(document).find('[colspan="33"]').parent().css("height",autoHeight );
								$(document).find('[colspan="33"]').css("height",autoHeight );
								
							}else{
								autoHeight=(violationDetails.length+1)*32+4;
								$(document).find('[colspan="1"]').css("height",autoHeight );
							
							}
						
							if(violationDetails){							
							if(violationDetails.length>0){
								for(var i=0;i<violationDetails.length;i++){
									var tr=document.createElement("tr");
									var td=document.createElement("td");
									var typeNo=parseInt(violationDetails[i].typeNo);
								    td.innerHTML=i+1+"."+violationDetails[i].typeName;
									//td.innerHTML=i+1+"."+violationDetails[i].typeName;
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
							 if(toolbarType==1 &&(appeal_status=="2" && audit_methods==0 )){		
									 $(document).find('.easyui-input').css({ "border": "1px solid"});
									 $(document).find('.easyui-input').attr("readOnly",false);
									 $(document).find('.changeCostMoney').css({ "display": "block" }); 
								 }															
							});
						
						});
				
					}					
				});				
				colspan="1"
			/*	$('#dg').datagrid('fixDetailRowHeight',index);//固定详情页面高度
				var innerH=$('.datagrid-body').height();
				console.log(innerH);*/
					  
			}
		});	
	   $("#dg").datagrid('resize'); 
	   //$('.datagrid-cell').css('font-size','10px');
	   $('.datagrid-header .datagrid-cell span ').css('font-size','13px');  
	   //$('.datagrid-header .datagrid-cell span ').css('color','#666666');
		}); 
function addInfo(itemId,status,medicalId,countTime){
	medicalId=appeal_id;
	if(countTime!=''&& countTime!=null){
		//处理
		layer.open({
			type: 2
			,title: '申诉材料(历史记录)'
				,content: $WEB_ROOT_PATH+'/resultAppeal/resultAppealInfo'
				,maxmin: true
				,area: ['760px', '500px']
		,btn: ['确定', '取消']
		,success: function(layero, index){
		 var iframeWindow = window['layui-layer-iframe'+ index];
			iframeWindow.child(itemId,status,medicalId,countTime);
		},yes: function(index, layero){
			  layer.close(index); 
			  layer.close(indexAll); 
		}
		,no:function(index, layero){
		}
		}); 

	}else if(toolbarType=='2'&& status!='5'){
		layer.open({
			type: 2
			,title: '查看申诉材料'
				,content: $WEB_ROOT_PATH+'/resultAppeal/resultAppealInfo'
				,maxmin: true
				,area: ['760px', '500px']
		,btn: ['确定', '取消']
		,success: function(layero, index){
         var iframeWindow = window['layui-layer-iframe'+ index];
			iframeWindow.child(itemId,status,medicalId,"0");
		},yes: function(index, layero){
		}
		,no:function(index, layero){
		}
		}); 
	}else{ 
		layer.open({
			type: 2
			,title: '提交申诉材料'
				,content: $WEB_ROOT_PATH+'/resultAppeal/resultAppealInfo'
				,maxmin: true
				,area: ['760px', '500px']
		,btn: ['确定', '取消']
		,success: function(layero, index){
			var iframeWindow = window['layui-layer-iframe'+ index];
			iframeWindow.child(itemId,status,medicalId,"0");
		}
		,yes: function(index, layero){
			indexAll=index;
			var iframeWindow = window['layui-layer-iframe'+ indexAll]
			,submitID = 'layuiadmin-btn-useradmin'
				,submit = layero.find('iframe').contents().find('#'+ submitID);
			submit.trigger('click');
			$("#dg").datagrid('reload');

		}
		,no:function(index, layero){		
		}
		}); 
	}
	return;
}
function addInfoMain(appealId,money,reason){	
	layer.open({
		type: 2
		,title: '提交申诉材料'
			,content: $WEB_ROOT_PATH+'/resultAppeal/resultAppealInfoMain'
			,maxmin: true
			,area: ['760px', '500px']
	,btn: ['确定', '取消']
	,success: function(layero, index){
		var iframeWindow = window['layui-layer-iframe'+ index];
		iframeWindow.child(appealId,money,reason);
	},yes: function(index, layero){
		indexAll=index;
		var iframeWindow = window['layui-layer-iframe'+ indexAll]
		,submitID = 'layuiadmin-btn-useradmin'
			,submit = layero.find('iframe').contents().find('#'+ submitID);
		submit.trigger('click');
	}
	,no:function(index, layero){
	}
	}); 
	button_event=true;
	return;
}	
function closeLayer(inFlag){
	layer.close(indexAll); 
 	  if(inFlag==true){
   		  layer.msg('材料提交成功!',{
   			  icon: 1,
   			  time: 2000 
   			}, function(){
   			});   
   	  }else{
   		  layer.msg('提交失败!');
   	  }
	$("#dg").datagrid('reload');
}
function reloadLayer(inFlag){
	layer.close(indexAll);
	  if(inFlag==true){
   		  layer.msg('材料提交成功!',{
   			  icon: 1,
   			  time: 2000 
   			}, function(){
   			});   
   	  }else{
   		  layer.msg('提交失败!');
   	  }
	tableAll.reload('resultAppeal');
}

function closeOnly(){
	layer.close(indexAll); 
}
//获取4表联查的数据
function findMedicalDate(medical_id){
	 var url=$WEB_ROOT_PATH+"/dhccApi/medical/medical/findMedicalDate";
	 $.post(url,{"medicalVoJpp.id":medical_id},function(result){
		
		 var medicalVoJpp=result.medicalVoJpp;
		 //console.log(medicalVoJpp);
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
				 $("#dg").datagrid('resize'); 
				return;
			}
			//判断是否点击的同一个
			if(index_last==index){
				$('#dg').datagrid('collapseRow',index);
				index_last=null;
				statu_last=null;
				 $("#dg").datagrid('resize'); 
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
				 $("#dg").datagrid('resize'); 
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
function getAllViolationCost(medicalId) {
	$.getJSON($WEB_ROOT_PATH+'/dhccApi/resultAppeal/resultAppeal/getAllViolationCost?medicalDetail.medicalId='+medicalId,function(cost){				
		
		if(cost.length==0){
			text="";	
		}
		var accepetCost=cost[0];
		if(accepetCost==null||accepetCost==''){
			accepetCost="0";
		}
		var accepetItemAmount=cost[1];
		if(accepetItemAmount==null||accepetItemAmount==''){
			accepetItemAmount="0";
		}
		var violationItemAmount=cost[2];
		if(violationItemAmount==null||violationItemAmount==''){
			violationItemAmount="0";
		}
		var violationItemCost=cost[3];
		if(violationItemCost==null||violationItemCost==''){
			violationItemCost="0";
		}
		var text="<span style='font-size:15px; color:red'>接受违规明细:"+accepetItemAmount+"条，合计:"+accepetCost+"。未确认明细:"+violationItemAmount+"条，合计:"+violationItemCost+"。</span>";
		//var text="<table style='padding-left: 10px;margin-left: 30px;'><tr style='height: 22px;'><td style='font-size:14px; color:red'>接受违规明细:"+accepetItemAmount+"条，合计:"+accepetCost+"元。</td></tr><td style='font-size:14px; color:red'>未确认明细:"+violationItemAmount+"条，合计:"+violationItemCost+"元。<tr></td></tr></table>";
		document.getElementById("showAllCostInfo").innerHTML=text;	 		
	});	
}
//保存详细下拉框,并更新该条明细审核IS_ILEGAL状态
function saveItem(){
	var itemId=item_id;
	var withholdingQuantity= $("#withholdingQuantity").val();
	var withholdingAmount=$("#withholdingAmount").val();
	var deductions=$("#deductions").val();
	var field={"medicalDetail.id":itemId,"medicalDetail.deductions":deductions,"medicalDetail.withholdingAmount":withholdingAmount,"medicalDetail.withholdingQuantity":withholdingQuantity};
	  var url=$WEB_ROOT_PATH+"/dhccApi/resultAppeal/resultAppeal/saveCostDetail";
	  	  $.post(url,field,function(result){
 	  if(result.inFlag==0){
 		  layer.msg('保存成功!');
 	  }else{
 		 layer.msg('保存不成功!');
 	  }
   });
 $("#dg").datagrid('reload');
}
$(".cxtjtop").hide();
$("#shangla").hide();
function showSearch(){
	$("#shangla").show();
	$("#xiala").hide();
	$(".cxtjtop").show();
}
function hideSearch(){
	$(".cxtjtop").hide();
	$("#shangla").hide();
	$("#xiala").show();
}