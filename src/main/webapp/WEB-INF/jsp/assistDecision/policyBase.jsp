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
	href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css"
	media="all">

<link rel="stylesheet"
	href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css"
	media="all">

<title>基础政策</title>
<style>
.layui-form-label {
	width: 115px;
}
</style>
</head>
<body>
	<div class="layui-form" lay-filter="layuiadmin-form-useradmin"
		id="layuiadmin-form-studentadmin" style="padding: 20px 0 0 0;">

        <div class="layui-card">
          <div class="layui-card-body">
            <div class="layui-tab layui-tab-card">
            <ul class="layui-tab-title">
              <li class="layui-this">基础系数</li>
              <li>药品目录</li>
              <li>项目目录</li>
              <li>材料目录</li>
              <li>特殊疾病目录</li>
              <li>机构等级系数</li>
            </ul>
            <div class="layui-tab-content">
              <div class="layui-tab-item layui-show">
		        	<div class="layui-card">
			          <div class="layui-card-body">
			            <div class="layui-tab layui-tab-brief" lay-filter="component-tabs-brief">
			              <ul class="layui-tab-title">
			                <li class="layui-this">报销比例</li>
			                <li>起付线</li>
			                <li>住院基金支付比例</li>
			                <li>门特报销比例</li>
			                <li>封顶线</li>
			              </ul>
			              <div class="layui-tab-content">
			                <div class="layui-tab-item layui-show">
				                  <table class="layui-table" lay-skin="line">
								  <thead>
								    <tr><th>参数名称</th><th>基础值</th></tr> 
								  </thead>
								  <tbody>
								    <tr>
								      <td>乙类居民报销比例</td>
								      <td><div class="layui-input-inline">
											<input type="text" id="taskKey" name="" style="width: 70px"
												lay-verify="" placeholder="调整值 " autocomplete="off" value="85"
												class="layui-input">
										</div>%</td>
								    </tr>
								    <tr>
								      <td>乙类职工报销比例</td>
								      <td><div class="layui-input-inline">
											<input type="text" id="taskKey" name="" style="width: 70px"
												lay-verify="" placeholder="调整值 " autocomplete="off" value="95"
												class="layui-input">
										</div>%</td>
								    </tr>
								  </tbody>
								</table>
			                </div>
			                <div class="layui-tab-item">
				                  <table class="layui-table" lay-skin="line">
								  <thead>
								    <tr><th>参数名称</th><th>基础值</th></tr> 
								  </thead>
								  <tbody>
								    <tr>
								      <td>乙类居民起付线</td>
								      <td><div class="layui-input-inline">
											<input type="text" id="taskKey" name="" style="width: 70px"
												lay-verify="" placeholder="调整值 " autocomplete="off" value="4500"
												class="layui-input">
										</div></td>
								    </tr>
								    <tr>
								      <td>乙类职工起付线</td>
								      <td><div class="layui-input-inline">
											<input type="text" id="taskKey" name="" style="width: 70px"
												lay-verify="" placeholder="调整值 " autocomplete="off" value="2500"
												class="layui-input">
										</div></td>
								    </tr>
								  </tbody>
								</table>
							</div>
			                <div class="layui-tab-item">
								<table class="layui-table" lay-skin="line">
								  <thead>
								    <tr><th>参数名称</th><th>基础值</th></tr> 
								  </thead>
								  <tbody>
								    <tr>
								      <td>乙类居民住院基金支付比例</td>
								      <td><div class="layui-input-inline">
											<input type="text" id="taskKey" name="" style="width: 70px"
												lay-verify="" placeholder="调整值 " autocomplete="off" value="85"
												class="layui-input">
										</div>%</td>
								    </tr>
								    <tr>
								      <td>乙类职工住院基金支付比例</td>
								      <td><div class="layui-input-inline">
											<input type="text" id="taskKey" name="" style="width: 70px"
												lay-verify="" placeholder="调整值 " autocomplete="off" value="95"
												class="layui-input">
										</div>%</td>
								    </tr>
								  </tbody>
								</table>
							</div>
			                <div class="layui-tab-item">
								<table class="layui-table" lay-skin="line">
								  <thead>
								    <tr><th>参数名称</th><th>基础值</th></tr> 
								  </thead>
								  <tbody>
								    <tr>
								      <td>乙类居民门特报销比例</td>
								      <td><div class="layui-input-inline">
											<input type="text" id="taskKey" name="" style="width: 70px"
												lay-verify="" placeholder="调整值 " autocomplete="off" value="85"
												class="layui-input">
										</div>%</td>
								    </tr>
								    <tr>
								      <td>乙类职工门特报销比例</td>
								      <td><div class="layui-input-inline">
											<input type="text" id="taskKey" name="" style="width: 70px"
												lay-verify="" placeholder="调整值 " autocomplete="off" value="95"
												class="layui-input">
										</div>%</td>
								    </tr>
								  </tbody>
								</table>
							</div>
			                <div class="layui-tab-item">
			                	<table class="layui-table" lay-skin="line">
								  <thead>
								    <tr><th>参数名称</th><th>基础值</th></tr> 
								  </thead>
								  <tbody>
								    <tr>
								      <td>乙类居民封顶线</td>
								      <td><div class="layui-input-inline">
											<input type="text" id="taskKey" name="" style="width: 70px"
												lay-verify="" placeholder="调整值 " autocomplete="off" value="8500"
												class="layui-input">
										</div></td>
								    </tr>
								    <tr>
								      <td>乙类职工封顶线</td>
								      <td><div class="layui-input-inline">
											<input type="text" id="taskKey" name="" style="width: 70px"
												lay-verify="" placeholder="调整值 " autocomplete="off" value="20000"
												class="layui-input">
										</div></td>
								    </tr>
								  </tbody>
								</table>
			                </div>
			              </div>
			            </div>
			          </div>
			        </div>
			  </div>
              <div class="layui-tab-item">参见药品目录模块
              </div>
              <div class="layui-tab-item">参见项目目录模块
              </div>
              <div class="layui-tab-item">参见材料目录模块
              </div>
              <div class="layui-tab-item">参见特殊疾病目录模块
              </div>
              <div class="layui-tab-item">
					<table class="layui-table" lay-skin="line">
					  <thead>
					    <tr><th>参数名称</th><th>基础值</th></tr> 
					  </thead>
					  <tbody>
					    <tr>
					      <td>三级医院报销比例</td>
					      <td><div class="layui-input-inline">
								<input type="text" id="taskKey" name="" style="width: 70px"
									lay-verify="" placeholder="调整值 " autocomplete="off" value="80"
									class="layui-input">
							</div>%</td>
					    </tr>
					    <tr>
					      <td>二级医院报销比例</td>
					      <td><div class="layui-input-inline">
								<input type="text" id="taskKey" name="" style="width: 70px"
									lay-verify="" placeholder="调整值 " autocomplete="off" value="75"
									class="layui-input">
							</div>%</td>
					    </tr>
					    <tr>
					      <td>一级医院报销比例</td>
					      <td><div class="layui-input-inline">
								<input type="text" id="taskKey" name="" style="width: 70px"
									lay-verify="" placeholder="调整值 " autocomplete="off" value="70"
									class="layui-input">
							</div>%</td>
					    </tr>
					  </tbody>
					</table>
              </div>
            </div>
          </div>
        </div>
      </div>
      
		<input type="hidden" name="scheduledTask.id" id="id">
		<div class="layui-form-item layui-hide">
			<input type="button" lay-submit lay-filter="LAY-cityorg-front-submit"
				id="LAY-cityorg-front-submit" value="确认">
		</div>
	</div>

<script>
	layui.config({
	  base: '<%=request.getContextPath() %>/plugins/layui/layuiadmin/'
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'form','laydate'], function(){
		  var form=layui.form;
		
	  });
	 function child(obj){
			 
	   var cityOrg = JSON.parse(obj);
		$("#id").val(cityOrg["id"]);
		for (var index in cityOrg){
		 $("#"+index).val(cityOrg[index]);
		}
	    var initStartFlag=cityOrg.initStartFlag;
	    $("#initStartFlag").find("option[value ='"+initStartFlag+"']").attr("selected","selected");
	    form.render('select');
			  
	}
</script>
</body>
</html>