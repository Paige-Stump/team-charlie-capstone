<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<title>UrbanAdvisor</title>
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	    <c:url var="cssHref" value="/css/site.css" />
		<link rel="stylesheet" href="${cssHref}">
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	    <script src="http://cdn.jsdelivr.net/jquery.validation/1.15.0/jquery.validate.min.js"></script>
	    <script src="http://cdn.jsdelivr.net/jquery.validation/1.15.0/additional-methods.js "></script>
	    <script src="https://cdn.jsdelivr.net/jquery.timeago/1.4.1/jquery.timeago.min.js"></script>
	    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
		
		<script type="text/javascript">
			$(document).ready(function() {
				$("time.timeago").timeago();
				
				$("#logoutLink").click(function(event){
					$("#logoutForm").submit();
				});
				
				var pathname = window.location.pathname;
				$("nav a[href='"+pathname+"']").parent().addClass("active");
			});
		</script>
		
		<script src="//use.edgefonts.net/yeseva-one;source-sans-pro.js"></script>
		
	</head>
	
	
	<body>
		<header class="navbar">
			<div class="container">
  				<nav class="sticky-top navbar-light">
					<c:url var="homePageHref" value="/" />
					<c:url var="imgSrc" value="/img/logo.png" />
					<a class="navbar-brand" href="${homePageHref}"><img src="${imgSrc}" class="d-inline-block align-middle" />
					<h1>urban</h1><h2>advisor</h2></a>
				</nav>
				<nav class="navbar">
					<div class="nav navbar-nav navbar-right" id="currentUser">
						<c:choose>
							<c:when test="${empty currentUser}">
								<c:url var="loginHref" value="/login" />
								<a href="${loginHref}"><button type="button" class="btn dropdown-toggle navbar-btn">Log In</button></a>
							</c:when>
							<c:otherwise>
								<p>Welcome,<p>
								<div class="dropdown">
								  <button class="btn dropdown-toggle navbar-btn" type="button" data-toggle="dropdown">
								  ${currentUser.firstName}
								  <span class="caret"></span></button>
									  <ul class="dropdown-menu">
										<c:url var="changePasswordHref" value="/users/${currentUser}/changePassword" />
										<li><a href="${changePasswordHref}">
										Change Password</a></li>
										
										<c:url var="dashboardHref" value="/users/userDash" />
										<li><a href="${dashboardHref}">
										View Dashboard</a></li>
										
										<c:if test= "${currentUser.admin}" >
										<c:url var="adminCreateLandmark" value="/adminCreateLandmark"/>
										<li><a href="${adminCreateLandmark}">
										Create New Landmark</a></li>
										</c:if>
										
										<li class="divider"></li>
										
										<c:url var="logoutAction" value="/logout" />
										<form id="logoutForm" action="${logoutAction}" method="POST"></form>
										<li><a id="logoutLink" href="#">
										Log Out</a></li>
									  </ul>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</nav>
			</div>
		</header>
			
		
		<div class="container">