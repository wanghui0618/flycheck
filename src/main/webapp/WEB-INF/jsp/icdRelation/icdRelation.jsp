<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp"%>
<link rel="stylesheet"	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/layui/css/layui.css" media="all">
<link rel="stylesheet"	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/style/admin.css" media="all">
<title>ICD数据对应</title>
<style>
.layui-form-label{
width: 60px;
}
.layui-form-item .layui-inline {
    margin-right: 7px;
}
.layui-form-select .layui-input{
width: 120px;
}
.layui-input{
width: 120px;
}
.layui-card-header.layuiadmin-card-header-auto{
padding-top: 0px;
}
.layui-form-item{
margin-top: -20px;
}
</style>
</head>
<body>
	<div class="layui-fluid" style="">
		<div class="layui-row layui-col-space15">
<!-- 左对照表 -->
			<div class="layui-col-md6">
				<div class="layui-card">
					<div style="margin-left:0px;padding: 0px;" class="layui-form layui-card-header layuiadmin-card-header-auto">
					<div class="layui-inline " id="layui-card-header" style="height: 25px; width: 300px;margin-top: -10px;line-height: 30px; font-weight: 600;"></div>
					<div class="layui-form-item" >
						<div class="layui-inline" style="left:0px;">
							<label class="layui-form-label ">ICD版本</label>
							<div class="layui-input-inline" style="width:120px;">
								<select name="icd.typeName" id="typeName" lay-filter="typeName" lay-search="">
									<option value="" disabled selected style='display: none;'>请选择版本</option>
								</select>
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">ICD编码</label>
							<div class="layui-input-inline" style="width: 120px;">
							    <input type="text" name="icd.code" placeholder="请输入ICD编码" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">ICD名称</label> 
							<div class="layui-input-inline" style="width: 120px;">
								<input type="text" name="icd.name" placeholder="请输入ICD名称" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-inline" style="margin-right: 0px;margin-bottom: 5px;">
							<button id="icdRelation-search-left"
								class="layui-btn layuiadmin-btn-useradmin "
								lay-submit lay-filter="city-search">
								<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
							</button>
						</div>
					</div>
					</div>
					<div class="layui-card-body">
						<table id="icdTable" class="layui-hide" lay-filter="icdTable"></table>
					</div>
				</div>
			</div>
<!-- 右对照表 -->
			<div class="layui-col-md6">
				<div class="layui-card">
					<div style="margin-left:0px;padding: 0px;" class="layui-form layui-card-header layuiadmin-card-header-auto">
						<div class="layui-inline " id="layui-card-header-s" value="国家临床V2.0 ICD-10"
					style="height: 25px; line-height: 30px;margin-top: -10px; font-weight: 600;">国家临床V2.0
						ICD-10</div>
					<div class="layui-form-item" >
						<div class="layui-inline" style="left:0px!important;">
							<label class="layui-form-label ">ICD版本</label>
							<div class="layui-input-inline" style="width:120px;">
								<select name="icd.typeName" id="typeName2"
									lay-filter="typeName2" lay-search="">
									<option value="" disabled selected style='display: none;'>请选择版本名</option>
								</select>
							</div>
						</div>
						<div class="layui-inline" >
							<label class="layui-form-label">ICD编码</label>
								<div class="layui-input-inline" style="width: 120px;">
							<input type="text"  name="icd.code" placeholder="请输入ICD编码" autocomplete="off" class="layui-input">
						</div>
						</div>
						<div class="layui-inline" >
							<label class="layui-form-label">ICD名称</label> 
							<div class="layui-input-inline" style="width: 120px;" >
							<input type="text" name="icd.name" placeholder="请输入ICD名称" autocomplete="off" class="layui-input">
						</div>
						</div>
						<div class="layui-inline" style="margin-right: 0px;margin-bottom: 5px;">
							<button id="LAY-user-front-search-to"
								class="layui-btn layuiadmin-btn-useradmin"
								lay-submit lay-filter="LAY-user-front-search-to">
								<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
							</button>
						</div>
					</div>
					</div>
					<div class="layui-card-body">
						<table id="icdTableTo" class="layui-hide" lay-filter="icdTableTo"></table>
						<script type="text/html" id="table-useradmin-webuser">
          					<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="findrelation"><i class="layui-icon layui-icon-search"></i>查询关联</a>				
                   </script>
					</div>
				</div>
			</div>
			<div class="layui-col-md12" style="padding-top: 0px; padding-bottom: 0px;">
				<div class="layui-card">
					<div class="layui-card-body"
						style="height: 40px; text-align: center;">
						<div class="layui-inline" style="margin-right: 0px;margin-bottom: 0px;">
						<button id='icdRelation-relatedAtOnceTime'
							class="layui-btn layuiadmin-btn-useradmin"
							data-type="allrelation" style="margin: 0 10px 0 0;"
							lay-filter="LAY-user-front-search-relation">
							一键关联<i
								class="layui-icon layui-icon-lianjie layuiadmin-button-btn"></i>
						</button>
						<button id='icdRelation-related' style="margin: 0 10px 0 0;"
							class="layui-btn layuiadmin-btn-useradmin " data-type="relation"
							lay-filter="LAY-user-front-search-relation">
							关&nbsp;&nbsp;&nbsp;联<i
								class="layui-icon layui-icon-lianjie layuiadmin-button-btn"></i>
						</button>
						<button id='icdRelation-breakRelated' style="margin: 0 10px 0 0;"
							class="layui-btn layuiadmin-btn-useradmin "
							data-type="breakrelation"
							lay-filter="LAY-user-front-search-breakrelation">
							解除关联<i
								class="layui-icon layui-icon-lianjie layuiadmin-button-btn"></i>
						</button>
						<button id='icdRelation-showAllRelated' style="margin: 0 10px 0 0;"
							class="layui-btn layuiadmin-btn-useradmin" href="javascript:;"
							lay-href="<%=request.getContextPath()%>/icdRelation/icdRelationShow"
							lay-tips="显示全部关联数据">
							显示全部已关联<i class="layui-icon layui-icon-lianjie layuiadmin-button-btn"></i>
						</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/icdRelation/icdRelation2.js"></script>

</body>
</html>