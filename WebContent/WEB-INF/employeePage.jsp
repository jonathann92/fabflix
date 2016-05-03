<%@include file="/WEB-INF/includes/jspheader.jsp" %>
<html>
	<head>
		<%@include file="/WEB-INF/includes/head.jsp" %>
		<link rel="stylesheet" type="text/css" href="./mainpage.css"/>
		<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>		
    	<link rel='stylesheet' href='http://cdn.jsdelivr.net/jquery.slick/1.3.7/slick.css'>
		<style type="text/css">
			
		</style>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>	
		</head>
	<body>
		<div class="container" id="employeeForm">
			<%@include file="/WEB-INF/includes/navbar.jsp" %>
			<h2 class="employeeHeader">Enter Credentials -</h2>
			<form action="" role="form" method=GET>
				<div class="form-group">
					<label>Email:</label>
					<input type="text" class="form-control" name=email placeholder="Enter email" value="${email}">
				</div>
				
				<div class="form-group">
					<label>password:</label>
					<input type="text" class="form-control" name=email placeholder="Enter password" value="${password}">
				</div>
				<button type="submit" id="submitToInserts" class="btn btn-default">Submit</button>
			</form>
			<h4 class="error-message">${error }</h4>
		</div>
		<div id="insertForm"></div>
	</body>
	<style type="text/javascript">
		$(document).ready(function() {
			$("button").click(function() {
				$.ajax({
					type: "GET",
					url: "EmployeePage",
					dataType: "html",
					success: function(data) {
						$("#employeeForm").hide();
						$("#insertForm").html(data);
					}, 
					error: function() {
						$("#submitToInserts").html('An error occured while processing html file');
					}
				});
			});
		});
	</style>
</html>