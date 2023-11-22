package com.bookshop01.goods.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bookshop01.goods.vo.GoodsVO;

public interface GoodsService {
	
	// 메인 화면에서 조회된 상품 목록
	public Map<String,List<GoodsVO>> listGoods() throws Exception;
	
	// 상품 상세 화면 조회
	public Map goodsDetail(String _goods_id) throws Exception;
	
	// keyword
	public List<String> keywordSearch(String keyword) throws Exception;
	public List<GoodsVO> searchGoods(String searchWord) throws Exception;
}
