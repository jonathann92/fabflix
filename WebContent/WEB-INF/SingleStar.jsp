<%@include file="/WEB-INF/includes/jspheader.jsp" %>
<html>
<%@include file="/WEB-INF/includes/header.jsp" %>
<body>
<div class="container" >
<%@include file="/WEB-INF/includes/navbar.jsp" %>
<%
Star s = (Star)request.getAttribute("star");
Set<Movie> m = s.getMovies();
%>
<h1>
Star Details:
</h1>
<div class="row">
	<div class="col-sm-2 col-sm-offset-4">
		<img src="${star.photo}">
	</div>
	<div class="col-sm-4">
		<p>Name: ${star.first} ${star.last}</p>
		<p>DOB: ${star.dob} </p>
	</div>
</div>
<h1>Movies</h1>
<div class="row">
<%
for(Movie movie : m){
%>
<div class="col-sm-4">
	<h5><%= movie.getTitle() %>	</h5>
	<img src="<%= movie.getBanner() %>" height="120" width="90" />
</div>
<%
}
%>
</div>
</div> <!--  container -->
</body>
</html>
