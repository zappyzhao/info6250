<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta HTTP-EQUIV="Content-Type" CONTENT="text/html; CHARSET=windows-1252">
	<title>Home page</title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/home.css" />" />

	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="<c:url value="/resources/javascript/homescroll.js"/>"></script>
</head>
<body>
	
<nav class="myNav">
	<div class="dropdown">
		<button class="dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span>Welcome ${sessionScope.user.username}!</span><span class="caret"></span></button>
		<ul class="dropdown-menu">
	    <li><a href='details.htm'>View My Profile</a></li> 
	    <li><a href='updateProfile.htm'>Update My Information</a></li>
	    <li><a href='viewContacts.jsp'>View My Contacts</a></li>
	    <li><a href='change.jsp'>Change my password</a></li>
	  	</ul>
	</div>
	<div class="hoverpart">
		<a href="logout.htm">Log Out</a>
		<a id="search" href="#searchSec">Quick Search</a>
		<a id="lookuser" href="#lookuserSec">Look Up Username</a>
		<a id="looknum" href="#looknumSec">Look Up Member Number</a>	
		<a id="messageBox" href="#messageBoxSec">Message Box</a>
	  </div>
</nav>

	<div class="sect sectOne"><div class="beginToContact">Begin to contact &#8681</div></div>

	<div id="messageBoxSec" class="subSection">
		<h2>Message Box</h2><br />
		<button class="form-control" onclick="newMessageWindow()">New Message</button>
	</div>


	<div class="sect sectTwo"><div class="lookupByNum">Look Up By Member Number &#8681</div></div>
	<div id="looknumSec" class="subSection">
		<div class="info">Please enter the member number of the person you want to look up</div>
		<form class="form-inline" name='lookupByNumberForm' method='post' action='lookupByNumber.jsp'>
		<div class="seeProfile">
		<input class="form-control input-lg" type='text' name='LookupMemberID' placeholder="Enter a Number">
		<input type='submit' name='cmdGo1' value='See Profile' class="btn btn-info btn-lg">
		</div>
		</form>
	</div>


	<div class="sect sectThree"><div class="lookupByName">Look Up By User Name &#8681</div></div>
	<div id="lookuserSec" class="subSection">
		<div class="info">Please enter the username of the person you want to look up</div>
		<form class="form-inline" name='lookupByNameForm' method='post' action='lookupByName.jsp'>
		<div class="seeProfile">
		<input class="form-control input-lg" type='text' name='LookupMemberName' placeholder="Enter a Name" ng-model="test">
		<input type='submit' name='cmdGo2' value='See Profile' class="btn btn-info btn-lg">
		</div>
		</form>
	</div>


	<div class="sect sectFour"><div class="quickSearch">Quick Search &#8681</div></div>
	<div id="searchSec" class="subSection">
	<form name='searchUserForm' method='post' action='searchUsers.jsp'>
	
	<div class="form-inline"><b/>
		<lable>Show me: </lable> 
		<select name='SeekingGenderID' value='' class="form-control" >
		<option value='Male'>Male</option>
		<option value='Female' selected>Female</option>
		</select>
	</div><br />
	
	<div><span>Search Location: </span></div><br />
	<div class="form-inline">
		<span>Country: </span>
		<select name="CountryRegionID" class="form-control">
	              <option value="China"               >China</option>
	              <option value="India"               >India</option>
	              <option value="USA"  selected       >USA</option>
	            </select>
	</div><br />

	<div class="form-inline">
		<span>State: </span>
		<input class="form-control" type='text' name='stateName' value='' size='18' maxlength='40'>
	&nbsp&nbsp&nbsp&nbsp&nbsp
		<span>City: </span>
		<input class="form-control" type='text' name='cityName' value='' size='18' maxlength='40'>
	</div><br />
	
	<div>
	<input type='submit' name='cmdGo3' value='Search' class="btn btn-info btn-lg">
	</div>
	
	
	</form>
	</div>
	<div class="sect sectFive"><div class="enjoy">Enjoy your Day! &#8679</div></div>

<script type="text/javascript">
	function newMessageWindow() {
		window.location.href="newMessage.htm";
	}
</script>

</body>
</html>