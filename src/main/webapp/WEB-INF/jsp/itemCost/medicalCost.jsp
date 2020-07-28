<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
<%@include file="/WEB-INF/jsp/common/easyui.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css" media="all">
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css" media="all">
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/login.css" media="all">
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/tree/css/zTreeStyle.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/dictdiag/js/jquery.ztree.all.js"></script>
<title>药品金额统计</title>
</head>
<body style="overflow: hidden">
<div class="layui-fluid" style="overflow: hidden">
  <div class="layui-row layui-col-space15">
  	<div class="layui-col-md12">
  	<div class="layui-col-md12" style="padding-left: 2px;padding-right: 5px;">
      <div class="layui-card">
		<div class="layui-form layui-card-header layuiadmin-card-header-auto">
          <div class="layui-form-item">

            <div class="layui-inline">
              <label class="layui-form-label" style="width:70px;">药品名称</label>
              <div class="layui-input-block" style="margin-left:80px;">
                <input type="text" id="medicalName" name="CostStatisticsVo.medicalName" placeholder="请输入" autocomplete="off" class="layui-input">
              </div>
            </div>
            
            <div class="layui-inline">
				<label class="layui-form-label">就诊类型</label>
					<div class="layui-input-block">
        				<select   id="diagType" name="CostStatisticsVo.diagType" lay-filter="diagType">
     			 			<option value="">请选择</option>
     			 			<option value="1">住院</option>
     			 			<option value="2">普通门诊</option>
     			 			<option value="9">门诊大病</option>
     			 			<option value="0">其他</option>
      	  	             </select>
                     </div>
			</div>
			
			<div class="layui-inline">
				<label class="layui-form-label">报销级别</label>
					<div class="layui-input-block">
        				<select   id="applyPayLevel" name="CostStatisticsVo.applyPayLevel" lay-filter="applyPayLevel">
     			 			<option value="">请选择</option>
     			 			<option value="甲类">甲类</option>
     			 			<option value="乙类">乙类</option>
     			 			<option value="丙类">丙类</option>
      	  	             </select>
                     </div>
			</div>
			
			<div class="layui-inline">
            <label class="layui-form-label" style="width:80px;">统筹区</label>
            <div class="layui-input-inline">
                 <input id="getHanddingName" name="CostStatisticsVo.handdingInsName"/>
                 <input type="text" id="handdingCode" name="CostStatisticsVo.handdingInsCode" style="display: none;" />
            </div>
          </div>
          
           <div class="layui-inline">
            <label class="layui-form-label" style="width:80px;">医疗机构</label>
            <div class="layui-input-inline">
                 <input id="getOrgName" name="CostStatisticsVo.orgName"/>
                 <input type="text" id="orgCode" name="CostStatisticsVo.orgCode" style="display: none;" />
            </div>
          </div>

			<div class="layui-inline" >
              <button class="layui-btn layuiadmin-btn-useradmin" lay-submit lay-filter="LAY-user-front-search">
                <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询
              </button>
            </div>
				
          </div>
        </div>
        </div>
        </div>
        </div>
        
    <div class="layui-col-md12">
    <div class="layui-col-md6" style="padding-left: 2px;padding-right: 5px;">
      <div class="layui-card">
        
        <div class="layui-card-body">
          <table id="medicalTable" class="layui-hide" lay-filter="medicalTable"></table>
        </div>
      </div>
      </div>
      
      <div class="layui-col-md6" style="padding-left: 2px;padding-right: 5px;">
      	<div class="layui-card">
			<div class="layui-card-header" style="font-size: 14px;border-bottom: 1px solid #f6f6f6;">
	  			<img style="margin-top:-2px;padding-right: 8px;" src="<%=request.getContextPath() %>/images/auditing/mark.png"  />
	  			用量Top10医院
	  		</div>
		    <div id=class="layui-card-body" style="color:#3D3D3D">
		    	<div id="mainTwo" style="height:535px;width:730px"></div>
		    </div>
	  	</div>
      </div>
      
    </div>
  
  </div>
</div>
<script src="<%=request.getContextPath() %>/js/echarts_jpp/echarts-2.2.7/build/dist/echarts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/itemCost/medicalCost.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/common/orgDictSelect.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/common/handdingDictSelect.js"></script>
</body>
</html>