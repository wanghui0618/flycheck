<!DOCTYPE html >
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isErrorPage="true"%>
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>404</title>
</head>
<style type="text/css">
    body{
		background:rgb(231,231,231);
	}
    .body-404{
		width:980px;
		height:640px;
		margin:0 auto;
	}
    .body-404 h1{
		position:relative;
		width:678px;
		height:635px;
		background:url(../images/404bg2.jpg) center top;
		margin:0 auto;
	}
	.body-404 h1 span{
		position:absolute;
		padding-left:23%;	
		top:13%;
		font-size:36px;
		font-family:"华文行楷";
		color:rgb(58,139,143);
	}
</style>
<%response.setStatus(HttpServletResponse.SC_NOT_FOUND);%>
<body>
 <img onclick="javascript:history.go(-1);">
    <div class="body-404">
        <h1>
            <span>您要访问的资源不存在!</span>
        </h1>
    </div>
</body>
</html>
