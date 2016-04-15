<c:set var="context" value="${pageContext.request.contextPath}" />
<nav class="navbar navbar-default">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${context}">Film DB</a>
          </div>
          <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
              <li ><a href="${context}/">Home</a></li>
              <li><a href="${context}/AdvancedSearch.jsp">Search</a></li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">ALL KINDS OF TESTS <span class="caret"></span></a>
                <ul class="dropdown-menu">
                  <li><a href="${context}/StarPage?id=634005">StarPage Test</a></li>
	              <li><a href="${context}/MoviePage?id=135005">MoviePage Test</a></li>
	              <li><a href="${context}/MoviesByTitle?letter=a">Movies By Character Test</a></li>
	              <li><a href="${context}/GenreSearch?id=872004">Movie List by Genre ID Test</a></li>
	              <li><a href="${context}/GenreSearch?name=adventure">Movie List by Genre Name Test</a></li>
                </ul>
              </li>
              
            </ul>
            <ul class="nav navbar-nav navbar-right">
            
              
              	<c:choose>
              		<c:when test="${sessionScope.user != null }">
              		<li>
              			<a href="${context}/Logout">Logout</a>
              		</li>
              		<li>
              			<a class ="navbar-user">${sessionScope.user.email }</a>
              		</li>
              		
              		</c:when>
              		<c:otherwise>
              		<li>
              			<a href="${context}/LoginPrompt.jsp">Login</a>
              		</li>
              		</c:otherwise>
              	</c:choose>
              
              
              <li><a href="${context}/Cart">Cart</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </nav>