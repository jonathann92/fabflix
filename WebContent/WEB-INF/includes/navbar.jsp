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
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Search <span class="caret"></span></a>                
                
                <form class="dropdown-menu" action="AdvSearch" role="form" method=GET style="padding-left: 15px; padding-right: 15px;">
					<div class="form-group" >
				      <label>ID:</label>
				      <input style="width: 500px;" type="text" class="form-control" name=id placeholder="Enter id">
				    </div>
				    
				    <div class="form-group">
				      <label>Title:</label>
				      <input type="text" class="form-control" name=title placeholder="Enter title">
				    </div>
				
				    <div class="form-group">
				      <label>Year:</label>
				      <input type="text" class="form-control" name=year placeholder="Enter year">
				    </div>
				
				    <div class="form-group">
				      <label>Director:</label>
				      <input type="text" class="form-control" name=director placeholder="Enter director">
				    </div>
				
				    <div class="form-group">
				      <label>Star's First Name:</label>
				      <input type="text" class="form-control" name=first placeholder="Star's first name">
				    </div>
				
				    <div class="form-group">
				      <label>Star's Last name:</label>
				      <input type="text" class="form-control" name=last placeholder="Star's last name">
				    </div>
				
				    <input type="submit" style="position: absolute; left: -9999px"/>
			  </form>

              
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">ALL KINDS OF TESTS <span class="caret"></span></a>
                <ul class="dropdown-menu">
                  <li><a href="${context}/StarPage?id=634005">StarPage Test</a></li>
	              <li><a href="${context}/MoviePage?id=135005">MoviePage Test</a></li>
	              <li><a href="${context}/MoviesByTitle?letter=a">Movies By Character Test</a></li>
	              <li><a href="${context}/GenreSearch?id=872004">Movie List by Genre ID Test</a></li>
	              <li><a href="${context}/GenreSearch?name=adventure">Movie List by Genre Name Test</a></li>
	              <li><a href="${context}/AddToCart?id=135005">Add to the cart</a></li>
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
              
              
              <li><a href="${context}/cart.jsp">Cart</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </nav>