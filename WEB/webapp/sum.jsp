<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.web.util.MyUtil" %>    
    
<!-- 비즈니스로직(Model) : DTO, DAO, Util 클래스-> 클래스로 따로 분리 -> View기능에 집중 -> 유지보수에 용이 
     Model 1 방식 : JSP페이지에서 프리젠테이션로직(View)과 비즈니스로직(Model)을 분리한다. 
     MVC의 Model, View only -->
<% MyUtil util = new MyUtil(); 
   int sum = util.hap(); %>
    
<!-- 프리젠테이션로직(View) -->    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<table border ='1'>
<tr>
 <td>1~10000까지의 합</td>
 <td><%= sum %></td>
</tr>
</table>

</body>
</html>