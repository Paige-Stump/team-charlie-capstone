<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/header.jsp" />

<h2>Create a New Landmark</h2>

<form method="POST" action="">
	<div class="form-group">
		<label for="title">Title</label> 
		<input type="text" class="form-control" name="title"> 
		<small id="titleInstruction" class="form-text text-muted">Enter a title for the landmark</small>
	</div>
	
	<div class="form-group"></div>
	<label for="landmarkCityState">Address</label>
<!-- 	Code adapted from Google API "https://developers.google.com/maps/documentation/javascript/examples/places-placeid-finder" -->

    <input id="pac-input" class="controls" type="text" name="landmarkCityState"
        placeholder="Enter a location and use Autocomplete to select the address">
    <div id="map"></div>
    <div id="infowindow-content">
      <span id="place-name"  class="title"></span><br>
      Place ID <span id="place-id"></span><br>
      <span id="place-address"></span>
    </div>

    <script>
      // This sample uses the Place Autocomplete widget to allow the user to search
      // for and select a place. The sample then displays an info window containing
      // the place ID and other information about the place that the user has
      // selected.

      // This example requires the Places library. Include the libraries=places
      // parameter when you first load the API. For example:
      // <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&libraries=places">

      function initMap() {
        var map = new google.maps.Map(document.getElementById('map'), {
          center: {lat: -33.8688, lng: 151.2195},
          zoom: 13
        });

        var input = document.getElementById('pac-input');

        var autocomplete = new google.maps.places.Autocomplete(input);
        autocomplete.bindTo('bounds', map);

        map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

        var infowindow = new google.maps.InfoWindow();
        var infowindowContent = document.getElementById('infowindow-content');
        infowindow.setContent(infowindowContent);
        var marker = new google.maps.Marker({
          map: map
        });
        marker.addListener('click', function() {
          infowindow.open(map, marker);
        });

        autocomplete.addListener('place_changed', function() {
          infowindow.close();
          var place = autocomplete.getPlace();
          if (!place.geometry) {
            return;
          }

          if (place.geometry.viewport) {
            map.fitBounds(place.geometry.viewport);
          } else {
            map.setCenter(place.geometry.location);
            map.setZoom(17);
          }

          // Set the position of the marker using the place ID and location.
          marker.setPlace({
            placeId: place.place_id,
            location: place.geometry.location
          });
          marker.setVisible(true);

          infowindowContent.children['place-name'].textContent = place.name;
          infowindowContent.children['place-id'].textContent = place.place_id;
          infowindowContent.children['place-address'].textContent = place.formatted_address;
          infowindow.open(map, marker);
        });
      }
    </script>
    <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&libraries=places&callback=initMap"
        async defer></script>
        
<!--        End Google API code -->
	
	
	
	<div class="form-group">
		<label for="description">Description</label> 
		<input type="text" class="form-control" name="description"> 
		<small id="descriptionInstruction" class="form-text text-muted">Enter a short description (about 100 words). Suggested: use the synopsis provided by Wikipedia on a Google search of this location.</small>
	</div>
	<div class="form-group">
		<label for="location">Google Place ID</label> 
		<input type="text" class="form-control" name="location"> 
		<small id="locationInstruction" class="form-text text-muted">Get the Google Place ID from the address form above.</small>
	</div>
	<div class="form-group">
		<label for="landmarkHref">Details</label> 
		<input type="text" class="form-control" name="landmarkHref"> 
		<small id="landmarkHrefInstruction" class="form-text text-muted">Enter the HTTP address from the Wikipedia page for your landmark.</small>
	</div>
	<div class="featureText">
		<ol>
			<li>Find an image of your landmark</li>
			<li>Name it with the Google Place id</li>
			<li>Crop it to a square (1:1 aspect)</li>
			<li>Save as a .jpg to '/src/img' folder</li>
		</ol>
	</div>
	
	<button type="submit" class="btn btn-primary">Save Landmark</button>
	
</form>

<!-- <button type="button" class="btn btn-primary">Add to My Itinerary</button>  -->

<c:import url="/WEB-INF/jsp/footer.jsp" />