package com.bookshop01.admin.goods.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;



//관리자 전용 굿즈 컨트롤러
public interface AdminGoodsController {
	
	
	//ModelAndView 반환 타입을 가지고 있는 adminGoodsMain 추상 메서드
	//관리자 굿즈페이지의 메인을 실행한다.
	
	//@RequestParam 웹 요청의 파라미터를 메서드의 매개변수로 바인딩
	//Map<String, String> dateMap   Http 파라미터를 저장하는 맵 / dataMap 이라는 이름으로 맵을 받아오는 중
	//Http 파라미터의 수나 이름이 동적으로 변하는 경우 사용 Map
 	public ModelAndView adminGoodsMain(@RequestParam Map<String, String> dateMap,HttpServletRequest request, HttpServletResponse response)  throws Exception;
	
	
	
	
	//ResponseEntity 반환 타입을 가지고 있는 addNewGoods 추상메서드
	//관리자 새로운 굿즈 추가 기능을 실행한다.
	
	//ResponseEntity - Spring Framework에서 사용하는 http 응답을 나타내는 클래스 / 응답을 커스터마이징(응답의 상태 코드, 헤더, 본문 등 제어) 할 수 있다.
	//MultipartHttpServletRequest 멀티파트 요청 처리 / 파일 업로드와 같이 여러 종류의 데이터를 전송할 때 사용한다.
	public ResponseEntity addNewGoods(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)  throws Exception;
	
	
	
	
	//ResponseEntity 반환 타입을 가지고 있는 modifyGoodsInfo(상품 수정) 추상 메서드
	//상품에 대한 정보 수정을 실행한다.	
	public ResponseEntity modifyGoodsInfo( 
			
								//@RequestParam Http 요청을 파라미터로 받아온다.
								//특정한 파라미터만을 명시적으로 받아와야하는 경우
								// http 요청에서 "goods_id" 라는 이름의 파라미터를 추출해 이 변수에 할당 
								// goods_id 변수명
								 @RequestParam("goods_id") String goods_id,
                                 @RequestParam("mod_type") String mod_type,
                                 @RequestParam("value") String value,
			                     HttpServletRequest request, HttpServletResponse response)  throws Exception;
	
	
	
	
	
//	-----------------------------------------------------------------------------------------------
	// 반환 타입이 void인 경우
	// 어떠한 동작을 실행한다고 해서 클라이언트에게 그것을 알려주는 응답을 보내지 않는다.
	// 대부분 데이터 베이스 만의 수정을 위해 void 반환 타입을 사용한다.
	
	// void = 클라이언트에게 알려줄 정보 없음
	// ResponseEntity = 클라이언트에게 알려줄 정보 있음
	
	
	
	//반환 타입이 없는 굿즈 이미지 삭제 실행 추상메서드
	
	public void  removeGoodsImage(
			
			//@RequestParam("goods_id") int goods_id 
			//- 특정한 굿즈 아이디 값을 명시적으로 받아와야한다.(선택한 이미지만 삭제되어야하기 때문) 
			@RequestParam("goods_id") int goods_id,
			
			//- 위와 같이 특정한 굿즈 이미지의 값을 명시적으로 받아와야한다.
            @RequestParam("image_id") int image_id,
            
            // - 위와 같이 특정한 굿즈 이미지 파일의 이름을 명시적으로 받아와야한다.
            @RequestParam("imageFileName") String imageFileName,
            HttpServletRequest request, HttpServletResponse response)  throws Exception;
	
	
	
	//새로운 굿즈 이미지의 추가 기능 실행
	//반환 타입이 void 인 것을 제외 - 처음 설정하는 굿즈 이미지 추가 기능과 비슷
	//해당 파일 업로드와 관련된 정보를 처리한 후, 특별한 응답을 처리해 클라이언트에게 보낼 필요가 없음
	public void  addNewGoodsImage(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)  throws Exception;
	
	
	
	//굿즈 이미지 정보를 수정하는 기능 실행
	//업로드 된 이미지 파일과 관련된 정보를 MultipartHttpServletRequest를 통해 받아와 처리하고
	//필요에 따라 데이터베이스에 수정된 정보를 반영한다.
	public void modifyGoodsImageInfo(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)  throws Exception;
}
