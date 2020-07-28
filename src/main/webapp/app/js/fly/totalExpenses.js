layui.config({
	    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(
						[ 'laydate', 'laypage', 'layer', 'table', 'carousel',
								'upload', 'element', 'slider','form' ],
						function() {
							var laydate = layui.laydate //日期
							, table = layui.table //表格
							, form = layui.form;
							
							laydate.render({
							    elem: '#test1'
							  });
							
							form.on('submit(search)', function(data){
								
							    var formData = data.field;
					            console.debug(formData);
					            //数据表格重载
					            table.reload('TotalExpenses',{
					            	page : {curr:1}
									,cellMinWidth: 80
									,height:tableHeight
					                 ,url: $WEB_ROOT_PATH+'/dhccApi/totalExpenses/totalExpenses/totalExpenses' 
					                , where:{ 
					                	 'billdate':formData.billdate,
					                	 'week':'',
					                	 'month':'',
					                	 'year':'',
					                },      
					            });
					        });

							table.render({
										elem :'#TotalExpenses'
										,cellMinWidth: 80
										,height:tableHeight
										,url : $WEB_ROOT_PATH+'/dhccApi/totalExpenses/totalExpenses/totalExpenses'  //数据接口
										,title : '按时间点统计分析'	
									    ,cols: [[
										 {field:'billingNo', align:'center',title: '收费单据号', width:200}
										,{field:'paymentDate',align: 'center', title: '费用发生日期',width:200}
										,{field:'idcard', align:'center',title: '身份证号', width:200}
										,{field:'sscno',align: 'center', width:200,title: '社保卡号'}
										,{field:'name',align: 'center', width:200,title: '姓名'}
										,{field:'sex',align: 'center', title: '性别', width:200}
										,{field:'totalCost',align: 'center', title: '总金额', width:200}
										,{field:'fundCost',align: 'center', title: '基金支付金额',width:200}
										,{field:'selfCost',align: 'center', title: '个人负担金额',width:200}
										,{field:'basicCostM',align: 'center', title: '基本统筹应支付',width:200}
										,{field:'povertyAlleviationSubsidy',align: 'center', title: '扶贫补助' ,width:200}
										,{field:'financeSubsidy', align:'center',title: '财政补助', width:200}
										,{field:'officialSubsidy',align: 'center', width:200,title: '公务员补助'}	
										,{field:'treatmentType', align:'center',title: '待遇享受类别', width:200}
										,{field:'basicCostR',align: 'center', title: '基本统筹实际支付' ,width:200}
										,{field:'selfPayAmount', align:'center',title: '个人自付金额', width:200}
										,{field:'selfExpenditureAmount',align: 'center', width:200,title: '个人自费金额'}	
										,{field:'sscAccountCost', align:'center',title: '个人账户自付', width:200}
										,{field:'cashCost',align: 'center', title: '现金支付金额' ,width:200}
										,{field:'largeCostM', align:'center',title: '大额应支付', width:200}
										,{field:'largeCostR',align: 'center', width:200,title: '大额实支付'}	
										,{field:'civilAffairSubsidy', align:'center',title: '民政补助', width:200}
										,{field:'fullOrdination',align: 'center', title: '全额统筹' ,width:200}
										,{field:'partialOrdination', align:'center',title: '部分统筹', width:200}
										,{field:'partialPayment',align: 'center', width:200,title: '部分自付'}	
										,{field:'fullPayment', align:'center',title: '全额自付', width:200}
										,{field:'reimbursementType', align:'center',title: '报销类型', width:200}
										,{field:'withholdingAmount',align: 'center', width:200,title: '扣款金额'}	
										,{field:'withholdingQuantity', align:'center',title: '扣款数量', width:200}
										,{field:'deductions', align:'center',title: '扣款原因', width:200}
										]],
										page : true //开启分页
									});
						})
						
						   function week(){
                        	 var week="week";
                            	  layui.table.reload('TotalExpenses',{
  					            	page : {curr:1}
  									,cellMinWidth: 80
  									,height:tableHeight
  					                 ,url: $WEB_ROOT_PATH+'/dhccApi/totalExpenses/totalExpenses/totalExpenses' 
  					                , where:{ 
  					                	'billdate':'',
					                	 'week':week,
					                	 'month':'',
					                	 'year':'',
  					                },      
  					            });
                              };
                            function month(){
                             	 var month="month";
                                 	  layui.table.reload('TotalExpenses',{
       					            	page : {curr:1}
       									,cellMinWidth: 80
       									,height:tableHeight
       					                 ,url: $WEB_ROOT_PATH+'/dhccApi/totalExpenses/totalExpenses/totalExpenses' 
       					                , where:{ 
       					                	'billdate':'',
   					                	    'week':'',
   					                	    'year':'',
       					                	'month':month,
       					                },      
       					            });
                                   };
                             function year(){
                                   	 var year="year";
                                       	  layui.table.reload('TotalExpenses',{
             					            	page : {curr:1}
             									,cellMinWidth: 80
             									,height:tableHeight
             					                 ,url: $WEB_ROOT_PATH+'/dhccApi/totalExpenses/totalExpenses/totalExpenses' 
             					                , where:{ 
             					                 'billdate':'',
           					                	 'week':'',
           					                	 'month':'',
             					                 'year':year,
             					                },      
             					            });
                                         };