<%@include file="/WEB-INF/includes/jspheader.jsp" %>
<html>
<head>
<%@include file="/WEB-INF/includes/head.jsp" %>
<style>
	.titles {
		color: #347BCD;
	}
</style>
</head>
<body>
<div class="container" >
<%@include file="/WEB-INF/includes/navbar.jsp" %>
<c:choose>
<c:when test="${star != null }">

<h1 class="titles">${star.first} ${star.last}</h1>

<div class="row">
	<div>
		<img style="display: block; margin: 0 auto;" src="${star.photo}" onError="this.src='https://escherdax.files.wordpress.com/2009/12/illustration.jpg';" width=120 height=180>
		<p style="display: block; margin: 0 auto; text-align: center; padding-top: 25px; font-size: 24px;">Born</p>	
		<p style="display: block; margin: 0 auto; text-align: center; font-size: 20px;">${star.dob} </p>	
	</div>

</div>
<hr/>
<h1 class="titles">Movies</h1>
<div class="row">
	<c:forEach items="${star.movies}" var="movie">
		<div class="col-xs-6 col-md-6">
			<h4 style="padding-top: 30px;"><a href="${context}/MoviePage?id=${movie.id }">${movie.title}</a></h4>
			<a href="${context}/MoviePage?id=${movie.id }"><img src="${movie.banner }" onError="this.src='https://escherdax.files.wordpress.com/2009/12/illustration.jpg';" width=90 height=120></a>
			<p style="display: block; padding-top: 10px;"><a href="${context }/AddToCart?id=${movies.id }" class="btn btn-primary">Add to Cart</a></p>
		</div>
	</c:forEach>
</div>

</c:when>
<c:otherwise>
 <h1>No Star matching id ${param.id} </h1>
</c:otherwise>


</c:choose>

</div> <!--  container -->
</body>
</html>
