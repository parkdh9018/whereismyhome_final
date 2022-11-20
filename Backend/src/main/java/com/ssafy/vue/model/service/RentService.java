package com.ssafy.vue.model.service;

import java.util.List;

import com.ssafy.vue.model.BoardDto;
import com.ssafy.vue.model.RentDto;

public interface RentService {

	List<RentDto> listRent() throws Exception;

}
