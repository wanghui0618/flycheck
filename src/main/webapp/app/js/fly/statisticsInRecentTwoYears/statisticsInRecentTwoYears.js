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
								elem : '#AA',
								page : true,
								height : tableHeight,
								url : $WEB_ROOT_PATH
										+ '/dhccApi/statisticsOfDiagnosisAndTreatmentItems/statisticsOfDiagnosisAndTreatmentItems/dosage',
								cols : [ [ {
									type : 'numbers',
									title : '编号'
								}, {
									field : 'itemName',
									title : '项目名称'
								}, {
									field : 'itemNum',
									title : '项目数量'
								}, {
									field : 'itemPrice',
									title : '项目单价'
								}, {
									field : 'itemCost',
									title : '项目总金额'
								}, ] ]
							});

					// 监听搜索
					form
							.on(
									'submit(sel)',
									function(data) {
										var field = data.field;
										console.log(field)
										// 执行重载
										table
												.render({
													elem : '#AA',
													url : $WEB_ROOT_PATH
															+ '/dhccApi/statisticsOfDiagnosisAndTreatmentItems/statisticsOfDiagnosisAndTreatmentItems/dosage',
													height : tableHeight,
													page : true,
													cols : [ [ {
														type : 'numbers',
														title : '编号'
													}, {
														field : 'itemName',
														title : '项目名称'
													}, {
														field : 'itemNum',
														title : '项目数量'
													}, {
														field : 'itemPrice',
														title : '项目单价'
													}, {
														field : 'itemCost',
														title : '项目总金额'
													}, ] ],
													where : {
														type : field.type,
														year : field.year
													}
												});
									});

					layui.use('laydate', function() {
						var laydate = layui.laydate;
						// 年范围
						laydate.render({
							elem : '#year',
							type : 'year',
							min : -730,
							max : 0
						});
					});
					$('.layui-btn.layuiadmin-btn-useradmin').on('click',
							function() {
								var type = $(this).data('type');
								active[type] ? active[type].call(this) : '';
							});
				});