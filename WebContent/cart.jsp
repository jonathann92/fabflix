<%@include file="/WEB-INF/includes/jspheader.jsp" %>
<html>
	<head>
		<%@include file="/WEB-INF/includes/head.jsp" %>
	</head>
	<body>
		<div class="container">
		<%@include file="/WEB-INF/includes/navbar.jsp" %>
		
		
		<h1 class="text-center">
			Shopping Cart
		</h1>
		
		<c:if test="${error != null }">
			<h4>${error }</h4>
		</c:if>
		
		<c:choose>
			<c:when test="${not empty cart and cart != null }">
				
			</c:when>
		</c:choose>
		
		</div>
	</body>
</html>