<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
<%@include file="CSS/style.css"%>
</style>
<link href="myStyle.css" rel="stylesheet" type="text/css" />
<title>Reserve Event</title>
</head>
    <div class="header_resize">
      <div class="logo"><h1><a href="<c:url value='/' />">Campus Event Catering System</a></h1></div>
	  <div class="content">Reserve User Event </div>
	 </div>

<body style="width: 900px; ">
<input name="errMsgs"  value="<c:out value='${errorMsgs.errorMsg}'/>" class="errorPane">
<table>
<tr>
<td>
<form action="<c:url value='/EventController?action=reserveUsrEvent' />" method="post">
<input type="hidden" name="id" value="<c:out value='${EVENTS.eventId}'/>">
<input type="hidden" name="cost" value="<c:out value='${EVENTS.estimatedCost}'/>">
<table>
	
	<tr>
	<td>Estimated Cost: </td>
	<td><c:out value='${EVENTS.estimatedCost}'/> </td>
	</tr>
	<tr>
	<td>Enter Credit Card Number: </td>
	<td><input name="cardno" value="<c:out value='${cardno}'/>"> </td>
	<td> <input name="ccnoError"  value="<c:out value='${errorMsgs.cardNumberError}'/>" class="errorMsg"> </td>
	</tr>
	<tr><td>Enter Card Exp Date: </td>
	<td><input name="cardexp" value="<c:out value='${cardexp}'/>"> </td>
	<td> <input name="ccexpError"  value="<c:out value='${errorMsgs.cardExpError}'/>" class="errorMsg"> </td>
	</tr>
	<tr><td>Enter Card PIN: </td>
	<td><input name="cardpin" value="<c:out value='${cardpin}'/>"> </td>
	<td> <input name="cardPinError"  value="<c:out value='${errorMsgs.cardPinError}'/>" class="errorMsg"> </td>
	</tr>
	
	<tr>
	
	
	<tr>
	<td><input type="submit" value="Reserve" onclick="return reserveconfirm()"></td>
	</tr>
	
</table>
</form>
<%-- Confirmation script for reserve user event. --%>
<script>
function reserveconfirm() {

if(confirm("Are you sure you want to Reserve?"))
	return true;	
else
    history.go(-1);
	return false;
}
</script>
<a href="<c:url value='/UserController?action=logout'/>"><input name="logoutButton" type="submit" value="Logout"></a>
</table>
</body>
</html>