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
	<div>
		<%@include file="header.jsp"%>

		<div class="login-user">
			<h2>
				<spring:message code="login_page" />
			</h2>
			<c:url var="loginUrl" value="/login" />
			<form action="${loginUrl}" method="post" class="form-horizontal">
				<div class="input-group">
					<spring:message code="login" var="login" />
					<span class="input-group-addon"><i
						class="glyphicon glyphicon-user"></i></span> <input class="form-control"
						type="text" name="login" placeholder='${login}' required>
				</div>
				<br />
				<div class="input-group">
					<spring:message code="password" var="password" />
					<span class="input-group-addon"><i
						class="glyphicon glyphicon-lock"></i></span> <input class="form-control"
						type="password" name="password" placeholder='${password}' required>
				</div>

				<div class="input-group">
					<label><input type="checkbox" id="rememberme"
						name="remember-me"> <spring:message code="remember_me" /></label>
				</div>

				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />

				<div>
					<input type="submit" value=<spring:message code="login_btn" />
						class="btn btn-default" />
				</div>
			</form>
		</div>
		<div class="login-error">
			<c:if test="${param.error != null}">
				<p class="alert alert-danger">
					<spring:message code="invalid_login_password" />
				</p>
			</c:if>
		</div>

		<%@include file="footer.jsp"%>
	</div>
</body>
</html>