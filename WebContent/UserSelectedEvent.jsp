<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
<%@include file="CSS/style.css"%>
</style>
<title>View Selected Event</title>
</head>
    <div class="header_resize">
      <div class="logo"><h1><a href="<c:url value='/' />">Campus Event Catering System</a></h1></div>
	  <div class="content">View Selected Event</div>
      <div class="menu_nav">
      </div>
  </div>

<body>

<table>
  <tr>
   <td>
   <form name="userEventForm" action="<c:url value='/EventController' />" method="post">
         <input name="id" value="<c:out value='${EVENTS.eventId}'/>" type="hidden">
         <input name="username" value="${EVENTS.username}" type="hidden">
     <br>
         <table border="1" class="myTable"> 
    <tr>
    <td> Last Name: </td>
    <td> <c:out value="${EVENTS.userLast}" /> </td>
    </tr>

    <tr>
    <td> First Name: </td>
    <td> <c:out value="${EVENTS.userFirst}"/> </td>
    </tr>

    <tr>
    <td> Date: </td>
    <td> <c:out value="${EVENTS.date}" /> </td>
    </tr>

    <tr>
    <td> Start Time: </td>
    <td> <c:out value="${EVENTS.time}" /> </td>
    </tr>
    
    <tr>
    <td> Duration: </td>
    <td> <c:out value="${EVENTS.duration}" /> </td>
    </tr>

	<tr>
    <td> Hall Name: </td>
    <td> <c:out value="${EVENTS.hall}" /> </td>
    </tr>
    
    <tr>
    <td> Estimated Attendees: </td>
    <td> <c:out value="${EVENTS.attendees}" /> </td>
    </tr>
    
    <tr>
    <td> Event Name: </td>
    <td> <c:out value="${EVENTS.eventName}" /> </td>
    </tr>
    
    <tr>
    <td> Food Type: </td>
    <td> <c:out value="${EVENTS.foodType}" /> </td>
    </tr>
    
    <tr>
    <td> Meal: </td>
    <td> <c:out value="${EVENTS.meal}" /> </td>
    </tr>
    
    <tr>
    <td> Meal Formality: </td>
    <td> <c:out value="${EVENTS.formality}" /> </td>
    </tr>
    
    <tr>
    <td> Drink Type: </td>
    <td> <c:out value="${EVENTS.drinkType}" /> </td>
    </tr>
    
    <tr>
    <td> Entertainment: </td>
    <td> <c:out value="${EVENTS.entertainment}" /> </td>
    </tr>
    
    <tr>
    <td> Event Status: </td>
    <td> <c:out value="${EVENTS.status}" /> </td>
    </tr>
    
    
    <tr>
    </tr>
    </table>
    <br>
    <input name="action" value="modifyUserEvent" type="hidden">
	<input name="modifyUserButton" type="submit" value="Modify Event">
	<input name="cancelUserButton" type="submit" value="Cancel Event">
	<button type = "button" name = "back" onclick =  "history.back()">Back</button>
	</form>
	<br>
	<a href="<c:url value='/UserController?action=logout'/>"><input name="logoutButton" type="submit" value="Logout"></a>
</td>
</tr>
</table>
</body>
</html>