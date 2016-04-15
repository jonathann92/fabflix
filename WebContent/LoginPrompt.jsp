<%@include file="/WEB-INF/includes/jspheader.jsp" %>
<html>
<%@include file="/WEB-INF/includes/header.jsp" %>
<body>
<div class="container">
<%@include file="/WEB-INF/includes/navbar.jsp" %>
<%  
if(session.getAttribute("refpage") == null)
	session.setAttribute("refpage",request.getHeader("Referer"));
%> 
<form action="LoginPage" role="form" method=POST>

    <div class="form-group">
      <label>Username:</label>
      <input type="text" class="form-control" name=username placeholder="Enter username">
    </div>

    <div class="form-group">
      <label>Password:</label>
      <input type="password" class="form-control" name=password placeholder="Enter password">
    </div>
	<input type="hidden" name="from" value="${pageContext.request.requestURI}" />
    <button type="submit" class="btn btn-default">Submit</button>
  </form>
  
  <c:if test="${not empty sessionScope.error}">
   <h4>${error}</h4>
   <c:remove var="error" scope="session" />
  </c:if>
  


</div>
</body>
</html>

