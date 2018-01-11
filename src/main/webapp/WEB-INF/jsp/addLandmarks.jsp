<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:import url="/WEB-INF/jsp/header.jsp" />


<h2>Add Landmarks to ${itineraryName} Itinerary</h2>
<!-- ADD JAVASCRIPT BACK IN? -->

<c:url var="formAction" value="/users/userDash">
				<c:param name="itineraryStart">${itineraryStart}</c:param></c:url>

<form method="POST" action="" id="searchForm">
	<div class="form-group">
		<div class="input-group">
			<span class="input-group-btn">
				<button class="btn btn-primary" type="submit">Find
					landmark!</button>
			</span> <input type="text" name="searchForLandmark" id="searchForLandmark"
				name="search" placeholder="adventure awaits...">
		</div>
	</div>
</form>
<form method="POST" action="" id="landmarkForm">
	<div class="form-group">
		<p>Your starting point is: ${itineraryStart}</p>
		<label for="changeStartingPoint">Change your Starting Point</label> <input
			type="text" class="form-control" name="changeStartingPoint"
			placeholder="${itineraryStart}"> <small
			id="howToEnter" class="form-text text-muted">Please enter
			starting point like '21 C Hotel, Cincinnati, OH' including all commas</small>
	</div>
<div class="resultsList">
	<c:forEach items="${landmarks}" var="landmark">
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
				<div class="form-check">
					<input type="checkbox" class="form-check-input" name="landmarkId" value="${landmark.landmarkId}">
					<label class="form-check-label"  for="exampleCheck1">Add to My Itinerary</label>
				</div>
			</div>
		</div>
	</c:forEach>
</div>
	<button type="submit" class="btn btn-primary">Change My Itinerary</button>
	<c:url var="goBack" value="/users/userDash"/>
<a href="${goBack}"><button type="button" class="btn btn-primary">Return to My Itineraries</button></a>
	
</form>

<!-- <button type="button" class="btn btn-primary">Add to My Itinerary</button>  -->

<c:import url="/WEB-INF/jsp/footer.jsp" />