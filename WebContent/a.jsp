<%@include file="/WEB-INF/includes/jspheader.jsp" %>
<html>
	<head>
		<%@include file="/WEB-INF/includes/head.jsp" %>
	</head>
	<body>
		<div class="container">
		<%@include file="/WEB-INF/includes/navbar.jsp" %>
		
		<script language="javascript" type="text/javascript">
<!--
//Browser Support Code
function ajaxFunction(){
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
			document.myForm.autocomplete.value = ajaxRequest.responseText;
			document.getElementById('autocompletebox').innerHTML += ajaxRequest.responseText;
		}
	}
	ajaxRequest.open("GET", "${context}/AjaxTest", true);
	ajaxRequest.send(null);
}

//-->
</script>



<form name='myForm'>
Item Name: <input type='text' onChange="ajaxFunction();" name='username' /> <br />
<div id="autocompletebox">
</div>
</form>
		
		</div>
	</body>
</html>