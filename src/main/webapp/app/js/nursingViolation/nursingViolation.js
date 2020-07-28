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
										 {field:'patientId', align:'center',title: '个人编码', width:200}
										,{field:'patientName',align: 'center', title: '患者姓名',width:200}
										,{field:'socialCardId', align:'center',title: '患者社保卡号', width:200}
										,{field:'medicaRecordId',align: 'center', width:200,title: '病案号'}
										,{field:'patientGender',align: 'center', width:200,title: '性别'}
										,{field:'patientAge',align: 'center', title: '年龄', width:200}
										,{field:'patientBirthday',align: 'center', title: '患者出生日期', width:200}
										,{field:'benefitType',align: 'center', title: '险种类型',width:200}
										,{field:'hospitalId',align: 'center', title: '医疗机构编码',width:200}
										,{field:'hospitalName',align: 'center', title: '医疗机构名称',width:200}
										,{field:'itemName',align: 'center', title: '护理等级' ,width:200}
										,{field:'admissionDate', align:'center',title: '入院日期', width:200}
										,{field:'dischargeDate',align: 'center', width:200,title: '出院日期'}	
										,{field:'zyts', align:'center',title: '住院天数', width:200}
										]],
										page : true //开启分页
									});
						})