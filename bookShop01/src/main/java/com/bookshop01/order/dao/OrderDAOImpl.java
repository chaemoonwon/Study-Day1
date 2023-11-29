package com.bookshop01.order.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.bookshop01.order.vo.OrderVO;



//Spring에서 데이터 액세스 계층(DAO)의 구현체
//해당 클래스가 데이터베이스와의 상호작용을 담당하는 DAO 클래스임을 나타내고,
//빈으로 등록될 것임을 의미한다.

//"orderDAO"은 빈의 이름으로 사용 / Spring 컨테이너에서 해당 빈 참조 가능

//데이터 베이스와의 통신 담당!!!!!
//SQL 쿼리 실행하고 결과 반환하는 역할!!!!!!!!!!!!
@Repository("orderDAO")
public class OrderDAOImpl implements OrderDAO {
	
	
	//Spring 프레임워크에서 해당 필드를 자동으로 주입하도록 지정하는 역할
	@Autowired
	
	//SqlSession = MyBatis와 관련 / 데이터베이스와의 상호작용을 간소화하기 위한 자바 ORM
	//SqlSession Mybatis에서 SQL을 실행하기 위한 주요한 인터페이스
	private SqlSession sqlSession;
	
	
	
	public List<OrderVO> listMyOrderGoods(OrderVO orderVO) throws DataAccessException{
		List<OrderVO> orderGoodsList=new ArrayList<OrderVO>();
		orderGoodsList=(ArrayList)sqlSession.selectList("mapper.order.selectMyOrderList",orderVO);
		return orderGoodsList;
	}
	
	
	
	
	
	
	
	//여러 주문 정보를 받아와 데이터베이스에 새로운 주문으로 추가하는 역할
	public void insertNewOrder(List<OrderVO> myOrderList) throws DataAccessException{
		
		
		//새로운 주문 ID를 생성하기
		//selectOrderID 메서드는 새로운 주문 ID를 생성하는 역할
		int order_id=selectOrderID();
		
		
		//주문 리스트를 순회하며 각 주문 정보에 새로운 주문 ID를 설정하고 데이터베이스에 추가
		for(int i=0; i<myOrderList.size();i++){
			
			//.get (컬렉션)
			OrderVO orderVO =(OrderVO)myOrderList.get(i);
			orderVO.setOrder_id(order_id);
			
			
			//MyBatis 사용 - 데이터베이스에 새로운 주문 정보를 추가하는 부분
			
			//mapper.order.insertNewOrder 는 MyBatis SQL매퍼 파일에서
			//해당 SQL쿼리를 찾아 실행하는데 사용되는 식별자
			sqlSession.insert("mapper.order.insertNewOrder",orderVO);
		}
		
	}	
	
	
	
	
	
	
	public OrderVO findMyOrder(String order_id) throws DataAccessException{
		OrderVO orderVO=(OrderVO)sqlSession.selectOne("mapper.order.selectMyOrder",order_id);		
		return orderVO;
	}
	
	public void removeGoodsFromCart(OrderVO orderVO)throws DataAccessException{
		sqlSession.delete("mapper.order.deleteGoodsFromCart",orderVO);
	}
	
	public void removeGoodsFromCart(List<OrderVO> myOrderList)throws DataAccessException{
		for(int i=0; i<myOrderList.size();i++){
			OrderVO orderVO =(OrderVO)myOrderList.get(i);
			sqlSession.delete("mapper.order.deleteGoodsFromCart",orderVO);		
		}
	}	
	private int selectOrderID() throws DataAccessException{
		return sqlSession.selectOne("mapper.order.selectOrderID");
		
	}
}

