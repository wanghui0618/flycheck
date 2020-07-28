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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/webuploader/demo.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/webuploader/webuploader.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/webuploader/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/webuploader/webuploader.min.js"></script>

<title>稽核</title>
<style>
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
.layui-form-label {
    font-size:15px;
    width: 90px;
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
<div class="layui-form" id="layuiadmin-form-useradmin" lay-filter="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">
      <div class="layui-form-item"  >
   				 						<label class="layui-form-label">是否违规</label>
    									<div class="layui-input-block">
      										<input type="radio" id="medicalAuditOnline.aduitStatus" name="medicalAuditOnline.aduitStatus" value="2" title="正常" checked class="layui-form-item" >
      										<input type="radio" id="medicalAuditOnline.aduitStatus"  name="medicalAuditOnline.aduitStatus" value="0" title="违规"  class="layui-form-item" >
   										 </div>
  									 </div> 
    
  
    <div class="layui-form-item layui-hide">
        <input type="button" lay-submit lay-filter="LAY-user-front-submit" id="LAY-user-front-submit"
               value="确认">
    </div>
</div>
  <script>
  var dataAll;
  layui.config({
	    base: '<%=request.getContextPath() %>/plugins/layui/layuiadmin/'
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'form','laydate'], function(){
		  var laydate=layui.laydate;
		  var form=layui.form;
			laydate.render({
				elem:'#patrolTime',
				trigger:'click',
				type:'datetime',
				format:'yyyy-MM-dd'
			})
            form.on('submit(LAY-user-front-submit)', function(data){
            	//先传图片文件
                uploader.upload();
                dataAll=data;
            });
	  })
  var medicalId;
  var id;
  var fileurl;
  var fileResurl;
  function child(obj){
	  var medical = JSON.parse(obj);
	  medicalId = medical["medicalId"];
	  id = medical["id"];
	  fileurl = medical["fileurl"];
	  fileResurl = medical["filenameurl"];
	  for (var index in medical){
	      $("#"+index).val(medical[index]);
	  }
	  
	  console.info(fileurl);
	  console.info(fileResurl);
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
}
</script>
<script type="text/javascript" src="<%=request.getContextPath() %>/app/js/medical/medicalAuditForm.js"></script>
<%-- <script type="text/javascript" src="<%=request.getContextPath() %>/app/js/medical/medicalAuditFormPicture.js"></script>
 --%>
</body>
</html>