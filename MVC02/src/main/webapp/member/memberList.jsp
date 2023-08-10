<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.bit.model.*" %>    
<%@ page import="java.util.*" %>    

<% 	MemberDAO dao = new MemberDAO();
	ArrayList<MemberVO> list = dao.memberList();
	
	response.setContentType("text/html;charset=UTF-8");
%> 
 
<!-- view용 JSP ! -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name='viewport' content='width=device-width, initial-scale=1'>
<title>회원 리스트 화면</title>

<!--자바스크립트 메서드(함수) 선언   -->
<script type="text/javascript">

	function deleteFn(num) {
	location.href="memberDelete.jsp?num=" + num; 
	}
	
</script>

<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9' crossorigin='anonymous'>
</head>
<body>

	<h1>회원 전체 리스트</h1>
	
	<table class = 'table table-borderd'>
		
		<thead>

 		<tr>
		<th>번호</th>
		<th>아이디</th>
		<th>비밀번호</th>
		<th>이름</th>
		<th>나이</th>
		<th>이메일</th>
		<th>전화번호</th>
		<th>삭제하기</th>
		</tr>
		
		</thead>
		
		<tbody>
		
		<%for(MemberVO vo : list) {  %>
			<tr>
			<td><%=vo.getNum()%></td>
			<!-- a태그 : get방식으로 QueryString 전달. -->
			<td> <a href="memberContent.jsp?num=<%=vo.getNum()%>"><%=vo.getId()%></a></td>
			<td><%=vo.getPass()%></td>
			<td><%=vo.getName()%></td>
			<td><%=vo.getAge()%></td>
			<td><%=vo.getEmail()%></td>
			<td><%=vo.getPhone()%></td>
			<td>
			<!-- <td><a href="">삭제하기</a> </td> a태그도 QueryString 보내기 가능! get방식. -->
																				<!-- 자바스크립트 메서드(함수) 호출 -->
			<input type="button" value="삭제하기" class="btn btn-warning" onclick="deleteFn(<%=vo.getNum()%>)"/>
			</td>
			</tr>
		<% } %>

		<tr>
		<td colspan='8' align='center'>
		<!-- <a href = "memberRegister.html">가입하기</a> --> <!-- 자바스크립트 location. -->
		<!-- submit (X) -->
		<input type="button" value="회원가입 화면으로" class="btn btn-primary" onclick="location.href='memberRegister.html'"/>
		</td>
		
		</tr> 

		</tbody>

		</table>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
</body>
</html>