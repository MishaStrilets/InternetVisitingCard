<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${card.login}</title>
<link
	href="http://cdn.jsdelivr.net/webjars/bootstrap/3.3.4/css/bootstrap.min.css"
	href="@{/webjars/bootstrap/3.3.4/css/bootstrap.min.css}"
	rel="stylesheet" media="screen" />
<link href="<c:url value='/static/css/style.css' />" rel="stylesheet"></link>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<body>
	<div class="view">
		<div class="row">

			<div class="col-sm-8">
				<h1>${card.name}</h1>
				<h2>${card.people}</h2>
				<h2>${card.address}</h2>
				<h2>${card.email}</h2>
				<h2>${card.phone}</h2>

				<c:if test="${card.facebook != '' }">
					<a href="<c:url value='${card.facebook}' />" target="_blank"><i
						class="fa fa-facebook-square"
						style="font-size: 48px; color: #245e99"></i></a>
				</c:if>

				<c:if test="${card.twitter != ''}">
					<a href="<c:url value='${card.twitter}' />" target="_blank"><i
						class="fa fa-twitter-square"
						style="font-size: 48px; color: #66b1ff"></i></a>
				</c:if>

				<c:if test="${card.instagram != ''}">
					<a href="<c:url value='${card.instagram}' />" target="_blank"><i
						class="	fa fa-instagram" style="font-size: 48px; color: #000000"></i></a>

				</c:if>

			</div>

			<div class="col-sm-4">
				<img
					src="https://upload.wikimedia.org/wikipedia/uk/b/bf/Chelsea.png"
					class="img-rounded" style="width: 100%" alt="Image">
			</div>
		</div>
		<h2>${card.description}</h2>
	</div>
</body>
</html>