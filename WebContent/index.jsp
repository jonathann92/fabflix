<%@include file="/WEB-INF/includes/jspheader.jsp" %>
<html>
<head>
	<%@include file="/WEB-INF/includes/head.jsp" %>
</head>
	<body>
		<div class="container" style="padding-bottom: 200px;">
			<%@include file="/WEB-INF/includes/navbar.jsp" %>
		</div>
		<!-- 
		<div class="row">
			<div class="row_inner">
				<%
					//MovieListService movies = new MovieListService(10);
					//List<Movie> movieList = movies.getMovieList();
				%>
				<p>${movieList.get(0).banner}</p>
				<c:choose>
					<c:when test="${not empty movieList}">						
						<c:forEach var="movies" items="${movieList}">
							<p>"${movies.title}"</p>
						</c:forEach>
					</c:when>
				</c:choose>
			   </div>
			</div>
			-->
	</body>
</html>