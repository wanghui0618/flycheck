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
										 {field:'patientId', align:'center',title: '个人编码', width:200}
										,{field:'patientName',align: 'center', title: '患者姓名',width:200}
										,{field:'socialCardId', align:'center',title: '患者社保卡号', width:200}
										,{field:'medicaRecordId',align: 'center', width:200,title: '病案号'}
										,{field:'patientGender',align: 'center', width:200,title: '性别'}
										,{field:'patientAge',align: 'center', title: '年龄', width:200}
										,{field:'patientBirthday',align: 'center', title: '患者出生日期', width:200}
										,{field:'totalAmount',align: 'center', title: '医疗总费用',width:200}
										,{field:'bmiPayAmount',align: 'center', title: '基本统筹支付',width:200}
										,{field:'dbbx',align: 'center', title: '大病保险',width:200}
										,{field:'yljz',align: 'center', title: '医疗救助' ,width:200}
										,{field:'gwybz', align:'center',title: '公务员医疗补助', width:200}
										,{field:'bmiConveredAmount',align: 'center', width:200,title: '符合基本医疗保险的费用'}	
										,{field:'accommodationFee', align:'center',title: '床位费', width:200}
										,{field:'diagnosisFee',align: 'center', title: '诊察费' ,width:200}
										,{field:'inspectionFee', align:'center',title: '检查费', width:200}
										,{field:'testFee',align: 'center', width:200,title: '化验费'}	
										,{field:'treatmentFee', align:'center',title: '治疗费', width:200}
										,{field:'nursingFee',align: 'center', title: '护理费' ,width:200}
										,{field:'materialFee', align:'center',title: '卫生材料费', width:200}
										,{field:'westernMedicineFee',align: 'center', width:200,title: '西药费'}	
										,{field:'chineseMedicineYinpian', align:'center',title: '中药饮片费', width:200}
										,{field:'chineseMedicineForm',align: 'center', title: '中成药费' ,width:200}
										,{field:'consultationFee', align:'center',title: '一般诊疗费', width:200}
										,{field:'registrationFee',align: 'center', width:200,title: '挂号费'}	
										,{field:'otherFee', align:'center',title: '其他费', width:200}
										
										,{field:'ybPayType', align:'center',title: '医保支付方式', width:200}
										,{field:'drgsCode',align: 'center', width:200,title: '病组\病种编码'}	
										,{field:'DRGS_NAME', align:'center',title: '病组\病种名称', width:200}
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