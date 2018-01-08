<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<h2>Landmarks</h2>

<script type="text/javascript">
	$(document).ready(function () {
	
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
<div class="input-group mb-3">
  <div class="input-group-prepend">
    <span class="input-group-text" id="itineraryName inputGroup-sizing-default">My Itinerary Name</span>
  </div>
  <input type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default">
</div>
<p>
<div class="input-group mb-3">
  <div class="input-group-prepend">
    <span class="input-group-text" id="startingPoint inputGroup-sizing-default">My Starting Point</span>
  </div>
  <input type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default">
</div>
Please enter starting point like '21 C Hotel, Cincinnati, OH' including all commas


<p>
	<c:forEach items="${landmarks}" var="landmark">
			<div class="feature">
				<c:url var="landmarkHref" value="${landmark.landmarkLink}"></c:url>
				<c:url var="landmarkImage" value="/img/${landmark.landmarkLocation}.jpg"></c:url>
				
				<div class="featureThumb">
					<a href="${landmarkHref}" target="_blank"><img src="${landmarkImage}"/></a>
				</div>
				<div class="featureText">
					<p class="title">${landmark.landmarkName}</p>
					<p class="description">${landmark.landmarkDescription}</p>
					<button type="button" class="btn btn-primary">Add to My Itinerary</button>
				</div>
			</div>
		</c:forEach>

</p>
<button type="submit" class="btn btn-primary">Create My Itinerary</button>
<p>
</p>
</form>

<c:import url="/WEB-INF/jsp/footer.jsp" />