<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!--내장객체 pageContext, request -> get contextPath -> /MVC05 -->
<c:set var='ctx' value="${pageContext.request.contextPath}" />


<!-- MVC03 : Model 2 시작 

 ** Controller(Servlet)와 View(JSP) 사이의 소통, 연동이 핵심!
 
1. RequestDispatcher(요청 의뢰)

2. forward기법

3. 객체 바인딩

-->

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>회원 가입 화면</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
  </head>
  <body>
    <br>
      <h1>회원 가입 화면</h1>
     <br>

<!-- JDBC는 맛보기. 어차피 요즘 사용하지 않음. MVC 공부 용도!! -->
     
<!-- 현재 위치 : http://localhost:8080/MVC01/member/memberRegister.html 
 			   <form action="memberInsert.do" method="post">라고 해버리면,   
	 		   http://localhost:8080/MVC01/member/memberInsert.do (X)      -->
	 		   
<!-- 요청 : http://localhost:8080/MVC04/memberInsert.do
	 /(슬래쉬) -> 절대경로로 입력 -->
<!--  <form action="/MVC05/memberInsert.do" method="post"> -->
 <form action="${ctx}/memberInsert.do" method="post">
 
  <table class="table">
    <thead>
    <tr>
      <th scope="col"></th>
      <th scope="col"></th>
      <th scope="col"></th>
    </tr>
  </thead>
  
  <tbody>
  
    <tr>
      <th scope="row">1</th>
      <td>아이디</td>
      <!--id(변수) = kimks071(값) -->
      <td> <input type="text" name="id"/> </td>
    </tr>
    
    <tr>
      <th scope="row">2</th>
      <td>패스워드</td>
      <!--pass(변수) = 1234(값) -->
      <td><input type="password" name="pass"/></td>
    </tr>
    
    <tr>
      <th scope="row">3</th>
      <td>이름</td>
      <!--name(변수) = 김경수(값) -->
      <td><input type="text" name="name"/></td>
      <!--한글데이터(ex) 김 : 2byte) -> 서블릿으로 전송 시(1byte + 1byte) -> 한글 깨짐 현상 발생 -->
    </tr>
    
    <tr>
      <th scope="row">4</th>
      <td>나이</td>
      <!--age(변수) = 33(값) -->
      <td><input type="text" name="age"/></td>
    </tr>
       
    <tr>
      <th scope="row">5</th>
      <td>이메일</td>
      <!--email(변수) = ainokks071@gmail.com(값) -->
      <td><input type="text" name="email"/></td>
    </tr>
    
    <tr>
      <th scope="row">6</th>
      <td>전화번호</td>
      <!--phone(변수) = 010-3014-4462(값) -->
      <td><input type="text" name="phone"/></td>
    </tr>    
    
    <tr>
      <th scope="row"></th>
      <td></td>
	  <td>	  
	  <!--전송, 초기화 -->
	  <input type="submit" class="btn btn-primary" value="가입"/>
	  <input type="reset" class="btn btn-warning" value="취소"/>
	  </td>
    </tr>
    
  </tbody>
  
</table>

</form> 
    <!-- 
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script> -->
  </body>
</html>