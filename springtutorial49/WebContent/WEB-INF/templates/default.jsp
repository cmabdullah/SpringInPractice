<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- apache tiles taglib -->
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
	
    <title><tiles:getAsString name="title"></tiles:getAsString></title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value='/static/lib/bootstrap/css/bootstrap.css'/>" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<c:url value='/static/css/notice-main.css'/>" rel="stylesheet">

  </head>

  <body>

	<h1>hello from tiles</h1>

	<div>
		<tiles:insertAttribute name="header"></tiles:insertAttribute>
	</div>
	<main role="main" class="container">
		<tiles:insertAttribute name="content"></tiles:insertAttribute>
    </main><!-- /.container -->
	<div>
		<tiles:insertAttribute name="footer"></tiles:insertAttribute>
	</div>
</body>
</html>