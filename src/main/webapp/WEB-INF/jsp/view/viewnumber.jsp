<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp"%>
<%@include file="/WEB-INF/jsp/common/easyui.jsp" %>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/layui/css/layui.css"
	media="all">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/layui//layuiadmin/style/admin.css"
	media="all">
<title>医院上传数量统计</title>
<style>
.layui-table-page{
    height: 38px;
}
.layui-btn {
    margin: 5px 15px;
}
</style>
</head>
<body style="overflow:hidden">
	<div>
		<div class="layui-row">
			<div class="layui-col-md6" id="parent">
				<div class="grid-demo grid-demo-bg1" id="child">
					<div class="layui-fluid">
						<div class="layui-card">

							<div class="layui-card-body">
								<div class="layui-form layui-card-header layuiadmin-card-header-auto">
			                       <div class="layui-form-item" >
			                            <div class="layui-inline">
								            <label class="layui-form-label">医疗机构</label>
								            <div class="layui-input-inline">
								                 <input id="getOrgName" name="medicalNumVo.qualityName"/>
				                                 <input type="text" id="orgCode" name="medicalNumVo.orgCode" style="display: none;" />
								            </div>
								         </div>
										 <!-- <div class="layui-inline ">
											<label class="layui-form-label ">年度选择</label>
											<div class="layui-input-inline" style="width: 50px;">
												<input id="createTime" name="inFlag" lay-filter="createTime"
													type="text" class="layui-input" placeholder="yyyy">
											</div>
										</div>  -->
										<div class="layui-inline">
											<label class="layui-form-label">年度选择</label>
											<div class="layui-input-inline">
												<input id="createTime" name="inFlag" lay-filter="createTime"
													type="text" class="layui-input" placeholder="yyyy">
											</div>
										</div> 
										<button style="margin-left:0px;" class="layui-btn layuiadmin-btn-useradmin" lay-submit lay-filter="LAY-org-front-search" id="datastatisticsinfo-chaxun">
					                        <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
					                    </button>
			                       </div>
			                     </div>
								<table id="numberTable" class="layui-hide"
									lay-filter="numberTable"></table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="layui-col-md6">
				<div class="grid-demo">
					<div class="layui-fluid">
						<div class="layui-card">
							<div class="layui-card-header" style="height: 55px" id="name">医院上传数据统计</div>
							<div class="layui-card-body">
								<div id="pie"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script
		src="<%=request.getContextPath()%>/js/echarts_jpp/echarts-2.2.7/build/dist/echarts.js"></script>
	<script type="text/javascript"">
		//
		layui
				.config({
					base : $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' //静态资源所在路径
				})
				.extend({
					index : 'lib/index' //主入口模块
				})
				.use(
						[ 'index', 'table','laydate' ],
						function() {
							var $ = layui.$, form = layui.form, table = layui.table;

							 var laydate=layui.laydate;
							 laydate.render({
									elem:'#createTime'
										,type:'year'
										,max:nowYear()
										,value:nowYear()
								});
								function nowYear(){
									var date=new Date();
									var nowYear=date.getFullYear();

									return nowYear.toString();
								}
							table.render({
										elem : '#numberTable',
										url : $WEB_ROOT_PATH
												+ '/dhccApi/medical/medical/listQuality2?tableName=T_PICCBID_MEDICAL',
										cellMinWidth : 80,
										height : tableHeight,
										cols : [ [ {
											type : 'numbers',
											width : 40,
											title : '序号'
										}, {
											field : 'qualityName',
											align : 'center',
											title : '医院名称'
										}, {
											field : 'orgCode',
											align : 'center',
											title : '医院编码',
											hide : true
										}, {
											field : 'totalCount',
											align : 'center',
											title : '上传总数量'
										}, {
											field : 'untegrity',
											align : 'center',
											title : '无效数据'
										}/* , {
											type : 'numbers',
											width : 100,
											align : 'center',
											title : '排名 '
										} */ ] ],
										done : function() {
											initialization(table.cache.numberTable[0]);
										},
										page : true
									});
							
							$("#pie").height(document.documentElement.clientHeight-80);
							   //监听搜索
							form.on('submit(LAY-org-front-search)', function(data){
						    	var field = data.field;
						        //执行重载
						        layui.table.reload('numberTable', {
						            where: field
						        });
						    });

							table.on(
							'row(numberTable)',
							function(obj) {
								var qualityName = obj.data.qualityName;
								var totalCount = obj.data.totalCount;
								var untegrity = obj.data.untegrity;
								$("#name").text(qualityName);
								// 路径配置
								require
										.config({
											paths : {
												echarts : $WEB_ROOT_PATH
														+ '/js/echarts_jpp/echarts-2.2.7/build/dist'
											}
										});
								require(
										[ 'echarts',
												'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
										],
										function(ec) {
											// 基于准备好的dom，初始化echarts图表
											var myChart = ec
													.init(document
															.getElementById('pie'));

											var option = {
												title : {
													x : 'center'
												},
												tooltip : {
													trigger : 'item',
													formatter : "{a} <br/>{b} : {c} ({d}%)"
												},
												legend : {
													left : 'left',
													data : []
												},
												series : [ {
													name : '违规数量',
													type : 'pie',
													radius : '70%',
													center : [
															'50%',
															'60%' ],
													data : [

													]
												} ]
											};
											var names = [];//定义两个数组
											var nums = [];
											names.push("无效数据");
											var obj = new Object();
											obj.name = "无效数据";
											obj.value = parseInt(untegrity);
											nums[0] = obj;
											var obj = new Object();
											names.push("有效数据");
											obj.name = "有效数据";
											obj.value = parseInt(totalCount
													- untegrity);
											nums[1] = obj;
											myChart
													.setOption({ //加载数据图表
														title : {
															x : 'center'
														},
														tooltip : {
															trigger : 'item',
															formatter : "{a} <br/>{b} : {c} ({d}%)"
														},
														legend : {
															left : 'left',
															data : names
														},
														series : [ {
															name : '上传数量',
															type : 'pie',
															radius : '60%',
															center : [
																	'50%',
																	'60%' ],
															data : nums
														} ]
													});

										});

							})

		});

		function initialization(data) {
			// 路径配置
			require.config({
				paths : {
					echarts : $WEB_ROOT_PATH
							+ '/js/echarts_jpp/echarts-2.2.7/build/dist'
				}
			});
			// 使用
			require([ 'echarts', 'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
			], function(ec) {
				// 基于准备好的dom，初始化echarts图表
				var myChart = ec.init(document.getElementById('pie'));

				var option = {
					title : {
						x : 'center'
					},
					tooltip : {
						trigger : 'item',
						formatter : "{a} <br/>{b} : {c} ({d}%)"
					},
					legend : {
						left : 'left',
						data : []
					},
					series : [ {
						name : '违规数量',
						type : 'pie',
						radius : '70%',
						center : [ '50%', '60%' ],
						data : [

						]
					} ]
				};

				// 为echarts对象加载数据 
				var names = [];//定义两个数组
				var nums = [];
                if(data){
                	names.push("无效数据");
    				var obj = new Object();
    				obj.name = "无效数据";
    				obj.value = parseInt(data.untegrity);
    				nums[0] = obj;
    				var obj = new Object();
    				names.push("有效数据");
    				obj.name = "有效数据";
    				obj.value = parseInt(data.totalCount - data.untegrity);
    				nums[1] = obj;
    				$("#name").text(data.qualityName);
                }else{
                	$("#name").text("");
                }
				
				

				myChart.setOption({ //加载数据图表
					title : {
						x : 'center'
					},
					tooltip : {
						trigger : 'item',
						formatter : "{a} <br/>{b} : {c} ({d}%)"
					},
					legend : {
						left : 'left',
						data : names
					},
					series : [ {
						name : '上传数量',
						type : 'pie',
						radius : '60%',
						center : [ '50%', '60%' ],
						data : nums
					} ]
				});

			});
		}
	</script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/common/orgDictSelect.js"></script>
</body>
</html>