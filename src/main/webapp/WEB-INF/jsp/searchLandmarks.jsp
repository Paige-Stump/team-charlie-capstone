<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/header.jsp" />
<h2>Search Landmarks</h2>

	<h3>Landmarks</h3>
	
	<c:url var="formAction" value="/users/userDash" />
<form method="POST" action="">
	<div class="form-group">
		<div class="input-group">
			<span class="input-group-btn">
				<button class="btn btn-secondary" type="submit">Find
					landmark!</button>
			</span> <input type="text" name="searchForLandmark" id="searchForLandmark"
				name="search" placeholder="adventure awaits...">
		</div>
	</div>
</form>

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
				</div>
			</div>
		</c:forEach>

	


<c:import url="/WEB-INF/jsp/footer.jsp" />