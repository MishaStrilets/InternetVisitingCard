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

		<div class="login-form">
			<h2>Login Form:</h2>
			<c:url var="loginUrl" value="/login" />
			<form action="${loginUrl}" method="post" class="form-horizontal">

				<c:if test="${param.error != null}">
					<p class="alert alert-danger">Invalid login and password.</p>
				</c:if>

				<div class="input-group">
					<span class="input-group-addon"><i
						class="glyphicon glyphicon-user"></i></span> <input class="form-control"
						type="text" name="login" placeholder="Enter Login" required>
				</div>
				<br />
				<div class="input-group">
					<span class="input-group-addon"><i
						class="glyphicon glyphicon-lock"></i></span> <input class="form-control"
						type="password" name="password" placeholder="Enter Password"
						required>
				</div>

				<div class="input-group">
					<div class="checkbox">
						<label><input type="checkbox" id="rememberme"
							name="remember-me"> Remember Me</label>
					</div>
				</div>

				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />

				<div>
					<input type="submit" value="Log in" class="btn btn-default" />
				</div>
			</form>
		</div>

		<%@include file="footer.jsp"%>
	</div>
</body>
</html>