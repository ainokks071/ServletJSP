<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.bit.model.*" %>    
<%@ page import="java.util.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 내장객체이용 -> ctx = "/MVC06"  -->    
<c:set var='ctx' value="${pageContext.request.contextPath}" />

<!-- 객체 바인딩  -->
<%-- <% ArrayList<MemberVO> list = (ArrayList<MemberVO>) request.getAttribute("list"); %> --%>
<!-- getAttribute대신, JSTL을 사용해보자 ! --> 
 
<!-- 클라이언트에게 View페이지를 응답한다.
	 but, 
	 최초 요청이 Controller로 왔고, forward로 jsp로 넘겼기 때문에 응답 url은 jsp가 아닌, controller이다. url변경이 x -->
	  
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name='viewport' content='width=device-width, initial-scale=1'>
<title>회원 리스트 화면</title>
<!--부트스트랩5  -->
<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9' crossorigin='anonymous'>
<!--부트스트랩3, jquery  -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!--자바스크립트 메서드(함수) 선언   -->
<script type="text/javascript">
	
	/* JQuery
	MemberListController 최초 요청시, memberList.jsp포워딩 -> 변수msg는 비어있으므로, 아래 코드는 실행X(alert창X)
	변수msg에 값이 있다는 것 = MemberLoginController에서 "로그인에 실패"하여 변수msg(에러메세지)를 session객체에 바인딩을 했다.
	변수msg에 값이 비어있지 않으면(있으면) alert창(msg = "사용자 정보가 올바르지 않습니다.")을 띄우고,
	"""session객체에 바인딩 된 msg를 remove로 지운다."""
	
	만약 remove를 해주지 않으면? 
	한번 로그인 실패로 session객체에 바인딩된 계속 msg가 존재하여,
	그 후에 memberList.jsp가 요청될 때마다(두번째 이상의 요청) "alert창을 지속적으로 띄우는 에러" 발생. 
	하나의 브라우저 = 하나의 동일한 세션ID = 하나의 동일한 session객체 메모리공간 !!*/
	$(document).ready(function() {
		<c:if test="${!empty msg}">
			alert("${msg}");
			<c:remove var="msg" scope="session"/>
        </c:if>
	});
		
	function deleteFn(num) {
												/* get방식  */
		location.href="${ctx}/memberDelete.do?num=" + num; 
	}
	
	/* 로그인 유효성 검사 : id, pw입력해야만 true 반환 */
	function check() {
		/*JQuery문법 -> #은 input태그의 id를 뜻함, val() : 입력값, '' : null  */
		
		if($('#user_id').val()=='' && $('#password').val()=='') {
			alert("아이디와 비밀번호를 입력하세요.");
			return false;
		}
		
		if($('#user_id').val()=='') {
			alert("아이디를 입력하세요.");
			return false;
		}
		
		if($('#password').val()=='') {
			alert("비밀번호를 입력하세요.");
			return false;
		}
		
		return true;
	}
	
	function logout() {
		location.href="<c:url value='/memberLogout.do' />"; // /MVC06/memberLogout.do
	}
	
</script>
</head>

<body>
<!-- head -->
	<div class="p-5 bg-primary text-white text-center">
		<h1>Header : 회원 관리 시스템</h1>
 		<p>Resize this responsive page to see the effect!</p> 
	</div>
	
	<br>

<!-- content 1. 로그인폼 2. 리스트테이블  -->
<div class="container">
 	<h2>회원 전체 리스트</h2>
 	<div class="panel panel-default">
  		<!-- 패널 헤드 -> 로그인form : MemberLoginController로 요청!-->
		<div class="panel-heading">
		
		  <!--MemberLoginController에서 로그인 실패 시 : session객체 바인딩 X(null) -> form태그 창 띄움.-->	
		  <c:if test="${sessionScope.userId==null || sessionScope.userId==''}">
			<form class="form-inline" action="${ctx}/memberLogin.do" method="post">
				<div class="form-group">
			  		<label for="user_id">ID:</label>  <!--id는 JQuery에 사용, name은 login컨트롤러의 파라메터로 보낸다. -->
			  		<input type="text" class="form-control" id="user_id" name="user_id">
				</div>
				<div class="form-group">
			  		<label for="pwd">Password:</label> <!--id는 JQuery, name은 파라메터로 보낸다. -->
			  		<input type="password" class="form-control" id="password" name="password">
				</div>
							<!--로그인 버튼 유효성 검사 : 입력하지않고 로그인버튼 누르는 것 check -->
							<!--JQuery : check() 호출결과 true일 때에만 "return true"가 되어 "submit 성공".-->
							<!--		 check() 호출결과 false이면 "return false"가 되어 "submit 실패", alert창 띄움.-->
				<button type="submit" class="btn btn-default" onclick="return check()">로그인</button>
			</form>
		  </c:if>
		  
		 <!--MemberLoginController에서 로그인 성공 시 : session객체 바인딩 O(not null) -> 로그인 한 회원 이름 getAttribute(EL태그) -->	
		 <c:if test="${sessionScope.userId!=null && sessionScope.userId!=''}"> 
		    ${sessionScope.userName}님 안녕하세요.
		  	<button type="button" class="btn btn-default" onclick="logout()">로그아웃</button>
		 </c:if> 
			
	    </div>
     
    <!-- 패널 바디 -> 테이블 -->
<div class="panel-body">
	<table class = 'table table-borderd table-hover'>
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
			<%-- JSTL ${ }는 getAttribute다. --%>
			<!-- request객체에 set객체바인딩 후, memberListController로부터 forward기법으로 넘어온 data("list")에서 vo를 하나씩 꺼낸다.(반복문) -->
			<c:forEach var="vo" items="${list}">
				<tr>
					<td>
						<%-- <%=vo.getNum()%> --%>${vo.num}
					</td>
						<!-- a태그 : get방식으로 QueryString 전달. -->
						<%-- <td> <a href="memberContent.do?num=<%=vo.getNum()%>"><%=vo.getId()%></a></td> --%>
					<td>																							<!--id버튼 클릭하면, get방식으로 MemberContentController에 num값을 보내면서 요청한다.   -->					
						<input type="button" value="<%-- <%=vo.getId()%> --%>${vo.id}" class="btn btn-outline-info" onclick="location.href='${ctx}/memberContent.do?num=${vo.num}<%-- <%=vo.getNum()%> --%>'" />
					</td>
					<td>
						<%-- <%=vo.getPass()%> --%>${vo.pass}
					</td>
					<td>
						<%-- <%=vo.getName()%> --%>${vo.name}
					</td>
					<td>
						<%-- <%=vo.getAge()%> --%>${vo.age}
					</td>
					<td>
						<%-- <%=vo.getEmail()%> --%>${vo.email}
					</td>
					<td>
						<%-- <%=vo.getPhone()%> --%>${vo.phone}
					</td>
					<td>
						<!-- <td><a href="">삭제하기</a> </td> a태그도 QueryString 보내기 가능! get방식. -->
																				<!-- 자바스크립트 메서드(함수) 호출 --> 
					  <c:if test="${sessionScope.userId!=vo.id}">	
						<input type="button" value="삭제하기" class="btn btn-warning" onclick="deleteFn(${vo.num}<%-- <%=vo.getNum()%> --%>)" disabled="disabled"/>
					  </c:if>	
					  <c:if test="${sessionScope.userId==vo.id}">	
						<input type="button" value="삭제하기" class="btn btn-warning" onclick="deleteFn(${vo.num})"/>
					  </c:if>	
					</td>
				</tr>
					<%-- 		<% } %> --%>
			</c:forEach>
				<tr>
					<td colspan='8' align='center'>
					<!-- <a href = "memberRegister.do">회원가입 화면으로</a> --> <!-- 자바스크립트 location. -->
					<!-- <input type="button" value="회원가입 화면으로" class="btn btn-primary" onclick="location.href='/MVC06/memberRegister.do'"/> -->
					<!-- url경로가 ~html or ~jsp로 끝나지 않도록.. html로 요청하는 대신 controller로 요청 후, forward하자.-->
					<input type="button" value="회원가입 화면으로" class="btn btn-primary" onclick="location.href='${ctx}/memberRegister.do'"/>
					</td>	
				</tr> 
		</tbody>
	</table>
</div>
    
<div class="panel-footer">
	<p>패널 - Footer</p>
</div>

  </div>
</div>

		
<!-- footer -->
<div class="mt-5 p-4 bg-dark text-white text-center">
  	<p>Footer</p>
</div>

</body>
</html>