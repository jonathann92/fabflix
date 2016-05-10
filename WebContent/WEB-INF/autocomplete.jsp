<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="objects.*, java.util.*, java.io.*"%>
    
<ul style="list-style-type: none; padding:0; margin:0;">
<c:forEach items="${movieList }" var="movie">
<li>
<a href="${context}/MoviePage?id=${movie.id }">${movie.title }</a>
</li>
</c:forEach>
</ul>
