<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />
<c:url var="newUserHref" value="/users/new" />

<div class="row">
<p>UrbanAdvisor helps you map your city tour based on the most efficient route to see as many local landmarks as possible. <a href="${newUserHref}">Sign up now</a> to create your custom itinerary!</p>
</div>
<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-8">
		<h2>Featured Landmarks</h2>
		<c:forEach items="${landmarks}" var="landmark">
			<div class="featuredLandmark">
				<c:url var="landmarkHref" value="${landmark.landmarkLink}"></c:url>
				<c:url var="landmarkImage" value="/img/${landmark.landmarkLocation}.jpg"></c:url>
				
				<div class="landmarkThumb">
					<a href="${landmarkHref}" target="_blank"><img src="${landmarkImage}"/></a>
				</div>
				<div class="landmarkText">
					<p class="landmarkTitle">${landmark.landmarkName}</p>
					<p class="landmarkDescription">${landmark.landmarkDescription}</p>
				</div>
			</div>
		</c:forEach>
	</div>
	<div class="col-sm-2"></div>
</div>
	
		
<c:import url="/WEB-INF/jsp/footer.jsp" />
		