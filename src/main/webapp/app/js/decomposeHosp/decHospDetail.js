	   
var orgCode=null,idNo=null,patientName=null,condition=null,lastDischargeDate=null,admissionDate=null;
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table'], function(){
		var $ = layui.$,table = layui.table;
	    orgCode=$("#orgCode").val();
	    idNo=$("#idNo").val();
	    patientName=$("#patientName").val();
	    condition=$("#condition").val();
	    lastDischargeDate=$("#lastDischargeDate").val();
	    admissionDate=$("#admissionDate").val();
	    table.render({
	    	elem: '#dg'
	            ,url: $WEB_ROOT_PATH+'/dhccApi/medicalrecord/medicalRecord/findRecordList'
	            ,cellMinWidth: 60
	            ,height: 'full-60'
	            ,even:true
	            ,cols: [[
	               { fixed:'left',type: 'numbers', title: '序号',align:'center' }
	              ,{fixed:'left',field:'caseNo', title: '案件编号',width:'8%',align:'right' }
	              ,{fixed:'left',field:'orgName', title: '医疗机构',width:'15%'}
	              ,{fixed:'left',field:'admissionNo', title: '住院号',width:'10%',align:'center'}
	              ,{fixed:'left',field:'idNo', title: '身份证号',width:'15%',align:'center'}
	              ,{fixed:'left',field:'patientName', title: '患者姓名',width:'10%',align:'center'}
	              ,{field:'billingNo', title: '收费单据号',width:'10%',align:'center'}
	              ,{field:'condition', title: '病情',width:'20%',align:'left'}
	              ,{field:'admissionDate', title: '入院日期',width:'18%',align:'center'}
	              ,{field:'dischargeDate', title: '出院日期',width:'18%',align:'center'}
	             
	            ]]
	            ,page: false,
	            where:{
	            	'medicalRecord.orgCode':orgCode,
	            	'medicalRecord.idNo':idNo,
	            	'medicalRecord.patientName':patientName,
	            	'medicalRecord.condition':condition,
	            	'medicalRecord.lastDischargeDate':lastDischargeDate,
	            	'medicalRecord.admissionDate':admissionDate
	            }
	    
	          });
	    
	  });