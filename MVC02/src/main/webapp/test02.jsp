<!--비즈니스로직을 따로 model로 추출   -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.bit.model.MyCalc" %>

<% MyCalc c = new MyCalc(); %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

1~300까지의총합 : <%=c.hap(1, 300)%> <br>
1~100까지의 총합 : <%=c.sum() %>

</body>
</html>