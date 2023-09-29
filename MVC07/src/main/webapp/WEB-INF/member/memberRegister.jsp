<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<%-- <!--내장객체 pageContext, request -> get contextPath -> 변수 ctx = 값 "/MVC06" -->
<c:set var='ctx' value="${pageContext.request.contextPath}" />
 --%>
 
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
	   /* 가입 버튼 클릭 시, add()메서드 호출  */
		function add() {
			document.form1.action = "<c:url value='/memberInsert.do' />";
			document.form1.submit();
		}
	   
		/* 클라이언트가 회원정보 입력 후, 가입하기 버튼 클릭하면 add2()메서드 호출
			Ajax를 이용해서 파일업로드 구현 
			1. 파일이 첨부된 경우
			2. 파일이 첨부되지 않은 경우
		*/
		function add2() {
			/* 1. 파일이 첨부된 경우 */
			if($("#file").val() != '') {
				/* 파일데이터를 서버(controller)로 전송하기 위해 'FormData객체' 사용 -> 'fomr태그'와 구분 */
				var formData = new FormData();
/* 				FormData객체의 append()메서드 : 파일데이터를 key/value로 묶어준다.
				$("input[name=file]")[0] : name속성이 file인 input태그들 중 첫번째(0) input태그
				files[0] : 사용자가 업로드한 파일들 중 첫번째(0) 파일.
 */				
 				formData.append("file", $("input[name=file]")[0].files[0]);
				/* 1. ajax(비동기통신)방법으로 파일자체(formData)를 서버로 전송 후, 
				   2. 업로드 된 파일이름을 반환받아 input(hidden) : filename변수에 저장
				   3. 모든 텍스트데이터 서버로 전송. */
				$.ajax({
			 	/* 1. formData에 묶인 파일데이터를 서버로 전송하기(비동기) : 파일 업로드!  */
					url : "<c:url value='/fileUpload.do' />",
					type : "post",
					data : formData, /* 파일데이터 전송. */
					/* formData는 단순 text가 아닌, binary데이터이므로 false로 해야한다. */
					processData : false,
					contentType : false,
				/* 2. 클라이언트의 회원정보 + 파일이름 (텍스트데이터) 서버로 전송하기 */	
					success : function(data) { /* 실제 업로드 된 파일 이름 반환 후, input(hidden) : filename변수에 저장. */
						alert("가입 되었습니다.");
					
  						$("#filename").val(data);
						/* 사용자가 입력한 id, pass, name, age, email, phone, 첨부한 filename 7개 서버로 전송. */					
						document.form1.action = "<c:url value='/memberInsert.do?mode=fileAdd' />";
						document.form1.submit();
 	 					},
					error : function() { alert("error"); }
				});
				   
			/* 파일이 첨부되지 않은 경우  */
			} else {
				/* id, pass, name, age, email, phone 6개 서버로 전송 */
				document.form1.action = "<c:url value='/memberInsert.do?mode=add' />";
				document.form1.submit();
			}
		}
		
		function frmreset() {
			document.form1.reset();
		}
		
		function memlist() {
			location.href="<c:url value='/memberList.do' />";
		}
		
		/* 중복확인 버큰 클릭 시 실행되는 함수 */
		function doublecheck() {
			/*JQuery 문법 */
		    if($("#id").val()=='') {
		    	alert("아이디를 입력하세요.")
		    	$("#id").focus(); /* 커서 위치 이동 */
		    	return; /* return 적지 않으면 아래의 코드도 실행되겠지. */
		    }
			
		    /* 변수 선언, 입력값 대입  */
 		    var id = $("#id").val();
		    
		    /* ajax함수 사용 : 클라이언트와 서버 사이의 비동기통신 */
		    $.ajax({
		    	url : "<c:url value='/memberDbcheck.do'/>", /* 요청을 보낼 컨트롤러 지정 : 서버에서 중복 체크*/
		    	type : "POST", /* POST방식으로 데이터 전송 */
		    	data : {"id" : id}, /* 컨트롤러에 요청 시 보낼 데이터. -> 컨트롤러에서 request.getParameter("id")로 받는다. */
		    	success : dbCheck, /* 위 컨트롤러에 요청 성공 시, 응답에 대한 결과데이터를 callback함수에서 받아줌. */
		    	error : /* error */function() { alert("컨트롤러로 요청 실패 error"); } /* 컨트롤러에 요청 실패 시 호출되는 함수. */
		    });
		}
		
		/* 서버로부터 응답에 대한 결과를 받아주는 callback함수. */
		function dbCheck(isDouble) {
			if(isDouble == "true") {
				alert("중복된 아이디 입니다.");
				$("#id").focus();
			} else {
				alert("사용 가능한 아이디 입니다.");
				$("#id").focus();
			}
		}
		/* function error() {
			alert("컨트롤러로 요청 실패 error");
		} */
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
    		 <table>
    		  <tr>
    		   <td>
    		   								     <!-- id속성은 페이지에서 고유해야한다. -->
    		    <input type="text" class="form-control" id="id" name="id" placeholder="Enter id">
    		   </td>
      		   <td>
      		    <input type="button" value="중복확인" class="btn btn-warning" onclick="doublecheck()">
      		   </td>	
      		  </tr>	
      		</table>	
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
  		
  		<div class="form-group">
    		<label class="control-label col-sm-2" >첨부파일</label>
    		<div class="col-sm-10">
      			<input type="file" class="control-label" id="file" name="file">
    		</div>
  		</div>
   				<input type="hidden" id="filename" name="filename" >
 	</form>
  </div>
  
  <div class="panel-footer" style="text-align : center">
  	  <input type="button" class="btn btn-primary" value="가입" onclick="add2()"/>
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