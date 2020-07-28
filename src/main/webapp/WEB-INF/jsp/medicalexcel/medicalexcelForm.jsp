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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/layui/css/layui.css"
	media="all">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/style/admin.css"
	media="all">
<style>
.layui-input-block {
	margin-left: 90px;
	min-height: 36px;
}

.layui-input-block1 {
	margin-left: 90px;
}

.layui-form-label {
	width: 60px;
}

.layui-tab-title li {
	min-width: 62px;
}

.layui-form-radio {
	margin: 0px 0px 0 0;
}
</style>
<title></title>
</head>
<body>



	<div class="layui-fluid" id="LAY-component-grid-mobile-pc">
		<div class="layui-row layui-col-space10">
			<div class="layui-col-xs12 layui-col-md12">
				<!-- 填充内容 -->
				<div class="layui-card">
					<div
						class="layui-form layui-card-header layuiadmin-card-header-auto">
						<div class="layui-form-item">
							<div class="layui-inline">
								<h2>根据你得查询条件得到如下信息：</h2>

							</div>
						</div>
						<div class="layui-form-item">
						
							<div class="layui-inline">
								<h5 id="medicalInfo">1、病例条数:</h5>

							</div>
						</div>
							<!-- <div class="layui-form-item">
								<div class="layui-inline">
									<h5 id="detailInfo">2、明细条数:</h5>
								</div>
							</div> -->
							<div class="layui-form-item">
								<div class="layui-inline">
								
									<h2>导出：</h2>
									<div id="exportInfo">
									<h5>1、1-1000条导出:</h5>
									<h5>2、1001-2000条导出:</h5>
									<h5>3、2001-3000条导出:</h5>
									</div>
								</div>
							</div>
						</div>
					
				</div>
			</div>
		</div>


	</div>

<script>
function getData(path){
	window.open($WEB_ROOT_PATH+ '/dhccApi/medicalexcel/medicalexcel/exportExcelToSelfByNums?nums='+path);
	out.clear();
	out = pageContext.pushBody();
}
function getStr(ss){
	var nums = ss;
	var path ="";
	var htmls = "";
	path = $WEB_ROOT_PATH+'/dhccApi/medicalexcel/medicalexcel/exportExcelToSelfByNums?nums='+nums+1;
	htmls += '<h5>全部导出:'
	+ '<div class="layui-inline ">'
	+ '<a id="medical-jpp-qbdc" href="javascript:void(0)"  class="layui-btn layuiadmin-btn-useradmin layui-btn-sm"' 
	+ '	lay-filter="LAY-user-front-export" stylename="export"  onclick="getData('+  nums+1+')">'
	+ '	<i class="layui-icon layui-icon-file  layuiadmin-button-btn"></i>导出'
	+ '</a>'
+'</div></h5>';
	if(nums > 0){
		for(var i = 1; i <= ss+1;i++){
			
			if(i == 1 ){
				path = '/dhccApi/medicalexcel/medicalexcel/exportExcelToSelfByNums?nums='+1;
			
				htmls += '<h5>'+"1、"+'1-1000导出:'
				+ '<div class="layui-inline ">'
				+ '<a id="medical-jpp-qbdc" href="javascript:void(0)" class="layui-btn layuiadmin-btn-useradmin layui-btn-sm"' 
				+ '	lay-filter="LAY-user-front-export" stylename="export" onclick="getData('+  i+')" >'
				+ '	<i class="layui-icon layui-icon-file  layuiadmin-button-btn"></i>导出'
				+ '</a>'
				+'</div></h5>';
			}else{
				path = $WEB_ROOT_PATH+'/dhccApi/medicalexcel/medicalexcel/exportExcelToSelfByNums?nums='+i;
				htmls += '<h5>'+i+'、 '+(parseInt((i-1)*1000)+parseInt(1)) +"-"+i*1000+'导出:'
				+ '<div class="layui-inline ">'
				+ '<a id="medical-jpp-qbdc" href="javascript:void(0)"  class="layui-btn layuiadmin-btn-useradmin layui-btn-sm"' 
				+ '	lay-filter="LAY-user-front-export" stylename="export"  onclick="getData('+  i+')">'
				+ '	<i class="layui-icon layui-icon-file  layuiadmin-button-btn"></i>导出'
				+ '</a>'
			+'</div></h5>';
			}
		}
	}

/* 	if(nums == 0){
		path = $WEB_ROOT_PATH+'/dhccApi/medicalexcel/medicalexcel/exportExcelToSelfByNums?nums='+0;
		htmls += '<h5>全部导出:'
			+ '<div class="layui-inline ">'
			+ '<a id="medical-jpp-qbdc" href="'+ path +'" class="layui-btn layuiadmin-btn-useradmin layui-btn-sm"' 
			+ '	lay-filter="LAY-user-front-export" stylename="export">'
			+ '	<i class="layui-icon layui-icon-file  layuiadmin-button-btn"></i>导出'
			+ '</a>'
		+'</div></h5>';
	} */
	return htmls;
 }
	 $.ajax({
		url:$WEB_ROOT_PATH+'/dhccApi/medicalexcel/medicalexcel/getAllInfo',
		type:'get',
		success:function(data){
			if(data!=null || data != ""){
				$('#medicalInfo').html("主病例查询条数:"+data+"条");
				//$('detailInfo').val(ss[1]);
				var nums = parseInt(data);
				if(nums==0){
					$('#exportInfo').html("无数据导出");

				}else{
					$('#exportInfo').html(getStr(parseInt(nums/1000)));
				}
			}else {
				$('#medicalInfo').val("无数据");
				//$('detailInfo').val("无数据");
			}
		}
	}); 
	 
	
</script>

</body>
</html>