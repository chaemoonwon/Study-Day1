<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />





<head>


<!--  	 Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">

<link href='//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css' rel='stylesheet' type='text/css'>

<style>
* { font-family: 'Spoqa Han Sans Neo', 'sans-serif'; }
</style>


</head>






<nav>
	<ul>
		<c:choose>
			<c:when test="${side_menu=='admin_mode' }">

				<li>
					<ul class="list-group list-group-flush">
						<li class="list-group-item"
							style="background: darkcyan; color: white;"><b>관리자 기능</b></li>
						<li class="list-group-item"><a
							href="${contextPath}/admin/goods/adminGoodsMain.do">상품관리</a></li>
						<li class="list-group-item"><a
							href="${contextPath}/admin/order/adminOrderMain.do">주문관리</a></li>
						<li class="list-group-item"><a
							href="${contextPath}/admin/member/adminMemberMain.do">회원관리</a></li>
					</ul>
				</li>

			</c:when>



			<c:when test="${side_menu=='my_page' }">

				<li>
					<ul class="list-group list-group-flush">
						<li class="list-group-item"
							style="background: darkcyan; color: white;"><b>주문내역</b></li>
						<li class="list-group-item"><a
							href="${contextPath}/mypage/listMyOrderHistory.do">주문내역<br>배송 조회</a></li>
						<li class="list-group-item"><a href="#">반품/교환<br>신청 및 조회</a></li>
						<li class="list-group-item"><a href="#">취소 내역</a></li>
<!-- 						<li class="list-group-item"><a href="#">세금 계산서</a></li> -->
					</ul>
				</li>



				
				<li>
					<ul class="list-group list-group-flush">
						<li class="list-group-item"
							style="background: darkcyan; color: white;"><b>정보내역</b></li>
						<li class="list-group-item"><a href="${contextPath}/mypage/myDetailInfo.do">회원정보관리</a></li>
						<li class="list-group-item"><a href="#">나의 주소록</a></li>
						<li class="list-group-item"><a href="#">개인정보 동의내역</a></li>
						<li class="list-group-item"><a href="#">회원탈퇴</a></li>
					</ul>
				</li>
				
				
				
			</c:when>




			<c:otherwise>
				<li>
					<ul class="list-group list-group-flush">
						<li class="list-group-item"
							style="background: darkcyan; color: white;"><b>음반</b></li>
						<li class="list-group-item"><a
							href="#">IT/인터넷</a></li>
						<li class="list-group-item"><a href="#">경제/경영</a></li>
						<li class="list-group-item"><a href="#">대학교재</a></li>
						<li class="list-group-item"><a href="#">자기계발</a></li>
						<li class="list-group-item"><a href="#">자연과학/공학</a></li>
						<li class="list-group-item"><a href="#">역사/인문학</a></li>
					</ul>
				</li>

				<div class="clear"></div>

				<li>
					<ul class="list-group list-group-flush">
						<li class="list-group-item"
							style="background: darkcyan; color: white;"><b>음반</b></li>
						<li class="list-group-item"><a href="#">K-POP</a></li>
						<li class="list-group-item"><a href="#">Rock</a></li>
						<li class="list-group-item"><a href="#">Classic</a></li>
						<li class="list-group-item"><a href="#">Jazz</a></li>
					</ul>
				</li>
			</c:otherwise>
		</c:choose>
	</ul>
</nav>








</html>