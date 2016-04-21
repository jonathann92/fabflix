<%@include file="/WEB-INF/includes/jspheader.jsp" %>
<html>
	<head>
		<%@include file="/WEB-INF/includes/head.jsp" %>
		<title>404...</title>
		<style>
			h1 h4 p {
				display: inline-block;
				width: 50%;
				margin: 0 auto;
				text-align: center;
			}	
				
		</style>
	</head>
	<body>
		<div class="container">
			<%@include file="/WEB-INF/includes/navbar.jsp" %>
			
			<div class="jumbotron vertical-center">
				<div class="container">
					<h1>Crap...</h1>
					<h4>Thought we fixed that</h4>
					<p><a href="${context}" class="btn btn-primary btn-lg" role="button">Get me out of here!</a></p>
				</div>
			</div>
		</div>
	</body>
</html>