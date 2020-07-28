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
										+ '/dhccApi/statisticsOfDiagnosisAndTreatmentItems/statisticsOfDiagnosisAndTreatmentItems/statisticalAnalysisByInsuranceType',
								cols : [ [ {
									type : 'numbers',
									title : '编号',
								}, {
									field : 'insurePersonType',
									title : '参保人员类别',
								}, {
									field : 'basicCostM',
									title : '基本统筹应支付',
								}, {
									field : 'basicCostR',
									title : '基本统筹实际支付',
								}, {
									field : 'povertyAlleviationSubsidy',
									title : '扶贫补助',
								}, {
									field : 'financeSubsidy',
									title : '财政补助',
								}, {
									field : 'officialSubsidy',
									title : '公务员补助',
								} ] ],
							});
					$('.layui-btn.layuiadmin-btn-useradmin').on('click',
							function() {
								var type = $(this).data('type');
								active[type] ? active[type].call(this) : '';
							});
				});