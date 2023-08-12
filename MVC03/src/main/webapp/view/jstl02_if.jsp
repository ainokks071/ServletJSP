<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="cnt" value="10"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!--jstl은 if문만 있음. -->
<c:if test="${cnt% 2==0}">
짝수입니다 
</c:if>
<c:if test="${cnt % 2!=0}">
홀수입니다 
</c:if>





</body>
</html>