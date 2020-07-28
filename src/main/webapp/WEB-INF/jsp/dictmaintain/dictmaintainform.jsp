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
<title>基础字典新增/修改</title>
<style>
    .layui-form-select dl { max-height:150px; }
</style>
</head>
<body>
	<div style="padding: 20px 0 0 20px;" class="layui-form" lay-filter="layuiadmin-form-useradmin" id="layuiadmin-form-useradmin">
		<input type="hidden" name="dictmaintain.id" id="id">
		<!-- <div class="layui-form-item">
			
			<div class="layui-input-inline " style="width: 180px;"
				onblur="getSum2()">
				<label class="layui-form-label ">统筹区名称</label>
				<select name="dictmaintain.handdingInsCode" id="cityName" lay-filter="city" lay-search=""  style="height:10px">
					<option value="" disabled selected style='display: none;'>请选择统筹区</option>
				</select>
			</div>
			
			<div class="layui-input-inline " style="width: 180px;"
				onblur="getSum3()">
				<label class="layui-form-label ">机构名称</label>
				<select name="dictmaintain.hosName" id="hosName" lay-filter="hos"
					lay-verify="" lay-search  size="number">
					<option value="" disabled selected style='display: none;'>请选择医院</option>
				</select>
			</div>
			<input type="hidden" readonly id="orgCode"
				name="dictmaintain.orgCode" lay-verify="" placeholder="请输入"
				autocomplete="off" class="layui-input">

			<div class="layui-form-item">
				<label class="layui-form-label">名称</label>
				<div class="layui-input-inline" style="width: 180px;">
					<input type="text" id="name" name="dictmaintain.name"
						lay-verify="required" placeholder="请输入" autocomplete="off"
						class="layui-input">
				</div>
				<label class="layui-form-label">值</label>
				<div class="layui-input-inline" style="width: 180px;">
					<input type="text" id="value" name="dictmaintain.value"
						lay-verify="" placeholder="请输入" autocomplete="off"
						class="layui-input">
				</div>


			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">业务类型</label>
				<div class="layui-input-inline" style="width: 180px;">
					<input type="text" id="type" name="dictmaintain.type" lay-verify=""
						placeholder="请输入" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item layui-hide">
				<input type="button" lay-submit lay-filter="LAY-user-front-submit"
					id="LAY-user-front-submit" value="确认">
			</div>
		</div> -->

					<div class="layui-form">
						<div class="layui-form-item" >
							<div class="layui-inline " onblur="getSum2()">
								<label class="layui-form-label "  style="width: 87px;">统筹区名称 </label>
								<div class="layui-input-inline ">
									<select name="dictmaintain.handdingInsCode" id="cityName"
										lay-filter="city" lay-search="" style="height: 10px">
										<option value="" disabled selected style='display: none;'>请选择统筹区</option>
									</select>
								</div>
							</div>

							<div class="layui-inline " onblur="getSum3()">
								<label class="layui-form-label ">机构名称</label>
								<div class="layui-input-inline ">
									<select name="dictmaintain.hosName" id="hosName"
										lay-filter="hos" lay-verify="" lay-search size="number">
										<option value="" disabled selected style='display: none;'>请选择医院</option>
									</select>
								</div>
							</div>
						</div>
						<div class="layui-form-item" >
							<input type="hidden" readonly id="orgCode"
								name="dictmaintain.orgCode" lay-verify="" placeholder="请输入"
								autocomplete="off" class="layui-input">

							<div class="layui-form-item">
								<div class="layui-inline">
									<label class="layui-form-label"style="width: 87px;">名称</label>
									<div class="layui-input-inline" >
										<input type="text" id="name" name="dictmaintain.name"
											lay-verify="required" placeholder="请输入" autocomplete="off"
											class="layui-input">
									</div>
								</div>
								<div class="layui-inline">
									<label class="layui-form-label">值</label>
									<div class="layui-input-inline">
										<input type="text" id="value" name="dictmaintain.value"
											lay-verify="" placeholder="请输入" autocomplete="off"
											class="layui-input">
									</div>
								</div>
							</div>

						</div>
						<div class="layui-form-item" >
						<div class="layui-inline">
							<label class="layui-form-label" style="width: 87px;">业务类型</label>
							<div class="layui-input-inline" >
								<input type="text" id="type" name="dictmaintain.type"
									lay-verify="" placeholder="请输入" autocomplete="off"
									class="layui-input">
							</div>
							</div>
						</div>
						<div class="layui-form-item layui-hide">
							<input type="button" lay-submit
								lay-filter="LAY-user-front-submit" id="LAY-user-front-submit"
								value="确认">
						</div>
					</div>
		</div>


		<%-- <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/dictmaintain/dictmaintainform.js"></script> --%>
		<script>
  var form;
  layui.config({
	    base: '<%=request.getContextPath() %>/plugins/layui/layuiadmin/'
	  }).extend({
	    index: 'lib/index'   //主入口模块
	  }).use(['index', 'form'], function(){
		   form=layui.form;
		   
		   
	  })
  var handdingInsName;
  var cityName="1";
  function child(obj){
	  var dictmaintain = JSON.parse(obj);
	  $("#id").val(dictmaintain["id"]);
	  for (var index in dictmaintain){
	      $("#"+index).val(dictmaintain[index]);
	      //console.log( $("#"+index).val());
	  }
	  handdingInsName=dictmaintain.handdingInsCode;
	  orgCode=dictmaintain.orgCode;
	 // $("#hosName").val(dictmaintain.orgName);
	 SelectJiGou(dictmaintain.orgCode);
	 loadSelect(dictmaintain.handdingInsCode);
	/*  $("#hosName option[text='"+dictmaintain.orgName+"']").attr("selected", true);
	 form.render('select'); */
	 
  }
  
//加载城市下拉字典,根据参数为select赋值
	function loadSelect(code){
	
		 $.getJSON($WEB_ROOT_PATH+'/dhccApi/dict/dict/findDict?dictRequestVo.tableName=t_piccbid_dictCity', 
					function(data){
	 					var form=layui.form;
			     		var  dataList= data.dictList;
			     		for(var i=0 ;i<dataList.length;i++){
			     			if(dataList[i].value == code){
			     				var mm="<option value='"+dataList[i].value+"' selected='selected'>"+dataList[i].text+"</option>";
			     			}else{
			     				var mm="<option value='"+dataList[i].value+"'>"+dataList[i].text+"</option>";
			     			}
			     			
			     			$("#cityName").append(mm); 
			     		}
			     		form.render('select');
			     	/* 	if(cityName != "1"){
			     		 $("#cityName option[value='"+cityName+"']").attr("selected", true);
			     		} */
			     		form.render('select'); 
	}); 
	}
	//机构select
	function SelectJiGou(a){
		var p = $(this).find('select[lay-filter=city]');
		 // var c = $(this).find('select[lay-filter=hos]');
		  var h = $('#hosName');
		  var dataList=null; 
		 var value =  a;
		 if(value!=""||value!= null){
			 h.html('');
		 $.ajax({
		    	url : $WEB_ROOT_PATH+"/dhccApi/cityorg/dictCityOrg/listVo1?value="+value,
		    	type : "get",
		    	success : function(data1) {	
		    		var form=layui.form;
		    		dataList = data1;
		    		if(dataList.length != 0){
		    			var mm = "<option value='' disabled selected style='display:none;'>请选择医院</option>";
		    			h.append(mm);
		    			for (var i in dataList) {
		    				if(dataList[i].orgCode == value){
		    					 var mm = "<option value='"+dataList[i].orgCode+"' selected='selected'>"+dataList[i].orgName+"</option>";
			     			}else{
			     				 var mm = "<option value='"+dataList[i].orgCode+"'>"+dataList[i].orgName+"</option>";
			     			}
			    	         
			    	        //  c.append(mm);
			    	          h.append(mm);
			    	    }
		    			
		    		}else{
		    			var mm = "<option value='' disabled selected style='display:none;'>无数据</option>";
		    			h.append(mm);
		    		}
		    		form.render('select');
		    	},
		    	error : function(data){
		    		
		    	}
		    });
		 
		 }
	}
  </script>
		<script>
  function getSum2(){
	  var p = $(this).find('select[lay-filter=city]');
	 // var c = $(this).find('select[lay-filter=hos]');
	  var h = $('#hosName');
	  var dataList=null; 
	 var value =  document.getElementById("cityName").value;
	 if(value!=""||value!= null){
		 h.html('');
		 $.ajax({
		    	url : $WEB_ROOT_PATH+"/dhccApi/cityorg/dictCityOrg/listVo1?value="+value,
		    	type : "get",
		    	success : function(data1) {
		    		var form=layui.form;
		    		dataList = data1;
		    		if(dataList.length != 0){
		    			var mm = "<option value='' disabled selected style='display:none;'>请选择医院</option>";
		    			h.append(mm);
		    			for (var i in dataList) {
			    	          var mm = "<option value='"+dataList[i].orgCode+"'>"+dataList[i].orgName+"</option>";
			    	        //  c.append(mm);
			    	          h.append(mm);
			    	    }
		    		}else{
		    			var mm = "<option value='' disabled selected style='display:none;'>无数据</option>";
		    			h.append(mm);
		    		}
		    		form.render('select');
		    	},
		    	error : function(data){
		    		
		    	}
		    });
	 }
  }
  
  function getSum3(){
	  var h = document.getElementById("hosName").value;
	  if(h != "" || h != null){
		  $('#orgCode').val(h);
	  }
  }
  
  </script>
</body>
</html>