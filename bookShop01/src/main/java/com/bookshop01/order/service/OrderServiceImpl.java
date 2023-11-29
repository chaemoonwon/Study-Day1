package com.bookshop01.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import com.bookshop01.order.dao.OrderDAO;
import com.bookshop01.order.vo.OrderVO;



//@Service : 해당 클래스가 비즈니스 로직을 담당하는 서비스 클래스 임을 나타낸다.
//Spring 이 빈으로 등록하고 서비스 계층의 구현체로 활용한다.

//"orderService" IoC 컨테이너에서 해당 빈을 식별할 수 있다.
@Service("orderService")


//트랜잭션 관리를 위해 사용된다.
//propagation=Propagation.REQUIRED 트랜잭션이 필요한 경우 새로운 트랜잭션을 시작하고,
//이미 진행 중인 트랜잭션이 있으면 참여하도록 지정한다.
// = 메서드 실행이 트랜잭션 내에서 수행되어야 함을 알 수 있다.
@Transactional(propagation=Propagation.REQUIRED)

//OrderService 구현
public class OrderServiceImpl implements OrderService {
	
	// @Autowired spring ioc 컨테이너가 자동으로 빈 주입할때 사용
	@Autowired
	//OrderDAO 타입 객체 생성
	private OrderDAO orderDAO;
	
	
	
	
	
	//OrderDAO 를 통해 주문 상품 목록 조회
	public List<OrderVO> listMyOrderGoods(OrderVO orderVO) throws Exception{
		
		//OrderVo 주문과 관련된 정보를 표현하는 자바 객체
		//주문 상품 목록이 OrderVO 객체들의 리스트로 표현된다. 
		// = 여러 주문 상품을 담을 수 있는 리스트를 선언 / 담아서 리턴 때 사용할 예정
		List<OrderVO> orderGoodsList;
		
		

		//orderDAO.listMyOrderGoods(orderVO)
		//orderDAO 안 listMyOrderGoods 메소드 안에 orderVO를 대입해 만든 걸
		
		//orderGoodsList 로 결과물을 대입한다. 
		orderGoodsList=orderDAO.listMyOrderGoods(orderVO);
		
		//조회된 주문 상품 목록 반환
		return orderGoodsList;
	}
	
	
	
	
	
	
	//추가 상품 주문 기능 메소드
	//List<OrderVO> = 새로 추가할 주문에 관한 정보를 담은 OrderVO 객체 포함
	
	//주문을 추가하는 기능이기 때문에 메서드 내부에서 orderDAO를 사용해 데이터베이스에
	//주문 정보를 저장하는 작업이 이루어진다.
	public void addNewOrder(List<OrderVO> myOrderList) throws Exception{
		
		
		
		
		//orderDAO의 insertNewOrder메서드를 이용한다.
		//insertNewOrder = 여러 주문 정보를 받아와 데이터베이스에 
		//새로운 주문을 추가하는 역할
		
		//insertNewOrder 메서드에 myOrderList 대입
		orderDAO.insertNewOrder(myOrderList);
		
		
		//카트에서 주문 상품 제거한다.
		orderDAO.removeGoodsFromCart(myOrderList);
	}	
	
	
	
	
	//주문 ID 기반으로 주문정보를 조회
	public OrderVO findMyOrder(String order_id) throws Exception{
		
		
		//orderDAO = 주문 데이터 액세스 개체
		//MyBatis 데이터 액세스 프레임워크를 사용해
		//주문 정보를 데이터베이스에서 가져오는 역할
		return orderDAO.findMyOrder(order_id);
		
		//findMyOrder 주문 정보를 찾는 기능 실행
		//order_id 매개변수로 주문 ID를 받는다.
		//OrderVO 반환 타입 주문 정보를 나타내는 클래스나 객체 반환
		
		
	}

}
