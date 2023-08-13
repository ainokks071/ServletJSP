<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 접두어 c -> c:for 이런식으로 사용하겠다는 선언.-->

<!--JSTL tag 종류
1. core tag : if, for, switch~case, 변수선언 등 자주사용하는 태그
2. fmt tag(포맷팅 태그) : 날짜, 통화, 시간 등
3. SQL tag
4. xml tag
5. 함수 tag : fn  -->

<c:set var="cnt" value="10"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!--JSTL(JSP Standard Tag Library)
	: 프로그래밍적인 요소(for문, if문 등)를 태그로 표현 -> 디자이너와 협업에 유리.
	mvnrepository에서 API jar파일 다운로드 후 lib폴더에 붙여넣기..-->
	
<!--EL(출력식 ) -->
${cnt}	 		
${cnt * 99}	 		
${cnt < 100}	 		

</body>
</html>