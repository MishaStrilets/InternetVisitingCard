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
<link
	href="http://cdn.jsdelivr.net/webjars/bootstrap/3.3.4/css/bootstrap.min.css"
	href="@{/webjars/bootstrap/3.3.4/css/bootstrap.min.css}"
	rel="stylesheet" media="screen" />
<link href="<c:url value='/static/css/style.css' />" rel="stylesheet"></link>
</head>

<body>
	<div>
		<%@include file="header.jsp"%>

		<div class="edit-form">
			<h2>Edit Card:</h2>

			<form:form method="POST" modelAttribute="card"
				enctype="multipart/form-data" class="form-horizontal">

				<div class="form-group">
					<label class="col-sm-2 control-label">Name:</label>
					<div class="col-sm-3">
						<form:input type="text" path="name" id="name" class="form-control" />
					</div>
					<div class="col-sm-6">
						<form:errors path="name" style="color: #ff0000"
							class="alert alert-danger" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">Description:</label>
					<div class="col-sm-3">

						<form:textarea name="description" path="description"
							id="description" class="form-control" cols="40" rows="5" />

					</div>
					<div class="col-sm-6">
						<form:errors path="description" style="color: #ff0000"
							class="alert alert-danger" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">People:</label>
					<div class="col-sm-3">
						<form:input type="text" path="people" id="people"
							class="form-control" />
					</div>
					<div class="col-sm-6">
						<form:errors path="people" style="color: #ff0000"
							class="alert alert-danger" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">Address:</label>
					<div class="col-sm-3">
						<form:input type="text" path="address" id="address"
							class="form-control" />
					</div>
					<div class="col-sm-6">
						<form:errors path="address" style="color: #ff0000"
							class="alert alert-danger" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">Email:</label>
					<div class="col-sm-3">
						<form:input type="text" path="email" id="email"
							class="form-control" />
					</div>
					<div class="col-sm-6">
						<form:errors path="email" style="color: #ff0000"
							class="alert alert-danger" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">Phone:</label>
					<div class="col-sm-3">
						<form:input type="text" path="phone" id="phone"
							class="form-control" />
					</div>
					<div class="col-sm-6">
						<form:errors path="phone" style="color: #ff0000"
							class="alert alert-danger" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">Facebook:</label>
					<div class="col-sm-3">
						<form:input type="text" path="facebook" id="facebook"
							class="form-control" />
					</div>
					<div class="col-sm-6">
						<form:errors path="facebook" style="color: #ff0000"
							class="alert alert-danger" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">Twitter:</label>
					<div class="col-sm-3">
						<form:input type="text" path="twitter" id="twitter"
							class="form-control" />
					</div>
					<div class="col-sm-6">
						<form:errors path="twitter" style="color: #ff0000"
							class="alert alert-danger" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">Instagram:</label>
					<div class="col-sm-3">
						<form:input type="text" path="instagram" id="instagram"
							class="form-control" />
					</div>
					<div class="col-sm-6">
						<form:errors path="instagram" style="color: #ff0000"
							class="alert alert-danger" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">Image:</label>
					<div class="col-sm-3">
						<form:input type="file" path="file" id="file" class="form-control" />

						<c:if test="${card.nameImage != ''}">
							<br />
							<img src="image-<c:out value="${card.login}"/>"
								class="img-rounded" style="width: 100%" alt="Image">
						</c:if>

						<div>
							<br /> <a href="<c:url value='/delete-image-${card.login}' />"
								class="btn btn-danger">Delete image</a>
						</div>
					</div>
				</div>

				<div>
					<input type="submit" value="Edit" class="btn btn-warning" />
				</div>
			</form:form>
		</div>

		<%@include file="footer.jsp"%>
	</div>
</body>
</html>