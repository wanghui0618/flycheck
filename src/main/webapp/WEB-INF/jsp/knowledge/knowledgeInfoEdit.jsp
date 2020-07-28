<%@ page language="java" import="java.util.*"	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"  content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css" media="all">
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css" media="all">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ckeditor_4.9.2/ckeditor.js"></script>
<title>知识库编辑新增/修改页面</title>
<style>
.layui-form-label {
	width: 115px;
}
</style>
</head>
<body>
	<div class="layui-fluid">
		<div class="layui-card">
			<div class="layui-card-body" >
				<div class="layui-form" lay-filter="layuiadmin-form-useradmin"
					id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">
					
					<input type="hidden" name="knowledge.id" id="id">
					<input type="hidden" name="knowledge.createUser" id="createUser">
					<input type="hidden" name="knowledge.createDate" id="createDate">
					<input type="hidden" name="knowledge.fabulosFrequency" id="fabulosFrequency">
					<input type="hidden" name="knowledge.lookFrequency" id="lookFrequency">
					<input type="hidden" name="knowledge.treadFrequency" id="treadFrequency">
					<input type="hidden" name="knowledge.content" id="contentHidden">
					<!-- 第一行 -->
					<div class="layui-form-item">
						<label class="layui-form-label" style="width:55px;">类型</label>
						<div class="layui-input-inline" onchange="chooseType(this[selectedIndex].value)">
							<select name="knowledge.type" autocomplete="off" id="type"
								lay-verify="required"  lay-filter="choose" >
								<option value="1" onchange="chooseType(2)">大类</option>
								<option value="2" onchange="chooseType(2)">小类</option>
							</select>
						</div>
						
						<label class="layui-form-label" style="width:55px;margin-left:50px;">分类</label>
						<div class="layui-input-inline" id="changeInput" lay-filter="changeInput">	
						<input type="text" id="typeSmall" name="knowledge.typeSmall"
								lay-verify="required" placeholder="请输入分类" autocomplete="off"
								class="layui-input">
						</div>
					</div>


					<!-- 第二行 -->
					<div class="layui-form-item">
						<label class="layui-form-label" style="width:55px;">标题</label>
						<div class="layui-input-inline">
							<input type="text" id="title" name="knowledge.title"
								lay-verify="required" placeholder="请输入标题" autocomplete="off"
								class="layui-input"  >
						</div>
						<label class="layui-form-label" style="width:55px;margin-left:50px;">关键字</label>
						<div class="layui-input-inline">
							<input type="text" id="keywords" name="knowledge.keywords"
								lay-verify="required" placeholder="请输入关键字" autocomplete="off"
								class="layui-input">
						</div>
					</div>
					<!-- 第二行 -->
					<div class="layui-form-item">
					    <label class="layui-form-label" style="width:55px;">内容</label>
							<div class="layui-input-inline" style="width: 600px;">
								<textarea id="TextArea" lay-verify="article_desc"
									name="TextArea" cols="20" rows="2" class="ckeditor" style="width: 656px; height: 216px;"></textarea>
							</div>
					
					</div>

					<div class="layui-form-item layui-hide">
						<input type="button" lay-submit
							lay-filter="layuiadmin-btn-useradmin"
							id="layuiadmin-btn-useradmin" value="确认">
					</div>
				</div>
			</div>
		</div>
	</div>


<script>
var neirong;
var form;
//初始化	
layui.config({
	base: '<%=request.getContextPath()%>/plugins/layui/layuiadmin/' //静态资源所在路径
}).extend({
	index: 'lib/index' //主入口模块
}).use(['index', 'table','laydate','layedit','form'], function(){
	var $ = layui.$
	,table = layui.table;
	 var layedit = layui.layedit;
	 form = layui.form;
	 /*  form.verify({
		    title: function(value){
		      if(value.contains("+")){
		        return '不能有特殊字符存在';
		      }
		    }
		  });
	  */
	  
	  form.on('select(choose)', function(data){
		var value=data.value;
		if(value==1){
			document.getElementById('changeInput').innerHTML='<input type="text" id="typeSmall" name="knowledge.typeSmall" lay-verify="required" placeholder="请输入分类" autocomplete="off" class="layui-input">'	
		    form.render(null,'changeInput');
		}else if(value==2){
			document.getElementById('changeInput').innerHTML='<select name="knowledge.typeSmall" autocomplete="off" id="typeSmall" lay-verify="required" lay-filter="type" placeholder="请选择分类"><option value="" ></option></select>'
			form.render(null,'changeInput');	
			//加载分类
			 $.getJSON($WEB_ROOT_PATH+'/dhccApi/dict/dict/findDict?dictRequestVo.tableName=T_PICCBID_KNOWLEDGE', 
						function(data){
				     		var  dataList= data.dictList;
				     		for(var i=0 ;i<dataList.length;i++){
				     			 mm="<option value='"+dataList[i].value+"'>"+dataList[i].value+"</option>";
				     			$("#typeSmall").append(mm); 
				     		}
				    		form.render('select');
			 });
		}
		});      
	  
	  
	  $('#type').bind('onchange', function() {
			console.log("我在改变");	
	  });
	  
	//加载分类
		 $.getJSON($WEB_ROOT_PATH+'/dhccApi/dict/dict/findDict?dictRequestVo.tableName=T_PICCBID_KNOWLEDGE', 
					function(data){
			     		var  dataList= data.dictList;
			     		for(var i=0 ;i<dataList.length;i++){
			     			 mm="<option value='"+dataList[i].value+"'>"+dataList[i].value+"</option>";
			     			$("#typeSmall").append(mm); 
			     		}
			    		form.render('select');
		 });
	  
	  
	var index =layedit.build('content', {
		  height: 200 //设置编辑器高度
		 });
		 form.verify({
		  article_desc: function(value){
			  var content = CKEDITOR.instances.TextArea.getData();
	          $("#contentHidden").val(content);
		       return layedit.sync(index);
		    }
		});
	    CKEDITOR.instances.TextArea.setData(neirong);

});

//信息回填
function child(obj) {
	var knowledge = JSON.parse(obj);	
	//console.log(knowledge);
	for ( var index in knowledge) {
		$("#" + index).val(knowledge[index]);
	}
	 neirong = knowledge["content"];
	var type=knowledge.type;
	$("#type").find("option[value ='"+type+"']").attr("selected","selected");
	var title=knowledge.title;
	title=title.replace("!",/\s+/g);
	$("#title" ).val(title);
	var typeSmall=knowledge.typeSmall;
	$("#typeSmall").find("option[value ='"+typeSmall+"']").attr("selected","selected");
    form.render('select');
}

function chooseType(value){	
	console.log(value);
	console.log("我在改变");	
}
</script>
</body>
</html>