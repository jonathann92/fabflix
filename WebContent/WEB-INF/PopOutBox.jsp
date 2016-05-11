<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="objects.*, java.util.*, java.io.*"%>
<img src="${movie.banner}" style="height: 120px; width:90px;" />
<h1>${movie.title }</h1>
<h2>${movie.year }</h2>
<h2>Starring: </h2>
<c:choose>
	<c:when test="${not empty stars}">
		<c:forEach var="star" items="${stars}">
			<div style="float:left; padding-left: 15px; padding-right: 15px;">
				<p style="display: block;">${star.first} ${star.last}</p>
				<img src="${star.photo} alt="Image not found" onError="this.src='https://escherdax.files.wordpress.com/2009/12/illustration.jpg';" width="100" height="150" />
			</div>
		</c:forEach>
	</c:when>
	
</c:choose>









