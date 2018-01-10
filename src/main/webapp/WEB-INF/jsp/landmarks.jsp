<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/header.jsp" />
<h2>Create New Itinerary</h2>
<script type="text/javascript">
	$(document).ready(function() {

		$("form").validate({

			rules : {
				itineraryName : {
					required : true
				},
				startingPoint : {
					required : true
				}
			},
		});
	});
</script>



<c:url var="formAction" value="/users/userDash" />
<form method="POST" action="">
	<div class="form-group">
		<label for="itineraryName">Itinerary Name</label> <input
			type="text" class="form-control" id="itineraryName"
			placeholder="My Itinerary Name" name="itineraryName">
	</div>
	<div class="form-group">
		<label for="startingPoint">Starting address</label> <input
			type="text" required class="form-control" name="startingPoint"
			placeholder="21 C Hotel, Cincinnati, OH"> <small
			id="startingPoint" class="form-text text-muted">Please enter
			starting point like '21 C Hotel, Cincinnati, OH' including all commas</small>
	</div>
	<h3>Landmarks</h3>
	

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

	<button type="submit" class="btn btn-primary">Create My Itinerary</button>
</form>

<!-- <button type="button" class="btn btn-primary">Add to My Itinerary</button>  -->

<c:import url="/WEB-INF/jsp/footer.jsp" />