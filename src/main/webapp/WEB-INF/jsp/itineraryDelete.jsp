<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<h2>${currentUser.firstName}'s Itinerary Dashboard</h2>
<c:url var="allLandmarks" value="/landmarks" />

<div class="feature">
	<div class="featureText">
		<c:url var="itineraryHref" value="/itinerary" />
		<h3>
			${itineraryName}
		</h3>
		<p>${itinerary.startingPoint}</p>
	</div>
	<p>Are you sure you want to delete ${itineraryName}?</p>
	<form method="POST" action="">
		<button type="submit" class="btn btn-danger" name="itineraryName"
			value="${itineraryName}">Yes, Delete my Itinerary</button>
		<c:url var="goBack" value="/users/userDash" />
		<a href="${goBack}"><button type="submit" class="btn btn-primary"
				value="${itineraryName}">No, Return to my Itinerary
				Dashboard</button></a>
	</form>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />