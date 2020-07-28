//初始化
var cols = [ [ {type : 'numbers',title : '编号',}, 
	{field : 'diagType',title : '就诊类型',width : '7%',
	templet:function(a){
		if (a.diagType=="1") return "住院"
		else if(a.diagType=="2") return "门诊"
		else if(a.diagType=="3") return "门诊大病"
		else if(a.diagType=="9") return "其他"
	}},
	{field : 'orgName',title : '就诊机构',width : '10%'},{field : 'name',title : '姓名',width : '6%'},
	{field : 'idcard',title : '身份证号',width : '12%'},
	{field : 'sex',title : '性别',width : '5%',
		templet:function(a)
		{if (a.sex=="1") return "男"
		else if(a.sex=="2") return "女"
	}},
	{field : 'age',title : '年龄',width : '5%'},
	{field : 'departName',title : '科室',width : '8%'},
	{field : 'condition',title : '病情',width : '10%'},{field : 'balanceDate',title : '结算日期',width : '10%'},
	{field : 'inhosDate',title : '入院日期',width : '10%'},{field : 'outhosDate',title : '出院日期',width : '10%'},
	{field : 'stayLength',title : '住院天数',width : '7%'},
	{field : 'medicalType',title : '险种类型',width : '7%',
	templet:function(a){
		if (a.medicalType=="301") return "职工医疗"
		else if(a.medicalType=="305") return "居民医疗"
		else if(a.medicalType=="309") return "新农合"
		else if(a.medicalType=="401") return "生育险种"
		else if(a.medicalType=="501") return "工伤险种"
	}},
	{field : 'admissionNo',title : '住院号',width : '15%'},{field : 'sscno',title : '社保卡号',width : '12%'},
	{field : 'admissionType',title : '住院类型',width : '7%'},{field : 'dischargeState',title : '出院状态',width : '7%'},
	{field : 'totalCost',title : '总金额',width : '10%'}
	]]
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
										+ '/dhccApi/statisticsOfDiagnosisAndTreatmentItems/statisticsOfDiagnosisAndTreatmentItems/getCount',
								cols : cols,
							});

					// 监听搜索
					form.on('submit(getCount)', function(data) {
						var field = data.field;
						console.log(field)
						// 执行重载
						table.render({
							elem : '#emptyHangingBedAnalysis',
							url : $WEB_ROOT_PATH+'/dhccApi/statisticsOfDiagnosisAndTreatmentItems/statisticsOfDiagnosisAndTreatmentItems/getCount',
							cellMinWidth : 80,
							height : tableHeight,
							page : true,
							cols : cols,
							where : {
								name : field.name,
								type : field.type,
							}
						});
					});
					$('.layui-btn.layuiadmin-btn-useradmin').on('click',
							function() {
								var type = $(this).data('type');
								active[type] ? active[type].call(this) : '';
							});
				});