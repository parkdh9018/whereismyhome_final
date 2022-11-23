package com.ssafy.vue.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.vue.model.ApartDealDto;
import com.ssafy.vue.model.HouseDealDto;

public interface HouseDealService {
	//아파트
	public List<HouseDealDto> getApartDeal(Map<String, Object> resultMap) throws SQLException;
	public List<HouseDealDto> getApartDealDetail(Map<String, Object> resultMap) throws SQLException;

	
	//다세대주택
	public List<HouseDealDto> getMultiplexHouseDeal(Map<String, Object> resultMap) throws SQLException;
	public List<HouseDealDto> getMultiplexHouseDealDetail(Map<String, Object> resultMap) throws SQLException;
	
	//오피스텔
	public List<HouseDealDto> getOfficetelDeal(Map<String, Object> resultMap) throws SQLException;
	public List<HouseDealDto> getOfficetelDealDetail(Map<String, Object> resultMap) throws SQLException;


	
	
	
	
}
