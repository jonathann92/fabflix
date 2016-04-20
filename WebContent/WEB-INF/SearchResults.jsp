<%@include file="/WEB-INF/includes/jspheader.jsp" %>
<html>
<head>
<%@include file="/WEB-INF/includes/head.jsp" %>
</head>
<body>
<div class="container" >
<%@include file="/WEB-INF/includes/navbar.jsp" %>
<%
	request.setAttribute("fullMovieList", request.getAttribute("fullMovieList"));
%>

	<c:choose>
	<c:when test="${not empty movieList and movieList != null}">
		
	
	<div>
		<div class="text-center">
			<h1>Search Results</h1>
		</div>
	</div>
	<p style="display: inline; font-size: 18px;">Row Count: </p>
	<a href="${context}/${prevpage}?${query}&page=${page}&sortby=${sortby}&rows=10">10</a>
	<a href="${context}/${prevpage}?${query}&page=${page}&sortby=${sortby}&rows=25">25</a>
	<a href="${context}/${prevpage}?${query}&page=${page}&sortby=${sortby}&rows=50">50</a>
	<a href="${context}/${prevpage}?${query}&page=${page}&sortby=${sortby}&rows=100">100</a>
	<div class="results" align="center">
	
	
	
	
		<table>
		<thead>
			<tr>
				<th>
					id
					<a href="${context}/${prevpage}?${query}&page=${page}&sortby=idup&rows=${rows}"><i class="fa fa-arrow-up" aria-hidden="true"></i></a>
					<a href="${context}/${prevpage}?${query}&page=${page}&sortby=iddown&rows=${rows}"><i class="fa fa-arrow-down" aria-hidden="true"></i></a>
				</th>
				<th>
					photo
				</th>
				<th>
					title
					<a href="${context}/${prevpage}?${query}&page=${page}&sortby=titleup&rows=${rows}"><i class="fa fa-arrow-up" aria-hidden="true"></i></a>
					<a href="${context}/${prevpage}?${query}&page=${page}&sortby=titledown&rows=${rows}"><i class="fa fa-arrow-down" aria-hidden="true"></i></a>
				</th>
				<th>
					year
					<a href="${context}/${prevpage}?${query}&page=${page}&sortby=yearup&rows=${rows}"><i class="fa fa-arrow-up" aria-hidden="true"></i></a>
					<a href="${context}/${prevpage}?${query}&page=${page}&sortby=yeardown&rows=${rows}"><i class="fa fa-arrow-down" aria-hidden="true"></i></a>
				</th>
			</tr>
		</thead>			
	</table>
	</div>
	<div style="padding: 15px;"></div>
	<div class="container">
		<c:forEach var="movie" items="${movieList}">
			<div class="row panel panel-default">
				<div class="col-sm-2">
					<a href="${context}/MoviePage?id=${movie.id }"><img src="${movie.banner }" onError="this.src='https://escherdax.files.wordpress.com/2009/12/illustration.jpg';" width=180 height=200></a>
				</div>
				<div class="col-sm-4">
					<div style="display: block;">
						<p style="font-weight: bold; display: inline;">Title:</p>
						<p style="display: inline;"><a href="${context}/MoviePage?id=${movie.id }">${movie.title}</a></p>
					</div>
					<div style="display: block;">
						<p style="font-weight: bold; display: inline;">Year:</p>
						<p style="display: inline;">${movie.year}</p>
					</div>
					<div style="display: block;">
						<p style="font-weight: bold; display: inline;">Genres:</p>	
						<c:forEach var="genre" items="${movie.genres}">
              				<p style="display: inline; margin-right: 10px"><a style="text-decoration: underline;" href="${context}/GenreSearch?id=${genre.id}">${genre.genre}</a></p>
						</c:forEach>				
					</div>
					<div style="display: block;">
						<p style="font-weight: bold; display: inline;">Director:</p>
						<p style="display: inline;">${movie.director}</p>
					</div>
					<div style="display: block;">
						<p style="font-weight: bold; display: inline;">Stars:</p>
						<c:forEach var="star" items="${movie.stars}">
              				<p style="display: inline; margin-right: 10px"><a style="text-decoration: underline;" href="${context}/StarPage?id=${star.id}">${star.first} ${star.last}</a></p>
						</c:forEach>
					</div>
					<div style="display: block; display: inline;">
						<p style="font-weight: bold; display: inline;">ID:</p>
						<p style="display: inline;">${movie.id}</p>
					</div>
				</div>
				<div class="col-sm-12" style="">
					<p style="position: bottom; float: right;"><button type="button" class="btn btn-primary">Add to Cart</button></p>						
					<p style="margin-right: 15px; float: right;">$15.99</p>
				</div>
			</div>
		</c:forEach>
	</div>
	
		
	<div class="page-selector">
		<c:if test="${page > 1 }">
		<div class="prev-page pull-left">
			<a href="${context}/${prevpage}?${query}&page=${page-1 }&sortby=${sortby}"  rel="prev"><i class="fa fa-long-arrow-left fa-5x"></i></a>
			<p>Page</p>
		</div>
		</c:if>
		<c:if test="${fn:length(fullMovieList) > rows*page}">
		<div class="next-page pull-right">
			<a href="${context}/${prevpage}?${query}&page=${page+1 }&sortby=${sortby}&rows=${rows}" rel="next"><i class="fa fa-long-arrow-right fa-5x"></i></a>
			<p>Page</p>
		</div>
		</c:if>
	</div>
</c:when>
<c:otherwise>
	<h1>No Results</h1>
</c:otherwise>
</c:choose>
</div>

</body>
</html>