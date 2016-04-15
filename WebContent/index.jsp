<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="objects.*, java.util.List, java.util.ArrayList, java.io.*, services.MovieListService"%>
<%@include file="/WEB-INF/includes/jspheader.jsp" %>
<html>
	<link rel="stylesheet" href="${context}/MovieScrollList.css" type="text/css">
	<%@include file="/WEB-INF/includes/header.jsp" %>
	<body>
		<div class="container" style="padding-bottom: 200px;">
			<%@include file="/WEB-INF/includes/navbar.jsp" %>





		</div>
		
		<div class="row">
			<div class="row_inner">
				<%
					MovieListService movies = new MovieListService(10);
					List<Movie> movieList = movies.getMovieList();
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
		
	</body>
</html>