<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="objects.*, java.util.*, java.io.*"%>

<style>
.autocomplete-li{
	 padding: 3px 0px 3px; color: black;
}

.autocomplete-div{
border-bottom: 1px solid;
display: list-item !important;
}


a.autocomplete{
	padding: 5px 7px 5px 7px;
	display: list-item;
	color: black;
}

div.autocomplete-div:nth-child(odd){
	background-color: #f0f0f0;
}

div.autocomplete-div:nth-child(even){
	background-color: #e0e0e0;
	
}

</style>
  

<c:forEach items="${movieList }" var="movie">

	<div  class="autocomplete-div hvr-reveal">
		<a href="${context}/MoviePage?id=${movie.id }" class="autocomplete">${movie.title }</a>
	</div>


</c:forEach>

