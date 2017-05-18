<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="templates.Message" %>
<% Message msg = (Message) session.getAttribute("message"); %>

<div class="container">
				
<div class="panel panel-primary" style="margin-top:100px;">
<div class="panel-heading">Bejelentekzés</div>
<div class="panel-body">

<form class="form-horizontal" action="bejelentkezes" method="POST">
  <div class="form-group">
    <label for="username" class="col-sm-2 control-label">Felhasználónév</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="username" name="username" placeholder="Felhasználónév">
    </div>
  </div>
  <div class="form-group">
    <label for="password" class="col-sm-2 control-label">Jelszó</label>
    <div class="col-sm-10">
      <input type="password" class="form-control" id="password" name="password" placeholder="Jelszó">
    </div>
  </div>  
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-primary">Bejelentkezés</button>
    </div>
  </div>
  <c:if test="${sessionScope.message != null}">
				<div id="msg" class="<%=msg.getAlert()%>" role="alert" style="display:none;"><%=msg.getMessage()%></div> 
				<% session.removeAttribute("message"); %>
				</c:if>
</form></div>
<div class="panel-footer">Obernay Dániel<span class="pull-right">NeptunKód: <span class="neptunkod">E8QUMV</span></span></div>

</div>

</div>

<script>
$( document ).ready(function()
{
	if( $('#msg').length > 0 )
	{
		$('#msg').fadeIn(400);
		$('#msg').delay(3000).fadeOut(400);
	}
});	
</script>
