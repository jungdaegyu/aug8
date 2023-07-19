<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!--core태그를 사용하기 위해 써줌-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="./img/favicon.ico" type="image/x-icon">
<link rel="icon" href="./img/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="./css/menu.css">
<link rel="stylesheet" href="./css/board.css">
</head>
<body>
<%@ include file="menu.jsp" %>
	<h1>보드입니다</h1>
	<div style="text-align:center;"><!--사진 가운데로 -->
		<img alt="" src="./img/imagess.png" height="200px">	
	</div>
	
<table>
	<tr class= "td3">
		<td>번호</td>
		<td>제목</td> 
		<td>글쓴이</td>
		<td>날짜</td>
		<td>좋아요</td>
	</tr>
	
	<c:forEach items="${list}" var="row">
	<!--onclick 자바스크립트 페이지 이동, URL?파라미터=값  -->
		<tr onclick="location.href='./detail?bno=${row.bno}'">
			<td class="td1">${row.bno }</td>
			<td class="title">${row.btitle }</td>
			<td class="td1">${row.bwrite }</td>
			<td class="td2">${row.bdate }</td>
			<td class="td1">${row.blike }</td>	
		</tr>
	</c:forEach>
</table>	
	<div style="text-align:right;">
		<button onclick="location.href='./write'">글쓰기</button>
	</div>	
</body>
</html>