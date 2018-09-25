<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
		<div class="registration-user">
		
			<div class="row">
				<div class="registration-column" style="background-color: #004387;">
					<h2>Registration</h2>
				</div>
			</div>

			<form:form method="POST" modelAttribute="user"
				class="form-horizontal">
				<form:input type="hidden" path="id" id="id" />
				<form:input type="hidden" path="role" id="role" />

				<div class="row">
					<div class="registration-column" style="background-color: #004387;">
						<label>Login</label>
						<form:input type="text" path="login" id="login"
							class="form-control" />
					</div>
					<div class="registration-column"
						style="background-color: #ffffff; width: 50%;">
						<form:errors path="login" style="color: #ff0000"
							class="alert alert-danger" />
					</div>
				</div>

				<div class="row">
					<div class="registration-column" style="background-color: #004387;">
						<label>Password</label>
						<form:input type="password" path="password" id="password"
							class="form-control" />
					</div>
					<div class="registration-column"
						style="background-color: #ffffff;">
						<form:errors path="password" style="color: #ff0000"
							class="alert alert-danger" />
					</div>
				</div>

				<div class="row">
					<div class="registration-column" style="background-color: #004387;">
						<input type="submit" value="Sign up" class="btn btn-success" />
					</div>
				</div>
			</form:form>
		</div>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>