<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
<%@include file="CSS/style.css"%>
</style>
<link href="myStyle.css" rel="stylesheet" type="text/css" />
<title>Assign Caterer</title>
</head>
<div class="header_resize">
	<div class="logo">
		<h1>
			<a href="<c:url value='/' />">Campus Event Catering System</a>
		</h1>
	</div>
	<div class="content">Assign Catering Staff</div>
</div>

<body style="width: 900px;">
	<input name="errMsgs" value="<c:out value='${errorMsgs.errorMsg}'/>"
		class="errorPane">
	<table>
		<tr>
			<td>
				<form
					action="<c:url value='/EventController?action=assignCaterer' />"
					method="post">
					<input type="hidden" name="id"
						value="<c:out value='${EVENTS.eventId}'/>">
						<input type="hidden" name="date1" value="<c:out value='${event.date}'/>" class="hidden"> 
					<input type="hidden" name="time1" value="<c:out value='${event.time}'/>"class="hidden"> 
					<table style="width: 1200px;">

						<tr>
							<td>Enter caterer last name:</td>
							<td><input name="lname"
								value="<c:out value='${event.catererLast}'/>"></td>
							<td><input name="eventDateError"
								value="<c:out value='${errorMsgs.catererError}'/>"
								class="errorMsg"></td>
						</tr>
						<tr>
							<td>Enter caterer first name:</td>
							<td><input name="fname"
								value="<c:out value='${event.catererFirst}'/>"></td>
							<td><input name="eventDateError"
								value="<c:out value='${errorMsgs.catererError}'/>"
								class="errorMsg"></td>
						</tr>

						
					</table>
		
<br>
			<input type="submit" value="Assign">


		</form>
		<input name="back" type="submit" value="Back" onclick="history.back()">
		<a href="<c:url value='/UserController?action=logout'/>"><input
			name="logoutButton" type="submit" value="Logout"></a>
		</td>
		</tr>
	</table>
</body>
</html>