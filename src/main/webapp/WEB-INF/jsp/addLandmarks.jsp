<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/header.jsp" />

<h2>Add Landmarks</h2>
<!-- ADD JAVASCRIPT BACK IN? -->

<c:url var="formAction" value="/users/userDash" />
<form method="POST" action="">
	<div class="form-group">
		<h3>Add more landmarks to your {itinerary.itinerary_name} itinerary</h3>
		<!-- CHANGE CONTROLLER - PASS IN AN ITINERARY NAME -->
	</div>
	<div class="form-group">
	<p>Your starting point is: ${startingPoint}</p>
		<label for="startingPoint">Change your Starting Point</label> <input
			type="text" class="form-control" name="startingPoint"
			placeholder="21 C Hotel, Cincinnati, OH"> <small
			id="howToEnter" class="form-text text-muted">Please enter
			starting point like '21 C Hotel, Cincinnati, OH' including all commas</small>
	</div>




	<p>
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
					<!-- MAKE SURE TO CHANGE THIS PORTION BELOW BASED ON CHECKBOX ARRAY -->
						<input type="checkbox" class="form-check-input" name="checked${landmark.landmarkId}" value="${landmark.landmarkId}">
						<label class="form-check-label"  for="exampleCheck1">Add to My Itinerary</label>
					</div>
				</div>
			</div>
		</c:forEach>

	</p>
	<button type="submit" class="btn btn-primary">Change My Itinerary</button>
	<p></p>
</form>

<!-- <button type="button" class="btn btn-primary">Add to My Itinerary</button>  -->

<c:import url="/WEB-INF/jsp/footer.jsp" />