<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- getAttribute 필요없음. -->
<%-- <%String[] array = (String[])request.getAttribute("array");%> --%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
						
<!-- conroller에서 forward방식(객체 바인딩 setAttribute("array",array))으로 보낸
	 "array"를 items로 받아온 후, 하나씩 꺼내어 출력한다. -->
<c:forEach var="fruit" items="${array}">	

${fruit} <br>

</c:forEach>


</body>
</html>