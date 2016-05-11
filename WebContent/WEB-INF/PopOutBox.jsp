<%@include file="/WEB-INF/includes/jspheader.jsp" %>
<html>
	<head>
		<%@include file="/WEB-INF/includes/head.jsp" %>
	</head>
	<body>
		<img src="${movie.banner}" style="height: 40px; width: 40px;" />
		<h1>${movie.title }</h1>
		<h2>${movie.year }</h2>
		
	</body>
</html>