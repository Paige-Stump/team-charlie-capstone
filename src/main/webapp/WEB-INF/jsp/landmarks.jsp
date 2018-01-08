<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/header.jsp" />

<h2>Landmarks</h2>

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
			type="text" class="form-control" id="exampleInputPassword1"
			placeholder="My Itinerary Name" name="itineraryName">
	</div>
	<div class="form-group">
		<label for="startingPoint">Starting address</label> <input
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
						<input type="checkbox" class="form-check-input" name="checked${landmark.landmarkId}" value="${landmark.landmarkId}">
						<label class="form-check-label"  for="exampleCheck1">Add to My Itinerary</label>
					</div>
				</div>
			</div>
		</c:forEach>
<p>
	</p>
	<button type="submit" class="btn btn-primary">Create My Itinerary</button>
	<p></p>
</form>

<!-- <button type="button" class="btn btn-primary">Add to My Itinerary</button>  -->

<c:import url="/WEB-INF/jsp/footer.jsp" />