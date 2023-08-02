<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--core태그를 사용하기 위해 써줌-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="./img/favicon.ico" type="image/x-icon">
<link rel="icon" href="./img/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="./css/menu.css">
<link rel="stylesheet" href="./css/board.css">
<script src="./js/jquery-3.7.0.min.js"></script> 
<script type="text/javascript">
$(function(){
	//보드 내용 가져오기
	var lists = [];
	$.ajax({
		url: "./boardList2",
		type: "get",
		data: {"pageNO": 1},
		dataType: "json",
		success: function(data){
			alert("정상입니다. : " + data);			
		},
		error:function(error){
			alert("에러가 발생했습니다. : " + error);
		}
	});
	
	const list = [
		{bno:10, btitle:'제목입니다', m_name : '홍길동', bdate : '2023-08-02', blike : 15},
		{bno:9, btitle:'제목입니다', m_name : '홍길동', bdate : '2023-08-02', blike : 15},
		{bno:8, btitle:'제목입니다', m_name : '홍길동', bdate : '2023-08-02', blike : 15},
		{bno:7, btitle:'제목입니다', m_name : '홍길동', bdate : '2023-08-02', blike : 15},
		{bno:6, btitle:'제목입니다', m_name : '홍길동', bdate : '2023-08-02', blike : 15},
		{bno:5, btitle:'제목입니다', m_name : '홍길동', bdate : '2023-08-02', blike : 15},
		{bno:4, btitle:'제목입니다', m_name : '홍길동', bdate : '2023-08-02', blike : 15},
		{bno:3, btitle:'제목입니다', m_name : '홍길동', bdate : '2023-08-02', blike : 15},
		{bno:2, btitle:'제목입니다', m_name : '홍길동', bdate : '2023-08-02', blike : 15},
		{bno:1, btitle:'제목입니다', m_name : '홍길동', bdate : '2023-08-02', blike : 15}
		]; 
	for(let i = 0; i < list.length; i++){
		$(".tableHead").append("<tr><td>"+list[i].bno+"</td><td>"+list[i].btitle+
			"</td><td>"+list[i].m_name+"</td><td>"+list[i].bdate+"</td><td>"+list[i].blike+"</td></tr>");		
	}
});
</script>
</head>
<body>
<%@ include file="menu.jsp" %>
<div class="board-div">
<h1>보드2</h1>
<table class="tableHead">
	<tr class="title">
		<th class="td1">번호</th>
		<th>제목</th>
		<th class="td1">글쓴이</th>
		<th class="td2">날짜</th>
		<th class="td1">읽음</th>
	</tr>
</table>
</div>
</body>
</html>