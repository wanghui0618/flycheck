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
							
							form.on('submit(search)', function(data){
								
							    var formData = data.field;
					            console.debug(formData);
					            //数据表格重载
					            table.reload('NursingViolation',{
					            	page : {curr:1}
									,cellMinWidth: 80
									,height:tableHeight
					                 ,url: $WEB_ROOT_PATH+'/dhccApi/nursingViolation/nursingViolation/nursingViolation' 
					                , where:{ 
					                	 'limitgrade':formData.limitgrade,
					                },      
					            });
					        });
							table.render({
										elem :'#NursingViolation'
										,cellMinWidth: 80
										,height:tableHeight
										,url : $WEB_ROOT_PATH+'/dhccApi/nursingViolation/nursingViolation/nursingViolation'  //数据接口
										,title : '统计护理违规'	
									    ,cols: [[
										 {field:'billingNo', align:'center',title: '收费单据号', width:200}
										,{field:'admissionNo',align: 'center', title: '住院号',width:200}
										,{field:'idcard', align:'center',title: '身份证号', width:200}
										,{field:'name',align: 'center', width:200,title: '姓名'}
										,{field:'sex',align: 'center', width:200,title: '性别'}
										,{field:'admissionType',align: 'center', title: '住院类型', width:200}
										,{field:'medicalType',align: 'center', title: '险种类型', width:200}
										,{field:'inhosDate',align: 'center', title: '入院日期',width:200}
										,{field:'outhosDate',align: 'center', title: '出院日期',width:200}
										,{field:'age',align: 'center', title: '年龄',width:200}
										,{field:'stayLength',align: 'center', title: '住院天数' ,width:200}
										,{field:'hospCount', align:'center',title: '住院次数', width:200}
										,{field:'inDiagnosisNo',align: 'center', width:200,title: '入院诊断编码'}	
										,{field:'inDiagnosisName', align:'center',title: '入院诊断名称', width:200}
										,{field:'outDiagnosisNo',align: 'center', title: '出院诊断编码' ,width:200}
										,{field:'outDiagnosisName', align:'center',title: '出院诊断名称', width:200}
										,{field:'diagType',align: 'center', width:200,title: '就诊类型'}	
										,{field:'visitingCardNumber', align:'center',title: '就诊卡号', width:200}
										,{field:'itemName',align: 'center', title: '项目名称' ,width:200}
										,{field:'itemCode', align:'center',title: '项目编号', width:200}
										,{field:'itemStandard',align: 'center', width:200,title: '项目规格'}	
										
										]],
										page : true //开启分页
									});
						})