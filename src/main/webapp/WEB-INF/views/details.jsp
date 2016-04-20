<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
	<title>My Profile</title>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/detals.css"/>" />
</head>
<body>
	<nav class="myNav">
		  <span>Welcome ${sessionScope.user.username}!</span>
		  <a href="logout.htm">Log Out</a>
		  <a href='change.jsp'>Change my password</a>
		  <a href='viewContacts.jsp'>View My Contacts</a>
		  <a href='updateProfile.htm'>Update My Information</a>
		  <a href='loginUser.htm'>Back To Home Page</a>
	</nav>
	<div id="clear"></div>
	<div class="slide-wrapper">
		<div id="homepage-feature" class="carousel slide" data-ride="carousel">
			<ol class="carousel-indicators">
	        	<li data-target="#homepage-feature" data-slide-to="0" class="active"></li>
	        	<li data-target="#homepage-feature" data-slide-to="1"></li>
	        	<li data-target="#homepage-feature" data-slide-to="2"></li>
	     	</ol>
	     	
	     	<!-- Wrapper for slides -->
      		<div class="carousel-inner"  role="listbox">
      			<div class="item active">
		            <div class="carousel-caption" id="firstSlide">
		            	<h2>Introduce</h2>
		            	<br />
						<p>My name is ${requestScope.profile.username }, ${requestScope.profile.gender }</p>
			               <p>My Birthday is ${requestScope.profile.dateOfBirth }</p>
			               <p>I'm from ${requestScope.profile.city }, ${requestScope.profile.state }, ${requestScope.profile.country }</p>
			               <br />
			               <p>About Me:</p>
			               <p>${requestScope.profile.aboutMe }</p>
			               <br />
			               <p>Personality:</p>
			               <p>My personality traits:${requestScope.profile.personality }</p>
			               <p>About the one I'm looking for:${requestScope.profile.aboutLookingFor }</p>
		            </div>
		         </div>
		         
		        <div class="item">
	            <div class="carousel-caption" id="secondSlide">
	            	<h2>Basics</h2>
	            	<br />
	            	<p>Height: ${requestScope.profile.height }</p>
	            	<p>Weight: ${requestScope.profile.weight }</p>
	            	<p>BodyStyle: ${requestScope.profile.bodyStyle }</p>
	            	<p>HairColor: ${requestScope.profile.hairColor }</p>
	            	<p>EyeColor: ${requestScope.profile.eyeColor }</p>
	            	<p>Religion: ${requestScope.profile.religion }</p>
	            	<p>Hometown: ${requestScope.profile.hometown }</p>
	            	<p>EducationLevel: ${requestScope.profile.educationLevel }</p>
	            	<p>Occupation: ${requestScope.profile.occupation }</p>
	            </div>
	            </div>
      			
      			<div class="item">
	            <div class="carousel-caption" id="thirdSlide">
	            	<h2>Interests</h2>
	            	<br />
	            	<p>My favorite activities:${requestScope.profile.favoriteActivity }</p>
	            	<p>My favorite foods:${requestScope.profile.favoriteFood }</p>
	            	<p>Drinking: ${requestScope.profile.drinking }</p>
	            	<p>Smoking: ${requestScope.profile.smoking }</p>
	            </div>
	            </div>
      		
      		</div>
      		<!-- /.carousel-inner -->
		      <!-- Controls -->
		      <a class="left carousel-control" href="#homepage-feature" data-slide="prev">
		        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
		        <span class="sr-only">Previous</span>
		      </a>
		      <a class="right carousel-control" href="#homepage-feature" data-slide="next">
		        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
		    <span class="sr-only">Next</span>
		      </a>
		</div>
	</div>
</body>
</html>
