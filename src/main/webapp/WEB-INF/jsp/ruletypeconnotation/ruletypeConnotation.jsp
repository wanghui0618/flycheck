<%@ page language="java" import="java.util.*"
         contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css"
          media="all">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css" media="all">
    <title>角色</title>
    <style>
        .layadmin-iframe {
            position: relative;
            width: 100%;
            height: 100%;
            left: 0;
            top: 0;
            right: 0;
            bottom: 0;
        }
        .layui-col-space15 > * {
		    padding: 1px;
		}
		.layui-nav .layui-nav-item a {
		    color: #000;
		}
    </style>
</head>
<body>
<div class="layui-fluid" style="overflow-y: hidden">
	<div class="layui-card">
	    <div class="layui-row layui-col-space15" style="background: #F2F2F2;">
	        <div class="layui-col-md2">
	            <ul class="layui-nav layui-nav-tree layui-nav-side" style="width:16%;background-color: #fff;color:#000;margin:8px;" lay-filter="demo">
                    <li class="layui-nav-item"><a href="javascript:jump('<%=request.getContextPath()%>/outprice/outpriceinfo');"><i class="layui-icon layui-icon-app" style="font-size: 20px; color: #000;padding-right:10px"></i>超限额收费</a></li>
	                <li class="layui-nav-item"><a href="javascript:jump('<%=request.getContextPath()%>/outfrequency/outfrequencyinfo');"><i class="layui-icon layui-icon-app" style="font-size: 20px; color: #000;padding-right:10px"></i>超频次收费</a></li>
	                <li class="layui-nav-item"><a href="javascript:jump('<%=request.getContextPath()%>/outintension/outintensioninfo');"><i class="layui-icon layui-icon-app" style="font-size: 20px; color: #000;padding-right:10px"></i>项目内涵收费</a></li>
                    <li class="layui-nav-item"><a href="javascript:jump('<%=request.getContextPath()%>/limitTreatment/limitTreatment');"><i class="layui-icon layui-icon-app" style="font-size: 20px; color: #000;padding-right:10px"></i>材料审查</a></li>
                    <li class="layui-nav-item"><a href="javascript:jump('<%=request.getContextPath()%>/outhospday/outhospday');"><i class="layui-icon layui-icon-app" style="font-size: 20px; color: #000;padding-right:10px"></i>超住院天数收费</a></li>
                    <li class="layui-nav-item"><a href="javascript:jump('<%=request.getContextPath()%>/outtreatment/outtreatment');"><i class="layui-icon layui-icon-app" style="font-size: 20px; color: #000;padding-right:10px"></i>超限定疗程</a></li>
                    <li class="layui-nav-item"><a href="javascript:jump('<%=request.getContextPath()%>/resolveFee/resolveFee');"><i class="layui-icon layui-icon-app" style="font-size: 20px; color: #000;padding-right:10px"></i>分解收费</a></li>
                    <li class="layui-nav-item"><a href="javascript:jump('<%=request.getContextPath()%>/indicationTreat/indicationTreat');"><i class="layui-icon layui-icon-app" style="font-size: 20px; color: #000;padding-right:10px"></i>诊疗项目限适应症</a></li>
                    <li class="layui-nav-item"><a href="javascript:jump('<%=request.getContextPath()%>/matchCheck/narcosisFee');"><i class="layui-icon layui-icon-app" style="font-size: 20px; color: #000;padding-right:10px"></i>麻醉项目价格审核</a></li>
                    <li class="layui-nav-item"><a href="javascript:jump('<%=request.getContextPath()%>/matchCheck/jointOper');"><i class="layui-icon layui-icon-app" style="font-size: 20px; color: #000;padding-right:10px"></i>联合手术项目审核</a></li>
                    <li class="layui-nav-item"><a href="javascript:jump('<%=request.getContextPath()%>/matchCheck/outMatch');"><i class="layui-icon layui-icon-app" style="font-size: 20px; color: #000;padding-right:10px"></i>项目与项目匹配</a></li>
                    <li class="layui-nav-item"><a href="javascript:jump('<%=request.getContextPath()%>/excessiveTreat/excessiveTreat');"><i class="layui-icon layui-icon-app" style="font-size: 20px; color: #000;padding-right:10px"></i>过度检查</a></li>
	            	<li class="layui-nav-item"><a href="javascript:jump('<%=request.getContextPath()%>/repeatfee/repeatfee');"><i class="layui-icon layui-icon-app" style="font-size: 20px; color: #000;padding-right:10px"></i>重复收费</a></li>
	            	<li class="layui-nav-item"><a href="javascript:jump('<%=request.getContextPath()%>/narcosis/narcosis');"><i class="layui-icon layui-icon-app" style="font-size: 20px; color: #000;padding-right:10px"></i>麻醉项目审核</a></li>
	            </ul>
	        </div>
	        <div class="layui-col-md10">
	            <!-- 填充内容 -->
	            <iframe id="iframe-body" src="" frameborder="0" class="layadmin-iframe"></iframe>
	        </div>
	    </div>
    </div>
</div>

<script>
    layui.config({
        base: $WEB_ROOT_PATH + '/plugins/layui/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'table','element'], function () {
        var $ = layui.$
            , form = layui.form
            , table = layui.table
            ,element=layui.element;
        var element = layui.element;
        document.getElementById("iframe-body").style.height=document.documentElement.clientHeight+"px";
        $("#iframe-body").attr("src","<%=request.getContextPath()%>/city/cityinfo");
    });
    function jump(url){
       	$("#iframe-body").attr("src",url);
    }
</script>
</body>
</html>