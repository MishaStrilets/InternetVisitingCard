<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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

	<div class="add-review">

		<div class="row">
			<div class="review-column" style="background-color: #004387;">
				<h2>Add review</h2>
			</div>
		</div>

		<form:form method="POST" modelAttribute="review"
			class="form-horizontal">

			<div class="row">
				<div class="review-column" style="background-color: #004387;">
					<label>Author</label>
					<form:input type="text" path="author" id="author"
						class="form-control" />
				</div>
				<div class="review-column" style="background-color: #ffffff;">
					<form:errors path="author" style="color: #ff0000"
						class="alert alert-danger" />
				</div>
			</div>

			<div class="row">
				<div class="review-column" style="background-color: #004387;">
					<label>Description</label>
					<form:textarea name="description" path="description"
						id="description" class="form-control" cols="40" rows="5" />
				</div>
				<div class="review-column" style="background-color: #ffffff;">
					<form:errors path="description" style="color: #ff0000"
						class="alert alert-danger" />
				</div>
			</div>

			<div class="row">
				<div class="review-column" style="background-color: #004387;">
					<label>Rating</label>
					<form:select name="rating" path="rating" id="rating"
						class="form-control">
						<option value="">-</option>
						<option value="0">0</option>
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
				<div class="review-column" style="background-color: #ffffff;">
					<form:errors path="rating" style="color: #ff0000"
						class="alert alert-danger" />
				</div>
			</div>

			<div class="row">
				<div class="review-column" style="background-color: #004387;">
					<input type="submit" value="Add" class="btn btn-warning" />
				</div>
			</div>
		</form:form>
	</div>

	<%@include file="footer.jsp"%>
</body>
</html>