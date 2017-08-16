<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Internet Visiting Card</title>
<link
	href="http://cdn.jsdelivr.net/webjars/bootstrap/3.3.4/css/bootstrap.min.css"
	href="@{/webjars/bootstrap/3.3.4/css/bootstrap.min.css}"
	rel="stylesheet" media="screen" />
<link href="<c:url value='/static/css/style.css' />" rel="stylesheet"></link>
</head>

<body>
	<div>
		<%@include file="header.jsp"%>
		<div class="jumbotron">
			<div>
				<div class="row text-center">
					<h2>Welcome to Internet Visiting Card site!!!</h2>
				</div>
				<div>
					<h3 class="info">On this site you can create your own
						internet visiting card.</h3>
				</div>
			</div>
		</div>
		<%@include file="footer.jsp"%>
	</div>
</body>
</html>