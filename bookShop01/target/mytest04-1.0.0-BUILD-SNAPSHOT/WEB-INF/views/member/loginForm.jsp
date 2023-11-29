<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html >
<html>






<head>





<!--  	 Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">




<meta http-equiv="Content-Type" content="text/html; charset=utf-8">



<c:if test='${not empty message }'>
	<script>
		window.onload=function()
		{
		  result();
		}
		
			function result(){
				alert("아이디나  비밀번호가 틀립니다. 다시 로그인해주세요");
		}
	</script>
</c:if>








</head>





<body>
	<H2>로그인</H2>
	
	
	<!-- 기존 -->
	<DIV id="detail_table">
	
	
		<form action="${contextPath}/member/login.do" method="post">
			<TABLE>
				<TBODY>
				
				
				
		<!-- label 을 이용해서 비밀번호라는 글자를 클릭하면 아이디 입력창으로 자동 이동 -->			
					<TR class="solid_line">
						<TD class="fixed_join">
				        	<label for="inputID2">아이디</label>
				        </TD>
						<TD>
							<input name="member_id" type="text" class="form-control" id="inputID2" size="50" placeholder="아이디를 입력해주세요."/>
						</TD>						
					</TR>
					
					
		<!-- label 을 이용해서 비밀번호라는 글자를 클릭하면 패스워드 입력창으로 자동 이동 -->			
		<TR class="solid_line">
		    <TD class="fixed_join">
		        <label for="inputPassword2">비밀번호</label>
		    </TD>
		    <TD>
		        <input name="member_pw" type="password" class="form-control" id="inputPassword2" size="50" placeholder="비밀번호를 입력해주세요." />
		    </TD>
		</TR>


					
				</TBODY>
			</TABLE>
			
			
			
			
			<!-- 부트 스트랩  -->
<!-- 			<form class="row g-3">
			  <div class="col-auto">
			    <label for="staticEmail2" class="visually-hidden">Email</label>
			    <input type="text" readonly class="form-control-plaintext" id="staticEmail2" value="email@example.com">
			  </div>
  
  <div class="col-auto">
    <label for="inputPassword2" class="visually-hidden">Password</label>
    <input type="password" class="form-control" id="inputPassword2" placeholder="Password">
  </div>
  
  
  <div class="col-auto">
    <button type="submit" class="btn btn-primary mb-3">Confirm identity</button>
  </div>
</form> -->
			
			
			
			
			
			
			
			<!-- 확인해보기  -->
			<!-- <button type="button" class="btn btn-success">Success</button> -->
			
			
			<br> <br>
			
			
			<button type="submit" class="btn btn-primary mb-3">로그인</button>
			 
			<!-- <INPUT type="submit" class="btn btn-success" value="로그인"> 
			<INPUT type="button" class="btn btn-success" value="초기화"> --> 
			<Br> <br> 
			<a href="#">아이디찾기</a> | <a href="#">비밀번호 찾기</a> | <a
				href="${contextPath}/member/addMember.do">회원가입</a> | <a href="#">고객
				센터</a>

		</form>
</body>
</html>