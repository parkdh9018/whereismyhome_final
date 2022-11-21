package com.ssafy.vue.model.service;

import java.util.List;

import com.ssafy.vue.model.HousedealDto;
import com.ssafy.vue.model.RentDto;

public interface HouseDealService {

	public List<HousedealDto> listPrivateHouseDeal() throws Exception;

}
