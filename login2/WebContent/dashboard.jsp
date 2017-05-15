<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.io.*" %>
<%@ page isELIgnored ="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%-- <c:set var="root" value="${pageContext.request.contextPath}" />
 --%>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>NetFlex</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="/login2/CSS/welcome.css" />
  
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
    </button>
    <a class="navbar-brand" href="/login2/dashboard.jsp">Board</a>
  </div>

  <div class="collapse navbar-collapse" id="navitem">
    <ul class="nav navbar-nav navbar-right">
      <li><a href="/login2/index.html">NetFlix Login</a></li>
    </ul>
    
  </div>
</nav>
  <div class="jumbotron jumbotron-fluid">
  <div class ="container">
	<h1>Welcome, <strong>${name}</strong></h1>
  </div>
</div>
  <div class = "container-fluid">
  	<div class = "row">
  	<div class = "col-md-4 col-sm-6 box">
  	<div>
  	<h2> Metadata </h2>	
  	 <a href="/login2/servlet/Metadata"><h4> MetaData</h4></a>
  	</div>
  	</div>	
  	<div class = "col-md-4 col-sm-6 box">
  	<div>
  	 <h2>New Star</h2>
  	 <a href="/login2/newStar.jsp"> <h4> Add New Star</h4></a>
  	</div>
  	</div>
  	
  	<div class = "col-md-4 col-sm-6 box">
  	<div>
  	<h2> Update Movie </h2>	
  	 <a href="/login2/addMovie.jsp"><h4> Update Movie</h4></a>
  	</div>
  	</div>	
  	<div class = "col-md-4 col-sm-6 box">
  	<div>
  	<h2> Add New Movie </h2>	
  	 <a href="/login2/newMovie.jsp"><h4> Add New Movie</h4></a>
  	</div>
  	</div>	
  	</div>
  </div>
</body>
</html>