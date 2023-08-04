<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세보기</title>
<link rel="shortcut icon" href="./img/favicon.ico" type="image/x-icon">
<link rel="icon" href="./img/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="./css/menu.css">
<link rel="stylesheet" href="./css/detail.css">
<script src="./js/jquery-3.7.0.min.js"></script>
<script type="text/javascript"> /* 자바스크립트 메소드를 사용하기 위해 적어줌 */
	function edit(){// 7월 20일
		if (confirm("수정하시겠어요?")) {
			location.href="./edit?bno=${dto.bno}";
		}	
}
	
	function del(){
		let chk = confirm("삭제하시겠어요?") // 삭제하기 눌렀을때 삭제할지말지 콘솔 창이 나옴..참 거짓으로 나옵니다.
		//alert(chk);
		
		if(chk){
			location.href="./delete?bno=${dto.bno}"; //페이지 이동		
		}		
} 

	$(function(){
		$(".commentBox").hide();
		$("#openComment").click(function(){
			$(".commentBox").show('slow');
			$("#openComment").remove();
		});
	});
	
	
</script>
</head>
<body>


<%@ include file="menu.jsp" %> <!--메뉴를 계속 위에 띄워놓기 위해 -->
<h1>디테일이다</h1>
	<div class="detail-content">
		<div class="title">
		${dto.bno } - ${dto.btitle } 
		<c:if test="${sessionScope.mid ne null && sessionScope.mid eq dto.m_id}"> <!--7.24 로그인 한 사람만 수정하고 삭제할 수 있게 만들어주는거임-->
		<img alt="" src="./img/update1.png" onclick="edit()">&nbsp;<img alt="" src="./img/delete1.png" onclick="del()">	
		</c:if>
		
		</div>
		<div class="name-bar">
			<div class="name">${dto.m_name }님</div>
			<div class="like">${dto.blike }</div>
			<div class="date">${dto.bdate }</div>
			<div class="ip">${dto.bip}</div>
		</div>
		<div class="content">${dto.bcontent }</div>
	

	<div class="commentsList">
	<c:choose>
		<c:when test="${fn:length(commentsList) gt 0}">
		<div class="comments">
			<c:forEach items="${commentsList}" var="c">
			<div class="comment">
			<div class="commentHead">
				<div class="cname">${c.m_name}(${c.m_id})</div>
				<div class="cdate">${c.c_date}</div>
				<div class="cid">${c.c_no}</div>
			</div>
			<div class="commentBody">${c.c_comment } </div>
			</div>
			</c:forEach>
		</div>
		</c:when> 
		<c:otherwise>
		<div><h2>댓글이 없습니다.</h2></div>
		</c:otherwise>	
	</c:choose>
	</div>

	<c:if test="${sessionScope.mid ne null}">
	<button type="button" id="openComment">설레는 댓글창 열기</button>
	<div class="commentBox">
			<form action="./comment" method="post">
				<textarea id="commenttextarea" name="comment" placeholder="댓글을 입력하세요."></textarea>
				<button type="submit" id="comment">글쓰기</button>
				<input type="hidden" name="bno" value="${dto.bno }">
		</form>
	</div>
	</c:if>
</div>
	
	
	
	
	
	
</body>
</html>