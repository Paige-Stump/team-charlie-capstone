<!--  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> -->

 <c:import url="/WEB-INF/jsp/header.jsp" />  

</div>
<style>
      #right-panel {
        font-family: 'Roboto','sans-serif';
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
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
      #map {
        height: 500px;
        float: left;
        width: 70%;
        margin-top: 100px;
        margin-left: 40px;
      }
      #right-panel {
        margin: 20px;
        width: 20%;
        height: 400px;
        float: left;
        text-align: left;
        padding-top: 36px;
      }
      #directions-panel {
        margin-top: 10px;
        background-color: #f2f1fe;
        padding: 10px;
        overflow: scroll;
        height: 500px;
      }
      #footer {
      	margin-top: 600px;
      }
    </style>



<div id="map"></div> 
<div id="right-panel">
	 <div> <!-- 
		<b>Start:</b> <select id="start">
			<option value="Cincinnati, OH">Cincinnati, OH</option>
			<option value="Westin Cincinnati, OH">Cincinnati Westin</option>
			<option value="21C Hotel, Cincinnati, OH">21 C Hotel</option>
			<option value="Cincinnatian Hotel, Cincinnati, OH">The Cincinnatian</option>
		</select> <br> <b>Places to Add to My Itinerary:</b> <br> <i>(Ctrl+Click or Cmd+Click
			for multiple)</i> <br> <select multiple id="waypoints">
			<option value="Spring Grove Cemetery, Cincinnati, OH">Spring Grove Cemetery</option>
			<option value="Reds Stadium, Cincinnati, OH">Reds Stadium</option>
			<option value="Carew Tower, Cincinnati, OH">Carew Tower</option>
			<option value="John Roebling Bridge, Cincinnati, OH">John Roebling Bridge</option>
			<option value="Fountain Square, Cincinnati, OH">Fountain Square</option>
			<option value="Cathedral Basilica, Cincinnati, OH">Cathedral Basilica</option>
			<option value="William Howard Taft National Historic Site, Cincinnati, OH">William H Taft Historic Site</option>
			<option value="Paul Brown Stadium, Cincinnati, OH">Paul Brown Stadium</option>
			<option value="Mount Adams, Cincinnati, OH">Mount Adams</option>
			<option value="Winton Woods Park, Cincinnati, OH">Winton Woods</option>
		</select> <br> <b>End:</b> <select id="end">
			<option value="Cincinnati, OH">Cincinnati, OH</option>
			<option value="Westin Cincinnati, OH">Cincinnati Westin</option>
			<option value="21C Hotel, Cincinnati, OH">21 C Hotel</option>
			<option value="Cincinnatian Hotel, Cincinnati, OH">The Cincinnatian</option>
			
		</select> //--> 
	</div> 
	<button class="btn btn-primary" type="submit" id="submit">Generate my Route</button>
	<div id="directions-panel"></div>
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

		document.getElementById('submit').addEventListener('click', function() {
			calculateAndDisplayRoute(directionsService, directionsDisplay);
		});
		
		function loadScript() {
			var script = document.createElement("script");
			script.src = "http://"
		}
	}

	function calculateAndDisplayRoute(directionsService, directionsDisplay) {
		var waypts = [];
		//var checkboxArray = document.getElementById('waypoints');
		//for (var i = 0; i < checkboxArray.length; i++) {
		//	if (checkboxArray.options[i].selected) {
		//		waypts.push({
		//			location : checkboxArray[i].value,
		//			stopover : true
		//		});
		//	}
	//	}

		directionsService.route({
			origin : "${start}",
			destination : "${end}",
			waypoints : [{location: {placeId: "ChIJLwacWEOxQYgRcu3UJQuLYwc"}, stopover: true}, 
				{location: {placeId: "ChIJvVE_ekaxQYgRt78Wg7uX35w"}, stopover: true} 
				],
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

<p id="footer"></p>
<c:import url="/WEB-INF/jsp/footer.jsp" />