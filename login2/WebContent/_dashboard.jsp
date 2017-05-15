<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Admin Login</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src='https://www.google.com/recaptcha/api.js'></script>

	</head>
	<body>
<nav class="navbar navbar-default">
<div class="container">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target="#myNavbar">
			<span class="icon-bar"></span> <span class="icon-bar"></span> <span
				class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="#">NetFlex</a>
	</div>
	<div class="collapse navbar-collapse" id="myNavbar">
		<!-- <ul class="nav navbar-nav navbar-right">
			<li><a href="#">About</a></li>
			<li><a href="#">Billing</a></li>
			<li><a href="#">Sign In</a></li>
		</ul> -->
	</div>
</div>
</nav>


<div class="container">
	<h3 class="text-center">Sign In</h3>
	<p class="text-center">
		<em>classta@email.edu</em>
	</p>
	<form id="login-form" class="text-left"
		ACTION="/login2/servlet/AdminLogin" METHOD="POST">
		<div class="main-login-form">
			<div class="login-group">
				<div class="form-group">
					<label>Email</label> <input type="text" class="form-control"
						name="email" placeholder="username">
				</div>
				<div class="form-group">
					<label>Password</label> <input type="password" class="form-control"
						name="pw" placeholder="password">
				</div>
			</div>
			<button class="btn" type="submit">Submit</button>
		</div>
		
		<!-- Please change data-sitekey -->
		<div class="g-recaptcha" data-sitekey="6Lf0OiEUAAAAACApcyMzFLdM9iZ-PZ4km_KY23tS" style ="margin-top: 10px;"></div>
		
	</form>
	
</div>

</body>
</html>