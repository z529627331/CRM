<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<c:set var="ctp" value="${pageContext.request.contextPath }"></c:set>
<link href="${ctp}/static/css/styles.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctp }/static/jquery/jquery-1.9.1.min.js"></script>

<c:if test="${message != null }">
	<script type="text/javascript">
		alert("${message}"); 
	</script>
</c:if>