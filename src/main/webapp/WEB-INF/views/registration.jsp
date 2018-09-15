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

		<div class="registration-form">
			<h2>Registration Form:</h2>

			<form:form method="POST" modelAttribute="user"
				class="form-horizontal">
				<form:input type="hidden" path="id" id="id" />
				<form:input type="hidden" path="role" id="role" />

				<div class="form-group">
					<label class="col-sm-2 control-label">Login:</label>

					<div class="col-sm-4">
						<form:input type="text" path="login" id="login"
							class="form-control" />
					</div>

					<div class="col-sm-6">
						<form:errors path="login" style="color: #ff0000"
							class="alert alert-danger" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">Password:</label>

					<div class="col-sm-4">
						<form:input type="password" path="password" id="password"
							class="form-control" />
					</div>

					<div class="col-sm-6">
						<form:errors path="password" style="color: #ff0000"
							class="alert alert-danger" />
					</div>
				</div>

				<div>
					<input type="submit" value="Sign up" class="btn btn-success" />
				</div>
			</form:form>
		</div>

		<%@include file="footer.jsp"%>
	</div>
</body>
</html>