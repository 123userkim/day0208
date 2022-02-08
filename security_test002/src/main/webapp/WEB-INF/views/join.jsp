<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head> 
<body>
	<form action="join" method="post">
		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
		아이디 : <input type="text" name="id"><br>
		암호 : <input type="text" name="pwd"><br>
		이름 : <input type="text" name="name"><br>
		<input type="submit" value="등록">
	</form>
</body>
</html>