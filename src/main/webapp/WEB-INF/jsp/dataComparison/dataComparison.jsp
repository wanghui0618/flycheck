<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp"%>
<%@include file="/WEB-INF/jsp/common/easyui.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/layui/css/layui.css"
	media="all">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/style/admin.css"
	media="all">
<script
	src="<%=request.getContextPath()%>/js/echarts_jpp/echarts-2.2.7/build/dist/echarts.js"></script>
<title>医院数据对比</title>
<style type="text/css">
html {
	background-color: #F2F2F2;
}

div[lay-id=diseasesAnalysisTable] .layui-table-body {
	height: 280px;
}

.layui-this {
	background-color: #F68F00;
	color: #fff !important;
	border-radius: 2px;
}
</style>
</head>
<body>
	<div class="layui-fluid" style="overflow: hidden;">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md12">
				<div class="layui-card">
					<div
						class="layui-form layui-card-header layuiadmin-card-header-auto">
						<div class="layui-form-item">
							<div class="layui-inline">
								<label class="layui-form-label">就诊途径</label>
								<div class="layui-input-inline">
									<select id="diagType" name="diagType">
										<option value="1">住院</option>
										<option value="2">门诊</option>
									</select>
								</div>
							</div>
							<div class="layui-inline">
								<label class="layui-form-label">人群类型</label>
								<div class="layui-input-inline">
									<input type="hidden" id="bingZ"> <input type="text"
										style="" id="personType" name="personType" placeholder="请输入"
										autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-inline">
								<label class="layui-form-label">医疗机构</label>
								<div class="layui-input-inline">
									<input id="getOrgName" name="orgName1" /> <input type="text"
										id="orgCode" name="orgCode1" style="display: none;" />
								</div>
							</div>
							<div id="xiala" onclick="showSearch()"
								style="color: #2284FF; cursor: pointer; z-index: 9999; display: inline-block;">
								更多<img style="margin: 0 auto; height: 6px; width: 8px;"
									src="<%=request.getContextPath()%>/images/main/xiala.png" />
							</div>
							<!--对比医院下拉框需要自己写-->
							<div class="layui-inline cxtjtop">
								<label class="layui-form-label" style="width: 100px">对比医疗机构</label>
								<div class="layui-input-inline">
									<input id="getOrgName1" name="orgName2" /> <input type="text"
										id="orgCode1" name="orgCode2" style="display: none;" />
								</div>
							</div>
							<div class="layui-inline cxtjtop" id="timeSelect">
								<label class="layui-form-label">结算日期</label>
								<div class="layui-input-inline">
									<input type="text" class="layui-input" id="time"
										name="balanceDate" readonly="true">
								</div>
							</div>
							<div class="layui-inline cxtjtop">
								<label class="layui-form-label">选择年份</label>
								<div class="layui-input-inline">
									<input type="text" class="layui-input" id="year" name="year"
										readonly="true" placeholder="收治病人对比 |按年">
								</div>
							</div>
							<div id="shangla" onclick="hideSearch()"
								style="color: #2284FF; cursor: pointer; z-index: 9999; display: inline-block;">
								收起<img style="margin: 0 auto; height: 6px; width: 8px;"
									src="<%=request.getContextPath()%>/images/main/shangla.png" />
							</div>
							<div class="layui-inline">
								<button class="layui-btn layuiadmin-btn-useradmin" lay-submit
									lay-filter="LAY-avgDays-front-search" stylename="search">
									<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
								</button>
								<button id='startAll' data-type="startAll"
									class="layui-btn layui-btn-primary" lay-submit id="resets"
									stylename="allUpdate" lay-filter="LAY-user-front-add">
									<i class="layui-icon layui-icon-refresh layuiadmin-button-btn"></i>重置
								</button>
								<button id='stopAll' data-type="stopAll" stylename="export"
									class="layui-btn layuiadmin-btn-useradmin" lay-submit
									lay-filter="LAY-user-front-stop">
									<i class="layui-icon layui-icon-file  layuiadmin-button-btn"></i>导出
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="layui-col-md4">
				<div class="layui-card">
					<div class="layui-card-header"
						style="border-bottom: 1px solid #f6f6f6;">
						<img style="margin-top: -2px; padding-right: 8px;"
							src="<%=request.getContextPath()%>/images/auditing/mark.png" />
						总费用对比
					</div>
					<div class="layui-card-body">
						<div class="layui-tab layui-tab-brief"
							lay-filter="docDemoTabBrief">
							<div class="layui-tab-content"
								style="margin-top: -10px; padding-left: 0px; padding-right: 0px;">
								<div class="layui-tab-item layui-show" style="height: 300px;">
									<div id="main1" style="height: 300px; width: 100%;"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="layui-col-md4">
				<div class="layui-card">
					<div class="layui-card-header"
						style="border-bottom: 1px solid #f6f6f6;">
						<img style="margin-top: -2px; padding-right: 8px;"
							src="<%=request.getContextPath()%>/images/auditing/mark.png" />
						报销金额对比
					</div>
					<div class="layui-card-body">
						<div class="layui-tab layui-tab-brief"
							lay-filter="docDemoTabBrief">
							<div class="layui-tab-content"
								style="margin-top: -10px; padding-left: 0px; padding-right: 0px;">
								<div class="layui-tab-item layui-show" style="height: 300px;">
									<div id="main2" style="height: 300px; width: 100%;"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="layui-col-md4">
				<div class="layui-card">
					<div class="layui-card-header"
						style="border-bottom: 1px solid #f6f6f6;">
						<img style="margin-top: -2px; padding-right: 8px;"
							src="<%=request.getContextPath()%>/images/auditing/mark.png" />
						收治病人对比
						<%-- <span style="float: right;">
							<li class="layui-this" onclick="huan(0)"
							style="height: 15px; line-height: 15px; min-width: 25px; margin: 4px; padding: 4px; border-bottom: none">人数</li>
							<img style="margin-top: -2px; height: 25px; width: 2px;"
							src="<%=request.getContextPath()%>/images/auditing/fenge_red.png" />
							<input type="button" value="人次" onclick="huan(1)"
							style="border: 0; cursor: pointer; background: none; color: #FF9900">
							<img style="margin-top: -2px; height: 25px; width: 2px;"
							src="<%=request.getContextPath()%>/images/auditing/fenge_red.png" />
							<input type="button" value="人次/年" onclick="huan(2)"
							style="border: 0; cursor: pointer; background: none; color: #FF9900">
							<img style="margin-top: -2px; height: 25px; width: 2px;"
							src="<%=request.getContextPath()%>/images/auditing/fenge_red.png" />
							<input type="button" value="人数/年" onclick="huan(3)"
							style="border: 0; cursor: pointer; background: none; color: #FF9900">
						</span> --%>
						<div class="layui-card-body" style="height: 315px;">
							<div class="layui-tab layui-tab-brief"
								lay-filter="docDemoTabBrief">
								<ul class="layui-tab-title"
									style="height: 20px; line-height: 15px; border: none; margin-top: -38px; margin-right: -20px; float: right;">
									<li id="cz" class="layui-this"
										style="height: 22px; line-height: 15px; min-width: 25px; margin: 4px; padding: 4px; border-bottom: none"
										onclick="huan(0)">人数</li>
									<img style="height: 25px; width: 2px;"
										src="<%=request.getContextPath()%>/images/auditing/fenge_red.png" />
									<li
										style="height: 22px; line-height: 15px; min-width: 25px; margin: 4px; padding: 4px; border-bottom: none"
										onclick="huan(1)">人次</li>
									<img style="height: 25px; width: 2px;"
										src="<%=request.getContextPath()%>/images/auditing/fenge_red.png" />
									<li
										style="height: 22px; line-height: 15px; min-width: 25px; margin: 4px; padding: 4px; border-bottom: none"
										onclick="huan(2)">人次/年</li>
									<img style="height: 25px; width: 2px;"
										src="<%=request.getContextPath()%>/images/auditing/fenge_red.png" />
									<li
										style="height: 22px; line-height: 15px; min-width: 25px; margin: 4px; padding: 4px; border-bottom: none"
										onclick="huan(3)">人数/年</li>
								</ul>
							</div>
						</div>
					</div>
					<div class="layui-card-body">
						<div class="layui-tab layui-tab-brief"
							lay-filter="docDemoTabBrief">
							<div class="layui-tab-content"
								style="margin-top: -10px; padding-left: 0px; padding-right: 0px;">
								<div class="layui-tab-item layui-show" style="height: 300px;">
									<div id="main3" style="height: 300px; width: 100%;"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="layui-col-md4">
				<div class="layui-card">
					<div class="layui-card-header"
						style="border-bottom: 1px solid #f6f6f6;">
						<img style="margin-top: -2px; padding-right: 8px;"
							src="<%=request.getContextPath()%>/images/auditing/mark.png" />
						次均住院/门诊费用对比
					</div>
					<div class="layui-card-body">
						<div class="layui-tab layui-tab-brief"
							lay-filter="docDemoTabBrief">
							<div class="layui-tab-content"
								style="margin-top: -10px; padding-left: 0px; padding-right: 0px;">
								<div class="layui-tab-item layui-show" style="height: 300px;">
									<div id="main4" style="height: 300px; width: 100%;"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="layui-col-md4">
				<div class="layui-card">
					<div class="layui-card-header"
						style="border-bottom: 1px solid #f6f6f6;">
						<img style="margin-top: -2px; padding-right: 8px;"
							src="<%=request.getContextPath()%>/images/auditing/mark.png" />
						平均住院天数对比
					</div>
					<div class="layui-card-body">
						<div class="layui-tab layui-tab-brief"
							lay-filter="docDemoTabBrief">
							<div class="layui-tab-content"
								style="margin-top: -10px; padding-left: 0px; padding-right: 0px;">
								<div class="layui-tab-item layui-show" style="height: 300px;">
									<div id="main6" style="height: 300px; width: 100%;"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="layui-col-md4">
				<div class="layui-card">
					<div class="layui-card-header"
						style="border-bottom: 1px solid #f6f6f6;">
						<img style="margin-top: -2px; padding-right: 8px;"
							src="<%=request.getContextPath()%>/images/auditing/mark.png" />
						复诊率对比&nbsp;&nbsp;(人次-人数)&nbsp;/&nbsp;人数
					</div>
					<div class="layui-card-body">
						<div class="layui-tab layui-tab-brief"
							lay-filter="docDemoTabBrief">
							<div class="layui-tab-content"
								style="margin-top: -10px; padding-left: 0px; padding-right: 0px;">
								<div class="layui-tab-item layui-show" style="height: 300px;">
									<div id="main7" style="height: 300px; width: 100%;"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="layui-col-md12">
				<div class="layui-card">
					<div class="layui-card-header"
						style="border-bottom: 1px solid #f6f6f6;">
						<img style="margin-top: -2px; padding-right: 8px;"
							src="<%=request.getContextPath()%>/images/auditing/mark.png" />
						两个医院治疗疾病总费用top10
					</div>
					<div class="layui-card-body">
						<div class="layui-tab layui-tab-brief"
							lay-filter="docDemoTabBrief">
							<div class="layui-tab-content"
								style="margin-top: -10px; padding-left: 0px; padding-right: 0px;">
								<div class="layui-tab-item layui-show" style="height: 300px;">
									<div id="main8" style="height: 300px; width: 100%;"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<%-- 			<div class="layui-col-md4">
				<div class="layui-card">
					<div class="layui-card-header"
						style="border-bottom: 1px solid #f6f6f6;">
						<img style="margin-top: -2px; padding-right: 8px;"
							src="<%=request.getContextPath()%>/images/auditing/mark.png" />
						两个医院收治病人top10科室: A TOP10,B TOP10
					</div>
					<div class="layui-card-body">
						<div class="layui-tab layui-tab-brief"
							lay-filter="docDemoTabBrief">
							<div class="layui-tab-content"
								style="margin-top: -10px; padding-left: 0px; padding-right: 0px;">
								<div class="layui-tab-item layui-show" style="height: 300px;">
									<div id="main9" style="height: 300px; width: 100%;"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div> --%>
		</div>
	</div>

	<script
		src="<%=request.getContextPath()%>/js/echarts_jpp/echarts-2.2.7/build/dist/echarts.js"></script>
	<!--医疗机构下拉-->
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/app/js/common/orgDictSelect.js"></script>
	<!-- 对比医院下拉框 -->
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/app/js/dataComparison/contrastHospital.js"></script>
	<script>
		$("#getOrgName").val("哈尔滨医科大学附属第一医院");
		$("#getOrgName1").val("哈尔滨医科大学附属第二医院");
		layui
				.config({
					base : $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' //静态资源所在路径
				})
				.extend({
					index : 'lib/index' //主入口模块
				})
				.use(
						[ 'index', 'table', 'laydate' ],
						function() {
							var $ = layui.$, form = layui.form, table = layui.table, laydate = layui.laydate;

							laydate.render({
								elem : '#time',
								range : true,
								format : 'yyyyMMdd',
							});
							//查询方法
							form.on('submit(LAY-avgDays-front-search)',
									function(data) {
										var field = data.field;
										createTable(field.diagType,
												field.personType,
												field.orgCode1, field.orgCode2,
												field.balanceDate, field.year);

										$("li").removeAttr("class");
										$("#cz").attr("class", "layui-this");
									});
							//导出方法
							form
									.on(
											'submit(LAY-user-front-stop)',
											function(data) {
												var bingZ = $("#bingZ").val();
												var field = data.field;
												var param = encodeURI(JSON
														.stringify(field));
												window
														.open($WEB_ROOT_PATH
																+ '/dhccApi/dataComparison/dataComparison/exportExcelToSelf?param='
																+ param
																+ '&bingZ='
																+ bingZ);
											});
							//重置
							$("#startAll")
									.click(
											function(data) {
												$("#personType").val("");
												$("#_easyui_textbox_input1")
														.val("");
												$("#_easyui_textbox_input2")
														.val("");
												$("#getOrgName").val("");
												$("#getOrgName1").val("");
												$("#time").val("");
												$("#year").val("");
												$("#diagType").val("1");
												$("#orgCode1").val("");
												$("#orgCode").val("");
												var diagType = $("#diagType")
														.val();
												var personType = $(
														"#personType").val();
												var orgCode1 = $("#orgCode1")
														.val();
												var orgCode2 = $("#orgCode2")
														.val();
												var balanceDate = $("#time")
														.val();
												var code = $("#code").val();
												var year = $("#year").val();
												form.render('select',
														'diagType');
												form.render("select");
												$("li").removeAttr("class");
												$("#cz").attr("class",
														"layui-this");
												createTable(diagType,
														personType, orgCode1,
														orgCode2, balanceDate,
														year);
											});
							//年选择器
							laydate.render({
								elem : '#year',
								type : 'year'
							});
							//按钮事件绑定底层方法-勿动
							$('.layui-btn.layuiadmin-btn-useradmin').on(
									'click',
									function() {
										var type = $(this).data('type');
										active[type] ? active[type].call(this)
												: '';
									});
						});

		$(".cxtjtop").hide();
		$("#shangla").hide();
		//eChars---------------------------------------------------------------------------------------------------
		createTable("1", "", "0001", "0002", "");
		//点击人数显示人数
		function huan(code) {
			var colors = [ [ "#83D944", "#F9AD08" ] ];
			var name = "人数对比"
			var fmt = "{a} <br/>{b} : {c}(人)";
			var year = "";
			if (code == "1") {
				name = "人次对比"
				fmt = "{a} <br/>{b} : {c}(人次)";
			} else if (code == "2") {
				name = "按年对比人次"
				fmt = "{a} <br/>{b} : {c}(人次/年)";
			} else if (code == "3") {
				name = "按年对比人数"
				fmt = "{a} <br/>{b} : {c}(人数/年)";
			}
			var myChart3 = echarts.init(document.getElementById('main3'));
			var option3 = {
				tooltip : {
					trigger : 'item',
					position : 'left',
					confine : true,
					position : [ '10%', '40%' ],
					formatter : fmt
				},
				legend : {
					type : 'scroll',
					orient : 'vertical',
					x : "left",
					top : -5,
					data : []
				},
				color : colors[0],
				series : [ {
					name : name,
					type : 'pie',
					radius : [ '0', '63%' ],
					hoverAnimation : true,
					center : [ '50%', '50%' ],
					avoidLabelOverlap : true,
					label : {
						normal : {
							show : false
						}
					},
					data : []
				} ]
			};

			$.ajax({
				url : $WEB_ROOT_PATH
						+ "/dhccApi/dataComparison/dataComparison/table3",
				type : "get",
				async : true, //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
				dataType : "json",
				data : {
					code : code,
					diagType : $("#diagType").val(),
					personType : $("#personType").val(),
					orgCode1 : $("#orgCode").val(),
					orgCode2 : $("#orgCode1").val(),
					balanceDate : $("#time").val(),
					year : $("#year").val(),
				},
				success : function(data) {
					var data2;
					if (data.data.length != 0) {
						myChart3.setOption({
							title : {
								show : false
							//平常时设置为false，隐藏没有数据的文字提示
							}
						});
						for (var i = 0; i < data.data.length; i++) {
							if (code == "2") {
								data2 = {
									name : data.data[i].year + "年-"
											+ data.data[i].hospitalName,
									value : data.data[i].result
								}
								option3.series[0].data.push(data2);
								option3.legend.data.push(data.data[i].year
										+ "年-" + data.data[i].hospitalName);
							} else if (code == "0" || code == "1") {
								data2 = {
									value : data.data[i].result,
									name : data.data[i].hospitalName

								}
								option3.series[0].data.push(data2);
								option3.legend.data
										.push(data.data[i].hospitalName);
							} else if (code == "3") {
								data2 = {
									name : data.data[i].year + "年-"
											+ data.data[i].hospitalName,
									value : data.data[i].result
								}
								option3.series[0].data.push(data2);
								option3.legend.data.push(data.data[i].year
										+ "年-" + data.data[i].hospitalName);
							}
						}

					} else {
						myChart3.setOption({
							title : {
								show : true,//平常时设置为false，隐藏没有数据的文字提示
								textStyle : {
									color : '#bcbcbc'
								},
								text : '本年度暂无数据',
								left : 'center',
								top : 'center'
							}
						});
					}
					myChart3.setOption(option3);
				}
			});
		}
		function createTable(diagType, personType, orgCode1, orgCode2,
				balanceDate) {
			var bingZ1 = [];
			huan(0);
			// 路径配置
			require.config({
				paths : {
					echarts : $WEB_ROOT_PATH
							+ '/js/echarts_jpp/echarts-2.2.7/build/dist'
				}
			});
			// 使用
			require(
					[ 'echarts', 'echarts/chart/pie' ], // 使用柱状图就加载bar模块，按需加载
					function(ec) {
						// 基于准备好的dom，初始化echarts图表
						var myChart = echarts.init(document
								.getElementById('main1'));
						var myChart2 = echarts.init(document
								.getElementById('main2'));
						var myChart4 = echarts.init(document
								.getElementById('main4'));
						var myChart6 = echarts.init(document
								.getElementById('main6'));
						var myChart7 = echarts.init(document
								.getElementById('main7'));
						var myChart8 = echarts.init(document
								.getElementById('main8'));
						colors = [ [ '#0092ff', '#eba954' ],
								[ '#d74e67', '#21b6b9' ],
								[ '#43cadd', '#3893e5' ],
								[ '#ff733f', '#ec4863' ],
								[ 'green', '#6666cc' ],
								[ '#BB0000', '#003077' ] ];
						var option = {
							tooltip : {
								trigger : 'item',
								position : 'right',
								confine : true
							},
							tooltip : {
								trigger : 'item',
								position : [ '10%', '40%' ],
								formatter : '{a} <br/>{b} : {c}(元)'
							},
							legend : {
								type : 'scroll',
								orient : 'vertical',
								x : "left",
								top : -5,
								data : []
							},
							color : colors[0],
							series : [ {
								name : "总费用对比",
								type : 'pie',
								radius : [ '0', '63%' ],
								hoverAnimation : true,
								center : [ '50%', '50%' ],
								avoidLabelOverlap : true,
								label : {
									normal : {
										show : false
									}
								},
								data : []
							} ]
						};

						$
								.ajax({
									url : $WEB_ROOT_PATH
											+ "/dhccApi/dataComparison/dataComparison/table1",
									type : "get",
									async : true, //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
									dataType : "json",
									data : {
										diagType : diagType,
										personType : personType,
										orgCode1 : orgCode1,
										orgCode2 : orgCode2,
										balanceDate : balanceDate

									},
									success : function(data) {
										var data1;
										for (var i = 0; i < data.data.length; i++) {
											data1 = {
												value : data.data[i].totalAmount,
												name : data.data[i].hospitalName
											}
											option.series[0].data.push(data1);
											option.legend.data
													.push(data.data[i].hospitalName);
										}
										myChart.setOption(option);
									}
								});

						var option2 = {
							tooltip : {
								trigger : 'item',
								position : 'right',
								confine : true
							},
							tooltip : {
								trigger : 'item',
								position : [ '10%', '40%' ],
								formatter : '{a} <br/>{b} : {c}(元)'
							},
							legend : {
								type : 'scroll',
								orient : 'vertical',
								x : "left",
								top : -5,
								data : []
							},
							color : colors[1],
							series : [ {
								name : "报销费用对比",
								type : 'pie',
								radius : [ '0', '63%' ],
								hoverAnimation : true,
								center : [ '50%', '50%' ],
								avoidLabelOverlap : true,
								label : {
									normal : {
										show : false

									}
								},
								data : []
							} ]
						};

						$
								.ajax({
									url : $WEB_ROOT_PATH
											+ "/dhccApi/dataComparison/dataComparison/table2",
									type : "get",
									async : true, //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
									dataType : "json",
									data : {
										diagType : diagType,
										personType : personType,
										orgCode1 : orgCode1,
										orgCode2 : orgCode2,
										balanceDate : balanceDate
									},
									success : function(data) {
										var data1;
										for (var i = 0; i < data.data.length; i++) {
											data1 = {
												value : data.data[i].bmiPayAmount,
												name : data.data[i].hospitalName
											}
											option2.series[0].data.push(data1);
											option2.legend.data
													.push(data.data[i].hospitalName);
										}
										myChart2.setOption(option2);
									}
								});

						var option4 = {
							tooltip : {
								// trigger:'axis'

								trigger : 'item',
								position : 'left',
								confine : true
							},
							tooltip : {
								trigger : 'item',
								position : [ '10%', '40%' ],
								formatter : '{a} <br/>{b} : {c}(元)'
							},
							legend : {
								type : 'scroll',
								orient : 'vertical',
								x : "left",
								top : -5,
								data : []
							},
							color : colors[2],
							series : [ {
								name : "次均住院费用对比",
								type : 'pie',
								radius : [ '0', '63%' ],
								hoverAnimation : true,
								center : [ '50%', '50%' ],
								avoidLabelOverlap : true,
								label : {
									normal : {
										show : false

									}
								},
								data : []
							} ]
						};

						$
								.ajax({
									url : $WEB_ROOT_PATH
											+ "/dhccApi/dataComparison/dataComparison/table45",
									type : "get",
									async : true, //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
									dataType : "json",
									data : {
										diagType : diagType,
										personType : personType,
										orgCode1 : orgCode1,
										orgCode2 : orgCode2,
										balanceDate : balanceDate
									},
									success : function(data) {
										var data1;
										for (var i = 0; i < data.data.length; i++) {
											data1 = {
												value : data.data[i].eachCost,
												name : data.data[i].hospitalName
											}
											option4.series[0].data.push(data1);
											option4.legend.data
													.push(data.data[i].hospitalName);
										}
										myChart4.setOption(option4);
									}
								});
						var option6 = {
							series : [ {
								type : 'pie',
								data : []
							} ]
						};
						var option6 = {
							tooltip : {
								trigger : 'item',
								position : 'left',
								confine : true,
								position : [ '10%', '40%' ],
								formatter : '{a} <br/>{b} : {c}(天)'
							},
							legend : {
								type : 'scroll',
								orient : 'vertical',
								x : "left",
								top : -5,
								data : []
							},
							color : colors[3],
							series : [ {
								name : "平均住院天数对比",
								type : 'pie',
								radius : [ '0', '63%' ],
								hoverAnimation : true,
								center : [ '50%', '50%' ],
								avoidLabelOverlap : true,
								label : {
									normal : {
										show : false

									}
								},
								data : []
							} ]
						};

						$
								.ajax({
									url : $WEB_ROOT_PATH
											+ "/dhccApi/dataComparison/dataComparison/table6",
									type : "get",
									async : true, //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
									dataType : "json",
									data : {
										diagType : diagType,
										personType : personType,
										orgCode1 : orgCode1,
										orgCode2 : orgCode2,
										balanceDate : balanceDate
									},
									success : function(data) {
										var data1;
										if (data.data != null) {
											myChart6.setOption({
												title : {
													show : false,//平常时设置为false，隐藏没有数据的文字提示
												}
											});
											for (var i = 0; i < data.data.length; i++) {
												data1 = {
													value : data.data[i].avgDays,
													name : data.data[i].hospitalName
												}
												option6.series[0].data
														.push(data1);
												option6.legend.data
														.push(data.data[i].hospitalName);
											}
										} else {
											myChart6.setOption({
												title : {
													show : true,//平常时设置为false，隐藏没有数据的文字提示
													textStyle : {
														color : '#bcbcbc'
													},
													text : '暂无数据',
													left : 'center',
													top : 'center'
												}
											});
										}
										myChart6.setOption(option6);
									}
								});
						/* 图表7 */
						var option7 = {
							tooltip : {
								// trigger:'axis'

								trigger : 'item',
								confine : true,
								position : [ '10%', '40%' ],
								formatter : '{a} <br/>{b} : {c}(%)'
							},
							legend : {
								type : 'scroll',
								orient : 'vertical',
								x : "left",
								top : -5,
								data : []
							},
							color : colors[4],
							series : [ {
								name : "复诊率对比",
								type : 'pie',
								radius : [ '0', '63%' ],
								hoverAnimation : true,
								center : [ '50%', '50%' ],
								avoidLabelOverlap : true,
								label : {
									normal : {
										show : false
									}
								},
								data : []
							} ]
						};

						$
								.ajax({
									url : $WEB_ROOT_PATH
											+ "/dhccApi/dataComparison/dataComparison/table7",
									type : "get",
									async : true, //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
									dataType : "json",
									data : {
										diagType : diagType,
										personType : personType,
										orgCode1 : orgCode1,
										orgCode2 : orgCode2,
										balanceDate : balanceDate
									},
									success : function(data) {
										var data1;
										for (var i = 0; i < data.data.length; i++) {
											data1 = {
												name : data.data[i].hospitalName,
												value : data.data[i].result
											}
											option7.series[0].data.push(data1);
											option7.legend.data
													.push(data.data[i].hospitalName);
										}
										myChart7.setOption(option7);
									}
								});
						var hospital = $("#getOrgName").val();
						var hospital1 = $("#getOrgName1").val();
						if (hospital == null && hospital1 == null
								|| hospital == "" && hospital1 == "") {
							hospital = "哈尔滨医科大学附属第一医院";
							hospital1 = "哈尔滨医科大学附属第二医院";
						}
						if (hospital != "" && hospital1 == "" || hospital == ""
								&& hospital1 != "") {
							hospital = "哈尔滨医科大学附属第一医院";
							hospital1 = "哈尔滨医科大学附属第二医院";
						}
						myChart8.setOption({
							grid : {
								left : 100,// 调整这个属性，
							},
							legend : {
								data : [ hospital, hospital1 ]
							},
							toolbox : {
								right:120,
								show : true,
								feature : {
									dataView : {
										show : true,
										readOnly : false
									},
									magicType : {
										show : true,
										type : [ 'line', 'bar' ]
									},
									restore : {
										show : true
									},
									saveAsImage : {
										show : true
									}
								}
							},
							tooltip : {
								trigger : 'axis',
								formatter : function(params) {
									if (params.length == 1) {
										return params[0].name + "<br>"
												+ params[0].marker
												+ params[0].seriesName + ":"
												+ params[0].data + "(元)";
									} else {
										return params[0].name + "<br>"
												+ params[0].marker
												+ params[0].seriesName + ":"
												+ params[0].data + "(元)"
												+ "<br>" + params[1].marker
												+ params[1].seriesName + ":"
												+ params[1].data + "(元)";

									}
								},
								axisPointer : {
									type : 'shadow'
								}
							},
							xAxis : [ {
								type : 'category',
								axisLabel : {
									interval : 0,//强制文字产生间隔
									/* rotate : 45,//文字逆时针旋转45° */
									textStyle : { //文字样式	
										fontSize : 15,
									}
								}
							} ],
							yAxis : {},
							color : colors[5],
							series : [ {
								name : hospital,
								type : 'bar',
								data : []
							}, {
								name : hospital1,
								type : 'bar',
								data : []
							} ]
						});
						//第一家医院
						$
								.ajax({
									url : $WEB_ROOT_PATH
											+ "/dhccApi/dataComparison/dataComparison/table8_1",
									type : "get",
									async : true, //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
									dataType : "json",
									data : {
										diagType : diagType,
										personType : personType,
										orgCode1 : orgCode1,
										orgCode2 : orgCode2,
										balanceDate : balanceDate
									},
									success : function(data) {

										var result = [];
										var hospitalName = [];
										var sum = [];
										var sum1 = [];
										for (var i = 0; i < data.data.length; i++) {
											hospitalName
													.push(data.data[i].hospitalName);
											sum.push(data.data[i].sum);
											result.push(data.data[i].result);
										}
										/* myChart8.setOption({
											xAxis : {
												data : result
											},
											series : [ {
												name : hospital,
												data : sum
											} ]
										}); */
										$
												.ajax({
													url : $WEB_ROOT_PATH
															+ "/dhccApi/dataComparison/dataComparison/table8_2",
													type : "get",
													async : true, //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
													dataType : "json",
													traditional : true,
													data : {
														bingZ : result,
														diagType : diagType,
														personType : personType,
														orgCode1 : orgCode1,
														orgCode2 : orgCode2,
														balanceDate : balanceDate
													},
													success : function(data) {
														for (var i = 0; i < data.data.length; i++) {
															bingZ1
																	.push(data.data[i].result);
														}
														$("#bingZ").val(bingZ1);
														for (var i = 0; i < data.data.length; i++) {
															sum1
																	.push(data.data[i].sum);
														}
														myChart8
																.setOption({
																	xAxis : {
																		data : result
																	},
																	series : [
																			{
																				name : hospital,
																				data : sum
																			},
																			{
																				name : hospital1,
																				data : sum1
																			} ]
																});
													}
												});
									}
								});
					});
		}
	</script>
</body>
</html>