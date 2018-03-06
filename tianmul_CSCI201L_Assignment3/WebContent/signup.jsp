<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/logsign.css">
<link href="https://fonts.googleapis.com/css?family=Roboto:900" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
	<div id="top">
		<h1><a href="index.html">LibraMate</a></h1>
	</div>
	<div id="container">
	<h1>signup</h1>
	<form name="signup" method="GET" action = "Results.jsp"  onsubmit="return validate();">
		<h3>Username</h3> <input type="text" name="username"/>
		<span style="color: red;font-weight:bold;left: 33%; position: relative;" id = "username_err"></span>
		<span style="color: red;font-weight:bold;left: 33%; position: relative;" id = "username_taken"></span><br>
		<h3>Password</h3> <input type="text" name="password"/>
		<span style="color: red;font-weight:bold;left: 33%; position: relative;" id = "password_err"></span><br>
		<h3>Image URL</h3> <input type="text" name="imageURL"/>
		<span style="color: red;font-weight:bold;left: 33%; position: relative;" id = "image_err"></span><br/>
		<input type="submit">
	</form>
	</div>
	
	<div id="bottom">
	</div>
</body>

<script>
function validate(){
	/* var json = sessionStorage.getItem("users"); */
	var requeststr = "";
	requeststr += "username=" + document.signup.username.value;
	requeststr += "&password="+ document.signup.password.value;
	requeststr += "&imageURL="+ document.signup.imageURL.value;
	/* requeststr += "&users="+ json; */
	/* console.log(requeststr); */
	
	var xhttp = new XMLHttpRequest();
	xhttp.open("POST", "Signup", false);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send(requeststr);
	
	
	if(xhttp.responseText.trim().length > 0){
		var x = xhttp.responseText;
		
		if (x.indexOf("1") != -1){
			document.getElementById("username_taken").innerHTML = "";
			document.getElementById("username_err").innerHTML = "Username cannot be empty!";
		}
		else{
			document.getElementById("username_err").innerHTML = "";
		}
		if (x.indexOf("2") != -1){
			document.getElementById("password_err").innerHTML = "Password cannot be empty!";
		}
		else{
			document.getElementById("password_err").innerHTML = "";
		}
		if (x.indexOf("3") != -1){
			document.getElementById("username_taken").innerHTML = "Username taken!";
			document.getElementById("username_err").innerHTML = "";
		}
		else{
			document.getElementById("username_taken").innerHTML = "";
		}
		if (x.indexOf("4") != -1){
			document.getElementById("image_err").innerHTML = "Image URL cannot be empty!";
		}
		else{
			document.getElementById("image_err").innerHTML = "";
		}
		return false;
	}
	return true;
}
</script>
</html>