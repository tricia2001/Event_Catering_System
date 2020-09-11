<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Search User Events</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<style type="text/css">
<%@include file="CSS/style.css"%>
</style>
<link href="myStyle.css" rel="stylesheet" type="text/css" />
<body>
      <div class="logo"><h1><a href="<c:url value='/' />">Campus Event Catering System</a></h1></div>    
	  <div class="content">Search User Events</div>
      <div class="menu_nav">
  </div>
<input name="errMsgs"  value="<c:out value='${errorMsgs.errorMsg}'/>" class="errorPane">
<table>
<tr>
	<td>
	<form action="<c:url value='/EventController?action=searchUserEvent' />" method="post">
	<input name="user" value="<c:out value='${user.username}'/>" type="hidden">
	<table style="width: 1200px; ">
					<tr>
  						<td> Date:</td>
 						<td> <input name="date" value="<c:out value='${event.date}'/>" type="date">  </td>
  						<td> <input name="eventDateError"  value="<c:out value='${errorMsgs.eventDateError}'/>" class="errorMsg"> </td>
					</tr>
    				<tr>
    					<td> Start Time: </td>
    					<td> <input name="time" value="<c:out value='${event.time}'/>" type="time"> </td>
  						<td> <input name="companyIDerror"  value="<c:out value='${errorMsgs.eventTimeError}'/>" class="errorMsg"> </td>
    				</tr>
			</table>
  <input type="submit" value="Submit">
	</form>
	<a href="<c:url value='/UserController?action=logout'/>"><input type="submit" value="Logout"></a>      
</td>
</tr>
</table>
</body>
</html>