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
		<div class="add-review">
			<h2>Add review</h2>
			<form:form method="POST" modelAttribute="review"
				class="form-horizontal">
				<div class="form-group">
					<label class="col-sm-2 control-label">Author</label>
					<div class="col-sm-6">
						<form:input type="text" path="author" id="author"
							class="form-control" />
					</div>
					<div class="col-sm-4">
						<form:errors path="author" style="color: #ff0000"
							class="alert alert-danger" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">Description</label>
					<div class="col-sm-6">

						<form:textarea name="description" path="description"
							id="description" class="form-control" cols="40" rows="5" />

					</div>
					<div class="col-sm-4">
						<form:errors path="description" style="color: #ff0000"
							class="alert alert-danger" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">Rating</label>
					<div class="col-sm-6">
						<form:select name="rating" path="rating" id="rating"
							class="form-control">
							<option value="">-</option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
							<option value="7">7</option>
							<option value="8">8</option>
							<option value="9">9</option>
							<option value="10">10</option>
						</form:select>

					</div>
					<div class="col-sm-4">
						<form:errors path="rating" style="color: #ff0000"
							class="alert alert-danger" />
					</div>
				</div>

				<div>
					<input type="submit" value="Add" class="btn btn-warning" />
				</div>
			</form:form>
		</div>
		<%@include file="footer.jsp"%>
	</div>
</body>
</html>