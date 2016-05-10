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
		<div class="container">
		<%@include file="/WEB-INF/includes/navbar.jsp" %>
		
<div id="pop1" class="popbox">
</div>

		
		This is a popbox test. <a href="/fabflix/MoviePage?id=924909" class="popper" data-popbox="pop1">Star wars</a> to see how it works.
		<p>		This is a popbox test. <a href="/fabflix/MoviePage?id=924909" class="popper" data-popbox="pop1">Star wars</a> to see how it works.
		</p>
		<p>		This is a popbox test. <a href="/fabflix/MoviePage?id=924909" class="popper" data-popbox="pop1">Star wars</a> to see how it works.
		</p>
		<p>		This is a popbox test. <a href="/fabflix/MoviePage?id=921" class="popper" data-popbox="pop1">921</a> to see how it works.
		</p>
		<p>		This is a popbox test. <a href="/fabflix/MoviePage?id=924909" class="popper" data-popbox="pop1">Star wars</a> to see how it works.
		</p>
		<p>		This is a popbox test. <a href="/fabflix/MoviePage?id=924909" class="popper" data-popbox="pop1">Star wars</a> to see how it works.
		</p>
		<p>		This is a popbox test. <a href="/fabflix/MoviePage?id=924909" class="popper" data-popbox="pop1">Star wars</a> to see how it works.
		</p>
		<p>		This is a popbox test. <a href="/fabflix/MoviePage?id=924909" class="popper" data-popbox="pop1">Star wars</a> to see how it works.
		</p>
		<p>		This is a popbox test. <a href="/fabflix/MoviePage?id=924909" class="popper" data-popbox="pop1">Star wars</a> to see how it works.
		</p>
		<p>		This is a popbox test. <a href="/fabflix/MoviePage?id=924909" class="popper" data-popbox="pop1">Star wars</a> to see how it works.
		</p>
		<p>		This is a popbox test. <a href="/fabflix/MoviePage?id=924909" class="popper" data-popbox="pop1">Star wars</a> to see how it works.
		</p>
		<p>		This is a popbox test. <a href="/fabflix/MoviePage?id=924909" class="popper" data-popbox="pop1">Star wars</a> to see how it works.
		</p>

		
		</div>
		
		<script type="text/javascript">
		var moveLeft = 0;
		var moveDown = 0;
		$('a.popper').hover(function showBox(e) {
			var href = $(this).attr("href");
			// start ajax
			var ajaxRequest;  // The variable that makes Ajax possible!
			var input = document.getElementById('inputBox').value;
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