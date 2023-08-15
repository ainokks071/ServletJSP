<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.bit.model.*" %>    
<%@ page import="java.util.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    

<!-- 객체 바인딩  -->
<%-- <% ArrayList<MemberVO> list = (ArrayList<MemberVO>) request.getAttribute("list"); %> --%>
<!-- getAttribute대신, JSTL을 사용해보자 ! --> 
 
<!-- 클라이언트에게 View페이지를 응답한다.
	 but, 
	 forward기법이므로 최초 요청이 Contoller(Servlet)에 왔으므로, 응답 url은 jsp가 아닌, servlet이다. -->
	  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name='viewport' content='width=device-width, initial-scale=1'>
<title>회원 리스트 화면</title>
<!--자바스크립트 메서드(함수) 선언   -->
<script type="text/javascript">

/* get방식  */
	function deleteFn(num) {
	location.href="memberDelete.do?num=" + num; 
	}
	
</script>

<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9' crossorigin='anonymous'>
</head>
<body>

	<h1>회원 전체 리스트</h1>

JSTL + EL 사용
	
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

			<%-- <%for(MemberVO vo : list) {  %> --%>
			<c:forEach var="vo" items="${list}">
				<tr>
					<td>
						<%-- <%=vo.getNum()%> --%>${vo.num}</td>
					<!-- a태그 : get방식으로 QueryString 전달. -->
					<%-- <td> <a href="memberContent.do?num=<%=vo.getNum()%>"><%=vo.getId()%></a></td> --%>
					<td>
						<input type="button" value="<%-- <%=vo.getId()%> --%>${vo.id}" class="btn btn-outline-info" onclick="location.href='memberContent.do?num=${vo.num}<%-- <%=vo.getNum()%> --%>'" />
					</td>
					<td>
						<%-- <%=vo.getPass()%> --%>${vo.pass}</td>
					<td>
						<%-- <%=vo.getName()%> --%>${vo.name}</td>
					<td>
						<%-- <%=vo.getAge()%> --%>${vo.age}</td>
					<td>
						<%-- <%=vo.getEmail()%> --%>${vo.email}</td>
					<td>
						<%-- <%=vo.getPhone()%> --%>${vo.phone}</td>
					<td>
						<!-- <td><a href="">삭제하기</a> </td> a태그도 QueryString 보내기 가능! get방식. -->
						<!-- 자바스크립트 메서드(함수) 호출 --> 
						<input type="button" value="삭제하기" class="btn btn-warning" onclick="deleteFn(${vo.num}<%-- <%=vo.getNum()%> --%>)" />
					</td>
				</tr>
				<%-- 		<% } %> --%>
			</c:forEach>

			<tr>
		<td colspan='8' align='center'>
		<!-- <a href = "memberRegister.do">회원가입 화면으로</a> --> <!-- 자바스크립트 location. -->
		<!-- <input type="button" value="회원가입 화면으로" class="btn btn-primary" onclick="location.href='memberRegister.do'"/> -->
		<!-- url경로가 ~html or ~jsp로 끝나지 않도록.. html로 요청하는 대신 controller로 요청 후, forward하자.-->
		<input type="button" value="회원가입 화면으로" class="btn btn-primary" onclick="location.href='memberRegister.do'"/>
		</td>
		
		</tr> 

		</tbody>

		</table>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
</body>
</html>