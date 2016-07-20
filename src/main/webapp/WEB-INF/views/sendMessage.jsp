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
			href="logout.htm">Log Out</a> <a href='#'>Change my password</a> <a
			href='#'>View My Contacts</a> <a href='details.htm'>View My
			Profile</a> <a href='loginUser.htm'>Back To Home Page</a>
	</nav>
	<div style="margin:0 auto; margin-top:3%; color:red;"><c:if test="${not empty sessionScope.messageError }">${sessionScope.messageError }</c:if></div>
	<form:form action="sendMessage.htm" method="post" commandName="message">
		<table align="center">
			<tr>
				<th>From (Username):</th>
				<td><form:input path="fromUser.username"
						value="${sessionScope.user.username}" class="form-control"
						required="required" />
						<form:errors path="fromUser.username" ></form:errors></td>
			</tr>
			<c:choose>
				<c:when test="${not empty requestScope.toUser }">
					<tr>
						<th>To (Username):</th>
						<td><form:input path="toUser.username"
								value="${requestScope.toUser }" class="form-control"
								required="required" />
								<form:errors path="toUser.username" ></form:errors></td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<th>To (Username):</th>
						<td><form:input path="toUser.username" class="form-control"
								required="required" />
								<form:errors path="toUser.username" ></form:errors></td>
					</tr>
				</c:otherwise>
			</c:choose>
			<tr>
				<th>Subject:</th>
				<td><form:input path="title" class="form-control"
						required="required" /></td>
			</tr>
			<tr>
				<th>Message:</th>
				<td><form:textarea path="message" class="form-control"
						required="required" rows="5"></form:textarea>
					<form:errors path="message" ></form:errors></td>
			</tr>

		</table>
		<br />
		<input type="submit" value="Send" class="btn btn-success" id="submit" />

	</form:form>
</body>
</html>