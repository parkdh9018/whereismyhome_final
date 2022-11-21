package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.vue.model.ApartDealDto;


public interface ApartDealMapper {
	
	List<ApartDealDto> getAptByaptCode(Map<String, String> param) throws SQLException;
}
