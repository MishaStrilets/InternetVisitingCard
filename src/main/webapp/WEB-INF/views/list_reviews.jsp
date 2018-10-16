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
</head>

<body>
	<div>
		<%@include file="header.jsp"%>

		<div class="row">
			<div class="col-sm-8">
				<div class="list-reviews">
					<h2>
						<spring:message code="list_reviews" />
					</h2>

					<h3>
						<spring:message code="average_rating" />
						${averageRating}
					</h3>

					<table class="table table-condensed"
						style="color: #ffffff; table-layout: fixed">
						<thead>
							<tr>
								<th><spring:message code="author" /></th>
								<th><spring:message code="description" /></th>
								<th><spring:message code="rating" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${reviews}" var="review">
								<tr>
									<td><i>${review.author}</i></td>
									<td style="word-wrap: break-word">${review.description}</td>
									<td><b>${review.rating}</b></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<%@include file="footer.jsp"%>
	</div>
</body>
</html>