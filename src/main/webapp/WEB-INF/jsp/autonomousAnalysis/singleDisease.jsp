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

<title>单病种费用分析</title>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-card">
      <div class="layui-form layui-card-header layuiadmin-card-header-auto">
        <div class="layui-form-item">
        			<div class="layui-inline">
				            <label class="layui-form-label">就诊类别</label>
				            <div class="layui-input-inline">
				                <select name="class1" lay-verify="">
				                	  <option value="1">住院</option>
									  <option value="2">门诊</option>
									  <option value="3">门特</option>
								</select>   
				            </div>
				          </div>
                    <div class="layui-inline">
		                 <label class="layui-form-label">疾病名称</label>
				            <div class="layui-input-inline">
				                <input type="text" name="level" required  autocomplete="off" class="layui-input">    
				            </div>
				          </div>
				             选择年份
                    <div class="layui-inline">
		                <div class="layui-input-inline">
		                    <input type="text" name="inFlag" style=""  class="layui-input" id="test10" placeholder="yyyy" readonly="true">
		                </div>
            		</div>
					<button class="layui-btn layuiadmin-btn-useradmin" lay-submit lay-filter="LAY-org-front-search">
                          	<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
                    </button>  
               </div> 
		      </div> 
		      <div class="layui-card-body">
		         <table id="userTable" class="layui-hide" lay-filter="userTable">
				         <tbody>
				        <script type="text/html" id="xuhao">
   								{{d.LAY_TABLE_INDEX+1}}
						</script>
				        </tbody>
		        </table> 
		      </div>
    </div>
    </div>
  <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/autonomousAnalysis/singleDisease.js"></script>
</body>
</html>