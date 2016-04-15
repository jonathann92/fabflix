<%@include file="/WEB-INF/includes/jspheader.jsp" %>
<html>
<head>
<%@include file="/WEB-INF/includes/head.jsp" %>
</head>
<body>
<div class="container" >
<%@include file="/WEB-INF/includes/navbar.jsp" %>
<c:choose>
<c:when test="${star != null }">

<h1>Star Details:</h1>

<div class="row">
	<div class="col-xs-3 col-sm-offset-4">
		<img src="${star.photo}" onError="this.src='https://escherdax.files.wordpress.com/2009/12/illustration.jpg';" width=90 height=120>
	</div>
	<div class="col-xs-4 col-sm-offset-0">
		<p>Name: ${star.first} ${star.last}</p>
		<p>DOB: ${star.dob} </p>
	</div>
</div>
<h1>Movies</h1>
<div class="row">
	<c:forEach items="${star.movies}" var="movie">
		<div class="col-xs-6 col-sm-4">
			<a href="${context}/MoviePage?id=${movie.id }"><h5>${movie.title}</h5></a>
			<a href="${context}/MoviePage?id=${movie.id }"><img src="${movie.banner }" onError="this.src='https://escherdax.files.wordpress.com/2009/12/illustration.jpg';" width=90 height=120></a>
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
