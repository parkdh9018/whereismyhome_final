package com.ssafy.vue.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.vue.model.ApartDealDto;

public interface ApartDealService {
	List<ApartDealDto> getAptByaptCode(Map<String, String> param) throws Exception;
}
