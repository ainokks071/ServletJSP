<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!--for(int i = 1; i <=5; i++)   -->
<!--  var의 원리 : request.setAttribute("i", 1);
		 		 request.setAttribute("i", 2);
				 request.setAttribute("i", 3);
				 request.setAttribute("i", 4);
				 request.setAttribute("i", 5);
				 
EL출력식(딸라기호) 의 원리 : request.getAttribute("i");	 -->

<c:forEach var="i" begin="1" end="5" step="1">

	<font size="${i}">
		야 호 ~ <br>
	</font>

</c:forEach>


</body>
</html>