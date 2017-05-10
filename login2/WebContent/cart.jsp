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
  <!--   <div class="col-sm-6 col-md-6 ">
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
      <li><a href="/login2/forTitle.jsp">Title</a></li>
    </ul>
    
  </div>
</nav>
  <div class = "line">
  <a href ="/login2/checkout.jsp" class="rightSpace"><button type="button" class="btn btn-danger btn-sm rightSpace">
  <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span> Check Out
  </button></a>
  </div>
<%@ page import="java.util.*" %>
<div class="container">
<table class="table table-hover movieTable">
    <thead>
    <tr>
      <th>Title</th>
    </thead>
    <tbody>
 <c:forEach items="${movieList}" var= "mList">
    <tr>
    <td>${mList.title}</td>
    <td>${mList.year}</td>
    <td>${mList.director}</td>
    <td>${mList.quantity}</td>
        <td>
    <form id="login-form" class="text-left"
        ACTION="/login2/servlet/Carthandler" METHOD="POST">
            <input type="hidden" name="moviename" value="${mList.title}"/>
                <div class="form-group">
                    <label>Quantity</label> <input type="text" class="form-control"
                        name="quantity" placeholder="Enter a positive integer">
                </div>
            <button type="submit" class="btn btn-info btn-sm rightSpace" name="updatecart" >Update</button>
    </form>
        </td>
    <td>
    <form id="login-form" class="text-left"
        ACTION="/login2/servlet/Carthandler" METHOD="POST">
            <input type="hidden" name="moviename" value="${mList.title}"/>
            <button type="submit" class="btn btn-info btn-sm rightSpace" name="deletefromcart" >Delete from Cart</button>
    </form>
        </td>
    </tr>
    </c:forEach>
    </tbody>
 </table>
 </div>
</body>
</html>