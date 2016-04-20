<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>Send Message</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/message.css"/>" />
</head>
<body>
	<nav class="myNav">
		<span>Welcome ${sessionScope.user.username}!</span> <a
			href="logout.htm">Log Out</a> <a href='change.jsp'>Change my
			password</a> <a href='viewContacts.jsp'>View My Contacts</a> <a
			href='details.htm'>View My Profile</a> <a href='loginUser.htm'>Back
			To Home Page</a>
	</nav>
	
	<form:form action="sendMessage.htm" method="post" commandName="message">
		<table align="center">
			<tr><th>From (Username): </th><td><form:input path="fromUser.username" value="${sessionScope.user.username}" class="form-control" required="required" /></td></tr>
			<tr><th>To (Username): </th><td><form:input path="toUser.username" class="form-control" required="required" /></td></tr>
			<tr><th>Subject: </th><td><form:input path="title" class="form-control" required="required" /></td></tr>
			<tr><th>Message: </th><td><form:textarea path="message" class="form-control" required="required" rows="5"></form:textarea> </td></tr>
			
		</table>
		<br />
			<input type="submit" value="Send" class="btn btn-success" id="submit" />
		
	</form:form>
</body>
</html>