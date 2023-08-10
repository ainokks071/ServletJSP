<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.bit.model.*" %>

<% 	int num = Integer.parseInt(request.getParameter("num"));

	MemberDAO dao = new MemberDAO();
	int count = dao.memberDelete(num);
	if(count > 0) {
		response.sendRedirect("memberList.jsp");
	} else {
		throw new ServletException("not delete");
	}
%>    
    
    
<!-- controller용 JSP -->

<!-- <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html> -->