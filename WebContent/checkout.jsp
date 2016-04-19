<%@include file="/WEB-INF/includes/jspheader.jsp" %>
<html>
	<head>
		<%@include file="/WEB-INF/includes/head.jsp" %>
	</head>
	<%
		if(session.getAttribute("user") == null){
			session.setAttribute("refpage", request.getContextPath() + "/checkout.jsp");
			response.sendRedirect(request.getContextPath() + "/LoginPrompt.jsp");
		}
	%>
	<body>
		<div class="container">
		<%@include file="/WEB-INF/includes/navbar.jsp" %>
		
		<h1 class="text-center">Checkout</h1>
		
		
		<form action="Checkout" role="form" method=POST>
	<div class="form-group">
      <label>First Name:</label>
      <input type="text" class="form-control" name=first>
    </div>
    
    <div class="form-group">
      <label>Last Name:</label>
      <input type="text" class="form-control" name=last >
    </div>

    <div class="form-group">
      <label>Card Number:</label>
      <input type="text" class="form-control" name=card >
    </div>

    <div class="form-group">
      <label>Expiration Year:</label>
      <input type="text" class="form-control" name=year >
    </div>

    <div class="form-group">
      <label>Expriation month:</label>
      <input type="text" class="form-control" name=month>
    </div>

    <div class="form-group">
      <label>Expiration Day:</label>
      <input type="text" class="form-control" name=day>
    </div>

    <button type="submit" class="btn btn-default">Submit</button>
  </form>
		
		
		
		</div>
	</body>
</html>