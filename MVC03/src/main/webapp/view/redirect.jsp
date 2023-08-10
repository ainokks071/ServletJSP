<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- redirect. QueryString(GET방식)으로 넘어온 데이터 getParameter()로 추출하기. -->
<% String name = request.getParameter("name");
String age = request.getParameter("age");
String email = request.getParameter("email");
%>
    
<!-- forward. 객체바인딩으로 넘어온 데이터 getAttribute()로 추출하기  -->
<%-- <% String data = (String)request.getAttribute("data"); %> --%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
Controller에서 redirect로 넘어온 데이터 출력하기 <br>
이름 : <%= name  %> 입니다. <br>
나이 : <%= age  %> 입니다. <br>
이메일 : <%= email  %> 입니다. 

  <!-- 새롭게 재요청하므로 URL 변경이 있다.
  	   http://localhost:8080/MVC03/rc.do (X)  
       http://localhost:8080/MVC03/redirect.jsp (O)  -->


<%-- <%= data %> 입니다. --%>

</body>
</html>