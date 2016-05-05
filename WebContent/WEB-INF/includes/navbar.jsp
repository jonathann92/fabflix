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
			<form class="navbar-form navbar-left" role="search" id="frm1">
			  <div class="form-group">
			    <input type="text" class="form-control" placeholder="Search" name="search">
			  </div>
			  <fieldset class="form-group" style="min-height: 1px;">
				<select class="form-control" id="searchOption" name="searchFilter">
 					 <option value="title">Movie Title</option>
					 <option value="first">Star's First</option>
					 <option value="last">Star's Last</option>
					</select>
				</fieldset>
			  <button id="searchBtn" type="submit" class="btn btn-default" onclick="return Redirect();">Submit</button>
			  
			  <script type="text/javascript">

				function Redirect() {
					var filter = document.getElementById("searchOption").value;
				  	var frm1 = document.getElementById("frm1").search.value;
					switch(filter) {
					case "title":
						window.location = "./AdvSearch?id=&title=" + frm1 + "&year=&director=&first=&last=";
						break;
					case "first":
						window.location = "./AdvSearch?id=&title=&year=&director=&first=" + frm1 + "&last=";
						break;
					case "last":
						window.location = context + "./AdvSearch?id=&title=&year=&director=&first=&last=" + frm1;
						break;
					}
					return false;
				}
			  </script>
			  
		
	  		
			</form>
			<ul class="nav navbar-nav">
				<li>
					<a href="${context}/search.jsp" style="text-decoration: underline;">Advanced Search</a>
				</li>    
			</ul>
	        <ul class="nav navbar-nav navbar-right">           
	         	<c:choose>
              		<c:when test="${sessionScope.user != null }">
              		<li>
              			<a href="${context}/Logout">Logout</a>
              		</li>
              		<li>
              			<a class ="navbar-user">${sessionScope.user.email}</a>
              		</li>
              		
              		</c:when>
              		<c:otherwise>
              		<li>
              			<a href="${context}/EmployeePage">Employee Login</a>
              		</li>
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