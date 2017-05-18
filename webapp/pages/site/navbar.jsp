<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <c:set var="page" value="${pageContext.request.contextPath}"/>
 
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Car Manager</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="<c:if test="${param.site == null}"><c:out value="active" /></c:if>">
          	<a href="<c:out value="${page}" /> ">Welcome</a>
         </li>         
         <li class="<c:if test="${param.site == 'profile'}"><c:out value="active" /></c:if>">
          	<a href="?site=profile">Profil</a>
         </li>
         <li class="<c:if test="${param.site == 'mycars'}"><c:out value="active" /></c:if>">
          	<a href="?site=mycars">Saját autóim</a>
         </li>
         <li class="<c:if test="${param.site == 'allcars'}"><c:out value="active" /></c:if>">
          	<a href="?site=allcars">Összes autó</a>
         </li>        
      </ul>
      
      
      
      
      
      
      <form class="navbar-form navbar-right">        
        <a href="kijelentkezes"  class="btn btn-danger" role="button">Kijelentkezés</a>
      </form>      
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>