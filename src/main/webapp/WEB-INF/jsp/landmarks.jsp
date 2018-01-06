<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<h2>Landmarks</h2>

This is the TEST one ${username} is working on<p>	
<button type="button" class="btn btn-primary">Create New Itinerary</button>
<p>
<p>
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
					<button type="button" class="btn btn-primary">Add to My Itinerary</button>
				</div>
			</div>
		</c:forEach>

</p>
</p>

<c:import url="/WEB-INF/jsp/footer.jsp" />