<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.bit.model.*" %>    
    
    
<% int num = Integer.parseInt(request.getParameter("num"));

   MemberDAO dao = new MemberDAO();
   MemberVO vo = dao.memberContent(num);
   
   response.setContentType("text/html;charset=UTF-8");
%>
		
		
<!DOCTYPE html>
<html>
<head>
<meta name='viewport' content='width=device-width, initial-scale=1'>
<title>Insert title here</title>
<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9' crossorigin='anonymous'>
<meta charset="UTF-8">
</head>
<body>
	<h1> 회원 상세 페이지 </h1>

	 <form action='memberUpdate.jsp' method='post'>
		<!-- 전송 시, 수정 없이 num 함께 전송됨. -->
		<input type='hidden' name='num' value='<%=vo.getNum()%>'/>
		<table class = 'table table-borderd'>
		<% if(vo != null) { %>
		
		<tr colspan='2'>
		<td> <%=vo.getName()%> 회원님의 상세페이지입니다.</td>
		</tr>
		
		<tr>
		<th>번호</th>
		<td><%=vo.getNum()%></td>
		</tr>
		
		<tr>
		<th>아이디</th>
		<td><%=vo.getId()%></td>
		</tr>
		
		<tr>
		<th>비밀번호</th>
		<td><%=vo.getPass()%></td>
		</tr>
		
		<tr>
		<th>이름</th>
		<td><%=vo.getName()%></td>
		</tr>
		
		<tr>
		<th>나이</th>
		<td> <input type='text' name='age' value='<%=vo.getAge()%>'/></td>
		</tr>
		
		<tr>
		<th>이메일</th>
		<td> <input type='text' name='email' value='<%=vo.getEmail()%>'/></td>
		</tr>
		
		<tr>
		<th>전화번호</th>
		<td> <input type='text' name='phone' value='<%=vo.getPhone()%>'/></td>
		</tr>
		
		<% } else { %>
		
		<tr>
			<td>일치하는 회원이 없습니다.</td>
		</tr>
		
		<% } %>
		
		<tr>
		<td></td>		
		<td colspan='2'>
		<!-- 수정하기 : 결국, input태그 내 값을 작성하여 form태그를 이용하여 전송(POST방식)  -->
		<input type='submit' class='btn btn-primary' value='수정하기'/>
		<input type='reset' class='btn btn-warning' value='취소'/>
		<a href="memberList.jsp"> 리스트로 </a>
		</td>
		</tr>
		
		</table>
		
		</form>
</body>
</html>