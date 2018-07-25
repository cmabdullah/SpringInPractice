<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!-- post মেথড হলে url এ parameter দেখা যাবে না  -->
<div class="col-md-6 col-md-offset-3">
	<sf:form class="form-horizontal" method="post"
		action="${pageContext.request.contextPath}/docreate"
		commandName="notice">
		<fieldset>
			<!-- Form Name -->
			<legend>Create Notice</legend>
			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="name">Name :</label>
				<div class="col-md-4">
					<!-- Path must be equal to input name -->
					<sf:input id="name" path="name" name="name" type="text"
						placeholder="Enter your name" class="form-control input-md" />
					<!-- Show error message into view -->
					<sf:errors path="name" cssClass="alert-danger"></sf:errors>
				</div>
			</div>
			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="email">Email :</label>
				<div class="col-md-4">
					<sf:input id="email" name="email" path="email" type="text"
						placeholder="Enter your email" class="form-control input-md" />
					<!-- Show error message into view -->
					<sf:errors path="email" cssClass="alert-danger"></sf:errors>
				</div>
			</div>
			<!-- Textarea -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="text">Notice :</label>
				<div class="col-md-4">
					<sf:textarea class="form-control" path="text" id="text" name="text"></sf:textarea>
					<!-- Show error message into view -->
					<sf:errors path="text" cssClass="alert-danger"></sf:errors>
				</div>
			</div>
			<!-- Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="submit"></label>
				<div class="col-md-4">
					<button id="submit" name="submit" class="btn btn-primary">Create
						Notice</button>
				</div>
			</div>
		</fieldset>
	</sf:form>
</div>