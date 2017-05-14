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
    <a class="navbar-brand" href="/login2/dashboard.jsp">Board</a>
  </div>

  <div class="collapse navbar-collapse" id="navitem">
    <ul class="nav navbar-nav navbar-right">

      <li><a href="/login2/index.html">Sign Out</a></li>
    </ul>
    
  </div>
</nav>

<div class="container">
<div class="row">
<c:forEach var="meta" items="${meta}">
<div class ="col-md-6 col-sm-6 col-xs-12">
<div class="panel panel-default">
  <div class="panel-heading"><h3>${meta.key}</h3></div>
  <div class="panel-body"><table class="table table-hover">
    <thead>
      <tr>
        <th>Column Name</th>
        <th>Column Type</th>
      </tr>
    </thead>
    <tbody>
  <c:forEach var="value" items="${meta.value}">
    <tr>
    <td><div>${value.x}</div></td>
    <td><div>${value.y}</div></td>
    </tr>
  </c:forEach>
  </tbody>
  </table>
  </div>
    </div>
    </div>
</c:forEach>
</div>
 </div>



</body>
</html>



