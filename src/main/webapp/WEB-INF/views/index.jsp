<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<!DOCTYPE html>
<html>
<head>
	<title>Final Project</title>
	
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<!-- 
	<script language="JavaScript" src="javascript/matchnet.js"></script>
	<script language="JavaScript" src="javascript/region3.js"></script>
	<script language="JavaScript" src="im/im.js"></script>
 -->
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/index.css" />" />
	<script language="JavaScript" src="<c:url value="/resources/javascript/index.js" />"></script>
	
	<script>
		$(document).ready(function(){
			
			$("#myBtn").click(function(){
				$("#myModal").modal();
			});
      $(".signUpBtn").click(function(){
        $("#myModal2").modal();
      });
      
      
		});
	</script>
</head>
<body>
	<nav>
	<a href="index.jsp" class="logo">Meet With You</a>
	  	<button type="button" class="btn btn-default btn-lg adminBtn" id="adminBtn">Staff Member</button>
		<button type="button" class="btn btn-default btn-lg signUpBtn">Sign Up</button>
	  	<button type="button" class="btn btn-default btn-lg" id="myBtn">Log In</button>
	</nav>
	
	<div class="background-wrap">
	<video id="bgvid" preload="auto" autoplay loop muted>
	<source src="<c:url value="/resources/img/video.mp4"/>" type="video/mp4" />
	Your browser does not support HTML5 video.
	</video>
</div>

	<div class="content">
		<h2>Millions of member profiles</h2>
		<h2>Thousands more joining each day</h2>
	</div>
	
	<div class="container">
  <!-- <h2>Modal Login Example</h2> -->
  <!-- Trigger the modal with a button -->
  <!-- <button type="button" class="btn btn-default btn-lg" id="myBtn">Login</button>
 -->
  <!-- Modal -->
  <div class="modal" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header" style="padding:35px 50px;">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4><span class="glyphicon glyphicon-lock"></span> Login</h4>
        </div>
        <div class="modal-body" style="padding:40px 50px;">
        <!-- log in jsp !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!  -->
          <form:form id="loginForm" method="post" action="loginUser.htm" commandName="user">
            <div class="form-group">
              <label><span class="glyphicon glyphicon-user"></span> Email Address</label>
              <form:input type="email" path="email.emailAdd" class="field form-control" placeholder="Enter EmailAddress" required="required" />
            </div>
            <div class="form-group">
              <label><span class="glyphicon glyphicon-eye-open"></span> Password</label>
              <form:input type="password" path="password" class="field form-control" placeholder="Enter password" required="required" />
            </div>
            <%-- 
            <div class="checkbox">
              <label><form:checkbox path="" checked="checked">Remember me</label>
            </div> --%>
              <input id="loginBtn" type="submit" class="btn btn-success btn-block" value=" Login" /><br />
          </form:form>
          <div id="errormessage" align="center"></div>
        </div>
        <div class="modal-footer">
          <p>Not a member? <a class="signUpBtn">Sign Up</a></p>
        </div> <!-- end of footer -->
      </div>  <!-- end of Modal content-->
    </div>	<!-- end of modal-dialog-->
  </div> 	<!-- end of Modal-->
</div>	<!-- end of container-->




<div class="container2">
  <!-- Modal -->
  <div class="modal fade" id="myModal2" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header" style="padding:35px 50px;">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4><span class="glyphicon glyphicon-lock"></span> SignUp</h4>
        </div>
        <div class="modal-body" style="padding:40px 50px;">
        <!-- sign up jsp !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!  -->
          <form:form id="signupForm" method="post" action="addUser.htm" commandName="user">
            <div class="form-group">
              <label><span class="glyphicon glyphicon-user"></span> Username</label>
              <form:input type="text" path="username" class="field form-control" placeholder="Enter Username" required="required" />
            </div>
            <!-- <div class="form-group">
              <label><span class="glyphicon glyphicon-eye-open"></span> Password</label>
              <input type="password" id="password1" class="field form-control" placeholder="Enter password (Confidential)" required="required" />
            </div> -->
            <div class="form-group">
              <label><span class="glyphicon glyphicon-eye-open"></span> Password</label>
              <form:password id="password" path="password" class="field form-control" placeholder="Enter password" required="required" />
            </div>

            <div class="form-inline">
             <label><span class="glyphicon glyphicon-calendar"></span> Date of Birth:</label><br />
              <form:input type="date" path="dateOfBirth" class="form-control" required="required" />
            </div> <!-- inline -->
            <br />
            <div class="form-group">
              <label><span class="glyphicon glyphicon-envelope"></span> Email Address</label>
              <form:input type="email" path="email.emailAdd" class="field form-control" placeholder="Enter Email (Confidential)" required="required" />
              <form:errors path="email.emailAdd"></form:errors>
            </div>
            <div class="form-group">
              <label><span class="glyphicon glyphicon-info-sign"></span> You are a</label>
              <form:radiobutton path="gender" value="Male" checked="checked"/> Male
              <form:radiobutton path="gender" value="Female"/> Female
            </div>
            
            <div class="form-group">
              <label><span class="glyphicon glyphicon-globe"></span> Country</label><br />
          <select name="country"  class="form-control" required="required">
              <option value="China"               >China</option>
              <option value="India"               >India</option>
              <option value="USA"  selected       >USA</option>
          </select>
            </div>

            <div class="form-group">
              <label><span class="glyphicon glyphicon-globe"></span> State</label>
              <form:input path="state" class="field form-control" placeholder="Enter State" required="required" />
            </div>
            <div class="form-group">
              <label><span class="glyphicon glyphicon-globe"></span> City</label>
              <form:input path="city" class="field form-control" placeholder="Enter City" required="required" />
            </div>

              <input id="signupBtn" type="submit" class="btn btn-success btn-block" value="Sign Up Now!" />
          </form:form>
          <div id="errormessage1" align="center"></div>
        </div> <!-- model-body -->
        <div class="modal-footer">
          <p>I confirm that I have read and agreed to the <a href="#" onClick="javascript:launchWindow('termsandconditions.html','TermsAndCond',700,550,'');" class="default">Terms and Conditions of Service</a> of membership at CSE220ServiceByYusufOzbek.com</p>
        </div>
      </div> <!-- modal-content -->
      
    </div> <!-- modal-dialog -->
  </div> <!-- myModal2 -->
</div> <!-- container2 -->

</body>
</html>
