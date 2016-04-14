<nav class="navbar navbar-default">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/filmdb">Film DB</a>
          </div>
          <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
              <li ><a href="/filmdb/">Home</a></li>
              <li><a href="/filmdb/AdvancedSearch.jsp">Search</a></li>
              <li><a href="/filmdb/StarPage">StarPage Test</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
            
              
              	<c:choose>
              		<c:when test="${sessionScope.user != null }">
              		<li>
              			<a href="/filmdb/Logout">Logout</a>
              		</li>
              		<li>
              			<a class ="navbar-user">${sessionScope.user.email }</a>
              		</li>
              		
              		</c:when>
              		<c:otherwise>
              		<li>
              			<a href="/filmdb/LoginPrompt.jsp">Login</a>
              		</li>
              		</c:otherwise>
              	</c:choose>
              
              
              <li><a href="/filmdb/Cart">Cart</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </nav>