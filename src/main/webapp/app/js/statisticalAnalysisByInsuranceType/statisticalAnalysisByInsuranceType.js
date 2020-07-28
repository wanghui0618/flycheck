//初始化
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
								elem : '#statisticalAnalysisByInsuranceType',
								page : true,
								height : tableHeight,
								url : $WEB_ROOT_PATH
										+ '/dhccApi/diseaseAndDiagnosticStatistics/diseaseAndDiagnosticStatistics/statisticalAnalysisByInsuranceType',
								cols : [ [ {
									type : 'numbers',
									title : '编号',
								}, {
									field : 'benefitGroupId',
									title : '人员类型',
									width : '7%'
								}, {
									field : 'bmiPayAmount',
									title : '基本统筹支付',
									width : '9%'
								}, {
									field : 'accommodationFee',
									title : '床位费',
									width : '6%'
								}, {
									field : 'diagnosisFee',
									title : '诊察费',
									width : '6%'
								}, {
									field : 'inspectionFee',
									title : '检查费',
									width : '6%'
								}, {
									field : 'testFee',
									title : '化验费',
									width : '6%'
								}, {
									field : 'treatmentFee',
									title : '治疗费',
									width : '6%'
								}, {
									field : 'nursingFee',
									title : '护理费',
									width : '6%'
								}, {
									field : 'materialFee',
									title : '卫生材料费',
									width : '7%'
								}, {
									field : 'westernMedicineFee',
									title : '西药费',
									width : '6%'
								}, {
									field : 'chineseMedicineYinpian',
									title : '中药饮片费',
									width : '7%'
								}, {
									field : 'chineseMedicineForm',
									title : '中成药费',
									width : '7%'
								}, {
									field : 'consultationFee',
									title : '一般诊疗费',
									width : '7%'
								}, {
									field : 'registrationFee',
									title : '挂号费',
									width : '6%'
								}, {
									field : 'otherFee',
									title : '其他费',
									width : '5.8%'
								} ] ],
							});

					// 监听搜索
					form
							.on(
									'submit()',
									function(data) {
										var field = data.field;
										console.log(field)
										// 执行重载
										table
												.render({
													elem : '#statisticalAnalysisByInsuranceType',
													url : $WEB_ROOT_PATH
															+ '/dhccApi/diseaseAndDiagnosticStatistics/diseaseAndDiagnosticStatistics/statisticalAnalysisByInsuranceType',
													height : tableHeight,
													page : true,
													cols : [ [

													] ],
													where : {}
												});
									});
					$('.layui-btn.layuiadmin-btn-useradmin').on('click',
							function() {
								var type = $(this).data('type');
								active[type] ? active[type].call(this) : '';
							});
				});