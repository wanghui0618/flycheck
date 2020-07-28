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
	    
	    
  /*      //加载城市下拉字典
        $.getJSON($WEB_ROOT_PATH+'/dhccApi/handle/handle/findOrgNameDict',
            function(data){
                for(var i=0 ;i<data.length;i++){
                    var mm="<option value='"+data[i].value+"'>"+data[i].text+"</option>";
                    $("#handdingInsCode").append(mm);
                }
                form.render('select');
           });*/
	    
      /*  //加载医疗机构下拉字典
        $.getJSON($WEB_ROOT_PATH+'/dhccApi/admin/admin/medicalNameDict',
            function(data){
                for(var i=0 ;i<data.length;i++){
                    var mm="<option value='"+data[i].value+"'>"+data[i].text+"</option>";
                    $("#orgName").append(mm);
                }
                form.render('select');
           });*/
	   
	    
	    table.render({
	    	elem: '#userTable'
	            ,url: $WEB_ROOT_PATH+'/dhccApi/medical/medical/listupload'
	            ,cellMinWidth: 100
	             ,height: document.documentElement.clientHeight-65
	            ,where: {  ilegalChild: '1'  }
	            ,cols: [[
	            	   {type: 'numbers', title: '序号' }
	            	   ,{field:'left', title:'操作', align:'center', toolbar: '#table-useradmin-webuser', width:230,hide:rowOperate(['medicalUplpad-medical-viewInfo','medicalUplpad-medical-edit','medicalUplpad-medical-del'])}
	            	   ,{field:'id', title: 'ID',  align:'center',sort: true, hide:true}
	            	   ,{field:'handdingInsName', align:'center', width:220,title:'统筹区' }
	            	   /*,{field:'handdingInsCode', align:'center', width:220,title:'统筹区' }*/
			              ,{field:'orgName', align:'center', width:180,title:'医疗机构' }
			              ,{field:'name',  align:'center',width:120, title:'姓名'}
			              ,{field:'sex',  align:'center',width:120, title: '性别' }
			              ,{field:'age',  align:'center',width:120, title:'年龄' }
			              ,{field:'idcard',  align:'center',width:180, title:'身份证号' }
			              ,{field:'diagType',  align:'center',width:120, title:'就诊类型' }
			              ,{field:'crowdType', width:140, align:'center',title:'人群类别' }
			              /*,{field:'insurePersonType', width:140, align:'center',title:'参保人员类别' }*/
			              ,{field:'sscno',  align:'center', width:160,title: '社保卡号' }
			              ,{field:'billingNo', align:'center',width:200, title:'收费单据号'}
			              ,{field:'paymentDate', align:'center',width:120, title:'费用发生日期'}
			              ,{field:'reversalMark', align:'center',width:115, title:'冲销标志'}
			              ,{field:'admissionNo',  align:'center',width:200, title:'住院号' }
			              ,{field:'admissionType',  align:'center', width:90,title: '住院类型' }
			              ,{field:'medicalType',  align:'center', width:90,title: '险种类型' }
			              ,{field:'condition',  align:'center', width:180,title: '病情' }
			              ,{field:'departName',  align:'center',width:120, title:'科室' }
			              ,{field:'inhosDate',  align:'center',width:120,title:'入院日期' }
			              ,{field:'outhosDate', align:'center', width:120,title:'出院日期' }
			              ,{field:'totalCost',  align:'center',title:'总金额' }
			              ,{field:'fundCost', align:'center',width:140,  title:'基金支付金额' }
			              ,{field:'selfCost',  align:'center',width:140, title:'个人负担金额' }
			              ,{field:'basicCostM',  align:'center',width:150, title:'基本统筹应支付' }
			              ,{field:'povertyAlleviationSubsidy',  align:'center', width:90,title:'扶贫补助' }
			              ,{field:'financeSubsidy',  align:'center',title:'财政补助' }
			              ,{field:'officialSubsidy',   align:'center',width:115,title:'公务员补助' }
			              ,{field:'treatmentType',  align:'center', width:155,title:'待遇享受类别' }
			              ,{field:'medicalTreatmentState',  align:'center', width:115,title:'医疗待遇状态' }
			              ,{field:'dischargeState',  align:'center',width:120, title:'出院状态' }
			              ,{field:'treatmentWay', align:'center', width:120, title:'就诊方式' }
			              ,{field:'stayLength',  align:'center',width:120, title:'住院天数' }
			              ,{field:'rangeCost', align:'center', width:180, title:'本次纳入报销范围金额' }
			              ,{field:'basicCostR', align:'center',width:160, title:'基本统筹实际支付' }
			              ,{field:'selfPayAmount', align:'center', width:160,title:'个人自付金额' }
			              ,{field:'selfExpenditureAmount', align:'center',width:150, title:'个人自费金额' }
			              ,{field:'sscAccountCost', align:'center', width:150,title:'个人账户自付' }
			              ,{field:'cashCost',  align:'center',width:150,title:'现金支付金额' }
			              ,{field:'largeCostM', align:'center',width:150, title:'大额应支付' }
			              ,{field:'largeCostR', width:150,title:'大额实支付' }
			              ,{field:'civilAffairSubsidy',  align:'center',title:'民政救助' }
			              ,{field:'fullOrdination',  align:'center',title:'全额统筹' }
			              ,{field:'partialOrdination',  align:'center',title:'部分统筹' }
			              ,{field:'partialPayment',  align:'center',title:'部分自付' }
			              ,{field:'fullPayment',  align:'center',title:'全额自付' }
			              ,{field:'reimbursementType', align:'center', title:'报销类型' }
			              ,{field:'hospCount',  align:'center',title:'住院次数' }
			              ,{field:'insuranceMark', align:'center', title:'险种标志' }
			              ,{field:'inDiagnosisNo', align:'center', width:160, title:'入院诊断编码' }
			              ,{field:'inDiagnosisName',  align:'center',width:180, title:'入院诊断名称' }
			              ,{field:'outDiagnosisNo', align:'center', width:160, title:'出院诊断编码' }
			              ,{field:'outDiagnosisName', align:'center', width:180, title:'出院诊断名称' }
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
			      		  }
		            ,page: true
        		});
	    hideButtonStatic();//按钮权限
		    		
	    
	    //监听搜索
		form.on('submit(LAY-org-front-search)', function(data){
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
	    		          ,content: $WEB_ROOT_PATH+'/medicalUpload/medicalform'
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
	    		              if(field["medical.diagType"]=="住院"){
	    		            	  field["medical.diagType"]="1";
	    		              }
	    		              
	    		              if(field["medical.diagType"]=="门诊"){
	    		            	  field["medical.diagType"]="2";
	    		              }
	    		              
	    		              if(field["medical.diagType"]=="门特"){
	    		            	  field["medical.diagType"]="3";
	    		              }
	    		              
	    		              if(field["medical.diagType"]=="其他"){
	    		            	  field["medical.diagType"]="9";
	    		              }
	    		             
	    		             // console.log(field);
	       
	    	  //提交 Ajax后台 
              var url=$WEB_ROOT_PATH+"/dhccApi/medical/medical/save";
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
	},
	            upload:function () {
					layer.open({
						type: 2
						,title: '文件上传'
						,content: $WEB_ROOT_PATH+'/medicalUpload/medicalUpload'
						,maxmin: true
						,area: ['500px', '400px']
						,btn: ['确定', '取消']
						,yes: function(index, layero){
							var iframeWindow = window['layui-layer-iframe'+ index]
								,submitID = 'LAY-user-front-submit'
								,submit = layero.find('iframe').contents().find('#'+ submitID);

							//监听提交
							iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
								var field = data.field; //获取提交的字段
								// console.log(field);

								//提交 Ajax后台
								var url=$WEB_ROOT_PATH+"";
								$.post(url,field,function(result){
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
	      if(obj.event === 'del'){
	    	//删除
	        layer.confirm('确定删除该条记录吗？', function(index){
	        	//执行 Ajax 后重载
	            var url=$WEB_ROOT_PATH+"/dhccApi/medical/medical/delete";
	            $.post(url,{"medical.id":data.id},function(result){
	        	    table.reload('userTable');
	                layer.msg('已删除');
	    	    });
	        });
	      } else if(obj.event === 'edit'){
	    	//修改方法
		    layer.open({
		          type: 2
		          ,title: '修改就诊信息'
		          ,content: $WEB_ROOT_PATH+'/medicalUpload/medicalform'
		          ,maxmin: true
		          ,area: ['1100px', '450px']
		          ,btn: ['确定', '取消']
		          ,success: function(layero, index){
		        	  var iframeWindow = window['layui-layer-iframe'+ index];
		        	  //向此iframe层方法 传递参数
		        	  iframeWindow.child(JSON.stringify(data));
		          }
		          ,yes: function(index, layero){
		            var iframeWindow = window['layui-layer-iframe'+ index]
		            ,submitID = 'LAY-user-front-submit'
		            ,submit = layero.find('iframe').contents().find('#'+ submitID);
	
		            //监听提交
		            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
		              var field = data.field; //获取提交的字段
		              
		              if(field["medical.diagType"]=="住院"){
		            	  field["medical.diagType"]="1";
		              }
		              
		              if(field["medical.diagType"]=="门诊"){
		            	  field["medical.diagType"]="2";
		              }
		              
		              if(field["medical.diagType"]=="门特"){
		            	  field["medical.diagType"]="3";
		              }
		              
		              if(field["medical.diagType"]=="其他"){
		            	  field["medical.diagType"]="9";
		              }
		              

		              //提交 Ajax后台 
		              var url=$WEB_ROOT_PATH+"/dhccApi/medical/medical/save";
		              $.post(url,field,function(result){
		            	  var inFlag= result.inFlag; 
		            	  if(inFlag==0){
		            		  layer.msg('修改成功!');
			            	  //后台成功后，静态更新表格中的数据
			                  table.reload('userTable'); //数据刷新
			                  layer.close(index); //关闭弹层
		            	  }
					  });
		            });  
		            submit.trigger('click');
		          }
		        }); 
	      }else if(obj.event === 'viewInfo'){
	    	    //明细
	    	  layer.open({
		          type: 2
		          ,title: '就诊明细-当前就诊患者：<span style="color: red;">'+ data.name +'</span>'
		          ,content: $WEB_ROOT_PATH+'/medical/medicalDetailInfo'
		          ,maxmin: true
		          ,area: ['92%', '550px']
	    	      ,success: function(layero, index){
	        	  var iframeWindow = window['layui-layer-iframe'+ index];
	        	  //向此iframe层方法 传递参数
	        	  iframeWindow.child(JSON.stringify(data));
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