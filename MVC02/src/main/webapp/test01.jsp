<%-- <JSP의 개념, 구성요소>
1. 클라이언트가 서버에 http://localhost:8080/MVC02/test01.jsp 요청 -> WAS(tomcat)이 해석(렌더링) 후 응답.
2. JSP가 곧 Servlet이다?
  1) 클라이언트가 JSP 요청
  2) WAS에 의해 JSP -> Servlet(.java파일)로 변환 -> JSP에서 Servlet의 내장객체 사용 가능.
  3) 컴파일된 .class파일이 클라이언트에게 응답.
3. JSP의 내장객체(Servlet에서 이미 만들어진 객체) -> new연산자 없이, jsp에서 바로 사용 가능.
 - request, response
 - session, out, config, application, page, pageContext

- 웹 프로그래밍
  1) Servlet(server + let) : 100% Java코드, Controller역할
  2)JSP(Java Server Page) : HTML + Java코드, View역할, 동적인 페이지
	  cf) 추후에, MVC01버전의 servlet(Controller)의 view영역을 JSP로 치환(연동) --%>

<%--  <% %> : 스크립트적인 요소 = 프로그래밍적인 요소. -> 지시자, 스크립틀릿, 출력식 --%>

<%--  1. 지시자 : <%@  %> 
	   1) <%@ page 지시자 %> 
	   2) <%@ include 지시자 %> 
	   3) <%@ taglib 지시자 %>   --%>
	   		
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 위 페이지 지시자는 서블릿으로 변환시,
    response.setContentType("text/html;charset=utf-8")
	PrintWriter out = response.getWriter(); 			-->





<%--  2. 선언문 : <%!  %> 메서드 선언 --%>

<%! public int hap(int a, int b) {
	  int sum = 0;
	  for(int i = a; i <= b; i++) {
		  sum += i;
	  }
	return sum;
	} %>

<%--  3. 스크립틀릿 : <%  %> --%>
<% int sum = 0;
   for(int i = 1; i <= 100; i++) {
	   sum += i;
   }%>
 
<!-- HTML 태그 영역 : JSP의 주 목적, 프리젠테이션 로직(view) --> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%--  3. 출력식 : <%=  %>  --%>

<table border='1'>
 <tr>
  <td>1~100까지의 합</td>
  <td><%=sum %></td>
 </tr>
 
  <tr>
  <td>55~350까지의 합</td>
  <td><%=hap(55, 350) %></td>
 </tr>
</table>


</body>
</html>