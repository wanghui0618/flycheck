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
    <div class="layui-form-item layui-form-text">
    <input type="hidden" name="medicalAudit.fileurl" id="fileurl" hide=true>
					<input type="hidden" name="medicalAuditOnline.filenameurl" id="filenameurl" hide=true>
    
    
        <label class="layui-form-label">巡查审核结果</label>
        <div class="layui-input-inline">
            <input type="text" id="reviewResult" name="medicalAuditOnline.reviewResult" placeholder="" readonly="readonly"
                   autocomplete="off"
                   class="layui-input">
        </div>
        <label class="layui-form-label">巡查记录状态</label>
        <div class="layui-input-inline">
            <input type="text" id="patrolRecordState" name="medicalAuditOnline.patrolRecordState" placeholder="" readonly="readonly"
                   autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">巡查时间</label>
        <div class="layui-input-inline">
            <input type="text" id="patrolTime" name="medicalAuditOnline.patrolTime" placeholder="" readonly="readonly"
                   autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">审核意见</label>
        <div class="layui-input-block">
                    <textarea style="width: 600px;height: 80px;" id="auditOpinion" lay-verify="article_desc" name="medicalAuditOnline.auditOpinion" cols="20"
                              rows="2" class="ayui-textarea" placeholder="" readonly="readonly"></textarea>
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">总体问题</label>
        <div class="layui-input-inline">
        <textarea style="width: 600px;height: 80px;" id="overallProblem" lay-verify="article_desc" name="medicalAuditOnline.overallProblem" cols="20"
                              rows="2" class="ckeditor" readonly="readonly"></textarea>
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">巡查结论</label>
        <div class="layui-input-block">
             <textarea style="width: 600px;height: 80px;" id="patrolConclusion" lay-verify="article_desc" name="medicalAuditOnline.patrolConclusion"
                              cols="20" rows="2" class="ckeditor" readonly="readonly"></textarea>
        </div>
    </div>
      <div class="layui-form-item"  >
   			<label class="layui-form-label">是否违规</label>
    			<div class="layui-input-block">
    			<input type="text" id="aduitStatus"
					name="medicalAuditOnline.aduitStatus" lay-verify="required"
					readonly="readonly" class="layui-input" style="width: 300px">
      			<!-- <input type="radio" id="medicalAudit.aduitStatus" name="medicalAudit.aduitStatus" value="0" title="违规" checked class="layui-form-item" >
      			<input type="radio" id="medicalAudit.aduitStatus"  name="medicalAudit.aduitStatus" value="2" title="未违规"  class="layui-form-item" > -->
   				</div>
  	</div> 
    
    <div class="layui-form-item">
        <div class="layui-inline " style="margin-left: 40px">
	       <!--  <div class="btns" id='selectText' disabled="true">
                <div id="filepicker" disabled="true">选择附件</div>
            </div>
            <div id="uploader" class="wu-example">
                用来存放文件信息
                <div id="fileList" style="margin-top:15px;"  class="uploader-list"></div>
                <div id="fileListImg" style="margin-top:15px;"  class="uploader-list-img"></div>
            </div> -->
			<!-- 图片文件回显 -->
			
			<table id="fileShow" border="0" cellpadding="0" cellspacing="0">
			</table>
			
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
	  });
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
	  var code2=medical.aduitStatus;
		 if(code2==0){
			  	 $("#aduitStatus").val("违规");
		 }else if(code2==2){
				 $("#aduitStatus").val("正常");
	  }
      //图片回填
      //fileUrl="a03d88afbce1487593f30cc58c18472a.jfif";
	  //fileResurl="小脑袋.jfif";
      fileShow(fileurl,fileResurl);
	  
}
  
var medicalAduitId;
function child1(id){
	medicalAduitId=id; 
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