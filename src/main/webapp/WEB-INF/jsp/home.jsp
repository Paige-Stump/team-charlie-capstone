<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<div class="jumbotron">
	<div>
		<p>UrbanAdvisor helps you map a customized city tour based on the most efficient route to experience all your city has to offer.</p>
		<c:url var="loginHref" value="/login" />
		<div class="text-center" id="registerButton">
			<a href="${loginHref}"><button type="button" class="btn-primary btn-lg"><p>Get Started!</p></button></a>
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
		