<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 

<jsp:include page="site/navbar.jsp" />

<div class="container">
<div class="row">
<div class="col-md-12">
<div class="well">
		<c:choose>
			<c:when test="${param.site == 'profile'}">
				<jsp:include page="content/profile.jsp" />
			</c:when>			
			<c:when test="${param.site == 'mycars'}">
				<jsp:include page="content/mycars.jsp" />
			</c:when>
			<c:when test="${param.site == 'allcars'}">
				<jsp:include page="content/allcars.jsp" />
			</c:when>
			<c:otherwise>
				<jsp:include page="content/welcome.jsp" />
			</c:otherwise>
		</c:choose>
		</div>
		</div>
		</div>
</div>

<jsp:include page="site/footer.jsp" />