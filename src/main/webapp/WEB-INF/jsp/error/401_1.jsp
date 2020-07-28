<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isErrorPage="true"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>401.1</title>
</head>
<style type="text/css">
    body{
		background:rgb(219,219,219);
	}
    .body-401{
		width:980px;
		margin:0 auto;
	}
    .body-401 h1{
		position:relative;
		width:550px;
		height:200px;
		background:url(../images/401bg.jpg) no-repeat left top;
		margin:15% auto;
	}
	.body-401 h1 span{
		position:absolute;
		padding-left:30%;	
		top:13%;
		font-size:36px;
		font-family:"华文行楷";
		color:rgb(58,139,143);
	}
</style>
<%-- <%response.setStatus(401.1);%> --%>
<%response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);%>
<body>
 <img onclick="javascript:history.go(-1);">
    <div class="body-401">
         <h1>
            <span>Sorry！<p>当前您没有访问权限！</span>
         </h1>
    </div>
</body>
</html>
