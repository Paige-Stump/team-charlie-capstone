<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<h2>${username}'s Itinerary Dashboard</h2>

<p>	
<button type="button" class="btn btn-primary">Create New Itinerary</button>
<p>
	<c:forEach items="${itineraries}" var="itinerary">
		<div class="feature">
			<c:url var="itineraryHref" value="/itinerary">
				<c:param name="itineraryObject">${itinerary}</c:param>
			</c:url>
			<div class="featureText">
				<h3><a href = "${itineraryHref}">${itinerary.itineraryName}</a></h3>
				<p>${itinerary.startingPoint}</p>
				<a href = ""><button type="button" class="btn btn-primary">Edit Itinerary</button></a>
			<button type="button" class="btn btn-primary">Delete Itinerary</button>
			</div>
		</div>
	</c:forEach>
</p>

<c:import url="/WEB-INF/jsp/footer.jsp" />