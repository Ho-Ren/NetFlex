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
    <a class="navbar-brand" href="/login2/welcome.jsp">Home</a>
  </div>

  <div class="collapse navbar-collapse" id="navitem">
    <div class="col-sm-6 col-md-6 ">
     <form class="navbar-form" role="search">
        <div class="input-group">
            <input type="text" class="form-control" placeholder="Search" name="q">
            <div class="input-group-btn">
                <button class="btn btn-default" type="submit">Search</button>
            </div>
        </div>
        </form>
    </div>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="#">About</a></li>
      <li><a href="#">Sign Out</a></li>
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
  	 <h2> Genre</h2>
  	 <a href="/login2/servlet/browseItem?param=genre"> <h4> Search by Genre</h4></a>
  	</div>
  	</div>
  	<div class = "col-md-4 col-sm-6 box">
  	<div>
  	<h2> Title </h2>	
  	 <a href="/login2/servlet/browseItem?param=title"><h4> Search by Title</h4></a>
  	</div>
  	</div>	
  	<div class = "col-md-4 col-sm-6 box">
  	<div>
  	<h2> Search </h2>	
  	 <a href="/login2/Advancedsearch.jsp"><h4> Advanced Search</h4></a>
  	</div>
  	</div>	
  	</div>
  </div>
</body>
</html>