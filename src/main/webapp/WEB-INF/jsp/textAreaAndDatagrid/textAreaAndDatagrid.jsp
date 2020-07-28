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
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css" media="all">
<title>文本域和表格</title>
</head>
<style>
.xxvalue input{
    width:72px;
}
.limittime input{
    width:72px;
}
</style>
<body>
	<div class="layui-fluid">
		<div class="layui-card">
			<div class="layui-form layui-card-header layuiadmin-card-header-auto">
				<div class="layui-form-item">
					<div class="layui-inline ">
		                <label class="layui-form-label ">就诊类型</label>
		                <div class="layui-input-inline xxvalue" style="width:72px">
		                    <select id="dialogType" name="dialogType" lay-filter="dialogType" >
		                        <option value="mz" selected>门诊</option>
		                        <option value="zy">住院</option>
	                    	</select>
		                </div>
            		</div>
					<div class="layui-inline ">
					 	<div class="layui-input-inline xxvalue" style="width:72px;margin-left:10px;">
		                  <select id="costOrnum" name="costOrnum" lay-filter="costOrnum" >
		                        <option value="cost" selected>价格</option>
		                        <option value="num">数量</option>
		                   </select>
	                	</div>
		                <div class="layui-input-inline" id="xzxztj" style="width:85px;margin-left:-5px;">
		                   <select id="xzzt" name="sxtj" lay-filter="xzzt" >
		                        <option value="dey" selected>等于</option>
		                        <option value="dy">大于</option>
		                        <option value="xy">小于</option>
		                    </select>
		                </div>
		                <div class="layui-input-inline xxvalue " style="width:72px">
		                   <input type="text" id="xzvalue" name="xzvalue" placeholder="请输入" autocomplete="off" class="layui-input" style="width:72px">
		                </div>
            	</div>
            	<div class="layui-inline ">
	                <label class="layui-form-label ">出现条件</label>
	                <div class="layui-input-inline xxvalue" style="width:72px">
	                    <select id="limittime" name="limittime" lay-filter="limittime" >
	                        <option value="tc" selected>同次</option>
	                        <option value="tt">同天</option>
                    	</select>
	                </div>
            	</div>
				<div class="layui-inline">
					<button id="consumableRule-search" class="layui-btn layuiadmin-btn-useradmin" lay-submit lay-filter="LAY-user-front-search" stylename="search">
						<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
					</button>
				</div>

				</div>

			</div>

			<div class="layui-card-body">
				<div class="layui-container">
					<div class="layui-row">
						<div class=" layui-col-md4">
							<textarea name="wby" rows="5" required lay-verify="required" placeholder="请输入" class="layui-textarea" style="width:96%;min-height:480px"></textarea>
						</div>
						<div class="layui-col-md8">
						 	<table id="tableTest" class="layui-hide" lay-filter="tableTest"></table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
layui.config({
    base: $WEB_ROOT_PATH+'/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table'], function(){
    var $ = layui.$
    ,form = layui.form
    ,table = layui.table;
    table.render({
        elem: '#tableTest'
        ,url: $WEB_ROOT_PATH+''
        ,height: tableHeight-11
        ,cols: [[
            {type: 'numbers', title: '序号' }
            ,{field:'id', width:80,hide:true,title: '编号'}
            ,{field:'orgCode', width:110,title: '机构编码'}
            ,{field:'orgName',width:220, title: '机构名称'}
            ,{field:'orgKind',width:120, title: '服务机构类型'}
        ]]
        ,page: true
    });
    
});
</script>
</body>
</html>