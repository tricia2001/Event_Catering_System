<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Campus Event Catering System</title>
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

      <div class="menu_nav">
      <div class="content">Main</div>
        
         <a href="<c:url value='/login.jsp'/>"> <input name="loginButton" type="submit" value="Login"></a>
         <a href="<c:url value='/register.jsp'/>"> <input name="registerButton" type="submit" value="Register"></a>
        
      </div>
    </div>
  </div>
  </div>
  </div>  
</body>
</html>