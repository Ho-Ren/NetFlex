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
    </button>
    <a class="navbar-brand" href="/login2/welcome.jsp">Home</a>
  </div>

  <div class="collapse navbar-collapse" id="navitem">
<!--     <div class="col-sm-6 col-md-6 ">
     <form class="navbar-form" role="search">
        <div class="input-group">
            <input type="text" class="form-control" placeholder="Search" name="q">
            <div class="input-group-btn">
                <button class="btn btn-default" type="submit">Search</button>
            </div>
        </div>
        </form>
    </div> -->
    <ul class="nav navbar-nav navbar-right">
      <li><a href="/login2/servlet/Carthandler?&addToCart=null">Cart</a></li>
      <li><a href="/login2/servlet/browseItem?param=genre">Genre</a></li>
      <li><a href="/login2/servlet/browseItem?param=title">Title</a></li>
      <li><a href="/login2/index.html">Sign Out</a></li>
    </ul>
    
  </div>
</nav>

<div class="container">
<div class = "row">
<div class ="col-md-5 col-sm-6">
  <h2 class = "bottomMar">${star.firstName} ${star.lastName}</h2>
  <img src="${star.photo}" class="img-responsive" width="260" height="310"> 
</div>
<div class ="col-md-7 col-sm-6" id ="firstLine">
   <div class = "line">
  <h4>Star ID:</h4>
  <p class = "rightSpace"> ${star.id}</p>
  </div>
  <div class = "line">
  <h4>DOB:</h4>
  <p class = "rightSpace"> ${star.DOB}</p>
  </div>

  <div class ="line">
  <h4>Works</h4>
  <c:forEach items="${movieList}" var= "list">
   <span class = "rightSpace"><a href ="/login2/servlet/MoviePage?id=${list.id}">${list.title}</a></span> 
     <hr/>
  </c:forEach>
  </div>
  


</div>
   
  
<%--   <div class ="col-md-12 ">
  <div class ="videoHolder">
  <iframe width="560" height="315" src="${item.trailer}" frameborder="0" allowfullscreen></iframe>
 </div>
  </div> --%>
  
  
 </div>

 </div>



</body>
</html>



