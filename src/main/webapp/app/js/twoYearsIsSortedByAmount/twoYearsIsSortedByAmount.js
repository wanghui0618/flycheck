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
								elem : '#twoYearsIsSortedByAmount',
								page : true,
								height : tableHeight,
								url : $WEB_ROOT_PATH
										+ '/dhccApi/emptyHangingBedAnalysis/emptyHangingBedAnalysis/towYears',
								cols : [ [ {
									type : 'numbers',
									title : '编号'
								}, {
									field : 'hospitalName',
									title : '医院名称'
								}, {
									field : 'itemId',
									title : '药品编码'
								}, {
									field : 'itemName',
									title : '药品名称'
								}, {
									field : 'num',
									title : '药品数量'
								}, {
									field : 'unitPrice',
									title : '药品单价'
								}, {
									field : 'cost',
									title : '药品总金额'
								}, ] ]
							});

					// 监听搜索
					form
							.on(
									'submit(towYears)',
									function(data) {
										var field = data.field;
										console.log(field)
										// 执行重载
										table
												.render({
													elem : '#twoYearsIsSortedByAmount',
													url : $WEB_ROOT_PATH
															+ '/dhccApi/emptyHangingBedAnalysis/emptyHangingBedAnalysis/towYears',
													height : tableHeight,
													page : true,
													cols : [ [ {
														type : 'numbers',
														title : '编号'
													}, {
														field : 'hospitalName',
														title : '医院名称'
													}, {
														field : 'itemId',
														title : '药品编码'
													}, {
														field : 'itemName',
														title : '药品名称'
													}, {
														field : 'num',
														title : '药品数量'
													}, {
														field : 'unitPrice',
														title : '药品单价'
													}, {
														field : 'cost',
														title : '药品总金额'
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
							min:-730,
							max:0
						});
					});
					$('.layui-btn.layuiadmin-btn-useradmin').on('click',
							function() {
								var type = $(this).data('type');
								active[type] ? active[type].call(this) : '';
							});
				});