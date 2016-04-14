<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="/filmdb/movieScrollList.css" type="text/css">

<div class="row">
	<div class="row_inner">
		
		<c:choose>
			<c:when test="${not empty movieList}">
				<c:forEach var="movies" items="${movieList}">
					<div class="tile">
						<div class="tile_media">
							<img class="tile_img" src="${movies.banner}" alt=""/>
						</div>
						<div class="tile_details">
							<div class="tile_title">
								"${movies.title}"
							</div>
						</div>
					</div>
				</c:forEach>
			</c:when>
		</c:choose>
		
	</div>
</div>
	
