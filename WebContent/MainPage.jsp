<%@include file="/WEB-INF/includes/jspheader.jsp" %>
<html>
	<head>
		<%@include file="/WEB-INF/includes/head.jsp" %>
		<link rel="stylesheet" type="text/css" href="./mainpage.css"/>
		<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
		
		<!-- link rel='stylesheet' href='http://cdn.foundation5.zurb.com/foundation.css'-->
    	<link rel='stylesheet' href='http://cdn.jsdelivr.net/jquery.slick/1.3.7/slick.css'>
    	<style class="cp-pen-styles">   
	      .content{
	          min-height:200px;
	        }
	        .faq-content{
	          min-height: 500px;
	        }
	        .alphabet {
	        	display: inline-block;
	        	font-size: 24px;
	        }
    	</style>
	</head>
	<body>
		<div class="container">
			<%@include file="/WEB-INF/includes/navbar.jsp" %>
			<c:set var="carousel-size" value="5"/>
			
			<h2 style="padding-top: 20px; font-weight: bold; color: #347BCD">Browse by Title</h2>
			<hr/>
			<div class="container" style="text-align: center;">
				<p class="alphabet"><a href="${context}/MoviesByTitle?letter=0">0</a></p>
				<p class="alphabet"><a href="${context}/MoviesByTitle?letter=1">1</a></p>
				<p class="alphabet"><a href="${context}/MoviesByTitle?letter=2">2</a></p>
				<p class="alphabet"><a href="${context}/MoviesByTitle?letter=3">3</a></p>
				<p class="alphabet"><a href="${context}/MoviesByTitle?letter=4">4</a></p>
				<p class="alphabet"><a href="${context}/MoviesByTitle?letter=5">5</a></p>
				<p class="alphabet"><a href="${context}/MoviesByTitle?letter=6">6</a></p>
				<p class="alphabet"><a href="${context}/MoviesByTitle?letter=7">7</a></p>
				<p class="alphabet"><a href="${context}/MoviesByTitle?letter=8">8</a></p>
				<p class="alphabet"><a href="${context}/MoviesByTitle?letter=9">9</a></p>
				<p class="alphabet"><a href="${context}/MoviesByTitle?letter=a">A</a></p>
				<p class="alphabet"><a href="${context}/MoviesByTitle?letter=b">B</a></p>
				<p class="alphabet"><a href="${context}/MoviesByTitle?letter=c">C</a></p>
				<p class="alphabet"><a href="${context}/MoviesByTitle?letter=d">D</a></p>
				<p class="alphabet"><a href="${context}/MoviesByTitle?letter=e">E</a></p>
				<p class="alphabet"><a href="${context}/MoviesByTitle?letter=f">F</a></p>
				<p class="alphabet"><a href="${context}/MoviesByTitle?letter=g">G</a></p>
				<p class="alphabet"><a href="${context}/MoviesByTitle?letter=h">H</a></p>
				<p class="alphabet"><a href="${context}/MoviesByTitle?letter=i">I</a></p>
				<p class="alphabet"><a href="${context}/MoviesByTitle?letter=j">J</a></p>
				<p class="alphabet"><a href="${context}/MoviesByTitle?letter=k">K</a></p>
				<p class="alphabet"><a href="${context}/MoviesByTitle?letter=l">L</a></p>
				<p class="alphabet"><a href="${context}/MoviesByTitle?letter=m">M</a></p>
				<p class="alphabet"><a href="${context}/MoviesByTitle?letter=n">N</a></p>
				<p class="alphabet"><a href="${context}/MoviesByTitle?letter=o">O</a></p>
				<p class="alphabet"><a href="${context}/MoviesByTitle?letter=p">P</a></p>
				<p class="alphabet"><a href="${context}/MoviesByTitle?letter=q">Q</a></p>
				<p class="alphabet"><a href="${context}/MoviesByTitle?letter=r">R</a></p>
				<p class="alphabet"><a href="${context}/MoviesByTitle?letter=s">S</a></p>
				<p class="alphabet"><a href="${context}/MoviesByTitle?letter=t">T</a></p>
				<p class="alphabet"><a href="${context}/MoviesByTitle?letter=u">U</a></p>
				<p class="alphabet"><a href="${context}/MoviesByTitle?letter=v">V</a></p>
				<p class="alphabet"><a href="${context}/MoviesByTitle?letter=w">W</a></p>
				<p class="alphabet"><a href="${context}/MoviesByTitle?letter=x">X</a></p>
				<p class="alphabet"><a href="${context}/MoviesByTitle?letter=y">Y</a></p>
				<p class="alphabet"><a href="${context}/MoviesByTitle?letter=z">Z</a></p>
			</div>
			<!-- A B C D E F G H I J K L M N O P Q R S T U V W X Y Z -->
			
			
			<h2 class="titles">Genres</h2> 
			<hr/>			
			<div class="row-fluid text-center slick-multislider">
				<c:forEach var="genres" items="${genreList}">
					<div class="col-xs-4 col-md-6">
						<h3><a href="${context}/GenreSearch?id=${genres.id}">${genres.genre}</a></h3>  <!-- TODO switch to SEARCH BY GENRE -->
					</div>
				</c:forEach>
			</div>
			
			<h2 class="titles">Random Movies</h2>
      		<hr/>
			<div class="row-fluid text-center slick-multislider">
				<c:forEach var="movies" items="${randomList}">
					<div class="col-xs-4 col-md-6" style="padding: 40px;">	
						<div style="min-height: 60px;">					
							<h5><a href="${context}/MoviePage?id=${movies.id}">${movies.title}</a></h5>
						</div>				
						<a href="${context}/MoviePage?id=${movies.id}"><img style="padding-bottom: 30px; height: 250px; width: 200px;" src="${movies.banner}" alt="Image not found" onError="this.src='http://placehold.it/700x300';" width="700" height="300"/></a>
						<p>$15.99</p>
						<p><a href="${context }/AddToCart?id=${movies.id }" class="btn btn-primary">Add to Cart</a></p>
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
			            dots: false
			        });
			    } else {
			        $('.slick-multislider').unslick();
			        $('.slick-multislider').slick({
			            slidesToShow: 4,
			            slidesToScroll: 3,
			            pauseOnHover: true, 
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
			        dots: false
			    });
			} else {
			    $('.slick-multislider').slick({
			        slidesToShow: 4,
			        slidesToScroll: 3,
			        pauseOnHover: true,
			        autoplay: true,
			        autoplaySpeed: 2000,
			        dots: true
			    });
			}
		</script>		
	</body>
</html>