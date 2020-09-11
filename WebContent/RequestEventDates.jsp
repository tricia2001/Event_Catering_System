<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Request Event</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<style type="text/css">
<%@include file="CSS/style.css"%>
</style>
<link href="myStyle.css" rel="stylesheet" type="text/css" />
<body>
	<div class="logo">
		<h1>
			<a href="<c:url value='/' />">Campus Event Catering System</a>
		</h1>
	</div>
	<div class="content">Request Event</div>
	<div class="menu_nav"></div>
	<input name="errMsgs" value="<c:out value='${errorMsgs.errorMsg}'/>"
		class="errorPane">
	<input name="none" value="<c:out value='${none}'/>" class="errorPane">
	<table>
		<tr>
			<td>
				<form action="<c:url value='/EventController?action=Reserve' />"
					method="post">


					<input name="user" value="<c:out value='${event.username}'/>"
						type="hidden">
					<table style="width: 1200px;">
						<tr>
							<td>Date:</td>
							<td><input name="date"
								value="<c:out value='${event.date}'/>" type="date"></td>
							<td><input name="eventDateError"
								value="<c:out value='${errorMsgs.eventDateError}'/>"
								class="errorMsg"></td>
						</tr>
						<tr>
							<td>Start Time:</td>
							<td><input name="time"
								value="<c:out value='${event.time}'/>" type="time"></td>
							<td><input name="companyIDerror"
								value="<c:out value='${errorMsgs.eventTimeError}'/>"
								class="errorMsg"></td>
						</tr>
					</table>
					<input type="submit" value="Submit">
				</form> <a href="<c:url value='/UserController?action=logout'/>"><input
					name="logoutButton" type="submit" value="Logout"></a>
			</td>
		</tr>
	</table>
</body>
</html>