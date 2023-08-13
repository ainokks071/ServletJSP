<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!-- getAttribute 필요없음. -->
<%-- <% MemberVO vo = (MemberVO)request.getAttribute("vo"); %> --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%-- 내부적으로 <%= vo.getName %> 과 같은 효과 ! --%> 
<%-- 내부적으로 <%= vo.getAge %> 과 같은 효과 ! --%> 
<%-- 내부적으로 <%= vo.getPass %> 과 같은 효과 ! --%> 
${vo.name} <br>
${vo.age} <br>
${vo.pass}
 

</body>
</html>