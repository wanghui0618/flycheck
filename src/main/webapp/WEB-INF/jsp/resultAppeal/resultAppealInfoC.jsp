<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@page import="com.dhcc.piccbid.entity.user.User" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<%@include file="/WEB-INF/jsp/common/scriptInc.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css" media="all">
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css" media="all">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/webuploader/demo.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/webuploader/webuploader.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/webuploader/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/webuploader/webuploader.min.js"></script>
<title>申诉材料</title>
<style>
.layui-form-label {
	width: 80px;
	margin-left:20px;
}
.file-itemimg{
	float:left;
	display:block;
	margin-right: 20px;
}
.info{
	white-space:normal;
    word-break:break-all;
    word-wrap:break-word;
    width:125px;
}
#uploader{margin-top: 0px;}
	.maxPic{line-height:38px;margin-left: 95px;}
	.file-item{float: left; position: relative; width: 110px;height: 150px; margin: 0 0 0 0; padding: 4px;}
	.file-item .info{overflow: hidden;}
	.uploader-list-img{width: 100%; overflow: hidden;}
	
	.del_file{
		display: none;
		position: relative;
		margin-left: 15px;
		height: 16px;
		width: 16px;
		line-height: 25px;
		background-image: url(../js/webuploader/delete.png);
	}
	.del_img{
		display: none;
		position: relative;
		margin-top: 0px;
		margin-left: 110px;
		height: 16px;
		width: 16px;
		background-image: url(../js/webuploader/delete.png);
	}
	.radiory{
		width: 50px;
	}
	.ptright,.textbox-label{
		margin-left: 15px!important;
	}
	.labt{
		margin-left: 30px;
	}
</style>
<%
	User user = (User)session.getAttribute("user");
    String name = user.getName();
%>

</head>
<body>
	<div class="layui-fluid">
		<div class="layui-card">
			<div class="layui-card-body">
				<div class="layui-form" lay-filter="layuiadmin-form-useradmin"
					id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">

					<input type="hidden" name="resultAppeal.id" id="id" >
					<input type="hidden" id="status" hide=true>
					<input type="hidden" name="resultAppeal.fileUrl" id="fileUrl" hide=true>
					<input type="hidden" name="resultAppeal.originalFilename" id="originalFilename" hide=true>
					<input type="hidden" name="resultAppeal.itemId" id="itemId" hide=true>
					<input type="hidden" name="resultAppeal.examinePerson" id="examinePerson" hide=true>
					<input type="hidden" name="resultAppeal.countTime" id="countTime" hide=true>
					

					<!-- 第一行 -->
					<div class="layui-form-item">
						<label class="layui-form-label">申诉时间</label>
						<div class="layui-input-inline">
							<input type="text" id="appealDate" name="resultAppeal.appealDate"
								placeholder="请选择申诉时间" autocomplete="off"
								class="layui-input">
						</div>
					</div>
					<!-- 第2行 -->
					<div class="layui-form-item">
						<label class="layui-form-label">申诉人</label>
						<div class="layui-input-inline">
							<input type="text" id="appealPerson" readonly="ture" 
								name="resultAppeal.appealPerson" placeholder="请输入申诉人"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<!-- 第3行 -->
					<div class="layui-form-item">
						<label class="layui-form-label">申诉原因 </label>
						<div class="layui-input-inline">
							<textarea style="width: 600px" placeholder="请提交申诉材料并说明" 
								name="resultAppeal.appealReason" id="appealReason" readonly="ture"
							 class="layui-textarea"></textarea>
						</div>
					</div>
					<!-- 第6行 -->
							<fieldset class="layui-elem-field layui-field-title"
						style="margin-top:30px;">
						<legend>附件（点击下载）</legend>
					</fieldset>
					<div class="layui-form-item" style="margin-top: -20px;">
						<div class="layui-inline" style="margin-left:36px">
							<div id="uploader" class="wu-example" style="width: 550px;border-style: solid; border-color: blue rgb(25%,35%,45%) #909090 red;">
								<div id="fileList" style="margin-top: 20px;"
									class="uploader-list" ></div>
								<div id="fileListImg" style="margin-top: 20px;"
									class="uploader-list-img"></div>
							</div>
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
	</div>
 <script>
  var dataAll;
  var roleCode;
  var fileurl;
  var fileResurl;
  var name;
  layui.config({
	    base: '<%=request.getContextPath() %>/plugins/layui/layuiadmin/'
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'form','laydate','table'], function(){
		  var form=layui.form;	
	/* 	  var laydate=layui.laydate;
			laydate.render({
				elem:'#appealDate'
					,type:'datetime'
					,trigger:'click'
						,format:'yyyy-MM-dd' 
							,value: new Date() 
			}); */
		  
            form.on('submit(layuiadmin-btn-useradmin)', function(data){
            	 var status=$("#status").val();
			     window.parent.closeOnly();       
			});
		})

function child(itemId, status, medicalId, countTime) {
	  roleCode=1;
	  name='<%=name%>';
	  $("#status").val(status);
      $("#itemId").val(itemId);
		//罗列所有小分类
		$.getJSON($WEB_ROOT_PATH+'/dhccApi/resultAppeal/resultAppeal/findItemInfo?resultAppeal.itemId='+itemId+'&resultAppeal.countTime='+countTime,function(data){
			//console.log(data);
		  	   var idno=data["id"];
		  	   var appealDate=data["appealDate"];
		  	   var appealPerson=data["appealPerson"];
		  	   var appealReason=data["appealReason"];
		  	   var examineComments=data["examineComments"];
		  	   var examinePerson=data["examinePerson"];
		  fileurl = data["fileUrl"];
		  fileResurl = data["originalFilename"];
		      $("#id").val(idno);
		      if(appealDate==null||appealDate==''){
		      }else{
		    	 $("#appealDate").val(appealDate);
		      }
		  
		      $("#appealReason").val(appealReason);
		      $("#examineComments").val(examineComments);
		      $("#fileUrl").val(fileurl);
		      $("#originalFilename").val(fileResurl);
		      $("#appealPerson").val(appealPerson);

		      if(status!='5'){
		        	$("#appealReason").attr("readonly","readonly");//待调整
					$("#appealPerson").attr("readonly","readonly");
					$("#appealDate").attr("readonly","readonly");
					$("#filepicker").hide();					
		      }
		        
	      //图片回填
		  if(fileurl!=null && fileurl!="" && fileResurl!=null && fileResurl!="" ){
			   var imgs= fileurl.split(",");
			   var imgsNameRes = fileResurl.split(",");
			   for(var i=0;i<imgs.length;i++){
				   
				   var filetype=imgs[i].split(".")[1];
				   if(filetype=="jpg"||filetype=="png"){
					   addFile("../storeFile/"+imgs[i],imgsNameRes[i]);
				   }else{
					   addFile("../storeFile/"+imgs[i],imgsNameRes[i]);
				   }
			   }
		  } 
		
		});	
		var appealPerson=$("#appealPerson").val();
		 if(appealPerson==null||appealPerson==''){
	  		 appealPerson=name;
	  		$("#appealPerson").val(appealPerson);
	  	   }
		
}
</script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/resultAppeal/resultAppealInfo.js"></script>
</body>
</html>