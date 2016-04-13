<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="objects.*, java.util.*, java.io.*"%>
<%@include file="/WEB-INF/includes/head.jsp" %>
<body>
<div class="container" >
<%@include file="/WEB-INF/includes/navbar.jsp" %>
	<div>
		<div class="text-center">
			<h1>Search Results</h1>
		</div>
</div>
		<div class="results" align="center">
			<table>
			<thead>
				<tr>
					<th>id</th>
					<th>photo</th>
					<th>title</th>
					<th>year</th>
					<th>director</th>
					<th>stars</th>
					<th>genres</th>
				</tr>
			</thead>
				<c:forEach items="${movieList}" var="movie">
				<tr>
					<td class="table-cell-pad">${movie.id}</td>
					<td class="table-cell-photo"><img src="${movie.banner }" width=90 height=120></td>
					<td class="table-cell-pad">${movie.title}</td>
					<td class="table-cell-pad">${movie.year}</td>
					<td class="table-cell-pad">${movie.director}</td>
					<td class="table-cell-pad">${movie.stars}</td>
					<td class="table-cell-pad">${movie.genres}</td>
				</tr>

		        </c:forEach>
			</table>
		</div>
	</div>






</body>
</html>