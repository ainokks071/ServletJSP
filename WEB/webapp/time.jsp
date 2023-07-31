<!-- JSP(Java Server Page) = Java코드영역(프로그래밍) + HTML영역(View) -->    

<%-- <%@ 지시자 %> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>

<%-- <% 스크립틀릿 %> --%>    
<% Date d = new Date(); %>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>


<body>
JSP페이지 (동적인 페이지)<br>
톰캣 서버가 지시자, 스크립틀릿, 출력식을 렌더링(해석)하여 그 결과를 클라이언트의 요청에 맞는 응답을 한다.<br>
지금 시간이 몇시입니까 ? <br>
<%-- <%= 출력식 %> --%>
<%= d.toString() %> 입니다.

</body>
</html>