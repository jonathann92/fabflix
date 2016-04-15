<%@include file="/WEB-INF/includes/jspheader.jsp" %>
<html>
<head>
<%@include file="/WEB-INF/includes/head.jsp" %>
</head>
<body>
<div class="container">
<%@include file="/WEB-INF/includes/navbar.jsp" %>
  <h2>Movie Search</h2>
  <form action="AdvSearch" role="form" method=GET>
	
	<div class="form-group">
      <label>ID:</label>
      <input type="text" class="form-control" name=id placeholder="Enter id" value="${id }">
    </div>
    
    <div class="form-group">
      <label>Title:</label>
      <input type="text" class="form-control" name=title placeholder="Enter title" value="${title }">
    </div>

    <div class="form-group">
      <label>Year:</label>
      <input type="text" class="form-control" name=year placeholder="Enter year" value="${year }">
    </div>

    <div class="form-group">
      <label>Director:</label>
      <input type="text" class="form-control" name=director placeholder="Enter director" value="${director }">
    </div>

    <div class="form-group">
      <label>Star's First Name:</label>
      <input type="text" class="form-control" name=first placeholder="Enter Star's first name" value="${first }">
    </div>

    <div class="form-group">
      <label>Star's Last name:</label>
      <input type="text" class="form-control" name=last placeholder="Enter Star's last name" value="${last }">
    </div>

    <button type="submit" class="btn btn-default">Submit</button>
  </form>
  <h4 class="error-message">${error }</h4>
</div>
</body>
</html>

