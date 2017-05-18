<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <div class="well well-lg">
 
 <div class="alert alert-info">Az itt szereplő adatok tájékoztató jellegűek és nem módosíthatóak!</div>
 
 <div class="row bg-primary">
 <div class="col-xs-4">Azonosítód</div><div class="col-xs-4"> <c:out value="${sessionScope.userid}" /></div>
 </div>
 <div class="row bg-info">
 <div class="col-xs-4">Vezetékneved</div><div class="col-xs-4"> <c:out value="${sessionScope.lastName}" /></div>
 </div> 
 <div class="row bg-primary">
 <div class="col-xs-4">Keresztneved</div><div class="col-xs-4"> <c:out value="${sessionScope.firstName}" /></div>
 </div> 
 <div class="row bg-info">
 <div class="col-xs-4">Felhasználóneved</div><div class="col-xs-4"> <c:out value="${sessionScope.username}" /></div>
 </div>
 </div>
