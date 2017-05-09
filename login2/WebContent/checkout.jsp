<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Checkout</title>
</head>
<body>
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

<div class="container">
	<h1 class="text-center">Checkout</h1>
	<p class="text-center">
		<em>Enter credit card information below</em>
	</p>
	<form id="checkout-form" class="text-left"
		ACTION="/login2/servlet/Checkout" METHOD="POST">
		<div class="main-checkout-form">
			<div class="checkout-group">
				<div class="form-group">
					<label>First Name</label> <input type="text" class="form-control"
						name="Firstname" placeholder="">
				</div>
				<div class="form-group">
					<label>Last Name</label> <input type="text" class="form-control"
						name="Lastname" placeholder="">
				</div>
				<div class="form-group">
					<label>Credit card number</label> <input type="text" class="form-control"
						name="Creditcard" placeholder="">
				</div>
				<div class="form-group">
					<label>Expiration date</label> <input type="text" class="form-control"
						name="Expiration" placeholder="YYYY-MM-DD">
				</div>
			</div>
			<button class="btn" type="submit">Checkout</button>
		</div>
	</form>
</div>


</body>
</html>