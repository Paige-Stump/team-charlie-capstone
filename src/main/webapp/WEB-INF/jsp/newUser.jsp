<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<script type="text/javascript">
	$(document).ready(function () {
		
		//add a method in javascript that returns true or false 
		$.validator.addMethod('hasUppercase', function(value) {
			return value.match(/[A-Z]/); //regular expression we are using (.match string in JS)
			// will tell us true if there is a single char in the string that matches 
			// a capital letter between A and Z
		});
		
	
		$("form").validate({
			
			rules : {
				userName : {
					required : true
				},
				
				firstName : {
					required : true
				},
				
				lastName : {
					required : true
				},
				
				password : {
					required : true,
					minlength: 10,
					hasUppercase: true // must have an uppercase letter b/t A and Z
				},
				
				confirmPassword : {
					required : true,		
					equalTo : "#password"  
				}
			},
			messages : {			
				password: {
					hasUppercase: "You need at least one uppercase character",
					minlength: "Password too short, must be at least 10 characters"
					
				}, 
				confirmPassword : {
					equalTo : "Passwords do not match",
				}
			},
			errorClass : "error"
		});
	});
</script>

				
<c:url var="formAction" value="/users" />
<form method="POST" action="${formAction}">
	<div class="row">
		<div class="col-sm-4"></div>
		<div class="col-sm-4">
			<div class="form-group">
				<label for="userName">User Name: </label>
				<input type="text" id="userName" name="userName" placeHolder="User Name" class="form-control" />
				<div class="error">${error}</div>
			</div>
			<div class="form-group">
				<label for="firstName">First Name: </label>
				<input type="text" id="firstName" name="firstName" placeHolder="First Name" class="form-control" />
			</div>
			<div class="form-group">
				<label for="lastName">Last Name: </label>
				<input type="text" id="lastName" name="lastName" placeHolder="Last Name" class="form-control" />
			</div>
			<div class="form-group">
				<label for="password">Password: </label>
				<input type="password" id="password" name="password" placeHolder="Password" class="form-control" />
			</div>
			<div class="form-group">
				<label for="confirmPassword">Confirm Password: </label>
				<input type="password" id="confirmPassword" name="confirmPassword" placeHolder="Re-Type Password" class="form-control" />	
			</div>
			<button type="submit" class="btn btn-default">Create User</button>
		</div>
		<div class="col-sm-4"></div>
	</div>
</form>
		
<c:import url="/WEB-INF/jsp/footer.jsp" />