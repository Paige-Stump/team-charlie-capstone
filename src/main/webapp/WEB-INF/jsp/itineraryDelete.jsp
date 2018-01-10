<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<h2>${currentUser.firstName}'s Itinerary Dashboard</h2>
<c:url var="allLandmarks" value="/landmarks"/>
<c:if test= "${currentUser.admin}" >
<button type="button" class="btn btn-primary">Create New Landmark</button> 
</c:if>

		<div class="feature">
			<div class="featureText">
				<c:url var="itineraryHref" value="/itinerary" />
				<h3><a href = "${itineraryHref}">${itineraryName}</a></h3>
				<p>${itinerary.startingPoint}</p>
			</div>
				
			<form method="POST" action=""><label for="submit">Are you sure you want to delete ${itineraryName}?</label>
				<button type="submit" class="btn btn-danger" name="itineraryName" value="${itineraryName}">Yes</button>
			</form>
		</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />