<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kr.bit.model.*"%>
<%--
[JSTL 이란?]
JSP 에서 java 코드가 들어갈 때마다 <% ~~ %>  태그를 사용하기에 불편함이 있고
가독성도 좋지 않아 등장한 태그 라이브러리
[Core Tag 란?]
JSTL의 태그 라이브러리 중 하나로 변수 지정, 제어문, 페이징 처리 등을 할 수 있는 기능을 제공.
아래와 같이 JSP파일의 상단에 선언해준다.
[Core Tag의 종류]
1. set	: 사용될 변수를 설정해 값을 저장하는 태그 -> 속성 : var(변수이름지정) value(값)
2. if : 조건에 따라 내부 코드를 수행하는 태그 -> 속성 : test 속성에 조건을 입력.
3. forEach	: 반복문을 수행하는 태그 -> 
			<c:forEach var="vo" items="${list}">

4. out	: 페이지에 값을 출력하는 태그
5. url	: 변수에 url 값과 쿼리스트링 값을 저장하고 호출할 수 있게 하는 태그
6. choose : 다중 조건을 처리하는 코드, if, else문과 비슷하며 <c:choose> 내부에 <c:when>, <c:otherwise> 태그를 사용
7. for Tokens	: 구분자로 분리된 각각의 토큰을 처리할 때 사용하는 태그
8. redirect : 페이지를 재요청하는 태그 																	--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- get 객체 바인딩. 
    	getAttribute대신, JSTL, EL($기호 : getAttribute효과) 사용해보기 !-->
<%-- <% MemberVO vo = (MemberVO) request.getAttribute("vo");%> --%>

<!-- pageContext 내장객체 -> 변수 ctx="/MVC06" -->
<c:set var="ctx" value="${pageContext.request.contextPath}" />

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
	
	function update2() {
		/* 1. 파일이 첨부된 경우 */
		if($("#file").val() != '') {
			/* 첨부된 파일데이터를 서버(controller)로 전송하기 위해 'FormData객체' 사용 -> 'fomr태그'와 구분 */
			var formData = new FormData();
/* 			FormData객체의 append()메서드 : 파일데이터를 key/value로 묶어준다.
			$("input[name=file]")[0] : name속성이 file인 input태그들 중 첫번째(0) input태그
			files[0] : 사용자가 업로드한 파일들 중 첫번째(0) 파일. */				
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
					alert("수정 되었습니다.");
					$("#filename").val(data);
					/* 사용자가 수정한 age, email, phone, filename + num 5개 서버로 전송. */					
					document.form1.action = "<c:url value='/memberUpdate.do?mode=fileAdd' />";
					document.form1.submit();
	 				},
				error : function() { alert("error"); }
			});
			   
		/* 파일이 첨부되지 않은 경우  */
		} else {
			/* id, pass, name, age, email, phone 6개 서버로 전송 */
			document.form1.action = "<c:url value='/memberUpdate.do?mode=add' />";
			document.form1.submit();
		}
	}
	
	function frmreset() {
		document.form1.reset();
	}

	function memlist() {
		location.href="<c:url value='/memberList.do' />";
	}
	
	function downloadFile(filename) {
		location.href = "<c:url value='/fileDownload.do'/>?filename=" + filename;
	}
	
	function deleteFile(num, filename) {
		location.href = "<c:url value='/fileDelete.do'/>?num=" + num + "&filename=" + filename;
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
  <h2 style="color:#1234;">회원 상세 페이지</h2>
  <!-- 로그인 했으면 회원이름 출력 -->
  <c:if test="${sessionScope.userName!=null && sessionScope.userName!=''}">
   <label style="color:green;"> ${sessionScope.userName} </label> 회원님이 로그인 하셨습니다.
  </c:if>
  <!-- 로그인 하지 않았으면 로그인 하라는 메세지 출력 -->
  <c:if test="${sessionScope.userName==null || sessionScope.userName==''}">
   <p style="color:red;"> 로그인을 해주세요. </p>
  </c:if>
   
  <div class="panel panel-default">
	<div class="panel-heading">
	  <p> "${vo.name}" 회원님의 상세페이지입니다.</p>
	</div>
    
<div class="panel-body">
    <!-- id중복불가 name중복가능 -->
 <form id="form1" name="form1" class="form-horizontal" method='post'> 
 
 	<input type="hidden" id="filename" name="filename" >
 	
 	<!-- 전송 시, 수정 없이 num 함께 전송됨. -->
    <input type='hidden' name='num' value='${vo.num}'/> 	
    
    <div class="panel-body">
    
    <!-- 로그인 + 자신의 것-->
	 <c:if test="${!empty sessionScope.userId && sessionScope.userId==vo.id}">
	   <!-- 파일첨부 한 경우 . 따라서, 로그인한 사람이 자신의 사진만 볼 수 있다.-->
	   <c:if test="${vo.filename!=null && vo.filename!=''}">
		  <div class="form-group">
	        	<label class="control-label col-sm-2">사진</label>
	        	<div class="col-sm-10">  
					<img src="file_repo/${vo.filename}" width="120px" height="100px" >
	        	</div>
	      </div>    
	  </c:if>
	 </c:if>
    
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
          <input type="text" class="form-control" id="age" name="age" value="${vo.age}" style="width:10%"/>
		</div>
      </div>
      
      <div class="form-group">
        <label class="control-label col-sm-2">이메일 :</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="email" name="email" value="${vo.email}" style="width:30%"/>
		</div>
      </div>
      
      <div class="form-group">
        <label class="control-label col-sm-2">전화번호 :</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="phone" name="phone" value="${vo.phone}" style="width:30%"/>
        </div>
      </div>
      
      <div class="form-group">
        <label class="control-label col-sm-2">첨부파일 :</label>
	        <div class="col-sm-10">
	        <!-- 로그인을 하고, 나의 것만 첨부파일 수정할 수 있다. -->
	        <c:if test="${sessionScope.userId!=null && sessionScope.userId == vo.id }">
	          <input type="file" id="file" name="file" style="width:30%"/>
	        </c:if>
	        <!-- 로그인을 하고, 나의 것이 아니면 첨부파일 수정할 수 없다. -->
	        <c:if test="${sessionScope.userId!=null && sessionScope.userId != vo.id }">
	          <input type="file" id="file" name="file" style="width:30%" disabled="disabled"/>
	        </c:if>
	        <!-- 로그인을 하지 않으면 첨부파일 수정할 수 없다. -->
	        <c:if test="${sessionScope.userId==null}">
	          <input type="file" id="file" name="file" style="width:30%" disabled="disabled"/>
	        </c:if>
        
          <!-- 클릭하면 서버에 업로드 된 파일을 다운로드한다. -->
          <c:if test="${vo.filename != null && vo.filename != ''}" >
          	<a href="javascript:downloadFile('${vo.filename}')"><c:out value="${vo.filename}" /></a>
          </c:if>
          <!-- 로그인 후 내 파일만 삭제할 수 있다. -->
          <c:if test="${sessionScope.userId != null && sessionScope.userId == vo.id && vo.filename != null && vo.filename != ''}" >
            <a href="javascript:deleteFile('${vo.num}', '${vo.filename}')"><span class="glyphicon glyphicon-remove"></span></a>
          </c:if>
          	
        </div>
      </div>
      
     </div>
  </form>
</div>  
  
     <div class="panel-footer" style="text-align : center">
		<!-- 수정하기 : 결국, input태그 내 값을 작성하여 form태그를 이용하여 전송(POST방식) : name속성의 num, age, email, phone -->
<%-- 	  <c:if test="${sessionScope.userId!=null && sessionScope.userId!=''}">	--%>

      <!--로그인을 해야 수정하기 버튼을 클릭할 수 있는데, 자신의 것만 클릭가능하다.  -->
      <c:if test="${!empty sessionScope.userId}">
        <c:if test="${sessionScope.userId==vo.id}">
		   <input type='button' class='btn btn-primary' value='수정하기' onclick="update2()"/> 
        </c:if>
        <!-- 다른 사람의 수정하기버튼은 클릭할 수 없다. -->
        <c:if test="${sessionScope.userId!=vo.id}">
		   <input type='button' class='btn btn-primary' value='수정하기' disabled="disabled"/> 
        </c:if>
	  </c:if>
	  <!--로그인을 하지 않았으면 수정하기 버튼은 아예 클릭할 수 없다.  -->
	  <c:if test="${empty sessionScope.userId}">
		 <input type='button' class='btn btn-primary' value='수정하기' disabled="disabled"/> 
	  </c:if>	
		<input type='button' class='btn btn-warning' value='취소' onclick="frmreset()" /> 
																	<!-- <a href="memberList.do"> 리스트로 </a> -->
		<%-- <input type='button' class='btn btn-success' value='리스트로' onclick="location.href='${ctx}/memberList.do'" /> --%>
		<input type='button' class='btn btn-success' value='리스트로' onclick="memlist()" />
	</div>
 </div>
</div>






<!-- footer -->
<div class="mt-5 p-4 bg-dark text-white text-center">
  	<p>Footer</p>
</div>

</body>
</html>