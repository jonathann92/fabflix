<%@include file="/WEB-INF/includes/jspheader.jsp" %>
<html>
<head>
<%@include file="/WEB-INF/includes/head.jsp" %>
<style>
			.popbox {
				border: 1px solid #4D4F53;
			    display: none;
			    position: absolute;
			    z-index: 99999;
			    width: 400px;
			    height: 400px;
			    padding: 10px;
			    overflow: hidden;
			    background-color: white;
			    /*
			    background: #EEEFEB;
			    color: #000000;
			    margin: 0px;
			    -webkit-box-shadow: 0px 0px 5px 0px rgba(164, 164, 164, 1);
			    box-shadow: 0px 0px 5px 0px rgba(164, 164, 164, 1);
			    */
			}
		</style>
</head>
<body>
<div class="container" >
<%@include file="/WEB-INF/includes/navbar.jsp" %>
<%
	request.setAttribute("fullMovieList", request.getAttribute("fullMovieList"));
%>

	<c:choose>
	<c:when test="${not empty movieList and movieList != null}">
		
	
	<div>
		<div class="text-center">
			<h1>Search Results</h1>
		</div>
	</div>
	<p style="display: inline; font-size: 18px;">Row Count: </p>
	<a href="${context}/${prevpage}?${query}&page=${page}&sortby=${sortby}&rows=10">10</a>
	<a href="${context}/${prevpage}?${query}&page=${page}&sortby=${sortby}&rows=25">25</a>
	<a href="${context}/${prevpage}?${query}&page=${page}&sortby=${sortby}&rows=50">50</a>
	<a href="${context}/${prevpage}?${query}&page=${page}&sortby=${sortby}&rows=100">100</a>
	<div class="results" align="center">
	
	
	
	
		<table>
		<thead>
			<tr>
				<th>
					id
					<a href="${context}/${prevpage}?${query}&page=${page}&sortby=id asc&rows=${rows}"><i class="fa fa-arrow-up" aria-hidden="true"></i></a>
					<a href="${context}/${prevpage}?${query}&page=${page}&sortby=id desc&rows=${rows}"><i class="fa fa-arrow-down" aria-hidden="true"></i></a>
				</th>
				<th>
					photo
				</th>
				<th>
					title
					<a href="${context}/${prevpage}?${query}&page=${page}&sortby=title asc&rows=${rows}"><i class="fa fa-arrow-up" aria-hidden="true"></i></a>
					<a href="${context}/${prevpage}?${query}&page=${page}&sortby=title desc&rows=${rows}"><i class="fa fa-arrow-down" aria-hidden="true"></i></a>
				</th>
				<th>
					year
					<a href="${context}/${prevpage}?${query}&page=${page}&sortby=year asc&rows=${rows}"><i class="fa fa-arrow-up" aria-hidden="true"></i></a>
					<a href="${context}/${prevpage}?${query}&page=${page}&sortby=year desc&rows=${rows}"><i class="fa fa-arrow-down" aria-hidden="true"></i></a>
				</th>
			</tr>
		</thead>			
	</table>
	</div>
	<div style="padding: 15px;"></div>
	<div class="container">
		<c:forEach var="movie" items="${movieList}">
			<div class="row panel panel-default">
				<div class="col-sm-2">
					<a href="${context}/MoviePage?id=${movie.id }"><img src="${movie.banner }" onError="this.src='https://escherdax.files.wordpress.com/2009/12/illustration.jpg';" width=180 height=200></a>
				</div>
				<div class="col-sm-4">
					<div style="display: block;">
						<p style="font-weight: bold; display: inline;">Title:</p>
						<p style="display: inline;"><a href="${context}/MoviePage?id=${movie.id }" class="popper" data-popbox="pop1">${movie.title}</a></p>
					</div>
					<div style="display: block;">
						<p style="font-weight: bold; display: inline;">Year:</p>
						<p style="display: inline;">${movie.year}</p>
					</div>
					<div style="display: block;">
						<p style="font-weight: bold; display: inline;">Genres:</p>	
						<c:forEach var="genre" items="${movie.genres}">
              				<p style="display: inline; margin-right: 10px"><a style="text-decoration: underline;" href="${context}/GenreSearch?id=${genre.id}">${genre.genre}</a></p>
						</c:forEach>				
					</div>
					<div style="display: block;">
						<p style="font-weight: bold; display: inline;">Director:</p>
						<p style="display: inline;">${movie.director}</p>
					</div>
					<div style="display: block;">
						<p style="font-weight: bold; display: inline;">Stars:</p>
						<c:forEach var="star" items="${movie.stars}">
              				<p style="display: inline; margin-right: 10px"><a style="text-decoration: underline;" href="${context}/StarPage?id=${star.id}">${star.first} ${star.last}</a></p>
						</c:forEach>
					</div>
					<div style="display: block; display: inline;">
						<p style="font-weight: bold; display: inline;">ID:</p>
						<p style="display: inline;">${movie.id}</p>
					</div>
				</div>
				<div class="col-sm-12" style="">
					<p style="position: bottom; float: right;"><a href="${context }/AddToCart?id=${movies.id }" class="btn btn-primary">Add to Cart</a></p>						
					<p style="margin-right: 15px; float: right;">$15.99</p>
				</div>
			</div>
		</c:forEach>
	</div>
	
		
	<div class="page-selector">
		<c:if test="${page > 1 }">
		<div class="prev-page pull-left">
			<a href="${context}/${prevpage}?${query}&page=${page-1 }&sortby=${sortby}"  rel="prev"><i class="fa fa-long-arrow-left fa-5x"></i></a>
			<p>Page</p>
		</div>
		</c:if>
		<c:if test="${count > rows * page}">
		<div class="next-page pull-right">
			<a href="${context}/${prevpage}?${query}&page=${page+1 }&sortby=${sortby}&rows=${rows}" rel="next"><i class="fa fa-long-arrow-right fa-5x"></i></a>
			<p>Page</p>
		</div>
		</c:if>
	</div>
</c:when>
<c:otherwise>
	<h1>No Results</h1>
</c:otherwise>
</c:choose>
</div>

<div id="pop1" class="popbox">
</div>

<script type="text/javascript">
		var moveLeft = 0;
		var moveDown = 0;
		$('a.popper').hover(function showBox(e) {
			var href = $(this).attr("href");
			href = href.replace("MoviePage", "SearchPopBox");
			// start ajax
			var ajaxRequest;  // The variable that makes Ajax possible!
			try{
				// Opera 8.0+, Firefox, Safari
				ajaxRequest = new XMLHttpRequest();
			} catch (e){
				// Internet Explorer Browsers
				try{
					ajaxRequest = new ActiveXObject("Msxml2.XMLHTTP");
				} catch (e) {
					try{
						ajaxRequest = new ActiveXObject("Microsoft.XMLHTTP");
					} catch (e){
						// Something went wrong
						alert("Your browser broke!");
						return false;
					}
				}
			}
			// Create a function that will receive data sent from the server
			ajaxRequest.onreadystatechange = function(){
				if(ajaxRequest.readyState == 4){
					document.getElementById('pop1').innerHTML = ajaxRequest.responseText;
				}
			}
			console.log(href);
			ajaxRequest.open("GET", href, true);
			ajaxRequest.send(null);
			
		
			// end ajax 
			
			
		    var target = '#' + ($(this).attr('data-popbox'));
		    

		    $(target).show();
		    moveLeft = $(this).outerWidth();
		    moveDown = ($(target).outerHeight() / 2);
		}, function () {
		    var target = '#' + ($(this).attr('data-popbox'));
		    if (!($("a.popper").hasClass ("show"))) {
		        $(target).hide();
		    }
		});

		$('a.popper').mousemove(function (e) {
		    var target = '#' + ($(this).attr('data-popbox'));
		    

		    leftD = e.pageX + parseInt(moveLeft);
		    maxRight = leftD + $(target).outerWidth();
		    windowLeft = $(window).width() - 40;
		    windowRight = 0;
		    maxLeft = e.pageX - (parseInt(moveLeft) + $(target).outerWidth() + 20);

		    if (maxRight > windowLeft && maxLeft > windowRight) {
		        leftD = maxLeft;
		    }

		    topD = e.pageY - parseInt(moveDown);
		    maxBottom = parseInt(e.pageY + parseInt(moveDown) + 20);
		    windowBottom = parseInt(parseInt($(document).scrollTop()) + parseInt($(window).height()));
		    maxTop = topD;
		    windowTop = parseInt($(document).scrollTop());
		    if (maxBottom > windowBottom) {
		        topD = windowBottom - $(target).outerHeight() - 20;
		    } else if (maxTop < windowTop) {
		        topD = windowTop + 20;
		    }

		    $(target).css('top', topD).css('left', leftD);
		});
		$('a.popper').click(function (e) {
		    var target = '#' + ($(this).attr('data-popbox'));
		    if (!($(this).hasClass("show"))) {
		        $(target).show();
		    }
		    $(this).toggleClass("show");
		});
		</script>


</body>
</html>