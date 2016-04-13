<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="objects.*, java.util.*, java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="/WEB-INF/includes/head.jsp" %>
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
	<div class="col-md-2 col-md-offset-4">
		<img src="${star.photo}">
	</div>
	<div class="col-md-4">
		<p>Name: ${star.first} ${star.last}</p>
		<p>DOB: ${star.dob} </p>
	</div>
</div>
<h1>Movies</h1>
<div class="row">
<%
for(Movie movie : m){
%>
<div class="col-md-4">
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
