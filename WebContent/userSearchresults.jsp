<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Selected User</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="myStyle.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<%@include file="CSS/style.css"%>
</style>
<body>
    <div class="header_resize">
      <div class="logo"><h1><a href="<c:url value='/' />">Campus Event Catering System</a></h1></div>
      <div class="content">Selected User Details</div>
  </div>
         
 <form action="<c:url value='/UserController?action=editUser' />" method="post"> 
   <input type="hidden" name="user" value="<c:out value='${selectedUser.username}'/>" >
   <input type="hidden" name="id" value="<c:out value='${selectedUser.utaId}'/>" >         
       <table border="1" class="myTable"> 
			<tr class="myTableRow"> 
				<th class="myTable20">Username</th>
				<th class="myTable35">Firstname</th> 
				<th class="myTable20">Lastname</th>
				<th class="myTable30">UTA ID</th> 
				<th class="myTable30">Role</th> 
				<th class="myTable30">Password</th> 
				<th class="myTable30">Phone</th> 
				<th class="myTable30">Email</th> 
				<th class="myTable30">Street Name</th> 
				<th class="myTable30">Street Number</th> 
				<th class="myTable30">City</th> 
				<th class="myTable30">State</th> 
				<th class="myTable30">Zip code</th> 
				
			</tr>


			<tr class="myTableRow">
			<td class="myTable30 "><c:out value="${selectedUser.username}" /></td>
			<td class="myTable20 "><c:out value="${selectedUser.firstname}" /></td>
			<td class="myTable35 "><c:out value="${selectedUser.lastname}" /></td>
			<td class="myTable20 "><c:out value="${selectedUser.utaId}" /></td>
			<td class="myTable30 "><c:out value="${selectedUser.role}" /></td>
			<td class="myTable30 "><c:out value="${selectedUser.password}" /></td>
			<td class="myTable30 "><c:out value="${selectedUser.phone}" /></td>
			<td class="myTable30 "><c:out value="${selectedUser.email}" /></td>
			<td class="myTable30 "><c:out value="${selectedUser.streetName}" /></td>
			<td class="myTable30 "><c:out value="${selectedUser.streetNumber}" /></td>
			<td class="myTable30 "><c:out value="${selectedUser.city}" /></td>
			<td class="myTable30 "><c:out value="${selectedUser.state}" /></td>
			<td class="myTable30 "><c:out value="${selectedUser.zipcode}" /></td>
			</tr>

		
		
 </table>
 <input name="ModifyUserButton" type="submit"  value="Modify User">
<a href="<c:url value='/adminHomepage.jsp' />"><input type="submit" value="Home"/></a>
 
 </form>
 <a href="<c:url value='/UserController?action=logout'/>"><input name="logoutButton" type="submit" value="Logout"/></a>
</body>
</html>