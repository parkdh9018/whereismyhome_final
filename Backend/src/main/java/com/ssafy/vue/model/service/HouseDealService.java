package com.ssafy.vue.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.vue.model.HouseDealDto;

public interface HouseDealService {

	public List<HouseDealDto> listPrivateHouseDeal() throws Exception;
	public List<HouseDealDto> listMultiplexHouseDeal() throws Exception;
	public List<HouseDealDto> listOfiicetelHouseDeal() throws Exception;
	public List<HouseDealDto> getPrivateHouseDeal(String bjdong_nm) throws SQLException;
	public List<HouseDealDto> getMultiplexHouseDeal(String bjdong_nm) throws SQLException;
	public List<HouseDealDto> getOfficetelHouseDeal(String bjdong_nm) throws SQLException;
}
