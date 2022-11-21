package com.ssafy.vue.model.mapper;

import java.util.List;

import com.ssafy.vue.model.HousedealDto;
import com.ssafy.vue.model.RentDto;

public interface HouseDealMapper {

	// 단독다가구 
	public List<HousedealDto> listPrivateHouseDeal() ;

}
