<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>

<style>
div {
	display: inline-block;
	margin-left: 20px;
}
</style>

</head>
<body>

	<%
		ServletContext Context = getServletContext();
		int totalCount = (int) Context.getAttribute("totalCount");
		int onlineCount = (int) Context.getAttribute("onlineCount");
		int visitorCount = (int) Context.getAttribute("visitorCount");
	%>

	<div>
		<form method='post' action='/J2EE-Lab/Login'>
			<input type='text' name='id' placeholder='请输入学号'> <input
				type='submit' value='登录'>
		</form>
	</div>

	<div>
		<div>
			访客总人数：<%=totalCount%></div>
		<div>
			在线人数：<%=onlineCount%></div>
		<div>
			游客人数：<%=visitorCount%></div>
	</div>

</body>
</html>