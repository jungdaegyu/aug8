<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!--core태그를 사용하기 위해 써줌-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 화면</title>
<link rel="shortcut icon" href="./img/favicon.ico" type="image/x-icon">
<link rel="icon" href="./img/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="./css/menu.css">
<link rel="stylesheet" href="./css/board.css">

</head>
<body>
<%@ include file="menu.jsp" %>
	<div style="text-align:center;"><!--사진 가운데로 -->
		<img alt="" src="./img/login.png" height="350px">
	
	</div>
	<form action="/login" method="post">
        <label for="username">아이디:</label>
        <input type="text" id="username" name="username">
        <br>
        <label for="password">비밀번호:</label>
        <input type="password" id="password" name="password">
        <br>
        <button type="submit">Log In</button>
    </form>
    
    
  <form>
  <div id="wrap">
   <h1 class="member">member login</h1>
   <div class="form">
    <div class="form2">
     <div class="form3">
      <label for="user">아이디</label><input type="text" id="user">
      <div class="clear"></div>
      <label for="user">비밀번호</label><input type="password" id="user">
     </div>
     <input type="submit" value="로그인하기">
     <div class="clear"></div>
     <div class="form4">
      <label><input type="checkbox">아이디저장</label> <label><input
       type="checkbox">보안접속</label>
      <div class="clear"></div>
      <label><input type="button" value="회원가입"></label> <label><input
       type="button" value="아이디/비밀번호 찾기"></label>
     </div>
    </div>
   </div>
  </div>
 </form>
    
    
ㅣ
</body>


</html>