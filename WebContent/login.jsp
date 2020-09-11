<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Search Caterer Event Summary</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<style type="text/css">
<%@include file="CSS/style.css"%>
</style>
<link href="myStyle.css" rel="stylesheet" type="text/css" />
<body>
      <div class="logo"><h1><a href="<c:url value='/' />">Campus Event Catering System</a></h1></div>    
	  <div class="content">Login</div>
      <div class="menu_nav">
  </div>
<input name="errMsgs"  value="<c:out value='${errorMsgs.errorMsg}'/>" class="errorPane">
<input name="none"  value="<c:out value='${none}'/>" class="errorPane">
<table>
<tr>
	<td>
	<form action="<c:url value='/UserController?action=login' />" method="post">
	<table style="width: 1200px; ">
					<tr>
  						<td> Username: </td>
 						<td> <input name="username" value="<c:out value='${user.username}'/>" class="text45">  </td>
  						<td> <input name="usernameError"  value="<c:out value='${errorMsgs.usernameError}'/>" class="errorMsg"> </td>
					</tr>
    				<tr>
    					<td> Password: </td>
    					<td> <input type="password" name="password" value="<c:out value='${user.password}'/>" > </td>
  						<td> <input name="passwordError"  value="<c:out value='${errorMsgs.passwordError}'/>" class="errorMsg"> </td>
    				</tr>
			</table>
  <input type="submit" value="Submit">
	</form>      
</td>
</tr>
</table>
</body>
</html>