<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
 	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
<title>分解住院（限7天）/住院天数异常</title>
<style type="text/css">
.layui-table-cell>span{
text-align: center;
display: block;
}
.layui-fluid {
    padding: 10px;
}
.layui-card-header.layuiadmin-card-header-auto {
    padding-top: 5px;
    padding-bottom: 0;
    height: auto;
}
.layui-card-body {
    position: relative;
    padding: 5px 10px;
    line-height: 24px;
}
</style>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-card">
      <div class="layui-form layui-card-header layuiadmin-card-header-auto">
        <div class="layui-form-item layui-row">
          <div class="layui-inline layui-col-md3">
            <label class="layui-form-label">是否违规</label>
            <div class="layui-input-block">
               <select name="medicalRecord.ilegalInHosp" id="ilegalInHosp" >
		        <option value="">全部</option>
              	<option value="0">0-未违规</option>
                <option value="1">1-明确违规</option>
                <option value="2">2-疑似违规</option>
		      </select>
            </div>
          </div>
          <div class="layui-inline layui-col-md3">
            <label class="layui-form-label">案件编号</label>
            <div class="layui-input-block">
              <input type="text" id="caseNo" name="medicalRecord.caseNo" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
          </div>
          <div class="layui-inline layui-col-md3">
            <label class="layui-form-label">身份证号</label>
            <div class="layui-input-block">
              <input type="text" id="idNo" name="medicalRecord.idNo" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
          </div>
          <div class="layui-inline layui-col-md2" >
	            <button class="layui-btn layuiadmin-btn-useradmin" style="margin-top:-6px;" lay-submit lay-filter="LAY-user-front-search">
	              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
	                                   查询
	            </button>
          </div>
        </div>
      </div>
      
      <div class="layui-card-body">
        <table id="dg" class="layui-hide" lay-filter="dg"></table>
        <script type="text/html" id="toolbar">
          <a class="layui-btn layui-btn-xs" lay-event="detail"><i class="layui-icon layui-icon-tips"></i>查看</a>
		  {{#  if(d.ilegalInHosp == '0' ){ }}
    		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="markToViolation"><i class="layui-icon layui-icon-edit"></i>标记为违规</a>
  		  {{#  } }}
 		  {{#  if(d.ilegalInHosp =='1' ){ }}
    		<a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="markToNormal"><i class="layui-icon layui-icon-edit"></i>标记为正常</a>
  		  {{#  } }}
 		  {{#  if(d.ilegalInHosp =='2' ){ }}
			<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="markToViolation"><i class="layui-icon layui-icon-edit"></i>标记为违规</a>
    		<a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="markToNormal"><i class="layui-icon layui-icon-edit"></i>标记为正常</a>
  		  {{#  } }}
		</script>
      </div>
    </div>
  </div>
  <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/decomposeHosp/decomposeHosp.js"></script>
</body>
</html>