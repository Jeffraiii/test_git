<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/userSearch.css">
<title>Insert title here</title>
</head>
<body>
	<div id="top">
		<h1><a href="index.html">LibraMate</a></h1>
		<form name="myform" method="GET" action="UserSearch.jsp" >
			<input type="text" name="search" id="search">
			<div id="search-icon-container">
			<a href="Results.jsp">
				<img id="search-icon" src="https://upload.wikimedia.org/wikipedia/commons/7/70/User_icon_BLACK-01.png" />
			</a>
			</div>
		</form>
		<a href="#">
				<img id="profilePic" src="#" style="display: none; border-radius: 50%; width: 50px; position: absolute; top: 1%; right: 1%; border: solid black 3px;" onclick="j"/>
		</a>
	</div>
	<table id="table">
	</table>
</body>

<script>
	<%
		String users = (String) request.getSession().getAttribute("users");
		String user = (String) request.getSession().getAttribute("user");
		String result = (String) request.getSession().getAttribute("userResult");
	%>
	var urlParams = new URLSearchParams(window.location.search);
	var query = urlParams.get("search");
	// sets the value of search and radiobutton to previous entry
	document.getElementById("search").value = query; 
	
 	xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if(this.readyState == 4 && this.status == 200) {
			var jsonResponse = JSON.parse(xhttp.responseText.trim());
			// begin populating table with books
			var table = document.getElementById("table");
			var row = document.createElement("tr"); 
			table.appendChild(row);
			
			for(var i = 0; i < jsonResponse.users.length; i++) {
				var td = document.createElement("td");
				
				//Getting the image
				var img = document.createElement("img");
				//If imageLinks exists.
				if( jsonResponse.users[i].imageURL) {
					img.src=	jsonResponse.users[i].imageURL
				} 
				
				//Getting the Title
				var h1 = document.createElement("h2");
				var t = document.createTextNode("@"+jsonResponse.users[i].username);
				
				//Getting the link to go to the Book.jsp
				var anchor = document.createElement("A");
				anchor.href = "#";
				
				//Getting the author (sometimes there are no authors)
				
				
				
				//Putting it all together
				h1.appendChild(t);
				anchor.appendChild(h1);
				
				//Adding to the cell
				td.appendChild(img);
				td.appendChild(anchor);
	
				//Adding to the row			
				if(i % 4 === 0 && i != 0) {
					row = document.createElement("tr"); //Creating a new row.
					row.appendChild(td);
					table.appendChild(row);
				} else {
					row.appendChild(td);
				}
			}
		}
	};
	

	var url = "SearchUser?";
	var query = document.getElementById("search").value;
	query = query.replace(" ", "+"); 
	var finalUrl = url + "term=" + query;	
	
	xhttp.open("GET", finalUrl, false); 
	xhttp.send();

	if (<%=user%> != null){
		var json = <%=user%>;
		document.getElementById("profilePic").src = json.imageURL;
		document.getElementById("profilePic").style.display = "inline";
	}
	
</script>
</html>