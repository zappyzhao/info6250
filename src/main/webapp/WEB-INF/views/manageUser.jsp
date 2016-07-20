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
		<span>Welcome ${sessionScope.admin.username}!</span> <a
			href="staffLogout.htm">Log Out</a> <a href="loginAdmin.htm">Back
			to my Profile</a>
	</nav>
<div id="errorMessage"><c:if test="not empty ${requestScope.errorDelete }">${requestScope.errorDelete }</c:if></div>



	<div class="container" id="profile">
		<table class="table">
			<tr>
				<th>UserId</th>
				<th>Username</th>
				<th>Email</th>
				<th>Date of Birth</th>
				<th>Gender</th>
				<th>Country</th>
				<th>State</th>
				<th>City</th>
				<th>Delete</th>
			</tr>

			<c:forEach var="u" begin="0" items="${sessionScope.userList}">
				<tr>
					<td>${u.personID}</td>
					<td>${u.username}</td>
					<td><a href="mailto:${u.email.emailAdd}">${u.email.emailAdd}</a></td>
					<td>${u.dateOfBirth}</td>
					<td>${u.gender}</td>
					<td>${u.country}</td>
					<td>${u.state}</td>
					<td>${u.city}</td>
					<td>
						<form action="deleteUser.htm" method="post">
							<input type="submit" value="Delete" class="deleteBtn btn btn-danger" />
							<input type="hidden" name="userName" value="${u.username}" />
						</form>
					</td>
				</tr>
			</c:forEach>
			
		</table>
		
		<br /><br />
		<div align="center">
			<c:forEach var="j" begin="1" end="${sessionScope.pageNumber}">
				<form action="getPage.htm" method="post" style="float: left">
					<input type="submit" value="${j}" />
					<input type="hidden" name="page" value="${j}" />
				</form>
	        </c:forEach>
        </div>
	</div>
	<script type="text/javascript">
	$(".deleteBtn").on("click", function(event) {
		event.preventDefault();
		var x = confirm("Are you sure you want to delete?");
		if (x) {
			$(this).parent().submit();
		}
	});
	</script>
</body>
</html>