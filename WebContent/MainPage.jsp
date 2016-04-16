<%@include file="/WEB-INF/includes/jspheader.jsp" %>
<html>
	<head>
		<%@include file="/WEB-INF/includes/head.jsp" %>
		<link rel="stylesheet" type="text/css" href="./mainpage.css"/>
	</head>
	<body>
		<div class="container">
			<%@include file="/WEB-INF/includes/navbar.jsp" %>
			<c:set var="carousel-size" value="5"/>
			<h2 class="titles">Genres</h2>
			<hr/>
			
			<!-- try slick..  http://kenwheeler.github.io/slick/ -->
			
			<div id="genreCarousel" class="carousel slide">
				<div class="carousel-inner">
					<div class="item active">
						<div class="row">
							<c:forEach var="i" begin="0" end="3">
								<div class="col-xs-3">
										<img src="$http://placehold.it/250x250 alt="Image not found" onError="this.src='https://escherdax.files.wordpress.com/2009/12/illustration.jpg';" width="100" />
									<div class="carousel-caption">
										<a href="${context}/GenrePage?id=${genreList.get(i).id}"><h3 style="color: red;">${genreList.get(i).genre}</h3></a>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
					
					<c:set var="index" value="3"/>
					
					<c:forEach var="i" begin="0" end="10">
						<div class="item">
							<div class="row">
								<c:set var="index" value="${index+1}"/>
								<c:if test="${not empty genreList.get(index)}">
									<div class="col-xs-3">
										<img src="$http://placehold.it/250x250 alt="Image not found" onError="this.src='https://escherdax.files.wordpress.com/2009/12/illustration.jpg';" width="100" />
										<div class="carousel-caption">											
											<a href="${context}/GenrePage?id=${genreList.get(index).id}"><h3 style="color: red;">${genreList.get(index).genre}</h3></a>
										</div>
									</div>
								</c:if>
								
								<c:set var="index" value="${index+1}"/>
								<c:if test="${not empty genreList.get(index)}">
									<div class="col-xs-3">
										<img src="$http://placehold.it/250x250 alt="Image not found" onError="this.src='https://escherdax.files.wordpress.com/2009/12/illustration.jpg';" width="100" />
										<div class="carousel-caption">											
											<a href="${context}/GenrePage?id=${genreList.get(index).id}"><h3 style="color: red;">${genreList.get(index).genre}</h3></a>
										</div>
									</div>
								</c:if>
								
								<c:set var="index" value="${index+1}"/>
								<c:if test="${not empty genreList.get(index)}">
									<div class="col-xs-3">
										<img src="$http://placehold.it/250x250 alt="Image not found" onError="this.src='https://escherdax.files.wordpress.com/2009/12/illustration.jpg';" width="100" />
										<div class="carousel-caption">											
											<a href="${context}/GenrePage?id=${genreList.get(index).id}"><h3 style="color: red;">${genreList.get(index).genre}</h3></a>
										</div>
									</div>
								</c:if>
								
								<c:set var="index" value="${index+1}"/>
								<c:if test="${not empty genreList.get(index)}">
									<div class="col-xs-3">
										<img src="$http://placehold.it/250x250 alt="Image not found" onError="this.src='https://escherdax.files.wordpress.com/2009/12/illustration.jpg';" width="100" />
										<div class="carousel-caption">											
											<a href="${context}/GenrePage?id=${genreList.get(index).id}"><h3 style="color: red;">${genreList.get(index).genre}</h3></a>
										</div>
									</div>
								</c:if>
								
							</div>
						</div>
					</c:forEach>					
				</div>
				<a class="left carousel-control" href="#genreCarousel" data-slide="prev"><</a>
				<a class="right carousel-control" href="#genreCarousel" data-slide="next">></a>
			</div>
			
			<br>

			<h2 class="titles">Random Movies</h2>
			<hr/>
			
			<div id="randomCarousel" class="carousel slide">
				<div class="carousel-inner">
					<div class="item active">
						<div class="row">
							<c:forEach var="i" begin="0" end="3">
								<div class="col-xs-3">
										<img src="${randomList.get(i).banner} alt="Image not found" onError="this.src='https://escherdax.files.wordpress.com/2009/12/illustration.jpg';" width="100" />
									<div class="carousel-caption">
										<a href="${context}/MoviePage?id=${randomList.get(i).id}"><h3 style="color: red;">${randomList.get(i).title}</h3></a>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
					
					<c:set var="index" value="3"/>
					
					<c:forEach var="i" begin="0" end="6">
						<div class="item">
							<div class="row">
								<c:set var="index" value="${index+1}"/>
								<c:if test="${not empty randomList.get(index)}">
									<div class="col-xs-3">
										<img src="${randomList.get(index).banner} alt="Image not found" onError="this.src='https://escherdax.files.wordpress.com/2009/12/illustration.jpg';" width="100" />
										<div class="carousel-caption">
											<a href="${context}/MoviePage?id=${randomList.get(index).id}"><h3 style="color: red;">${randomList.get(index).title}</h3></a>
										</div>
									</div>
								</c:if>
								
								<c:set var="index" value="${index+1}"/>
								<c:if test="${not empty randomList.get(index)}">
									<div class="col-xs-3">
										<img src="${randomList.get(index).banner} alt="Image not found" onError="this.src='https://escherdax.files.wordpress.com/2009/12/illustration.jpg';" width="100" />
										<div class="carousel-caption">
											<a href="${context}/MoviePage?id=${randomList.get(index).id}"><h3 style="color: red;">${randomList.get(index).title}</h3></a>
										</div>
									</div>
								</c:if>
								
								<c:set var="index" value="${index+1}"/>
								<c:if test="${not empty randomList.get(index)}">
									<div class="col-xs-3">
										<img src="${randomList.get(index).banner} alt="Image not found" onError="this.src='https://escherdax.files.wordpress.com/2009/12/illustration.jpg';" width="100" />
										<div class="carousel-caption">
											<a href="${context}/MoviePage?id=${randomList.get(index).id}"><h3 style="color: red;">${randomList.get(index).title}</h3></a>
										</div>
									</div>
								</c:if>
								
								<c:set var="index" value="${index+1}"/>
								<c:if test="${not empty randomList.get(index)}">
									<div class="col-xs-3">
										<img src="${randomList.get(index).banner} alt="Image not found" onError="this.src='https://escherdax.files.wordpress.com/2009/12/illustration.jpg';" width="100" />
										<div class="carousel-caption">
											<a href="${context}/MoviePage?id=${randomList.get(index).id}"><h3 style="color: red;">${randomList.get(index).title}</h3></a>
										</div>
									</div>
								</c:if>
							</div>
						</div>
					</c:forEach>
				</div>
				<a class="left carousel-control" href="#randomCarousel" data-slide="prev"><</a>
				<a class="right carousel-control" href="#randomCarousel" data-slide="next">></a>
			</div>
			
			
			
			
		</div>
	</body>
</html>