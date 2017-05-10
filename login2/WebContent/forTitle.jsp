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
      <li><a href="/login2/index.html">Sign Out</a></li>
    </ul>
    
  </div>
</nav>


<div class="container">
<div class="row">
  <h3>Search by Title</h3>
  <hr/>
  <div class ="category" style="padding: 10px;">
    <div class ="col-md-2 col-sm-2 col-xs-2">
    <a class ="rightSpace" href="/login2/servlet/browseItem?param=title&alpha=a">A</a>   
    </div>   
    <div class ="col-md-2 col-sm-2 col-xs-2">
    <a class ="rightSpace" href="/login2/servlet/browseItem?param=title&alpha=b">B</a>
    </div>   
    <div class ="col-md-2 col-sm-2 col-xs-2">
    <a class ="rightSpace"  href="/login2/servlet/browseItem?param=title&alpha=c">C</a>
    </div>   
    <div class ="col-md-2 col-sm-2 col-xs-2">
    <a class ="rightSpace"  href="/login2/servlet/browseItem?param=title&alpha=d">D</a>
    </div>
    <div class ="col-md-2 col-sm-2 col-xs-2">
    <a class ="rightSpace" href="/login2/servlet/browseItem?param=title&alpha=e">E</a>
    </div>   
    <div class ="col-md-2 col-sm-2 col-xs-2">
    <a class ="rightSpace" href="/login2/servlet/browseItem?param=title&alpha=f">F</a>
    </div>
    <div class ="col-md-2 col-sm-2 col-xs-2">
    <a class ="rightSpace" href="/login2/servlet/browseItem?param=title&alpha=g">G</a>
    </div>   
    <div class ="col-md-2 col-sm-2 col-xs-2">
    <a class ="rightSpace" href="/login2/servlet/browseItem?param=title&alpha=h">H</a>
    </div>
    <div class ="col-md-2 col-sm-2 col-xs-2">
    <a class ="rightSpace" href="/login2/servlet/browseItem?param=title&alpha=i">I</a>
    </div>   
    <div class ="col-md-2 col-sm-2 col-xs-2">
    <a class ="rightSpace" href="/login2/servlet/browseItem?param=title&alpha=j">J</a>
    </div>
    <div class ="col-md-2 col-sm-2 col-xs-2">
    <a class ="rightSpace" href="/login2/servlet/browseItem?param=title&alpha=k">K</a>
    </div>   
    <div class ="col-md-2 col-sm-2 col-xs-2">
    <a href="/login2/servlet/browseItem?param=title&alpha=l">L</a>
    </div>
    <div class ="col-md-2 col-sm-2 col-xs-2">
    <a class ="rightSpace" href="/login2/servlet/browseItem?param=title&alpha=m">M</a>
    </div>   
    <div class ="col-md-2 col-sm-2 col-xs-2">
    <a href="/login2/servlet/browseItem?param=title&alpha=n">N</a>
    </div>
    <div class ="col-md-2 col-sm-2 col-xs-2">
    <a class ="rightSpace" href="/login2/servlet/browseItem?param=title&alpha=o">O</a>
    </div>   
    <div class ="col-md-2 col-sm-2 col-xs-2">
    <a class ="rightSpace" href="/login2/servlet/browseItem?param=title&alpha=p">P</a>
    </div>
    <div class ="col-md-2 col-sm-2 col-xs-2">
    <a class ="rightSpace" href="/login2/servlet/browseItem?param=title&alpha=q">Q</a>
    </div>   
    <div class ="col-md-2 col-sm-2 col-xs-2">
    <a class ="rightSpace" href="/login2/servlet/browseItem?param=title&alpha=r">R</a> 
    </div>
    <div class ="col-md-2 col-sm-2 col-xs-2">  
    <a class ="rightSpace"  href="/login2/servlet/browseItem?param=title&alpha=s">S</a> 
    </div>   
    <div class ="col-md-2 col-sm-2 col-xs-2">
    <a class ="rightSpace"  href="/login2/servlet/browseItem?param=title&alpha=t">T</a> 
    </div>
    <div class ="col-md-2 col-sm-2 col-xs-2">  
    <a  class ="rightSpace" href="/login2/servlet/browseItem?param=title&alpha=l">L</a>
    </div>   
    <div class ="col-md-2 col-sm-2 col-xs-2">  
    <a class ="rightSpace"  href="/login2/servlet/browseItem?param=title&alpha=m">M</a>
    </div>
    <div class ="col-md-2 col-sm-2 col-xs-2">
    <a class ="rightSpace"  href="/login2/servlet/browseItem?param=title&alpha=n">N</a>
    </div>   
    <div class ="col-md-2 col-sm-2 col-xs-2">
    <a class ="rightSpace" href="/login2/servlet/browseItem?param=title&alpha=o">O</a>
    </div>
    <div class ="col-md-2 col-sm-2 col-xs-2">
    <a class ="rightSpace"  href="/login2/servlet/browseItem?param=title&alpha=p">P</a>
    </div>   
    <div class ="col-md-2 col-sm-2 col-xs-2">
    <a class ="rightSpace" href="/login2/servlet/browseItem?param=title&alpha=q">Q</a>
    </div>
    <div class ="col-md-2 col-sm-2 col-xs-2">
    <a class ="rightSpace"  href="/login2/servlet/browseItem?param=title&alpha=r">R</a>
    </div>   
    <div class ="col-md-2 col-sm-2 col-xs-2">
    <a class ="rightSpace" href="/login2/servlet/browseItem?param=title&alpha=s">S</a>
    </div>
    <div class ="col-md-2 col-sm-2 col-xs-2">
    <a class ="rightSpace"  href="/login2/servlet/browseItem?param=title&alpha=t">T</a>
    </div>   
    <div class ="col-md-2 col-sm-2 col-xs-2">
    <a class ="rightSpace" href="/login2/servlet/browseItem?param=title&alpha=u">U</a>
    </div>
    <div class ="col-md-2 col-sm-2 col-xs-2">
    <a class ="rightSpace"  href="/login2/servlet/browseItem?param=title&alpha=v">V</a>
    </div>   
    <div class ="col-md-2 col-sm-2 col-xs-2">
    <a class ="rightSpace"  href="/login2/servlet/browseItem?param=title&alpha=w">W</a>
    </div>
    <div class ="col-md-2 col-sm-2 col-xs-2">
    <a class ="rightSpace" href="/login2/servlet/browseItem?param=title&alpha=x">X</a>
    </div>   
    <div class ="col-md-2 col-sm-2 col-xs-2">
    <a class ="rightSpace" href="/login2/servlet/browseItem?param=title&alpha=y">Y</a>
    </div>
    <div class ="col-md-2 col-sm-2 col-xs-2">
    <a class ="rightSpace" href="/login2/servlet/browseItem?param=title&alpha=z">Z</a>
    </div>
    
  </div>
  </div>
 </div>



</body>
</html>



