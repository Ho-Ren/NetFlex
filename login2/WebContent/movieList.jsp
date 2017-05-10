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
    <a class="navbar-brand" href="/login2/welcome.jsp">Home</a>
  </div>

  <div class="collapse navbar-collapse" id="navitem">
 <!--    <div class="col-sm-6 col-md-6 ">
     <form class="navbar-form" role="search" action="/login2/servlet/Results" method="post">
        <div class="input-group">
            <input type="text" class="form-control" placeholder="Search" name="Title" placeholder="Enter Movie Title">
            <div class="input-group-btn">
                <button class="btn btn-default" type="submit">Search</button>
            </div>
        </div>
        </form>
    </div> -->
    <ul class="nav navbar-nav navbar-right">
      <li><a href="/login2/servlet/Carthandler">Cart</a></li>
      <li><a href="/login2/servlet/browseItem?param=genre">Genre</a></li>
      <li><a href="/login2/forTitle.jsp">Title</a></li>
      <li><a href="/login2/index.html">Sign Out</a></li>
    </ul>
    
  </div>
</nav>

<div class="container">
<form class="form-inline" style ="margin: 10px;" action="/login2/servlet/Results?Title=${Title}&Year=${Year}&Director=${Director}&Firstname=${Firstname}&Lastname=${Firstname}" method="post" >
   <label class ="text-right" style ="width: 120px;">Sort By:</label>
    <select class="form-control" name="sort" >
        <option value="title">Title</option>
        <option value="year">Year</option>
    </select>
    <label class ="text-right" style ="width: 120px;">Order by:</label>
    <select class="form-control" name="order" >
        <option value="asc">Ascending</option>
        <option value="desc">Descending</option>
    </select>
    
    <label  class ="text-right" style ="width: 120px;">Item per page:</label>
    <select class="form-control" name="perPage" >
        <option value="10">10</option>
        <option value="15">15</option>
        <option value="20">20</option>
        <option value="25">25</option>
    </select>
    <input class="btn btn-default" type="submit"/>
</form>

</div>
<hr/>
<c:forEach items="${movieList}" var="mlist">
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
            <span class="space"><span class= "blueBkgr">${genre}</span></span>
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
    <form id="login-form" 
        ACTION="/login2/servlet/Carthandler" METHOD="POST">
          <input type="hidden" name="moviename" value="${mlist.title}"/>
            <button type="submit" class="btn btn-info btn-sm rightSpace" name="addtocart" ><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span> Add to Cart</button>
    </form>
  </div>
 </div>
 </div>
</c:forEach>

<!-- Pagination -->
<div class ="container">
<ul class="pagination">
<c:forEach begin="1" end="${pageNum}" varStatus="loop">
  <li><a href="/login2/servlet/Results?Title=${Title}&Year=${Year}&Director=${Director}&Firstname=${Firstname}&Lastname=${Firstname}&page=${loop.count}&sort=${sort}&perPage=${perPage}&order=${order}">${loop.count}</a></li>
</c:forEach>
</ul>
</div>
</body>
</html>






































<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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
      <link rel="stylesheet" href="../CSS/movieList.css" />
  
  
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
    <li><a href="/login2/servlet/Carthandler">Cart</a></li>
      <li><a href="#">About</a></li>
      <li><a href="/login2/index.html">Sign Out</a></li>
    </ul>
    
  </div>
</nav>
<div class="container">
<table class="table table-hover movieTable">
	<thead>
	<tr>
      <th>Title</th>
	</thead>
	<tbody>
 <c:forEach items="${movieList}" var= "mList">
  	<tr>
  	<td>${mList}</td>
  	</tr>
  	</c:forEach>
  	</tbody>
 </table>
 </div>
</body>
</html> --%>