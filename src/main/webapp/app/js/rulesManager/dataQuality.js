//初始化	
layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'table','laydate'], function(){
	    var $ = layui.$
	    ,form = layui.form
	    ,table = layui.table;
	    var laydate = layui.laydate;
	    var filterCondition={"json":"json"};
	    
	  //日期范围
	    laydate.render({
	      elem: '#test-laydate-range-date'
	      ,range: true
	    });
	    hideButtonStatic();//按钮权限
	    
	    
	    table.render({
	    	elem: '#dataQualityTable'
	            ,url: $WEB_ROOT_PATH+'/dhccApi/medical/medical/incompleteDataList?tableName=T_PICCBID_MEDICAL'
	            ,cellMinWidth: 100
	             ,height: document.documentElement.clientHeight-65
	            ,where: {  ilegalChild: '1'  }
	            ,cols: [[
	            	   {type: 'numbers', title: '序号' }
	            	   ,{field:'id', title: 'ID',  align:'center',sort: true, hide:true}
	            	   ,{field:'name',  align:'center',width:120, title:'姓名',templet:function(d){
	            	      	  return setColor(d.name,isFilterCondition('name'))
	            	  	  }}
	            	   ,{field:'sex',  align:'center',title: '性别' ,templet:function(d){
	            	      	  return setColor(d.sex,isFilterCondition('sex'))
	            	  	  }}
	            	   ,{field:'age',  align:'center',title:'年龄' ,templet:function(d){
	            	      	  return setColor(d.age,isFilterCondition('age'))
	            	  	  }}
	            	   ,{field:'idcard',  align:'center',width:180, title:'身份证号',templet:function(d){
	            	      	  return setColor(d.idcard,isFilterCondition('idcard'))
	            	  	  } }
			           ,{field:'handdingInsName', align:'center', width:180,title:'统筹区',templet:function(d){
	            	      	  return setColor(d.handdingInsName,isFilterCondition('handding_ins_name'))
	            	  	  } }
			           ,{field:'orgName', align:'center', width:180,title:'医疗机构' ,templet:function(d){
	            	      	  return setColor(d.orgName,isFilterCondition('org_name'))
	            	  	  }}
			           ,{field:'diagType',  align:'center',title:'就诊类型' ,templet:function(d){
			            	  return setColor(d.diagType,isFilterCondition('diag_type'))
		            	  }}
			           ,{field:'sscno',  align:'center', width:130,title: '社保卡号' ,templet:function(d){
	            	      	  return setColor(d.sscno,isFilterCondition('sscno'))
	            	  	  }}
			           ,{field:'billingNo', align:'center',width:140, title:'收费单据号',templet:function(d){
	            	      	  return setColor(d.billingNo,isFilterCondition('billing_no'))
	            	  	  }}
			           ,{field:'paymentDate', align:'center',width:120, title:'费用发生日期',templet:function(d){
	            	      	  return setColor(d.paymentDate,isFilterCondition('payment_date'))
	            	  	  }}
			           ,{field:'reversalMark', align:'center',width:115, title:'冲销标志',templet:function(d){
	            	      	  return setColor(d.reversalMark,isFilterCondition('reversal_mark'))
	            	  	  }}
			           ,{field:'admissionNo',  align:'center',title:'住院号' ,templet:function(d){
	            	      	  return setColor(d.admissionNo,isFilterCondition('admission_no'))
	            	  	  }}
			           ,{field:'admissionType',  align:'center', width:90,title: '住院类型' ,templet:function(d){
	            	      	  return setColor(d.admissionType,isFilterCondition('admission_type'))
	            	  	  }}
			           ,{field:'medicalType',  align:'center', width:90,title: '险种类型' ,templet:function(d){
	            	      	  return setColor(d.medicalType,isFilterCondition('medical_type'))
	            	  	  }}
			           ,{field:'condition',  align:'center', width:90,title: '病情' ,templet:function(d){
	            	      	  return setColor(d.condition,isFilterCondition('condition'))
	            	  	  }}
			           ,{field:'departName',  align:'center',title:'科室' ,templet:function(d){
	            	      	  return setColor(d.departName,isFilterCondition('depart_name'))
	            	  	  }}
			           ,{field:'inhosDate',  align:'center',width:120,title:'入院日期' ,templet:function(d){
	            	      	  return setColor(d.inhosDate,isFilterCondition('inhos_date'))
	            	  	  }}
			           ,{field:'outhosDate', align:'center', width:120,title:'出院日期' ,templet:function(d){
	            	      	  return setColor(d.outhosDate,isFilterCondition('outhos_date'))
	            	  	  }}
			           ,{field:'totalCost',  align:'center',title:'总金额' ,templet:function(d){
	            	      	  return setColor(d.totalCost,isFilterCondition('total_cost'))
	            	  	  }}
			           ,{field:'fundCost', align:'center', title:'基金支付金额' ,templet:function(d){
	            	      	  return setColor(d.fundCost,isFilterCondition('fund_cost'))
	            	  	  }}
			           ,{field:'selfCost',  align:'center',title:'个人负担金额' ,templet:function(d){
	            	      	  return setColor(d.selfCost,isFilterCondition('self_cost'))
	            	  	  }}
			           ,{field:'basicCostM',  align:'center',width:90, title:'基本统筹应支付' ,templet:function(d){
	            	      	  return setColor(d.basicCostM,isFilterCondition('basic_costM'))
	            	  	  }}
			           ,{field:'povertyAlleviationSubsidy',  align:'center', width:90,title:'扶贫补助' ,templet:function(d){
	            	      	  return setColor(d.povertyAlleviationSubsidy,isFilterCondition('poverty_alleviation_subsidy'))
	            	  	  }}
			           ,{field:'financeSubsidy',  align:'center',title:'财政补助' ,templet:function(d){
	            	      	  return setColor(d.financeSubsidy,isFilterCondition('finance_subsidy'))
	            	  	  }}
			           ,{field:'officialSubsidy',   align:'center',width:115,title:'公务员补助' ,templet:function(d){
	            	      	  return setColor(d.officialSubsidy,isFilterCondition('official_subsidy'))
	            	  	  }}
			           ,{field:'treatmentType',  align:'center', width:115,title:'待遇享受类别' ,templet:function(d){
	            	      	  return setColor(d.treatmentType,isFilterCondition('treatment_type'))
	            	  	  }}
			           ,{field:'medicalTreatmentState',  align:'center', width:115,title:'医疗待遇状态' ,templet:function(d){
	            	      	  return setColor(d.medicalTreatmentState,isFilterCondition('medical_treatment_state'))
	            	  	  }}
			           ,{field:'dischargeState',  align:'center',title:'出院状态' ,templet:function(d){
	            	      	  return setColor(d.dischargeState,isFilterCondition('discharge_state'))
	            	  	  }}
			           ,{field:'treatmentWay', align:'center', title:'就诊方式' ,templet:function(d){
	            	      	  return setColor(d.treatmentWay,isFilterCondition('treatment_way'))
	            	  	  }}
			              ,{field:'stayLength',  align:'center',title:'住院天数',templet:function(d){
	            	      	  return setColor(d.stayLength,isFilterCondition('stay_length'))
	            	  	  } }
			              ,{field:'rangeCost', align:'center', title:'本次纳入报销范围金额',templet:function(d){
	            	      	  return setColor(d.rangeCost,isFilterCondition('range_cost'))
	            	  	  } }
			              ,{field:'basicCostR', align:'center', title:'基本统筹实际支付' ,templet:function(d){
	            	      	  return setColor(d.basicCostR,isFilterCondition('basic_cost_r'))
	            	  	  }}
			              ,{field:'selfPayAmount', align:'center', title:'个人自付金额' ,templet:function(d){
	            	      	  return setColor(d.selfPayAmount,isFilterCondition('self_pay_amount'))
	            	  	  }}
			              ,{field:'selfExpenditureAmount', align:'center', title:'个人自费金额' ,templet:function(d){
	            	      	  return setColor(d.selfExpenditureAmount,isFilterCondition('self_expenditure_amount'))
	            	  	  }}
			              ,{field:'sscAccountCost', align:'center', title:'个人账户自付' ,templet:function(d){
	            	      	  return setColor(d.sscAccountCost,isFilterCondition('ssc_account_cost'))
	            	  	  }}
			              ,{field:'cashCost',  align:'center',title:'现金支付金额' ,templet:function(d){
	            	      	  return setColor(d.cashCost,isFilterCondition('cash_cost'))
	            	  	  }}
			              ,{field:'largeCostM', align:'center', title:'大额应支付' ,templet:function(d){
	            	      	  return setColor(d.largeCostM,isFilterCondition('large_cost_m'))
	            	  	  }}
			              ,{field:'largeCostR', title:'大额实支付' ,templet:function(d){
	            	      	  return setColor(d.largeCostR,isFilterCondition('large_cost_r'))
	            	  	  }}
			              ,{field:'civilAffairSubsidy',  align:'center',title:'民政救助' ,templet:function(d){
	            	      	  return setColor(d.civilAffairSubsidy,isFilterCondition('civil_affair_subsidy'))
	            	  	  }}
			              ,{field:'fullOrdination',  align:'center',title:'全额统筹' ,templet:function(d){
	            	      	  return setColor(d.fullOrdination,isFilterCondition('full_ordination'))
	            	  	  }}
			              ,{field:'partialOrdination',  align:'center',title:'部分统筹' ,templet:function(d){
	            	      	  return setColor(d.partialOrdination,isFilterCondition('partial_ordination'))
	            	  	  }}
			              ,{field:'partialPayment',  align:'center',title:'部分自付' ,templet:function(d){
	            	      	  return setColor(d.partialPayment,isFilterCondition('partial_payment'))
	            	  	  }}
			              ,{field:'fullPayment',  align:'center',title:'全额自付' ,templet:function(d){
	            	      	  return setColor(d.fullPayment,isFilterCondition('full_payment'))
	            	  	  }}
			              ,{field:'crowdType',  align:'center',title:'人群类别' ,templet:function(d){
	            	      	  return setColor(d.crowdType,isFilterCondition('crowd_type'))
	            	  	  }}
			              ,{field:'reimbursementType', align:'center', title:'报销类型' ,templet:function(d){
	            	      	  return setColor(d.reimbursementType,isFilterCondition('reimbursement_type'))
	            	  	  }}
			              ,{field:'hospCount',  align:'center',title:'住院次数' ,templet:function(d){
	            	      	  return setColor(d.hospCount,isFilterCondition('hosp_count'))
	            	  	  }}
			              ,{field:'insuranceMark', align:'center', title:'险种标志' ,templet:function(d){
	            	      	  return setColor(d.insuranceMark,isFilterCondition('insurance_mark'))
	            	  	  }}
			              ,{field:'inDiagnosisNo', align:'center', title:'入院诊断编码',templet:function(d){
	            	      	  return setColor(d.inDiagnosisNo,isFilterCondition('in_diagnosis_no'))
	            	  	  } }
			              ,{field:'inDiagnosisName',  align:'center',title:'入院诊断名称' ,templet:function(d){
	            	      	  return setColor(d.inDiagnosisName,isFilterCondition('in_diagnosis_name'))
	            	  	  }}
			              ,{field:'outDiagnosisNo', align:'center', title:'出院诊断编码',templet:function(d){
	            	      	  return setColor(d.outDiagnosisNo,isFilterCondition('out_diagnosis_no'))
	            	  	  } }
			              ,{field:'outDiagnosisName', align:'center', title:'出院诊断名称' ,templet:function(d){
	            	      	  return setColor(d.outDiagnosisName,isFilterCondition('out_diagnosis_name'))
	            	  	  }}
			              ,{field:'createTime', width:120,title: '数据上传时间',templet:function(d){
	            	      	  return setColor(d.createTime,isFilterCondition('create_time'))
	            	  	  }}
			              ]]
	            ,done:function(data){
		              $("[data-field='status']").children().each(function(){
		                    if($(this).text()=='0'){
		                        $(this).text("未审核")
		                    }else if($(this).text()=='1'){
		                        $(this).text("机审完成")
		                    }else if($(this).text()=='2'){
		                        $(this).text("人工审完成")
		                    }else if($(this).text()=='3'){
		                        $(this).text("终审完成")
		                    }
		                });
		      			$("[data-field='userStatus']").children().each(function(){
		                    if($(this).text()=='0'){
		                        $(this).text("未违规")
		                    }else if($(this).text()=='1'){
		                        $(this).text("违规")
		                    }
		                });
		      			$("[data-field='finaStatus']").children().each(function(){
		                    if($(this).text()=='0'){
		                        $(this).text("未违规")
		                    }else if($(this).text()=='1'){
		                        $(this).text("违规")
		                    }
		                });
		      			$("[data-field='sysStatus']").children().each(function(){
		                    if($(this).text()=='0'){
		                        $(this).text("未违规")
		                    }else if($(this).text()=='1'){
		                        $(this).text("违规")
		                    }else if($(this).text()=='2'){
		                        $(this).text("疑似违规")
		                    }
		                });
		      			$("[data-field='medicalType']").children().each(function(){
		                    if($(this).text()=='301'){
		                        $(this).text("职工医疗")
		                    }else if($(this).text()=='305'){
		                        $(this).text("居民医疗")
		                    }else if($(this).text()=='309'){
		                        $(this).text("新农合")
		                    }else if($(this).text()=='401'){
		                        $(this).text("生育险种")
		                    }else if($(this).text()=='501'){
		                        $(this).text("工伤险种")
		                    }
		                });
		      			
		      			$("[data-field='crowdType']").children().each(function(){
		                    if($(this).text()=='A'){
		                        $(this).text("职工")
		                    }else if($(this).text()=='B'){
		                        $(this).text("居民")
		                    }else if($(this).text()=='C'){
		                        $(this).text("农合")
		                    }
		                });
		      			
		      			$("[data-field='diagType']").children().each(function(){
		                    if($(this).text()=='1'){
		                        $(this).text("住院")
		                    }else if($(this).text()=='2'){
		                        $(this).text("普通门诊")
		                    }else if($(this).text()=='3'){
		                        $(this).text("门诊大病")
		                    }else if($(this).text()=='9'){
		                        $(this).text("其它")
		                    }
		                });
		      		  }
		            ,page: true
        		});
	    loadSelect();            
		    		
	    //监听搜索
		form.on('submit(LAY-org-front-search)', function(data){
	    	var field = data.field;
	        //执行重载
	        layui.table.reload('dataQualityTable', {
	            where: field
	        });
	    });
		
		//加载过滤条件
		function loadSelect(){
			$.getJSON($WEB_ROOT_PATH+'/dhccApi/dataquality/dataquality/getFilterCondition?dataquality.tableName=T_PICCBID_MEDICAL', 
					function(data){
				var  dataList= data.dataqualitys;
				var  form = layui.form
				for(var i=0 ;i<dataList.length;i++){
					filterCondition[dataList[i].tableCol.toLowerCase()]="1";
					var mm="<option value='"+dataList[i].tableCol+"'>"+dataList[i].tableColName+"</option>";
					$("#filterCondition").append(mm); 
				}
				form.render('select');
			});
		}
		
		function isFilterCondition(code){
			if(filterCondition[code]!="1"){
				return false;
			}
			return true;
		}
		
		function setColor(codex,fc){
			if(fc==true){
				if(codex==null||codex==""){
	         		return '<span style="color:red">无</span>';
	         	}else{
	         		return codex;
	         	}
			}else{
				if(codex==null||codex==""){
	         		return '';
	         	}else{
	         		return codex;
	         	}
			}
		}

		/*//加载医院下拉字典
        $.getJSON($WEB_ROOT_PATH+'/dhccApi/admin/admin/findCityOrgDict',
            function(data){
                //var  dataList= data.dictList;
                for(var i=0 ;i<data.length;i++){
                    var mm="<option value='"+data[i].value+"'>"+data[i].text+"</option>";
                    $("#getOrgName").append(mm);
                }
                form.render('select');
           });
        
        
        //加载城市下拉字典
        $.getJSON($WEB_ROOT_PATH+'/dhccApi/admin/admin/findCityDict',
            function(data){
                for(var i=0 ;i<data.length;i++){
                    var mm="<option value='"+data[i].value+"'>"+data[i].text+"</option>";
                    $("#getCityName").append(mm);
                }
                form.render('select');
           });*/
	  

	    
	    //按钮事件绑定底层方法-勿动
	    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
	      var type = $(this).data('type');
	      active[type] ? active[type].call(this) : '';
	    });
	  });