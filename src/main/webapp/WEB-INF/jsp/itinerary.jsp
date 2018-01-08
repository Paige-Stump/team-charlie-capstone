<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/header.jsp" />

<h2>${currentUser.firstName}'s Itinerary Dashboard</h2>

<c:url var="mapHref" value="/mapSelector">
	<c:param name="itineraryStart">${itinerary.startingPoint}</c:param>
	<c:param name="itineraryName">${itinerary.itineraryName}</c:param>
</c:url>

<a href="${mapHref}"><button type="button" class="btn btn-primary">Generate
		Travel Route</button></a>
<c:forEach items="${itinerary.landmarks}" var="landmark">
	<form method="POST" action="">

		<div class="feature">
			<c:url var="landmarkHref" value="${landmark.landmarkLink}"></c:url>
			<c:url var="landmarkImage"
				value="/img/${landmark.landmarkLocation}.jpg"></c:url>

			<div class="featureThumb">
				<a href="${landmarkHref}" target="_blank"><img
					src="${landmarkImage}" /></a>
			</div>
			<div class="featureText">
				<p class="title">${landmark.landmarkName}</p>
				<p class="description">${landmark.landmarkDescription}</p>
				<button type="submit" class="btn btn-danger" name="landmarkId" value="${landmark.landmarkId}">Delete From My Itinerary</button>

			</div>
		</div>
	</form>
</c:forEach>

<c:import url="/WEB-INF/jsp/footer.jsp" />