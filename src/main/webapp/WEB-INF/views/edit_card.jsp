<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var selItem = localStorage.getItem("locales");
		$('#locales').val(selItem ? selItem : 'en');
		$("#locales").change(function() {
			var selectedOption = $('#locales').val();
			if (selectedOption) {
				window.location.replace('?lang=' + selectedOption);
				localStorage.setItem("locales", selectedOption);
			}
		});
	});
</script>
</head>

<body>
	<%@include file="header.jsp"%>

	<div class="edit-card">
		<div class="row">
			<div class="edit-column" style="background-color: #004387;">
				<h2>Edit card</h2>
			</div>
		</div>
		<form:form method="POST" modelAttribute="card"
			enctype="multipart/form-data" class="form-horizontal">

			<div class="row">
				<div class="edit-column" style="background-color: #004387;">
					<label>Name</label>
					<form:input type="text" path="name" id="name" class="form-control" />
				</div>

				<div class="edit-column" style="background-color: #ffffff;">
					<form:errors path="name" style="color: #ff0000"
						class="alert alert-danger" />
				</div>
			</div>

			<div class="row">
				<div class="edit-column" style="background-color: #004387;">
					<label>People</label>
					<form:input type="text" path="people" id="people"
						class="form-control" />
				</div>

				<div class="edit-column" style="background-color: #ffffff;">
					<form:errors path="people" style="color: #ff0000"
						class="alert alert-danger" />
				</div>
			</div>

			<div class="row">
				<div class="edit-column" style="background-color: #004387;">
					<label>Address</label>

					<form:input type="text" path="address" id="address"
						class="form-control" />
				</div>

				<div class="edit-column" style="background-color: #ffffff;">
					<form:errors path="address" style="color: #ff0000"
						class="alert alert-danger" />
				</div>
			</div>

			<div class="row">
				<div class="edit-column" style="background-color: #004387;">
					<label>Email</label>
					<form:input type="text" path="email" id="email"
						class="form-control" />
				</div>

				<div class="edit-column" style="background-color: #ffffff;">
					<form:errors path="email" style="color: #ff0000"
						class="alert alert-danger" />
				</div>
			</div>

			<div class="row">
				<div class="edit-column" style="background-color: #004387;">
					<label>Phone</label>
					<form:input type="text" path="phone" id="phone"
						class="form-control" />
				</div>

				<div class="edit-column" style="background-color: #ffffff;">
					<form:errors path="phone" style="color: #ff0000"
						class="alert alert-danger" />
				</div>
			</div>

			<div class="row">
				<div class="edit-column" style="background-color: #004387;">
					<label>LinkedIn</label>
					<form:input type="text" path="linkedin" id="linkedin"
						class="form-control" />
				</div>
				<div class="edit-column" style="background-color: #ffffff;">
					<form:errors path="linkedin" style="color: #ff0000"
						class="alert alert-danger" />
				</div>
			</div>

			<div class="row">
				<div class="edit-column" style="background-color: #004387;">
					<label>Facebook</label>
					<form:input type="text" path="facebook" id="facebook"
						class="form-control" />
				</div>
				<div class="edit-column" style="background-color: #ffffff;">
					<form:errors path="facebook" style="color: #ff0000"
						class="alert alert-danger" />
				</div>
			</div>

			<div class="row">
				<div class="edit-column" style="background-color: #004387;">
					<label>Twitter</label>
					<form:input type="text" path="twitter" id="twitter"
						class="form-control" />
				</div>

				<div class="edit-column" style="background-color: #ffffff;">
					<form:errors path="twitter" style="color: #ff0000"
						class="alert alert-danger" />
				</div>
			</div>

			<div class="row">
				<div class="edit-column" style="background-color: #004387;">
					<label>Instagram</label>
					<form:input type="text" path="instagram" id="instagram"
						class="form-control" />
				</div>

				<div class="edit-column" style="background-color: #ffffff;">
					<form:errors path="instagram" style="color: #ff0000"
						class="alert alert-danger" />
				</div>
			</div>

			<div class="row">
				<div class="edit-column" style="background-color: #004387;">
					<label>Description</label>

					<form:textarea name="description" path="description"
						id="description" class="form-control" cols="40" rows="5" />
				</div>

				<div class="edit-column" style="background-color: #ffffff;">
					<form:errors path="description" style="color: #ff0000"
						class="alert alert-danger" />
				</div>
			</div>

			<div class="row">
				<div class="edit-column" style="background-color: #004387;">
					<label>Image</label>
					<form:input type="file" path="file" id="file" class="form-control" />

					<c:if test="${card.nameImage != ''}">
						<br />
						<img src="image-<c:out value="${card.login}"/>"
							class="img-rounded" style="height: 200px" alt="Image">

						<div>
							<br /> <a href="<c:url value='/delete-image-${card.login}' />"
								class="btn btn-danger">Delete image</a>
						</div>
					</c:if>
				</div>
			</div>

			<div class="row">
				<div class="edit-column" style="background-color: #004387;">
					<label>Font color</label>
					<div>
						<form:input type="color" value='${card.fontColor}'
							path="fontColor" id="fontColor" class="form-control" />
					</div>
				</div>
			</div>

			<div class="row">
				<div class="edit-column" style="background-color: #004387;">
					<label>Background color</label>
					<div>
						<form:input type="color" value='${card.backgroundColor}'
							path="backgroundColor" id="backgroundColor" class="form-control" />
					</div>
				</div>
			</div>

			<div class="row">
				<div class="edit-column" style="background-color: #004387;">
					<label>Visible</label>
					<div>
						<form:checkbox name="visible" value='${card.visible}'
							path="visible" id="visible" />
					</div>
				</div>
			</div>

			<div class="row">
				<div class="edit-column" style="background-color: #004387;">
					<input type="submit" value="Edit" class="btn btn-warning" />
				</div>
			</div>

			<div class="row">
				<div class="edit-column" style="background-color: #004387;">
					<a href="<c:url value='/delete-user-${card.login}' />"
						class="btn btn-danger">Delete account</a>
				</div>
			</div>
		</form:form>
	</div>

	<div class="edit-card-footer">
		<div id="footer-name">
			<br />&copy; 2017-2018 Internet Visiting Card
		</div>
		<div id="footer-change-lang">
			<label><spring:message code="lang" /></label>
		</div>
		<div id="footer-lang">
			<select id="locales" class="form-control">
				<option value="en">English</option>
				<option value="ua">Українська</option>
			</select>
		</div>
		<div id="footer-contact">
			<spring:message code="contact_inf" />
			: mishastrilets@gmail.com
		</div>
	</div>
</body>
</html>