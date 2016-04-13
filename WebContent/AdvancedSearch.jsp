<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="/WEB-INF/includes/head.jsp" %>
<body>
<div class="container-fluid">
<%@include file="/WEB-INF/includes/navbar.jsp" %>
  <h2>Movie Search</h2>
  <form action="AdvSearch" role="form" method=POST>

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
      <input type="text" class="form-control" name=first placeholder="Enter Star's first name">
    </div>

    <div class="form-group">
      <label>Star's Last name:</label>
      <input type="text" class="form-control" name=last placeholder="Enter Star's last name">
    </div>

    <button type="submit" class="btn btn-default">Submit</button>
  </form>
</div>
</body>
</html>

