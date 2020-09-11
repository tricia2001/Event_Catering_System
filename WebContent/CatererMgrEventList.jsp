<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Manager Event List</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<style type="text/css">
<%@include file="CSS/style.css"%>
</style>
<link href="myStyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="script.js"></script>
</head>
<body>
      <div class="logo"><h1><a href="<c:url value='/' />">Campus Event Catering System</a></h1></div>
      <div class="content">Caterer Event Summary</div>
      <div class="menu_nav">
      </div>
	    <div class="header_resize">
      <div class="menu_nav">
      </div>
  </div>

<input name="errMsg"  value="<c:out value='${errorMsgs}'/>" class="errorPane">
     <div class="mainbar"><div class="submb"></div></div>
     
 <form action="<c:url value='EventController?action=listSpecificEvent' />" method="post">
  <input type="hidden" name="date" value="<c:out value='${event.date}'/>" class="hidden">    
    <input type="hidden" name="time" value="<c:out value='${event.time}'/>" class="hidden">        
       <table border="1" class="myTable"> 
			<tr class="myTableRow"> 
				<th class="myTable20">Select Event</th>
				<th class="myTable20">Event Name</th>
				<th class="myTable35">Date</th> 
				<th class="myTable20">Start Time</th>
				<th class="myTable30">Duration</th> 
				<th class="myTable35">Hall Name</th> 
				<th class="myTable20">User Last Name</th>
				<th class="myTable30">User First Name</th> 
				<th class="myTable30">Estimated Attendees</th>  
			</tr>

 		<c:forEach items="${EVENTS}" var="item" varStatus="status">
			<tr class="myTableRow">
			<td class="myTableRadio"><input type="radio" id="radioEvent${status.count}" name="radioEvent" value="<c:out value="${status.count}" />"></td> 	
			<td class="myTable20 "><c:out value="${item.eventName}" /></td>
			<td class="myTable35 "><c:out value="${item.date}" /></td>
			<td class="myTable20 "><c:out value="${item.time}" /></td>
			<td class="myTable30 "><c:out value="${item.duration}" /></td>
			<td class="myTable35 "><c:out value="${item.hall}" /></td>
			<td class="myTable20 "><c:out value="${item.userLast}" /></td>
			<td class="myTable30 "><c:out value="${item.userFirst}" /></td>
			<td class="myTable30 "><c:out value="${item.attendees}" /></td>
			</tr>
		</c:forEach>
 </table>
 <br>
<input name="ListSelectedEventButton" type="submit" value="View selected">
 </form>
 <a href="<c:url value='/UserController?action=logout'/>"><input name="logoutButton" type="submit" value="Logout"></a>
</body>
</html>