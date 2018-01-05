<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/header.jsp" />
<h3 id="#h3Maps">My UrbanAdventure</h3>
<script type="text/javascript">
	var markers = [ {
		"lat" : "39.1031",
		"lng" : "-84.5120",
		"description" : "Cincinnati, OH"

	}, {
		"lat" : "39.0979",
		"lng" : "-84.5082",
		"description" : "Reds Ballpark"

	}, {
		"lat" : '39.1743',
		"lng" : '-84.5250',
		"description" : "Spring Grove Cemetery"
	}, {
		"lat" : "39.0930",
		"lng" : "-84.5099",
		"description" : "John Roebling Bridge"
	}

	];
	window.onload = function() {
		var mapOptions = {
			center : new google.maps.LatLng(39.1031, -84.5120),
			zoom : 9,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};
		var map = new google.maps.Map(document.getElementById("dvMap"),
				mapOptions);
		var infoWindow = new google.maps.InfoWindow();
		var lat_lng = new Array();
		var latlngbounds = new google.maps.LatLngBounds();
		for (i = 0; i < markers.length; i++) {
			var data = markers[i]
			var myLatlng = new google.maps.LatLng(data.lat, data.lng);
			lat_lng.push(myLatlng);
			var marker = new google.maps.Marker({
				position : myLatlng,
				map : map,
				optimizeWaypoints: true,
				title : data.title
			});
			latlngbounds.extend(marker.position);
			(function(marker, data) {
				google.maps.event.addListener(marker, "click", function(e) {
					infoWindow.setContent(data.description);
					infoWindow.open(map, marker);
				});
			})(marker, data);
		}
		map.setCenter(latlngbounds.getCenter());
		map.fitBounds(latlngbounds);

		//***********ROUTING****************//

		//Initialize the Path Array
		var path = new google.maps.MVCArray();

		//Initialize the Direction Service
		var service = new google.maps.DirectionsService();

		//Set the Path Stroke Color
		var poly = new google.maps.Polyline({
			map : map,
			strokeColor : '#4986E7'
		});

		//Loop and Draw Path Route between the Points on MAP
		for (var i = 0; i < lat_lng.length; i++) {
			if ((i + 1) < lat_lng.length) {
				var src = lat_lng[i];
				var des = lat_lng[i + 1];
				path.push(src);
				poly.setPath(path);
				service
						.route(
								{
									origin : src,
									destination : des,
									travelMode : google.maps.DirectionsTravelMode.DRIVING
								},
								function(result, status) {
									if (status == google.maps.DirectionsStatus.OK) {
										for (var i = 0, len = result.routes[0].overview_path.length; i < len; i++) {
											path
													.push(result.routes[0].overview_path[i]);
										}
									}
								});
			}
		}
	}
</script>
<div id="dvMap" style="width: 100%; height: 500px"></div>
<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAyG_Bzw3pK-8mlytLJy-JgUvNzgkzO4d4&callback=initMap">
	
</script>



<c:import url="/WEB-INF/jsp/footer.jsp" />


