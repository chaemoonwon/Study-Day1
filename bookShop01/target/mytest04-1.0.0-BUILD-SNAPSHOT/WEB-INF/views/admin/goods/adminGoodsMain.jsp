<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html >
<html>
<head>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">


<meta charset="utf-8">
<script>
function search_goods_list(fixeSearchPeriod){
	var formObj=document.createElement("form");
	var i_fixedSearch_period = document.createElement("input");
	i_fixedSearch_period.name="fixedSearchPeriod";
	i_fixedSearch_period.value=searchPeriod;
    formObj.appendChild(i_fixedSearch_period);
    document.body.appendChild(formObj); 
    formObj.method="get";
    formObj.action="${contextPath}/admin/goods/adminGoodsMain.do";
    formObj.submit();
}


function  calcPeriod(search_period){
	var dt = new Date();
	var beginYear,endYear;
	var beginMonth,endMonth;
	var beginDay,endDay;
	var beginDate,endDate;
	
	endYear = dt.getFullYear();
	endMonth = dt.getMonth()+1;
	endDay = dt.getDate();
	if(search_period=='today'){
		beginYear=endYear;
		beginMonth=endMonth;
		beginDay=endDay;
	}else if(search_period=='one_week'){
		beginYear=dt.getFullYear();
		beginMonth=dt.getMonth()+1;
		dt.setDate(endDay-7);
		beginDay=dt.getDate();
		
	}else if(search_period=='two_week'){
		beginYear = dt.getFullYear();
		beginMonth = dt.getMonth()+1;
		dt.setDate(endDay-14);
		beginDay=dt.getDate();
	}else if(search_period=='one_month'){
		beginYear = dt.getFullYear();
		dt.setMonth(endMonth-1);
		beginMonth = dt.getMonth();
		beginDay = dt.getDate();
	}else if(search_period=='two_month'){
		beginYear = dt.getFullYear();
		dt.setMonth(endMonth-2);
		beginMonth = dt.getMonth();
		beginDay = dt.getDate();
	}else if(search_period=='three_month'){
		beginYear = dt.getFullYear();
		dt.setMonth(endMonth-3);
		beginMonth = dt.getMonth();
		beginDay = dt.getDate();
	}else if(search_period=='four_month'){
		beginYear = dt.getFullYear();
		dt.setMonth(endMonth-4);
		beginMonth = dt.getMonth();
		beginDay = dt.getDate();
	}
	
	if(beginMonth <10){
		beginMonth='0'+beginMonth;
		if(beginDay<10){
			beginDay='0'+beginDay;
		}
	}
	if(endMonth <10){
		endMonth='0'+endMonth;
		if(endDay<10){
			endDay='0'+endDay;
		}
	}
	endDate=endYear+'-'+endMonth +'-'+endDay;
	beginDate=beginYear+'-'+beginMonth +'-'+beginDay;
	//alert(beginDate+","+endDate);
	return beginDate+","+endDate;
}
</script>
</head>
<body>
	<H3>상품 조회</H3>
	<form method="post">
		<TABLE cellpadding="10" cellspacing="10">
			<TBODY>
				<TR>
					<TD style="padding-top: 15px; padding-bottom: 15px;">
					
					<div class="form-check">
					  <input class="form-check-input" type="radio" name="r_search" id="flexRadioDefault2" checked>
					  <label class="form-check-label" for="flexRadioDefault1">
					    등록일로조회
					  </label>
					</div>
					<div class="form-check">
					  <input class="form-check-input" type="radio" name="r_search" id="flexRadioDefault1">
					  <label class="form-check-label" for="flexRadioDefault2">
					    상세조회
					  </label>
					</div>
				</TR>
				
				
				
				<TR>
					<TD><select name="curYear" style="width:65px;">
							<c:forEach var="i" begin="0" end="5">
								<c:choose>
									<c:when test="${endYear==endYear-i}">
										<option value="${endYear}" selected>${endYear}</option>
									</c:when>
									<c:otherwise>
										<option value="${endYear-i }">${endYear-i }</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
					</select>
					
					
					년 <select name="curMonth" style="width: 50px;">
							<c:forEach var="i" begin="1" end="12">
								<c:choose>
									<c:when test="${endMonth==i }">
										<option value="${i }" selected>${i }</option>
									</c:when>
									<c:otherwise>
										<option value="${i }">${i }</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
					</select>
					
					
					월 <select name="curDay" style="width:50px;">
							<c:forEach var="i" begin="1" end="31">
								<c:choose>
									<c:when test="${endDay==i}">
										<option value="${i }" selected>${i }</option>
									</c:when>
									<c:otherwise>
										<option value="${i }">${i }</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>

					</select>
						일 &nbsp; ~ &nbsp;&nbsp;&nbsp;&nbsp;
						<div class="btn-group" role="group"
							aria-label="Basic outlined example">
							<button type="button" class="btn btn-outline-primary"
								onclick="search_goods_list('today')">당일</button>
							<button type="button" class="btn btn-outline-primary"
								onclick="search_goods_list('one_week')">1주</button>
							<button type="button" class="btn btn-outline-primary"
								onclick="search_goods_list('two_week')">2주</button>
							<button type="button" class="btn btn-outline-primary"
								onclick="search_goods_list('one_month')">1개월</button>
							<button type="button" class="btn btn-outline-primary"
								onclick="search_goods_list('two_month')">2개월</button>
							<button type="button" class="btn btn-outline-primary"
								onclick="search_goods_list('three_month')">3개월</button>
							<button type="button" class="btn btn-outline-primary"
								onclick="search_goods_list('four_month')">4개월</button>
						</div> 
						 &nbsp; <br></TD>
				</TR>

				<br>

				<tr>
				  <td style="padding-top: 15px; padding-bottom: 15px;  ">
				    <select name="search_condition">
				      <option value="전체" checked>전체</option>
				      <option value="제품번호">상품번호</option>
				      <option value="제품이름">상품이름</option>
				      <option value="제조사">제조사</option>
				    </select>
				    <input type="text" size="30"  />
					<button type="button" class="btn btn-outline-dark">조회</button>

				  </td>
				</tr>
				
				<tr>
				  <td style="padding-top: 15px; padding-bottom: 15px;">
				    조회한 기간:<input type="text" size="4" value="${beginYear}" />년
				    <input type="text" size="4" value="${beginMonth}" />월
				    <input type="text" size="4" value="${beginDay}" />일 &nbsp; ~
				    <input type="text" size="4" value="${endYear}" />년
				    <input type="text" size="4" value="${endMonth}" />월
				    <input type="text" size="4" value="${endDay}" />일
				  </td>
				</tr>	
			</TBODY>
		</TABLE>
		</DIV>
	</form>

	<br>
	<br>

	<DIV class="clear"></DIV>
	<TABLE class="list_view">
		<TBODY align=center>
			<tr style="background-color:#009688; color: white;">
				<td>상품번호</td>
				<td>상품이름</td>
				<td>저자</td>
				<td>출판사</td>
				<td>상품가격</td>
				<td>입고일자</td>
				<td>출판일</td>
			</tr>
			<c:choose>
				<c:when test="${empty newGoodsList }">
					<TR>
						<TD colspan=8 class="fixed"><strong>조회된 상품이 없습니다.</strong></TD>
					</TR>
				</c:when>
				<c:otherwise>
					<c:forEach var="item" items="${newGoodsList }">
						<TR>
							<TD><strong>${item.goods_id }</strong></TD>
							<TD><a
								href="${pageContext.request.contextPath}/admin/goods/modifyGoodsForm.do?goods_id=${item.goods_id}">
									<strong>${item.goods_title } </strong>
							</a></TD>
							<TD><strong>${item.goods_writer }</strong></TD>
							<TD><strong>${item.goods_publisher }</strong></TD>
							<td><strong>${item.goods_sales_price }</strong></td>
							<td><strong>${item.goods_credate }</strong></td>
							<td><c:set var="pub_date"
									value="${item.goods_published_date}" /> <c:set var="arr"
									value="${fn:split(pub_date,' ')}" /> <strong> <c:out
										value="${arr[0]}" />
							</strong></td>

						</TR>


					</c:forEach>
				</c:otherwise>
			</c:choose>
			<tr>
				<td colspan=8 class="fixed"><c:forEach var="page" begin="1"
						end="10" step="1">
						<c:if test="${section >1 && page==1 }">
							<a
								href="${contextPath}/admin/goods/adminGoodsMain.do?chapter=${section-1}&pageNum=${(section-1)*10 +1 }">&nbsp;
								&nbsp;</a>
						</c:if>
						<a
							href="${contextPath}/admin/goods/adminGoodsMain.do?chapter=${section}&pageNum=${page}">${(section-1)*10 +page }
						</a>
						<c:if test="${page ==10 }">
							<a
								href="${contextPath}/admin/goods/adminGooodsMain.do?chapter=${section+1}&pageNum=${section*10+1}">&nbsp;
								next</a>
						</c:if>
					
					</c:forEach>
		</TBODY>

	</TABLE>
	<DIV class="clear"></DIV>
	<br>
	<br>
	<br>
	<DIV id="search">
		<form action="${contextPath}/admin/goods/addNewGoodsForm.do">

			<div class="d-grid gap-2 col-6 mx-auto">
				<button class="btn btn-primary" type="submit">상품 등록하기</button>
			</div>
		</form>

	</DIV>
</body>
</html>