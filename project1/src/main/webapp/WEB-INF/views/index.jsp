<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/menu.css">
<link rel="shortcut icon" href="./img/favicon.ico" type="image/x-icon">
<link rel="icon" href="./img/favicon.ico" type="image/x-icon">	<!--위에 파비콘 넣으려고 했는데 다시 해보기-->
</head>
<body>
<%@ include file="menu.jsp" %>
		
		<div style="text-align:center;"><!--사진 가운데로 -->
		<img alt="" src="./img/login.png" height="300px">	
	</div>
	<div style="text-align:center;">	
	<form action="/login" method="post">
        <label for="username">ID:</label>
        <input type="text" id="username" name="username">
        <br>
        <label for="password">PW:</label>
        <input type="password" id="password" name="password">
        <br>
        <br>
        <button type="submit">들어오세요</button>
        
    </form>	
	</div>

</body>
</html>