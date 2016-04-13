<%@include file="/WEB-INF/includes/jspheader.jsp" %>
<html>
<%@include file="/WEB-INF/includes/header.jsp" %>
<body>
<ul>
<li>
new
${username}
</li>
<li>
${sessionScope.username}
</li>
<li>
${star}
</li>
</ul>
<table>
<tr>
<td><input type="text" value="${requestScope.username}" /></td>
<td><input type="text" value="${sessionScope.username}" /></td>
</tr>
</table>
</body>
</html>