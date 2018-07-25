<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<a href="${pageContext.request.contextPath}/notices">Show Current notices</a><br>
<a href="${pageContext.request.contextPath}/createnotice">Create notices</a>
<sec:authorize access="!isAuthenticated()">
	<a href="<c:url value='/login'/>">Log In</a>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
	<a href="<c:url value='/j_spring_security_logout'/>">Log Out</a>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_ADMIN')">
	<a href="<c:url value='/admin'/>">Admin Page</a>
</sec:authorize>