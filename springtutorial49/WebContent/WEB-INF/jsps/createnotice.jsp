<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css" rel='stylesheet' type="text/css" />
</head>
<body>
<!-- post মেথড হলে url এ parameter দেখা যাবে না  -->
<div class="container">
<form method="post" action="${pageContext.request.contextPath}/docreate">
<table>
<tr> <td>Name :</td><td><input name = "name" type = "text"/></td>   </tr>
<tr> <td>Email :</td><td><input name = "email" type = "text"/></td> 
<tr> <td>Notice :</td><td><textarea rows="10" cols="15" name = "text"></textarea></td> 
<tr> <td>&nbsp;</td><td><input name = "create query" type = "submit"/></td> 
</table>


</form>
</div>
</body>
</html>