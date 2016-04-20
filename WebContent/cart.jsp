<%@include file="/WEB-INF/includes/jspheader.jsp" %>
<html>
	<head>
		<%@include file="/WEB-INF/includes/head.jsp" %>
	</head>
	<body>
		<div class="container">
		<%@include file="/WEB-INF/includes/navbar.jsp" %>
		
		
		<h1 class="text-center">
			Shopping Cart
		</h1>
		
		<c:if test="${sessionScope.error != null and not empty error }">
			<h4 class="error-message">${sessionScope.error }</h4>
			<% session.removeAttribute("error"); %>
		</c:if>
		
		<c:choose>
			<c:when test="${cart != null and not empty cart  }">
			 <c:set var="total" value="${0 }" />
				<table align="center">
					<thead>
						<tr>
							<th>Movie Name</th>
							<th>Price</th>
							<th>Quantity</th>
							<th>Total</th>
						</tr>
					</thead>
					<tbody>
							<c:forEach items="${cart}" var="item">
								<tr>
				    				<td>${item.key.title}</td> 
				    				<td>15.99</td>
				    				<td>
			    					    <form action="UpdateCart" role="form" method=GET>
											<input type="hidden" name=update value="${item.key.id }">
					    					<input type="text" name=quantity value="${item.value.value}">
				    					    <button type="submit" class="btn btn-default">Update</button>
			    					    </form>
		    					      	<form action="UpdateCart" role="form" method=GET>
			    					      <input type="hidden" name=remove value="${item.key.id }">
			    					      <button type="submit" class="btn btn-default" >Remove</button>
		    					      	</form>
				    				</td>
				    				<td>${item.value.value * 15.99 }</td>
    							 	<c:set var="total" value="${total + (item.value.value * 15.99) }" />
				    			</tr>
							</c:forEach>
					</tbody>
				</table>
				<div class="checkout " >
					<p style="float: right;">Total: ${total }</p>
					<form action="checkout.jsp" role="form" method=GET>
				      <button type="submit" class="btn btn-default" style="float: right;" >Checkout</button>
			      	</form>
				</div>
				
			</c:when>
			<c:otherwise>
				<h4>empty cart</h4>
			</c:otherwise>
		</c:choose>
		
		</div>
	</body>
</html>