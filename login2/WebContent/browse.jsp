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
      <li><a href="/login2/servlet/browseItem?param=genre">Genre</a></li>
      <li><a href="/login2/servlet/browseItem?param=title">Title</a></li>
      <li><a href="#">Sign Out</a></li>
    </ul>
    
  </div>
</nav>

<div class="container">
<div class="row">
<form action="/login2/servlet/browseItem?param=${value}&genreName=${genreName}&perPage=${perPage}&order=${order}" method="post" >
   <label>Sort By:</label>
    <select name="sort" >
        <option value="title">Title</option>
        <option value="year">Year</option>
    </select>
    <input type="submit" />
</form>
<form action="/login2/servlet/browseItem?param=${value}&genreName=${genreName}&sort=${sort}&perPage=${perPage}" method="post" >
   <label>Order by:</label>
    <select name="order" >
        <option value="asc">Ascending</option>
        <option value="desc">Descending</option>
    </select>
    <input type="submit" />
</form> 
<form action="/login2/servlet/browseItem?param=${value}&genreName=${genreName}&sort=${sort}&order=${order}" method="post" >
   <label>Item per page:</label>
    <select name="perPage" >
        <option value="10">10</option>
        <option value="15">15</option>
        <option value="20">20</option>
        <option value="25">25</option>
    </select>
    <input type="submit" />
</form> 
</div>
<c:forEach items="${list2}" var="mlist">
<div class="container">
<div class="row">

  <!-- Movie Image -->
  <div class ="col-md-3 col-sm-4 col-xs-5">
  <div class ="imgHolder">
    <img src="${mlist.pic}" class="img-responsive" alt="No Image Available" width="120" height="130"> 
  </div>
  </div>
 
  <!-- List Title, Director and Year -->
  <div class ="col-md-9 col-sm-8 col-xs-7">
    <a href="/login2/servlet/MoviePage?id=${mlist.id}"><h3>${mlist.title}</h3></a>
    <h4>Director: ${mlist.director} </h4>
    <h4>Year: ${mlist.y}</h4>
    <h4>Genres: 
        <span><c:forEach items="${mlist.genreList}" var="genre">
            <span class="space"><span class=blueBkgr>${genre}</span></span>
        </c:forEach></span>
    </h4>
    <h4>Stars: 
    <span>|
        <c:forEach items = "${mlist.starList}" var="star">
          <span><a href= "/login2/servlet/StarPage?id=${star.id}"> ${star.fullName} </a>|</span>
        </c:forEach>
    </span></h4>
  </div>
  
  <!-- Shopping Cart Button -->
  <div class = "col-md-8">
    <a href ="#">
      <button type="button" class="btn btn-info btn-sm">
        <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span> Add To Cart
      </button>
    </a>
  </div>
 </div>
 </div>
</c:forEach>
<!-- Pagination -->
<div class ="container">
<ul class="pagination">
	<c:if test="${param.page gt 1}">
		<li><a href='/login2/servlet/browseItem?param=${value}&page=${param.page-1}&sort=${sort}&genreName=${genreName}'>Previous</a></li>
	</c:if>
	
	<c:forEach begin="1" end="${pageNum}" varStatus="loop">
		<c:if test="${pageNum gt 1}">
	    	<li><a href="/login2/servlet/browseItem?param=${value}&page=${loop.count}&sort=${sort}&genreName=${genreName}">${loop.count}</a></li>
	   	</c:if>
	</c:forEach>
	
	<c:if test="${param.page lt pageNum}">
		<li><a href="/login2/servlet/browseItem?param=${value}&page=${param.page+1}&sort=${sort}&genreName=${genreName}">Next</a></li>
	</c:if>
</ul>
</div>
</body>
</html>
