<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<c:url var="imgSrc" value="/img/logo.png" />
<div class="jumbotron">
	<c:choose>
		<c:when test="${empty currentUser}">
			<p>UrbanAdvisor helps you map a customized city tour based on the most efficient route to experience all your city has to offer.</p>
			<c:url var="loginHref" value="/login" />
			<div class="text-center" id="jumboButton">
				<a href="${loginHref}"><button type="button" class="btn-primary btn-lg"><p>Get Started!</p></button></a>
			</div>
		</c:when>
		<c:otherwise>
			<p>Welcome back, ${currentUser.firstName}! UrbanAdvisor helps you map a customized city tour based on the most efficient route to experience all your city has to offer.</p>
			<c:url var="itinerariesHref" value="/users/userDash" /> 
			<div class="text-center" id="jumboButton">
				<a href="${itinerariesHref}"><button type="button" class="btn-primary btn-lg"><p>View My Itineraries</p></button></a>
			</div>
		</c:otherwise>
	</c:choose>
		
</div>
<div id="carousel" class="carousel slide carousel-fade" data-ride="carousel">
	<ol class="carousel-indicators">
	    <li data-target="#carousel" data-slide-to="0" class="active"></li>
	    <li data-target="#carousel" data-slide-to="1"></li>
	    <li data-target="#carousel" data-slide-to="2"></li>
	</ol>
	<!-- Carousel items -->
	<div class="carousel-inner carousel-zoom">
	    <div class="active item"><img class="img-responsive" src="https://images.unsplash.com/photo-1419064642531-e575728395f2?crop=entropy&fit=crop&fm=jpg&h=400&ixjsv=2.1.0&ixlib=rb-0.3.5&q=80&w=1200">
	      <div class="carousel-caption">
	        <h2>Title</h2>
	        <p>Description</p>
	      </div>
	    </div>
	    <div class="item"><img class="img-responsive" src="https://images.unsplash.com/photo-1445280471656-618bf9abcfe0?crop=entropy&fit=crop&fm=jpg&h=400&ixjsv=2.1.0&ixlib=rb-0.3.5&q=80&w=1200">
	      <div class="carousel-caption">
	        <h2>Title</h2>
	        <p>Description</p>
	      </div>
	    </div>
	    <div class="item"><img class="img-responsive" src="https://images.unsplash.com/photo-1445462657202-a0893228a1e1?crop=entropy&fit=crop&fm=jpg&h=400&ixjsv=2.1.0&ixlib=rb-0.3.5&q=80&w=1200">
	      <div class="carousel-caption">
	        <h2>Title</h2>
	        <p>Description</p>
	      </div>
	    </div>
	</div>
</div>

<div class="row">
	<div class="col-sm-1 features"></div>
	<div class="col-sm-10 features">
		<h3>Featured Landmarks</h3>
		<c:forEach items="${landmarks}" var="landmark">
			<div class="feature">
				<c:url var="landmarkHref" value="${landmark.landmarkLink}"></c:url>
				<c:url var="landmarkImage" value="/img/${landmark.landmarkLocation}.jpg"></c:url>
				
				<div class="featureThumb">
					<a href="${landmarkHref}" target="_blank"><img src="${landmarkImage}"/></a>
				</div>
				<div class="featureText">
					<p class="title">${landmark.landmarkName}</p>
					<p class="description">${landmark.landmarkDescription}</p>
				</div>
			</div>
		</c:forEach>
	</div>
	<div class="col-sm-1 features"></div>
</div>
	
		
<c:import url="/WEB-INF/jsp/footer.jsp" />
		