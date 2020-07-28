<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
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
<title>申诉材料新增/修改页面</title>
<style>
.layui-form-label {
	width: 80px;
	margin-left: 20px;
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
	
	.delFile{
		display: none;
		position: relative;
		margin-left: 15px;
		height: 16px;
		width: 16px;
		line-height: 25px;
		background-image: url(../js/webuploader/delete.png);
	}
	.delImg{
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
</head>
<body>
	<div class="layui-fluid">
		<div class="layui-card">
			<div class="layui-card-body">
				<div class="layui-form" lay-filter="layuiadmin-form-useradmin"
					id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">

					<input type="hidden" name="resultAppeal.id" id="id" hide=true>
					<input type="hidden" name="resultAppeal.status" id="status" hide=true>
					<input type="hidden" name="resultAppeal.medicalId" id="medicalId">
					<input type="hidden" name="resultAppeal.orgName" id="orgName" hide=true>
					<input type="hidden" name="resultAppeal.orgCode" id="orgCode" hide=true>
					<input type="hidden" name="resultAppeal.fileUrl" id="fileUrl" hide=true>
					<input type="hidden" name="resultAppeal.originalFilename" id="originalFilename" hide=true>
					<input type="hidden" name="resultAppeal.examinePerson" id="examinePerson" hide=true>
					<input type="hidden" name="resultAppeal.countTime" id="countTime" hide=true>


					<fieldset class="layui-elem-field layui-field-title"
						style="margin-top: 0px;">
						<legend>医院提交信息：</legend>
					</fieldset>
					<!-- 第一行 -->
					<div class="layui-form-item">
						<label class="layui-form-label">申诉时间</label>
						<div class="layui-input-inline">
							<input type="text" id="appealDate" name="resultAppeal.appealDate"
								lay-verify="required" placeholder="请选择申诉时间" autocomplete="off"
								class="layui-input">
						</div>
					</div>
					<!-- 第2行 -->
					<div class="layui-form-item">
						<label class="layui-form-label">申诉人</label>
						<div class="layui-input-inline">
							<input type="text" id="appealPerson" readonly="true"
								name="resultAppeal.appealPerson" placeholder="请输入申诉人"
								autocomplete="off" class="layui-input" lay-verify="required">
						</div>
					</div>
					<!-- 第3行 -->
					<div class="layui-form-item">
						<label class="layui-form-label">申诉原因 </label>
				<div class="layui-input-inline"> 
							<textarea style="width: 600px" placeholder="请提交申诉材料并说明"
								name="resultAppeal.appealReason" id="appealReason" readonly="true"
								lay-verify="required" class="layui-textarea"></textarea>
						</div>
					</div>
					
					
						
					
				<fieldset class="layui-elem-field layui-field-title"
						style="margin-top:30px;">
						<legend>医院提交附件（<span style="color:#ea5f5f;font-weight:600;">点击下载</span>）</legend>
					
					<!-- 第6行 -->
				<div class="layui-form-item" style="margin-top: -20px; margin-left: 70px;">
						<div class="layui-inline" style="margin-left:33px">
							<div id="uploader" class="wu-example" style=" width: 550px;border-style: solid; border-color: blue rgb(25%,35%,45%) #909090 red;">
								<!--用来存放文件信息-->
								<div id="fileList" style="margin-top: 20px;"
									class="uploader-list"></div>
								<div id="fileListImg" style="margin-top: 20px;"
									class="uploader-list-img"></div>
							</div>

						</div>
					</div>
						</fieldset>
					<fieldset class="layui-elem-field layui-field-title"
						style="margin-top: 20px;">
						<legend>医保局审核结果：</legend>
					</fieldset>
					
					<div class="layui-form-item">
						<label class="layui-form-label">扣除金额</label>
						<div class="layui-input-inline">
							<input type="text" id="totalAmount" lay-filter="number"	name="totalAmount" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">扣除原因</label>
						<div class="layui-input-inline">
							<input type="text" id="userVerifyRemarks"	name="userVerifyRemarks" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">审核意见</label>
						<div class="layui-input-inline">
							<textarea style="width: 600px" placeholder="审核意见（待处理）"
								name="resultAppeal.examineComments" id="examineComments"
								class="layui-textarea"></textarea>
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
  var fileurl;
  var fileResurl;
  var roleCode;
  layui.config({
	    base: '<%=request.getContextPath() %>/plugins/layui/layuiadmin/'
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'form','laydate','table'], function(){
		  var form=layui.form;	
		  var laydate=layui.laydate;
			laydate.render({
				elem:'#appealDate'
					,type:'datetime'
					,trigger:'click'
						,format:'yyyy-MM-dd' 
							,value: new Date() 
			  //,isInitValue:true
			});
		  
		
            form.on('submit(layuiadmin-btn-useradmin)', function(data){
            	
            	var status=$("#status").val();
            	if(status == '2'){ 
        			  uploader.upload();
					  dataAll = data;
		           }else{ 
		        	   window.parent.closeOnly();  
		        	   } 
            });
	  });
 
  function child(appealId,money,reason){	
	  $("#id").val(appealId);
	  if(reason==null||reason=="null"){
		  reason=''; 
	  }
	  if(money==null||money=="null"){
		  money=''; 
	  }
		//罗列所有小分类
		$.post($WEB_ROOT_PATH+'/dhccApi/resultAppeal/resultAppeal/findById',{"resultAppeal.id":appealId},function(data){			
		  	   var medicalId=data["medicalId"];	 
		  	   var orgName=data["orgName"];
		  	   var orgCode=data["orgCode"];
		  	   var appealDate=data["appealDate"];
		  	   var appealPerson=data["appealPerson"];
		  	   var countTime=data["countTime"];
		  	   var status=data["status"];
		  	   var appealReason=data["appealReason"];
		  	   var examineComments=data["examineComments"];
		  	   var examinePerson=data["examinePerson"];
		  fileurl = data["fileUrl"];
		  fileResurl = data["originalFilename"]; 
		      $("#medicalId").val(medicalId);
		      $("#orgName").val(orgName);
		      $("#orgCode").val(orgCode);

	
	
		if (appealDate == null || appealDate == '') {
				
				} else {
					$("#appealDate").val(appealDate);
				}
				$("#appealPerson").val(appealPerson);
				$("#appealReason").val(appealReason);
				$("#examinePerson").val(examinePerson);
				$("#fileUrl").val(fileurl);
				$("#originalFilename").val(fileResurl);
				$("#countTime").val(countTime);
				$("#status").val(status);
				$("#totalAmount").val(money);

				$("#userVerifyRemarks").val(reason);
				$("#examineComments").val(examineComments);

					if (status != '2') {
						$("#examineComments").attr("readonly", "true");
						$("#totalAmount").attr("readonly", "true");
						$("#userVerifyRemarks").attr("readonly", "true");
					}
				

				//图片回填
				if (fileurl != null && fileurl != "" && fileResurl != null
						&& fileResurl != "") {
					var imgs = fileurl.split(",");
					var imgsNameRes = fileResurl.split(",");
					for (var i = 0; i < imgs.length; i++) {

						var filetype = imgs[i].split(".")[1];
						if (filetype == "jpg" || filetype == "png") {
							addFile("../storeFile/" + imgs[i], imgsNameRes[i]);
						} else {
							addFile("../storeFile/" + imgs[i], imgsNameRes[i]);
						}
					}
				}

			});

		}
	</script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/resultAppeal/resultAppealInfoMain.js"></script>
</body>
</html>