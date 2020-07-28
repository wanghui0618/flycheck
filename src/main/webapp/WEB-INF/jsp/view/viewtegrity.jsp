<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css" media="all">
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui//layuiadmin/style/admin.css" media="all">
<title>医院上传数量统计</title>
</head>
<body style="">
<<div class="layui-fluid">
  <div class="layui-card">
          <div class="layui-card-header" style="border-bottom: 1px solid #f6f6f6;">
  					<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
  					医院上传完整性统计
  				</div>
		   <table id="tegrityTable" class="layui-hide" lay-filter="tegrityTable"></table>
     </div>
    </div>
		<script type="text/javascript" ">
		//
		layui.config({
			    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
			  }).extend({
			    index: 'lib/index' //主入口模块
			  }).use(['index', 'table'], function(){
			    var $ = layui.$
			    ,form = layui.form
			    ,table = layui.table;
			
			    table.render({
			    	elem: '#tegrityTable'
			        ,url: $WEB_ROOT_PATH+'/dhccApi/medical/medical/Tegrity'
			        ,cellMinWidth: 80
			        ,height: 420
			        ,cols: [[
					
					{field:'qualityName',align:'center', title: '医院名称' }
					,{field:'tegrity', align:'center',title: '完整数据量' }
					,{field: 'untegrity',align:'center', title: '完整率',
						templet: function(d){
							var codex = d.untegrity;
							var codex1 = d.tegrity;
							codex=parseInt((codex/codex1)*100);
							return '<span>'+ codex +"%"+'</span>'
					}
					}
			            ]]
			            ,page: true
			          });
		
			  });
	</script>
</body>
</html>