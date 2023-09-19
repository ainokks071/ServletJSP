<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!--내장객체 pageContext, request -> get contextPath -> 변수 ctx = 값 "/MVC06" -->
<c:set var='ctx' value="${pageContext.request.contextPath}" />

<!-- MVC03 : Model 2 시작 

 ** Controller(Servlet)와 View(JSP) 사이의 소통, 연동이 핵심!
 
1. DAO로부터 얻어온 데이터를 set 객체 바인딩

2. RequestDispatcher(요청 의뢰)

3. forward기법으로 데이터를 controller(Servlet) -> view(JSP)로 보내기.


-->

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>회원 가입 화면</title>
    <!--부트스트랩5  -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <!--부트스트랩3, jquery  -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
		function add() {
			document.form1.action = "<c:url value='/memberInsert.do' />";
			document.form1.submit();
		}
		
		function frmreset() {
			document.form1.reset();
		}
		
		function memlist() {
			location.href="<c:url value='/memberList.do' />";
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
<div class="container">
 <h2>회원 가입 화면</h2>  
<div class="panel panel-default">
  <div class="panel-body">
    <form class="form-horizontal" method="post" id="form1" name="form1">
  		<div class="form-group">
    		<label class="control-label col-sm-2" for="id">아이디</label>
    		<div class="col-sm-10">
      			<input type="text" class="form-control" id="id" name="id" placeholder="Enter id" style="width:30%">
    		</div>
  		</div>
  		
  		<div class="form-group">
    		<label class="control-label col-sm-2" for="pass">패스워드</label>
    		<div class="col-sm-10">
      			<input type="password" class="form-control" id="pass" name="pass" placeholder="Enter password" style="width:30%">
    		</div>
  		</div>
  		
  		<div class="form-group">
    		<label class="control-label col-sm-2" for="name">이름</label>
    		<div class="col-sm-10">
      			<input type="text" class="form-control" id="name" name="name" placeholder="Enter name" style="width:30%">
    		</div>
  		</div>
  		  
   		<div class="form-group">
    		<label class="control-label col-sm-2" for="age">나이</label>
    		<div class="col-sm-10">
      			<input type="text" class="form-control" id="age" name="age" placeholder="Enter age" style="width:30%">
    		</div>
  		</div>
  		 				
  		<div class="form-group">
    		<label class="control-label col-sm-2" for="email">이메일</label>
    		<div class="col-sm-10">
      			<input type="email" class="form-control" id="email" name="email" placeholder="Enter email" style="width:30%">
    		</div>
  		</div>  

  		<div class="form-group">
    		<label class="control-label col-sm-2" for="phone">전화번호</label>
    		<div class="col-sm-10">
      			<input type="text" class="form-control" id="phone" name="phone" placeholder="Enter phone" style="width:30%">
    		</div>
  		</div>
	</form>
  </div>
  
  <div class="panel-footer" style="text-align : center">
  	  <input type="button" class="btn btn-primary" value="가입" onclick="add()"/>
	  <input type="button" class="btn btn-warning" value="취소" onclick="frmreset()"/>
	  <input type="button" class="btn btn-success" value="리스트로" onclick="memlist()"/>
  </div>
  
</div>
</div>  
  


<!-- JDBC는 맛보기. 어차피 요즘 사용하지 않음. MVC 공부 용도!! -->
     
<!-- 요청 : http://localhost:8080/MVC06/memberInsert.do
	 /(슬래쉬) -> 절대경로로 입력 -->
<!--  <form action="/MVC06/memberInsert.do" method="post"> -->
<!--$기호 : EL태그-->


<!-- footer -->
<div class="mt-5 p-4 bg-dark text-white text-center">
  	<p>Footer</p>
</div>

  </body>
</html>