<%@include file="/WEB-INF/includes/jspheader.jsp" %>
<html>
	<head>
		<%@include file="/WEB-INF/includes/head.jsp" %>
		<link rel="stylesheet" href="${context}/MoviePage.css" type="text/css">
		<c:set var="movie" value="${movieInfo}"/>
		<title>${movie.title}</title>
	</head>
	<body>
		<div class="container">	
			<%@include file="/WEB-INF/includes/navbar.jsp" %>
			<c:choose>
				<c:when test="${movie != null }">
					
				
			<div class="jumbotron">
				<center><img src="${movie.banner}" style="height: 350px" /></center>
			</div>		
			<div class="row">
				<div class="col-md-6">
					<h2><p>${movie.title} (${movie.year})</p></h2>
				</div>
				<div class="col-md-6">
					<div style="float: right;">
						<p style="margin-right: 10px;">$15.99</p>
						<p><a href="${context }/AddToCart?id=${movie.id }" class="btn btn-primary">Add to Cart</a></p>
					</div>
				</div>
			</div>
			<hr/>
			<div class="row row-centered">
			    <div class="col-sm-4">
			      <p style="display: inline-block; font-weight: bold;">Director:</p>
			      <p style="display: inline-block;"> ${movie.director }</p>
			    </div>
			    <div class="col-sm-4">
			      <p style="display: inline-block; font-weight: bold;">Trailer:</p>
			      <a href="${movie.trailer}">CLICK HERE</a>
			    </div>
			    <div class="col-md-4">
					<p style="font-weight: bold;">Genre:</p>
					<c:choose>
						<c:when test="${not empty genreList}">
							<c:forEach var="genres" items="${genreList}">
								<p><a href="${context}/GenreSearch?id=${genres.id}">${genres.genre}</a></p>
							</c:forEach>
						</c:when>
				 	</c:choose>					
				 </div>
  			</div>	
			<div class="star-list">
				<ul class="list-inline">
					<c:choose>
						<c:when test="${not empty starList}">
							<c:forEach var="star" items="${starList}">
								<li class="star-item">
									<a href="${context}/StarPage?id=${star.id }" style="display: block;">${star.first} ${star.last}</a>
									<a href="${context}/StarPage?id=${star.id }"><img src="${star.photo} alt="Image not found" onError="this.src='https://escherdax.files.wordpress.com/2009/12/illustration.jpg';" width="100" height="150" /></a>
								</li>
							</c:forEach>
						</c:when>
					</c:choose>
				</ul>
			</div>
			
			</c:when>
			<c:otherwise>
				<h1>No Movie matching id ${param.id} </h1>
			</c:otherwise>
			</c:choose>
		</div>			
	</body>
</html>