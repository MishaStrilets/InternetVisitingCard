<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

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
	<div class="index">
		<div class="row text-center">
			<h1>Welcome to Internet Visiting Card site!</h1>
		</div>
		<div>
			<h3>
				&nbsp;On this site you can create your own internet visiting card.
				For this you need to register. Then you can add information about
				yourself, such as:<br />&nbsp;&nbsp;- the name;<br />&nbsp;&nbsp;-
				contact person;<br />&nbsp;&nbsp;- contact data (address, phone
				number, email);<br />&nbsp;&nbsp;- links to social networking sites
				(LinkedIn, Facebook, Twitter, Instagram);<br />&nbsp;&nbsp;-
				description;<br />&nbsp;&nbsp;- photo.<br />&nbsp;Also, the user
				can change the font color and background color. After you added need
				information, you will get your own internet visiting card. In
				result, you can share your card through URL.<br />&nbsp;Users of
				site can search cards and leave reviews on the cards.
			</h3>
		</div>
		<div class="row text-center">
			<h2>Good luck!</h2>
		</div>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>