<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Advanced Search</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!--   <link rel="stylesheet" href="/login2/CSS/welcome.css" />
 -->
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
   <!--  <div class="col-sm-6 col-md-6 ">
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
	<p class="text">
		<em>Please search by one or more of the following option: </em>
	</p>
	<form id="login-form" class="col-md-6 offset-md-3 "
		ACTION="/login2/servlet/Results" METHOD="GET">
		<div class="main-login-form">
			<div class="login-group">
				<div class="form-group">
					<label>Title</label> <input type="text" class="form-control"
						name="Title" placeholder="Enter Movie Title">
				</div>
				<div class="form-group">
					<label>Year</label> <input type="text" class="form-control"
						name="Year" placeholder="Enter Movie Year">
				</div>
				<div class="form-group">
					<label>Director</label> <input type="text" class="form-control"
						name="Director" placeholder="Enter Movie Director">
				</div>
				<div class="form-group">
					<label>First Name</label> <input type="text" class="form-control"
						name="Firstname" placeholder="Enter Star's First Name">
				</div>
				<div class="form-group">
					<label>Last Name</label> <input type="text" class="form-control"
						name="Lastname" placeholder="Enter Star's Last Name">
				</div>
			</div>
			<button class="btn" type="submit">Search</button>
		</div>
	</form>
</div>
</div>

</body>
</html>