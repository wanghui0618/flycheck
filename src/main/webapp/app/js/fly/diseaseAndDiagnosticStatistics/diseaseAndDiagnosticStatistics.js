//初始Hua
layui
		.config({
			base : $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' // 静态资源所在路径
		})
		.extend({
			index : 'lib/index' // 主入口模块
		})
		.use(
				[ 'index', 'table', 'element' ],
				function() {
					var $ = layui.$, form = layui.form, table = layui.table
					table
							.render({
								elem : '#admissionDiseaseName',
								cellMinWidth : 80,
								page : true,
								height : tableHeight,
								url : $WEB_ROOT_PATH
										+ '/dhccApi/statisticsOfDiagnosisAndTreatmentItems/statisticsOfDiagnosisAndTreatmentItems/getlist',
								cols : [ [ {
									field : 'hisid',
									title : 'ID',
									sort : true,
									hide : true
								}, {
									type : 'numbers',
									width : 40,
									title : '编号',
									width : '15%'
								}, {
									field : 'inDiagnosisName',
									title : '诊断名称',
									width : '85%'
								} ] ],
							});
					table
							.render({
								elem : '#diseaseAndDiagnosticStatistics',
								url : $WEB_ROOT_PATH
										+ '/dhccApi/statisticsOfDiagnosisAndTreatmentItems/statisticsOfDiagnosisAndTreatmentItems/getlistByinhosDiag',
								cellMinWidth : 80,
								height : tableHeight,
								page : true,
								cols : [ [ {
									field : 'hisid',
									title : '序号',
									sort : true,
									hide : true
								}, {
									field : 'paymentDate',
									title : '就诊时间',
									width : '24%'
								}, {
									field : 'orgName',
									title : '就诊机构',
									width : '26%'
								}, {
									field : 'name',
									title : '参保人姓名',
									width : '25%'
								}, {
									field : 'age',
									title : '参保人年龄',
									width : '25%'
								} ] ]
							});
					
					table.on('row(admissionDiseaseName)', function(obj) {
						$("tr").css("background-color", "");
						$(this).css("background-color", "#EEF6FF");
						var data = obj.data;
						var inDiagnosisName = data.inDiagnosisName;
						initRightTable(inDiagnosisName);
					});
					
					function initRightTable(param, year, month) {
						table
								.render({
									elem : '#diseaseAndDiagnosticStatistics',
									url : $WEB_ROOT_PATH
											+ '/dhccApi/statisticsOfDiagnosisAndTreatmentItems/statisticsOfDiagnosisAndTreatmentItems/getlistByinhosDiag',
									cellMinWidth : 80,
									height : tableHeight,
									page : true,
									cols : [ [ {
										field : 'hisid',
										title : '序号',
										sort : true,
										hide : true
									}, {
										field : 'paymentDate',
										title : '就诊时间',
										width : '24%'
									}, {
										field : 'orgName',
										title : '就诊机构',
										width : '26%'
									}, {
										field : 'name',
										title : '参保人姓名',
										width : '25%'
									}, {
										field : 'age',
										title : '参保人年龄',
										width : '25%'
									} ] ],
									where : {
										inDiagnosisName : param,
									}

								});
					}
					function initleftTable(param) {
						table
								.render({
									elem : '#admissionDiseaseName',
									url : $WEB_ROOT_PATH
											+ '/dhccApi/statisticsOfDiagnosisAndTreatmentItems/statisticsOfDiagnosisAndTreatmentItems/getlist',
									height : tableHeight,
									page : true,
									cols : [ [ {
										field : 'hisid',
										title : 'ID',
										sort : true,
										hide : true
									}, {
										type : 'numbers',
										width : 40,
										title : '编号',
										width : '15%'
									}, {
										field : 'inDiagnosisName',
										title : '诊断名称',
										width : '85%'
									} ] ],
									where : {
										inDiagnosisName : param,
									}
								});
					}

					// 监听搜索
					form
							.on(
									'submit(year)',
									function(data) {
										var field = data.field;
										console.log(field)
										// 执行重载
										table
												.render({
													elem : '#diseaseAndDiagnosticStatistics',
													url : $WEB_ROOT_PATH
															+ '/dhccApi/statisticsOfDiagnosisAndTreatmentItems/statisticsOfDiagnosisAndTreatmentItems/getlistByinhosDiag',
													height : tableHeight,
													page : true,
													cols : [ [ {
														field : 'hisid',
														title : 'ID',
														sort : true,
														hide : true
													}, {
														field : 'paymentDate',
														title : '就诊时间',
														width : '24%'
													}, {
														field : 'orgName',
														title : '就诊机构',
														width : '26%'
													}, {
														field : 'name',
														title : '参保人姓名',
														width : '25%'
													}, {
														field : 'age',
														title : '参保人年龄',
														width : '25%'
													} ] ],
													where : {
														year : field.year,
														month : field.month,
													}

												});
									});

					// 诊断名称
					form.on('submit(zhenduanName)', function(data) {
						var field = data.field;
						var inDiagnosisName = field.inDiagnosisName
						console.log(data)
						// 执行重载
						initleftTable(inDiagnosisName);
					});

					layui.use('laydate', function() {
						var laydate = layui.laydate;
						// 年范围
						laydate.render({
							elem : '#year',
							type : 'year'
						});
					});
					layui.use('laydate', function() {
						var laydate = layui.laydate;
						// 月范围
						laydate.render({
							elem : '#month',
							type : 'month'
						});
					});

					$('.layui-btn.layuiadmin-btn-useradmin').on('click',
							function() {
								var type = $(this).data('type');
								active[type] ? active[type].call(this) : '';
							});
				});