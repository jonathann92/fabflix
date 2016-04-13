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
		<link href="/filmdb/css/MoviePage.css" rel="stylesheet">
		<c:set var="movie" value="${movieInfo}"/>
		<title>${movie.title}</title>
	</head>
	<body>
		<div class="container">	
		<%@include file="/WEB-INF/includes/navbar.jsp" %>
			<div class="jumbotron">
				<center><img src="${movie.banner}" style="height: 350px" /></center>
			</div>			
			<div class="container" >
				<h2><p>${movie.title} (${movie.year})</p></h2>
				<hr/>
				<div class="info-item">
					<div style="display: inline-block;">
						<h4>Director:</h4>
						<span class="label label-default">"${movie.director}"</span><br>
					</div>					
					<div>
						<span class="label label-default">"${movie.director}"</span><br>
					</div>
				</div>
				
			</div>			
		</div>
		
		
		
		
		<% 
		/*
		<div class="star-row">
			<c:choose>
				<c:when test="${not empty starList}">
					<c:forEach var="star" items="${starList}">
						<c:set var="classSuccess" value="info"/>
						<div class="col-md-4">
							<p>"${star.first}"</p><p>" ${star.last}"</p>
							<img src="${star.photo}" style="width:150px; height:150px"/>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<br>
					<div class="alert alert-info">
						No stars found in movie...
					</div>
				</c:otherwise>
			</c:choose>
		</div>
		*/
		%>
	</body>
</html>