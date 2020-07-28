<%@page import="com.dhcc.framework.common.config.PropertiesBean"%>
<%@page isErrorPage="true" contentType="text/html;charset=utf-8"%>
<%
	if (request.getAttribute("javax.servlet.error.message").toString()
			.indexOf("TicketValidationException") != -1) {
		String url = PropertiesBean.getInstance().getProperty(
				"conf.sysAddress")
				+ "/"
				+ PropertiesBean.getInstance().getProperty(
						"conf.sysShorName");
		response.sendRedirect(url);
	}
%>
<html>
<head>
<title>服务器内部错误</title>
</head>
<style type="text/css">
body {
	background: rgb(219, 219, 219);
}

.body-500 {
	width: 980px;
	margin: 0 auto;
}

.body-500 h1 {
	position: relative;
	width: 600px;
	height: 261px;
	background: url(../images/500.png) no-repeat left top;
	margin: 13% auto;
}

.body-500 h1 span {
	position: absolute;
	padding-left: 40%;
	top: 13%;
	font-size: 36px;
	font-family: "华文行楷";
	color: rgb(58, 139, 143);
}
</style>
<%
	response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
%>
<body>
	<img onclick="javascript:history.go(-1);">
	<div>
		<h1>
			<span>Sorry！
				<p />服务器内部错误，<br />请稍后再试！
			</span>
		</h1>
	</div>
</body>
</html>


