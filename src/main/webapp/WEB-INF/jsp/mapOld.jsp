<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/header.jsp" />
<style>
#map {
	width: 100%;
	height: 400px;
	background-color: lightblue;
}
</style>

<h3>My UrbanAdvisor Itinerary</h3>

<p>Starting from: <span id="start">Cincinnati Hilton</span></p>
<p>Landmark 1: <span id="first">Cincinnati Zoo</span></p>
<p>Landmark 2: <span id="second">Fountain Square</span></p>
<p>Landmark 3: <span id="third">Spring Grove Cemetery</span></p>
<p>Landmark 4: <span id="fourth">Paul Brown Stadium</span></p>
<button type="button" class="btn btn-primary" id="button">Calculate Route</button>
<p id="clickedField"></p>
<!-- ADD CSS TO CREATE MARGIN AROUND BUTTON -->
<div id="map"></div>
<script>
	function initMap() {
		var cincinnati = {
			lat : 39.1031,
			lng : -84.5120
		};
		var map = new google.maps.Map(document.getElementById('map'), {
			zoom : 12,
			center : cincinnati
		});
		var marker = new google.maps.Marker({
			position : cincinnati,
			map : map
		});
	}
	$("#button").click(function() {
		var start = $("#start").text();
		$("#clickedField").text(start);
	});
</script>
<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAyG_Bzw3pK-8mlytLJy-JgUvNzgkzO4d4&callback=initMap"> 
	
</script>



<!-- Below is the section that should take the information the user submits and update the page -->

<!-- Could also run through the legs between waypoints and print them out here or to the right -->








<c:import url="/WEB-INF/jsp/footer.jsp" />


