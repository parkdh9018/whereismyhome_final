package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.vue.model.ApartDealDto;
import com.ssafy.vue.model.HouseDealDto;

public interface HouseDealMapper {

	// 단독다가구 
	public List<HouseDealDto> listPrivateHouseDeal();
	
	// 다세대 주택
	public List<HouseDealDto> listMultiplexHouseDeal();

	//오피스텔
	public List<HouseDealDto> listOfficetelHouseDeal();
	
	// 동으로 검색해서 특정 단독 다가구 리스트 가져오기 
	public List<HouseDealDto> getPrivateHouseDeal(String sgg_cd, String bjdong_cd ) throws SQLException;

	// 동으로 검색해서 특정 다세대주택 리스트 가져오기 
	public List<HouseDealDto> getMultiplexHouseDeal(String sgg_cd, String bjdong_cd) throws SQLException;

	// 동으로 검색해서 특정 오피스텔 리스트 가져오기 
	public List<HouseDealDto> getOfficetelHouseDeal(String sgg_cd, String bjdong_cd) throws SQLException;
	
	public List<ApartDealDto> getAptByaptCode(String aptCode) throws SQLException;

	public List<HouseDealDto> getMultiplexHouseDealDetail(String sgg_cd, String bjdong_cd, String bubn) throws SQLException;

}
