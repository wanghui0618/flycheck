<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp"%>
<%@include file="/WEB-INF/jsp/common/easyui.jsp" %>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/layui/css/layui.css"
	media="all">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/layui//layuiadmin/style/admin.css"
	media="all">
<title>常见病例住院费用分析</title>
<style>
.layui-table-page{
    height: 38px;
}
</style>
</head>
<body style="">
	<div class="">
		<div class="layui-row">
			<div class="layui-col-md6">
				<div class="grid-demo">
					<div class="layui-fluid">
						<div class="layui-card">
							<div class="layui-form layui-card-header layuiadmin-card-header-auto">
			                       <div class="layui-form-item" >
			                            <div class="layui-inline">
								            <label class="layui-form-label">疾病名称</label>
								            <div class="layui-input-block">
								               <input type="text" name="AllAnalysis.conDition" placeholder="请输入" autocomplete="off" class="layui-input">
								            </div>
								         </div>
								          
		 	<div class="layui-inline">
                <label class="layui-form-label" style="width:45px" >年份</label>
                <div class="layui-input-block" style="margin-left: 55px;">
                    <input id="createTime" name="allAnalysis.yearTime" lay-filter="createTime"
						type="text" class="layui-input" placeholder="yyyy">
                </div>
                </div> 
                <div class="layui-inline">
					                           <button class="layui-btn layuiadmin-btn-useradmin" lay-submit lay-filter="LAY-org-front-search">
					                           <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
					                            </button>
											</div>
			                       </div>
			                     </div> 
			                     
			                  <div class="layui-card-body">
								<table id="numberTable" class="layui-hide"
									lay-filter="numberTable">
								</table>
								<script type="text/html" id="table-useradmin-webuser">
       							 </script>
     						 </div>
					</div>
				</div>
			</div>
		</div>
			<div class="layui-col-md6">
				<div class="grid-demo">
					<div class="layui-fluid">
						<div class="layui-card" style="width: 750px; margin-left: -10px">
						 	<div class="layui-form layui-card-header layuiadmin-card-header-auto">
			                       <div class="layui-form-item" id="myDiv" >
			                            <div class="layui-inline">
								            <label class="layui-form-label"></label>
								            <div class="layui-input-block">
								            </div>
								         </div>
								          <div class="layui-inline">
										 </div>
			                       </div>
			                </div> 
							<div class="layui-card-body">
								<table id="pie" class="layui-hide" lay-filter="pie"></table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript"">
		//
		var gid;
		layui.config({
					base : $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' //静态资源所在路径
				})
			 .extend({
					index : 'lib/index' //主入口模块
				})
				.use([ 'index', 'table' ,'laydate'],function() {
							var $ = layui.$, form = layui.form, table = layui.table, laydate = layui.laydate;
							
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
												+ '/dhccApi/allAnalysis/allAnalysis/search1',
										cellMinWidth : 80,
										height : document.documentElement.clientHeight-65,
										cols : [ [ {
											type : 'numbers',
											width : 40,
											title : '序号'
										}, {field : 'conDition',
											width : 250,
											align : 'center',
											title : '疾病名称'
										},{
											field : 'allCount',
											align : 'center',
											title : '病例数'
										}, {
											field : 'allPrice',
											align : 'center',
											title : '总费用',
											templet:function(d){
												//获取的数字保留两位小数
												var allPrice=d.allPrice;
												allPrice = Math.round(allPrice*100)/100; 
												 var str = allPrice.toString(); 
												 var rs = str.indexOf('.'); 
												 if (rs < 0) { 
												    rs = str.length; 
												    str += '.'; 
												   } 
												 while (str.length <= rs + 2) { 
												    str += '0'; 
												   }
												return str
												} 
										}, {
											field : 'avgPrice',
											align : 'center',
											title : '次均费用',
											templet:function(d){
													var avgPrice=d.avgPrice;
													//allPrice=allPrice.substr(0,allPrice.indexOf(".")+3)
													avgPrice = Math.round(avgPrice*100)/100; 
													 var str = avgPrice.toString(); 
													 var rs = str.indexOf('.'); 
													 if (rs < 0) { 
													    rs = str.length; 
													    str += '.'; 
													   } 
													 while (str.length <= rs + 2) { 
													    str += '0'; 
													   }
													return str
													} 
										} ] ],
										
										page : true,
										 done: function(res){
											//获取表格第一行数据 触发单击事件
									    /* 	var ftr = $($('#numberTable tr')[1]);
									    	ftr.trigger("click"); */
									    	// $('tr').eq(1).css("background-color","#C0C0C0");
									    	var ftr = $('tr').eq(1);
									    	ftr.trigger("click");
										} 
									}); 
 						    //监听搜索
 							form.on('submit(LAY-org-front-search)', function(data){
 								var field = data.field;
 								//执行重载
 						        layui.table.reload('numberTable', {
 						            where: field
 						        });
 						       var year = document.getElementById("test2");
 								if (year.value === "") {
 								year.value = nowYear();
 								}
 						    });

							$("#pie").height(document.documentElement.clientHeight-110);
							   //监听搜索
							form.on('submit(LAY-org-front-search)', function(data){
						    	var field = data.field;
						        //执行重载
						        layui.table.reload('numberTable', {
						            where: field
						        });
						    });
							  
						    //按钮事件绑定底层方法-勿动
						    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
						      var type = $(this).data('type');
						      active[type] ? active[type].call(this) : '';
						    });
						    
							table.on(
							'row(numberTable)',
							function(obj) {
						         //被点击行改变样式
								obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
								gid = obj.data.conDition;
								//获取点击行的病情名称，展示到右边的表头上方
								var myLabel='<div class="layui-form-item" id="myDiv" style="padding-top:4px">疾病名称：'+gid+'</div>';
								$('#myDiv').replaceWith(myLabel);
								//点击左边表一行，刷新右边表的数据
							 table.render({
								elem : '#pie',
								url : $WEB_ROOT_PATH
										+ '/dhccApi/allAnalysis/allAnalysis/search2?gid='+gid,
								cellMinWidth : 80,
								height : document.documentElement.clientHeight-68,
								cols : [ [ {
									type : 'numbers',
									width : 40,
									title : '序号'
								}, {field : 'orgName',
									width : 250,
									align : 'center',
									title : '医院名称'
								},{
									field : 'allCount',
									align : 'center',
									title : '病例数'
								}, {
									field : 'allPrice',
									align : 'center',
									title : '总费用',
									templet:function(d){
										var allPrice=d.allPrice;
										allPrice = Math.round(allPrice*100)/100; 
										 var str = allPrice.toString(); 
										 var rs = str.indexOf('.'); 
										 if (rs < 0) { 
										    rs = str.length; 
										    str += '.'; 
										   } 
										 while (str.length <= rs + 2) { 
										    str += '0'; 
										   }
										return str
										}  
								}, {
									field : 'avgPrice',
									align : 'center',
									title : '次均费用',
									templet:function(d){
										var avgPrice=d.avgPrice;
										avgPrice = Math.round(avgPrice*100)/100; 
										 var str = avgPrice.toString(); 
										 var rs = str.indexOf('.'); 
										 if (rs < 0) { 
										    rs = str.length; 
										    str += '.'; 
										   } 
										 while (str.length <= rs + 2) { 
										    str += '0'; 
										   }
										return str
										} 
								} ] ],
								page : true
							}); 
							
						})

			});
	</script>
</body>
</html>