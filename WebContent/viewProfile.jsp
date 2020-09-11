 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
<%@include file="CSS/style.css"%>
</style>
<title>View My Profile</title>
</head>
<div class="header_resize">
      <div class="logo"><h1><a href="<c:url value='/' />">Campus Event Catering System</a></h1></div>
	  <div class="content">View My User Profile</div>
	  <br>
<body>
<table>
<tr>
<td>
<form name="greetingForm" action="<c:url value='/UserController?action=updateUser0' />" method="post" style="width: 300px; ">
         <input name="user1" value="${users.username}" type="hidden">
<table border="1" class="myTable"> 
			<tr class="myTableRow"> 
				<th class="myTable20">Username</th>
				<th class="myTable30">Password</th> 
				<th class="myTable20">Last Name</th>
				<th class="myTable35">First Name</th> 
				<th class="myTable30">Role</th> 
				<th class="myTable30">UTA ID</th> 
				<th class="myTable30">Phone</th> 
				<th class="myTable30">Email</th> 
				<th class="myTable30">Street Number</th> 
				<th class="myTable30">Street Name</th> 
				<th class="myTable30">City</th> 
				<th class="myTable30">State</th> 
				<th class="myTable30">Zip code</th> 
				
			</tr>


			<tr class="myTableRow">
			<td class="myTable30 "><c:out value="${users.username}" /></td>
			<td class="myTable30 "><c:out value="${users.password}" /></td>
			<td class="myTable35 "><c:out value="${users.lastname}" /></td>
			<td class="myTable20 "><c:out value="${users.firstname}" /></td>
			<td class="myTable30 "><c:out value="${users.role}" /></td>
			<td class="myTable20 "><c:out value="${users.utaId}" /></td>
			<td class="myTable30 "><c:out value="${users.phone}" /></td>
			<td class="myTable30 "><c:out value="${users.email}" /></td>
			<td class="myTable30 "><c:out value="${users.streetNumber}" /></td>
			<td class="myTable30 "><c:out value="${users.streetName}" /></td>
			<td class="myTable30 "><c:out value="${users.city}" /></td>
			<td class="myTable30 "><c:out value="${users.state}" /></td>
			<td class="myTable30 "><c:out value="${users.zipcode}" /></td>
			</tr>

		
		
 </table>
 <br>
 

 <input type="submit" value="Back" formaction="<c:url value='/UserController?action=backHome' />">
<input type="submit" value="Update Profile">
</form>
<a href="<c:url value='/UserController?action=logout'/>"> <input name="logoutButton" type="submit" value="Logout"></a>
</table>
</body>
</html>