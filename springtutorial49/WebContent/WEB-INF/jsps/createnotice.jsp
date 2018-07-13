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

 <div class="col-md-6 col-md-offset-3">
<form class="form-horizontal"   method="post" action="${pageContext.request.contextPath}/docreate">
<fieldset>

<!-- Form Name -->
<legend>Create Notice</legend>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="name">Name :</label>  
  <div class="col-md-4">
  <input id="name" name="name" type="text" placeholder="Enter your name" class="form-control input-md">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="email">Email :</label>  
  <div class="col-md-4">
  <input id="email" name="email" type="text" placeholder="Enter your email" class="form-control input-md">
    
  </div>
</div>

<!-- Textarea -->
<div class="form-group">
  <label class="col-md-4 control-label" for="text">Notice :</label>
  <div class="col-md-4">                     
    <textarea class="form-control" id="text" name="text"></textarea>
  </div>
</div>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="submit"></label>
  <div class="col-md-4">
    <button id="submit" name="submit" class="btn btn-primary">Create Notice</button>
  </div>
</div>

</fieldset>
</form>
</div>





</div>
</body>
</html>