<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="index,follow" name="robots" />
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<meta content="minimum-scale=1.0, width=device-width, maximum-scale=0.6667, user-scalable=no" name="viewport" />
<link href="css/style.css" rel="stylesheet" media="screen" type="text/css" />
<script src="javascript/functions.js" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WPI Suite Mobile - Log In</title>
</head>
<body>

<script type="text/javascript">
function login()
{
	//generate unencoded authentication header
	var authString = document.getElementById("loginusername").value + ":" + document.getElementById("loginpassword").value;
	//Base64 encode the header
	authString = window.btoa(authString);
	//add the word Basic plus a space
	authString = 'Basic ' + authString;
	
	//create new XHR
	var xml = new XMLHttpRequest();
	
	//define behavior for when the response is recieved
	xml.onreadystatechange = function()
	{
		if(xml.readyState == 4)//wait until response is available
		{
			if (xml.statusText === "OK") {
				window.location = "mobilerequirements.jsp";
			} else {
				document.getElementById("loginresponse").innerHTML = "Error";
			}
		}
	};
	
	//setup reuqest to POST to /API/Login
	xml.open('POST','API/login',false);
	//set the request header
	xml.setRequestHeader('Authorization', authString);
	//send the request
	xml.send();             
}
</script>

<div id="topbar">
	<div id="leftnav"></div>
	<div id="title">WPI Suite Mobile</div>
</div>

<div id="content">
		<fieldset>
			<span class="graytitle">Login</span>
			<ul class="pageitem">
				<li class="smallfield"><span class="name">User</span><input type="text" id="loginusername" />
				</li>
				<li class="smallfield"><span class="name">Pass</span><input type="password" id="loginpassword" />
				</li>
			</ul>
			<span class="graytitle">Project</span>
			<ul class="pageitem">
				<li class="select"><select name="d" id="project">
				<option value="1">proj</option>
				<option value="2">Project 2</option>
				<option value="3">Project 3</option>
				<option value="4">Project 4</option>
				<option value="5">Project 5</option>
				<option value="6">Project 6</option>
				</select><span class="arrow"></span> </li>
			</ul>
			<ul class="pageitem">
				<li class="button">
				<input name="Submit input" type="submit" value="Submit input" id="search_button" onclick="login()" /></li>
			</ul>
			<span id="loginresponse" />
		</fieldset>
</div>

<div id="footer">
	<!-- Support iWebKit by sending us traffic; please keep this footer on your page, consider it a thank you for my work :-) -->
	<a class="noeffect" href="http://snippetspace.com">iPhone site powered by iWebKit</a>
</div>

</body>
</html>