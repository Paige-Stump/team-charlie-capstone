<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<h2>Itinerary Dashboard</h2>
<p>My Itineraries</p>

This is the TEST one ${username} is working on

<p>
<p>
	<c:forEach items="${itineraries}" var="itinerary">
			<div class="feature">
				<c:url var="itineraryHref" value=""></c:url>
		
				<div class="featureText">
					<p>${itinerary.itineraryName}</p>
					<p>${itinerary.startingPoint}</p>
				</div>
			</div>
		</c:forEach>

</p>
</p>

<c:import url="/WEB-INF/jsp/footer.jsp" />