<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Internet Visiting Card</title>

<link rel="icon" type="image/png" href="/static/images/icon.png" />
<link
	href="http://cdn.jsdelivr.net/webjars/bootstrap/3.3.4/css/bootstrap.min.css"
	rel="stylesheet" media="screen" />
<link href="<c:url value='/static/css/style.css' />" rel="stylesheet" />
</head>

<body>
	<%@include file="header.jsp"%>

	<div class="index">
		<div class="row text-center">
			<h1>
				<spring:message code="home_1" />
			</h1>
		</div>

		<div>
			<h3>
				&nbsp;
				<spring:message code="home_2" />
				<br />&nbsp;&nbsp;
				<spring:message code="home_3" />
				<br />&nbsp;&nbsp;
				<spring:message code="home_4" />
				<br />&nbsp;&nbsp;
				<spring:message code="home_5" />
				<br />&nbsp;&nbsp;
				<spring:message code="home_6" />
				<br />&nbsp;&nbsp;
				<spring:message code="home_7" />
				<br />&nbsp;&nbsp;
				<spring:message code="home_8" />
				<br />&nbsp;
				<spring:message code="home_9" />
				<br />&nbsp;
				<spring:message code="home_10" />
			</h3>
		</div>
		<div class="row text-center">
			<h2>
				<spring:message code="home_11" />
			</h2>
		</div>
	</div>

	<%@include file="footer.jsp"%>
</body>
</html>