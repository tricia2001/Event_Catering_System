<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Admin Homepage</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<style type="text/css">
<%@include file="CSS/style.css"%>
</style>
<link href="myStyle.css" rel="stylesheet" type="text/css" />
<body>
      <div class="logo"><h1><a href="<c:url value='/' />">Campus Event Catering System</a></h1></div>
      <div class="content">
 <div class="content">Admin Homepage</div>
      <div class="menu_nav">
	<form action="<c:url value='/UserController?action=adminSearch' />" method="post">
	  <input name="user" value="<c:out value='${user.username}'/>" type="hidden">
       <br>
       <input type="submit" value="View My User Profile" formaction="<c:url value='/UserController?action=viewUser' />">
        <input type="submit" value="Search User">
        </form>
	  <br><br>
	<a href="<c:url value='/UserController?action=logout'/>"><input type="submit" value="Logout"></a>
       
      </div>
    </div> 
</body>
</html>