<%@include file="/WEB-INF/includes/jspheader.jsp" %>
<html>
	<head>
		<%@include file="/WEB-INF/includes/head.jsp" %>
		<link href="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.4.5/css/bootstrapvalidator.min.css">
		<script src="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.4.5/js/bootstrapvalidator.min.js"></script>
		<style>
			
		</style>
	</head>
	<body>
		<div class="container">
			<%@include file="/WEB-INF/includes/navbar.jsp" %>
			<% session.removeAttribute("action"); %> 
			<h1>Employee Dashboard</h1>
			<br/>
			<br/>
			<h3 style="text-align: center;">Insert a new Star</h3>
			<form id="insertForm" action="${pageContext.request.contextPath}/EmployeeDashboard" 
				class="form-horizontal" role="form" method="POST">
			
				<div class="form-group">
					<label class="col-xs-3 control-label">First Name</label>
					<div class="col-xs-5">
						<input type="text" class="form-control lgn" name="firstName">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-xs-3 control-label">Last Name</label>
					<div class="col-xs-5">
						<input type="text" class="form-control lgn" name="lastName">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-xs-3 control-label">Date of Birth</label>
					<div class="col-xs-5">
						<input type="text" class="form-control" name="dob">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-xs-3 control-label">Photo URL</label>
					<div class="col-xs-5">
						<input type="text" class="form-control" name="photo">
					</div>
				</div>
				<input type="hidden" name="action" value="insert">	 
				
				<div class="form-group">
        			<div class="col-xs-6 col-xs-offset-3">
            			<button type="submit" id="starBtn" onclick="setStar(event)" class="btn btn-success lgn">Submit</button>
            			<c:choose>            			
            				<c:when test="${isAdded == 'y'}">
            					<div>
           							<p style="text-weight: bold; color: green;">Added New Star: ${star.getFirst()} ${star.getLast()}!</p>            					
            					</div>
            				</c:when>
            				<c:otherwise>
            					<div>
            						<p style="text-weight: bold; color: red;">Nothing Submitted</p>
            					</div>
            				</c:otherwise>
            			</c:choose>
        			</div>
   				</div>
	   		</form>
	   		
	   		<form action="XMLParser" 
				class="form-horizontal" role="form" method="POST">
			
				<div class="form-group">
					<label class="col-xs-3 control-label">Title</label>
					<div class="col-xs-5">
						<input type="text" class="form-control lgn" name="title">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-xs-3 control-label">Director</label>
					<div class="col-xs-5">
						<input type="text" class="form-control lgn" name="director">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-xs-3 control-label">Year</label>
					<div class="col-xs-5">
						<input type="text" class="form-control lgn" name="year">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-xs-3 control-label">Genre</label>
					<div class="col-xs-5">
						<input type="text" class="form-control lgn" name="genre">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-xs-3 control-label">Star First Name</label>
					<div class="col-xs-5">
						<input type="text" class="form-control lgn" name="starFirst">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-xs-3 control-label">Star Last Name</label>
					<div class="col-xs-5">
						<input type="text" class="form-control lgn" name="starLast">
					</div>
				</div>				
			</form>
	   		
	   		<hr/>
	   		<h2>Database Metadata</h2>
		   	<div id="metadataTable">
		   		creditcards<br/>
				id -- VARCHAR(20)<br/>
				first -- VARCHAR(50)<br/>
				last -- VARCHAR(50)<br/>
				expiration -- DATE(null)
				
				<br/>
				<br/>
				
				customers<br/>
				id -- INT(10)<br/>
				first -- VARCHAR(50)<br/>
				last -- VARCHAR(50)<br/>
				cc -- VARCHAR(20)<br/>
				address -- VARCHAR(200)<br/>
				email -- VARCHAR(50)<br/>
				password -- VARCHAR(20)
				
				<br/><br/>
				
				employees<br/>
				email -- VARCHAR(50)<br/>
				password -- VARCHAR(20)<br/>
				fullname -- VARCHAR(100)
				<br/><br/>
				
				genres<br/>
				id -- INT(10)<br/>
				name -- VARCHAR(32)
				
				<br/><br/>
				
				genres_in_movies<br/>
				genre_id -- INT(10)<br/>
				movie_id -- INT(10)
				
				<br/>
				<br/>
				
				movies<br/>
				id -- INT(10)<br/>
				title -- VARCHAR(100)<br/>
				year -- INT(10)<br/>
				director -- VARCHAR(100)<br/>
				banner -- VARCHAR(200)<br/>
				trailer -- VARCHAR(200)
				
				<br/>
				<br/>
				
				sales<br/>
				id -- INT(10)<br/>
				customers -- INT(10)<br/>
				movie -- INT(10)<br/>
				sale -- DATE(null)
				
				<br/>
				<br/>
				
				
				stars<br/>
				id -- INT(10)<br/>
				first -- VARCHAR(50)<br/>
				last -- VARCHAR(50)<br/>
				dob -- DATE(null)<br/>
				photo -- VARCHAR(200)
				
				<br/>
				<br/>
				
				stars_in_movies<br/>
				star_id -- INT(10)<br/>
				movie_id -- INT(10)
		   	</div>
		</div>
	</body>
	<script type="text/javascript">
		$(document).ready(function() {			
			$('#insertForm').bootstrapValidator({
		        feedbackIcons: {
		            valid: 'glyphicon glyphicon-ok',
		            invalid: 'glyphicon glyphicon-remove',
		            validating: 'glyphicon glyphicon-refresh'
		        },
		        fields: {
		            firstName: {
		                validators: {
		                    notEmpty: {
		                        message: 'First Name Required'
		                    } 
		                }
		            },
		            lastName: {
		                validators: {
		                    notEmpty: {
		                        message: 'Last Name Required'
		                    }
		                }
		            }, 
		            dob: {
		            	validators: {
		            		regexp: {
		            			regexp: /^\d{4}\-(0?[1-9]|1[012])\-(0?[1-9]|[12][0-9]|3[01])$/i,
		            			message: 'Date Format must be in YYYY-MM-DD'
		            		}
		            	}
		            }
		        }
		    });
		});
		
		function setStar(event) {
			document.getElementById('action').value = 'insert'; 
			document.getElementById('starBtn').submit();
		}
	</script>
</html>