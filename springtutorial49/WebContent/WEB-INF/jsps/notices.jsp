<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css" rel='stylesheet' type="text/css" />
</head>
<body>
<!-- 
<c:forEach var="notice" items="${notices}">
	<p><c:out value="${notice}"></c:out></p>
</c:forEach>
 -->
 
<table class="table table-striped">

    <tr>
      <th scope="col">ID</th>
      <th scope="col">Name</th>
      <th scope="col">Email</th>
      <th scope="col">Notice</th>
    </tr>

<c:forEach var="notice" items="${notices}">
    <tr>
      <td><c:out value="${notice.id}"></c:out></td>
      <td><c:out value="${notice.name}"></c:out></td>
      <td><c:out value="${notice.email}"></c:out></td>
      <td><c:out value="${notice.text}"></c:out></td>
    </tr>

</c:forEach>
</table>



</body>

</html>




