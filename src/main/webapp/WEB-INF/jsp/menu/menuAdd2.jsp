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
<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/layui/layuiadmin/layui/layui.js"></script>
<style>
</style>
    <title>菜单管理页面</title>
</head>
<body>
<div class="layui-fluid">
    <fieldset style="width:90%;margin:0 auto">
        <legend>菜单信息</legend>


        <div class="layui-card-body">
            <div class="layui-form" lay-filter="layuiadmin-form-useradmin"
                 id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">

                <input type="hidden" name="menu.id" id="id"  hide=true>
                 <input type="hidden" name="menu.parentId" id="parentId"  hide=true>
                  <input type="hidden" name="menu.parentLeaf" id="parentLeaf"  hide=true>
                   <input type="hidden" name="menu.onclickBef" id="onclickBef"  hide=true>

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">菜单名称</label>
                        <div class="layui-input-inline">
                            <input type="text" id="menuName"
                                   name="menu.menuName" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                </div>

                <div class="layui-form-item">
                  <div class="layui-inline">
                       <label class="layui-form-label">菜单代码</label>
                       <div class="layui-input-inline">
                            <input type="text" id="menuCode"
                                   name="menu.menuCode" autocomplete="off"
                                   class="layui-input">
                       </div>
                  </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">URL</label>
                        <div class="layui-input-inline">
                            <input type="text" id="menuUrl"
                                   name="menu.menuUrl" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">创建人</label>
                        <div class="layui-input-inline">
                            <input type="text" id="owner"
                                   name="menu.owner" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                  <div class="layui-inline" style="margin:0px">
       			     <label class="layui-form-label" style="height:70px;line-height:70px">图片上传</label>
        			   <div class="layui-input-block"  style="width:300px">
           		 			<input type="button"  class="layui-btn" id="test1" style=" text-align:left; float:left;height:30px" value="选择图片" >

               				 <!-- <i class="layui-icon">&#xe67c;</i>选择图片 -->
						</div>
					(请选择两张图片，先选默认图片,再选点击后图片)
        	   	 </div>
						<button type="button" class="layui-btn" id="test9" style="height:30px">开始上传</button>
           	 			<button type="button" class="layui-btn" id="cleanImgs" style="height:30px"> <i class="fa fa-trash-o fa-lg"></i>清空图片预览</button>
       			 <blockquote class="layui-elem-quote layui-quote-nm" style="margin:0px">
						图片预览：
            		<div class="layui-upload-list" id="demo2"></div>
       		 	 </blockquote>
    		   </div>

                <div class="layui-form-item layui-hide">
                    <input type="button" lay-submit
                           lay-filter="layuiadmin-btn-useradmin"
                           id="layuiadmin-btn-useradmin1" value="确认">
                </div>

        </div>
    </fieldset>
</div>
<script>
var dataAll;
  layui.config({
	    base: '<%=request.getContextPath() %>/plugins/layui/layuiadmin/'
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use(['index', 'form','laydate','table'], function(){
		  var form=layui.form;
            form.on('submit(layuiadmin-btn-useradmin1)', function(data){
            	console.info(data);
            	//先传图片文件
                //uploader.upload();
                dataAll=data;
               // table.reload('menuAdd'); //数据刷新
            });
	  })

var parentId;
var level;
/*    function child(obj){
	  var medical = JSON.parse(obj);
	  //parentId=medical["id"];
	  //level=medical["level"];
	  //console.log(index)
	  console.log(medical)
	  for (var index in medical){
	      $("#"+index).val(medical[index]);
	  }

}  */
  function childAdd(obj){
	  //console.log(obj)
	  var medical = JSON.parse(obj);
	  var level = medical.level;
	  //新增是level+1
	  $("#parentLeaf").val(level);
	  $("#parentId").val(medical.parentId);
	  $("#id").val(medical.id);
	  //console.log(medical.id)
	  //编辑时的数据回填
	  $("#menuName").val(medical.menuName);
	  $("#menuCode").val(medical.menuCode);
	  $("#menuUrl").val(medical.menuUrl);
	  $("#owner").val(medical.owner);
  }
  </script>
    <script>
    function child(obj){
    var org = JSON.parse(obj);
    for (var index in org){
    $("#"+index).val(org[index]);
    }
    }
    </script>

<script type="text/javascript" src="<%=request.getContextPath()%>/app/js/menu/menuUpload2.js"></script>
</body>
</html>