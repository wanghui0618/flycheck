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
										,url : $WEB_ROOT_PATH+'/dhccApi/hospitalizationAnalysis/hospitalizationAnalysis/list'  //数据接口
										,title : '分解住院情况筛查分析表'	
									    ,cols: [[
										 {field:'medicalRecordId', align:'center',title: '病案编号', width:200}
										,{field:'hospitalName', align:'center',title: '机构名称', width:200}
										,{field:'patientName',align: 'center', width:200,title: '参保人'}
										,{field:'patientGender',align: 'center', width:200,title: '性别'}
										,{field:'patientAge',align: 'center', title: '年龄', width:200}
										,{field:'patientBirthday',align: 'center', title: '患者出生日期', width:200}
										,{field:'dischargeDiseaseIdMain',align: 'center', title: '出院诊断编码',width:200}
										,{field:'dischargeDiseaseNameMain',align: 'center', title: '出院诊断名称',width:200}
										,{field:'admissionDiseaseId',align: 'center', title: '入院诊断编码',width:200}
										,{field:'admissionDiseaseName',align: 'center', title: '入院诊断名称',width:200}
										,{field:'bmiConveredAmount',align: 'center', title: '医保报销费用' ,width:200}
										,{field:'preAdmissionDate', align:'center',title: '入院日期', width:200}
										,{field:'dischargeDate',align: 'center', width:200,title: '出院日期'}							
										,{field:'hospitalId', align:'center',title: '医疗机构编码', width:200}
										,{field:'billDate',align: 'center', width:200,title: '结算日期'}
										,{field:'zyh',align: 'center', width:200,title: '住院号'}
										,{field:'socialCardId',align: 'center', title: '患者社保卡号', width:200}
										,{field:'benefitType',align: 'center', title: '险种类型', width:200}
										,{field:'benefitGroupId',align: 'center', title: '人员类型', width:200}
										,{field:'admissionDeptName',align: 'center', title: '入院科别', width:200}
										,{field:'transferDeptName',align: 'center', title: '转科科别',width:200}
										,{field:'dischargeDeptName	',align: 'center', title: '出院科别',width:200}
										,{field:'patientCompany',align: 'center', title: '患者所在单位' ,width:200}
										,{field:'patientAddress', align:'center',title: '患者现住址', width:200}
										,{field:'nbType',align: 'center', width:200,title: '新生儿入院类型'}							
										,{field:'nbBirthWeight', align:'center',title: '新生儿出生体重', width:200}
										,{field:'nbInpatientWeight',align: 'center', width:200,title: '新生儿入院体重'}
										,{field:'claimType',align: 'center', width:200,title: '住院医疗类型'}
										,{field:'ifLocalFlag',align: 'center', title: '异地标志'}
										,{field:'dischargeStatus',align: 'center', title: '离院方式'}
										,{field:'doctorId',align: 'center', title: '主诊医师编码',width:200}
										,{field:'doctorName',align: 'center', title: '主诊医师姓名' ,width:200}		
										,{field:'preAdmissionDate',align: 'center', title: '上一次出院日期',width:200}
										,{field:'daysReAdmission31	',align: 'center', title: '是否有31天内再住院计划',width:200}
										,{field:'totalAmount',align: 'center', title: '医疗总费用' ,width:200}
										,{field:'bmiPayAmount', align:'center',title: '基本统筹支付', width:200}
										,{field:'dbbx',align: 'center', width:200,title: '大病保险'}							
										,{field:'yljz', align:'center',title: '医疗救助', width:200}
										,{field:'gwybz',align: 'center', width:200,title: '公务员医疗补助'}
										,{field:'debc',align: 'center', width:200,title: '大额补充'}
										,{field:'qybc',align: 'center', title: '企业补充', width:200}
										,{field:'cash',align: 'center', title: '个人现金支付', width:200}
										,{field:'selfPayAmount',align: 'center', title: '个人账户支付',width:200}
										,{field:'selfPayIn',align: 'center', title: '个人自负' ,width:200}
										,{field:'accommodationFee',align: 'center', title: '床位费',width:200}
										,{field:'diagnosisFee	',align: 'center', title: '诊察费',width:200}
										,{field:'inspectionFee',align: 'center', title: '检查费' ,width:200}
										,{field:'testFee', align:'center',title: '化验费', width:200}
										,{field:'treatmentFee',align: 'center', width:200,title: '治疗费'}							
										,{field:'nursingFee', align:'center',title: '护理费', width:200}
										,{field:'materialFee',align: 'center', width:200,title: '卫生材料费'}
										,{field:'westernMedicineFee',align: 'center', width:200,title: '西药费'}
										,{field:'chineseMedicineYinpian',align: 'center', title: '中药饮片费', width:200}
										,{field:'chineseMedicineForm',align: 'center', title: '中成药费', width:200}
										,{field:'consultationFee',align: 'center', title: '一般诊疗费',width:200}
										,{field:'registrationFee',align: 'center', title: '挂号费' ,width:200}
										,{field:'otherFee',align: 'center', title: '其他费', width:200}
										,{field:'ybPayType',align: 'center', title: '医保支付方式', width:200}
										,{field:'drgsCode',align: 'center', title: '病组\病种编码',width:200}
										,{field:'drgsName',align: 'center', title: '病组\病种名称' ,width:200}
										]],
										page : true //开启分页
									});
						})