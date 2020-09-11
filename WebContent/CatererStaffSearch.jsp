<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Staff Event Search</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<style type="text/css">
<%@include file="CSS/style.css"%>
</style>
<link href="myStyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="script.js"></script>
</head>
<body>
      <div class="logo"><h1><a href="<c:url value='/' />">Campus Event Catering System</a></h1></div>
      <div class="content">Caterer Staff Event Search</div>
      <div class="menu_nav">
      </div>
      <input name="errMsg"  value="<c:out value='${errorMsgs.errorMsg}'/>" class="errorPane">
      <table>
      <tr>
      	<td>
      		<form action="<c:url value='/EventController?action=searchCatererEvent' />" method="post">
			<input type="hidden" name="user" value="<c:out value='${username}'/>" >
			<table style="width: 1200px; ">
					<tr>
  						<td> Date: </td>
 						<td> <input name="date" value="<c:out value='${event.date}'/>" type="date">  </td>
  						<td> <input name="dateError"  value="<c:out value='${errorMsgs.eventDateError}'/>" class="errorMsg"> </td>
					</tr>
    				<tr>
    					<td> Start Time: </td>
    					<td> <input name="time" value="<c:out value='${event.time}'/>" type="time"> </td>
  						<td> <input name="startTimeerror"  value="<c:out value='${errorMsgs.eventTimeError}'/>" class="errorMsg"> </td>
    				</tr>
			</table>
			<br>
  			<input type="submit" value="Submit">
			</form> 
			<a href="<c:url value='/UserController?action=logout'/>"><input type="submit" value="Logout"></a>     
		</td>
		</tr>
	</table>
</body>
</html>