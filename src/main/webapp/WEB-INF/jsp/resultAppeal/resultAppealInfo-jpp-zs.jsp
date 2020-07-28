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
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/layui/css/layui.css" media="all">
<link rel="stylesheet" href="<%=request.getContextPath() %>/plugins/layui/layuiadmin/style/admin.css" media="all">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/webuploader/demo.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/webuploader/webuploader.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/webuploader/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/webuploader/webuploader.min.js"></script>
<title>申诉材料新增/修改页面</title>
<style>
.layui-form-label {
	width: 115px;
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
#uploader{margin-top: 10px;}
	.maxPic{line-height:38px;margin-left: 95px;}
	.file-item{float: left; position: relative; width: 110px;height: 110px; margin: 0 20px 20px 0; padding: 4px;}
	.file-item .info{overflow: hidden;}
	.uploader-list-img{width: 100%; overflow: hidden;}
	
	.del_file{
		display: block;
		position: relative;
		margin-left: 15px;
		height: 16px;
		width: 16px;
		line-height: 25px;
		background-image: url(../js/webuploader/delete.png);
	}
	.del_img{
		display: block;
		position: absolute;
		margin-top: 3px;
		margin-left: 72px;
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
					<input type="hidden" name="resultAppeal.medicalId" id="medicalId" hide=true>
					<input type="hidden" name="resultAppeal.orgName" id="orgName" hide=true>
					<input type="hidden" name="resultAppeal.createDate" id="createDate" hide=true>
					<input type="hidden" name="resultAppeal.updateDate" id="updateDate" hide=true>
					<input type="hidden" name="resultAppeal.fileUrl" id="fileUrl" hide=true>
					<input type="hidden" name="resultAppeal.originalFilename" id="originalFilename" hide=true>
					<input type="hidden" name="resultAppeal.itemId" id="itemId" hide=true>


					<!-- 第一行 -->
					<div class="layui-form-item">
						<label class="layui-form-label">申诉时间</label>
						<div class="layui-input-inline">
							<input type="text" id="appealDate" name="resultAppeal.appealDate"
								lay-verify="required" placeholder="请选择申诉时间" autocomplete="off"
								class="layui-input" readonly="readonly">
						</div>
					</div>
					<!-- 第2行 -->
					<div class="layui-form-item">
						<label class="layui-form-label">申&nbsp;诉&nbsp;人&nbsp;</label>
						<div class="layui-input-inline">
							<input type="text" id="appealPerson"
								name="resultAppeal.appealPerson" placeholder="请输入申诉人"
								autocomplete="off" class="layui-input" readonly="readonly">
						</div>
					</div>
					<!-- 第3行 -->
					<div class="layui-form-item">
						<label class="layui-form-label">申诉原因 </label>
						<div class="layui-input-block"
							style="float: left; margin-left: 55px">
							<textarea style="width: 650px" placeholder="请提交申诉材料并说明"
								name="resultAppeal.appealReason" id="appealReason"
								lay-verify="required" class="layui-textarea" readonly="readonly"></textarea>
						</div>
					</div>
					<!-- 第4行 -->
					<div class="layui-form-item">
						<label class="layui-form-label">审&nbsp;核&nbsp;人&nbsp;</label>
						<div class="layui-input-inline">
							<input type="text" id="examinePerson"
								name="resultAppeal.examinePerson" placeholder="请输入审核人"
								autocomplete="off" class="layui-input" readonly="readonly">
						</div>
					</div>
					<!-- 第5行 -->
					<div class="layui-form-item">
						<label class="layui-form-label">审核意见 </label>
						<div class="layui-input-block"
							style="float: left; margin-left: 55px">
							<textarea style="width: 650px" placeholder="审核意见（待处理）"
								name="resultAppeal.examineComments" id="examineComments"
								class="layui-textarea" readonly="readonly"></textarea>
						</div>
					</div>
					<!-- 第6行 -->
					<div class="layui-form-item">
						<div class="layui-inline " style="margin-left: 40px">
							<table id="fileShow" border="0" cellpadding="0" cellspacing="0">
							</table>

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
  layui.config({
	    base: '<%=request.getContextPath() %>/plugins/layui/layuiadmin/'
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'form','laydate','table'], function(){
		  var form=layui.form;	
		  var laydate=layui.laydate;
			
	  })
  var fileurl;
  var fileResurl;
  function child(row){
	  //alert(id);
		//var idnew=id.substring(1,id.length-1);
		var url=$WEB_ROOT_PATH+'/dhccApi/medical/medical/findResultAppealLast';
		var filed={"resultAppeal.itemId":row.id,"resultAppeal.medicalId":row.medicalId};
		$.post(url,filed,function(data){
			  	//debugger;
			   console.log("公示回显数据");
			   console.log(data);
		  	   var idno=data["id"];
		  	   var medicalIdInfo=data["medicalId"];
		  	   var orgName=data["orgName"];
		  	   var orgCode=data["orgCode"];
		  	   var createDate=data["createDate"];
		  	   var updateDate=data["updateDate"];
		  	   var appealDate=data["appealDate"];
		  	   var appealPerson=data["appealPerson"];
		  	   var appealReason=data["appealReason"];
		  	   var examineComments=data["examineComments"];
		  	   var examinePerson=data["examinePerson"];
		  	   fileurl = data["fileUrl"];
		  	   fileResurl = data["originalFilename"];
		      $("#id").val(idno);
		      $("#medicalIdInfo").val(medicalIdInfo);
		      $("#orgName").val(orgName);
		      $("#orgCode").val(orgCode);
		      $("#createDate").val(createDate);
		      $("#updateDate").val(updateDate);
		      $("#appealDate").val(appealDate);
		      $("#appealPerson").val(appealPerson);
		      $("#appealReason").val(appealReason);
		      $("#examineComments").val(examineComments);
		      $("#examinePerson").val(examinePerson);
		      $("#fileUrl").val(fileurl);
		      $("#originalFilename").val(fileResurl);
		  	  fileShow(fileurl,fileResurl)
		});	


}
  function fileShow(fileurl,fileResurl){
		 var table = document.getElementById("fileShow");
		 removeAllChild(table);
		 if(fileurl!=null && fileurl!="" && fileResurl!=null && fileResurl!="" ){
			   var imgs= fileurl.split(",");
			   var imgsNameRes = fileResurl.split(",");
			   for(var i=0;i<imgs.length;i++){
				   var tr=document.createElement("tr");
					var td=document.createElement("td");
					var nameFile=imgsNameRes[i].split(".")[1];
					if(nameFile=="jpg"||nameFile=="gif"||nameFile=="png"||nameFile=="tif"||nameFile=="jfif"||nameFile=="tag"){
						var a=document.createElement("img");
						a.setAttribute("src",$WEB_ROOT_PATH+"/storeFile/"+imgs[i]);
						a.innerHTML =imgsNameRes[i];
						a.setAttribute("width",100);
						a.setAttribute("height",100);
						td.appendChild(a);
					}else{
						var a=document.createElement("a");
						a.setAttribute("href",$WEB_ROOT_PATH+"/storeFile/"+imgs[i]);
						a.innerHTML =imgsNameRes[i];
						td.appendChild(a);
					}
					tr.appendChild(td);
					table.appendChild(tr);
			   }
		 }
	}
	function removeAllChild(table){
		    //var table = document.getElementById(table);
		    while(table.hasChildNodes()) //当div下还存在子节点时 循环继续
		    {
		        table.removeChild(table.firstChild);
		    }
	}
</script>
	
</body>
</html>