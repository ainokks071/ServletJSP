<!-- 지시자. -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.bit.model.*" %>
    
<!--memberInsert.jsp는 view기능 X -> Controller용 JSP! -->

<!-- 내장객체 request 바로 사용 가능.(JSP는 곧 Servlet이다.) -->

<%  /* form으로 넘어온 한글 데이터 처리. */
	request.setCharacterEncoding("utf-8");

   	String id = request.getParameter("id");
	String pass = request.getParameter("pass");
	String name = request.getParameter("name");
	int age = Integer.parseInt(request.getParameter("age")); 
	String email = request.getParameter("email");
	String phone = request.getParameter("phone");
	
	MemberVO vo = new MemberVO();
	vo.setId(id);
	vo.setPass(pass);
	vo.setName(name);
	vo.setAge(age);
	vo.setEmail(email);
	vo.setPhone(phone);
	
	MemberDAO dao = new MemberDAO();
	
	int count = dao.memberInsert(vo);
	
	if(count > 0) {
		response.sendRedirect("memberList.jsp");
	} else {
		throw new ServletException("not insert");
	}
%>	
	
<!-- <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html> -->