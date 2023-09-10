<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="kr.bit.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- get 객체 바인딩. 
    	getAttribute대신, JSTL, EL($기호 : getAttribute효과) 사용해보기 !-->
<%-- <% MemberVO vo = (MemberVO) request.getAttribute("vo");%> --%>

<!-- 내장객체 -> ctx="/MVC06" -->
<c:set var='ctx' value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name='viewport' content='width=device-width, initial-scale=1'>
<title>Insert title here</title>
<!--부트스트랩5  -->
<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9' crossorigin='anonymous'>
<!--부트스트랩3, jquery  -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<script type="text/javascript">

	function update() {
		document.form1.action = "<c:url value='/memberUpdate.do' />";
		document.form1.submit();
	}
	
	function frmreset() {
		document.form1.reset();
	}


</script>

</head>

<body>

<!-- header -->
	<div class="p-5 bg-primary text-white text-center">
		<h1>Header : 회원 관리 시스템</h1>
 		<p>Resize this responsive page to see the effect!</p> 
	</div>
	
	<br>

<!-- body -->
<div class="container">
  <h2>회원 상세 페이지</h2>
  <c:if test="${sessionScope.userName!=null && sessionScope.userName!=''}">
   <p> ${sessionScope.userName} 회원님 안녕하세요. (로그인 된 상태입니다.)</p>
  </c:if>
  <c:if test="${sessionScope.userName==null || sessionScope.userName==''}">
   <p> 로그인을 해주세요. </p>
  </c:if>
   
  <div class="panel panel-default">
	<div class="panel-heading">
	  <p> "${vo.name}" 회원님의 상세페이지입니다.</p>
	</div>
    
 <form id="form1" name="form1" class="form-horizontal" method='post'> 
 	<!-- 전송 시, 수정 없이 num 함께 전송됨. -->
    <input type='hidden' name='num' value='${vo.num}'/> 	
    
    <div class="panel-body">
      <div class="form-group">
        <label class="control-label col-sm-2">번호 :</label>
        <div class="col-sm-10"><c:out value="${vo.num}"/></div>
      </div>
      
      <div class="form-group">
        <label class="control-label col-sm-2">아이디 :</label>
        <div class="col-sm-10"><c:out value="${vo.id}"/></div>
      </div>
    
      <div class="form-group">
        <label class="control-label col-sm-2">비밀번호 :</label>
        <div class="col-sm-10"><c:out value="${vo.pass}"/></div>
      </div>
      
      <div class="form-group">
        <label class="control-label col-sm-2">이름 :</label>
        <div class="col-sm-10"><c:out value="${vo.name}"/></div>
      </div>	
      
      <div class="form-group">
        <label class="control-label col-sm-2">나이 :</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="age" name="age" value="${vo.age}"/>
		</div>
      </div>
      
      <div class="form-group">
        <label class="control-label col-sm-2">이메일 :</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="email" name="email" value="${vo.email}"/>
		</div>
      </div>
      
      <div class="form-group">
        <label class="control-label col-sm-2">전화번호 :</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="phone" name="phone" value="${vo.phone}"/>
        </div>
      </div>
     </div>
     
	
  </form>
  
     <div class="panel-footer" style="text-align : center">
		<!-- 수정하기 : 결국, input태그 내 값을 작성하여 form태그를 이용하여 전송(POST방식) : name속성의 num, age, email, phone -->
		
		<!--1. 로그인을 해야 수정하기 버튼 나온다.
		    2. 로그인 한 회원은 자신의 페이지에서만 수정하기 버튼 누를 수 있다. -->
<%-- 	  <c:if test="${sessionScope.userId!=null && sessionScope.userId!=''}">	--%>
      <c:if test="${!empty sessionScope.userId}">
        <c:if test="${sessionScope.userId==vo.id}">
		 <input type='submit' class='btn btn-primary' value='수정하기' onclick="update()"/> 
        </c:if>
        <c:if test="${sessionScope.userId!=vo.id}">
		 <input type='submit' class='btn btn-primary' value='수정하기' disabled="disabled"/> 
        </c:if>
	  </c:if>	
	  
		<input type='reset' class='btn btn-warning' value='취소' onclick="frmreset()" /> 
																	<!-- <a href="memberList.do"> 리스트로 </a> -->
		<input type='button' class='btn btn-success' value='리스트로' onclick="location.href='${ctx}/memberList.do'" />
	</div>
	
 </div>
</div>






<!-- footer -->
<div class="mt-5 p-4 bg-dark text-white text-center">
  	<p>Footer</p>
</div>

</body>
</html>