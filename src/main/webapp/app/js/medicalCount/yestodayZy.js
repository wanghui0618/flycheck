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
	    
	  //日期范围
	    laydate.render({
	      elem: '#test-laydate-range-date'
	      ,range: true
	    });
	    hideButtonStatic();//按钮权限
	   
	    
	    table.render({
	    	elem: '#yestodayZyTable'
	            ,url: $WEB_ROOT_PATH+'/dhccApi/medicalCount/medicalCount/yestodayHospitalData?medicalVo.auditMethods=1'
	            ,cellMinWidth: 100
	             ,height: document.documentElement.clientHeight-80
	            ,where: {  ilegalChild: '1'  }
	            ,cols: [[
	            	   {type: 'numbers', title: '序号' }
	            	   ,{field:'id', title: 'ID',  align:'center',sort: true, hide:true}
	            	   ,{field:'name',  align:'center',width:120, title:'姓名'}
	            	   ,{field:'sex',  align:'center',title: '性别' }
	            	   ,{field:'age',  align:'center',title:'年龄' }
	            	   ,{field:'idcard',  align:'center',width:180, title:'身份证号' }
	            	   ,{field:'handdingInsName', align:'center', width:180,title:'统筹区' }
			           ,{field:'orgName', align:'center', width:180,title:'医疗机构' }
			           ,{field:'diagType',  align:'center',title:'就诊类型' }
			           ,{field:'sscno',  align:'center', width:130,title: '社保卡号' }
			           ,{field:'billingNo', align:'center',width:140, title:'收费单据号'}
			           ,{field:'paymentDate', align:'center',width:120, title:'费用发生日期'}
			           ,{field:'reversalMark', align:'center',width:115, title:'冲销标志'}
			           ,{field:'admissionNo',  align:'center',title:'住院号' }
			           ,{field:'admissionType',  align:'center', width:90,title: '住院类型' }
			           ,{field:'medicalType',  align:'center', width:90,title: '险种类型' }
			           ,{field:'condition',  align:'center', width:90,title: '病情' }
			           ,{field:'departName',  align:'center',title:'科室' }
			           ,{field:'inhosDate',  align:'center',width:120,title:'入院日期' }
			           ,{field:'outhosDate', align:'center', width:120,title:'出院日期' }
			           ,{field:'totalCost',  align:'center',title:'总金额' }
			           ,{field:'fundCost', align:'center', title:'基金支付金额' }
			           ,{field:'selfCost',  align:'center',title:'个人负担金额' }
			           ,{field:'basicCostM',  align:'center',width:90, title:'基本统筹应支付' }
			           ,{field:'povertyAlleviationSubsidy',  align:'center', width:90,title:'扶贫补助' }
			           ,{field:'financeSubsidy',  align:'center',title:'财政补助' }
			           ,{field:'officialSubsidy',   align:'center',width:115,title:'公务员补助' }
			           ,{field:'treatmentType',  align:'center', width:115,title:'待遇享受类别' }
			           ,{field:'medicalTreatmentState',  align:'center', width:115,title:'医疗待遇状态' }
			           ,{field:'dischargeState',  align:'center',title:'出院状态' }
			           ,{field:'treatmentWay', align:'center', title:'就诊方式' }
			              ,{field:'stayLength',  align:'center',title:'住院天数' }
			              ,{field:'rangeCost', align:'center', title:'本次纳入报销范围金额' }
			              ,{field:'basicCostR', align:'center', title:'基本统筹实际支付' }
			              ,{field:'selfPayAmount', align:'center', title:'个人自付金额' }
			              ,{field:'selfExpenditureAmount', align:'center', title:'个人自费金额' }
			              ,{field:'sscAccountCost', align:'center', title:'个人账户自付' }
			              ,{field:'cashCost',  align:'center',title:'现金支付金额' }
			              ,{field:'largeCostM', align:'center', title:'大额应支付' }
			              ,{field:'largeCostR', title:'大额实支付' }
			              ,{field:'civilAffairSubsidy',  align:'center',title:'民政救助' }
			              ,{field:'fullOrdination',  align:'center',title:'全额统筹' }
			              ,{field:'partialOrdination',  align:'center',title:'部分统筹' }
			              ,{field:'partialPayment',  align:'center',title:'部分自付' }
			              ,{field:'fullPayment',  align:'center',title:'全额自付' }
			              ,{field:'crowdType',  align:'center',title:'人群类别' }
			              ,{field:'reimbursementType', align:'center', title:'报销类型' }
			              ,{field:'hospCount',  align:'center',title:'住院次数' }
			              ,{field:'insuranceMark', align:'center', title:'险种标志' }
			              ,{field:'inDiagnosisNo', align:'center', title:'入院诊断编码' }
			              ,{field:'inDiagnosisName',  align:'center',title:'入院诊断名称' }
			              ,{field:'outDiagnosisNo', align:'center', title:'出院诊断编码' }
			              ,{field:'outDiagnosisName', align:'center', title:'出院诊断名称' }
			              ,{field:'createTime', width:120,title: '数据上传时间'}
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
	    
		            
		    		
	    
	    //监听搜索
		form.on('submit(LAY-org-front-search)', function(data){
	    	var field = data.field;
	        //执行重载
	        layui.table.reload('yestodayZyTable', {
	            where: field
	        });
	    });
	  

	  
		 //加载医院下拉字典
       /* $.getJSON($WEB_ROOT_PATH+'/dhccApi/admin/admin/findCityOrgDict',
            function(data){
                //var  dataList= data.dictList;
                for(var i=0 ;i<data.length;i++){
                    var mm="<option value='"+data[i].value+"'>"+data[i].text+"</option>";
                    $("#getOrgName").append(mm);
                }
                form.render('select');
           });*/
        
        
        //加载城市下拉字典
        /*$.getJSON($WEB_ROOT_PATH+'/dhccApi/admin/admin/findCityDict',
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