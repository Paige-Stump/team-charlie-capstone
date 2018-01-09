<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<h2>${currentUser.firstName}'s Itinerary Dashboard</h2>
<c:url var="allLandmarks" value="/landmarks"/>
<c:if test= "${currentUser.admin}" >
<button type="button" class="btn btn-primary">Create New Landmark</button> 
</c:if>

<a href="${allLandmarks}"><button type="button" class="btn btn-primary">Create New Itinerary</button></a>

	<c:forEach items="${itineraries}" var="itinerary">
		<div class="feature">
			<c:url var="itineraryHref" value="/itinerary">
				<c:param name="itineraryStart">${itinerary.startingPoint}</c:param>
				<c:param name = "itineraryName">${itinerary.itineraryName}</c:param>
			</c:url>
			<div class="featureText">
				<h3><a href = "${itineraryHref}">${itinerary.itineraryName}</a></h3>
				<p>${itinerary.startingPoint}</p>
			
			<c:url var="itineraryDeleteHref" value="/itineraryDelete">
				<c:param name = "itineraryDeleteButton">${itinerary.itineraryName}</c:param></c:url>
				<a href="${itineraryDeleteHref}"><button type="submit" class="btn btn-danger" name="itineraryId" value="${itinerary.itineraryName}">Delete Itinerary</button></a>
			</div>
		</div>
	</c:forEach>

<c:import url="/WEB-INF/jsp/footer.jsp" />