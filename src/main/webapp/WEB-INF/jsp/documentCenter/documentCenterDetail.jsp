<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css"
	media="all">
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/plugins/layui/dist-jpp/dist/formSelects-v4.css"
	type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css"
	media="all">
<script
	src="<%=request.getContextPath() %>/plugins/layui/dist-jpp/dist/formSelects-v4.js"
	type="text/javascript"></script>
<title>就诊单据中心明细</title>
<style>
.bluediv{
color:blue;
width:170px;
}
.bluedivmin{
width:80px;
}
.minLine{
margin-top:5px;
}
.lableValue{
width:180px;
float:right;
text-align: right;
display: inline-block;
}
.layui-colla-title {
background-color: #DDDDDD;
height: 25px;
line-height: 23px;
font-size:12px;
}
.layui-table-cell {
	height: 20px;
	line-height: 18px;
	font-size: 12px;
	color:black;
}
.xm-select-tips{
display:none;
}
.icon-close{
display:none;
}
#Myselect{
margin-top:30px;
}
</style>
</head>
<body>
<div class="layui-fluid" style="font-size:12px;overflow-y: hidden;">
	<div class="layui-row" ><!-- 行 -->
		<div class="layui-card"style="height:30px;"><!-- 面板 -->
			<div class="layui-card-body">
				<div class="layui-inline" >
					<label >病例编号:</label>
					<div class="layui-input-inline" >
						<div id="billingNoTop" class="bluediv" >20189992828</div>
					</div>
				</div>
				<div class="layui-inline" >
					<label >参保人:</label>
					<div class="layui-input-inline" >
						<div id="nameTop" class="bluediv" style="width:80px">20189992828</div>
					</div>
				</div>
				<div class="layui-inline" >
					<label >机构:</label>
					<div class="layui-input-inline" >
						<div id="orgNameTop" class="bluediv" >20189992828</div>
					</div>
				</div>
				<div class="layui-inline minLine" style="margin-left:670px">
					<label >单据金额:</label>
					<div class="layui-input-inline" >
						<div  id="totalCostTop" class="bluedivmin" >0</div>
					</div>
				</div>
				<!-- <div class="layui-inline minLine" >
					<label >拒付金额:</label>
					<div class="layui-input-inline" >
						<div id="kcCostTop" class="bluedivmin" >0</div>
					</div>
				</div> -->
				<!-- <div class="layui-inline minLine" >
					<label >补差金额:</label>
					<div class="layui-input-inline" >
						<div class="bluedivmin" >0</div>
					</div>
				</div> -->
			</div>
		</div>
	</div>
	<div class="layui-row layui-col-space12">	
		<div class="layui-col-md3">
			<div class="layui-card">
			<div class="layui-collapse " lay-filter='zdmb' style="height:462px;margin-top:2px" lay-accordion="">
  				<div class="layui-colla-item">
    				<h2 class="layui-colla-title">单据信息</h2>
    				<div class="layui-colla-content layui-show" style="height:366px;overflow:auto;">
    					<div style="font-weight: bold;"><i class="layui-icon layui-icon-list"></i>就诊信息</div>
    					<hr style="margin-top:-2px"/>
    					<div><lable >单据号：</lable><lable id="billingNo" class='lableValue'>2019090412345</lable></div>
    					<div><lable>统筹区：</lable><lable id="handdingInsName" class='lableValue'>新余市</lable></div>
    					<div><lable>机构名称：</lable><lable id="orgName" class='lableValue'>**第一人民医院</lable></div>
    					<div><lable>机构编码：</lable><lable id="orgCode" class='lableValue'>10202020</lable></div>
    					<!-- <div><lable>就诊号：</lable><lable id="visitingCardNumber" class='lableValue'>090499887</lable></div> -->
    					<div><lable>入院日期：</lable><lable id="inhosDate" class='lableValue'>2019-05-17</lable></div>
    					<div><lable>出院日期：</lable><lable id="outhosDate" class='lableValue'>2019-05-19</lable></div>
    					<div><lable>在院天数：</lable><lable id="stayLength" class='lableValue'>2</lable></div>
    					<div><lable>医疗类别：</lable><lable id="diagType" class='lableValue'>住院</lable></div>
    					<div><lable>入院诊断：</lable><lable id="inDiagnosisName" class='lableValue'>上呼吸道感染</lable></div>
    					<div><lable>出院诊断：</lable><lable id="outDiagnosisName" class='lableValue'>上呼吸道感染</lable></div>
    					<div><lable>科室名称：</lable><lable id="departName" class='lableValue'>呼吸内科</lable></div>
    					<div><lable>病区床号：</lable><lable id="billingNo" class='lableValue'>13</lable></div>
    					<div><lable>医师名称：</lable><lable id="doctorName" class='lableValue'>王*旺</lable></div>
    					<div style="margin-top:8px;font-weight: bold;"><i class="layui-icon layui-icon-list"></i>患者信息</div>
    					<hr style="margin-top:-2px"/>
    					<div><lable>患者姓名：</lable><lable id="name" class='lableValue'>李*磊</lable></div>
    					<div><lable>患者性别：</lable><lable id="sex" class='lableValue'>男</lable></div>
    					<div><lable>患者年龄：</lable><lable id="age" class='lableValue'>28</lable></div>
    					<div><lable>身份证号：</lable><lable id="idcard" class='lableValue'>1990-11-30</lable></div>
    					<div><lable>社保卡号：</lable><lable id="sscno" class='lableValue'>1988872662776233</lable></div>
    					
    				</div>
  				</div>
  				
  				
  				<div class="layui-colla-item">
    				<h2 class="layui-colla-title">机审疑点</h2>
    				<div class="layui-colla-content ">
    					<div id="violationTable"></div>
    				</div>
  				</div>
  				
  				<div class="layui-colla-item">
    				<h2 class="layui-colla-title">审核记录</h2>
    				<div class="layui-colla-content ">
    					<div id="havePersonViolation"></div>
    				</div>
  				</div>
  				
			</div>
			</div>
		</div>
		
		<div class="layui-col-md9">
			<div class="layui-card"style="min-height:456px;margin-top:2px"><!-- 面板 -->
				<div class="layui-form layui-card-header layuiadmin-card-header-auto" >
					<div class="layui-inline">
						<label class="layui-form-label">违规类型</label>
						<div class="layui-input-block" id="xldown" >
							<select id="ruleType" name="documentBasicVo.ilegalType" xm-select="select2" xm-select-direction="down" xm-select-radio>
								<option value="" disabled selected style='display:none;'>请选择类型</option>
							</select>
						</div>
					</div>
					<div class="layui-inline" >
						<label class="layui-form-label">是否违规</label>
						<div class="layui-input-block" style="width:270px" >
							<select name="documentBasicVo.isIlegal"  id="Myselect" xm-select="select1" xm-select-direction="down" >
								<option></option>
								<option value="0">违规</option>
								<option value="2">未违规</option>
								<option value="1">正常</option>
							</select>
						</div>
					 </div>
					<div class="layui-inline" >
						<label class="layui-form-label">编码/名称</label>
						<div class="layui-input-block" >
							<input class="layui-input" name="documentBasicVo.itemCode"></input>
						</div>
					 </div>

					<div class="layui-inline" style="margin-left:0px">
						<button id="documentCenter-search"  class="layui-btn layuiadmin-btn-useradmin layui-btn-sm" lay-submit lay-filter="LAY-user-front-search2"><i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询</button>
					</div>
					<br/>
				<div style="float:right;margin-top:-13px;font-size: 12px;color:blue;height:15px">
					<div class="layui-inline" id="costSpread" style="z-index: 9999;">收费类别分布</div>
					<div class="layui-inline" id="detailsAmount" style="margin-left:20px">明细总数</div>
				</div>
				</div>
				<div class="layui-card-body">
					<div class="layui-fluid" style="margin-top:10px">
						<table id="medicalDetails" class="layui-hide layui-table" lay-filter="medicalDetails" lay-size="sm" style="width:100%"></table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>



<script>
//全局变量
var medical_id='';
var medicalAll=new Object();
var type_no='';
var nowTitle='';/* 当前展开的折叠面板名 */
var is_ilegal='';
var formSelects = layui.formSelects;
$(function(){
	formSelects.data('select1', 'local', {
	    arr: [
	        {"name": "违规", "value": 0},
	        {"name": "疑似违规", "value": 1},
	        {"name": "正常", "value": 2}
	    ]
	});
	var getValue = JSON.stringify(layui.formSelects.value('select1', 'val'));
	is_ilegal=getValue.substring(1,getValue.length-1);
});
layui.config({
   base: '<%=request.getContextPath() %>/plugins/layui/layuiadmin/'
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'form','laydate','element','table'], function(){
	var form=layui.form;
	var element = layui.element;
	var table=layui.table;
	//折叠面板监听
	element.on('collapse(zdmb)', function(data){
  		//console.log(data.show); //得到当前面板的展开状态，true或者false
  		//console.log(data.title.context.innerText); //得到当前点击面板的标题区域DOM对象
  		nowTitle=data.title.context.innerText;
  
	});
	//加载规则下拉字典
    //localStorage.clear();
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
			    localStorage.setItem('dict_rule',dict_rule);//存入浏览器数据库
			    for(var i=0 ;i<dataList.length;i++){
			      var mm="<option value='"+dataList[i].value+"'>"+dataList[i].text+"</option>";
			      $("#ruleType").append(mm); 
			    }
			form.render('select');
		});
	}
	//渲染medicalDetail表
	table.render({
    	elem: '#medicalDetails'
        ,url: $WEB_ROOT_PATH+'/dhccApi/documentCenter/documentCenter/list'
        ,height: document.documentElement.clientHeight-125
        ,limits: [5,10,20]
        ,limit: 10
        ,where:{'documentBasicVo.medicalId':medical_id,'documentBasicVo.isIlegal':is_ilegal}
        ,cols: [[
            	{type: 'numbers', title: '序号' }
            	,{field:'id',title:'ID',hide:true,width:80}
				,{field:'jhgs', title: '稽核/公示结果',width:150,templet:function(d){
					var jh=d.aduitStatus;
					var gs=d.appealResult;
					
					if(jh!=null){
						return "稽核"+jh;
					}else if(gs!=null){
						if(gs=='0'){
							return '公示违规';
						}else if(gs=='2'){
							return '公示正常';
						}else{
							return '公示'+gs
						}
					}else{
						return '-';
					}
				}}
				,{field:'userStatus', title: '初审结果',width:150,templet:function(d){
	            	if(d.isIlegal=='2'){
	            		return '正常';
	            	}else if(d.userStatus==null||d.userStatus=='null'){
	            		return '待初审';
	            	}else{
	            		return d.userStatus;
	            	}
	            }}
	            ,{field:'isIlegal', title: '机审结果',width:100,templet:function(d){
	            	if(d.isIlegal=='0'){
	            		return '违规';
	            	}else if(d.isIlegal=='1'){
	            		return '疑似违规';
	            	}else if(d.isIlegal=='2'){
	            		return '正常';
	            	}else{
	            		return d.isIlegal;
	            	}
	            }}
	            ,{field:'feeCreateDate', title: '费用发生时间',width:160}
				,{field:'itemCode', title: '项目编号',width:150}
	            ,{field:'itemName', title: '项目名称',width:250}
	            ,{field:'chargeType', title: '收费类别',width:120}
	            ,{field:'drugType', title: '药品类别',width:120}
	            ,{field:'itemCost', title: '项目金额'}
	            ,{field:'sumAmount', title: '总金额'}
	            ,{field:'itemNum', title: '项目数量'}
	            ,{field:'itemPrice', title: '项目单价'}
	            ,{field:'itemStandard', title: '项目规格',width:80}
	            ,{field:'applyPayLevel', title: '报销级别'}
	            ,{field:'doseForm', width:100,title: '剂型'}
	            ,{field:'limitPrice', title: '限价金额'}
	            ,{field:'partialOrdination', title: '部分统筹'}
	            ,{field:'partialPayment', title: '部分自付'}
	            ,{field:'recipelId', title: '门诊处方编号'}
	            ,{field:'selfPayAmount', title: '自付金额'}
	            ,{field:'singleDose', title: '单次用量'}
	            ,{field:'takeFrequence', title: '服用频次'}
	            ,{field:'useDay', title: '用药天数'}
	            ,{field:'applyPayAmount', title: '报销金额'}
	            ,{field:'deliverWay',title: '给药途径'}
	            ,{field:'doseUnit', title: '用量单位'}
	            ,{field:'fullOrdination', title: '全额统筹'}
	            ,{field:'fullPayment', title: '全额自付'}
	            ,{field:'isInsuranceProject', title: '是否医保项目'}
	           ]]
	    
	    ,page: true
	    ,done:function(res){
	          // $('tr').eq(1).css("background-color","#EEF6FF");
	          $("#detailsAmount").html('明细总数： '+res.count +" 条" );
	     }
    });
    //medicalDetail表查询
	form.on('submit(LAY-user-front-search2)', function(data){
	    var field = data.field;
	    //执行重载
	    layui.table.reload('medicalDetails', {
	          where: field
	    });
	});
    //medicalDetail行点击事件
     table.on('row(medicalDetails)', function(obj){
    	 var data=obj.data;
    	 var ruletypeNo=$("#ruleType").val();
    	 if(type_no!=''){ 
    		 //查询条件选中后，type_no 按查询条件
    		 if(ruletypeNo){
    			 type_no=ruletypeNo;
    			 //判断是机审疑点or审核记录
    			var titleNow=nowTitle.substring(0,4);
    			 if(titleNow=='机审疑点'){ 
	    	 		 sysDetailView('机审疑点明细',data,type_no);
    			 }else if(titleNow=='审核记录'){
    				 violationRecoderView('审核记录',data,type_no);
    			 }
    		 }
    	 }
		
     });
   //收费类别弹框
     $("#costSpread").on("click", function () {
    	 layer.open({
    	        type: 2
    	        ,title: "收费类别分布"
    	        ,content: $WEB_ROOT_PATH+'/documentcenter/documentcenter'
    	        ,maxmin: true
    	        ,area: ['600px', '480px']
    	        ,success: function(layero, index){
    	            var iframeWindow = window['layui-layer-iframe'+ index];
    	            //向此iframe层方法 传递参数
    	            iframeWindow.child(medical_id);/*调用弹出窗口，填充该行数据到修改表单*/
    	        }
    	  }); 
     });
	//填充右侧单据信息
	documentInformatio(medical_id);
	//机审疑点加载
	sysVerify(medicalAll,form);
	//审核记录加载
	violationRecordLoad(medicalAll,form);
	
});
function child(obj){
	//将medical_id传过来，做全局变量
	var medical = JSON.parse(obj);
	medicalAll=medical;
	medical_id=medical["id"];
	//顶部数据赋值
	$("#billingNoTop").html(medical["id"]);
	$("#nameTop").html(medical["name"]);
	$("#orgNameTop").html(medical["orgName"]);
	$("#totalCostTop").html(medical["totalCost"]);
	/* if(medical["totalAmount"]){
		$("#kcCostTop").html(medical["totalAmount"]);
	}else if(medical["userVerifyMoney"]){
		$("#kcCostTop").html(medical["userVerifyMoney"]);
	}else if(medical){
		
	} */
}
//单据信息
function documentInformatio(medicalId){
	var url=$WEB_ROOT_PATH+'/dhccApi/medicalId/medicalId/getMedicalId';
	var filed={'medical.id':medicalId};
	$.post(url,filed,function(result){
		if(result){
			for(var index in result){
				$("#"+index).html(result[index]);
			}
			var inDiagnosisName=result.inDiagnosisName;
			$("#inDiagnosisName").html(cantShowAll(inDiagnosisName,14));
			topHover('inDiagnosisName',inDiagnosisName);
			var outDiagnosisName=result.outDiagnosisName;
			$("#outDiagnosisName").html(cantShowAll(result.outDiagnosisName,14));
			topHover('outDiagnosisName',outDiagnosisName);
		}
	});
	
}
//机审疑点加载列表
function sysVerify(row,form){
	var div=document.getElementById("violationTable");
	$("#violationTable").empty();
	//加一个新的空table  id="sysVerify" border="0" cellpadding="0" cellspacing="0"
	var table=document.createElement("table");
	table.setAttribute("id","sysVerify");
	table.setAttribute("border","0");
	table.setAttribute("cellpadding","0");
	table.setAttribute("cellspacing","0");
	div.appendChild(table)
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
			var url=$WEB_ROOT_PATH+"/dhccApi/violationdetails/violationDetail/findByMedicalId";
			var filed={"violationDetail.medicalId":row.id,"shzt":'cs'};
			$.post(url,filed,function(result){
				//2.1拼接数据
				var sysVerifyVo=result.sysVerifyVo;
				if(!sysVerifyVo){
					var tr=document.createElement('tr');
					tr.innerHTML='暂无违规信息！';
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
					switchChang(i,td,sysVerifyVo[i].typeNames);
					td2.innerHTML="<span>&#12288;&#12288;</span>"+sysVerifyVo[i].countNum+"条";
					tr.appendChild(td);
					tr.appendChild(td2);
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
function switchChang(i,td,typeNames){
	if(!typeNames){
		td.innerHTML=i+1+"."+'其他';
	}else{
		td.innerHTML=i+1+"."+typeNames;
	}
}
function wgDetailonclick(typeno,form){
	var t=$(typeno).attr("typevalue");
	type_no=t;
	//让违规类型选中
	//var oldValue=$("#ruleType").val();
	//alert(type_no);
	//$("#ruleType option[value='"+oldValue+"']").removeAttr("selected");
	$("#ruleType option[value='"+type_no+"']").prop("selected", true);;
	//$("#ruleType").find("option[value ='"+t+"']").attr("selected","selected");
	 form.render('select');
	//模拟点击查询
	$('#documentCenter-search').trigger("click");
	//判断是否为主病例，是主病例违规直接弹出记录明细框（审核记录）
	var titleNow=nowTitle.substring(0,4);
   	if(titleNow=='审核记录'){ 
		var url=$WEB_ROOT_PATH+"/dhccApi/violationdetails/violationDetail/IsMain";
		var filed={"violationDetail.typeNo":t,"violationDetail.medicalId":medical_id};
		$.post(url,filed,function(result){
			//是主病例
			if(result.ruleProperty=='0'){
				//弹框
				violationRecoderViewMain('主病例审核记录明细',t);
			}
		});
   	}
}
//弹框2--机审疑点明细
function sysDetailView(name,data,typeNo){
	//弹出机审疑点详情页面
	 layer.open({
        type: 2
        ,title: name
        ,content: $WEB_ROOT_PATH+'/medical/documentCenter/documentCenterSysDetail'
        ,maxmin: true
        ,area: ['1000px', '435px']
        ,success: function(layero, index){
            var iframeWindow = window['layui-layer-iframe'+ index];
            //向此iframe层方法 传递参数
            iframeWindow.child(JSON.stringify(data),typeNo);/*调用弹出窗口，填充该行数据到修改表单*/
        }
  });
}
//弹框3--审核记录明细
function violationRecoderView(name,data,typeNo){
	//弹出机审疑点详情页面
	 layer.open({
        type: 2
        ,title: name
        ,content: $WEB_ROOT_PATH+'/medical/documentCenter/violationRecoder'
        ,maxmin: true
        ,area: ['450px', '510px']
	 	,offset: 'rb'//右下角弹出
        ,success: function(layero, index){
            var iframeWindow = window['layui-layer-iframe'+ index];
            //向此iframe层方法 传递参数
 
            iframeWindow.child(JSON.stringify(data),typeNo,medicalAll);/*调用弹出窗口，填充该行数据到修改表单*/
        }
  });
}
//弹框4--审核记录明细--按主病例
function violationRecoderViewMain(name,typeNo){
	//弹出机审疑点详情页面
	 layer.open({
        type: 2
        ,title: name
        ,content: $WEB_ROOT_PATH+'/medical/documentCenter/violationRecoderMain'
        ,maxmin: true
        ,area: ['450px', '510px']
	 	,offset: 'rb'//右下角弹出
        ,success: function(layero, index){
            var iframeWindow = window['layui-layer-iframe'+ index];
            //向此iframe层方法 传递参数
            iframeWindow.child(typeNo,JSON.stringify(medicalAll));/*调用弹出窗口，填充该行数据到修改表单*/
        }
  });
}
//审核记录加载数据
function violationRecordLoad(row,form){
	//清空div
	$("#havePersonViolation").empty();
	var div=document.getElementById("havePersonViolation");
	//加一个新的空table  id="sysVerify" border="0" cellpadding="0" cellspacing="0"
	var table=document.createElement("table");
	table.setAttribute("id","personViolation");
	table.setAttribute("border","0");
	table.setAttribute("cellpadding","0");
	table.setAttribute("cellspacing","0");
	div.appendChild(table)
	//请求数据，并动态拼接
	var url=$WEB_ROOT_PATH+"/dhccApi/violationdetails/violationDetail/findByMedicalId";
	var filed={"violationDetail.medicalId":row.id,"shzt":'djzx'};
	$.post(url,filed,function(result){
		var sysVerifyVo=result.sysVerifyVo;
		if(sysVerifyVo){
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
			$("#personViolation").on('click','.isexistNum',function(){
				wgDetailonclick(this,form);
			});
		}else{
			var tr=document.createElement('tr');
			tr.innerHTML='暂无违规审核信息！';
			table.appendChild(tr);
			return;
		}
	});
}
//显示不完全，截取字符串，拼接....
function cantShowAll(str,length){
	if(str&&str.length>length){	
	 	return str=str.substring(0,length)+'...';
	}else{
		return str;
	}
}
//鼠标悬停事件
function topHover(idname,value){
	$("#"+idname).hover(function() {
		subtips = layer.tips(value, '#'+idname,{tips:[1,'#0000FF'],time: 5000});
	}, function() {
		layer.close(subtips);
	});
}
</script>
</body>
</html>