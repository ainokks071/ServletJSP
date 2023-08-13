<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!-- getAttribute 필요없음. -> EL태그로 대체 ! -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- items로 받을 때, $기호 빼먹지 말 것. -->
<c:forEach var="vo" items="${list}">

${vo.name} <br>
${vo.pass} <br>
${vo.age}  <br>
		   <br>

</c:forEach> 

</body>
</html>