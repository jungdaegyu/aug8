<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!--core태그를 사용하기 위해 써줌-->
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    <%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="./img/favicon.ico" type="image/x-icon">
<link rel="icon" href="./img/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="./css/menu.css">
<link rel="stylesheet" href="./css/board.css">
<script type="text/javascript">
	function linkPage(pageNo){
		location.href = "./board?pageNo="+pageNo;
	}	
</script>
</head>
<body>
<%@ include file="menu.jsp" %>
	<h1>게시판입니다.</h1>
	${paginationInfo} 
		<div style="text-align:center;"><!--사진 가운데로 -->
		<img alt="" src="./img/happy.png" height="200px">	
		</div>
	<%-- 길이 검사 : ${fn:length(list) } --%>
	<c:choose>
		<c:when test="${fn:length(list) gt 0 }">
		<table>
	<tr class= "td3">
		<td>번호</td>
		<td>제목</td> 
		<td>글쓴이</td>
		<td>날짜</td>
		<td>관심</td>
	</tr>
	
	<c:forEach items="${list}" var="row">
	<!--onclick 자바스크립트 페이지 이동, URL?파라미터=값  -->
		<tr onclick="location.href='./detail?bno=${row.bno}'">
			<td class="td1">${row.bno }</td>
			<td class="title">${row.btitle }  
				<small>
					<c:if test="${row.commentcount gt 0 }"> (${row.commentcount })</c:if>
				</small>
			</td>
			<td class="td1">${row.m_name }</td>
			<td class="td2">${row.bdate }</td>
			<td class="td1">${row.blike }</td>	
		</tr>
	</c:forEach>
</table>

<div class="page-div">
	<ui:pagination paginationInfo = "${paginationInfo}"	type="image" jsFunction="linkPage"/>
</div>
	</c:when>
		<c:otherwise><h1>출력할 데이터가 없습니다.</h1></c:otherwise>
	</c:choose>
	
	<!--로그인 했다면 글쓰기 버튼이 보이게 만드는거-->
	로그인한 이름 : ${sessionScope.mname } / ${sessionScope.mid }
	<c:if test="${sessionScope.mname ne null}">
			<button onclick="location.href='./write'">글쓰기</button>
	</c:if>

	

	
</body>
</html>