<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${user.login}</title>

<link rel="icon" type="image/png" href="/static/images/icon.png" />
<link
	href="http://cdn.jsdelivr.net/webjars/bootstrap/3.3.4/css/bootstrap.min.css"
	rel="stylesheet" media="screen" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="<c:url value='/static/css/style.css' />" rel="stylesheet" />

</head>

<body
	style="color: ${user.fontColor}; background-color: ${user.backgroundColor}">
	<div class="view-card">
		<div class="row">
			<div class="col-sm-7">
				<h1>
					<b>${user.name}</b>
				</h1>
				<hr />

				<c:if test="${user.people != ''}">
					<h2>
						<i>Contact person: </i>${user.people}</h2>
				</c:if>

				<c:if test="${user.address != ''}">
					<h2>
						<i>Address: </i>${user.address}</h2>
				</c:if>

				<c:if test="${user.email != ''}">
					<h2>
						<i>Email: </i>${user.email}</h2>
				</c:if>

				<c:if test="${user.phone != ''}">
					<h2>
						<i>Phone number: </i>${user.phone}</h2>
				</c:if>

				<c:if test="${user.linkedin != '' }">
					<a href="<c:url value='${user.linkedin}' />" target="_blank"><i
						class="fa fa-linkedin-square"
						style="font-size: 48px; color: #245e99"></i></a>
				</c:if>

				<c:if test="${user.facebook != '' }">
					<a href="<c:url value='${user.facebook}' />" target="_blank"><i
						class="fa fa-facebook-square"
						style="font-size: 48px; color: #245e99"></i></a>
				</c:if>

				<c:if test="${user.twitter != ''}">
					<a href="<c:url value='${user.twitter}' />" target="_blank"><i
						class="fa fa-twitter-square"
						style="font-size: 48px; color: #66b1ff"></i></a>
				</c:if>

				<c:if test="${user.instagram != ''}">
					<a href="<c:url value='${user.instagram}' />" target="_blank"><i
						class="	fa fa-instagram" style="font-size: 48px; color: #000000"></i></a>

				</c:if>
			</div>

			<c:if test="${user.nameImage != ''}">
				<div class="col-sm-5">
					<img src="image-<c:out value="${user.login}"/>" class="img-rounded"
						style="width: 100%" alt="Image">
				</div>
			</c:if>
		</div>
		<hr />

		<h2>${user.description}</h2>
	</div>
</body>
</html>