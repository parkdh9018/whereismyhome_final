package com.ssafy.vue.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.vue.model.ApartDealDto;
import com.ssafy.vue.model.HouseDealDto;

public interface HouseDealService {
	//아파트
	public List<ApartDealDto> getAptByaptCode(String aptCode) throws Exception;
	
	
	public List<HouseDealDto> listPrivateHouseDeal() throws Exception;
	public List<HouseDealDto> listMultiplexHouseDeal() throws Exception;
	public List<HouseDealDto> listOfiicetelHouseDeal() throws Exception;
	
	public List<HouseDealDto> getPrivateHouseDeal(String sgg_cd, String bjdong_nm ) throws SQLException;
	public List<HouseDealDto> getMultiplexHouseDeal(String sgg_cd, String bjdong_nm) throws SQLException;
	public List<HouseDealDto> getOfficetelHouseDeal(String sgg_cd, String bjdong_nm) throws SQLException;


	public List<HouseDealDto> getMultiplexHouseDealDetail(String sgg_cd, String bjdong_cd, String bubn) throws SQLException;
	
}
