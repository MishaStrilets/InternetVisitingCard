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
	href="@{/webjars/bootstrap/3.3.4/css/bootstrap.min.css}"
	rel="stylesheet" media="screen" />
<link href="<c:url value='/static/css/style.css' />" rel="stylesheet"></link>
</head>

<body>
	<div>
		<%@include file="header.jsp"%>

		<div class="row">
			<div class="col-sm-8">

				<div class="list-form">
					<h2>List of cards</h2>
					<table class="table table-hover">

						<tbody>
							<c:forEach items="${users}" var="user">

								<tr
									style="color: ${user.fontColor}; background-color: ${user.backgroundColor}">

									<td><h3>
											<b>${user.name}</b>
										</h3></td>

									<c:choose>
										<c:when test="${user.nameImage != ''}">
											<td><img src="image-<c:out value="${user.login}"/>"
												class="img-rounded" style="width: 25%" alt="Image"></td>
										</c:when>
										<c:otherwise>
											<td></td>
										</c:otherwise>
									</c:choose>

									<sec:authorize access="hasRole('ADMIN')">
										<td><h4>${user.login}</h4></td>
									</sec:authorize>

									<td><a href="<c:url value='/${user.login}' />"
										class="btn btn-info" target="_blank"><span
											class="glyphicon glyphicon-eye-open"></span></a></td>

									<sec:authorize access="hasRole('ADMIN')">
										<td><a
											href="<c:url value='/delete-card-${user.login}' />"
											class="btn btn-danger"><span
												class="glyphicon glyphicon-trash"></span></a></td>
									</sec:authorize>

								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

			<div class="col-sm-4">
				<div class="search-form">
					<h2>Search:</h2>

					<form:form method="POST" modelAttribute="search"
						class="form-horizontal">

						<form:input type="search" path="name" id="name" placeholder="Name"
							class="form-control" />
						<br />

						<form:input type="search" path="description" id="description"
							placeholder="Description" class="form-control" />
						<br />

						<form:input type="search" path="address" id="address"
							placeholder="Address" class="form-control" />
						<br />

						<div>
							<button type="submit" class="btn btn-primary">
								<span class="glyphicon glyphicon-search"></span>
							</button>
						</div>
					</form:form>
				</div>
			</div>
		</div>

		<%@include file="footer.jsp"%>
	</div>
</body>
</html>