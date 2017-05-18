<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>CRUD műveletek | Bejelentkezés</title>
<jsp:include page="includes/metatag-source.jsp" />
<jsp:include page="includes/css-source.jsp" />
<jsp:include page="includes/javascript-source.jsp" />
</head>
<body style="background-color: #282e3e;padding-top: 70px;">

	<c:choose>
		<c:when test="${!sessionScope.loggedin}">
  				<jsp:include page="pages/loginpage.jsp" />
 			 </c:when>	
			<c:otherwise>
   				<jsp:include page="pages/home.jsp" />
  			</c:otherwise>
	</c:choose>
		
	
</body>
</html>