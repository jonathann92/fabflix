<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="objects.*, java.util.*, java.io.*"%>

<style>
/* unvisited link */
.autocomplete-li a:link {
    color: black;
}

ul{
	list-style-type: none; padding:0; margin:0;
}

.autocomplete-li{
	border-bottom: 1px solid; padding: 3px 0px 3px; color: black;
}

}
</style>
  
<ul>
<c:forEach items="${movieList }" var="movie">
<li class="autocomplete-li">
<a href="${context}/MoviePage?id=${movie.id }">${movie.title }</a>
</li>
</c:forEach>
</ul>
