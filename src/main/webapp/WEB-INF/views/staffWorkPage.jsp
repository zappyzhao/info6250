<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Staff Work Page</title>

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/staffWorkPage.css" />" />
<script language="JavaScript"
	src="<c:url value="/resources/javascript/staffWorkPage.js" />"></script>

</head>
<body>
	<nav>
		<span>Welcome ${sessionScope.admin.username}!</span>
		<a href="staffLogout.htm">Log Out</a>
		<a href="manageUser.htm">Manage User Accounts</a>
	</nav>
	
	
		
		<div class="container">
		<div id="profile">
		<span>My Profile</span>
			<div class="row">
				<div class="col-sm-11">
					<table class="table" align="center">
						<tr>
							<th>Username:</th>
							<td>${sessionScope.admin.username }</td>
						</tr>
						<tr>
							<th>Email:</th>
							<td>${sessionScope.admin.email.emailAdd }</td>
						</tr>
						<tr><th>Department:</th><td>${sessionScope.admin.department }</td></tr>
						<tr><th>ID:</th><td>${sessionScope.admin.personID }</td></tr>
						<tr>
							<th>First Name:</th>
							<td>${sessionScope.admin.firstName }</td>
						</tr>
						<tr>
							<th>Last Name:</th>
							<td>${sessionScope.admin.lastName }</td>
						</tr>
						<tr><th>Date of Birth:</th><td>${sessionScope.admin.dateOfBirth }</td></tr>
						<tr><th>Country:</th><td>${sessionScope.admin.country }</td></tr>
						<tr><th>State:</th><td>${sessionScope.admin.state }</td></tr>
						<tr><th>City:</th><td>${sessionScope.admin.city }</td></tr>
						
					</table>
				</div>
				<div class="col-sm-1 photo-row">
					<img id="photo" alt="Profile Picture"
						src="<c:url value="/resources/img/${sessionScope.admin.photoName }"/>">
				</div>
				
			</div>
		</div>
	</div>


</body>
</html>