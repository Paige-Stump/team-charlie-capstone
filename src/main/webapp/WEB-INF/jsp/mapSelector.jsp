<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

 <c:import url="/WEB-INF/jsp/header.jsp" />  

<style>
      #right-panel {
        line-height: 30px;
        padding-left: 10px;
      }

      #right-panel select, #right-panel input {
        font-size: 15px;
      }

      #right-panel select {
        width: 100%;
      }

      #right-panel i {
        font-size: 12px;
      }
      #map {
        height: 500px;
      }
      #right-panel {
        height: 400px;
        text-align: left;
      }
      #directions-panel {
        background-color: #f2f1fe;
        padding: 10px;
        overflow: scroll;
        height: 500px;
      }
      #google {
      	margin-top: 2rem;
      }
    </style>
    

<h2>${itineraryName} Itinerary Route</h2>

<c:url var="goBack" value="/users/userDash"/>
<a href="${goBack}"><button type="button" class="btn btn-primary">Return to My Itineraries</button></a>
	
<div class="row" id="google">
    <div class="col-sm-8">
		<div id="map"></div> 
	</div>

	<div class="col-sm-4">	
		<div id="right-panel">
			<div id="waypoints">
			</div>
			<div id="directions-panel">
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	function initMap() {
		var directionsService = new google.maps.DirectionsService;
		var directionsDisplay = new google.maps.DirectionsRenderer;
		var map = new google.maps.Map(document.getElementById("map"), {
			zoom : 12,
			center : {
				lat : 39.1031,
				lng : -84.5120
			}
		});
		directionsDisplay.setMap(map);

		window.onload = function() {
			calculateAndDisplayRoute(directionsService, directionsDisplay);
		};
		
		function loadScript() {
			var script = document.createElement("script");
			script.src = "http://"
		}
	}

	function calculateAndDisplayRoute(directionsService, directionsDisplay) {
		var waypts = [];
		var itineraryArray = document.getElementById('waypoints');
		for (var i = 0; i < itineraryArray.length; i++) {
				waypts.push({
					location : checkboxArray[i].value,
					stopover : true
				});
		
		}

		directionsService.route({
			origin : "${start}",
			destination : "${end}",
			waypoints : ${waypts},
			optimizeWaypoints : true,
			travelMode : 'DRIVING'
		}, function(response, status) {
			if (status === 'OK') {
				
				directionsDisplay.setDirections(response);
				var route = response.routes[0];
				var summaryPanel = document.getElementById('directions-panel');
				summaryPanel.innerHTML = '';
				// For each route, display summary information.
				for (var i = 0; i < route.legs.length; i++) {
					var routeSegment = i + 1;
					summaryPanel.innerHTML += '<b>Route Segment: '
							+ routeSegment + '</b><br>';
					summaryPanel.innerHTML += route.legs[i].start_address
							+ ' to ';
					summaryPanel.innerHTML += route.legs[i].end_address
							+ '<br>';
					summaryPanel.innerHTML += route.legs[i].distance.text
							+ '<br><br>';
				}
			} else {
				window.alert('Directions request failed due to ' + status);
			}
		});
	}
</script>

<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAyG_Bzw3pK-8mlytLJy-JgUvNzgkzO4d4&callback=initMap">
	
</script>

<c:import url="/WEB-INF/jsp/footer.jsp" />