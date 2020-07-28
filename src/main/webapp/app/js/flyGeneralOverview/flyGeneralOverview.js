var cols = [ [ {
	type : 'numbers',
	title : '编号'
}, {
	field : 'hospitalId',
	title : '医疗机构编码',
	width : '10%'
}, {
	field : 'hospitalName',
	title : '医疗机构名称',
	width : '15%'
}, {
	field : 'visitingRoute',
	title : '就诊途径',
	width : '10%'
}, {
	field : 'benefitType',
	title : '人员类型',
	width : '10%'
}, {
	field : 'year',
	title : '年度',
	width : '7%'
}, {
	field : 'peopleNumber',
	title : '住院人数',
	width : '8%'
}, {
	field : 'peopleNumber2',
	title : '住院人次',
	width : '8%'
}, {
	field : 'medicalTotal',
	title : '总费用',
	width : '7%'
}, {
	field : 'medicalBmi',
	title : '报销费用',
	width : '8%'
}, {
	field : 'avgCost',
	title : '平均费用',
	width : '8%'
}, {
	field : 'avgDay',
	title : '平均住院天数',
	width : '10%'
}/*, {
	field : 'drugShare',
	title : '药占比',
	width : '7%'
} */] ]
// 初始化
layui.config({
			base : $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' // 静态资源所在路径
		}).extend({
			index : 'lib/index' // 主入口模块
		}).use([ 'index', 'table', 'element', 'laydate' ],function() {
					var $ = layui.$
					,form = layui.form
					,table = layui.table
					,laydate = layui.laydate;
					
					//日期范围
					
			/*		laydate.render({ 
						  elem: '#finaTime'
							  ,trigger:'click'
						  ,type: 'year'
						  ,range: '~' //或 range: '~' 来自定义分割字符
						});
				    laydate.render({
				    	elem: '#finaTime'
				    		,trigger:'click'
				    			,format:'yyyy'
				    				,range: true
				    });
				    
				    //日期范围
				    laydate.render({
				      elem: '#test-laydate-range-date'
				      ,range: true
				    });*/
					
				/*	$.getJSON($WEB_ROOT_PATH+'/dhccApi/flyGeneralOverview/flyGeneralOverview/findHospitalName',
							function(data){
					     		var  dataList= data.flyGeneralOverviewVos;
					     		var hospital_name=JSON.stringify(dataList);//解析为字符串
					     		//localStorage.clear();
					     		localStorage.setItem('hospital_name',hospital_name);//存入浏览器数据库
					     		var mm="<option></option>";
				     			$("#hospitalName").append(mm);
					     		for(var i=0 ;i<dataList.length;i++){
					     			var mm="<option value='"+dataList[i].hospitalName+"'>"+dataList[i].hospitalName+"</option>";
					     			$("#hospitalName").append(mm); 
					     		}
					     	form.render('select');
				});
					$.getJSON($WEB_ROOT_PATH+'/dhccApi/flyGeneralOverview/flyGeneralOverview/findHospitalId', 
							function(data){
					     		var  dataList= data.flyGeneralOverviewVos;
					     		var hospital_id=JSON.stringify(dataList);//解析为字符串
					     		//localStorage.clear();
					     		localStorage.setItem('hospital_name',hospital_id);//存入浏览器数据库
					     		var mm="<option></option>";
				     			$("#hospitalId").append(mm);
					     		for(var i=0 ;i<dataList.length;i++){
					     			var mm="<option value='"+dataList[i].hospitalId+"'>"+dataList[i].hospitalId+"</option>";
					     			$("#hospitalId").append(mm); 
					     		}
					     	form.render('select');
				});*/
					table.render({
								elem : '#flyCheck',
								page : true,
								limit: 3,
								height : 220,
								url : $WEB_ROOT_PATH
										+ '/dhccApi/flyGeneralOverview/flyGeneralOverview/flyCheckfind',
								cols : cols,
								done: function(res, curr, count){
									hosTop("住院","2018","","");
						        }
							});
//行点击跳转至医院详情统计
    table.on('row(flyCheck)', function(obj){
    	var hosName=obj.data.hospitalName;
    	window.location.href= $WEB_ROOT_PATH
            + '/hospitalInformationStatistics/hospitalInformationStatistics?hospitalName='+hosName
	});

					// 监听搜索
					form.on('submit(flyCheckFind)',function(data) {
										var field = data.field;
										if (field.visitingRoute=="门诊") {
											layui.$("#hopIn").text("门诊");
										}else {
                                            layui.$("#hopIn").text("住院");
										}
										hosTop(field.visitingRoute,field.year,field.hospitalId,field.hospitalName);
										// 执行重载
										table.render({
													elem : '#flyCheck',
													url : $WEB_ROOT_PATH
															+ '/dhccApi/flyGeneralOverview/flyGeneralOverview/flyCheckfind',
													limit: 3,
													height : 220,
													page : true,
													cols : cols,
													where : {
														"flyGeneralOverviewVo.hospitalId": field.hospitalId,
														"flyGeneralOverviewVo.hospitalName" : field.hospitalName,
														"flyGeneralOverviewVo.year":field.year,
														"flyGeneralOverviewVo.visitingRoute":field.visitingRoute,
														"flyGeneralOverviewVo.benefitGroupId":field.benefitGroupId
													}
												});
									});

    //导出监听
    form.on('submit(LAY-flycheck-front-export)', function (data) {
        var field = data.field;
        var param=encodeURI(JSON.stringify(field));
        window.open($WEB_ROOT_PATH + '/dhccApi/flyGeneralOverview/flyGeneralOverview//exportExcel?param='+param);
        return false;
    });
					// 重置事件
					$("#reset").click(function(date) {
						$("input").val("");
						$("#baifenhao").val("%");
						table.reload("#flyCheck")
					})

					//按钮事件绑定底层方法-勿动
				    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function () {
				        var type = $(this).data('type');
				        active[type] ? active[type].call(this) : '';
				    });
				});
