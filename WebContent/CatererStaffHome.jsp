<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Caterer Staff Home</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<style type="text/css">
<%@include file="CSS/style.css"%>
</style>
</head>
<body>
<div class="main">
  <div class="header">
    <div class="header_resize">

      <div class="logo"><h1><a href="<c:url value='/' />">Campus Event Catering System</a></h1></div>
  <div class="content">  
	  <div class="content">Caterer Staff Homepage</div>
	<form action="<c:url value='/EventController?action=catererSearch' />" method="post">
	  <input name="user" value="<c:out value='${user.username}'/>" type="hidden">
			
	<table style="width: 1200px; ">
					
			</table>
			<br>
			<input type="submit" value="View My User Profile" formaction="<c:url value='/UserController?action=viewUser' />">
  <input type="submit" value="View My Assigned Events">
	</form>    
	
	<a href="<c:url value='/UserController?action=logout'/>"><input type="submit" value="Logout"></a>
	
  </div>
  </div>
  </div>  
</body>
</html>