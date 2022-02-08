v<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 로그인한 이후에 모든 문서에서 공통으로 포함시킬 문서를 만들어서 상태유지한 객체 
	 m을 통해서 아이디와 이름을 출력하고 로그아웃에 대한 링크를 설정함
	 이때 우리가 스프링 환경 설정 파일에서 로그아웃을 위한 서비스명을 logout이라고 설정함
	 로그아웃은 이것만 링크를 걸어주면되고(logout) 실제로 로그아웃에 대한 처리는 시큐리티가 알아서 함-->
	${m.id }(${m.name })님 로그인되었습니다.
	<a href="logout">로그아웃</a>
</body>

</html>