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
		<h1>Login</h1>
<span style="color: red;font-weight:bold;left: 33%; position: relative;" id = "err_message"></span>
<form name="login" method="GET" action="Results.jsp" onsubmit="return validate()">
	<h3>Username</h3> <input type="text" name="username"/><br/>
	<h3>Password</h3> <input type="text" name="password"/><br/>
	<input type="submit" value="Login"/>
</form>
	</div>
<div id="bottom"></div>

<script>
function validate(){
	var requeststr = "Login?";
	requeststr += "username=" + document.login.username.value;
	requeststr += "&password="+ document.login.password.value
	
	var xhttp = new XMLHttpRequest();
	xhttp.open("GET", requeststr, false);
	xhttp.send();
	
	if(xhttp.responseText.trim().length > 0){
		document.getElementById("err_message").innerHTML = xhttp.responseText;
		return false;
	}
	return true;
}
	
</script>
</body>
</html>