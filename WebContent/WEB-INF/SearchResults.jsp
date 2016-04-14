<%@include file="/WEB-INF/includes/jspheader.jsp" %>
<html>
<%@include file="/WEB-INF/includes/header.jsp" %>
<body>
<div class="container" >
<%@include file="/WEB-INF/includes/navbar.jsp" %>
<%
	request.setAttribute("fullMovieList", request.getAttribute("fullMovieList"));
%>
	<div>
		<div class="text-center">
			<h1>Search Results</h1>
		</div>
	</div>
	<p>Rows: </p>
	<a href="/filmdb/MovieList?page=${page}&sortby=${sortby}&rows=10">10</a>
	<a href="/filmdb/MovieList?page=${page}&sortby=${sortby}&rows=25">25</a>
	<a href="/filmdb/MovieList?page=${page}&sortby=${sortby}&rows=50">50</a>
	<a href="/filmdb/MovieList?page=${page}&sortby=${sortby}&rows=100">100</a>
	<div class="results" align="center">
		<table>
		<thead>
			<tr>
				<th>
					id
					<a href="/filmdb/MovieList?page=${page}&sortby=idup&rows=${rows}"><i class="fa fa-arrow-up" aria-hidden="true"></i></a>
					<a href="/filmdb/MovieList?page=${page}&sortby=iddown&rows=${rows}"><i class="fa fa-arrow-down" aria-hidden="true"></i></a>
				</th>
				<th>
					photo
				</th>
				<th>
					title
					<a href="/filmdb/MovieList?page=${page}&sortby=titleup&rows=${rows}"><i class="fa fa-arrow-up" aria-hidden="true"></i></a>
					<a href="/filmdb/MovieList?page=${page}&sortby=titledown&rows=${rows}"><i class="fa fa-arrow-down" aria-hidden="true"></i></a>
				</th>
				<th>
					year
					<a href="/filmdb/MovieList?page=${page}&sortby=yearup&rows=${rows}"><i class="fa fa-arrow-up" aria-hidden="true"></i></a>
					<a href="/filmdb/MovieList?page=${page}&sortby=yeardown&rows=${rows}"><i class="fa fa-arrow-down" aria-hidden="true"></i></a>
				</th>
				<th>
					director
				</th>
				<th>
					stars
				</th>
				<th>
					genres
				</th>
			</tr>
		</thead>
			<c:forEach items="${movieList}" var="movie">
			<tr>
				<td class="table-cell-pad">
					<a href="/filmdb/MoviePage?id=${movie.id }">${movie.id}</a>
				</td>
				<td class="table-cell-photo">
					<a href="/filmdb/MoviePage?id=${movie.id }"><img src="${movie.banner }" onError="this.src='https://escherdax.files.wordpress.com/2009/12/illustration.jpg';" width=90 height=120></a>
				</td>
				<td class="table-cell-pad">
					<a href="/filmdb/MoviePage?id=${movie.id }">${movie.title}</a>
				</td>
				<td class="table-cell-pad">${movie.year}</td>
				<td class="table-cell-pad">${movie.director}</td>
				<td class="table-cell-pad">
					
						<c:forEach items="${movie.stars}" var="star">
							<a href="/filmdb/StarPage?id=${star.id }" class="movielist-star" style="padding-bottom: 10px; display: block;"> ${star.first } ${star.last } </a>
						</c:forEach>
					
				</td>
				<td class="table-cell-pad">
					<c:forEach items="${movie.genres }" var="genre">
						<p style="line-height: .5;"> ${genre.genre } </p>
					</c:forEach>
					
				</td>
			</tr>

	        </c:forEach>
		</table>
	</div>
		
	<div class="page-selector">
		<div class="prev-page pull-left">
			<a href="/filmdb/MovieList?page=${page-1 }&sortby=${sortby}"  rel="prev"><i class="fa fa-long-arrow-left fa-5x"></i></a>
			<p>Page</p>
		</div>
		<div class="next-page pull-right">
			<a href="/filmdb/MovieList?page=${page+1 }&sortby=${sortby}&rows=${rows}" rel="next"><i class="fa fa-long-arrow-right fa-5x"></i></a>
			<p>Page</p>
		</div>
	</div>
</div>

</body>
</html>