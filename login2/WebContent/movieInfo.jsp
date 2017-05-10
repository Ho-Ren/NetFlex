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
  <link rel="stylesheet" href="/login2/CSS/movieinfo.css" />
  
  
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
      <li><a href="/login2/servlet/Carthandler">Cart</a></li>
      <li><a href="/login2/servlet/browseItem?param=genre">Genre</a></li>
      <li><a href="/login2/servlet/browseItem?param=title">Title</a></li>
      <li><a href="/login2/index.html">Sign Out</a></li>
    </ul>
    
  </div>
</nav>

<div class="container">
<div class = "row">
<div class ="col-md-5 col-sm-6">
  <h2 class = "bottomMar">${item.title}</h2>
  <img src="${item.pic}" class="img-responsive" width="260" height="310"> 
</div>
<div class ="col-md-7 col-sm-6" id ="firstLine">
  <div class = "line">
  <h4>Movie ID:</h4>
  <p class = "rightSpace"> ${item.id}</p>
  </div>
  
  <div class = "line"> 
  <h4>Genre: </h4>
   <c:forEach items="${genre}" var= "genre">
   <span class=space><span class = "blueBkgr"><a href="/login2/servlet/browseItem?param=genre&genreName=${genre}">${genre}</a></span></span>
  </c:forEach>
  </div>
  <div class = "line">
  <h4>Director:</h4>
  <p class = "rightSpace"> ${item.director}</p>
  </div>
  <div class ="line">
  <h4>Year:</h4>
  <p class = "rightSpace">${item.y}</p>
  </div>
  <div class ="line">
  <h4>Starring</h4>
  <c:forEach items="${star}" var= "star">
   <span class = "rightSpace"><a href ="/login2/servlet/StarPage?id=${star.id}">${star.fullName}</a></span> 
  </c:forEach>
  </div>
  <div class = "line">
   <a href ="${item.trailer}"><button type="button" class="btn btn-default btn-lg">
  <span class="glyphicon glyphicon-film" aria-hidden="true"></span> Trailer
  </button></a>
  </div>
  <hr/>
  
  <div class ="line">
    <form id="login-form" class="rightSpace"
        ACTION="/login2/servlet/Carthandler" METHOD="POST">
          <input type="hidden" name="moviename" value="${item.title}"/>
            <button type="submit" class="btn btn-info btn-sm rightSpace" name="addtocart" >Add to Cart</button>
    </form>
</div>
  
</div>
</div>
  

</div>
   
  
<%--   <div class ="col-md-12 ">
  <div class ="videoHolder">
  <iframe width="560" height="315" src="${item.trailer}" frameborder="0" allowfullscreen></iframe>
 </div>
  </div> --%>
  
  




</body>
</html>



