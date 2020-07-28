//初始化
layui.config({ base : $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' // 静态资源所在路径
		}).extend({
			index : 'lib/index' // 主入口模块
		}).use([ 'index', 'table', 'element', 'laydate', 'form', 'upload','layer' ],
				function() {
					var width = $(window).width();
					var height = $(window).height();
					// $('#laycode').css('width',width-100);
					// $('#laycode').css('height',height-200);
					// 这里调用是初始化进来的时候，防止表格没有数据而加的
					search();
					var laydate = layui.laydate;
					var table = layui.table;
					var form = layui.form;				

					laydate.render({
						elem : '#admissiondate',
						format : 'yyyyMMdd',
						range : true // 中间以‘/’分开
						,
						max : 0
					// 7天后
					});
					
					laydate.render({
						elem : '#dischargedate',
						format : 'yyyyMMdd',
						range : true // 中间以‘/’分开
						,
						max : 0
					// 7天后
					});
					
				});
// 搜索方法
function search() {

	var hospitalCode = $("input[name='hospitalCode']").val();// 医院编码
	var hospitalName = $("input[name='hospitalName']").val();// 医院名称
	var admissiondate = $("input[name='admissiondate']").val();// 入院日期
	var dischargedate = $("input[name='dischargedate']").val();// 出院日期
 

	var formDate = {		
		hospitalCode : hospitalCode,
		hospitalName : hospitalName,
		admissiondate : admissiondate,
		dischargedate : dischargedate,
	};
	
	console.log(formDate);
	Detaildg(formDate);
	totle(formDate);
	
};
// 重置按钮
function reset() {

	$('#getOrgName').combogrid("setValue", "");// 医疗机构
	$("input[name='admissiondate']").val("");// 入院日期
	$("input[name='dischargedate']").val("");// 出院日期

}

// 给汇总按钮赋值
function totle(formDate) {
	$
			.ajax({
				url : $WEB_ROOT_PATH
						+ '/dhccApi/abnormalanesthesiarest/getTotalNumberOfCasesAndTotalAmount',
				type : "POST",
				data : formDate,
				dataType : "json",
				async : true,
				success : function(result) {
					$("#total_number_of_cases").html(
							result[0].TOTALNUMBEROFCASES);
					$("#total_amount").html(result[0].SUMTOTALAMOUNT);
				},
			});
}
// 具体病例信息数据表格渲染方法
function Detaildg(formDate) {
	var table = layui.table;
	var index = layer.load(0); // 添加laoding,0-2两种方式
	table
			.render({
				elem : '#dg',
				loading : true,
				url : $WEB_ROOT_PATH
						+ '/dhccApi/abnormalanesthesiarest/getAbnormalAnesthesia',
				cellMinWidth : 80,
				loading : true // 翻页加loading
				,
				height : tableHeight - 55,
				cols : [ [
					{ type : 'numbers',fixed : 'left',title : "序号",fixed : 'left',align : 'right'
					}, {title : '详情',width : 100,align : 'center',toolbar : '#barDetails'
					} ,{field : 'HISID',title : '结算单据号',align : 'center',width : 200
					}, {field : 'HOSPITAL_ID',title : '医院编号',align : 'center',width : 200
					},{field : 'HOSPITAL_NAME',title : '医院名称',align : 'center',width : 165
					},{field : 'P_LEVEL',title : '级别',align : 'center',width : 100
					},{field : 'BMI_AREA_ID',title : '医保区编号',align : 'center',width : 100
					},{field : 'BMI_AREA_NAME',title : '医保区',align : 'center',width : 100
					},{field : 'BILL_DATE',title : '结算时间',	align : 'right',width : 170,templet : function(d) {return layui.util.toDateString(d.BILL_DATE);}
					},{field : 'YEAR',title : '年',align : 'center',width : 100
					}, {field : 'MONTH',title : '月',align : 'center',width : 100
					},{field : 'ZYH',title : '住院号',align : 'center',width : 165
					}, {field : 'PATIENT_ID',title : '患者ID',align : 'center',width : 200}
					,{field:'SOCIAL_CARD_ID',title:'社保卡号',align:'center',width:165}
					,{field:'MEDICAL_RECORD_ID',title:'医疗记录号',align:'center',width:165}
					,{field:'BENEFIT_TYPE',title:'医保类型',align:'center',width:165}
					,{field:'DISCHARGE_DEPT_NAME',title:'出院科室',align:'center',width:165}
					,{field:'PATIENT_NAME',title:'患者姓名',align:'center',width:165}
					,{field:'PATIENT_GENDER',title:'性别',align:'center',width:80}
					,{field:'PATIENT_BIRTHDAY',title:'出生日期',align:'center',width:165,templet : function(d) {	return layui.util.toDateString(d.PATIENT_BIRTHDAY);} }
					,{field:'PATIENT_AGE',title:'年龄',align:'center',width:80}
					,{field:'PATIENT_COMPANY',title:'工作地点',align:'center',width:300}
					,{field:'CLAIM_TYPE',title:'CLAIM_TYPE',align:'center',width:165}
					,{field:'IF_LOCAL_FLAG',title:'是否异地',align:'center',width:165}
					,{field:'ADMISSION_DATE',title:'入院时间',align:'center',width:165,templet : function(d) {	return layui.util.toDateString(d.ADMISSION_DATE);} }
					,{field:'DISCHARGE_DATE',title:'出院时间',align:'center',width:165,templet : function(d) {	return layui.util.toDateString(d.ADMISSION_DATE);} }
					,{field:'ZYTS',title:'住院天数',align:'center',width:100}
					,{field:'TOTAL_AMOUNT',title:'总费用',align:'center',width:165}
					,{field:'BMI_PAY_AMOUNT',title:'医保费用',align:'center',width:165}
					,{field:'ADMISSION_DISEASE_ID',title:'入院诊断编号',align:'center',width:165}
					,{field:'ADMISSION_DISEASE_NAME',title:'入院诊断',align:'center',width:165}
					,{field:'DISCHARGE_DISEASE_ID_MAIN',title:'出院主诊断编号',align:'center',width:165}
					,{field:'DISCHARGE_DISEASE_NAME_MAIN',title:'出院主诊断',align:'center',width:165}									
					] ],
				page : true,
				where : formDate,
				done : function(res) {// 返回数据执行回调函数
					layer.close(index); // 返回数据关闭loading
				}

			});
	// 监听工具条
	table.on('tool(dg)',function(obj) { // 注：tool 是工具条事件名，test 是 table 原始容器的属性
									// lay-filter="对应的值"
						var data = obj.data; // 获得当前行数据
						var layEvent = obj.event; // 获得 lay-event 对应的值（也可以是表头的
													// event 参数对应的值）
						var tr = obj.tr; // 获得当前行 tr 的 DOM 对象（如果有的话）

						if (layEvent === 'detail') { // 查看
							var form = { hisid : obj.data.HISID };
							layer.open({
										type : 2,
										area : [ '1100px', '550px' ],
										title : '病例详情',
										content : $WEB_ROOT_PATH + '/abnormalanesthesia/abnormalanesthesiaDetails',// 这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content:
																													// ['http://sentsin.com',
																													// 'no']
										success : function(layero, index) {
											// var body =
											// layer.getChildFrame('body',
											// index);
											var iframeWin = window[layero
													.find('iframe')[0]['name']]; // 得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
											iframeWin.initData(form);
										}
									});
							return false;
						}
					});

}
function View_details(formDate) {
	var table = layui.table;
	var index = layer.load(0); // 添加laoding,0-2两种方式
	table.render({
				elem : '#dg',
				loading : true,
				url : $WEB_ROOT_PATH
						+ '/dhccApi/abnormalanesthesiarest/getAbnormalAnesthesia',
				cellMinWidth : 80,
				loading : true // 翻页加loading
				,
				height : tableHeight - 55,
				cols : [ [
					{ type : 'numbers',fixed : 'left',title : "序号",fixed : 'left',align : 'right'
					}, {title : '详情',width : 100,align : 'center',toolbar : '#barDetails'
					} ,{field : 'HISID',title : '结算单据号',align : 'center',width : 200
					}, {field : 'HOSPITAL_ID',title : '医院编号',align : 'center',width : 200
					},{field : 'HOSPITAL_NAME',title : '医院名称',align : 'center',width : 165
					},{field : 'P_LEVEL',title : '级别',align : 'center',width : 100
					},{field : 'BMI_AREA_ID',title : '医保区编号',align : 'center',width : 100
					},{field : 'BMI_AREA_NAME',title : '医保区',align : 'center',width : 100
					},{field : 'BILL_DATE',title : '结算时间',	align : 'right',width : 170,templet : function(d) {return layui.util.toDateString(d.BILL_DATE);}
					},{field : 'YEAR',title : '年',align : 'center',width : 100
					}, {field : 'MONTH',title : '月',align : 'center',width : 100
					},{field : 'ZYH',title : '住院号',align : 'center',width : 165
					}, {field : 'PATIENT_ID',title : '患者ID',align : 'center',width : 200}
					,{field:'SOCIAL_CARD_ID',title:'社保卡号',align:'center',width:165}
					,{field:'MEDICAL_RECORD_ID',title:'医疗记录号',align:'center',width:165}
					,{field:'BENEFIT_TYPE',title:'医保类型',align:'center',width:165}
					,{field:'DISCHARGE_DEPT_NAME',title:'出院科室',align:'center',width:165}
					,{field:'PATIENT_NAME',title:'患者姓名',align:'center',width:165}
					,{field:'PATIENT_GENDER',title:'性别',align:'center',width:80}
					,{field:'PATIENT_BIRTHDAY',title:'出生日期',align:'center',width:165,templet : function(d) {	return layui.util.toDateString(d.PATIENT_BIRTHDAY);} }
					,{field:'PATIENT_AGE',title:'年龄',align:'center',width:80}
					,{field:'PATIENT_COMPANY',title:'工作地点',align:'center',width:300}
					,{field:'CLAIM_TYPE',title:'CLAIM_TYPE',align:'center',width:165}
					,{field:'IF_LOCAL_FLAG',title:'是否异地',align:'center',width:165}
					,{field:'ADMISSION_DATE',title:'入院时间',align:'center',width:165,templet : function(d) {	return layui.util.toDateString(d.ADMISSION_DATE);} }
					,{field:'DISCHARGE_DATE',title:'出院时间',align:'center',width:165,templet : function(d) {	return layui.util.toDateString(d.ADMISSION_DATE);} }
					,{field:'ZYTS',title:'住院天数',align:'center',width:100}
					,{field:'TOTAL_AMOUNT',title:'总费用',align:'center',width:165}
					,{field:'BMI_PAY_AMOUNT',title:'医保费用',align:'center',width:165}
					,{field:'ADMISSION_DISEASE_ID',title:'入院诊断编号',align:'center',width:165}
					,{field:'ADMISSION_DISEASE_NAME',title:'入院诊断',align:'center',width:165}
					,{field:'DISCHARGE_DISEASE_ID_MAIN',title:'出院主诊断编号',align:'center',width:165}
					,{field:'DISCHARGE_DISEASE_NAME_MAIN',title:'出院主诊断',align:'center',width:165}									
					] ],
				page : true,
				where : formDate,
				done : function(res) {// 返回数据执行回调函数
					layer.close(index); // 返回数据关闭loading
				}
			});
	// 监听工具条
	table.on('tool(dg)',function(obj) { // 注：tool 是工具条事件名，test 是 table 原始容器的属性
									// lay-filter="对应的值"
						var data = obj.data; // 获得当前行数据
						var layEvent = obj.event; // 获得 lay-event 对应的值（也可以是表头的
													// event 参数对应的值）
						var tr = obj.tr; // 获得当前行 tr 的 DOM 对象（如果有的话）

						if (layEvent === 'detail') { // 查看
							var form = {hisid : obj.data.HISID };
							layer.open({ type : 2,
										area : [ '1100px', '550px' ],
										title : '病例详情',
										content : $WEB_ROOT_PATH + '/abnormalanesthesia/abnormalanesthesiaDetails',// 这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content:
																													// ['http://sentsin.com',
																													// 'no']
										success : function(layero, index) {
											// var body =
											// layer.getChildFrame('body',
											// index);
											var iframeWin = window[layero.find('iframe')[0]['name']]; // 得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
											iframeWin.initData(form);}
									});
							return false;
						}
					});
}



// 文档下载
function downLoadXsl(fileName) {
	var form = $("<form>");
	form.attr('style', 'display:none');
	form.attr('target', '');
	form.attr('method', 'post');
	form.attr('action', $WEB_ROOT_PATH
			+ '/dhccApi/abnormalanesthesiarest/downLoadFile');
	var inputConfirmId = $('<input>');
	inputConfirmId.attr('type', 'hidden');
	inputConfirmId.attr('name', 'fileName');
	inputConfirmId.attr('value', fileName);
	$('body').append(form);
	form.append(inputConfirmId);
	form.submit();
	form.remove();
}
//function exportD(sid) {
//	var formDate = {
//		social_card_id : sid
//	};
//	layer.load(2, {
//		shade : [ 0.4, '#000' ]
//	});
//	$.ajax({
//		url : $WEB_ROOT_PATH + "/dhccApi/sametimeHospitalrestrest/expDate",
//		type : "POST",
//		data : formDate,
//		dataType : "json",
//		async : true,
//		success : function(result) {
//			layer.closeAll('loading');
//			if (result.operateSuccess) {
//				var fileName = result.fileName;
//				downLoadXsl(fileName);
//			} else {
//				layer.alert("下载异常");
//			}
//		},
//		error : function() {
//			layer.closeAll('loading');
//			layer.msg("数据导出失败，请联系管理员！");
//		}
//	});
//	return false;
//}
// 批量导出
$("#export").click(
		function() {

			var hospitalCode = $("input[name='hospitalCode']").val();// 医院编码
			var hospitalName = $("input[name='hospitalName']").val();// 医院名称
			var admissiondate = $("input[name='admissiondate']").val();// 入院日期
			var dischargedate = $("input[name='dischargedate']").val();// 出院日期
			
			var formDate = {};
			var sid = null;
			if (sid != null && sid != "") {
				formDate = {
					social_card_id : sid
				};
			} else {
				formDate = {

					hospitalCode : hospitalCode,
					hospitalName : hospitalName,
					admissiondate : admissiondate,
					dischargedate : dischargedate,

				};
			}
			layer.load(2, {
				shade : [ 0.4, '#000' ]
			});
			$.ajax({
				url : $WEB_ROOT_PATH
						+ "/dhccApi/abnormalanesthesiarest/expDate",
				type : "POST",
				data : formDate,
				dataType : "json",
				async : true,
				success : function(result) {
					layer.closeAll('loading');
					if (result.operateSuccess) {
						var fileName = result.fileName;
						downLoadXsl(fileName);
					} else {
						layer.alert("下载异常");
					}
				},
				error : function() {
					layer.closeAll('loading');
					layer.msg("数据导出失败，请联系管理员！");
				}
			});
			return false;
		});
