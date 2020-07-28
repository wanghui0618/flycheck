//初始化	
var medical_id='';//medical表id
var medicalId;
var resultt;
var billing_no='';//就诊明细id
var is_ilegal='';//是否违规
var index_last=null;
var statu_last=null;
var indexAll;
var tableAll;
var medicalAduitId;
var excelData = null;
var allStatus;
var totalAmount;
var aduit_methods;
var table1;
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
	    table1=layui.table;
	    var laydate = layui.laydate;
	    
	    var Request = GetRequest()
	    var orgCode = Request["orgCode"];
	    var type = Request["type"];
	  //日期范围
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
       /* $.getJSON($WEB_ROOT_PATH+'/dhccApi/dataauthority/dataauthority/findOrg',
            function(data){
                var orgs=data.pageModel.rows
                //var  dataList= data.dictList;
                var org_save=JSON.stringify(orgs);//解析为字符串
                for(var i=0 ;i<orgs.length;i++){
                    var mm="<option value='"+orgs[i].value+"'>"+orgs[i].text+"</option>";
                    $("#zyOrgName").append(mm);
                }
                form.render('select');
           });*/
        
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
                    $("#orgName").append(mm);
                }
                form.render('select');
           });
	    

	    table.render({
	    	elem: '#userTable'
	            ,url: $WEB_ROOT_PATH+'/dhccApi/medical/medical/listAuditVo'
	            ,cellMinWidth: 100
	             ,height: 210
	             ,limits:[5,10,20]
	             ,limit: 5
	             ,where:{"medical.orgCode":orgCode,"type":type}
	            ,cols: [[
	            	 {type: 'numbers', title: '序号' }
		              ,{field:'id', title: 'ID', sort: true, hide:true}
		              ,{field:'left', title:'操作',  width:135,templet: function(d){
		            	var statu=d.auditMethods;  
		            	if(statu==0){
		            		return "<a class='layui-btn layui-btn-xs' lay-event='jihe'>提交</a>";
		            		}
		            	else if(statu==1){
		            		return '<a class="layui-btn  layui-btn-xs" lay-event="jihe">提交</a><a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="jihecailiao">稽核材料</a>';
		            		}
		              	}
		              }
		              ,{field:'aduitStatus',title:'稽核结果'}
		              ,{field:'userStatus', title:'初审结果' }
		              ,{field:'sysStatus', title:'机审结果' }
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
	            ,done:function(data){
	            	if(data.data!=""){
		            	var id=data.data['0']['id'];
		   	            medicalId=id;
		   	         	var index=-1;
		   	            //分类显示中文名称
		   	            $("[data-field='id']").children().each(function () {
		   	            index++;
		   	            if ($(this).text() == data.data['0']['id']) {
		   	              $('tr').eq(index).css("background-color","#EEF6FF");
		   	                }
		   	             });
		   	            
		   	         $(document).find('[data-index="0"]').trigger("click");//选中单选按钮  
		      		}else{
	            	 return;
		      		}
	            	$("#medical_id_form").val(id);
	            	//console.info(medical_id);
	            	var getValue = JSON.stringify(layui.formSelects.value('select1', 'val'));
	        		is_ilegal=getValue.substring(1,getValue.length-1);
	            	$('#dg').datagrid('load',{'medicalDetail.medicalId': medical_id,'medicalDetail.isIlegal':is_ilegal});
	            }
	           
		      		,page: true
		      		
        		});
	    hideButtonStatic();//按钮权限
	   
	    table.reload('userTable'); //数据刷新
	    
	    layui.use(['table'],function(){
	    	 $("#medical-medicalAudit-exportExcel").click(function(){
	    		 var balanceDate,inhosDate,billingNo,admissionNo,diagType,idcard,name,sscno,sysStatus,ilegalType,orgName,outhosDate;
	    		 if(excelData != null){
	    			/* field["medical.name"]*/
	    			 balanceDate = excelData.balanceDate;
	    			 inhosDate=excelData.inhosDate;
	    			 billingNo=excelData.billingNo;
	    			 admissionNo=excelData["medical.admissionNo"];
	    			 diagType=excelData["medical.diagType"];
	    			 idcard=excelData["medical.idcard"];
	    			 name=excelData["medical.name"];
	    			 sscno=excelData["medical.sscno"];
	    			 sysStatus=excelData["medical.sysStatus"];
	    			 outhosDate=excelData["outhosDate"];
	    			 ilegalType=excelData["medicalDetail.ilegalType"];
	    			 orgName=excelData.orgName;
	    		 }	
	    		 /*table.exportFile(ins1.config.id,allExportData,'xls');*/
	    		 window.open($WEB_ROOT_PATH+'/dhccApi/medicalexcel/medicalexcel/exportExcelToSelf-jh?balanceDate='+balanceDate+'&inhosDate='+inhosDate+'&billingNo='+billingNo+'&admissionNo='+admissionNo+'&diagType='+diagType+'&idcard='+idcard+ '&name='+name+'&orgName='+orgName+'&sysStatus='+sysStatus+'&outhosDate='+outhosDate+'&sscno='+sscno+'&ilegalType='+ilegalType);
	    	 })
	    });
	    
	    var thisRowData;
		    		
	    
	    
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
              var url=$WEB_ROOT_PATH+"/dhccApi/medicalaudit/medicalAudit/save";
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
	      var data = obj.data;
	    	 var result;
	      medicalId=data.id;
	      
	      
	      if(obj.event === 'del'){
	    	//删除
	        layer.confirm('确定删除该条记录吗？', function(index){
	        	//执行 Ajax 后重载
	            var url=$WEB_ROOT_PATH+"/dhccApi/medicalaudit/medicalAudit/delete";
	            $.post(url,{"medicalAudit.Id":data.id},function(result){
	        	    table.reload('userTable');
	                layer.msg('已删除');
	    	    });
	        });
	      } else if(obj.event === 'audit'){
	      	//修改方法
		    layer.open({
		          type: 2
		          ,title: '稽核信息核对'
		          ,content: $WEB_ROOT_PATH+'/medical/medicalAuditForm'
		          ,maxmin: true
		          ,area: ['1100px', '450px']
		          ,btn: ['确定', '取消']
		          ,success: function(layero, index){
		        	  indexAll =index;
		        	  var url=$WEB_ROOT_PATH+"/dhccApi/medicalaudit/medicalAudit/findData1?medicalId1="+medicalId;
		        	  $.ajaxSetup({
		        		  async : false
		        		  });
			            $.post(url,{"medicalAudit.medicalId":data.id},function(result){
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
	
		            //监听提交
/*		            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
		              var field = data.field; //获取提交的字段
		              //提交 Ajax后台 
		              var url=$WEB_ROOT_PATH+"/dhccApi/medicalaudit/medicalAudit/save?medicalId1="+medicalId;
		              $.post(url,field,function(result){
		            	  var inFlag= result.inFlag; 
		            	  if(inFlag==0){
		            		  layer.msg('修改成功!');
			            	  //后台成功后，静态更新表格中的数据
			                  table.reload('userTable'); //数据刷新
			                  layer.close(index); //关闭弹层
		            	  }
					  });
		            });  */
		            submit.trigger('click');
		          }
		        }); 
	      }
	      else if(obj.event === 'jihe'){
			            //监听提交
			            layer.confirm('确定提交<span style="color: red;"></span>审核',function(data){
			              var field = data.field; //获取提交的字段
			              //提交 Ajax后台 
			              var url=$WEB_ROOT_PATH+"/dhccApi/medicalaudit/medicalAudit/save2?medicalId1="+medicalId;
			              $.post(url,field,function(result){
			            	  var inFlag= result.inFlag; 
			            	  if(inFlag==0){
				            	  //后台成功后，静态更新表格中的数据
				                  table.reload('userTable'); //数据刷新
				                   closeLayer("完成");
			            	  }else if(inFlag==2){
			            		//后台成功后，静态更新表格中的数据
				                   closeLayer1("");
				                   layer.msg('请先进行稽核');
			            	  }else if(inFlag==3){
			            		  closeLayer1("");
				                   layer.msg('未全部稽核');
			            	  }
						  });
			            });  
			            
		      
	      } 
	      else if(obj.event === 'jihecailiao'){

	    		//修改方法
	    	    layer.open({
			          type: 2
			          ,title: '稽核信息核对'
			          ,content: $WEB_ROOT_PATH+'/medical/medicalAuditForm2'
			          ,maxmin: true
			          ,area: ['850px', '550px']
			          ,btn: ['确定', '取消']
			          ,success: function(layero, index){
			        	  indexAll =index;
			        	  var url=$WEB_ROOT_PATH+"/dhccApi/medicalaudit/medicalAudit/findZhuBiao?medicalId1="+medicalId;
			        	  $.ajaxSetup({
			        		  async : false
			        		  });
				            $.post(url,{"medicalAudit.medicalId":data.id},function(result){
				            	 resultt=result.medicalAudit;
				            	  totalAmount=result.totalAmount;
				            })
				            var iframeWindow = window['layui-layer-iframe'+ index];
				            var aduitStatus=resultt.aduitStatus;
				        	  //向此iframe层方法 传递参数
				        	  iframeWindow.child(JSON.stringify(resultt));
				        	  iframeWindow.child2(medicalId);
				        	  iframeWindow.childTotalAmount(totalAmount);
				        	  iframeWindow.child3(JSON.stringify(aduitStatus));
			          }
			          ,yes: function(index, layero){
			            var iframeWindow = window['layui-layer-iframe'+ index]
			            ,submitID = 'LAY-user-front-submit'
			            ,submit = layero.find('iframe').contents().find('#'+ submitID);
			            submit.trigger('click');
			          }
			          
			        });
	    	  

	      
	      }
	      
	    });
	   
	    
	    //行监听
	    table.on('row(userTable)', function(obj){
	    	 var data = obj.data;
	            var id = data.id;
	            medicalId=data.id;
	            allStatus=data.auditMethods;
	            aduit_methods=data.auditMethods;
	    	 $("tr").css("background-color",""); 
	            $(this).css("background-color","#EEF6FF"); 
	    	//刷新就诊明细
	    	var result=obj.data;
	    	//findMedicalDate(result.id);
	    	
	    	//将medical_id赋值到全局变量
	    	medical_id=result.id;
	    	$("#medical_id_form").val(medical_id);
	    	var getValue = JSON.stringify(layui.formSelects.value('select1', 'val'));
    		is_ilegal=getValue.substring(1,getValue.length-1);
	    	$('#dg').datagrid('reload',{'medicalDetail.medicalId': medical_id,'medicalDetail.isIlegal':is_ilegal}); 
	    	
	    });
	    
	    //监听搜索上
		form.on('submit(LAY-user-front-search)', function(data){
	    	var field = data.field;
	    	excelData = field;
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
		form.on('submit(LAY-user-front-search-allUpdate)', function(data){
			//console.log($("#dg").datagrid('getChecked'));
			//return;
			var myArray=new Array();
			for(var i=0;i<$("#dg").datagrid('getChecked').length;i++){
				myArray[i]=$("#dg").datagrid('getChecked')[i].id;
			};
			var num=myArray.length;
			if(num=='0'){
				layer.msg('请先选择数据');
				return;
			}else {
			 layer.open({
				 content: '所选明细审核为'
					  ,btn: ['正常', '违规']
			  ,btnAlign: 'c'
				  ,btn1: function(a,b){
					//提交 Ajax后台 
		              var url=$WEB_ROOT_PATH+"/dhccApi/medicalaudit/medicalAudit/allUpdate?myArray="+myArray+"&medicalId1="+medicalId;
		              var lol="2";
		              var field={"medicalAudit.aduitStatus":lol};
						$.post(url,field,function(result){
							var inFlag= result.inFlag; 
							if(inFlag==0){
								layer.msg('批量审核成功!,共 '+myArray.length+' 条数据被审核。');
								 closeLayer("完成");
								 layer.close(index); //关闭弹层
							}
						});
				  }
			 ,btn2: function(a,b){
					//提交 Ajax后台 
		              var url=$WEB_ROOT_PATH+"/dhccApi/medicalaudit/medicalAudit/allUpdate?myArray="+myArray+"&medicalId1="+medicalId;
		              var lol="0";
		              var field={"medicalAudit.aduitStatus":lol};
						$.post(url,field,function(result){
							var inFlag= result.inFlag; 
							if(inFlag==0){
								layer.msg('批量审核成功!,共 '+myArray.length+' 条数据被审核。');
								 closeLayer("完成");
								 layer.close(index); //关闭弹层
							}
						});
				  }	 
				 
				 
				 
				 
				 
				 
				 /*
		          type: 2
		          ,title: '稽核信息'
		          ,content: $WEB_ROOT_PATH+'/medical/medicalAuditForm1'
		          ,maxmin: true
		          ,area: ['350px', '170px']
		          ,btn: ['正常', '违规']
		          ,yes: function(index, layero){
		            var iframeWindow = window['layui-layer-iframe'+ index]
		            ,submitID = 'LAY-user-front-submit'
		            ,submit = layero.find('iframe').contents().find('#'+ submitID);
		            
		            //监听提交
		            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
		              var field = data.field; //获取提交的字段
		              
		              //提交 Ajax后台 
		              var url=$WEB_ROOT_PATH+"/dhccApi/medicalaudit/medicalAudit/allUpdate?myArray="+myArray+"&medicalId1="+medicalId;
						$.post(url,field,function(result){
							var inFlag= result.inFlag; 
							if(inFlag==0){
								layer.msg('批量审核成功!,共 '+myArray.length+' 条数据被审核。');
								 closeLayer("完成");
								 layer.close(index); //关闭弹层
							}
						});
  });
submit.trigger('click');
}
*/}); 
			};
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
		
		 var medicalVoJpp=result.medicalVoJpp;
		 console.log(medicalVoJpp);
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
//保存详细下拉框
function saveItem(valueObject,index){
	var row = $('#dg').datagrid('getRows')[index];
	var withholdingQuantity=valueObject.withholdingQuantity; //$("#withholdingQuantity").val();
	var withholdingAmount=valueObject.withholdingAmount;//$("#withholdingAmount").val();
	var deductions=valueObject.deductions//$("#deductions").val();
	var field={"medicalDetail.id":row.id,"medicalDetail.medicalId":medicalId,"medicalDetail.deductions":deductions,"medicalDetail.withholdingAmount":withholdingAmount,"medicalDetail.withholdingQuantity":withholdingQuantity};
	  var url=$WEB_ROOT_PATH+"/dhccApi/resultAppeal/resultAppeal/saveCostDetail";
	  $.post(url,field,function(result){
	 	  if(result.inFlag==0){
	 		  layer.msg('保存成功!');
	 		 $("#dg").datagrid('reload');  
	 		  //刷新
	 		 //amountTotal();//右侧总金额刷新
	 	  }else{
	 		 layer.msg('保存不成功!');
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
	var sysStatus=row.sysStatus;
	if(sysStatus=="正常"){
			return;
	}
	if(allStatus=="0"){
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
		}else if(allStatus=="1"){
			return;
		}
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

function closeLayer(log){
	layer.close(indexAll); //关闭弹层
	layer.msg(log);
	 $("#dg").datagrid('reload');
	 table1.reload('userTable');
}

function closeLayer1(log){
	layer.close(indexAll); //关闭弹层
	layer.msg(log);
	 $("#dg").datagrid('reload');
}

function audit(id){
	medicalAduitId=id;
	//修改方法
    layer.open({
          type: 2
          ,title: '稽核信息核对'
          ,content: $WEB_ROOT_PATH+'/medical/medicalAuditForm'
          ,maxmin: true
          ,area: ['850px', '550px']
          ,btn: ['确定', '取消']
          ,success: function(layero, index){
        	  indexAll =index;
        	  var url=$WEB_ROOT_PATH+"/dhccApi/medicalaudit/medicalAudit/findData?medicalId1="+medicalId+"&idd="+id;
        	  $.ajaxSetup({
        		  async : false
        		  });
	            $.post(url,{"medicalAudit.medicalId":medicalId},function(result){
	            	
	            	 resultt=result.medicalAudit;
	            })
	            var iframeWindow = window['layui-layer-iframe'+ index];
            	 var aduitStatus=resultt.aduitStatus;
	        	  //向此iframe层方法 传递参数
	        	  iframeWindow.child(JSON.stringify(resultt));
	        	  iframeWindow.child1(JSON.stringify(medicalAduitId));
	        	  iframeWindow.child2(JSON.stringify(medicalId));
	        	  iframeWindow.child3(JSON.stringify(aduitStatus));
          }
          ,yes: function(index, layero){
            var iframeWindow = window['layui-layer-iframe'+ index]
            ,submitID = 'LAY-user-front-submit'
            ,submit = layero.find('iframe').contents().find('#'+ submitID);

   
            submit.trigger('click');
          }
        });
}

