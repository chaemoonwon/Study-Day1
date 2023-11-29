package com.bookshop01.order.service;

import java.util.List;
import java.util.Map;

import com.bookshop01.order.vo.OrderVO;

public interface OrderService {
	
	
	//사용자가 주문한 상품 목록 조회
	//List<OrderVO> 반환 타입 / 주문한 상품 목록을 담은 OrderVO 객체의 리스트 반환
	public List<OrderVO> listMyOrderGoods(OrderVO orderVO) throws Exception;

	
	
	//새로운 주문 추가
	//새로운 주문 정보를 담은 OrderVO 객체의 리스트(List<OrderVO>)를 매개변수로 받는다.
	public void addNewOrder(List<OrderVO> myOrderList) throws Exception;

	
	
	//주문 ID를 기반으로 특정 주문을 찾는다.
	//특정 주문 정보를 담은 OrderVO 객체 반환 
	//String order_id 찾고자하는 주문의 ID를 매개변수로 받는다.
	public OrderVO findMyOrder(String order_id) throws Exception;
	
	
}
