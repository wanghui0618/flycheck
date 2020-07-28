<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  isErrorPage="true"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>超时</title>
</head>
<style type="text/css">
    body{
		background:rgb(231,231,231);
	}
    .body-timeout{
		width:980px;
		margin:0 auto;
		padding-top:20px;
	}
    .body-timeout h1{
		position:relative;
		width:500px;
		height:400px;
		background:url(images/timeout2.png);
		margin:10% auto;
	}
	.body-timeout h1 span{
		position:absolute;
		padding-left:13%;	
		font-size:32px;
		font-family:"华文行楷";
		color:rgb(58,139,143);
		top:-10%;
	}
</style>
<%response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);%>
<body>
<img onclick="javascript:history.go(-1);">
    <div class="body-timeout">
        <h1>
            <span>服务器连接超时，请重试！</span>
        </h1>
    </div>
</body>
</html>
