<%@include file="/WEB-INF/includes/jspheader.jsp" %>
<html>
	<head>
		<%@include file="/WEB-INF/includes/head.jsp" %>
	</head>
	<body>
		<div class="container">
		<%@include file="/WEB-INF/includes/navbar.jsp" %>
		
			<h1 class="text-center">Checkout Success</h1>
				
			<h2 class="text-center">Your transaction IDs are:</h2>
			<c:forEach var="sale" items="${transactions}">
				<p class="text-center">${sale }</p>
			</c:forEach>
		
		</div>
	</body>
</html>