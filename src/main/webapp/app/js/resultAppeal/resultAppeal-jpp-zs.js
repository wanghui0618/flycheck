//初始化	
var medical_id='';//medical表id
var tableAll;
var indexAll;
var billing_no='';//就诊明细id
var is_ilegal='';//是否违规
var index_last=null;
var statu_last=null;
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table
	    ,unitType="";
/* 
	   unitType ="<%=session.getAttribute('user').getAttribute('unitType')%>"; 
	   console.info("结果");
	   console.info(unitType);
	   console.info("结果");*/
    var toolbarType="";
			//罗列所有小分类
			$.getJSON($WEB_ROOT_PATH+'/dhccApi/resultAppeal/resultAppeal/findUnitType',function(unitType){				
				toolbarType = unitType[0];
				//console.log(toolbarType);
			});	
	   
				
	    
	    table.render({
	    	elem: '#resultAppeal'
	            ,url: $WEB_ROOT_PATH+'/dhccApi/resultAppeal/resultAppeal/listAndFind'
	            ,cellMinWidth: 100
	             ,height: 230
	             ,limit: 5
	            ,cols: [[
	            	   {type: 'numbers',width:40, title: '序号',fixed: 'left' }
	            	  ,{field:'id', title: 'ID', sort: true, hide:true} 
		              ,{field:'unitType',title: '操作', width:280,align:'center',templet: function(d){
		            	  	/*var unitType = d.unitType;*/
		            	  var type=d.status;
		            	  	if(toolbarType==1 && (type==6||type==2)){
	                	    return "<a class='layui-btn layui-btn-xs' lay-event='agree'><i class='layui-icon layui-icon-ok'></i>接受</a><a class='layui-btn layui-btn-danger layui-btn-xs' lay-event='reject'><i class='layui-icon layui-icon-no'>&#x1006;</i>不接受</a>";
	                	    }else if(toolbarType==2 && (type==2)){
		               		return '<span style="color:black "; >'+ "等待对方处理 "+'</span>';	
		                	}else if(toolbarType==2 && (type==4||type==0)){
		               		return '<a class="layui-btn layui-btn-xs" lay-event="yes"><i class="layui-icon layui-icon-ok"></i>接受</a><a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="no"><i class="layui-icon layui-icon-no">&#x1006;</i>不接受</a>'	
		                	}else if(toolbarType==2 && (type==5||type==2)){
		               		return '<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="send"><i class="layui-icon"></i></button>发送</a>'	
		                	}else if(type==1||type==3){
			               		return '<span style="color:green "; >'+ "审核结果双方已确认 "+'</span>';
			                }else if(type==8){
			               		return '<span style="color:black "; >'+ "已重新申请审核 "+'</span>';
			                }else{
		                		return'<span style="color:black "; >'+ "等待对方处理 "+'</span>'	;
		                	}
		            	  	
	                  }}        
		              ,{field:'status',title: '处理进度', width:280,align:'center',templet: function(d){
	                	    var codex =d.status;
		                	if(codex==0){
		                		codex="医保局提交，医院未处理 ";
		                		 return '<span style="color:black"; >'+ codex +'</span>'	 
		                	}else if(codex==1){
		                		codex="医院接受违规";
		                		return '<span style="color:green "; >'+ codex +'</span>'	
		                	}else if(codex==2){
		                		codex="医院拒绝违规，申诉材料（已提交）";
		                		return '<span style="color:black "; >'+ codex +'</span>'	
		                	}else if(codex==5){
		                		codex="医院拒绝违规，申诉材料（未提交）";
		                		return '<span style="color:black "; >'+ codex +'</span>'	
		                	}else if(codex==3){
		                		codex="医保局接受申诉";
		                		return '<span style="color:green "; >'+ codex +'</span>'	
		                	}else if(codex==4){
		                		codex="医保局不接受申诉，打回";
		                		return '<span style="color:#FF3300 "; >'+ codex +'</span>'	
		                	}else if(codex==6){
		                		codex="医保局已提交审核意见";
		                		return '<span style="color:black"; >'+ codex +'</span>'	
		                	}else if(codex==8){
		                		codex="已重新申请审核";
		                		return '<span style="color:black"; >'+ codex +'</span>'	
		                	}
	                  }}
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
		              ,{field:'civilAffairSubsidy', title:'民政救助'}
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
		              ,{field:'medicalId', title: '病例编号',width:200,align:'center'}	 
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
	         $(document).find('[data-index="0"]').trigger("click");//选中单选按钮 
	         $(document).find('[data-index="0"]').css("background-color","#C0C0C0");
	         
  		}

	            ,page: true
	          });
	    
	    //监听搜索
		form.on('submit(LAY-user-front-search)', function(data){
	    	var field = data.field;    	
	    	 console.info(field);
	        //执行重载
	        layui.table.reload('resultAppeal', {
	            where: field
	        });
	    });
		
	    //监听行点击
	    table.on('tool(resultAppeal)', function(obj){
	      var data = obj.data;
	      if(obj.event === 'chuli'){/*
	    	  //处理
	    	    layer.open({
			          type: 2
			          ,title: '提交申诉材料'
			          ,content: $WEB_ROOT_PATH+'/resultAppeal/resultAppealInfo'
			          ,maxmin: true
			          ,area: ['760px', '500px']
			          ,btn: ['确定', '取消']
	    	          ,success: function(layero, index){
	    	        	  
		        	  var iframeWindow = window['layui-layer-iframe'+ index];
		        	  //向此iframe层方法 传递参数
		        	  iframeWindow.child(JSON.stringify(data));
		              }
			          ,yes: function(index, layero){
			        	  indexAll=index;
			            var iframeWindow = window['layui-layer-iframe'+ indexAll]
			            ,submitID = 'layuiadmin-btn-useradmin'
			            ,submit = layero.find('iframe').contents().find('#'+ submitID);
     		            submit.trigger('click');
     		            setInterval(table.reload('resultAppeal'),3000); 
     		  
			          }
			          ,no:function(index, layero){
			        	  table.reload('resultAppeal'); //数据刷新
				          }
			        }); 
  
	      */}else if(obj.event === 'submit'){
	    	  
	      }else if(obj.event === 'send'){
	    	  if(toolbarType==1){
	    		  var status="6";
	    	  }else if(toolbarType==2){
	    		  var status="2";
	    	  }
		  	   var fileUrl=data.fileUrl;
		  	   var originalFilename=data.originalFilename;
		  	   var id=data.id;
		  	   var medicalIdInfo=data.medicalId;
		  	   var orgName=data.orgName;
		  	   var orgCode=data.orgCode;
		  	   var createDate=data.createDate;
		  	   var updateDate=data.updateDate;
		  	   var appealDate=data.appealDate;
		  	   var appealPerson=data.appealPerson;
		  	   var appealReason=data.appealReason;
		  	   var examineComments=data.examineComments;
		  	   var examinePerson=data.examinePerson;
    	  
	    	  layer.confirm('是否发送<span style="color: red;">'+medicalIdInfo+'</span>的申请材料', function(index){
		        	//执行 Ajax 后重载
	    		var url = $WEB_ROOT_PATH + "/dhccApi/resultAppeal/resultAppeal/save";
	    	    $.post(url,{"resultAppeal.status":status,
	    	    	"resultAppeal.fileUrl":fileUrl,
	    	    	"resultAppeal.originalFilename":originalFilename,
	    	    	"resultAppeal.id":id,
	    	    	"resultAppeal.medicalId":medicalIdInfo,
	    	    	"resultAppeal.orgName":orgName,
	    	    	"resultAppeal.orgCode":orgCode,
	    	    	"resultAppeal.createDate":createDate,
	    	    	"resultAppeal.updateDate":updateDate,
	    	    	"resultAppeal.appealDate":appealDate,
	    	    	"resultAppeal.appealPerson":appealPerson,
	    	    	"resultAppeal.appealReason":appealReason,
	    	    	"resultAppeal.examineComments":examineComments ,	
	    	    	"resultAppeal.examinePerson":examinePerson  	
	    	    }, function (result) {
	    	    	 var inFlag= result.operateSuccess; 
	    	   	  if(inFlag==true){
	    	   		  layer.msg('接受成功!');
	    	   		  table.reload('resultAppeal'); //数据刷新
	    	   	  }else{
	    	   		  layer.msg('接受失败!');
	    	   		  table.reload('resultAppeal'); //数据刷新
	    	   	  }
	    	       
	    	    });
	    	  });
	      
	    	  
	      }else if(obj.event === 'agree'){
	    	   var status="3";
		  	   var fileUrl=data.fileUrl;
		  	   var originalFilename=data.originalFilename;
		  	   var id=data.id;
		  	   var medicalIdInfo=data.medicalId;
		  	   var orgName=data.orgName;
		  	   var orgCode=data.orgCode;
		  	   var createDate=data.createDate;
		  	   var updateDate=data.updateDate;
		  	   var appealDate=data.appealDate;
		  	   var appealPerson=data.appealPerson;
		  	   var appealReason=data.appealReason;
		  	   var examineComments=data.examineComments;
		  	   var examinePerson=data.examinePerson;
    	  
	    	  layer.confirm('是否确定接受<span style="color: red;">'+medicalIdInfo+'</span>的审核', function(index){
		        	//执行 Ajax 后重载
	    		var url = $WEB_ROOT_PATH + "/dhccApi/resultAppeal/resultAppeal/save";
	    	    $.post(url,{"resultAppeal.status":status,
	    	    	"resultAppeal.fileUrl":fileUrl,
	    	    	"resultAppeal.originalFilename":originalFilename,
	    	    	"resultAppeal.id":id,
	    	    	"resultAppeal.medicalId":medicalIdInfo,
	    	    	"resultAppeal.orgName":orgName,
	    	    	"resultAppeal.orgCode":orgCode,
	    	    	"resultAppeal.createDate":createDate,
	    	    	"resultAppeal.updateDate":updateDate,
	    	    	"resultAppeal.appealDate":appealDate,
	    	    	"resultAppeal.appealPerson":appealPerson,
	    	    	"resultAppeal.appealReason":appealReason,
	    	    	"resultAppeal.examineComments":examineComments ,	
	    	    	"resultAppeal.examinePerson":examinePerson  	
	    	    }, function (result) {
	    	    	 var inFlag= result.operateSuccess; 
	    	   	  if(inFlag==true){
	    	   		  layer.msg('接受成功!');
	    	   		  table.reload('resultAppeal'); //数据刷新
	    	   	  }else{
	    	   		  layer.msg('接受失败!');
	    	   		  table.reload('resultAppeal'); //数据刷新
	    	   	  }
	    	       
	    	    });
	    	  });
	      
	    	  
	      }else if(obj.event === 'reject'){
	    	   var status="4";
		  	   var fileUrl=data.fileUrl;
		  	   var originalFilename=data.originalFilename;
		  	   var id=data.id;
		  	   var medicalIdInfo=data.medicalId;
		  	   var orgName=data.orgName;
		       var orgCode=data.orgCode;
		  	   var createDate=data.createDate;
		  	   var updateDate=data.updateDate;
		  	   var appealDate=data.appealDate;
		  	   var appealPerson=data.appealPerson;
		  	   var appealReason=data.appealReason;
		  	   var examineComments=data.examineComments;
		  	   var examinePerson=data.examinePerson;
	      	/*  console.log(data);*/
	    	  layer.confirm('是否确定拒绝接受<span style="color: red;">'+medicalIdInfo+'</span>的审核', function(index){
		        	//执行 Ajax 后重载
	    		var url = $WEB_ROOT_PATH + "/dhccApi/resultAppeal/resultAppeal/save";
	    	    $.post(url,{"resultAppeal.status":status,
	    	    	"resultAppeal.fileUrl":fileUrl,
	    	    	"resultAppeal.originalFilename":originalFilename,
	    	    	"resultAppeal.id":id,
	    	    	"resultAppeal.medicalId":medicalIdInfo,
	    	    	"resultAppeal.orgName":orgName,
	    	    	"resultAppeal.orgCode":orgCode,
	    	    	"resultAppeal.createDate":createDate,
	    	    	"resultAppeal.updateDate":updateDate,
	    	    	"resultAppeal.appealDate":appealDate,
	    	    	"resultAppeal.appealPerson":appealPerson,
	    	    	"resultAppeal.appealReason":appealReason,
	    	    	"resultAppeal.examineComments":examineComments ,	
	    	    	"resultAppeal.examinePerson":examinePerson }, function (result) {
	    	    	 var inFlag= result.operateSuccess; 
	    	   	  if(inFlag==true){
	    	   		  layer.msg('打回成功!');
	    	   		  table.reload('resultAppeal'); //数据刷新
	    	   	  }else{
	    	   		  layer.msg('打回失败!');
	    	   		  table.reload('resultAppeal'); //数据刷新
	    	   	  }
	    	       
	    	    });
	    	  });    	  
	      }else if(obj.event === 'yes'){
	    	   var status="1";		 
	    	   var fileUrl=data.fileUrl;
		  	   var originalFilename=data.originalFilename;
		  	   var id=data.id;
		  	   var medicalIdInfo=data.medicalId;
		  	   var orgName=data.orgName;
		       var orgCode=data.orgCode;
		  	   var createDate=data.createDate;
		  	   var updateDate=data.updateDate;
		  	   var appealDate=data.appealDate;
		  	   var appealPerson=data.appealPerson;
		  	   var appealReason=data.appealReason;
		  	   var examineComments=data.examineComments;
		  	   var examinePerson=data.examinePerson;
    	  
	    	  layer.confirm('是否确定接受<span style="color: red;">'+medicalIdInfo+'</span>的审核', function(index){
		        	//执行 Ajax 后重载
	    		var url = $WEB_ROOT_PATH + "/dhccApi/resultAppeal/resultAppeal/save";
	    	    $.post(url,{"resultAppeal.status":status,
	    	    	"resultAppeal.fileUrl":fileUrl,
	    	    	"resultAppeal.originalFilename":originalFilename,
	    	    	"resultAppeal.id":id,
	    	    	"resultAppeal.medicalId":medicalIdInfo,
	    	    	"resultAppeal.orgName":orgName,
	    	    	"resultAppeal.orgCode":orgCode,
	    	    	"resultAppeal.createDate":createDate,
	    	    	"resultAppeal.updateDate":updateDate,
	    	    	"resultAppeal.appealDate":appealDate,
	    	    	"resultAppeal.appealPerson":appealPerson,
	    	    	"resultAppeal.appealReason":appealReason,
	    	    	"resultAppeal.examineComments":examineComments ,	
	    	    	"resultAppeal.examinePerson":examinePerson }, function (result) {
	    	    	 var inFlag= result.operateSuccess; 
	    	   	  if(inFlag==true){
	    	   		  layer.msg('接受成功!');
	    	   		  table.reload('resultAppeal'); //数据刷新
	    	   	  }else{
	    	   		  layer.msg('接受失败!');
	    	   		  table.reload('resultAppeal'); //数据刷新
	    	   	  }
	    	       
	    	    });
	    	  });
	      }else if(obj.event === 'no'){
	    	   var status="5";
		  	   var fileUrl=data.fileUrl;
		  	   var originalFilename=data.originalFilename;
		  	   var id=data.id;
		  	   var medicalIdInfo=data.medicalId;
		  	   var orgName=data.orgName;
		  	   var orgCode=data.orgCode;
		  	   var createDate=data.createDate;
		  	   var updateDate=data.updateDate;
		  	   var appealDate=data.appealDate;
		  	   var appealPerson=data.appealPerson;
		  	   var appealReason=data.appealReason;
		  	   var examineComments=data.examineComments;
		  	   var examinePerson=data.examinePerson;
	      	/*  console.log(data);*/
	    	  layer.confirm('是否确定拒绝接受<span style="color: red;">'+medicalIdInfo+'</span>的审核', function(index){
		        	//执行 Ajax 后重载
	    		var url = $WEB_ROOT_PATH + "/dhccApi/resultAppeal/resultAppeal/save";
	    	    $.post(url,{"resultAppeal.status":status,
	    	    	"resultAppeal.fileUrl":fileUrl,
	    	    	"resultAppeal.originalFilename":originalFilename,
	    	    	"resultAppeal.id":id,
	    	    	"resultAppeal.medicalId":medicalIdInfo,
	    	    	"resultAppeal.orgName":orgName,
	    	    	"resultAppeal.orgCode":orgCode,
	    	    	"resultAppeal.createDate":createDate,
	    	    	"resultAppeal.updateDate":updateDate,
	    	    	"resultAppeal.appealDate":appealDate,
	    	    	"resultAppeal.appealPerson":appealPerson,
	    	    	"resultAppeal.appealReason":appealReason,
	    	    	"resultAppeal.examineComments":examineComments ,	
	    	    	"resultAppeal.examinePerson":examinePerson }, function (result) {
	    	    	 var inFlag= result.operateSuccess; 
	    	   	  if(inFlag==true){
	    	   		  layer.msg('接受成功!');
	    	   		  table.reload('resultAppeal'); //数据刷新
	    	   	  }else{
	    	   		  layer.msg('接受失败!');
	    	   		  table.reload('resultAppeal'); //数据刷新
	    	   	  }
	    	       
	    	    });
	    	  });    	  
	      }
	    });
	    
	    //行监听
	    table.on('row(resultAppeal)', function(obj){
	    
	    	//刷新就诊明细
	    	var result=obj.data;
	    	//findMedicalDate(result.id);
	    	//将medical_id赋值到全局变量
	    	medical_id=result.medicalId;
	    	$("#medical_id_form").val(medical_id);
	    	var getValue = JSON.stringify(layui.formSelects.value('select1', 'val'));
    		is_ilegal=getValue.substring(1,getValue.length-1);
	    	$('#dg').datagrid('reload',{'medicalDetail.medicalId': medical_id,'medicalDetail.isIlegal':is_ilegal}); 
	    	 $("tr").css("background-color",""); 
	     	obj.tr.css("background-color","#C0C0C0");
	
	    });
	    
	    
	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });

function addInfo(id){
	console.log(id);
	  //处理
	    layer.open({
	          type: 2
	          ,title: '提交申诉材料'
	          ,content: $WEB_ROOT_PATH+'/resultAppeal/resultAppealInfo'
	          ,maxmin: true
	          ,area: ['760px', '500px']
	          ,btn: ['确定', '取消']
	          ,success: function(layero, index){
	        	  
      	  var iframeWindow = window['layui-layer-iframe'+ index];
      	  //向此iframe层方法 传递参数
      	  iframeWindow.child(id);
            }
	          ,yes: function(index, layero){
	        	  indexAll=index;
	            var iframeWindow = window['layui-layer-iframe'+ indexAll]
	            ,submitID = 'layuiadmin-btn-useradmin'
	            ,submit = layero.find('iframe').contents().find('#'+ submitID);
	            submit.trigger('click');
	            setInterval(table.reload('resultAppeal'),3000); 
	  
	          }
	          ,no:function(index, layero){
	        	  table.reload('resultAppeal'); //数据刷新
		          }
	        }); 


}

function closeLayer(log){
	layer.close(indexAll); //关闭弹层
	tableAll.reload('resultAppeal'); //数据刷新
	layer.msg(log);
}
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