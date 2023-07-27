<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!--core태그를 사용하기 위해 써줌-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="shortcut icon" href="./img/favicon.ico" type="image/x-icon">
<link rel="icon" href="./img/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="./css/menu.css">
<link rel="stylesheet" href="./css/join.css">
</head>
<body>
<%@ include file="menu.jsp" %>
<div>
	<h1>회원가입 창입니다.</h1>
	<div>
		<div>아이디</div>
		<div>
			<input type="text" name="id"> 
		</div>	
</div>
	<div>
		<div>비밀번호</div>
		<div>
			<input type="password" name="pw1"> 
			<input type="password" name="pw2"> <!--비밀번호 확인용도-->
		</div>	
	</div>	
	
	<div>
		<div>이름</div>
		<div><input type="text" name="name"> </div>
	</div>
	
	<div>	
		<div>주소</div>
		<div><input type="text" name="addr"> </div>	
	</div>
	

	<div>
	 MBTI
	 <br>
	<select name="mbti1">
		<option value="ESTJ"> ESTJ </option> <option value="ESFJ"> ESFJ </option> <option value="ISTJ"> ISTJ </option> <option value="ISFJ"> ISFJ </option>
		<option value="ESTP"> ESTP </option> <option value="ESFP"> ESFP </option> <option value="ISTP"> ISTP </option> <option value="ISFP"> ISFP </option>
		<option value="ENFP"> ENFP </option> <option value="ENFJ"> ENFJ </option> <option value="INFP"> INFP </option> <option value="INFJ"> INFJ </option>
		<option value="ENTP"> ENTP </option> <option value="ENTJ"> ENTJ </option> <option value="INTP"> INTP </option> <option value="INTJ"> INTJ </option>
	</select>	
	</div>	

	<div>
		<div>생년월일</div>
		<div><input type="date" name="birth"> </div>
	</div>
	
	<div>
		<div>성별</div>
		<div><input type="text" name="id"> </div>	
	</div>
		
		<button type="reset">가입 NO</button>
		<button type="submit">가입 YES</button>
	
	</div>


</body>
</html>