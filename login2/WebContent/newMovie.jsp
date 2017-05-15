<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.io.*" %>
<%@ page isELIgnored ="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>NetFlex</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="/login2/CSS/movieList.css" />
  </head>
  
<body>
<nav class="navbar navbar-default" role="navigation">
  <!-- Brand and toggle get grouped for better mobile display -->
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navitem">
      <span class="sr-only"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="/login2/dashboard.jsp">Board</a>
  </div>

  <div class="collapse navbar-collapse" id="navitem">

    <ul class="nav navbar-nav navbar-right">
 
      <li><a href="/login2/index.html">Sign Out</a></li>
    </ul>
    
  </div>
</nav>

<div class="container">
<c:if test ="${sucess eq 'sucess'}">
<div class="alert alert-success alert-dismissible  show" role="alert">
 <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    <span aria-hidden="true">&times;</span>
  </button>
  <strong>Success!</strong> Star has been added.
</div>
</c:if>
<c:if test ="${sucess eq 'fail'}">
<div class="alert alert-danger alert-dismissible  show" role="alert">
  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    <span aria-hidden="true">&times;</span>
  </button>
  <strong>Input invalid!</strong> Please make sure all required field are entered.
</div>
</c:if>
<p>Please enter the following information to add a new Movie.</p>
<form id="login-form" class="col-md-6 offset-md-3 "
		ACTION="/login2/servlet/newStar" METHOD="POST">
		<input type="hidden" name="category" value="movie">
		<div class="main-login-form">
			<div class="login-group">
				<small><i>If the star has a single name, please enter it as his Last Name and leave First Name empty</i></small>
				<div class="form-group">
					<label>Title *</label> <input type="text" class="form-control"
						name="title" placeholder="Enter title">
				</div>
				<div class="form-group">
					<label>Year *</label> <input type="text" class="form-control"
						name="year" placeholder="Enter Last Name">
				</div>
				<div class="form-group">
					<label>Director *</label> <input type="text" class="form-control"
						name="director" placeholder="Enter DOB in the follow format: YYYY/MM/DD">
				</div>
				<div class="form-group">
					<label>Banner url</label> <input type="text" class="form-control"
						name="banner" placeholder="Enter Banner's url">
				</div>
				<div class="form-group">
					<label>Trailer url</label> <input type="text" class="form-control"
						name="trailer" placeholder="Enter Trailer's url">
				</div>
			</div>
			<button class="btn" type="submit">Add Movie</button>
		</div>
	</form>

</div>
<hr/>
</body>
</html>












