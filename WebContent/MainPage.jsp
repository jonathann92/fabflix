<%@include file="/WEB-INF/includes/jspheader.jsp" %>
<html>
	<head>
		<%@include file="/WEB-INF/includes/head.jsp" %>
		<link rel="stylesheet" type="text/css" href="./mainpage.css"/>
		<link rel='stylesheet prefetch' href='http://cdn.foundation5.zurb.com/foundation.css'>
    	<link rel='stylesheet prefetch' href='http://cdn.jsdelivr.net/jquery.slick/1.3.7/slick.css'>
    	<style class="cp-pen-styles">   
	      .content{
	          min-height:200px;
	        }
	        .faq-content{
	          min-height: 500px;
	        }
    	</style>
	</head>
	<body>
		<div class="container">
			<%@include file="/WEB-INF/includes/navbar.jsp" %>
			<c:set var="carousel-size" value="5"/>
			
			<h2 class="titles">Browse</h2>
			<hr/>
			<!-- A B C D E F G H I J K L M N O P Q R S T U V W X Y Z -->
			
			
			<h2 class="titles">Genres</h2> 
			<hr/>			
			<div class="row text-center slick-multislider">
				<c:forEach var="genres" items="${genreList}">
					<div class="large-4 small-12 columns">
	    				<img src="http://placehold.it/700x300" alt="">
						<h5 class="h5-title-margin-top"><a href="${context}/GenreSearch?id=${genres.id}">${genres.genre}</a></h5>  <!-- TODO switch to SEARCH BY GENRE -->
					</div>
				</c:forEach>
			</div>
			
			<h2 class="titles">Random Movies</h2>
      		<hr/>
			<div class="row text-center slick-multislider">
				<c:forEach var="movies" items="${randomList}">
					<div class="large-4 small-12 columns">	
						<div style="min-height: 90px;">					
							<h5 class="h5-title-margin-top"><a href="${context}/MoviePage?id=${movies.id}">${movies.title}</a></h5>
						</div>				
						<a href="${context}/MoviePage?id=${movies.id}"><img style="padding-bottom: 30px; height: 250px; width: 250px;" src="${movies.banner}" alt="Image not found" onError="this.src='http://placehold.it/700x300';" width="700" height="300"/></a>
						<p><button type="button" class="button small">Add to Cart</button></p>
					</div>
				</c:forEach>
			</div>		
			
		</div>
		<script src='http://cdn.foundation5.zurb.com/foundation.js'></script>
		<script src='http://cdn.jsdelivr.net/jquery.slick/1.3.7/slick.min.js'></script>
		<script>
			$(document).foundation();
			var $windowWidth = $(window).width();
			$(window).resize(function () {
			    var $windowWidth = $(window).width();
			    if ($windowWidth > 1 && $windowWidth < 640) {
			        $('.slick-multislider').unslick();
			        $('.slick-multislider').slick({
			            slidesToShow: 1,
			            slidesToScroll: 1,
			            autoplay: true,
			            autoplaySpeed: 2000,
			            dots: true
			        });
			    } else {
			        $('.slick-multislider').unslick();
			        $('.slick-multislider').slick({
			            slidesToShow: 4,
			            slidesToScroll: 3,
			            autoplay: true,
			            autoplaySpeed: 2000,
			            dots: true
			        });
			    }
			});
			if ($windowWidth > 1 && $windowWidth < 640) {
			    $('.slick-multislider').slick({
			        slidesToShow: 1,
			        slidesToScroll: 1,
			        autoplay: true,
			        autoplaySpeed: 2000,
			        dots: true
			    });
			} else {
			    $('.slick-multislider').slick({
			        slidesToShow: 4,
			        slidesToScroll: 3,
			        autoplay: true,
			        autoplaySpeed: 2000,
			        dots: true
			    });
			}
		</script>		
	</body>
</html>