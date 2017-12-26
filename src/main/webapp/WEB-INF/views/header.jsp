<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head lang="en">
</head>
<body>
	<div class="container">
		<div class="header">
			<nav class="navbar">
				<div class="container-fluid">

					<ul class="nav navbar-nav">
						<li><a href="<c:url value='/'/>" class="home"><span
								class="glyphicon glyphicon-home"></span></a></li>

						<li><a href="<c:url value='/list-cards'/>" class="cards"><span
								class="glyphicon glyphicon-list"></span></a></li>

						<li><c:if
								test="${pageContext.request.userPrincipal.name != null}">
								<a
									href="<c:url value='/edit-card-${pageContext.request.userPrincipal.name}'/>"
									class="cards"><span class="glyphicon glyphicon-edit"></span></a>
							</c:if></li>

						<li><c:if
								test="${pageContext.request.userPrincipal.name != null}">
								<a
									href="<c:url value='/${pageContext.request.userPrincipal.name}'/>"
									class="cards" target="_blanc"><span
									class="glyphicon glyphicon-eye-open"></span></a>
							</c:if></li>
					</ul>

					<ul class="nav navbar-nav navbar-right">
						<li><c:if
								test="${pageContext.request.userPrincipal.name == null}">
								<a href="<c:url value='/registration-user'/>" class="sign"><span
									class="glyphicon glyphicon-user"></span></a>
							</c:if></li>

						<li><c:if
								test="${pageContext.request.userPrincipal.name == null}">
								<a href="<c:url value='/login'/>" class="login"><span
									class="glyphicon glyphicon-log-in"></span></a>
							</c:if> <c:if test="${pageContext.request.userPrincipal.name != null}">
								<a href="<c:url value='/logout'/>" class="login"><span
									class="glyphicon glyphicon-log-out"></span></a>
							</c:if></li>
					</ul>
				</div>
			</nav>
		</div>
	</div>
</body>
</html>