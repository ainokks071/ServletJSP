<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- getAttribute 필요없음. -->
<%-- <% ArrayList<String> list = (ArrayList<String>)request.getAttribute("list");%> --%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- conroller에서 forward방식(객체 바인딩 request.setAttribute("list", list))으로 보낸,
"list"를 items로 받아온 후, 하나씩 꺼내어 출력. -->

<c:forEach var="language" items="${list}">	

${language} <br>

</c:forEach>

</body>
</html>