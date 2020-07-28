<%@ page language="java" import="java.util.*"
         contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <%@include file="/WEB-INF/jsp/common/scriptInc.jsp" %>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/webuploader/demo.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/webuploader/webuploader.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/webuploader/style.css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/webuploader/webuploader.min.js"></script>
    <style>
    </style>
    <title>临床指南添加</title>
    <style>
        .layui-form-label {
            width: 115px;
        }
        #uploadimg{margin-top: 10px;}
        .maxPic{line-height:38px;margin-left: 95px;}
        .file-item{float: left; position: relative; height: 25px; margin: 0 20px 20px 0; padding: 4px;}
        .file-item .info{overflow: hidden;}
        .uploader-list{width: 100%; overflow: hidden;}

    </style>
</head>
<body>
<div class="layui-fluid">
    <fieldset style="width:90%;margin:0 auto">

        <div class="layui-card-body">
            <div class="layui-form" lay-filter="layuiadmin-form-useradmin"
                 id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">

                <input type="hidden" name="medical.id" id="id"
                       hide=true>

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">统筹区域</label>
                        <div class="layui-input-inline">
                            <input type="text" id="source"
                                   name="medical.name" autocomplete="off" lay-verify="upload"
                                   class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">机构名称</label>
                        <div class="layui-input-inline">
                            <input type="text" id="source1"
                                   name="medical.name" autocomplete="off" lay-verify="upload"
                                   class="layui-input">
                        </div>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-inline " style="margin-left: 40px">
                        <div id="uploader" class="wu-example">
                            <!--用来存放文件信息-->
                            <div id="fileList" class="uploader-list"></div>
                            <div class="btns">
                                <div id="filepicker">选择文件</div>
                            </div>
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
    </fieldset>
</div>

<script type="text/javascript"
        src="<%=request.getContextPath() %>/app/js/clinicalguideline/clinicalguidelineAdd.js"></script>


</body>
</html>