<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Admin User Search</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="myStyle.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<%@include file="CSS/style.css"%>
</style>
<body>
      <div class="logo"><h1><a href="<c:url value='/' />">Campus Event Catering System</a></h1></div>
      <div class="menu_nav">
  </div>
<input name="errMsg"  value="<c:out value='${errorMsgs.errorMsg}'/>" class="errorPane">
<table>
<tr>
	<td>
	<form action="<c:url value='/UserController?action=searchUser' />" method="post">
	<table style="width: 1200px; ">
	<tr>
	<tr>
  	<td> User Last name: </td>
 	<td> <input name="last_name" value="" class="text45">  </td>
  	<td> <input name="lastError"  value="<c:out value='${errorMsgs.lastError}'/>" class="errorMsg"> </td>
	</tr>
   
</table>
<br>
  <input type="submit" value="Submit">
	</form> 
	<br>
	<a href="<c:url value='/UserController?action=logout'/>"><input name="logoutButton" type="submit" value="Logout"></a>     
</td>
</tr>
</table>
</body>
</html>