<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>List of USERS-ADMIN</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<style type="text/css">
<%@include file="CSS/style.css"%>
</style>
<link href="myStyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="header_resize">
      <div class="logo"><h1><a href="<c:url value='/' />">Catering Management Application</a></h1></div>
      <div class="menu_nav">
      </div>
	<div class="content">User Search Results</div>
  </div>

<input name="errMsg"  value="<c:out value='${errorMsgs}'/>" class="errorPane">
     <div class="mainbar"><div class="submb"></div></div>
      
 <form action="<c:url value='UserController?action=listSpecificUser' />" method="post"> 
 <input type="hidden" name="lname" value="<c:out value='${lname}'/>" class="hidden">    
       <table border="1" class="myTable"> 
			<tr class="myTableRow"> 
				<th class="myTable20">Select User</th>
				<th class="myTable20">Last Name</th>
				<th class="myTable35">First Name</th> 
				<th class="myTable20">Username</th>
				<th class="myTable30">Role</th> 
			</tr>

 		<c:forEach items="${USERS}" var="item" varStatus="status">
			<tr class="myTableRow">
			<td class="myTableRadio"><input type="radio" id="radioUser${status.count}" name="radioUser" value="<c:out value="${status.count}" />"></td> 	
			<td class="myTable20 "><c:out value="${item.lastname}" /></td>
			<td class="myTable20 "><c:out value="${item.firstname}" /></td>
			<td class="myTable35 "><c:out value="${item.username}" /></td>
			
			<td class="myTable30 "><c:out value="${item.role}" /></td>
            
			</tr>
		</c:forEach>
 </table>
<input name="ListSelectedUserButton" type="submit" value="View selected">
<input name="DeleteUserButton" type="submit" value="Delete User" onclick="return deleteconfirm()">
<%-- Confirmation script for deleting user profile. --%>
<script>
function deleteconfirm() {

if(confirm("Are you sure you want to Delete?"))
	return true;	
else
	return false;
}
</script>
 </form>
<a href="<c:url value='/adminHomepage.jsp' />"><input value="Home" name="back" type="submit"></a>
 <a href="<c:url value='/UserController?action=logout'/>"><input name="logoutButton" type="submit" value="Logout"></a>
</body>
</html>