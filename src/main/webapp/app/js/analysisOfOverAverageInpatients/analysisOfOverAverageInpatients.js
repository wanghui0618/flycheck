//初始化
var cols = [ [ {type : 'numbers',title : '编号',}, {field : 'hisid',title : '单据号',width : '20%'},
	{field : 'zyh',title : '住院号',width : '5%'},{field : 'patientName',title : '姓名',width : '7%'},
	{field : 'admissionDiseaseName',title : '诊断类型',width : '6%'},{field : 'hospitalName',title : '医疗机构名称',width : '10%'},
	{field : 'admissionDate',title : '入院日期',width : '7%'},{field : 'dischargeDate',title : '出院日期',width : '7%'},
	{field : 'pCategory',title : '费用类别',width : '6%'}, {field : 'usageDate',title : '项目使用日期',width : '8%'}, 
	{field : 'usageDateFlag',title : '项目使用日期标识',width : '10%'}, {field : 'billDate',title : '结算日期',width : '7%'},
	{field : 'year',title : '收费年份',width : '6%'}, {field : 'month',title : '收费月份',width : '6%'},
	{field : 'itemIdHosp',title : '医院项目编码',width : '8%'}, {field : 'itemNameHosp',title : '医院项目名称',width : '8%'},
	{field : 'itemId',title : '医保项目编码',width : '8%'}, {field : 'itemName',title : '医保项目名称',width : '8%'},
	{field : 'drugSpec',title : '规格',width : '5%'}, {field : 'unitPrice',title : '单价',width : '5%'}, 
	{field : 'num',title : '数量',width : '5%'}, {field : 'cost',title : '金额',width : '5%'}, 
	{field : 'bmiConveredAmount',title : '医保范围内金额',width : '9%'}, {field : 'bmiPayAmount',title : '医保实际支付金额',width : '10%'}, 
	{field : 'pType',title : '支付类别',width : '6%'}, {field : 'pTypePct',title : '报销比例',width : '6%'} ] ]
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
								elem : '#emptyHangingBedAnalysis',
								page : true,
								height : tableHeight,
								url : $WEB_ROOT_PATH
										+ '/dhccApi/emptyHangingBedAnalysis/emptyHangingBedAnalysis/getCount',
								cols : cols,
							});

					// 监听搜索
					form.on('submit(getCount)', function(data) {
						var field = data.field;
						console.log(field)
						// 执行重载
						table.render({
							elem : '#emptyHangingBedAnalysis',
							url : $WEB_ROOT_PATH+'/dhccApi/emptyHangingBedAnalysis/emptyHangingBedAnalysis/getCount',
							cellMinWidth : 80,
							height : tableHeight,
							page : true,
							cols : cols,
							where : {
								patientName : field.patientName,
								admissionDiseaseName : field.admissionDiseaseName,
							}
						});
					});
					$('.layui-btn.layuiadmin-btn-useradmin').on('click',
							function() {
								var type = $(this).data('type');
								active[type] ? active[type].call(this) : '';
							});
				});