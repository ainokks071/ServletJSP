<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.bit.model.*" %>    
    
    <!-- ForwardController에서 넘어온 request객체와 동일!
     그 안에는 vo가 저장되어있음. 객체바인딩으로 추출  -->
     
	<%  MemberVO vo = (MemberVO) request.getAttribute("vo"); %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

Controller에서 forward로 넘어온 데이터 출력하기 <br>
이름 : <%= vo.getName()  %> 입니다. <br>
나이 : <%= vo.getAge()  %> 입니다. <br>
이메일 : <%= vo.getEmail()  %> 입니다. 


<!-- 렌더링된 결과를 클라이언트에게 곧바로 응답하는 것이 아니라,
     먼저 Controller에게 응답하고, 비로소 Controller가 클라이언트에게 응답하므로
     요청 클라이언트의 URL 변경이 없다!
     http://localhost:8080/MVC03/fc.do (O)  
     http://localhost:8080/MVC03/forward.jsp (X)  -->
     
</body>
</html>