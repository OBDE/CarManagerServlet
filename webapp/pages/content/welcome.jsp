<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<div class="alert alert-success">
	Üdvözlünk köreinkben kedves <strong><c:out value="${sessionScope.lastName}" /> <c:out value="${sessionScope.firstName}" /></strong>!
	</div>
	Kérlek a fenti menüből válassz! :-)	
