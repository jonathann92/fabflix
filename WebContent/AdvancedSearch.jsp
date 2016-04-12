<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="/WEB-INF/includes/head.jsp" %>
<body>
<div class="container-fluid">
<%@include file="/WEB-INF/includes/navbar.jsp" %>
  <h2>Movie Search</h2>
  <form action="Search" role="form">
    <div class="form-group">
      <label for="email">Email:</label>
      <input type="email" class="form-control" id="email" placeholder="Enter email">
    </div>
    
    <div class="form-group">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control" id="pwd" placeholder="Enter password">
    </div>

    <button type="submit" class="btn btn-default">Submit</button>
  </form>
</div>
</body>
</html>
