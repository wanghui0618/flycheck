var medical_id='';//medical表id
var billing_no='';//就诊明细id
var is_ilegal='';//是否违规
var index_last=null;
var statu_last=null;
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
      //加载城市下拉字典
   	 $.getJSON($WEB_ROOT_PATH+'/dhccApi/dict/dict/findDict?dictRequestVo.tableName=t_piccbid_dict_city', 
   				function(data){
   		     		var  dataList= data.dictList;
   		     		for(var i=0 ;i<dataList.length;i++){
   		     			var mm="<option value='"+dataList[i].value+"'>"+dataList[i].text+"</option>";
   		     			$("#zycity").append(mm); 
   		     		}
   		     	form.render('select');
   	});
   
	   var allExportData = [];
	   var ins1= table.render({
	    	elem: '#userTable'
	            ,url: $WEB_ROOT_PATH+'/dhccApi/medical/medical/listVo'
	            ,cellMinWidth: 100
	             ,height: 230
	             ,limit: 5
	             ,where:{"medical.orgCode":orgCode,"type":type}
			   ,cols: [[
		    	   {type: 'numbers', title: '序号' }
		          ,{field:'id', title: 'ID', sort: true, hide:true}
		        /* ,{field:'left', title:'稽核', toolbar: '#table-useradmin-webuser2', width:120}
		          ,{field:'status', title:'数据校验标识' }
		         ,{field:'billingNo',width:90, title:'收费单据号'}*/
		          ,{field:'sysStatus', title:'机审结果' }
		          ,{field:'name', width:120, title:'姓名'}
		          ,{field:'sex', title: '性别' }
		          ,{field:'age', title:'年龄' }
		          ,{field:'idcard', title:'身份证号' }
		          ,{field:'sscno',  width:115,title: '社保卡号' }
		          ,{field:'admissionNo', title:'住院号' }
		          ,{field:'admissionType',  width:90,title: '住院类型' }
		          ,{field:'crowdType', title:'人群类别' }
		          ,{field:'orgCode', width:90,title:'医疗机构编码'}
		          ,{field:'orgName', title:'医疗机构名称' }
		          ,{field:'condition',  width:90,title: '病情' }
		          ,{field:'departName', title:'科室' }
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
		          ,{field:'paymentDate',width:90, title:'费用发生日期'}
		         ,{field:'userStatus', title:'初审结果' }
		          /*,{field:'finaStatus', title:'终审结果状态' }*/
		          ,{field:'cityCode', title:'城市编码' }
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
		            	$('tr').eq(1).css("background-color","#C0C0C0");
		            	if (allExportData.length < 1){
		                    $.get( $WEB_ROOT_PATH+'/dhccApi/medical/medical/listVo1', '', function (res) {                    
		                        	allExportData=res;	                         
		                     });
		                }
		            
		            	var result=res.data;
		            	medical_id=result[0].id;
		            	//findMedicalDate(medical_id);
		            	sysVerify(result[0]);
		            	$("#medical_id_form").val(medical_id);
		            	//console.info(medical_id);
		            	var getValue = JSON.stringify(layui.formSelects.value('select1', 'val'));
		        		is_ilegal=getValue.substring(1,getValue.length-1);
		            	$('#dg').datagrid('load',{'medicalDetail.medicalId': medical_id,'medicalDetail.isIlegal':is_ilegal});
		            	
		            	
		      		 }
		      		,page: true
        		});
	    
		 layui.use(['table'],function(){
	    	 $("#exportExcel").click(function(){
	 	       	table.exportFile(ins1.config.id,allExportData,'xls');
	         })
	    });
		 hideButtonStatic();//静态按钮授权
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
	    	$("tr").css("background-color",""); 
	         $(this).css("background-color","#C0C0C0"); 
	    	//刷新就诊明细
	    	var result=obj.data;
	    	//findMedicalDate(result.id);
	    	
	    	//将medical_id赋值到全局变量
	    	medical_id=result.id;
	    	sysVerify(result);
	    	$("#medical_id_form").val(medical_id);
	    	var getValue = JSON.stringify(layui.formSelects.value('select1', 'val'));
    		is_ilegal=getValue.substring(1,getValue.length-1);
	    	$('#dg').datagrid('reload',{'medicalDetail.medicalId': medical_id,'medicalDetail.isIlegal':is_ilegal}); 
	    	
	    });
	    
	    //监听搜索上
		form.on('submit(LAY-user-front-search)', function(data){
	    	var field = data.field;
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
		//监听select change
		 form.on('select(zycity)', function(data){
				 //$("#cityName").attr("value",data.text);
				// alert($(data.elem).find("option:selected").text());
			 	console.log($(data.elem).find("option:selected"));
			 	var cityCode=$(data.elem).find("option:selected").val();
			 	console.log(cityCode);
				//$("#cityName").val($(data.elem).find("option:selected").text());
			 	//重新渲染医院下拉框
		        $.getJSON($WEB_ROOT_PATH+'/dhccApi/dataauthority/dataauthority/findOrg?dataAuthority.cityCode='+cityCode,
		            function(data){
		                var orgs=data.pageModel.rows
		                //var  dataList= data.dictList;
		                var org_save=JSON.stringify(orgs);//解析为字符串
		                //localStorage.clear();
		                $("#zyOrgName").empty();
		                var mm="<option value=''disabled selected style='display:none;'>请选择</option>";
		                $("#zyOrgName").append(mm);
		                for(var i=0 ;i<orgs.length;i++){
		                    var mm="<option value='"+orgs[i].value+"'>"+orgs[i].text+"</option>";
		                    $("#zyOrgName").append(mm);
		                }
		                form.render('select');
		          });
			 	
			 	
				form.render('select');
		 
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
		
	 });
}
//保存详细下拉框
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
//右侧系统审核动态拼接
function sysVerify(row){
	console.log(row);
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
			var url=$WEB_ROOT_PATH+"/dhccApi/violationdetails/violationDetail/findByMedicalId";
			var filed={"violationDetail.medicalId":medical_id};
			$.post(url,filed,function(result){
				//2.1拼接数据
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
					var td=document.createElement('td');
					var td2=document.createElement('td');
					switchChang(i,td,sysVerifyVo[i].typeNo);
					td2.innerHTML="<span>&#12288;&#12288;</span>"+sysVerifyVo[i].countNum+"条";
					tr.appendChild(td);
					tr.appendChild(td2);
					table.appendChild(tr);
				}
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