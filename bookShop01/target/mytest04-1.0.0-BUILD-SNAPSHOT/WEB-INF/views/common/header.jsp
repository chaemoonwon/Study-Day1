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


</head>






<script type="text/javascript">
	var loopSearch=true;
	function keywordSearch(){
		if(loopSearch==false)
			return;
	 var value=document.frmSearch.searchWord.value;
		$.ajax({
			type : "get",
			
			
			async : true, //false인 경우 동기식으로 처리한다.
			url : "${contextPath}/goods/keywordSearch.do",
			data : {keyword:value},
			success : function(data, textStatus) {
			    var jsonInfo = JSON.parse(data);
				displayResult(jsonInfo);
			},
			error : function(data, textStatus) {
				alert("에러가 발생했습니다."+data);
			},
			complete : function(data, textStatus) {
				//alert("작업을완료 했습니다");
				
			}
		}); //end ajax	
	}
	
	function displayResult(jsonInfo){
		var count = jsonInfo.keyword.length;
		if(count > 0) {
		    var html = '';
		    for(var i in jsonInfo.keyword){
			   html += "<a href=\"javascript:select('"+jsonInfo.keyword[i]+"')\">"+jsonInfo.keyword[i]+"</a><br/>";
		    }
		    var listView = document.getElementById("suggestList");
		    listView.innerHTML = html;
		    show('suggest');
		}else{
		    hide('suggest');
		} 
	}
	
	function select(selectedKeyword) {
		 document.frmSearch.searchWord.value=selectedKeyword;
		 loopSearch = false;
		 hide('suggest');
	}
		
	function show(elementId) {
		 var element = document.getElementById(elementId);
		 if(element) {
		  element.style.display = 'block';
		 }
		}
	
	function hide(elementId){
	   var element = document.getElementById(elementId);
	   if(element){
		  element.style.display = 'none';
	   }
	}

</script>






<body>

	<!-- 헤더 북토피아 로고 사진 -->
	<div id="logo">
		<a href="${contextPath}/main/main.do"> <img width="176"
			height="80" alt="booktopia"
			src="${contextPath}/resources/image/Booktopia_Logo.jpg">
		</a>
	</div>



	<div id="head_link">
		<ul>
		
			<!-- choose / switch 문 -->
			<!-- when / case 문 -->
			<!-- ohterwise / default 문 -->
			<c:choose>

				<%-- 일반 고객 로그인 --%>
   				 <%-- isLogOn 변수가 true 이고 memberInfo 변수가 비어있지 않은 경우 --%>
				<c:when test="${isLogOn==true and not empty memberInfo }">
					<div class="btn-group btn-group-sm" role="group" aria-label="Small button group">
						<button type="button" class="btn btn-outline-primary"
							onclick="location.href='${contextPath}/member/logout.do'">로그아웃</button>
						<button type="button" class="btn btn-outline-primary"
							onclick="location.href='${contextPath}/mypage/myPageMain.do'">마이페이지</button>
						<button type="button" class="btn btn-outline-primary"
							onclick="location.href='${contextPath}/cart/myCartList.do'">장바구니</button>


						<!-- 주문배송 구현안됨 -->
						<button type="button" class="btn btn-outline-primary"
							onclick="location.href='#'">주문배송</button>
						<!-- 고객센터도 아직 구현안됨 구현하면 링크바구기 아니 왜 시프트키가 안먹지 -->
						<button type="button" class="btn btn-outline-primary"
							onclick="location.href='#'">고객센터</button>
					</div>
				</c:when>



				<c:otherwise>
					<!-- 기본 로그인 전 디폴트 화면 -->
					<div class="btn-group btn-group-sm" role="group"
						aria-label="Small button group">
						<button type="button" class="btn btn-outline-primary"
							onclick="location.href='${contextPath}/member/loginForm.do'">로그인</button>
						<button type="button" class="btn btn-outline-primary"
							onclick="location.href='${contextPath}/member/memberForm.do'">회원가입</button>
						<button type="button" class="btn btn-outline-primary"
							onclick="location.href='#'">고객센터</button>
					</div>
				</c:otherwise>
			</c:choose>



			<!-- 어드민 접속 -->
			<c:if test="${isLogOn==true and memberInfo.member_id =='admin' }">
				<li class="no_line">
				
				<div class="btn-group btn-group-sm" role="group" aria-label="Small button group">
				
				<button type="button" class="btn btn-outline-primary"
							onclick="location.href='${contextPath}/admin/goods/adminGoodsMain.do'">관리자 제어</button>
				
				</div>
				</li>
			</c:if>

		</ul>




	</div>


	<br>











	<!-- 검색창!!!!!!!!! -->
	<div id="search">
		<form name="frmSearch" action="${contextPath}/goods/searchGoods.do">
			<input name="searchWord" class="main_input" type="text"
				onKeyUp="keywordSearch()"> <input type="submit"
				name="search" class="btn1" value="검 색">
		</form>
	</div>

	<div id="suggest">
		<div id="suggestList"></div>
	</div>

</body>
</html>