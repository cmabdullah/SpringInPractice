<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(document).ready(function() {
		$(j_username).focus();
	});
</script>
<h3>Login with Username and Password</h3>
<div class="col-md-6 col-md-offset-3">
	<form class="form-horizontal"
		action='${pageContext.request.contextPath}/j_spring_security_check'
		method='POST'>
		<fieldset>
			<!-- Form Name -->
			<legend>Login</legend>
			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="j_username">Username
					:</label>
				<div class="col-md-4">
					<input id="j_username" name="j_username" type="text" placeholder=""
						class="form-control input-md">
				</div>
			</div>
			<!-- Password input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="j_password">Password
					:</label>
				<div class="col-md-4">
					<input id="j_password" name="j_password" type="password"
						placeholder="" class="form-control input-md">
				</div>
			</div>
			<div class="alert-danger">
				<c:if test="${param.error != null }">
						incorrect username or password
					</c:if>
			</div>
			<!-- Checkbox input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="j_password">Remember
					me :</label> <input id="_spring_security_remember_me"
					name="_spring_security_remember_me" type="checkbox"
					checked="checked">
			</div>
			<!-- Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="submit"></label>
				<div class="col-md-4">
					<button id="submit" name="submit" class="btn btn-primary">Submit</button>
				</div>
			</div>
		</fieldset>
	</form>
	<p>
		<a href="<c:url value='/newaccount'/>">create new Account</a>
	</p>
</div>