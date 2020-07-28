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
							     elem: '#test5'
							    ,type: 'datetime'
							  });
							laydate.render({
							    elem: '#test6'
							   ,type: 'datetime'
							  });
							form.on('submit(search)', function(data){
							    var formData = data.field;
					            console.debug(formData);
					            //数据表格重载
					            table.reload('analysis',{
					            	page : {curr:1}
									,cellMinWidth: 80
									,height:tableHeight
					                 ,url: $WEB_ROOT_PATH+'/dhccApi/hospitalizationAnalysis/hospitalizationAnalysis/hospitalizationAnalysis' //后台做模糊搜索接口路径 
					                , where:{ 
					                	 'zyts':formData.stayLength,
					                	 'num':formData.num,
					                	 'admissionDate':formData.indate,
					                	 'dischargeDate':formData.outdate,},      
					            });
					        });
							table.render({
										elem :'#analysis'
										,cellMinWidth: 80
										,height:tableHeight
										,url : $WEB_ROOT_PATH+'/dhccApi/hospitalizationAnalysis/hospitalizationAnalysis/hospitalizationAnalysis'  //数据接口
										,title : '分解住院情况筛查分析表'	
									    ,cols: [[
										 {field:'billingNo', align:'center',title: '收费单据号', width:200}
										,{field:'orgCode', align:'center',title: '医疗机构编码', width:200}
										,{field:'name',align: 'center', width:200,title: '参保人'}
										,{field:'sex',align: 'center', width:200,title: '性别'}
										,{field:'age',align: 'center', title: '年龄', width:200}
										,{field:'outDiagnosisNo',align: 'center', title: '出院诊断编码',width:200}
										,{field:'outDiagnosisName',align: 'center', title: '出院诊断名称',width:200}
										,{field:'inDiagnosisNo',align: 'center', title: '入院诊断编码',width:200}
										,{field:'inDiagnosisName',align: 'center', title: '入院诊断名称',width:200}
										,{field:'claimCost',align: 'center', title: '医保报销费用' ,width:200}
										,{field:'inhosDate', align:'center',title: '入院日期', width:200}
										,{field:'outhosDate',align: 'center', width:200,title: '出院日期'}							
										,{field:'balanceDate',align: 'center', width:200,title: '结算日期'}
										,{field:'admissionNo',align: 'center', width:200,title: '住院号'}
										,{field:'sscno',align: 'center', title: '患者社保卡号', width:200}
										,{field:'medicalType',align: 'center', title: '险种类型', width:200}
										,{field:'attendingDocCode',align: 'center', title: '主诊医师编码',width:200}
										,{field:'attendingDocTitle',align: 'center', title: '主诊医师姓名' ,width:200}									
										]],
										page : true //开启分页
									});
						})