<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register User</title>
</head>
<link href="myStyle.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<%@include file="CSS/style.css"%>
</style>
    <div class="header_resize">
      <div class="logo"><h1><a href="<c:url value='/' />">Campus Event Catering System</a></h1></div>
	  <div class="content">Register User</div>
      <div class="menu_nav">
      </div>
  </div>

<body>
<input name="errMsg" value="<c:out value='${errorMsgs.errorMsg}'/>" class="errorPane">
<table>
  <tr>
   <td>
   <form name="register" action="<c:url value='/UserController?action=register' />" method="post">
         <table border="1" class="myTable"> 
    
    <tr>
    <td> Username: </td>
    <td> <input name = "username" value = "<c:out value="${user.username}"/>" class="text45"/> </td>
    <td> <input name="usernameError" value="<c:out value='${errorMsgs.usernameError}'/>" class="errorMsg"> </td>
    </tr>
    
    <tr>
    <td> Password: </td>
    <td> <input name = "password" value = "<c:out value="${user.password}"/>" class="text45"/> </td>
    <td> <input name="passwordError" value="<c:out value='${errorMsgs.passwordError}'/>" class="errorMsg"> </td>
    </tr>
    
    <tr>
    <td> Last Name: </td>
    <td> <input name = "userLast" value = "<c:out value="${user.lastname}"/>" class="text45"/> </td>
    <td> <input name="userLastError" value="<c:out value='${errorMsgs.lastError}'/>" class="errorMsg"> </td>
    </tr>

    <tr>
    <td> First Name: </td>
    <td> <input name = "userFirst" value = "<c:out value="${user.firstname}"/>" class="text45"/> </td>
     <td> <input name="userFirstError" value="<c:out value='${errorMsgs.firstError}'/>" class="errorMsg"> </td>
    </tr>

    <tr>
    <td> Role: </td>
    <td><select name="role">
    <option value="Admin"
       >Admin</option>     
    <option value="Caterer Manager"
       >Caterer Manager</option>     
    <option value="User"
        >User</option>     
    <option value="Caterer Staff"
        >Caterer Staff</option>     
    </select> </td> 
     <td> <input name="roleError" value="<c:out value='${errorMsgs.roleError}'/>" class="errorMsg"> </td>
    </tr>

    <tr>
    <td> UTA ID: </td>
    <td> <input name = "utaId" value = "<c:out value="${user.utaId}" />" class="text45" /> </td>
     <td> <input name="utaIdError" value="<c:out value='${errorMsgs.utaIdError}'/>" class="errorMsg"> </td>
    </tr>
    
    <tr>
    <td> Phone: </td>
    <td> <input name = "phone" value = "<c:out value="${user.phone}" />" class="text45"/> </td>
     <td> <input name="phoneError" value="<c:out value='${errorMsgs.phoneError}'/>" class="errorMsg"> </td>
    </tr>
    
    <tr>
    <td> Email: </td>
    <td> <input name = "email" value = "<c:out value="${user.email}" />" class="text45" /></td>
     <td> <input name="emailError" value="<c:out value='${errorMsgs.emailError}'/>" class="errorMsg"> </td>
    </tr>
    
    <tr>
    <td> Street Number: </td>
    <td> <input name = "streetNumber" value = "<c:out value="${user.streetNumber}" />" class="text45" /></td>
     <td> <input name="streetNoError" value="<c:out value='${errorMsgs.streetNoError}'/>" class="errorMsg"> </td>
    </tr>
    
    <tr>
    <td> Street Name: </td>
    <td> <input name = "streetName" value = "<c:out value="${user.streetName}" />" class="text45" /></td>
     <td> <input name="streetNameError" value="<c:out value='${errorMsgs.streetNameError}'/>" class="errorMsg"> </td>
    </tr>
    
    <tr>
    <td> City: </td>
    <td> <input name = "city" value = "<c:out value="${user.city}" />" class="text45" /></td>
     <td> <input name="cityError" value="<c:out value='${errorMsgs.cityError}'/>" class="errorMsg"> </td>
    </tr>
    
    <tr>
    <td> State: </td>
    <td> <input name = "state" value = "<c:out value="${user.state}" />" class="text45" /></td>
     <td> <input name="stateError" value="<c:out value='${errorMsgs.stateError}'/>" class="errorMsg"> </td>
    </tr>
    
    <tr>
    <td> Zipcode: </td>
    <td> <input name = "zipcode" value = "<c:out value="${user.zipcode}" />" class="text45" /></td>
     <td> <input name="zipError" value="<c:out value='${errorMsgs.zipError}'/>" class="errorMsg"> </td>
    </tr>
  
    
    
    <tr>
    </tr>
    </table>
    <input name="action" value="register" type="hidden">
	<input name="submit" type="submit" value="Register">
	</form>
</td>
</tr>
</table>
</body>
</html>