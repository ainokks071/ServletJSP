<%-- <%@ 지시자 %> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- servlet-api.jar BuildPath에 라이브러리 추가해줄 것.(웹용 API에 의해 인식) -->    
<%@ page import="java.util.Date" %>
<!-- 컴파일된 .class파일의 저장위치 변경 : WEB/webapp/WEB-INF/classes -->
<%-- <% 스크립틀릿 %> : 자바코드영역 --%>    
<% Date d = new Date(); %>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
<h1>JSP의 특징과 웹의 작동원리</h1>
- JSP(Java Server Page) = Java코드영역(프로그래밍, 비즈니스로직) + HTML영역(View, 프리젠테이션로직) <br>
- JSP는 MVC의 View 담당 = Presentation Logic(주 역할)<br>
- JSP는 서버에서 동작되는 동적인 페이지<br>
- 톰캣 서버가 "지시자, 스크립틀릿, 출력식"을 렌더링(해석)하여 그 결과를 클라이언트의 요청에 맞게 응답을 한다.<br>

지금 시간이 몇시입니까 ? <br>
<%-- <%= 출력식 %> --%>
<%= d.toString() %> 입니다.
</body>

</html>