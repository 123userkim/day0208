<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>로그인</h2>
	<form action="login" method="post">
		<!-- 스프링 시큐리티에서는 모든 폼태그에 대한 요청일때	안전한 요청이라는 의미의 다음의 hidden값을 반드시 전달해야 합니다.  -->
		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
		
		<!-- 스프링 시큐리티의 로그인에 대한 post방식에 대한 처리를 우리가 따로 하지 않아도 시큐리티가 알아서 처리해 줍니다.
		이때에 아이디와 암호에 대한 입력상자의 이름은 반드시 username, password로 해야 합니다. -->
		아이디 : <input type="text" name="username"><br>
		암호 : <input type="password" name="password"><br>
		<input type="submit" value="로그인">
	</form>
</body>
</html>