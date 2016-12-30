<%@ taglib prefix="check" uri="/WEB-INF/tlds/checkLogin.tld"%>
<%@page import="edu.nju.models.ScoresPO"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CheckResult</title>

<style>
div, form {
	margin-left: 40px;
	margin-bottom: 10px;
}

.viewer {
	margin-left: 0;
}

.viewer div {
	display: inline-block;
}
</style>

</head>
<body>

	<check:checkLogin req="<%=request%>" resp="<%=response%>"/>

	<%
		ServletContext Context = getServletContext();
		int totalCount = (int) Context.getAttribute("totalCount");
		int onlineCount = (int) Context.getAttribute("onlineCount");
		int visitorCount = (int) Context.getAttribute("visitorCount");
	%>

	<div class="viewer">
		<div>
			访客总人数：<%=totalCount%></div>
		<div>
			在线人数：<%=onlineCount%></div>
		<div>
			游客人数：<%=visitorCount%></div>
	</div>

	<jsp:useBean id="listBean" type="edu.nju.action.business.ScoreListBean"
		scope="session"></jsp:useBean>

	<%
		ArrayList<ScoresPO> scoreList = listBean.getScoreList();
		ArrayList<ScoresPO> absentList = listBean.getAbsentList();

		for (ScoresPO sp : scoreList) {
	%>

	<div><%=sp.getCourse()%>：&nbsp;<%=sp.getScore()%></div>

	<%
		}

		if (absentList.size() > 0) {
	%>

	<div>
		注意！您有以下<%=absentList.size()%>场测验未参加：
	</div>

	<%
		}
		for (ScoresPO abPo : absentList) {
	%>
	<div><%=abPo.getCourse()%></div>
	<%
		}
	%>

	<form method='get' action='/J2EE-Lab/Logout'>
		<input type='submit' value='注销'>
	</form>
</body>
</html>