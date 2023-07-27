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
<link rel="stylesheet" href="./css/login.css">
<link rel="stylesheet" href="./css/menu.css">
<script type="text/javascript">
let text = "<p>올바른 아이디를 입력하세요.</p>"; //전역변수
//호이스팅이 뭐예요? let vs var? json? const
		
		
function checkID(){
	//alert("!");
	let msg = document.getElementById("msg"); //지역변수
	msg.innerHTML = "<p>" + document.getElementById("id").value + " 아이디를 변경했습니다.</p>";
}


//스크립트 영역
function check(){
	// alert("!");
	let msg = document.getElementById("msg");
	let id = document.getElementById("id");
	// alert(id.value);
	// alert(id.value.length);
	
	if (id.value.length < 3) {
		alert("아이디는 3글자 이상이어야 합니다.");
		msg.innerHTML = text;
		id.focus(); /*아이디 쪽으로 다시 옮겨짐 */
		return false;
	}
	
	let pw = document.getElementById("pw");
	// alert(id.value);
	// alert(id.value.length);
	
	if (pw.value.length < 3) {
		alert("비밀번호는 3글자 이상이어야 합니다.");
		pw.focus(); /*아이디 쪽으로 다시 옮겨짐 */
		return false;
	}
	
}


function check1(){
	// alert("!");
	let id = document.getElementById("id");	
	alert("왼쪽에서 로그인 하세요");
	id.focus(); /*아이디 쪽으로 다시 옮겨짐 */
		
}

</script>

</head>
<body>
<%@ include file="menu.jsp" %>
	<div style="text-align:center;"><!--사진 가운데로 -->
		<img alt="" src="./img/login.png" height="350px">
	
	</div>
	
	<div class="login-form">
	<form action="/login" method="post" onsubmit="return check()">
        <label for="username">아이디:</label>
        <input type="text" id="id" name="id" onchange="checkID()">
        <br>
        <label for="password">비밀번호:</label>
        <input type="password" id="pw" name="pw">
        <br>
        <button type="submit" class="login">LogIn</button>
        <span id="msg"></span> 
    </form>
    	 <button>아이디 찾기</button>
    	 <button>비밀번호 찾기</button>
   		 <a href="./join">회원가입</a>
    </div>
   
    
 
 
 
    
  <form action="/login" method="post">
  <div id="wrap">
   <h1 class="member">member login</h1>
   <div class="form">
    <div class="form2">
     <div class="form3">
      <label for="user">아이디</label><input type="text" id="id" name="id">
      <div class="clear"></div>
      <label for="user">비밀번호</label><input type="password" id="pw" name="pw">
     </div>
     <input type="button" class="login" onclick="check1()" value="로그인하기" >
     <div class="clear"></div>
     <div class="form4">
      <label><input type="checkbox">아이디저장</label> 
       <label><input type="checkbox">보안접속</label>
      <div class="clear"></div>
      <label><input type="button" value="회원가입"></label> 
       <label><input type="button" value="아이디/비밀번호 찾기"></label>
     </div>
    </div>
   </div>
  </div>
 </form>
 
 
 

ㅣ
</body>


</html>