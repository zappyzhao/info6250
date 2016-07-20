<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>My Profile</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/detals.css"/>" />
</head>
<body>
	<nav class="myNav">
		<span>Welcome ${sessionScope.user.username}!</span> <a
			href="logout.htm">Log Out</a> <a href='#'>Change my
			password</a> <a href='#'>View My Contacts</a> <a
			href='details.htm'>View My Profile</a> <a href='loginUser.htm'>Back
			To Home Page</a>
	</nav>

	<form:form method="post" action="updateProfile.htm" commandName="user">
		<table align="center" id="inUpdate">
			<tr>
				<th>1. About Me:</th>
				<td><form:input path="aboutMe" class="form-control" value="${sessionScope.user.aboutMe }" required="required" /></td>
			</tr>
			<tr>
				<th>2. Personality:</th>
				<td><form:input path="personality" class="form-control" value="${sessionScope.user.personality }" required="required" /></td>
			</tr>
			<tr>
				<th>3. About Looking For:</th>
				<td><form:input path="aboutLookingFor" class="form-control" value="${sessionScope.user.aboutLookingFor }" required="required" /></td>
			</tr>
			<tr>
				<th>4. Height:</th>
				<td><form:input path="height" class="form-control" value="${sessionScope.user.height }" required="required" /></td>
			</tr>
			<tr>
				<th>5. Weight:</th>
				<td><form:input path="weight" class="form-control" value="${sessionScope.user.weight }" required="required" /></td>
			</tr>
			<tr>
				<th>6. Body Style:</th>
				<td><form:input path="bodyStyle" class="form-control" value="${sessionScope.user.bodyStyle }" required="required" /></td>
			</tr>
			<tr>
				<th>7. Hair Color:</th>
				<td><form:input path="hairColor" class="form-control" value="${sessionScope.user.hairColor }" required="required" /></td>
			</tr>
			<tr>
				<th>8. Eye Color:</th>
				<td><form:input path="eyeColor" class="form-control" value="${sessionScope.user.eyeColor }" required="required" /></td>
			</tr>
			<tr>
				<th>9. Religion:</th>
				<td><form:input path="religion" class="form-control" value="${sessionScope.user.religion }" required="required" /></td>
			</tr>
			<tr>
				<th>10. Hometown:</th>
				<td><form:input path="hometown" class="form-control" value="${sessionScope.user.hometown }" required="required" /></td>
			</tr>
			<tr>
				<th>11. Education Level:</th>
				<td><form:input path="educationLevel" class="form-control" value="${sessionScope.user.educationLevel }" required="required" /></td>
			</tr>
			<tr>
				<th>12. Occupation:</th>
				<td><form:input path="occupation" class="form-control" value="${sessionScope.user.occupation }" required="required" /></td>
			</tr>
			<tr>
				<th>13. Favorite Activity:</th>
				<td><form:input path="favoriteActivity" class="form-control" value="${sessionScope.user.favoriteActivity }" required="required" /></td>
			</tr>
			<tr>
				<th>14. Favorite Food:</th>
				<td><form:input path="favoriteFood" class="form-control" value="${sessionScope.user.favoriteFood }" required="required" /></td>
			</tr>
			<tr>
				<th>15. Drinking:</th>
				<td><form:input path="drinking" class="form-control" value="${sessionScope.user.drinking }" required="required" /></td>
			</tr>
			<tr>
				<th>16. Smoking:</th>
				<td><form:input path="smoking" class="form-control" value="${sessionScope.user.smoking }" required="required" /></td>
			</tr>
			<tr><td colspan="2"><input type="submit" value="Update" class="form-control" /></td></tr>
		</table>
		
	</form:form>
</body>
</html>