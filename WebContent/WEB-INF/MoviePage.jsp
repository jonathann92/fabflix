<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="objects.*, java.util.List, java.util.ArrayList, java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%@include file="/WEB-INF/includes/header.jsp" %>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
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
			<div>
				<h2><p>${movie.title} (${movie.year})</p></h2>
				<hr/>
			</div>
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
								<p><a style="display: inline-block;" href="${genres.genre}">${genres.genre}</a></p>
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