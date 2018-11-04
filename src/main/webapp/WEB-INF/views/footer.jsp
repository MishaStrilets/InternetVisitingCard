<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
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
	<div class="footer">
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
			mishastrilets@gmail.com
		</div>
	</div>
</body>
</html>