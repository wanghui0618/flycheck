var cols = [ [ {
	type : 'numbers',
	title : '序号'
}, {
	title : '操作',
	toolbar : '#mingxi',
	width : '100'
}, {
	field : 'hisid',
	align : 'center',
	title : '明细表关联Id',
	hide : true
}, {
	field : 'hospitalId',
	title : '医疗机构编码',
	width : '8%'
}, {
	field : 'hospitalName',
	title : '医疗机构名称',
	width : '15%'
}, {
	field : 'zyh',
	title : '住院号',
	width : '15%'
}, {
	field : 'admissionDeptName',
	title : '就诊科室',
	width : '10%'
}, {
	field : 'patientName',
	title : '患者姓名',
	width : '10%'
}, {
	field : 'admissionDate',
	title : '入院日期',
	width : '12%'
}, {
	field : 'dischargeDate',
	title : '出院日期',
	width : '12%'
}, {
	field : 'zyts',
	title : '住院天数',
	width : '9%'
}, {
	field : 'totalAmount',
	title : '医疗总费用',
	width : '12%'
}, {
	field : 'benefitType',
	title : '险种类型',
	width : '10%'
}, {
	field : 'drugfee',
	title : '药品费',
	width : '10%'
},{
	field : 'jcfzb',
	title : '检查费占比(%)',
	width : '10%'
},{
	field : 'westernMedicineFee',
	title : '西药费',
	width : '10%'
}, {
	field : 'chineseMedicineYinpian',
	title : '中药饮片费',
	width : '11%'
}, {
	field : 'chineseMedicineForm',
	title : '中成药费',
	width : '10%'
}, {
	field : 'accommodationFee',
	title : '床位费',
	width : '10%'
}, {
	field : 'diagnosisFee',
	title : '诊察费',
	width : '10%'
}, {
	field : 'inspectionFee',
	title : '检查费',
	width : '10%'
}, {
	field : 'testFee',
	title : '化验费',
	width : '10%'
}, {
	field : 'treatmentFee',
	title : '治疗费',
	width : '10%'
}, {
	field : 'nursingFee',
	title : '护理费',
	width : '10%'
}, {
	field : 'materialFee',
	title : '卫生材料费',
	width : '10%'
}, {
	field : 'consultationFee',
	title : '一般诊疗费',
	width : '10%'
}, {
	field : 'registrationFee',
	title : '挂号费',
	width : '10%'
}, {
	field : 'otherFee',
	title : '其他费',
	width : '10%'
} ] ]
// 初始化
layui
		.config({
			base : $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' // 静态资源所在路径
		})
		.extend({
			index : 'lib/index' // 主入口模块
		})
		.use(
				[ 'index', 'table', 'element', 'laydate' ],
				function() {
					var $ = layui.$, form = layui.form, table = layui.table, laydate = layui.laydate
					table
							.render({
								elem : '#physicalExaminationAdmission',
								page : true,
								height : tableHeight - 62,
								url : $WEB_ROOT_PATH
										+ '/dhccApi/physicalExaminationAdmission/physicalExaminationAdmission/physicalExamination',
								cols : cols,
								done : function(res, curr, count) {
									$("#bingli").val(/*"总病例数：" +*/ count);
								},
								where : {
									code : '<',
									code1 : '>',
									baifenhao : '%',
									jianchafei : '80',
									Sumdrugs : '200',
								}
							});
					// 病例数汇总
					$
							.ajax({
								url : $WEB_ROOT_PATH
										+ '/dhccApi/physicalExaminationAdmission/physicalExaminationAdmission/sumTotalCount',
								type : 'POST',
								dataType : 'json',
								data : {
									code : '<',
									code1 : '>',
									baifenhao : '%',
									jianchafei : '80',
									Sumdrugs : '200',
								},
								success : function(data) {
									if (data.data[0].num == null) {
										data.data[0].num = 0;
									}
									$("#jine").val(
											/*"涉及病例总金额：" +*/ data.data[0].num);
								}
							})
					// 明细汇总
					$
							.ajax({
								url : $WEB_ROOT_PATH
										+ '/dhccApi/physicalExaminationAdmission/physicalExaminationAdmission/countphysicalExaminationMx',
								dataType : 'json',
								data : {
									code : '<',
									code1 : '>',
									baifenhao : '%',
									jianchafei : '80',
									Sumdrugs : '200',
								},
								success : function(data) {
									console.log(data.data[0])
									if (data.data[0].totalMx == null) {
										data.data[0].totalMx = 0;
									}
									$("#bingliMX").val(
											/*"涉及明细数量：" +*/ data.data[0].totalMx);
									$("#jineMX").val(
											/*"涉及明细金额：" +*/ data.data[0].moneyMx);
								}
							});
					// 导出
					form
							.on(
									'submit(LAY-user-front-export)',
									function(data) {
										var field = data.field;
										var param=encodeURI(JSON.stringify(field));
										// 执行重载
										window
												.open($WEB_ROOT_PATH
														+ '/dhccApi/physicalExaminationAdmission/physicalExaminationAdmission/exportExcelToSelf?param='+param);
									});
					// 监听搜索sumTotalCount
					form
							.on(
									'submit(physicalExamination)',
									function(data) {
										var field = data.field;
										// 执行重载
										table
												.render({
													elem : '#physicalExaminationAdmission',
													url : $WEB_ROOT_PATH
															+ '/dhccApi/physicalExaminationAdmission/physicalExaminationAdmission/physicalExamination',
													height : tableHeight,
													page : true,
													cols : cols,
													where : {
														hospitalId : field.hospitalId,
														hospitalName : field.hospitalName,
														billDate : field.billDate,
														code : field.code,
														code1 : field.code1,
														Sumdrugs : field.Sumdrugs,
														jianchafei : field.jianchafei,
														admissionDate : field.admissionDate,
														dischargeDate : field.dischargeDate,
														admissionDiseaseId : field.admissionDiseaseId,
														admissionDiseaseName : field.admissionDiseaseName,
														baifenhao : field.baifenhao,
													},
													done : function(res, curr,
															count) {
														$("#bingli")
																.val(
																		/*"总病例数："
																				+*/ count);
														num(data);
														// 明细汇总
														$
																.ajax({
																	url : $WEB_ROOT_PATH
																			+ '/dhccApi/physicalExaminationAdmission/physicalExaminationAdmission/countphysicalExaminationMx',
																	dataType : 'json',
																	data : {
																		hospitalId : field.hospitalId,
																		hospitalName : field.hospitalName,
																		billDate : field.billDate,
																		code : field.code,
																		code1 : field.code1,
																		Sumdrugs : field.Sumdrugs,
																		jianchafei : field.jianchafei,
																		admissionDate : field.admissionDate,
																		dischargeDate : field.dischargeDate,
																		admissionDiseaseId : field.admissionDiseaseId,
																		admissionDiseaseName : field.admissionDiseaseName,
																		baifenhao : field.baifenhao,
																	},
																	success : function(
																			data) {
																		console
																				.log(data.data[0])
																		if (data.data[0].totalMx == null) {
																			data.data[0].totalMx = 0;
																		}
																		$(
																				"#bingliMX")
																				.val(
																						/*"涉及明细数量："
																								+*/ data.data[0].totalMx);
																		$(
																				"#jineMX")
																				.val(
																						/*"涉及明细金额："
																								+*/ data.data[0].moneyMx);
																	}
																});
													}
												});
									});
					function num(data) {
						var field = data.field;
						$
								.ajax({
									url : $WEB_ROOT_PATH
											+ '/dhccApi/physicalExaminationAdmission/physicalExaminationAdmission/sumTotalCount',
									type : 'POST',
									dataType : 'json',
									data : {
										hospitalId : field.hospitalId,
										hospitalName : field.hospitalName,
										billDate : field.billDate,
										code : field.code,
										code1 : field.code1,
										Sumdrugs : field.Sumdrugs,
										jianchafei : field.jianchafei,
										admissionDate : field.admissionDate,
										dischargeDate : field.dischargeDate,
										admissionDiseaseId : field.admissionDiseaseId,
										admissionDiseaseName : field.admissionDiseaseName,
										baifenhao : field.baifenhao,
									},
									success : function(data) {
										if (data.data[0].num == null) {
											data.data[0].num = 0;
										}
										$("#jine").val(
												/*"涉及病例总金额：" +*/ data.data[0].num);
									}
								})
					}

					// 重置事件
					$("#resets")
							.click(
									function(data) {
										$("input").val("");
										$("#baifenhao").val("%");
										$("#Sumdrugs").val("200");
										$("#jianchafei").val("80");
										$("#code").val("<");
										form.render('select', 'code');
										$("#code1").val(">");
										form.render('select', 'code1');
										form.render("select");
										// 执行重载
										table
												.render({
													elem : '#physicalExaminationAdmission',
													url : $WEB_ROOT_PATH
															+ '/dhccApi/physicalExaminationAdmission/physicalExaminationAdmission/physicalExamination',
													height : tableHeight,
													page : true,
													cols : cols,
													done : function(res, curr,
															count) {
														$("#bingli")
																.val(
																		/*"总病例数："
																				+*/ count);

													},
													where : {
														code : '<',
														code1 : '>',
														baifenhao : '%',
														jianchafei : '80',
														Sumdrugs : '200',
													}
												});
										$
												.ajax({
													url : $WEB_ROOT_PATH
															+ '/dhccApi/physicalExaminationAdmission/physicalExaminationAdmission/sumTotalCount',
													type : 'POST',
													dataType : 'json',
													data : {
														code : '<',
														code1 : '>',
														baifenhao : '%',
														jianchafei : '80',
														Sumdrugs : '200',
													},
													success : function(data) {
														console
																.log(data.data[0].num);
														if (data.data[0].num == null) {
															data.data[0].num = 0;
														}
														$("#jine")
																.val(
																		/*"涉及病例总金额："
																				+*/ data.data[0].num);
													}
												});
										// 明细汇总
										$
												.ajax({
													url : $WEB_ROOT_PATH
															+ '/dhccApi/physicalExaminationAdmission/physicalExaminationAdmission/countphysicalExaminationMx',
													dataType : 'json',
													data : {
														code : '<',
														code1 : '>',
														baifenhao : '%',
														jianchafei : '80',
														Sumdrugs : '200',
													},
													success : function(
															data) {
														console
																.log(data.data[0])
														if (data.data[0].totalMx == null) {
															data.data[0].totalMx = 0;
														}
														$(
																"#bingliMX")
																.val(
																		/*"涉及明细数量："
																				+*/ data.data[0].totalMx);
														$(
																"#jineMX")
																.val(
																		/*"涉及明细金额："
																				+*/ data.data[0].moneyMx);
													}
												});

									})

					// 结算明细窗口
					table
							.on(
									'tool(physicalExaminationAdmission)',
									function(obj) {
										console.log(obj)
										if (obj.event === 'mingxi') {
											var data = {
												'hisid' : obj.data.hisid,
											};
											var formIndex = layer
													.open({
														type : 2,
														area : [ '1100px',
																'500px' ],
														content : $WEB_ROOT_PATH
																+ '/physicalExaminationAdmission/mingXiPage',// 这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content:
														// ['http://sentsin.com',
														// 'no']
														success : function(
																layero, index) {
															var body = layer
																	.getChildFrame(
																			'body',
																			index);
															var iframeWin = window[layero
																	.find('iframe')[0]['name']]; // 得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
															iframeWin
																	.initData(data);
														}
													});
											return false;
										}
									});
					// 结算日期
					laydate.render({
						elem : '#billDate',
						range : true,
						format : 'yyyyMMdd'
					});
					// 入院日期
					laydate.render({
						elem : '#admissionDate',
						range : true,
						format : 'yyyyMMdd'
					});
					// 出院日期
					laydate.render({
						elem : '#dischargeDate',
						range : true,
						format : 'yyyyMMdd'
					});
					$('.layui-btn.layuiadmin-btn-useradmin').on('click',
							function() {
								var type = $(this).data('type');
								active[type] ? active[type].call(this) : '';
							});
				});

// 下拉上收按钮代码块
$(".cxtjtop").hide();
$("#shangla").hide();