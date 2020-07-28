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
	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/layui/css/layui.css"
	media="all">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/layui/layuiadmin/style/admin.css"
	media="all">
<style>
.rule_div {
	float: left;
	width: 70%;
	height: 75px;
	margin-left: 1%;
	background: #ECE3D8;
}

.rule_img_delete {
	margin-left: 5px;
	margin-top: -15px;
}

.rule_div_left {
	float: left;
	background: #009688;
	padding: 3px;
	margin: 0px 15px;
	margin-top: 0px;
	line-height: 20px;
	border-radius: 3px;
	color: #fff;
	margin-top: 2px;
}

.rule_div_right {
	line-height: 20px;
	float: left;
	background: #fff;
	padding: 5px 0px 5px 5px;
}
</style>
<title>规则类型添加/修改</title>
</head>
<body onload="getSome1()">
	<div class="layui-tab layui-tab-brief" lay-filter="demo">
		<ul class="layui-tab-title">
			<li class="layui-this">编辑规则</li>
			<li>规则详情</li>
			<li>规则明细维护</li>
		</ul>
		<div class="layui-tab-content">
			<div class="layui-tab-item layui-show ">
				<div class="layui-card" style="height: 360px;">
					<div class="layui-card-body">
						<div class="layui-form" lay-filter="layuiadmin-form-useradmin"
							id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">
							<input type="hidden" name="ruleType.id" id="id">
<!-- 第一行 -->
							<div class="layui-form-item">
								<div class="layui-inline">
									<label class="layui-form-label">规则编号</label>
									<div class="layui-input-inline">
										<!-- onkeyup="this.value=this.value.replace(/[^0-9-]+/,'');" -->
										<input type="text" id="ruleNo" name="ruleType.ruleNo"
											lay-verify="number" placeholder="请输入编号" autocomplete="off"
											class="layui-input">
									</div>
								</div>
								<div class="layui-inline">
									<label class="layui-form-label">规则名称</label>
									<div class="layui-input-inline">
										<input type="text" id="ruleName" name="ruleType.ruleName"
											lay-verify="required" placeholder="请输入" autocomplete="off"
											class="layui-input">
									</div>
								</div>
								<div class="layui-inline">
						<label class="layui-form-label">规则分类</label>
						<div class="layui-input-inline" ">
						<select name=ruleType.ruleCatagory lay-search="" id='ruleCatagory'>
									<option></option>
									<option value="0" selected="selected">违规</option>
									<option value="1">疑似违规</option>
								</select>
                    	</div>
					</div>
							</div>
							<!-- 第2行 -->
							<div class="layui-form-item" style="width: 800px;">
								<div class="layui-inline" layer-filter="validFlag">
									<label class="layui-form-label">是否生效</label>
									<div class="layui-input-inline" id="validFlag">
										<input type="radio" name="ruleType.validFlag" value="1"
											title="有效" checked> <input type="radio"
											name="ruleType.validFlag" value="0" title="无效">
									</div>
								</div>
							</div>

							<!-- 第3行 -->
							<div class="layui-form-item" style="width: 800px;">
								<div class="layui-inline" layer-filter="diagType11">
									<label class="layui-form-label">类型</label>
									<div class="layui-input-inline" id="diagType11"
										style="width: auto;">
										<input type="checkbox" name="ruleType.diagType11" value="2"
											title="事后" checked> <input type="checkbox"
											name="ruleType.diagType11" value="1" title="事中"> <input
											type="checkbox" name="ruleType.diagType11" value="0"
											title="事前">
									</div>
								</div>
							</div>
							<!-- 第4行 -->
							<div class="layui-form-item" style="width: 800px;">
								<div class="layui-inline" layer-filter="diagType">
									<label class="layui-form-label" style="width: 150px;">规则使用业务类别</label>
									<div class="layui-input-inline" id="diagType"
										style="width: auto;">
										<input type="radio" name="ruleType.diagType" value="2"
											title="全部" lay-skin="primary" checked> <input
											type="radio" name="ruleType.diagType" value="1" title="门诊"
											lay-skin="primary"> <input type="radio"
											name="ruleType.diagType" value="0" title="住院"
											lay-skin="primary">
									</div>
								</div>
							</div>
							<!-- 第5行 -->
							<div class="layui-form-item" style="width: 800px;">
								<div class="layui-form-item">
									<div class="layui-inline" layer-filter="ruleProperty">
										<label class="layui-form-label" style="width: 150px;">规则适用类型</label>
										<div class="layui-input-inline" id="ruleProperty">
											<input type="radio" name="ruleType.ruleProperty" value="1"
												title="明细" lay-skin="primary" checked> <input
												type="radio" name="ruleType.ruleProperty" value="0"
												title="主病例" lay-skin="primary">
										</div>
									</div>
								</div>
<!-- 第6行 -->
								<div class="layui-form-item" style="width: 800px;">
									<div class="layui-inline" layer-filter="validFlag">
										<label class="layui-form-label" style="width: 150px;">是否仅适用于本城市</label>
										<div class="layui-input-inline" name="ruleType.applyCity"
											id="applyCity" onclick="getSome();">
											<input type="radio" name="ruleType.applyCity" value="1"
												title="是"> <input type="radio"
												name="ruleType.applyCity" value="0" title="否" checked>
											<input type="text" style="display: none; width: 190px"
												id="cityName" name="ruleType.cityName" placeholder="请输入城市"
												autocomplete="off" class="layui-input">
										</div>
									</div>
								</div>

							</div>
							<h5>
								<input type="hidden" id="ruleDetails" name="ruleType.ruleDetails"
									class="layui-form-item" placeholder="" autocomplete="off"
									class="layui-input" hidden="true">
							</h5>
							<h5>
								<input type="hidden" id="statements" lay-verify="article_desc"
									name="ruleType.statements" class="layui-form-item"
									placeholder="" autocomplete="off" class="layui-input">
							</h5>

							<div class="layui-form-item layui-hide">
								<input type="button" lay-submit
									lay-filter="LAY-user-front-submit" id="LAY-user-front-submit"
									value="确认">
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="layui-tab-item " style="overflow: hidden;">
				<div class="layui-form" lay-filter="layuiadmin-form-useradmin1"
					id="layuiadmin-form-useradmin1" style="padding: 10px 0 0 15px;">

					<div style="height: 80px">
						<div style='float: left;'>
							<label style="line-height: 25px;">规则公式</label><br>
							<button onclick="addRule(index)" class="layui-btn layui-btn-xs">
								<i class="layui-icon">&#xe654;</i>添加规则
							</button>
						</div>
						<div class="rule_div">
							<ul id="rule_div_ul"></ul>
						</div>
					</div>

					<div>
						<label style="line-height: 25px;">规则逻辑</label><br>
						<div class="layui-input-inline">
							<textarea id="statements1" name="ruleType.statements1"
								style="width: 850px;" rows="2" class="layui-textarea"
								style="width:450px;height:150px;" style="display: none;"></textarea>
						</div>
					</div>

					<div>
						<label style="line-height: 25px;">规则内涵 <!-- &nbsp&nbsp&nbsp&nbsp<button onclick="addRule(index)" class="layui-btn layui-btn-radius layui-btn-warm"  >添加规则</button> -->
						</label><br>
						<div class="layui-input-inline">
							<textarea id="ruleDetails1" name="ruleType.ruleDetails1"
								style="width: 850px;" rows="2" class="layui-textarea"
								style="width:450px;height:150px;" style="display: none;"></textarea>
						</div>
					</div>



				</div>

			</div>

			<div class="layui-tab-item ">
				<button
					style="position: absolute; z-index: 999; float: right; right: 16px; top: 10px;"
					class="layui-btn layuiadmin-btn-useradmin" data-type="c">添加</button>
				<div class="layui-fluid" style="padding: 0px 0px; margin: 0px 0px">
					<div class="layui-card" style="padding: 0px 0px; margin: 0px 0px">
						<div class="layui-card-body"
							style="padding: 0px 0px; margin: 0px 0px">
							<table id="dictTable" class="layui-hide table"
								lay-filter="dictTable"></table>
							<script type="text/html" id="table-useradmin-webuser">
          <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
          <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
        </script>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/app/js/ruletype/ruleTypeform.js"></script>
	<script>
  var rul;
  var sta;
  var a=0;
  
  layui.config({
	    base: '<%=request.getContextPath()%>/plugins/layui/layuiadmin/'
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'form','element'], function(){
		  var form=layui.form;
		  var element = layui.element;
		  element.on('tab(demo)', function(data){
			   if(a<=0){
				   var result1 = $("input[id='ruleDetails']").val()
				      var result2 = $("input[id='statements']").val()
				      $("#ruleDetails1").val(result1); 
						     $("#statements1").val(result2); 
						     a++;
			   }else if(a>0){
			    	  rul = document.getElementById("ruleDetails1").value;
					     sta = document.getElementById("statements1").value;
					     document.getElementsByName("ruleType.ruleDetails")[0].value =rul;
					     document.getElementsByName("ruleType.statements")[0].value =sta;
			   } 
			  });
		  
		  form.verify({
			  article_desc: function(value){
				  $("#ruleDetails").val($("#ruleDetails1").val());
				  $("#statements").val($("#statements1").val());
			  }
		 });
	  });
  var adc='空';
  
  function addRuleText(guanxi,text,sql){
	  var ul=document.getElementById("rule_div_ul");
	  var li= document.createElement("li");
	  li.setAttribute('sql',sql);
	  li.setAttribute("style","float:left;padding:5px 10px;");
	  li.innerHTML="<div class='rule_div_left'>"+guanxi+"</div><div class='rule_div_right'>"+text+"<img class='rule_img_delete' src="+$WEB_ROOT_PATH+"/images/index/delete.png></div>";
	  ul.appendChild(li);
  }
  
  $(document).on('click','.rule_img_delete',function(){
	  var delsql = $(this).parent().parent().attr('sql');
	  //console.info("sql del:"+delsql);
	  var sqltext = $("#statements1").val(); 
	  sqltext = sqltext.replace(delsql,"");
	  //console.info("====:"+sqltext);
	  $("#statements1").val(sqltext);
	  $(this).parent().parent().remove();
  });
  
 
  function child(obj){
	  $("#ruleNo").attr("readOnly","true");
	  var ruleType = JSON.parse(obj);
	  adc=ruleType.id;
	  var ss = ruleType.applyCity;	
	  if(ss==1){
	  		$("input[name='ruleType.cityName']").show();
	  	}else{
	  		$("input[name='ruleType.cityName']").hide();
	  	}
	 $("#diagType").find("input[title="+ruleType.diagType+"]").attr("checked","true");
	 $("#ruleProperty").find("input[title="+ruleType.ruleProperty+"]").attr("checked","true");
	 $("#validFlag").find("input[title="+ruleType.validFlag+"]").attr("checked","true");
	 $("#applyCity").find("input[title="+ruleType.applyCity+"]").attr("checked","true");
	  $("#id").val(ruleType["id"]);
	  for (var index in ruleType){
	      $("#"+index).val(ruleType[index]);
	  }
  }
  
 /*  function ruleProperty(a){
	  $("#ruleProperty").find("input[title="+a+"]").attr("checked","true");
  } */
	
	//规则类型动态添加规则
	 function addRule(){
    	//新增方法
        layer.open({
          type: 2
          ,title: '添加规则条件'
          ,content: $WEB_ROOT_PATH+'/ruletype/dynamicRuleType'
          ,maxmin: true
          ,area: ['740px', '430px']
          ,btn: ['确定', '取消']
        ,success: function(layero, index){
			var iframeWindow = window['layui-layer-iframe'+ index];
			//加载select下拉option
			iframeWindow.loadSelect();
		}
          ,yes: function(index, layero){
        	 
            var iframeWindow = window['layui-layer-iframe'+ index]
            ,submitID = 'LAY-user-front-submit'
            ,submit = layero.find('iframe').contents().find('#'+ submitID);
            //监听提交
            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
              var field = data.field; //获取提交的字段
              var neihan=$("#statements1").val();
              var fuhao;
              if(field['ruleType.xianzhi']=="0"){
	       		   fuhao="<";
	       	  }else if(field['ruleType.xianzhi']=="1"){
	       		   fuhao="=";
	       	  }else if(field['ruleType.xianzhi']=="2"){
	       		   fuhao=">";
	       	  }else if(field['ruleType.xianzhi']=="3"){
	       		   fuhao="<=";
	       	  }else if(field['ruleType.xianzhi']=="4"){
	       		   fuhao=">=";   
	       	  }else if(field['ruleType.xianzhi']=="5"){
	       		   fuhao="in";
	       	  }else if(field['ruleType.xianzhi']=="6"){
	       		   fuhao="not in";
	       	  }
              if(neihan==null || neihan==""){
            	  if(fuhao == "in" || fuhao == "not in"){
             		 
            	  var a='SELECT * FROM '+field['tableName']+' WHERE 1=1 '+field['ruleType.logic']+" "+field['ruleType.name']+' '+fuhao+' '+"'("+field['tiaojian']+")'";
            	  var text = field['colname'] + fuhao + field['tiaojian'];
            	  var sql = field['ruleType.logic']+" "+field['ruleType.name']+' '+fuhao+' '+"'("+field['tiaojian']+")'";
            	  console.info("text:"+text);
            	  console.info("sql:"+sql);
            	  // AND text < aa
            	  addRuleText(field['ruleType.logic'],text,sql);
            	  $("#statements1").val(a);
            	  }else{
            		  var a='SELECT * FROM '+field['tableName']+' WHERE 1=1 '+field['ruleType.logic']+" "+field['ruleType.name']+' '+fuhao+' '+"'"+field['tiaojian']+"'";
                	  var text = field['colname'] + fuhao + field['tiaojian'];
                	  var sql = field['ruleType.logic']+" "+field['ruleType.name']+' '+fuhao+' '+"'"+field['tiaojian']+"'";
                	  console.info("text:"+text);
                	  console.info("sql:"+sql);
                	  // AND text < aa
                	  addRuleText(field['ruleType.logic'],text,sql);
                	  $("#statements1").val(a);
            	  }
              }else{
            	  if(fuhao == "in" || fuhao == "not in"){
            	  var b=neihan+' '+field['ruleType.logic']+" "+field['ruleType.name']+' '+fuhao+' '+"'("+field['tiaojian']+")'";
            	  var text = field['colname'] + fuhao + field['tiaojian'];
            	  var sql = field['ruleType.logic']+" "+field['ruleType.name']+' '+fuhao+' '+"'("+field['tiaojian']+")'";
            	  console.info("text:"+text);
            	  console.info("sql:"+sql);
            	  addRuleText(field['ruleType.logic'],text,sql);
            	  $("#statements1").val(b); 
            	  }else{
            		  var b=neihan+' '+field['ruleType.logic']+" "+field['ruleType.name']+' '+fuhao+' '+"'"+field['tiaojian']+"'";
                	  var text = field['colname'] + fuhao + field['tiaojian'];
                	  var sql = field['ruleType.logic']+" "+field['ruleType.name']+' '+fuhao+' '+"'"+field['tiaojian']+"'";
                	  console.info("text:"+text);
                	  console.info("sql:"+sql);
                	  addRuleText(field['ruleType.logic'],text,sql);
                	  $("#statements1").val(b); 
            	  }
              }
             
              layer.close(index); //关闭弹层
				});
				submit.trigger('click');
			}
		}); 
	} 
  </script>

	<script type="text/javascript">
   
   $(function(){
	   var ss= $("input[name='ruleType.applyCity']");
	  	if(ss[0].checked==true){
	  		$("input[name='ruleType.cityName']").show();
	  	}else{
	  		$("input[name='ruleType.cityName']").hide();
	  	}
   });
  function  getSome(){
  	var ss= $("input[name='ruleType.applyCity']");
  	if(ss[0].checked==true){
  		$("input[name='ruleType.cityName']").show();
  		
  	}else{
  		$("input[name='ruleType.cityName']").hide();
  	}
  }
    function  getSome1(){
    	
    
    	
		var ss = $("input[name='ruleType.applyCity']");
		
			if (ss[0].checked == false) {
				$("input[name='ruleType.cityName']").show();
			} 
			if (ss[1].checked == true){
				$("input[name='ruleType.cityName']").hide();
			}
		};
	
	</script>
</body>
</html>