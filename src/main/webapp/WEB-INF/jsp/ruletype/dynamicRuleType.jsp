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

<title>规则类型管理</title>
</head>
<body>
<div class="layui-form" lay-filter="layuiadmin-form-useradmin" id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">
<input type="hidden" name="colname" id="colname" />      	
      	<div class="layui-inline">
        	<label class="layui-form-label">选择所需表</label>
        		<div class="layui-input-block" style="width: 200px;">
        			<select name=tableName id="tableName" lay-filter="tableName" >
							<option value="" disabled selected style='display:none;'>选择表</option>
								<option value="T_PICCBID_MEDICAL_REGISTER">就诊记录-就诊登记信息</option>
								<option value="T_PICCBID_MEDICAL">就诊记录-主表</option>
								<option value="T_PICCBID_MEDICAL_DETAIL">就诊记录-明细表</option>
								<option value="T_PICCBID_MEDICAL_ADVICE">就诊记录-医嘱表</option>
								<option value="T_PICCBID_MEDICAL_DIAG">就诊记录-诊断</option>
								<option value="T_PICCBID_MEDICAL_OPERATION">就诊记录-手术表</option>
								<option value="T_PICCBID_SPECSICK_REG">就诊记录-门特备案表</option>
								<option value="T_PICCBID_ZY_MEDICALRECORD">就诊记录-中医病案首页表</option>
								<option value="T_PICCBID_DELETE">就诊数据删除表</option>
								<option value="T_PICCBID_EXAMINE">就诊数据删除代码表</option>
					</select>
        		</div>
        </div>
      	
      	
   		<div class="layui-form-item">
            	<label class="layui-form-label">条件类型</label>
            		<div class="layui-input-block" style="width: 200px;">
						<select name="ruleType.name" lay-filter="ruleType_name" id="name" >
								<option value="" disabled selected style='display:none;'>请选择条件</option>
						</select>
            		</div>
       </div>
          
          <div class="layui-form-item">
            	<label class="layui-form-label">限制条件</label>
            		<div class="layui-input-block" style="width: 200px;">
						<select name="ruleType.xianzhi" id="xianzhi" >
								<option >选择限制条件</option>
									<option value="0"><</option>
									<option value="1">=</option>
									<option value="2">></option>
									<option value="3"><=</option>
									<option value="4">>=</option>
									<option value="5">包含</option>
									<option value="6">不包含</option>
						</select>
            		</div>
          	</div>
          	
          	<div class="layui-inline">
                    	<label class="layui-form-label">具体限制条件</label>
                    	<div class="layui-input-block" style="width: 200px;">
                        <input type="text" name="tiaojian" placeholder="请输入限制条件" autocomplete="off"
                               class="layui-input">
                    	</div>
                	</div>
                	
              <div class="layui-form-item">
            	<label class="layui-form-label">逻辑关系</label>
            		<div class="layui-input-block" style="width: 200px;">
						<select name="ruleType.logic" id="logic" >
								<option >选择条件</option>
									<option value="AND">并且</option>
									<option value="OR">或者</option>
						</select>
            		</div>
          	</div>
       <div class="layui-form-item layui-hide">
      <input type="button" lay-submit lay-filter="LAY-user-front-submit" id="LAY-user-front-submit" value="确认">
    </div>
 	   </div>
  <%-- <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/ruletype/dynamicRuleType.js"></script> --%>
  <script>
  var Layer;
  layui.use(['layer','form'], function(){
 		Layer = layui.layer;
 		var $=layui.$;
 		form = layui.form;
 		//监听select change
 		form.on('select(tableName)', function(data){
			loadSelect(data.value);
 			form.render('select');
 		});
 		form.on('select(ruleType_name)', function(data){
			$("#colname").val(data.elem[data.elem.selectedIndex].text);
 		});
 	});
	
	
	
	//加载表字段名,根据参数为select赋值
	function loadSelect(code){
		 $.getJSON($WEB_ROOT_PATH+'/dhccApi/dict/dict/findDict?dictRequestVo.tableName=logicRule&dictRequestVo.ttName='+code, 
				function(data){
						var form=layui.form;
		     		var  dataList= data.dictList;
		     		for(var i=0 ;i<dataList.length;i++){
		     			var mm="<option text='"+dataList[i].text+"' value='"+dataList[i].value+"'>"+dataList[i].text+"</option>";
		     			$("#name").append(mm); 
		     		}
		     		form.render('select');
		}); 
	}
  </script>
</body>
</html>