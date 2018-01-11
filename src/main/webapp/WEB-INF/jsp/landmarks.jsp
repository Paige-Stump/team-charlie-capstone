<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/header.jsp" />
<h2>Create New Itinerary</h2>
<script type="text/javascript">
	$(document).ready(function() {

		$(document).validate({
			
			rules : {
				itineraryName : {
					required : true
				},
				startingPoint : {
					required : true
				},
				landmarkId: {
					minlength: 1
				}
			},
			messages: {
				itineraryName: {
					equalTo: "Please enter an Itinerary Name",
				},  
					
				startingPoint:  {
					equalTo: "Please enter a Starting Point",
				}
			},
			errorClass: "error",
			validClass: "valid"
		});
	
		
	$("#createItineraryForm").submit(function() {
		
		try {
			var epicFail = false;
			var form = document.getElementById('createItineraryForm');
			var elements = form.elements;
			var options = elements.landmarkId;
			var array = [];
			if (options == undefined) {
				epicFail = true;
			}
			else if ('length' in options == false) {
				if (options.checked == false) {
					epicFail = true;
				}
			}
			else {
				for (var i = 0; i < options.length; i++) {
					if (options[i].checked) {
						array.push(options[i]);
					}
				}
				if (array.length == 0 ) {
					epicFail = true;
				}
			}
			if(epicFail == true) {
				alert("Please choose a landmark to create an itinerary","Urban Advisor");	//Put this in Javascript text instead somewhere on page
				return false;
			}
		}
		catch(e) {
			alert(e.message, "Urban Advisor");
		}


		return true;
	});
});
</script>

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

<c:url var="formAction" value="/users/userDash" />
<form method="POST" action="" id="createItineraryForm">
	<div class="form-group">
		<label class="required" for="itineraryName">Itinerary Name</label> 
		<input type="text" class="form-control" id="itineraryName" placeholder="My Itinerary Name" name="itineraryName" required>
		<small id="itineraryName" class="form-text text-muted">Please enter a itinerary name.</small>
		
	</div>
	<div class="form-group">
		<label class="required" for="startingPoint">Starting Address</label> 
		<input type="text" required class="form-control" name="startingPoint" placeholder="21 C Hotel, Cincinnati, OH"> 
		<small id="startingPoint" class="form-text text-muted">Please enter a starting point.</small>
	</div>
	
	
	
	<h3 class="required" >Landmarks</h3>
	<small id="itineraryName" class="form-text text-muted">Please select at least one landmark.</small>
	
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
						<label class="form-check-label"  for="landmarkId">Add to My Itinerary</label>
					</div>
				</div>
			</div>
		</c:forEach>
		</div>

	<button type="submit" class="btn btn-primary">Create My Itinerary</button>
	<c:url var="goBack" value="/users/userDash"/>
	<a href="${goBack}"><button type="button" class="btn btn-primary">Return to My Itineraries</button></a>
</form>

<!-- <button type="button" class="btn btn-primary">Add to My Itinerary</button>  -->

<c:import url="/WEB-INF/jsp/footer.jsp" />