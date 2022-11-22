package com.ssafy.vue.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.vue.model.ApartDealDto;
import com.ssafy.vue.model.HouseDealDto;

public interface HouseDealService {
	//아파트
	public List<ApartDealDto> getAptByaptCode(String aptCode) throws Exception;
	
	//다세대주택
	public List<HouseDealDto> listMultiplexHouseDeal() throws Exception;
	public List<HouseDealDto> getMultiplexHouseDeal(String sgdong_cd) throws SQLException;
	public List<HouseDealDto> getMultiplexHouseDealDetail(String multiplexhouseCode) throws SQLException;
	
	//오피스텔
	public List<HouseDealDto> listOfiicetelDeal() throws Exception;
	public List<HouseDealDto> getOfficetelDeal(String sgdong_cd) throws SQLException;
	public List<HouseDealDto> getOfficetelDealDetail(String officetelCode) throws SQLException;
	
	//단독주택
	public List<HouseDealDto> listPrivateHouseDeal() throws Exception;
	public List<HouseDealDto> getPrivateHouseDeal(String sgg_cd, String bjdong_nm ) throws SQLException;

	
}
