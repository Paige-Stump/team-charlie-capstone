<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<h2>Itinerary Dashboard</h2>

This is the TEST one ${username} is working on<p>	
<button type="button" class="btn btn-primary">Create New Itinerary</button>
<p>
<p>
	<c:forEach items="${itineraries}" var="itinerary">
			<div class="feature">
				<c:url var="itineraryHref" value="/mapSelector">
				<c:param name="itineraryObject">${itinerary}</c:param></c:url>
		
				<div class="featureText">
				
					<a href = "${itineraryHref}"><h3>${itinerary.itineraryName}</h3></a>
					<p>${itinerary.startingPoint}</p>
					<a href = ""><button type="button" class="btn btn-primary">Edit Itinerary</button></a>
				<button type="button" class="btn btn-primary">Delete Itinerary</button>
				</div>
			</div>
		</c:forEach>

</p>
</p>

<c:import url="/WEB-INF/jsp/footer.jsp" />